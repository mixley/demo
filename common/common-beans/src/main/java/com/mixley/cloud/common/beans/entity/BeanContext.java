package com.mixley.cloud.common.beans.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true,chain = true)
public class BeanContext {
    private final Metadata metadata;
    private final Content content;
}
