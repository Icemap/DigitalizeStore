package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.pojo.VipPushMsg;

public interface VipPushInfoService
{
	List<VipPushMsg> selectByVipId(Integer vipId);
	Boolean insert(VipPushMsg vipPushMsg);
}
