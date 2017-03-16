package com.wqz.ds.dao;

import com.wqz.ds.pojo.CameraPushMsg;

public interface CameraPushMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CameraPushMsg record);

    int insertSelective(CameraPushMsg record);

    CameraPushMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CameraPushMsg record);

    int updateByPrimaryKey(CameraPushMsg record);
}