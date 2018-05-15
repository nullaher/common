/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.validator.AppTagConvert.java Administrator 2016年8月13日
 */
package cn.nullah.common.validator;

import java.util.ResourceBundle;
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @autor: Administrator desc : ...
 */
public class AppTagConvert {
	// public class AppTagConvert extends ClassicConverter{
	ResourceBundle bundle = ResourceBundle.getBundle("init");

//	@Override
	public String convert(ILoggingEvent event) {
		System.out.println(bundle);
		return bundle.getString("appLogName");// JSONObject.toJSONString(event);
		// return "abc";
	}
}
