package com.cube.config;

import javax.annotation.Resource;

import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-3-4
 */
@Component
@Slf4j
public class ApolloConfigRefresh {

	private static final String LOG_LEVEL_PREFIX = "logging.level.";

	private static final String LOGGER_DEFAULT_LEVEL = "WARN";

	@Resource
	RefreshScope refreshScope;

	@Resource
	private LoggingSystem loggingSystem;

	@Resource
	private ObjectMapper objectMapper;

	@ApolloConfigChangeListener
	public void applicationChange(ConfigChangeEvent changeEvent) {
		String namespace = changeEvent.getNamespace();
		// 刷新所有的配置，不仅仅是前缀的
		refreshScope.refreshAll();
		log.info("{} namespace config refresh ... ", namespace);
		refreshLogLevel(changeEvent);
	}

	private void refreshLogLevel(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			log.error("log config # changed value {} ", changeEvent.getChange(key));
			if (StrUtil.startWithIgnoreCase(key, LOG_LEVEL_PREFIX)) {
				ConfigChange configChange = changeEvent.getChange(key);
				// 删除配置时，key对应的newValue为空，这里设置为默认日志级别warn
				String newValue = StrUtil.isBlank(configChange.getNewValue()) ? LOGGER_DEFAULT_LEVEL
						: configChange.getNewValue();
				LogLevel level = LogLevel.valueOf(newValue.toUpperCase());
				// 去除日志前缀
				loggingSystem.setLogLevel(key.replace(LOG_LEVEL_PREFIX, ""), level);
				log.info("key:{},new value:{},old value:{}", key, newValue, configChange.getOldValue());
			}
		}
	}

}
