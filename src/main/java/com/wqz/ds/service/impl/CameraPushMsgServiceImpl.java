package com.wqz.ds.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wqz.ds.bean.CameraPushMsgEx;
import com.wqz.ds.dao.CameraPushMsgMapper;
import com.wqz.ds.pojo.AllFace;
import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.service.CameraPushMsgService;

@Service
public class CameraPushMsgServiceImpl implements CameraPushMsgService
{
	@Autowired
	CameraPushMsgMapper cameraPushMsgMapper;
	
	@Autowired
	AllFaceServiceImpl allFaceServiceImpl;
	
	@Override
	public Boolean pushMsg(CameraPushMsg msg)
	{
		return cameraPushMsgMapper.insertSelective(msg) == 1;
	}

	@Override
	public List<CameraPushMsgEx> getMsgByStoreId(Integer storeId,Integer start,Integer size)
	{
		List<CameraPushMsg> msgList = cameraPushMsgMapper.getMsgByStoreId(storeId, start, size);
		List<CameraPushMsgEx> msgexList = new ArrayList<CameraPushMsgEx>();
		for(CameraPushMsg msg : msgList)
		{
			CameraPushMsgEx msgEx = new CameraPushMsgEx();
			msgEx.setAge(msg.getAge());
			msgEx.setCameraId(msg.getCameraId());
			msgEx.setDatetime(msg.getDatetime());
			msgEx.setId(msg.getId());
			msgEx.setIsAdd(msg.getIsAdd());
			msgEx.setIsEnterStore(msg.getIsEnterStore());
			msgEx.setIsMale(msg.getIsMale());
			msgEx.setStoreId(msg.getStoreId());
			
			if(!msg.getHold().isEmpty())
			{
				AllFace face = allFaceServiceImpl.selectById(Integer.parseInt(msg.getHold()));
				msgEx.picUrl = face.getPicurl();
			}
			
			msgexList.add(msgEx);
		}
		return msgexList;
	}
}
