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
import common.util.CommonWD;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/***
 * 排名提升优化 模板下载
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class BenchmarkOptimizationDownLoadModelTest {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ParamePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\参数化表\\排名提升优化.xlsx";
	private static String Baseline = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\模板";
	private static String ResultPath = ConstUrl.getProjectHomePath() + "\\Data\\result\\排名提升优化\\模板";
	private String secns;
	private String TestCaseId;
	private String[] CDRDataFile;
	private boolean operatorIndexModel;// 运营商模板下载
	private boolean UNTSTelephoneDataNodeB;// UMTS话统数据NodeB模板下载
	private boolean RNCReportAnEmergency;// RNC告警数据模板下载
	private boolean eNodeBReportAnEmergency;// eNodeB告警数据模板下载

	// 参数模板设置
	private boolean UMTSParmBaseLineModel;// UMTS参数基线模板下载
	private boolean LTEParmBaseLineModel;// LTE参数基线模板下载

	private boolean P3TaskAnalyseParmeModel;// CDR文件名校验规则模板
	private boolean TCPDNSsetUpModel;// TCP DNS模板

	private boolean scannerAnalyse;// 运营商和频点配置

	/**
	 * @param secns
	 * @param testCaseId
	 * @param cDRDataFile
	 * @param operatorIndexModel
	 * @param uNTSTelephoneDataNodeB
	 * @param rNCReportAnEmergency
	 * @param eNodeBReportAnEmergency
	 * @param uMTSParmBaseLineModel
	 * @param lTEParmBaseLineModel
	 * @param p3TaskAnalyseParmeModel
	 * @param tCPDNSsetUpModel
	 * @param scannerAnalyse
	 */
	public BenchmarkOptimizationDownLoadModelTest(String secns, String TestCaseId, String[] CDRDataFile,
			boolean operatorIndexModel, boolean UNTSTelephoneDataNodeB, boolean RNCReportAnEmergency,
			boolean eNodeBReportAnEmergency, boolean UMTSParmBaseLineModel, boolean LTEParmBaseLineModel,
			boolean P3TaskAnalyseParmeModel, boolean TCPDNSsetUpModel, boolean scannerAnalyse) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.CDRDataFile = CDRDataFile;
		this.operatorIndexModel = operatorIndexModel;
		this.UNTSTelephoneDataNodeB = UNTSTelephoneDataNodeB;
		this.RNCReportAnEmergency = RNCReportAnEmergency;
		this.eNodeBReportAnEmergency = eNodeBReportAnEmergency;
		this.UMTSParmBaseLineModel = UMTSParmBaseLineModel;
		this.LTEParmBaseLineModel = LTEParmBaseLineModel;
		this.P3TaskAnalyseParmeModel = P3TaskAnalyseParmeModel;
		this.TCPDNSsetUpModel = TCPDNSsetUpModel;
		this.scannerAnalyse = scannerAnalyse;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParamePath, "排名提升优化模板下载"));
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
		NetworkAnalysisCenterTask.openLETBenchmarkOptimization(driver);
		BenchmarkOptimizationTask.downLoadModel(driver, CDRDataFile, operatorIndexModel, UNTSTelephoneDataNodeB,
				RNCReportAnEmergency, eNodeBReportAnEmergency, UMTSParmBaseLineModel, LTEParmBaseLineModel,
				P3TaskAnalyseParmeModel, TCPDNSsetUpModel, scannerAnalyse, Baseline, ResultPath, defaultWindowID);
	}

}
