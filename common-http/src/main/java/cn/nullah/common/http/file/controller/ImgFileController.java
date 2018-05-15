package cn.nullah.common.http.file.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.nullah.common.http.file.FileException;
import cn.nullah.common.http.file.model.FileForm;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;
import cn.nullah.common.http.file.model.ImgFileInf;
import cn.nullah.common.http.file.model.InputFile;
import cn.nullah.common.http.file.service.IFileService;

@Controller
@RequestMapping("file/img")
public class ImgFileController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	IFileService imgFileService;
	
	@RequestMapping(value = "upload" , method = RequestMethod.POST)
	@ResponseBody
	public ImgFileInf multiUpload(FileForm fileForm , @RequestParam("file") MultipartFile[] files){
		String platfmId = fileForm.getPlatfmId();
		boolean genThumbImg =10==fileForm.getCompressType();
		FileProcResult fileResult = null;
		for(MultipartFile file : files){
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
					upFile.setFileType(FileInf.FILE_TYPE_IMG + (genThumbImg ? 1 : 0));
					fileResult = imgFileService.save(upFile);
				}catch(FileException e){
					throw e;
				}catch(Exception e){
					throw new FileException("文件[" + fileName + "]上传异常" , e);
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
		
		ImgFileInf fileInf = (ImgFileInf) fileResult.getFileInf();
		ImgFileInf imgFile = new ImgFileInf();
		imgFile.setFileUri(fileInf.getFileUri());
		imgFile.setFileId(fileInf.getFileId());
		if(genThumbImg) imgFile.setThumbUri(fileInf.getThumbUri());
		return imgFile;
	}
	
	@RequestMapping("{fileId}")
	public String toImgUrl(@PathVariable String fileId){
		return "redirect:" + imgFileService.getURIById(fileId);
	}
	
	@RequestMapping("thumb/{fileId}")
	public String toThumbURI(@PathVariable String fileId){
		String url = imgFileService.getURIById(fileId);
		if(StringUtils.isNotBlank(url)){
			return "redirect:" + url;
		}
		return "404";
	}
}
