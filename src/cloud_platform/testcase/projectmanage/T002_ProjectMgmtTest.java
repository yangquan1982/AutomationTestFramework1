package cloud_platform.testcase.projectmanage;

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

import cloud_platform.task.platform.ProjectManageTask;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.parameter.Account;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SeleniumUtil;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T002_ProjectMgmtTest {

	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static boolean isDelete = true;// 存在项目时是否删除项目
	private static String ParaFile = ConstUrl.getProjectHomePath()
			+ "\\Data\\baseline\\00_Platform\\ProjectManage\\项目管理参数化.xlsx";// 参数化表路径

	private static String paramPath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\00_Platform\\UserInfor.xlsx";
	private static Account account1 = new Account(paramPath, "功能测试人员分配", "项目管理人员01");
	private String secns;// 场景描述
	private String TestCaseId;
	private String type;// 工程业务领域
	private String region;// 中国区
	private String agent;// 中国云南
	private String operator;// 昆明移动
	private String rat;// LTE
	private String code;// 工程编码
	private String projectNameLabel;// 工程优化自动化项目
	private String userfile;// 批量导入用户文件
	private String GSC_userid;// GSC 接口人账户

	public T002_ProjectMgmtTest(String secns, String testCaseId, String type, String region, String agent,
			String operator, String rat, String code, String projectNameLabel, String userfile, String gSC_userid) {
		this.secns = secns;
		this.TestCaseId = testCaseId;
		this.type = type;
		this.region = region;
		this.agent = agent;
		this.operator = operator;
		this.rat = rat;
		this.code = code;
		this.projectNameLabel = projectNameLabel;
		this.userfile = userfile;
		GSC_userid = gSC_userid;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Traffic"));
		return coll;

	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login_account_pwd(driver, account1.getAccount(), account1.getPwd());
		// LoginTask.loginSys(driver);
	}

	@Before
	public void setUp() {
		result = false;
		HomeTask.JumpToprojectManage(driver, true);
	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(TestCaseId, result);
		SeleniumUtil.killJAVA();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void CreatPrj_NotDel() {
		LOG.info_testcase("场景描述:" + secns);
		ProjectManageTask.paramCreateProject(driver, type, region, agent, operator, projectNameLabel, rat, code,
				userfile, GSC_userid, isDelete);
		// 设置TMSS回填结果
		result = true;

	}
}
