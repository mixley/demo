package com.mixley.cloud.common.beans.annotations;

import com.mixley.cloud.common.beans.cache.BeanCache;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;

import java.lang.annotation.Annotation;

/**
 * 枚举注解条件处理条件
 *
 * @author 李志锐
 * @date 2021/05/29
 */
public class EnumCondition implements Condition {

    /**
     * 匹配
     *
     * @param context  上下文
     * @param metadata 元数据
     * @return boolean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean needRegister = true;
        String classOrMethodName = getClassOrMethodName(metadata);
        try {
            Class<?> aClass = Class.forName(classOrMethodName);
            if (!aClass.isAnnotation()){
                BeanCache.getInstance().registerClass(aClass);
            }else {
                needRegister = false;
            }
            if (aClass.isEnum()) {
                needRegister = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return needRegister;
    }

    /**
     * 元数据得到注解类型
     *
     * @param metadata 元数据
     * @return {@link Class<? extends Annotation>[]}
     */
    @Deprecated
    private Class<? extends Annotation>[] getAnnotationTypes(AnnotatedTypeMetadata metadata) {
        Class[] collect = metadata.getAnnotations().stream()
                .filter(MergedAnnotation::isDirectlyPresent)
                .map(MergedAnnotation::getType)
                .toArray(Class[]::new);
        return collect;
    }

    /**
     * 得到类或方法的名称
     *
     * @param metadata 元数据
     * @return {@link String}
     */
    private static String getClassOrMethodName(AnnotatedTypeMetadata metadata) {
        if (metadata instanceof ClassMetadata) {
            ClassMetadata classMetadata = (ClassMetadata) metadata;
            return classMetadata.getClassName();
        }
        MethodMetadata methodMetadata = (MethodMetadata) metadata;
        return methodMetadata.getDeclaringClassName() + "#" + methodMetadata.getMethodName();
    }
}
