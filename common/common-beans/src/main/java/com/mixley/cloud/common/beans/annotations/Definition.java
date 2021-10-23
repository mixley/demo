package com.mixley.cloud.common.beans.annotations;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 实体定义
 *
 * @author 李志锐
 * @date 2021/05/29
 */
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Inherited
@Conditional(BeanCondition.class)//自定义实体的Bean处理
public @interface Definition {
    /**
     * 实体tag标记名称，默认为类名，可自定义
     *
     * @return {@link String}
     */
    String value() default "";

    /**
     * 标题,显示名称
     *
     * @return {@link String}
     */
    String title() default "";

}