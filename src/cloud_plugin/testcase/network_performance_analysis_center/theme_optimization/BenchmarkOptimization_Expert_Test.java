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
 * 排名提升优化 专家处理
 * 
 * @author pgenexautotest
 *
 */
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class BenchmarkOptimization_Expert_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String secns;
	private String TestCaseId;
	private String taskName;

	// 专家处理
	private String ProcType;
	private String detailedDescription;
	private String Filepath;

	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\参数化表\\排名提升优化.xlsx";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\附件";
	private static String GT3KFile = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\GT3K.ini";

	/**
	 * @param secns
	 * @param testCaseId
	 * @param taskName
	 * @param procType
	 * @param detailedDescription
	 * @param filepath
	 */
	public BenchmarkOptimization_Expert_Test(String secns, String TestCaseId, String taskName, String ProcType,
			String detailedDescription, String Filepath) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.ProcType = ProcType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "report"));// 排名提升优化专家处理
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.loginExpert(driver);
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
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		String filePath1 = null;
		if (Filepath != null) {
			filePath1 = FilePath1 + "\\" + Filepath;
		}
		NetworkAnalysisCenterTask.openLETBenchmarkOptimization(driver);
		TaskViewPluginTask.expertProc(driver, taskName, ProcType, detailedDescription, filePath1);
		result = true;
	}

}
