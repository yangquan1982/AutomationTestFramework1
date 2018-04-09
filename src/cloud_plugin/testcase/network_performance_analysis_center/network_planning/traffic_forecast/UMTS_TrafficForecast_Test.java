package cloud_plugin.testcase.network_performance_analysis_center.network_planning.traffic_forecast;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.traffic_forecast.TrafficForecastTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.ZIP;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UMTS_TrafficForecast_Test {
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
	public void T001_U_CancleTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		TrafficForecastTask.Creat_CancleTask(driver, "Cover");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T002_U_CancleTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		TrafficForecastTask.Creat_CancleTask2(driver, "Cover");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
