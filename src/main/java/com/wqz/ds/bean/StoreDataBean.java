package com.wqz.ds.bean;

import java.util.List;

import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.pojo.StoreBillsPushMsg;
import com.wqz.ds.utils.ByteBooleanUtils;

public class StoreDataBean
{
	public String storeName = "";
	public Float hasMsgManCount = 0f;//�ٷֱ�
	
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
	public int[] dealsCountTotalTime = new int[13];//9-21
	public int[] notInStoreTotalTime = new int[13];//9-21
	public Float[] interStoreRateTotalTime = new Float[13];//9-21
	public Float[] bringBagRateTotalTime = new Float[13];//9-21
	
	/*60������   51-60��   41-50��   31-40��   21-30��   20������*/
	public int[] manAgeArray = new int[6];
	public int[] womanAgeArray = new int[6];
	
	public StoreDataBean(String storeName,List<StoreBillsPushMsg> billsList,List<CameraPushMsg> camerasList)
	{
		this.storeName = storeName;
		
		for(CameraPushMsg cameraInfo : camerasList)
		{
			if(cameraInfo.getDatetime().getHours() - 9 < 0 || 
	                  cameraInfo.getDatetime().getHours() - 9 >= customerTotalTime.length) continue;
			
			if(cameraInfo.getIsEnterStore().equals(ByteBooleanUtils.falseByte))
			{
				notInStoreTotalTime[cameraInfo.getDatetime().getHours() - 9] ++;
				continue;
			}
			
			if(cameraInfo.getIsAdd().equals(ByteBooleanUtils.falseByte)) continue;
			
			customerTotalTime[cameraInfo.getDatetime().getHours() - 9] ++;//24Сʱ����ͳ��
			
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
				dealsCountTotalTime[bill.getDatetime().getHours() - 9] ++;
			}
		}
		
		for(int i = 0;i < manAgeArray.length;i++)
		{
			storeManCount += manAgeArray[i];
			storeWomanCount += womanAgeArray[i];
		}
		
		for(int i = 0;i < moneyTotalTime.length;i++)//13
		{
			storeSalesMoney += moneyTotalTime[i];
			payManCount += dealsCountTotalTime[i];
			notInStoreCount += notInStoreTotalTime[i];
			if(customerTotalTime[i] == 0)
			{
				interStoreRateTotalTime[i] = 0.0f;
				bringBagRateTotalTime[i] = 0.0f;
			}
			else
			{
				interStoreRateTotalTime[i] = notInStoreTotalTime[i] / (customerTotalTime[i] + notInStoreTotalTime[i] * 1.0f);
				bringBagRateTotalTime[i] = dealsCountTotalTime[i] / (customerTotalTime[i] * 1.0f);
			}
		}
		
		storeAllCount = storeManCount + storeWomanCount;
		perBillCost = storeSalesMoney / payManCount;
		storeManAndWomanRate = ((int)(storeManCount * 100.0)) / storeAllCount 
				+ ":" + ((int)(storeWomanCount * 100.0)) / storeAllCount;
		
		bringBagRate = payManCount / (storeAllCount * 1.0f);
		interStoreRate = (camerasList.size() - notInStoreCount) / (camerasList.size() * 1.0f);
	}
}
