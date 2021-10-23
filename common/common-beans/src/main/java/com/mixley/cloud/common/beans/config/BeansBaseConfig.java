package com.mixley.cloud.common.beans.config;

import com.mixley.cloud.common.beans.BeanContextHolder;
import com.mixley.cloud.common.beans.handle.BaseHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 实体基本配置
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
            Collection<BaseHandler> baseHandlers = factory.getBeansOfType(BaseHandler.class).values();
            BeanContextHolder instance = BeanContextHolder.getInstance();
            instance.init(new ArrayList<>(baseHandlers));
            //注册完成后处理
            instance.overRegister();
        };
    }


}
