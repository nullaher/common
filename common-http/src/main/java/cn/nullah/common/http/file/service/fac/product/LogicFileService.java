/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.fac.product.DbFileRecordService.java zxy@nullah.cn 2017年7月27日
 */
package cn.nullah.common.http.file.service.fac.product;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import cn.nullah.common.http.base.exception.ServiceException;
import cn.nullah.common.http.file.dao.FileInfRepository;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;

/**
 * @autor: zxy@nullah.cn
 * @desc : 文件逻辑记录服务
 */
@Service("fileLogicService")
public class LogicFileService implements IMyFileService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FileInfRepository fileInfRepository;
	
	@Autowired
	private Md5PasswordEncoder md5Encode;
	
	public FileProcResult save(FileInf fileInf){
		String fileId = md5Encode.encodePassword(fileInf.getFileKey() , fileInf.getPlatfmId());
		fileInf.setFileId(fileId);
		fileInfRepository.save(fileInf);
		// fileInfRepository.save(JSONObject.toJavaObject((JSONObject) JSONObject.toJSON(fileInf) ,
		// FileInf.class));
		return new FileProcResult();
	}
	
	public void delete(String fileId){
		fileInfRepository.delete(fileId);
	}
	
	public FileInf getById(String fileId){
		FileInf fileInf = fileInfRepository.findByFileId(fileId);
		if(null == fileInf) throw new ServiceException("找不到该文件!");
		return fileInf;
	}
	
	@Override
	public FileProcResult save(List<FileInf> fileList){
		StringBuffer idStr = new StringBuffer();
		for(FileInf fileInf : fileList){
			FileProcResult result = save(fileInf);
			if(result.beSuccess()){
//				idStr.append(",").append(result.getFileIdStr());
			}
		}
		FileProcResult result = new FileProcResult();
		if(idStr.length() == 0){
			result.toFaliure();
		}else{
//			result.setFileIdStr(idStr.substring(1));
		}
		return result;
	}
	
}
