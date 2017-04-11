package com.wqz.ds.bean;

public class FormatResultBean
{
	/**
	 * 1:permission denied
	 * 2:database error
	 * 3:data is empty
	 * 4:date transform error
	 * 5:login error
	 * 6:username is exist
	 */
	
	Integer errorCode = 0;
	String errorMsg = "success";
	Object result = "";
	
	public static FormatResultBean PermissionDenied()
	{
		FormatResultBean formatResultBean = new FormatResultBean();
		formatResultBean.errorCode = 1;
		formatResultBean.errorMsg = "permission denied";
		
		return formatResultBean;
	}
	
	public static FormatResultBean DatabaseError()
	{
		FormatResultBean formatResultBean = new FormatResultBean();
		formatResultBean.errorCode = 2;
		formatResultBean.errorMsg = "database error";
		
		return formatResultBean;
	}
	
	public static FormatResultBean DataIsEmpty()
	{
		FormatResultBean formatResultBean = new FormatResultBean();
		formatResultBean.errorCode = 3;
		formatResultBean.errorMsg = "data is empty";
		
		return formatResultBean;
	}
	
	public static FormatResultBean DataTransformError()
	{
		FormatResultBean formatResultBean = new FormatResultBean();
		formatResultBean.errorCode = 4;
		formatResultBean.errorMsg = "date transform error";
		
		return formatResultBean;
	}
	
	public static FormatResultBean LoginError()
	{
		FormatResultBean formatResultBean = new FormatResultBean();
		formatResultBean.errorCode = 5;
		formatResultBean.errorMsg = "login error";
		
		return formatResultBean;
	}

	public static FormatResultBean UsernameIsExist()
	{
		FormatResultBean formatResultBean = new FormatResultBean();
		formatResultBean.errorCode = 6;
		formatResultBean.errorMsg = "username is exist";
		
		return formatResultBean;
	}
	
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
