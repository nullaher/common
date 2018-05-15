/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.fac.IBaseFileService.java zxy@nullah.cn 2017年7月27日
 */
package cn.nullah.common.http.file.service.fac.product;

import java.util.List;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
public interface IMyFileService {
	public FileProcResult save(FileInf fileInf);
	
	public FileProcResult save(List<FileInf> fileList);
	
	public void delete(String id);
	
	public FileInf getById(String fileId);
}
