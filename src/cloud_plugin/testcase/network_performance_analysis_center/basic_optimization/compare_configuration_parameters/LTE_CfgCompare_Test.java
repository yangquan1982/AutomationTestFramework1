package cloud_plugin.testcase.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

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

import cloud_plugin.task.network_performance_analysis_center.basic_optimization.compare_configuration_parameters.CompareCfgParaTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_CfgCompare_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

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
	public void T001_L_CancleTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		CompareCfgParaTask.Creat_CancleTask(driver, "SameNet");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T002_L_CancleTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		CompareCfgParaTask.Creat_CancleTask2(driver, "SameNet");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
