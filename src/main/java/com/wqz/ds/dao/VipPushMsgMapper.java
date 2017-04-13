package com.wqz.ds.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.VipPushMsg;

@MapperScan
public interface VipPushMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipPushMsg record);

    int insertSelective(VipPushMsg record);

    VipPushMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipPushMsg record);

    int updateByPrimaryKey(VipPushMsg record);
    
    List<VipPushMsg> selectByVipId(Integer vipId);
}