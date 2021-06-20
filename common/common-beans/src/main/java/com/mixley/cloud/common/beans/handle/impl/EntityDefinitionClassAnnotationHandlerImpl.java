package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.beans.annotations.Definition;
import com.mixley.cloud.common.beans.annotations.EntityDefinition;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.handle.ClassAnnotationHandler;
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
public class EntityDefinitionClassAnnotationHandlerImpl implements ClassAnnotationHandler<EntityDefinition> {
    @Override
    public int getOrder() {
        return -10;
    }

    @Override
    public Class<EntityDefinition> type() {
        return EntityDefinition.class;
    }

    @Override
    public void handel(BeanContext beanContext, Class obj, EntityDefinition bean) {
        Content content = beanContext.content();
        content.code(bean.code());
        content.describe(bean.describe());
        content.order(bean.order());
    }
}
