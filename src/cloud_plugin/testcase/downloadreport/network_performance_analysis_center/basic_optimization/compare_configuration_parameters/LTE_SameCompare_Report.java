package cloud_plugin.testcase.downloadreport.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

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
public class LTE_SameCompare_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();
//	
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	
	//Data path
	private static String ResultHome = ConstUrl.getProjectHomePath()+"\\Data\\result\\Compare\\Same";
	private static String BaselineHome = ConstUrl.getProjectHomePath()+"\\Data\\baseline\\Compare\\Same";
	private static String taskName = "SameNet_";
	@BeforeClass
	public static void setUpBeforeClass() {
		driver=  CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		NetworkAnalysisCenterTask.OpenLTECfgCompare(driver);
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
	public void T001_LTESameNet_ExportReport() {
		System.out.println(ZIP.NowTime()+"Start the testcase:"+name.getMethodName());
		String getID = TaskReportTask.asserTaskEndState(driver, taskName);
		CompareCfgParaTask.downLoad_MoveReport(driver, defaultWindowID, getID, ResultHome);
		String[] StarWith = {"LTE MML Compare Report _","LTE MML Compare Report UnMatchedNes_"};
		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome) ,  
				FileCompare.fileCompare(BaselineHome, ResultHome, StarWith));
		System.out.println(ZIP.NowTime()+"End the testcase:"+name.getMethodName());	
	}
}
