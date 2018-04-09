package cloud_plugin.testcase.downloadreport.network_evaluation_assurance_center.lte_kpi_report;

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

import cloud_plugin.task.network_evaluation_assurance_center.NetworkSafeGuardCenterTask;
import cloud_plugin.task.network_evaluation_assurance_center.lte_kpi_report.LTEKPIReportTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_KPIReport_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ResultHome = ConstUrl.getProjectHomePath() + "\\Data\\result\\LTE KPI报告\\";
	private static String BaselineHome = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\LTE KPI报告\\";

	private String secns;
	private String TestCaseId;
	private String taskName;
	private String Type;
	private String[] StarWith;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\LTE KPI报告";
	private static String ParaFile = FilePath + "\\参数化表\\KPI报告报告下载.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	public LTE_KPIReport_Report(String secns, String TestCaseId, String taskName, String Type, String[] StarWith) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.Type = Type;
		this.StarWith = StarWith;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "report"));
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
	public void L_KPIReport() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LOG.info_testcase("场景描述:" + secns);

		String ResultHome1 = ResultHome + taskName;
		String BaselineHome1 = BaselineHome + taskName;
		if ("移动".equalsIgnoreCase(Type)) {
			NetworkSafeGuardCenterTask.openLKPIReportChinaMobile(driver);
		} else if ("电信".equalsIgnoreCase(Type)) {
			NetworkSafeGuardCenterTask.openLKPIReportChinaTelecom(driver);
		} else {
			NetworkSafeGuardCenterTask.openLKPIReportChinaUnicom(driver);
		}
		TaskReportTask.asserTaskEndState(driver, taskName);
		LTEKPIReportTask.downLoad_MoveReport(driver, defaultWindowID, taskName, ResultHome1);
		if (StarWith != null) {
			Assert.assertTrue(CommonFile.fileReportMsg(ResultHome1),
					FileCompare.fileCompare(BaselineHome1, ResultHome1, StarWith));
		} else {
			Assert.assertTrue(CommonFile.fileReportMsg(ResultHome1),
					FileCompare.SameNameCompareInPath(BaselineHome1, ResultHome1));
		}

		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
