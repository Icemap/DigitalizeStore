package com.wqz.ds.bean;

public class FormatResultBean
{
	/**
	 * 1:Ȩ�޲���
	 * 2:���ݿ��������
	 * 3:������
	 * 4:ʱ��ת������
	 * 5:��¼ʧ��
	 * 6:�û����ظ�
	 */
	
	Integer errorCode = 0;
	String errorMsg = "�ɹ�";
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
