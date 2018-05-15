/* 
 * Copyright@ 2015-2018 http://www.nullah.cn/ rights reserved.
 */ 
package cn.nullah.common.base.processor.file; 

import cn.nullah.common.base.processor.NoProcessor;
import cn.nullah.common.base.processor.ProcessResult;
import cn.nullah.common.base.processor.Processor;
import cn.nullah.common.base.processor.exception.ProcessException;

/** 
 * TODO
 * @Project  : common-util 
 * @Author   : Administrator
 * @Date     : 2015年12月31日 
 */
public class CVSRowProcessor implements Processor<String> {
	private Processor<String> clientProcessor = NoProcessor.getInstance();
	@Override
	public ProcessResult execute(String csvLine) throws ProcessException {
		return clientProcessor.execute(csvLine);
	}
	
}
