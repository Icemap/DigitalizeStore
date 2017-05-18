package com.wqz.ds.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileUtils
{
	public static Integer readTideInfo(String fileName,String datetime)
	{
		File file = new File(fileName);
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null)
			{
				String[] tempArray = tempString.split(",");
				if(tempArray[2].trim().equals("tide" + datetime + ".nc"))
					return Integer.parseInt(tempArray[0]);
			}
			reader.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} 
				catch (IOException e1)
				{
				}
			}
		}
		
		return null;
	}
	
	public static String readFileByLines(String fileName)
	{
		StringBuilder sb = new StringBuilder();
		File file = new File(fileName);
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null)
			{
				sb.append(tempString);
			}
			reader.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} 
				catch (IOException e1)
				{
				}
			}
		}
		
		return sb.toString();
	}
	
	public static String readResourcesByLines(String path)
	{
		Resource res = new ClassPathResource(path);
		
		try
		{
			return readFileByUtf8(res.getFile().getAbsolutePath());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String readFileByUtf8(String path) throws IOException
	{
		StringBuffer sb = new StringBuffer();
		FileInputStream fis = new FileInputStream(path);   
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");   
		BufferedReader br = new BufferedReader(isr);   
		String line = null;   
		while ((line = br.readLine()) != null) 
		{   
			sb.append(line);
		} 
		  
		return sb.toString();
	}
	
	public static String savePic(InputStream inputStream, String fileName,String path) 
	{
		String savePath = "";
		
        OutputStream os = null;
        try {
            byte[] bs = new byte[1024];
            int len;

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            
            os = new FileOutputStream(path + "/" + fileName);
            // 寮�濮嬭鍙�
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 瀹屾瘯锛屽叧闂墍鏈夐摼鎺�
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return savePath;
    }
}
