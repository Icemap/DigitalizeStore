package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.pojo.StoreInfo;

public interface StoreInfoService
{
	boolean createStoreInfo(StoreInfo info);
	boolean deleteStoreInfo(Integer storeId);
	boolean updateStoreInfo(StoreInfo info);
	
	StoreInfo getInfoById(Integer storeId);
	List<StoreInfo> getInfoByBrandId(Integer brandId);
	List<StoreInfo> getInfoByBusinessUnitId(Integer businessUnitId);
}
