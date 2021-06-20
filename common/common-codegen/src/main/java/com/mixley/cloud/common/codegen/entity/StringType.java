package com.mixley.cloud.common.codegen.entity;

import com.mixley.cloud.common.codegen.constant.DataTypeEnum;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 字符串类型定义
 *
 * @author 李志锐
 * @date 2021/06/18
 */
@Data
public class StringType extends DataType {
    /** 最小长度 */
    Integer minLength;
    /** 最大长度 */
    Integer maxLength;

    @Override
    public DataTypeEnum dataType() {
        return DataTypeEnum.STRING;
    }
}
