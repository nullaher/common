package cn.nullah.common.base.processor.file;

public class Result {
	private int status = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isOk() {
		return status == 0;
	}
}
