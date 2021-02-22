package com.cube.controller;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cube.config.MyConfig;
import com.cube.event.NotifyEvent;
import com.cube.pojo.MyResp;
import com.cube.pojo.Resp;
import com.cube.pojo.doo.PhoenixUser;
import com.cube.pojo.dto.UserDTO;
import com.cube.req.resp.advice.ReqDec;
import com.cube.req.resp.advice.RespEnc;
import com.cube.service.UserService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.http.HtmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
@RestController
@Api("用户操作")
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Resource
	private MyConfig myConfig;

	@Resource
	private UserService userService;

	@Resource
	private ApplicationEventPublisher publisher;

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	@ApiOperation("根据ID查询用户信息")
	@GetMapping("/{id}")
	public MyResp getUserById(@PathVariable("id") Long id) {
		if (log.isInfoEnabled()) {
			log.info("根据ID {} 查找用户", id);
		}
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg())
				.data(userService.getUserById(id)).build();
	}

	@ApiIgnore
	@GetMapping("/refresh")
	public MyResp refresh() {
		if (log.isInfoEnabled()) {
			log.info("配置刷新");
		}
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg()).data(myConfig).build();
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param userDto
	 * @return
	 */
	@ApiOperation("保存用户")
	@PostMapping("/save")
	public MyResp saveUser(@RequestBody @Validated UserDTO userDto) {
		if (log.isInfoEnabled()) {
			log.info("保存用户信息 {}", userDto);
		}
		Map<String, String> mapping = CollUtil.newHashMap();
		mapping.put("username", "name");
		PhoenixUser user = new PhoenixUser();
		BeanUtil.copyProperties(userDto, user, CopyOptions.create().setFieldMapping(mapping));
		user.setPassword("123");
		user.setEmail("mail");
		user.setSalt("salt");
		user.setPhoneNumber("188");
		user.setStatus(1);
		//假设name是一个没有明确正则的字段，入库前要过滤HTML字符，防止XSS
		user.setName(HtmlUtil.escape(user.getName()));
		userService.saveUser(user);
		/**
		 * 用户保存成功后，要异步发送邮件通知
		 */
		publisher.publishEvent(new NotifyEvent(user));
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg()).build();
	}

	@Resource(name = "myRedisTemplate")
	private RedisTemplate<String, String> myRedisTemplate;

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@ApiOperation("cache测试")
	@GetMapping("/cache")
	public MyResp cache(HttpServletRequest request) {
		if (log.isInfoEnabled()) {
			log.info("redis 测试");
		}
		// 阻塞队列效果
		String res = myRedisTemplate.opsForList().rightPop("cube-list", 1000L, TimeUnit.MILLISECONDS);
		// 查询用户
		PhoenixUser pu = userService.getUserById(Long.parseLong(request.getParameter("id")));
		log.info("id pu {}", pu);
		pu = userService.getUserByName(request.getParameter("name"));
		log.info("name pu {}", pu);
		pu = userService.getUserByPhone(request.getParameter("phone"));
		log.info("phone pu {}", pu);
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg()).data(res).build();
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @return
	 */
	@ApiOperation("删除用户")
	@GetMapping("/delete/{id}")
	public MyResp delete(@PathVariable Long id) {
		PhoenixUser user = userService.getUserById(id);
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg())
				.data(userService.deleteUser(user)).build();
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @return
	 */
	@ApiOperation("性能测试")
	@GetMapping("/performance/{count}")
	public MyResp performance(@PathVariable Integer count) {
		// 创建N个用户
		TimeInterval timer = DateUtil.timer();
		for (int i = 0; i < count; i++) {
			PhoenixUser pu = PhoenixUser.builder().name("name" + i).email("mail" + i).password("pwd" + i)
					.salt("salt" + i).phoneNumber("phone" + i).status(1).build();
			userService.saveUser(pu);
		}
		log.warn("创建" + count + "个用户耗时：" + timer.intervalSecond() + " 秒");
		// 查询N次
		timer = DateUtil.timer();
		for (int i = 0; i < count; i++) {
			userService.getUserById(i);
			userService.getUserByName("name" + i);
			userService.getUserByPhone("phone" + i);
		}
		log.warn("查询" + count * 3 + "次用户耗时：" + timer.intervalSecond() + " 秒");
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg()).build();
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param userDto
	 * @return
	 */
	@ApiOperation("请求响应报文加解密测试")
	@PostMapping("/encdec")
	@ReqDec
	@RespEnc
	public MyResp encdec(@RequestBody @Validated @NotBlank String cont) {
		log.info("请求报文解密后 {}", cont);
		return MyResp.builder().code(Resp.SUCCESS.getCode()).msg(Resp.SUCCESS.getMsg()).data("my data resp " + cont)
				.build();
	}

}
