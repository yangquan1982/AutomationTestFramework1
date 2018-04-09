package cloud_plugin.testcase.network_performance_analysis_center.network_planning.rank_up_plan;

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

import com.huawei.hutaf.webtest.htmlaw.BasicAction;

import cloud_plugin.page.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlanPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlan;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_rankupplan_Experthelp_DatenullTest {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static String testcaseId = "";
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_Rankupplan";
	private static String ExpertHelpFujian = FilePath + "\\CommonFile\\Chrysan您好hemum.zip";

	private static String taskName_T011 = "默认修改参数";
	private static String[] CDR = { "cdr" };
	private static String[] allchoose = { "全选" };
	private static String HelpStye = "所有类型";

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@Before
	public void setUp() {
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(testcaseId, result);

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	// 专家求助
	@Test(GT3Kid = "")
	public void T011_LTE_StartExpertHelp() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LOG.info("发起专家求助");
		NetworkAnalysisCenterTask.openLTERankUpPlan(driver);
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskName_T011);
		RankUpPlan.ExpertHelp(driver, taskName_T011, HelpStye, taskName_T011, ExpertHelpFujian);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
		testcaseId = "";
	}

	// 新建任务必选数据为空时提示信息
	@Test(GT3Kid = "")
	public void T012_LTE_DateNullMsg() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LOG.info("新建任务必选数据为空时提示信息的验证");
		NetworkAnalysisCenterTask.openLTERankUpPlan(driver);
		// ***点击创建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		GetDataByTypeTask.chooseFolder(driver, CDR, RankUpPlanPage.BtnCDRFDD, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, RankUpPlanPage.BtnParameterSet, true);
		for (int i = 0; i < 120; i++) {
			boolean flage = CommonJQ.isExisted(driver, RankUpPlanPage.Btnmask, true);
			if (flage) {
				BasicAction.sleep(1);
			} else {
				break;
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
		RankUpPlan.ParameterSet(driver, true, null, null, false, allchoose, null, null, null, null, null, null, false,
				null);
		RankUpPlanPage.BtnOkSet(driver);
		RankUpPlanPage.commitBtnClick(driver);
		// 待封装
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		for (int i = 0; i < 4; i++) {
			if (!CommonJQ.isExisted(driver, RankUpPlanPage.ErrorMessage[i], true)) {
				Assert.fail("必须数据为空时期望提示信息有误");
			}
		}
		// 设置TMSS回填结果
		result = true;
		testcaseId = "";
	}
}
