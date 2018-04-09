package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data;

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
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_DT_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String[] cfgfile = ConstUrl.getTargetValue("DT_KunMingBase_Cfg").split("/");
	private static String[] EParafile = ConstUrl.getTargetValue("DT_KunMingBase_EPara").split("/");
	private static String[] DTfile = ConstUrl.getTargetValue("DT_KunMingBase_DT").split("/");

	private static String[] cfgfile_empty = {};
	private static String[] EParafile_empty = {};
	private static String[] DTfile_empty = {};
	private static String ThemeType_empty = "";

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
	public void T001_LTE_DT_CfgMsgErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String ThemeType = "全选";
		AutoDTDataTask.creatThemeOptimizTask(driver, defaultWindowID, "Theme", ThemeType, cfgfile_empty, EParafile,
				DTfile, null, null, null, null);
		AutoDTDataTask.DT_ErrorMsg(driver, "Cfg");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T002_LTE_DT_EParaMsgErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String ThemeType = "全选";
		AutoDTDataTask.creatThemeOptimizTask(driver, defaultWindowID, "Theme", ThemeType, cfgfile, EParafile_empty,
				DTfile, null, null, null, null);
		AutoDTDataTask.DT_ErrorMsg(driver, "EPara");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T003_LTE_DT_DTMsgErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String ThemeType = "全选";
		AutoDTDataTask.creatThemeOptimizTask(driver, defaultWindowID, "Theme", ThemeType, cfgfile, EParafile,
				DTfile_empty, null, null, null, null);
		AutoDTDataTask.DT_ErrorMsg(driver, "DT");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T004_LTE_DT_TopNErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		AutoDTDataTask.creatDTOptimizTask(driver, defaultWindowID, "DTOptimiz", true, "", true, "30", "30", true, null,
				null, null, null, cfgfile, EParafile, DTfile);
		AutoDTDataTask.DT_ErrorMsg(driver, "TopN");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T005_LTE_DT_RasterErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		AutoDTDataTask.creatDTOptimizTask(driver, defaultWindowID, "DTOptimiz", true, "15", true, "", "30", true, null,
				null, null, null, cfgfile, EParafile, DTfile);
		AutoDTDataTask.DT_ErrorMsg(driver, "Raster");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T006_LTE_DT_SpecialErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		AutoDTDataTask.creatThemeOptimizTask(driver, defaultWindowID, "Theme", ThemeType_empty, cfgfile, EParafile,
				DTfile, null, null, null, null);
		AutoDTDataTask.DT_ErrorMsg(driver, "Special");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T007_LTE_DT_StartTimeErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		AutoDTDataTask.creatCounterStatisticsTask(driver, defaultWindowID, "CounterStatistics", "", "2016-08-09");
		AutoDTDataTask.DT_ErrorMsg(driver, "StartTime");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T008_LTE_DT_EndTimeErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		AutoDTDataTask.creatCounterStatisticsTask(driver, defaultWindowID, "CounterStatistics", "2016-08-08", "");
		AutoDTDataTask.DT_ErrorMsg(driver, "EndTime");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
