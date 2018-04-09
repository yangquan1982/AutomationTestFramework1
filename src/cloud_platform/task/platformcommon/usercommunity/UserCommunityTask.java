package cloud_platform.task.platformcommon.usercommunity;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.UserCommunityPage;

public class UserCommunityTask {

	/**
	 * 验证界面分类
	 * 
	 * @param driver
	 */
	public static void pageTypeVerification(WebDriver driver) {
		boolean pageType = UserCommunityPage.pageTypeReturn(driver);
		Assert.assertTrue("用户社区界面分类模块缺少分类", pageType);
	}

	/**
	 * 验证界面按钮
	 * 
	 * @param driver
	 */
	public static void pageButtonVerificationSystem(WebDriver driver) {
		// 发起求助或讨论按钮
		boolean b1 = UserCommunityPage.seekHelpButton(driver);
		Assert.assertTrue("用户社区界面发起求助或讨论按钮不存在", b1);

		// 删除按钮
		boolean b2 = UserCommunityPage.deleteButton(driver);
		Assert.assertTrue("用户社区界面删除按钮不存在", b2);

		// 编辑按钮
		boolean b3 = UserCommunityPage.editButton(driver);
		Assert.assertTrue("用户社区界面编辑按钮不存在", b3);

		// 置顶按钮
		boolean b4 = UserCommunityPage.topButton(driver);
		Assert.assertTrue("用户社区界面置顶按钮不存在", b4);

		// 取消置顶按钮
		boolean b5 = UserCommunityPage.cancelTopButton(driver);
		Assert.assertTrue("用户社区界面取消置顶按钮不存在", b5);

	}

	/**
	 * 验证界面按钮
	 * 
	 * @param driver
	 */
	public static void pageButtonVerificationProject(WebDriver driver) {
		// 发起求助或讨论按钮
		boolean b1 = UserCommunityPage.seekHelpButton(driver);
		Assert.assertTrue("用户社区界面发起求助或讨论按钮不存在", b1);

		// 删除按钮
		boolean b2 = UserCommunityPage.deleteButton(driver);
		Assert.assertTrue("用户社区界面删除按钮不存在", b2);

		// 编辑按钮
		boolean b3 = UserCommunityPage.editButton(driver);
		Assert.assertTrue("用户社区界面编辑按钮不存在", b3);

	}

}
