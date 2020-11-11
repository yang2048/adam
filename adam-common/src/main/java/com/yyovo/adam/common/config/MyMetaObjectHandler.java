package com.yyovo.adam.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 字段为空自动填充,如果要使填充生效,一定在在实体类对应的字段上设置@TableField(fill = FieldFill.INSERT)字段
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object registerIp = getFieldValByName("registerIp", metaObject);
        Object registerTime = getFieldValByName("registerTime", metaObject);
        Object lastLoginIp = getFieldValByName("lastLoginIp", metaObject);
        Object lastLoginTime = getFieldValByName("lastLoginTime", metaObject);

        Object createTime = getFieldValByName("createdTime", metaObject);
        Object createdBy = getFieldValByName("createdBy", metaObject);
        Object updatedTime = getFieldValByName("updatedTime", metaObject);
        Object updatedBy = getFieldValByName("updatedBy", metaObject);
        Object deleted = getFieldValByName("deleted", metaObject);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        System.out.println("==> 请求者的IP：" + request.getRemoteAddr());
        String ip = request.getHeader("X-Real-IP");
        if (Objects.isNull(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (registerIp == null) {
            setFieldValByName("registerIp", ip, metaObject);
        }
        if (registerTime == null) {
            setFieldValByName("registerTime", LocalDateTime.now(), metaObject);
        }
        if (lastLoginIp == null) {
            setFieldValByName("lastLoginIp", ip, metaObject);
        }
        if (lastLoginTime == null) {
            setFieldValByName("lastLoginTime", LocalDateTime.now(), metaObject);
        }

        if (createdBy == null) {
            setFieldValByName("createdBy", "admin", metaObject);
        }
        if (createTime == null) {
            setFieldValByName("createdTime", LocalDateTime.now(), metaObject);
        }
        if (updatedBy == null) {
            setFieldValByName("updatedBy", "admin", metaObject);
        }
        if (updatedTime == null) {
            setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
        }
        if (deleted == null) {
            //0:正常，1:删除
            setFieldValByName("deleted", 0, metaObject);
        }

    }

    /**
     * 自动填充     @TableField(fill = FieldFill.INSERT_UPDATE)
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object lastLoginIp = getFieldValByName("lastLoginIp", metaObject);
        Object lastLoginTime = getFieldValByName("lastLoginTime", metaObject);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = request.getHeader("X-Real-IP");
        if (Objects.isNull(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (lastLoginIp == null) {
            setFieldValByName("lastLoginIp", ip, metaObject);
        }
        if (lastLoginTime == null) {
            setFieldValByName("lastLoginTime", LocalDateTime.now(), metaObject);
        }
        setFieldValByName("updatedBy", "admin", metaObject);
        setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
    }
}
