package com.bhhyy.aircraft.maintenance.staff.service;

import com.bhhyy.aircraft.maintenance.staff.bean.StUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-10
 */
public interface StUserRoleService extends IService<StUserRole> {
    /**
     * 根据角色名获得角色信息
     *
     * @param roleName
     * @return
     */
    StUserRole getStUserRoleByRoleName(String roleName);
}
