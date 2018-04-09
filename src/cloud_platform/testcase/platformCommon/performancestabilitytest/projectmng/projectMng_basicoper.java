package cloud_platform.testcase.platformCommon.performancestabilitytest.projectmng;

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
import common.constant.constparameter.ConstUrl;
import common.parameter.platform.ProjectManager_ZH_EN;
import common.parameter.userinfo.DomainUser;
import common.util.CommonWD;
import common.util.LOG;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(GUITestRunner.class)
public class projectMng_basicoper {

	String projectNameLabel = "性能稳定性测试自动化项目";
	String projectName = "中国云南昆明移动LTE性能稳定性测试自动化项目";
	String region = "中国区";
	String agent = "中国云南";
	String operator = "昆明移动";
	String rat = "LTE";

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

	//
	@Test(GT3Kid = "")
	public void test_ProjectManagement_Case1() {
		LOG.info_testcase("删除项目，一般不用");//
		AwProjectManager.openProjectManagePage(driver);// 进入项目管理界面
		// 删除项目
		boolean flag = AwProjectManager.isExistProject(driver, projectName);
		if (flag) {
			// 如果存在，删除项目
			AwProjectManager.deleteProjectByName(driver, projectName);
		}
		// 验证删除成功
		AwProjectManager.verifyPrjDelSuccess(driver, projectName);
	}

	//
	@Test(GT3Kid = "")
	public void test_ProjectManagement_Case2() {
		LOG.info_testcase("创建项目");//
		AwProjectManager.openProjectManagePage(driver);// 进入项目管理界面
		// 创建RF项目
		AwProjectManager.creatProject(driver, projectNameLabel, region, agent, operator, rat,
				ConstUrl.getProjectHomePath() + "\\Data\\数据上传凭证\\test.docx", true);
		// 验证添加成功
		AwProjectManager.verifyPrjAddSuccess(driver, projectName);
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
		String path2 = ConstUrl.getProjectHomePath() + "\\Data\\PEAC用户导入模板_插件.xlsx";
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
