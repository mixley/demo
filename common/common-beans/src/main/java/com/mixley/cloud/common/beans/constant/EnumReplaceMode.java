package com.mixley.cloud.common.beans.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举重复值模式
 * 元素注册类型，默认为报错，可以选择将相同元素进行追加和覆盖
 *
 * @author 李志锐
 * @date 2021/05/29
 */
@Getter
@AllArgsConstructor
public enum EnumReplaceMode {
    THROW("TAG+GROUP重复 报错"),
    KEY_THROW("TAG+GROUP+KEY重复 报错"),
    APPEND_KEY_OVERRIDE("TAG+GROUP+KEY重复 覆盖"),
    APPEND_KEY_DISCARD("TAG+GROUP+KEY重复 丢弃");

    String name;
}
