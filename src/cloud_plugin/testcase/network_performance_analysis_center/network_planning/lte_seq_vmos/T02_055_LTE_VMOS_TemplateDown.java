package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_seq_vmos;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.junit.v4_5.runner.GUITestRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_Seq_VMOS_Page;
import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_VMOS_Const;
import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.HomePage;
import cloud_public.page.IndexPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonWD;
import common.util.LOG;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T02_055_LTE_VMOS_TemplateDown {
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

	}

	@Before
	public void setUp() {
		LOG.info("用例开始");
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		SeqVMOSTask.goBackToNetworkAnalysisCenter(driver);
		SeqVMOSTask.ChangeProject(driver, LTE_VMOS_Const.VMOS_Project);
		SeqVMOSTask.openPlugin(driver);
	}

	@After
	public void tearDown() {
		LOG.info("用例结束");

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	// 模板下载
	@Test(GT3Kid = "新建任务.055")
	public void T001_DowmTemplate() {
		LOG.info("下载任务报告验证");
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);
		SeqVMOSTask.checkCreateNewTaskSkip(driver);
		LTE_Seq_VMOS_Page.setTaskName(driver, "视频流量预测模板下载验证01");
		String[] taskType = { LTE_VMOS_Const.TaskType_Evaluation };
		SeqVMOSTask.SelectTaskType(driver, taskType);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);
		SeqVMOSTask.SkipOneCheck(driver);
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		LTE_Seq_VMOS_Page.clickTemplateDownBTN(driver);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, "Factor_Resolution.csv");
	}

	@Ignore
	@Test(GT3Kid = "新建任务.187")
	public void T002_DowmTemplate() {
		LOG.info("下载任务报告验证");
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);
		SeqVMOSTask.checkCreateNewTaskSkip(driver);
		LTE_Seq_VMOS_Page.setTaskName(driver, "视频流量预测模板下载验证02");
		String[] taskType = { LTE_VMOS_Const.TaskType_SeqAssistant };
		SeqVMOSTask.SelectTaskType(driver, taskType);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);
		SeqVMOSTask.SkipOneCheck(driver);
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		LTE_Seq_VMOS_Page.clickTemplateDownBTN(driver);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, "Factor_Resolution.csv");
	}

}
