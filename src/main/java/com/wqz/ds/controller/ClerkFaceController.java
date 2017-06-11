package com.wqz.ds.controller;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.wqz.ds.bean.FormatResultBean;
import com.wqz.ds.bean.PathBean;
import com.wqz.ds.pojo.ClerkFace;
import com.wqz.ds.service.impl.ClerkFaceServiceImpl;
import com.wqz.ds.utils.FileUtils;

@Controller
@RequestMapping("/clerk_face")
public class ClerkFaceController
{
	@Autowired
	ClerkFaceServiceImpl clerkFaceServiceImpl;
	
	@RequestMapping("/selectByStoreId")
	@ResponseBody
	public Object selectByStoreId(Integer storeId,String callback)
	{
		FormatResultBean result = new FormatResultBean();
		result.setResult(clerkFaceServiceImpl.selectByStoreId(storeId));
		return new JSONPObject(callback,result);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insertSelective(
			@RequestParam(value = "pic",required = true)MultipartFile pic,
			@RequestParam(value = "storeId",required = true)Integer storeId,
			@RequestParam(value = "callback",required = true)String callback)
	{
		PathBean pathBean = new Gson().fromJson(FileUtils.
				readResourcesByLines("path.json"), PathBean.class);
		String vipFacePath = pathBean.vipSavePath;
		String vipGetPath = pathBean.vipGetPath;
		String filename = new Date().getTime() + ".jpg";
		
		ClerkFace record = new ClerkFace();
		record.setStoreid(storeId);
		try
		{
			FileUtils.savePic(pic.getInputStream(), filename, vipFacePath);
			record.setPic(vipGetPath + filename);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		FormatResultBean result = new FormatResultBean();
		result.setResult(clerkFaceServiceImpl.insertSelective(record));
		return new JSONPObject(callback,result);
	}
}
