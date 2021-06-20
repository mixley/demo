package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.handle.BeanHandler;
import com.mixley.cloud.common.core.constant.ErrorType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ErrorTypeBeanHandler implements BeanHandler<ErrorType> {
    @Override
    public int getOrder() {
        return -10;
    }

    @Override
    public Class<ErrorType> type() {
        return ErrorType.class;
    }
    @Override
    public void handel(BeanContext beanContext, Class obj, ErrorType bean) {
        Content content = beanContext.content();
        int code = bean.getCode();
        String describe = bean.getDescribe();
        content.code(String.valueOf(code));
        content.describe(String.valueOf(describe));
    }

}
