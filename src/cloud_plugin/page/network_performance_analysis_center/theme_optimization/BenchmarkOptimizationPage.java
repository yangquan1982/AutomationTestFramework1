package cloud_plugin.page.network_performance_analysis_center.theme_optimization;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;

public class BenchmarkOptimizationPage {

	private static final String TextTaskName = "#taskName";//Task Name
	
	public static final String CheckBox_paramCheck = "#paramCheck";
	public static final String CheckBox_p3Analyse = "#p3Analyse";
	public static final String CheckBox_gapAnalyse = "#gapAnalyse";
	public static final String CheckBox_scaDataAnalyse = "#scaDataAnalyse";
	public static final String operatorIndexModelBtn = "#select_operatorIndexFileTemplateDownload";
	public static final String UNTSTelephoneDataNodeBModelBtn = "#select_umtsMikeDataNodeBTemplateDownload";
	public static final String RNCReportAnEmergencyModelBtn = "#select_rncNoticeDataTemplateDownload";
	public static final String eNodeBReportAnEmergencyModelBtn = "#select_enodeBNoticeDataTemplateDownload";
	public static final String operatorIndexFile = "#operatorIndexFile";
	public static final String isOK = ".l-btn-text";
	public static final String umtsMikeDataNodeBBtn = "#umtsMikeDataNodeB";
	public static final String select_rncNoticeDataTemplateDownloadBtn = "#rncNoticeData";
	public static final String enodeBNoticeDataBtn = "#enodeBNoticeData";
	public static final String umtsParamBaselineFormworkDataBtn = "#umtsParamBaselineFormworkData";
	public static final String lteParamBaselineFormworkDataBtn = "#lteParamBaselineFormworkData";
	public static final String UmtsDownloadBtn = "a[href=\"downloadTemplateAction?templateType=UmtsParamBaselineFile\"]";
	public static final String LteDownloadBtn = "a[href=\"downloadTemplateAction?templateType=LteParamBaselineFile\"]";
	
	public static final String localFileChooseBtn = ".localFileChooseBtn";
	public static final String cdrFileNameAdapterRuleTemplateBtn = "#cdrFileNameAdapterRuleTemplate";
	public static final String CDRFileNameAdapterRuleTemplateBtn = "a[href=\"downloadTemplateAction?templateType=CDRFileNameAdapterRuleTemplate\"]";
	public static final String p3OperatorBtn = "#p3Operator~span";
	public static final String onlyQueTicketTcpAnalysisCheckBox = "#onlyQueTicketTcpAnalysis";
	
	public static final String tcpDnsTemplateBtn = "#tcpDnsTemplate";
	
	public static final String scannerOperatorAndFreqDataBtn = "#scannerOperatorAndFreqData";
	public static void setUpParame(WebDriver driver, String functionSubitemStr, 
			//参数模板设置
			String UMTSParmBaseLineModelFile,
			String LTEParmBaseLineModelFile, 
			//p3业务问题分析
			//p3业务问题分析参数
			String[] P3TaskAnalyseParme,//该数组工四个参数：0.CDR文件名校验规则模板路径(String); 1.CDR文件名校验规则模板下载（boolean）;2.运营商（String）;3.只进行问题话单的TCP分析（boolean）
			//评估参数 web Youtube upload Download other
			String[] Web, String[] Youtube, String[] upload, String[] Download,String[] other,
			//TCP数传问题分析参数 
			String[] TCPUploadDataParame,
			//覆盖干扰问题分析参数
			//UMTS LTE
			String[] coverDisturbParameUMTS,String[] coverDisturbParameLTE,
			//资源问题分析参数 
			//UMTS  LTE
			String[] resourceProblemAnalyseUMTS,String[] resourceProblemAnalyseLTE,
			//Scanner分析参数
			String[] ScannerAnalyseParame,
			//TCP DNS模板设置
			String[] TCPDNSsetUpModel,//该数组有两个参数：0.文件路径(String);1.是否下载模板(boolean)
			//CDR文件名校验规则模板
			String[] CDRFileNameModel,
			//GAP分析&问题话单筛选
			//业务KQI
			String professionalWorkKQI,
			//分析纬度
			String[] analyseLatitude,
			//Scanner分析
			//String[] scannerAnalyse,//该数组一共四个参数：0.运营商和频点配置模板文件路径（String），1.运营商和频点配置模板下载（boolean），2.选择分析运营商（String），3.频点（String）
			//Scanner 分析参数设置
			//String[] scannerParame,
			//邻区漏配参数设置
			String[] adjacentParameSetUp,
			boolean tacitlyApproveResume,
			String ResultPath
	) {
		//参数模板设置
		if(functionSubitemStr.indexOf("参数核查")>-1) {
			//UMTS参数基线模板
			if(UMTSParmBaseLineModelFile!=null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
				if(CommonJQ.isExisted(driver, BenchmarkOptimizationPage.umtsParamBaselineFormworkDataBtn, true)) {
					CommonJQ.click(driver, BenchmarkOptimizationPage.umtsParamBaselineFormworkDataBtn,
							true, "导入模板->浏览");
					CommonFile.ChooseOneFile(ResultPath+"\\"+UMTSParmBaseLineModelFile);
					CommonJQ.click(driver, BenchmarkOptimizationPage.isOK,
							true, "导入模板->浏览->确定");
				}
				SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
			}
			/*if(UMTSParmBaseLineModel) {
				String ResultHome = ResultPath+"\\";
				BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "OperatorIndex_template.xlsx",UMTSParmBaseLineModel, BenchmarkOptimizationPage.UmtsDownloadBtn);
			}*/
			//LTE参数基线模板
			if(LTEParmBaseLineModelFile!=null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
				if(CommonJQ.isExisted(driver, BenchmarkOptimizationPage.lteParamBaselineFormworkDataBtn, true)) {
					CommonJQ.click(driver, BenchmarkOptimizationPage.lteParamBaselineFormworkDataBtn,
							true, "导入模板->浏览");
					CommonFile.ChooseOneFile(ResultPath+"\\"+LTEParmBaseLineModelFile);
					CommonJQ.click(driver, BenchmarkOptimizationPage.isOK,
							true, "导入模板->浏览->确定");
				}
				SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
			}
			/*if(LTEParmBaseLineModel) {
				String ResultHome = ResultPath+"\\";
				BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, LTEParmBaseLineModel, BenchmarkOptimizationPage.LteDownloadBtn);
			}*/
		}
		//P3业务问题分析 P3业务问题分析
		if(functionSubitemStr.indexOf("P3业务问题分析")>-1) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			CommonJQ.click(driver, 
					//"span:contains(\"P3业务问题分析\")",
					"#p3BusinessAnalysis>div>span",
					true,"P3业务问题分析");
			SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
			//P3业务问题分析参数
			if(P3TaskAnalyseParme!=null) {
				if(P3TaskAnalyseParme[0]!=null) {//CDR文件名校验规则模板
					SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
					if(CommonJQ.isExisted(driver, BenchmarkOptimizationPage.cdrFileNameAdapterRuleTemplateBtn, true)) {
						CommonJQ.click(driver, BenchmarkOptimizationPage.cdrFileNameAdapterRuleTemplateBtn,
								true, "导入模板->浏览");
						CommonFile.ChooseOneFile(ResultPath+"\\"+P3TaskAnalyseParme[0]);
						CommonJQ.click(driver, BenchmarkOptimizationPage.isOK,
								true, "导入模板->浏览->确定");
					}
					SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
				}
				/*if("TRUE".equals(P3TaskAnalyseParme[1].toUpperCase())) { //CDR文件名校验规则模板 下载
					String ResultHome = ResultPath+"\\";
					BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, true, CDRFileNameAdapterRuleTemplateBtn);
				}*/
				/*if(P3TaskAnalyseParme[1]!=null) {
					SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
					CommonJQ.click(driver, BenchmarkOptimizationPage.p3OperatorBtn,
							true, "运营商");
					if("vodafone".equals(P3TaskAnalyseParme[1])) {
						CommonJQ.click(driver, "#vodafone",
								true, "运营商->vodafone");
					} else if("telefonica".equals(P3TaskAnalyseParme[1])) {
						CommonJQ.click(driver, "#telefonica",
								true, "运营商->telefonica");
					} else if("telekom".equals(P3TaskAnalyseParme[1])) {
						CommonJQ.click(driver, "#telekom",
								true, "运营商->telekom");
					} else if("e_plus".equals(P3TaskAnalyseParme[1])) {
						CommonJQ.click(driver, "#e_plus",
								true, "运营商->e_plus");
					} 
					SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
				}
				if(P3TaskAnalyseParme[2]!=null) {
					if(!"TRUE".equals(P3TaskAnalyseParme[2].toUpperCase())) {
						CommonJQ.click(driver, onlyQueTicketTcpAnalysisCheckBox,
								true, "运营商->只进行问题话单的TCP分析");
						
					}
				}*/
			}
			//评估参数
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			CommonJQ.click(driver, 
					"#evaluationParam>div>div>span",
					true,"评估参数");
			//Web
			if(Web!=null) {
				if(Web[0]!=null) {
					CommonJQ.value(driver, "#WebParam1_l", Web[0], true);
				}
				if(Web[1]!=null) {
					CommonJQ.value(driver, "#WebParam1_r", Web[1], true);
				}
				if(Web[2]!=null) {
					CommonJQ.value(driver, "#WebParam2_l", Web[2], true);
				}
			}
			if(Youtube!=null) {
				if(Youtube[0]!=null) {
					CommonJQ.value(driver, "#YoutubeParam1_l", Youtube[0], true);
				}
				if(Youtube[1]!=null) {
					CommonJQ.value(driver, "#YoutubeParam1_r", Youtube[1], true);
				}
				if(Youtube[2]!=null) {
					CommonJQ.value(driver, "#YoutubeParam2_l", Youtube[2], true);
				}
				if(Youtube[3]!=null) {
					CommonJQ.value(driver, "#YoutubeParam2_r", Youtube[3], true);
				}
				if(Youtube[4]!=null) {
					CommonJQ.value(driver, "#YoutubeParam3_l", Youtube[4], true);
				}
				if(Youtube[5]!=null) {
					CommonJQ.value(driver, "#YoutubeParam3_r", Youtube[5], true);
				}
				if(Youtube[6]!=null) {
					CommonJQ.value(driver, "#YoutubeParam4_l", Youtube[6], true);
				}
				if(Youtube[7]!=null) {
					CommonJQ.value(driver, "#YoutubeParam4_r", Youtube[7], true);
				}
				if(Youtube[8]!=null) {
					CommonJQ.value(driver, "#YoutubeParam5_l", Youtube[8], true);
				}
			}
			if(upload!=null) {
				if(upload[0]!=null) {
					CommonJQ.value(driver, "#UploadParam1_l", upload[0], true);
				}
				if(upload[1]!=null) {
					CommonJQ.value(driver, "#UploadParam1_r", upload[1], true);
				}
				if(upload[2]!=null) {
					CommonJQ.value(driver, "#UploadParam2_l", upload[2], true);
				}
			}
			if(Download!=null) {
				if(Download[0]!=null) {
					CommonJQ.value(driver, "#DownloadParam1_l", Download[0], true);
				}
				if(Download[1]!=null) {
					CommonJQ.value(driver, "#DownloadParam1_r", Download[1], true);
				}
				if(Download[2]!=null) {
					CommonJQ.value(driver, "#DownloadParam2_l", Download[2], true);
				}
			}
			if(other!=null) {
				if(other[0]!=null) {
					CommonJQ.value(driver, "#OtherParam1_l", other[0], true);
				}
				if(other[1]!=null) {
					CommonJQ.value(driver, "#OtherParam1_r", other[1], true);
				}
				if(other[2]!=null) {
					CommonJQ.value(driver, "#OtherParam2_l", other[2], true);
				}
				if(other[3]!=null) {
					CommonJQ.value(driver, "#OtherParam2_r", other[3], true);
				}
			}
			//TCP数传问题分析
			CommonJQ.click(driver, 
					"#TCPProblemParameters>div>div>span",
					true,"TCP数传问题分析");
			if(TCPUploadDataParame!=null) {
				if(TCPUploadDataParame[0]!=null) {
					CommonJQ.value(driver, "#TCPParam1_l", TCPUploadDataParame[0], true);
				}
				if(TCPUploadDataParame[1]!=null) {
					CommonJQ.value(driver, "#TCPParam1_r", TCPUploadDataParame[1], true);
				}
				if(TCPUploadDataParame[2]!=null) {
					CommonJQ.value(driver, "#TCPParam2_l", TCPUploadDataParame[2], true);
				}
				if(TCPUploadDataParame[3]!=null) {
					CommonJQ.value(driver, "#TCPParam2_r", TCPUploadDataParame[3], true);
				}
				if(TCPUploadDataParame[4]!=null) {
					CommonJQ.value(driver, "#TCPParam3_l", TCPUploadDataParame[4], true);
				}
				if(TCPUploadDataParame[5]!=null) {
					CommonJQ.value(driver, "#TCPParam3_r", TCPUploadDataParame[5], true);
				}
				if(TCPUploadDataParame[6]!=null) {
					CommonJQ.value(driver, "#TCPParam4_l", TCPUploadDataParame[6], true);
				}
				if(TCPUploadDataParame[7]!=null) {
					CommonJQ.value(driver, "#TCPParam4_r", TCPUploadDataParame[7], true);
				}
				if(TCPUploadDataParame[8]!=null) {
					CommonJQ.value(driver, "#TCPParam5_l", TCPUploadDataParame[8], true);
				}
				if(TCPUploadDataParame[9]!=null) {
					CommonJQ.value(driver, "#TCPParam5_r", TCPUploadDataParame[9], true);
				}
				if(TCPUploadDataParame[10]!=null) {
					CommonJQ.value(driver, "#TCPParam6_l", TCPUploadDataParame[10], true);
				}
				if(TCPUploadDataParame[11]!=null) {
					CommonJQ.value(driver, "#TCPParam6_r", TCPUploadDataParame[11], true);
				}
				if(TCPUploadDataParame[12]!=null) {
					CommonJQ.value(driver, "#TCPParam7_l", TCPUploadDataParame[12], true);
				}
				if(TCPUploadDataParame[13]!=null) {
					CommonJQ.value(driver, "#TCPParam7_r", TCPUploadDataParame[13], true);
				}
				if(TCPUploadDataParame[14]!=null) {
					CommonJQ.value(driver, "#TCPParam8_l", TCPUploadDataParame[14], true);
				}
				if(TCPUploadDataParame[15]!=null) {
					CommonJQ.value(driver, "#TCPParam8_r", TCPUploadDataParame[15], true);
				}
				if(TCPUploadDataParame[16]!=null) {
					CommonJQ.value(driver, "#TCPParam9_l", TCPUploadDataParame[16], true);
				}
			}
			//覆盖干扰问题分析参数
			CommonJQ.click(driver, 
					"#overrideProblemParameters>div>div>span",
					true,"覆盖干扰问题分析参数");
			//UMTS
			if(coverDisturbParameUMTS!=null) {
				if(coverDisturbParameUMTS[0]!=null) {
					CommonJQ.value(driver, "#overrideUMTSParam1_l", coverDisturbParameUMTS[0], true);
				}
				if(coverDisturbParameUMTS[1]!=null) {
					CommonJQ.value(driver, "#overrideUMTSParam1_r", coverDisturbParameUMTS[1], true);
				}
				if(coverDisturbParameUMTS[2]!=null) {
					CommonJQ.value(driver, "#overrideUMTSParam2_l", coverDisturbParameUMTS[2], true);
				}
				if(coverDisturbParameUMTS[3]!=null) {
					CommonJQ.value(driver, "#overrideUMTSParam2_r", coverDisturbParameUMTS[3], true);
				}
				if(coverDisturbParameUMTS[4]!=null) {
					CommonJQ.value(driver, "#overrideUMTSParam3_l", coverDisturbParameUMTS[4], true);
				}
				if(coverDisturbParameUMTS[5]!=null) {
					CommonJQ.value(driver, "#overrideUMTSParam3_r", coverDisturbParameUMTS[5], true);
				}
				if(coverDisturbParameUMTS[6]!=null) {
					CommonJQ.value(driver, "#overrideUMTSParam4_l", coverDisturbParameUMTS[6], true);
				}
				if(coverDisturbParameUMTS[7]!=null) {
					CommonJQ.value(driver, "#overrideUMTSParam4_r", coverDisturbParameUMTS[7], true);
				}
			}
			//LTE
			if(coverDisturbParameLTE!=null) {
				if(coverDisturbParameLTE[0]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam1_l", coverDisturbParameLTE[0], true);
				}
				if(coverDisturbParameLTE[1]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam1_r", coverDisturbParameLTE[1], true);
				}
				if(coverDisturbParameLTE[2]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam2_l", coverDisturbParameLTE[2], true);
				}
				if(coverDisturbParameLTE[3]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam2_r", coverDisturbParameLTE[3], true);
				}
				if(coverDisturbParameLTE[4]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam3_l", coverDisturbParameLTE[4], true);
				}
				if(coverDisturbParameLTE[5]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam3_r", coverDisturbParameLTE[5], true);
				}
				if(coverDisturbParameLTE[6]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam4_l", coverDisturbParameLTE[6], true);
				}
				if(coverDisturbParameLTE[7]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam4_r", coverDisturbParameLTE[7], true);
				}
				if(coverDisturbParameLTE[8]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam5_l", coverDisturbParameLTE[8], true);
				}
				if(coverDisturbParameLTE[9]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam5_r", coverDisturbParameLTE[9], true);
				}
				if(coverDisturbParameLTE[10]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam6_l", coverDisturbParameLTE[10], true);
				}
				if(coverDisturbParameLTE[11]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam6_r", coverDisturbParameLTE[11], true);
				}
				if(coverDisturbParameLTE[12]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam7_l", coverDisturbParameLTE[12], true);
				}
				if(coverDisturbParameLTE[13]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam7_r", coverDisturbParameLTE[13], true);
				}
				if(coverDisturbParameLTE[14]!=null) {
					CommonJQ.value(driver, "#overrideLTEParam8_l", coverDisturbParameLTE[14], true);
				}
			}
			//资源问题分析参数 
			CommonJQ.click(driver, 
					"#resourceProblemParameters>div>div>span",
					true,"资源问题分析参数 ");
			//UMTS
			if(resourceProblemAnalyseUMTS!=null) {
				if(resourceProblemAnalyseUMTS[0]!=null) {
					if("小区级资源核查".equals(resourceProblemAnalyseUMTS[0])) {
						CommonJQ.click(driver, "input[name=\"p3Bean.umtsSummaryLevel\"]", true,0);
					} else if("小时级资源核查".equals(resourceProblemAnalyseUMTS[0])) {
						CommonJQ.click(driver, "input[name=\"p3Bean.umtsSummaryLevel\"]", true,1);
					}
				}
				if(resourceProblemAnalyseUMTS[1]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam1_l", resourceProblemAnalyseUMTS[1], true);
				}
				if(resourceProblemAnalyseUMTS[2]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam1_r", resourceProblemAnalyseUMTS[2], true);
				}
				if(resourceProblemAnalyseUMTS[3]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam2_l", resourceProblemAnalyseUMTS[3], true);
				}
				if(resourceProblemAnalyseUMTS[4]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam2_r", resourceProblemAnalyseUMTS[4], true);
				}
				if(resourceProblemAnalyseUMTS[5]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam3_l", resourceProblemAnalyseUMTS[5], true);
				}
				if(resourceProblemAnalyseUMTS[6]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam3_r", resourceProblemAnalyseUMTS[6], true);
				}
				if(resourceProblemAnalyseUMTS[7]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam4_l", resourceProblemAnalyseUMTS[7], true);
				}
				if(resourceProblemAnalyseUMTS[8]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam4_r", resourceProblemAnalyseUMTS[8], true);
				}
				if(resourceProblemAnalyseUMTS[9]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam5_l", resourceProblemAnalyseUMTS[9], true);
				}
				if(resourceProblemAnalyseUMTS[10]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam5_r", resourceProblemAnalyseUMTS[10], true);
				}
				if(resourceProblemAnalyseUMTS[11]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam6_l", resourceProblemAnalyseUMTS[11], true);
				}
				if(resourceProblemAnalyseUMTS[12]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam6_r", resourceProblemAnalyseUMTS[12], true);
				}
				if(resourceProblemAnalyseUMTS[13]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam7_l", resourceProblemAnalyseUMTS[13], true);
				}
				if(resourceProblemAnalyseUMTS[14]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam7_r", resourceProblemAnalyseUMTS[14], true);
				}
				if(resourceProblemAnalyseUMTS[15]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam8_l", resourceProblemAnalyseUMTS[15], true);
				}
				if(resourceProblemAnalyseUMTS[16]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam8_r", resourceProblemAnalyseUMTS[16], true);
				}
				if(resourceProblemAnalyseUMTS[17]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam9_l", resourceProblemAnalyseUMTS[17], true);
				}
				if(resourceProblemAnalyseUMTS[18]!=null) {
					CommonJQ.value(driver, "#resourceUMTSParam9_r", resourceProblemAnalyseUMTS[18], true);
				}
			}
			if(resourceProblemAnalyseLTE!=null) {
				if(resourceProblemAnalyseLTE[0]!=null) {
					if("小区级资源核查".equals(resourceProblemAnalyseLTE[0])) {
						CommonJQ.click(driver, "input[name=\"p3Bean.lteSummaryLevel\"]", true,0);
					} else if("小时级资源核查".equals(resourceProblemAnalyseLTE[0])) {
						CommonJQ.click(driver, "input[name=\"p3Bean.lteSummaryLevel\"]", true,1);
					}
				}
				if(resourceProblemAnalyseLTE[1]!=null) {
					CommonJQ.value(driver, "#resourceLTEParam1_l", resourceProblemAnalyseLTE[1], true);
				}
				if(resourceProblemAnalyseLTE[2]!=null) {
					CommonJQ.value(driver, "#resourceLTEParam1_r", resourceProblemAnalyseLTE[2], true);
				}
				if(resourceProblemAnalyseLTE[3]!=null) {
					CommonJQ.value(driver, "#resourceLTEParam2_l", resourceProblemAnalyseLTE[3], true);
				}
				if(resourceProblemAnalyseLTE[4]!=null) {
					CommonJQ.value(driver, "#resourceLTEParam2_r", resourceProblemAnalyseLTE[4], true);
				}
				if(resourceProblemAnalyseLTE[5]!=null) {
					CommonJQ.value(driver, "#resourceLTEParam3_l", resourceProblemAnalyseLTE[5], true);
				}
				if(resourceProblemAnalyseLTE[6]!=null) {
					CommonJQ.value(driver, "#resourceLTEParam3_r", resourceProblemAnalyseLTE[6], true);
				}
				if(resourceProblemAnalyseLTE[7]!=null) {
					CommonJQ.value(driver, "#resourceLTEParam4_l", resourceProblemAnalyseLTE[7], true);
				}
				if(resourceProblemAnalyseLTE[8]!=null) {
					CommonJQ.value(driver, "#resourceLTEParam4_r", resourceProblemAnalyseLTE[8], true);
				}
			}
			//Scanner分析参数
			CommonJQ.click(driver, 
					"#scannerParameters>div>div>span",
					true,"Scanner分析参数");
			if(ScannerAnalyseParame!=null) {//
				if(ScannerAnalyseParame[0]!=null) {
					CommonJQ.value(driver, "#scannerP3Param1_l", ScannerAnalyseParame[0], true);
				}
				if(ScannerAnalyseParame[1]!=null) {
					CommonJQ.value(driver, "#scannerP3Param1_r", ScannerAnalyseParame[1], true);
				}
				if(ScannerAnalyseParame[2]!=null) {
					CommonJQ.value(driver, "#scannerP3Param2_l", ScannerAnalyseParame[2], true);
				}
				if(ScannerAnalyseParame[3]!=null) {
					CommonJQ.value(driver, "#scannerP3Param2_r", ScannerAnalyseParame[3], true);
				}
				if(ScannerAnalyseParame[4]!=null) {
					CommonJQ.value(driver, "#scannerP3Param3_l", ScannerAnalyseParame[4], true);
				}
				if(ScannerAnalyseParame[5]!=null) {
					CommonJQ.value(driver, "#scannerP3Param3_r", ScannerAnalyseParame[5], true);
				}
				if(ScannerAnalyseParame[6]!=null) {
					CommonJQ.value(driver, "#scannerP3Param4_l", ScannerAnalyseParame[6], true);
				}
				if(ScannerAnalyseParame[7]!=null) {
					CommonJQ.value(driver, "#scannerP3Param4_r", ScannerAnalyseParame[7], true);
				}
				if(ScannerAnalyseParame[8]!=null) {
					CommonJQ.value(driver, "#scannerP3Param5_l", ScannerAnalyseParame[8], true);
				}
				if(ScannerAnalyseParame[9]!=null) {
					CommonJQ.value(driver, "#scannerP3Param5_r", ScannerAnalyseParame[9], true);
				}
				if(ScannerAnalyseParame[10]!=null) {
					CommonJQ.value(driver, "#scannerP3Param6_l", ScannerAnalyseParame[10], true);
				}
				if(ScannerAnalyseParame[11]!=null) {
					CommonJQ.value(driver, "#scannerP3Param6_r", ScannerAnalyseParame[11], true);
				}
				if(ScannerAnalyseParame[12]!=null) {
					CommonJQ.value(driver, "#scannerP3Param7_l", ScannerAnalyseParame[12], true);
				}
				if(ScannerAnalyseParame[13]!=null) {
					CommonJQ.value(driver, "#scannerP3Param7_r", ScannerAnalyseParame[13], true);
				}
				if(ScannerAnalyseParame[14]!=null) {
					CommonJQ.value(driver, "#scannerP3Param8_l", ScannerAnalyseParame[14], true);
				}
				if(ScannerAnalyseParame[15]!=null) {
					CommonJQ.value(driver, "#scannerP3Param8_r", ScannerAnalyseParame[15], true);
				}
				if(ScannerAnalyseParame[16]!=null) {
					CommonJQ.value(driver, "#scannerP3Param9_l", ScannerAnalyseParame[16], true);
				}
				if(ScannerAnalyseParame[17]!=null) {
					CommonJQ.value(driver, "#scannerP3Param9_r", ScannerAnalyseParame[17], true);
				}
				if(ScannerAnalyseParame[18]!=null) {
					CommonJQ.value(driver, "#scannerP3Param10_l", ScannerAnalyseParame[18], true);
				}
				if(ScannerAnalyseParame[19]!=null) {
					CommonJQ.value(driver, "#scannerP3Param10_r", ScannerAnalyseParame[19], true);
				}
				if(ScannerAnalyseParame[20]!=null) {
					CommonJQ.value(driver, "#scannerP3Param11_l", ScannerAnalyseParame[20], true);
				}
				if(ScannerAnalyseParame[21]!=null) {
					CommonJQ.value(driver, "#scannerP3Param11_r", ScannerAnalyseParame[21], true);
				}
				if(ScannerAnalyseParame[22]!=null) {
					CommonJQ.value(driver, "#scannerP3Param12_l", ScannerAnalyseParame[22], true);
				}
			}
			//TCP DNS模板设置
			CommonJQ.click(driver, 
					"#TCPDNS>div>div>span",
					true,"TCP DNS模板设置");
			if(TCPDNSsetUpModel!=null) {
				if(TCPDNSsetUpModel[0]!=null) {
					if(CommonJQ.isExisted(driver, tcpDnsTemplateBtn, true)) {
						CommonJQ.click(driver, tcpDnsTemplateBtn,
								true, "导入模板->浏览");
						CommonFile.ChooseOneFile(ResultPath+"\\"+TCPDNSsetUpModel[0]);
						CommonJQ.click(driver, BenchmarkOptimizationPage.isOK,
								true, "导入模板->浏览->确定");
					}
				}
			}
			//CDR文件名校验规则模板
			CommonJQ.click(driver, 
					"#p3BusinessAnalysisDivParam>div>div>span",
					true,"CDR文件名校验规则模板");
			if(CDRFileNameModel!=null) {
				if(CDRFileNameModel[0]!=null) {
					if(CommonJQ.isExisted(driver, "#cdrFileNameAdapterRuleTemplate", true)) {
						CommonJQ.click(driver, "#cdrFileNameAdapterRuleTemplate",
								true, "导入模板->浏览");
						CommonFile.ChooseOneFile(ResultPath+"\\"+CDRFileNameModel[0]);
						CommonJQ.click(driver, BenchmarkOptimizationPage.isOK,
								true, "导入模板->浏览->确定");
					}
				}
			}
			
			SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
			/*if(TCPDNSsetUpModel!=null) {
				if(TCPDNSsetUpModel[1]!=null) {
					if("TRUE".equals(TCPDNSsetUpModel[1].toUpperCase())) {
						String ResultHome = ResultPath+"\\";
						BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, true, "a[href=\"downloadTemplateAction?templateType=TcpDnsTemplate\"]");
					}
				}
			}*/
		}
		if(functionSubitemStr.indexOf("GAP分析")>-1) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			//GAP分析&问题话单筛选
			CommonJQ.click(driver, 
					"#gapAnalysis>div>span",
					true,"GAP分析&问题话单筛选");
			//业务KQI
			if(professionalWorkKQI!=null) {
				wirelessParameter(driver, professionalWorkKQI);
			}
			//分析纬度
			if(analyseLatitude!=null) {
				CommonJQ.click(driver, "#sceneAllTree_2_check", true);
				CommonJQ.click(driver, "#sceneAllTree_3_check", true);
				CommonJQ.click(driver, "#sceneAllTree_4_check", true);
				for (int i = 0; i < analyseLatitude.length; i++) {
					if("CITY".equals(analyseLatitude[i])) {
						CommonJQ.click(driver, "#sceneAllTree_2_check", true);
					} else if("SCENCE".equals(analyseLatitude[i])) {
						CommonJQ.click(driver, "#sceneAllTree_3_check", true);
					} else if("VENDOR".equals(analyseLatitude[i])) {
						CommonJQ.click(driver, "#sceneAllTree_4_check", true);
					}
				}
				CommonJQ.click(driver, "#sceneSelectClick>ul>li>span", true,0);
			}
			SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
		}
		/*//Scanner分析
		if(functionSubitemStr.indexOf("Scanner数据分析")>-1) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			//Scanner分析
			CommonJQ.click(driver, 
					"#scannerAnalysis>div>span",
					true,"Scanner分析");
			if(scannerAnalyse!=null) {
				if(scannerAnalyse[0]!=null) {// 运营商和频点配置模板
					if(CommonJQ.isExisted(driver, scannerOperatorAndFreqDataBtn, true)) {
						CommonJQ.click(driver, scannerOperatorAndFreqDataBtn,
								true, "浏览");
						CommonFile.ChooseOneFile(ResultPath+"\\"+scannerAnalyse[0]);
						CommonJQ.click(driver, BenchmarkOptimizationPage.isOK,
								true, "浏览->确定");
					}
				}
				if(scannerAnalyse[1]!=null) {
					SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
					if("TRUE".equals(scannerAnalyse[1].toUpperCase())) {// 运营商和频点配置模板 下载
						String ResultHome = ResultPath+"\\";
						BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, true, "a[href=\"downloadTemplateAction?templateType=ScannerOperatorAndFreqTemplate\"]");
					}
					SwitchDriver.switchDriverToFrame(driver, "//iframe");
				}
				if(scannerAnalyse[1]!=null) {//选择分析运营商 
					CommonJQ.click(driver, "#scannerOperator~span", true);
					if("Telecom".equals(scannerAnalyse[2])) {
						CommonJQ.click(driver, "#analysisOperatorOption>li", true,0);
					} else if("Vodafone".equals(scannerAnalyse[2])) {
						CommonJQ.click(driver, "#analysisOperatorOption>li", true,1);
					} else if("E-Plus".equals(scannerAnalyse[2])) {
						CommonJQ.click(driver, "#analysisOperatorOption>li", true,2);
					} else if("Telefonica".equals(scannerAnalyse[2])) {
						CommonJQ.click(driver, "#analysisOperatorOption>li", true,3);
					}
				}
				if(scannerAnalyse[2]!=null) {//频点 
					CommonJQ.click(driver, "#frequencyInput~span", true);
					if("LTE:620".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,0);
					} else if("LTE:33".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,1);
					} else if("LTE:50".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,2);
					} else if("LTE:1575".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,3);
					} else if("LTE".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,4);
					} else if("UMTS:10738".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,5);
					} else if("UMTS:10762".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,6);
					} else if("UMTS:10786".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,7);
					} else if("UMTS".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,8);
					} else if("GSM".equals(scannerAnalyse[3])) {
						CommonJQ.click(driver, "#frequencyOption>li", true,9);
					}
				}
			}
			//Scanner分析参数设置
			if(scannerParame!=null) {
				CommonJQ.value(driver, "#scannerParam1_l", scannerParame[0], true);
				CommonJQ.value(driver, "#scannerParam1_r", scannerParame[1], true);
				CommonJQ.value(driver, "#scannerParam2_l", scannerParame[2], true);
				CommonJQ.value(driver, "#scannerParam2_r", scannerParame[3], true);
				CommonJQ.value(driver, "#scannerParam3_l", scannerParame[4], true);
				CommonJQ.value(driver, "#scannerParam3_r", scannerParame[5], true);
				CommonJQ.value(driver, "#scannerParam4_l", scannerParame[6], true);
				CommonJQ.value(driver, "#scannerParam4_r", scannerParame[7], true);
				CommonJQ.value(driver, "#scannerParam5_l", scannerParame[8], true);
				CommonJQ.value(driver, "#scannerParam5_r", scannerParame[9], true);
				CommonJQ.value(driver, "#scannerParam6_l", scannerParame[10], true);
				CommonJQ.value(driver, "#scannerParam6_r", scannerParame[11], true);
				CommonJQ.value(driver, "#scannerParam7_l", scannerParame[12], true);
				CommonJQ.value(driver, "#scannerParam7_r", scannerParame[13], true);
				CommonJQ.value(driver, "#scannerParam8_l", scannerParame[14], true);
				CommonJQ.value(driver, "#scannerParam8_r", scannerParame[15], true);
				CommonJQ.value(driver, "#scannerParam9_l", scannerParame[16], true);
				CommonJQ.value(driver, "#scannerParam9_r", scannerParame[17], true);
				CommonJQ.value(driver, "#scannerParam10_l", scannerParame[18], true);
				CommonJQ.value(driver, "#scannerParam10_r", scannerParame[19], true);
				CommonJQ.value(driver, "#scannerParam11_l", scannerParame[20], true);
				CommonJQ.value(driver, "#scannerParam11_r", scannerParame[21], true);
				CommonJQ.value(driver, "#scannerParam12_l", scannerParame[22], true);
			}
			//邻区漏配参数设置
			if(adjacentParameSetUp!=null) {
				CommonJQ.value(driver, "#neighboringParam1_l", adjacentParameSetUp[0], true);
				CommonJQ.value(driver, "#neighboringParam1_r", adjacentParameSetUp[1], true);
			}
			SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
		}*/
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if(tacitlyApproveResume) {
			CommonJQ.click(driver, "#reset_Task", true);
		} else {
			CommonJQ.click(driver, "#close_ok", true);
		}
		SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
	}
	//模板下载
	public static void downLoad_Mould(WebDriver driver,String filePath,String fileName,boolean ifDownLoadMould,String btn) {
		FileHandle.delSubFile(ConstUrl.DownLoadPath);//设置浏览器下载路径
		FileHandle.delSubFile(filePath);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if(ifDownLoadMould) {
			CommonJQ.click(driver,btn,true,"模板");
			String reportName = TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, fileName);
			FileHandle.copyFile(ConstUrl.DownLoadPath+"\\"+reportName, filePath+"\\"+reportName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void wirelessParameter(WebDriver driver, String treeNode) {
		CommonJQ.click(driver, "#selectedKqiTree_1_check", true);
		String[] treeNodeArray = treeNode.split(";");
		for(int i = 0; i < treeNodeArray.length; i++) {
			String[] treeNodeStr = treeNodeArray[i].substring(treeNodeArray[i].indexOf("[")+1, treeNodeArray[i].lastIndexOf("]")).split(",");
			String node1 = treeNodeStr[0];//一级节点
			String node2 = treeNodeStr[1];//二级节点
			String node3 = treeNodeStr[2];//叶子节点
			if("".equals(node3)||"null".equals(node3)) {
				if("".equals(node2)||"null".equals(node2)) {
					if(!"".equals(node1)&&!"null".equals(node1)) {
						CommonJQ.click(driver, "#selectedKqiTree_1_span", true);
					}
				} else {//如果子节点为空 二级节点部位空
					String id_2span = CommonJQ.getAttrValue(driver, "span:contains(\""+node2+"\")", "id", 0);
					String id_2check = id_2span.replace("span", "check");
					CommonJQ.click(driver, "#"+id_2check, true);
				}
			} else {//如果子节点不为空
				String id_2 = CommonJQ.getAttrValue(driver, "span:contains(\""+node2+"\")", "id", 0);
				String addIcon = id_2.replace("span", "switch");
				CommonJQ.click(driver, "#"+addIcon, true);
				LoadingPage.Loading(driver, "#"+addIcon, "加载叶子节点");
				
				String id_3span = CommonJQ.getAttrValue(driver, "span:contains(\""+node3+"\")", "id", 0);
				String id_3check = id_3span.replace("span", "check");
				CommonJQ.click(driver, "#"+id_3check, true);
				CommonJQ.click(driver, "#kqiSelectClick>ul>li>span", true,2);
			}
		}
		//CommonJQ.click(driver, "#selectedKqiTree_1_check", true);
	}
	public static String getTitle(String PTitle, int i) {
		String text = "";
		if("评估参数".equals(PTitle)) {
			switch (i) {
				case 1:
					text = "服务接入时长(s)";
					break;
				case 2:
					text = "HTTP传输时长(s)";
					break;
				case 3:
					text = "传输速率(kbit/s)";
					break;
				case 4:
					text = "目录DNS时延(s)";
					break;
				case 5:
					text = "目录接入时延(s)";
					break;
				case 6:
					text = "目录下载时长(s)";
					break;
				case 7:
					text = "视频业务接入时间(s)";
					break;
				case 8:
					text = "视频初始缓冲时长(s)";
					break;
				case 9:
					text = "HD最低下载速率(Mbit/s)";
					break;
				case 10:
					text = "SD最低下载速率(Mbit/s)";
					break;
				case 11:
					text = "视频播放卡顿次数(>=)";
					break;
				case 12:
					text = "清晰度门限 ";
					break;
				case 13:
					text = "服务接入时长(s)";
					break;
				case 14:
					text = "HTTP传输时长(s)";
					break;
				case 15:
					text = "上传速率门限(Mbps)";
					break;
				case 16:
					text = "服务接入时长(s) ";
					break;
				case 17:
					text = "HTTP传输时长(s)";
					break;
				case 18:
					text = "下载速率门限(Mbps)";
					break;
				case 19:
					text = "RB数不满样本比例门限(%)";
					break;
				case 20:
					text = "单点RB数不满足比例门限";
					break;
				case 21:
					text = "HSDPA码字门限(%)";
					break;
				case 22:
					text = "HSDPA码字受限比例门限(%)";
					break;
				default:
					break;
			}
			text = "P3业务问题分析->评估参数->"+text;
		}
		if("TCP数传问题分析参数".equals(PTitle)) {
			switch (i) {
				case 1:
					text = "DNS响应门限(s)";
					break;
				case 2:
					text = "二次握手时延门限(ms)";
					break;
				case 3:
					text = "下行RTT时延门限(ms)";
					break;
				case 4:
					text = "GET响应时延门限(ms)";
					break;
				case 5:
					text = "TCP ACK到HTTP GET时延门限(ms)";
					break;
				case 6:
					text = "POST响应时延门限(ms)";
					break;
				case 7:
					text = "TCP ACK到HTTP POST时延门限(ms)";
					break;
				case 8:
					text = "上行RTT时延门限(ms)";
					break;
				case 9:
					text = "接收窗口门限(byte)";
					break;
				case 10:
					text = "MSS门限(byte)";
					break;
				case 11:
					text = "丢包比例门限(%)";
					break;
				case 12:
					text = "乱序比例门限(%)";
					break;
				case 13:
					text = "重传比例门限(%)";
					break;
				case 14:
					text = "接收零窗口次数门限(>)";
					break;
				case 15:
					text = "快速重传次数(>)";
					break;
				case 16:
					text = "上行ACK重传次数(>)";
					break;
				case 17:
					text = "下载速率门限(Mbps)";
					break;
				case 18:
					text = "接收窗口收缩次数 ";
					break;
				default:
					break;
			}
			text = "P3业务问题分析->TCP数传问题分析参数->"+text;
		}
		if("覆盖干扰问题分析参数".equals(PTitle)) {
			switch (i) {
				case 1:
					text = "弱覆盖电平门限(dBm)";
					break;
				case 2:
					text = "弱覆盖质量门限(dBm)";
					break;
				case 3:
					text = "连续采样点数量 ";
					break;
				case 4:
					text = "问题采样点比例(%)";
					break;
				case 5:
					text = "干扰(场景1)RSCP(dBm)";
					break;
				case 6:
					text = "干扰(场景2)RSCP(dBm)";
					break;
				case 7:
					text = "干扰(场景1)EC/NO ";
					break;
				case 8:
					text = "干扰(场景2)EC/NO";
					break;
				case 9:
					text = "主服弱覆盖低电平门限(dBm)";
					break;
				case 10:
					text = "重叠覆盖RSRP差值门限(dBm) ";
					break;
				case 11:
					text = "强邻区电平门限(dBm) ";
					break;
				case 12:
					text = "干扰(场景1)RSRP门限(dBm) ";
					break;
				case 13:
					text = "问题点数门限";
					break;
				case 14:
					text = "干扰(场景1)SINR门限(dBm)";
					break;
				case 15:
					text = "跳跃点数门限";
					break;
				case 16:
					text = "干扰(场景2)RSRP门限(dBm)";
					break;
				case 17:
					text = "上行终端发射功率门限(dBm)";
					break;
				case 18:
					text = "干扰(场景2)SINR门限(dBm)";
					break;
				case 19:
					text = "无主服RSRP差门限值(dBm) ";
					break;
				case 20:
					text = "频繁切换时间区间(s)";
					break;
				case 21:
					text = "无主服邻区个数 ";
					break;
				case 22:
					text = "频繁切换次数门限 ";
					break;
				case 23:
					text = "重叠覆盖邻区个数 ";
					break;
				default:
					break;
			}
			text = "P3业务问题分析->覆盖干扰问题分析参数->"+text;
		}
		if("资源问题分析参数".equals(PTitle)) {
			switch (i) {
				case 1:
					text = "Non-HSSCCH利用率(%)";
					break;
				case 2:
					text = "上行端口拥塞率(%)";
					break;
				case 3:
					text = "Total TCP利用率(%)";
					break;
				case 4:
					text = "IUB下行带宽利用率(%)";
					break;
				case 5:
					text = "下行功率拥塞比例(%)";
					break;
				case 6:
					text = "下行端口拥塞率(%)";
					break;
				case 7:
					text = "HSSCCH码利用率(%) ";
					break;
				case 8:
					text = "上行端口丢帧率(%) ";
					break;
				case 9:
					text = "HSPDSCH码利用率(%)";
					break;
				case 10:
					text = "下行端口丢帧率(%) ";
					break;
				case 11:
					text = "码拥塞率(%) ";
					break;
				case 12:
					text = "DPA RLC重传率(%) ";
					break;
				case 13:
					text = "小区HSDPA用户数比例(%) ";
					break;
				case 14:
					text = "时延均值(ms) ";
					break;
				case 15:
					text = "小区HSUPA用户数比例(%) ";
					break;
				case 16:
					text = "上行CE利用率(%)";
					break;
				case 17:
					text = "IUB上行带宽利用率(%)";
					break;
				case 18:
					text = "下行CE利用率(%)";
					break;
				case 19:
					text = "下行PRB利用率过高门限(%)";
					break;
				case 20:
					text = "上行PRB利用率过高门限(%)";
					break;
				case 21:
					text = "CCE利用率过高门限(%)";
					break;
				case 22:
					text = "CFI3利用率过高门限(%)";
					break;
				case 23:
					text = "PUCCH利用率过高门限(%)";
					break;
				case 24:
					text = "SRS利用率过高门限(%) ";
					break;
				case 25:
					text = "PRACH利用率过高门限(%) ";
					break;
				case 26:
					text = "传输资源组丢包率过高门限(%)";
					break;
				default:
					break;
			}
			text = "P3业务问题分析->资源问题分析参数->"+text;
		}
		if("p3Scanner分析参数".equals(PTitle)) {
			switch (i) {
				case 1:
					text = "Scanner越区距离门限(m)";
					break;
				case 2:
					text = "邻区漏配最小距离门限(m) ";
					break;
				case 3:
					text = "扫描最小距离门限(m)";
					break;
				case 4:
					text = "LTE同频强邻区门限(dBm) ";
					break;
				case 5:
					text = "路段最小问题点数 ";
					break;
				case 6:
					text = "LTE异频强邻区门限(dBm) ";
					break;
				case 7:
					text = "Romes UMTS强邻区电平门限(dBm) ";
					break;
				case 8:
					text = "LTE异系统UMTS强邻区门限(dBm) ";
					break;
				case 9:
					text = "Romes LTE强邻区电平门限(dBm) ";
					break;
				case 10:
					text = "LTE异系统GSM强邻区门限(dBm) ";
					break;
				case 11:
					text = "Scanner GSM强邻区电平门限(dBm) ";
					break;
				case 12:
					text = "UMTS同频强邻区门限(dBm)";
					break;
				case 13:
					text = "Scanner UMTS强邻区电平门限(dBm) ";
					break;
				case 14:
					text = "UMTS异频强邻区门限(dBm) ";
					break;
				case 15:
					text = "Scanner LTE 强邻区电平门限(dBm) ";
					break;
				case 16:
					text = "UMTS异系统LTE强邻区门限(dBm) ";
					break;
				case 17:
					text = "LTE弱覆盖电平门限(dBm) ";
					break;
				case 18:
					text = "UMTS异系统GSM强邻区门限(dBm) ";
					break;
				case 19:
					text = "UMTS弱覆盖电平门限(dBm) ";
					break;
				case 20:
					text = "GSM异系统最小距离门限(m) ";
					break;
				case 21:
					text = "LTE弱覆盖质量门限(dBm) ";
					break;
				case 22:
					text = "UMTS异系统最小距离门限(m) ";
					break;
				case 23:
					text = "UMTS弱覆盖质量门限(dBm) ";
					break;
				default:
					break;
			}
			text = "P3业务问题分析->Scanner分析参数->"+text;
		}
		if("Scanner分析".equals(PTitle)) {
			switch (i) {
				case 1:
					text = "Scanner越区距离门限(m)";
					break;
				case 2:
					text = "邻区漏配最小距离门限(m) ";
					break;
				case 3:
					text = "扫描最小距离门限(m) ";
					break;
				case 4:
					text = "LTE同频强邻区门限(dBm) ";
					break;
				case 5:
					text = "路段最小问题点数 ";
					break;
				case 6:
					text = "LTE异频强邻区门限(dBm) ";
					break;
				case 7:
					text = "Romes UMTS强邻区电平门限(dBm) ";
					break;
				case 8:
					text = "LTE异系统UMTS强邻区门限(dBm) ";
					break;
				case 9:
					text = "Romes LTE强邻区电平门限(dBm) ";
					break;
				case 10:
					text = "LTE异系统GSM强邻区门限(dBm) ";
					break;
				case 11:
					text = "Scanner GSM强邻区电平门限(dBm) ";
					break;
				case 12:
					text = "UMTS同频强邻区门限(dBm)";
					break;
				case 13:
					text = "Scanner UMTS强邻区电平门限(dBm) ";
					break;
				case 14:
					text = "UMTS异频强邻区门限(dBm) ";
					break;
				case 15:
					text = "Scanner LTE 强邻区电平门限(dBm) ";
					break;
				case 16:
					text = "UMTS异系统LTE强邻区门限(dBm) ";
					break;
				case 17:
					text = "LTE弱覆盖电平门限(dBm) ";
					break;
				case 18:
					text = "UMTS异系统GSM强邻区门限(dBm) ";
					break;
				case 19:
					text = "UMTS弱覆盖电平门限(dBm) ";
					break;
				case 20:
					text = "GSM异系统最小距离门限(m) ";
					break;
				case 21:
					text = "LTE弱覆盖质量门限(dBm) ";
					break;
				case 22:
					text = "UMTS异系统最小距离门限(m) ";
					break;
				case 23:
					text = "UMTS弱覆盖质量门限(dBm) ";
					break;
				case 24:
					text = "问题点数门限";
					break;
				case 25:
					text = "跳跃点数门限";
					break;
				default:
					break;
			}
			text = "Scanner分析 ->"+text;
		}
		return text;
	}
}
