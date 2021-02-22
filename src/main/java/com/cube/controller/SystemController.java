package com.cube.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月22日
 */
@Controller
@Api("系统操作")
@RequestMapping("/system")
@Slf4j
public class SystemController {

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 */
	@ApiOperation("生成图形验证码")
	@GetMapping("/captcha")
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		GifCaptcha captcha = CaptchaUtil.createGifCaptcha(80, 30);
		try {
			captcha.write(response.getOutputStream());
			log.info("验证码内容为 {}", captcha.getCode());
		} catch (Exception e) {
			log.error("验证生成错误", e);
			throw new RuntimeException(e);
		}
	}

}
