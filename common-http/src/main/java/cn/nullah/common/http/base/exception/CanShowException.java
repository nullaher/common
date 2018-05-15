/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.opensvc.web.exception.CanShowException.java zxy@nullah.cn 2016年7月5日
 */
package cn.nullah.common.http.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.nullah.common.http.api.ApiStatusEnum;

/**
 * @autor: zxy@nullah.cn
 * @desc : 可显示异常
 */
public class CanShowException extends RuntimeException {
	
	
	private static final long serialVersionUID = -750375435895334416L;
	
	private ApiStatusEnum ex;
	
	private String detailMsg;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public CanShowException(ApiStatusEnum ex){
		super(ex.getMsg());
		this.ex = ex;
		logger.info(getMessage());
		printStackTrace();
	}
	
	public CanShowException(ApiStatusEnum ex , String detailMsg){
		super(ex.getMsg());
		this.ex = ex;
		this.detailMsg = detailMsg;
		logger.info(getMessage() + (null != detailMsg ? "," + detailMsg : ""));
//		printStackTrace();
	}
	
	public CanShowException(String detailMsg , Throwable cause){
		super(detailMsg , cause);
		this.ex = ApiStatusEnum.SYS_EX;
		this.detailMsg = detailMsg;
		logger.info(getMessage() + (null != detailMsg ? "," + detailMsg : ""));
//		printStackTrace();
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
