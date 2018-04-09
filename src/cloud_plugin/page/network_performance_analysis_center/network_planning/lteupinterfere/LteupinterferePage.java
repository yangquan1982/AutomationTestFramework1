package cloud_plugin.page.network_performance_analysis_center.network_planning.lteupinterfere;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class LteupinterferePage {

	public static final String BtnPrjTask = "li[ng-click=\"appDetailTagClick('task')\"]";
	public static final String CreateTask = "li[ng-click=\"createTask()\"]";// 场景任务

	private static final String TextTaskName = "#taskName";// 任务名称
	public static final String RadioGanRaoShibie = "#twoscene";
	public static final String RadioFdd = "#fdd";

	public static final String BtnParameterSet = "#UPInterfere_fdd_Parameter_Setting";
	public static final String[] TextGanRaoJianCe = { "#inThreshOffSet_tdd", "#noiseFactor_tdd",
			"#thresholdInDensity_tdd", "#thresNumValidin_tdd", "#thresholdInIntensityLow_tdd",
			"#thresholdInIntensityMid_tdd", "#thresholdInIntensityHigh_tdd", };// 干扰程度检测设置
	public static final String RadioMid = "#Mid_tdd";
	public static final String RadioHigh_tdd = "#High_tdd";
	public static final String RBoxRrcAnalyCheck = "#isRrcAnalyCheck";
	public static final String[] TextRrcAnalyCheck = { "#thresRrcFail", "#thresRrcSucc", "#thresRrcNoreply",
			"#thresRrcNok" }; // RRC建立成功率检测
	public static final String RBoxTDDOtherCheck = "#TDDOtherCheck";
	public static final String[] TextTDDOtherCheck = { "#loclePHSFrequency_tdd", "#limitXPower_tdd",
			"#thresholdRegionCorr_tdd" };// 其他信息
	public static final String BtnPmrSetOk = "#tdd_Parameter_Setting_OKButton";
	public static final String[] TextType = { "#WindowHalfWidth", "#ThresholdRicTimeCorr",
			"#ThresholdRicTimeCorrFlowPersent", "#InDiffOffSet", "#InDiffThreshold" };// 干扰类型识别
	public static final String BtnPCIPmrSetOk = "#pci_Parameter_Setting_OKButton";
	// FDD互调干扰检测
	public static final String RBoxFddPimAnalyCheck = "#isFddPimAnalyCheck";
	public static final String[] TextFddPimAnalyCheck = { "#minPwrDiffForFddpim", "#intermodulationUpliftThreshold",
			"#intermodulationInterferenceProbability" };
	public static final String BtnFDDPmrSetOk = "#fdd_Parameter_Setting_OKButton";

	public static final String BtnCfg = "ConfigIdSelect";
	public static final String BtnPerf = "daPfmIdSelect";
	public static final String BtnCSV = "daPfmcsvIdSelect";
	public static final String BtnPare = "parasIdSelect";
	public static final String Btnchr = "cellricchrIdSelect";

	public static final String BtnDownReport = "a[id=\"downloadReportsId0\"]";
	public static final String BtnDownReportT = "a[id=\"downloadReportsId1\"]";
	public static final String BtnCommit = "#commit_Tasknew";
	private static final String BtnCancel = "#cancel_Task";

	// 切换到新建任务页签
	public static void PrjTaskClick(WebDriver driver) {
		LOG.info_aw("进入射频插件界面点击任务任务页签");
		CommonJQ.click(driver, BtnPrjTask, true);
	}

	// 点击新建任务按钮
	public static void NewCreatTask(WebDriver driver) {
		LOG.info_aw("点击新建任务");
		CommonJQ.click(driver, CreateTask, true);
	}

	// 输入任务名称
	public static String setTaskName(WebDriver driver, String taskName) {
		taskName = taskName + "_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime() + "TaskName:" + taskName);
		return taskName;
	}

	public static void GanRaoShiBie(WebDriver driver) {
		LOG.info_aw("场景设置：干扰识别");
		CommonJQ.click(driver, RadioGanRaoShibie, true);
	}

	public static void SceneFdd(WebDriver driver) {
		LOG.info_aw("制式：FDD");
		CommonJQ.click(driver, RadioFdd, true);
	}

	public static void ParameterSet(WebDriver driver) {
		LOG.info_aw("点击参数设置");
		CommonJQ.click(driver, BtnParameterSet, true);
	}

	public static void PmrSetValue(WebDriver driver, String selector, String value) {
		CommonJQ.value(driver, selector, value, "修改失败");
	}

	public static void PmrSetRadioMid(WebDriver driver) {
		LOG.info_aw("干扰程度不达标门限：中");
		CommonJQ.click(driver, RadioMid, true);
	}

	public static void PmrSetRadioHigh(WebDriver driver) {
		LOG.info_aw("干扰程度不达标门限：高");
		CommonJQ.click(driver, RadioHigh_tdd, true);
	}

	public static void PmrSetRrcChoose(WebDriver driver) {
		LOG.info_aw("RRC建立成功率检测:勾选");
		CommonJQ.click(driver, RBoxRrcAnalyCheck, true);
	}

	public static void CancelOtherCheck(WebDriver driver) {
		LOG.info_aw("其他信息:取消勾选");
		CommonJQ.click(driver, RBoxTDDOtherCheck, true);
	}

	public static void CancelFddCheckPmrSet(WebDriver driver) {
		LOG.info_aw("FDD互调干扰检测:取消勾选");
		CommonJQ.click(driver, RBoxFddPimAnalyCheck, true);
	}

	public static void PmrSetOk(WebDriver driver) {
		if (CommonJQ.isExisted(driver, BtnPCIPmrSetOk)) {
			CommonJQ.click(driver, BtnPCIPmrSetOk, true);
		}
		if (CommonJQ.isExisted(driver, BtnPmrSetOk)) {
			CommonJQ.click(driver, BtnPmrSetOk, true);
		}
		if (CommonJQ.isExisted(driver, BtnFDDPmrSetOk)) {
			CommonJQ.click(driver, BtnFDDPmrSetOk, true);
		}
	}

	public static void ClickDownReport(WebDriver driver, String select) {
		CommonJQ.click(driver, select, true, "报告下载按钮");
	}

	public static String GetText(WebDriver driver, String select) {
		String Controlname = CommonJQ.text(driver, select, "");
		return Controlname;
	}

	public static boolean DownReportExist(WebDriver driver, String select) {
		return CommonJQ.isExisted(driver, select);
	}

	public static void CommitBtnClick(WebDriver driver) {
		CommonJQ.click(driver, BtnCommit, true);
	}

	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCancel, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
