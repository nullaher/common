/* 
 * Copyright@ 2015-2018 http://www.nullah.cn/ rights reserved.
 */ 
package cn.nullah.common.base.processor.exception; 

/** 
 * TODO
 * @Project  : common-util 
 * @Author   : Administrator
 * @Date     : 2016年1月1日 
 */
@SuppressWarnings("serial")
public class ProcessException extends Exception {
	public ProcessException() {
        super();
    }

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(Throwable cause) {
        super(cause);
    }
}
