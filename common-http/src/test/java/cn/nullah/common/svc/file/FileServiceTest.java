package cn.nullah.common.svc.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;
import cn.nullah.common.http.file.service.fac.FileContext;
import cn.nullah.common.http.file.service.fac.GenFileServiceFac;
import cn.nullah.common.http.file.service.fac.IFileServiceFac;
import cn.nullah.common.http.file.service.fac.product.IMyFileService;

public class FileServiceTest {
	
	public static String url = "http://127.0.0.1:8080/img/upload";
	// public static String url = "http://192.168.3.73:9001/img/upload";
	
	public static String uploadImg(File file , String platfmId , boolean genThumbImg) throws IOException{
		return uploadImg(file.getName() , new FileInputStream(file) , platfmId , genThumbImg);
	}
	
	public static void main(String[] args) throws IOException{
		File file = new File("d:/abc.jpg");
		int fileType = 101;
		FileInf fileInf = new FileInf();
		fileInf.setInputStream(new FileInputStream(file));
		
		FileContext context = new FileContext();
		context.setFileType(fileType);// 带缩略图图片
		
		IFileServiceFac fileFac = GenFileServiceFac.build(context);
		
		IMyFileService fileSvc = fileFac.createBaseService();
		FileProcResult result = fileSvc.save(fileInf);
		System.out.println("文件执行结果:\r\n" + JSONObject.toJSONString(result , true));
		// main2(args);
	}
	
	public static String uploadImgList(List<File> fileList , String platfmId , boolean genThumbImg)
	    throws IOException{
		List<JSONObject> fileParamList = new ArrayList();
		for(File file : fileList){
			JSONObject json = new JSONObject();
			json.put("bytes" , IOUtils.toByteArray(new FileInputStream(file)));
			json.put("fileName" , file.getName());
			fileParamList.add(json);
		}
		return uploadImg(fileParamList , platfmId , genThumbImg);
	}
	
	public static String uploadImg(String fileName , InputStream inputStream , String platfmId ,
	    boolean genThumbImg) throws IOException{
		List<JSONObject> fileParamList = new ArrayList();
		JSONObject json = new JSONObject();
		json.put("bytes" , IOUtils.toByteArray(inputStream));
		json.put("fileName" , fileName);
		fileParamList.add(json);
		return uploadImg(fileParamList , platfmId , genThumbImg);
	}
	
	private static String uploadImg(List<JSONObject> fileParamList , String platfmId , boolean genThumbImg)
	    throws ClientProtocolException , IOException{
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		try{
			HttpPost post = new HttpPost(url);
			// 响应状态
			MultipartEntityBuilder paramBuilder = MultipartEntityBuilder.create();
			paramBuilder.addPart("platfmId" ,
			    new StringBody(platfmId , ContentType.create("text/plain" , Consts.UTF_8)));
			paramBuilder.addPart("genThumbImg" ,
			    new StringBody((genThumbImg ? "1" : "0") , ContentType.create("text/plain" , Consts.UTF_8)));
			for(JSONObject paramFile : fileParamList){
				ByteArrayBody stream = new ByteArrayBody(paramFile.getBytes("bytes") ,
				    ContentType.APPLICATION_OCTET_STREAM , paramFile.getString("fileName"));
				paramBuilder.addPart("file" , stream);
			}
			
			HttpEntity reqEntity = paramBuilder.build();
			post.setEntity(reqEntity);
			HttpResponse httpResponse = closeableHttpClient.execute(post);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			JSONObject rspJson = null;
			if(entity != null){
				rspJson = (JSONObject) JSONObject.parseObject(EntityUtils.toString(entity) ,
				    JSONObject.class);
			}
			String str = rspJson.toJSONString();
			System.out.println(str);
			return str;
		}finally{
			try{
				closeableHttpClient.close();// 关闭流并释放资源
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main2(String[] args) throws IOException{
		File file = new File(
		    "D://simon//project//FastDFS_Client-master//target//test-classes//images//cat.jpg");
		// inputStream = new FileInputStream(file);
		//
		// FileClient.uploadImg("abc.jpg", inputStream, "group1", true);
		
		List<File> fileList = new ArrayList();
		fileList.add(file);
		fileList
		    .add(new File("D://simon//project//FastDFS_Client-master//target//test-classes//images//gs.jpg"));
		uploadImgList(fileList , "group1" , true);
	}
	
}
