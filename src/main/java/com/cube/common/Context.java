package com.cube.common;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年5月19日
 */
@Getter
@Setter
@ToString
@Builder
public class Context {

	private String traceId;
	private Date timestamp;
	private String stack;

}
