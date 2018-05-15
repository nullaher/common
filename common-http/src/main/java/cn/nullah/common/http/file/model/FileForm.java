/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.model.FileForm.java zxy@nullah.cn 2017年7月26日
 */
package cn.nullah.common.http.file.model;

import java.io.Serializable;
import cn.nullah.common.http.api.BaseReq;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
public class FileForm extends BaseReq implements Serializable {
	private static final long serialVersionUID = 8316732924115332121L;
	
	private String platfmId;
	
	/** 参考cn.nullah.common.http.file.model.FileInf.FILE_TYPE_XXX */
	private Integer fileType;
	
	private Integer compressType;
	
	private String creatorId;
	
	public String getPlatfmId(){
		return platfmId;
	}
	
	public void setPlatfmId(String platfmId){
		this.platfmId = platfmId;
	}
	
	public Integer getFileType(){
		return fileType;
	}
	
	public void setFileType(Integer fileType){
		this.fileType = fileType;
	}
	
	public Integer getCompressType(){
		return compressType;
	}
	
	public void setCompressType(Integer compressType){
		this.compressType = compressType;
	}
	
	public String getCreatorId(){
		return creatorId;
	}
	
	public void setCreatorId(String creatorId){
		this.creatorId = creatorId;
	}
	
}
