package cloud_platform.page.reportStatistic;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.LOG;

public class ReportStatisticPage {

	/**
	 * 点击报表统计左边页面
	 * 
	 * @param driver
	 * @param taskName
	 */
	public static void clickByTaskName(WebDriver driver, String taskName) {
		LOG.info_aw("点击-" + taskName);
		String script = "$(\"li div:contains('"+taskName+"'):visible\").click()";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);
	}

	
	/**
	 * 给一个下拉框中的设置值
	 * @param driver
	 * @param taskName
	 * @param choseTaskName
	 */
	public static void setDropDownListValue(WebDriver driver, String taskName,
			String chooseTaskName) 
	{
		LOG.info_aw("将"+taskName+"的下拉框值设置为："+chooseTaskName);
		
		// 找taskName的span
		driver.switchTo().frame("centoriframe");
		searchParamIsExist(driver,taskName);
		
		// 点击下拉框展示按钮
		clickDropDownListButton(driver,taskName);

		// 点击选择框中的内容
		clickDropDownListValue(driver,taskName,chooseTaskName);
		LoadingPage.Loading(driver);
		driver.switchTo().defaultContent();
	}

	
	/**
	 * 给多个下拉框中设置value值
	 * @param driver
	 * @param taskName
	 * @param valueList
	 */
	public static void setDropDownListManyValue(WebDriver driver, String taskName,String[] valueList)
	{
		//切换至iframe
		driver.switchTo().frame("centoriframe");
		
		//设置值
		setValueThree(driver,taskName,valueList);
	}
	
	
	
	/**
	 * 判断task是否存在
	 */
	public static void searchParamIsExist(WebDriver driver, String taskName)
	{
		int length = CommonJQ.length(driver, "span:contains('"+taskName+"'):visible");
		Assert.assertTrue("界面未找到"+taskName+"!",length > 0);
	}

	/**
	 * 点击选择框中的内容
	 * @param driver
	 * @param taskName
	 * @param chooseTaskName
	 */
	private static void clickDropDownListValue(WebDriver driver,
			String taskName, String chooseTaskName) 
	{
		String textScript = "return $(\"span:contains('"+taskName+"'):visible\").next('span').find(\"ul li:contains('"+
				chooseTaskName+"')\").text()";
		if (!"".equals(CommonJQ.excuteJStoString(driver, textScript))) 
		{
			String chooseScript = "$(\"span:contains('"+taskName+"'):visible\").next('span').find(\"ul li:contains" +
					"('"+chooseTaskName+"')\").click()";
			CommonJQ.excuteJS(driver, chooseScript);
		}else 
		{
			Assert.fail("下拉框中"+chooseTaskName+"不存在！");
		}
	}


	/**
	 * 点击下拉框
	 * @param driver
	 * @param taskName
	 */
	private static void clickDropDownListButton(WebDriver driver, String taskName) 
	{
		String script = "$(\"span:contains('"+taskName+"'):visible\").next('span').find('span').click()";
		CommonJQ.excuteJS(driver, script);
		String isBlockScript = "return $(\"span:contains('"+taskName+"'):visible\").next('span').find('ul').css('display')";
		String blockStr = CommonJQ.excuteJStoString(driver, isBlockScript);
		LOG.info("点击下拉框后的返回值："+blockStr);
		LoadingPage.Loading(driver);
		Assert.assertTrue("点击下拉框失败！", blockStr.contains("block"));
	}
	
	
	
	/**
	 * 设置下拉框中的值
	 * @param driver
	 * @param taskName
	 * @param valueList
	 */
	private static void setValueThree(WebDriver driver, String taskName,
			String[] valueList) 
	{
		searchParamIsExist(driver,taskName);
		
		//依据valueList设置值
		for(int i =0 ;i<valueList.length;i++)
		{
			//点击下拉框
			String clickDropScript = "$(\"span:contains('"+taskName+"')\").parent().find('input:visible').eq("+i+").nextAll('span:visible').click()";
			CommonJQ.excuteJS(driver, clickDropScript);
			
			//判断下拉框点击是否成功
			isClickRight(driver,taskName,i);
			
			//setValue值
			String 	script = "$(\"span:contains('"+taskName+"')\").parent().find(\"input:visible\").eq("+i+").parent().next('ul').find(\"li:contains('"+valueList[i]+"')\").click()";
			CommonJQ.click(driver, script);
			LoadingPage.Loading(driver);
		}
		
	}


	/**
	 * 下拉框点击是否成功
	 * @param driver
	 * @param taskName
	 * @param i
	 */
	private static void isClickRight(WebDriver driver, String taskName,int i ) 
	{
		String script ="return $(\"span:contains('"+taskName+"')\").parent().find(\"input:visible\").eq("+i+").parent().next('ul:visible').css('display')";
		if(!CommonJQ.excuteJStoString(driver, script).equals("block"))
		{
			Assert.fail("点击下拉框失败！");
		}
	}


	


	/**
	 * 点击查询
	 * @param driver
	 */
	public static void clickSearch(WebDriver driver)
	{
		LOG.info("点击查询按钮");
		String script = "$(\"span:contains('查询')\").click()";
		CommonJQ.excuteJS(driver, script);
		
	}

}
