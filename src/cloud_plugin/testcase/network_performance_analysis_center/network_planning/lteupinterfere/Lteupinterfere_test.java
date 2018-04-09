package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lteupinterfere;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.lteupinterfere.Lteupinterfere;
import cloud_public.page.GetDataByTypePage;
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
public class Lteupinterfere_test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\射频通道检查";
	private static String ParaFile = FilePath + "\\参数化表\\射频通道检查参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private String SceneSet;
	private String[] GanRaoCheckSet;
	private String Nothreshold;
	private String[] RrcAnalyCheck;
	private String[] AreaCheck;
	private String[] TypeCheck;
	private String[] FddCheck;
	private String[] cfgfile;
	private String[] Pfmfile;
	private String[] Csvfile;
	private String[] Parafile;
	private String[] Chrfile;

	public Lteupinterfere_test(String Sceneparameter, String taskName, String TestCaseId, String SceneSet,
			String[] GanRaoCheckSet, String Nothreshold, String[] RrcAnalyCheck, String[] AreaCheck, String[] TypeCheck,
			String[] FddCheck, String[] cfgfile, String[] Pfmfile, String[] Csvfile, String[] Parafile,
			String[] Chrfile) {

		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.SceneSet = SceneSet;
		this.GanRaoCheckSet = GanRaoCheckSet;
		this.Nothreshold = Nothreshold;
		this.RrcAnalyCheck = RrcAnalyCheck;
		this.AreaCheck = AreaCheck;
		this.TypeCheck = TypeCheck;
		this.FddCheck = FddCheck;
		this.cfgfile = cfgfile;
		this.Pfmfile = Pfmfile;
		this.Csvfile = Csvfile;
		this.Parafile = Parafile;
		this.Chrfile = Chrfile;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("读取参数化excel表中的参数值");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "LTE射频", 0));
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
	public void Lte_lteupinterfereCreatTask() {
		LOG.info_testcase("场景描述:" + Sceneparameter);
		String taskNamel = Lteupinterfere.creatlteupinterfereTask(driver, defaultWindowID, taskName, SceneSet,
				GanRaoCheckSet, Nothreshold, RrcAnalyCheck, AreaCheck, TypeCheck, FddCheck, cfgfile, Pfmfile, Csvfile,
				Parafile, Chrfile);
		TaskReportTask.asserTaskInitialState(driver, taskName, "待系统分析");
		System.out.println("新建任务名称为：" + taskNamel);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// TMSS回填
		result = true;
	}
}
