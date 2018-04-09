package cloud_platform.testcase.platformCommon.performancestabilitytest.usercommunity;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.junit.v4_5.runner.GUITestRunner;
import org.fest.swing.timing.Pause;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.community.AWUsercommunity;
import cloud_platform.page.community.UserCommunity_ZH_EN;
import cloud_platform.page.community.Usercommunitypage;
import cloud_platform.task.platformcommon.projectmng.AwtaskManager_1;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.parameter.userinfo.DomainUser;
import common.util.CommonWD;
import common.util.LOG;


@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(GUITestRunner.class)
public class userCommunity{
	String helpTitle = "性能稳定性用户社区功能验证";
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	
	//用例共执行时长50s
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver=  CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login_account_pwd(driver, DomainUser.USER_NORMAL_39, DomainUser.USER_COMMON_PASSWORD2);

	}

	@Before
	public void setUp() throws Exception {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		AwtaskManager_1.gotousercommunity(driver);
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
	 * 1、【用户社区】如果案例“用户社区功能验证”存在 ，则删除
	 * */
	@Test()
	public void test_1() throws Exception {
		LOG.info_testcase("【用户社区】如果案例“用户社区功能验证”存在 ，则删除");
		AWUsercommunity.searchHelp(driver, helpTitle);
		AWUsercommunity.isExsitAndDel(driver, helpTitle);
	}
	
	/*
	 * 1、【用户社区】发布案例
	 * */
	@Test
	public void test_2() {
		LOG.info_testcase("【用户社区】发布案例");
		AWUsercommunity.createHelp(driver, UserCommunity_ZH_EN.Problem_Feedback,
				helpTitle, "用户社区内容", null,
				ConstUrl.getProjectHomePath()+"\\Data\\PEAC用户导入模板_插件.xlsx", true);
		
	}
	
	/*
	 * 1、【用户社区】附件审批
	 * */
	@Test
	public void test_3() {
		LOG.info_testcase("【用户社区】附件审批");
		 //
		LoginTask.loginOutNotIndex(driver);
		Pause.pause(1000);
		LoginTask.login_account_pwd(driver, DomainUser.USER_NORMAL_31, DomainUser.USER_COMMON_PASSWORD2);
		Pause.pause(1000);
		//
		AwtaskManager_1.gotousercommunity(driver);
		//
		AWUsercommunity.searchHelp(driver, helpTitle);
		Usercommunitypage.clickTaskByName(driver, helpTitle);
		//
		((JavascriptExecutor) driver).executeScript("$('a#updateStatusNoPass').get(0).click()");
		Pause.pause(500);LoadingPage.Loading(driver);Pause.pause(1000);
		String info2 = (String)((JavascriptExecutor) driver).executeScript("return $('div#annex').text()");
		LOG.info_testcase("info2:"+info2);
		Assert.assertTrue(info2.contains("取消审批"));
		
	}

		
	
	
   
	

	
}
