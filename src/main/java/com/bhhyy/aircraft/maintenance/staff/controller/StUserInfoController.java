package com.bhhyy.aircraft.maintenance.staff.controller;

import com.alibaba.fastjson.JSON;
import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.common.utils.RedisUtil;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;
import com.bhhyy.aircraft.maintenance.staff.service.StUserInfoService;
import com.bhhyy.aircraft.maintenance.common.token.JwtUtils;
import com.bhhyy.aircraft.maintenance.common.token.Token;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/stUserInfo")
public class StUserInfoController {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private HttpSession httpSession;
    private final StUserInfoService stUserInfoService;
    private final JwtUtils jwtUtils;

    @PostMapping("register")
    public FlightResponse register(@Valid @RequestBody StUserInfo stUserInfo) {
        return new FlightResponse().success().put("userId", stUserInfoService.createStUserInfo(stUserInfo));
    }
//
//    @RequestMapping("register")
//    public String register(StUserInfo stUserInfo){
//        try {
//            stUserInfoService.createStUserInfo(stUserInfo);
//            return "redirect:/login.jsp";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/register.jsp";
//    }


    @PostMapping("login")
    public FlightResponse login(@RequestBody Map<String, String> map) {
        String userName = map.get("userName");
        String password = map.get("password");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userName, password));
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            throw new RuntimeException("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            throw new RuntimeException("密码错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StUserInfo stUserInfo = stUserInfoService.getByUserName(userName);
        if (null != stUserInfo) {

            httpSession.setAttribute("loginUserInfo", stUserInfo);

            String token = jwtUtils.generateToken(stUserInfo.getUserId());
            if (token != null) {

                redisUtil.set(token, JSON.toJSONString(stUserInfo));
                redisUtil.expire(token, 1, TimeUnit.DAYS);

                return Objects.requireNonNull(new FlightResponse().success()
                        .put("stUserInfo", stUserInfo))
                        .put("token", token);
            }
        }
        return new FlightResponse().fail().message("密码错误，登陆失败");
    }

    @GetMapping("query")
//    @RequiresPermissions("order:add")
    public FlightResponse query(@RequestParam String userName) {
        StUserInfo stUserInfo = stUserInfoService.getByUserName(userName);
        return new FlightResponse().success().put("data", stUserInfo);
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        // 退出后仍然会到登录页面
        return "redirect:/login.jsp";
    }
}
