package com.bhhyy.aircraft.maintenance.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jinglin
 * @Date: 2022/05/05
 * @Description:
 */
@RestController
public class TestController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/sample/testlog")
    @ResponseBody
    Object testlog() {
        LOGGER.info("统计异常池数量异常,异常信息如下:e.getStackTrace().toString()");
        LOGGER.error("error");
        LOGGER.warn("warning");
        LOGGER.info("info");    // 默认的日志级别信息
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        return "ok";
    }
}
