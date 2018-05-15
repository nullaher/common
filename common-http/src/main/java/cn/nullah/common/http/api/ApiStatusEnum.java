package cn.nullah.common.http.api;

public enum ApiStatusEnum {

	OK("0000", "操作成功"), 
	BIZ_FALIURE("9000", "操作失败"),
	SYS_EX("9999", "内部异常,请联系管理员"),
	
	NET_EX("1000", "网络异常"), 
	PARAM_INVALID("1001", "无效的参数"),
	HTTP_TEXT_INVALID("1002", "无效的报文"), 
	;

	private ApiStatusEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private String code;

	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
