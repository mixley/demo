package com.mixley.cloud.common.beans;

import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Metadata;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeanContextHolder {
    @Getter
    private List<BeanContext> contextList = new CopyOnWriteArrayList<>();

    public BeanContext registerContext(Class clazz) {
        Metadata metadata = Metadata.builder().build(clazz);
        List<Content> contents;
        if (clazz.isEnum()){
            contents = Stream.of(clazz.getEnumConstants())
                    .map(anEnum -> Content.builder().build(metadata, anEnum))
                    .collect(Collectors.toList());
        }else {
            contents = new ArrayList(){{add(Content.builder().build(metadata, clazz));}};
        }
        BeanContext beanContext = new BeanContext(metadata, contents);
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
