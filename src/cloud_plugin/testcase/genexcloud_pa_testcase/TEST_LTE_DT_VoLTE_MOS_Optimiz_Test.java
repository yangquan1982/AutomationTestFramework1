package cloud_plugin.testcase.genexcloud_pa_testcase;

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

import cloud_plugin.task.genexcloud_pa.AutoPADataTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class TEST_LTE_DT_VoLTE_MOS_Optimiz_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	// private static String Theme = "Theme Optimization";
	private static String Theme = "";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\PA_路测分析";
	private static String ParaFile = FilePath + "\\参数化表\\PA.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String ReportMode;
	private String DateMode;
	private String[] ThemeType;

	private String[] cfgfile;
	private String[] EParafile;
	private String[] Datefile;

	public TEST_LTE_DT_VoLTE_MOS_Optimiz_Test(String secns, String ReportMode, String DateMode, String[] ThemeType,
			String[] cfgfile, String[] EParafile, String[] Datefile) {
		this.secns = secns;
		this.ReportMode = ReportMode;
		this.DateMode = DateMode;
		this.ThemeType = ThemeType;

		this.cfgfile = cfgfile;
		this.EParafile = EParafile;
		this.Datefile = Datefile;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "VoLTE报告"));
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
		StartBackFillTMSS.backFill(ReportMode, result, GT3KFile);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void VoLTE_MOS_ThemeOptimiz() {
		// String DateMode = "gen";
		LOG.info_testcase("场景描述:" + secns);

		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoPADataTask.creatThemeOptimizTask(driver, defaultWindowID, ReportMode, DateMode, ThemeType,
				cfgfile, EParafile, Datefile, secns);
		TaskReportTask.asserTaskInitialState(driver, taskName, Theme);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}

}
