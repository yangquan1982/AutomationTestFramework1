package cloud_plugin.testcase.network_performance_analysis_center.network_planning.volte_coverage_evaluation;

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

import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.AppParamUtil;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class Volte_UMTS_Coverage_ExpertProc {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean result = false;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\VoLTE覆盖评估";
	private static String ParaFile = FilePath + "\\参数化表\\VolteCoverageEvaluation.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";
	private String secns;
	private String TestCaseId;
	private String taskName;

	// 专家反馈
	private String ProcType;
	private String detailedDescription;
	private String Filepath;

	public Volte_UMTS_Coverage_ExpertProc(String secns, String TestCaseId, String taskName, String ProcType,
			String detailedDescription, String Filepath) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;

		this.ProcType = ProcType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "ExpertProc"));
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.loginExpert(driver);
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
	public void U_Coverage_Evaluation() {
		LOG.info_testcase("场景描述:" + secns);

		String helpfile = null;
		if (Filepath != null) {
			helpfile = FilePath + Filepath;
		}
		// 打开
		NetworkAnalysisCenterTask.openVolteCoverageEvaluation(driver);
		TaskViewPluginTask.expertProc(driver, taskName, ProcType, detailedDescription, helpfile);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
