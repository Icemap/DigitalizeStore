package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.pojo.BusinessUnitInfo;

public interface BusinessUnitInfoService
{
	boolean createBusinessUnit(BusinessUnitInfo info);
	boolean deleteBusinessUnit(Integer businessUnitId);
	boolean updateBusinessUnit(BusinessUnitInfo info);
	
	BusinessUnitInfo selectByUserId(Integer userId);
	BusinessUnitInfo selectById(Integer id);
	List<BusinessUnitInfo> selectByBrandId(Integer brandId);
}
