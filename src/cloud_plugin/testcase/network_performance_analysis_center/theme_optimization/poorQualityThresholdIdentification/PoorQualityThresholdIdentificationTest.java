package cloud_plugin.testcase.network_performance_analysis_center.theme_optimization.poorQualityThresholdIdentification;

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
import cloud_plugin.task.network_performance_analysis_center.theme_optimization.poorQualityThresholdIdentificationTask.PoorQualityThresholdIdentificationTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/***
 * 视频优化质差门限识别
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class PoorQualityThresholdIdentificationTest {

	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String FilePath = ConstUrl.getProjectHomePath()
			+ "\\Data\\baseline\\视频优化质差门限识别\\参数化表\\质差门限识别_评估前台参数化表.xlsx";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\视频优化质差门限识别\\附件";
	private String secns;
	private String TestCaseId;
	private String taskName;
	private String[] modelData; // 视屏建模数据
	private String[] parameSetUp; // 参数与设置
	// 专家求助
	private String HelpType;
	private String detailedDescription;
	private String Filepath;

	/**
	 * @param secns
	 * @param testCaseId
	 * @param taskName
	 * @param modelData
	 * @param parameSetUp
	 */
	public PoorQualityThresholdIdentificationTest(String secns, String TestCaseId, String taskName, String[] modelData,
			String[] parameSetUp,
			// 专家求助
			String HelpType, String detailedDescription, String Filepath) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.modelData = modelData;
		this.parameSetUp = parameSetUp;
		this.HelpType = HelpType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "VMOS_算法"));
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
		String Filepath1 = null;
		if (Filepath != null) {
			Filepath1 = FilePath1 + "\\" + Filepath;
		}
		NetworkAnalysisCenterTask.openltePoorQualityThresholdIdentification(driver);
		String taskNameStr = PoorQualityThresholdIdentificationTask.createPoorQualityThresholdIdentification(driver,
				taskName, modelData, parameSetUp, defaultWindowID);
		LoadingPage.Loading(driver, "li[ng-click=\"createTask()\"]");
		// 专家求助
		PoorQualityThresholdIdentificationTask.expertHelp(driver, taskNameStr, HelpType, detailedDescription,
				Filepath1);

	}

}
