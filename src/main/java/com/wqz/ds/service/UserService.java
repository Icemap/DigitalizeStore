package com.wqz.ds.service;

import com.wqz.ds.pojo.UserInfo;

public interface UserService
{
	Object userLogin(String username, String password);
	boolean userRegister(UserInfo userInfo);
	boolean userUpdate(UserInfo userInfo);
	
	Object getStoreMsg(Integer userId, String startTime,String endTime);//得到单数
}
