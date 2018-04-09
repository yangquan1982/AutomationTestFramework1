package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_seq_vmos;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.junit.v4_5.runner.GUITestRunner;
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
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_VMOS_Const;
import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.HomePage;
import cloud_public.page.IndexPage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T02_001_CreateTaskBaseInforCheck {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	// @Rule
	// public Timeout globalTimeout = Timeout.
	// public Timeout globalTimeout = Timeout.seconds(Long
	// .parseLong(ConstUrl.TestCaseTimeout) / 1000);

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomePage.gotoldVersion(driver);
		IndexPage.OpenNetPerfomanceAnalysisCenter(driver);

	}

	@Before
	public void setUp() {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		SeqVMOSTask.goBackToNetworkAnalysisCenter(driver);
		SeqVMOSTask.ChangeProject(driver, LTE_VMOS_Const.VMOS_Project);
		SeqVMOSTask.openPlugin(driver);
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "新建任务.001,新建任务.002,新建任务.003,新建任务.004,新建任务.005,新建任务.006,新建任务.007,新建任务.008,新建任务.009,新建任务.010")
	public void T001_U_CancleTask() {
		SeqVMOSTask.createTaskBaseInforCheck(driver);
	}

}
