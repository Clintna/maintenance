package com.bhhyy.aircraft.maintenance.materials.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.mapper.MaBaseInfoMapper;
import com.bhhyy.aircraft.maintenance.materials.service.MaBaseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class MaBaseInfoServiceImpl extends ServiceImpl<MaBaseInfoMapper, MaBaseInfo> implements MaBaseInfoService {

    private final MaBaseInfoMapper maBaseInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMaBaseInfo(MaBaseInfo maBaseInfo) {
        maBaseInfo.setCreateTime(LocalDateTime.now());
        maBaseInfo.setModifyTime(LocalDateTime.now());
        save(maBaseInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMaBaseInfo(String pieceNumber) {
        removeById(pieceNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateMaBaseInfo(MaBaseInfo maBaseInfo) {
        maBaseInfo.setModifyTime(LocalDateTime.now());
        return updateById(maBaseInfo);
    }

    @Override
    public MaBaseInfo queryMaBaseInfo(String pieceNumber) {
        return getOne(new LambdaQueryWrapper<MaBaseInfo>().eq(MaBaseInfo::getPieceNumber, pieceNumber));
    }

    @Override
    public IPage<MaBaseInfo> queryMaBaseInfoList(QueryRequest request) {
        QueryWrapper<MaBaseInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return maBaseInfoMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }
}
