package com.ssafy.happyhouse.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        if(id == null) {
            response.sendRedirect(request.getContextPath() + "/member/login_form.do");
            return false;
        }
        return true;
    }
}
