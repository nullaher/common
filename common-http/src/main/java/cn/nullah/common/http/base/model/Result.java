package cn.nullah.common.http.base.model;

public class Result {
	public Result(){
		this.resultEnum = ResultEnum.SUCCESS;
	}
	
	public Result(ResultEnum resultEnum){
		this.resultEnum = resultEnum;
	}
	
	private ResultEnum resultEnum;
	
	private String message;
	
	public ResultEnum getResultEnum(){
		return resultEnum;
	}
	
	public void setResultEnum(ResultEnum resultEnum){
		this.resultEnum = resultEnum;
	}
	
	public boolean beSuccess(){
		return resultEnum == ResultEnum.SUCCESS;
	}
	
	public boolean beFaliure(){
		return resultEnum == ResultEnum.FALIURE;
	}
	
	public boolean beException(){
		return resultEnum == ResultEnum.EXCEPTION;
	}
	
	public static Result success(){
		return new Result(ResultEnum.SUCCESS);
	}
	
	public static Result faliure(){
		return new Result(ResultEnum.FALIURE);
	}
	
	public static Result exception(){
		return new Result(ResultEnum.EXCEPTION);
	}
	
	public void toSuccess(){
		this.resultEnum = ResultEnum.SUCCESS;
	}
	
	public void toFaliure(){
		this.resultEnum = ResultEnum.FALIURE;
	}
	
	public void toException(){
		this.resultEnum = ResultEnum.EXCEPTION;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
}
