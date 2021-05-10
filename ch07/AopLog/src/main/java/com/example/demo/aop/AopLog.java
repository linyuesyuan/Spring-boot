package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AopLog {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.example..*.*(..))")
    public void aopWebLog(){
    }
    @Before("aopWebLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("URL:" + request.getRequestURL().toString());
        logger.info("HTTP方法：" + request.getMethod());
        logger.info("IP位址：" + request.getRemoteAddr());
        logger.info("類別的方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("參數：" + request.getQueryString());
    }
    @AfterReturning(pointcut = "aopWebLog()", returning = "retObject")
    public void doAfterReturning(Object retObject) throws Throwable{
        logger.info("回應值" + retObject);
        logger.info("費時" + (System.currentTimeMillis() - startTime.get()));
    }
    @AfterThrowing(pointcut = "aopWebLog()", throwing = "exception")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception exception){
        logger.error("執行" + "例外" + exception);
    }
}
