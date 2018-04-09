package cloud_platform.testcase.common.kuwait_env;

import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
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
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.platformcommon.projectmng.AwProjectManager;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.parameter.platform.ProjectManager_ZH_EN;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class Kuwait_DelPrj_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	// "中国云南昆明移动LTE工程优化自动化项目";
	private String secns;
	private String region;// 中国区
	private String agent;// 中国云南
	private String operator;// 昆明移动
	private String rat;// LTE
	private String projectNameLabel;// 工程优化自动化项目
	private String userfile ;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath()
			+ "\\Data\\科威特环境";
	private static String ParaFile = FilePath + "\\工程创建参数化表.xlsx";
	private static String proofile = FilePath + "\\Chrysanthemum.jpg";

	public Kuwait_DelPrj_Test(String secns, String region, String agent,
			String operator, String rat, String projectNameLabel, String userfile) {
		this.secns = secns;
		this.region = region;
		this.agent = agent;
		this.operator = operator;
		this.rat = rat;
		this.projectNameLabel = projectNameLabel;
		this.userfile = userfile;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		/**
		 * 1.Basefile is empty,Checkfile is not empty 2.Basefile is not
		 * empty,Checkfile is empty 3.Basefile is empty,Checkfile is empty
		 * 4.Para is empty 5.CustParafile is empty
		 */

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Traffic", 0));
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.loginSys(driver);
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

	@Test(GT3Kid = "")
	public void CreatPrj_Del() {
		LOG.info_testcase("场景描述:" + secns);
		
		String projectName = agent + operator + rat + projectNameLabel;
		String userfile1 = FilePath +"\\"+userfile;
		
		// 删除项目
		boolean flag = AwProjectManager.isExistProject(driver, projectName);
		if (flag) {
			// 如果存在，删除项目
			AwProjectManager.deleteProjectByName(driver, projectName);
			// 验证删除成功
			AwProjectManager.verifyPrjDelSuccess(driver, projectName);
		}
		// 创建RF项目
		AwProjectManager.creatProject(driver, projectNameLabel, region, agent,
				operator, rat, proofile, true);
		// 验证添加成功
		AwProjectManager.verifyPrjAddSuccess(driver, projectName);

		//
		AwProjectManager.Search(driver, projectName);
		//
		AwProjectManager.openProjectPage(driver, projectName);
		//
		AwProjectManager.openUserManagePage(driver);
		
		// 批量导入用户
		AwProjectManager.bathImportUser(driver, userfile1, true);

		//
		AwProjectManager.Search(driver, projectName);
		//
		AwProjectManager.openProjectPage(driver, projectName);
		//
		AwProjectManager.openRoleManagePage(driver);
		//
		AwProjectManager.modifyRole_selectAll(driver,
				ProjectManager_ZH_EN.get_Partner_Employee_Permission());
		//
		AwProjectManager.modifyRole_selectAll(driver,
				ProjectManager_ZH_EN.get_Huawei_Employee_Permission());
	}
}
