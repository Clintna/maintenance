package com.bhhyy.aircraft.maintenance.materials.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaDefectMaterials;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
public interface MaDefectMaterialsService extends IService<MaDefectMaterials> {
    /**
     * 新增故障件信息
     *
     * @param maDefectMaterials
     */
    void createMaDefectMaterials(MaDefectMaterials maDefectMaterials);

    /**
     * 删除故障件
     *
     * @param defectMaterialId
     */
    void deleteMaDefectMaterials(String defectMaterialId);

    /**
     * 修改故障件信息
     *
     * @param maDefectMaterials
     */
    void updateMaDefectMaterials(MaDefectMaterials maDefectMaterials);

    /**
     * 查询故障件信息
     *
     * @param defectMaterialId
     * @return MaDefectMaterials
     */
    MaDefectMaterials queryMaDefectMaterials(String defectMaterialId);

    /**
     * 分页查询故障件信息
     *
     * @param request request
     * @return IPage
     */
    IPage<MaDefectMaterials> queryMaDefectMaterialsList(QueryRequest request);

}
