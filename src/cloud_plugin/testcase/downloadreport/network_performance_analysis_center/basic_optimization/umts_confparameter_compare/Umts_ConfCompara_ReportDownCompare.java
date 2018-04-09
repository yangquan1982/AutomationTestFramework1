package cloud_plugin.testcase.downloadreport.network_performance_analysis_center.basic_optimization.umts_confparameter_compare;

import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.UmtsConfParameterCompare.UmtsConfCompare;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class Umts_ConfCompara_ReportDownCompare {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ResultHome = ConstUrl.getProjectHomePath() + "\\Data\\result\\UMTS配置参数对比\\";
	private static String BaselineHome = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\UMTS配置参数对比\\";

	private String secns;
	private String taskName;
	private String TestCaseId;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\UMTS配置参数对比";
	private static String ParaFile = FilePath + "\\参数化表\\U配置参数对比报告下载参数化.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	public Umts_ConfCompara_ReportDownCompare(String secns, String taskName, String TestCaseId) {
		this.secns = secns;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		/**
		 * 1.Basefile is empty,Checkfile is not empty 2.Basefile is not
		 * empty,Checkfile is empty 3.Basefile is empty,Checkfile is empty
		 * 4.Para is empty 5.CustParafile is empty
		 */
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "ConfCompare", 0));
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@Before
	public void setUp() {
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
		System.out.println("tmss end xx");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void L_downLoad_report() {

		LOG.info_testcase("场景描述:" + secns);
		String ResultHome1 = ResultHome + taskName;
		String BaselineHome1 = BaselineHome + taskName;
		NetworkAnalysisCenterTask.OpenUMTSCfgCompare(driver);
		TaskReportTask.asserTaskEndState(driver, taskName);
		UmtsConfCompare.downLoad_report(driver, defaultWindowID, taskName, ResultHome1);
		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome1),
				FileCompare.fileCompare_Deviation(BaselineHome1, ResultHome1, new String[] { "UMTS" }));
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
