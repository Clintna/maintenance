package com.bhhyy.aircraft.maintenance.materials.controller;

import com.bhhyy.aircraft.maintenance.common.annotation.ControllerEndpoint;
import com.bhhyy.aircraft.maintenance.common.controller.BaseController;
import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.token.Token;
import com.bhhyy.aircraft.maintenance.materials.bean.MaStorageOutRecord;
import com.bhhyy.aircraft.maintenance.materials.service.MaStorageOutRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/maStorageOutRecord")
@RequiredArgsConstructor
public class MaStorageOutRecordController extends BaseController {
    @Resource
    private final MaStorageOutRecordService maStorageOutRecordService;

    @PostMapping("create")
    @ControllerEndpoint(operation = "新增出库记录", exceptionMessage = "新增出库记录失败")
    @Token
    public FlightResponse createMaBaseInfo(@Valid @RequestBody MaStorageOutRecord maStorageOutRecord) {
        return new FlightResponse().success().put("materialStorId", maStorageOutRecordService.createMaStorageOutRecord(maStorageOutRecord));
    }

    @PostMapping("list")
    @ControllerEndpoint(operation = "查询入库记录", exceptionMessage = "查询入库记录失败")
    @Token
    public FlightResponse userList(QueryRequest request) {
        return new FlightResponse().success()
                .data(getDataTable(maStorageOutRecordService.queryMaStorageOutRecordList(request)));
    }
}
