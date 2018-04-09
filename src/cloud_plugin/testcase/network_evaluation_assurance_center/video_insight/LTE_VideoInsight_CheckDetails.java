package cloud_plugin.testcase.network_evaluation_assurance_center.video_insight;

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
public class LTE_VideoInsight_CheckDetails {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\视频洞察";
	private static String ParaFile = FilePath + "\\参数化表\\视频洞察创建任务.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName;
	private String SEQAllNet;
	private String OTTSheet;
	private String ISPSheet;
	private String SEQCell;
	private String SEQGrid;

	private String Epara;
	private String Cfg;
	private String Sig;
	private String NoEncryVideo;
	private String EncryVideo;
	private String EMap;
	private String OTT;
	private String NoToLow;
	private String LVToH;
	private String LVUser;
	private String TopTNum;

	public LTE_VideoInsight_CheckDetails(String secns, String TestCaseId, String taskName, String SEQAllNet,
			String OTTSheet, String ISPSheet, String SEQCell, String SEQGrid, String Epara, String Cfg, String Sig,
			String NoEncryVideo, String EncryVideo, String EMap, String OTT, String NoToLow, String LVToH,
			String LVUser, String TopTNum) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;

		this.SEQAllNet = SEQAllNet;
		this.OTTSheet = OTTSheet;
		this.ISPSheet = ISPSheet;
		this.SEQCell = SEQCell;
		this.SEQGrid = SEQGrid;

		this.Epara = Epara;
		this.Cfg = Cfg;
		this.Sig = Sig;
		this.NoEncryVideo = NoEncryVideo;
		this.EncryVideo = EncryVideo;
		this.EMap = EMap;
		this.OTT = OTT;

		this.NoToLow = NoToLow;
		this.LVToH = LVToH;
		this.LVUser = LVUser;
		this.TopTNum = TopTNum;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "详情检查"));
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
	public void L_Report() {
		LOG.info_testcase("场景描述:" + secns);

		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());

		VideoInsightDetailslTask.checkDetails(driver, defaultWindowID, taskName, SEQAllNet, OTTSheet, ISPSheet, SEQCell,
				SEQGrid, Cfg, Epara, Sig, NoEncryVideo, EncryVideo, EMap, OTT, NoToLow, LVToH, LVUser, TopTNum);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
