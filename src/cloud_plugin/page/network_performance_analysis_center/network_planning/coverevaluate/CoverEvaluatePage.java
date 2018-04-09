package cloud_plugin.page.network_performance_analysis_center.network_planning.coverevaluate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class CoverEvaluatePage {

	private static final String TaskName_ID = "#taskName";//Task Name
	
	public static final String PosiObjLi_PATH = "#posiObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String FeaturePosit  = "ul[id=\"mshowpositioningUmts\"] li[title=\"特征库定位\"]";
	public static final String FastPosit  = "ul[id=\"mshowpositioningUmts\"] li[title=\"快速定位\"]";
	public static final String AgpsPosit  = "ul[id=\"mshowpositioningUmts\"] li[title=\"AGPS定位\"]";
	public static final String ComplexPosit  = "ul[id=\"mshowpositioningUmts\"] li[title=\"综合定位\"]";
	public static final String RasterObjLi_PATH = "#rasterObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String Raster20  = "ul[id=\"mshowrasterAccuracyUmts\"] li[id=\"20\"]";
	public static final String Raster50  = "ul[id=\"mshowrasterAccuracyUmts\"] li[id=\"50\"]";
	
	public static final String CheckAgps_ID  = "#use_AGPS_library";
	
	public static final String CfgBtn  = "select_confData";
	public static final String EparaBtn  = "select_engineerParaData";
	public static final String MrBtn  = "select_MrData";
	public static final String FeatureBtn  = "select_propertyData";
	public static final String MapBtn  = "select_mapData";



	public static final String TextRasterMR = "#raster_mr_measur_threshould";
	public static final String TextRasterCellMR = "#raster_cell_mr_measur_threshould";
	public static final String TextECIO = "#ECIO_fiter_threshould";
	public static final String TextRSCP = "#RSCP_fiter_threshould";
	public static final String CBoxMainCellFiter = "input[name=\"umtsTaskBean.mainCellWheatherFiter\"]";
	public static final String TextMainCell = "#raster_cell_mr_fiter_threshould";

	public static final String TextIndoorCoverRate = "#indoor_weak_cover_rate_threshold";
	public static final String TextComplexCoverRate = "#complex_weak_cover_rate_threshold";
	public static final String TextIndoorCoverLevel = "#indoor_weak_cover_level_threshold";
	public static final String TextComplexCoverLevel = "#complex_weak_cover_level_threshold";
	
	public static final String TextClearMR = "#weatherClearMrUmts";
	public static final String ListMrYES  = "ul[id=\"mshowweatherClearMrUmts\"] li[id=\"yes\"]";
	public static final String ListMrNO  = "ul[id=\"mshowweatherClearMrUmts\"] li[id=\"no\"]";
	
	public static final String TextAcp = "#wheatherOutputAcpResultUmts";
	public static final String ListAcpYES  = "ul[id=\"mshowwheatherOutputAcpResultUmts\"] li[id=\"yes\"]";
	public static final String ListAcpNO  = "ul[id=\"mshowwheatherOutputAcpResultUmts\"] li[id=\"no\"]";
	
	private static final String BtnNext = "input[ng-click=\"next()\"]";
	private static final String CommitBtn  = "input[ng-click=\"insertAction()\"]";	
	private static final String CancelBtn  = " .cancelButton";
	
	private static final String TitleCSS_CLASS = "span[class=\"titleCSS ng-binding\"] a[class=\"ng-binding\"]";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName+"_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TaskName_ID);
//		CommonJQ.value(driver, TaskName_ID, taskName);
//		String text = CommonJQ.getValue(driver, TaskName_ID);
		driver.findElement(By.id("taskName")).sendKeys(taskName);
//		if (!(text.equals(taskName))) {
//			CommonJQ.value(driver, TaskName_ID, taskName);
//		}
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
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
