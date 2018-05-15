/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.fac.FileContext.java zxy@nullah.cn 2017年7月27日
 */
package cn.nullah.common.http.file.service.fac;

import java.io.InputStream;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
public class FileContext {
	private String fileName;
	
	private String creatorId;
	
	private Integer fileType;
	
	@NotNull
	@JSONField(serialize = false)
	@Transient
	private InputStream inputStream;
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public String getCreatorId(){
		return creatorId;
	}
	
	public void setCreatorId(String creatorId){
		this.creatorId = creatorId;
	}
	
	public Integer getFileType(){
		return fileType;
	}
	
	public void setFileType(Integer fileType){
		this.fileType = fileType;
	}
	
	public InputStream getInputStream(){
		return inputStream;
	}
	
	public void setInputStream(InputStream inputStream){
		this.inputStream = inputStream;
	}
	
}
