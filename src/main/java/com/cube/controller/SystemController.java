package com.cube.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.pojo.MyResp;
import com.cube.pojo.Resp;
import com.cube.pojo.vo.UserVO;
import com.cube.service.PayRouteService;

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

	@Resource
	private PayRouteService payRouteService;

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param payType
	 * @return
	 */
	@ApiOperation("支付策略接口模拟")
	@GetMapping("/pay/{payType}")
	@ResponseBody
	public MyResp pay(@PathVariable @Validated @NotBlank String payType) {
		if (log.isInfoEnabled()) {
			log.info("根据传参选择不同的实现类，共用同一个接口");
		}
		String res = payRouteService.pay("付钱了啊", payType);
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg()).data(res).build();
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param payType
	 * @return
	 */
	@ApiOperation("集合传参")
	@PostMapping("/list")
	@ResponseBody
	public MyResp collectionTest(@RequestBody @Validated List<Long> ids) {
		log.info("集合参数 {}", ids);
		return MyResp.result(Resp.SUCCESS);
	}

	/**
	 * 测试
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ids
	 * @return
	 */
	@ApiOperation("返回测试")
	@PostMapping("/vo")
	@ResponseBody
	public MyResp vo() {
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg())
				.data(UserVO.builder().age(1).id(2L).username("chiwei").build()).build();
	}
}
