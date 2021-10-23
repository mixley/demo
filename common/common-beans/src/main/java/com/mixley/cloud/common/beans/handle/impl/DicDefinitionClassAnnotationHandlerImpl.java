package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.beans.annotations.DicDefinition;
import com.mixley.cloud.common.beans.annotations.DicDefinition;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.handle.ClassAnnotationHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 持有人实体处理程序
 * 实体收集
 * @author 李志锐
 * @date 2021/05/29
 */
@AllArgsConstructor
@Component
public class DicDefinitionClassAnnotationHandlerImpl implements ClassAnnotationHandler<DicDefinition> {
    @Override
    public int getOrder() {
        return -10;
    }

    @Override
    public Class<DicDefinition> type() {
        return DicDefinition.class;
    }

    @Override
    public void handel(BeanContext beanContext, Class obj, DicDefinition bean) {
        Content content = beanContext.content();
        content.code(bean.tag());
        content.describe(bean.remark());
    }
}
