package com.mixley.cloud.common.beans.handle;

import com.mixley.cloud.common.beans.entity.BeanContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * 类上的实体处理程序（含注解上注解）
 *
 * @author 李志锐
 * @date 2021/06/02
 */
public interface ClassAnnotationHandler<T extends Annotation> extends AnnotationHandler<T,Class> {

    default Class<Class> annotatedElement() {
        return Class.class;
    }
    void handel(BeanContext beanContext, Class obj, T bean);
}
