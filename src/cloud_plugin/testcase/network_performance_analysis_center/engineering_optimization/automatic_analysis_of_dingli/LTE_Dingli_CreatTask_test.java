package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dingli;

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

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDingLiDataTask;
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
public class LTE_Dingli_CreatTask_test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\鼎利数据自动分析";
	private static String ParaFile = FilePath + "\\参数化表\\鼎利分析优化任务下发参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskname;
	private String TestCaseId;
	private boolean WhetherTopN;
	private String TopN;
	private boolean WhetherGrids;
	private String GridsPre;
	private String GridsLater;
	private boolean WhetherPoint;
	private String[] cfgfile;
	private String[] EParafile;
	private String[] DTfile;
	private String[] Polygon;

	public LTE_Dingli_CreatTask_test(String Sceneparameter, String taskname, String TestCaseId, boolean WhetherTopN,
			String TopN, boolean WhetherGrids, String GridsPre, String GridsLater, boolean WhetherPoint,
			String[] cfgfile, String[] EParafile, String[] DTfile, String[] Polygon) {

		this.Sceneparameter = Sceneparameter;
		this.taskname = taskname;
		this.TestCaseId = TestCaseId;
		this.WhetherTopN = WhetherTopN;
		this.TopN = TopN;
		this.WhetherGrids = WhetherGrids;
		this.GridsPre = GridsPre;
		this.GridsLater = GridsLater;
		this.WhetherPoint = WhetherPoint;
		this.cfgfile = cfgfile;
		this.EParafile = EParafile;
		this.DTfile = DTfile;
		this.Polygon = Polygon;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "dingli", 0));
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
	public void L_DingliCreatTask() {

		LOG.info_testcase("场景描述:" + Sceneparameter);
		String taskNamel = AutoDingLiDataTask.creatDingLiOptimizTask(driver, defaultWindowID, taskname, WhetherTopN,
				TopN, WhetherGrids, GridsPre, GridsLater, WhetherPoint, cfgfile, EParafile, DTfile, Polygon);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskNamel, "鼎利分析优化");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
