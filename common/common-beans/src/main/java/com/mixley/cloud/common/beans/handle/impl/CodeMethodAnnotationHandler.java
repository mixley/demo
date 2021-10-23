package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.beans.annotations.BeanCode;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.handle.FiledAnnotationHandler;
import com.mixley.cloud.common.beans.handle.MethodAnnotationHandler;
import com.mixley.cloud.common.core.utils.Covers;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 方法上注解处理
 *
 * @author mixley
 * @date 2021/10/23
 */
@AllArgsConstructor
@Component
public class CodeMethodAnnotationHandler implements MethodAnnotationHandler<BeanCode> {
    @Override
    public Class<BeanCode> type() {
        return BeanCode.class;
    }

    @SneakyThrows
    @Override
    public void handel(BeanContext beanContext, Method obj, BeanCode bean) {
        Content content = beanContext.content();
        Object code = Covers.methodGetVal(content.object()).apply(obj);
        content.code(String.valueOf(code));
    }
}
