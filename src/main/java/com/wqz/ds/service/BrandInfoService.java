package com.wqz.ds.service;

import com.wqz.ds.pojo.BrandInfo;

public interface BrandInfoService
{
	boolean createBrand(BrandInfo info);
	boolean deleteBrand(Integer brandId);
	boolean updateBrand(BrandInfo info);
	
	BrandInfo selectBrand(Integer brandId);
	BrandInfo selectBrandByUserId(Integer userId);
}
