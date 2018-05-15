/* 
 * Copyright@ 2015-2018 http://www.nullah.cn/ rights reserved.
 */
package cn.nullah.common.base.processor.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Project : common-util
 * @Author : Administrator
 * @Date : 2015年12月31日
 */
public class CSVFileHandler extends FileProcessSupport implements
		IFileHandler<File> {
	/**
	 * 若设置为true则直接使用逗号分割，用于数据格式无符号的文件处理，提高性能
	 */
	private boolean noSpcilSymbol=false; 

	private IRowHandler csvRowHandler;
	
	@Override
	public Result write(File file) {
		return new Result();
	}

	@Override
	public Result read(File file) {
		Result result=new Result();
		FileInputStream inputStrem = null;
		InputStreamReader readStrem = null;
		BufferedReader bufferedRead = null;
		try {
			inputStrem = new FileInputStream(file);
			readStrem = new InputStreamReader(inputStrem, encode);
			bufferedRead = new BufferedReader(readStrem);
			String lineText;
			int lineNum=0;
			while ((lineText = bufferedRead.readLine()) != null) {
				csvRowHandler.read(new Row<String>(lineNum++,lineText));
			}
			return result;
		} catch (Exception e) {
//			throw new FileProcessException(e);
		} finally {
			try {
				if (null != bufferedRead) {
					bufferedRead.close();
				}
				if (null != readStrem) {
					readStrem.close();
				}
				if (null != inputStrem) {
					inputStrem.close();
				}
			} catch (IOException e) {
//				throw new FileProcessException(e);
			}
		}
		return result;
	}

	public boolean isNoSpcilSymbol() {
		return noSpcilSymbol;
	}

	public void setNoSpcilSymbol(boolean noSpcilSymbol) {
		this.noSpcilSymbol = noSpcilSymbol;
	}

	public IRowHandler getCsvRowHandler() {
		return csvRowHandler;
	}

	public void setCsvRowHandler(IRowHandler csvRowHandler) {
		this.csvRowHandler = csvRowHandler;
	}
}
