package com.cube.pojo.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cube.common.OrderStatusEnum;
import com.cube.common.OrderTypeEnum;
import com.cube.validator.EnumValidator;
import com.cube.validator.TimeIntervalCheckValidator;
import com.cube.validator.TimeIntervalCheckValidatorClass;
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
 * @date 2021年5月26日
 */
@Getter
@Setter
@ToString
@Builder
@TimeIntervalCheckValidator(beginTime = "startTime", endTime = "endTime", interval = 1, intervalUnit = TimeIntervalCheckValidatorClass.UNIT_YEAR,message = "下单时间查询错误")
@ApiModel("查询参数校验枚举、时间测试对象")
public class EnumDTO {
	
	/**
	 * 查询开始时间
	 */
	@ApiModelProperty(value = "查询开始时间", example = "2021-05-01")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startTime;

	/**
	 * 查询结束时间
	 */
	@ApiModelProperty(value = "查询结束时间", example = "2021-05-01")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime;

	@ApiModelProperty(value = "订单类型")
	@EnumValidator(enumClass = OrderTypeEnum.class,enumValueFieldName = "type", message = "订单类型查询错误")
	private Integer orderType;

	@ApiModelProperty(value = "订单状态")
	@EnumValidator(enumClass = OrderStatusEnum.class, enumValueFieldName="status", message = "订单状态查询错误")
	private Integer orderStatus;

}
