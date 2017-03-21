package com.wqz.ds.controller;

import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqz.ds.bean.FormatResultBean;
import com.wqz.ds.bean.StoreDataBean;
import com.wqz.ds.pojo.UserInfo;
import com.wqz.ds.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController
{
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/login")
	@ResponseBody
	public JSONPObject login(String callback,String username,String password)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo user = userServiceImpl.userLogin(username, password);
		if(user == null)
		{
			result.setErrorCode(5);
			result.setErrorMsg("�޴��û�");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		result.setResult(user);
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public JSONPObject regist(String callback,
			String username,String password,
			Integer level,Integer brandId,
			Integer businessUnitId,Integer storeId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() >= level)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���,���ô���ͬȨ�޻��Ȩ���˺š�");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		if(level == 2 && cmdUser.getBrandId() != brandId)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���,���ô����Ǳ�Ʒ�Ƶ���ҵ���û�");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		else if(level == 3 && cmdUser.getBusinessUnitId() != businessUnitId)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���,���ô����Ǳ���ҵ�����ŵ��û�");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		if(0 != userServiceImpl.getUsernameCount(username))
		{
			result.setErrorCode(6);
			result.setErrorMsg("�û����Ѵ���");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		UserInfo info = new UserInfo();
		info.setBrandId(brandId);
		info.setBusinessUnitId(businessUnitId);
		info.setLevel(level);
		info.setPassword(password);
		info.setStoreId(storeId);
		info.setUsername(username);
		boolean r = userServiceImpl.userRegister(info);
		
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("���ݿ��������");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public JSONPObject update(String callback,
			Integer id,String username,String password,
			Integer level,Integer brandId,
			Integer businessUnitId,Integer storeId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() >= level)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���,�����޸�ͬȨ�޻��Ȩ���˺š�");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		if(level == 2 && cmdUser.getBrandId() != brandId)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���,�����޸ķǱ�Ʒ�Ƶ���ҵ���û�");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		else if(level == 3 && cmdUser.getBusinessUnitId() != businessUnitId)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�û�Ȩ�޲���,�����޸ķǱ���ҵ�����ŵ��û�");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		UserInfo info = new UserInfo();
		info.setId(id);
		info.setBrandId(brandId);
		info.setBusinessUnitId(businessUnitId);
		info.setLevel(level);
		info.setPassword(password);
		info.setStoreId(storeId);
		info.setUsername(username);
		boolean r = userServiceImpl.userUpdate(info);
		
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("���ݿ��������");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/getStoreMsg")
	@ResponseBody
	public JSONPObject getStoreMsg(String callback,String cmdUserName,
			String cmdPassword, String startTime,String endTime)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null)
		{
			result.setErrorCode(1);
			result.setErrorMsg("�޴��û�");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		List<StoreDataBean> r = userServiceImpl.getStoreMsg(cmdUser.getId(), startTime, endTime);
		
		if(r == null || r.isEmpty())
		{
			result.setErrorCode(3);
			result.setErrorMsg("������");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		return new JSONPObject(callback, result);
	}
}
