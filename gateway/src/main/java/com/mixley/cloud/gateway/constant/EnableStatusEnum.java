package com.mixley.cloud.gateway.constant;

import com.mixley.cloud.common.beans.annotations.EnumCode;
import com.mixley.cloud.common.beans.annotations.Definition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Definition(title = "启用状态")
public enum EnableStatusEnum {

    YES("0", "正常"),
    NO("1", "已取消");
    @EnumCode
    private String code;
    private String describe;

}
