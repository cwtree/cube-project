package com.cube.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 时间跨度校验
 * 
 * @author phoenix
 * @date 2021年5月24日
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE,
    ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeIntervalCheckValidatorClass.class)
@Documented
public @interface TimeIntervalCheckValidator {

	String beginTime() default "beginTime";
	 
    String endTime() default "endTime";
    
    int interval() default 0;
    
    String intervalUnit() default "day";
 
    String message() default "查询时间有误 ";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};

}
