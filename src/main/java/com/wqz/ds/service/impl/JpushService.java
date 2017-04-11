package com.wqz.ds.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.wqz.ds.dao.VipInfoMapper;
import com.wqz.ds.pojo.VipInfo;

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
	
	private static String APP_KEY = "dfd42e392d9e081d68c0cc9d";
	private static String MASTER_SECRET = "a032bdb0b1375246349c54df";
	
	private PushPayload getPushInfo(Integer vipId, String storeId, String alert, String title) 
	{
		VipInfo vipInfo = vipInfoMapper.selectByPrimaryKey(vipId);
		
		Map<String,String> extra = new HashMap<>();
		extra.put("name", vipInfo.getName());
		extra.put("age", vipInfo.getAge() + "");
		extra.put("phone", vipInfo.getPhone());
		extra.put("otherLabel", vipInfo.getOtherLabel());
		
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(storeId))
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
	
	public void doActionPush(Integer vipId, String storeId, String alert, String title)
	{
		JPushClient jpushClient = new JPushClient(
				MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

		// For push, all you need do is to build PushPayload object.
		PushPayload payload = getPushInfo(vipId, storeId, alert, title);
		System.out.println(new Gson().toJson(payload));

		try
		{
			PushResult result = jpushClient.sendPush(payload);
			System.out.println("Got result - " + result);
		} 
		catch (APIConnectionException e)
		{
			// Connection error, should retry later
			System.out.println("Connection error, should retry later");
		} 
		catch (APIRequestException e)
		{
			// Should review the error, and fix the request
			System.out.println("Should review the error, and fix the request");
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());
		}
	}
}
