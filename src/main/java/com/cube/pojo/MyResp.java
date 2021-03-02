package com.cube.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 前后端交互统一数据格式
 * 
 * @author phoenix
 * @date 2020年2月4日
 */
@Getter
@Setter
@ToString
@Builder
public class MyResp {

	private int code;
	private String msg;
	private Object data;
	/**
	 * 该字段用来赋值具体的错误exception等信息，方便通过返回能直接定位到接口错误原因
	 * 避免登服务器查日志，该字段前端不用处理，留给后端研发通过接口返回查看
	 */
	private Object errMsg;

	public static MyResp result(Resp resp) {
		return MyResp.builder().code(resp.getCode()).msg(resp.getMsg()).build();
	}

	public static MyResp result(Resp resp, Object data) {
		return MyResp.builder().code(resp.getCode()).msg(resp.getMsg()).data(data).build();
	}

	public static MyResp result(Resp resp, Object data, Object errMsg) {
		return MyResp.builder().code(resp.getCode()).msg(resp.getMsg()).data(data).errMsg(errMsg).build();
	}

}
