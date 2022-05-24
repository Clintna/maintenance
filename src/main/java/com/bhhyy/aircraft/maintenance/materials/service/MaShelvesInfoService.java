package com.bhhyy.aircraft.maintenance.materials.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaShelvesInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
public interface MaShelvesInfoService extends IService<MaShelvesInfo> {
    /**
     * 新增航材
     *
     * @param maShelvesInfo
     */
    void createMaShelvesInfo(MaShelvesInfo maShelvesInfo);

    /**
     * 删除航材
     *
     * @param shelfId
     */
    void deleteMaShelvesInfo(String shelfId);

    /**
     * 修改航材信息
     *
     * @param maShelvesInfo
     */
    void updateMaShelvesInfo(MaShelvesInfo maShelvesInfo);

    /**
     * 查询航材信息
     *
     * @param shelfId
     * @return MaBaseInfo
     */
    MaShelvesInfo queryMaShelvesInfo(String shelfId);

    /**
     * 分页查询航材信息
     *
     * @param request request
     * @return IPage
     */
    IPage<MaShelvesInfo> queryMaShelvesInfoList(QueryRequest request);
}
