package com.wqz.ds.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.wqz.ds.bean.PathBean;
import com.wqz.ds.pojo.AllFace;
import com.wqz.ds.service.impl.AllFaceServiceImpl;
import com.wqz.ds.utils.FileUtils;

@Controller
@RequestMapping("/all_face")
public class AllFaceController
{
	@Autowired
	AllFaceServiceImpl allFaceServiceImpl;
	
	@RequestMapping("/push")
	@ResponseBody
	public Object pushAllFace(
			@RequestParam(value = "cameraId",required = true)Integer cameraId,
			@RequestParam(value = "timeStramp",required = true)Long timeStramp,
			@RequestParam(value = "pic",required = true)MultipartFile pic)
	{
		PathBean pathBean = new Gson().fromJson(FileUtils.
				readResourcesByLines("path.json"), PathBean.class);
		String allFacePath = pathBean.allSavePath;
		String allGetPath = pathBean.allGetPath;
		String filename = new Date().getTime() + ".jpg";
		
		AllFace allFace = new AllFace();
		allFace.setCameraid(cameraId);
		allFace.setDatetime(new Date(timeStramp));
		try
		{
			FileUtils.savePic(pic.getInputStream(), filename, allFacePath);
			allFace.setPicurl(allGetPath + filename);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		return allFaceServiceImpl.insert(allFace);
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public Object getAllFaceByPage(Integer start,Integer size)
	{
		return allFaceServiceImpl.select(start, size);
	}
	
	@RequestMapping("/getByStoreId")
	@ResponseBody
	public Object getFaceByStoreId(Integer storeId, Integer start, Integer size)
	{
		return allFaceServiceImpl.selectByStoreId(storeId, start, size);
	}
}
