package com.bhhyy.aircraft.maintenance.staff.service;

import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-29
 */
public interface StUserInfoService extends IService<StUserInfo> {
    /**
     * 用户注册
     *
     * @param stUserInfo
     */
    String createStUserInfo(StUserInfo stUserInfo);

    /**
     * 用户登录判断
     *
     * @param userName
     * @param password
     * @return
     */
    StUserInfo isLegalLogin(String userName, String password);

    /**
     * 根据名字获取信息
     *
     * @param userName
     * @return
     */
    StUserInfo getByUserName(String userName);

    /**
     * 根据名字获取信息
     *
     * @param userId
     * @return
     */
    StUserInfo getByUserId(String userId);
}
