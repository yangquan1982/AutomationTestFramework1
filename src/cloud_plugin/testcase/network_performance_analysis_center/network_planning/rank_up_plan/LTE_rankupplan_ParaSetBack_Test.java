package cloud_plugin.testcase.network_performance_analysis_center.network_planning.rank_up_plan;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.junit.v4_5.runner.GUITestRunner;
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
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlanPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlan;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.SwitchDriver;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_rankupplan_ParaSetBack_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static String testcaseId = "";
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String[] CDR = { "cdr" };
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_Rankupplan";

	private static String zengzhangfile = FilePath + "\\CommonFile\\Factor1.csv";

	private static String taskName_T010 = "话统数据预处理修改参数后恢复默认值";

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

	@Test(GT3Kid = "")
	public void T012_LTE_ParaSetBack() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		NetworkAnalysisCenterTask.openLTERankUpPlan(driver);
		// ***点击创建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// ***任务名称输入
		String taskName = RankUpPlanPage.setTaskName(driver, taskName_T010);
		// ***参数设置
		GetDataByTypeTask.chooseFolder(driver, CDR, RankUpPlanPage.BtnCDRFDD, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		RankUpPlan.ParameterSet(driver, true, null, null, false, null, null, null, null, null, "2", zengzhangfile,
				false, null);
		RankUpPlanPage.BtnRecover(driver);
		RankUpPlanPage.BtnOkSet(driver);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
		testcaseId = "";
	}
}
