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
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.platformcommon.systemManage.SystemManageTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class All_DelExpert_Test {
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	// "中国云南昆明移动LTE工程优化自动化项目";
	private String secns;
	private String userName;
	private String region;// 中国区
	private String agent;// 中国云南
	private String[] operator;// 昆明移动
	private String[] rat;// LTE

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath()
			+ "\\Data\\全量环境";
	private static String ParaFile = FilePath + "\\添加专家参数化表.xlsx";


	public All_DelExpert_Test(String secns,String userName, String region, String agent,
			String[] operator, String[] rat) {
		this.secns = secns;
		this.userName = userName;
		this.region = region;
		this.agent = agent;
		this.operator = operator;
		this.rat = rat;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Expert"));
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
	public void addExpert_Del() 
	{
		LOG.info_testcase("场景描述:" + secns);
		SystemManageTask.addExpert(driver,true, userName, region, agent, rat,operator);
	}
}
