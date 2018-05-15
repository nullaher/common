package cn.nullah.common.http.base.exception;

import cn.nullah.common.http.api.ApiStatusEnum;

public class SecurityProcessException extends CanShowException {
	private static final long serialVersionUID = 3209448514624512330L;

	public SecurityProcessException() {
		super(ApiStatusEnum.HTTP_TEXT_INVALID);
	}

	public SecurityProcessException(String detailMsg) {
		super(ApiStatusEnum.HTTP_TEXT_INVALID, detailMsg);
	}
}
