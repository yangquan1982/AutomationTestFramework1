package cloud_platform.testcase.usercommunity;


import org.fest.swing.annotation.GUITest;
import org.fest.swing.junit.v4_5.runner.GUITestRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.usercommunity.UserCommunityConst;
import cloud_platform.task.platform.UserCommunityTask;
import cloud_platform.task.platform.WorkSpaceTask;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.parameter.Account;
import common.util.CommonWD;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T001_UserCommunity {
	
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	
	private static String paramPath = ConstUrl.getProjectHomePath()+ "\\Data\\baseline\\00_Platform\\UserInfor.xlsx";
	private static String ProjectName = "中国北京北京电信LTE个人空间权限审批项目";
	private static Account account1 = new Account(paramPath,"功能测试人员分配","用户社区普通发帖人1");
	private static Account account2 = new Account(paramPath,"功能测试人员分配","用户社区附件审批人");
	private static Account account3 = new Account(paramPath,"功能测试人员分配","用户社区普通发帖人2");
	

	@BeforeClass
	public static void setUpBeforeClass() {
		driver=  CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();		
	}
	@Before
	public void setUp() {
	}
	@After
	public void tearDown()  {
		SwitchDriver.switchDriverToSEQ(driver);
		HomeTask.logOut(driver);
	}
	@AfterClass
	public static void tearDownAfterClass()  {
		driver.quit();
	}

	
	
	
//	/**
//	 *  测试步骤
//	 *  1   普通用户发带附件的帖子
//	 *  2   管理员在个人空间附件审批 删除附件
//	 *  3 管理员进入用户社区才看看帖子详情无附件
//	 *  3 普通用户进入用户社区查看帖子详情附件被删除
//	 *   
//	 */
//	@Test(GT3Kid = "")
//	public void T01_UserCommunity() {
//		String title = "title"+ZIP.GetTime("yyMMddHHmmss");//主题
//		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
//		HomeTask.JumpToUserCommunity(driver);
//		UserCommunityTask.askHelpOrDiscuss(driver, UserCommunityConst.QuestionFeedBack, title, title, title, UserCommunityConst.AttachPath);
//		UserCommunityTask.checkToppicTop(driver, title, false);
//		HomeTask.logOut(driver);
//		LoginTask.login_account_pwd(driver, account2.getAccount(), account2.getPwd());
//		//
//		HomeTask.JumpToPersonalWorkspace(driver);
//		WorkSpaceTask.approveAttachDelete(driver, title, account1.getAccount());
//		
//		// 进入用户社区-查看帖子是否存在附件
////		HomeTask.JumpToUserCommunity(driver);
////		UserCommunityTask.checkTopicAttach(driver, title, false);
//		//
//		HomeTask.logOut(driver);
//		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
//		HomeTask.JumpToUserCommunity(driver);
//		UserCommunityTask.checkTopicAttach(driver, title, true);
//	}
	
	/**
	 *  测试步骤
	 *  1   普通用户发带附件的帖子
	 *  2   管理员在用户社区 删除附件
	 *  ///////////////////////////////3 管理员在用户社区才看看帖子详情无附件
	 *  3 普通用户进入用户社区查看帖子详情附件被删除
	 *   
	 */
	@Test(GT3Kid = "")
	public void T02_UserCommunity() {
		String title = "title"+ZIP.GetTime("yyMMddHHmmss");//主题
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToUserCommunity(driver);
		LOG.info_testcase("用户社区发帖"+title);
		UserCommunityTask.askHelpOrDiscuss(driver, UserCommunityConst.QuestionFeedBack, title, title, title, UserCommunityConst.AttachPath);
		UserCommunityTask.checkToppicTop(driver, title, false);
		LOG.info_testcase("切换用户， 删除帖子附件");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account2.getAccount(), account2.getPwd());
		HomeTask.JumpToPersonalWorkspace(driver);
		WorkSpaceTask.approveAttachDelete(driver, title, account1.getAccount());		
		LOG.info_testcase("切换用户， 验证附件已经被删除");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToUserCommunity(driver);
		UserCommunityTask.checkTopicAttachDeleted(driver, title);
	}
	
	/**
	 *  测试步骤
	 *  1   普通用户发带附件的帖子
	 *  2   管理员在个人空间看到审批附件贴子
	 *  3  普通用户删除帖子附件
	 *  4  管理员在个人空间看不到审批附件贴子
	 *  5 普通用户重新添加帖子附件成功
	 *  6 管理员在个人空间看到审批附件贴子
	 *   
	 */
	@Test(GT3Kid = "")
	public void T03_UserCommunity() {
		String title = "title"+ZIP.GetTime("yyMMddHHmmss");//主题
		
		//用户登录，发带附件帖子
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToUserCommunity(driver);
		LOG.info_testcase("用户社区发帖"+title);
		UserCommunityTask.askHelpOrDiscuss(driver, UserCommunityConst.QuestionFeedBack, title, title, title, UserCommunityConst.AttachPath);
		UserCommunityTask.checkToppicTop(driver, title, false);
		//切换用户，检查存在主题附件审批
		LOG.info_testcase("切换用户，查看存在审批附件");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account2.getAccount(), account2.getPwd());
		HomeTask.JumpToPersonalWorkspace(driver);
		WorkSpaceTask.approveAttachExistCheck(driver, title,account1.getAccount(), true);
		
		//切账用户，删除发帖附件
		LOG.info_testcase("切换用户，删除帖子附件");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToUserCommunity(driver);		
//		UserCommunityTask.checkTopicAttach(driver, title, true);
		UserCommunityTask.deleteAttach(driver, title);
		HomeTask.JumpToUserCommunity(driver);
		UserCommunityTask.checkTopicAttachDeleted(driver, title);
		// 切换用户，检查不存在发附件审批
		LOG.info_testcase("切换用户,查看不存在审批附件");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account2.getAccount(), account2.getPwd());
		HomeTask.JumpToPersonalWorkspace(driver);
		WorkSpaceTask.approveAttachExistCheck(driver, title,account1.getAccount(), false);
		//切换用户，重新添加发帖附件
		LOG.info_testcase("切换用户,重新添加发帖附件");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToUserCommunity(driver);	
		UserCommunityTask.appendAttach(driver, title,UserCommunityConst.AttachPath);
		// 切换用户，检查存在附件审批
		LOG.info_testcase("切换用户,查看存在审批附件");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account2.getAccount(), account2.getPwd());
		HomeTask.JumpToPersonalWorkspace(driver);
		WorkSpaceTask.approveAttachExistCheck(driver, title,account1.getAccount(), true);		
		
	}
	
	/**
	 *  测试步骤
	 *  1   普通用户发带附件的帖子
	 *  2   管理员审批附件通过
	 *  3  普通用户看看帖子详情，对比附件内容
	 *  4  其他用户登录 看不到茄子附件
	 *   
	 */
	@Test(GT3Kid = "")
	public void T04_UserCommunity() {
		String title = "title"+ZIP.GetTime("yyMMddHHmmss");//主题
		
		//用户登录，发带附件帖子
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToUserCommunity(driver);
		UserCommunityTask.askHelpOrDiscuss(driver, UserCommunityConst.QuestionFeedBack, title, title, title, UserCommunityConst.AttachPath);
		UserCommunityTask.checkToppicTop(driver, title, false);
		LOG.info_testcase("切换用户，附件审批通过");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account2.getAccount(), account2.getPwd());
		HomeTask.JumpToPersonalWorkspace(driver);
		WorkSpaceTask.approveAttachPass(driver, title,account1.getAccount());
		LOG.info_testcase("切账用户，下载附件对比，删除附件");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToUserCommunity(driver);	
		UserCommunityTask.downLoadAttach(driver, title,UserCommunityConst.AttachPath);
		HomeTask.JumpToUserCommunity(driver);
		UserCommunityTask.deleteAttach(driver, title);
		LOG.info_testcase("切换用户，看不到帖子附件");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account3.getAccount(), account3.getPwd());
		HomeTask.JumpToUserCommunity(driver);	
		UserCommunityTask.checkTopicAttach(driver, title, false);
		
	}
	
	/**
	 *  测试步骤
	 *  1   普通用户A发带附件的帖子
	 *  2   管理员审批附件通过，下载附件对比
	 *  3  普通用户B看看帖子详情，对比附件内容
	 *   
	 */
	@Test(GT3Kid = "")
	public void T05_UserCommunity() {
		String title = "title"+ZIP.GetTime("yyMMddHHmmss");//主题
		
		LOG.info_testcase("用户登录，发带附件帖子");
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToUserCommunity(driver);
		UserCommunityTask.askHelpOrDiscuss(driver, UserCommunityConst.QuestionFeedBack, title, title, title, UserCommunityConst.AttachPath);
		UserCommunityTask.checkToppicTop(driver, title, false);
		LOG.info_testcase("切换用户，附件审批通过");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account2.getAccount(), account2.getPwd());
		HomeTask.JumpToPersonalWorkspace(driver);
		WorkSpaceTask.approveAttachPassAndDown(driver, title,account1.getAccount(),UserCommunityConst.AttachPath);
		LOG.info_testcase("切账用户，下载附件对比，删除附件	");
		HomeTask.logOut(driver);
		LoginTask.login_account_pwd(driver, account3.getAccount(), account3.getPwd());
		HomeTask.JumpToUserCommunity(driver);	
		UserCommunityTask.downLoadAttach(driver, title,UserCommunityConst.AttachPath);
		
	}
	
	/**
	 *  测试步骤
	 *  1   普通用户A发带附件的帖子
	 *  2   管理员审批附件通过
	 *  3  管理员取消审批
	 *  4 个人空间看到对应审批附件帖子 
	 */
//	@Test(GT3Kid = "")
//	public void T06_UserCommunity() {
//		String title = "title"+ZIP.GetTime("yyMMddHHmmss");//主题		
//		//用户登录，发带附件帖子
//		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
//		HomeTask.JumpToUserCommunity(driver);
//		UserCommunityTask.askHelpOrDiscuss(driver, UserCommunityConst.QuestionFeedBack, title, title, title, UserCommunityConst.AttachPath);
//		UserCommunityTask.checkToppicTop(driver, title, false);
//		//切换用户，附件审批通过
//		HomeTask.logOut(driver);
//		LoginTask.login_account_pwd(driver, account2.getAccount(), account2.getPwd());
//		HomeTask.JumpToUserCommunity(driver);
//		UserCommunityTask.ApproveApplyAttach(driver, title);
//		HomeTask.JumpToPersonalWorkspace(driver);
//		WorkSpaceTask.approveAttachExistCheck(driver, title, account1.getAccount(), false);		
//		HomeTask.JumpToUserCommunity(driver);
//		UserCommunityTask.cancelApproveApplyAttach(driver, title);
//		
//		HomeTask.JumpToPersonalWorkspace(driver);
//		WorkSpaceTask.approveAttachExistCheck(driver, title, account1.getAccount(), true);
//		
//	}
	
	
}

