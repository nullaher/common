/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.validator.anno.ValidEntity.java Administrator 2016年8月12日
 */
package cn.nullah.common.validator;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import cn.nullah.validation.constraints.IDCNum;
import cn.nullah.validation.constraints.PhoneNum;

/**
 * @autor: Administrator desc : ...
 */
public class ValidEntity{
	
	@PhoneNum
//	@NotBlank
	private String phoneNum;
	
	@Email
//	@NotBlank
	private String email;
	
	@IDCNum
//	@NotBlank
	private String idc;
	
	@Pattern(regexp="\\d{2}")
	private String bankCardNum;
	
	public String getPhoneNum(){
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getIdc(){
		return idc;
	}
	
	public void setIdc(String idc){
		this.idc = idc;
	}
	
	public String getBankCardNum(){
		return bankCardNum;
	}
	
	public void setBankCardNum(String bankCardNum){
		this.bankCardNum = bankCardNum;
	}
	
}
