package com.mixley.cloud.common.codegen.constant;

import com.mixley.cloud.common.beans.annotations.Definition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据类型实体
 * JSON-Schema Draft 4 http://json-schema.org/
 * https://swagger.io/docs/specification/data-models/data-types/
 * @author 李志锐
 * @date 2021/06/17
 */
@Getter
@AllArgsConstructor
@Definition(title = "数据类型实体")
public enum DataTypeEnum {
    STRING("string","字符串(含日期文件)"),
    NUMBER("number","数字类型"),
    INTEGER("integer","整形数字"),
    BOOLEAN("boolean","布尔值"),
    ARRAY("array","数组类型"),
    OBJECT("object","对象"),
    MIXED("mixed","混合类型");
    private String name;
    private String describe;
}
