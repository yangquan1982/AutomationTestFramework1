package cloud_plugin.testcase.network_performance_analysis_center.network_planning.dl_comp_cluster_design;

import java.util.Arrays;
import java.util.Collection;

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
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.task.network_performance_analysis_center.network_planning.dl_comp_cluster_design.DLCoMPClusterDesignTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_DLCoMPClusterDesign_SetParaErr {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static boolean result = false;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_特性规划设计";
	private static String ParaFile = FilePath + "\\参数化表\\L_特性规划设计_SetParaErr.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";
	private String secns;
	private String TestCaseId;
	private String taskName001;
	private String[] ServiceModule;

	private String[] cfgFile;
	private String[] paraFile;
	private String[] sigFile;
	private String[] pefFile;
	private String[] chrFile;
	private String[] FeatureFile;
	private String cellFile;

	private String CAType;
	private String[] Band;
	private String[] BandWidth;

	private boolean CATerminalFlage;
	private String CATerminal;

	private boolean AverageDRateFlage;
	private String AverageDRate;

	private boolean ServiceVolumeFlage;
	private String ServiceVolume;

	private boolean DLPRBUsageFlage;
	private String DLPRBUsage;

	private boolean CoverageScenarioFlage;
	private String[] CoverageScenario;

	private String CoCover;
	private String CoverRatio;
	private String RsrpThreshoid;
	private String MrThreshoid;
	private String PlanCAType;
	private String Azimuth;
	private String Excursion;
	private String NotDistance;

	private String ForecastType;
	private String PenetranceRatio;
	private String ActivateRatio;
	private String Throughput;

	public LTE_DLCoMPClusterDesign_SetParaErr(String secns, String TestCaseId, String taskName001,
			String[] ServiceModule, String[] cfgFile, String[] paraFile, String[] sigFile, String[] pefFile,
			String[] chrFile, String[] FeatureFile, String cellFile, String CAType, String[] Band, String[] BandWidth,
			boolean CATerminalFlage, String CATerminal, boolean AverageDRateFlage, String AverageDRate,
			boolean ServiceVolumeFlage, String ServiceVolume, boolean DLPRBUsageFlage, String DLPRBUsage,
			boolean CoverageScenarioFlage, String[] CoverageScenario, String CoCover, String CoverRatio,
			String RsrpThreshoid, String MrThreshoid, String PlanCAType, String Azimuth, String Excursion,
			String NotDistance, String ForecastType, String PenetranceRatio, String ActivateRatio, String Throughput) {

		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.ServiceModule = ServiceModule;

		this.cfgFile = cfgFile;
		this.paraFile = paraFile;
		this.sigFile = sigFile;
		this.pefFile = pefFile;
		this.chrFile = chrFile;
		this.FeatureFile = FeatureFile;
		this.cellFile = cellFile;

		this.CAType = CAType;
		this.Band = Band;
		this.BandWidth = BandWidth;

		this.CATerminalFlage = CATerminalFlage;
		this.CATerminal = CATerminal;

		this.AverageDRateFlage = AverageDRateFlage;
		this.AverageDRate = AverageDRate;

		this.ServiceVolumeFlage = ServiceVolumeFlage;
		this.ServiceVolume = ServiceVolume;

		this.DLPRBUsageFlage = DLPRBUsageFlage;
		this.DLPRBUsage = DLPRBUsage;

		this.CoverageScenarioFlage = CoverageScenarioFlage;
		this.CoverageScenario = CoverageScenario;

		this.CoCover = CoCover;
		this.CoverRatio = CoverRatio;
		this.RsrpThreshoid = RsrpThreshoid;
		this.MrThreshoid = MrThreshoid;
		this.PlanCAType = PlanCAType;
		this.Azimuth = Azimuth;
		this.Excursion = Excursion;
		this.NotDistance = NotDistance;
		this.ForecastType = ForecastType;
		this.PenetranceRatio = PenetranceRatio;
		this.ActivateRatio = ActivateRatio;
		this.Throughput = Throughput;

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
	public void L_SetParaErr() {
		LOG.info_testcase("场景描述:" + secns);
		String cellFile1 = null;
		if (cellFile != null) {
			cellFile1 = FilePath + cellFile;
		}
		String taskName = DLCoMPClusterDesignTask.creatLTEDLCoMPClusterDesignTask(driver, defaultWindowID, taskName001,
				ServiceModule, cfgFile, paraFile, sigFile, pefFile, chrFile, FeatureFile, cellFile1, CAType, Band,
				BandWidth, CATerminalFlage, CATerminal, AverageDRateFlage, AverageDRate, ServiceVolumeFlage,
				ServiceVolume, DLPRBUsageFlage, DLPRBUsage, CoverageScenarioFlage, CoverageScenario, CoCover,
				CoverRatio, RsrpThreshoid, MrThreshoid, PlanCAType, Azimuth, Excursion, NotDistance, ForecastType,
				PenetranceRatio, ActivateRatio, Throughput);
		LoadingPage.Loading(driver);
		boolean flage = CommonJQ.isExisted(driver, TaskReportPage.CreateTask, true);
		if (flage) {
			Assert.fail("预期任务创建失败,实际任务创建成功,taskName:" + taskName);
		}

		// 设置TMSS回填结果
		result = true;
	}
}
