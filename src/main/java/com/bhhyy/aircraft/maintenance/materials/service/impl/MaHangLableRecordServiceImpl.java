package com.bhhyy.aircraft.maintenance.materials.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaHangLableRecord;
import com.bhhyy.aircraft.maintenance.materials.mapper.MaHangLableRecordMapper;
import com.bhhyy.aircraft.maintenance.materials.service.MaHangLableRecordService;
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
public class MaHangLableRecordServiceImpl extends ServiceImpl<MaHangLableRecordMapper, MaHangLableRecord> implements MaHangLableRecordService {

    private final MaHangLableRecordMapper maHangLableRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMaHangLableRecord(MaHangLableRecord hangLableRecord) {
        hangLableRecord.setCreateTime(LocalDateTime.now());
        hangLableRecord.setModifyTime(LocalDateTime.now());
        save(hangLableRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMaHangLableRecord(String hangLableId) {
        removeById(hangLableId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMaHangLableRecord(MaHangLableRecord maHangLableRecord) {
        maHangLableRecord.setModifyTime(LocalDateTime.now());
        updateById(maHangLableRecord);
    }

    @Override
    public MaHangLableRecord queryMaHangLableRecord(String hangLableId) {
        return getOne(new LambdaQueryWrapper<MaHangLableRecord>().eq(MaHangLableRecord::getHangLableId, hangLableId));
    }

    @Override
    public IPage<MaHangLableRecord> queryMaHangLableRecordList(QueryRequest request) {
        return maHangLableRecordMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()), null);
    }
}
