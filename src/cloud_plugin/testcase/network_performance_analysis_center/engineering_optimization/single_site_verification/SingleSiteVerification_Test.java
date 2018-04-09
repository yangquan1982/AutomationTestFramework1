package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.single_site_verification;

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

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.single_site_verification.SingleSiteVerificationPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.single_site_verification.SingleSiteVerificationTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SwitchDriver;
import common.util.tmss.StartBackFillTMSS;

/***
 * 单站验证
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class SingleSiteVerification_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "MV Remote Technology";

	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\单站验证\\附件";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\单站验证";
	private static String ParaFile = FilePath1 + "\\参数化表\\单站验证.xlsx";
	private String secns;
	private String TestCaseId;
	private String taskName;// 任务名称
	private String reportModel;// 报告模板
	private String taskType;// 任务类型
	private String mapType;// 地图类型
	private String[] cfgData;// 配置数据
	private String[] projectParame;// 工程参数
	private String[] roadSurveyData;// 路测数据
	// 参数设置
	private String[] reconnaissanceMapCfg;// 勘测图片配置
	private String[] speedImg;// 速率截图配置
	private String[] DTFileCfg;// 文件映射
	private String[] RSSICfg;// PSSI配置
	private String[] expansionParameter;// 其他配置

	// 专家求助
	private String HelpType;
	private String detailedDescription;
	private String Filepath;

	/**
	 * @param secns
	 * @param testCaseId
	 * @param taskName
	 * @param taskType
	 * @param mapType
	 * @param cfgData
	 * @param projectParame
	 * @param roadSurveyData
	 */
	public SingleSiteVerification_Test(String secns, String TestCaseId, String taskName, String reportModel,
			String taskType, String mapType, String[] cfgData, String[] projectParame, String[] roadSurveyData,
			// 参数设置
			String[] reconnaissanceMapCfg, // 勘测图片配置
			String[] speedImg, // 速率截图配置
			String[] DTFileCfg, // 文件映射
			String[] RSSICfg, // PSSI配置
			String[] expansionParameter, // 其他配置
			// 专家求助
			String HelpType, String detailedDescription, String Filepath) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.reportModel = reportModel;
		this.taskType = taskType;
		this.mapType = mapType;
		this.cfgData = cfgData;
		this.projectParame = projectParame;
		this.roadSurveyData = roadSurveyData;
		// 参数配置
		this.reconnaissanceMapCfg = reconnaissanceMapCfg;// 勘测图片配置
		this.speedImg = speedImg;// 速率截图配置
		this.DTFileCfg = DTFileCfg;// 文件映射
		this.RSSICfg = RSSICfg;// PSSI配置
		this.expansionParameter = expansionParameter;// 其他配置
		// 专家求助
		this.HelpType = HelpType;
		this.detailedDescription = detailedDescription;
		this.Filepath = Filepath;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "单站验证_优化"));
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
			helpfile = FilePath + Filepath;
		}
		NetworkAnalysisCenterTask.openLETSingleSiteVerification(driver);
		String taskName_str = SingleSiteVerificationTask.creat_SingleSiteCheck(driver, taskName, reportModel, taskType,
				mapType, cfgData, projectParame, roadSurveyData, reconnaissanceMapCfg, // 勘测图片配置
				speedImg, // 速率截图配置
				DTFileCfg, // 文件映射
				RSSICfg, // PSSI配置
				expansionParameter, // 其他配置
				FilePath, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 点击保存
		CommonJQ.click(driver, SingleSiteVerificationPage.BtnCommit_Tasknew, true);
		SwitchDriver.switchDriverToSEQ(driver);
		// 专家求助
		TaskViewPluginTask.expertHelp(driver, taskName_str, HelpType, detailedDescription, helpfile);

		result = true;
	}

}
