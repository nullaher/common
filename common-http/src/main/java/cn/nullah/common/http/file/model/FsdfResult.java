package cn.nullah.common.http.file.model;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import cn.nullah.common.http.base.model.ResultEnum;

public class FsdfResult extends FileProcResult {
	public FsdfResult(StorePath storePath){
		super(ResultEnum.SUCCESS);
		this.storePath = storePath;
	}
	
	public FsdfResult(ResultEnum resultEnum){
		super(resultEnum);
	}
	
	public FsdfResult(FileInfo dfsFileInf){
		super(ResultEnum.SUCCESS);
		 this.dfsFileInf = dfsFileInf;
	}
	
	private StorePath storePath;
	
	 private FileInfo dfsFileInf;
	
	 public FileInfo getDfsFileInf(){
	 return dfsFileInf;
	 }
	
	 public void setDfsFileInf(FileInfo dfsFileInf){
	 this.dfsFileInf = dfsFileInf;
	 }
	
	public StorePath getStorePath(){
		return storePath;
	}
	
	public void setStorePath(StorePath storePath){
		this.storePath = storePath;
	}
	
}
