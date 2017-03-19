package com.wqz.ds.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqz.ds.bean.FormatResultBean;
import com.wqz.ds.pojo.BusinessUnitInfo;
import com.wqz.ds.pojo.UserInfo;
import com.wqz.ds.service.impl.BusinessUnitInfoServiceImpl;
import com.wqz.ds.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/businessUnit")
public class BusinessUnitController
{
	@Autowired
	BusinessUnitInfoServiceImpl businessUnitInfoServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/create")
	@ResponseBody
	public Object createBusinessUnit(String callback,String businessUnitName,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() > 1)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		BusinessUnitInfo info = new BusinessUnitInfo();
		info.setBrandId(cmdUser.getBrandId());
		info.setName(businessUnitName);
		
		boolean r = businessUnitInfoServiceImpl.createBusinessUnit(info);
		result.setResult(r);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("���ݿ��������");
		}
		
		return new JSONPObject(callback, result);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object deleteBusinessUnit(String callback,Integer businessUnitId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() > 1)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		boolean r = businessUnitInfoServiceImpl.deleteBusinessUnit(businessUnitId);
		
		result.setResult(r);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("���ݿ��������");
		}
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object updateBusinessUnit(String callback,Integer businessUnitId,
			String businessUnitName,String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() > 1)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		BusinessUnitInfo info = new BusinessUnitInfo();
		info.setId(businessUnitId);
		info.setName(businessUnitName);
		
		boolean r = businessUnitInfoServiceImpl.createBusinessUnit(info);
		result.setResult(r);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("���ݿ��������");
		}
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectById")
	@ResponseBody
	public Object selectBusinessUnitById(String callback,Integer businessUnitId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		BusinessUnitInfo r = businessUnitInfoServiceImpl.selectById(businessUnitId);
		if(r == null)
		{
			result.setResult(false);
			result.setErrorCode(2);
			result.setErrorMsg("���ݿ��������");
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByUserId")
	@ResponseBody
	public Object selectBusinessUnitByUserId(String callback,Integer userId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		BusinessUnitInfo r = businessUnitInfoServiceImpl.selectByUserId(userId);
		if(r == null)
		{
			result.setResult(false);
			result.setErrorCode(2);
			result.setErrorMsg("���ݿ��������");
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		return new JSONPObject(callback, result);
	}
}