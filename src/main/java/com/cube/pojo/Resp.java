package com.cube.pojo;

/**
 * 后端返回码定义
 * 
 * @author phoenix
 * @date 2020年2月4日
 */
public enum Resp {

	/**
	 * 2000 成功返回码
	 */
	SUCCESS(2000, "SUCCESS"),

	/**
	 * 3XXX 格式校验类错误，不发生跨网络IO类的错误 如邮箱格式错误，纯内存操作
	 */
	PARAM_ERROR(3000, "参数校验类错误"),

	PARAM_ID_ERROR(3001, "ID参数错误"),

	/**
	 * 4XXX 系统内部需要经过跨网络IO操作 如果xx用户已存在，需要根据用户名查询数据库
	 */
	SYSTEM_ERROR(4000, "系统内部自定义错误"),

	/**
	 * 5XXX 服务端内部异常错误 通过该返回码，研发人员可快速锁定异常范围， 减少不必要的服务端日志排查工作
	 */
	ERROR(5000, "服务端系统错误"),

	;

	private int code;
	private String msg;

	Resp(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
