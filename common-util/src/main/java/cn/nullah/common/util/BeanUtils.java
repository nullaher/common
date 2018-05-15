/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.jsh.m.center.domain.common.BeanUtils.java zxy@nullah.cn 2017年4月21日
 */
package cn.nullah.common.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
public class BeanUtils {
	public static <T> T copyField(Object obj , Class<T> clazz){
		return ((JSONObject) JSONObject.toJSON(obj)).toJavaObject(clazz);
	}
}
