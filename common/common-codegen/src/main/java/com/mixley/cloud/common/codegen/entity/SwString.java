package com.mixley.cloud.common.codegen.entity;

import lombok.Data;

@Data
public class SwString extends SwDataType {
    Integer minLength;
    Integer maxLength;
}
