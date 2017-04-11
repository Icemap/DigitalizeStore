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
	
	@RequestMapping("/push")
	@ResponseBody
	public JSONPObject push(Integer vipId, String storeId, String alert, String title,String callback)
	{
		jpushService.doActionPush(vipId, storeId, alert, title);
		return new JSONPObject(callback, true);
	}
}
