package cloud_plugin.testcase.network_performance_analysis_center.theme_optimization;

import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.theme_optimization.BenchmarkOptimizationTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SwitchDriver;
import common.util.tmss.StartBackFillTMSS;

/***
 * 排名提升优化
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class BenchmarkOptimizationTest {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\参数化表\\排名提升优化.xlsx";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\附件";
	private String secns;
	private String TestCaseId;
	private String taskName;
	private String[] functionSubitem;// 功能子项
	// 任务类型
	private String taskType;
	// 运营商
	private String[] p3Operator;
	// 分析数据
	private String[] CDRDataFile; // CDR Data
	private String[] PCAPDataFile;// PCAP Data
	private String[] ROMESL3MessageFile;//
	private String[] ROMESUELogFile;
	private String[] ScannerDataFile;
	private String operatorIndexFile;// 运营商索引文件
	private String[] UMTSCifDataRNCFile;// UMTS配置数据(RNC)
	private String[] UMTSCifDataNodeBFile;// UMTS配置数据(NodeB)
	private String[] LTECifDataFile;// LTE配置数据
	private String[] UMTSTelephoneDataRNCFile;// UMTS话统数据RNC
	private String UNTSTelephoneDataNodeBFile;// UMTS话统数据NodeB
	private String[] LTETelephoneDataFile;// LTE话筒数据
	private String[] RNCLicenseFile;
	private String[] NodeBLicenseFile;
	private String[] UMTSProjectParmFile;// UMTS工程参数
	private String[] LTEProjectParmFile;// LTE工程参数
	private String[] GSMprojectParmFile;// GSM工程参数
	private String RNCReportAnEmergencyFile;// RNC告警数据
	private String eNodeBReportAnEmergencyFile;// eNodeB告警数据

	// 参数设置项
	// 参数模板设置
	private String UMTSParmBaseLineModelFile;// UMTS参数基线模板
	private String LTEParmBaseLineModelFile;// LTE参数基线模板
	// P3业务分析数据
	private String[] P3TaskAnalyseParme;// 该数组工四个参数：0.CDR文件名校验规则模板路径(String);
										// 1.CDR文件名校验规则模板下载（boolean）;2.运营商（String）;3.只进行问题话单的TCP分析（boolean）
	// 评估参数
	// Web
	private String[] Web;
	// Youtube
	private String[] Youtube;
	// Upload
	private String[] Upload;
	private String[] Download;
	private String[] other;
	// TCP数传问题分析参数
	private String[] TCPUploadDataParame;
	// 覆盖干扰问题分析参数
	// UMTS
	private String[] coverDisturbParameUMTS;
	// LTE
	private String[] coverDisturbParameLTE;
	// 资源问题分析参数
	// UMTS
	private String[] resourceProblemAnalyseUMTS;
	// LTE
	private String[] resourceProblemAnalyseLTE;
	// Scanner分析参数
	private String[] ScannerAnalyseParame;
	// TCP DNS模板设置
	private String[] TCPDNSsetUpModel;
	// CDR文件名校验规则模板
	private String[] CDRFileNameModel;

	// GAP分析&问题话单筛选
	// 业务KQI
	private String professionalWorkKQI;
	// 分析维度
	private String[] analyseLatitude;
	/*
	 * //Scanner 分析 private String[]
	 * scannerAnalyse;//该数组一共四个参数：0.运营商和频点配置模板文件路径（String），1.运营商和频点配置模板下载（
	 * boolean），2.选择分析运营商（String），3.频点（String） //Scanner 分析参数设置 private String[]
	 * scannerParame;
	 */
	// 邻区漏配参数设置
	private String[] adjacentParameSetUp;
	private boolean tacitlyApproveResume;// 默认回复

	// 专家求助
	private String HelpType;
	private String detailedDescription;
	private String Filepath;

	/**
	 * @param secns
	 * @param testCaseId
	 * @param taskName
	 * @param taskType
	 * @param cDRDataFile
	 * @param pCAPDataFile
	 * @param rOMESL3MessageFile
	 * @param rOMESUELogFile
	 * @param scannerDataFile
	 * @param operatorIndexFile
	 * @param uMTSCifDataRNCFile
	 * @param uMTSCifDataNodeBFile
	 * @param lTECifDataFile
	 * @param uMTSTelephoneDataRNCFile
	 * @param uNTSTelephoneDataNodeBFile
	 * @param lTETelephoneDataFile
	 * @param rNCLicenseFile
	 * @param nodeBLicenseFile
	 * @param uMTSProjectParmFile
	 * @param lTEProjectParmFile
	 * @param gSMprojectParmFile
	 * @param rNCReportAnEmergencyFile
	 * @param eNodeBReportAnEmergencyFile
	 * @param operatorIndexModel
	 * @param uNTSTelephoneDataNodeB
	 * @param rNCReportAnEmergency
	 * @param eNodeBReportAnEmergency
	 * @param uMTSParmBaseLineModelFile
	 * @param uMTSParmBaseLineModel
	 * @param lTEParmBaseLineModelFile
	 * @param lTEParmBaseLineModel
	 * @param p3TaskAnalyseParme
	 * @param web
	 * @param youtube
	 * @param upload
	 * @param download
	 * @param other
	 * @param tCPUploadDataParame
	 * @param coverDisturbParameUMTS
	 * @param coverDisturbParameLTE
	 * @param resourceProblemAnalyseUMTS
	 * @param resourceProblemAnalyseLTE
	 * @param scannerAnalyseParame
	 * @param tCPDNSsetUpModel
	 * @param professionalWorkKQI
	 * @param analyseLatitude
	 * @param scannerAnalyse
	 * @param scannerParame
	 * @param adjacentParameSetUp
	 * @param tacitlyApproveResume
	 * @param helpType
	 * @param detailedDescription
	 * @param filepath
	 */
	public BenchmarkOptimizationTest(String secns, String TestCaseId, String taskName, String[] functionSubitem,
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
			String[] adjacentParameSetUp, boolean tacitlyApproveResume, String HelpType, String detailedDescription,
			String Filepath) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.functionSubitem = functionSubitem;
		this.taskType = taskType;
		this.p3Operator = p3Operator;
		this.CDRDataFile = CDRDataFile;
		this.PCAPDataFile = PCAPDataFile;
		this.ROMESL3MessageFile = ROMESL3MessageFile;
		this.ROMESUELogFile = ROMESUELogFile;
		this.ScannerDataFile = ScannerDataFile;
		this.operatorIndexFile = operatorIndexFile;
		this.UMTSCifDataRNCFile = UMTSCifDataRNCFile;
		this.UMTSCifDataNodeBFile = UMTSCifDataNodeBFile;
		this.LTECifDataFile = LTECifDataFile;
		this.UMTSTelephoneDataRNCFile = UMTSTelephoneDataRNCFile;
		this.UNTSTelephoneDataNodeBFile = UNTSTelephoneDataNodeBFile;
		this.LTETelephoneDataFile = LTETelephoneDataFile;
		this.RNCLicenseFile = RNCLicenseFile;
		this.NodeBLicenseFile = NodeBLicenseFile;
		this.UMTSProjectParmFile = UMTSProjectParmFile;
		this.LTEProjectParmFile = LTEProjectParmFile;
		this.GSMprojectParmFile = GSMprojectParmFile;
		this.RNCReportAnEmergencyFile = RNCReportAnEmergencyFile;
		this.eNodeBReportAnEmergencyFile = eNodeBReportAnEmergencyFile;
		this.UMTSParmBaseLineModelFile = UMTSParmBaseLineModelFile;
		this.LTEParmBaseLineModelFile = LTEParmBaseLineModelFile;
		this.P3TaskAnalyseParme = P3TaskAnalyseParme;
		this.Web = Web;
		this.Youtube = Youtube;
		this.Upload = Upload;
		this.Download = Download;
		this.other = other;
		this.TCPUploadDataParame = TCPUploadDataParame;
		this.coverDisturbParameUMTS = coverDisturbParameUMTS;
		this.coverDisturbParameLTE = coverDisturbParameLTE;
		this.resourceProblemAnalyseUMTS = resourceProblemAnalyseUMTS;
		this.resourceProblemAnalyseLTE = resourceProblemAnalyseLTE;
		this.ScannerAnalyseParame = ScannerAnalyseParame;
		this.TCPDNSsetUpModel = TCPDNSsetUpModel;
		this.CDRFileNameModel = CDRFileNameModel;
		this.professionalWorkKQI = professionalWorkKQI;
		this.analyseLatitude = analyseLatitude;
		// this.scannerAnalyse = scannerAnalyse;
		// this.scannerParame = scannerParame;
		this.adjacentParameSetUp = adjacentParameSetUp;
		this.tacitlyApproveResume = tacitlyApproveResume;
		this.HelpType = HelpType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "排名提升优化"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result);
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		String helpfile = null;
		if (Filepath != null) {
			helpfile = FilePath1 + "\\" + Filepath;
		}
		String operatorIndexFile1 = null;
		if (operatorIndexFile != null) {
			operatorIndexFile1 = FilePath1 + "\\" + operatorIndexFile;
		}
		String UNTSTelephoneDataNodeBFile1 = null;
		if (UNTSTelephoneDataNodeBFile != null) {
			UNTSTelephoneDataNodeBFile1 = FilePath1 + "\\" + UNTSTelephoneDataNodeBFile;
		}
		String RNCReportAnEmergencyFile1 = null;
		if (RNCReportAnEmergencyFile != null) {
			RNCReportAnEmergencyFile1 = FilePath1 + "\\" + RNCReportAnEmergencyFile;
		}
		String eNodeBReportAnEmergencyFile1 = null;
		if (eNodeBReportAnEmergencyFile != null) {
			eNodeBReportAnEmergencyFile1 = FilePath1 + "\\" + eNodeBReportAnEmergencyFile;
		}
		NetworkAnalysisCenterTask.openLETBenchmarkOptimization(driver);
		String taskNameStr = BenchmarkOptimizationTask.createBenchmarkModel(driver, taskName, functionSubitem, taskType,
				p3Operator, CDRDataFile, PCAPDataFile, ROMESL3MessageFile, ROMESUELogFile, ScannerDataFile,
				operatorIndexFile1, UMTSCifDataRNCFile, UMTSCifDataNodeBFile, LTECifDataFile, UMTSTelephoneDataRNCFile,
				UNTSTelephoneDataNodeBFile1, LTETelephoneDataFile, RNCLicenseFile, NodeBLicenseFile,
				UMTSProjectParmFile, LTEProjectParmFile, GSMprojectParmFile, RNCReportAnEmergencyFile1,
				eNodeBReportAnEmergencyFile1, UMTSParmBaseLineModelFile, LTEParmBaseLineModelFile, P3TaskAnalyseParme,
				Web, Youtube, Upload, Download, other, TCPUploadDataParame, coverDisturbParameUMTS,
				coverDisturbParameLTE, resourceProblemAnalyseUMTS, resourceProblemAnalyseLTE, ScannerAnalyseParame,
				TCPDNSsetUpModel, CDRFileNameModel, professionalWorkKQI, analyseLatitude,
				// scannerAnalyse,
				// scannerParame,
				adjacentParameSetUp, tacitlyApproveResume, defaultWindowID, FilePath1);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, "#commit_Tasknew", true);
		SwitchDriver.switchDriverToSEQ(driver);// 迁出 iframe*/
		BenchmarkOptimizationTask.expertHelp(driver, taskNameStr, HelpType, detailedDescription, helpfile);
		result = true;
	}

}
