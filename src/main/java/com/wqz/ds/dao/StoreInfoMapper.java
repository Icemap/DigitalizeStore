package com.wqz.ds.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.StoreInfo;

@MapperScan
public interface StoreInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
    
    List<StoreInfo> selectByBrandId(Integer brandId);
    List<StoreInfo> selectByBusinessUnitId(Integer businessUnitId);
    
    String getStoreName(Integer storeId);
}