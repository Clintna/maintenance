package com.bhhyy.aircraft.maintenance.materials.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaHangLableRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
public interface MaHangLableRecordService extends IService<MaHangLableRecord> {
    /**
     * 新增挂签
     *
     * @param hangLableRecord
     */
    void createMaHangLableRecord(MaHangLableRecord hangLableRecord);

    /**
     * 删除挂签
     *
     * @param hangLableId
     */
    void deleteMaHangLableRecord(String hangLableId);

    /**
     * 修改挂签信息
     *
     * @param maHangLableRecord
     */
    void updateMaHangLableRecord(MaHangLableRecord maHangLableRecord);

    /**
     * 查询挂签信息
     *
     * @param hangLableId
     * @return MaBaseInfo
     */
    MaHangLableRecord queryMaHangLableRecord(String hangLableId);

    /**
     * 分页查询挂签信息
     *
     * @param request request
     * @return IPage
     */
    IPage<MaHangLableRecord> queryMaHangLableRecordList(QueryRequest request);
}
