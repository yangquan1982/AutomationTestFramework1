package cloud_plugin.testcase.network_performance_analysis_center.basic_optimization.topn_cell_processing;

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

import cloud_plugin.task.network_performance_analysis_center.basic_optimization.topn_cell_processing.Umtstopncellprocessing;
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
public class Umts_Topn_CellProcessing_test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\Umts_Topn小区处理";
	private static String ParaFile = FilePath + "\\参数化表\\UmtsTopn小区处理任务下发参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private String[] ThemeOptimization;
	private String[] SetRABAccess;
	private String SetRRCAccess;
	private String[] SetThroughtOutRate;
	private String[] SetQiehuan;
	private String[] SetDisconnect;

	private String[] cfgfile;
	private String[] Parafile;
	private String[] Pfmfile;
	private String[] PChrfile;
	private String[] Mrfile;
	private String[] NodebConf;
	private String[] NodebM2000Conf;
	private String[] NodeBLicense;
	private String[] MML;

	public Umts_Topn_CellProcessing_test(String Sceneparameter, String taskName, String TestCaseId,
			String[] ThemeOptimization, String[] SetRABAccess, String SetRRCAccess, String[] SetThroughtOutRate,
			String[] SetQiehuan, String[] SetDisconnect, String[] cfgfile, String[] Parafile, String[] Pfmfile,
			String[] PChrfile, String[] Mrfile, String[] NodebConf, String[] NodebM2000Conf, String[] NodeBLicense,
			String[] MML) {

		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.ThemeOptimization = ThemeOptimization;
		this.SetRABAccess = SetRABAccess;
		this.SetRRCAccess = SetRRCAccess;
		this.SetThroughtOutRate = SetThroughtOutRate;
		this.SetQiehuan = SetQiehuan;
		this.SetDisconnect = SetDisconnect;
		this.cfgfile = cfgfile;
		this.Parafile = Parafile;
		this.Pfmfile = Pfmfile;
		this.PChrfile = PChrfile;
		this.Mrfile = Mrfile;
		this.NodebConf = NodebConf;
		this.NodebM2000Conf = NodebM2000Conf;
		this.NodeBLicense = NodeBLicense;
		this.MML = MML;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("读取参数化excel表中的参数值");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "UmtsTopn", 0));
		return coll;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@Before
	public void setUp() {
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
	public void Umts_TopnCreatTask() {

		LOG.info_testcase("场景描述:" + Sceneparameter);
		String taskNamel = Umtstopncellprocessing.creatUMTSTopnTask(driver, defaultWindowID, taskName,
				ThemeOptimization, SetRABAccess, SetRRCAccess, SetThroughtOutRate, SetQiehuan, SetDisconnect, cfgfile,
				Parafile, Pfmfile, PChrfile, Mrfile, NodebConf, NodebM2000Conf, NodeBLicense, MML);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskNamel, "");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
