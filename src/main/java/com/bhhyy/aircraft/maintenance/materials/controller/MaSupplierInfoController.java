package com.bhhyy.aircraft.maintenance.materials.controller;

import com.bhhyy.aircraft.maintenance.common.annotation.ControllerEndpoint;
import com.bhhyy.aircraft.maintenance.common.controller.BaseController;
import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.token.Token;
import com.bhhyy.aircraft.maintenance.materials.bean.MaSupplierInfo;
import com.bhhyy.aircraft.maintenance.materials.service.MaSupplierInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/maSupplierInfo")
public class MaSupplierInfoController extends BaseController {
    @Resource
    private MaSupplierInfoService maSupplierInfoService;

    @PostMapping("create")
    @ControllerEndpoint(operation = "新增供应商", exceptionMessage = "新增供应商失败")
    @Token
    public FlightResponse createMaSupplierInfo(@Valid @RequestBody MaSupplierInfo maSupplierInfo) {
        return new FlightResponse().success().put("supplierId", maSupplierInfoService.createMaSupplierInfo(maSupplierInfo));
    }

    @PostMapping("queryIdByName/{venderName}")
    @ControllerEndpoint(operation = "根据供应商名字获取id", exceptionMessage = "获取供应商id失败")
    @Token
    public FlightResponse queryIdByName(@NotBlank(message = "{venderName}") @PathVariable String venderName) {
        return new FlightResponse().success().put("venderId", maSupplierInfoService.queryIdByName(venderName));
    }

    @PostMapping("list")
    @ControllerEndpoint(operation = "批量查询供应商", exceptionMessage = "批量查询供应商失败")
    @Token
    public FlightResponse supplierList(QueryRequest request) {
        return new FlightResponse().success()
                .data(getDataTable(maSupplierInfoService.queryMaSupplierInfoList(request)));
    }

}
