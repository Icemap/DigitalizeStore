package com.wqz.ds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.CameraPushMsg;

@MapperScan
public interface CameraPushMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CameraPushMsg record);

    int insertSelective(CameraPushMsg record);

    CameraPushMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CameraPushMsg record);

    int updateByPrimaryKey(CameraPushMsg record);
    
    List<CameraPushMsg> getCameraPushByBrandId(
    		@Param(value="brandId")Integer brandId,
    		@Param(value="startTime")String startTime,
    		@Param(value="endTime")String endTime);

    List<CameraPushMsg> getCameraPushByBusinessUnitId(
    		@Param(value="businessUnitId")Integer businessUnitId,
    		@Param(value="startTime")String startTime,
    		@Param(value="endTime")String endTime);
    
    List<CameraPushMsg> getCameraPushByStoreId(
    		@Param(value="storeId")Integer storeId,
    		@Param(value="startTime")String startTime,
    		@Param(value="endTime")String endTime);
}