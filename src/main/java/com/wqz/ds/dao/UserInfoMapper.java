package com.wqz.ds.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.wqz.ds.pojo.UserInfo;

@MapperScan
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo login(
    		@Param(value="username")String username ,
    		@Param(value="password")String password);
    
	Integer userGetUsernameCount(String username);
}