package com.experiment.daeseda_renewal.config;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.experiment.daeseda_renewal..*(..))")
    public void loggableMethods() {}

    @Before("loggableMethods()")
    public void logBefore() {
        logger.info("메서드 실행 전 로그");
    }

    @After("loggableMethods()")
    public void logAfter() {
        logger.info("메서드 실행 후 로그");
    }

    @AfterReturning(value = "loggableMethods()", returning = "result")
    public void logAfterReturning(Object result) {
        logger.info("메서드 실행 후 리턴된 값: {}", result);
    }
}
