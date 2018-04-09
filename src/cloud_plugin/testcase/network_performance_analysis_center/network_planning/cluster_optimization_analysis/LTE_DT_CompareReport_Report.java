package cloud_plugin.testcase.network_performance_analysis_center.network_planning.cluster_optimization_analysis;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.cluster_optimization_analysis.ClusterOptimizationTask;
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
public class LTE_DT_CompareReport_Report {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	private static String ServiceType = "Comparison Report";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\簇优化分析";
	private static String ParaFile = FilePath + "\\参数化表\\簇优化分析.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName001;

	private String[] cfgfile;
	private String[] EParafile;
	private String[] DTfile;

	private String[] cfgfile2;
	private String[] EParafile2;
	private String[] DTfile2;

	private boolean WhetherGrids;
	private String GridsPre;
	private String GridsLater;

	private String TextRSRP;
	private String TextSINR;
	private String TextPDCPUL;
	private String TextPDCPDL;

	public LTE_DT_CompareReport_Report(String secns, String TestCaseId, String taskName001, String[] cfgfile,
			String[] EParafile, String[] DTfile, String[] cfgfile2, String[] EParafile2, String[] DTfile2,
			boolean WhetherGrids, String GridsPre, String GridsLater, String TextRSRP, String TextSINR,
			String TextPDCPUL, String TextPDCPDL) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;

		this.cfgfile = cfgfile;
		this.EParafile = EParafile;
		this.DTfile = DTfile;

		this.cfgfile2 = cfgfile2;
		this.EParafile2 = EParafile2;
		this.DTfile2 = DTfile2;

		this.WhetherGrids = WhetherGrids;
		this.GridsPre = GridsPre;
		this.GridsLater = GridsLater;

		this.TextRSRP = TextRSRP;
		this.TextSINR = TextSINR;
		this.TextPDCPUL = TextPDCPUL;
		this.TextPDCPDL = TextPDCPDL;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "任务创建-对比报告"));
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
	public void L_CompareReport() {
		LOG.info_testcase("场景描述:" + secns);

		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = ClusterOptimizationTask.creatCompareReportTask(driver, defaultWindowID, taskName001,
				WhetherGrids, GridsPre, GridsLater, TextRSRP, TextSINR, TextPDCPUL, TextPDCPDL, cfgfile, EParafile,
				DTfile, cfgfile2, EParafile2, DTfile2);
		TaskReportTask.asserNewTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
