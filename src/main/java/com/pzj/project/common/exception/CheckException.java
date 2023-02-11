package com.pzj.project.common.exception;
public  class CheckException extends  RuntimeException {
	private static final long serialVersionUID = 1L;

	private  Integer status;
	
	private  String  message;
	
	private CheckException errorCode;
	
	public CheckException() {

	}
	public CheckException(CheckException errorCode) {
		this.errorCode = errorCode;
	}
	public CheckException(String  message) {
		super(message);
	}
    public CheckException(Integer  code, String message) {
		this.status = code;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer code) {
		this.status = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public CheckException getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(CheckException errorCode) {
		this.errorCode = errorCode;
	}
}
