package com.sorry.buskingbusking.config;

import com.sorry.buskingbusking.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
@Aspect
@Slf4j
@Configuration
public class AopConfiguration {

    @Around("execution(* com.sorry.buskingbusking.controller.*Controller.*(..))")
    public Object before(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String signature = proceedingJoinPoint.getSignature().getName();
        String ipAddr = ContextUtil.getRequest().getLocalAddr();

        log.info("signature" + signature);
        log.info("signature" + ipAddr);

        Object object = proceedingJoinPoint.proceed();

        return object;
    }
}
