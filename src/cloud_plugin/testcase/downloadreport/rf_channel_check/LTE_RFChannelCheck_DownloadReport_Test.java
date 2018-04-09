package cloud_plugin.testcase.downloadreport.rf_channel_check;

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
import cloud_plugin.task.network_performance_analysis_center.rf_channel_check.RFChannelCheckTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_RFChannelCheck_DownloadReport_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String secns;
	private String taskName;

	// Data path
	private static String ParaFile = ConstUrl.getProjectHomePath() + "\\Data\\稳定性测试\\用户报告下载\\射频通道检查\\射频通道检查.xlsx";

	public LTE_RFChannelCheck_DownloadReport_Test(String secns, String taskName) {
		this.secns = secns;
		this.taskName = taskName;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "report"));
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@Before
	public void setUp() {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void downLoad_report() {
		LOG.info_testcase("场景描述:" + secns);

		NetworkAnalysisCenterTask.openLTERFChannelCheck(driver);
		TaskReportTask.asserTaskEndState(driver, taskName);
		RFChannelCheckTask.downLoad_report(driver, defaultWindowID, taskName);

	}
}
