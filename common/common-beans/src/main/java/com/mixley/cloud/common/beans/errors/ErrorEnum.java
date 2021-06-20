package com.mixley.cloud.common.beans.errors;

import com.mixley.cloud.common.core.constant.ErrorType;
import com.mixley.cloud.common.beans.annotations.ErrorDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 资源类型
 */
@Getter
@AllArgsConstructor
@ErrorDefinition
public enum ErrorEnum implements ErrorType {


    NO_EXIST(404, "类不存在"),
    IS_REGISTER(405, "已经注册"),
    TAG_IS_NULL(406, "标签不存在"),
    FIELD_NO_EXIST(407, "字段不存在"),
    FIELD_ERROR(408, "字段异常");
    private final int code;
    private final String describe;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescribe() {
        return describe;
    }
}
