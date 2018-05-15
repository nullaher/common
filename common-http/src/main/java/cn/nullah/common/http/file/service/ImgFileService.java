package cn.nullah.common.http.file.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.exception.FdfsUnsupportImageTypeException;
import com.github.tobato.fastdfs.exception.FdfsUploadImageException;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;
import cn.nullah.common.http.file.model.FsdfResult;
import cn.nullah.common.http.file.model.ImgFileInf;
import cn.nullah.common.http.file.model.InputFile;
import net.coobird.thumbnailator.Thumbnails;

@Service("imgFileService")
public class ImgFileService extends FileServiceSupport {
	
	private static final String[] SUPPORT_IMAGE_TYPE = {"JPG" , "JPEG" , "PNG" , "GIF" , "BMP" , "WBMP"};
	
	private static final List<String> SUPPORT_IMAGE_LIST = Arrays.asList(SUPPORT_IMAGE_TYPE);
	
	@Resource
	private ThumbImageConfig thumbImageConfig;
	
	@Override
	public FileProcResult save(InputFile inputFile){
		String fileExtName = inputFile.getFileExtName();
		if(!isSupportImage(fileExtName)){
			throw new FdfsUnsupportImageTypeException("不支持的图片格式" + fileExtName);
		}
		byte[] bytes = null;
		boolean isGenThumbImage = inputFile.getFileType() % 10 == 1;
		FsdfResult result = null;
		if(!isGenThumbImage){
			result = (FsdfResult) super.save(inputFile);
		}else{
			bytes = inputStreamToByte(inputFile.getInputStream());
			inputFile.setInputStream(new ByteArrayInputStream(bytes));
			// 主文件上传
			result = (FsdfResult) super.save(inputFile);
			inputFile.setInputStream(new ByteArrayInputStream(bytes));
			// 缩略图上传
			uploadThumbImage(result.getStorePath().getPath() , inputFile);
			bytes = null;
		}
		return result;
	}
	
	public void deletePhysicsFile(String fileId){
		FileInf hisFileInf = getInf(fileId);
		try{
			super.deletePhysicsFile(fileId);
			if(hisFileInf.isCompressFile()){
				deletePhysicsFile(hisFileInf.getPlatfmId() , getThumbName(hisFileInf.getFileKey()));
			}
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("删除目标文件异常,可能文件已经被删除,fileId:" + fileId + " 路径:" + hisFileInf.getFileKey() , e);
		}
	}
	
	@Override
	public ImgFileInf getInf(String fileId){
		FileInf fileInf = super.getInf(fileId);
		ImgFileInf imgFile = new ImgFileInf();
		BeanUtils.copyProperties(fileInf , imgFile);
		imgFile.setThumbUri(getThumbName(fileInf.getFileUri()));
		return imgFile;
	}
	
	public String getThumbName(String fileKeyOrURI){
		StringBuffer key = new StringBuffer(fileKeyOrURI);
		int pointIdx = fileKeyOrURI.lastIndexOf(".");
		if(-1 != pointIdx){
			key.insert(pointIdx , thumbImageConfig.getPrefixName());
		}
		return key.toString();
	}
	
	public String getThumbURIById(String fileId){
		String mastURI = getURIById(fileId);
		return getThumbName(mastURI);
	}
	
	protected String uploadThumbImage(String masterFilename , InputFile inputFile){
		logger.info("准备上传缩略图...");
		String fileExtName = inputFile.getFileExtName();
		String groupName = inputFile.getPlatfmId();
		ByteArrayInputStream thumbImageStream = null;
		try{
			thumbImageStream = getThumbImageStream(inputFile.getInputStream());// getFileInputStream
			// 获取文件大小
			long fileSize = thumbImageStream.available();
			// 获取缩略图前缀
			String prefixName = thumbImageConfig.getPrefixName();
			StorePath storePath = uploadSlaveFile(groupName , masterFilename , thumbImageStream , fileSize ,
			    prefixName , fileExtName);
			return storePath.getPath();
		}catch(IOException e){
			LOGGER.error("upload ThumbImage error" , e);
			throw new FdfsUploadImageException("upload ThumbImage error" , e.getCause());
		}finally{
			IOUtils.closeQuietly(thumbImageStream);
		}
		
	}
	
	/**
	 * 获取缩略图
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private ByteArrayInputStream getThumbImageStream(InputStream inputStream) throws IOException{
		// 在内存当中生成缩略图
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// @formatter:off
		Thumbnails.of(inputStream).size(thumbImageConfig.getWidth() , thumbImageConfig.getHeight())
		    .toOutputStream(out);
		// @formatter:on
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	/**
	 * 获取byte流
	 * 
	 * @param inputStream
	 * @return
	 */
	protected byte[] inputStreamToByte(InputStream inputStream){
		try{
			return IOUtils.toByteArray(inputStream);
		}catch(IOException e){
			LOGGER.error("image inputStream to byte error" , e);
			throw new FdfsUploadImageException("upload ThumbImage error" , e.getCause());
		}
	}
	
	private boolean isSupportImage(String fileExtName){
		return SUPPORT_IMAGE_LIST.contains(fileExtName.toUpperCase());
	}
	
}
