package com.wqz.ds.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.ClerkFace;

@MapperScan
public interface ClerkFaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClerkFace record);

    int insertSelective(ClerkFace record);

    ClerkFace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClerkFace record);

    int updateByPrimaryKey(ClerkFace record);
    
    List<ClerkFace> selectByStoreId(Integer storeId);
}