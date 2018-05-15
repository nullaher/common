/* 
 * Copyright@ 2015-2018 http://www.nullah.cn/ rights reserved.
 */
package cn.nullah.common.base.processor;

/**
 * TODO
 * 
 * @Project : common-io
 * @Author : Administrator
 * @Date : 2015年12月31日
 */
public class ProcessResult {
	private Object params;
	private boolean succeeded;

	protected ProcessResult(Object params, boolean succeeded) {
		this.params = params;
		this.succeeded = succeeded;
	}

	public static ProcessResult succeed(Object params) {
		return new ProcessResult(params, true);
	}

	public static ProcessResult fail(Object params) {
		return new ProcessResult(params, false);
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}
	

}
