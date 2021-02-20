package com.cube.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 先从缓存读取，读不到走数据层读取，并存入缓存 只需要选择一个唯一确定的key读取即可，所以这里cachekey不需要数组
 * 
 * @author phoenix
 * @date 2020年2月25日
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheGet {

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	CacheKey get();

}
