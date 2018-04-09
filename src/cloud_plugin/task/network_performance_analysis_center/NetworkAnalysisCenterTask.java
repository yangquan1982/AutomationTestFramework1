package cloud_plugin.task.network_performance_analysis_center;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.NetworkAnalysisCenterPage;
import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDTDataPage;
import cloud_plugin.page.network_performance_analysis_center.network_planning.network_audit.NetWorkAuditPage;
import cloud_plugin.page.network_performance_analysis_center.three_dimensional_network_evaluation.ThreeDNetWorkEvaPage;
import cloud_plugin.page.network_performance_analysis_center.volte_coverage_evaluation.VolteCoverageEvaluationPage;
import cloud_public.page.IndexPage;
import cloud_public.task.IndexTask;
import cloud_public.task.LoginTask;

public class NetworkAnalysisCenterTask {

	/**
	 * <b>Description:</b>打开LTE配置参数核查任务
	 */
	public static void openLTEconfigparamcheck(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.CfgPara_ClASS, "插件未找到，插件名：配置参数核查   ");
	}

	/**
	 * <b>Description:</b>打开PA插件
	 * 
	 * @author xwx357019
	 * @param driver
	 * @return void
	 */
	public static void openAutoPAData(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.ChinaMobileLTEAssistantXiAn,
				NetworkAnalysisCenterPage.Assistant_ClASS, "插件未找到，插件名：配置对比");
	}

	/**
	 * <b>Description:</b>打开 LTE配置对比任务
	 * 
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void OpenLTECfgCompare(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.CompareCfgPara_ClASS, "插件未找到，插件名：配置对比");
	}

	/**
	 * <b>Description:</b>打开UMTS配置参数对比任务
	 */
	public static void OpenUMTSCfgCompare(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.UMTS_Mobile,
				NetworkAnalysisCenterPage.UmtsConfigcompare, "插件未找到，插件名：配置参数对比   ");
	}

	/**
	 * <b>Description:</b>打开 ATU数据自动分析任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openAutoATUData(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.ATU_ClASS, "插件未找到，插件名：ATU数据自动分析");
	}

	/**
	 * <b>Description:</b>打开 DT数据自动分析任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openAutoDTData(WebDriver driver) {
		// LoginTask.changeVersion(driver);
		// IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID,
		// IndexPage.LTE_Mobile, NetworkAnalysisCenterPage.DT_ClASS,
		// "插件未找到，插件名：DT数据自动分析");
		IndexPage.netAppMange(driver);
		AutoDTDataPage.DTClick(driver);
		IndexTask.changePrj(driver, IndexPage.LTE_Mobile);
	}

	/**
	 * <b>Description:</b>打开 鼎利数据自动分析
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openAutoDingLiData(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.DingLi_ClASS, "插件未找到，插件名：鼎利数据自动分析");
	}

	/**
	 * <b>Description:</b>打开 簇优化任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openClusterOptimiz(WebDriver driver) {
		// LoginTask.changeVersion(driver);
		// IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID,
		// IndexPage.LTE_Mobile,
		// NetworkAnalysisCenterPage.ClusterOptimiz_ClASS,
		// "插件未找到，插件名：簇优化");
		IndexPage.netAppMange(driver);
		AutoDTDataPage.ClusterClick(driver);
		IndexTask.changePrj(driver, IndexPage.LTE_Mobile);
	}

	/**
	 * <b>Description:</b>打开 LTE DT数据自动分析任务(海外)
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTEAutoDTDataOverseas(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.DT_HW_ClASS, "插件未找到，插件名：DT数据自动分析任务(海外)");
	}

	/**
	 * 打开LTE MV数据自动分析任务
	 * 
	 * @author pgenexautotest return void
	 **/
	public static void openLTEAutoMVData(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.MV_ClASS, "插件未找到，插件名：MV专家求助");

	}

	/**
	 * <b>Description:</b>打开 GSM DT数据自动分析任务(海外)
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openGSMAutoDTDataOverseas(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.GSM_Mobile,
				NetworkAnalysisCenterPage.DT_HW_ClASS, "插件未找到，插件名：DT数据自动分析任务(海外)");
	}

	/**
	 * <b>Description:</b>打开 UMTS DT数据自动分析任务(海外)
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openUMTSAutoDTDataOverseas(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.UMTS_Mobile,
				NetworkAnalysisCenterPage.DT_HW_ClASS, "插件未找到，插件名：DT数据自动分析任务(海外)");
	}

	/**
	 * <b>Description:</b>打开LTE 网络评估 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTENetWorkAudit(WebDriver driver) {
		// LoginTask.changeVersion(driver);
		// IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID,
		// IndexPage.LTE_Mobile,
		// NetworkAnalysisCenterPage.NetWorkAudit_ClASS, "插件未找到，插件名：网络评估");
		IndexPage.netAppMange(driver);
		NetWorkAuditPage.NetWorkAuditClick(driver);
		IndexTask.changePrj(driver, IndexPage.LTE_Mobile);
	}

	/**
	 * <b>Description:</b>打开UMTS 网络评估 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openUMTSNetWorkAudit(WebDriver driver) {
		// LoginTask.changeVersion(driver);
		// IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID,
		// IndexPage.UMTS_Mobile,
		// NetworkAnalysisCenterPage.NetWorkAudit_ClASS, "插件未找到，插件名：网络评估");
		IndexPage.netAppMange(driver);
		NetWorkAuditPage.NetWorkAuditClick(driver);
		IndexTask.changePrj(driver, IndexPage.UMTS_Mobile);
	}

	/**
	 * <b>Description:</b>打开UMTS 覆盖评估 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openUMTSCoverEvaluate(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.UMTS_Mobile,
				NetworkAnalysisCenterPage.CoverEvaluate_ClASS, "插件未找到，插件名：覆盖评估");
	}

	/**
	 * <b>Description:</b>打开UMTS 价值区域评估 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openUMTSHotSpot(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.UMTS_Mobile,
				NetworkAnalysisCenterPage.HotSpot_ClASS, "插件未找到，插件名：价值区域评估 ");
	}

	/**
	 * <b>Description:</b>打开 单站验证 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTESingleSiteVerification(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.SingleStaValidate_ClASS, "插件未找到，插件名：单站验证 ");
	}

	/**
	 * <b>Description:</b>打开LTE 价值区域评估 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTEHotSpot(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.HotSpot_ClASS, "插件未找到，插件名：价值区域评估 ");
	}

	/**
	 * <b>Description:</b>打开LTE 覆盖评估 任务
	 */
	public static void openLTEContentEvaluate(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.CoverEvaluate_ClASS, "插件未找到，插件名：覆盖评估 ");
	}

	/**
	 * <b>Description:</b>打开LTE 邻区核查与优化GUL 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTENbrCheckGul(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.NbrCheckGul_ClASS, "插件未找到，插件名：邻区核查与优化GUL ");
	}

	/**
	 * <b>Description:</b>打开UMTS 容量评估 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openUMTSContentEvaluate(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.UMTS_Mobile,
				NetworkAnalysisCenterPage.CapacEvaluate_ClASS, "插件未找到，插件名：容量评估 ");
	}

	/**
	 * <b>Description:</b>打开UMTS 目标网预测 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openUMTSTrafficForecast(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.UMTS_Mobile,
				NetworkAnalysisCenterPage.TrafficForecas_ClASS, "插件未找到，插件名：业务与话务预测  ");
	}

	/**
	 * <b>Description:</b>打开LTE 目标网预测 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTEhuawuyuce(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, "中国贵州毕节移动LTE自动化测试项目",
				NetworkAnalysisCenterPage.TrafficForecas_ClASS, "插件未找到，插件名：业务与话务预测  ");
	}

	/**
	 * <b>Description:</b>打开LTE benchmark/排名提升规划 任务
	 * 
	 * @author zwx336999
	 * @param driver
	 * @return void
	 */
	public static void openLTERankUpPlan(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.RankUpPlan_ClASS, "插件未找到，插件名：排名提升规划  ");
	}

	/**
	 * <b>Description:</b>打开LTE 规划类参数核查 任务
	 * 
	 * @author zwx336999
	 * @param driver
	 * @return void
	 */
	public static void openLTETALCheck(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.TAL_ClASS, "插件未找到，插件名：规划类参数核查 ");
	}

	/**
	 * <b>Description:</b>打开LTE 射频通道检查 任务
	 * 
	 * @author zwx336999
	 * @param driver
	 * @return void
	 */
	public static void openLTERFChannelCheck(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.RFChannelCheck_ClASS, "插件未找到，插件名：射频通道检查 ");
	}

	/**
	 * <b>Description:</b> 打开容量管理任务
	 * 
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void openCapcacityManagement(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.Capcacitymgt_ClASS, "插件未找到，插件名：容量管理  ");
	}

	/**
	 * <b>Description:</b> 打开掉话分析
	 * 
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void openDropCallAnalysis(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.Drop_Call_Mobile,
				NetworkAnalysisCenterPage.Dropcallanalysis_ClASS, "插件未找到，插件名：掉话分析 ");
	}

	/**
	 * <b>Description:</b> 打开LTE容量评估 任务
	 * 
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void openLTECapcacityEvaluate(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.CapacEvaluate_ClASS, "插件未找到，插件名：容量评估   ");
	}

	/**
	 * <b>Description:</b>打开 VoLTE覆盖评估
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openVolteCoverageEvaluation(WebDriver driver) {
		// LoginTask.changeVersion(driver);
		// IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID,
		// IndexPage.LTE_Mobile,
		// NetworkAnalysisCenterPage.VoLteCoverEva_ClASS,
		// "插件未找到，插件名：VoLTE覆盖评估 ");
		IndexPage.netAppMange(driver);
		VolteCoverageEvaluationPage.VideoInsightClick(driver);
		IndexTask.changePrj(driver, IndexPage.LTE_Mobile);
	}

	/**
	 * <b>Description:</b>打开 PCI优化
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openPCIOptimization(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.PCIOptimization_ClASS, "插件未找到，插件名：PCI优化   ");
	}

	/**
	 * <b>Description:</b>打开深度覆盖
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openInDepthCoverage(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Telecom,
				NetworkAnalysisCenterPage.InDepthCoverage_ClASS, "插件未找到，插件名：深度覆盖   ");
	}

	/**
	 * <b>Description:</b>打开随时随地xMbps
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTExMbps(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Telecom,
				NetworkAnalysisCenterPage.xMbps_ClASS, "插件未找到，插件名：随时随地xMbps");
	}

	/**
	 * <b>Description:</b>打开vMOS
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTEvMOS(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Telecom,
				NetworkAnalysisCenterPage.vMOS_ClASS, "插件未找到，插件名：vMOS");
	}

	/**
	 * <b>Description:</b>打开 特性规划设计
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTEDLCoMPClusterDesign(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.DLCoMPClusterDesign_ClASS, "插件未找到，插件名：特性规划设计   ");
	}

	public static void openTopnData(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.LteTopn_ClASS, "插件未找到，插件名：TOPN小区处理");
	}

	public static void openUmtsTopnData(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.UMTS_Mobile,
				NetworkAnalysisCenterPage.LteTopn_ClASS, "插件未找到，插件名：TOPN小区处理");
	}

	public static void openLTEAutoCfgParma_CompareData(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.CompareCheckCfgPara_ClASS, "插件未找到，插件名：配置参数对比核查");
	}

	/**
	 * <b>Description:</b>打开 单站验证 任务
	 *
	 * @author dwx431110
	 * @param driver
	 * @return void
	 */
	public static void openLETSingleSiteVerification(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.SingleStaValidate_ClASS, "插件未找到，插件名：单站验证 ");
	}

	/**
	 * <b>Description:</b>排名提升优化
	 *
	 * @author dwx431110
	 * @param driver
	 * @return void
	 */
	public static void openLETBenchmarkOptimization(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.BenchmarkOptimization_CLASS, "插件未找到，插件名：排名提升优化");
	}

	/**
	 * <b>Description:</b>打开LTE 覆盖评估 任务
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void openLTECoverEvaluate(WebDriver driver) {
		IndexTask.openPlugin(driver, IndexPage.NetPerAnalyCenter_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.CoverEvaluate_ClASS, "插件未找到，插件名：覆盖评估");
	}

	/**
	 * <b>Description:</b>打开 射频通道检查 任务
	 * 
	 * @author zwx336999
	 * @param driver
	 * @return void
	 */
	public static void openlteupinterfere(WebDriver driver) {
		IndexTask.openNetPlanPlugin(driver, IndexPage.NetPlan_ID, IndexPage.LTE_Mobile,
				NetworkAnalysisCenterPage.RFChannelCheck_ClASS, "插件未找到，插件名：射频通道检查");
	}

	/**
	 * <b>Description:</b>打开 3D网络评估
	 * 
	 * @author swx198085
	 * @param driver
	 * @return void
	 */
	public static void open3DNetWorkEvaluation(WebDriver driver) {
		IndexPage.netAppMange(driver);
		ThreeDNetWorkEvaPage.ThreeDNetWorkEvaClick(driver);
		IndexTask.changePrj(driver, IndexPage.LTE_Mobile);
	}

	/**
	 * <b>Description:</b>视频优化质差门限识别
	 * 
	 * @author zwx336999
	 * @param driver
	 * @return void
	 */
	public static void openltePoorQualityThresholdIdentification(WebDriver driver) {
		IndexPage.netAppMange(driver);
		ThreeDNetWorkEvaPage.PoorQualityThresholdIdentificationClick(driver);
		IndexTask.changePrj(driver, IndexPage.LTE_Mobile);
	}
}
