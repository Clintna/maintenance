package com.bhhyy.aircraft.maintenance.materials.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaShelvesInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaSupplierInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
public interface MaSupplierInfoService extends IService<MaSupplierInfo> {
    /**
     * 新增供应商
     *
     * @param maSupplierInfo
     * @return
     */
    String createMaSupplierInfo(MaSupplierInfo maSupplierInfo);

    /**
     * 查询供应商信息
     *
     * @param supplierId
     */
    MaSupplierInfo queryMaSupplierInfo(String supplierId);

    /**
     * 根据供应商名获取id
     *
     * @param venderName
     * @return
     */
    String queryIdByName(String venderName);

    /**
     * 分页查询供应商信息
     *
     * @param request request
     * @return IPage
     */
    IPage<MaSupplierInfo> queryMaSupplierInfoList(QueryRequest request);
}
