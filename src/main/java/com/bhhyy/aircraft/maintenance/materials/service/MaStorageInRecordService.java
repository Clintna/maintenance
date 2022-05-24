package com.bhhyy.aircraft.maintenance.materials.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhhyy.aircraft.maintenance.common.dto.DeleteStorInRecordDTO;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.aggregates.MaStorInRecordAggregate;
import com.bhhyy.aircraft.maintenance.materials.bean.MaStorageInRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
public interface MaStorageInRecordService extends IService<MaStorageInRecord> {
    /**
     * 新增航材入库记录
     *
     * @param maStorageInRecord
     */
    String createMaStorageInRecord(MaStorageInRecord maStorageInRecord);

    /**
     * 删除到回收站
     *
     * @param materialStorId
     * @param loginUserInfo
     * @return
     */
    Boolean deleteMaBaseInfoToBin(String materialStorId, StUserInfo loginUserInfo);

    /**
     * 删除航材入库记录
     *
     * @param materialStorIds
     */
    DeleteStorInRecordDTO deleteMaStorageInRecord(List<String> materialStorIds);

    /**
     * 修改航材入库记录
     *
     * @param maStorageInRecord
     */
    void updateMaStorageInRecord(MaStorageInRecord maStorageInRecord);

    /**
     * 根据id查询入库详细信息聚合类
     *
     * @param materialStorId
     * @return MaBaseInfo
     */
    MaStorInRecordAggregate queryMaStorageInRecord(String materialStorId);

    /**
     * 分页查询航材入库记录
     *
     * @param request request
     * @return IPage
     */
    IPage<MaStorageInRecord> queryMaStorageInRecordList(QueryRequest request);
}
