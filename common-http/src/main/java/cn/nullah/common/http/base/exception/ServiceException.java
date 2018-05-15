/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.aggregate.bigmcht.exception.ServiceException.java zxy@nullah.cn 2017年3月10日
 */
package cn.nullah.common.http.base.exception;

import cn.nullah.common.http.api.ApiStatusEnum;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
public class ServiceException extends CanShowException {
	
	
	private static final long serialVersionUID = 3209448514624512330L;
	
	public ServiceException(){
		super(ApiStatusEnum.BIZ_FALIURE);
	}
	
	public ServiceException(String detailMsg){
		super(ApiStatusEnum.BIZ_FALIURE , detailMsg);
	}
	
	public ServiceException(String detailMsg , Throwable cause){
		super(detailMsg , cause);
	}
}
