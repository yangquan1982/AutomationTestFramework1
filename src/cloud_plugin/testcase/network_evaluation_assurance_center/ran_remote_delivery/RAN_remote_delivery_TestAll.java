package cloud_plugin.testcase.network_evaluation_assurance_center.ran_remote_delivery;

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

import cloud_plugin.task.network_evaluation_assurance_center.RAN_remote_delivery.RANRemoteDeliveryTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/**
 * 
 * RAN 网络设计_RAN(覆盖全部用例即：基础片区、套餐包选择区、场景设计区、上传采集数据区)
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class RAN_remote_delivery_TestAll {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "Network_Design_RAN";

	private static String FilePath１ = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\RAN远程交付";
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\RAN远程交付\\附件";
	private static String ParaFile = FilePath１ + "\\参数化表\\RAN远程交付.xlsx";
	private String secns;
	private String TestCaseId;
	private String taskName;
	private String IOAFilePath;
	private String workType;// 业务类型
	private String[] paySetMeal;// 交付套餐
	private String[] number;// 数量

	private String secnsChoose;// 场景选择
	private String netContent;// 网设内容
	private String[] controllerChoose;// 控制器选择
	private String baseStationControllerModel;// 基站控制模式
	private String baseStationWorkModel;// 基站工作模式
	private String projectDocumentFilePath;// 上传工程文档
	private String[] MMLFilePath;
	private String RNPFilePath;
	private String BOQFilePath;
	private String telephoneModelFilePath;
	private String consultParameFilePath;
	private String taskDescArea;
	private String submitState;

	public RAN_remote_delivery_TestAll(String secns, String TestCaseId, String taskName, String IOAFilePath,
			String workType, String[] paySetMeal, String[] number, String secnsChoose, String netContent,
			String[] controllerChoose, String baseStationControllerModel, String baseStationWorkModel,
			String projectDocumentFilePath, String[] MMLFilePath, String RNPFilePath, String BOQFilePath,
			String telephoneModelFilePath, String consultParameFilePath, String taskDescArea, String submitState) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName = taskName;
		this.IOAFilePath = IOAFilePath;
		this.workType = workType;
		this.paySetMeal = paySetMeal;
		this.number = number;
		this.secnsChoose = secnsChoose;
		this.netContent = netContent;
		this.controllerChoose = controllerChoose;
		this.baseStationControllerModel = baseStationControllerModel;
		this.baseStationWorkModel = baseStationWorkModel;
		this.projectDocumentFilePath = projectDocumentFilePath;
		this.MMLFilePath = MMLFilePath;
		this.RNPFilePath = RNPFilePath;
		this.BOQFilePath = BOQFilePath;
		this.telephoneModelFilePath = telephoneModelFilePath;
		this.consultParameFilePath = consultParameFilePath;
		this.taskDescArea = taskDescArea;
		this.submitState = submitState;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Network_Design_RAN"));
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
		// GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result);

	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		// IOA
		if (IOAFilePath != null) {
			IOAFilePath = FilePath + IOAFilePath;
		}
		// 工程文档
		if (projectDocumentFilePath != null) {
			projectDocumentFilePath = FilePath + projectDocumentFilePath;
		}
		// RNP
		if (RNPFilePath != null) {
			RNPFilePath = FilePath + RNPFilePath;
		}
		// BOQ
		if (BOQFilePath != null) {
			BOQFilePath = FilePath + BOQFilePath;
		}
		// 话务模型
		if (telephoneModelFilePath != null) {
			telephoneModelFilePath = FilePath + telephoneModelFilePath;
		}
		// 协商参数
		if (consultParameFilePath != null) {
			consultParameFilePath = FilePath + consultParameFilePath;
		}
		String taskName_Star = RANRemoteDeliveryTask.creatCountStatisticsTask(driver, taskName, IOAFilePath, workType,
				paySetMeal, number, secnsChoose, netContent, controllerChoose, baseStationControllerModel,
				baseStationWorkModel, projectDocumentFilePath, MMLFilePath, RNPFilePath, BOQFilePath,
				telephoneModelFilePath, consultParameFilePath, taskDescArea, submitState, defaultWindowID);
		// CommonJQ.getAttrValue(driver, "span:contains(\""+node2+"\")", "id",
		// 0);
		if (CommonJQ.isExisted(driver, "span:contains(\"任务添加失败\")", true)) {
			Assert.fail("任务提交失败");// 任务添加失败
		}
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName_Star, ServiceType);
		result = true;
	}

}
