package com.wqz.ds.controller;

import java.text.ParseException;
import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqz.ds.bean.FormatResultBean;
import com.wqz.ds.pojo.StoreBillsPushMsg;
import com.wqz.ds.pojo.StoreInfo;
import com.wqz.ds.pojo.UserInfo;
import com.wqz.ds.service.impl.StoreBillsPushMsgServiceImpl;
import com.wqz.ds.service.impl.StoreInfoServiceImpl;
import com.wqz.ds.service.impl.UserServiceImpl;
import com.wqz.ds.utils.DateTimeUtils;

@Controller
@RequestMapping("/store")
public class StoreController
{
	@Autowired
	StoreBillsPushMsgServiceImpl storeBillsPushMsgServiceImpl;
	
	@Autowired
	StoreInfoServiceImpl storeInfoServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/push")
	@ResponseBody
	public JSONPObject pushMsg(String callback, String datetime,
			String boughtList,Integer boughtMoney,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getStoreId() == null)
		{
			result = FormatResultBean.PermissionDenied();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		StoreBillsPushMsg msg = new StoreBillsPushMsg();
		try
		{
			msg.setDatetime(DateTimeUtils.Str2Date(datetime));
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
			result = FormatResultBean.DatabaseError();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		msg.setBoughtList(boughtList);
		msg.setBoughtMoney(boughtMoney.toString());
		msg.setStoreId(cmdUser.getStoreId());
		
		boolean r = storeBillsPushMsgServiceImpl.pushMsg(msg);
		if(!r)
		{
			result = FormatResultBean.DatabaseError();
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JSONPObject delete(String callback,Integer storeId)
	{
		FormatResultBean result = new FormatResultBean();
		
		Boolean r = storeInfoServiceImpl.deleteStoreInfo(storeId);
		
		if(!r)
		{
			result = FormatResultBean.DatabaseError();
		}
		
		result.setResult(r);
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public JSONPObject createStore(String callback,String name,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() != 2)
		{
			result = FormatResultBean.PermissionDenied();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		StoreInfo info = new StoreInfo();
		
		info.setBusinessUnitId(cmdUser.getBusinessUnitId());
		info.setName(name);
		
		boolean r = storeInfoServiceImpl.createStoreInfo(info);
		if(!r)
		{
			result = FormatResultBean.DatabaseError();
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}

	@RequestMapping("/update")
	@ResponseBody
	public JSONPObject updateStore(String callback,
			Integer id,String name,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		StoreInfo storeInfo = storeInfoServiceImpl.getInfoById(id);
		
		if(cmdUser == null || 
				cmdUser.getLevel() != 2 || 
				storeInfo.getBusinessUnitId() != cmdUser.getBusinessUnitId())
		{
			result = FormatResultBean.PermissionDenied();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		StoreInfo info = new StoreInfo();
		info.setId(id);
		info.setBusinessUnitId(cmdUser.getBusinessUnitId());
		info.setName(name);
		
		boolean r = storeInfoServiceImpl.updateStoreInfo(info);
		if(!r)
		{
			result = FormatResultBean.DatabaseError();
		}
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}

	@RequestMapping("/selectById")
	@ResponseBody
	public JSONPObject selectById(String callback,Integer storeId)
	{
		FormatResultBean result = new FormatResultBean();
		
		StoreInfo r = storeInfoServiceImpl.getInfoById(storeId);
		
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
		
		List<StoreInfo> r = storeInfoServiceImpl.getInfoByBrandId(brandId);
		
		if(r == null || r.size() == 0)
		{
			result = FormatResultBean.DataIsEmpty();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByBusinessUnitId")
	@ResponseBody
	public JSONPObject selectByBusinessUnitId(String callback,Integer businessUnitId)
	{
		FormatResultBean result = new FormatResultBean();
		
		List<StoreInfo> r = storeInfoServiceImpl.getInfoByBusinessUnitId(businessUnitId);
		
		if(r == null || r.size() == 0)
		{
			result = FormatResultBean.DataIsEmpty();
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		return new JSONPObject(callback, result);
	}
}
