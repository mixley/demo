package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.core.utils.Covers;
import com.mixley.cloud.common.beans.annotations.EnumCode;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.handle.FiledEnumHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@AllArgsConstructor
@Component
public class CodeFiledEnumHandler implements FiledEnumHandler<EnumCode> {
    @Override
    public Class<EnumCode> annotationType() {
        return EnumCode.class;
    }

    @SneakyThrows
    @Override
    public void handel(EnumCode annotation, BeanContext beanContext, Field obj) {
        for (Content content : beanContext.contents()) {
            Object code = null;
            if (content.expandMap().containsKey(obj.getName())) {
                code = content.expandMap().get(obj.getName());
            } else {
                code = Covers.field2Val(content.object()).apply(obj);
            }
            content.code(String.valueOf(code));
        }
    }
}
