package com.wqz.ds.dao;

import com.wqz.ds.pojo.VipPushMsg;

public interface VipPushMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipPushMsg record);

    int insertSelective(VipPushMsg record);

    VipPushMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipPushMsg record);

    int updateByPrimaryKey(VipPushMsg record);
}