package cloud_plugin.testcase.downloadreport.network_evaluation_assurance_center.video_insight;

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

import cloud_plugin.task.network_evaluation_assurance_center.video_insight.VideoInsightDetailslTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.AppParamUtil;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_VideoInsight_WholeNetCheck {

	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String secns;
	private String TestCaseId;
	private String taskname;
	private String TrafficInsightLeft;
	private String TrafficInsightRight;
	private String VideoSource;
	private String ValueUser;
	private String ValueTerminal;
	private String TrafficModelTrend;
	private String TrafficModel3G4GStat;
	private String TrafficModelVideoStat;
	private String TrafficModelCDNStat;

	private String E2EDelay;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\视频洞察";
	private static String ParaFile = FilePath + "\\参数化表\\视频洞察创建任务.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	public LTE_VideoInsight_WholeNetCheck(String secns, String TestCaseId, String taskname, String TrafficInsightLeft,
			String TrafficInsightRight, String VideoSource, String ValueUser, String ValueTerminal,
			String TrafficModelTrend, String TrafficModel3G4GStat, String TrafficModelVideoStat,
			String TrafficModelCDNStat, String E2EDelay) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskname = taskname;
		this.TrafficInsightLeft = TrafficInsightLeft;
		this.TrafficInsightRight = TrafficInsightRight;
		this.VideoSource = VideoSource;
		this.ValueUser = ValueUser;
		this.ValueTerminal = ValueTerminal;
		this.TrafficModelTrend = TrafficModelTrend;
		this.TrafficModel3G4GStat = TrafficModel3G4GStat;
		this.TrafficModelVideoStat = TrafficModelVideoStat;
		this.TrafficModelCDNStat = TrafficModelCDNStat;
		this.E2EDelay = E2EDelay;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "整网结果比对"));
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
	public void L_WholeNetCheck() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LOG.info_testcase("场景描述:" + secns);
		VideoInsightDetailslTask.checkResults(driver, defaultWindowID, taskname, TrafficInsightLeft,
				TrafficInsightRight, VideoSource, ValueUser, ValueTerminal, TrafficModelTrend, TrafficModel3G4GStat,
				TrafficModelVideoStat, TrafficModelCDNStat, E2EDelay);

		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
