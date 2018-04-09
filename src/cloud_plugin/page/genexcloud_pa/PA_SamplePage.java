package cloud_plugin.page.genexcloud_pa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_public.page.IndexPage;
import cloud_public.page.LoadingPage;
import cloud_public.task.IndexTask;
import common.util.CommonJQ;

public class PA_SamplePage {
	public static final String AlgorithmProject = "";
	public static final String NetPerAnalyCemter = "td[id=\"netPerformanceAnalysisCenter\"] > a";
	public static final String NetPerAnalyCenter_ID = "netPerformanceAnalysisCenter";

	public static void openPlugin(WebDriver driver, String CenterType_ID, String PrjName, String PlugName,
			String Message) {

		try {
			if (CommonJQ.isExisted(driver, "#netPerformanceAnalysisCenter")) {
				driver.findElement(By.id("netPerformanceAnalysisCenter")).findElement(By.tagName("a")).click();
			}
		} catch (Exception e) {

		}
		if (CenterType_ID.equals(IndexPage.NetPerAnalyCenter_ID)) {
			IndexPage.netPerAnalyCenterMenuClick(driver);
		} else if (CenterType_ID.equals(IndexPage.NetEvaAndAssCenter_ID)) {
			IndexPage.netEvaAndAssCenterMenuClick(driver);
		}
		LoadingPage.Loading(driver);
		IndexTask.changePrj(driver, PrjName);
		CommonJQ.click(driver, PlugName, false, Message);
		LoadingPage.Loading(driver, ".prev", "进入任务列表界面失败");
	}

}
