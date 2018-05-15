/**
 * Copyright ©2017 www.nullah.cn Technology Co.,Ltd.All Rights Reserved
 * cn.nullah.common.http.file.service.IFileServiceFactory.java zxy@nullah.cn 2017年7月27日
 */
package cn.nullah.common.http.file.service.fac;

import cn.nullah.common.http.file.service.fac.product.IMyFileService;
import cn.nullah.common.http.file.service.fac.product.IStrategyService;

/**
 * @autor: zxy@nullah.cn
 * @desc : 文件服务工厂
 */
public interface IFileServiceFac {
	public IMyFileService createBaseService();
	
	// public IRuleFileService createRuleService();
	
	public IStrategyService createStrategyService();
}
