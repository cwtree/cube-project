package com.cube.pojo.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.cube.validator.annotation.UserAge;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("新增用户请求参数")
public class UserDTO {

	@ApiModelProperty("用户名")
	@Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$", message = "用户名格式错误")
	private String username;

	/**
	 * 自定义注解校验参数格式
	 */
	@ApiModelProperty("用户年龄")
	@UserAge
	private Integer age;

	@ApiModelProperty("出生年月日")
	// 适合application/json，反射AbstractJackson2HttpMessageConverter#canRead(Type,
	// Class<?>, MediaType)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	// 适合application/form
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;

	@ApiModelProperty("用户备注")
	@Size(min = 0, max = 50, message = "用户备注字段过长")
	private String comment;

}
