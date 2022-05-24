package com.bhhyy.aircraft.maintenance.materials.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaStorageOutRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
public interface MaStorageOutRecordService extends IService<MaStorageOutRecord> {
    /**
     * 新增航材出库记录
     *
     * @param maStorageOutRecord
     * @return
     */
    String createMaStorageOutRecord(MaStorageOutRecord maStorageOutRecord);

    /**
     * 分页查询航材入库记录
     *
     * @param request request
     * @return IPage
     */
    IPage<MaStorageOutRecord> queryMaStorageOutRecordList(QueryRequest request);

    /**
     * 根据航材件号和合同号查询航材出库记录
     *
     * @param pieceNumber
     * @param contractNumber
     * @return
     */
    List<MaStorageOutRecord> queryRecordByContract(String pieceNumber, String contractNumber);
}
