/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.model.FileResult.java zxy@nullah.cn 2017年7月26日
 */
package cn.nullah.common.http.file.model;

import cn.nullah.common.http.base.model.Result;
import cn.nullah.common.http.base.model.ResultEnum;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
public class FileProcResult extends Result {
	public FileProcResult(){
		super();
	}
	
	public FileProcResult(ResultEnum resultEnum){
		super(resultEnum);
	}
	
	private FileInf fileInf;
	
	public FileInf getFileInf(){
		return fileInf;
	}
	
	public void setFileInf(FileInf fileInf){
		this.fileInf = fileInf;
	}
	
}
