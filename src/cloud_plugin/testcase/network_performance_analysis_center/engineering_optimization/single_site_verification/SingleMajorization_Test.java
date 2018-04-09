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

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.single_site_verification.SingleSiteVerificationPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.single_site_verification.SingleSiteVerificationTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SwitchDriver;
import common.util.tmss.StartBackFillTMSS;

/***
 * 指标统计
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class SingleMajorization_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "MV Remote Technology";
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\单站验证\\附件";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\单站验证";
	private static String ParaFile = FilePath1 + "\\参数化表\\单站验证.xlsx";
	private String secns;
	private String TestCaseId;
	private String taskName;// 任务名称
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	// 专家求助
	private String HelpType;
	private String detailedDescription;
	private String Filepath;

	/**
	 * @param secns
	 * @param testCaseId
	 * @param taskName
	 * @param startTime
	 * @param endTime
	 * @param helpType
	 * @param detailedDescription
	 * @param filepath
	 */
	public SingleMajorization_Test(String secns, String TestCaseId, String taskName, String startTime, String endTime,
			String HelpType, String detailedDescription, String Filepath) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.HelpType = HelpType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "指标统计"));
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
		String helpfile = null;
		if (Filepath != null) {
			helpfile = FilePath + Filepath;
		}
		NetworkAnalysisCenterTask.openLETSingleSiteVerification(driver);
		String taskName_str = SingleSiteVerificationTask.create_SingleMajorization(driver, taskName, startTime,
				endTime);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 点击保存
		CommonJQ.click(driver, SingleSiteVerificationPage.BtnCommit_Tasknew, true);
		SwitchDriver.switchDriverToSEQ(driver);
		// 专家求助
		TaskViewPluginTask.expertHelp(driver, taskName_str, HelpType, detailedDescription, helpfile);
		result = true;
	}

}
