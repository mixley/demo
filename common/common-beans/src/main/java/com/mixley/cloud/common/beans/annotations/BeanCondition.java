package com.mixley.cloud.common.beans.annotations;

import com.mixley.cloud.common.beans.BeanContextHolder;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

/**
 * 枚举注解条件处理条件
 *
 * @author 李志锐
 * @date 2021/05/29
 */
public class BeanCondition implements Condition {

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
            BeanContextHolder contextHolder = BeanContextHolder.getInstance();
            if (!aClass.isAnnotation()) {
                if (aClass.isEnum()) {
                    //如果是枚举，无法注册为bean
                    needRegister = false;
                    //枚举拆分开再进行注册
                    Stream.of(aClass.getEnumConstants()).forEach(contextHolder::registerContext);
                } else {
                    //直接注册
                    Object bean = context.getBeanFactory().createBean(aClass);
//                    Object bean = context.getBeanFactory().getBean(aClass);
                    contextHolder.registerContext(bean);
                }
            }else{
                needRegister = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
//        return needRegister;
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
