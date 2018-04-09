package cloud_plugin.testcase.network_performance_analysis_center.value_area_evaluation;

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

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class UMTS_ValueAreaEvaluation_Speed_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "User Rate Evaluate";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\U_速率评估";
	private static String ParaFile = FilePath + "\\参数化表\\SmokeValueAreaEvaluationSpeed.xlsx";
	private String secns;
	private String taskName001;

	private String[] cfgFile;
	private String[] EparaFile;
	private String[] MrFile;
	private String[] PfmFile;
	private String[] NodeBPfmFile;
	private String[] FeatureFile;
	private String[] MapFile;

	private String PositType;
	private String RasterValue;
	private boolean AGPSFlage;

	private String RasterRateEvaType;
	private String MRCount;
	private String StartTime;
	private String EndTime;

	private String LowRate;
	private String MainCellMR;

	private boolean ClusterFlage;
	private String FactorFile;
	private boolean ClearMRFlage;

	public UMTS_ValueAreaEvaluation_Speed_Report(String secns, String taskName001, String[] cfgFile, String[] EparaFile,
			String[] MrFile, String[] PfmFile, String[] NodeBPfmFile, String[] FeatureFile, String[] MapFile,
			String PositType, String RasterValue, boolean AGPSFlage, String RasterRateEvaType, String MRCount,
			String StartTime, String EndTime, String LowRate, String MainCellMR, boolean ClusterFlage,
			String FactorFile, boolean ClearMRFlage) {
		this.secns = secns;
		this.taskName001 = taskName001;

		this.cfgFile = cfgFile;
		this.EparaFile = EparaFile;
		this.MrFile = MrFile;
		this.PfmFile = PfmFile;
		this.NodeBPfmFile = NodeBPfmFile;
		this.FeatureFile = FeatureFile;
		this.MapFile = MapFile;

		this.PositType = PositType;
		this.RasterValue = RasterValue;
		this.AGPSFlage = AGPSFlage;

		this.RasterRateEvaType = RasterRateEvaType;
		this.MRCount = MRCount;
		this.StartTime = StartTime;
		this.EndTime = EndTime;

		this.LowRate = LowRate;
		this.MainCellMR = MainCellMR;

		this.ClusterFlage = ClusterFlage;
		this.FactorFile = FactorFile;
		this.ClearMRFlage = ClearMRFlage;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		/**
		 * 1.Basefile is empty,Checkfile is not empty 2.Basefile is not
		 * empty,Checkfile is empty 3.Basefile is empty,Checkfile is empty
		 * 4.Para is empty 5.CustParafile is empty
		 */

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Basic", 0));
	}

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
	public void U_SpeedEvaluate() {
		LOG.info_testcase("场景描述:" + secns);
		String FactorFile1 = null;
		if (FactorFile != null) {
			FactorFile1 = FilePath + FactorFile;
		}
		String taskName = HotSpotTask.creatUMTSHotSpotSpeedTask(driver, defaultWindowID, taskName001, PositType,
				RasterValue, AGPSFlage, cfgFile, EparaFile, MrFile, PfmFile, NodeBPfmFile, FeatureFile, MapFile,
				RasterRateEvaType, MRCount, StartTime, EndTime, LowRate, MainCellMR, ClusterFlage, FactorFile1,
				ClearMRFlage);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
