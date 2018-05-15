/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.validator.PhoneNumValidator.java Administrator 2016年8月12日
 */
package cn.nullah.validation.constraintvalidators;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import cn.nullah.validation.constraints.PhoneNum;

/**
 * @autor: zxy desc : 手机号校验
 */
public class PhoneNumValidator implements ConstraintValidator<PhoneNum , String>{
	
	private Pattern pattern = Pattern.compile("\\d{11}");
	
	@Override
	public void initialize(PhoneNum constraintAnnotation){
		// setBlankIsPass(constraintAnnotation.blankIsPass());
	}
	
	@Override
	public boolean isValid(String value , ConstraintValidatorContext context){
		if(value == null || value.length() == 0){
			return true;
		}
		return pattern.matcher(value).matches();
	}
}
