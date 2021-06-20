package com.mixley.cloud.common.beans;

import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.entity.Metadata;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@NoArgsConstructor
public class BeanContextHolder {
    private final static BeanContextHolder instance = new BeanContextHolder();

    public static BeanContextHolder getInstance() {
        return instance;
    }

    @Getter
    private List<BeanContext> contextList = new CopyOnWriteArrayList<>();
    public BeanContext registerContext(Object bean) {
        Metadata metadata = Metadata.builder().build(bean.getClass());
        BeanContext beanContext = new BeanContext(metadata, Content.builder().build(metadata, bean));
        contextList.add(beanContext);
        return beanContext;
    }

    /**
     * 停止注册时执行
     */
    public void overRegister() {
        //将contextList设置为不可编辑
        contextList = Collections.unmodifiableList(contextList);
    }
}
