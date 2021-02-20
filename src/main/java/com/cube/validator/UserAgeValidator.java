package com.cube.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cube.validator.annotation.UserAge;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年2月10日
 */
public class UserAgeValidator implements ConstraintValidator<UserAge, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value != null && value >= 0 && value <= 130;
	}

}
