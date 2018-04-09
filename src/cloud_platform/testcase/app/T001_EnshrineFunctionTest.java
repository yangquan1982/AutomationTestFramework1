package cloud_platform.testcase.app;

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

import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.parameter.Account;
import common.util.CommonWD;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T001_EnshrineFunctionTest {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String paramPath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\00_Platform\\UserInfor.xlsx";
	private static Account account1 = new Account(paramPath, "功能测试人员分配", "APP收藏功能验证01");

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());

	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	// 收藏功能验证
	@Test(GT3Kid = "")
	public void T001_EnshrineCheck() {
		HomeTask.enshrineApp(driver);
	}

	// 取消收藏功能验证
	@Test(GT3Kid = "")
	public void T002_EnshrineCheck() {
		HomeTask.desEnshrineApp(driver);
	}

}
