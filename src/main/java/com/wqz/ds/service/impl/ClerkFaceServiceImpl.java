package com.wqz.ds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.dao.ClerkFaceMapper;
import com.wqz.ds.pojo.ClerkFace;
import com.wqz.ds.service.ClerkFaceService;

@Service
public class ClerkFaceServiceImpl implements ClerkFaceService
{
	@Autowired
	ClerkFaceMapper clerkFaceMapper;
	
	@Override
	public List<ClerkFace> selectByStoreId(Integer storeId)
	{
		return clerkFaceMapper.selectByStoreId(storeId);
	}

	@Override
	public Boolean insertSelective(ClerkFace record)
	{
		return clerkFaceMapper.insertSelective(record) == 1;
	}
}
