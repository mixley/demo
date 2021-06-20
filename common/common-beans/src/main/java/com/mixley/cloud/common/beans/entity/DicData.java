package com.mixley.cloud.common.beans.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mixley.cloud.common.core.utils.Checks;
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
	private String label;

	/** 禁用 */
	@Getter
	@Setter
	private Boolean disabled;

	@Getter
	@Setter
	private T value;

	@Getter
	@Setter
	private List<DicData<T>> children;

	@JsonProperty("hasChildren")
	public Boolean getHasChildren() {
		return !Checks.isNull(children);
	}

	public DicData(String label, T value) {
		this.label = label;
		this.value = value;
	}
}
