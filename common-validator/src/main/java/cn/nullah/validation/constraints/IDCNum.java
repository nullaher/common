/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.validator.anno.IDCNum.java Administrator 2016年8月12日
 */
package cn.nullah.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import cn.nullah.validation.constraintvalidators.IDCValidator;

/**
 * @autor: Administrator desc : ...
 */
@Documented
@Constraint(validatedBy = {IDCValidator.class})
@Target({ElementType.ANNOTATION_TYPE , ElementType.METHOD , ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IDCNum{
	
	String message() default "{nullah.common.validation.constraints.IDCNum.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
