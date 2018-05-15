package cn.nullah.common.http.api;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import com.alibaba.fastjson.JSONObject;

public class BaseRsp extends SecurityContext implements Serializable {
	
	public static final String STATUS_SUCCESS = ApiStatusEnum.OK.getCode();
	
	public static final String STATUS_FALIURE = ApiStatusEnum.BIZ_FALIURE.getCode();
	
	private static final long serialVersionUID = 335197152179561091L;
	
	public String status;
	
	public String msg;
	
	public String reqId;
	
	private JSONObject data;
	
	public boolean beSuccess(){
		return STATUS_SUCCESS.equals(status);
	}
	
	public BaseRsp(){
		super();
		this.status = ApiStatusEnum.OK.getCode();
		this.msg = ApiStatusEnum.OK.getMsg();
		setEncryptStrategy(null);
	}
	
	public BaseRsp(ApiStatusEnum ex){
		super();
		this.status = ex.getCode();
		this.msg = ex.getMsg();
		setEncryptStrategy(null);
	}
	
	@NotBlank
	public String getStatus(){
		return status;
	}
	
	public JSONObject getData(){
		return data;
	}
	
	public void setData(JSONObject data){
		this.data = data;
	}
	
	@NotBlank
	public String getReqId(){
		return reqId;
	}
	
	public void setReqId(String reqId){
		this.reqId = reqId;
	}
	
	public void appendMsg(String detailMsg){
		msg += "," + detailMsg;
	}
	
	public String getMsg(){
		return msg;
	}
	
}
