package com.wqz.ds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.AllFace;

@MapperScan
public interface AllFaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AllFace record);

    int insertSelective(AllFace record);

    AllFace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AllFace record);

    int updateByPrimaryKey(AllFace record);
    
    List<AllFace> selectPage(
    		@Param(value="start")Integer start,
    		@Param(value="size")Integer size);
}