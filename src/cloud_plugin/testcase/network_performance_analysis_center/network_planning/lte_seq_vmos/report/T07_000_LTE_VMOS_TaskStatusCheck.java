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
import common.util.ZIP;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T07_000_LTE_VMOS_TaskStatusCheck {
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

	// 任务状态栏规格验证
	@Test(GT3Kid = "状态栏.001")
	public void T001_checkTaskStatus() {
		LOG.info("任务状态栏显示检查");
		Seq_VMOS_ManagePage.clickTaskSelectBtN(driver, 0);
		ArrayList<String> TaskStatus = LTE_Seq_VMOS_Page.getTaskStatusItems(driver);

		if (!TaskStatus.contains(LTE_VMOS_Const.AllTask))
			Assert.fail("状态栏无" + LTE_VMOS_Const.AllTask + "选项可选择！！");
		if (!TaskStatus.contains(LTE_VMOS_Const.WaitForAnalysis))
			Assert.fail("状态栏无" + LTE_VMOS_Const.WaitForAnalysis + "选项可选择！！");
		if (!TaskStatus.contains(LTE_VMOS_Const.Analysising))
			Assert.fail("状态栏无" + LTE_VMOS_Const.Analysising + "选项可选择！！");
		if (!TaskStatus.contains(LTE_VMOS_Const.AnalysisSuccess))
			Assert.fail("状态栏无" + LTE_VMOS_Const.AnalysisSuccess + "选项可选择！！");
		if (!TaskStatus.contains(LTE_VMOS_Const.AnalysisFail))
			Assert.fail("状态栏无" + LTE_VMOS_Const.AnalysisFail + "选项可选择！！");
	}

	// 任务全选验证
	@Test(GT3Kid = "状态栏.002")
	public void T002_checkAllTaskStatus() {
		LOG.info("检查选择全部任务");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AllTask);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择全部，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskStatus(driver);

		if (TaskStatusS.contains(LTE_VMOS_Const.AllTask))
			Assert.fail("全选择任务下,存在-" + LTE_VMOS_Const.AllTask + "-状态类型的任务！！");

	}

	// 检查选择系统待分析任务
	@Test(GT3Kid = "状态栏.003")
	public void T003_checkAllTaskStatus() {
		LOG.info("检查选择系统待分析任务");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.WaitForAnalysis);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.WaitForAnalysis + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.WaitForAnalysis)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.WaitForAnalysis + ",任务状态存在" + string + "类型任务！！");
			}
		}
	}

	// 选择任务分析中任务验证
	@Test(GT3Kid = "状态栏.004")
	public void T004_checkAllTaskStatus() {
		LOG.info("检查选择" + LTE_VMOS_Const.Analysising + "任务");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.Analysising);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.Analysising + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.Analysising)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.Analysising + ",任务状态存在" + string + "类型任务！！");
			}
		}

	}

	// 任务选择分析成功任务验证
	@Test(GT3Kid = "状态栏.005")
	public void T005_checkAllTaskStatus() {
		LOG.info("检查选择" + LTE_VMOS_Const.AnalysisSuccess + "任务");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisSuccess);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.AnalysisSuccess + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.AnalysisSuccess)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.AnalysisSuccess + ",任务状态存在" + string + "类型任务！！");
			}
		}
	}

	// 任务选择任务分析失败用例验证
	@Test(GT3Kid = "状态栏.006")
	public void T006_checkAllTaskStatus() {
		LOG.info("检查选择" + LTE_VMOS_Const.AnalysisFail + "任务");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisFail);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.AnalysisFail + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.AnalysisFail)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.AnalysisFail + ",任务状态存在" + string + "类型任务！！");
			}
		}
	}

	// 专家任务状态任务求和验证
	@Test(GT3Kid = "状态栏.007")
	public void T007_checkTaskStatusSum() {
		LOG.info("任务状态任务总和件查");
		int expectNumber = Seq_VMOS_ManagePage.getAllTaskNum(driver);
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.WaitForAnalysis);
		int WaitForAnalysis = Seq_VMOS_ManagePage.getTaskNum(driver);
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.Analysising);
		int Analysising = Seq_VMOS_ManagePage.getTaskNum(driver);
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisSuccess);
		int AnalysisSuccess = Seq_VMOS_ManagePage.getTaskNum(driver);
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AnalysisFail);
		int AnalysisFail = Seq_VMOS_ManagePage.getTaskNum(driver);
		int sum = WaitForAnalysis + Analysising + AnalysisSuccess + AnalysisFail;
		if (expectNumber != sum) {
			Assert.fail("任务总数：" + expectNumber + "专家状态各任务总数：" + sum);
		}
	}

	// 专家求助状态规格检查
	@Test(GT3Kid = "状态栏.008")
	public void T008_checkTaskExpertStatus() {
		LOG.info("任务状态栏显示检查");
		Seq_VMOS_ManagePage.clickTaskSelectBtN(driver, 1);
		ArrayList<String> ExpertStatus = LTE_Seq_VMOS_Page.getTaskExpertStatusItems(driver);

		if (!ExpertStatus.contains(LTE_VMOS_Const.AllTask))
			Assert.fail("专家求助状态栏无" + LTE_VMOS_Const.AllTask + "选项可选择！！");
		if (!ExpertStatus.contains(LTE_VMOS_Const.NotAskHelp))
			Assert.fail("专家求助状态栏无" + LTE_VMOS_Const.NotAskHelp + "选项可选择！！");
		if (!ExpertStatus.contains(LTE_VMOS_Const.ExpertFeedBack))
			Assert.fail("专家求助状态栏无" + LTE_VMOS_Const.ExpertFeedBack + "选项可选择！！");
		if (!ExpertStatus.contains(LTE_VMOS_Const.AddData))
			Assert.fail("专家求助状态栏无" + LTE_VMOS_Const.AddData + "选项可选择！！");
		if (!ExpertStatus.contains(LTE_VMOS_Const.UserConfirmation))
			Assert.fail("专家求助状态栏无" + LTE_VMOS_Const.UserConfirmation + "选项可选择！！");
		if (!ExpertStatus.contains(LTE_VMOS_Const.ProcessIsComplete))
			Assert.fail("专家求助状态栏无" + LTE_VMOS_Const.ProcessIsComplete + "选项可选择！！");
	}

	// 任务全选验证
	@Test(GT3Kid = "状态栏.009")
	public void T009_checkAllTaskExpertStatus() {
		LOG.info("检查选择全部任务");
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.AllTask);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境专家 状态选择全部，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskExpertStatus(driver);

		if (TaskStatusS.contains(LTE_VMOS_Const.AllTask))
			Assert.fail("专家状态全选择任务下,存在-" + LTE_VMOS_Const.AllTask + "-状态类型的任务！！");
	}

	// 检查选择尚未发起求助任务
	@Test(GT3Kid = "状态栏.010")
	public void T010_checkTaskNotAskHelpStatus() {
		LOG.info("检查选择尚未发起求助任务");
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.NotAskHelp);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.NotAskHelp + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskExpertStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.NotAskHelp)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.NotAskHelp + ",专家求助状态存在" + string + "类型任务！！");
			}
		}
	}

	// 检查选择专家反馈任务
	@Test(GT3Kid = "状态栏.011")
	public void T011_checkTaskExpertFeedBackStatus() {
		LOG.info("检查选择专家反馈任务");
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.ExpertFeedBack);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.ExpertFeedBack + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskExpertStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.ExpertFeedBack)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.ExpertFeedBack + ",专家求助状态存在" + string + "类型任务！！");
			}
		}
	}

	// 检查选择数据追加任务
	@Test(GT3Kid = "状态栏.012")
	public void T012_checkTaskAddDataStatus() {
		LOG.info("检查选择数据追加任务");
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.AddData);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.AddData + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskExpertStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.AddData)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.AddData + ",专家求助状态存在" + string + "类型任务！！");
			}
		}
	}

	// 检查选择用户确认任务
	@Test(GT3Kid = "状态栏.013")
	public void T013_checkTaskUserConfirmationStatus() {
		LOG.info("检查选择用户确认任务");
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.UserConfirmation);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.UserConfirmation + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskExpertStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.UserConfirmation)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.UserConfirmation + ",专家求助状态存在" + string + "类型任务！！");
			}
		}
	}

	// 检查选择流程结束任务
	@Test(GT3Kid = "状态栏.014")
	public void T014_checkTaskProcessIsCompleteStatus() {
		LOG.info("检查选择流程结束任务");
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.ProcessIsComplete);
		if (Seq_VMOS_ManagePage.getTaskNum(driver) == 0) {
			Assert.fail("当前环境选择" + LTE_VMOS_Const.ProcessIsComplete + "，无任务！！");
		}
		ArrayList<String> TaskStatusS = Seq_VMOS_ManagePage.getAllTaskExpertStatus(driver);
		for (String string : TaskStatusS) {
			if (!string.equals(LTE_VMOS_Const.ProcessIsComplete)) {
				Assert.fail("当前环境选择" + LTE_VMOS_Const.ProcessIsComplete + ",专家求助状态存在" + string + "类型任务！！");
			}
		}
	}

	// 专家任务状态任务求和验证
	@Test(GT3Kid = "状态栏.015")
	public void T015_checkTaskExpertStatusSum() {
		LOG.info("专家状态栏任务总和检查");
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.ProcessIsComplete);
		int ProcessIsComplete = Seq_VMOS_ManagePage.getTaskNum(driver);
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.UserConfirmation);
		int UserConfirmation = Seq_VMOS_ManagePage.getTaskNum(driver);
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.AddData);
		int AddData = Seq_VMOS_ManagePage.getTaskNum(driver);
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.ExpertFeedBack);
		int ExpertFeedBack = Seq_VMOS_ManagePage.getTaskNum(driver);
		Seq_VMOS_ManagePage.selectTaskByExpertStatus(driver, LTE_VMOS_Const.NotAskHelp);
		int NotAskHelp = Seq_VMOS_ManagePage.getTaskNum(driver);
		int sum = ProcessIsComplete + UserConfirmation + AddData + ExpertFeedBack + NotAskHelp;
		int expectNumber = Seq_VMOS_ManagePage.getAllTaskNum(driver);
		if (expectNumber != sum) {
			Assert.fail("任务总数：" + expectNumber + "专家状态各任务总数：" + sum);
		}
	}

	// 搜索功能验证
	@Test(GT3Kid = "状态栏.021,状态栏.022,状态栏.023,状态栏.024")
	public void T020_checkTaskSearch() {
		LOG.info("任务搜索验证");
		Seq_VMOS_ManagePage.selectTaskByStatus(driver, LTE_VMOS_Const.AllTask);
		int sum = Seq_VMOS_ManagePage.getTaskNum(driver);
		if (sum == 0) {
			Assert.fail("当前环境无任务");
		}
		String taskid = Seq_VMOS_ManagePage.getTaskId(driver, 0);
		// 精准搜索
		Seq_VMOS_ManagePage.searchTask(driver, taskid);
		Assert.assertTrue("搜做ID为" + taskid + "-搜做结果为不对", Seq_VMOS_ManagePage.getTaskNum(driver) == 1);
		// 非法字符搜索
		Seq_VMOS_ManagePage.searchTask(driver, taskid.replaceFirst("2016", "") + " ");
		Assert.assertTrue("搜索带空格ID字段成功", Seq_VMOS_ManagePage.getTaskNum(driver) == 0);
		// 模糊搜索
		Seq_VMOS_ManagePage.searchTask(driver, ZIP.GetTime("yyyy"));
		Assert.assertTrue("按时间年份搜索模糊搜索失败！", Seq_VMOS_ManagePage.getTaskNum(driver) > 0);
	}

}
