package com.cube.event;

import org.springframework.context.ApplicationEvent;

import com.cube.pojo.doo.PhoenixUser;
import com.google.common.base.Preconditions;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月7日
 */
public class NotifyEvent extends ApplicationEvent {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public NotifyEvent(PhoenixUser source) {
		super(source);
	}

	public PhoenixUser getApp() {
		Preconditions.checkState(source != null);
		return (PhoenixUser) this.source;
	}

}
