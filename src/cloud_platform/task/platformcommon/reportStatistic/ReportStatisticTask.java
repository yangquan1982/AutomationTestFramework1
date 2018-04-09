package cloud_platform.task.platformcommon.reportStatistic;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.huawei.webtest.restful.util.Log;

import cloud_platform.page.reportStatistic.ReportStatisticPage;
import common.util.CommonJQ;

public class ReportStatisticTask {

	
	/**
	 *验证报表统计界面元素
	 * @param driver
	 * @param taskName
	 * @param verficationList
	 */
	public static void reportStatisticVerfication(WebDriver driver,
			String taskName, String[] verficationList) 
	{
		//点击任务
		ReportStatisticPage.clickByTaskName(driver, taskName);
		
		//查看具体任务界面的元素
		for(int i =0;i<verficationList.length;i++)
		{
			
			if(spanReturn(driver,verficationList[i])||labelReturn(driver,verficationList[i])||tdReturn(driver,verficationList[i]))
			{
				Log.info(verficationList[i]+"在界面显示正常。");
				break;
			}else {
				Assert.fail("在界面未找到"+verficationList[i]+"元素！");
			}
		}
	}

	
	private static boolean tdReturn(WebDriver driver, String element) 
	{
		String script = "return $(\"iframe\").contents().find('td:visible').filter(function(){return $(this).text().trim()=='"+element+"'}).length";
		Log.info("script："+script);
		int length = CommonJQ.excuteJStoInt(driver, script);
		System.out.print(length+"~~~~~tdReturn");
		if(length>0){
			return true;
		}else{
			return false;
		}
	}


	private static boolean labelReturn(WebDriver driver, String element) 
	{
		String script = "return $(\"iframe\").contents().find('label:visible').filter(function(){return $(this).text().trim()=='"+element+"'}).length";
		Log.info("script："+script);
		String length = CommonJQ.excuteJStoString(driver,script);
		System.out.print(length+"~~~~~labelReturn");
		if(Integer.parseInt(length)>0)
		{
			return true;
		}else{
			return false;
		}
	}


	private static boolean spanReturn(WebDriver driver, String element) 
	{
		String  script = "return $(\"iframe\").contents().find('span:visible').filter(function(){return $(this).text().trim()=='"+element+"'}).length";
		Log.info("script："+script);
		int length = CommonJQ.excuteJStoInt(driver, script);
		System.out.print(length+"~~~~~spanReturn");
		if(length>0){
			return true;
		}else{
			return false;
		}
	}



}
