package com.hqyj.SpringBootDemo.modules.common.vo;

public class Result<T> {


	private int status;
	private String message;
	private T obj;
	
	public Result() {}
	
	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Result(int status, String message, T obj) {
		this.status = status;
		this.message = message;
		this.obj = obj;
	}
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	
	
	public enum ResultStatus{
		SUCCESS(200),FAILD(500);
		
		public int status;

		private ResultStatus(int status) {
			this.status = status;
		}
	}
	
	
	
	
}
































