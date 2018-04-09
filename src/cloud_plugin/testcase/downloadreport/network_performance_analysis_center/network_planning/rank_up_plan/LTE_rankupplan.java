package cloud_plugin.testcase.downloadreport.network_performance_analysis_center.network_planning.rank_up_plan;

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
import cloud_plugin.task.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlan;
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
public class LTE_rankupplan {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ResultHome = ConstUrl.getProjectHomePath() + "\\Data\\result\\LTE_Rankupplan\\";
	private static String BaselineHome = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_Rankupplan\\";

	private String secns;
	private String taskName;
	private String TestCaseId;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_Rankupplan";
	private static String ParaFile = FilePath + "\\参数化表\\排名提升规划.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	public LTE_rankupplan(String secns, String ProjectName, String taskName, String TestCaseId) {
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

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "报告下载", 0));
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
		LOG.info_testcase("排名提升规划报告下载与对比");
		LOG.info_testcase("场景描述:" + secns);
		String ResultHome1 = ResultHome + taskName;
		String BaselineHome1 = BaselineHome + taskName;
		NetworkAnalysisCenterTask.openLTERankUpPlan(driver);
		TaskReportTask.asserTaskEndState(driver, taskName);
		RankUpPlan.downLoad_report(driver, defaultWindowID, taskName, ResultHome1);
		// Assert.assertTrue(CommonFile.fileReportMsg(ResultHome1),
		// FileCompare.SameNameCompareInPath2MAX (BaselineHome1, ResultHome1));
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}

}
