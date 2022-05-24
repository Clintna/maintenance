package com.bhhyy.aircraft.maintenance.materials.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
public interface MaBaseInfoService extends IService<MaBaseInfo> {
    /**
     * 新增航材
     *
     * @param maBaseInfo
     */
    void createMaBaseInfo(MaBaseInfo maBaseInfo);

    /**
     * 删除航材
     *
     * @param pieceNumber
     */
    void deleteMaBaseInfo(String pieceNumber);

    /**
     * 修改航材信息
     *
     * @param maBaseInfo
     * @return
     */
    Boolean updateMaBaseInfo(MaBaseInfo maBaseInfo);

    /**
     * 查询航材信息
     *
     * @param pieceNumber
     * @return MaBaseInfo
     */
    MaBaseInfo queryMaBaseInfo(String pieceNumber);

    /**
     * 分页查询航材信息
     *
     * @param request request
     * @return IPage
     */
    IPage<MaBaseInfo> queryMaBaseInfoList(QueryRequest request);
}
