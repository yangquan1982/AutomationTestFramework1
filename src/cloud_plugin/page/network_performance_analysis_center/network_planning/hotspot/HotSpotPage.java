package cloud_plugin.page.network_performance_analysis_center.network_planning.hotspot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class HotSpotPage {

	private static final String TaskName_ID = "#taskName";//Task Name
	
	public static final String PosiObjLi_PATH = "#posiObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String FeaturePosit  = "ul[id=\"mshowpositioning\"] li[title=\"特征库定位\"]";
	public static final String FastPosit  = "ul[id=\"mshowpositioning\"] li[title=\"快速定位\"]";
	public static final String AgpsPosit  = "ul[id=\"mshowpositioning\"] li[title=\"AGPS定位\"]";
	public static final String ComplexPosit  = "ul[id=\"mshowpositioning\"] li[title=\"综合定位\"]";
	public static final String RasterObjLi_PATH = "#rasterObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String Raster20  = "ul[id=\"mshowrasterAccuracy\"] li[id=\"20\"]";
	public static final String Raster50  = "ul[id=\"mshowrasterAccuracy\"] li[id=\"50\"]";
	
	public static final String CheckAgps_ID  = "#use_AGPS_library";
	

	public static final String CfgBtn  = "select_confFilePath";
	public static final String EparaBtn  = "select_parasFilePath";
	public static final String MrBtn  = "select_mrFilePath";
	public static final String PfmBtn  = "select_perforFilePath";
	public static final String PCHRBtn  = "select_PCHRFilePath";
	public static final String NodeBPfmBtn  = "select_nodeBFilePath";
	public static final String FeatureBtn  = "select_propertyFilePath";
	public static final String MapBtn  = "select_mapFilePath";
	
	public static final String BtnFactor  = "#villageFile";
	
	public static final String TextRasterRateEvaType  = "input[name=\"task.shangeRateAssess\"]";
	public static final String ListRateEvaType_All  = "ul#mshowshangeRateAssess li#0";
	public static final String ListRateEvaType_one  = "ul#mshowshangeRateAssess li#1";
	public static final String TextMRCount = "#shangeMRCount";
	public static final String TextStartTime = "#date1";
	public static final String TextEndTime = "#date2";
	
	public static final String TextLowRate = "#lowRateMenXian";
	public static final String TextMainCellMR = "#zhuXiaoQuMenXian";
	
	public static final String TextCluster  = "input[name=\"task.isOrNotSeparate\"]";
	public static final String ListCluster_No  = "ul#mshowisOrNotSeparate li[title=\"否\"]";
	public static final String ListCluster_Yes  = "ul#mshowisOrNotSeparate li[title=\"是\"]";
	
	public static final String TextWhetherMR  = "input[name=\"task.isMrQingXi\"]";
	public static final String ListWhetherMR_Yes  = "ul[id=\"mshowisMrQingXi\"] li[id=\"yes\"]";
	public static final String ListWhetherMR_No  = "ul[id=\"mshowisMrQingXi\"] li[id=\"no\"]";

	public static final String CBox6DAll  = "#allchose";
	public static final String CBox6DTerminal  = "input[name=\"task.jiaZhiZhongDuan\"]";
	public static final String CBox6DBusiness  = "input[name=\"task.jiaZhiYeWu\"]";
	public static final String CBox6DUser  = "input[name=\"task.jiaZhiYongHu\"]";
	public static final String CBox6DComplaints  = "input[name=\"task.userFeedBack\"]";
	public static final String CBox6DAnalysis  = "input[name=\"task.umtsAnalysis\"]";
	
	private static final String BtnNext = "#next_Tasknew";
	private static final String CommitBtn  = "#commit_Tasknew";	
	private static final String CancelBtn  = "#cancel_Task";
	private static final String TitleCSS_CLASS = "span[class=\"titleCSS ng-binding\"] a[class=\"ng-binding\"]";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName+"_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TaskName_ID);
//		CommonJQ.value(driver, TaskName_ID, taskName);
		driver.findElement(By.id("taskName")).sendKeys(taskName);
//		String text = CommonJQ.getValue(driver, TaskName_ID);
//		if (!(text.equals(taskName))) {
//			CommonJQ.value(driver, TaskName_ID, taskName);
//		}
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}
	public static void speedBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
//		driver.findElement(By.id("umtsTypeSelect")).click();
		CommonJQ.click(driver, "#umtsTypeSelect", true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void d6BtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
//		driver.findElement(By.id("6DTypeSelect")).click();
		CommonJQ.click(driver, "#6DTypeSelect", true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CommitBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void nextBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnNext, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CancelBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void cancelTitleClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, TitleCSS_CLASS, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
