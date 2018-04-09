package cloud_plugin.testcase.network_evaluation_assurance_center.video_insight;

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

import cloud_plugin.task.network_evaluation_assurance_center.video_insight.VideoInsightTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.AppParamUtil;
import common.util.CommonFile;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.LOG;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_VideoInsight_DownTemplate {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\视频洞察";
	private static String ParaFile = FilePath + "\\参数化表\\视频洞察创建任务.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private static String ResultHome = ConstUrl.getProjectHomePath() + "\\Data\\result\\视频洞察\\";
	private static String BaselineHome = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\视频洞察\\";

	private String secns;
	private String TestCaseId;
	private String TaskType;
	private boolean TempSEQAllNetFlage;
	private boolean TempOTTSheetFlage;
	private boolean TempISPSheetFlage;
	private boolean TempSEQCellFlage;
	private boolean TempSEQGridFlage;
	private String ResultFile;

	public LTE_VideoInsight_DownTemplate(String secns, String TestCaseId, String TaskType, boolean TempSEQAllNetFlage,
			boolean TempOTTSheetFlage, boolean TempISPSheetFlage, boolean TempSEQCellFlage, boolean TempSEQGridFlage,
			String ResultFile) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.TaskType = TaskType;

		this.TempSEQAllNetFlage = TempSEQAllNetFlage;
		this.TempOTTSheetFlage = TempOTTSheetFlage;
		this.TempISPSheetFlage = TempISPSheetFlage;
		this.TempSEQCellFlage = TempSEQCellFlage;
		this.TempSEQGridFlage = TempSEQGridFlage;

		this.ResultFile = ResultFile;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "模板下载"));
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
		String ResultHome1 = ResultHome + ResultFile;
		String BaselineHome1 = BaselineHome + ResultFile;

		VideoInsightTask.TemplateDownload(driver, TaskType, TempSEQAllNetFlage, TempOTTSheetFlage, TempISPSheetFlage,
				TempSEQCellFlage, TempSEQGridFlage, ResultHome1);
		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome1),
				FileCompare.SameNameCompareInPath(BaselineHome1, ResultHome1));

		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
