package com.wqz.ds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.dao.BusinessUnitInfoMapper;
import com.wqz.ds.pojo.BusinessUnitInfo;
import com.wqz.ds.service.BusinessUnitInfoService;

@Service
public class BusinessUnitInfoServiceImpl implements BusinessUnitInfoService
{
	@Autowired
	BusinessUnitInfoMapper businessUnitInfoMapper;
	
	@Override
	public boolean createBusinessUnit(BusinessUnitInfo info)
	{
		return businessUnitInfoMapper.insertSelective(info) == 1;
	}

	@Override
	public boolean deleteBusinessUnit(Integer businessUnitId)
	{
		return businessUnitInfoMapper.deleteByPrimaryKey(businessUnitId) == 1;
	}

	@Override
	public boolean updateBusinessUnit(BusinessUnitInfo info)
	{
		return businessUnitInfoMapper.updateByPrimaryKeySelective(info) == 1;
	}

	@Override
	public BusinessUnitInfo selectByUserId(Integer userId)
	{
		return businessUnitInfoMapper.selectByUserId(userId);
	}

	@Override
	public BusinessUnitInfo selectById(Integer id)
	{
		return businessUnitInfoMapper.selectByPrimaryKey(id);
	}
}
