package com.wqz.ds.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.VipInfo;

@MapperScan
public interface VipInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipInfo record);

    int insertSelective(VipInfo record);

    VipInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipInfo record);

    int updateByPrimaryKey(VipInfo record);
}