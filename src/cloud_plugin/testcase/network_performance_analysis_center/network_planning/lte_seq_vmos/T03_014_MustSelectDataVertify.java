package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_seq_vmos;

import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_Seq_VMOS_Page;
import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_VMOS_Const;
import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.page.HomePage;
import cloud_public.page.IndexPage;
import cloud_public.task.IndexTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T03_014_MustSelectDataVertify {

	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = ConstUrl.getProjectHomePath()
			+ "\\Data\\baseline\\lte_seq_vmos\\参数化表\\精品视频参数化表.xlsx";
	private static String SheetName = "必选信息验证";

	private String secns;
	private String TestCaseId;
	private String[] TaskType;
	private String[] mustselected;
	private String[] mustselecttipmessage;

	// 必选项信息验证

	public T03_014_MustSelectDataVertify(String secns, String testCaseId, String[] taskType, String[] mustselected,
			String[] mustselecttipmessage) {
		super();
		this.secns = secns;
		TestCaseId = testCaseId;
		TaskType = taskType;
		this.mustselected = mustselected;
		this.mustselecttipmessage = mustselecttipmessage;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.print("This is collection");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, SheetName));
		return coll;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomePage.gotoldVersion(driver);
		IndexPage.OpenNetPerfomanceAnalysisCenter(driver);
		IndexTask.changePrj(driver, LTE_VMOS_Const.VMOS_Project);
		SeqVMOSTask.openPlugin(driver);
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);
		String TASKNAME = "必选项验证" + "_" + ZIP.MMddhhmmssms();
		LTE_Seq_VMOS_Page.setTaskName(driver, TASKNAME);

	}

	@Before
	public void setUp() {
		result = false;
	}

	@After
	public void tearDown() {

		StartBackFillTMSS.backFill(TestCaseId, result);
		LTE_Seq_VMOS_Page.createTaskPreviousBTN(driver);
		System.out.println("tmss end xx");

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test
	public void T001_lte_seq_vmosCreateTask() {
		LOG.info_testcase("场景描述:" + secns);
		SeqVMOSTask.checkMustSelectData(driver, TaskType, mustselected, mustselecttipmessage);
		// 设置TMSS回填结果
		result = true;
	}

}
