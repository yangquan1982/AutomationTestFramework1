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
public class UMTS_DTOverseas_Probe_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String[] DTfile = ConstUrl.getTargetValue("DT_Probe_UMTS_DT").split("/");
	private static String[] LEParafile = {};
	private static String[] GEParafile = {};
	private static String[] UEParafile = ConstUrl.getTargetValue("DT_Probe_UMTS_EPara").split("/");
	private static String[] Mapfile = {};
	private static String[] ExEParafile = {};
	private static String[] Ratefile = {};

	private static String[] EmptyFile = {};

	private static String taskName_T001 = "Probe";
	private static String taskName_T002 = "Probe_BA";

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
	public void T001_UMTS_CreatProbeTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoDTDataOverseasTask.creatUMTSProbeTask(driver, defaultWindowID, taskName_T001, "", DTfile,
				LEParafile, GEParafile, UEParafile, Mapfile, ExEParafile, Ratefile);
		TaskReportTask.asserTaskInitialState(driver, taskName, "");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T002_UMTS_CreatProbe_BATask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoDTDataOverseasTask.creatUMTSProbeTask(driver, defaultWindowID, taskName_T002, "BA",
				DTfile, LEParafile, GEParafile, UEParafile, Mapfile, ExEParafile, Ratefile);
		TaskReportTask.asserTaskInitialState(driver, taskName, "");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T003_UMTS_DT_EParaErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		AutoDTDataOverseasTask.creatUMTSProbeTask(driver, defaultWindowID, taskName_T001, "", DTfile, LEParafile,
				GEParafile, EmptyFile, Mapfile, ExEParafile, Ratefile);
		AutoDTDataOverseasTask.DT_ErrorMsg(driver, "UEPara");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T004_UMTS_DT_DTErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		AutoDTDataOverseasTask.creatUMTSProbeTask(driver, defaultWindowID, taskName_T001, "", EmptyFile, LEParafile,
				GEParafile, UEParafile, Mapfile, ExEParafile, Ratefile);
		AutoDTDataOverseasTask.DT_ErrorMsg(driver, "DT");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
