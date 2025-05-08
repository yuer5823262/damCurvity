package com.example.damcurvity.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WeblogAspect {

    private  final Logger log = LoggerFactory.getLogger(WeblogAspect.class);

//    @Pointcut("execution(public * com.example.dampouring.controller.*.*(..))")
//    @Pointcut("execution(public * com.example.dampouring.controller.*.*)")
//    public void webLog(){
//
//    }
    @Before("within(com.example.dampouring.controller.*.*)")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest  request = attributes.getRequest();
        log.info("URL:" + request.getRequestURL().toString());
        log.info("HTTP_METHOD :" + request.getMethod());
        log.info("IP : " + request.getRemoteAddr() );
        log.info("Class_METHOD:" + joinPoint.getSignature().getDeclaringTypeName()
        +"."+joinPoint.getSignature().getName());
        log.info("ARGS :" + Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(returning = "res", value = "within(com.example.dampouring.controller.*.*)")
    public void  doAfterReturning(Object res) throws JsonProcessingException {
        log.info("RESPONSE" + new ObjectMapper().writeValueAsString(res));
    }

}
