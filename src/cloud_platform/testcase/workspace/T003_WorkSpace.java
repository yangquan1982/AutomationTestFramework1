package cloud_platform.testcase.workspace;


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

import cloud_platform.task.platform.WorkSpaceTask;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.parameter.Account;
import common.util.CommonWD;
import common.util.SwitchDriver;
import common.util.ZIP;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T003_WorkSpace {
	
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	
	private static String paramPath = ConstUrl.getProjectHomePath()+ "\\Data\\baseline\\00_Platform\\UserInfor.xlsx";
	private static String ProjectName = "中国北京北京电信LTE个人空间权限审批项目";
	private static Account account1 = new Account(paramPath,"功能测试人员分配","项目成员成员申请人");
	private static Account account2 = new Account(paramPath,"功能测试人员分配","项目经理审批人");
	

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
	}
	@AfterClass
	public static void tearDownAfterClass()  {
		//driver.quit();
	}

	
	
	
	
	/**
	 *  再次申请加入项目，提示已经是项目成员
	 */
	@Test(GT3Kid = "")
	public void T03_workspace() {	
		String applyreason = "Reason_"+ZIP.GetTime("yyMMddHHmmss");//申请原因
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		HomeTask.JumpToPersonalWorkspace(driver);
		WorkSpaceTask.applyJoinProject(driver, account2.getAccount(), ProjectName, applyreason,false);
	}
	
}

