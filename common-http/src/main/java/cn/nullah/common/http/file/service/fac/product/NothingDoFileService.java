/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.fac.product.NothingDoFileService.java zxy@nullah.cn 2017年7月27日
 */
package cn.nullah.common.http.file.service.fac.product;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.nullah.common.http.file.model.FileInf;
import cn.nullah.common.http.file.model.FileProcResult;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
public class NothingDoFileService implements IMyFileService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public FileProcResult save(FileInf fileInf){
		logger.info("不做任何[保存]操作!");
		FileProcResult result = new FileProcResult();
//		result.setFileIdStr(System.currentTimeMillis() + "");
		return result;
	}
	
	@Override
	public FileProcResult save(List<FileInf> fileList){
		return save(fileList.get(0));
	}
	
	@Override
	public void delete(String id){
		logger.info("不做任何[删除]操作!");
	}
	
	@Override
	public FileInf getById(String fileId){
		logger.info("不做任何[查询]操作!,返回为空");
		return null;
	}
	
}
