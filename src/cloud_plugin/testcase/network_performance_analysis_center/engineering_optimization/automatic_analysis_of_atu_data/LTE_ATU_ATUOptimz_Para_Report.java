package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_atu_data;

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

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_atu_data.AutoATUDataTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.AppParamUtil;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_ATU_ATUOptimz_Para_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	private static String ServiceType = "ATU Data Analysis";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\ATU数据自动分析";
	private static String ParaFile = FilePath + "\\参数化表\\ATU任务创建.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName001;

	private String[] cfgfile;
	private String[] EParafile;
	private String[] DTfile;

	private boolean WhetherGrids;
	private String GridsPre;
	private String GridsLater;

	private String[] chapter;
	private String[] Indicator;

	private String OperRSRP;
	private String TextRSRP;

	private String OperSINR;
	private String TextSINR;

	private String OperAPPUL;
	private String TextAPPUL;

	private String OperAPPDL;
	private String TextAPPDL;

	public LTE_ATU_ATUOptimz_Para_Report(String secns, String TestCaseId, String taskName001, String[] cfgfile,
			String[] EParafile, String[] DTfile, boolean WhetherGrids, String GridsPre, String GridsLater,
			String[] chapter, String[] Indicator, String OperRSRP, String TextRSRP, String OperSINR, String TextSINR,
			String OperAPPUL, String TextAPPUL, String OperAPPDL, String TextAPPDL) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;

		this.cfgfile = cfgfile;
		this.EParafile = EParafile;
		this.DTfile = DTfile;

		this.WhetherGrids = WhetherGrids;
		this.GridsPre = GridsPre;
		this.GridsLater = GridsLater;

		this.chapter = chapter;
		this.Indicator = Indicator;

		this.OperRSRP = OperRSRP;
		this.TextRSRP = TextRSRP;

		this.OperSINR = OperSINR;
		this.TextSINR = TextSINR;

		this.OperAPPUL = OperAPPUL;
		this.TextAPPUL = TextAPPUL;

		this.OperAPPDL = OperAPPDL;
		this.TextAPPDL = TextAPPDL;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "ATU分析优化参数"));
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
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void L_ATUOptimz() {
		LOG.info_testcase("场景描述:" + secns);

		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoATUDataTask.creatATUOptimizTask(driver, defaultWindowID, taskName001, WhetherGrids,
				GridsPre, GridsLater, cfgfile, EParafile, DTfile, chapter, Indicator, OperRSRP, TextRSRP, OperSINR,
				TextSINR, OperAPPUL, TextAPPUL, OperAPPDL, TextAPPDL);
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
