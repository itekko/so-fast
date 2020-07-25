package com.sofast.common.aspect;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofast.common.annotation.Logs;
import com.sofast.common.utils.IPUtils;
import com.sofast.system.entity.Log;
import com.sofast.system.mapper.LogMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(com.sofast.common.annotation.Logs)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    @Pointcut("execution(public * com.sofast.*.controller.*.*(..))")
    public void logController(){}

    /** 记录controller日志，包括请求、ip、参数、响应结果 */
    @Around("logController()")
    public Object controller(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("{} {} {} {}.{}{}", request.getMethod(), request.getRequestURI(), IPUtils.getIpAddr(request), point.getTarget().getClass().getSimpleName(), point.getSignature().getName(), Arrays.toString(point.getArgs()));

        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;

        log.info("result({}) {}", time, objectMapper.writeValueAsString(result));
        return result;
    }


    /**
     *
     * @param joinPoint
     * @param time
     * @throws JsonProcessingException
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logEntity = new Log();
        Logs syslog = method.getAnnotation(Logs.class);
        if (syslog != null) {
            // 注解上的描述
            logEntity.setOperation(syslog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        String params = null;
        logEntity.setMethod(className + "." + methodName + "()");
        Object[] args = joinPoint.getArgs();
        params = objectMapper.writeValueAsString(args);
        int maxLength = 4999;
        if(params.length() > maxLength){
        	params = params.substring(0, maxLength);
        }
        logEntity.setParams(params);
        logEntity.setTimes((int) time);
        // 保存系统日志
        logMapper.insert(logEntity);
    }
}
