package com.wqz.ds.dao;

import com.wqz.ds.pojo.BusinessUnitInfo;

public interface BusinessUnitInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessUnitInfo record);

    int insertSelective(BusinessUnitInfo record);

    BusinessUnitInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessUnitInfo record);

    int updateByPrimaryKey(BusinessUnitInfo record);
}