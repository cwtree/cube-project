package com.cube.req.resp.advice;

import javax.annotation.Resource;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.cube.config.MyConfig;
import com.cube.manager.third.UtilInitManager;
import com.cube.pojo.MyResp;
import com.cube.pojo.Resp;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月22日
 */
@Slf4j
@ControllerAdvice(basePackages = { "com.cube.controller" })
public class RespAdvice implements ResponseBodyAdvice<Object> {

	@Resource
	private MyConfig myConfig;

	@Resource
	private UtilInitManager utilInitManager;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return returnType.hasMethodAnnotation(RespEnc.class) && Boolean.parseBoolean(myConfig.getResponseEncSwitch());
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body != null) {
			try {
				MyResp resp = (MyResp) body;
				String data = (String) resp.getData();
				if (StrUtil.isNotBlank(data)) {
					String newData = Base64.encode(utilInitManager.aesEnc(data));
					resp.setData(newData);
					return resp;
				} else {
					if (Resp.SUCCESS.getCode() != resp.getCode()) {
						return resp;
					}
				}
			} catch (Exception e) {
				log.error("response数据加密异常", e);
				return MyResp.result(Resp.ERROR);
			}
		}
		return body;
	}

}
