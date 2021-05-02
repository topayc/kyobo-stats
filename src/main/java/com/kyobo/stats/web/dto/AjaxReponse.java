package com.kyobo.stats.web.dto;

public class AjaxReponse {
	public String result;
	public int resultCode;
	public String message; //1 성공, 1외의 값 : 실패 
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
}
