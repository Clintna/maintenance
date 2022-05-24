package com.bhhyy.aircraft.maintenance.materials.controller;

import com.bhhyy.aircraft.maintenance.common.annotation.ControllerEndpoint;
import com.bhhyy.aircraft.maintenance.common.controller.BaseController;
import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.token.Token;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.bean.MaShelvesInfo;
import com.bhhyy.aircraft.maintenance.materials.service.MaShelvesInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/maShelvesInfo")
@RequiredArgsConstructor
public class MaShelvesInfoController extends BaseController {
    @Resource
    private final MaShelvesInfoService maShelvesInfoService;

    @PostMapping("create")
    @ControllerEndpoint(operation = "新增货架", exceptionMessage = "新增货架失败")
    @Token
    public FlightResponse createMaShelvesInfo(@Valid @RequestBody MaShelvesInfo maShelvesInfo) {
        maShelvesInfoService.createMaShelvesInfo(maShelvesInfo);
        return new FlightResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "更新货架", exceptionMessage = "更新货架失败")
    @Token
    public FlightResponse updateMaShelvesInfo(@Valid @RequestBody MaShelvesInfo maShelvesInfo) {
        maShelvesInfoService.updateMaShelvesInfo(maShelvesInfo);
        return new FlightResponse().success();
    }

    @PostMapping("list")
    @ControllerEndpoint(operation = "批量查询货架", exceptionMessage = "批量查询货架失败")
    @Token
    public FlightResponse shelvesList(QueryRequest request) {
        return new FlightResponse().success()
                .data(getDataTable(maShelvesInfoService.queryMaShelvesInfoList(request)));
    }

}
