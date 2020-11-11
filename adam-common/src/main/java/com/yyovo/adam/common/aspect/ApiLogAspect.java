package com.yyovo.adam.common.aspect;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yyovo.adam.common.base.model.WebLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.yyovo.adam.admin.system.controller.*.*(..)) || execution(public * com.yyovo.adam.*.controller.*.*(..))")
    public void logPointCut() {

    }

    /**
     * 环绕型增强通知
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        TimeInterval timer = new TimeInterval();
        long startTime = timer.start();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        WebLog webLog = new WebLog();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            StringBuilder description = new StringBuilder(log.value());
            if (StringUtils.isNotBlank(log.notes())) {
                description.append("【" + log.notes() + "】");
            }
            webLog.setDescription(description.toString());
        }
        Object result = point.proceed();
        String urlStr = request.getRequestURL().toString();
        webLog.setUsername(request.getRemoteUser());
        webLog.setIp(request.getRemoteAddr());
        webLog.setMethod(request.getMethod());
        //打印请求参数
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap != null && paramMap.size() > 0) {
            StringBuffer paramSbf = new StringBuffer();
            for (String mapKey : paramMap.keySet()) {
                String[] mapValue = paramMap.get(mapKey);
                //添加判断
                if (mapValue != null && mapValue.length > 0) {
                    for (String paramStr : mapValue) {
                        if (StrUtil.isNotBlank(paramStr)) {
                            paramSbf.append("" + mapKey + "=");
                            paramSbf.append(paramStr);
                        }
                    }
                }
            }
//            Iterator it = Arrays.stream(point.getArgs()).iterator();
//            StringBuilder sb = new StringBuilder();
//            it.forEachRemaining(i -> sb.append(i));

            webLog.setParameter(paramSbf.toString());
//            log.info("-->request请求参数 : \t" + paramSbf);
        }
        webLog.setResult(result.toString());
        webLog.setStartTime(startTime);
        webLog.setSpendTime((int) timer.intervalRestart());
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        webLog.setClassName(point.getSignature().getDeclaringTypeName() + "=>" + point.getSignature().getName());

        log.info(webLog.toString());
        return result;
    }

    /**
     * 异常通知
     */
    @AfterThrowing(throwing = "ex", pointcut = "logPointCut()")
    public void doRecoveryActions(Throwable ex) {
        System.out.println("目标方法中抛出的异常:" + ex);
        System.out.println("模拟抛出异常后的增强处理...");
    }
}
