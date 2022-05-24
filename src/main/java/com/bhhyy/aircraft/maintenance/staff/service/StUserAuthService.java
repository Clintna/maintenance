package com.bhhyy.aircraft.maintenance.staff.service;

import com.bhhyy.aircraft.maintenance.staff.bean.StUserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-10
 */
public interface StUserAuthService extends IService<StUserAuth> {
    /**
     * 新增用户权限
     *
     * @param stUserAuth
     * @return
     */
    Boolean createAuth(StUserAuth stUserAuth);
}
