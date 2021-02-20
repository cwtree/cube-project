package com.cube.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月19日
 */
public class Utils {

	private static Random r = new SecureRandom();

	/**
	 * 防止缓存雪崩，随机过期时间
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ttl
	 * @return
	 */
	public static int randomTtl(int ttl) {
		return ttl + randomNum(0, ttl);
	}

	/**
	 * randomNum:(). <br/>
	 *
	 * @param begin
	 * @param end
	 * @return
	 * @author chiwei
	 * @since JDK 1.6
	 */
	public static int randomNum(int begin, int end) {
		return r.nextInt(end - begin) + begin;
	}

}
