package cloud_plugin.testcase.network_performance_analysis_center.network_planning.dt_overseas;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDTDataOverseasTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_DTOverseas_ClearText_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String[] DTfile = ConstUrl.getTargetValue("DT_WFZ_DT").split("/");
	private static String[] EParafile = ConstUrl.getTargetValue("DT_WFZ_EPara").split("/");
	private static String[] Mapfile = {};
	private static String[] ExEParafile = {};
	private static String[] Ratefile = {};

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
	public void T001_LTE_CreatClearTextTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoDTDataOverseasTask.creatClearTextTask(driver, defaultWindowID, DTfile, EParafile, Mapfile,
				ExEParafile, Ratefile);
		TaskReportTask.asserTaskInitialState(driver, taskName, "");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
