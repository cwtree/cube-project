package com.cube.runner;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cube.common.GlobalVar;
import com.cube.config.MyConfig;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年3月1日
 */
@Slf4j
@Component
public class InitRunner implements CommandLineRunner {

	@Resource
	private MyConfig myConfig;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		byte[] key = Base64.decode(myConfig.getReqRespAesKey());
		GlobalVar.AES = SecureUtil.aes(key);
		if (log.isInfoEnabled()) {
			log.info("完成AES加解密对象初始化");
		}
	}

}
