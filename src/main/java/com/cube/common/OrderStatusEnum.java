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
public enum OrderStatusEnum {

	SUCCESS(0, "成功"), FAIL(1, "失败");

	/**
	 * 类型code
	 */
	private Integer status;

	/**
	 * 描述
	 */
	private String desc;

}
