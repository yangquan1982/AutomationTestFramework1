package cloud_platform.testcase.common.all_env;

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
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class NewParamCreateProject {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	
	private static boolean isDelete = true;// 存在项目时是否删除项目
	private static String ParaFile = ConstUrl.getProjectHomePath()+ "\\Data\\全量环境\\新参数化创建工程样例.xlsx";//参数化表路径
	private String secns;// 场景描述
	private String type;//工程业务领域
	private String region;// 中国区
	private String agent;// 中国云南
	private String operator;// 昆明移动
	private String rat;// LTE
	private String code;// 工程编码
	private String projectNameLabel;// 工程优化自动化项目
	private String userfile ;// 批量导入用户文件
	private String GSC_userid ;// GSC 接口人账户
	

	

	public NewParamCreateProject(String secns, String type, String region,
			String agent, String operator, String rat, String code,
			String projectNameLabel, String userfile, String gSC_userid) {
		super();
		this.secns = secns;
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
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(
				ParaFile, "Traffic"));
		return coll;

	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.loginSys(driver);
	}

	@Before
	public void setUp() {
		HomeTask.JumpToprojectManage(driver, true);
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
		ProjectManageTask.paramCreateProject(driver, type, region, agent, operator, projectNameLabel, rat, code, userfile, GSC_userid, isDelete);

	}
}
