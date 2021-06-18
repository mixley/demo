package com.mixley.cloud.common.beans.annotations;

import com.mixley.cloud.common.beans.constant.EnumsConstants;

import java.lang.annotation.*;

/**
 * 枚举定义
 *
 * @author 李志锐
 * @date 2021/05/29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Definition(group = EnumsConstants.SYS_GROUP,title = "异常处理",value = EnumsConstants.ERROR)
public @interface ErrorDefinition {

}