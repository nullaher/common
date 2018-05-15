package cn.nullah.common.http.base.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class BaseConfig {
	@Bean
	public FastJsonHttpMessageConverter jacksonMessageConverter(){
//		http://blog.csdn.net/zgmzyr/article/details/8478563   http://www.csdn.net/article/2014-09-25/2821866
		FastJsonHttpMessageConverter jsonCvt=new FastJsonHttpMessageConverter();
		List<MediaType> supportedMediaTypes=new ArrayList();
		//设置支持格式
//		supportedMediaTypes.add(MediaType.TEXT_HTML);
//		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		supportedMediaTypes.add(MediaType.ALL);
		jsonCvt.setSupportedMediaTypes(supportedMediaTypes);
		//设置特征
//		jsonCvt.setFeatures(features);
		return jsonCvt;
	}
	
	@Bean
	public Md5PasswordEncoder dd5PasswordEncoder(){
		return new Md5PasswordEncoder();
	}
}
