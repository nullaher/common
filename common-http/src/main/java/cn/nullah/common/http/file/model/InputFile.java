package cn.nullah.common.http.file.model;

import java.io.InputStream;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

public class InputFile extends FileInf {
	public InputFile(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}

	@NotNull
	@JSONField(serialize = false)
	@Transient
	private InputStream inputStream;

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}
