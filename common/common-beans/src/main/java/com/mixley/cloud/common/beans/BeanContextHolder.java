package com.mixley.cloud.common.beans;

import com.mixley.cloud.common.beans.entity.BeanContext;
import com.mixley.cloud.common.beans.entity.Content;
import com.mixley.cloud.common.beans.entity.Metadata;
import com.mixley.cloud.common.beans.handle.BaseHandler;
import com.mixley.cloud.common.core.function.MyHandel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public class BeanContextHolder {
    private final static BeanContextHolder instance = new BeanContextHolder();

    public static BeanContextHolder getInstance() {
        return instance;
    }

    /**
     * 使用BaseHandler创建
     *
     * @param baseHandlers 基础处理程序
     */
    public BeanContextHolder(List<BaseHandler> baseHandlers){
        this.init(baseHandlers);
    }
    /**
     * 复制一个可以继续增加的上下文
     *
     * @param beanContextHolder bean上下文持有人
     */
    public BeanContextHolder(BeanContextHolder beanContextHolder){
        this.contextList.addAll(beanContextHolder.getContextList());
    }
    @Getter
    private List<BeanContext> contextList = new CopyOnWriteArrayList<>();


    public BeanContext registerContext(Object bean) {
        if (bean instanceof BeanContext){
            BeanContext beanContext = (BeanContext) bean;
            contextList.add(beanContext);
            return beanContext;
        }
        Metadata metadata = Metadata.builder().build(bean.getClass());
        BeanContext beanContext = new BeanContext(metadata, Content.builder().build(metadata, bean));
        contextList.add(beanContext);
        return beanContext;
    }

    /**
     * 初始化
     * 单独出来，允许BaseHandler手动注入了，其余非spring的程序，可以想办法迁移进行注入
     *
     * @param baseHandlers 基础处理程序
     */
    public void init(List<BaseHandler> baseHandlers) {
        Map<Boolean, List<BaseHandler>> listMap = baseHandlers
                .stream().collect(Collectors.partitioningBy(beanHandler -> beanHandler.type().isAnnotation()));
        Map<Class, List<BaseHandler>> annotationHandMap = listMap.get(true).stream().collect(Collectors.groupingBy(handler -> handler.type()));
        Map<BaseHandler, List<Metadata>> metaHandMap = listMap.get(false).stream().collect(Collectors.toMap(handler -> handler, handler -> Metadata.MetadataBuilder.getMetaAssignable(handler.type())));
        //获取EnumContextHolder上下文
        BeanContextHolder contextHolder = this;
        contextHolder.getContextList().parallelStream().flatMap(context ->
                        Stream.concat(
                                metaHandMap.entrySet().stream()
//                                    .filter(entry->entry.getValue().contains(context.metadata()))
                                        .flatMap(entry -> entry.getValue().stream()
                                                .filter(metadata -> Class.class.isAssignableFrom(entry.getKey().annotatedElement()))
                                                .map(metadata -> {
                                                    if (metadata.equals(context.metadata())) {
                                                        return entry.getKey().handelFunction(context, metadata.clazz(), context.content().object());
                                                    } else {
                                                        return null;
                                                    }
                                                })).filter(Objects::nonNull)
                                ,
                                context.metadata().annotationMap().entrySet()
                                        .stream().flatMap(entry -> annotationHandMap.getOrDefault(entry.getKey().annotationType(), (List<BaseHandler>) Collections.EMPTY_LIST)
                                        .stream()
                                        .filter(handler -> entry.getValue().getClass().isAssignableFrom(handler.annotatedElement()))
                                        .map(handler -> handler.handelFunction(context, entry.getValue(), entry.getKey()))
                                )
                        )
        )
                //排序后再进行处理,倒叙排列，数字越小越先执行
                .sorted(Comparator.comparing(MyHandel::getOrder))
                .forEach(MyHandel::handel);
    }
    /**
     * 停止注册时执行
     */
    public void overRegister() {
        //将contextList设置为不可编辑
        contextList = Collections.unmodifiableList(contextList);
    }
}
