package cloud_plugin.testcase.network_performance_analysis_center.three_dimensional_network_evaluation;

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

import cloud_plugin.task.network_performance_analysis_center.three_dimensional_network_evaluation.ThreeDNetWorkEvaTask;
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
public class ThreeDNetWorkEva_DownTemplate {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\3D网络评估";
	private static String ParaFile = FilePath + "\\参数化表\\3D网络评估.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private static String ResultHome = ConstUrl.getProjectHomePath() + "\\Data\\result\\3D网络评估\\";
	private static String BaselineHome = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\3D网络评估\\";

	private String secns;
	private String TestCaseId;
	private String RAT;
	private boolean TempAntennasFlage;
	private boolean TempDTFlage;
	private String ResultFile;

	public ThreeDNetWorkEva_DownTemplate(String secns, String TestCaseId, String RAT, boolean TempAntennasFlage,
			boolean TempDTFlage, String ResultFile) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.RAT = RAT;

		this.TempAntennasFlage = TempAntennasFlage;
		this.TempDTFlage = TempDTFlage;

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

		ThreeDNetWorkEvaTask.TemplateDownload(driver, RAT, TempAntennasFlage, TempDTFlage, ResultHome1);
		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome1),
				FileCompare.SameNameCompareInPath(BaselineHome1, ResultHome1));

		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
