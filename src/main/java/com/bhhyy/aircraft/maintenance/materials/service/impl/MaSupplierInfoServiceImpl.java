package com.bhhyy.aircraft.maintenance.materials.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.exceptions.BizException;
import com.bhhyy.aircraft.maintenance.materials.bean.MaShelvesInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaSupplierInfo;
import com.bhhyy.aircraft.maintenance.materials.mapper.MaSupplierInfoMapper;
import com.bhhyy.aircraft.maintenance.materials.service.MaSupplierInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
@Service
public class MaSupplierInfoServiceImpl extends ServiceImpl<MaSupplierInfoMapper, MaSupplierInfo> implements MaSupplierInfoService {

    @Resource
    private MaSupplierInfoMapper maSupplierInfoMapper;

    @Override
    public String createMaSupplierInfo(MaSupplierInfo maSupplierInfo) {
        maSupplierInfo.setVenderId(UUID.randomUUID().toString());
        maSupplierInfo.setCreateTime(LocalDateTime.now());
        maSupplierInfo.setModifyTime(LocalDateTime.now());
        save(maSupplierInfo);
        return maSupplierInfo.getVenderId();
    }

    @Override
    public MaSupplierInfo queryMaSupplierInfo(String supplierId) {
        return getOne(new LambdaQueryWrapper<MaSupplierInfo>()
                .eq(MaSupplierInfo::getVenderId, supplierId));
    }

    @Override
    public String queryIdByName(String venderName) {
        return getOne(new LambdaQueryWrapper<MaSupplierInfo>().eq(MaSupplierInfo::getVenderName, venderName)).getVenderId();
    }

    @Override
    public IPage<MaSupplierInfo> queryMaSupplierInfoList(QueryRequest request) {
        QueryWrapper<MaSupplierInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return maSupplierInfoMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }
}
