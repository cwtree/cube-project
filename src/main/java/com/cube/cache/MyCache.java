package com.cube.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 某些方法需要组合注解的用这个注解标注 put和del返回必然是int型，操作影响行数
 * 
 * @author phoenix
 * @date 2020年3月4日
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyCache {

	CachePut[] put() default {};// 保存数据同时设置多个key缓存

	// CacheGet[] get() default {};

	CacheDel[] del() default {};// 删除对象，需要删除该对象的所有缓存数据

}
