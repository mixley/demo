package com.mixley.cloud.common.beans.entity;

import com.mixley.cloud.common.core.utils.Covers;
import com.mixley.cloud.common.beans.constant.EnumsConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 枚举值
 *
 * @author 李志锐
 * @date 2021/05/30
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Content {
//    private final Metadata metadata;
    /** 对象 */
    private final Object object;
    /** 名字 */
    private final String name;
    /** 枚举值 */
    private final Map<String, Object> expandMap;
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
            if (object instanceof Enum){
                name = ((Enum)object).name();
            }else if (object instanceof Class){
                name = ((Class)object).getSimpleName();
            }else {
                name = object.getClass().getSimpleName();
            }
            Map<String, Object> valueMap = metadata.fields().stream()
                    .collect(Collectors.toMap(Field::getName, Covers.field2Val(object)));
            String code = String.valueOf(valueMap.getOrDefault(EnumsConstants.ENUM_CODE, name));
            String describe = String.valueOf(valueMap.getOrDefault(EnumsConstants.ENUM_DESCRIBE, name));
            return new Content(object, name, valueMap, code, describe);
        }
    }

}
