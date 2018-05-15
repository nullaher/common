/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.service.SpringContextHolder.java zxy@nullah.cn 2017年7月28日
 */
package cn.nullah.common.http.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
@Component
public class SpringContextHolder {
	private static  WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
	
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext){
		SpringContextHolder.applicationContext = (WebApplicationContext) applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		checkApplicationContext();
		return applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name,Object ...args){
		checkApplicationContext();
		return (T) applicationContext.getBean(name,args);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz){
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}
	
	private static void checkApplicationContext(){
		if(applicationContext == null) throw new IllegalStateException("未注入applicationContext");
	}
}
