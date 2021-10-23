package com.mixley.cloud.common.beans.handle;

import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.core.function.MyHandel;
import org.springframework.core.PriorityOrdered;

import java.lang.reflect.AnnotatedElement;

public interface BaseHandler<T, R extends AnnotatedElement> extends PriorityOrdered {

    Class<T> type();

    Class<R> annotatedElement();

    /**
     * 设置优先级别，值约低，约优先
     *
     * @return int
     */
    default int getOrder() {
        return 0;
    }

    void handel(BeanContext beanContext, R obj, T bean);

    default MyHandel handelFunction(BeanContext beanContext, R obj, T bean){
        BaseHandler handler = this;
        return new MyHandel(){
            @Override
            public int getOrder() {
                return handler.getOrder();
            }
            @Override
            public void handel() {
                handler.handel(beanContext, obj, bean);
            }
        };
    }
}