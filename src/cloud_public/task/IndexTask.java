package cloud_public.task;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_public.page.IndexPage;
import cloud_public.page.LoadingPage;
import common.util.LOG;

import common.util.CommonJQ;

public class IndexTask {

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver netPerformanceAnalysisCenter
	 * @return void
	 */
	public static void openPlugin(WebDriver driver,String CenterType_ID, String PrjName,
			String PlugName,String Message) {

		try {
			if (CommonJQ.isExisted(driver, "#netPerformanceAnalysisCenter")) {
				driver.findElement(By.id("netPerformanceAnalysisCenter")).findElement(By.tagName("a")).click();
			}
		} catch (Exception e) {
			
		}
		if(CenterType_ID.equals(IndexPage.NetPerAnalyCenter_ID)){
			IndexPage.netPerAnalyCenterMenuClick(driver);
		}else if(CenterType_ID.equals(IndexPage.NetEvaAndAssCenter_ID)){
			IndexPage.netEvaAndAssCenterMenuClick(driver);
		}		
		LoadingPage.Loading(driver);
		IndexTask.changePrj(driver, PrjName);
		CommonJQ.click(driver, PlugName, false,Message);
		LoadingPage.Loading(driver, ".prev","进入任务列表界面失败");
	}	
	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param Prj
	 * @return void
	 */
	public static void changePrj(WebDriver driver, String Prj) {
		if (!(CommonJQ.isExisted(driver, "span[title=\"" + Prj + "\"]"))) {
			LOG.info_aw("切换项目到："+Prj);
			CommonJQ.click(driver, IndexPage.PrjSwi, false);
			driver.findElement(By.id("searchText")).sendKeys(Prj);
			CommonJQ.value(driver, IndexPage.SearchText_ID, Prj);
			LoadingPage.Loading(driver);
			CommonJQ.click(driver, IndexPage.PrjList_ID, "li",
					false,Prj);
			LoadingPage.Loading(driver);
		}
		boolean flage = CommonJQ.isExisted(driver, "span[title=\"" + Prj + "\"]");
		if(flage == false){
			Assert.fail("项目切换失败，项目名称："+Prj);
		}
	}
	public static void openNetPlanPlugin(WebDriver driver,String CenterType_ID, String PrjName,
			String PlugName,String Message) {

		try {
			if (CommonJQ.isExisted(driver, CenterType_ID)) {
				CommonJQ.click(driver, CenterType_ID, true);
			}
		} catch (Exception e) {
			
		}
		LoadingPage.Loading(driver);
		IndexTask.changePrj(driver, PrjName);
		CommonJQ.click(driver, PlugName, false,Message);
		LoadingPage.Loading(driver, ".r_body","进入插件任务列表界面失败");
		IndexPage.PrjTaskClick(driver);
	}	
}
