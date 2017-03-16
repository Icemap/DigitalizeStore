package com.wqz.ds.bean;

public class FormatResultBean
{
	Integer errorCode = 0;
	String errorMsg = "success";
	Object result = "";
	
	public void setErrorCode(Integer errorCode)
	{
		this.errorCode = errorCode;
	}
	
	public Integer getErrorCode()
	{
		return errorCode;
	}
	
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
	
	public String getErrorMsg()
	{
		return errorMsg;
	}
	
	public void setResult(Object result)
	{
		this.result = result;
	}
	
	public Object getResult()
	{
		return result;
	}
	
}
