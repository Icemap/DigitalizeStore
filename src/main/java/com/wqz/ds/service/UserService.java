package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.bean.StoreDataBean;
import com.wqz.ds.pojo.UserInfo;

public interface UserService
{
	UserInfo userLogin(String username, String password);
	boolean userRegister(UserInfo userInfo);
	boolean userUpdate(UserInfo userInfo);
	
	List<StoreDataBean> getStoreMsg(Integer userId, String startTime,String endTime);//得到单数
}
