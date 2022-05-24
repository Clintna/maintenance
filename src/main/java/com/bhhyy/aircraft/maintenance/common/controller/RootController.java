package com.bhhyy.aircraft.maintenance.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jinglin
 * @Date: 2022/04/28
 * @Description:
 */
@RestController
public class RootController {
    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }
}
