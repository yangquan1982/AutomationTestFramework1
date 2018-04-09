package cloud_plugin.testcase.network_performance_analysis_center.service_and_trafficforecast;

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
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class UMTS_ServiceAndTrafficForecast_Report {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "TRAFFIC_FORECAST";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\U_目标网预测";
	private static String ParaFile = FilePath + "\\参数化表\\SmokeServiceAndTrafficForecast.xlsx";
	private String secns;
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
	private String IndicType;
	private boolean CalcFlage;
	private static String HolidayFile = FilePath + "\\CommonFile\\节假日补偿表.xlsx";

	public UMTS_ServiceAndTrafficForecast_Report(String secns, String taskName001, boolean groupFlage, String groupType,
			String groupFile, String[] cfgFile, String[] pefFile, String[] chrFile, String[] pefU2000File,
			String[] pefPRSFile, String terminalFile, String ModifyPara, String PredictionDate, String IndicType,
			boolean CalcFlage) {
		this.secns = secns;
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
		this.IndicType = IndicType;
		this.CalcFlage = CalcFlage;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		/**
		 * 1.Basefile is empty,Checkfile is not empty 2.Basefile is not
		 * empty,Checkfile is empty 3.Basefile is empty,Checkfile is empty
		 * 4.Para is empty 5.CustParafile is empty
		 */

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "BasicTraffic"));
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
	public void U_BasicTraffic() {
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
		String taskName = TrafficForecastTask.creatUMTSBasicTrafficForecastTask(driver, defaultWindowID, taskName001,
				groupFlage, groupType, groupFile1, cfgFile, pefFile, chrFile, pefU2000File, pefPRSFile, terminalFile1,
				HolidayFile, ModifyPara, PredictionDate, IndicType, CalcFlage);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
