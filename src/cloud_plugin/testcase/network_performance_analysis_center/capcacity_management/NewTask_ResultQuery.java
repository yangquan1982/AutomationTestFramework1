package cloud_plugin.testcase.network_performance_analysis_center.capcacity_management;

import java.util.Arrays;
import java.util.Collection;

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

import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.capcacity_management.CapcacityManagementTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.ParamUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class NewTask_ResultQuery {

	private final static String paraFile = "Data/baseline/CapcacityManagement/参数化表/CapcacityManagementCfg.xlsx";

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String secns;

	public NewTask_ResultQuery(String secns) {
		super();
		this.secns = secns;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		Object[][] param = ParamUtil.getObjectArr(paraFile, "Message");

		Object[][] firstContentParam = new Object[param.length][1];

		for (int i = 0; i < firstContentParam.length; i++) {
			firstContentParam[i][0] = param[i][0];
		}
		return Arrays.asList(firstContentParam);
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
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
	public void T001_L_CancleTask() {
		System.out.println(secns);
		NetworkAnalysisCenterTask.openCapcacityManagement(driver);
		CapcacityManagementTask.compareResultStatue(driver, secns);

	}

}
