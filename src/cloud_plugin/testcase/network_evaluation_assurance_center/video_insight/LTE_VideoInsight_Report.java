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

import cloud_plugin.task.network_evaluation_assurance_center.video_insight.VideoInsightTask;
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
public class LTE_VideoInsight_Report {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	private static String ServiceType = "Video Insight";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\视频洞察";
	private static String ParaFile = FilePath + "\\参数化表\\视频洞察创建任务.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName001;
	private String TaskType;
	private String SEQAllNetFile;
	private String OTTSheetFile;
	private String ISPSheetFile;
	private String SEQCellFile;
	private String SEQGridFile;

	private String paraFile;
	private String[] CfgFile;
	private String[] SigFile;
	private String[] NoEncryVideoFile;
	private String[] EncryVideoFile;
	private String[] eMapFile;
	private String[] OTTFile;
	private String NoToLow;
	private String LVToH;
	private String LVUser;
	private String TopTNum;

	public LTE_VideoInsight_Report(String secns, String TestCaseId, String taskName001, String TaskType,
			String SEQAllNetFile, String OTTSheetFile, String ISPSheetFile, String SEQCellFile, String SEQGridFile,
			String paraFile, String[] CfgFile, String[] SigFile, String[] NoEncryVideoFile, String[] EncryVideoFile,
			String[] eMapFile, String[] OTTFile, String NoToLow, String LVToH, String LVUser, String TopTNum) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;

		this.TaskType = TaskType;
		this.SEQAllNetFile = SEQAllNetFile;
		this.OTTSheetFile = OTTSheetFile;
		this.ISPSheetFile = ISPSheetFile;
		this.SEQCellFile = SEQCellFile;
		this.SEQGridFile = SEQGridFile;

		this.paraFile = paraFile;
		this.CfgFile = CfgFile;
		this.SigFile = SigFile;
		this.NoEncryVideoFile = NoEncryVideoFile;
		this.EncryVideoFile = EncryVideoFile;
		this.eMapFile = eMapFile;
		this.OTTFile = OTTFile;

		this.NoToLow = NoToLow;
		this.LVToH = LVToH;
		this.LVUser = LVUser;
		this.TopTNum = TopTNum;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "任务创建"));
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
		String SEQAllNetFile1 = null;
		if (SEQAllNetFile != null) {
			SEQAllNetFile1 = FilePath + SEQAllNetFile;
		}
		String OTTSheetFile1 = null;
		if (OTTSheetFile != null) {
			OTTSheetFile1 = FilePath + OTTSheetFile;
		}
		String ISPSheetFile1 = null;
		if (ISPSheetFile != null) {
			ISPSheetFile1 = FilePath + ISPSheetFile;
		}
		String SEQCellFile1 = null;
		if (SEQCellFile != null) {
			SEQCellFile1 = FilePath + SEQCellFile;
		}
		String SEQGridFile1 = null;
		if (SEQGridFile != null) {
			SEQGridFile1 = FilePath + SEQGridFile;
		}
		String taskName = VideoInsightTask.CreatVideoInsightTask(driver, defaultWindowID, taskName001, TaskType,
				SEQAllNetFile1, OTTSheetFile1, ISPSheetFile1, SEQCellFile1, SEQGridFile1, CfgFile, paraFile, SigFile,
				NoEncryVideoFile, EncryVideoFile, eMapFile, OTTFile, NoToLow, LVToH, LVUser, TopTNum);
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
