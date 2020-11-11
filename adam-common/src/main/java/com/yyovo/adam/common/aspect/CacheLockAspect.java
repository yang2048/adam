package com.yyovo.adam.common.aspect;

import com.yyovo.adam.common.aspect.annotation.CacheLock;
import com.yyovo.adam.common.base.enums.ErrorType;
import com.yyovo.adam.common.base.model.Result;
import com.yyovo.adam.common.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class CacheLockAspect {

    @Autowired
    private RedisLock redisLock;

    @Pointcut("@annotation(com.yyovo.adam.common.aspect.annotation.CacheLock)")
    public void cacheLockPointCut() {
    }

    @Around("cacheLockPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 使用分布式锁 机制-实现
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        int lockSeconds = cacheLock.expire();
        String prefix = cacheLock.prefix();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        AssertUtils.notNull(request, "request can not null");

        // 获取请求的凭证，本项目中使用的JWT,可对应修改
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();

        String key = requestURI + ":" + token + ":" + prefix;
        String clientId = token;
        Object result;
        try {
            // 获取锁
            boolean lock = redisLock.tryLock(key, clientId, lockSeconds);
            log.info("tryLock key = [{}], clientId = [{}]", key, clientId);

            if (lock) {
                log.info("tryLock success, key = [{}], clientId = [{}]", key, clientId);
            } else {
                // 获取锁失败，认为是重复提交的请求
                log.info("tryLock fail, key = [{}]", key);
                return Result.failed(ErrorType.SYSTEM_BUSY);
            }
            // 执行进程
            result = joinPoint.proceed();
        } finally {
            // 解锁
//            redisLock.releaseLock(key, clientId);
//            log.info("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
        }
        return result;
    }

}
