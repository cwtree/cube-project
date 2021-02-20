package com.cube.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月7日
 */
@Component
@Slf4j
public class MyEventListener {

	/**
	 * @Async注解，异步通知，不阻塞主线程
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param event
	 */
	@Async
	@EventListener
	public void onNotifyEvent(NotifyEvent event) {
		if (log.isInfoEnabled()) {
			log.info("Event Listener ... {}", event.getSource());
			log.info("发送邮件通知用户...");
		}
	}

}
