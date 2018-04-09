package cloud_platform.testcase.platformCommon.projectmng.notdelete;

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

import cloud_platform.task.platformcommon.projectmng.AwProjectManager;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.parameter.platform.ProjectManager_ZH_EN;
import common.parameter.userinfo.DomainUser;
import common.util.CommonWD;
import common.util.LOG;

@GUITest
@RunWith(GUITestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectManagement_TS_1 {
	/*
	 * public static final String ChinaMobileKunMing = "中国云南昆明移动LTE工程优化自动化项目";
	 * public static final String ChinaTelecomShaoXing = "中国浙江绍兴电信LTE网络规划自动化项目";
	 * public static final String ChinaMobileUMTSBuoZhou =
	 * "中国安徽亳州移动UMTS自动化测试项目"; public static final String ChinaMobileGSMBuoZhou =
	 * "中国安徽亳州移动GSM自动化测试项目";
	 * 
	 * public static final String ChinaMobileGuangXi = "中国浙江绍兴电信LTE网络规划自动化项目";
	 * 
	 * public static final String ChinaUnicomDaLian = "中国辽宁大连联通LTE基础优化自动化项目";
	 * public static final String ChinaMobileSuZhou = "中国安徽宿州移动LTE001基础优化自动化工程";
	 */

	String projectNameLabel = "工程优化自动化项目";
	String projectName = "中国云南昆明移动LTE工程优化自动化项目";
	String region = "中国区";
	String agent = "中国云南";
	String operator = "昆明移动";
	String rat = "LTE";

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login_account_pwd(driver, DomainUser.USER_NORMAL_12, DomainUser.USER_COMMON_PASSWORD);
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

	//
	@Test(GT3Kid = "")
	public void test_ProjectManagement_Case1() {
		LOG.info_testcase("删除项目，一般不用");//
		AwProjectManager.openProjectManagePage(driver);// 进入项目管理界面
		// 删除项目
		boolean flag = AwProjectManager.isExistProject(driver, projectName);
		if (flag) {
			// 如果存在，删除项目
			// 不删
		} else {
			// 如果不存在，创建项目
			LOG.info_testcase("创建项目");//
			AwProjectManager.openProjectManagePage(driver);// 进入项目管理界面
			// 创建RF项目
			AwProjectManager.creatProject(driver, projectNameLabel, region, agent, operator, rat,
					"D:\\NACWebAutoTest\\data\\data_proof\\test.docx", true);
			// 验证添加成功
			AwProjectManager.verifyPrjAddSuccess(driver, projectName);
		}

	}

	//
	@Test(GT3Kid = "")
	public void test_ProjectManagement_Case3() {
		LOG.info_testcase("导入用户");//
		AwProjectManager.openProjectManagePage(driver);// 进入项目管理界面
		//
		AwProjectManager.Search(driver, projectName);
		//
		AwProjectManager.openProjectPage(driver, projectName);
		//
		AwProjectManager.openUserManagePage(driver);
		String path2 = "D:\\NACWebAutoTest\\doc\\用户模板\\PEAC用户导入模板_插件.xlsx";
		// 批量导入用户
		AwProjectManager.bathImportUser(driver, path2, true);

	}

	//
	@Test(GT3Kid = "")
	public void test_ProjectManagement_Case4() {
		LOG.info_testcase("修改合作方人员角色权限");//
		AwProjectManager.openProjectManagePage(driver);// 进入项目管理界面
		//
		AwProjectManager.Search(driver, projectName);
		//
		AwProjectManager.openProjectPage(driver, projectName);
		//
		AwProjectManager.openRoleManagePage(driver);
		//
		AwProjectManager.modifyRole_selectAll(driver, ProjectManager_ZH_EN.get_Partner_Employee_Permission());
		//
		AwProjectManager.modifyRole_selectAll(driver, ProjectManager_ZH_EN.get_Huawei_Employee_Permission());

	}

}
