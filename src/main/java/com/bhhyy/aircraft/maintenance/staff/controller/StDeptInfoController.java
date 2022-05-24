package com.bhhyy.aircraft.maintenance.staff.controller;

import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.staff.bean.StDeptInfo;
import com.bhhyy.aircraft.maintenance.staff.entity.DeptTree;
import com.bhhyy.aircraft.maintenance.staff.service.StDeptInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-23
 */
@RestController
@RequestMapping("/stDeptInfo")
@RequiredArgsConstructor
public class StDeptInfoController {

    @Resource
    private StDeptInfoService stDeptInfoService;

    @PostMapping("getDeptTree")
    public FlightResponse getDeptTree(){
        List<DeptTree<StDeptInfo>> deptTree = stDeptInfoService.findDeptTree();
        return new FlightResponse().success().data(deptTree);
    }
    @PostMapping("create")
    public FlightResponse create(@Valid @RequestBody StDeptInfo stDeptInfo){
        stDeptInfoService.createDept(stDeptInfo);
        return new FlightResponse().success();
    }
}
