package cloud_plugin.page.network_performance_analysis_center.network_planning.lte_trafficforecast;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class LteTrafficForecastPage {
    //基本信息
	private static final String TextTaskName = "#taskName";//Task Name
	//业务模块选择
	public static final String CBoxBasic = "input[name=\"lteTask.basicBasicForecast\"]";//基础话务预测 
	public static final String CBoxDepress ="input[name=\"lteTask.basicTrafficDepress\"]"; //话务抑制还原
	public static final String CBoxStarTerm = "input[name=\"lteTask.basicCheckRefluxScene\"]";//回流场景预测
	public static final String CBoxGradUser ="input[name=\"lteTask.basicGradUserDeve\"]";//用户发展预测	
	public static final String CBoxSeparate = "input[name=\"lteTask.separateDataScene\"]";//分业务流量预测
	public static final String CBoxFuncArea = "input[name=\"lteTask.greatEventCheckBox\"]";//重大事件预测	
	public static final String CBoxulRefluxScene = "input[name=\"lteTask.ulRefluxScene\"]";//UL回流场景
	public static final String CBoxGLRefluxScene = "input[name=\"lteTask.glRefluxScene\"]";//GL回流场景
	//分析数据
	public static final String BtnGCfg = "select_gconfData";
	public static final String BtnUCfg = "select_uconfData";
	public static final String BtnLCfg = "select_lconfData";
	public static final String BtnGpara = "select_gparaData";
	public static final String BtnUpara = "select_uparaData";
	public static final String BtnLpara = "select_lparaData";
	public static final String BtnGperf = "select_gperfData";
	public static final String BtnUperf = "select_uperfData";
	public static final String BtnLperf = "select_lperfData";
	public static final String BtnGchr = "select_gchrData";
	public static final String BtnGmr = "select_gmrData";
	public static final String BtnUmr = "select_umrData";
	public static final String BtnterminalData = "#terminalData";
	public static final String BtnterminalOk = "span[class=\"l-btn-text\"]";
	
	public static final String BtnGprsPerf = "select_gprsPerfData";
	public static final String RBoxUprsPerf = "select_uprsPerfData";
	public static final String BtnLprsPerf = "select_lprsPerfData";
	public static final String BtnfenyewuliuliangData = "select_targetSeperateData";
	public static final String BtnNeGroupData = "input[id=\"targetNeGroupData\"]";
	public static final String RBoxdefaultTerminal = "em:contains(\"默认终端数据\")";//默认为终端数据
	public static final String RBtnNeGroupData = "input[ng-click=\"defaultTermClick(item)\"]";
	
	public static final String BtnterminalFileDownload= "#select_terminalFileTemplateDownload";//终端数据 模板下载
	public static final String BtngprsPerfDownload = "#select_gprsPerfDataTemplateDownload";//GSM PRS话统  模板下载
	public static final String BtnuprsPerfDownload = "#select_uprsPerfDataTemplateDownload";//UMTS PRS话统 模板下载
	public static final String BtnlprsPerfDownload = "#select_lprsPerfDataTemplateDownload";//LTE PRS话统 模板下载

	public static final String BtnSeperateDownload = "#select_targetSeperateDataTemplateDownload";//分业务流量表  模板下载
	public static final String BtnNeGroupDataDownload = "#select_targetNeGroupDataTemplateDownload";//网元组  模板下载
    //公共参数
	public static final String BtntargetHolidayTableData = "#targetHolidayTableData";//节假日表 选择文件 按钮		
	public static final String BtnHolidayDownload= "#select_targetHolidayTableDataTemplateDownload";//节假日表  模板下载	
	public static final String BtnYuCeTime = "input[name=\"lteTask.futDate\"]";//预测时间
	//日常话务预测——基础指标设置	
	public static final String RBtnCell = "input[value=\"CELL\"]";
	public static final String RBtnGroup = "input[value=\"GROUP\"]";
	public static final String BtnAllZhiBiao= "a:contains(\"所有指标\")";	
	public static final String BtnMoRenZhiBiao= "a:contains(\"默认指标\")";
	public static final String BtnSeaarchZhibiao = "input[ng-model=\"searchKpi\"]";//指标搜索按钮
	public static final String RBoxChooseZhibiao = "div[class=\"ml boxMove\"] div ul label[class=\"ng-binding taskAddSelectTaskCheckboxUnchecked\"]";//index(0~)
	public static final String RBoxChooseZhibiaoLeft = "div[class=\"boxMove mr\"] div ul label[class=\"ng-binding taskAddSelectTaskCheckboxUnchecked\"]";//index(0~)
	public static final String BtnRight="input[value=\">>\"]";   
	public static final String BtnLeft="input[value=\"<<\"]"; 
	public static final String BtnParameterSetOk = "span[class=\"btnGreen mr ng-binding\"]";
	public static final String RBtnAddYinZi = "span[class=\"fr\"] label input";

	
	
	//***LTE PRS话统字段匹配
	public static final String RBtnENODEB = "input[name=\"lteTask.lselectedNeType\"]";
	public static final String ListenodeBName = "input[id=\"lteTask.enodeBName\"]";
	public static final String ListChooseenodeBName = "ul[id=\"mshowlteTask.enodeBName\"] li[id=\"eNodeBName\"]";
	public static final String ListlCellName = "input[id=\"lteTask.lCellName\"]";
	public static final String ListChooseCellName = "ul[id=\"mshowlteTask.lCellName\"] li[id=\"CELLNAME\"]";
	public static final String ListChooseCellName1 = "ul[id=\"mshowlteTask.lCellName\"] li[id=\"CellName\"]";
	
	public static final String ListlTime = "input[id=\"lteTask.lTime\"]";
	public static final String ListChooselTime = "ul[id=\"mshowlteTask.lTime\"] li[id=\"Time\"]";
	public static final String ListChooselTime1 = "ul[id=\"mshowlteTask.lTime\"] li[id=\"TIME\"]";
	public static final String ListlSubscribers = "input[id=\"lteTask.lSubscribers\"]";
	public static final String ListChooselSubscribers = "ul[id=\"mshowlteTask.lSubscribers\"] li[id=\"Subscribers\"]";
	public static final String ListlselectList = "input[id=\"lteTask.lselectList\"]";
	public static final String ListChoosellselectList = "ul[id=\"mshowlteTask.lselectList\"] li input[id=\"allSelect\"]";
	public static final String ListlLACCI = "input[id=\"lteTask.lLACCI\"]";	
	public static final String ListChooselLACCI = "ul[id=\"mshowlteTask.lLACCI\"] li[id=\"LACCI\"]";		
	public static final String Loading = "div[class=\"mask_loading\"]";	
	public static final String LACCI = "span:contains(\"LACCI\")";	
	
	//***网元组
	public static final String RBtnCellGroup = "input[name=\"lteTask.neType\"]";
	public static final String ListgroupType = "input[id=\"lteTask.groupType\"]";//选择网元组类型
	public static final String ListChooseType = "ul[id=\"mshowlteTask.groupType\"] li[id=\"CELL\"]";
	//话务抑制还原
	public static final String BtnShouDoSet = "input[name=\"lteTask.depressParamSetting\"]";//手动参数设置   prev("label")
	public static final String TextYiZhi[]={"#deprAreaPrbUsageThreshold","#deprAreaAverageUserThreshold","#deprAreaUserSpeedThreshold"};

	//回流场景预测
	public static final String BtnTargetBoss = "#targetBossBillData";
	public static final String HuiLiuSetParemeter[]={"#tafficRefluxFactor2GTo4G","#trafficRefluxFactor3GTo4G","#psFactor2GTo4G","#psFactor3GTo4G"};

	//***Gsm PRS字段匹配——
	public static final String ListGtchTraffic = "input[id=\"lteTask.tchTraffic\"]";
	public static final String ListChooseGtchTraffic = "ul[id=\"mshowlteTask.tchTraffic\"] li[id=\"CELLKPITCHTRAFERLTRAF\"]";
	public static final String ListgCellName = "input[id=\"lteTask.gCellName\"]";
	public static final String ListChoosegCellName = "ul[id=\"mshowlteTask.gCellName\"] li[id=\"CELLName\"]";
	public static final String ListGbtsName = "input[id=\"lteTask.btsName\"]";
	public static final String ListChooseGbtsName = "ul[id=\"mshowlteTask.btsName\"] li[id=\"BTSName\"]";
	public static final String ListGbscName = "input[id=\"lteTask.bscName\"]";
	public static final String ListChooseGbscName = "ul[id=\"mshowlteTask.bscName\"] li[id=\"BSCName\"]";
	public static final String ListgTime = "input[id=\"lteTask.gTime\"]";
	public static final String ListChoosegTime = "ul[id=\"mshowlteTask.gTime\"] li[id=\"Time\"]";
	public static final String ListgLACCI = "input[id=\"lteTask.gLACCI\"]";
	public static final String ListChoosegLACCI = "ul[id=\"mshowlteTask.gLACCI\"] li[id=\"LACCI\"]";
	public static final String ListgselectList = "input[id=\"lteTask.gselectList\"]";
	public static final String ListChoosegselectList = "ul[id=\"mshowlteTask.gselectList\"] li input[id=\"allSelect\"]";
	//***Umts PRS字段匹配——
	public static final String ListUrncName = "input[id=\"lteTask.rncName\"]";
	public static final String ListChooseUrncName = "ul[id=\"mshowlteTask.rncName\"] li[id=\"RNCNAME\"]";
	public static final String ListUnodeBName = "input[id=\"lteTask.nodeBName\"]";
	public static final String ListChooseUnodeBName = "ul[id=\"mshowlteTask.nodeBName\"] li[id=\"NODEBNAME\"]";
	public static final String ListuCellName = "input[id=\"lteTask.uCellName\"]";
	public static final String ListChooseuCellName = "ul[id=\"mshowlteTask.uCellName\"] li[id=\"CELLNAME\"]";
	public static final String ListuTime = "input[id=\"lteTask.uTime\"]";
	public static final String ListChooseuTime = "ul[id=\"mshowlteTask.uTime\"] li[id=\"Time\"]";
	public static final String ListuSubscribers = "input[id=\"lteTask.uSubscribers\"]";
	public static final String ListChooseuSubscribers = "ul[id=\"mshowlteTask.uSubscribers\"] li[id=\"Subscribers\"]";
	public static final String ListuLACCI = "input[id=\"lteTask.uLACCI\"]";
	public static final String ListChooseuLACCI = "ul[id=\"mshowlteTask.uLACCI\"] li[id=\"LACCI\"]";
	public static final String ListuselectList = "input[id=\"lteTask.uselectList\"]";
	public static final String ListChooseuselectList = "ul[id=\"mshowlteTask.uselectList\"] li input[id=\"allSelect\"]";			
	//用户发展预测
	public static final String UserDevelopPlan[] ={"#userDevelopPlan","#historyUserNum"};
	//分业务流量预测
	public static final String CBoxkpiTreecheck = "span[id=\"kpiTree_1_check\"]";//方法中 1作为参数输入（1~25）
	public static final String BtnHourList ="li[ng-repeat=\"hour in hourList\"]";//方法中使用时 用eq（0~23）
	//重大事件预测
	public static final String CBoxsceneTreecheck = "span[id=\"sceneTree_1_check\"]";//方法中 1作为参数输入（1~18）
	//用户数预测  
	public static final String TextgreatEventCount[] ={"#greatEventGroundCount","#greatEventNetTypeRatio","#greatEventMarketRatio"};

	
	public static final String BtnNext = "span:contains(\"下一步\")";
	public static final String BtnCommit = "span[ng-click=\"submit()\"]";
	public static final String BtnCancel = "span:contains(\"取消\")";
	
	
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


}
