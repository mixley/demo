package com.mixley.cloud.common.beans.annotations;

import com.mixley.cloud.common.beans.constant.Constants;

import java.lang.annotation.*;

/**
 * 定义
 *
 * @author 李志锐
 * @date 2021/05/29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Definition(title = "异常处理",value = Constants.ERROR)
public @interface ErrorDefinition {

}