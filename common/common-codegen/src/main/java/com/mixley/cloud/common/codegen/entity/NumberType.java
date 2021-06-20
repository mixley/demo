package com.mixley.cloud.common.codegen.entity;

import com.mixley.cloud.common.codegen.constant.DataTypeEnum;
import lombok.Data;

/**
 * 数字的实体
 *
 * @author 李志锐
 * @date 2021/06/18
 */
@Data
public class NumberType<E extends Number> extends DataType {
    /**
     * 最低
     * minimum ≤ value ≤ maximum
     */
    E minimum;
    /**
     * 最大
     * minimum ≤ value ≤ maximum
     */
    E maximum;

    /**
     * 排除最低
     * exclusiveMinimum: false or not included	value ≥ minimum
     * exclusiveMinimum: true	value > minimum
     */
    Boolean exclusiveMinimum;
    /**
     * 独家最大
     * exclusiveMaximum: false or not included	value ≤ maximum
     * exclusiveMaximum: true	value < maximum
     */
    Boolean exclusiveMaximum;

    /** 的倍数 */
    E multipleOf;

    @Override
    public DataTypeEnum dataType() {
        return DataTypeEnum.NUMBER;
    }
}
