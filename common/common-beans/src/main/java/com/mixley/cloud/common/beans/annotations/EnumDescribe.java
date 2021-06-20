package com.mixley.cloud.common.beans.annotations;

import com.mixley.cloud.common.beans.constant.Constants;

import java.lang.annotation.*;

/**
 * 枚举描述
 * @see Constants#ENUM_DESCRIBE
 * @author 李志锐
 * @date 2021/05/31
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumDescribe {
}