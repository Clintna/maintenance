package com.bhhyy.aircraft.maintenance.staff.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;
import com.bhhyy.aircraft.maintenance.staff.constant.ShiroConstant;
import com.bhhyy.aircraft.maintenance.staff.mapper.StUserInfoMapper;
import com.bhhyy.aircraft.maintenance.staff.service.StUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhhyy.aircraft.maintenance.staff.utils.SaltUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-29
 */
@Service
@Slf4j
public class StUserInfoServiceImpl extends ServiceImpl<StUserInfoMapper, StUserInfo> implements StUserInfoService {

    @Override
    public String createStUserInfo(StUserInfo stUserInfo) {
        final StUserInfo one = getOne(new LambdaQueryWrapper<StUserInfo>().eq(StUserInfo::getUserName, stUserInfo.getUserName()));
        if (null != one) {
            throw new RuntimeException("用户名重复");
        }
        String salt = SaltUtil.getSalt(ShiroConstant.SALT_LENGTH);
        stUserInfo.setPasswordSalt(salt);
        Md5Hash password = new Md5Hash(stUserInfo.getPassword(), salt, ShiroConstant.HASH_ITERATORS);
        stUserInfo.setPassword(password.toHex());
        stUserInfo.setUserId(UUID.randomUUID().toString());
        stUserInfo.setModifyTime(LocalDateTime.now());
        stUserInfo.setCreateTime(LocalDateTime.now());
        stUserInfo.setLastLogin(LocalDateTime.now());
        stUserInfo.setDepartmentId(stUserInfo.getDEFAULT_DEPARTMENT_ID());
        try {
            save(stUserInfo);
        } catch (Exception e) {
            log.error("创建用户异常,e:{},用户信息:{}", e.getStackTrace(), JSONObject.toJSONString(stUserInfo));
            throw new RuntimeException("创建用户异常，请检查相关信息");
        }
        return stUserInfo.getUserId();
    }

    @Override
    public StUserInfo isLegalLogin(String userName, String password) {
        final StUserInfo one = getOne(new LambdaQueryWrapper<StUserInfo>()
                .eq(StUserInfo::getUserName, userName));
        if (one.getPassword().equals(password)) {
            return one;
        } else {
            return null;
        }
    }

    @Override
    public StUserInfo getByUserName(String userName) {
        return getOne(new LambdaQueryWrapper<StUserInfo>().eq(StUserInfo::getUserName, userName));
    }

    @Override
    public StUserInfo getByUserId(String userId) {
        return getOne(new LambdaQueryWrapper<StUserInfo>().eq(StUserInfo::getUserId, userId));
    }
}
