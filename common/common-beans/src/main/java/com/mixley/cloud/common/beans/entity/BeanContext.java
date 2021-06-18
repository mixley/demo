package com.mixley.cloud.common.beans.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true,chain = true)
public class BeanContext {
    private final Metadata metadata;
    private final List<Content> contents;
}
