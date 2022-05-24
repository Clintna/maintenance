package com.bhhyy.aircraft.maintenance.staff.controller;

import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserAuth;
import com.bhhyy.aircraft.maintenance.staff.service.StUserAuthService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/stUserAuth")
@RequiredArgsConstructor
public class StUserAuthController {

    @Resource
    private StUserAuthService stUserAuthService;

    @PostMapping("create")
    public FlightResponse createAuth(@Validated @RequestBody StUserAuth stUserAuth) {
        Boolean b = stUserAuthService.createAuth(stUserAuth);
        if (b) {
            return new FlightResponse().success();
        } else {
            return new FlightResponse().fail();
        }
    }
}
