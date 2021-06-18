package com.mixley.cloud.common.beans.config;

import com.mixley.cloud.common.core.function.MyHandel;
import com.mixley.cloud.common.beans.BeanContextHolder;
import com.mixley.cloud.common.beans.cache.BeanCache;
import com.mixley.cloud.common.beans.entity.Metadata;
import com.mixley.cloud.common.beans.handle.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 枚举基本配置
 *
 * @author 李志锐
 * @date 2021/05/29
 */
@Component
@AllArgsConstructor
public class EnumBaseConfig {
    /**
     * 枚举上下文持有人
     *
     * @return {@link BeanContextHolder}
     */
    @Bean
    BeanContextHolder enumContextHolder() {
        return new BeanContextHolder();
    }

    /**
     * 工厂的后置处理程序
     *
     * @return {@link BeanFactoryPostProcessor}
     */
    @Bean
    @Order
    BeanFactoryPostProcessor factoryPostProcessor() {
        return (factory) -> {
            //先获取顺序
            List<EnumHandler> handlers = factory.getBeansOfType(EnumHandler.class).values().stream()
                    //排序后再进行处理,倒叙排列，数字越小越先执行
                    .sorted(Comparator.comparing(EnumHandler::getOrder))
                    .collect(Collectors.toList());
            //获取EnumContextHolder上下文
            BeanContextHolder contextHolder = factory.getBean(BeanContextHolder.class);
            Map<EnumHandler, List<MyHandel>> listMap =
                    //获取枚举缓存
                    BeanCache.getInstance()
                            .getClazzList().parallelStream()
                            //转换为枚举上下文
                            .map(contextHolder::registerContext).flatMap(context -> {
                        //每个枚举都对后置方法进行判断，并最终组织为Map<方法,List<具体的执行>>
                        return handlers.stream().collect(Collectors.toMap(handler -> handler, handler -> {
                            Metadata metadata = context.metadata();
                            if (handler instanceof ClassEnumHandler) {
                                //判断方法继承为类注解处理
                                return metadata.annotations().stream()
                                        //将类上注解与注解上类注解一起处理
                                        .flatMap(entry -> Stream.concat(Stream.of(entry),Stream.of(entry.annotationType().getAnnotations()))
                                                .filter(annotation -> annotation.annotationType() == handler.annotationType())
                                                .map(annotation -> {
                                                    return handler.enumHandelFunction(annotation, context, metadata.clazz());
                                                })).collect(Collectors.toList());
                            } else if (handler instanceof MethodEnumHandler) {
                                //判断方法继承为对方法注解处理
                                return metadata.methods().stream()
                                        .flatMap(entry -> Stream.concat(Stream.of(entry.getAnnotations()),Stream.of(entry.getAnnotations()).flatMap(a->Stream.of(a.annotationType().getAnnotations())))
                                                .filter(annotation -> annotation.annotationType() == handler.annotationType())
                                                .map(annotation -> {
                                                    return handler.enumHandelFunction(annotation, context, entry);
                                                })).collect(Collectors.toList());
                            } else if (handler instanceof FiledEnumHandler) {
                                //判断方法继承为对字段注解处理
                                return metadata.fields().stream()
                                        .flatMap(entry -> Stream.concat(Stream.of(entry.getAnnotations()),Stream.of(entry.getAnnotations()).flatMap(a->Stream.of(a.annotationType().getAnnotations())))
                                                .filter(annotation -> annotation.annotationType() == handler.annotationType())
                                                .map(annotation -> {
                                                    return handler.enumHandelFunction(annotation, context, entry);
                                                })).collect(Collectors.toList());
                            }
                            //不返回空
                            return new ArrayList<MyHandel>();
                        })).entrySet().stream();
                        //同一转map再合并
                    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (m1, m2) -> Stream.concat(m1.stream(),m2.stream()).collect(Collectors.toList())));
            handlers.forEach(handler -> {
                listMap.get(handler).forEach(MyHandel::handel);
            });
            contextHolder.overRegister();
        };
    }


}
