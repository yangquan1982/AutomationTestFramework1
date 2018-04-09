package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.single_site_verification;

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
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/***
 * 单站验证 用户确认
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class SingSite_userComfirm_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\单站验证\\参数化表\\单站验证.xlsx";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\单站验证\\附件";
	private String TestCaseId;

	private String taskName;
	private String ProcType;
	private String detailedDescription;
	private String Filepath;
	private int starNum;

	/**
	 * @param testCaseId
	 * @param taskName
	 * @param procType
	 * @param detailedDescription
	 * @param filepath
	 * @param starNum
	 */
	public SingSite_userComfirm_Test(String TestCaseId, String taskName, String ProcType, String detailedDescription,
			String Filepath, int starNum) {
		super();
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.ProcType = ProcType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
		this.starNum = starNum;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "用户确认"));
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
		String filePath = null;
		if (null != Filepath) {
			filePath = FilePath1 + "\\" + Filepath;
		}
		// 打开
		NetworkAnalysisCenterTask.openLETSingleSiteVerification(driver);
		TaskViewPluginTask.userConfirm(driver, taskName, ProcType, detailedDescription, filePath, starNum);
		result = true;
	}

}
