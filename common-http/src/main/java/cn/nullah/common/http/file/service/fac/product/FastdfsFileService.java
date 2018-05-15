/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.fac.product.DfsFileService.java zxy@nullah.cn 2017年7月27日
 */
package cn.nullah.common.http.file.service.fac.product;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.GenerateStorageClient;
import cn.nullah.common.http.base.exception.ServiceException;
import cn.nullah.common.http.base.model.ResultEnum;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;
import cn.nullah.common.http.file.model.FsdfResult;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
@Service("fastdfsFileService")
public class FastdfsFileService extends DefaultGenerateStorageClient implements IPhysicFileService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${file.downPort}")
	public String downPort;
	
	@Resource
	private IMyFileService fileLogicService;
	
	@Resource(name = "defaultFastFileStorageClient")
	private GenerateStorageClient storeClient;
	
	@Override
	public FsdfResult save(FileInf inputFile){
		String fileExtName = inputFile.getFileExtName();
		long fileSize = inputFile.getFileSize();
		InputStream inputStream = inputFile.getInputStream();
		String groupName = inputFile.getPlatfmId();
		Validate.notNull(inputStream , "上传文件流不能为空");
		Validate.notBlank(fileExtName , "文件扩展名不能为空");
		StorePath storePath = null;
		FsdfResult exResult = new FsdfResult(ResultEnum.EXCEPTION);
		try{
			storePath = storeClient.uploadFile(groupName , inputStream , fileSize , fileExtName);
		}catch(Exception e){
			logger.error("文件上传异常" , e);
			exResult.setMessage("文件上传异常,请检查日志");
		}
		inputFile.setFileKey(storePath.getPath());// 设置路径
		setMetaData(inputFile , storePath);
		FileProcResult saveResult = fileLogicService.save(inputFile);
		if(saveResult.beSuccess()){
			return new FsdfResult(storePath);
		}
		return exResult;
		
	}
	
	private void setMetaData(FileInf inputFile , StorePath storePath){
		Set<MateData> metaDataSet = new HashSet<MateData>();
		metaDataSet.add(new MateData("creator" , inputFile.getCreatorId()));
		String remark = inputFile.getRemark();
		if(StringUtils.isNoneBlank(remark)){
			metaDataSet.add(new MateData("remark" , remark));
		}
		if(hasMateData(metaDataSet)){
			overwriteMetadata(inputFile.getPlatfmId() , storePath.getPath() , metaDataSet);
		}
	}
	
	@Override
	public FileProcResult save(List<FileInf> fileList){
		return null;
	}
	
	@Override
	public void delete(String fileId){
		deletePhysicsFile(fileId);
	}
	
	@Override
	public FileInf getById(String fileId){
		FileInf fileInf = fileLogicService.getById(fileId);
		if(null == fileInf) throw new ServiceException("找不到该文件!");
		String groupName = fileInf.getPlatfmId();
		logger.debug("fdfs文件信息为:\r\n" + JSONObject.toJSONString(fileInf , true));
		fileInf.setFileUri(getURIById(groupName , fileInf.getFileKey()));
		return fileInf;
	}
	
	String getURIById(String fileId){
		FileInf fileInf = fileLogicService.getById(fileId);
		String groupName = fileInf.getPlatfmId();
		String fileKey = fileInf.getFileKey();
		return getURIById(groupName , fileKey);
	}
	
	String getURIById(String groupName , String fileKey){
		FileInfo file = storeClient.queryFileInfo(groupName , fileKey);
		StringBuilder absUrl = new StringBuilder("http://");
		absUrl.append(file.getSourceIpAddr()).append(":").append(downPort);
		absUrl.append("/").append(groupName).append("/");
		absUrl.append(fileKey);
		return absUrl.toString();
	}
	
	void deletePhysicsFile(String fileId){
		FileInf hisFileInf = fileLogicService.getById(fileId);
		storeClient.deleteFile(hisFileInf.getPlatfmId() , hisFileInf.getFileKey());
	}
	
	void deletePhysicsFile(String platfmId , String fileKey){
		storeClient.deleteFile(platfmId , fileKey);
	}
	
	/**
	 * 检查是否有MateData
	 * @param metaDataSet
	 * @return
	 */
	private boolean hasMateData(Set<MateData> metaDataSet){
		return null != metaDataSet && !metaDataSet.isEmpty();
	}
	
}
