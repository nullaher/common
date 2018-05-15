/**
 * Copyright ©2016 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.util.HttpUtils.java Administrator 2016年4月28日
 */
package cn.nullah.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

/**
 * @autor: zxy@nullah.cn desc :please use HttpReqUtils
 */
@Deprecated
public class HttpUtils {
	
	static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	@Deprecated
	public static Object sendJson(String url){
		return null;
	}
	
	@Deprecated
	public static String sendGet(String url){
		return sendGet(url , null);
	}
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	@Deprecated
	public static String sendGet(String url , String param){
		String result = "";
		BufferedReader in = null;
		try{
			logger.info("发送http-get请求,地址为:{},参数为:" , url , param);
			String urlNameString = url.trim();// + "?" + param;
			param=urlNameString+(url.indexOf("?")!=-1?"&":"?");
			if(StringUtils.isNotBlank(urlNameString))url+=param;
			
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept" , "*/*");
			connection.setRequestProperty("connection" , "Keep-Alive");
			connection.setRequestProperty("user-agent" ,"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			// 获取所有响应头字段
			// Map<String , List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for(String key : map.keySet()){
			// logger.info(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while((line = in.readLine()) != null){
				result += line;
			}
			logger.info("http返回参数为:{}" , line);
		}catch(Exception e){
			logger.error("发送GET请求出现异常！,{}" , e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally{
			try{
				if(in != null){
					in.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url 发送请求的 URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws IOException
	 */
	@Deprecated
	public static String sendPost(String url , String param) throws IOException{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		InputStreamReader inReader = null;
		try{
			// http://blog.csdn.net/wangpeng047/article/details/19624529
			URL realUrl = new URL(url.trim());
			logger.info("发送http-post请求,地址为:{}\r\n参数为:" , url , param);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept" , "*/*");
			conn.setRequestProperty("connection" , "Keep-Alive");
			conn.setRequestProperty("user-agent" , "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			inReader = new InputStreamReader(conn.getInputStream() , "UTF-8");
			in = new BufferedReader(inReader);
			String line;
			while((line = in.readLine()) != null){
				result += line;
			}
			// }catch(ConnectException e){
			// throw new ConnectException("无法连接服务器");
			// }catch(Exception e){
			// logger.error("发送 POST 请求出现异常！" , e);
			logger.info("本次http-post返回参数为:{}" , line);
		}
		// 使用finally块来关闭输出流、输入流
		finally{
			try{
				if(inReader != null){
					inReader.close();
				}
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	@Deprecated
	public static JSONObject sendJson(String url , JSONObject json){
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		try{
			HttpPost post = new HttpPost(url);
			String param = null != json ? json.toString() : "";
			StringEntity entityStr = new StringEntity(param , Consts.UTF_8);
			entityStr.setContentEncoding(Consts.UTF_8.toString());
			entityStr.setContentType("application/json");// 发送json数据需要设置contentType
			post.setEntity(entityStr);
			HttpResponse httpResponse = closeableHttpClient.execute(post);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			
			JSONObject rspJson = null;
			if(entity != null){
				rspJson = JSONObject.parseObject(EntityUtils.toString(entity));
			}
			logger.info("\r\n请求url:\r\n{}\r\n参数:\r\n{}\r\n返回:\r\n{}" , url ,
			    JSONObject.toJSONString(json , true) , JSONObject.toJSONString(rspJson , true));
			return rspJson;
		}catch(IOException e){
			logger.error("请求发送异常" , e);
			e.printStackTrace();
		}finally{
			try{
				closeableHttpClient.close();// 关闭流并释放资源
			}catch(IOException e){
				e.printStackTrace();
				logger.error("关闭http请求异常" , e);
			}
		}
		return null;
	}
	
	@Deprecated
	public static JSONObject sendPostWithHttps(String url){
		return httpSend(url , null , true);
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	public static JSONObject sendPostWithHttps(String url , @SuppressWarnings("rawtypes") Map paramMap){
		return httpSend(url , paramMap , true);
	}
	@SuppressWarnings("unchecked")
	@Deprecated
	public static JSONObject sendPost(String url , @SuppressWarnings("rawtypes") Map paramMap){
		return httpSend(url , paramMap , false);
	}
	
	private static JSONObject httpSend(String url , Map<String , Object> paramMap , boolean isUseSSL){
		String rspJsonStr = getPostSendStr(url , paramMap , isUseSSL);
		JSONObject reqJson = (JSONObject) JSONObject.toJSON(paramMap);
		JSONObject rspJson = JSONObject.parseObject(rspJsonStr);
		logger.info("\r\nurl:{}\r\n请求:\r\n{}\r\n返回:\r\n{}" , url , JSONObject.toJSONString(reqJson , true) ,
		    JSONObject.toJSONString(rspJson , true));
		return rspJson;
	}
	
	public static String getPostSendStr(String url , Map<String , Object> paramMap , boolean isUseSSL){
		CloseableHttpClient httpclient = null;
		UrlEncodedFormEntity uefEntity;
		try{
			if(isUseSSL){
				httpclient = sslTrustHttpClient();
			}else{
				httpclient = HttpClients.createDefault();
			}
			// 创建httppost
			HttpPost httppost = new HttpPost(url.trim());
			// httppost.addHeader(HTTP.CONTENT_TYPE , "application/json");
			
			// 创建参数队列
			List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
			
			if(null != paramMap){
				for(Map.Entry<String , Object> entry : paramMap.entrySet()){
					if(null != entry.getValue()){
						String key = entry.getKey() , value = entry.getValue().toString();
						formparams.add(new BasicNameValuePair(key , value));
					}
				}
			}
			uefEntity = new UrlEncodedFormEntity(formparams , "UTF-8");
			httppost.setEntity(uefEntity);
			// long beginTime = System.currentTimeMillis();
			CloseableHttpResponse response = httpclient.execute(httppost);
			
			try{
				HttpEntity entity = response.getEntity();
				if(entity != null){
					String respStr = EntityUtils.toString(entity , "UTF-8");
					// logger.info("http-post请求完毕,耗时{}ms,返回参数为:{}", (System.currentTimeMillis() -
					// beginTime), respStr);
					return respStr;
				}
			}finally{
				response.close();
			}
		}catch(ClientProtocolException e){
			e.printStackTrace();
			throw new RuntimeException("http请求异常(ClientProtocolException)" , e);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			throw new RuntimeException("http请求异常(UnsupportedEncodingException)" , e);
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException("http请求异常(IOException)" , e);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			throw new RuntimeException("http请求异常(NoSuchAlgorithmException)" , e);
		}catch(KeyStoreException e){
			e.printStackTrace();
			throw new RuntimeException("http请求异常(KeyStoreException)" , e);
		}catch(KeyManagementException e){
			e.printStackTrace();
			throw new RuntimeException("http请求异常(KeyManagementException)" , e);
		}finally{
			try{
				if(httpclient != null) httpclient.close();
			}catch(IOException e){
				e.printStackTrace();
				throw new RuntimeException("http连接关闭异常" , e);
			}
		}
		return null;
	}
	
	private static CloseableHttpClient sslTrustHttpClient()
	    throws NoSuchAlgorithmException , KeyStoreException , KeyManagementException{
		CloseableHttpClient httpclient;
		SSLContextBuilder builder = SSLContexts.custom();
		builder.loadTrustMaterial(null , new TrustStrategy(){
			
			
			@Override
			public boolean isTrusted(java.security.cert.X509Certificate[] chain , String authType)
		        throws java.security.cert.CertificateException{
				return true;
			}
		});
		SSLContext sslContext = builder.build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext ,
		    new X509HostnameVerifier(){
			    
			    
			    public void verify(String host , SSLSocket ssl) throws IOException{
			    }
			    
			    public void verify(String host , String[] cns , String[] subjectAlts) throws SSLException{
			    }
			    
			    @Override
			    public boolean verify(String s , SSLSession sslSession){
				    return true;
			    }
			    
			    @Override
			    public void verify(String host , java.security.cert.X509Certificate cert) throws SSLException{
			    }
		    });
		org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
		    .<ConnectionSocketFactory>create().register("https" , sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		httpclient = HttpClients.custom().setConnectionManager(cm).build();
		return httpclient;
	}
}
