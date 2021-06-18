package com.mixley.cloud.common.beans.handle;

import com.mixley.cloud.common.core.function.MyHandel;
import com.mixley.cloud.common.beans.annotations.Definition;
import com.mixley.cloud.common.beans.entity.BeanContext;
import org.springframework.core.PriorityOrdered;

import java.lang.annotation.Annotation;

/**
 * 注解处理程序
 * 处理程序仅针对使用枚举处理器{@link Definition}收集的枚举类
 *
 * @author 李志锐
 * @date 2021/05/29
 */
 public interface EnumHandler<T extends Annotation, R> extends PriorityOrdered {

    default Class<T> annotationType() {
        return (Class<T>) Definition.class;
    }

    /**
     * 设置优先级别，值约低，约优先
     *
     * @return int
     */
    default int getOrder() {
        return 0;
    }

    void handel(T annotation, BeanContext beanContext, R obj);

    default MyHandel enumHandelFunction(T annotation, BeanContext beanContext, R obj){
        return () -> this.handel(annotation, beanContext,obj);
    }
}
