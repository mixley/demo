package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.beans.annotations.EnumOrder;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.handle.FiledAnnotationHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@AllArgsConstructor
@Component
public class OrderFiledAnnotationHandler implements FiledAnnotationHandler<EnumOrder> {
    @Override
    public Class<EnumOrder> type() {
        return EnumOrder.class;
    }

    @SneakyThrows
    @Override
    public void handel(BeanContext beanContext, Field obj, EnumOrder bean) {
        Content content = beanContext.content();
        if (obj.getName().equals(content.name())) {
            content.order(bean.value());
        }
    }
}
