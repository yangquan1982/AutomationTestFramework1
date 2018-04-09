package cloud_plugin.testcase.network_performance_analysis_center.network_planning.hotspot;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.hotspot.HotSpotTask;
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
public class UMTS_HotSpot6D_Para_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static boolean result = false;

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "6D Model Evaluate";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\U_6D评估";
	private static String ParaFile = FilePath + "\\参数化表\\6D.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";
	private String secns;
	private String TestCaseId;
	private String taskName001;

	private String[] cfgFile;
	private String[] EparaFile;
	private String[] MrFile;
	private String[] PCHRFile;
	private String[] FeatureFile;

	private String PositType;
	private String RasterValue;
	private boolean AGPSFlage;

	private boolean AllFlage;
	private boolean TerminalFlage;
	private boolean BusinessFlage;
	private boolean UserFlage;
	private boolean ComplaintsFlage;
	private boolean AnalysisFlage;

	private String FactorFile;

	public UMTS_HotSpot6D_Para_Report(String secns, String TestCaseId, String taskName001, String[] cfgFile,
			String[] EparaFile, String[] MrFile, String[] PCHRFile, String[] FeatureFile, String PositType,
			String RasterValue, boolean AGPSFlage, boolean AllFlage, boolean TerminalFlage, boolean BusinessFlage,
			boolean UserFlage, boolean ComplaintsFlage, boolean AnalysisFlage, String FactorFile) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;

		this.cfgFile = cfgFile;
		this.EparaFile = EparaFile;
		this.MrFile = MrFile;
		this.PCHRFile = PCHRFile;
		this.FeatureFile = FeatureFile;

		this.PositType = PositType;
		this.RasterValue = RasterValue;
		this.AGPSFlage = AGPSFlage;

		this.AllFlage = AllFlage;
		this.TerminalFlage = TerminalFlage;
		this.BusinessFlage = BusinessFlage;
		this.UserFlage = UserFlage;
		this.ComplaintsFlage = ComplaintsFlage;
		this.AnalysisFlage = AnalysisFlage;
		this.FactorFile = FactorFile;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Basic"));
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
	public void U_6DEvaluate() {
		LOG.info_testcase("场景描述:" + secns);
		String FactorFile1 = null;
		if (FactorFile != null) {
			FactorFile1 = FilePath + FactorFile;
		}
		String taskName = HotSpotTask.creatUMTSHotSpot6DTask(driver, defaultWindowID, taskName001, cfgFile, EparaFile,
				MrFile, PCHRFile, FeatureFile, PositType, RasterValue, AGPSFlage, AllFlage, TerminalFlage,
				BusinessFlage, UserFlage, ComplaintsFlage, AnalysisFlage, FactorFile1);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
