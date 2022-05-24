package com.bhhyy.aircraft.maintenance.materials.controller;

import com.bhhyy.aircraft.maintenance.common.annotation.ControllerEndpoint;
import com.bhhyy.aircraft.maintenance.common.controller.BaseController;
import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.token.Token;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.service.MaBaseInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Validated
@RequiredArgsConstructor
@RequestMapping("/maBaseInfo")
public class MaBaseInfoController extends BaseController {
    private final MaBaseInfoService maBaseInfoService;

    @PostMapping("create")
    @ControllerEndpoint(operation = "新增航材", exceptionMessage = "新增航材失败")
    @Token
    public FlightResponse createMaBaseInfo(@Valid @RequestBody MaBaseInfo maBaseInfo) {
        maBaseInfoService.createMaBaseInfo(maBaseInfo);
        return new FlightResponse().success();
    }

    @PostMapping("delete/{pieceNumber}")
    @ControllerEndpoint(operation = "删除航材", exceptionMessage = "删除航材失败")
    @Token
    public FlightResponse deleteMaBaseInfo(@NotBlank(message = "{pieceNumber}") @PathVariable String pieceNumber) {
        maBaseInfoService.deleteMaBaseInfo(pieceNumber);
        return new FlightResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "更新航材", exceptionMessage = "更新航材失败")
    @Token
    public FlightResponse updateMaBaseInfo(@Valid @RequestBody MaBaseInfo maBaseInfo) {
        maBaseInfoService.updateMaBaseInfo(maBaseInfo);
        return new FlightResponse().success();
    }

    @PostMapping("query/{pieceNumber}")
    @ControllerEndpoint(operation = "查询航材", exceptionMessage = "查询航材失败")
    @Token
    public FlightResponse queryMaBaseInfo(@NotBlank(message = "{pieceNumber}") @PathVariable String pieceNumber) {
        return new FlightResponse().success().data(maBaseInfoService.queryMaBaseInfo(pieceNumber));
    }

    @PostMapping("list")
    @RequiresPermissions("user:view")
    @Token
    public FlightResponse userList(@RequestBody QueryRequest request) {
        return new FlightResponse().success()
                .data(getDataTable(maBaseInfoService.queryMaBaseInfoList(request)));
    }
}
