package com.bhhyy.aircraft.maintenance.common.token;

import com.alibaba.fastjson.JSONObject;
import com.bhhyy.aircraft.maintenance.common.enums.ErrorCodeEnum;
import com.bhhyy.aircraft.maintenance.common.utils.RedisUtil;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;

    public static final String USER_KEY = "USER_ID";
    public static final String USER_INFO = "USER_INFO";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Token annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Token.class);
        } else {
            return true;
        }
        //没有声明需要权限,或者声明不验证权限
        if (annotation == null || !annotation.validate()) {
            return true;
        }
        //从header中获取token
        String token = request.getHeader("token");
        if (token == null) {
            log.error("token授权异常，重新登陆,用户信息是{}", request.getHeader("stUserInfo"));
            throw new RuntimeException(ErrorCodeEnum.BIZ_FAILED_CODE.getMsg().concat("---TOKEN异常"));
        }
//        查询token信息
        Boolean aBoolean = redisUtil.hasKey(token);
        if (aBoolean) {
            StUserInfo user = JSONObject.parseObject(redisUtil.get(token), StUserInfo.class);
            if (user == null) {
                log.error("查询不到用户信息，重新登陆{}", request.getHeader("stUserInfo"));
                throw new RuntimeException(ErrorCodeEnum.BIZ_FAILED_CODE.getMsg().concat("---TOKEN异常"));
            }
        } else {
            log.error("token授权异常，重新登陆,用户信息是{}", request.getHeader("stUserInfo"));
            throw new RuntimeException(ErrorCodeEnum.BIZ_FAILED_CODE.getMsg().concat("---TOKEN异常"));
        }
        return true;
    }
}