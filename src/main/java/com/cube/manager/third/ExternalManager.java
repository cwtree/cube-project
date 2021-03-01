package com.cube.manager.third;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cube.common.GlobalVar;
import com.cube.config.MyConfig;

import cn.hutool.core.codec.Base64;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月22日
 */
@Component
public class ExternalManager {

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

}
