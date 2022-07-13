package com.mixley.cloud.common.codegen.entity;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;


/**
 * 数据结构
 *
 * @author mixley
 * @date 2021/11/15
 */
@Data
public class DataStructure{
    /** 名称 */
    private String name;
    /**
     * 描述
     */
    private String describe;
    /**
     * 数据设置->
     */
    private Setting[] settings;
    /**
     * 拓展(新增、删除、修改、查询、分页、excel导入，excel导出)
     */
    private String[] expand;
    /**
     * 字段属性
     */
    private DataField[] fields;
}
