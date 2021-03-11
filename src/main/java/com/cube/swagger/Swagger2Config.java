package com.cube.swagger;

import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cube.config.MyConfig;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * 
 * @author phoenix
 * @date 2020年2月10日
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Resource
	private MyConfig myConfig;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).enable(Boolean.parseBoolean(myConfig.getSwaggerSwitch()))
				.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
				// 错误路径不监控
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				// 对根下所有路径进行监控
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("cube系列示例工程", "API信息", "API V1.0", "xxx",
				new Contact("CUBE", "CUBE", "chiweitree2008@126.com"), "CUBE.HZ", "", Collections.emptyList());
	}

}
