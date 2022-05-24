package com.bhhyy.aircraft.maintenance.materials.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.common.dto.DeleteStorInRecordDTO;
import com.bhhyy.aircraft.maintenance.common.dto.ErrorUtilDTO;
import com.bhhyy.aircraft.maintenance.common.exceptions.BizException;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.tools.TaskIdGenerator;
import com.bhhyy.aircraft.maintenance.common.transaction.TxManager;
import com.bhhyy.aircraft.maintenance.common.utils.BeanUtilsConverter;
import com.bhhyy.aircraft.maintenance.materials.aggregates.MaStorInRecordAggregate;
import com.bhhyy.aircraft.maintenance.materials.bean.*;
import com.bhhyy.aircraft.maintenance.materials.convertor.ConvertorUtil;
import com.bhhyy.aircraft.maintenance.materials.mapper.MaStorageInRecordMapper;
import com.bhhyy.aircraft.maintenance.materials.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MaStorageInRecordServiceImpl extends ServiceImpl<MaStorageInRecordMapper, MaStorageInRecord> implements MaStorageInRecordService {

    @Resource
    private final TxManager txManager;
    @Resource
    private final MaHangLableRecordService maHangLableRecordService;
    @Resource
    private final MaBaseInfoService maBaseInfoService;
    @Resource
    private final MaShelvesInfoService maShelvesInfoService;
    @Resource
    private final MaStorageInRecordMapper maStorageInRecordMapper;
    @Resource
    private final MaSupplierInfoService maSupplierInfoService;
    @Resource
    private final MaStorageOutRecordService maStorageOutRecordService;

    @Override
    public String createMaStorageInRecord(MaStorageInRecord maStorageInRecord) {
        maStorageInRecord.setMaterialStorId(TaskIdGenerator.nextId());
        maStorageInRecord.setCreateTime(LocalDateTime.now());
        maStorageInRecord.setModifyTime(LocalDateTime.now());
        if (maStorageInRecord.getIsLife().equals(false)) {
            maStorageInRecord.setLifeDue(null);
        }
        try {
            String create = txManager.call(() -> {
                save(maStorageInRecord);
                MaHangLableRecord maHangLableRecord = new MaHangLableRecord();
                BeanUtilsConverter.copyBean(maHangLableRecord, maStorageInRecord);
                maHangLableRecordService.createMaHangLableRecord(maHangLableRecord);
                MaBaseInfo maBaseInfo = maBaseInfoService.queryMaBaseInfo(maStorageInRecord.getPieceNumber());
                if (null == maBaseInfo) {
//                    throw new RuntimeException("无航材记录，请添加航材信息");
                    MaBaseInfo maBaseInfo1 = ConvertorUtil.inRecordConverToBaseInfo(maStorageInRecord);
                    maBaseInfoService.createMaBaseInfo(maBaseInfo1);
                } else {
                    maBaseInfo.setMaterialInventory(maBaseInfo.getMaterialInventory() + maStorageInRecord.getMaterialQuantity());
                    maBaseInfoService.updateMaBaseInfo(maBaseInfo);
                }
                MaShelvesInfo maShelvesInfo = maShelvesInfoService.queryMaShelvesInfo(maStorageInRecord.getShelfId());
                if (null == maShelvesInfo) {
                    throw new RuntimeException("无货架信息，请添加货架信息");
                } else {
                    LinkedHashMap<String, Integer> map = JSONObject.parseObject(maShelvesInfo.getStorMaterial(), LinkedHashMap.class, Feature.OrderedField);
                    String s = maStorageInRecord.getMaterialStorId().concat("_").concat(maStorageInRecord.getPieceNumber());
                    if (null == map) {
                        map = new LinkedHashMap<>();
                    }
                    map.put(s, maStorageInRecord.getMaterialQuantity());
                    maShelvesInfo.setStorMaterial(JSON.toJSONString(map));
                    maShelvesInfoService.updateMaShelvesInfo(maShelvesInfo);
                }
                return maStorageInRecord.getMaterialStorId();
            });
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
        return maStorageInRecord.getMaterialStorId();
    }

    @Override
    public Boolean deleteMaBaseInfoToBin(String materialStorId, StUserInfo stUserInfo) {
        try {
            MaStorageInRecord maStorageInRecord = getOne(new LambdaQueryWrapper<MaStorageInRecord>()
                    .eq(MaStorageInRecord::getMaterialStorId, materialStorId));
            if (null == maStorageInRecord) {
                throw new BizException(materialStorId, "查询不到入库记录");
            }
            //先判断出库单是否有该入库单的出库信息，如果有，报错
            final List<MaStorageOutRecord> maStorageOutRecords = maStorageOutRecordService.queryRecordByContract(maStorageInRecord.getPieceNumber(), maStorageInRecord.getContractNumber());
            if (!maStorageOutRecords.isEmpty()) {
                throw new BizException(materialStorId, "该库存已有出库记录,请检查后重试");
            } else {
                return txManager.call(() -> {
                    //减库存
                    MaBaseInfo maBaseInfo = maBaseInfoService.queryMaBaseInfo(maStorageInRecord.getPieceNumber());
                    maBaseInfo.setMaterialInventory(maBaseInfo.getMaterialInventory() - maStorageInRecord.getMaterialQuantity());
                    maBaseInfoService.updateMaBaseInfo(maBaseInfo);
                    //减货架相关信息
                    MaShelvesInfo maShelvesInfo = maShelvesInfoService.queryMaShelvesInfo(maStorageInRecord.getShelfId());
                    LinkedHashMap<String, Integer> map = JSONObject.parseObject(maShelvesInfo.getStorMaterial(), LinkedHashMap.class, Feature.OrderedField);
                    String s = maStorageInRecord.getMaterialStorId().concat("_").concat(maStorageInRecord.getPieceNumber());
                    map.remove(s);
                    maShelvesInfo.setStorMaterial(JSON.toJSONString(map));
                    maShelvesInfoService.updateMaShelvesInfo(maShelvesInfo);
                    removeById(materialStorId);
                    log.info("用户：{},删除入库记录,{}", stUserInfo.getUserName(), JSONObject.toJSONString(maStorageInRecord));
                    return true;
                });
            }
        } catch (BizException e) {
            log.error("删除入库记录异常，请检查原因，用户是{}", stUserInfo.getUserName());
            throw new RuntimeException("用户" + stUserInfo.getUserName() + "删除入库记录失败，失败原因是：" + e.getMsg());
        } catch (Exception e) {
            log.error("删除入库记录异常，请检查原因，用户是{}", stUserInfo.getUserName());
            throw new RuntimeException("删除入库记录异常，请检查原因");
        }
    }

    @Override
    public DeleteStorInRecordDTO deleteMaStorageInRecord(List<String> materialStorIds) {
        DeleteStorInRecordDTO deleteStorInRecordDTO = new DeleteStorInRecordDTO();
        List<String> deletedRecords = new ArrayList<>();
        List<ErrorUtilDTO> unDeletedRecords = new ArrayList<>();
        materialStorIds.forEach(m -> {
            try {
                MaStorageInRecord maStorageInRecord = getOne(new LambdaQueryWrapper<MaStorageInRecord>()
                        .eq(MaStorageInRecord::getMaterialStorId, m));
                if (null == maStorageInRecord) {
                    throw new BizException(m, "查询不到入库记录");
                }
                //先判断出库单是否有该入库单的出库信息，如果有，报错
                final List<MaStorageOutRecord> maStorageOutRecords = maStorageOutRecordService.queryRecordByContract(maStorageInRecord.getPieceNumber(), maStorageInRecord.getContractNumber());
                if (!maStorageOutRecords.isEmpty()) {
                    throw new BizException(m, "该库存已有出库记录,请检查后重试");
                } else {
                    txManager.call(() -> {
                        //减库存
                        MaBaseInfo maBaseInfo = maBaseInfoService.queryMaBaseInfo(maStorageInRecord.getPieceNumber());
                        maBaseInfo.setMaterialInventory(maBaseInfo.getMaterialInventory() - maStorageInRecord.getMaterialQuantity());
                        maBaseInfoService.updateMaBaseInfo(maBaseInfo);
                        //减货架相关信息
                        MaShelvesInfo maShelvesInfo = maShelvesInfoService.queryMaShelvesInfo(maStorageInRecord.getShelfId());
                        LinkedHashMap<String, Integer> map = JSONObject.parseObject(maShelvesInfo.getStorMaterial(), LinkedHashMap.class, Feature.OrderedField);
                        String s = maStorageInRecord.getMaterialStorId().concat("_").concat(maStorageInRecord.getPieceNumber());
                        map.remove(s);
                        maShelvesInfo.setStorMaterial(JSON.toJSONString(map));
                        maShelvesInfoService.updateMaShelvesInfo(maShelvesInfo);
                        removeById(m);
                        deletedRecords.add(m);
                        log.info("用户：{},删除入库记录,{}", "userName", JSONObject.toJSONString(maStorageInRecord));
                        return true;
                    });
                }
            } catch (BizException e) {
                ErrorUtilDTO errorUtilDTO = new ErrorUtilDTO();
                errorUtilDTO.setId(e.getCode());
                errorUtilDTO.setMsg(e.getMsg());
                unDeletedRecords.add(errorUtilDTO);
            } catch (Exception e) {
                log.error("删除入库记录异常，请检查原因，id是{}", m);
                throw new RuntimeException("删除入库记录异常，请检查原因");
            }
        });
        deleteStorInRecordDTO.setDeletedRecords(deletedRecords);
        deleteStorInRecordDTO.setUnDeletedRecords(unDeletedRecords);
        return deleteStorInRecordDTO;
    }

    @Override
    public void updateMaStorageInRecord(MaStorageInRecord maStorageInRecord) {
        Logger log = Logger.getLogger("updateMaStorageInRecord");
        try {
            Boolean b = txManager.call(() -> {
                MaStorageInRecord maStorageInRecord1 = getOne(new LambdaQueryWrapper<MaStorageInRecord>()
                        .eq(MaStorageInRecord::getMaterialStorId, maStorageInRecord.getMaterialStorId()));
                MaShelvesInfo maShelvesInfo = maShelvesInfoService.queryMaShelvesInfo(maStorageInRecord1.getShelfId());
                LinkedHashMap<String, Integer> map = JSONObject.parseObject(maShelvesInfo.getStorMaterial(), LinkedHashMap.class, Feature.OrderedField);
                String s = maStorageInRecord1.getMaterialStorId().concat("_").concat(maStorageInRecord1.getPieceNumber());
                if (null != maStorageInRecord.getShelfId()) {
                    map.remove(s);
                    maShelvesInfo.setStorMaterial(JSON.toJSONString(map));
                    maShelvesInfoService.updateMaShelvesInfo(maShelvesInfo);
                    MaShelvesInfo maShelvesInfo1 = maShelvesInfoService.queryMaShelvesInfo(maStorageInRecord.getShelfId());
                    if (null == maShelvesInfo1) {
                        throw new RuntimeException("无货架信息，请添加货架信息");
                    } else {
                        LinkedHashMap<String, Integer> map1 = JSONObject.parseObject(maShelvesInfo1.getStorMaterial(), LinkedHashMap.class, Feature.OrderedField);
                        String ss = maStorageInRecord1.getMaterialStorId().concat("_").concat(maStorageInRecord1.getPieceNumber());
                        if (null == map1) {
                            map1 = new LinkedHashMap<>();
                        }
                        map1.put(ss, maStorageInRecord1.getMaterialQuantity());
                        maShelvesInfo1.setStorMaterial(JSON.toJSONString(map1));
                        maShelvesInfoService.updateMaShelvesInfo(maShelvesInfo1);
                    }
                }
                if (null != maStorageInRecord.getPieceNumber()) {
                    map.put(s, maStorageInRecord.getMaterialQuantity());
                    maShelvesInfo.setStorMaterial(JSON.toJSONString(map));
                    maShelvesInfoService.updateMaShelvesInfo(maShelvesInfo);
                    MaBaseInfo maBaseInfo = maBaseInfoService.queryMaBaseInfo(maStorageInRecord.getPieceNumber());
                    if (null == maBaseInfo) {
                        throw new RuntimeException("无航材记录，请添加航材信息");
                    } else {
                        maBaseInfo.setMaterialInventory(maBaseInfo.getMaterialInventory() - maStorageInRecord1.getMaterialQuantity() + maStorageInRecord.getMaterialQuantity());
                        maBaseInfoService.updateMaBaseInfo(maBaseInfo);
                    }
                }
                maStorageInRecord.setModifyTime(LocalDateTime.now());
                updateById(maStorageInRecord);
                return true;
            });
        } catch (BizException e) {
            log.warning(e.getMsg());
        }
    }

    @Override
    public MaStorInRecordAggregate queryMaStorageInRecord(String materialStorId) {
        MaStorageInRecord maStorageInRecord = getOne(new LambdaQueryWrapper<MaStorageInRecord>()
                .eq(MaStorageInRecord::getMaterialStorId, materialStorId));
        if (null == maStorageInRecord){
            throw new RuntimeException("找不到该记录");
        }
        MaBaseInfo maBaseInfo = maBaseInfoService.queryMaBaseInfo(maStorageInRecord.getPieceNumber());
        MaShelvesInfo maShelvesInfo = maShelvesInfoService.queryMaShelvesInfo(maStorageInRecord.getShelfId());
        MaSupplierInfo maSupplierInfo = maSupplierInfoService.queryMaSupplierInfo(maStorageInRecord.getVenderId());
        MaStorInRecordAggregate maStorInRecordAggregate = new MaStorInRecordAggregate();
        maStorInRecordAggregate.setMaBaseInfo(maBaseInfo);
        maStorInRecordAggregate.setMaShelvesInfo(maShelvesInfo);
        maStorInRecordAggregate.setMaStorageInRecord(maStorageInRecord);
        maStorInRecordAggregate.setMaSupplierInfo(maSupplierInfo);
        return maStorInRecordAggregate;
    }

    @Override
    public IPage<MaStorageInRecord> queryMaStorageInRecordList(QueryRequest request) {
        QueryWrapper<MaStorageInRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return maStorageInRecordMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }

}
