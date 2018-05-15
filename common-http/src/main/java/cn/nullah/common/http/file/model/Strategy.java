package cn.nullah.common.http.file.model;

import javax.persistence.Transient;

public class Strategy {
	@Transient
	private String type;
	@Transient
	private Object param;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

}
