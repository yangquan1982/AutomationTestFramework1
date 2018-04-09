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
public class UMTS_TrafficForecast_MinuteTraffic_Report {
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
	private String[] MinuteFile;
	private String ModifyPara;
	private String PredictionDate;
	private static String HolidayFile = FilePath + "\\CommonFile\\节假日补偿表.xlsx";

	private static int[] Times = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
			23 };

	private static String[] TypeIndicator = { "ALL" };

	public UMTS_TrafficForecast_MinuteTraffic_Report(String secns, String TestCaseId, String taskName001,
			boolean groupFlage, String groupType, String groupFile, String[] cfgFile, String[] MinuteFile,
			String ModifyPara, String PredictionDate) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.groupFlage = groupFlage;
		this.groupType = groupType;
		this.groupFile = groupFile;
		this.cfgFile = cfgFile;
		this.MinuteFile = MinuteFile;
		this.ModifyPara = ModifyPara;
		this.PredictionDate = PredictionDate;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "MinuteTraffic"));
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
		String taskName = TrafficForecastTask.creatUMTSMinuteTrafficTask(driver, defaultWindowID, taskName001,
				groupFlage, groupType, groupFile1, cfgFile, MinuteFile, HolidayFile, ModifyPara, PredictionDate,
				TypeIndicator, Times);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
