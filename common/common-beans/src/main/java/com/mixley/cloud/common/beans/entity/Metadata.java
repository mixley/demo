package com.mixley.cloud.common.beans.entity;

import com.mixley.cloud.common.beans.annotations.Definition;
import com.mixley.cloud.common.core.utils.Checks;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 枚举元数据
 *
 * @author 李志锐
 * @date 2021/05/30
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
//@AllArgsConstructor
@Accessors(fluent = true)
public class Metadata {
    /** 监听类 */
    private final Class clazz;
    /** 定义 */
    private final Definition definition;
    /** 注解 */
    private Map<Annotation, AnnotatedElement> annotationMap = new HashMap<>();

    /** 父元素tag标记 */
    private final String tagName;
    /** 父元素标题 */
    private final String title;

    private final Map<String, Object> expandMap = new HashMap<>();

    public static MetadataBuilder builder() {
        return new MetadataBuilder();
    }

    private  <T extends AnnotatedElement> void registerAnnotation(T obj) {
        Annotation[] annotations = obj.getDeclaredAnnotations();
        Stream.of(annotations).forEach(annotation -> {
            annotationMap.put(annotation, obj);
        });
    }

    private void finishRegisterAnnotation() {
        annotationMap = Collections.unmodifiableMap(annotationMap);
    }


    public static class MetadataBuilder {
        private static final Map<Class,Metadata> metadataMap = new HashMap<>();
        public static List<Metadata> getMetaAssignable(Class clazz){
            List<Metadata> metadata = new ArrayList<>();
            if (metadataMap.containsKey(clazz)){
                metadata.add(metadataMap.get(clazz));
            }
            for (Class aClass : metadataMap.keySet()) {
                if (clazz.isAssignableFrom(aClass)){
                    metadata.add(metadataMap.get(aClass));
                }
            }
            return metadata;
        }

        private Definition getDefinitionByClass(Class<?> clazz){
            Definition definition = clazz.getDeclaredAnnotation(Definition.class);
            if (null!=definition){
                return definition;
            }
            for (Annotation annotation : clazz.getDeclaredAnnotations()) {
                definition = annotation.annotationType().getDeclaredAnnotation(Definition.class);
            }
            if (null==definition){
                for (Annotation annotation : clazz.getDeclaredAnnotations()) {
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    definition = getDefinitionByClass(annotationType);
                    if (null!= definition){
                        break;
                    }
                }
            }
            return definition;
        }
        public Metadata build(Class clazz) {
            if (metadataMap.containsKey(clazz)){
                return metadataMap.get(clazz);
            }
            Definition definition = getDefinitionByClass(clazz);
            //注解与注解对应的枚举类
            String tagName = definition.value();
            if (Checks.isEmpty(tagName)) {
                //tagName默认为类名称
                tagName = clazz.getSimpleName();
            }
            Metadata metadata = new Metadata(clazz,definition,tagName,definition.title());
//            //注册类上的注解类
            metadata.registerAnnotation(clazz);
            //注册类上的注解类
            Stream.of(clazz.getDeclaredClasses())
//                    .filter(obj -> !Modifier.isStatic(obj.getModifiers()))
                    .forEach(metadata::registerAnnotation);
            //注册方法上的注解类
            Stream.of(clazz.getDeclaredMethods())
//                    .filter(obj -> !Modifier.isStatic(obj.getModifiers()))
                    .forEach(metadata::registerAnnotation);
            //注册实体上的注解类
            Stream.of(clazz.getDeclaredFields())
//                    .filter(obj -> !Modifier.isStatic(obj.getModifiers()))
                    .forEach(metadata::registerAnnotation);
            //注册构造方法上的注解类
            Stream.of(clazz.getDeclaredConstructors())
//                    .filter(obj -> !Modifier.isStatic(obj.getModifiers()))
                    .forEach(metadata::registerAnnotation);

            //注册完成后，防止篡改
            metadata.finishRegisterAnnotation();

            metadataMap.put(clazz,metadata);
            return metadata;
        }


    }

}
