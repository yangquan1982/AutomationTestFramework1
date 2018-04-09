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

import com.huawei.hutaf.webtest.htmlaw.BasicAction;

import cloud_plugin.page.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlanPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlan;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_rankupplan_fileChoiceErrorMsg_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static String testcaseId = "";
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_Rankupplan";
	private static String UelogCdrFile = FilePath + "\\CommonFile\\Chrysan您好hemum.zip";
	public static String ExBaseErrMsg = "请选择csv文件上传";
	public static String ExBaseErrMsg1 = "请选择xlsx文件上传";

	private static String[] CDR = { "cdr" };
	private static String[] allchoose = { "全选" };

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

	// 导入非csv文件 以及非xlsx文件 参数设置取消按钮功能
	@Test(GT3Kid = "")
	public void T012_LTE_fileChooseErrorMsg() {
		LOG.info_testcase("验证点：导入非CSV文件，提示信息的正确性");
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		NetworkAnalysisCenterTask.openLTERankUpPlan(driver);
		// ***点击创建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		GetDataByTypeTask.chooseFolder(driver, CDR, RankUpPlanPage.BtnCDRFDD, defaultWindowID);
		// 参数设置
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, RankUpPlanPage.BtnParameterSet, true);
		RankUpPlan.wait_elementshowok(driver, RankUpPlanPage.Btnmask, true);
		// 点击 取消 按钮
		CommonJQ.click(driver, RankUpPlanPage.BtnCancelSet, true);
		// 参数设置
		CommonJQ.click(driver, RankUpPlanPage.BtnParameterSet, true);
		RankUpPlan.wait_elementshowok(driver, RankUpPlanPage.Btnmask, true);
		// 选择文件 非法时提示信息
		CommonJQ.click(driver, RankUpPlanPage.BtnfieldMappingFileData, true);
		CommonFile.ChooseOneFile(UelogCdrFile);
		RankUpPlan.check_showtext(driver, RankUpPlanPage.fileChoiceErrorMsg, "", 1, ExBaseErrMsg);
		// 选择增长因子文件 非法时提示信息
		CommonJQ.click(driver, RankUpPlanPage.LinkDatayuchuli, true, 1);
		BasicAction.sleep(3);
		CommonJQ.click(driver, RankUpPlanPage.SingChoosezengzhangfile, true);
		CommonJQ.click(driver, RankUpPlanPage.Btnzengzhangfile, true);
		CommonFile.ChooseOneFile(UelogCdrFile);
		RankUpPlan.check_showtext(driver, RankUpPlanPage.fileChoiceErrorMsg, "", 0, ExBaseErrMsg1);
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
		testcaseId = "";
	}
}
