package com.cube.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cube.config.MyConfig;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * 轻量级定时任务
 * 
 * @author phoenix
 * @date 2020年2月4日
 */
@Component
@Slf4j
public class MyTask {

	@Resource
	private MyConfig myConfig;

	/**
	 * 测试任务 秒 分 小时 日期 月份 星期 每10秒执行 set nx px 任务实际执行完之后DEL锁
	 *
	 * @throws InterruptedException
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	@SchedulerLock(name = "LockDemo", lockAtLeastFor = "1S", lockAtMostFor = "10S")
	public void task() {
		log.info(" ......  {}", myConfig.getSystemName());
		try {
			Thread.sleep(15000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
