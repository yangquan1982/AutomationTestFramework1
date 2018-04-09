package cloud_plugin.page.network_performance_analysis_center.network_planning.traffic_forecast;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class TrafficForecastPage {

	private static final String TextTaskName = "#taskName";//Task Name
	public static final String CBoxBasic = "input[ng-model=\"umtsTask.basic_BasicForecast\"]";//基础话务预测
	public static final String CBoxDepress = "input[ng-model=\"umtsTask.basic_TrafficDepress\"]";//话务抑制还原
	public static final String CBoxStarTerm = "input[ng-model=\"umtsTask.basic_StarTermDeve\"]";//明星终端发展预测
	public static final String CBoxGradUser = "input[ng-model=\"umtsTask.basic_GradUserDeve\"]";//用户发展预测
	
	public static final String CBoxSeparate = "input[ng-model=\"umtsTask.separatedata_Scene\"]";//分业务流量预测
	public static final String CBoxFuncArea = "input[ng-model=\"umtsTask.funcAreaDeve_CheckBox\"]";//功能区发展预测
	public static final String CBoxGreatEven = "input[ng-model=\"umtsTask.greatEvent_CheckBox\"]";//重大事件预测
	
	private static final String BtnNext = "span[ng-click=\"next()\"]";
	private static final String BtnCancel = "span[ng-click=\"cancel()\"]";
	public static final String BtnPrevious = "span[ng-click=\"previous()\"]";	
	private static final String BtnCommit = "span[ng-click=\"submit()\"]";
	
	public static final String BtnCfg = "select_confData";
	public static final String BtnPef = "select_perfData";
	public static final String BtnChr = "select_chrData";
	public static final String BtnTerminal = "#terminalData";
	public static final String RBoxdefaultTerminal = "input[name=\"umtsTask.defaultTerminalData\"]";
	public static final String BtnU2000Pef = "select_u2000PerfData";
	public static final String BtnPrsPef = "select_prsPerfData";
	public static final String BtnMinuteTrafficData = "select_targetSeperateData";
	
	public static final String RBoxPRSUCELL = "div[class=\"cantSelect\"] input[value=\"UCELL\"]";
	public static final String RBoxPRSRNC = "div[class=\"cantSelect\"] input[value=\"RNC\"]";
	
	public static final String TextRNCName = "input[id=\"umtsTask.rncName\"]";
	public static final String ListRNCName = "ul[id=\"mshowumtsTask.rncName\"] li[title=\"﻿﻿﻿﻿RNCName\"]";
	
	public static final String TextNodeBName = "input[name=\"umtsTask.nodeBName\"]";
	public static final String ListNodeBName = "li[id=\"﻿﻿NodeBName\"]";
	
	public static final String TextCellName = "div[class=\"selectLine\"] input[name=\"umtsTask.cellName\"]";
	public static final String ListCellName = "ul[id=\"mshowumtsTask.cellName\"] li[id=\"﻿﻿CellName\"]";

	public static final String TextTime = "div[class=\"selectLine\"] input[name=\"umtsTask.time\"]";
	public static final String ListTime = "ul[id=\"mshowumtsTask.time\"] li[id=\"﻿﻿Time\"]";
	
	public static final String Textsubscribers = "div[class=\"selectLine\"] input[name=\"umtsTask.subscribers\"]";
	public static final String ListItem_NB_0 = "ul[id=\"mshowumtsTask.subscribers\"] li[id=\"﻿﻿Item_NB_0\"]";

	public static final String TextList = "div[class=\"selectLine\"] input[name=\"umtsTask.selectList\"]";
	public static final String ListAll = "ul[id=\"mshowumtsTask.selectList\"] li[id=\"﻿﻿allSelect\"]";
	
	
	
	public static final String CBoxGroup = "input[name=\"umtsTask.neType\"]";
	
//	public static final String TextGroup = "div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String TextGroup = "input[name=\"umtsTask.groupType\"]";
	public static final String ListRNC  = "ul[id=\"mshowumtsTask.groupType\"] li[id=\"RNC\"]";
	public static final String ListNODEB  = "ul[id=\"mshowumtsTask.groupType\"] li[id=\"NODEB\"]";
	public static final String ListUCELL  = "ul[id=\"mshowumtsTask.groupType\"] li[id=\"UCELL\"]";
	
	public static final String BtnGroup = "#targetNeGroupData";
	
	public static final String BtnMessageOK = "div[class=\"messager-button\"] span[class=\"l-btn-text\"]";
	
	//公共参数
	public static final String BtnHoliday = "#targetHolidayTableData";
	public static final String BtnHolidayTemplat = "#select_targetHolidayTableDataTemplateDownload";
	public static final String TextModify = "input[name=\"umtsTask.modifyParameter\"]";
	public static final String TextFutDate = "input[name=\"umtsTask.futDate\"]";
	//日常话务预测，基础指标设置
	public static final String RBoxRNC = "input[value=\"RNC\"]";
	public static final String RBoxNodeB = "input[value=\"NODEB\"]";
	public static final String RBoxCell = "input[value=\"CELL\"]";
	public static final String RBoxGroup = "input[value=\"GROUP\"]";
	//指标选择
	public static final String BtnIndexData ="a[class=\"blueLink ng-binding\"]";
	
	public static final String TextSearch ="div[class=\"searchBox\"] input[class=\"inputCom ng-valid ng-dirty\"]";
	public static final String BtnSearch ="div[class=\"searchBox\"] i[class=\"search\"]";
	public static final String CBoxCalc ="input[ng-model=\"umtsTask.isCalculate\"]";
	
	public static final String BtnRight ="input[value=\">>\"]";
	public static final String BtnLeft ="input[value=\"<<\"]";
	
	public static final String BtnndexDataOK ="div[class=\"bottomLine\"] span[ng-click=\"calculateTree()\"]";
	public static final String BtnIndexDataCancel ="div[class=\"bottomLine\"] span[ng-click=\"closePop()\"]";
	
	//话务抑制还原
	public static final String TextTcpUsage = "input[name=\"umtsTask.deprAreaTcpUsageThreshold\"]";
	public static final String TextDcmUser = "input[name=\"umtsTask.deprAreaDcmUserThreshold\"]";
	public static final String TextHsdpaAve = "input[name=\"umtsTask.deprAreaHsdpaAveSpeedThreshold\"]";
	//明星终端发展预测
	public static final String TextStarTFore = "input[id=\"umtsTask.startForecastVendor\"]";
	public static final String TextStarTTerm = "input[name=\"umtsTask.startTermForecast\"]";
	public static final String TextPlanTermCount = "input[name=\"umtsTask.startPlanTermCount\"]";
	
	public static final String TextTermA = "input[name=\"umtsTask.startTermRef1\"]";
	public static final String TextTermB = "input[name=\"umtsTask.startTermRef2\"]";
	
	//功能区发展预测 
	public static final String TextTerminal1 = "input[id=\"umtsTask.funcAreaDeveRefTerm1\"]";
	public static final String TextTerminal2 = "input[id=\"umtsTask.funcAreaDeveRefTerm2\"]";
	public static final String TextTerminal3 = "input[id=\"umtsTask.funcAreaDeveRefTerm3\"]";
	
	public static final String BtnRibbonDevBusyHour = "ul[id=\"hourList2\"] li[class=\"ng-scope ng-binding\"]";
	//用户发展预测
	public static final String RBoxNoGradUser = "div[ng-show=\"umtsTask.basic_GradUserDeve\"] label";
	public static final String TextUserPlan = "input[name=\"umtsTask.userDevelopPlan\"]";
	public static final String TextHistoryNum = "input[name=\"umtsTask.historyUserNum\"]";

	public static final String RBoxGradUser = "div[ng-show=\"umtsTask.basic_GradUserDeve\"] label";
	public static final String TextHighUser = "input[name=\"umtsTask.userDeveHighUserRatio\"]";
	public static final String TextMedialUser = "input[name=\"umtsTask.userDeveMedialUserRatio\"]";
	public static final String TextLowUser = "input[name=\"umtsTask.userDeveLowUserRatio\"]";

	public static final String BtnGradUserBusyHour = "ul[id=\"hourList3\"] li[class=\"ng-scope ng-binding\"]";
	
	//分业务流量导入 
	
	public static final String CBoxALL = "span[id=\"kpiTree_1_check\"]";
	public static final String BtnMinuteTrafficBusyHour = "ul[id=\"hourList1\"] li[class=\"ng-scope ng-binding\"]";
	//重大事件预测 
	public static final String RBoxNoHisData = "input[name=\"umtsTask.greatEventBtnHasHisData\"]";
	public static final String CBoxGreatEventALL = "span[id=\"sceneTree_1_check\"]";
	
	public static final String RBoxHasHisData = "input[name=\"umtsTask.greatEventBtnHasHisData\"]";
	public static final String TextHisTimeStart = "input[name=\"umtsTask.greatEventhHisTimeStart\"]";
	public static final String TextHisTimeEnd = "input[name=\"umtsTask.greatEventhHisTimeEnd\"]";
	
	public static final String RBoxLTEHisData = "input[name=\"umtsTask.greatEventBtnHasHisData\"]";
	public static final String TextLTETimeStart = "input[name=\"umtsTask.greatEventuFlHisTimeStart\"]";
	public static final String TextLTETimeEnd = "input[name=\"umtsTask.greatEventuFlHisTimeEnd\"]";
	public static final String TextModelStar = "input[ng-model=\"umtsTask.greatEvent_TrafficModelStart\"]";
	public static final String TextModelEnd = "input[ng-model=\"umtsTask.greatEvent_TrafficModelEnd\"]";
	public static final String TextSubStart = "input[ng-model=\"umtsTask.greatEvent_SubscribersStart\"]";
	public static final String TextSubEnd = "input[ng-model=\"umtsTask.greatEvent_SubscribersEnd\"]";
		

	public static final String TextGroundCount = "input[name=\"umtsTask.greatEventGroundCount\"]";
	public static final String TextNetTypeRatio = "input[name=\"umtsTask.greatEventNetTypeRatio\"]";
	public static final String TextMarketRatio = "input[name=\"umtsTask.greatEventMarketRatio\"]";
	
	private static final String TitleCSS_CLASS = "span[class=\"titleCSS ng-binding\"] a[class=\"ng-binding\"]";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		taskName = taskName+"_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		return taskName;
	}
	
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCommit, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void nextBtnClick(WebDriver driver) {
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
