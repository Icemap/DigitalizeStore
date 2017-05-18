package com.wqz.ds.bean;

import java.util.List;

import com.google.gson.Gson;
import com.wqz.ds.pojo.CameraPushMsg;
import com.wqz.ds.pojo.StoreBillsPushMsg;
import com.wqz.ds.utils.ByteBooleanUtils;
import com.wqz.ds.utils.FileUtils;

public class StoreDataBean
{
	public String storeName = "";
	public Float hasMsgManCount = 0f;
	
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
	public int haveSeenCount = 0;
	public Float haveSeenRate = 0.0f;
	public int vipCount = 0;
	public Float vipRate = 0.0f;
	public int[] customerTotalTime = new int[13];//9-21
	public int[] moneyTotalTime = new int[13];//9-21
	public int[] dealsCountTotalTime = new int[13];//9-21
	public int[] notInStoreTotalTime = new int[13];//9-21
	public Float[] interStoreRateTotalTime = new Float[13];//9-21
	public Float[] bringBagRateTotalTime = new Float[13];//9-21
	
	/*60以上  51-60岁  41-50岁  31-40岁   21-30岁   20以下*/
	public int[] manAgeArray = new int[6];
	public int[] womanAgeArray = new int[6];
	
	public StoreDataBean(String storeName,List<StoreBillsPushMsg> billsList,List<CameraPushMsg> camerasList)
	{
		String extraConfJson = FileUtils.readResourcesByLines("extra_conf.json");
		ExtraConfBean ex = new Gson().fromJson(extraConfJson,
				ExtraConfBean.class);
		Float threshold = ex.faceQualityEffectThreshold;
		this.storeName = storeName;
		
		for(CameraPushMsg cameraInfo : camerasList)
		{
			if(cameraInfo.getDatetime().getHours() - 9 < 0 || 
	                  cameraInfo.getDatetime().getHours() - 9 >= customerTotalTime.length ||
	                  (cameraInfo.getFaceQuality() == null ? 0 : cameraInfo.getFaceQuality()) < threshold) continue;
			
			if(cameraInfo.getIsEnterStore().equals(ByteBooleanUtils.falseByte))
			{
				notInStoreTotalTime[cameraInfo.getDatetime().getHours() - 9] ++;
				continue;
			}
			
			if(cameraInfo.getIsAdd().equals(ByteBooleanUtils.falseByte)) continue;
			
			customerTotalTime[cameraInfo.getDatetime().getHours() - 9] ++;//24小时
			if(cameraInfo.getIsHaveSeen() != null 
					&& cameraInfo.getIsHaveSeen().equals(ByteBooleanUtils.trueByte))
				haveSeenCount ++;
			if(cameraInfo.getIsVip() != null 
					&& cameraInfo.getIsVip().equals(ByteBooleanUtils.trueByte))
				vipCount ++;
			
			if(cameraInfo.getIsMale().equals(ByteBooleanUtils.trueByte))
			{
				if(cameraInfo.getAge() > 0 && cameraInfo.getAge() <= 20)
					manAgeArray[0] ++;
				else if(cameraInfo.getAge() > 20 && cameraInfo.getAge() <= 30)
					manAgeArray[1] ++;
				else if(cameraInfo.getAge() > 30 && cameraInfo.getAge() <= 40)
					manAgeArray[2] ++;
				else if(cameraInfo.getAge() > 40 && cameraInfo.getAge() <= 50)
					manAgeArray[3] ++;
				else if(cameraInfo.getAge() > 50 && cameraInfo.getAge() <= 60)
					manAgeArray[4] ++;
				else if(cameraInfo.getAge() > 60)
					manAgeArray[5] ++;
			}
			else//女
			{
				if(cameraInfo.getAge() > 0 && cameraInfo.getAge() <= 20)
					womanAgeArray[0] ++;
				else if(cameraInfo.getAge() > 20 && cameraInfo.getAge() <= 30)
					womanAgeArray[1] ++;
				else if(cameraInfo.getAge() > 30 && cameraInfo.getAge() <= 40)
					womanAgeArray[2] ++;
				else if(cameraInfo.getAge() > 40 && cameraInfo.getAge() <= 50)
					womanAgeArray[3] ++;
				else if(cameraInfo.getAge() > 50 && cameraInfo.getAge() <= 60)
					womanAgeArray[4] ++;
				else if(cameraInfo.getAge() > 60)
					womanAgeArray[5] ++;
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
		perBillCost = payManCount == 0 ? 0 : storeSalesMoney / payManCount;
		storeManAndWomanRate = storeAllCount == 0 ? "0:0" : ((int)(storeManCount * 100.0)) / storeAllCount 
				+ ":" + ((int)(storeWomanCount * 100.0)) / storeAllCount;
		
		bringBagRate = storeAllCount == 0 ? 0.0f : payManCount / (storeAllCount * 1.0f);
		interStoreRate = camerasList.isEmpty() ? 0.0f : (camerasList.size() - notInStoreCount) / (camerasList.size() * 1.0f);
		vipRate = storeAllCount == 0 ? 0.0f : vipCount / (storeAllCount * 1.0f);
		haveSeenRate = storeAllCount == 0 ? 0.0f : haveSeenCount / (storeAllCount * 1.0f);
	}
}
