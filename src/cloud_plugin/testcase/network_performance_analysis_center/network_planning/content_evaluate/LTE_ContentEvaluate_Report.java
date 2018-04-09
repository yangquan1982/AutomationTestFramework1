package cloud_plugin.testcase.network_performance_analysis_center.network_planning.content_evaluate;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_ContentEvaluate_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String[] cfgFile = ConstUrl.getTargetValue("ACPContent_LTE_Cfg").split("/");
	private static String[] EperfFile = ConstUrl.getTargetValue("ACPContent_LTE_EPerf").split("/");
	private static String EnodeArrFile = ConstUrl.getProjectHomePath()
			+ "\\Data\\baseline\\U_目标网预测\\CommonFile\\终端数据.csv";

	// private static String[] EmptyFile = {};
	private static String ServiceType = "LTE_ContentEvaluate";

	private static String taskName_T001 = "默认下发";
	private static String taskName_T002 = "忙时指标选DLPRB";

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

	// @Test(GT3Kid = "")
	// public void T001_LTE_ContentEvaluate_moren() {
	// System.out.println(ZIP.NowTime() + "Start the testcase:"
	// + name.getMethodName());
	// LOG.info_testcase("容量评估任务名称："+taskName_T001);
	// String taskName = ContentEvaluateTask.creatLTEfddContentEvaluateTask(
	// driver, defaultWindowID, taskName_T001,cfgFile, EperfFile,null
	// );
	// LoadingPage.Loading(driver, TaskReportPage.CreateTask);
	// TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
	// System.out.println(ZIP.NowTime() + "End the testcase:"
	// + name.getMethodName());
	// }
	// @Test(GT3Kid = "")
	// public void T002_LTE_ContentEvaluate() {
	// System.out.println(ZIP.NowTime() + "Start the testcase:"
	// + name.getMethodName());
	// LOG.info_testcase("容量评估任务名称："+taskName_T002);
	// String taskName = ContentEvaluateTask.creatLTEfddContentEvaluateTask(
	// driver, defaultWindowID, taskName_T002,cfgFile, EperfFile,null
	// );
	// LoadingPage.Loading(driver, TaskReportPage.CreateTask);
	// TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
	// System.out.println(ZIP.NowTime() + "End the testcase:"
	// + name.getMethodName());
	// }

}
