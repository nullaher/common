/* 
 * Copyright@ 2015-2018 http://www.nullah.cn/ rights reserved.
 */ 
package cn.nullah.common.base.processor.exception; 

/** 
 * TODO
 * @Project  : common-util 
 * @Author   : Administrator
 * @Date     : 2015年12月31日 
 */
@SuppressWarnings("serial")
public class FileProcessException extends ProcessException {
	public FileProcessException() {
        super();
    }

    public FileProcessException(String message) {
        super(message);
    }

    public FileProcessException(Throwable cause) {
        super(cause);
    }
}
