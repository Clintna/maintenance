package com.bhhyy.aircraft.maintenance.common.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查的拦截器
 *
 * @author cuiyongling
 * @since V1.0.0
 * 2020-11-14 21:47
 */

/**
 * 登录检查，没有登录的用户不可以进入系统进行增删改查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUserInfo");
        if (user == null) {
            //未登录，返回登录页面
            //错误消息
            request.setAttribute("msg", "没有权限，请先登录");
            //转发到/index.html这个请求，将请求和响应转发出去。
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        } else {
            //已经登录，放行请求
            return true;
        }
    }
}

