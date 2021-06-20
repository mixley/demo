package com.mixley.cloud.common.beans.config;

import com.mixley.cloud.common.beans.BeanContextHolder;
import com.mixley.cloud.common.beans.entity.Metadata;
import com.mixley.cloud.common.beans.handle.BaseHandler;
import com.mixley.cloud.common.beans.handle.BeanHandler;
import com.mixley.cloud.common.core.function.MyHandel;
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
public class BeansBaseConfig {

    /**
     * 工厂的后置处理程序
     *
     * @return {@link BeanFactoryPostProcessor}
     */
    @Bean
    @Order
    BeanFactoryPostProcessor factoryPostProcessor() {
        return (factory) -> {
            Map<Boolean, List<BaseHandler>> listMap = factory.getBeansOfType(BaseHandler.class).values()
                    .stream().collect(Collectors.partitioningBy(beanHandler -> beanHandler.type().isAnnotation()));
            Map<Class, List<BaseHandler>> annotationHandMap = listMap.get(true).stream().collect(Collectors.groupingBy(handler -> handler.type()));
            Map<BaseHandler, List<Metadata>> metaHandMap = listMap.get(false).stream().collect(Collectors.toMap(handler -> handler, handler -> Metadata.MetadataBuilder.getMetaAssignable(handler.type())));
            //获取EnumContextHolder上下文
            BeanContextHolder contextHolder = BeanContextHolder.getInstance();
            contextHolder.getContextList().parallelStream().flatMap(context ->
                    Stream.concat(
                            metaHandMap.entrySet().stream()
//                                    .filter(entry->entry.getValue().contains(context.metadata()))
                            .flatMap(entry->entry.getValue().stream().map(metadata -> {
                                if (metadata.equals(context.metadata())) {
                                    return entry.getKey().handelFunction(context,metadata.clazz(),context.content().object());
                                }else {
                                    return null;
                                }
                            })).filter(Objects::nonNull)
                            ,
                context.metadata().annotationMap().entrySet()
                        .stream().flatMap(entry ->annotationHandMap.getOrDefault(entry.getKey().annotationType(), (List<BaseHandler>) Collections.EMPTY_LIST)
                                .stream().map(handler -> handler.handelFunction(context, entry.getValue(), entry.getKey()))
                )
                    )
            )
                    //排序后再进行处理,倒叙排列，数字越小越先执行
                    .sorted(Comparator.comparing(MyHandel::getOrder))
                    .forEach(MyHandel::handel);

            //注册完成后处理
            contextHolder.overRegister();
        };
    }


}
