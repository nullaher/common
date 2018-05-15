/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.util.ValidateUtils.java Administrator 2016年7月5日
 */
package cn.nullah.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @autor: Administrator desc : ...
 */
public class ValidateUtils {
	
	static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	static String MSG_SPLIT = System.getProperty("line.separator");
	public static String validByJSR(Object validObj){
		return validByJSR(validObj , true);
	}
	
	public static String validByJSR(Object validObj , boolean breakWhenIsErr){
		Set<ConstraintViolation<Object>> vSet = validator.validate(validObj);
		if(vSet.size() == 0){
			return null;
		}
		StringBuffer msg = new StringBuffer();
		for(ConstraintViolation<Object> constraintViolation : vSet){
			msg.append(constraintViolation.getPropertyPath().toString()).append(constraintViolation.getMessage()).append(",");
			if(breakWhenIsErr) break;
		}
		return msg.toString();
	}
}
