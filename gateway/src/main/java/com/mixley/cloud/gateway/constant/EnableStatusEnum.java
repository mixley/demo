package com.mixley.cloud.gateway.constant;

import com.mixley.cloud.common.beans.annotations.BeanCode;
import com.mixley.cloud.common.beans.annotations.BeanDescribe;
import com.mixley.cloud.common.beans.annotations.Definition;
import com.mixley.cloud.common.beans.annotations.BeanOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Definition(title = "启用状态")
public enum EnableStatusEnum {

    YES("0", "正常"),
    @BeanOrder(1)
    NO("1", "已取消");
    @BeanCode
    private String code;
    private String describe;

    @BeanDescribe
    public String getDescribeVal(){
        return code+":"+describe;
    }
}
