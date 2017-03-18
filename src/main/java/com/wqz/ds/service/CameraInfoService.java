package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.pojo.CameraInfo;

public interface CameraInfoService
{
	boolean createCamera(CameraInfo info);
	boolean deleteCamera(Integer cameraId);
	boolean updateCamera(CameraInfo info);
	
	CameraInfo getCameraInfoById(Integer cameraId);
	List<CameraInfo> getCameraInfoByStoreId(Integer storeId);
	List<CameraInfo> getCameraInfoByBusinessUnitId(Integer businessUnitId);
	List<CameraInfo> getCameraInfoByBrandId(Integer brandId);
}
