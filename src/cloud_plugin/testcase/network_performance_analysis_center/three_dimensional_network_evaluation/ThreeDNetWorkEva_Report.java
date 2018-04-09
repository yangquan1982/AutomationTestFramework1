package cloud_plugin.testcase.network_performance_analysis_center.three_dimensional_network_evaluation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

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

import cloud_plugin.task.network_performance_analysis_center.three_dimensional_network_evaluation.ThreeDNetWorkEvaTask;
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
public class ThreeDNetWorkEva_Report {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static boolean result = false;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\3D网络评估";
	private static String ParaFile = FilePath + "\\参数化表\\3D网络评估.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName001;
	private String RAT;

	private String[] MRFile;
	private String[] PCHRFile;
	private String paraFile;
	private String[] PolygonFile;
	private boolean OnlineFlage;
	private String[] MapFile;
	private String[] FeatureFile;

	private String AntennaFile;
	private String[] OTTFile;
	private String DTFile;

	public ThreeDNetWorkEva_Report(String secns, String TestCaseId, String taskName001, String RAT, String[] MRFile,
			String[] PCHRFile, String paraFile, String[] PolygonFile, boolean OnlineFlage, String[] MapFile,
			String[] FeatureFile, String AntennaFile, String[] OTTFile, String DTFile) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.RAT = RAT;

		this.MRFile = MRFile;
		this.PCHRFile = PCHRFile;
		this.paraFile = paraFile;
		this.PolygonFile = PolygonFile;
		this.OnlineFlage = OnlineFlage;
		this.MapFile = MapFile;
		this.FeatureFile = FeatureFile;
		this.AntennaFile = AntennaFile;
		this.OTTFile = OTTFile;
		this.DTFile = DTFile;

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
	public void G_Coverage_Evaluation() {
		LOG.info_testcase("场景描述:" + secns);
		String AntennaFile1 = null;
		if (AntennaFile != null) {
			AntennaFile1 = FilePath + AntennaFile;
		}
		String DTFile1 = null;
		if (DTFile != null) {
			DTFile1 = FilePath + DTFile;
		}
		Set<String> handles_0 = ThreeDNetWorkEvaTask.Creat3DNetWorkEvaTask(driver, defaultWindowID, taskName001, RAT,
				MRFile, PCHRFile, paraFile, PolygonFile, OnlineFlage, MapFile, FeatureFile, AntennaFile1, OTTFile,
				DTFile1);
		ThreeDNetWorkEvaTask.view3DGis(driver, handles_0);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
