package com.mixley.cloud.common.beans.handle;

import com.mixley.cloud.common.beans.entity.BeanContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 方法上的枚举处理程序
 *
 * @author 李志锐
 * @date 2021/06/02
 */
public interface MethodEnumHandler<T extends Annotation> extends EnumHandler<T,Method> {
    void handel(T annotation, BeanContext beanContext, Method obj);
}
