package com.mixley.cloud.gateway.constant;

import com.mixley.cloud.common.beans.annotations.EntityDefinition;
import lombok.Data;

@Data
@EntityDefinition(code = "test_obj",describe = "抓取测试",order = 2)
public class TestObj {

    private String code;
    private String describe;

}
