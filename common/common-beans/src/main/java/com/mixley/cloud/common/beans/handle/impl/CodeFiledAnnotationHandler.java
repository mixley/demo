package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.beans.annotations.EnumCode;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.handle.FiledAnnotationHandler;
import com.mixley.cloud.common.core.utils.Covers;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@AllArgsConstructor
@Component
public class CodeFiledAnnotationHandler implements FiledAnnotationHandler<EnumCode> {
    @Override
    public Class<EnumCode> type() {
        return EnumCode.class;
    }

    @SneakyThrows
    @Override
    public void handel(BeanContext beanContext, Field obj, EnumCode bean) {
        Content content = beanContext.content();
        Object code = Covers.field2Val(content.object()).apply(obj);
        content.code(String.valueOf(code));
    }
}
