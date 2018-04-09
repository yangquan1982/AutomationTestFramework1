package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_seq_vmos.report;

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
import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.Seq_VMOS_ManagePage;
import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.HomePage;
import cloud_public.page.IndexPage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.LOG;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T04_003_LTE_VMOS_TaskAvailbaleOperaterCheck {
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
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		SeqVMOSTask.goBackToNetworkAnalysisCenter(driver);
		SeqVMOSTask.ChangeProject(driver, LTE_VMOS_Const.VMOS_Project);
		SeqVMOSTask.openPlugin(driver);
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "任务删除.004,批量下载.004,任务重试.001")
	public void T001_checkBTNAvailable() {
		LOG.info("不选择任何任务查看按钮状态！！");
		ArrayList<String> btnText = new ArrayList<String>();
		int num = LTE_Seq_VMOS_Page.getUnavailableBtn(driver);
		for (int i = 0; i < num; i++) {
			String text = LTE_Seq_VMOS_Page.getUnavailableBtnText(driver, i).trim();
			btnText.add(text);

		}
		if (!btnText.contains(LTE_VMOS_Const.Delete))
			Assert.fail("删除按钮不可用检查失败！！");
		if (!btnText.contains(LTE_VMOS_Const.BatchDownLoadReport))
			Assert.fail("批量报告下载按钮不可用检查失败！！");
		if (!btnText.contains(LTE_VMOS_Const.TaskRetry))
			Assert.fail("任务重试按钮不可用检查失败！！");
	}

	@Test(GT3Kid = "任务重试.002")
	public void T002_checkRetryBTNAvailable() {
		LOG.info("验证选择成功任务后界面任务重试按钮不可用！！");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisSuccess);
		int tasknum = Seq_VMOS_ManagePage.getTaskNum(driver);
		if (tasknum == 0) {
			Assert.fail("当前环境选择-" + LTE_VMOS_Const.AnalysisSuccess + "，无任务！！");
		}
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 0);
		ArrayList<String> btnText = new ArrayList<String>();
		int num = LTE_Seq_VMOS_Page.getUnavailableBtn(driver);
		for (int i = 0; i < num; i++) {
			String text = LTE_Seq_VMOS_Page.getUnavailableBtnText(driver, i).trim();
			btnText.add(text);

		}
		if (!btnText.contains(LTE_VMOS_Const.TaskRetry) || btnText.size() == 0)
			Assert.fail("任务重试按钮不可用检查失败！！");
	}

	// 测试失败任务重试
	@Test(GT3Kid = "任务重试.003")
	public void T003_RetryFailTask() {
		LOG.info("测试失败任务重试");
		String id = Seq_VMOS_ManagePage.clickFailureSelect(driver);
		ArrayList<String> btnText = new ArrayList<String>();
		int num = LTE_Seq_VMOS_Page.getAvailableBtn(driver);
		for (int i = 0; i < num; i++) {
			String text = LTE_Seq_VMOS_Page.getAvailableBtnText(driver, i).trim();
			btnText.add(text);

		}
		if (!btnText.contains(LTE_VMOS_Const.TaskRetry) || btnText.size() == 0)
			Assert.fail("任务重试按钮不可用！！");

		Seq_VMOS_ManagePage.clickTaskRebuild(driver, id);
		Seq_VMOS_ManagePage.compartFirstStatue(driver);
	}

	// 测试失败任务重试
	@Test(GT3Kid = "任务重试.004")
	public void T004_RetryTwoFailTask() {
		LOG.info("测试失败任务重试，选择两个失败任务");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisFail);
		int tasknum = Seq_VMOS_ManagePage.getTaskNum(driver);
		if (tasknum == 0) {
			Assert.fail("当前环境选择-" + LTE_VMOS_Const.AnalysisFail + "，无任务！！");
		}
		if (tasknum < 2) {
			Assert.fail("当前环境-" + LTE_VMOS_Const.AnalysisFail + "-任务数量小于2,测试条件不满足！！");
		}
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 0);
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 1);
		LTE_Seq_VMOS_Page.clicktaskRetryBTN(driver);
		for (int i = 0; i < 2; i++) {
			String tempTaskstatus = Seq_VMOS_ManagePage.getTaskStatusByRow(driver, i);
			if (!tempTaskstatus.equals(LTE_VMOS_Const.WaitForAnalysis)) {
				Assert.fail("重试失败任务 任务状态未更改为系统分析中！！");
			}
		}

	}

}
