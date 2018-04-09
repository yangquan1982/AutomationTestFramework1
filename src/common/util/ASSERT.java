package common.util;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.util.LOG;

public class ASSERT {
	
	/***
	 * 断言页面控件中包含该文本信息
	 * @param driver 
	 * 
	 * @param message
	 * @param textToBeVerified
	 */
	public static void assertTextPresent(WebDriver driver, String message, String textToBeVerified) 
	{
		for (int i = 0; i < 3; i++) 
		{
			try 
			{
				if (null != driver.findElement(By
						.xpath("//*[contains(.,'" + textToBeVerified + "')]"))) 
				{
					LOG.info("[" + message + "]assertTextPresent: Success");
					return;
				}
			} catch (Exception e) 
			{
				Pause.pause(1000);
			}
		}
		Assert.fail("[" + message + "] The Page don't exist \""+ textToBeVerified + "\"");
	}
	
	/**
	 * 校验多个控件存在，断言判断结果。
	 * 
	 * @param message
	 *            提示信息
	 * @param elementArray
	 *            元素集合，以;隔开
	 */
	public static void checkWebElementsExist(String message, String elementArray) {
		String[] element = elementArray.split(";");

		for (int i = 0; i < element.length; i++) {
			if ((null == element[i]) && ("".equals(element[i].trim()))) {
				continue; // 如果该元素为空，则不进行校验，进行下一次循环
			}
//			checkWebElementExist(message, element[i]);
		}
	}

	
	/***
	 * 校验元素存在，断言判断结果。默认查找3次，查找时暂停1秒
	 * 
	 * @param message
	 *            提示信息
	 * @param element
	 *            元素
	 */
	public static void checkWebElementExist(WebDriver driver,String message, String element) {
		Boolean result = false;
		int times = 2;
		while (0 <= times) {
			try {
				result = isWebElementExist(driver,element);
				if (result)
					return; // 如果存在，校验成功，退出方法
			} catch (Exception e) {
				Pause.pause(3000);
			}
			times--;
		}
		Assert.assertTrue("[" + message + "] The web element don't exist: " + element,
				result);
	}

	
	/**
	 * 检查web页面元素是否存在 传入参数为js语句,如:$('div#geoLoading')
	 */
	private static boolean isWebElementExist(WebDriver driver,String element) 
	{
		String jscmd = "return document.getElementById('XXX')";
		if (element.trim().startsWith("$")) 
		{
			jscmd = jscmd.replace("document.getElementById('XXX')", element
					+ ".length > 0");
		} else 
		{
			jscmd = jscmd.replace("document.getElementById('XXX')", element);
		}
		return CommonJQ.excuteJStoBoolean(driver, jscmd);
	}

}
