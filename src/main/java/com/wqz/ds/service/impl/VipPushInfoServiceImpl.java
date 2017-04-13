package com.wqz.ds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.dao.VipPushMsgMapper;
import com.wqz.ds.pojo.VipPushMsg;
import com.wqz.ds.service.VipPushInfoService;

@Service
public class VipPushInfoServiceImpl implements VipPushInfoService
{
	@Autowired
	VipPushMsgMapper vipPushMsgMapper;
	
	@Override
	public List<VipPushMsg> selectByVipId(Integer vipId)
	{
		return vipPushMsgMapper.selectByVipId(vipId);
	}
	
	@Override
	public Boolean insert(VipPushMsg vipPushMsg)
	{
		return vipPushMsgMapper.insertSelective(vipPushMsg) == 1;
	}
}
