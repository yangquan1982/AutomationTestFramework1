package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;

import common.constant.constparameter.ConstUrl;
import common.constant.system.SystemConstant;

public class LanguageUtil {

	public static String File_BaseConfig = SystemConstant.getProjectHomePath() + "\\setting\\resources\\";
	static Properties properties = new Properties();

	static {

		String pathString = File_BaseConfig + "resources.properties";
		File file = new File(pathString);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			properties.load(inputStream);
		} catch (Exception e) {
			System.out.println("error");
		}
		finally{
			if (inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.out.println("error");
				}
			}
		}
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String translate(String key) {
		if("CN".equalsIgnoreCase(ConstUrl.language)){
			String temp_Key = key.trim().replaceAll(" ", "_");
			String result = properties.getProperty(temp_Key);
			if (result == null) {
				return key;
			}else{
				return result;
			}
		}else{
			return key;
		}
	}
	/**
	 * 
	 * @param resourcesFile
	 * @param key
	 * @return
	 */
	public static String translate(String resourcesFile ,String key) {
		Properties properties_temp = LanguageUtil.Propert(resourcesFile);
		if("CN".equalsIgnoreCase(ConstUrl.language)){
			String temp_Key = key.trim().replaceAll(" ", "_");
			String result = properties_temp.getProperty(temp_Key);
			if (result == null) {
				return key;
			}else{
				return result;
			}
		}else{
			return key;
		}
	}
	
	private static Properties Propert(String resourcesFile) {
		Properties properties_temp = new Properties();
		String pathString = File_BaseConfig + resourcesFile + ".properties";
		File file = new File(pathString);
		FileInputStream inputStream = null;
		if(file.isFile() == false){
			Assert.fail("语言资源文件未找到:"+pathString);
		}
		try {
			inputStream = new FileInputStream(file);
			properties_temp.load(inputStream);
		} catch (Exception e) {
			System.out.println("error");
		}
		finally{
			if (inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.out.println("error");
				}
			}
		}
		return properties_temp;
	}
	
	
	/**
	 * 设置传入参数的中英文
	 * @param param
	 */
	public static void getParamLanguage(String paramStr)
	{
		if("CN".equalsIgnoreCase(ConstUrl.language))
		{
			
		}else{
			
		}
	}
	
}
