package com.wqz.ds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.dao.BrandInfoMapper;
import com.wqz.ds.pojo.BrandInfo;
import com.wqz.ds.service.BrandInfoService;

@Service
public class BrandInfoServiceImpl implements BrandInfoService
{
	@Autowired
	BrandInfoMapper brandInfoMapper;

	@Override
	public boolean createBrand(BrandInfo info)
	{
		return brandInfoMapper.insert(info) == 1;
	}
	
	@Override
	public boolean deleteBrand(Integer brandId)
	{
		return brandInfoMapper.deleteByPrimaryKey(brandId) == 1; 
	}

	@Override
	public boolean updateBrand(BrandInfo info)
	{
		return brandInfoMapper.updateByPrimaryKeySelective(info) == 1;
	}

	@Override
	public BrandInfo selectBrand(Integer brandId)
	{
		return brandInfoMapper.selectByPrimaryKey(brandId);
	}

	@Override
	public BrandInfo selectBrandByUserId(Integer userId)
	{
		return brandInfoMapper.selectByUserId(userId);
	}
}
