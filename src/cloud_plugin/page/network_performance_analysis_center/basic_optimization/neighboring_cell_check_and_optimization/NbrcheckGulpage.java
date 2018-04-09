package cloud_plugin.page.network_performance_analysis_center.basic_optimization.neighboring_cell_check_and_optimization;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class NbrcheckGulpage {

	public static final String NbrCheck = "span[class=\"neighbor\"]";//插件
	public static final String TASKNAME_ID = "#taskName";// 任务名称
	public static final String BtnParameterSet = "#parameter_setting";// 参数设置		
	public static final String LinkLTL = "#GULL2LIsEnabletit";//LTE到LTE邻区核查
	public static final String LinkLTG = "#GULL2GIsEnabletit";
	public static final String LinkLTU = "#GULL2UIsEnabletit";
	//LTE到LTE邻区核查
	public static final String RBtnLtoLAll = "#GULL2LIsEnable";
	public static final String RBtnL2LIsSignalCheck = "#L2LIsSignalCheck";//单向邻区检查
	public static final String RBtnL2LIsRedundancyCheck = "#L2LIsRedundancyCheck";//冗余外部小区检查
	public static final String RBtnL2LIsOuterSetting = "#L2LIsOuterSetting";//外部小区参数一致性检查
	public static final String RBtnL2LforbidSwitchNeCheck = "#L2LforbidSwitchNeCheck";//禁止切换的邻区核查
	public static final String RBtnL2LinvalidNCellCheck = "#L2LinvalidNCellCheck";// 无效邻区核查
	public static final String RBtnL2LNCellParamCheck = "#L2LNCellParamCheck";//邻区参数核查
	public static final String RBtnl2LnonPolicyCheck = "#l2LnonPolicyCheck";//不满足策略邻区关系核查
	public static final String RBtnNBRPCIConfusionCheck = "#NBRPCIConfusionCheck";//邻区PCI混淆核查
	public static final String RBtnNBRPCIMod30Check = "#NBRPCIMod30Check";//邻区同频同PCI MOD 30 核查
	public static final String TextNBRPCIMod30DistanceThr = "#NBRPCIMod30DistanceThr";
	public static final String RBtnPCIMod30Check = "#PCIMod30Check";//同频同PCI MOD 30 核查
	public static final String TextPCIMod30DistanceThr = "#PCIMod30DistanceThr";
	public static final String RBtnNBRPCIReusedDistanceCheck = "#NBRPCIReusedDistanceCheck";//同频同PCI复用距离核查
	public static final String TextPCIReusedDistanceThr = "#PCIReusedDistanceThr";
	public static final String RBtnl2LmissNeByANRModeCheck = "#l2LmissNeByANRModeCheck";//漏配邻区核查
	public static final String Btnl2LmissNeByANRModeFileName = "#l2LmissNeByANRModeFileName";
	public static final String RBtnL2LIsUltraCheck= "#L2LIsUltraCheck";//超远邻区检查
	public static final String RBtnL2LIsUltraCheckPamerSet[]={"#L2LIsUltraThreshold","#l2lIsUltraThresholdSuburb","#l2LhoattField","#l2LhosuccField","#l2LloopField"};

	public static final String RBtnL2LIsNeighborHighOrLow= "#L2LIsNeighborHighOrLow";//邻区过多过少检查
	public static final String TextL2LSameFreqHigh[] = {"#L2LSameFreqHigh","#L2LSameFreqLow","#L2LdiffFreqHigh","#L2LdiffFreqLow"};
	
	public static final String RBtnL2LDistanceThreshouldChecked = "#L2LDistanceThreshouldChecked";//距离门限
	public static final String ListL2LDistanceThresholdBiggerthan = "select[id=\"L2LDistanceThresholdBiggerthan\"]";
	public static final String ListXiaoYu = "option[value=\"LT\"]";

		
	public static final String TextL2LdistanceThreshold = "#L2LdistanceThreshold";
	public static final String RBtnL2LIsMissingGISCheck = "#L2LIsMissingGISCheck";// 漏配邻区检查
	public static final String TextL2LMissingGISSet[] = {"#L2LSameFreqNum","#L2LSameFreqDistance","#L2LdiffFreqNum","#L2LdiffFreqDistance","#L2LDistanceThres","#L2LIntraFreqHONoNRT","#L2LInterFreqHONoNRT"};
	public static final String BtnokButton = "span[id=\"ok_Button\"]";
	public static final String BtnterminalOk = "span[class=\"l-btn-text\"]";
	//LTE到G邻区核查
	public static final String RBtnLtoGAll = "#GULL2GIsEnable";	
	public static final String RBtnL2GIsSignalCheck = "#L2GIsSignalCheck";//单向邻区检查	
	public static final String RBtnL2GIsRedundancyCheck = "#L2GIsRedundancyCheck";//冗余外部小区检查	
	public static final String RBtnL2GIsOuterSetting = "#L2GIsOuterSetting";//外部小区参数一致性检查	
	public static final String RBtnL2GforbidSwitchNeCheck = "#L2GforbidSwitchNeCheck";//禁止切换的邻区核查	
	public static final String RBtnL2GNCellParamCheck = "#L2GNCellParamCheck";//邻区参数核查	
	public static final String RBtnl2GnonPolicyCheck = "#l2GnonPolicyCheck";// 不满足策略邻区关系核查	
	public static final String RBtnisMscPoolCheck = "#isMscPoolCheck";//跨MSC POOL邻区和频点冲突核查	
	public static final String RBtnL2GIsUltraCheck = "#L2GIsUltraCheck";//超远邻区检查	
	public static final String RBtnL2GIsUltraCheckPmSet[] = {"#L2GIsUltraThreshold","#l2GIsUltraThresholdSuburb"};
	public static final String RBtnL2GIsNeighborHighOrLow = "#L2GIsNeighborHighOrLow";	//邻区过多过少检查
	public static final String RBtnL2GIsNeighborHighOrLowPmSet[]={"#L2GFreqHigh","#L2GFreqLow"};

	public static final String RBtnL2GIsMissingGISCheck = "#L2GIsMissingGISCheck";//漏配邻区检查
	public static final String RBtnL2GIsMissingGISCheckPmSet[] ={"#L2GFreqNum","#L2GFreqDistance","#L2GDistanceThres"};
	public static final String RBtnl2GisGSM900FirstCheck = "#l2GisGSM900FirstCheck";
	//LTE到U邻区核查
	public static final String RBtnLtoUAll = "#GULL2UIsEnable";	
	public static final String RBtnL2UIsSignalCheck = "#L2UIsSignalCheck";//单向邻区检查	
	public static final String RBtnL2UIsRedundancyCheck = "#L2UIsRedundancyCheck";//冗余外部小区检查	
	public static final String RBtnL2UIsOuterSetting = "#L2UIsOuterSetting";//外部小区参数一致性检查	
	public static final String RBtnL2UforbidSwitchNeCheck = "#L2UforbidSwitchNeCheck";//禁止切换的邻区核查	
	public static final String RBtnL2UNCellParamCheck = "#L2UNCellParamCheck";//邻区参数核查	
	public static final String RBtnl2UnonPolicyCheck = "#l2UnonPolicyCheck";// 不满足策略邻区关系核查	
	public static final String RBtnL2UIsUltraCheck = "#L2UIsUltraCheck";//超远邻区检查	
	public static final String RBtnL2UIsUltraCheckPmSet[] ={"#L2UIsUltraThreshold","#l2UIsUltraThresholdSuburb"};

	public static final String RBtnL2UIsNeighborHighOrLow = "#L2UIsNeighborHighOrLow";//邻区过多过少检查	
	public static final String RBtnL2UIsNeighborHighOrLowPmSet[] = {"#L2UFreqHigh","#L2UFreqLow"};

	public static final String RBtnL2UIsMissingGISCheck = "#L2UIsMissingGISCheck";// 漏配邻区检查
	public static final String RBtnL2UIsMissingGISCheckPmSet[] = {"#L2UFreqNum","#L2UFreqDistance","#L2UDistanceThres"};
	//选择数据
	public static final String BtnlteConfigParams = "#lteConfigParams";	
	public static final String BtnlteEPParams = "#lteEPParams";	
	public static final String BtnumtsParaParams = "#umtsParaParams";	
	public static final String BtnGSMEPParams = "#GSMEPParams";	
	public static final String BtnPFMParams = "#PFMParams";	
	public static final String BtnmobilityPolicyFileName = "#mobilityPolicyFileName";	
	public static final String BtnumtsConfigParams = "#umtsConfigParams";	
	public static final String BtngsmConfigParams = "#gsmConfigParams";			
	public static final String commitTasknew_ID="#commit_Tasknew";
	
	
	
	public static String setTaskName(WebDriver driver, String taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName + "_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TASKNAME_ID);
		CommonJQ.value(driver, TASKNAME_ID, taskName);
		String text = CommonJQ.getValue(driver, TASKNAME_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TASKNAME_ID, taskName);
		}
		System.out.println(ZIP.NowTime() + "TaskName:" + taskName);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, commitTasknew_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}



}
