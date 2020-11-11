package com.yyovo.adam.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyovo.adam.common.base.enums.ErrorType;
import com.yyovo.adam.common.handler.ApiRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ConvertUtil {
    /**
     * 1.对象属性复制
     *
     * @param source 目标对象
     * @param target 源对象
     * @throws ApiRuntimeException 转换异常
     */
    public static void convert(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
//            log.debug("转换之前数据 ==> {}", source);
//            log.debug("转换之后数据 <== {}", target);
        } catch (Exception e) {
            throw new ApiRuntimeException(ErrorType.SYSTEM_CONVERT);
        }
    }


    /**
     * 2.对象转化,生成需要的对象
     *
     * @param source 源对象
     * @param tClass 目标对象
     * @param <T>    目标对象 类型
     * @return 目标对象实体
     * @throws ApiRuntimeException 转换异常
     */
    public static <T> T copyToDest(Object source, Class<T> tClass) {
        try {
            T t = tClass.newInstance();
            BeanUtils.copyProperties(source, t);
//            log.debug("转换之前数据 ==> {}", source);
//            log.debug("转换之后数据 <== {}", t);
            return t;
        } catch (Exception e) {
            throw new ApiRuntimeException(ErrorType.SYSTEM_CONVERT);
        }
    }

    /**
     * 3.列表转换
     *
     * @param sourceList 源列表
     * @param tClass     目标列表元素类型
     * @param <T>        目标列表元素类型
     * @return 目标列表
     */
    public static <T, V> List<V> copyToList(List<T> sourceList, Class<V> tClass) {
        List<V> list = new ArrayList<>(sourceList.size());
        sourceList.forEach(T -> list.add(copyToDest(T, tClass)));
        return list;
    }

    /**
     * 4.分页数据转换
     *
     * @param orgList 源列表
     * @param tClass
     * @param page
     * @param <T>
     * @return
     */
    public static <T, V> Page<V> copyToPage(IPage<T> orgList, Class<V> tClass) {
        Page<V> page = new Page<>();
        List<V> list = copyToList(orgList.getRecords(), tClass);
        convert(orgList, page);
        page.setRecords(list);
        return page;
    }

}
