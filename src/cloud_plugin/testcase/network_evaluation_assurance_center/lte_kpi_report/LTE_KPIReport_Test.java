package cloud_plugin.testcase.network_evaluation_assurance_center.lte_kpi_report;

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

import cloud_plugin.task.network_evaluation_assurance_center.lte_kpi_report.LTEKPIReportTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_KPIReport_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String[] Datafile_Empty = {};

	private static String[] Datafile = ConstUrl.getTargetValue("KPIReport_LTE_GTLQua").split("/");

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
	public void T001_LTE_KPIReport_taskNameEmpty() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LTEKPIReportTask.LKpiReport_ErrorMsg(driver, defaultWindowID, "", true, Datafile);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T002_LTE_KPIReport_ReportTypeEmpty() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LTEKPIReportTask.LKpiReport_ErrorMsg(driver, defaultWindowID, "aaaaaa", false, Datafile);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T003_LTE_KPIReport_DatafileEmpty() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LTEKPIReportTask.LKpiReport_ErrorMsg(driver, defaultWindowID, "aaaaaba", true, Datafile_Empty);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T004_LTE_KPIReport_AllEmpty() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LTEKPIReportTask.LKpiReport_ErrorMsg(driver, defaultWindowID, "", false, Datafile_Empty);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
