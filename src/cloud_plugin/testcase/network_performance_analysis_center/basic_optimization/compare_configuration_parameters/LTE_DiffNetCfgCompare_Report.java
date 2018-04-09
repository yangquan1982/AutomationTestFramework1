package cloud_plugin.testcase.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_DiffNetCfgCompare_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String[] Oldfile = ConstUrl.getTargetValue("Compare_CfgFileFirst").split("/");
	private static String[] Newfile = ConstUrl.getTargetValue("Compare_CfgFileSecond").split("/");

	private static String Baseline_CustParaFile = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\Compare\\template";
	private static String Result_CustParaFile = ConstUrl.getProjectHomePath() + "\\Data\\result\\Compare\\template";

	private static String taskName_T001 = "无线参数";
	private static String taskName_T002 = "自定义参数";

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
	public void T001_LTEDiffNet_ExportReport_RadioParameter() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = CompareCfgParaTask.Create_DiffNetCfgCompare(driver, defaultWindowID, taskName_T001, Oldfile,
				Newfile, true, "All", null);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName, "Comparison of Multiple LTE NEs");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T002_LTEDiffNet_ExportReport_UserDefinedParameter() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = CompareCfgParaTask.Create_DiffNetCfgCompare(driver, defaultWindowID, taskName_T002, Oldfile,
				Newfile, false, "All", Baseline_CustParaFile + "\\MO_parameter_CSV_template.csv");
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName, "Comparison of Multiple LTE NEs");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

	@Test(GT3Kid = "")
	public void T003_LTEDiffNet_template_Export() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		CompareCfgParaTask.CfgCompare_template_Exp(driver, Result_CustParaFile);
		// 结果对比
		Assert.assertTrue(CommonFile.fileReportMsg(Result_CustParaFile),
				FileCompare.SameNameCompareInPath(Baseline_CustParaFile, Result_CustParaFile));
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
