package com.itdragon.interceptor;

import com.itdragon.pojo.User;
import com.itdragon.service.UserService;
import com.itdragon.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginHandlerInterceptor implements HandlerInterceptor {

    public static final String COOKIE_NAME = "USER_TOKEN";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = CookieUtils.getCookieValue(httpServletRequest, COOKIE_NAME);
        User user = this.userService.getUserByToken(token);
        if(StringUtils.isEmpty(token) || null == user){
            //跳转到登录页面，把用户请求的url作为参数传递给登录页面
            httpServletResponse.sendRedirect("http://localhost:8081/login?redirect=" + httpServletRequest.getRequestURL());
            //返回false
            return false;
        }
        // 把用户信息放入Request
        httpServletRequest.setAttribute("user", user);
        // 返回值决定handler是否执行。true：执行，false：不执行。
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
