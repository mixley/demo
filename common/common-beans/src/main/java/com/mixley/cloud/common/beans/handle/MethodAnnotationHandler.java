package com.mixley.cloud.common.beans.handle;

import com.mixley.cloud.common.beans.entity.BeanContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 方法上的实体处理程序
 *
 * @author 李志锐
 * @date 2021/06/02
 */
public interface MethodAnnotationHandler<T extends Annotation> extends AnnotationHandler<T,Method> {

    default Class<Method> annotatedElement() {
        return Method.class;
    }

    void handel(BeanContext beanContext, Method obj, T bean);
}
