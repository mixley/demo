package com.mixley.cloud.common.beans.cache;

import com.mixley.cloud.common.core.exception.BaseException;
import com.mixley.cloud.common.beans.errors.ErrorEnum;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 枚举缓存
 *
 * @author 李志锐
 * @date 2021/06/02
 */
public class BeanCache {
    private final static BeanCache instance = new BeanCache();
    private BeanCache() {}
    public static BeanCache getInstance() {return instance;}
    @Getter
    private List<Class> clazzList = new CopyOnWriteArrayList<>();

    /**
     * 注册实体类
     *
     * @param clazz 类
     */
    public void registerClass(Class clazz) {
        if (clazzList.contains(clazz)) {
            throw new BaseException(ErrorEnum.ENUM_IS_REGISTER);
        }
        clazzList.add(clazz);
    }

}
