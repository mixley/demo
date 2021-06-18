package com.mixley.cloud.common.beans.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 枚举元数据
 *
 * @author 李志锐
 * @date 2021/05/30
 */
@Getter
@RequiredArgsConstructor
//@AllArgsConstructor
@Accessors(fluent = true)
public class Metadata {
    /** 监听类 */
    private final Class clazz;
    /** 注解 */
    private final List<Annotation> annotations;
    private final List<Method> methods;
    private final List<Field> fields;
    @Setter
    private String tagName;
    @Setter
    private String title;
    @Setter
    private String group;

    public static MetadataBuilder builder() {
        return new MetadataBuilder();
    }

    public static class MetadataBuilder {
        public Metadata build(Class clazz) {
            //注解与注解对应的枚举类
            List<Annotation> annotations = Collections.unmodifiableList(
                    Stream.of(clazz.getAnnotations()).collect(Collectors.toList())
            );
            List<Method> methods = Collections.unmodifiableList(
                    Stream.of(clazz.getDeclaredMethods())
                    .filter(obj -> !Modifier.isStatic(obj.getModifiers()))
                    .collect(Collectors.toList())
            );
            List<Field> fields = Collections.unmodifiableList(
                    Stream.of(clazz.getDeclaredFields())
                    .filter(obj -> !Modifier.isStatic(obj.getModifiers()))
                    .collect(Collectors.toList())
            );
            return new Metadata(clazz, annotations, methods, fields);
        }
    }
}
