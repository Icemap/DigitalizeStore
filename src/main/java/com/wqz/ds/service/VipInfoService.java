package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.pojo.VipInfo;

public interface VipInfoService
{
	boolean createVipInfo(VipInfo info);
	boolean deleteVipInfo(Integer vipId);
	boolean updateVipInfo(VipInfo info);
	
	List<VipInfo> selectVipInfoByName(String name);
	List<VipInfo> selectVipInfoByBrandId(Integer brandId);
	VipInfo selectVipInfoByPhone(String phone);
	List<VipInfo> selectVipInfoByOtherLabel(String otherLabel);
	VipInfo selectVipInfoById(Integer id);
}
