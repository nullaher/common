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
public class RmtServiceException extends CanShowException {
	
	private static final long serialVersionUID = 3209448514624512330L;
	
	public RmtServiceException(){
		super(ApiStatusEnum.BIZ_FALIURE);
	}
	
	public RmtServiceException(String detailMsg){
		super(ApiStatusEnum.BIZ_FALIURE , detailMsg);
	}
}
