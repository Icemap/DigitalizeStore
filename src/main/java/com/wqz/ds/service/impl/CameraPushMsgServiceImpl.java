package com.wqz.ds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.dao.CameraPushMsgMapper;
import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.service.CameraPushMsgService;

@Service
public class CameraPushMsgServiceImpl implements CameraPushMsgService
{
	@Autowired
	CameraPushMsgMapper cameraPushMsgMapper;
	
	@Override
	public Boolean pushMsg(CameraPushMsg msg)
	{
		return cameraPushMsgMapper.insertSelective(msg) == 1;
	}
}
