package cn.nullah.common.http.api;

import java.io.Serializable;

public class SecurityContext implements Serializable {
	
	private static final long serialVersionUID = 1317789119548024496L;
	
	/**
	 * 安全编码:两位处理, 第一位表示认证类型(1:约定md5方式(文本json串+securKey组合的文本),0:不认证), 第二位表示加密类型(1:3des,0: 不加密)
	 */
	private Integer encryptStrategy = SIGN_MD5_ENCRYPT_NO;
	
	public static final Integer SIGN_MD5_ENCRYPT_NO = 10;
	
	private String securityCode;
	
	public Integer getEncryptStrategy(){
		return encryptStrategy;
	}
	
	public void setEncryptStrategy(Integer encryptStrategy){
		this.encryptStrategy = encryptStrategy;
	}
	
	public String getSecurityCode(){
		return securityCode;
	}
	
	public void setSecurityCode(String securityCode){
		this.securityCode = securityCode;
	}
	
}
