package com.cube.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年3月10日
 */
@Slf4j
public class JasyptTest {

	@Test
	public void test() {
		StandardPBEStringEncryptor standardPbeStringEncryptor = new StandardPBEStringEncryptor();
		// 配置文件中配置如下的算法
		standardPbeStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
		// 配置文件中配置的password
		standardPbeStringEncryptor.setPassword("oeTX54S6cjv87/ZiVPufcsCRLQS6bmEx");
		// 要加密的文本
		String name = standardPbeStringEncryptor.encrypt("cmcc2019");
		String password = standardPbeStringEncryptor.encrypt("Yw/Vz4kpJUv0+E/4/LuZEA==");
		String redisPassword = standardPbeStringEncryptor.encrypt("cmcc2021");
		// 将加密的文本写到配置文件中
		System.out.println("name=" + name);
		System.out.println("password=" + password);
		System.out.println("redisPassword=" + redisPassword);

		// 要解密的文本
		String name2 = standardPbeStringEncryptor.decrypt("FarrmxSQX5uwtH/NZRxy+g==");
		String password2 = standardPbeStringEncryptor.decrypt("vhiaYB1gl9zPj16yu7uMkA==");
		String redisPassword2 = standardPbeStringEncryptor.decrypt("ZII7UphhbVuJ8c3oxPUeyw==");
		// 解密后的文本
		System.out.println("name2=" + name2);
		System.out.println("password2=" + password2);
		System.out.println("redisPassword2=" + redisPassword2);
	}

	@Test
	public void testDec() {
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword("oeTX54S6cjv87/ZiVPufcsCRLQS6bmEx");
		textEncryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		String enc = "SwWgzZp7gsN0w0+pNlMR4OlZvmkkGekW";
		// log.info("加密后 {}", enc);
		String dec = textEncryptor.decrypt(enc);
		log.info("解密后 {}", dec);
	}

	@Test
	public void test1() {
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword("oeTX54S6cjv87/ZiVPufcsCRLQS6bmEx");
		textEncryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		String enc = textEncryptor.encrypt("xx");
		log.info("加密后 {}", enc);
		String dec = textEncryptor.decrypt(enc);
		log.info("解密后 {}", dec);
	}

	@Test
	public void test2() {
		// byte[] desKey = Base64.decode("lMSiHEDWcDgm4AgLVwFi2i/H+65o8lEV");
		// log.info("--"+Base64.encode(desKey));
		byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
		log.info("DESEDE 秘钥：{}", Base64.encode(key));
	}

}
