package com.bhhyy.aircraft.maintenance.staff.service;

import com.bhhyy.aircraft.maintenance.staff.bean.StDeptInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhhyy.aircraft.maintenance.staff.entity.DeptTree;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-23
 */
public interface StDeptInfoService extends IService<StDeptInfo> {

    /**
     * 创建部门
     * @param stDeptInfo
     */
    void createDept(StDeptInfo stDeptInfo);

    /**
     * 获取部门展示树
     * @return
     */
    List<DeptTree<StDeptInfo>> findDeptTree();
}
