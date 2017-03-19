package com.wqz.ds.bean;

public class FormatResultBean
{
	/**
	 * 1:权限不足
	 * 2:数据库操作错误
	 * 3:无数据
	 * 4:时间转换错误
	 * 5:登录失败
	 * 6:用户名重复
	 */
	
	Integer errorCode = 0;
	String errorMsg = "成功";
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
