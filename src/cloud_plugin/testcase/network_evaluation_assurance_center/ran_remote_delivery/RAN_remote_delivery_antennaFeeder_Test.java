package cloud_plugin.testcase.network_evaluation_assurance_center.ran_remote_delivery;

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

import cloud_plugin.task.network_evaluation_assurance_center.RAN_remote_delivery.RANRemoteDeliveryTask;
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
 * 
 * RAN 基站与天馈设计(覆盖用例：基础片区、套餐包选择区、部分场景设计区、部分上传采集数据区)
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class RAN_remote_delivery_antennaFeeder_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver;
	private static String defaultWindowID = null;
	private static String ServiceType = "Base_Station_And_Antenna_Design";

	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\RAN远程交付";
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\RAN远程交付\\附件";
	private static String ParaFile = FilePath1 + "\\参数化表\\RAN远程交付.xlsx";

	private String secns;
	private String TestCaseId;
	private String taskName;
	private String IOAFilePath;
	private String workType;// 业务类型
	private String[] paySetMeal;// 交付套餐
	private String[] number;// 数量

	private String secnsChoose;// 场景选择
	private String netContent;// 网设内容
	private String projectDocumentFilePath;// 上传 工程文档
	private String[] stockConfigurationFilePath;// 存量配置
	private String bindingRelationFilePath;// 绑定关系
	private String baseInformationFilePath;// 基站信息

	private String antennaFeederInformationFilePath;// 天馈信息
	private String itemsRegulationFilePath;// 项目规则

	private String taskDescArea;// 任务描述
	private String submitState;

	public RAN_remote_delivery_antennaFeeder_Test(String secns, String testCaseId, String taskName, String IOAFilePath,
			String workType, String[] paySetMeal, String[] number, String secnsChoose, String netContent,
			String projectDocumentFilePath, String[] stockConfigurationFilePath, String bindingRelationFilePath,
			String baseInformationFilePath, String antennaFeederInformationFilePath, String itemsRegulationFilePath,
			String taskDescArea, String submitState) {
		this.secns = secns;
		this.TestCaseId = testCaseId;
		this.taskName = taskName;
		this.IOAFilePath = IOAFilePath;
		this.workType = workType;
		this.paySetMeal = paySetMeal;
		this.number = number;
		this.secnsChoose = secnsChoose;
		this.netContent = netContent;
		this.projectDocumentFilePath = projectDocumentFilePath;
		this.stockConfigurationFilePath = stockConfigurationFilePath;
		this.bindingRelationFilePath = bindingRelationFilePath;
		this.baseInformationFilePath = baseInformationFilePath;
		this.antennaFeederInformationFilePath = antennaFeederInformationFilePath;
		this.itemsRegulationFilePath = itemsRegulationFilePath;
		this.taskDescArea = taskDescArea;
		this.submitState = submitState;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Base_Station_And_Antenna_Design"));
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
		LOG.info_testcase("场景描述：" + secns);
		// IOA
		if (IOAFilePath != null) {
			IOAFilePath = FilePath + IOAFilePath;
		}
		// 工程文档
		if (projectDocumentFilePath != null) {
			projectDocumentFilePath = FilePath + projectDocumentFilePath;
		}
		// 天馈信息
		if (antennaFeederInformationFilePath != null) {
			antennaFeederInformationFilePath = FilePath + antennaFeederInformationFilePath;
		}
		if (bindingRelationFilePath != null) {
			bindingRelationFilePath = FilePath + bindingRelationFilePath;
		}
		if (baseInformationFilePath != null) {
			baseInformationFilePath = FilePath + baseInformationFilePath;
		}
		if (itemsRegulationFilePath != null) {
			itemsRegulationFilePath = FilePath + itemsRegulationFilePath;
		}
		String taskName_star = RANRemoteDeliveryTask.creatAntennaFeederTask(driver, taskName, IOAFilePath, workType,
				paySetMeal, number, secnsChoose, netContent, projectDocumentFilePath, stockConfigurationFilePath,
				bindingRelationFilePath, baseInformationFilePath, antennaFeederInformationFilePath,
				itemsRegulationFilePath, taskDescArea, submitState, defaultWindowID);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName_star, ServiceType);
		result = true;
	}

}
