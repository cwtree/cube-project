package com.cube.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 枚举值校验注解——通用
 * 
 * @author phoenix
 * @date 2021年5月22日
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy = EnumValidatorClass.class)
public @interface EnumValidator {

	Class<?> enumClass();
	
	/**
	 * 获取枚举值的方法名
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	String enumValueFieldName() default "";

	String message() default "参数不在枚举范围内";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
