package com.mixley.cloud.common.core.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * 检查
 *
 * @author 李志锐
 * @date 2021/05/30
 */
@UtilityClass
public class Checks {
    /**
     * 为空
     *
     * @param obj 对象
     * @return boolean
     */
    public boolean isNull(Object obj){
        return null == obj;
    }

    /**
     * 是空的
     *
     * @param str 字符串
     * @return boolean
     */
    public boolean isEmpty(String str){
        return isNull(str)||str.length()==0;
    }

    /**
     * 是空的
     *
     * @param objs str
     * @return boolean
     */
    public boolean isEmpty(Object... objs){
        return isNull(objs)||objs.length==0;
    }

    /**
     * 是空的
     *
     * @param coll 列表
     * @return boolean
     */
    public boolean isEmpty(Collection coll){
        return isNull(coll)||coll.size()==0;
    }


}
