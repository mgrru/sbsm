package com.mgrru.sbsm.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mgrru.sbsm.anno.LoginValidate;
import com.mgrru.sbsm.entity.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
@Async
public class LoginAspect {

    @Autowired
    private JwtUtil jwtUtil;

    @Around("@annotation(com.mgrru.sbsm.anno.LoginValidate)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        LoginValidate loginValidate = method.getAnnotation(LoginValidate.class);

        if (loginValidate != null && !loginValidate.validate()) {
            return joinPoint.proceed(joinPoint.getArgs());
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (requestAttributes == null || requestAttributes.getResponse() == null) {
            return joinPoint.proceed(joinPoint.getArgs());
        }

        HttpServletRequest req = requestAttributes.getRequest();
        String token = req.getHeader("Authorization");
        if (jwtUtil.validateToken(token)) {
            return joinPoint.proceed(joinPoint.getArgs());
        } else {
            return "登录验证失败,请重新登录!";
        }

    }
}
