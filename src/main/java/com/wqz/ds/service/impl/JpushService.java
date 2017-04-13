package com.wqz.ds.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.wqz.ds.controller.JPushController;
import com.wqz.ds.dao.VipInfoMapper;
import com.wqz.ds.dao.VipPushMsgMapper;
import com.wqz.ds.pojo.VipInfo;
import com.wqz.ds.pojo.VipPushMsg;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

@Service
public class JpushService
{
	@Autowired
	VipInfoMapper vipInfoMapper;
	
	@Autowired
	VipPushMsgMapper vipPushMsgMapper;
	
	Logger log = Logger.getLogger(JPushController.class);
	
	private static String APP_KEY = "2936fe5db1a138eb4bef314a";
	private static String MASTER_SECRET = "7f51133320b18cde7ab0c7ff";
	
	private PushPayload getPushInfo(Integer vipId, String storeId, String alert, String title) 
	{
		VipInfo vipInfo = vipInfoMapper.selectByPrimaryKey(vipId);
		List<VipPushMsg> msg = vipPushMsgMapper.selectByVipId(vipId);
		
		Map<String,String> extra = new HashMap<>();
		extra.put("name", vipInfo.getName());
		extra.put("age", vipInfo.getAge() + "");
		extra.put("phone", vipInfo.getPhone());
		extra.put("otherLabel", vipInfo.getOtherLabel());
		extra.put("bought", new Gson().toJson(msg));
		
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(storeId))
                .setNotification(Notification.newBuilder()
                .setAlert(alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
    	                        .setBuilderId(1)
    	                        .setTitle(title)
                                .addExtras(extra)
    	                        .build())
                        .build())
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
	
	public Object doActionPush(Integer vipId, String storeId, String alert, String title)
	{
		PushResult result = new PushResult();
		JPushClient jpushClient = new JPushClient(
				MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
		
		PushPayload payload = getPushInfo(vipId, storeId, alert, title);
		log.debug(new Gson().toJson(payload));
		try
		{
			result = jpushClient.sendPush(payload);
			log.debug("Got result - " + result);
		} 
		catch (APIConnectionException e)
		{
			log.debug("Connection error, should retry later");
			return e;
		} 
		catch (APIRequestException e)
		{
			log.debug("Should review the error, and fix the request");
			log.debug("HTTP Status: " + e.getStatus());
			log.debug("Error Code: " + e.getErrorCode());
			log.debug("Error Message: " + e.getErrorMessage());
			return e;
		}
		
		return result;
	}

	private PushPayload getBottlePushInfo(String alert, String title, 
			String msg, String surplus,String picUrl,String tag) 
	{
		Map<String,String> extra = new HashMap<>();
		extra.put("msg", msg);
		extra.put("surplus", surplus);
		extra.put("picUrl", picUrl);
		extra.put("tag", tag);
		
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.newBuilder()
                .setAlert(alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
    	                        .setBuilderId(1)
    	                        .setTitle(title)
                                .addExtras(extra)
    	                        .build())
                        .build())
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }

	public Object doBottleActionPush(String alert, String title, 
			String msg, String surplus,String picUrl,String tag)
	{
		PushResult result = new PushResult();
		JPushClient jpushClient = new JPushClient(
				"f28ce57b1f281b7415344e54", "95597b702ffcf6b12c843a9d", null, ClientConfig.getInstance());
		
		PushPayload payload = getBottlePushInfo(alert, title, msg, surplus, picUrl, tag);
		try
		{
			result = jpushClient.sendPush(payload);
			log.debug("Got result - " + result);
		} 
		catch (APIConnectionException e)
		{
			log.debug("Connection error, should retry later");
			return e;
		} 
		catch (APIRequestException e)
		{
			log.debug("Should review the error, and fix the request");
			log.debug("HTTP Status: " + e.getStatus());
			log.debug("Error Code: " + e.getErrorCode());
			log.debug("Error Message: " + e.getErrorMessage());
			return e;
		}
		return result;
	}
}
