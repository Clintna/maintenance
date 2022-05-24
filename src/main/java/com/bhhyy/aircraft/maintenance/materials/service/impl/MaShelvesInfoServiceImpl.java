package com.bhhyy.aircraft.maintenance.materials.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaShelvesInfo;
import com.bhhyy.aircraft.maintenance.materials.mapper.MaShelvesInfoMapper;
import com.bhhyy.aircraft.maintenance.materials.service.MaShelvesInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
public class MaShelvesInfoServiceImpl extends ServiceImpl<MaShelvesInfoMapper, MaShelvesInfo> implements MaShelvesInfoService {

    @Resource
    private MaShelvesInfoMapper maShelvesInfoMapper;

    @Override
    public void createMaShelvesInfo(MaShelvesInfo maShelvesInfo) {
        maShelvesInfo.setCreateTime(LocalDateTime.now());
        maShelvesInfo.setModifyTime(LocalDateTime.now());
        save(maShelvesInfo);
    }

    @Override
    public void deleteMaShelvesInfo(String shelfId) {
        removeById(shelfId);
    }

    @Override
    public void updateMaShelvesInfo(MaShelvesInfo maShelvesInfo) {
        maShelvesInfo.setModifyTime(LocalDateTime.now());
        updateById(maShelvesInfo);
    }

    @Override
    public MaShelvesInfo queryMaShelvesInfo(String shelfId) {
        return getOne(new LambdaQueryWrapper<MaShelvesInfo>().eq(MaShelvesInfo::getShelfId, shelfId));
    }

    @Override
    public IPage<MaShelvesInfo> queryMaShelvesInfoList(QueryRequest request) {
        QueryWrapper<MaShelvesInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return maShelvesInfoMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }
}
