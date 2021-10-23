package com.mixley.cloud.common.beans.annotations;

import com.mixley.cloud.common.beans.constant.Constants;

import java.lang.annotation.*;

/**
 * 实体定义
 *
 * @author 李志锐
 * @date 2021/05/29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Definition(title = "实体处理收集(调整为dic格式可解析)",value = Constants.ENTITY)
public @interface EntityDefinition {
    String code();
    String describe() default "";
    int order() default 0;
}