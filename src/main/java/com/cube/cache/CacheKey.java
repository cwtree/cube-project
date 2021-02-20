package com.cube.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheKey {

	/**
	 * 缓存的key拼接字符串中的属性名 和value的数组长度必须严格一致
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	String[] key() default "";

	/**
	 * 缓存的key拼接字符串中的属性值
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	String[] value() default "";

	/**
	 * 缓存有效期
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	int ttl() default 300;

	/**
	 * 是否缓存空值，防止缓存穿透使用 慎用 如果使用了空缓存，而不使用CachePut，则数据影响周期就是ttl时间
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	boolean cacheNull() default false;

}
