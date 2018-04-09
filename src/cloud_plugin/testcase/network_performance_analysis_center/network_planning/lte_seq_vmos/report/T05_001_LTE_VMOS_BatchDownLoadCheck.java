package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_seq_vmos.report;

import org.apache.commons.lang.StringUtils;
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
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonWD;
import common.util.LOG;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T05_001_LTE_VMOS_BatchDownLoadCheck {
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

	@Test(GT3Kid = "批量下载.001")
	public void T001_checkOneSuccessTaskDown() {
		LOG.info("选择一个分析成功任务下载验证");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisSuccess);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择-" + LTE_VMOS_Const.AnalysisSuccess + "，无任务！！");
		}
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 0);

		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		LTE_Seq_VMOS_Page.clickDownLoadReportsBTN(driver);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, "Report.zip");
	}

	@Test(GT3Kid = "批量下载.002")
	public void T002_checkTwoSuccessTaskDown() {
		LOG.info("选择俩个分析成功任务下载验证");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisSuccess);
		int tasknum = Seq_VMOS_ManagePage.getTaskNum(driver);
		if (tasknum == 0) {
			Assert.fail("当前环境选择-" + LTE_VMOS_Const.AnalysisSuccess + "，无任务！！");
		}
		if (tasknum < 2) {
			Assert.fail("当前环境成功任务数量少于2，不能验证选择多个成功任务下载报告！！！");
		}
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 0);
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 1);
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		LTE_Seq_VMOS_Page.clickDownLoadReportsBTN(driver);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, "Report.zip");
	}

	@Test(GT3Kid = "批量下载.003")
	public void T003_checkTenSuccessTaskDown() {
		LOG.info("选择一个分析成功任务下载验证");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisSuccess);
		int tasknum = Seq_VMOS_ManagePage.getTaskNum(driver);
		if (tasknum == 0) {
			Assert.fail("当前环境选择-" + LTE_VMOS_Const.AnalysisSuccess + "，无任务！！");
		}
		if (tasknum < 11) {
			Assert.fail("当前环境成功任务数量不大于10，不能验证选择10个成功任务下载报告！！！");
		}
		Seq_VMOS_ManagePage.clickSelectAllTask(driver);
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		LTE_Seq_VMOS_Page.clickDownLoadReportsBTN(driver);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, "Report.zip");
	}

	@Test(GT3Kid = "批量下载.005")
	public void T005_checkFailTaskDown() {
		LOG.info("选择一个待系统分析任务下载验证");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.WaitForAnalysis);
		int tasknum = Seq_VMOS_ManagePage.getTaskNum(driver);
		if (tasknum == 0) {
			Assert.fail("当前环境选择-" + LTE_VMOS_Const.WaitForAnalysis + "，无任务！！");
		}
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 0);
		LTE_Seq_VMOS_Page.clickDownLoadReportsBTN(driver);

		if (LTE_Seq_VMOS_Page.isTipMessageVisible(driver)) {
			String errorMessage = LTE_Seq_VMOS_Page.getTipMessage(driver);
			LTE_Seq_VMOS_Page.clickTipMessageOK(driver);
			if (!StringUtils.contains(errorMessage, LTE_VMOS_Const.BatchDowmTips)) {
				Assert.fail("提示信息不正确！  当前提示信息：" + errorMessage);
			}
		} else {
			Assert.fail("选择分析失败任务下载,无提示信息！");
		}
	}

	@Test(GT3Kid = "批量下载.006")
	public void T006_checkFailTaskDown() {
		LOG.info("选择一个-系统分析中-任务下载验证");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.Analysising);
		int tasknum = Seq_VMOS_ManagePage.getTaskNum(driver);
		if (tasknum == 0) {
			Assert.fail("当前环境选择-" + LTE_VMOS_Const.Analysising + "，无任务！！");
		}
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 0);
		LTE_Seq_VMOS_Page.clickDownLoadReportsBTN(driver);

		if (LTE_Seq_VMOS_Page.isTipMessageVisible(driver)) {
			String errorMessage = LTE_Seq_VMOS_Page.getTipMessage(driver);
			LTE_Seq_VMOS_Page.clickTipMessageOK(driver);
			if (!StringUtils.contains(errorMessage, LTE_VMOS_Const.BatchDowmTips)) {
				Assert.fail("提示信息不正确！  当前提示信息：" + errorMessage);
			}
		} else {
			Assert.fail("选择分析失败任务下载,无提示信息！");
		}
	}

	@Test(GT3Kid = "批量下载.007")
	public void T007_checkFailTaskDown() {
		LOG.info("选择一个分析失败任务下载验证");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.Analysising);
		int tasknum = Seq_VMOS_ManagePage.getTaskNum(driver);
		if (tasknum == 0) {
			Assert.fail("当前环境选择-" + LTE_VMOS_Const.Analysising + "，无任务！！");
		}
		Seq_VMOS_ManagePage.clickTaskByRow(driver, 0);
		LTE_Seq_VMOS_Page.clickDownLoadReportsBTN(driver);

		if (LTE_Seq_VMOS_Page.isTipMessageVisible(driver)) {
			String errorMessage = LTE_Seq_VMOS_Page.getTipMessage(driver);
			LTE_Seq_VMOS_Page.clickTipMessageOK(driver);
			if (!StringUtils.contains(errorMessage, LTE_VMOS_Const.BatchDowmTips)) {
				Assert.fail("提示信息不正确！  当前提示信息：" + errorMessage);
			}
		} else {
			Assert.fail("选择分析失败任务下载,无提示信息！");
		}
	}

}
