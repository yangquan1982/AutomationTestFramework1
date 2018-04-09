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
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/**
 * 排名提升优化 用户确认
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class BenchmarkOptimization_UserConfirm_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\参数化表\\排名提升优化.xlsx";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\附件";
	private static String GT3KFile = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName;
	// 用户确认
	private String ProcType;
	private String detailedDescription;
	private String Filepath;
	private int starNum;

	/**
	 * @param procType
	 * @param detailedDescription
	 * @param filepath
	 * @param starNum
	 */
	public BenchmarkOptimization_UserConfirm_Test(String secns, String TestCaseId, String taskName, String ProcType,
			String detailedDescription, String Filepath, int starNum) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;

		this.ProcType = ProcType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
		this.Filepath = Filepath;
		this.starNum = starNum;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "UserConfirm"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@Before
	public void setUp() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		String helpfile = null;
		if (Filepath != null) {
			helpfile = FilePath1 + "\\" + Filepath;
		}
		// 打开
		NetworkAnalysisCenterTask.openLETBenchmarkOptimization(driver);
		TaskViewPluginTask.userConfirm(driver, taskName, ProcType, detailedDescription, helpfile, starNum);
		// 设置TMSS回填结果
		result = true;
	}

}
