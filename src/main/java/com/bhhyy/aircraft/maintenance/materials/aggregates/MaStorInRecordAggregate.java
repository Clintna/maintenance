package com.bhhyy.aircraft.maintenance.materials.aggregates;

import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaShelvesInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaStorageInRecord;
import com.bhhyy.aircraft.maintenance.materials.bean.MaSupplierInfo;
import lombok.Data;

/**
 * @Author: Jinglin
 * @Date: 2022/04/27
 * @Description: 航材入库信息聚合类
 */
@Data
public class MaStorInRecordAggregate {

    /**
     * 入库记录信息记录
     */
    private MaStorageInRecord maStorageInRecord;

    /**
     * 货架信息
     */
    private MaShelvesInfo maShelvesInfo;

    /**
     * 航材基础信息
     */
    private MaBaseInfo maBaseInfo;

    /**
     * 供应商信息
     */
    private MaSupplierInfo maSupplierInfo;
}
