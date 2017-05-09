package com.wqz.ds.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.BrandInfo;

@MapperScan
public interface BrandInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandInfo record);

    int insertSelective(BrandInfo record);

    BrandInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandInfo record);

    int updateByPrimaryKey(BrandInfo record);
    
    BrandInfo selectByUserId(Integer userId);
    
    List<BrandInfo> selectAll();
}
