package com.cube.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年5月26日
 */
@Getter
@AllArgsConstructor
public enum OrderTypeEnum {

	SUB(0, "订购"), UN_SUB(1, "退订");

	/**
	 * 类型code
	 */
	private Integer type;

	/**
	 * 描述
	 */
	private String desc;

}
