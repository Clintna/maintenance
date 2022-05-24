package com.bhhyy.aircraft.maintenance.staff.service.impl;

import com.bhhyy.aircraft.maintenance.staff.bean.StUserAuth;
import com.bhhyy.aircraft.maintenance.staff.mapper.StUserAuthMapper;
import com.bhhyy.aircraft.maintenance.staff.service.StUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-10
 */
@Service
public class StUserAuthServiceImpl extends ServiceImpl<StUserAuthMapper, StUserAuth> implements StUserAuthService {

    @Override
    public Boolean createAuth(StUserAuth stUserAuth) {
        stUserAuth.setCreateTime(LocalDateTime.now());
        stUserAuth.setModifyTime(LocalDateTime.now());
        return save(stUserAuth);
    }
}
