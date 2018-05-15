/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.validator.anno.ValidTest.java Administrator 2016年8月12日
 */
package cn.nullah.common.validator;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @autor: Administrator desc : ...
 */
public class ValidTest{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Validator validator;
	
	@Before
	public void before(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testValidPhone(){
		ValidEntity entity = new ValidEntity();
		entity.setBankCardNum("1");
		entity.setEmail("1");
		entity.setIdc("1");
		entity.setPhoneNum("123");
		
		Set<ConstraintViolation<ValidEntity>> violations = validator.validate(entity);
		if(violations.size() == 0){}else{
			StringBuffer buf = new StringBuffer();
			for(ConstraintViolation<ValidEntity> violation : violations){
				buf.append(violation.getPropertyPath().toString()).append(":");
				buf.append(violation.getMessage()).append(System.getProperty("line.separator"));
			}
			logger.info(buf.toString());
			
			// logger.trace("======trace");
			// StringBuffer sb=new StringBuffer();
			// for(int i = 0; i < 1000; i++){
			// sb.append("数字"+i);
			// }
			// logger.info(sb.toString());
			// logger.debug("======debug");
			// logger.info("======info");
			// logger.warn("======warn");
			// logger.error("======error");
			
		}
	}
	
	public static void main(String[] args){
		String str = "好啊1";
		System.out.println(str.matches("[\u4E00-\u9FA5]{2,10}"));
	}
}
