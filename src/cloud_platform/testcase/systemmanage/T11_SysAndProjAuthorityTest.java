package cloud_platform.testcase.systemmanage;

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

import cloud_platform.page.PlatformConst;
import cloud_platform.page.systemManage.SystemManageConst;
import cloud_platform.task.platform.SystemManageTask;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.parameter.Account;
import common.parameter.CellInfo;
import common.parameter.platform.ProjectMangeParameter;
import common.parameter.platform.SystemManageParameter;
import common.util.CommonWD;
import common.util.PlatfromParamUtil;
import common.util.SeleniumUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T11_SysAndProjAuthorityTest {
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String paramPath = "D:\\GENEXCloud_V3_P1\\Data\\baseline\\00_Platform\\SystemManage\\系统管理参数化测试场景.xlsx";
	private static String AccountType = "系统管理员和项目经理";//系统管理员和项目经理
	private static Account account = new Account(paramPath,"用户分配",AccountType);
	private static SystemManageParameter parameter = new SystemManageParameter(
			SystemManageConst.Set_ProjectManger, PlatfromParamUtil.getAddAccount(paramPath, "用户分配", AccountType));
	private static ProjectMangeParameter projectparameter = new ProjectMangeParameter();
	

	private CellInfo Autuority;

	public T11_SysAndProjAuthorityTest(CellInfo c) {
		super();
		this.Autuority = c;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		Collection<Object[]> coll = Arrays
				.asList(PlatfromParamUtil.getArrayContent(paramPath,
						"系统管理测试场景", "系统管理测试用例", AccountType));
		return coll;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//设置验证项目
		projectparameter.setProjectLabel(PlatformConst.projectLabel01+ZIP.GetTime("yyMMddhhmmss"));
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
//		LoginTask.loginSys(driver);
		LoginTask.login_account_pwd(driver, account.getAccount(), account.getPwd());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		// SwitchDriver.closeOtherWin(driver, defaultWindowID);
		result = false;
		HomeTask.goBacktoHome(driver);
	}

	@After
	public void tearDown() throws Exception {

		StartBackFillTMSS.backFill(Autuority.getTestID(),result);
		SeleniumUtil.killJAVA();
	}

	@Test
	public void test() {
		SystemManageTask.AuthorityVertify(driver, parameter, projectparameter,Autuority,AccountType);
		// 设置TMSS回填结果		
		result = true;
	}

}
