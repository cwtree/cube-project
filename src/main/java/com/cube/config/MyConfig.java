package com.cube.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "cube.boot")
@RefreshScope
public class MyConfig {

	private String systemName;
	private String swaggerSwitch;
	private String requestDecSwitch;
	private String responseEncSwitch;
	private String reqRespAesKey;
	private String demoConfigTest;
	// demo.config.test

}
