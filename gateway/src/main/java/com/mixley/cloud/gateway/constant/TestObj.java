package com.mixley.cloud.gateway.constant;

import com.mixley.cloud.common.beans.annotations.Definition;
import com.mixley.cloud.common.beans.annotations.EntityDefinition;
import com.mixley.cloud.common.beans.annotations.EnumCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@EntityDefinition(code = "test_obj",describe = "抓取测试",order = 2)
public class TestObj {

    private String code;
    private String describe;

}
