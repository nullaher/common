/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.validator.ValidatorSupport.java Administrator 2016年8月13日
 */
package cn.nullah.validation.constraintvalidators;

import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @autor: Administrator desc : ...
 */
public class ValidatorSupport{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean blankIsPass = false;
	
	public boolean isValid(String value , ConstraintValidatorContext context){
		return StringUtils.isBlank(value) ? blankIsPass : true;
	}
	
	public boolean isBlankIsPass(){
		return blankIsPass;
	}
	
	public void setBlankIsPass(boolean blankIsPass){
		this.blankIsPass = blankIsPass;
	}
}
