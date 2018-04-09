package cloud_plugin.testcase.network_performance_analysis_center.network_planning.network_audit;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.network_audit.NetWorkAuditTask;
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
public class LTE_NetWorkAudit_Para_Report {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "NetWork Audit";
	private static boolean result = false;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\网络评估";
	private static String ParaFile = FilePath + "\\LTE\\参数化表\\网络评估.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName001;

	private boolean ExcelFlage;

	private String TopN;
	private String FailedCellsRati;
	private String RSRPThreshold;
	private String SceneType;
	private String[] checkType;
	private String BusyType;
	private String busyTime;
	private String ThresholdFile;

	private String[] cfgfile;
	private String[] Pfmfile;
	private String[] EParafile;
	private String[] MRfile;
	private String[] MMLfile;
	private String[] Propertyfile;
	private String[] SelfDefinefile;

	public LTE_NetWorkAudit_Para_Report(String secns, String TestCaseId, String taskName001, boolean ExcelFlage,
			String TopN, String FailedCellsRati, String RSRPThreshold, String SceneType, String[] checkType,
			String BusyType, String busyTime, String ThresholdFile, String[] cfgfile, String[] Pfmfile,
			String[] EParafile, String[] MRfile, String[] MMLfile, String[] Propertyfile, String[] SelfDefinefile) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.ExcelFlage = ExcelFlage;
		this.TopN = TopN;
		this.FailedCellsRati = FailedCellsRati;
		this.RSRPThreshold = RSRPThreshold;
		this.SceneType = SceneType;
		this.checkType = checkType;
		this.BusyType = BusyType;
		this.busyTime = busyTime;
		this.ThresholdFile = ThresholdFile;

		this.cfgfile = cfgfile;
		this.Pfmfile = Pfmfile;
		this.EParafile = EParafile;
		this.MRfile = MRfile;
		this.MMLfile = MMLfile;
		this.Propertyfile = Propertyfile;
		this.SelfDefinefile = SelfDefinefile;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "创建任务"));
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
	public void L_NetWorkAudit() {
		LOG.info_testcase("场景描述:" + secns);
		String ThresholdFile1 = null;
		if (ThresholdFile != null) {
			ThresholdFile1 = FilePath + ThresholdFile;
		}
		String taskName = NetWorkAuditTask.creatLTENetWorkAuditTask(driver, defaultWindowID, taskName001, ExcelFlage,
				TopN, FailedCellsRati, RSRPThreshold, SceneType, checkType, BusyType, busyTime, ThresholdFile1, cfgfile,
				Pfmfile, EParafile, MRfile, MMLfile, Propertyfile, SelfDefinefile);
		NetWorkAuditTask.GetAppName(driver);
		TaskReportTask.asserNewTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
