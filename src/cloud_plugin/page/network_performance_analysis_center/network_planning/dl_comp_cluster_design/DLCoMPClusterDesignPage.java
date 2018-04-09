package cloud_plugin.page.network_performance_analysis_center.network_planning.dl_comp_cluster_design;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class DLCoMPClusterDesignPage {

	
	private static final String TextTaskName = "#taskName";//Task Name
	
	public static final String RBoxCA  = "input[name=\"taskBean.characterSelect\"]";
	public static final String CBoxEvaluation  = "#evaluationId";
	public static final String CBoxCAPlan  = "#planId";
	
	public static final String BtnCfg = "select_configData";
	public static final String BtnPara = "select_paraData";
	public static final String BtnSig = "select_sigData";
	public static final String BtnPfm  = "select_pfmData";
	public static final String BtnChr  = "select_chrData";//
	public static final String BtnFeature  = "select_traitData";//select_traitData
	public static final String BtnCell  = "#templateFileCell";
	public static final String BtnMessageOK = "div[class=\"messager-button\"] span[class=\"l-btn-text\"]";
	
// CA Zone评估参数设置
	//CA类型配置
	public static final String RBoxNetType = "input[name=\"taskBean.caTaskBean.netType\"]";
	//频段设置
	public static final String TextBand = "#topnCellNumID";
	public static final String TextBandWidth = "#transLossID";
	
	//筛选开关
	public static final String CBoxCATerminal = "#terminalPermeabilityId";
	public static final String CBoxAverageRate = "#throughputThreshoidRateId";
	public static final String CBoxServiceVolume = "#businessNumId";
	public static final String CBoxDLPRBUsage = "#useRatioId";
	public static final String CBoxCoverageScenario = "#coverSceneId";
	//门限设置
	public static final String TextCATerminal = "#CAterminalId";
	public static final String TextAverageRate = "#downloadRateId";
	public static final String TextServiceVolume = "#businessWorkId";
	public static final String TextDLPRBUsage = "#PRBUseRateId";
	//场景选择
	public static final String CBoxScenarioAll = "#checkAllId";
	public static final String CBoxUniversity = "input[name=\"taskBean.caTaskBean.universityCampus\"]";
	public static final String CBoxRailwayStation = "input[name=\"taskBean.caTaskBean.railwayStation\"]";
	public static final String CBoxSuperStore = "input[name=\"taskBean.caTaskBean.superStore\"]";
	public static final String CBoxMotorStation = "input[name=\"taskBean.caTaskBean.motorStation\"]";
	public static final String CBoxScenicSites = "input[name=\"taskBean.caTaskBean.scenicSites\"]";
	public static final String CBoxHotel = "input[name=\"taskBean.caTaskBean.hotel\"]";
	public static final String CBoxGymnasium = "input[name=\"taskBean.caTaskBean.gymnasium\"]";
	public static final String CBoxOffice = "input[name=\"taskBean.caTaskBean.officeBuilding\"]";
	public static final String CBoxWharf = "input[name=\"taskBean.caTaskBean.wharf\"]";
	public static final String CBoxBusShelter = "input[name=\"taskBean.caTaskBean.busShelter\"]";
	public static final String CBoxRestaurant = "input[name=\"taskBean.caTaskBean.restaurant\"]";
	public static final String CBoxGovernment = "input[name=\"taskBean.caTaskBean.government\"]";
	public static final String CBoxCBD = "input[name=\"taskBean.caTaskBean.CBD\"]";
	public static final String CBoxResidentialQuarter = "input[name=\"taskBean.caTaskBean.residentialQuarter\"]";	
	public static final String CBoxPark = "input[name=\"taskBean.caTaskBean.park\"]";
	public static final String CBoxMainChannel = "input[name=\"taskBean.caTaskBean.mainChannel\"]";
	
// CA Zone规划参数设置
	//共覆盖参数设置
	public static final String RBoxCover = "input[name=\"taskBean.caTaskBean.allCoverSelect\"]";

	public static final String TextCoverRatio = "#allCoverRatioId";
	public static final String TextRsrpThreshoid = "#validRsrpThreshoidId";
	public static final String TextMrThreshoid = "#validMrThreshoidId";
	
	//CA类型
	public static final String RBoxACA     = "input[name=\"taskBean.caTaskBean.caType\"]";
	public static final String RBoxCAGROUP = "input[name=\"taskBean.caTaskBean.caType\"]";
	public static final String RBoxFCA     = "input[name=\"taskBean.caTaskBean.caType\"]";
	
	public static final String TextAzimuth = "#azimuthDeviationId";
	public static final String TextExcursion = "#excursionDistanceId";
	public static final String TextNotDistance = "#notDistanceId";
	
	//CA Zone预测参数设置 
	public static final String RBoxAuto = "#Auto";
	public static final String RBoxHandMove = "#handMove";
	
	public static final String TextPenetranceRatio = "#penetranceRatioId";
	public static final String TextActivateRatio = "#activateRatioId";
	public static final String TextThroughput = "#throughputThreshoidId";
	
	
	public static final String BtnPre = "span[ng-click=\"previous()\"]";
	public static final String BtnNext = "span[ng-click=\"next()\"]";
	private static final String BtnCancel = "span[ng-click=\"cancel()\"]";
	private static final String BtnSubmit = "span[ng-click=\"submit()\"]";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		if(taskName!= null){
			 taskName = taskName+"_" + ZIP.hhmmss();
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.value(driver, TextTaskName, taskName,"任务名称");
				String text = CommonJQ.getValue(driver, TextTaskName);
				if (!(text.equals(taskName))) {
					CommonJQ.value(driver, TextTaskName, taskName,"任务名称");
				}
				SwitchDriver.switchDriverToSEQ(driver);	
		}
		return taskName;
	}
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnSubmit, true,"取消按钮");
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void nextBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnNext, true,"下一步按钮");
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCancel, true,"提交按钮");
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
