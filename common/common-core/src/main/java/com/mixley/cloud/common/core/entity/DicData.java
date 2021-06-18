package com.mixley.cloud.common.core.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 李志锐
 * @CreateTime: 2019-12-24 21:43
 * @Description:
 **/
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DicData<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private String group;
    @Getter
    @Setter
    private String label;

    @Getter
    @Setter
    private T value;

    @Getter
    @Setter
    private List<DicData<T>> children;

    public DicData(String group,String label, T value) {
        this.group = group;
        this.label = label;
        this.value = value;
    }
    public DicData(String label, T value) {
        this.label = label;
        this.value = value;
    }
}
