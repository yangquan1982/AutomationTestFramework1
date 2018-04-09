package cloud_public.page;

import org.openqa.selenium.WebDriver;

import common.util.SeleniumUtil;

import common.util.CommonJQ;

public class TaskViewPluginPage {

	
	public static void reportDownLoadCompareClick( WebDriver driver){
		CommonJQ.click(driver, "#reportDownLoad", true);
	}
	
	public static void reportDownLoadLReportClick( WebDriver driver){
		CommonJQ.click(driver, "#showDownLoad", true);
	}
	public static void reportDownLoadCoverEvaluteClick( WebDriver driver){
		CommonJQ.click(driver, "#showDownLoad1", true);
	}
	public static void reportDownLoadHotSpotClick( WebDriver driver){
		CommonJQ.click(driver, "#showDownLoad1", true);
	}
	public static void reportDownLoadDTOverseasClick( WebDriver driver){
		SeleniumUtil.click_ByXpath(driver, "//a[@class='icon icon_down reportDownloadIcon']");
	}
	public static void reportDownLoadContentEvaluateClick( WebDriver driver){
		CommonJQ.click(driver, "#showDownLoad", true);
	}
	public static void reportDownLoadNetWorkAuditClick( WebDriver driver){
		CommonJQ.click(driver, "#showDownLoad", true);
	}
	public static void reportDownLoadTrafficForecastClick( WebDriver driver){
		CommonJQ.click(driver, "#showDownLoad", true);
	}
}
