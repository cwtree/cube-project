package com.cube.pojo.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

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
 * @date 2021年5月22日
 */
@Getter
@Setter
@ToString
@Builder
@ApiModel("用户查询")
public class UserQueryDTO {

	@ApiModelProperty("用户所在组织部门")
	@Positive
	private Long departmentId;
	
	@ApiModelProperty("用户手机号")
	@Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]{9}$", message = "手机号格式错误")
	private String phone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startTime;
	
}
