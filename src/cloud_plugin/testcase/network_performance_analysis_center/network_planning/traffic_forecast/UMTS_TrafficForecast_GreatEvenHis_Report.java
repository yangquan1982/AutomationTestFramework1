package cloud_plugin.testcase.network_performance_analysis_center.network_planning.traffic_forecast;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.traffic_forecast.TrafficForecastTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
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
public class UMTS_TrafficForecast_GreatEvenHis_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "TRAFFIC_FORECAST";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\U_目标网预测";
	private static String ParaFile = FilePath + "\\参数化表\\TrafficForecast.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";
	private String secns;
	private String TestCaseId;
	private String taskName001;
	private boolean groupFlage;
	private String groupType;
	private String groupFile;
	private String[] cfgFile;
	private String[] pefFile;
	private String[] chrFile;
	private String[] pefU2000File;
	private String[] pefPRSFile;
	private String terminalFile;
	private String ModifyPara;
	private String PredictionDate;

	private String HisTimeStart;
	private String HisTimeEnd;
	private String GroundCount;
	private String TypeRatio;
	private String MarketRatio;
	private static String HolidayFile = FilePath + "\\CommonFile\\节假日补偿表.xlsx";

	public UMTS_TrafficForecast_GreatEvenHis_Report(String secns, String TestCaseId, String taskName001,
			boolean groupFlage, String groupType, String groupFile, String[] cfgFile, String[] pefFile,
			String[] chrFile, String[] pefU2000File, String[] pefPRSFile, String terminalFile, String ModifyPara,
			String PredictionDate, String HisTimeStart, String HisTimeEnd, String GroundCount, String TypeRatio,
			String MarketRatio) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.groupFlage = groupFlage;
		this.groupType = groupType;
		this.groupFile = groupFile;
		this.cfgFile = cfgFile;
		this.pefFile = pefFile;
		this.chrFile = chrFile;
		this.pefU2000File = pefU2000File;
		this.pefPRSFile = pefPRSFile;
		this.terminalFile = terminalFile;
		this.ModifyPara = ModifyPara;
		this.PredictionDate = PredictionDate;
		this.HisTimeStart = HisTimeStart;
		this.HisTimeEnd = HisTimeEnd;
		this.GroundCount = GroundCount;
		this.TypeRatio = TypeRatio;
		this.MarketRatio = MarketRatio;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "GreatEvenHis"));
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
	public void U_BasicTraffic() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LOG.info_testcase("场景描述:" + secns);
		String groupFile1 = null;
		if (groupFlage == true) {
			if (groupFile != null) {
				groupFile1 = FilePath + groupFile;
			}
		}
		String terminalFile1 = null;
		if ((terminalFile != null) && (!"default".equalsIgnoreCase(terminalFile))) {
			terminalFile1 = FilePath + terminalFile;
		} else if ("default".equalsIgnoreCase(terminalFile)) {
			terminalFile1 = "default";
		}
		String taskName = TrafficForecastTask.creatUMTSGreatEvenHisTask(driver, defaultWindowID, taskName001, groupType,
				groupFile1, cfgFile, pefFile, chrFile, pefU2000File, pefPRSFile, terminalFile1, HolidayFile, ModifyPara,
				PredictionDate, HisTimeStart, HisTimeEnd, GroundCount, TypeRatio, MarketRatio);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
