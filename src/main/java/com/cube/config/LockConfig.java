package com.cube.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月19日
 */
@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
public class LockConfig {

	@Bean
	public LockProvider lockProvider(RedisConnectionFactory factory) {
		return new RedisLockProvider(factory);
	}

}
