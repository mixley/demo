package com.mixley.cloud.common.beans.annotations;

import com.mixley.cloud.common.beans.constant.Constants;

import java.lang.annotation.*;

/**
 * 实体描述
 * @see Constants#BEAN_DESCRIBE
 * @author 李志锐
 * @date 2021/05/31
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanDescribe {
}