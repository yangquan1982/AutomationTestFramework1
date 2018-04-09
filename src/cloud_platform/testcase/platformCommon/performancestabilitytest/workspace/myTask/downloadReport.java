package cloud_platform.testcase.platformCommon.performancestabilitytest.workspace.myTask;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.junit.v4_5.runner.GUITestRunner;
import org.fest.swing.timing.Pause;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.platformcommon.projectmng.AwtaskManager_1;
import cloud_platform.task.platformcommon.workspace.Aw_PersonalWorkspace;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.parameter.userinfo.DomainUser;
import common.util.CommonWD;
import common.util.FileHandle;
import common.util.LOG;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(GUITestRunner.class)
public class downloadReport {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login_account_pwd(driver, DomainUser.USER_NORMAL_39, DomainUser.USER_COMMON_PASSWORD2);

	}

	@Before
	public void setUp() throws Exception {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		AwtaskManager_1.intoMyWorkerSpace(driver);
	}

	@After
	public void tearDown() throws Exception {
		LOG.info_testcase("test end");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	/*
	 * 1、弹出下载框，点击保存后，报告被下载 2、打开下载包，里面的任务报告正常， 执行时间 3分6秒
	 */
	@Test()
	public void test_1() throws Exception {
		LOG.info_testcase("1、弹出下载框，点击保存后，报告被下载  2、打开下载包，里面的任务报告正常");

		// 根据 iframe 的src属性判断 iframe是否存在,如需要判断，则再加上
		Aw_PersonalWorkspace.filterTaskStatus(driver, "分析成功");
		// 每页设置显示10条任务
		Aw_PersonalWorkspace.setTaskNo(driver, "10");
		// 选择当前页全部任务 10个(如果少于10个，实际是几个就选几个)
		((JavascriptExecutor) driver)
				.executeScript("$('iframe').contents().find('div.x-grid-row-checker').eq(0).click()");
		((JavascriptExecutor) driver)
				.executeScript("$('iframe').contents().find('div.x-grid-row-checker').eq(1).click()");
		((JavascriptExecutor) driver)
				.executeScript("$('iframe').contents().find('div.x-grid-row-checker').eq(2).click()");
		((JavascriptExecutor) driver)
				.executeScript("$('iframe').contents().find('div.x-grid-row-checker').eq(3).click()");
		((JavascriptExecutor) driver)
				.executeScript("$('iframe').contents().find('div.x-grid-row-checker').eq(4).click()");

		Pause.pause(1000);

		// 点击批量下载报告按钮
		String path_r = ConstUrl.getProjectHomePath() + "\\Data\\temp";
		FileHandle.delSubFile(path_r);
		//
		((JavascriptExecutor) driver)
				.executeScript("$('iframe#centoriframe').contents().find('span#downLoadReportsSpan').click()");
		LOG.info_testcase("开始下载");
		Pause.pause(60000 * 3);
		LOG.info_testcase("等待3分钟，用例结束");

	}

}
