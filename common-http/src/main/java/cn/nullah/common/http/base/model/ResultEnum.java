package cn.nullah.common.http.base.model;

public enum ResultEnum{
	
	SUCCESS(1 , "SUCCESS") , FALIURE(-1 , "FALIURE"), EXCEPTION(-9 , "EXCEPTION");
	
	private ResultEnum(int code , String msg){
		this.code = code;
		this.msg = msg;
	}
	
	private int code;
	
	private String msg;
	
	public int getCode(){
		return code;
	}
	
	public void setCode(int code){
		this.code = code;
	}
	
	public String getMsg(){
		return msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
}
