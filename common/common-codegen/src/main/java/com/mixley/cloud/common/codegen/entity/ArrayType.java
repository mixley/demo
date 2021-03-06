package com.mixley.cloud.common.codegen.entity;

import lombok.Data;

import java.util.List;


/**
 * 表的实体
 *
 * @author 李志锐
 * @date 2021/06/17
 */
@Data
public class ArrayType extends ObjectType {
    /** 值 */
    private List<? extends ObjectType> items;
    /** 最小项目数 */
    private Integer minItems;
    /** 最多项目数 */
    private Integer maxItems;
}
