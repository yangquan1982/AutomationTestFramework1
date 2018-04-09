package cloud_plugin.testcase.network_performance_analysis_center.network_planning.volte_coverage_evaluation;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.volte_coverage_evaluation.VolteCoverageEvaluationTask;
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
public class Volte_GSM_Coverage_Evaluation_Report {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "VolteCoverevaluate";
	private static boolean result = false;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\VoLTE覆盖评估";
	private static String ParaFile = FilePath + "\\参数化表\\VolteCoverageEvaluation.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName001;
	private String RAT;
	private String[] TaskType;
	private String evaluateReport;

	private String[] LTEPPFile;

	private String[] LTEsigFile;
	private String[] PPFile;
	private String[] chrFile;
	private String[] cfgFile;
	private String[] mrFile;
	private String[] LTEpropertyFile;
	private String[] propertyFile;
	private String LTEPrfFile;
	private String GSMPrfFile;
	private String[] mapFile;
	private String[] polygonFile;

	private String PositType;
	private String RasterValue;

	private String Raster_mr;
	private String Raster_cell_mr;
	private String Min_interfrence;
	private String Min_level;
	private String LTECoverage;
	private String BusinessGrid;
	private String LTEMRCollect;
	private String MrReport;

	private String IndoorRate;
	private String ComplexRate;
	private String IndoorLevel;
	private String ComplexLevel;

	private String[] RSRP;

	private boolean AllFlage;
	private boolean RateFlage;
	private boolean RedirectionFlage;
	private boolean CQIFlage;
	private boolean UserCountFlage;
	private boolean PerfFlowFlage;
	private boolean SrvccFlage;

	public Volte_GSM_Coverage_Evaluation_Report(String secns, String TestCaseId, String taskName001, String RAT,
			String[] TaskType, String evaluateReport, String[] LTEsigFile, String[] LTEPPFile, String[] PPFile,
			String[] chrFile, String[] cfgFile, String[] mrFile, String[] LTEpropertyFile, String[] propertyFile,
			String LTEPrfFile, String GSMPrfFile, String[] mapFile, String[] polygonFile, String PositType,
			String RasterValue, String Raster_mr, String Raster_cell_mr, String Min_interfrence, String Min_level,
			String LTECoverage, String BusinessGrid, String LTEMRCollect, String MrReport, String IndoorRate,
			String ComplexRate, String IndoorLevel, String ComplexLevel, String[] RSRP, boolean AllFlage,
			boolean RateFlage, boolean RedirectionFlage, boolean CQIFlage, boolean UserCountFlage,
			boolean PerfFlowFlage, boolean SrvccFlage) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.RAT = RAT;
		this.TaskType = TaskType;
		this.evaluateReport = evaluateReport;

		this.LTEsigFile = LTEsigFile;
		this.LTEPPFile = LTEPPFile;
		this.PPFile = PPFile;
		this.chrFile = chrFile;
		this.cfgFile = cfgFile;
		this.mrFile = mrFile;
		this.LTEpropertyFile = LTEpropertyFile;
		this.propertyFile = propertyFile;
		this.LTEPrfFile = LTEPrfFile;
		this.GSMPrfFile = GSMPrfFile;
		this.mapFile = mapFile;
		this.polygonFile = polygonFile;

		this.PositType = PositType;
		this.RasterValue = RasterValue;

		this.Raster_mr = Raster_mr;
		this.Raster_cell_mr = Raster_cell_mr;
		this.Min_interfrence = Min_interfrence;
		this.Min_level = Min_level;
		this.LTECoverage = LTECoverage;
		this.BusinessGrid = BusinessGrid;
		this.LTEMRCollect = LTEMRCollect;
		this.MrReport = MrReport;

		this.IndoorRate = IndoorRate;
		this.ComplexRate = ComplexRate;
		this.IndoorLevel = IndoorLevel;
		this.ComplexLevel = ComplexLevel;

		this.RSRP = RSRP;

		this.AllFlage = AllFlage;
		this.RateFlage = RateFlage;
		this.RedirectionFlage = RedirectionFlage;
		this.CQIFlage = CQIFlage;
		this.UserCountFlage = UserCountFlage;
		this.PerfFlowFlage = PerfFlowFlage;
		this.SrvccFlage = SrvccFlage;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "HW-GSM"));
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
	public void G_Coverage_Evaluation() {
		LOG.info_testcase("场景描述:" + secns);

		String LTEPrfFile1 = null;
		if (LTEPrfFile != null) {
			LTEPrfFile1 = FilePath + LTEPrfFile;
		}
		String GSMPrfFile1 = null;
		if (GSMPrfFile != null) {
			GSMPrfFile1 = FilePath + GSMPrfFile;
		}
		String taskName = VolteCoverageEvaluationTask.creatGSMVolteCoverageEvaluationTask(driver, defaultWindowID,
				taskName001, RAT, TaskType, evaluateReport, LTEsigFile, LTEPPFile, PPFile, chrFile, cfgFile, mrFile,
				LTEpropertyFile, propertyFile, LTEPrfFile1, GSMPrfFile1, mapFile, polygonFile, PositType, RasterValue,
				Raster_mr, Raster_cell_mr, Min_interfrence, Min_level, LTECoverage, BusinessGrid, LTEMRCollect,
				MrReport, IndoorRate, ComplexRate, IndoorLevel, ComplexLevel, RSRP, AllFlage, RateFlage,
				RedirectionFlage, CQIFlage, UserCountFlage, PerfFlowFlage, SrvccFlage);

		VolteCoverageEvaluationTask.GetAppName(driver);
		TaskReportTask.asserNewTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
