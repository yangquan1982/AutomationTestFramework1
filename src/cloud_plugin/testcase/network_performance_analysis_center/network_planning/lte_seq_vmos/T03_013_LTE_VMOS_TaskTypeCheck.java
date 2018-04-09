package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_seq_vmos;

import java.util.ArrayList;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.junit.v4_5.runner.GUITestRunner;
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
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_Seq_VMOS_Page;
import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_VMOS_Const;
import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.page.HomePage;
import cloud_public.page.IndexPage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ZIP;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T03_013_LTE_VMOS_TaskTypeCheck {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	// @Rule
	// public Timeout globalTimeout = Timeout.
	// public Timeout globalTimeout = Timeout.seconds(Long
	// .parseLong(ConstUrl.TestCaseTimeout) / 1000);

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomePage.gotoldVersion(driver);
		IndexPage.OpenNetPerfomanceAnalysisCenter(driver);
		SeqVMOSTask.ChangeProject(driver, LTE_VMOS_Const.VMOS_Project);
		SeqVMOSTask.openPlugin(driver);
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);
		SeqVMOSTask.checkCreateNewTaskSkip(driver);
		LTE_Seq_VMOS_Page.setTaskName(driver, "TestTask" + ZIP.MMddhhmmssms());

	}

	@Before
	public void setUp() {
		// GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		// SeqVMOSTask.goBackToNetworkAnalysisCenter(driver);

	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "新建任务.014")
	public void T001_NoTaskTypeSelectCheck() {
		LOG.info("验证默认任务类型 和不选择默认类型提示信息");
		int type = LTE_Seq_VMOS_Page.getTaskTypeNumber(driver);
		if (type != 3) {
			Assert.fail("默认数据类型选择不对，只有" + type + "个默认选择");
		}
		LTE_Seq_VMOS_Page.CancelTaskTypeSet(driver);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);
		String tips = LTE_Seq_VMOS_Page.getTaskTypeTipMessage(driver);
		Assert.assertTrue("不选择任务类型，提示信息不正确", tips.equals(LTE_VMOS_Const.TaskTypeTipsMessage));
	}

	@Test(GT3Kid = "新建任务.182")
	public void T002_SEQModelTaskTypeSelectCheck() {
		LOG.info("验证默认任务类型 和不选择默认类型提示信息");
		LTE_Seq_VMOS_Page.CancelTaskTypeSet(driver);
		LTE_Seq_VMOS_Page.selectTaskType(driver, LTE_VMOS_Const.TaskType_SeqAssistant);
		ArrayList<String> selectedtype = LTE_Seq_VMOS_Page.getSelectedTaskTypes(driver);
		if (selectedtype.size() != 2 || !selectedtype.contains(LTE_VMOS_Const.TaskType_Evaluation)) {
			Assert.fail("选择SEQ建模任务类型，评估没有自动都勾选");
		}

	}

}
