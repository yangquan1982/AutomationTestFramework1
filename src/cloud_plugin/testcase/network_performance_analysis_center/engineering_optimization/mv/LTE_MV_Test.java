package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.mv;

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

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.mv.AutoMVDataTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/**
 * @author dxw 2016-12-5 MV专家求助
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_MV_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "MV Remote Technology";

	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\MV专家求助\\用户附件";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\MV专家求助";
	private static String ParaFile = FilePath1 + "\\参数化表\\MV.xlsx";
	private static String GT3KFile = FilePath1 + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName_Star;
	private boolean MVFlage;
	private String HelpTitle;
	private String HelpDetail;
	private String MVfilePath;

	public LTE_MV_Test(String secns, String TestCaseId, String taskName_Star, boolean MVFlage, String HelpTitle,
			String HelpDetail, String MVfilePath) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName_Star = taskName_Star;
		this.MVFlage = MVFlage;
		this.HelpTitle = HelpTitle;
		this.HelpDetail = HelpDetail;
		this.MVfilePath = MVfilePath;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "MV"));
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
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
	}

	/**
	 * @author pgenexautotest
	 * @param driver
	 * @param taskName
	 * @param ifMV
	 * @param Detail
	 * @param filePath
	 * @param submit
	 *            /cancle
	 **/
	@Test
	public void Creat_Task() {
		LOG.info_testcase("场景描述:" + secns);
		String MVfilePath1 = null;
		if (MVfilePath != null) {
			MVfilePath1 = FilePath + MVfilePath;
		}
		String taskName = AutoMVDataTask.creatCounterStatisticsTask(driver, taskName_Star, MVFlage, HelpTitle,
				HelpDetail, MVfilePath1);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		// asserTaskInitialState方法作用：1.根据任务名称查询记录；2.获取任务状态和业务类型，如果任务状态为分析成功
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		// 设置TMSS回填结果
		result = true;
	}
}
