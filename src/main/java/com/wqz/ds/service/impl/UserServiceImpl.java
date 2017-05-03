package com.wqz.ds.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.bean.StoreDataBean;
import com.wqz.ds.dao.CameraPushMsgMapper;
import com.wqz.ds.dao.StoreBillsPushMsgMapper;
import com.wqz.ds.dao.StoreInfoMapper;
import com.wqz.ds.dao.UserInfoMapper;
import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.pojo.StoreBillsPushMsg;
import com.wqz.ds.pojo.UserInfo;
import com.wqz.ds.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserInfoMapper userInfoMapper;
	
	@Autowired
	StoreBillsPushMsgMapper storeBillsPushMsgMapper;
	
	@Autowired
	CameraPushMsgMapper cameraPushMsgMapper;
	
	@Autowired
	StoreInfoMapper storeInfoMapper;
	
	@Override
	public UserInfo userLogin(String username, String password)
	{
		return userInfoMapper.login(username, password);
	}
	
	@Override
	public boolean userRegister(UserInfo userInfo)
	{
		return userInfoMapper.insertSelective(userInfo) == 1;
	}
	
	@Override
	public List<StoreDataBean> getStoreMsg(Integer userId, String startTime, String endTime)
	{
		List<StoreBillsPushMsg> billsList = null;
		List<CameraPushMsg> camerasList = null;
		UserInfo info = userInfoMapper.selectByPrimaryKey(userId);
		switch (info.getLevel())
		{
		case 1:
			billsList = storeBillsPushMsgMapper.getBillsByBrandId(info.getBrandId(), startTime, endTime);
			camerasList = cameraPushMsgMapper.getCameraPushByBrandId(info.getBrandId(), startTime, endTime);
			break;
		case 2:
			billsList = storeBillsPushMsgMapper.getBillsByBusinessUnitId(info.getBusinessUnitId(), startTime, endTime);
			camerasList = cameraPushMsgMapper.getCameraPushByBusinessUnitId(info.getBusinessUnitId(), startTime, endTime);
			break;
		case 3:
			billsList = storeBillsPushMsgMapper.getBillsByStoreId(info.getStoreId(), startTime, endTime);
			camerasList = cameraPushMsgMapper.getCameraPushByStoreId(info.getStoreId(), startTime, endTime);
			break;
		}
		
		
		Map<Integer, List<StoreBillsPushMsg>> billsMap = new HashMap<>();
		Map<Integer, List<CameraPushMsg>> camerasMap = new HashMap<>();
		List<Integer> storeIdList = new ArrayList<Integer>();
		Map<Integer, String> storeNameMap = new HashMap<>();
		
		for(StoreBillsPushMsg bill : billsList)
		{
			if(billsMap.containsKey(bill.getStoreId()))
			{
				billsMap.get(bill.getStoreId()).add(bill);
			}
			else
			{
				List<StoreBillsPushMsg> bills = new ArrayList<>();
				bills.add(bill);
				billsMap.put(bill.getStoreId(), bills);
				storeIdList.add(bill.getStoreId());
				storeNameMap.put(bill.getStoreId(),storeInfoMapper.getStoreName(bill.getStoreId()));
			}
		}
		
		Boolean cameraToCountStoreId = storeIdList.isEmpty() ? true : false;
		
		for(CameraPushMsg camera : camerasList)
		{
			if(camerasMap.containsKey(camera.getStoreId()))
			{
				camerasMap.get(camera.getStoreId()).add(camera);
			}
			else
			{
				List<CameraPushMsg> cameras = new ArrayList<>();
				cameras.add(camera);
				camerasMap.put(camera.getStoreId(), cameras);
				if(cameraToCountStoreId) 
				{
					storeIdList.add(camera.getStoreId());
					storeNameMap.put(camera.getStoreId(),storeInfoMapper.getStoreName(camera.getStoreId()));
				}
			}
		}
		
		List<StoreDataBean> storeList = new ArrayList<>();
		for(Integer storeId : storeIdList)
		{
			List<StoreBillsPushMsg> billsMsg = new ArrayList<>();
			List<CameraPushMsg> cameraMsg = new ArrayList<>();
			
			if(!storeNameMap.containsKey(storeId))continue;
			if(billsMap.containsKey(storeId)) billsMsg = billsMap.get(storeId);
			if(camerasMap.containsKey(storeId)) cameraMsg = camerasMap.get(storeId);
			
			storeList.add(new StoreDataBean(storeNameMap.get(storeId), billsMsg, cameraMsg));
		}
		
		return storeList;
	}

	@Override
	public boolean userUpdate(UserInfo userInfo)
	{
		return userInfoMapper.updateByPrimaryKey(userInfo) == 1;
	}

	@Override
	public Integer getUsernameCount(String username)
	{
		return userInfoMapper.userGetUsernameCount(username);
	}
}
