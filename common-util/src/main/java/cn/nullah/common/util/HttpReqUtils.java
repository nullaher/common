/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.util.HttpReqUtils.java zxy@nullah.cn 2017年5月11日
 */
package cn.nullah.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

/**
 * @autor: zxy@nullah.cn
 * @desc : http请求工具类,所有的请求方法,若是返回null表示请求失败(运行时异常,非业务失败),否则请求成功
 */
public class HttpReqUtils<T> {
	
	// https://my.oschina.net/gehui/blog/490108
	static final int GET = 10;
	
	static final int POST = 20;
	
	static final int POST_JSON = 21;
	
	static final int POST_FILE = 22;
	
	static final String SUCCESS_JSON_STR = "{\"status\":\"ok\"}";
	
	static final String HTTP_CONTENT_TYPE = "application/json";
	
	static Logger logger = LoggerFactory.getLogger(HttpReqUtils.class);
	
	public static JSONObject getJson(String url){
		String dataStr = get(url);
		if(null == dataStr) return null;
		JSONObject data = JSONObject.parseObject(dataStr);
		return data;
	}
	
	public static <T> T get(String url , Class<T> clazz){
		String dataStr = get(url);
		if(null == dataStr) return null;
		JSONObject data = JSONObject.parseObject(dataStr);
		return JSONObject.toJavaObject(data , clazz);
	}
	
	public static String get(String url){
		return send(url , GET , null);
	}
	
	public static void main(String[] args){
		// JSONObject json=new JSONObject();
		// json.put("orderNo" , "112233");
		// json.put("terminalId" , "123123");
		// String url="http://192.168.10.59:8082/dsp/card/test";
		// Map<String , Object> paramMap=new HashMap<String , Object>(json);
		// String rsp = HttpReqUtils.post(url , paramMap);
	}
	
	@SuppressWarnings({"unchecked" , "rawtypes"})
	public static String post(String url , String queryString){
		Map<String , Object> paramMap = null;
		if(StringUtils.isNotBlank(queryString)){
			String[] param = queryString.split("&");
			paramMap = new HashMap();
			for(String item : param){
				String[] arr = item.split("=");
				paramMap.put(arr[0] , arr[1]);
			}
		}
		return post(url , paramMap);
	}
	
	public static String post(String url , Map<String , Object> paramMap){
		return send(url , POST , paramMap);
	}
	
	public static JSONObject post(String url , JSONObject json){
		String respDataStr = send(url , POST_JSON , json);
		JSONObject jsonData = JSONObject.parseObject(SUCCESS_JSON_STR);
		if(null != respDataStr){
			jsonData = JSONObject.parseObject(respDataStr);
		}
		return jsonData;
	}
	
	public static JSONObject postFile(String url , Map<String , Object> paramMap){
		String respDataStr = send(url , POST_FILE , paramMap);
		JSONObject jsonData = JSONObject.parseObject(SUCCESS_JSON_STR);
		if(null != respDataStr){
			jsonData = JSONObject.parseObject(respDataStr);
		}
		return jsonData;
	}
	
	// private static String getBoundary(){
	// StringBuilder sb = new StringBuilder();
	// Random random = new Random();
	// for(int i = 0; i < 32; ++i){
	// sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(
	// random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
	// }
	// return sb.toString();
	// }
	
	static String send(final String url , int type , Map<String , Object> paramMap){
		if(StringUtils.isBlank(url)) return null;
		String sendUrl = url.trim();
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		List<BasicNameValuePair> nameParamList;
		try{
			logger.info("request url:\r\n{}" , sendUrl);
			HttpRequestBase reqBase = null;
			if(type / 10 == 1){
				reqBase = new HttpGet(sendUrl);
			}else if(type / 10 == 2){
				HttpPost post = new HttpPost(sendUrl);
				if(type % 10 == 0){// 标准表单提交
					if(paramMap != null && !paramMap.isEmpty()){
						nameParamList = new ArrayList<BasicNameValuePair>();
						for(Map.Entry<String , Object> entry : paramMap.entrySet()){
							if(null != entry.getValue()){
								String key = entry.getKey() , value = entry.getValue().toString();
								nameParamList.add(new BasicNameValuePair(key , value));
							}
						}
						post.setEntity(new UrlEncodedFormEntity(nameParamList , Consts.UTF_8));
					}
				}else if(type % 10 == 2){// 图片提交
					MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
					for(Map.Entry<String , Object> entry : paramMap.entrySet()){
						if(null != entry.getValue()){
							String key = entry.getKey();
							Object value = entry.getValue();
							if(value instanceof File){
								entityBuilder.addPart(key , new FileBody((File) value));
							}else if(value instanceof byte[]){
								entityBuilder.addBinaryBody(key , (byte[]) value);
							}else{
								entityBuilder.addPart(key ,
								    new StringBody(value.toString() , ContentType.TEXT_PLAIN));
							}
						}
					}
					// entityBuilder.setBoundary(getBoundary());
					post.setEntity(entityBuilder.build());
					// HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin",
					// bin).addPart("comment", comment).build();
					// MultipartEntity reqEntity = new MultipartEntity();
					// reqEntity.addPart("file1", bin);//file1为请求后台的File upload;属性
					// reqEntity.addPart("file2", bin2);//file2为请求后台的File upload;属性
					// reqEntity.addPart("filename1", comment);//filename1为请求后台的普通参数;属性
					
				}else if(type % 10 == 1){// json提交
					if(paramMap != null && !paramMap.isEmpty()){
						StringEntity entityStr = new StringEntity(new JSONObject(paramMap).toJSONString() ,
						    Consts.UTF_8);
						entityStr.setContentEncoding(Consts.UTF_8.toString());
						entityStr.setContentType(HTTP_CONTENT_TYPE);// 发送json数据需要设置contentType
						post.setEntity(entityStr);
					}
				}else{
					logger.error("未实现的发送类型:" + type);
					return null;
				}
				reqBase = post;
				// reqBase.getAllHeaders();
			}else{
				logger.error("未实现的发送类型:" + type);
				return null;
			}
			CloseableHttpClient httpclient = HttpClients.createDefault();
			response = httpclient.execute(reqBase);
			int respCode = response.getStatusLine().getStatusCode();
			if(respCode == 200){
				entity = response.getEntity();
				String entiyStr;
				if(entity != null && null != (entiyStr = EntityUtils.toString(entity , Consts.UTF_8))){
					logger.info("response data:\r\n{}" , entiyStr);
					return entiyStr;
				}else{
					return SUCCESS_JSON_STR;
				}
			}else{
				throw new Exception("错误的返回状态码:" + respCode);
			}
			
		}catch(HttpHostConnectException e){
			throw new RuntimeException("无法链接到服务器:" + e.getMessage());
		}catch(Exception e){
			logger.error("send get request error" , e);
			throw new RuntimeException("请求失败,出现异常:" + e.getCause() , e);
		}finally{
			try{
				EntityUtils.consume(entity);
			}catch(IOException e){
				logger.error("流关闭异常" , e);
			}
		}
	}
	
}
