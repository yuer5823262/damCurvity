package com.example.damcurvity.filter;

import com.example.damcurvity.exception.DamPourException;
import com.example.damcurvity.exception.DampouringExceptionEnum;
import com.example.damcurvity.util.JwtUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            throw new DamPourException(DampouringExceptionEnum.TOKEN_EMPTY);
        }
        try {
            JwtUtils.verify(token);
        } catch (Exception e) {
            throw new DamPourException(DampouringExceptionEnum.TOKEN_EMPTY);
        }
        return true;
    }
}

