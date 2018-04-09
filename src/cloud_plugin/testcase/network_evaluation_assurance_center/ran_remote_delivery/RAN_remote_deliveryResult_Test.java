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

import cloud_plugin.page.network_evaluation_assurance_center.RAN_remote_delivery.RANRemoteDeliveryPage;
import cloud_plugin.task.network_evaluation_assurance_center.NetworkSafeGuardCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SwitchDriver;
import common.util.tmss.StartBackFillTMSS;

/**
 * 
 * RAN 普通用户反馈
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class RAN_remote_deliveryResult_Test {
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
	private String idea;

	private String describe;

	public RAN_remote_deliveryResult_Test(String secns, String TestCaseId, String taskName, String idea,
			String describe) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.idea = idea;
		this.describe = describe;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "RANResult"));
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
	}

	@After
	public void tearDown() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result);
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		NetworkSafeGuardCenterTask.openAutoRANData(driver);
		String taskId = TaskReportTask.RANAsserTaskState(driver, taskName, "Expert");
		CommonJQ.click(driver, "#" + taskId, true);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe

		if ("关闭任务".equals(idea)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.taskResultClose, true);
		} else if ("返回任务分析".equals(idea)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.taskResultBack, true);
		}
		CommonJQ.value(driver, RANRemoteDeliveryPage.taskResultTextarea, describe, true);
		CommonJQ.click(driver, RANRemoteDeliveryPage.resultSubmitBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		result = true;
	}
}
