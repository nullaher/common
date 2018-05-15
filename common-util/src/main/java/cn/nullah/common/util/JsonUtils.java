/**
 * JsonUtil.java Copyright ©2016 http://www.nullah.cn All Rights Reserved
 */
package cn.nullah.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author zxy@nullah.cn
 * @note json 工具类
 */
public class JsonUtils{
	
	static PropertyPreFilter fmtFilter = new PropertyPreFilter(){
		
		@Override
		public boolean apply(JSONSerializer serializer , Object object , String name){
			if(name.startsWith("set")){
				return false;
			}
			return true;
		}
	};
	
	public static String toFmtJsonStr(Object obj){
		return JSONObject.toJSONString(obj , fmtFilter , SerializerFeature.PrettyFormat);
	}
	
	/**
	 * @note json字符串解析为map
	 * @param jsonStr json字符串
	 * @return
	 */
	@SuppressWarnings("all")
	public static Map parserToMap(String jsonStr){
		Map map = new HashMap();
		JSONObject json = JSONObject.parseObject(jsonStr);
		Iterator keys = json.keySet().iterator();
		while(keys.hasNext()){
			String key = (String) keys.next();
			String value = json.get(key).toString();
			if(value.startsWith("{") && value.endsWith("}")){
				map.put(key , parserToMap(value));
			}else{
				map.put(key , value);
			}
		}
		return map;
	}
	
	@SuppressWarnings("all")
	public static Map parserToTreeMap(String jsonStr){
		Map map = new TreeMap();
		JSONObject json = JSONObject.parseObject(jsonStr);
		Iterator keys = json.keySet().iterator();
		while(keys.hasNext()){
			String key = (String) keys.next();
			String value = json.get(key).toString();
			if(value.startsWith("{") && value.endsWith("}")){
				map.put(key , parserToMap(value));
			}else{
				map.put(key , value);
			}
		}
		return map;
	}
}
