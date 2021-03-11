package com.cube.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

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
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword("mycmccsalt");
		textEncryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		String enc = textEncryptor.encrypt("aa");
		log.info("加密后 {}", enc);
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
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword("oeTX54S6cjv87/ZiVPufcsCRLQS6bmEx");
		textEncryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		String enc = textEncryptor.encrypt("yy");
		log.info("加密后 {}", enc);
		String dec = textEncryptor.decrypt(enc);
		log.info("解密后 {}", dec);
	}

}
