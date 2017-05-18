package com.wqz.ds.service;

import java.util.List;

import com.wqz.ds.bean.CameraPushMsgEx;
import com.wqz.ds.pojo.CameraPushMsg;

public interface CameraPushMsgService
{
	Boolean pushMsg(CameraPushMsg msg);
	
	List<CameraPushMsgEx> getMsgByStoreId(Integer storeId, Integer start,Integer size);
}	
