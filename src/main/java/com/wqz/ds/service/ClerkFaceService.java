package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.pojo.ClerkFace;

public interface ClerkFaceService
{
	List<ClerkFace> selectByStoreId(Integer storeId);
	Boolean insertSelective(ClerkFace record);
}
