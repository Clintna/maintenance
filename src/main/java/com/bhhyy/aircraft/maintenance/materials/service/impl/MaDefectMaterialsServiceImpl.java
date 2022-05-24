package com.bhhyy.aircraft.maintenance.materials.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.tools.TaskIdGenerator;
import com.bhhyy.aircraft.maintenance.materials.bean.MaDefectMaterials;
import com.bhhyy.aircraft.maintenance.materials.mapper.MaBaseInfoMapper;
import com.bhhyy.aircraft.maintenance.materials.mapper.MaDefectMaterialsMapper;
import com.bhhyy.aircraft.maintenance.materials.service.MaDefectMaterialsService;
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
public class MaDefectMaterialsServiceImpl extends ServiceImpl<MaDefectMaterialsMapper, MaDefectMaterials> implements MaDefectMaterialsService {

    private final MaDefectMaterialsMapper maDefectMaterialsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMaDefectMaterials(MaDefectMaterials maDefectMaterials) {
        maDefectMaterials.setCreateTime(LocalDateTime.now());
        maDefectMaterials.setModifyTime(LocalDateTime.now());
        maDefectMaterials.setDefectMaterialId(TaskIdGenerator.nextId());
        save(maDefectMaterials);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMaDefectMaterials(String defectMaterialId) {
        removeById(defectMaterialId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMaDefectMaterials(MaDefectMaterials maDefectMaterials) {
        maDefectMaterials.setModifyTime(LocalDateTime.now());
        updateById(maDefectMaterials);
    }

    @Override
    public MaDefectMaterials queryMaDefectMaterials(String defectMaterialId) {
        return getOne(new LambdaQueryWrapper<MaDefectMaterials>().eq(MaDefectMaterials::getDefectMaterialId, defectMaterialId));
    }

    @Override
    public IPage<MaDefectMaterials> queryMaDefectMaterialsList(QueryRequest request) {
        return maDefectMaterialsMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()), null);
    }
}
