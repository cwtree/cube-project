package com.cube.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cube.validator.UserAgeValidator;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Constraint(validatedBy = { UserAgeValidator.class })
public @interface UserAge {

	String value() default "";

	String message() default "用户年龄格式错误";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
