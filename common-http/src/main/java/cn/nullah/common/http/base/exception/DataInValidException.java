/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.dsp.web.exception.OpenSvcReqParseException.java zxy@nullah.cn 2016年7月1日
 */
package cn.nullah.common.http.base.exception;

import cn.nullah.common.http.api.ApiStatusEnum;

/**
 * @autor: zxy@nullah.cn
 * @desc : 请求解析异常
 */
public class DataInValidException extends CanShowException {

	private static final long serialVersionUID = -6949346999546617308L;

	public DataInValidException() {
		super(ApiStatusEnum.PARAM_INVALID);
	}
	
	public DataInValidException(String msg) {
		super(ApiStatusEnum.PARAM_INVALID,msg);
	}

}
