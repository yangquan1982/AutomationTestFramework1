package cloud_plugin.task.network_performance_analysis_center.theme_optimization;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.network_audit.NetWorkAuditPage;
import cloud_plugin.page.network_performance_analysis_center.theme_optimization.BenchmarkOptimizationPage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileCompare;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

/**
 * 排名提升优化
 **/
public class BenchmarkOptimizationTask {
	public static String createBenchmarkModel(WebDriver driver, String taskName, String[] functionSubitem,
			String taskType, String[] p3Operator, String[] CDRDataFile, String[] PCAPDataFile,
			String[] ROMESL3MessageFile, String[] ROMESUELogFile, String[] ScannerDataFile, String operatorIndexFile,
			String[] UMTSCifDataRNCFile, String[] UMTSCifDataNodeBFile, String[] LTECifDataFile,
			String[] UMTSTelephoneDataRNCFile, String UNTSTelephoneDataNodeBFile, String[] LTETelephoneDataFile,
			String[] RNCLicenseFile, String[] NodeBLicenseFile, String[] UMTSProjectParmFile,
			String[] LTEProjectParmFile, String[] GSMprojectParmFile, String RNCReportAnEmergencyFile,
			String eNodeBReportAnEmergencyFile, String UMTSParmBaseLineModelFile, String LTEParmBaseLineModelFile,
			String[] P3TaskAnalyseParme, String[] Web, String[] Youtube, String[] Upload, String[] Download,
			String[] other, String[] TCPUploadDataParame, String[] coverDisturbParameUMTS,
			String[] coverDisturbParameLTE, String[] resourceProblemAnalyseUMTS, String[] resourceProblemAnalyseLTE,
			String[] ScannerAnalyseParame, String[] TCPDNSsetUpModel, String[] CDRFileNameModel,
			String professionalWorkKQI, String[] analyseLatitude,
			// String[] scannerAnalyse,
			// String[] scannerParame,
			String[] adjacentParameSetUp, boolean tacitlyApproveResume, String defaultWindowID, String ResultPath) {
		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 功能子项
		String functionSubitemStr = "";
		if (null != functionSubitem) {
			for (int i = 0; i < functionSubitem.length; i++) {
				if ("P3业务问题分析".equals(functionSubitem[i])) {
					CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_p3Analyse, true);
					// 任务类型
					CommonJQ.click(driver, "#serviceType~span", true);
					if ("Youtube".equals(taskType)) {
						CommonJQ.click(driver, "#Youtube", true);
					} else if ("Web".equals(taskType)) {
						CommonJQ.click(driver, "#Web", true);
					} else if ("UL1M".equals(taskType)) {
						CommonJQ.click(driver, "#UL1M", true);
					} else if ("DL3M".equals(taskType)) {
						CommonJQ.click(driver, "#DL3M", true);
					} else if ("UL10S".equals(taskType)) {
						CommonJQ.click(driver, "#UL10S", true);
					} else if ("DL10S".equals(taskType)) {
						CommonJQ.click(driver, "#DL10S", true);
					} else if ("CSFB".equals(taskType)) {
						CommonJQ.click(driver, "#CSFB", true);
					}
				} else if ("GAP分析".equals(functionSubitem[i])) {
					CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_gapAnalyse, true);
				} else if ("Scanner数据分析".equals(functionSubitem[i])) {
					CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_scaDataAnalyse, true);
				}
				functionSubitemStr = functionSubitemStr + functionSubitem[i];
			}

			if (functionSubitemStr.indexOf("参数核查") < 0) {
				CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_paramCheck, true);
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);// 迁出 iframe
		// 分析数据
		if (CDRDataFile != null) {// CDRData
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#cdrData~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, CDRDataFile,
						"$('iframe[name=main]').contents().find('#cdrData~span').click()", defaultWindowID);
			}
			// 运营商
			if (functionSubitemStr.indexOf("P3业务问题分析") > -1) {
				if (p3Operator != null) {
					if (p3Operator[0] != null) {
						SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
						CommonJQ.click(driver, BenchmarkOptimizationPage.p3OperatorBtn, true, "运营商");
						if ("vodafone".equals(p3Operator[0])) {
							CommonJQ.click(driver, "#vodafone", true, "运营商->vodafone");
						} else if ("telefonica".equals(p3Operator[0])) {
							CommonJQ.click(driver, "#telefonica", true, "运营商->telefonica");
						} else if ("telekom".equals(p3Operator[0])) {
							CommonJQ.click(driver, "#telekom", true, "运营商->telekom");
						} else if ("e_plus".equals(p3Operator[0])) {
							CommonJQ.click(driver, "#e_plus", true, "运营商->e_plus");
						}
						SwitchDriver.switchDriverToSEQ(driver);// 迁出 iframe
					}
					if (p3Operator[1] != null) {
						if (!"TRUE".equals(p3Operator[1].toUpperCase())) {
							CommonJQ.click(driver, BenchmarkOptimizationPage.onlyQueTicketTcpAnalysisCheckBox, true,
									"运营商->只进行问题话单的TCP分析");

						}
					}
				}
			}
		}
		if (PCAPDataFile != null) {// PCAData
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#pcapData~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, PCAPDataFile,
						"$('iframe[name=main]').contents().find('#pcapData~span').click()", defaultWindowID);
			}
		}
		if (ROMESL3MessageFile != null) {// ROMES L3 Message
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#romesL3Message~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, ROMESL3MessageFile,
						"$('iframe[name=main]').contents().find('#romesL3Message~span').click()", defaultWindowID);
			}
		}
		if (ROMESUELogFile != null) {// ROMES UE Log
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#remoseUeLog~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, ROMESUELogFile,
						"$('iframe[name=main]').contents().find('#remoseUeLog~span').click()", defaultWindowID);
			}
		}
		if (ScannerDataFile != null) {// Scanner Data
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#scannerData~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, ScannerDataFile,
						"$('iframe[name=main]').contents().find('#scannerData~span').click()", defaultWindowID);
			}
		}
		if (operatorIndexFile != null) {// 运营商索引文件
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			if (CommonJQ.isExisted(driver, BenchmarkOptimizationPage.operatorIndexFile, true)) {
				CommonJQ.click(driver, BenchmarkOptimizationPage.operatorIndexFile, true, "导入模板->浏览");
				CommonFile.ChooseOneFile(operatorIndexFile);
				CommonJQ.click(driver, BenchmarkOptimizationPage.isOK, true, "导入模板->浏览->确定");
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (UMTSCifDataRNCFile != null) {// UMTS配置数据(RNC)
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#umtsConfigureRnc~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, UMTSCifDataRNCFile,
						"$('iframe[name=main]').contents().find('#umtsConfigureRnc~span').click()", defaultWindowID);
			}
		}
		if (UMTSCifDataNodeBFile != null) {// UMTS配置数据(NodeB)
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#umtsConfigureNodeB~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, UMTSCifDataNodeBFile,
						"$('iframe[name=main]').contents().find('#umtsConfigureNodeB~span').click()", defaultWindowID);
			}
		}
		if (LTECifDataFile != null) {// LTE配置数据
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#lteConfigureData~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, LTECifDataFile,
						"$('iframe[name=main]').contents().find('#lteConfigureData~span').click()", defaultWindowID);
			}
		}
		if (UMTSTelephoneDataRNCFile != null) {// UMTS话统数据RNC
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#umtsMikeDataRnc~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, UMTSTelephoneDataRNCFile,
						"$('iframe[name=main]').contents().find('#umtsMikeDataRnc~span').click()", defaultWindowID);
			}
		}
		if (UNTSTelephoneDataNodeBFile != null) {// UMTS话统数据NodeB
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			if (CommonJQ.isExisted(driver, BenchmarkOptimizationPage.umtsMikeDataNodeBBtn, true)) {
				CommonJQ.click(driver, BenchmarkOptimizationPage.umtsMikeDataNodeBBtn, true, "导入模板->浏览");
				CommonFile.ChooseOneFile(UNTSTelephoneDataNodeBFile);
				CommonJQ.click(driver, BenchmarkOptimizationPage.isOK, true, "导入模板->浏览->确定");
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (LTETelephoneDataFile != null) {// LTE话筒数据
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#lteMikeData~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, LTETelephoneDataFile,
						"$('iframe[name=main]').contents().find('#lteMikeData~span').click()", defaultWindowID);
			}
		}
		if (RNCLicenseFile != null) {//
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#rncLicense~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, RNCLicenseFile,
						"$('iframe[name=main]').contents().find('#rncLicense~span').click()", defaultWindowID);
			}
		}
		if (NodeBLicenseFile != null) {//
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#nodeBLicense~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, NodeBLicenseFile,
						"$('iframe[name=main]').contents().find('#nodeBLicense~span').click()", defaultWindowID);
			}
		}
		if (UMTSProjectParmFile != null) {// UMTS工程参数
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#umtsParamData~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, UMTSProjectParmFile,
						"$('iframe[name=main]').contents().find('#umtsParamData~span').click()", defaultWindowID);
			}
		}
		if (LTEProjectParmFile != null) {// LTE工程参数
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#lteParam~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, LTEProjectParmFile,
						"$('iframe[name=main]').contents().find('#lteParam~span').click()", defaultWindowID);
			}
		}
		if (GSMprojectParmFile != null) {// GSM工程参数
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			boolean bool = CommonJQ.isExisted(driver, "#gsmParamData~span", true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (bool) {
				GetDataByTypeTask.chooseFolder(driver, GSMprojectParmFile,
						"$('iframe[name=main]').contents().find('#gsmParamData~span').click()", defaultWindowID);
			}
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		if (RNCReportAnEmergencyFile != null) {// RNC告警数据
			if (CommonJQ.isExisted(driver, BenchmarkOptimizationPage.select_rncNoticeDataTemplateDownloadBtn, true)) {
				CommonJQ.click(driver, BenchmarkOptimizationPage.select_rncNoticeDataTemplateDownloadBtn, true,
						"导入模板->浏览");
				CommonFile.ChooseOneFile(RNCReportAnEmergencyFile);
				CommonJQ.click(driver, BenchmarkOptimizationPage.isOK, true, "导入模板->浏览->确定");
			}
		}
		if (eNodeBReportAnEmergencyFile != null) {// eNodeB告警数据
			if (CommonJQ.isExisted(driver, BenchmarkOptimizationPage.enodeBNoticeDataBtn, true)) {
				CommonJQ.click(driver, BenchmarkOptimizationPage.enodeBNoticeDataBtn, true, "导入模板->浏览");
				CommonFile.ChooseOneFile(eNodeBReportAnEmergencyFile);
				CommonJQ.click(driver, BenchmarkOptimizationPage.isOK, true, "导入模板->浏览->确定");
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
		/*
		 * //运营商模板下载 if(operatorIndexModel) { //String BaselineHome =
		 * BaselinePath+"\\专家附件"; String ResultHome = ResultPath+"\\";
		 * BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome,
		 * "",operatorIndexModel,BenchmarkOptimizationPage.operatorIndexModelBtn
		 * ); //FileCompare.SameNameCompareInPath(BaselineHome, ResultHome); }
		 * //UMTS话统数据NodeB模板下载 if(UNTSTelephoneDataNodeB) { //String
		 * BaselineHome = BaselinePath+"\\专家附件"; String ResultHome =
		 * ResultPath+"\\"; BenchmarkOptimizationPage.downLoad_Mould(driver,
		 * ResultHome, "",UNTSTelephoneDataNodeB,
		 * BenchmarkOptimizationPage.UNTSTelephoneDataNodeBModelBtn);
		 * //FileCompare.SameNameCompareInPath(BaselineHome, ResultHome); }
		 * //RNC告警数据模板下载 if(RNCReportAnEmergency) { //String BaselineHome =
		 * BaselinePath+"\\专家附件"; String ResultHome = ResultPath+"\\";
		 * BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome,
		 * "",RNCReportAnEmergency,
		 * BenchmarkOptimizationPage.RNCReportAnEmergencyModelBtn);
		 * //FileCompare.SameNameCompareInPath(BaselineHome, ResultHome); }
		 * //eNodeB告警数据模板下载 if(eNodeBReportAnEmergency) { //String BaselineHome
		 * = BaselinePath+"\\专家附件"; String ResultHome = ResultPath+"\\";
		 * BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome,
		 * "",eNodeBReportAnEmergency,
		 * BenchmarkOptimizationPage.eNodeBReportAnEmergencyModelBtn);
		 * //FileCompare.SameNameCompareInPath(BaselineHome, ResultHome); }
		 */
		// 参数设置
		if (!"".equals(functionSubitemStr)) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
			CommonJQ.click(driver, BenchmarkOptimizationPage.localFileChooseBtn, true, "参数设置");
			SwitchDriver.switchDriverToSEQ(driver);// 迁出 iframe
			BenchmarkOptimizationPage.setUpParame(driver, functionSubitemStr, UMTSParmBaseLineModelFile,
					LTEParmBaseLineModelFile, P3TaskAnalyseParme, Web, Youtube, Upload, Download, other,
					TCPUploadDataParame, coverDisturbParameUMTS, coverDisturbParameLTE, resourceProblemAnalyseUMTS,
					resourceProblemAnalyseLTE, ScannerAnalyseParame, TCPDNSsetUpModel, CDRFileNameModel,
					professionalWorkKQI, analyseLatitude, adjacentParameSetUp, tacitlyApproveResume, ResultPath);
		}
		return NetWorkAuditPage.setTaskName(driver, taskName);
	}

	/**
	 * 专家求助
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param HelpType
	 *            ：处理类型：远程交付任务、任务执行失败等
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附件路径
	 */
	public static void expertHelp(WebDriver driver, String taskName, String HelpType, String detailedDescription,
			String Filepath) {

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, "li.expertHelpDetailLi  div[class=\"zz_select msel\"] input[type=\"text\"]", true,
				"专家求助:求助标题 下拉框");
		CommonJQ.click(driver, "ul#mshowflowTitle li#" + HelpType, true, "专家求助:求助标题 ");

		CommonJQ.value(driver, "textarea[class=\"expertHelpTextArea ng-pristine ng-valid\"]", detailedDescription, true,
				"专家求助:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#userfilePick", true, "专家求助:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		CommonJQ.click(driver, "div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]", true, "专家求助:提交按钮");
		if (CommonJQ.isExisted(driver, BenchmarkOptimizationPage.isOK, true)) {
			CommonJQ.click(driver, BenchmarkOptimizationPage.isOK, true);
		}
		LoadingPage.Loading(driver);
		String AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
		String ExMessage = "专家分析中...";

		Assert.assertTrue("专家求助界面，期望值：" + ExMessage + "，实际值：" + AcMessage, ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 专家处理
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param ProcType
	 *            ：处理类型：请用户追加数据、问题分析完成
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附件路径
	 */
	public static void expertProc(WebDriver driver, String taskName, String ProcType, String detailedDescription,
			String Filepath) {

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#addDataReply", true, "专家反馈:请用户追加数据");
		} else {
			CommonJQ.click(driver, "#finishReply", true, "专家反馈:问题分析完成");
		}

		CommonJQ.value(driver, "textarea[class=\"expertHelpTextArea ng-pristine ng-valid\"]", detailedDescription, true,
				"专家反馈:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#expertReplyFilePick", true, "专家反馈:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		CommonJQ.click(driver, "div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]", true, "专家反馈:提交按钮");
		if (CommonJQ.isExisted(driver, BenchmarkOptimizationPage.isOK, true)) {
			CommonJQ.click(driver, BenchmarkOptimizationPage.isOK, true);
		}
		LoadingPage.Loading(driver);
		String AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
		String ExMessage = "";
		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			ExMessage = "用户追加数据中...";
		} else {
			ExMessage = "用户确认结果中...";
		}

		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage, ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 用户确认
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param ProcType
	 *            ：继续求助，已解决
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附近文件路径
	 */
	public static void userConfirm(WebDriver driver, String taskName, String ProcType, String detailedDescription,
			String Filepath, int starNum) {

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("继续求助".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#seekForHelp", true, "用户确认:继续求助");
		} else {
			CommonJQ.click(driver, "#finishResolve", true, "用户确认:已解决");
		}

		CommonJQ.value(driver, "textarea[class=\"expertHelpTextArea ng-pristine ng-valid\"]", detailedDescription, true,
				"用户确认:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#usereComfirmFilePick", true, "用户确认:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		for (int i = 0; i < starNum; i++) {
			CommonJQ.click(driver, ".star li", true, i, "用户确认:用户评分" + (i + 1) + "星");
		}
		CommonJQ.click(driver, "div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]", true, "用户确认:提交按钮");
		if (CommonJQ.isExisted(driver, BenchmarkOptimizationPage.isOK, true)) {
			CommonJQ.click(driver, BenchmarkOptimizationPage.isOK, true);
		}
		LoadingPage.Loading(driver);
		String AcMessage = "";
		String ExMessage = "";
		if ("继续求助".equalsIgnoreCase(ProcType)) {
			AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
			ExMessage = "专家分析中...";
		} else {
			AcMessage = CommonJQ.text(driver, "div.expertHistoryTab span", "", 0);
			ExMessage = "用户求助历史记录";
		}
		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage, ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/***
	 * 模板下载
	 **/
	public static void downLoadModel(WebDriver driver, String[] CDRDataFile, boolean operatorIndexModel,
			boolean UNTSTelephoneDataNodeB, boolean RNCReportAnEmergency, boolean eNodeBReportAnEmergency,
			boolean UMTSParmBaseLineModel, boolean LTEParmBaseLineModel, boolean P3TaskAnalyseParmeModel,
			boolean TCPDNSsetUpModel, boolean scannerAnalyse, String BaselinePath, String ResultPath,
			String defaultWindowID) {
		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_paramCheck,
		// true);
		CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_p3Analyse, true);
		CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_gapAnalyse, true);
		CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_scaDataAnalyse, true);
		SwitchDriver.switchDriverToSEQ(driver);
		GetDataByTypeTask.chooseFolder(driver, CDRDataFile,
				"$('iframe[name=main]').contents().find('#cdrData~span').click()", defaultWindowID);
		// 运营商模板下载
		if (operatorIndexModel) {
			String BaselineHome = ResultPath + "\\运营商索引文件\\";
			String ResultHome = ResultPath + "\\运营商索引文件\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "OperatorIndex_template.xlsx",
					operatorIndexModel, BenchmarkOptimizationPage.operatorIndexModelBtn);
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
		// UMTS话统数据NodeB模板下载
		if (UNTSTelephoneDataNodeB) {
			String BaselineHome = BaselinePath + "\\UMTS话统数据（RNC）\\";
			String ResultHome = ResultPath + "\\UMTS话统数据（RNC）\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "NodeB_PerformanceData_Template.zip",
					UNTSTelephoneDataNodeB, BenchmarkOptimizationPage.UNTSTelephoneDataNodeBModelBtn);
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
		// RNC告警数据模板下载
		if (RNCReportAnEmergency) {
			String BaselineHome = BaselinePath + "\\RNC告警数据\\";
			String ResultHome = ResultPath + "\\RNC告警数据\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "RNCAlarmTemplate.csv", RNCReportAnEmergency,
					BenchmarkOptimizationPage.RNCReportAnEmergencyModelBtn);
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
		// eNodeB告警数据模板下载
		if (eNodeBReportAnEmergency) {
			String BaselineHome = BaselinePath + "\\eNodeB告警数据\\";
			String ResultHome = ResultPath + "\\eNodeB告警数据\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "eNodebAlarmTemplate.csv",
					eNodeBReportAnEmergency, BenchmarkOptimizationPage.eNodeBReportAnEmergencyModelBtn);
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, BenchmarkOptimizationPage.localFileChooseBtn, true, "参数设置");
		SwitchDriver.switchDriverToSEQ(driver);// 迁出 iframe
		// UMTS参数基线模
		if (UMTSParmBaseLineModel) {
			String BaselineHome = BaselinePath + "\\UMTS参数基线模板\\";
			String ResultHome = ResultPath + "\\UMTS参数基线模板\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "UMTSParamCheckCfg.xlsx",
					UMTSParmBaseLineModel, BenchmarkOptimizationPage.UmtsDownloadBtn);
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
		// LTE参数基线模板
		if (LTEParmBaseLineModel) {
			String BaselineHome = BaselinePath + "\\LTE参数基线模板\\";
			String ResultHome = ResultPath + "\\LTE参数基线模板\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "LTEParamCheckCfg.xlsx", LTEParmBaseLineModel,
					BenchmarkOptimizationPage.LteDownloadBtn);
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver,
				// "span:contains(\"P3业务问题分析\")",
				"#p3BusinessAnalysis>div>span", true, "P3业务问题分析");
		SwitchDriver.switchDriverToSEQ(driver);// 迁出 iframe
		// CDR文件名校验规则模板
		if (P3TaskAnalyseParmeModel) { // CDR文件名校验规则模板 下载
			String ResultHome = ResultPath + "\\CDR文件名校验规则模板\\";
			String BaselineHome = BaselinePath + "\\CDR文件名校验规则模板\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "CDRFileNameAdapterRuleTemplate.xlsx",
					P3TaskAnalyseParmeModel,
					"a[href=\"downloadTemplateAction?templateType=CDRFileNameAdapterRuleTemplate\"]");//
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
		// TCP DNS模板设置
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, "#TCPDNS>div>div>span", true, "TCP DNS模板设置");
		SwitchDriver.switchDriverToSEQ(driver);// 迁出 iframe
		// TCP DNS模板
		if (TCPDNSsetUpModel) {
			String BaselineHome = BaselinePath + "\\TCPDNS模板\\";
			String ResultHome = ResultPath + "\\TCPDNS模板\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "TCPDNSConfigTemplate.xlsx", TCPDNSsetUpModel,
					"a[href=\"downloadTemplateAction?templateType=TcpDnsTemplate\"]");
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
		// 运营商和频点配置模板
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// Scanner分析
		CommonJQ.click(driver, "#scannerAnalysis>div>span", true, "Scanner分析");

		SwitchDriver.switchDriverToSEQ(driver);// 迁出 iframe
		if (scannerAnalyse) {
			String BaselineHome = BaselinePath + "\\运营商和频点配置模板\\";
			String ResultHome = ResultPath + "\\运营商和频点配置模板\\";
			BenchmarkOptimizationPage.downLoad_Mould(driver, ResultHome, "OperatorAndFreqTemplate.xlsx", scannerAnalyse,
					"a[href=\"downloadTemplateAction?templateType=ScannerOperatorAndFreqTemplate\"]");
			FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
		}
	}

	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @param ResultHome
	 *            ：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			if (reportName[i].endsWith("zip")) {
				ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i], ResultHome);
			} else {
				FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + reportName[i],
						ResultHome + "\\" + reportName[i]);
			}
		}

	}

	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @return String：报告完整名称
	 */
	public static String[] downLoad_report(WebDriver driver, String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LoadingPage.Loading(driver, ".col6 a", "报告下载按钮");
		int reportNum = CommonJQ.length(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", true);
		String[] reportName = new String[reportNum];
		for (int i = 0; i < reportNum; i++) {
			CommonJQ.click(driver, ".col6 a", true, i, "报告下载按钮");
			reportName[i] = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", i);
			TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName[i]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}

	public static void tacitlyApproveCompare(WebDriver driver, String[] CDRDataFile, String catalogue1,
			String catalogue2, String[] value, String defaultWindowID) {
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_paramCheck, true);
		CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_p3Analyse, true);
		CommonJQ.click(driver, BenchmarkOptimizationPage.CheckBox_scaDataAnalyse, true);
		SwitchDriver.switchDriverToSEQ(driver);
		GetDataByTypeTask.chooseFolder(driver, CDRDataFile,
				"$('iframe[name=main]').contents().find('#cdrData~span').click()", defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, BenchmarkOptimizationPage.localFileChooseBtn, true, "参数设置");
		// 打开一级目录
		if ("P3业务问题分析".equals(catalogue1)) {
			// 打开二级目录
			if ("评估参数".equals(catalogue2)) {
				CommonJQ.click(driver, "#evaluationParam>div>div>span", true, "评估参数");
				String id = "#WebParam";

				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 1) + "的期望值：" + value[0] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[0].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 2) + "的期望值：" + value[1] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[1].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 3) + "的期望值：" + value[2] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[2].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				id = "#YoutubeParam";
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 4) + "的期望值：" + value[3] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[3].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 5) + "的期望值：" + value[4] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[4].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 6) + "的期望值：" + value[5] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[5].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 7) + "的期望值：" + value[6] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_r", ""),
						value[6].equals(CommonJQ.getValue(driver, id + "2_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 8) + "的期望值：" + value[7] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_l", ""),
						value[7].equals(CommonJQ.getValue(driver, id + "3_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 9) + "的期望值：" + value[8] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_r", ""),
						value[8].equals(CommonJQ.getValue(driver, id + "3_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 10) + "的期望值：" + value[9] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_l", ""),
						value[9].equals(CommonJQ.getValue(driver, id + "4_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 11) + "的期望值：" + value[10] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_r", ""),
						value[10].equals(CommonJQ.getValue(driver, id + "4_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 12) + "的期望值：" + value[11] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_l", ""),
						value[11].equals(CommonJQ.getValue(driver, id + "5_l", "")));
				id = "#UploadParam";
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 13) + "的期望值：" + value[12] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[12].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 14) + "的期望值：" + value[13] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[13].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 15) + "的期望值：" + value[14] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[14].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				id = "#DownloadParam";
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 16) + "的期望值：" + value[15] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[15].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 17) + "的期望值：" + value[16] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[16].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 18) + "的期望值：" + value[17] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[17].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				id = "#OtherParam";
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 19) + "的期望值：" + value[18] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[18].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 20) + "的期望值：" + value[19] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[19].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 21) + "的期望值：" + value[20] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[20].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("评估参数", 22) + "的期望值：" + value[21] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_r", ""),
						value[21].equals(CommonJQ.getValue(driver, id + "2_r", "")));
			}
			if ("TCP数传问题分析参数".equals(catalogue2)) {
				CommonJQ.click(driver, "#TCPProblemParameters>div>div>span", true, "TCP数传问题分析");
				String id = "#TCPParam";

				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 1) + "的期望值：" + value[0] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[0].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 2) + "的期望值：" + value[1] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[1].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 3) + "的期望值：" + value[2] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[2].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 4) + "的期望值：" + value[3] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_r", ""),
						value[3].equals(CommonJQ.getValue(driver, id + "2_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 5) + "的期望值：" + value[4] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_l", ""),
						value[4].equals(CommonJQ.getValue(driver, id + "3_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 6) + "的期望值：" + value[5] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_r", ""),
						value[5].equals(CommonJQ.getValue(driver, id + "3_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 7) + "的期望值：" + value[6] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_l", ""),
						value[6].equals(CommonJQ.getValue(driver, id + "4_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 8) + "的期望值：" + value[7] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_r", ""),
						value[7].equals(CommonJQ.getValue(driver, id + "4_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 9) + "的期望值：" + value[8] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_l", ""),
						value[8].equals(CommonJQ.getValue(driver, id + "5_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 10) + "的期望值：" + value[9] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_r", ""),
						value[9].equals(CommonJQ.getValue(driver, id + "5_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 11) + "的期望值：" + value[10] + "，实际值："
								+ CommonJQ.getValue(driver, id + "6_l", ""),
						value[10].equals(CommonJQ.getValue(driver, id + "6_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 12) + "的期望值：" + value[11] + "，实际值："
								+ CommonJQ.getValue(driver, id + "6_r", ""),
						value[11].equals(CommonJQ.getValue(driver, id + "6_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 13) + "的期望值：" + value[12] + "，实际值："
								+ CommonJQ.getValue(driver, id + "7_l", ""),
						value[12].equals(CommonJQ.getValue(driver, id + "7_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 14) + "的期望值：" + value[13] + "，实际值："
								+ CommonJQ.getValue(driver, id + "7_r", ""),
						value[13].equals(CommonJQ.getValue(driver, id + "7_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 15) + "的期望值：" + value[14] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_l", ""),
						value[14].equals(CommonJQ.getValue(driver, id + "8_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 16) + "的期望值：" + value[15] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_r", ""),
						value[15].equals(CommonJQ.getValue(driver, id + "8_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("TCP数传问题分析参数", 17) + "的期望值：" + value[16] + "，实际值："
								+ CommonJQ.getValue(driver, id + "9_l", ""),
						value[16].equals(CommonJQ.getValue(driver, id + "9_l", "")));
			} else if ("覆盖干扰问题分析参数".equals(catalogue2)) {
				CommonJQ.click(driver, "#overrideProblemParameters>div>div>span", true, "覆盖干扰问题分析参数");

				String id = "#overrideUMTSParam";
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 1) + "的期望值：" + value[0] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[0].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 2) + "的期望值：" + value[1] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[1].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 3) + "的期望值：" + value[2] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[2].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 4) + "的期望值：" + value[3] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_r", ""),
						value[3].equals(CommonJQ.getValue(driver, id + "2_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 5) + "的期望值：" + value[4] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_l", ""),
						value[4].equals(CommonJQ.getValue(driver, id + "3_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 6) + "的期望值：" + value[5] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_r", ""),
						value[5].equals(CommonJQ.getValue(driver, id + "3_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 7) + "的期望值：" + value[6] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_l", ""),
						value[6].equals(CommonJQ.getValue(driver, id + "4_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 8) + "的期望值：" + value[7] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_r", ""),
						value[7].equals(CommonJQ.getValue(driver, id + "4_r", "")));
				id = "#overrideLTEParam";
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 9) + "的期望值：" + value[8] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[8].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 10) + "的期望值：" + value[9] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[9].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 11) + "的期望值：" + value[10] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[10].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 12) + "的期望值：" + value[11] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_r", ""),
						value[11].equals(CommonJQ.getValue(driver, id + "2_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 13) + "的期望值：" + value[12] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_l", ""),
						value[12].equals(CommonJQ.getValue(driver, id + "3_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 14) + "的期望值：" + value[13] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_r", ""),
						value[13].equals(CommonJQ.getValue(driver, id + "3_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 15) + "的期望值：" + value[14] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_l", ""),
						value[14].equals(CommonJQ.getValue(driver, id + "4_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 16) + "的期望值：" + value[15] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_r", ""),
						value[15].equals(CommonJQ.getValue(driver, id + "4_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 17) + "的期望值：" + value[16] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_l", ""),
						value[16].equals(CommonJQ.getValue(driver, id + "5_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 18) + "的期望值：" + value[17] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_r", ""),
						value[17].equals(CommonJQ.getValue(driver, id + "5_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 19) + "的期望值：" + value[18] + "，实际值："
								+ CommonJQ.getValue(driver, id + "6_l", ""),
						value[18].equals(CommonJQ.getValue(driver, id + "6_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 20) + "的期望值：" + value[19] + "，实际值："
								+ CommonJQ.getValue(driver, id + "6_r", ""),
						value[19].equals(CommonJQ.getValue(driver, id + "6_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 21) + "的期望值：" + value[20] + "，实际值："
								+ CommonJQ.getValue(driver, id + "7_l", ""),
						value[20].equals(CommonJQ.getValue(driver, id + "7_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 22) + "的期望值：" + value[21] + "，实际值："
								+ CommonJQ.getValue(driver, id + "7_r", ""),
						value[21].equals(CommonJQ.getValue(driver, id + "7_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("覆盖干扰问题分析参数", 23) + "的期望值：" + value[22] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_l", ""),
						value[22].equals(CommonJQ.getValue(driver, id + "8_l", "")));

			} else if ("资源问题分析参数".equals(catalogue2)) {
				CommonJQ.click(driver, "#resourceProblemParameters>div>div>span", true, "资源问题分析参数 ");
				String id = "#resourceUMTSParam";
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 1) + "的期望值：" + value[0] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[0].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 2) + "的期望值：" + value[1] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[1].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 3) + "的期望值：" + value[2] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[2].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 4) + "的期望值：" + value[3] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_r", ""),
						value[3].equals(CommonJQ.getValue(driver, id + "2_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 5) + "的期望值：" + value[4] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_l", ""),
						value[4].equals(CommonJQ.getValue(driver, id + "3_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 6) + "的期望值：" + value[5] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_r", ""),
						value[5].equals(CommonJQ.getValue(driver, id + "3_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 7) + "的期望值：" + value[6] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_l", ""),
						value[6].equals(CommonJQ.getValue(driver, id + "4_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 8) + "的期望值：" + value[7] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_r", ""),
						value[7].equals(CommonJQ.getValue(driver, id + "4_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 9) + "的期望值：" + value[8] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_l", ""),
						value[8].equals(CommonJQ.getValue(driver, id + "5_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 10) + "的期望值：" + value[9] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_r", ""),
						value[9].equals(CommonJQ.getValue(driver, id + "5_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 11) + "的期望值：" + value[10] + "，实际值："
								+ CommonJQ.getValue(driver, id + "6_l", ""),
						value[10].equals(CommonJQ.getValue(driver, id + "6_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 12) + "的期望值：" + value[11] + "，实际值："
								+ CommonJQ.getValue(driver, id + "6_r", ""),
						value[11].equals(CommonJQ.getValue(driver, id + "6_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 13) + "的期望值：" + value[12] + "，实际值："
								+ CommonJQ.getValue(driver, id + "7_l", ""),
						value[12].equals(CommonJQ.getValue(driver, id + "7_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 14) + "的期望值：" + value[13] + "，实际值："
								+ CommonJQ.getValue(driver, id + "7_r", ""),
						value[13].equals(CommonJQ.getValue(driver, id + "7_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 15) + "的期望值：" + value[14] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_l", ""),
						value[14].equals(CommonJQ.getValue(driver, id + "8_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 16) + "的期望值：" + value[15] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_r", ""),
						value[15].equals(CommonJQ.getValue(driver, id + "8_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 17) + "的期望值：" + value[16] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_l", ""),
						value[16].equals(CommonJQ.getValue(driver, id + "9_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 18) + "的期望值：" + value[17] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_r", ""),
						value[17].equals(CommonJQ.getValue(driver, id + "9_r", "")));
				id = "#resourceLTEParam";
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 19) + "的期望值：" + value[18] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[18].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 20) + "的期望值：" + value[19] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[19].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 21) + "的期望值：" + value[20] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[20].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 22) + "的期望值：" + value[12] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_r", ""),
						value[21].equals(CommonJQ.getValue(driver, id + "2_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 23) + "的期望值：" + value[22] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_l", ""),
						value[22].equals(CommonJQ.getValue(driver, id + "3_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 24) + "的期望值：" + value[23] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_r", ""),
						value[23].equals(CommonJQ.getValue(driver, id + "3_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 25) + "的期望值：" + value[24] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_l", ""),
						value[24].equals(CommonJQ.getValue(driver, id + "4_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("资源问题分析参数 ", 26) + "的期望值：" + value[25] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_r", ""),
						value[25].equals(CommonJQ.getValue(driver, id + "4_r", "")));

			} else if ("Scanner分析参数".equals(catalogue2)) {
				CommonJQ.click(driver, "#scannerParameters>div>div>span", true, "Scanner分析参数");
				String id = "#scannerP3Param";// p3Scanner分析参数
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 1) + "的期望值：" + value[0] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_l", ""),
						value[0].equals(CommonJQ.getValue(driver, id + "1_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 2) + "的期望值：" + value[1] + "，实际值："
								+ CommonJQ.getValue(driver, id + "1_r", ""),
						value[1].equals(CommonJQ.getValue(driver, id + "1_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 3) + "的期望值：" + value[2] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_l", ""),
						value[2].equals(CommonJQ.getValue(driver, id + "2_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 4) + "的期望值：" + value[3] + "，实际值："
								+ CommonJQ.getValue(driver, id + "2_r", ""),
						value[3].equals(CommonJQ.getValue(driver, id + "2_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 5) + "的期望值：" + value[4] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_l", ""),
						value[4].equals(CommonJQ.getValue(driver, id + "3_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 6) + "的期望值：" + value[5] + "，实际值："
								+ CommonJQ.getValue(driver, id + "3_r", ""),
						value[5].equals(CommonJQ.getValue(driver, id + "3_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 7) + "的期望值：" + value[6] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_l", ""),
						value[6].equals(CommonJQ.getValue(driver, id + "4_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 8) + "的期望值：" + value[7] + "，实际值："
								+ CommonJQ.getValue(driver, id + "4_r", ""),
						value[7].equals(CommonJQ.getValue(driver, id + "4_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 9) + "的期望值：" + value[8] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_l", ""),
						value[8].equals(CommonJQ.getValue(driver, id + "5_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 10) + "的期望值：" + value[9] + "，实际值："
								+ CommonJQ.getValue(driver, id + "5_r", ""),
						value[9].equals(CommonJQ.getValue(driver, id + "5_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 11) + "的期望值：" + value[10] + "，实际值："
								+ CommonJQ.getValue(driver, id + "6_l", ""),
						value[10].equals(CommonJQ.getValue(driver, id + "6_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 12) + "的期望值：" + value[11] + "，实际值："
								+ CommonJQ.getValue(driver, id + "6_r", ""),
						value[11].equals(CommonJQ.getValue(driver, id + "6_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 13) + "的期望值：" + value[12] + "，实际值："
								+ CommonJQ.getValue(driver, id + "7_l", ""),
						value[12].equals(CommonJQ.getValue(driver, id + "7_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 14) + "的期望值：" + value[13] + "，实际值："
								+ CommonJQ.getValue(driver, id + "7_r", ""),
						value[13].equals(CommonJQ.getValue(driver, id + "7_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 15) + "的期望值：" + value[14] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_l", ""),
						value[14].equals(CommonJQ.getValue(driver, id + "8_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 16) + "的期望值：" + value[15] + "，实际值："
								+ CommonJQ.getValue(driver, id + "8_r", ""),
						value[15].equals(CommonJQ.getValue(driver, id + "8_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 17) + "的期望值：" + value[16] + "，实际值："
								+ CommonJQ.getValue(driver, id + "9_l", ""),
						value[16].equals(CommonJQ.getValue(driver, id + "9_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 18) + "的期望值：" + value[17] + "，实际值："
								+ CommonJQ.getValue(driver, id + "9_r", ""),
						value[17].equals(CommonJQ.getValue(driver, id + "9_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 19) + "的期望值：" + value[18] + "，实际值："
								+ CommonJQ.getValue(driver, id + "10_l", ""),
						value[18].equals(CommonJQ.getValue(driver, id + "10_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 20) + "的期望值：" + value[19] + "，实际值："
								+ CommonJQ.getValue(driver, id + "10_r", ""),
						value[19].equals(CommonJQ.getValue(driver, id + "10_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 21) + "的期望值：" + value[20] + "，实际值："
								+ CommonJQ.getValue(driver, id + "11_l", ""),
						value[20].equals(CommonJQ.getValue(driver, id + "11_l", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 22) + "的期望值：" + value[21] + "，实际值："
								+ CommonJQ.getValue(driver, id + "11_r", ""),
						value[21].equals(CommonJQ.getValue(driver, id + "11_r", "")));
				Assert.assertTrue(
						BenchmarkOptimizationPage.getTitle("p3Scanner分析参数", 23) + "的期望值：" + value[22] + "，实际值："
								+ CommonJQ.getValue(driver, id + "12_l", ""),
						value[22].equals(CommonJQ.getValue(driver, id + "12_l", "")));
			}
		}
		/*
		 * if("Scanner分析".equals(catalogue2)) { CommonJQ.click(driver,
		 * "#scannerAnalysis>div>span", true,"Scanner分析"); String
		 * id="#scannerParam";//p3Scanner分析参数
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 1)+"的期望值：" + value[0] + "，实际值：" + CommonJQ.getValue(driver, id+"1_l",
		 * ""), value[0].equals(CommonJQ.getValue(driver, id+"1_l", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 2)+"的期望值：" + value[1] + "，实际值：" + CommonJQ.getValue(driver, id+"1_r",
		 * ""), value[1].equals(CommonJQ.getValue(driver, id+"1_r", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 3)+"的期望值：" + value[2] + "，实际值：" + CommonJQ.getValue(driver, id+"2_l",
		 * ""), value[2].equals(CommonJQ.getValue(driver, id+"2_l", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 4)+"的期望值：" + value[3] + "，实际值：" + CommonJQ.getValue(driver, id+"2_r",
		 * ""), value[3].equals(CommonJQ.getValue(driver, id+"2_r", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 5)+"的期望值：" + value[4] + "，实际值：" + CommonJQ.getValue(driver, id+"3_l",
		 * ""), value[4].equals(CommonJQ.getValue(driver, id+"3_l", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 6)+"的期望值：" + value[5] + "，实际值：" + CommonJQ.getValue(driver, id+"3_r",
		 * ""), value[5].equals(CommonJQ.getValue(driver, id+"3_r", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 7)+"的期望值：" + value[6] + "，实际值：" + CommonJQ.getValue(driver, id+"4_l",
		 * ""), value[6].equals(CommonJQ.getValue(driver, id+"4_l", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 8)+"的期望值：" + value[7] + "，实际值：" + CommonJQ.getValue(driver, id+"4_r",
		 * ""), value[7].equals(CommonJQ.getValue(driver, id+"4_r", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 9)+"的期望值：" + value[8] + "，实际值：" + CommonJQ.getValue(driver, id+"5_l",
		 * ""), value[8].equals(CommonJQ.getValue(driver, id+"5_l", "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 10)+"的期望值：" + value[9] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"5_r", ""), value[9].equals(CommonJQ.getValue(driver, id+"5_r",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 11)+"的期望值：" + value[10] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"6_l", ""), value[10].equals(CommonJQ.getValue(driver, id+"6_l",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 12)+"的期望值：" + value[11] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"6_r", ""), value[11].equals(CommonJQ.getValue(driver, id+"6_r",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 13)+"的期望值：" + value[12] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"7_l", ""), value[12].equals(CommonJQ.getValue(driver, id+"7_l",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 14)+"的期望值：" + value[13] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"7_r", ""), value[13].equals(CommonJQ.getValue(driver, id+"7_r",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 15)+"的期望值：" + value[14] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"8_l", ""), value[14].equals(CommonJQ.getValue(driver, id+"8_l",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 16)+"的期望值：" + value[15] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"8_r", ""), value[15].equals(CommonJQ.getValue(driver, id+"8_r",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 17)+"的期望值：" + value[16] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"9_l", ""), value[16].equals(CommonJQ.getValue(driver, id+"9_l",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 18)+"的期望值：" + value[17] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"9_r", ""), value[7].equals(CommonJQ.getValue(driver, id+"9_r",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 19)+"的期望值：" + value[18] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"10_l", ""), value[18].equals(CommonJQ.getValue(driver, id+"10_l",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 20)+"的期望值：" + value[19] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"10_r", ""), value[19].equals(CommonJQ.getValue(driver, id+"10_r",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 21)+"的期望值：" + value[20] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"11_l", ""), value[20].equals(CommonJQ.getValue(driver, id+"11_l",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 22)+"的期望值：" + value[21] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"11_r", ""), value[21].equals(CommonJQ.getValue(driver, id+"11_r",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 23)+"的期望值：" + value[22] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"12_l", ""), value[22].equals(CommonJQ.getValue(driver, id+"12_l",
		 * ""))); id="#neighboringParam";
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 24)+"的期望值：" + value[23] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"1_l", ""), value[23].equals(CommonJQ.getValue(driver, id+"1_l",
		 * "")));
		 * Assert.assertTrue(BenchmarkOptimizationPage.getTitle("Scanner分析",
		 * 25)+"的期望值：" + value[24] + "，实际值：" + CommonJQ.getValue(driver,
		 * id+"1_r", ""), value[24].equals(CommonJQ.getValue(driver, id+"1_r",
		 * "")));
		 * 
		 * }
		 */
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
