package com.mixley.cloud.common.beans.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举值
 *
 * @author 李志锐
 * @date 2021/05/30
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Content {
    private final Metadata metadata;
    /** 对象 */
    private final Object object;
    /** 名字 */
    private final String name;
    /** 拓展值 */
    private final Map<String, Object> expandMap = new HashMap<>();

    @Setter
    private int order = 0;

    /** 编码 */
    @Setter
    private String code;
    /** 描述 */
    @Setter
    private String describe;


    public static ContentBuilder builder() {
        return new ContentBuilder();
    }

    public static class ContentBuilder {
        public Content build(Metadata metadata, Object object) {
            String name;
            if (object instanceof Enum) {
                name = ((Enum) object).name();
            } else {
                name = object.getClass().getSimpleName();
            }
            Content content = new Content(metadata, object, name);
            //初始化
            content.code(name);
            content.describe("");
            return content;
        }
    }

}
