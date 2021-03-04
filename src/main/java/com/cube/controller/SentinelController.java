package com.cube.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cube.pojo.MyResp;
import com.cube.pojo.Resp;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-3-4
 */
@RestController
@Slf4j
@RequestMapping("/sentinel")
public class SentinelController {

	@PostMapping("/update/headPic")
	public MyResp userHeadPicUpdate() {
		if (log.isInfoEnabled()) {
			log.info("进行用户头像更新的操作");
			log.info("第一步。。。");
			log.info("第二步。。。");
			log.info("第三步。。。");
		}
		return MyResp.result(Resp.SUCCESS);
	}

}
