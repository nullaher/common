package cn.nullah.common.http.file.service;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.GenerateStorageClient;
import cn.nullah.common.http.base.exception.ServiceException;
import cn.nullah.common.http.file.FileException;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;
import cn.nullah.common.http.file.model.FsdfResult;
import cn.nullah.common.http.file.model.InputFile;

@Component
public class FileServiceSupport extends DefaultGenerateStorageClient implements IFileService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FileInfService fileInfService;
	
	@Value("${file.downPort}")
	public String downPort;
	
	@Resource(name = "defaultFastFileStorageClient")
	GenerateStorageClient storeClient;
	
	@Override
	public FileProcResult save(InputFile inputFile){
		String fileExtName = inputFile.getFileExtName();
		long fileSize = inputFile.getFileSize();
		InputStream inputStream = inputFile.getInputStream();
		String groupName = inputFile.getPlatfmId();
		Validate.notNull(inputStream , "上传文件流不能为空");
		Validate.notBlank(fileExtName , "文件扩展名不能为空");
		
		StorePath storePath = null;
		try{
			storePath = storeClient.uploadFile(groupName , inputStream , fileSize , fileExtName);
		}catch(Exception e){
			throw new FileException(e.getMessage());
		}
		// 设置路径
		inputFile.setFileKey(storePath.getPath());
		Set<MateData> metaDataSet = new HashSet<MateData>();
		// 如果有文件则删除
		// String delFileId = inputFile.getDelFileId();
		// if(StringUtils.isNoneBlank(delFileId)){
		// delete(delFileId);
		// }
		String creator = inputFile.getCreatorId();
		String remark = inputFile.getRemark();
		if(StringUtils.isNoneBlank(creator)){
			metaDataSet.add(new MateData("creator" , creator));
		}
		if(StringUtils.isNoneBlank(remark)){
			metaDataSet.add(new MateData("remark" , remark));
		}
		inputFile.setCreatorId("1001");
		String fileId = fileInfService.save(inputFile);
		if(hasMateData(metaDataSet)){
			overwriteMetadata(groupName , storePath.getPath() , metaDataSet);
		}
		logger.debug("本次保存的fdfs文件信息为:\r\n" + JSONObject.toJSONString(inputFile , true));
		logger.info("文件id为:{}" , fileId);
		FsdfResult result = new FsdfResult(storePath);
		// result.setFileInf(getInf(fileId));
		return result;
	}
	
	public String getURIById(String fileId){
		FileInf fileInf = fileInfService.getById(fileId);
		String groupName = fileInf.getPlatfmId();
		String masterPath = fileInf.getFileKey();
		FileInfo file = storeClient.queryFileInfo(groupName , masterPath);
		StringBuilder absUrl = new StringBuilder("http://");
		absUrl.append(file.getSourceIpAddr()).append(":").append(downPort);
		absUrl.append("/").append(groupName).append("/");
		absUrl.append(masterPath);
		return absUrl.toString();
	}
	
	public String getURIById(String groupName , String fileKey){
		FileInfo file = storeClient.queryFileInfo(groupName , fileKey);
		StringBuilder absUrl = new StringBuilder("http://");
		absUrl.append(file.getSourceIpAddr()).append(":").append(downPort);
		absUrl.append("/").append(groupName).append("/");
		absUrl.append(fileKey);
		return absUrl.toString();
	}
	
	@Override
	public int delete(String fileId){
		try{
			deleteFileRecord(fileId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	void deletePhysicsFile(String fileId){
		FileInf hisFileInf = fileInfService.getById(fileId);
		storeClient.deleteFile(hisFileInf.getPlatfmId() , hisFileInf.getFileKey());
	}
	
	void deletePhysicsFile(String platfmId , String fileKey){
		storeClient.deleteFile(platfmId , fileKey);
	}
	
	void deleteFileRecord(String fileId){
		fileInfService.delete(fileId);
	}
	
	@Override
	public FileInf getInf(String fileId){
		FileInf fileInf = fileInfService.getById(fileId);
		if(null == fileInf) throw new ServiceException("找不到该文件!");
		String groupName = fileInf.getPlatfmId();
		String path = fileInf.getFileKey();
		FileInfo file = storeClient.queryFileInfo(groupName , path);
		logger.info("fdfs文件信息为:\r\n" + JSONObject.toJSONString(file , true));
		fileInf.setFileUri(getURIById(groupName , fileInf.getFileKey()));
		return fileInf;
	}
	
	/**
	 * 检查是否有MateData
	 * 
	 * @param metaDataSet
	 * @return
	 */
	private boolean hasMateData(Set<MateData> metaDataSet){
		return null != metaDataSet && !metaDataSet.isEmpty();
	}
}
