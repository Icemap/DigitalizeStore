package com.wqz.ds.controller;

import java.text.ParseException;
import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqz.ds.bean.FormatResultBean;
import com.wqz.ds.pojo.CameraInfo;
import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.pojo.UserInfo;
import com.wqz.ds.service.impl.CameraInfoServiceImpl;
import com.wqz.ds.service.impl.CameraPushMsgServiceImpl;
import com.wqz.ds.service.impl.UserServiceImpl;
import com.wqz.ds.utils.ByteBooleanUtils;
import com.wqz.ds.utils.DateTimeUtils;

@Controller
@RequestMapping("/camera")
public class CameraController
{
	@Autowired
	CameraInfoServiceImpl cameraInfoServiceImpl;
	
	@Autowired
	CameraPushMsgServiceImpl cameraPushMsgServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/push")
	@ResponseBody
	public JSONPObject pushMsg(String callback,Integer cameraId,
			String datetime,Boolean isAdd,Integer age,
			Boolean isMale,Boolean isEnterStore,
			Integer storeId, String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() != 0)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		CameraPushMsg msg = new CameraPushMsg();
		try
		{
			msg.setDatetime(DateTimeUtils.Str2Date(datetime));
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
			result.setErrorCode(4);
			result.setErrorMsg("时间格式错误");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		msg.setAge(age);
		msg.setCameraId(cameraId);
		msg.setIsAdd(ByteBooleanUtils.boolean2Byte(isAdd));
		msg.setIsEnterStore(ByteBooleanUtils.boolean2Byte(isEnterStore));
		msg.setIsMale(ByteBooleanUtils.boolean2Byte(isMale));
		msg.setStoreId(storeId);
		
		boolean r = cameraPushMsgServiceImpl.pushMsg(msg);
		result.setResult(r);
		if(!r)
		{
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
			return new JSONPObject(callback, result);
		}
		
		return new JSONPObject(callback, result);
	}

	@RequestMapping("/create")
	@ResponseBody
	public JSONPObject createCamera(String callback,String url,
			Boolean isEnter,String area,String hotPointPath, 
			String pathPath,Integer storeId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() != 0)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		CameraInfo info = new CameraInfo();
		
		info.setArea(area);
		info.setHotPointPath(hotPointPath);
		info.setIsEnter(ByteBooleanUtils.boolean2Byte(isEnter));
		info.setPathPath(pathPath);
		info.setStoreId(storeId);
		info.setUrl(url);
		
		boolean r = cameraInfoServiceImpl.createCamera(info);
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
	public JSONPObject updateCamera(String callback,Integer cameraId,
			String url,Boolean isEnter,String area,
			String hotPointPath,String pathPath,Integer storeId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() != 0)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		CameraInfo info = new CameraInfo();
		info.setId(cameraId);
		info.setArea(area);
		info.setHotPointPath(hotPointPath);
		info.setIsEnter(ByteBooleanUtils.boolean2Byte(isEnter));
		info.setPathPath(pathPath);
		info.setStoreId(storeId);
		info.setUrl(url);
		
		boolean r = cameraInfoServiceImpl.updateCamera(info);
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
	public JSONPObject deleteCamera(String callback,Integer cameraId,
			String cmdUserName,String cmdPassword)
	{
		FormatResultBean result = new FormatResultBean();
		
		UserInfo cmdUser = userServiceImpl.userLogin(cmdUserName, cmdPassword);
		if(cmdUser == null || cmdUser.getLevel() != 0)
		{
			result.setErrorCode(1);
			result.setErrorMsg("用户权限不足");
			result.setResult(false);
			return new JSONPObject(callback, result);
		}
		
		boolean r = cameraInfoServiceImpl.deleteCamera(cameraId);
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
	public JSONPObject selectCameraById(String callback,Integer cameraId)
	{
		FormatResultBean result = new FormatResultBean();
		
		CameraInfo r = cameraInfoServiceImpl.getCameraInfoById(cameraId);
		if(r == null)
		{
			result.setResult(false);
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByStoreId")
	@ResponseBody
	public JSONPObject selectCameraByStoreId(String callback,Integer storeId)
	{
		FormatResultBean result = new FormatResultBean();
		
		List<CameraInfo> r = cameraInfoServiceImpl.getCameraInfoByStoreId(storeId);
		if(r == null || r.size() == 0)
		{
			result.setResult(false);
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByBusinessUnitId")
	@ResponseBody
	public JSONPObject selectCameraByBusinessUnitId(String callback,Integer businessUnitId)
	{
		FormatResultBean result = new FormatResultBean();
		
		List<CameraInfo> r = cameraInfoServiceImpl.getCameraInfoByBusinessUnitId(businessUnitId);
		if(r == null || r.size() == 0)
		{
			result.setResult(false);
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/selectByBrandId")
	@ResponseBody
	public JSONPObject selectCameraByBrandId(String callback,Integer brandId)
	{
		FormatResultBean result = new FormatResultBean();
		
		List<CameraInfo> r = cameraInfoServiceImpl.getCameraInfoByBrandId(brandId);
		if(r == null || r.size() == 0)
		{
			result.setResult(false);
			result.setErrorCode(2);
			result.setErrorMsg("数据库操作错误");
			return new JSONPObject(callback, result);
		}
		
		result.setResult(r);
		
		return new JSONPObject(callback, result);
	}
}
