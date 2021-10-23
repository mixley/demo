package com.mixley.cloud.common.beans.handle;

import com.mixley.cloud.common.beans.annotations.Definition;
import com.mixley.cloud.common.core.constant.ErrorType;

/**
 * 注解处理程序
 * 处理程序仅针对使用实体处理器{@link Definition}收集的实体类
 *
 * @author 李志锐
 * @date 2021/05/29
 */
 public interface BeanHandler<T> extends BaseHandler<T,Class> {

     default Class<Class> annotatedElement() {
        return Class.class;
    }

     Class<T> type();
}
