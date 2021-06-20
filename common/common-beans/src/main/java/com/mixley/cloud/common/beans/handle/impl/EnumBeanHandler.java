package com.mixley.cloud.common.beans.handle.impl;

import com.mixley.cloud.common.beans.annotations.EnumOrder;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.handle.BeanHandler;
import com.mixley.cloud.common.core.utils.Checks;
import com.mixley.cloud.common.core.utils.Covers;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@AllArgsConstructor
@Component
public class EnumBeanHandler implements BeanHandler<Enum> {
    @Override
    public int getOrder() {
        return -30;
    }

    @Override
    public Class<Enum> type() {
        return Enum.class;
    }

    @Override
    public void handel(BeanContext beanContext, Class obj, Enum bean) {
        Content content = beanContext.content();
        if (Checks.isEmpty(content.code())) {
            String code = bean.name();
            try {
                Field codeField = obj.getDeclaredField("code");
                if (null != codeField) {
                    Object codeObj = Covers.field2Val(bean).apply(codeField);
                    code = String.valueOf(codeObj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            content.code(code);
        }
        if (Checks.isEmpty(content.describe())) {
            try {
                String describe = "";
                Field describeField = obj.getDeclaredField("describe");
                if (null != describeField) {
                    Object describeObj = Covers.field2Val(bean).apply(describeField);
                    describe = String.valueOf(describeObj);
                }
                content.describe(describe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
