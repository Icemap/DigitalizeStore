package com.wqz.ds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.dao.AllFaceMapper;
import com.wqz.ds.pojo.AllFace;
import com.wqz.ds.service.AllFaceService;

@Service
public class AllFaceServiceImpl implements AllFaceService
{
	@Autowired
	AllFaceMapper allFaceMapper;
	
	@Override
	public Boolean insert(AllFace allFace)
	{
		return allFaceMapper.insertSelective(allFace) == 1;
	}

	@Override
	public Boolean delete(Integer id)
	{
		return allFaceMapper.deleteByPrimaryKey(id) == 1;
	}

	@Override
	public List<AllFace> select(Integer start, Integer size)
	{
		return allFaceMapper.selectPage(start, size);
	}
}
