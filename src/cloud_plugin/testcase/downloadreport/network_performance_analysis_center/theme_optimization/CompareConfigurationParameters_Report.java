package cloud_plugin.testcase.downloadreport.network_performance_analysis_center.theme_optimization;

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
import cloud_plugin.task.network_performance_analysis_center.theme_optimization.BenchmarkOptimizationTask;
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
public class CompareConfigurationParameters_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ResultHome = ConstUrl.getProjectHomePath() + "\\Data\\result\\视频优化质差门限识别\\报告\\";
	private static String BaselineHome = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\视频优化质差门限识别\\报告\\";

	private String secns;
	private String TestCaseId;
	private String taskName;
	private String[] Folder;
	private String StartWith;
	private static String FilePath = ConstUrl.getProjectHomePath()
			+ "\\Data\\baseline\\视频优化质差门限识别\\参数化表\\视频优化质差门限识别下载.xlsx";
	private static String GT3KFile = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\视频优化质差门限识别\\GT3K.ini";

	public CompareConfigurationParameters_Report(String secns, String TestCaseId, String taskName, String[] Folder,
			String StartWith) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.Folder = Folder;
		this.StartWith = StartWith;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "report"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);

		String ResultHome1 = ResultHome + taskName;
		String BaselineHome1 = BaselineHome + taskName;
		String ResultHome2 = null;
		String BaselineHome2 = null;

		NetworkAnalysisCenterTask.openLETBenchmarkOptimization(driver);
		TaskReportTask.asserTaskEndState(driver, taskName);
		if (StartWith != null) {
			BenchmarkOptimizationTask.downLoad_MoveReport(driver, defaultWindowID, taskName, ResultHome1);
			String[] StartWithArray = StartWith.split(";");
			for (int i = 0; i < Folder.length; i++) {
				ResultHome2 = ResultHome1 + "\\" + Folder[i];
				BaselineHome2 = BaselineHome1 + "\\" + Folder[i];
				String[] startWidthStr = StartWithArray[i].split(",");
				Assert.assertTrue(CommonFile.fileReportMsg(ResultHome2),
						FileCompare.fileCompare(BaselineHome2, ResultHome2, startWidthStr));
				System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
			}
		}
		// 设置TMSS回填结果
		result = true;
	}
}
