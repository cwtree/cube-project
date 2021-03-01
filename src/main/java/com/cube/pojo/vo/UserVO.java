package com.cube.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
@Getter
@Setter
@ToString
@Builder
public class UserVO {

	private Long id;
	/**
	 * jackson 返回给前端的json字段名字变更 如果是fastjson，用JSONField
	 */
	@JsonProperty("user_name")
	private String username;
	private Integer age;

}
