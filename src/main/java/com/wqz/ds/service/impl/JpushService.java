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
	
	private static String APP_KEY = "95597b702ffcf6b12c843a9d";
	private static String MASTER_SECRET = "f28ce57b1f281b7415344e54";
	
	private PushPayload getPushInfo(Integer vipId,String storeId) 
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
                .setAlert("")
                        .addPlatformNotification(AndroidNotification.newBuilder()
    	                        .setBuilderId(1)
    	                        .setTitle("店内来了一位熟客")
                                .addExtras(extra)
    	                        .build())
                        .build())
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
	
	public void doActionPush(Integer vipId,String storeId)
	{
		JPushClient jpushClient = new JPushClient(
				MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

		// For push, all you need do is to build PushPayload object.
		PushPayload payload = getPushInfo(vipId, storeId);
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
