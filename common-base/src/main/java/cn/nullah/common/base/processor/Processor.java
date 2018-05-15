/* 
 * Copyright@ 2015-2018 http://www.nullah.cn/ rights reserved.
 */ 
package cn.nullah.common.base.processor; 

import cn.nullah.common.base.processor.exception.ProcessException;


/** 
 * @Project  : common-util 
 * @Author   : Administrator
 * @Date     : 2015年12月31日 
 */
public interface Processor<T> {
	public ProcessResult execute(T target) throws ProcessException;
}
