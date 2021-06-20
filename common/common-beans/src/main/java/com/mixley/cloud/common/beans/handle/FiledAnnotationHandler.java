package com.mixley.cloud.common.beans.handle;

import com.mixley.cloud.common.beans.entity.BeanContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 字段上的枚举处理程序
 *
 * @author 李志锐
 * @date 2021/06/02
 */
public interface FiledAnnotationHandler<T extends Annotation> extends AnnotationHandler<T,Field> {

    void handel(BeanContext beanContext, Field obj, T bean);

}
