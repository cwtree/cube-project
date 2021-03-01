package com.cube.req.resp.advice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.cube.config.MyConfig;
import com.cube.manager.third.ExternalManager;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月22日
 */
@Slf4j
@ControllerAdvice(basePackages = { "com.cube.controller" })
public class ReqAdvice implements RequestBodyAdvice {

	@Resource
	private MyConfig myConfig;

	@Resource
	private ExternalManager utilInitManager;

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return Boolean.parseBoolean(myConfig.getRequestDecSwitch());
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		if (parameter.hasMethodAnnotation(ReqDec.class)) {
			try {
				return decBody(inputMessage);
			} catch (Exception e) {
				log.error("报文解密异常", e);
				throw new RuntimeException(e);
			}
		}
		return inputMessage;
	}

	private HttpInputMessage decBody(HttpInputMessage inputMessage) throws Exception, IOException {
		String enc = IoUtil.readUtf8(inputMessage.getBody());
		log.info("接收密文: {}", enc);
		String plain = new String(utilInitManager.aesDec(enc));
		log.info("解密明文: {}", plain);
		InputStream is = IoUtil.toUtf8Stream(plain);
		return new MyHttpInputMessage(inputMessage.getHeaders(), is);
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		if (log.isInfoEnabled()) {
			log.info("RequestBodyAdvice afterBodyRead body {}", body);
		}
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		if (log.isInfoEnabled()) {
			log.info("RequestBodyAdvice handleEmptyBody body {}", body);
		}
		return body;
	}

}

class MyHttpInputMessage implements HttpInputMessage {

	private HttpHeaders headers;

	private InputStream body;

	public MyHttpInputMessage(HttpHeaders headers, InputStream body) throws Exception {
		this.headers = headers;
		this.body = body;
	}

	@Override
	public HttpHeaders getHeaders() {
		// TODO Auto-generated method stub
		return headers;
	}

	@Override
	public InputStream getBody() throws IOException {
		// TODO Auto-generated method stub
		return body;
	}

}
