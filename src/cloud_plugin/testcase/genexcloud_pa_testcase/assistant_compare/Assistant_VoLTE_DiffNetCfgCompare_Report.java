package cloud_plugin.testcase.genexcloud_pa_testcase.assistant_compare;

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

import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.basic_optimization.compare_configuration_parameters.CompareCfgParaTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Assistant_VoLTE_DiffNetCfgCompare_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();
//	
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	
	//Data path
	private static String ResultHome_T001 = ConstUrl.getProjectHomePath()+"\\Data\\result\\Compare\\PA_路测\\ViLTE";
	private static String BaselineHome_T001 = ConstUrl.getProjectHomePath()+"\\Data\\baseline\\Compare\\PA_路测\\ViLTE";

	private static String ResultHome_T002 = ConstUrl.getProjectHomePath()+"\\Data\\result\\Compare\\自定义参数";
	private static String BaselineHome_T002 = ConstUrl.getProjectHomePath()+"\\Data\\baseline\\Compare\\自定义参数";
	
	private static String taskName_T001 = "TestWithout";
	private static String taskName_T002 = "MOS报告";
	@BeforeClass
	public static void setUpBeforeClass() {
		driver=  CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		NetworkAnalysisCenterTask.openAutoPAData(driver);
	}


	@Before
	public void setUp() {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown()  {
		
	}
	@AfterClass
	public static void tearDownAfterClass()  {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T001_LTEDiffNet_ExportReport_RadioParameter() {
		System.out.println(ZIP.NowTime()+"Start the testcase:"+name.getMethodName());
		String getID = TaskReportTask.asserTaskEndState(driver, taskName_T001);
		CompareCfgParaTask.downLoad_MoveReport_ForPA(driver, defaultWindowID, getID, ResultHome_T001);
		String[] StarWith = {"VoLTE"};
		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome_T001) ,  
				FileCompare.fileCompare(BaselineHome_T001, ResultHome_T001, StarWith));
		System.out.println(ZIP.NowTime()+"End the testcase:"+name.getMethodName());	
	}
	@Test(GT3Kid = "")
	public void T002_LTEDiffNet_ExportReport_UserDefinedParameter() {
		System.out.println(ZIP.NowTime()+"Start the testcase:"+name.getMethodName());
		String getID = TaskReportTask.asserTaskEndState(driver, taskName_T002);
		CompareCfgParaTask.downLoad_MoveReport_ForPA(driver, defaultWindowID, getID, ResultHome_T002);
		String[] StarWith = {"VoLTE"};
		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome_T002) ,  
				FileCompare.fileCompare(BaselineHome_T002, ResultHome_T002, StarWith));
		System.out.println(ZIP.NowTime()+"End the testcase:"+name.getMethodName());	
	}
}
