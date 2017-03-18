package com.wqz.ds.bean;

import java.util.List;

import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.pojo.StoreBillsPushMsg;
import com.wqz.ds.utils.ByteBooleanUtils;

public class StoreDataBean
{
	public Float hasMsgManCount = 0f;//�ٷֱ�
	
	public Integer storeAllCount = 0;
	public Integer storeManCount = 0;
	public Integer storeWomanCount = 0;
	public Integer payManCount = 0;
	public Integer storeSalesMoney = 0;
	public Integer perBillCost = 0;
	public String storeManAndWomanRate = "";
	
	public int[] customerTotalTime = new int[13];//9-21
	public int[] moneyTotalTime = new int[13];//9-21
	
	/*60������   51-60��   41-50��   31-40��   21-30��   20������*/
	public int[] manAgeArray = new int[6];
	public int[] womanAgeArray = new int[6];
	
	public StoreDataBean(List<StoreBillsPushMsg> billsList,List<CameraPushMsg> camerasList)
	{
		for(CameraPushMsg cameraInfo : camerasList)
		{
			if(cameraInfo.getIsAdd().equals(ByteBooleanUtils.falseByte)) continue;
			
			customerTotalTime[cameraInfo.getDatetime().getHours() + 9] ++;//24Сʱ����ͳ��
			
			if(cameraInfo.getIsMale().equals(ByteBooleanUtils.trueByte))//��
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
			else//Ů
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
		storeManAndWomanRate = storeManCount / storeAllCount + ":" + storeWomanCount / storeAllCount;
	}
}