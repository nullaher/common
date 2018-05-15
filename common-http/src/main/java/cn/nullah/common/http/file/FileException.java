/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.opensvc.web.exception.CanShowException.java zxy@nullah.cn 2016年7月5日
 */
package cn.nullah.common.http.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.nullah.common.http.api.ApiStatusEnum;

/**
 * @autor: zxy@nullah.cn
 * @desc : 可显示异常
 */
public class FileException extends RuntimeException {
	
	private static final long serialVersionUID = -750375435895334416L;
	
	private static ApiStatusEnum ex = ApiStatusEnum.BIZ_FALIURE;
	
	private String detailMsg;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public FileException(){
		super(ex.getMsg());
		logger.info(getMessage());
		if(logger.isDebugEnabled()){
			printStackTrace();
		}
	}
	
	public FileException(String detailMsg){
		super(ex.getMsg());
		this.detailMsg = detailMsg;
		logger.info(getMessage() + (null != detailMsg ? "," + detailMsg : ""));
		if(logger.isDebugEnabled()){
			printStackTrace();
		}
	}
	
	public FileException(String detailMsg , Throwable cause){
		super(detailMsg , cause);
		this.detailMsg = detailMsg;
		logger.info(getMessage() + (null != detailMsg ? "," + detailMsg : ""));
		if(logger.isDebugEnabled()){
			printStackTrace();
		}
	}
	
	public ApiStatusEnum getEx(){
		return ex;
	}
	
	public String getDetailMsg(){
		return detailMsg;
	}
	
	public void setDetailMsg(String detailMsg){
		this.detailMsg = detailMsg;
	}
	
}
