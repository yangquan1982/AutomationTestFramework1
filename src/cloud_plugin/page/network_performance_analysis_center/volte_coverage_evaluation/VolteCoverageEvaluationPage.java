package cloud_plugin.page.network_performance_analysis_center.volte_coverage_evaluation;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class VolteCoverageEvaluationPage {

	private static final String AppName = "li[id=\"genexspace.plugin.ltevoltecoverPlugStr\"]";

	public static void VideoInsightClick(WebDriver driver) {
		CommonJQ.click(driver, AppName, true, "插件未找到，插件名：VoLTE覆盖评估");
	}



	private static final String TextTaskName = "#taskName";// Task Name

	public static final String RBoxTDD = "input[value=\"TDD\"]";
	public static final String RBoxFDD = "input[value=\"FDD\"]";

	public static final String RBoxGSM = "input[value=\"GSM\"]";
	public static final String RBoxUMTS = "input[value=\"UMTS\"]";
	public static final String RBoxCDMA = "input[value=\"CDMA\"]";

	public static final String RBoxHW = "#huaweiManufacturer";
	public static final String RBoxMV = "#mvManufacturer";

	public static final String CBoxFirst = "#taskTypeFirst";
	public static final String CBoxSecond = "#taskTypeSecond";

	public static final String BtnLTECfg = "select_volteprojectparamter";
	public static final String BtnLTEPP = "select_MrData";
	public static final String BtnLTEPfm = "select_propertyData";
	public static final String BtnLTESig = "select_MrData";
	public static final String Btncfg = "select_gsmConfData";
	public static final String BtnPP = "select_gsmConfData";
	public static final String BtnPfm = "select_gsmPfmData";
	public static final String BtnCHR = "select_gsmChrData";
	public static final String BtnMR = "select_mrGsmData";
	public static final String BtnMap = "select_mapData";
	public static final String BtnPology = "selectpology";

	public static final String BtnMessageOK = "div[class=\"messager-button\"] span[class=\"l-btn-text\"]";
	// 基础设置
	public static final String PosiObjLi_PATH = "#posiObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String FeaturePosit = "ul[id=\"mshowpositioning\"] li[title=\"特征库定位\"]";
	public static final String FastPosit = "ul[id=\"mshowpositioning\"] li[title=\"快速定位\"]";
	public static final String ComplexPosit = "ul[id=\"mshowpositioning\"] li[title=\"综合定位\"]";
	public static final String RasterObjLi_PATH = "#rasterObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String Raster20 = "ul[id=\"mshowrasterAccuracy\"] li[id=\"20\"]";
	public static final String Raster50 = "ul[id=\"mshowrasterAccuracy\"] li[id=\"50\"]";

	// 基础MR门限
	public static final String TextRaster_mr = "input[id=\"raster_mr_measur_threshould\"]";
	public static final String TextRaster_cell_mr = "input[id=\"raster_cell_mr_measur_threshould\"]";
	public static final String TextMin_interfrence = "input[id=\"min_interfrence_fiter_threshould\"]";
	public static final String TextMin_level = "input[id=\"min_level_fiter_threshould\"]";

	public static final String TextLTECoverage = "input[id=\"LTECoverageGridMRNumber\"]";
	public static final String TextBusinessGrid = "input[id=\"businessGridMRNumberThreshold\"]";
	public static final String TextTrafficGrid = "input[id=\"gridTrafficThreshold\"]";
	public static final String TextLTEMRCollect = "input[id=\"LTEMRCollectSample\"]";
	public static final String TextUMTSMRDatePapaer = "input[id=\"UMTSMRDatePapaerDate\"]";

	public static final String TextMrReport = "#lteMrPeriodObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String List5s = "ul[id=\"mshowlteMrPeriod\"] li[id=\"5\"]";
	public static final String List10s = "ul[id=\"mshowlteMrPeriod\"] li[id=\"10\"]";
	public static final String List60s = "ul[id=\"mshowlteMrPeriod\"] li[id=\"60\"]";

	// 弱覆盖MR门限
	public static final String TextIndoorRate = "input[id=\"indoor_weak_cover_rate_threshold\"]";
	public static final String TextComplexRate = "input[id=\"complex_weak_cover_rate_threshold\"]";
	public static final String TextIndoorLevel = "input[id=\"indoor_weak_cover_level_threshold\"]";
	public static final String TextComplexLevel = "input[id=\"complex_weak_cover_level_threshold\"]";

	// 扩展维度
	public static final String RBoxAllChose = "#allchose";
	public static final String RBoxRateGeography = "input[name=\"lteTaskBean.flowRateGeography\"]";
	public static final String RBoxRedirectionGeography = "input[name=\"lteTaskBean.redirectionGeography\"]";
	public static final String RBoxCQIGeography = "input[name=\"lteTaskBean.cqiGeography\"]";
	public static final String RBoxUserCountGeography = "input[name=\"lteTaskBean.userCountGeography\"]";
	public static final String RBoxPerfFlowGeography = "input[name=\"lteTaskBean.perfFlowGeography\"]";
	public static final String RBoxSrvccGeography = "input[name=\"lteTaskBean.srvccGeography\"]";

	public static final String BtnChooseFile = "#villageFile";

	// 其他设置
	public static final String TextClearMr = "#weaClearObjLi div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String ListClearMrYES = "ul[id=\"mshowweatherClearMr\"] li[id=\"yes\"]";
	public static final String ListClearMrNO = "ul[id=\"mshowweatherClearMr\"] li[id=\"no\"]";

	public static final String BtnCommit = "input[ng-click=\"insertAction()\"]";
	private static final String BtnCancel = "input[class=\"cancelButton\"]";
	private static final String BtnNext = "input[ng-click=\"next()\"]";

	public static String setTaskName(WebDriver driver, String taskName) {
		taskName = taskName + "_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TextTaskName, taskName, "任务名称");
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName, "任务名称");
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime() + "TaskName:" + taskName);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCommit, true, "提交");
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void nextBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnNext, true, "下一步");
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCancel, true, "取消");
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
