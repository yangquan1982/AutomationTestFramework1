package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_seq_vmos.report;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.HomePage;
import cloud_public.page.IndexPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.ParamUtil;
import common.util.SwitchDriver;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T10_000_LTE_VMOS_TaskReportDown {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String secns;
	private String TestCaseId;
	private String taskName;
	private String[] StartWith;

	// Data path
	private static String ParaFile = ConstUrl.getProjectHomePath()
			+ "\\Data\\baseline\\lte_seq_vmos\\参数化表\\精品视频参数化表.xlsx";

	public T10_000_LTE_VMOS_TaskReportDown(String secns, String testCaseId, String taskName, String[] startWith) {
		this.secns = secns;
		TestCaseId = testCaseId;
		this.taskName = taskName;
		StartWith = startWith;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "报告下载"));
		return coll;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomePage.gotoldVersion(driver);
		IndexPage.OpenNetPerfomanceAnalysisCenter(driver);
	}

	@Before
	public void setUp() {
		result = false;
		SwitchDriver.switchDriverToSEQ(driver);
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		SeqVMOSTask.goBackToNetworkAnalysisCenter(driver);

	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(TestCaseId, result);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T001_ContentEvaluate() {
		SeqVMOSTask.DownLoadReport(driver, secns, taskName, StartWith);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
