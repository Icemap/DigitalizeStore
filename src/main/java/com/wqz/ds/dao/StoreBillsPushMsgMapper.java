package com.wqz.ds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.StoreBillsPushMsg;

@MapperScan
public interface StoreBillsPushMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreBillsPushMsg record);

    int insertSelective(StoreBillsPushMsg record);

    StoreBillsPushMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreBillsPushMsg record);

    int updateByPrimaryKey(StoreBillsPushMsg record);
    
    List<StoreBillsPushMsg> getBillsByBrandId(
    		@Param(value="brandId")Integer brandId,
    		@Param(value="startTime")String startTime,
    		@Param(value="endTime")String endTime);

    List<StoreBillsPushMsg> getBillsByBusinessUnitId(
    		@Param(value="businessUnitId")Integer businessUnitId,
    		@Param(value="startTime")String startTime,
    		@Param(value="endTime")String endTime);
    
    List<StoreBillsPushMsg> getBillsByStoreId(
    		@Param(value="storeId")Integer storeId,
    		@Param(value="startTime")String startTime,
    		@Param(value="endTime")String endTime);
}
