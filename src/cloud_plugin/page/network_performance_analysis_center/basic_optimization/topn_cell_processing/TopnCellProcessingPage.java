package cloud_plugin.page.network_performance_analysis_center.basic_optimization.topn_cell_processing;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class TopnCellProcessingPage {

	public static final String TASKNAME_ID = "#taskName";// 任务名称
	public static final String ThroughputSF_ID = "#Throughput_SF";// 吞吐率
	public static final String LostTo_ID = "#Lost_To";// 掉线
	public static final String AccessRRC_ID = "#Access_RRC";// 接入
	public static final String Switching_ID = "#Switch_ing";// 切换
	
	public static final String parametersetting_ID = "#parameter_setting";// 参数设置
	public static final String everyUserThroughput_ID = "#everyUserThroughput";// 每用户平均吞吐率
	public static final String estateThroughput_ID = "#estateThroughput";// 小区下行平均吞吐率

	public static final String selecteNodeBConfigFile_ID = "#select_eNodeBConfigFile";// 配置数据   //内部CHR
	public static final String selectPData = "#select_PData";// 话统数据
	public static final String selectLteprojectparamter_ID = "#select_Lteprojectparamter";// 工参数据
	public static final String selectWarn_ID = "#select_Warn"; // 告警数据
	public static final String selectoperate_ID = "#select_operate"; // 操作日志
	public static final String selectMrData_ID = "#select_MrData"; // 外部CHR	
	public static final String commitTasknew_ID ="#commit_Tasknew";//提交	
	//参数设置（掉线）
	public static final String TOPNUM ="input[value=\"TOPNUM\"]"; //Top小区数 
	public static final String TOPNUM_ID ="#TOPNUM"; //Top小区数  参数值
	public static final String TOPPROPORTION ="input[value=\"TOPPROPORTION\"]"; //Top小区比例
	public static final String paramLabelBlank ="label[class=\"paramLabel paramLabelBlankInput\"]"; //Top小区比例  参数值
	public static final String isContinueAnalyze ="input[ng-model=\"lost.isContinueAnalyze\"]";//簇级指标达到门限，继续处理未达标小区 
	public static final String NOT_CONTAINING_MME ="input[value=\"NOT_CONTAINING_MME\"]";// 掉线公式 （不含MME异常原因）
	public static final String CONTAINING_MME ="input[value=\"CONTAINING_MME\"]";//掉线公式 （含MME异常原因）
	public static final String GaoJiCanShu ="span[class=\"tit ng-binding\"]";//高级参数
	public static final String allMR ="#allMR";//全MR分析
	public static final String abnormalMR ="#abnormalMR";//异常MR分析
	public static final String okButton ="span[id=\"ok_Button\"]";//参数设置中的 确定  按钮	
	//参数设置（接入）
	public static final String ContainS1 ="input[ng-model=\"lost.containS1\"]";//包含S1
	public static final String SuccessRate ="input[ng-model=\"lost.dropType\"]";//接入成功率公式
	public static final String accessTOPNUM_ID ="#accessTOPNUM"; //Top小区数  参数值
	public static final String access_TOPPROPORTION ="#access_TOPPROPORTION"; //Top小区比例  参数值
	//参数设置（切换）
	public static final String switchingTOPNUM ="#switchingTOPNUM"; //Top小区数 switchingTOPNUM
	public static final String TOPCUSTOM ="input[value=\"TOPCUSTOM\"]"; //Top小区自动筛选 
	public static final String isContinueAnalyzeqiehuan ="input[ng-model=\"switching.isContinueAnalyze\"]";//簇级指标达到门限，继续处理未达标小区
	public static final String TongPinQieHuan ="input[ng-model=\"switching.intraFreqHo\"]";//同频切换
	public static final String TongPinQieHuanSuccessRate ="input[id=\"switching_TOPPROPORTION1\"]";//同频切换成功率
	public static final String ZhiBiaoChoose ="span[class=\"ng-binding\"]:contains(\"指标选择\")";//指标选择
	public static final String YiPinQieHuan ="input[ng-model=\"switching.interFreqHo\"]";//异频切换
	public static final String YiPinQieHuanSuccessRate ="input[id=\"switching_TOPPROPORTION2\"]";//异频切换成功率	
	public static final String TPZhiBiao1 ="input[name=\"taskBean.switching.dropType\"]";//同频指标1，eq(1)~eq(3);异频指标为eq(0)~eq(7)
	public static final String TongPinOk ="#formulaChooseConfirm";//同频异频指标选择确定按钮
	public static final String allMR2 ="#allMR2";//全MR分析
	public static final String abnormalMR2 ="#abnormalMR2";//异常MR分析
	//吞吐率修改门限
	public static final String[] ThroughPutgate ={"#topnCellNumID","#transDelayID","#scheduleLatencyID",
		"#prbOccupancyID","#upInterferenceID","#downlinkCQIID","#linkAnomalyID","#unclaimedClothID","#noMasteServerRSRP_ID",
		"#pilotPollutionRSRP_ID","#DIROCTSO_ThresholdID","#WCROCSO_ThresholdID","#transLossID","#packetLossRateID",
		"#cceOccupancyID","#cpuUtilizationID","#downlinkRSRPID","#powerfulNeighborID","#depth_TA_ID","#interferenceRSRP_ID",
		"#pilotPollutedNeID","#DIROSO_ThresholdID","#WCROSO_ThresholdID"};
	public static final String[] Disconnectgate ={"#topTmsiThdID","#ulBadcovRsrpThdID","#dlBadcovMissRsrpThdID","#dlBadcovCQIThdID",
		"#dlBadcovPathLossDifThdID","#ulBadcovVTAThdID","#dlInterfPPRsrpDifThdID","#ulInterfULsinrThdID",
		"#srsDaysThdID","#pucchDaysThdID","#pdcchDaysThdID","#prachDaysThdID","#prbDLUSedaveThdID",
		"#cpuRateOverThdID","#topFgiThdID","#ulBadcovSinrThdID","#dlBadcovRsrpLThdID","#dlBadcovRsrpHThdID",
		"#ulBadcovDTAThdID","#dlBadcovNoServerRsrpDifThdID","#ulInterfULdmrsRsprThdID","#srsResRateThdID",
		"#pucchResRateThdID","#pdcchResRateThdID","#prachRateThdID","#prbULUsedaveThdID","#cpuRateAveThdID","#cpuDaysThdID"};
	//切换 参数门限值
	public static final String[] Accessgate ={"#topTmsiThdID1","#topTmsiThdID2","#topTmsiThdID3","#topTmsiThdID4",
		"#m1Id","#m3Id","#topFgiThdID1","#topFgiThdID2","#topFgiThdID3","#m0Id","#m2Id","#m4Id"};
	public static final String DiaoXianRadioBtnError ="优化对象：掉线单选按钮未找到";
	public static final String JieRuRadioBtnError ="优化对象：接入单选按钮未找到";
	public static final String QieHuanRadioBtnError ="优化对象：切换单选按钮未找到";
	public static final String SceneRadioBtnError ="场景选择：每用户平均吞吐率未找到";
	public static final String XiaoQuBiLiRadioBtnError ="参数设置：小区比例单选按钮未找到";
	public static final String ClusterLevelThreshodError ="参数设置： 簇级指标达到门限,继续处理未达标小区按钮未找到";
	public static final String IncloudMMLRadioBtnError ="参数设置： 含MME异常原因按钮未找到";	
	public static final String SetParameter ="修改参数";
	//UMTS TOPN 小区处理  界面元素
	public static final String CheckRabAccess="#taskTopicRab";  //RAB接入
	public static final String ChecktaskTopicRrc="#taskTopicRrc";//RRC接入
	public static final String ChecktaskTopicThroughput="#taskTopicThroughput";//吞吐率
	public static final String ChecktaskTopicInterRatHo="#taskTopicInterRatHo";//异系统切换
	public static final String ChecktaskTopicCallDrop="#taskTopicCallDrop";//掉话
	//UMTS TOPN 小区处理  参数设置
	//RAB接入
	public static final String LinkRABaccess="span[class=\"tit ng-binding\"]:contains(RAB接入)";
	public static final String[] TextRABaccess={"#csRabSucc","#psRabSucc","#psR99RabSucc","#hsdpaRabSucc","#hsupaRabSucc"};
	//RRC接入
	public static final String LinkRRCaccess="span[class=\"tit ng-binding\"]:contains(RRC接入)";
	public static final String TextAccessSuccessRate="#rrcSsr";
	//吞吐率
	public static final String LinkTuntulv="span[class=\"tit ng-binding\"]:contains(吞吐率)";
	public static final String[] TextThroughOut={"#hsdpaThreshold","#hsupaThreshold"};

	//异系统切换
	public static final String LinkQiehuan="span[class=\"tit ng-binding\"]:contains(异系统切换)";
	public static final String[] TextQiehuan={"#interRatCsSucc","#interRatPsSucc","#interRatCsPreSucc"};
	//掉话
    public static final String LinkDiaohua="span[class=\"tit ng-binding\"]:contains(掉话)";
    public static final String[] TextDisconnection={"#callDropCs","#callDropPs","#callDropHsdpa","#callDropHsupa"};

	//UMTS  选择文件  
	public static final String selectconfData_ID = "#select_confData";// 配置数据  
	public static final String selectengineerParaData = "#select_engineerParaData";// 工参数据
	public static final String selectperfData_ID = "#select_perfData";// 话统数据
	public static final String selectchrData_ID = "#select_chrData"; // PCHR数据
	public static final String selectmrData_ID = "#select_mrData"; // mr数据
	public static final String selectnodebConfData_ID = "#select_nodebConfData"; // NodeB配置数据	
	public static final String selectnodebPerfData_ID ="#select_nodebPerfData";//NodeB M2000话统数据
	public static final String selectnodebLicenceData_ID = "#select_nodebLicenceData";//NodeB License数据
	public static final String selectmmlData_ID = "#select_mmlData";//MML数据
	
	
	
	public static String setTaskName(WebDriver driver, String taskName) {
//		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName + "_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TASKNAME_ID);
		CommonJQ.value(driver, TASKNAME_ID, taskName);
		String text = CommonJQ.getValue(driver, TASKNAME_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TASKNAME_ID, taskName);
		}
		System.out.println(ZIP.NowTime() + "TaskName:" + taskName);
//		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, commitTasknew_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}



}
