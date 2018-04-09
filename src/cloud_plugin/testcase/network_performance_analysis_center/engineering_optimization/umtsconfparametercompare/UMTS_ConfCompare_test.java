package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.umtsconfparametercompare;

import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
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
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.UmtsConfParameterCompare.UmtsConfCompare;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class UMTS_ConfCompare_test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\UMTS配置参数对比";
	private static String ParaFile = FilePath + "\\参数化表\\U配置参数对比参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private String[] cfgfilebefore;
	private String[] cfgfileafter;
	private String splitMappingFile;
	private String[] WuxianParaSeclet;
	private String SelfDefineParaSeclet;

	public UMTS_ConfCompare_test(String Sceneparameter, String taskName, String TestCaseId, String[] cfgfilebefore,
			String[] cfgfileafter, String splitMappingFile, String[] WuxianParaSeclet, String SelfDefineParaSeclet) {

		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.cfgfilebefore = cfgfilebefore;
		this.cfgfileafter = cfgfileafter;
		this.splitMappingFile = splitMappingFile;
		this.WuxianParaSeclet = WuxianParaSeclet;
		this.SelfDefineParaSeclet = SelfDefineParaSeclet;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		/**
		 * 1.Basefile is empty,Checkfile is not empty 2.Basefile is not
		 * empty,Checkfile is empty 3.Basefile is empty,Checkfile is empty
		 * 4.Para is empty 5.CustParafile is empty
		 */

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "UMTSConfCompare", 0));
	}

	@BeforeClass
	public static void setUpBeforeClass() {

		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@Before
	public void setUp() {
		LOG.info("用例开始");
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
		System.out.println("tmss end xx");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void Umts_ConfCompareCreatTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LOG.info_testcase("场景描述:" + Sceneparameter);
		if (splitMappingFile != null) {
			splitMappingFile = FilePath + splitMappingFile;
		}
		if (SelfDefineParaSeclet != null) {
			SelfDefineParaSeclet = FilePath + SelfDefineParaSeclet;
		}
		String taskNamel = UmtsConfCompare.creatUMTSConfCompareTask(driver, defaultWindowID, taskName, cfgfilebefore,
				cfgfileafter, splitMappingFile, WuxianParaSeclet, SelfDefineParaSeclet);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskNamel, "配置参数对比");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
