package ru.geekbrains.aspect;

import org.apache.juli.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    @Pointcut("execution(* ru.geekbrains.controllers..*.*Controller(..))")
    private void getPointcut() {}

    @Before("getPointcut()")
    private void logBefore(JoinPoint j) {
        logger.info("Logging shop-ui from Controllers-Aspect in joinpoint: {}", j.toString());
    }

    @Around("@annotation(ru.geekbrains.aspect.ExecutionTimer)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        logger.info("time to exect. method: {} is: {} ms", joinPoint, System.currentTimeMillis() - start);

        return result;
    }
}
