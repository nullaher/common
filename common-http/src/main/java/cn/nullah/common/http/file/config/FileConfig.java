package cn.nullah.common.http.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileConfig {

//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		factory.setMaxFileSize(maxFileSize);
//		factory.setMaxRequestSize(maxRequestSize);
//		return factory.createMultipartConfig();
//	}

//	public String getMaxFileSize() {
//		return maxFileSize;
//	}
//
//	public void setMaxFileSize(String maxFileSize) {
//		this.maxFileSize = maxFileSize;
//	}
//
//	public String getMaxRequestSize() {
//		return maxRequestSize;
//	}
//
//	public void setMaxRequestSize(String maxRequestSize) {
//		this.maxRequestSize = maxRequestSize;
//	}

}
