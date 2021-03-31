package com.cube;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-2-22
 */
public class MyTest {

	public static void main(String[] args) {
		Snowflake sf = IdUtil.createSnowflake(1111L, 2222L);
		for (int i = 0; i < 10; i++) {
			System.out.println(sf.nextId());
		}
	}

}
