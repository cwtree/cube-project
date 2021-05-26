package com.cube.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.collect.Lists;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举值校验注解的具体实现
 * 
 * @author phoenix
 * @date 2021年5月22日
 */
@Slf4j
public class EnumValidatorClass implements ConstraintValidator<EnumValidator, Object>, Annotation {

	private List<Object> values = Lists.newArrayList();

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return ObjectUtil.isNull(value) || values.contains(value);
	}

	/**
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(EnumValidator enumValidator) {
		Class<?> clazz = enumValidator.enumClass();
		Object[] objs = clazz.getEnumConstants();
		try {
			Method method = clazz.getMethod("get" + StrUtil.upperFirst(enumValidator.enumValueFieldName()));
			if (ObjectUtil.isNull(method)) {
				log.error("枚举对象 {} 缺少字段名为 {} 的字段", clazz.getName(), enumValidator.enumValueFieldName());
				System.exit(0);
			}
			Object value = null;
			for (Object obj : objs) {
				value = method.invoke(obj);
				values.add(value);
			}
		} catch (Exception e) {
			log.error("枚举类参数校验异常", e);
			System.exit(0);
		}
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		return null;
	}

}
