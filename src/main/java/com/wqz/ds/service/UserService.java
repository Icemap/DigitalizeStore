package com.wqz.ds.service;

import com.wqz.ds.pojo.UserInfo;

public interface UserService
{
	Object userLogin(String username, String password);
	Object userRegister(UserInfo userInfo);
	
	Object getStoreMsg(String startTime,String endTime);
}
