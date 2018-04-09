package cloud_plugin.testcase.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

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

import cloud_plugin.page.network_performance_analysis_center.basic_optimization.compare_configuration_parameters.CompareCfgParametersPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.basic_optimization.compare_configuration_parameters.Compare_Configuration_Parameters_Task;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SwitchDriver;
import common.util.tmss.StartBackFillTMSS;

/**
 * 配置参数对比 GSM 同网元
 ***/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class GSM_Compare_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "Comparison_of_a_Single_LTE_NE";
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\配置参数对比核查\\参数化表\\配置参数对比核查.xlsx";
	private static String uploadFilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\配置参数对比核查\\附件";
	private String secns;
	private String TestCaseId;
	private String taskName;
	private String[] oldConfigFileName;// 就配置文件
	private String[] newConfigFileName;// 新配置文件
	// 专家求助
	private String HelpType;
	private String detailedDescription;
	private String Filepath;

	public GSM_Compare_Test(String secns, String TestCaseId, String taskName, String[] oldConfigFileName,
			String[] newConfigFileName,
			// 专家求助
			String HelpType, String detailedDescription, String Filepath) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.oldConfigFileName = oldConfigFileName;
		this.newConfigFileName = newConfigFileName;
		this.HelpType = HelpType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
	}

	// 对比_GSM
	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "对比_GSM"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result);
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		String helpfile = null;
		if (Filepath != null) {
			helpfile = uploadFilePath + Filepath;
		}
		NetworkAnalysisCenterTask.openLTEAutoCfgParma_CompareData(driver);
		String taskName_str = Compare_Configuration_Parameters_Task.creatGSM(driver, taskName, oldConfigFileName,
				newConfigFileName, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, CompareCfgParametersPage.submitBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		// asserTaskInitialState方法作用：1.根据任务名称查询记录；2.获取任务状态和业务类型，如果任务状态为分析成功
		// TaskReportTask.asserTaskInitialState(driver, taskName_str,
		// ServiceType);
		// 设置TMSS回填结果
		// 专家求助
		Compare_Configuration_Parameters_Task.expertHelp(driver, taskName_str, HelpType, detailedDescription, helpfile);
		result = true;
	}

}
