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
@Definition(title = "字典收集)",value = Constants.DICTIONARY)
public @interface DicDefinition {

    /**
     * 标签 唯一标识字典名称
     *
     * @return {@link String}
     */
    String tag();

    /**
     * 备注
     *
     * @return {@link String}
     */
    String remark()  default "";

    //处理
    
}