package com.wqz.ds.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqz.ds.bean.FormatResultBean;
import com.wqz.ds.pojo.BrandInfo;
import com.wqz.ds.pojo.UserInfo;
import com.wqz.ds.service.impl.BrandInfoServiceImpl;
import com.wqz.ds.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/brand")
public class BrandController
{
	@Autowired
	BrandInfoServiceImpl brandInfoServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/create")
	@ResponseBody
	public JSONPObject createBrand(String callback,String brandName,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser != null && cmdUser.getLevel() != 0)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		BrandInfo info = new BrandInfo();
		info.setName(brandName);
		
		boolean r = brandInfoServiceImpl.createBrand(info);
		result.setResult(r);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
		}
		
		return new JSONPObject(callback, result);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public JSONPObject deleteBrand(String callback,Integer brandId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser != null && cmdUser.getLevel() != 0)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		boolean r = brandInfoServiceImpl.deleteBrand(brandId);
		
		result.setResult(r);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
		}
		
		return new JSONPObject(callback, result);
	}

	@RequestMapping("/update")
	@ResponseBody
	public JSONPObject updateBrand(String callback, Integer brandId, String brandName,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser != null && cmdUser.getLevel() != 0)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		BrandInfo info = new BrandInfo();
		info.setName(brandName);
		info.setId(brandId);
		
		boolean r = brandInfoServiceImpl.updateBrand(info);
		result.setResult(r);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
		}
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectById")
	@ResponseBody
	public JSONPObject selectById(String callback, Integer brandId)
	{
		FormatResultBean result = new FormatResultBean();
		BrandInfo info = brandInfoServiceImpl.selectBrand(brandId);
		
		if(info == null)
		{
			result.setErrorCode(3);
			result.setErrorMsg("无数据");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		result.setResult(info);
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByUserId")
	@ResponseBody
	public JSONPObject selectByUserId(String callback, Integer userId)
	{
		FormatResultBean result = new FormatResultBean();
		BrandInfo info = brandInfoServiceImpl.selectBrandByUserId(userId);
		
		if(info == null)
		{
			result.setErrorCode(3);
			result.setErrorMsg("无数据");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		result.setResult(info);
		return new JSONPObject(callback, result);
	}
}
