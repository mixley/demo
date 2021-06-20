package com.mixley.cloud.common.codegen.entity;

import com.mixley.cloud.common.codegen.constant.DataFormatEnum;
import lombok.Data;

/**
 * 列的实体
 *
 * @author 李志锐
 * @date 2021/06/17
 */
@Data
public class ObjectType {
    /** 名称 */
    private StringType name;
    /** 数据格式 */
    private DataFormatEnum dataFormat;
    /** 是否必须 */
    private Boolean required;
    /** 描述 */
    private StringType describe;
    /** 拓展 */
    private StringType extra;

}
