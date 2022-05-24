package com.bhhyy.aircraft.maintenance.materials.convertor;

import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaStorageInRecord;

import java.time.LocalDateTime;

/**
 * @Author: Jinglin
 * @Date: 2022/05/09
 * @Description:
 */
public class ConvertorUtil {
    public static final String DEFAULT_MATERIAL_TYPE = "default_type";

    public static MaBaseInfo inRecordConverToBaseInfo(MaStorageInRecord maStorageInRecord) {
        MaBaseInfo maBaseInfo = new MaBaseInfo();
        maBaseInfo.setMaterialInventory(maStorageInRecord.getMaterialQuantity());
        maBaseInfo.setMaterialName(maStorageInRecord.getMaterialName());
        maBaseInfo.setPieceNumber(maStorageInRecord.getPieceNumber());
        maBaseInfo.setIsLife(maStorageInRecord.getIsLife());
        maBaseInfo.setLifeDue(maStorageInRecord.getLifeDue());
        maBaseInfo.setShelvesNumber(maStorageInRecord.getShelfId());
        maBaseInfo.setMaterialType(DEFAULT_MATERIAL_TYPE);
        maBaseInfo.setModifyTime(LocalDateTime.now());
        maBaseInfo.setCreateTime(LocalDateTime.now());
        return maBaseInfo;
    }
}
