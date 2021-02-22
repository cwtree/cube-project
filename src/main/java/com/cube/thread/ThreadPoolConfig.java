package com.cube.thread;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * 
 * @author phoenix
 * @date 2020年2月5日
 */
@Configuration
public class ThreadPoolConfig {

	/**
	 * 
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@Bean("myThread")
	public Executor myExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix("my-thread-");
		executor.setMaxPoolSize((Runtime.getRuntime().availableProcessors() << 1) + 1);
		executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
		return executor;
	}

}
