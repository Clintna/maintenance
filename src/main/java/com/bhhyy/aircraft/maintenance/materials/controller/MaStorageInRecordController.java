package com.bhhyy.aircraft.maintenance.materials.controller;

import com.bhhyy.aircraft.maintenance.common.annotation.ControllerEndpoint;
import com.bhhyy.aircraft.maintenance.common.controller.BaseController;
import com.bhhyy.aircraft.maintenance.common.dto.DeleteStorInRecordDTO;
import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.token.Token;
import com.bhhyy.aircraft.maintenance.materials.bean.MaStorageInRecord;
import com.bhhyy.aircraft.maintenance.materials.service.MaStorageInRecordService;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/maStorageInRecord")
public class MaStorageInRecordController extends BaseController {

    @Resource
    private final MaStorageInRecordService maStorageInRecordService;

    @Resource
    private HttpSession httpSession;

    @PostMapping("create")
    @ControllerEndpoint(operation = "新增入库记录", exceptionMessage = "新增入库记录失败")
    @Token
    public FlightResponse createMaBaseInfo(@Valid @RequestBody MaStorageInRecord maStorageInRecord) {
        return new FlightResponse().success().put("materialStorId", maStorageInRecordService.createMaStorageInRecord(maStorageInRecord));
    }

    @PostMapping("query/{maStorageInRecord}")
    @ControllerEndpoint(operation = "查询入库记录", exceptionMessage = "查询入库记录失败")
    @Token
    public FlightResponse queryMaBaseInfo(@NotBlank(message = "{maStorageInRecord}") @PathVariable String maStorageInRecord) {
        return new FlightResponse().success().data(maStorageInRecordService.queryMaStorageInRecord(maStorageInRecord));
    }

    @PostMapping("deleteToBin")
    @ControllerEndpoint(operation = "删除入库记录到回收站", exceptionMessage = "删除入库记录到回收站失败")
    @Token
    public FlightResponse deleteMaBaseInfoToBin(@Valid @RequestParam String materialStorId) {
        StUserInfo loginUserInfo = (StUserInfo) httpSession.getAttribute("loginUserInfo");
        final Boolean aBoolean = maStorageInRecordService.deleteMaBaseInfoToBin(materialStorId, loginUserInfo);
        if (!aBoolean) {
            return new FlightResponse().fail();
        }
        return new FlightResponse().success();
    }

    @PostMapping("delete")
    @ControllerEndpoint(operation = "批量删除入库记录", exceptionMessage = "删除入库记录失败")
    @Token
    public FlightResponse deleteMaStorageInRecord(@RequestBody List<String> ids) {
//        StUserInfo loginUserInfo = (StUserInfo) httpSession.getAttribute("loginUserInfo");
        final DeleteStorInRecordDTO deleteStorInRecordDTO = maStorageInRecordService.deleteMaStorageInRecord(ids);
        if (!deleteStorInRecordDTO.getUnDeletedRecords().isEmpty()) {
            return new FlightResponse().fail().data(deleteStorInRecordDTO);
        }
        return new FlightResponse().success().data(deleteStorInRecordDTO);
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "更新入库记录", exceptionMessage = "更新入库记录失败")
    @Token
    public FlightResponse updateMaBaseInfo(@RequestBody MaStorageInRecord maStorageInRecord) {
        maStorageInRecordService.updateMaStorageInRecord(maStorageInRecord);
        return new FlightResponse().success();
    }

    @PostMapping("list")
    @ControllerEndpoint(operation = "查询入库记录", exceptionMessage = "查询入库记录失败")
    @Token
    public FlightResponse userList(QueryRequest request) {
        return new FlightResponse().success()
                .data(getDataTable(maStorageInRecordService.queryMaStorageInRecordList(request)));
    }
}
