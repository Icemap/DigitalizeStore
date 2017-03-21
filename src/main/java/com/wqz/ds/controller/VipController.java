package com.wqz.ds.controller;

import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqz.ds.bean.FormatResultBean;
import com.wqz.ds.pojo.UserInfo;
import com.wqz.ds.pojo.VipInfo;
import com.wqz.ds.service.impl.UserServiceImpl;
import com.wqz.ds.service.impl.VipInfoServiceImpl;

@Controller
@RequestMapping("/vip")
public class VipController
{
	@Autowired
	VipInfoServiceImpl vipInfoServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/create")
	@ResponseBody
	public JSONPObject createVip(String callback,String cmdUserName,String cmdPassword,
			Integer age,String name,String otherLabel,String phone)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		VipInfo vipInfo = new VipInfo();
		vipInfo.setAge(age);
		vipInfo.setBrandId(cmdUser.getBrandId());
		vipInfo.setName(name);
		vipInfo.setPhone(phone);
		vipInfo.setOtherLabel(otherLabel);
		boolean r = vipInfoServiceImpl.createVipInfo(vipInfo);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public JSONPObject updateVip(String callback,String cmdUserName,String cmdPassword,
			Integer vipId,Integer age,String name,String otherLabel,String phone)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		VipInfo vipInfo = new VipInfo();
		vipInfo.setId(vipId);
		vipInfo.setAge(age);
		vipInfo.setBrandId(cmdUser.getBrandId());
		vipInfo.setName(name);
		vipInfo.setPhone(phone);
		vipInfo.setOtherLabel(otherLabel);
		boolean r = vipInfoServiceImpl.updateVipInfo(vipInfo);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JSONPObject deleteVip(String callback,String cmdUserName,String cmdPassword,
			Integer vipId)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		boolean r = vipInfoServiceImpl.deleteVipInfo(vipId);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectById")
	@ResponseBody
	public JSONPObject selectById(String callback,Integer id)
	{
		FormatResultBean result = new FormatResultBean();
		VipInfo r = vipInfoServiceImpl.selectVipInfoById(id);
		if(r == null)
		{
			result.setErrorCode(3);
			result.setErrorMsg("无数据");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByPhone")
	@ResponseBody
	public JSONPObject selectByPhone(String callback,String phone)
	{
		FormatResultBean result = new FormatResultBean();
		VipInfo r = vipInfoServiceImpl.selectVipInfoByPhone(phone);
		if(r == null)
		{
			result.setErrorCode(3);
			result.setErrorMsg("无数据");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByBrandId")
	@ResponseBody
	public JSONPObject selectByBrandId(String callback,Integer brandId)
	{
		FormatResultBean result = new FormatResultBean();
		List<VipInfo> r = vipInfoServiceImpl.selectVipInfoByBrandId(brandId);
		if(r == null || r.isEmpty()) 
		{
			result.setErrorCode(3);
			result.setErrorMsg("无数据");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByName")
	@ResponseBody
	public JSONPObject selectByName(String callback,String name)
	{
		FormatResultBean result = new FormatResultBean();
		List<VipInfo> r = vipInfoServiceImpl.selectVipInfoByName(name);
		if(r == null || r.isEmpty()) 
		{
			result.setErrorCode(3);
			result.setErrorMsg("无数据");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByOtherLabel")
	@ResponseBody
	public JSONPObject selectByOtherLabel(String callback,String otherLabel)
	{
		FormatResultBean result = new FormatResultBean();
		List<VipInfo> r = vipInfoServiceImpl.selectVipInfoByOtherLabel(otherLabel);
		if(r == null || r.isEmpty()) 
		{
			result.setErrorCode(3);
			result.setErrorMsg("无数据");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
}
