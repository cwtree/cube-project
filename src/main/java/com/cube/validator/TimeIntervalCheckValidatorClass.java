package com.cube.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;

/**
 * 时间跨度校验器
 * 
 * @author phoenix
 * @date 2021年5月24日
 */
public class TimeIntervalCheckValidatorClass implements ConstraintValidator<TimeIntervalCheckValidator, Object> {

	public static final String UNIT_DAY = "day";
	public static final String UNIT_MONTH = "month";
	public static final String UNIT_YEAR = "year";

	private String beginTime;
	private String endTime;
	private int interval;
	private String intervalUnit;

	/**
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(TimeIntervalCheckValidator constraintAnnotation) {
		this.beginTime = constraintAnnotation.beginTime();
		this.endTime = constraintAnnotation.endTime();
		this.interval = constraintAnnotation.interval();
		this.intervalUnit = constraintAnnotation.intervalUnit();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		Date begin = (Date) beanWrapper.getPropertyValue(beginTime);
		Date end = (Date) beanWrapper.getPropertyValue(endTime);
		/**
		 * 开始时间在结束时间之后，错误 开始时间加一年在结束时间之前，错误
		 */
        return !begin.after(end) && !dateOffset(begin, interval, intervalUnit).before(end);
	}

	/**
     * 时间转换
     * 
     * @Description: 时间转换
     * @param interval
     * @param type
     * @return
     */
	private Date dateOffset(Date begin, int interval, String type) {
		switch (type) {
		case UNIT_DAY:
			return DateUtil.offset(begin, DateField.DAY_OF_YEAR, interval);
		case UNIT_MONTH:
			return DateUtil.offset(begin, DateField.MONTH, interval);
		case UNIT_YEAR:
			return DateUtil.offset(begin, DateField.YEAR, interval);
		default:
			return null;
		}
	}

}
