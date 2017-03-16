package com.wqz.ds.dao;

import com.wqz.ds.pojo.VipInfo;

public interface VipInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipInfo record);

    int insertSelective(VipInfo record);

    VipInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipInfo record);

    int updateByPrimaryKey(VipInfo record);
}