package cn.nullah.common.http.api;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class BaseReq extends SecurityContext implements Serializable {

	private static final long serialVersionUID = 4352462893470757150L;

	public BaseReq() {
		super();
	}

	private String reqId;

	private String svcCode;

	@NotBlank
	public String getReqId() {
		return this.reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getSvcCode() {
		return svcCode;
	}

	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}

}
