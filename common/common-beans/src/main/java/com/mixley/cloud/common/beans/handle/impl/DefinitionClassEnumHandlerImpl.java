package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.core.utils.Checks;
import com.mixley.cloud.common.beans.annotations.Definition;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Metadata;
import com.mixley.cloud.common.beans.handle.ClassEnumHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 持有人枚举处理程序
 * 枚举收集
 * @author 李志锐
 * @date 2021/05/29
 */
@AllArgsConstructor
@Component
public class DefinitionClassEnumHandlerImpl implements ClassEnumHandler<Definition> {
    @Override
    public int getOrder() {
        return -10;
    }

    @Override
    public Class<Definition> annotationType() {
        return Definition.class;
    }

    @Override
    public void handel(Definition annotation, BeanContext beanContext, Class obj) {
        Metadata metadata = beanContext.metadata();
        if (Checks.isEmpty(annotation.value())) {
            metadata.tagName(obj.getSimpleName());
        }else {
            metadata.tagName(annotation.value());
        }
        metadata.title(annotation.title());
        metadata.group(annotation.group());
    }
}
