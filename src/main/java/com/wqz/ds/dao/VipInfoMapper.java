package com.wqz.ds.dao;

import java.util.List;

import com.wqz.ds.pojo.VipInfo;

public interface VipInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipInfo record);

    int insertSelective(VipInfo record);

    VipInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipInfo record);

    int updateByPrimaryKey(VipInfo record);
    
    List<VipInfo> selectByBrandId(Integer brandId);
    List<VipInfo> selectByName(String name);
    VipInfo selectVipInfoByPhone(String phone);
    List<VipInfo> selectVipInfoByOtherLabel(String otherLabel);
}