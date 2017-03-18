package com.wqz.ds.utils;

public class ByteBooleanUtils
{
	public static Byte trueByte = 0;
	public static Byte falseByte = 1;
	public static Boolean Boolean0 = true;
	public static Boolean Boolean1 = false;
	
	public static Boolean byte2Boolean(Byte b)
	{
		return b == 0 ? Boolean0 : Boolean1;
	}
	
	public static Byte boolean2Byte(Boolean b)
	{
		return b == true ? trueByte : falseByte;
	}
}
