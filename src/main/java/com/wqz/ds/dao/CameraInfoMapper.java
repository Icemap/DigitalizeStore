package com.wqz.ds.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.CameraInfo;

@MapperScan
public interface CameraInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CameraInfo record);

    int insertSelective(CameraInfo record);

    CameraInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CameraInfo record);

    int updateByPrimaryKey(CameraInfo record);
    
    List<CameraInfo> selectByStoreId(Integer storeId);

    List<CameraInfo> selectByBusinessUnitId(Integer businessUnitId);

    List<CameraInfo> selectByBrandId(Integer brandId);
}