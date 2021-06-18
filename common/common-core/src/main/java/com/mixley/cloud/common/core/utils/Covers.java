package com.mixley.cloud.common.core.utils;

import com.mixley.cloud.common.core.function.MyFunction;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

/**
 * 转换
 *
 * @author 李志锐
 * @date 2021/06/01
 */
@UtilityClass
public class Covers {

    /**
     * field2对象
     *
     * @param field  场
     * @param object 对象
     * @return {@link Object}
     */
    public Object field2Val(Field field,Object object) {
        return field2Val(object).apply(field);
    }

    /**
     * field2对象
     *
     * @param object 对象
     * @return {@link MyFunction <Field, Object>}
     */
    public MyFunction<Field, Object> field2Val(Object object) {
        return field -> {
            field.setAccessible(true);
            Object obj = field.get(object);
            field.setAccessible(false);
            return obj;
        };
    }
}
