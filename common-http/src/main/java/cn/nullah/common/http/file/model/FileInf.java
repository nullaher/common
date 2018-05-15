package cn.nullah.common.http.file.model;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.alibaba.fastjson.annotation.JSONField;

@Entity
@DynamicInsert
@DynamicUpdate
public class FileInf extends Strategy {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public FileInf(){
		super();
	}
	
	
	
	/** 文件类型:图片 */
	public static final Integer FILE_TYPE_IMG = 100;
	
	/** 文件类型:普通文件,初始 */
	public static final Integer FILE_TYPE_GENERAL = 0;
	
	// http://blog.csdn.net/fancylovejava/article/details/7438660
	@Id
	private String fileId;
	
	private String platfmId;
	
	private String fileName;
	
	private Integer fileType;
	
	private String creatorId;
	
	private Date createTime;
	
	private String updaterId;
	
	private Date updateTime;
	
	private String remark;
	
	private String fileKey;
	
	private Long fileKbSize;
	
	private String batchId;
	
	@Transient
	@JSONField(serialize = false)
	private Long fileSize;
	
	@Transient
	private String fileUri;
	
	@JSONField(serialize = false)
	@Transient
	private InputStream inputStream;

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	
	public static Charset getDefaultCharset(){
		return DEFAULT_CHARSET;
	}
	
	public String getFileId(){
		return fileId;
	}
	
	public void setFileId(String fileId){
		this.fileId = fileId;
	}
	@NotNull
	public String getPlatfmId(){
		return platfmId;
	}
	
	public void setPlatfmId(String platfmId){
		this.platfmId = platfmId;
	}
	@NotNull
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	@NotNull
	public Integer getFileType(){
		return fileType;
	}
	
	public void setFileType(Integer fileType){
		this.fileType = fileType;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	@NotNull
	public String getCreatorId(){
		return creatorId;
	}
	
	public void setCreatorId(String creatorId){
		this.creatorId = creatorId;
	}
	
	public String getUpdaterId(){
		return updaterId;
	}
	
	public void setUpdaterId(String updaterId){
		this.updaterId = updaterId;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getFileKey(){
		return fileKey;
	}
	
	public void setFileKey(String fileKey){
		this.fileKey = fileKey;
	}
	
	public Long getFileKbSize(){
		if(null != fileSize){
			return fileSize / 1024;
		}else{
			return fileKbSize;
		}
	}
	
	public void setFileKbSize(Long fileKbSize){
		this.fileKbSize = fileKbSize;
	}
	
	public Long getFileSize(){
		return fileSize;
	}
	
	public void setFileSize(Long fileSize){
		this.fileSize = fileSize;
	}
	
	public String getBatchId(){
		return batchId;
	}
	
	public void setBatchId(String batchId){
		this.batchId = batchId;
	}
	
	@JSONField(serialize = false)
	public String getFileExtName(){
		String fileName = getFileName() , extName = "undef";
		if(StringUtils.isNotBlank(fileName)){
			extName = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return extName;
	}
	
	public boolean isCompressFile(){
		if(null != fileType && fileType % 100 == 1){
			return true;
		}
		return false;
	}
	
	public String getFileUri(){
		return fileUri;
	}
	
	public void setFileUri(String fileUri){
		this.fileUri = fileUri;
	}
	
}
