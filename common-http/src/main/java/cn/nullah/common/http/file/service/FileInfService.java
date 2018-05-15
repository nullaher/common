package cn.nullah.common.http.file.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.service.GenerateStorageClient;
import cn.nullah.common.http.base.exception.ServiceException;
import cn.nullah.common.http.file.dao.FileInfRepository;
import cn.nullah.common.http.file.model.FileInf;

@Service
public class FileInfService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private FileInfRepository fileInfRepository;

	@Autowired
	Md5PasswordEncoder md5Encode;

	@Autowired
	@Resource(name = "defaultFastFileStorageClient")
	GenerateStorageClient client;

	public String save(FileInf fileInf) {
		String fileId=md5Encode.encodePassword(fileInf.getFileKey(), fileInf.getPlatfmId());
		fileInf.setFileId(fileId);
		fileInfRepository.save(JSONObject.toJavaObject((JSONObject) JSONObject.toJSON(fileInf), FileInf.class));
		return fileId;
	}
	
	public void delete(String fileId) {
		fileInfRepository.delete(fileId);
	}

	public FileInf getById(String fileId) {
		FileInf fileInf = fileInfRepository.findByFileId(fileId);
		if (null == fileInf)
			throw new ServiceException("找不到该文件!");
//		String groupName = fileInf.getPlatfmId();
//		String path = fileInf.getFileKey();
//		FileInfo file = client.queryFileInfo(groupName, path);
//		logger.info("fdfs文件信息为:\r\n" + JSONObject.toJSONString(file, true));
		return fileInf;
	}
}
