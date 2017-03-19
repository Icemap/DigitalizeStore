package com.wqz.ds.bean;

import java.util.List;

import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.pojo.StoreBillsPushMsg;
import com.wqz.ds.utils.ByteBooleanUtils;

public class StoreDataBean
{
	public Float hasMsgManCount = 0f;//百分比
	
	public Integer storeAllCount = 0;
	public Integer storeManCount = 0;
	public Integer storeWomanCount = 0;
	public Integer payManCount = 0;
	public Integer storeSalesMoney = 0;
	public Integer perBillCost = 0;
	public String storeManAndWomanRate = "";
	public Float bringBagRate = 0.0f;
	public Float interStoreRate = 0.0f;
	public Integer notInStoreCount = 0;
	public int[] customerTotalTime = new int[13];//9-21
	public int[] moneyTotalTime = new int[13];//9-21
	
	/*60岁以上   51-60岁   41-50岁   31-40岁   21-30岁   20岁以下*/
	public int[] manAgeArray = new int[6];
	public int[] womanAgeArray = new int[6];
	
	public StoreDataBean(List<StoreBillsPushMsg> billsList,List<CameraPushMsg> camerasList)
	{
		for(CameraPushMsg cameraInfo : camerasList)
		{
			if(cameraInfo.getIsEnterStore().equals(ByteBooleanUtils.falseByte))
			{
				notInStoreCount ++;
				continue;
			}
			
			if(cameraInfo.getIsAdd().equals(ByteBooleanUtils.falseByte) || 
					cameraInfo.getDatetime().getHours() - 9 < 0 || 
	                  cameraInfo.getDatetime().getHours() - 9 >= customerTotalTime.length) continue;
			
			customerTotalTime[cameraInfo.getDatetime().getHours() - 9] ++;//24小时客流统计
			
			if(cameraInfo.getIsMale().equals(ByteBooleanUtils.trueByte))//男
			{
				switch(cameraInfo.getAge())
				{
					case 0:
						manAgeArray[0] ++;
						break;
					case 20:
						manAgeArray[1] ++;
						break;
					case 30:
						manAgeArray[2] ++;
						break;
					case 40:
						manAgeArray[3] ++;
						break;
					case 50:
						manAgeArray[4] ++;
						break;
					case 60:
						manAgeArray[5] ++;
						break;
						
				}
			}
			else//女
			{
				switch(cameraInfo.getAge())
				{
					case 0:
						womanAgeArray[0] ++;
						break;
					case 20:
						womanAgeArray[1] ++;
						break;
					case 30:
						womanAgeArray[2] ++;
						break;
					case 40:
						womanAgeArray[3] ++;
						break;
					case 50:
						womanAgeArray[4] ++;
						break;
					case 60:
						womanAgeArray[5] ++;
						break;
						
				}
			}
			
		}
		
		for(StoreBillsPushMsg bill : billsList)
		{
			if(bill.getDatetime().getHours() - 9 >= 0 &&
					bill.getDatetime().getHours() - 9 < customerTotalTime.length)
			{
				moneyTotalTime[bill.getDatetime().getHours() - 9] += 
					Math.round(Float.parseFloat(bill.getBoughtMoney()));
				payManCount ++;
			}
		}
		
		for(int i = 0;i < manAgeArray.length;i++)
		{
			storeManCount += manAgeArray[i];
			storeWomanCount += womanAgeArray[i];
		}
		
		for(int i = 0;i < moneyTotalTime.length;i++)
		{
			storeSalesMoney += moneyTotalTime[i];
		}
		
		storeAllCount = storeManCount + storeWomanCount;
		perBillCost = storeSalesMoney / payManCount;
		storeManAndWomanRate = ((int)(storeManCount * 100.0)) / storeAllCount 
				+ ":" + ((int)(storeWomanCount * 100.0)) / storeAllCount;
		
		bringBagRate = payManCount / (storeAllCount * 1.0f);
		interStoreRate = (camerasList.size() - notInStoreCount) / (camerasList.size() * 1.0f);
	}
}
