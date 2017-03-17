package com.wqz.ds.dao;

import com.wqz.ds.pojo.StoreBillsPushMsg;

public interface StoreBillsPushMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreBillsPushMsg record);

    int insertSelective(StoreBillsPushMsg record);

    StoreBillsPushMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreBillsPushMsg record);

    int updateByPrimaryKey(StoreBillsPushMsg record);
}