package cloud_platform.testcase.platformCommon.performancestabilitytest.commonoper;

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
import org.openqa.selenium.WebDriver;

import cloud_public.page.GetDataByTypePage;
import cloud_public.page.IndexPage;
import cloud_public.task.IndexTask;
import cloud_public.task.LoginTask;
import common.parameter.userinfo.DomainUser;
import common.util.CommonWD;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(GUITestRunner.class)
public class commonoper_switchproject {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	// 用例执行 126秒
	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login_account_pwd(driver, DomainUser.USER_NORMAL_39, DomainUser.USER_COMMON_PASSWORD2);
	}

	@Before
	public void setUp() {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	/**
	 * 在网络性能分析中心切换项目
	 */
	@Test
	public void test_switchProject() {
		String PrjName1 = "中国安徽宿州移动LTE性能稳定性切换自动化项目"; //
		String PrjName2 = "中国云南昆明移动LTE性能稳定性测试自动化项目";
		//
		IndexPage.OpenNetPerfomanceAnalysisCenter(driver);
		// 选择工程
		IndexTask.changePrj(driver, PrjName1);
		Pause.pause(1000);
		IndexTask.changePrj(driver, PrjName2);
		Pause.pause(1000);
	}

}
