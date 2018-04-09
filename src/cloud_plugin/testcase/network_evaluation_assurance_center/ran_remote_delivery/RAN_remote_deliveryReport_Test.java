package cloud_plugin.testcase.network_evaluation_assurance_center.ran_remote_delivery;

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

import cloud_plugin.task.network_evaluation_assurance_center.RAN_remote_delivery.RANRemoteDeliveryTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/***
 * 专家反馈
 * 
 * @author pgenexautotest
 *
 */
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class RAN_remote_deliveryReport_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\RAN远程交付";
	private static String ParaFile = FilePath + "\\参数化表\\RAN远程交付.xlsx";
	private String secns;
	private String TestCaseId;
	private String taskName;
	private String userName;
	private String pwd;
	private boolean IOADownload;
	private String idea;
	private String[] fileUploadPath;
	private String describe;
	// 远程接入分析

	public RAN_remote_deliveryReport_Test(String secns, String TestCaseId, String taskName, String userName, String pwd,
			boolean IOADownload, String idea, String[] fileUploadPath, String describe) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.userName = userName;
		this.pwd = pwd;
		this.IOADownload = IOADownload;
		this.idea = idea;
		this.fileUploadPath = fileUploadPath;
		this.describe = describe;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Report"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login_account_pwd(driver, userName, pwd);
		result = false;
	}

	@After
	public void tearDown() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result);
		driver.quit();
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		RANRemoteDeliveryTask.reportHandle(driver, taskName, IOADownload, fileUploadPath, idea, describe,
				defaultWindowID);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		result = true;
	}
}
