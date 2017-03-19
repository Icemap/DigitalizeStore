package com.wqz.ds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wqz.ds.dao.StoreInfoMapper;
import com.wqz.ds.pojo.StoreInfo;
import com.wqz.ds.service.StoreInfoService;

public class StoreInfoServiceImpl implements StoreInfoService
{
	@Autowired
	StoreInfoMapper storeInfoMapper;
	
	@Override
	public boolean createStoreInfo(StoreInfo info)
	{
		return storeInfoMapper.insertSelective(info) == 1;
	}

	@Override
	public boolean deleteStoreInfo(Integer storeId)
	{
		return storeInfoMapper.deleteByPrimaryKey(storeId) == 1;
	}

	@Override
	public boolean updateStoreInfo(StoreInfo info)
	{
		return storeInfoMapper.updateByPrimaryKeySelective(info) == 1;
	}

	@Override
	public StoreInfo getInfoById(Integer storeId)
	{
		return storeInfoMapper.selectByPrimaryKey(storeId);
	}

	@Override
	public List<StoreInfo> getInfoByBrandId(Integer brandId)
	{
		return storeInfoMapper.selectByBrandId(brandId);
	}

	@Override
	public List<StoreInfo> getInfoByBusinessUnitId(Integer businessUnitId)
	{
		return storeInfoMapper.selectByBusinessUnitId(businessUnitId);
	}
}
