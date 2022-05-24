package com.bhhyy.aircraft.maintenance.materials.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.common.exceptions.BizException;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.tools.TaskIdGenerator;
import com.bhhyy.aircraft.maintenance.common.transaction.TxManager;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaStorageOutRecord;
import com.bhhyy.aircraft.maintenance.materials.mapper.MaStorageOutRecordMapper;
import com.bhhyy.aircraft.maintenance.materials.service.MaBaseInfoService;
import com.bhhyy.aircraft.maintenance.materials.service.MaStorageOutRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
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
public class MaStorageOutRecordServiceImpl extends ServiceImpl<MaStorageOutRecordMapper, MaStorageOutRecord> implements MaStorageOutRecordService {
    @Resource
    private final TxManager txManager;

    @Resource
    private final MaBaseInfoService maBaseInfoService;

    private final MaStorageOutRecordMapper maStorageOutRecordMapper;
    private final Logger log = Logger.getLogger("MaStorageOutRecordServiceImpl.class");

    @Override
    public String createMaStorageOutRecord(MaStorageOutRecord maStorageOutRecord) {
        maStorageOutRecord.setMaterialOutId(TaskIdGenerator.nextId());
        maStorageOutRecord.setCreateTime(LocalDateTime.now());
        maStorageOutRecord.setModifyTime(LocalDateTime.now());
        try {
            txManager.call(() -> {
                save(maStorageOutRecord);
                MaBaseInfo maBaseInfo = maBaseInfoService.queryMaBaseInfo(maStorageOutRecord.getPieceNumber());
                if (null == maBaseInfo) {
                    throw new RuntimeException("未查询到对应航材信息");
                }
                maBaseInfo.setMaterialInventory(maBaseInfo.getMaterialInventory() - maStorageOutRecord.getMaterialQuantity());
                maBaseInfoService.updateMaBaseInfo(maBaseInfo);
                return true;
            });
        } catch (RuntimeException ex) {
            log.warning(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
        return maStorageOutRecord.getMaterialOutId();
    }

    @Override
    public IPage<MaStorageOutRecord> queryMaStorageOutRecordList(QueryRequest request) {
        QueryWrapper<MaStorageOutRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return maStorageOutRecordMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }

    @Override
    public List<MaStorageOutRecord> queryRecordByContract(String pieceNumber, String contractNumber) {
        QueryWrapper<MaStorageOutRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("piece_number", pieceNumber)
                .eq("contract_number", contractNumber);
        return list(queryWrapper);
    }
}
