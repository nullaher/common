package cn.nullah.common.base.processor.file;

public class Row<T> {
	
	public Row(int lineNum, String text) {
		super();
		this.lineNum = lineNum;
		this.text = text;
	}
	
	private int lineNum;
	private String tag;
	private String text;
	
	
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
