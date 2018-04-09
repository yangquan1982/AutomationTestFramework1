package cloud_plugin.testcase.downloadreport.network_performance_analysis_center.network_planning.dl_comp_cluster_design;

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

import cloud_platform.task.platformcommon.task.AWtaskManagement;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_DLCoMPClusterDesign_BatchReport {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String secns;
	private String TestCaseId;
	private String taskName;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_特性规划设计";
	private static String ParaFile = FilePath + "\\参数化表\\特性规划设计_报告下载参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	public LTE_DLCoMPClusterDesign_BatchReport(String secns, String TestCaseId, String taskName) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "批量下载"));
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
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T001_Batch_PlugIn() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LOG.info_testcase("场景描述:" + secns);
		NetworkAnalysisCenterTask.openLTEDLCoMPClusterDesign(driver);
		String getID = TaskReportTask.asserTaskEndState(driver, taskName);
		AWtaskManagement.DownloadFileBatch_PlugInComponentse(driver, null, null, null, getID);

		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
