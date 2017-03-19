package com.wqz.ds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.dao.CameraInfoMapper;
import com.wqz.ds.pojo.CameraInfo;
import com.wqz.ds.service.CameraInfoService;

@Service
public class CameraInfoServiceImpl implements CameraInfoService
{
	@Autowired
	CameraInfoMapper cameraInfoMapper;
	
	@Override
	public boolean createCamera(CameraInfo info)
	{
		return cameraInfoMapper.insertSelective(info) == 1;
	}

	@Override
	public boolean deleteCamera(Integer cameraId)
	{
		return cameraInfoMapper.deleteByPrimaryKey(cameraId) == 1;
	}

	@Override
	public boolean updateCamera(CameraInfo info)
	{
		return cameraInfoMapper.updateByPrimaryKeySelective(info) == 1;
	}

	@Override
	public CameraInfo getCameraInfoById(Integer cameraId)
	{
		return cameraInfoMapper.selectByPrimaryKey(cameraId);
	}

	@Override
	public List<CameraInfo> getCameraInfoByStoreId(Integer storeId)
	{
		return cameraInfoMapper.selectByStoreId(storeId);
	}

	@Override
	public List<CameraInfo> getCameraInfoByBusinessUnitId(Integer businessUnitId)
	{
		return cameraInfoMapper.selectByBusinessUnitId(businessUnitId);
	}

	@Override
	public List<CameraInfo> getCameraInfoByBrandId(Integer brandId)
	{
		return cameraInfoMapper.selectByBrandId(brandId);
	}
}
