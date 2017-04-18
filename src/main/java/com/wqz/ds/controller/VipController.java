package com.wqz.ds.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.wqz.ds.bean.FormatResultBean;
import com.wqz.ds.bean.OtherLabel;
import com.wqz.ds.bean.PathBean;
import com.wqz.ds.pojo.UserInfo;
import com.wqz.ds.pojo.VipInfo;
import com.wqz.ds.pojo.VipPushMsg;
import com.wqz.ds.service.impl.UserServiceImpl;
import com.wqz.ds.service.impl.VipInfoServiceImpl;
import com.wqz.ds.service.impl.VipPushInfoServiceImpl;
import com.wqz.ds.utils.ByteBooleanUtils;
import com.wqz.ds.utils.FileUtils;

@Controller
@RequestMapping("/vip")
public class VipController
{
	@Autowired
	VipInfoServiceImpl vipInfoServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	VipPushInfoServiceImpl vipPushInfoServiceImpl;
	
	@RequestMapping("/push")
	@ResponseBody
	public JSONPObject pushVip(Integer vipId,Long timestramp,Boolean isBought,
			String boughtMoney,String boughtList,String callback)
	{
		VipPushMsg vipPushMsg = new VipPushMsg();
		vipPushMsg.setVipId(vipId);
		vipPushMsg.setDatetime(new Date(timestramp));
		vipPushMsg.setBoughtList(boughtList);
		vipPushMsg.setBoughtMoney(boughtMoney);
		vipPushMsg.setIsBought(ByteBooleanUtils.boolean2Byte(isBought));
		
		FormatResultBean result = new FormatResultBean();
		result.setResult(vipPushInfoServiceImpl.insert(vipPushMsg));
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public JSONPObject createVip(
			@RequestParam(value = "callback",required = true)String callback,
			@RequestParam(value = "cmdUserName",required = true)String cmdUserName,
			@RequestParam(value = "cmdPassword",required = true)String cmdPassword,
			@RequestParam(value = "age",required = true)Integer age,
			@RequestParam(value = "name",required = true)String name,
			@RequestParam(value = "sex",required = true)String sex,
			@RequestParam(value = "pic",required = true)MultipartFile pic,
			@RequestParam(value = "phone",required = true)String phone)
	{
		PathBean pathBean = new Gson().fromJson(FileUtils.
				readResourcesByLines("path.json"), PathBean.class);
		String vipSavePath = pathBean.vipSavePath;
		String vipGetPath = pathBean.vipGetPath;
		String filename = new Date().getTime() + ".jpg";
		
		OtherLabel otherLabel = new OtherLabel();
		try
		{
			FileUtils.savePic(pic.getInputStream(), filename, vipSavePath);
			otherLabel.picUrl = vipGetPath + filename;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		otherLabel.sex = sex;
		
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null)
		{
			result = FormatResultBean.PermissionDenied();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		VipInfo vipInfo = new VipInfo();
		vipInfo.setAge(age);
		vipInfo.setBrandId(cmdUser.getBrandId());
		vipInfo.setName(name);
		vipInfo.setPhone(phone);
		vipInfo.setOtherLabel(new Gson().toJson(otherLabel));
		boolean r = vipInfoServiceImpl.createVipInfo(vipInfo);
		if(!r)
		{
			result = FormatResultBean.DatabaseError();
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
			result = FormatResultBean.PermissionDenied();
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
			result = FormatResultBean.DatabaseError();
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
			result = FormatResultBean.PermissionDenied();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		boolean r = vipInfoServiceImpl.deleteVipInfo(vipId);
		if(!r)
		{
			result = FormatResultBean.DatabaseError();
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
			result = FormatResultBean.DataIsEmpty();
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
			result = FormatResultBean.DataIsEmpty();
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
			result = FormatResultBean.DataIsEmpty();
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
			result = FormatResultBean.DataIsEmpty();
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
			result = FormatResultBean.DataIsEmpty();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectAll")
	@ResponseBody
	public Object selectAll(Integer start,Integer size)
	{
		return vipInfoServiceImpl.selectAll(start, size);
	}
}
