package com.wqz.ds.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wqz.ds.service.impl.JpushService;

@Controller
@RequestMapping("/jpush")
public class JPushController
{
	@Autowired
	JpushService jpushService;
	
	@RequestMapping("/vip/push")
	@ResponseBody
	public JSONPObject vipPush(Integer vipId, String storeId, String alert, String title, String callback)
	{
		Object result = jpushService.doActionPush(vipId, storeId, alert, title, "vip");
		return new JSONPObject(callback, result);
	}

	@RequestMapping("/face/push")
	@ResponseBody
	public JSONPObject facePush(Integer faceId, String storeId, String alert, String title, String callback)
	{
		Object result = jpushService.doActionPush(faceId, storeId, alert, title, "face");
		return new JSONPObject(callback, result);
	}
	
	@RequestMapping("/demo/push")
	@ResponseBody
	public JSONPObject demoPush(String alert, String title, String msg, String surplus,String picUrl,String tag,String callback)
	{
		Object result = jpushService.doBottleActionPush(alert, title, msg, surplus, picUrl, tag);
		return new JSONPObject(callback, result);
	}
}
