/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.controller.FileController.java zxy@nullah.cn 2017年7月26日
 */
package cn.nullah.common.http.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import cn.nullah.common.http.file.model.FileForm;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;
import cn.nullah.common.http.file.model.InputFile;
import cn.nullah.common.http.file.service.IFileService;
import cn.nullah.common.http.file.service.fac.FileContext;
import cn.nullah.common.http.file.service.fac.GenFileServiceFac;
import cn.nullah.common.http.file.service.fac.IFileServiceFac;
import cn.nullah.common.http.file.service.fac.product.IMyFileService;

/**
 * @autor: zxy@nullah.cn
 * @desc : 文件控制器
 */
@Controller
@RequestMapping("file")
public class FileController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/test")
	public void test() throws FileNotFoundException{
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
	}
	
	@Resource(name = "fileServiceSupport")
	IFileService fileService;
	
	@Resource
	IFileService imgFileService;
	
	@ResponseBody
	@RequestMapping(value = "upload" , method = RequestMethod.POST)
	public JSONObject multiUpload(FileForm fileForm , @RequestParam("file") MultipartFile[] fileList){
		JSONObject data = new JSONObject();
		String platfmId = fileForm.getPlatfmId();
		Integer fileType = fileForm.getFileType();
		for(MultipartFile file : fileList){
			if(!file.isEmpty()){
				String fileName = file.getOriginalFilename();
				logger.info("收到上传的文件,名字为:{}" , fileName);
				InputStream inputStream = null;
				try{
					inputStream = file.getInputStream();
					InputFile upFile = new InputFile(inputStream);
					upFile.setFileName(fileName);
					upFile.setPlatfmId(platfmId);
					upFile.setFileSize((long) file.getBytes().length);
					upFile.setFileType(fileType);
					imgFileService.save(upFile);
					data.put(upFile.getFileId() , fileName);
				}catch(Exception e){
					logger.error("文件[" + fileName + "]上传出错" , e);
					data.put("-" + System.currentTimeMillis() , fileName);
				}finally{
					if(null != inputStream){
						try{
							inputStream.close();
						}catch(IOException e){
							logger.error("文件[" + fileName + "]异常关闭" , e);
						}
					}
				}
			}
		}
		return data;
	}
	
	@RequestMapping("/down/{fileId}")
	public void download(@PathVariable String fileId){
	}
	
	@RequestMapping("/inf/{fileId}")
	public FileInf getFileInf(String fileId){
		return fileService.getInf(fileId);
	}
	
}
