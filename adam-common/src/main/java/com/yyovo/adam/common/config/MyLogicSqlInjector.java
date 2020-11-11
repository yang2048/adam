package com.yyovo.adam.common.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

public class MyLogicSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        // 保留MP自带方法
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
//        methodList.add(new SelectById());
        return methodList;
    }
}
