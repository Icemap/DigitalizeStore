package com.wqz.ds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.dao.StoreBillsPushMsgMapper;
import com.wqz.ds.pojo.StoreBillsPushMsg;
import com.wqz.ds.service.StoreBillsPushMsgService;

@Service
public class StoreBillsPushMsgServiceImpl implements StoreBillsPushMsgService
{
	@Autowired
	StoreBillsPushMsgMapper billsPushMsgMapper;
	
	@Override
	public boolean pushMsg(StoreBillsPushMsg msg)
	{
		return billsPushMsgMapper.insertSelective(msg) == 1;
	}
}
