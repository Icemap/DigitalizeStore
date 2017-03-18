package com.wqz.ds.dao;

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
}