package com.wqz.ds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wqz.ds.dao.VipInfoMapper;
import com.wqz.ds.pojo.VipInfo;
import com.wqz.ds.service.VipInfoService;

public class VipInfoServiceImpl implements VipInfoService
{
	@Autowired
	VipInfoMapper vipInfoMapper;
	
	@Override
	public boolean createVipInfo(VipInfo info)
	{
		return vipInfoMapper.insertSelective(info) == 1;
	}

	@Override
	public boolean deleteVipInfo(Integer vipId)
	{
		return vipInfoMapper.deleteByPrimaryKey(vipId) == 1;
	}

	@Override
	public boolean updateVipInfo(VipInfo info)
	{
		return vipInfoMapper.updateByPrimaryKeySelective(info) == 1;
	}

	@Override
	public List<VipInfo> selectVipInfoByName(String name)
	{
		return vipInfoMapper.selectByName(name);
	}

	@Override
	public List<VipInfo> selectVipInfoByBrandId(Integer brandId)
	{
		return vipInfoMapper.selectByBrandId(brandId);
	}

	@Override
	public VipInfo selectVipInfoByPhone(String phone)
	{
		return vipInfoMapper.selectVipInfoByPhone(phone);
	}

	@Override
	public List<VipInfo> selectVipInfoByOtherLabel(String otherLabel)
	{
		return vipInfoMapper.selectVipInfoByOtherLabel(otherLabel);
	}

	@Override
	public VipInfo selectVipInfoById(Integer id)
	{
		return vipInfoMapper.selectByPrimaryKey(id);
	}

}
