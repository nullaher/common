package cn.nullah.common.base.processor;

import cn.nullah.common.base.processor.file.Result;

public interface IRWHandle<T> {
	public Result read(T readObj);
	public Result write(T writeObj);
}
