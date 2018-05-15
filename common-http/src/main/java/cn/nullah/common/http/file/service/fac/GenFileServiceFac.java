/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.fac.GenFileServiceFac.java zxy@nullah.cn 2017年7月27日
 */
package cn.nullah.common.http.file.service.fac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.nullah.common.http.file.service.SpringContextHolder;
import cn.nullah.common.http.file.service.fac.product.IMyFileService;
import cn.nullah.common.http.file.service.fac.product.IStrategyService;

/**
 * @autor: zxy@nullah.cn
 * @desc : ...
 */
public class GenFileServiceFac implements IFileServiceFac {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private FileContext context;
	
	private GenFileServiceFac(FileContext context){
		this.context = context;
	}
	
	public static IFileServiceFac build(FileContext context){
		return new GenFileServiceFac(context);
	}
	
	@Override
	public IMyFileService createBaseService(){
		if(context != null){
			Integer fileType = context.getFileType();
			logger.info("文件类型为:" + fileType);
			return (IMyFileService) SpringContextHolder.getBean("fileRecordService");
		}
		return (IMyFileService) SpringContextHolder.getBean("nothingDoFileService");
	}
	
	@Override
	public IStrategyService createStrategyService(){
		return null;
	}
	
}
