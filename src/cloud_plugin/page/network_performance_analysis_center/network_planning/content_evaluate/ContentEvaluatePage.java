package cloud_plugin.page.network_performance_analysis_center.network_planning.content_evaluate;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class ContentEvaluatePage {

	private static final String TextTaskName = "#taskName";//Task Name
	
	public static final String BtnCfg  = "select_configFile";
	public static final String BtnEpara  = "select_configFile";
	public static final String BtnPfm = "select_configFile";
	public static final String BtnCSVPfm  = "select_configFile";
	
	public static final String CBoxParaTcp  = "input[name=\"taskBean.umtsTcp\"]";
	public static final String CBoxParaRtwp  = "input[name=\"taskBean.umtsRtwp\"]";	
	public static final String CBoxParaCode  = "input[name=\"taskBean.umtsCode\"]";	
	public static final String CBoxParaHsuser  = "input[name=\"taskBean.umtsHsuser\"]";
	
	
	public static final String TextTcpPowerOver  = "#powerOverloadThreshold";
	public static final String TextTcpDCHUser  = "#dchUsersThreshold";
	public static final String TextHSDPAUser  = "#hsdpaUsersThreshold";
	public static final String TextHSDPAThrough  = "#hsdpaThroughputThreshold";
	public static final String TextRtwpLoad  = "#rtwpLoadThreshold";
	public static final String TextRtwpUpUser  = "#equivalentUsersThreshold";
	public static final String TextCode  = "#codeUtilizaThreshold";
	
	public static final String TextFactorSub  = "#umtsubScriberFactor";
	public static final String TextFactorCsUser  = "#csUserSide";
	public static final String TextFactorPsUp  = "#psUpstreamUserSide";
	public static final String TextFactorPsDown  = "#psDownstreamUserSide";
	public static final String TextFactorCsSig  = "#csSignalingPlane";
	public static final String TextFactorPsSig  = "#psSignalingPlane";
	public static final String TextFactorHSDPAUser  = "#hsdpaUserTagrt";
	public static final String BtnFactorFile  = "#villageFile";
	
	public static final String TextTopN  = "input[name=\"taskBean.oavgPrccons\"]";
	public static final String ListTopN  = "ul[class=\"mshow\"] li[ng-model=\"param.oavgPrccons\"]";
	public static final String RBoxTCP  = "input[name=\"taskBean.busyFlag\"]";
	public static final String RBoxBusy  = "input[class=\"marginright15 marginright2\"]";
	
	public static final String BtnSeleAll  = "#seleAllHours";
	public static final String BtnCleanAll  = "#cleanAllColor";
	
	public static final String CBoxWhetherTopM  = "input[ng-model=\"param.topNWhetheter\"]";
	public static final String TextTopM  = "div[ng-if=\"!param.topNWhetheter\"] span[class=\"showSelects\"]";
	public static final String ListTopM  = "ul[class=\"mshow\"] li[ng-model=\"param.oUtilizationRatio\"]";
	
	public static final String ListSelect  = "div[class=\"zz_select msel\"] ul[class=\"mshow\"]";
	public static final String ListSelect2  = "div[class=\"msel zz_select\"] ul[class=\"mshow\"]";
	public static final String TextDataIntegrity  = "input[name=\"taskBean.omaxPRCCons\"]";
	public static final String ListDataIntegrity  = "ul[class=\"mshow\"] li[ng-model=\"param.oMaxPRCCons\"]";
	
	private static final String BtnCommit  = "#commit_Tasknew";
	private static final String BtnNext  = "#next_Tasknew";
	private static final String BtnCancel  = "#cancel_Task";	
	private static final String TitleCSS_CLASS = "div[class=\"titleCSS ng-binding\"] a[class=\"ng-binding\"]";
	
	public static final String BtnNeGroup  = "#neGroupData";
	public static final String BtnMessageOk  = "span[class=\"l-btn-text\"]";
	//制式 选择
	public static final String SingleBtnFDD  = "#FDD";
	public static final String SingleBtnTDD  = "#TDD";
	//门限 设置
	public static final String TexthDownFlow  = "#hDownFlow";
	public static final String TexthAvgPRCCons  = "#hAvgPRCCons";
	public static final String TexthMaxPRCCons  = "#hMaxPRCCons";
	public static final String TextoDownFlow  = "#oDownFlow";
	//预测因子 设置
	public static final String TextflowScriberFactor  = "#flowScriberFactor";
	public static final String TextsubScriberFactor  = "#subScriberFactor";
	public static final String BtnvillageFile  = "#villageFile";
	public static final String TextvillageFileDiv  = "#villageFileDiv";
	public static final String BtnFactorfile  = "span[class=\"btn_green\"] a[class=\"dataAnalysisTemplateBtn ng-binding\"]";
	public static final String CBoxcellUserFactor  = "#cellUserFactor";
	public static final String CBoxdlRepPSUser  = "#dlRepPSUser";
	//忙时
	public static final String SingleBtnDlprb  = "input[value=\"1\"]";
	public static final String SingleBtnLiuLiang  = "input[value=\"2\"]";
	public static final String Listbusyshuaixuanfs  = "div[class=\"zz_select msel\"] ul[class=\"mshow\"]";
	
	
	
	public static String setTaskName(WebDriver driver,String taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName+"_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TextTaskName);
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCommit, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void NextBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnNext, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}	
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCancel, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void cancelTitleClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, TitleCSS_CLASS, true,0);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
