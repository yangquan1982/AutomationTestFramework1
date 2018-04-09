package cloud_platform.testcase.upload_data.concurrentupload;

import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.platformcommon.projectmng.AwProjectManager;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.constant.system.SystemConstant;
import common.parameter.platform.ProjectManager_ZH_EN;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class ConcurrentCreateProject {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String user = null;
	private static String pwd = null;

	// "中国云南昆明移动LTE工程优化自动化项目";

	private String pronum;
	private String secns;
	private String region;// 中国区
	private String agent;// 中国云南
	private String operator;// 昆明移动
	private String rat;// LTE
	private String projectNameLabel;// 工程优化自动化项目
	private String uploadstate;// 工程优化自动化项目

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\全量环境";
	private static String ParaFile = SystemConstant.ConcurrentPath + "ConcurrentUpLoadParamTable.xlsx";
	private static String proofile = FilePath + "\\Chrysanthemum.jpg";
	private static String userfile = FilePath + "\\PEAC用户导入模板_数据上传并发.xlsx";

	public ConcurrentCreateProject(String pronum, String secns, String region, String agent, String operator,
			String rat, String projectNameLabel, String uploadstate) {
		super();
		this.pronum = pronum;
		this.secns = secns;
		this.region = region;
		this.agent = agent;
		this.operator = operator;
		this.rat = rat;
		this.projectNameLabel = projectNameLabel;
		this.uploadstate = uploadstate;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "ProjectInformation"));
		return coll;

	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		user = ParamUtil.getExcelValue(ParaFile, "LoginInformation", 0, 1);
		pwd = ParamUtil.getExcelValue(ParaFile, "LoginInformation", 1, 1);
		LoginTask.login_account_pwd(driver, user, pwd);
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
	public void CreatPrj_NotDel() {

		LOG.info_testcase("场景描述:" + secns);
		String projectName = agent + operator + rat + projectNameLabel;
		String userfile1 = userfile;

		boolean flag = AwProjectManager.isExistProject(driver, projectName);
		if (!flag) {
			LOG.info_testcase("创建RF项目");
			AwProjectManager.creatProject_withOutIntoList(driver, projectNameLabel, region, agent, operator, rat, null,
					proofile, true);
			AwProjectManager.openUserManagePage(driver);
			LOG.info_testcase("批量导入用户");
			AwProjectManager.bathImportUser(driver, userfile1, true);
			AwProjectManager.openRoleManagePage(driver);
			AwProjectManager.modifyRole_selectAll(driver, ProjectManager_ZH_EN.get_Partner_Employee_Permission());
			AwProjectManager.modifyRole_selectAll(driver, ProjectManager_ZH_EN.get_Huawei_Employee_Permission());
			LOG.info_testcase("验证添加成功");
			AwProjectManager.verifyPrjAddSuccess(driver, projectName);
		} else {
			AwProjectManager.openProjectPage(driver, projectName);
			AwProjectManager.openUserManagePage(driver);
			AwProjectManager.bathImportUser(driver, userfile1, true);
			// AwProjectManager.openProjectPage(driver, projectName);
			AwProjectManager.openRoleManagePage(driver);
			AwProjectManager.modifyRole_selectAll(driver, ProjectManager_ZH_EN.get_Partner_Employee_Permission());
			AwProjectManager.modifyRole_selectAll(driver, ProjectManager_ZH_EN.get_Huawei_Employee_Permission());
		}

	}
}
