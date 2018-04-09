package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data;

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

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDTDataTask;
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
public class LTE_DT_ThemeOptimiz_Report {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	private static String Theme = "Theme Optimization";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\DT数据自动分析";
	private static String ParaFile = FilePath + "\\参数化表\\DT.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName001;
	private String ThemeType;

	private String[] cfgfile;
	private String[] EParafile;
	private String[] DTfile;
	private String Mod3Para;
	private String OverCoverPara;
	private String GuoCoverPara;
	private String ZoomPara;

	public LTE_DT_ThemeOptimiz_Report(String secns, String TestCaseId, String taskName001, String ThemeType,
			String[] cfgfile, String[] EParafile, String[] DTfile, String Mod3Para, String OverCoverPara,
			String GuoCoverPara, String ZoomPara) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.ThemeType = ThemeType;

		this.cfgfile = cfgfile;
		this.EParafile = EParafile;
		this.DTfile = DTfile;

		this.Mod3Para = Mod3Para;
		this.OverCoverPara = OverCoverPara;
		this.GuoCoverPara = GuoCoverPara;
		this.ZoomPara = ZoomPara;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "任务创建-专题优化"));
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
	public void L_ThemeOptimiz() {
		LOG.info_testcase("场景描述:" + secns);

		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		String taskName = AutoDTDataTask.creatThemeOptimizTask(driver, defaultWindowID, taskName001, ThemeType, cfgfile,
				EParafile, DTfile, Mod3Para, OverCoverPara, GuoCoverPara, ZoomPara);
		TaskReportTask.asserNewTaskInitialState(driver, taskName, Theme);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
