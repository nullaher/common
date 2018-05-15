/* 
 * Copyright@ 2015-2018 http://www.nullah.cn/ rights reserved.
 */ 
package cn.nullah.common.base.processor; 

import cn.nullah.common.base.processor.exception.ProcessException;

/** 
 * TODO
 * @Project  : common-util 
 * @Author   : Administrator
 * @Date     : 2015年12月31日 
 */
public class NoProcessor implements Processor<String> {
	
	private static NoProcessor noProcessor=new NoProcessor();
	
	private NoProcessor(){
		
	}
	
	@Override
	public ProcessResult execute(String target) throws ProcessException {
		return ProcessResult.succeed(target);
	}
	
	 /**
     * 静态工厂方法
     */
    public static NoProcessor getInstance(){
        return noProcessor;
    }

}
