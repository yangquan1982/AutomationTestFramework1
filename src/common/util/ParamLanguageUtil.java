package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.huawei.webtest.restful.util.Log;

import common.constant.constparameter.ConstUrl;
import common.constant.system.SystemConstant;

public class ParamLanguageUtil {

	static String propertyFilePath = SystemConstant.getProjectHomePath()+"\\setting\\dataParam\\";
	static Properties properties = new Properties();

	static
	{
		FileInputStream fi = null;
		try 
		{
			if("CN".equalsIgnoreCase(ConstUrl.language))
			{
				propertyFilePath = propertyFilePath+"Element_ZH.properties";
			}else
			{
				propertyFilePath = propertyFilePath+"Element_EN.properties";
			}
			File f = new File(propertyFilePath);
			fi = new FileInputStream(f);
			properties.load(fi);
		} catch (IOException  e) 
		{
			Log.info("加载配置文件时异常");
			e.printStackTrace();
		}finally
		{
			if (fi!=null) 
			{
				try 
				{
					fi.close();
				} catch (IOException e) 
				{
					Log.info("error");
				}
			}
		}
	}
	
	
	/**
	 * 依据中英文环境，获取配置文件中的参数值
	 * @param paramStr
	 * @return
	 */
	public static String getParamValue(String paramStr)
	{
		String value = "";
		if(!"".equals(paramStr))
		{
			value = properties.getProperty(paramStr);
			if(value==null)
			{
				return paramStr;
			}else
			{
				return value;
			}
		}
		return paramStr;
	}
	
}
