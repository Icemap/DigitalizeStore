package com.wqz.ds.thrift.core;

import java.io.ByteArrayInputStream;
import java.util.Date;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.wqz.ds.bean.PathBean;
import com.wqz.ds.pojo.AllFace;
import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.service.impl.AllFaceServiceImpl;
import com.wqz.ds.service.impl.CameraPushMsgServiceImpl;
import com.wqz.ds.service.impl.JpushService;
import com.wqz.ds.utils.ByteBooleanUtils;
import com.wqz.ds.utils.FileUtils;

@Service
public class PostFaceImpl implements PostFace.Iface
{
	@Autowired
	JpushService jpushService;
	@Autowired
	CameraPushMsgServiceImpl cameraPushMsgServiceImpl;
	@Autowired
	AllFaceServiceImpl allFaceServiceImpl;
	
	@Override
	public String clientFindAFace(FaceInfo faceInfo, ClientInfo clientInfo) throws TException
	{
		String result = "Successful Request : ";
		
		//Insert CulData
		CameraPushMsg msg = new CameraPushMsg();
		msg.setStoreId(clientInfo.storeid);
		msg.setCameraId(clientInfo.cameraid);
		msg.setAge(faceInfo.age);
		msg.setDatetime(new Date(faceInfo.timestamp));
		msg.setFaceQuality(faceInfo.quality);
		if(faceInfo.have_seen) msg.setHold(faceInfo.fid + "");
		msg.setIsAdd(ByteBooleanUtils.boolean2Byte(faceInfo.is_enter_action));
		msg.setIsEnterStore(ByteBooleanUtils.boolean2Byte(faceInfo.is_enter_store));
		msg.setIsHaveSeen(ByteBooleanUtils.boolean2Byte(faceInfo.have_seen));
		msg.setIsMale(ByteBooleanUtils.boolean2Byte(faceInfo.gender == 0));
		msg.setIsVip(ByteBooleanUtils.boolean2Byte(faceInfo.is_vip));
		cameraPushMsgServiceImpl.pushMsg(msg);
		
		if(faceInfo.have_seen)
		{
			//Push
			jpushService.doActionPush(faceInfo.fid, clientInfo.storeid + "", 
					"找到相似人脸", "机器觉得这个人好像见过", "face");
		}
		else
		{
			//Add to all_face
			//Param
			PathBean pathBean = new Gson().fromJson(FileUtils.
					readResourcesByLines("path.json"), PathBean.class);
			String allFacePath = pathBean.allSavePath;
			String allGetPath = pathBean.allGetPath;
			String filename = faceInfo.timestamp + ".jpg";
			
			FileUtils.savePic(new ByteArrayInputStream(faceInfo.image.array()) , filename, allFacePath);
			AllFace allFace = new AllFace();
			allFace.setCameraid(clientInfo.cameraid);
			allFace.setDatetime(new Date(faceInfo.timestamp));
			allFace.setPicurl(allGetPath + filename);
			allFace.age = faceInfo.age;
			allFace.isMale = ByteBooleanUtils.boolean2Byte(faceInfo.gender == 0);
			result += allFaceServiceImpl.insert(allFace);
		}
		return result;
	}
}
