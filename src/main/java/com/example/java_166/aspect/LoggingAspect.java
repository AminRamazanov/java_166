package com.example.java_166.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Around("execution(* com.example.java_166.controller..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Method {} - started", methodName);

        Object result;
        try {
            result = joinPoint.proceed();
            log.info("Method {} - completed successfully", methodName);
        } catch (Throwable ex) {
            log.error("Method {} - threw exception: {}", methodName, ex.getMessage(), ex);
            throw ex;
        }

        return result;
    }
}
