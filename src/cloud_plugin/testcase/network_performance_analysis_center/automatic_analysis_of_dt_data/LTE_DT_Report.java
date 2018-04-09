package cloud_plugin.testcase.network_performance_analysis_center.automatic_analysis_of_dt_data;

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

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDTDataTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_DT_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String[] cfgfile = ConstUrl.getTargetValue("DT_KunMingBase_Cfg").split("/");
	private static String[] EParafile = ConstUrl.getTargetValue("DT_KunMingBase_EPara").split("/");
	private static String[] DTfile = ConstUrl.getTargetValue("DT_KunMingBase_DT").split("/");

	private static String[] cfgfile2 = ConstUrl.getTargetValue("DT_KunMingBase2_Cfg").split("/");
	private static String[] EParafile2 = ConstUrl.getTargetValue("DT_KunMingBase2_EPara").split("/");
	private static String[] DTfile2 = ConstUrl.getTargetValue("DT_KunMingBase2_DT").split("/");

	private static String DT = "Analysis and Optimization of DT";

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
	public void T001_LTE_CreatDTOptimizTask_All() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoDTDataTask.creatDTOptimizTask(driver, defaultWindowID, "DT数据自动分析_S001", true, "15", true,
				"30", "30", true, null, null, null, null, cfgfile, EParafile, DTfile);
		TaskReportTask.asserTaskInitialState(driver, taskName, DT);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T002_LTE_CreatCompareReportTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoDTDataTask.creatCompareReportTask(driver, defaultWindowID, "DT数据自动分析_S002", true, "30",
				"30", null, null, null, null, cfgfile, EParafile, DTfile, cfgfile2, EParafile2, DTfile2);
		TaskReportTask.asserTaskInitialState(driver, taskName, "Comparison Report");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T003_LTE_CreatThemeOptimizTask_All() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String ThemeType = "全选";
		String taskName = AutoDTDataTask.creatThemeOptimizTask(driver, defaultWindowID, "DT数据自动分析_S003", ThemeType,
				cfgfile, EParafile, DTfile, null, null, null, null);
		TaskReportTask.asserTaskInitialState(driver, taskName, "Theme Optimization");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T004_LTE_CreatCounterStatisticsTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoDTDataTask.creatCounterStatisticsTask(driver, defaultWindowID, "DT数据自动分析_S004",
				"2016-08-08", "2016-08-09");
		TaskReportTask.asserTaskInitialState(driver, taskName, "Analysis_Counter_Statistics");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
