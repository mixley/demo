package com.mixley.cloud.common.codegen.constant;

import com.mixley.cloud.common.beans.annotations.Definition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据格式实体
 * JSON-Schema Draft 4 http://json-schema.org/
 * https://swagger.io/docs/specification/data-models/data-types/
 *https://github.com/OAI/OpenAPI-Specification/blob/main/versions/3.0.0.md#exampleObject
 * @author 李志锐
 * @date 2021/06/17
 */
@Getter
@AllArgsConstructor
@Definition(title = "数据格式实体")
public enum DataFormatEnum {

    MIXED("mixed",  DataTypeEnum.MIXED, "混合类型"),
    NULL("null", DataTypeEnum.MIXED, "未知类型"),

    NUMBER("number",  DataTypeEnum.NUMBER, "数字"),
    FLOAT("float",  DataTypeEnum.NUMBER, "浮点数"),
    DOUBLE("double",  DataTypeEnum.NUMBER, "双精度浮点数"),
    INTEGER("integer",  DataTypeEnum.INTEGER, "整型"),
    LONG("long",  DataTypeEnum.INTEGER, "长整型"),

    STRING("string",  DataTypeEnum.STRING, "字符串"),
    DATE("date", DataTypeEnum.STRING, "日期(yyyy-MM-dd)"),
    DATE_TIME("date-time", DataTypeEnum.STRING, "日期时间(yyyy-MM-dd hh:mm:ss)"),
    PASSWORD("password ", DataTypeEnum.STRING, "密码输入"),
    BYTE("byte", DataTypeEnum.STRING, "base64值"),
    /** https://swagger.io/docs/specification/data-models/data-types/#file */
    BINARY("binary", DataTypeEnum.STRING, "文件"),

    EMAIL("email", DataTypeEnum.STRING, "邮箱"),
    UUID("uuid", DataTypeEnum.STRING, "唯一uid"),
    URI("uri", DataTypeEnum.STRING, "链接地址"),
    HOSTNAME("hostname", DataTypeEnum.STRING, "域名"),
    IPV4("ipv4", DataTypeEnum.STRING, "ipv4"),
    IPV6("ipv6", DataTypeEnum.STRING, "ipv6"),

    PATTERN("pattern", DataTypeEnum.STRING, "正则验证"),

    BOOLEAN("boolean", DataTypeEnum.BOOLEAN, "布尔型"),

    ARRAY("array", DataTypeEnum.ARRAY, "数组"),
    UNIQUE("unique", DataTypeEnum.ARRAY, "不重复数组"),

    OBJECT("object", DataTypeEnum.OBJECT, "对象")
    ;


    /** 名称 */
    private String name;
    /** 类型 */
    private DataTypeEnum type;
    /** 描述 */
    private String describe;
}
