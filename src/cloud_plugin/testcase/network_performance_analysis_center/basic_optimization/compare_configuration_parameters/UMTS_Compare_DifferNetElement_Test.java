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
 * 配置参数对比 UMTS小区劈裂前后配置对比
 ***/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class UMTS_Compare_DifferNetElement_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "Comparison_of_Multiple_LTE_NEs";
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\配置参数对比核查\\参数化表\\配置参数对比核查.xlsx";
	private static String uploadFilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\配置参数对比核查\\附件";
	private String secns;
	private String TestCaseId;
	private String taskName;

	private String[] flerryPreCfgFilePath;// 劈裂前配置文件
	private String[] flerryAftCfgFilePath;// 劈裂后配置文件
	private String relationMapping;// 关系映射表
	private String parameSetup;
	private String treeNode;// 树节点
	private String exportModel;// 导出全参数模板
	private String inputModel;// 导入自定义参数模板
	// 专家求助
	private String HelpType;
	private String detailedDescription;
	private String Filepath;

	public UMTS_Compare_DifferNetElement_Test(String secns, String TestCaseId, String taskName,
			String[] flerryPreCfgFilePath, String[] flerryAftCfgFilePath, String relationMapping, String parameSetup,
			String treeNode, String exportModel, String inputModel,
			// 专家求助
			String HelpType, String detailedDescription, String Filepath) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.flerryPreCfgFilePath = flerryPreCfgFilePath;
		this.flerryAftCfgFilePath = flerryAftCfgFilePath;
		this.relationMapping = relationMapping;
		this.parameSetup = parameSetup;
		this.treeNode = treeNode;
		this.exportModel = exportModel;
		this.inputModel = inputModel;
		this.HelpType = HelpType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "对比_UMTS小区劈裂前后配置对比"));// 对比_UMTS小区劈裂前后配置对比1
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
		// driver.quit();
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		NetworkAnalysisCenterTask.openLTEAutoCfgParma_CompareData(driver);
		String inputModel1 = null;
		String relationMapping1 = null;
		if (inputModel != null) {
			inputModel1 = uploadFilePath + inputModel;
		}
		if (relationMapping != null) {
			relationMapping1 = uploadFilePath + "\\" + relationMapping;
		}
		String helpfile = null;
		if (Filepath != null) {
			helpfile = uploadFilePath + Filepath;
		}
		String taskName_str = Compare_Configuration_Parameters_Task.creatUMTSDifferNetElement(driver, taskName,
				flerryPreCfgFilePath, flerryAftCfgFilePath, relationMapping1, parameSetup, treeNode, exportModel,
				inputModel1, defaultWindowID, uploadFilePath);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, CompareCfgParametersPage.submitBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		// asserTaskInitialState方法作用：1.根据任务名称查询记录；2.获取任务状态和业务类型，如果任务状态为分析成功
		// TaskReportTask.asserTaskInitialState(driver, taskName_str,
		// ServiceType);
		// 设置TMSS回填结果
		Compare_Configuration_Parameters_Task.expertHelp(driver, taskName_str, HelpType, detailedDescription, helpfile);
		result = true;

	}

}
