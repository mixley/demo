package com.mixley.cloud.common.beans.handle;

import com.mixley.cloud.common.beans.annotations.Definition;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * 注解处理程序
 * 处理程序仅针对使用实体处理器{@link Definition}收集的实体类
 *
 * @author 李志锐
 * @date 2021/05/29
 */
 public interface AnnotationHandler<T extends Annotation, R extends AnnotatedElement> extends BaseHandler<T,R> {

    Class<T> type();

}
