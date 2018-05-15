/**
 * Field.java Copyright ©2016 http://www.nullah.cn All Rights Reserved
 */
package cn.nullah.common.base;

/**
 * @author zxy@nullah.cn
 * @note ...
 */
public class Field {
	
	/** 名称 */
	private String id;
	
	/** 文本值 */
	private String name;
	
	/** 数据类型 */
	private int type;
	
	/** 位置索引 */
	private String text;
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
}
