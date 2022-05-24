package com.bhhyy.aircraft.maintenance.staff.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserRole;
import com.bhhyy.aircraft.maintenance.staff.mapper.StUserRoleMapper;
import com.bhhyy.aircraft.maintenance.staff.service.StUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-10
 */
@Service
public class StUserRoleServiceImpl extends ServiceImpl<StUserRoleMapper, StUserRole> implements StUserRoleService {

    @Override
    public StUserRole getStUserRoleByRoleName(String roleName) {
        return getOne(new LambdaQueryWrapper<StUserRole>().eq(StUserRole::getRoleName, roleName));
    }
}
