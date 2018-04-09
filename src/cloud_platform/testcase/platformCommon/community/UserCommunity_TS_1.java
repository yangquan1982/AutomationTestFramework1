package cloud_platform.testcase.platformCommon.community;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_public.task.LoginTask;
import common.parameter.userinfo.DomainUser;
import common.util.CommonWD;
import common.util.LOG;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCommunity_TS_1 {
	String title = "按钮覆盖验证";
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login_account_pwd(driver, DomainUser.USER_NORMAL_7, DomainUser.USER_COMMON_PASSWORD);
	}

	@Before
	public void setUp() {
		// LOG.info_testcase("跳转至用户社区界面。");
		// GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T001_L_CancleTask() {
		LOG.info_testcase("如果案例“用户社区功能验证”存在 ，则删除");
		// AWUsercommunity.searchHelp(driver, title);
		// AWUsercommunity.isExsitAndDel(driver, title);

	}

	@Test
	public void test_1() {
		LOG.info_testcase("【用户社区】发布案例");
		// AWUsercommunity.createHelp(driver,UserCommunity_ZH_EN.Problem_Feedback,
		// "用户社区功能验证", "用户社区内容", null,
		// "D:\\NACWebAutoTest\\doc\\用户模板\\PEAC用户导入模板_插件.xlsx", true);
		// AWUsercommunity.searchHelp(driver,"用户社区功能验证");
		// ASSERT.assertTextPresent(driver,"发布求助", "用户社区功能验证");
	}

	// @Test
	public void test_2() {
		LOG.info_testcase("【用户社区】自己删除自己的回复");
		// AWUsercommunity.searchHelp(driver,"用户社区功能验证");
		// CommunityPage page = CommonObject.communitypage;
		// AWUsercommunity.waitforPageload(driver);
		// AWUsercommunity.searchHelp(driver,"用户社区功能验证");
		// Integer[] reply = AWUsercommunity.getreplayNum(driver,"用户社区功能验证");
		// AWUsercommunity.replyHelp(driver,"用户社区功能验证", "我回复的内容", true);
		// page.cancelReply(driver);
		// AWUsercommunity.searchHelp(driver,"用户社区功能验证");
		// Integer[] reply1 = AWUsercommunity.getreplayNum(driver,"用户社区功能验证");
		// Assert.assertTrue((reply[0] + 1) == (reply1[0]));
		// Assert.assertTrue((reply[1] + 1) == (reply1[1]));
		// page.clickTaskByName(driver,"用户社区功能验证");
		// AWUsercommunity.deleteRep(driver,true);

		// 验证回复已经被删除
		// String replycontent = (String) TestWebDriver
		// .executeScript("return $('div.reply_content').text()");
		// Assert.assertTrue(!replycontent.contains("我回复的内容"));
	}
	//
	// /*
	// * 置顶和取消置顶
	// * */
	// @Test(timeout = 300000)
	// public void test_3() {
	// LOG.info_testcase("【用户社区】置顶和取消置顶");
	// AWUsercommunity.searchHelp("用户社区功能验证");
	// AWUsercommunity.waitforPageload();
	// AWUsercommunity.topHelp("用户社区功能验证", true);
	// Assert.assertTrue("置顶验证", AWUsercommunity.isHelpToped("用户社区功能验证"));
	// AWUsercommunity.cancelTopHelp("用户社区功能验证", true);
	// Assert.assertFalse("取消置顶验证", AWUsercommunity.isHelpToped("用户社区功能验证"));
	// }
	//
	// @Test(timeout = 300000)
	// public void test_4() {
	// LOG.info_testcase("【用户社区】点赞和分享");
	// UsercommunityPage page = CommonObject.usercommunitypage;
	// AWUsercommunity.searchHelp("用户社区功能验证");
	// AWUsercommunity.waitforPageload();
	// Assert.assertTrue("点赞验证",
	// AWUsercommunity.clickGoodAndcompear("用户社区功能验证"));
	// /*
	// * AWUsercommunity.searchHelp("用户社区功能验证");
	// * AWUsercommunity.waitforPageload(); int share_num1 =
	// * page.getShareNum("用户社区功能验证"); page.clickTaskByName("用户社区功能验证"); int
	// * num = page.getshareText();
	// * AWUsercommunity.shareHelp("test@huawei.com;test2@huawei.com", null,
	// * true); Assert.assertTrue((num + 1) == page.getshareText()); //
	// * page.clickShareCancelbtn(); page.cancelReply();
	// * AWUsercommunity.waitforPageload();
	// * AWUsercommunity.searchHelp("用户社区功能验证"); Assert.assertTrue((share_num1
	// * + 1) == page.getShareNum("用户社区功能验证"));
	// */
	// }
	//
	// @Test(timeout = 300000)
	// public void test_5() {
	//
	// LOG.info_testcase("【用户社区】系统管理员处理附件");
	//
	// AwLogin.loginOutNotIndex();
	// AwLogin.loginSystem(BasicDataUser.USER_NORMAL_5,
	// BasicDataUser.USER_NORMAL_5_PASSWORD);
	// AwMain.intoMyWorkerSpace();
	// Pause.pause(3000);
	// AwMyWorker.goToExamine(Sharing_ZH_EN.My_To_Do,
	// Sharing_ZH_EN.Attachments_to_Be_Approved);
	// AwMyWorker.waitForPageLoad();
	// AwMyWorker.intotask("用户社区功能验证");
	// AWUsercommunity.passexamine();
	//
	// ASSERT.assertFrameTextNotPresent("", "用户社区功能验证", "centoriframe");
	//
	// }
	//
	// @Test(timeout = 300000)
	// public void test_5_1() {
	// AW_FileHandle.doRemovePath(new File(CommonObject.downloadPath));
	// LOG.info_testcase("【用户社区】系统管理员处理附件");
	// UsercommunityPage page = CommonObject.usercommunitypage;
	// AwtaskManager_1.gotousercommunity();
	// page.clickTaskByName("用户社区功能验证");
	// AwCommon.waitForPagLoad();
	// AwSharingCenter.DownloadBath("PEAC用户导入模板_插件.xlsx",
	// CommonObject.downloadPath + "用户社区PEAC用户导入模板_平台.xlsx");
	// Pause.pause(3000);
	// boolean flag2 = AW_ExcelCheck.fileCompare(
	// "D:\\NACWebAutoTest\\doc\\用户模板\\PEAC用户导入模板_插件.xlsx",
	// CommonObject.downloadPath + "\\PEAC用户导入模板_插件.xlsx");
	// Assert.assertTrue(flag2);
	// }
	//
	// @Test(timeout = 300000)
	// public void test_6() {
	// LOG.info_testcase("【用户社区】编辑“用户社区功能验证”案例");
	// UsercommunityPage page = CommonObject.usercommunitypage;
	// AWUsercommunity.searchHelp("用户社区功能验证");
	// AWUsercommunity.waitforPageload();
	// AWUsercommunity.ModifyHelp("用户社区功能验证", null, "用户社区功能验证", "编辑后的内容",
	// null, true);
	// AWUsercommunity.searchHelp("用户社区功能验证");
	// AWUsercommunity.waitforPageload();
	// Pause.pause(2000);
	// page.clickTaskByName("用户社区功能验证");
	// AWUsercommunity.waitforPageload();
	// ASSERT.assertTextPresent("编辑功能验证", "编辑后的内容");
	// }
	//
	// // @Test(timeout = 300000)
	// public void test_7() {
	// LOG.info_testcase("【用户社区】用户社区功能验证");
	// AWUsercommunity.searchHelp("用户社区功能验证");
	// AWUsercommunity.waitforPageload();
	// AWUsercommunity.deleteHelp("用户社区功能验证", true);
	// ASSERT.assertTextNotPresent("删除取消验证", "用户社区功能验证");
	// }
	//
	// // @Test(timeout = 300000)
	// public void test_8() {
	// LOG.info_testcase("【用户社区】删除：用户社区功能验证");
	// AwLogin.loginOutNotIndex();
	// AwLogin.loginSystem(BasicDataUser.USER_NORMAL_3,
	// BasicDataUser.USER_NORMAL_3_PASSWORD);
	// AwtaskManager_1.gotousercommunity();
	// AWUsercommunity.searchHelp("用户社区功能验证");
	// AWUsercommunity.waitforPageload();
	// AWUsercommunity.deleteHelp("用户社区功能验证", true);
	// ASSERT.assertTextNotPresent("删除取消验证", "用户社区功能验证");
	// }

}
