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
 * 配置参数核查 UMTS
 ***/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class UMTS_check_Test {
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
	private String taskType;
	// 参数设置
	private String regulationSetup;// 规则设置
	private String selectSecns;// 场景
	private String parameBaseModel;// 参数基线模板
	private boolean importModel;
	private boolean historicalTemplate;
	private String UMTSScenes;// 场景
	private boolean filterCondition_ifCheckAll;// 是否全选
	private String filterConditionSelect;// 过滤条件
	private String[] filterConditionChecked;// 过滤条件
	private String[] outPutSetup;// 输出设置
	// 上传文件
	private String[] RNCcfgDataFile;// RNC配置数据
	private String[] NodeBcfgDataFile;// NodeB配置数据
	private String netSecnsFile;// 组网场景
	// 专家求助
	private String HelpType;
	private String detailedDescription;
	private String Filepath;

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "核查_UMTS"));// 对比_UMTS小区劈裂前后配置对比1
	}

	/**
	 * @param secns
	 * @param testCaseId
	 * @param taskName
	 * @param regulationSetup
	 * @param selectSecns
	 * @param filterCondition_ifCheckAll
	 * @param filterConditionSelect
	 * @param filterConditionChecked
	 * @param outPutSetup
	 * @param rNCcfgDataFile
	 * @param nodeBcfgDataFile
	 * @param netSecnsFile
	 * @param helpType
	 * @param detailedDescription
	 * @param filepath
	 */
	public UMTS_check_Test(String secns, String TestCaseId, String taskName, String taskType, String regulationSetup,
			String selectSecns, String parameBaseModel, boolean importModel, boolean historicalTemplate,
			String UMTSScenes, boolean filterCondition_ifCheckAll, String filterConditionSelect,
			String[] filterConditionChecked, String[] outPutSetup, String[] RNCcfgDataFile, String[] NodeBcfgDataFile,
			String netSecnsFile, String HelpType, String detailedDescription, String Filepath) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.taskType = taskType;
		this.regulationSetup = regulationSetup;
		this.selectSecns = selectSecns;
		this.parameBaseModel = parameBaseModel;
		this.importModel = importModel;
		this.UMTSScenes = UMTSScenes;
		this.filterCondition_ifCheckAll = filterCondition_ifCheckAll;
		this.filterConditionSelect = filterConditionSelect;
		this.filterConditionChecked = filterConditionChecked;
		this.outPutSetup = outPutSetup;
		this.RNCcfgDataFile = RNCcfgDataFile;
		this.NodeBcfgDataFile = NodeBcfgDataFile;
		this.netSecnsFile = netSecnsFile;
		this.HelpType = HelpType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
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
		NetworkAnalysisCenterTask.openLTEAutoCfgParma_CompareData(driver);
		String parameBaseModel1 = null;
		String Filepath1 = null;
		if (null != parameBaseModel) {
			parameBaseModel1 = uploadFilePath + "\\" + parameBaseModel;
		}
		if (null != Filepath) {
			Filepath1 = uploadFilePath + "\\" + Filepath;
		}
		String netSecnsFile1 = null;
		if (netSecnsFile != null) {
			netSecnsFile1 = uploadFilePath + "\\" + netSecnsFile;
		}
		String taskName_str = Compare_Configuration_Parameters_Task.createUTMS_check(driver, taskName, taskType,
				regulationSetup, selectSecns, parameBaseModel1, filterCondition_ifCheckAll, filterConditionSelect,
				filterConditionChecked, outPutSetup, RNCcfgDataFile, NodeBcfgDataFile, netSecnsFile1, uploadFilePath,
				importModel, historicalTemplate, UMTSScenes, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, CompareCfgParametersPage.submitBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		// 专家求助
		Compare_Configuration_Parameters_Task.expertHelp(driver, taskName_str, HelpType, detailedDescription,
				Filepath1);
		// 设置TMSS回填结果
		result = true;
	}
}
