package com.cube.manager.third;

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
 * @date 2021年2月22日
 */
@Slf4j
@Component
public class UtilInitManager implements CommandLineRunner {

	@Resource
	private MyConfig myConfig;

	/**
	 * aes 加密
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param str
	 * @return
	 */
	public String aesEnc(String str) {
		return Base64.encode(GlobalVar.AES.encrypt(str));
	}

	/**
	 * aes解密
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param str base64编码的密文
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String aesDec(String str) throws Exception {
		return new String(GlobalVar.AES.decrypt(str), GlobalVar.UTF8);
	}

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
