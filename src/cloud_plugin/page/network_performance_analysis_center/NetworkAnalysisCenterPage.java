package cloud_plugin.page.network_performance_analysis_center;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;

public class NetworkAnalysisCenterPage {

	// Network Planning const string.
	public static final String NetWorkAudit_ClASS = ".networkaudit";
	public static final String ClusterOptimiz_ClASS = ".lteclusterInter";
	public static final String CoverEvaluate_ClASS = ".coverevaluate";
	public static final String HotSpot_ClASS = ".networkhotspot";
	public static final String DT_HW_ClASS = ".intlcluster";
	public static final String SingleStaValidate_ClASS = ".ltesinglestationvalidate";
	public static final String MV_ClASS = ".mvremotetechnology";
	public static final String CapacEvaluate_ClASS = ".ltecontentevaluate";
	public static final String Capcacitymgt_ClASS = ".capcacitymgt";
	public static final String Dropcallanalysis_ClASS = ".ltedropcallanalysis";
	public static final String TrafficForecas_ClASS = ".trafficForecast";
	public static final String PCIOptimization_ClASS = ".pcioptimization";
	public static final String InDepthCoverage_ClASS = ".ltedepthcover";
	public static final String vMOS_ClASS = ".vMOS";
	public static final String VoLteCoverEva_ClASS = ".ltevoltecover";
	public static final String DLCoMPClusterDesign_ClASS = ".dlcompclusterdesign";
	public static final String TAL_ClASS = ".ltetalcheck";
	public static final String RFChannelCheck_ClASS = ".lteupinterfere";
	public static final String xMbps_ClASS = ".lteeverywherexm";
	// Basic Optimization const string.
	public static final String CompareCfgPara_ClASS = ".lteconfigcompare";

	public static final String CompareCheckCfgPara_ClASS = ".configparacheckandcompare";// ".configurationparametercheckandcomparison";
	public static final String CfgPara_ClASS = ".lteconfigparamcheck";
	// Engineering Optimization const string.
	public static final String DT_ClASS = ".ltecluster";
	public static final String DingLi_ClASS = ".ltedingli";
	public static final String ATU_ClASS = ".ATUshujuzidongfenxi";
	public static final String RankUpPlan_ClASS = ".benchmark";
	public static final String NbrCheckGul_ClASS = ".neighbor";
	public static final String UmtsConfigcompare = ".lteconfigcompare";
	public static final String LteTopn_ClASS = "span[class=\"lteTopN\"]";
	public static final String CfgParma_Compare = ".lteconfigcompare";
	public static final String BenchmarkOptimization_CLASS = ".benchmarkopt";
	public static final String Assistant_ClASS = ".assistant";
	public static final String PoorQualityThresholdIdentification_ClASS = ".videoOptQualityThreshold";

	/**
	 * 配置参数对比核查_核查_参数设置
	 **/
	public static void checkParameSetup(WebDriver driver, Object... objects) {

		// 检查类型
		if ("LTE XML配置文件检查".equals(objects[0])) {
			CommonJQ.click(driver, "#LTE", true);
		} else if ("LTE MML配置文件检查".equals(objects[0])) {
			CommonJQ.click(driver, "#UMTS", true);
		}
	}
}
