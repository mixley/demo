package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.beans.annotations.BeanDescribe;
import com.mixley.cloud.common.core.utils.Covers;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.handle.FiledAnnotationHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@AllArgsConstructor
@Component
public class DescribeFiledAnnotationHandler implements FiledAnnotationHandler<BeanDescribe> {
    @Override
    public Class<BeanDescribe> type() {
        return BeanDescribe.class;
    }

    @SneakyThrows
    @Override
    public void handel(BeanContext beanContext, Field obj, BeanDescribe bean) {
        Content content = beanContext.content();
        Object code = Covers.field2Val(content.object()).apply(obj);
        content.describe(String.valueOf(code));
    }
}
