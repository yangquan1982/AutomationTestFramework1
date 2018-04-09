package cloud_plugin.testcase.network_performance_analysis_center.theme_optimization;

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

import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_plugin.task.network_performance_analysis_center.theme_optimization.BenchmarkOptimizationTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/***
 * 排名提升优化 默认值对比
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class BeanchmarkOptimization_tacitlyApproveCompare {

	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\排名提升优化\\参数化表\\排名提升优化.xlsx";
	private String secns;
	private String TestCaseId;
	private String[] CDRDataFile;
	private String catalogue1;// 一级目录
	private String catalogue2;// 二级目录
	private String[] value; // 默认值

	/**
	 * @param secns
	 * @param testCaseId
	 * @param catalogue1
	 * @param catalogue2
	 * @param value
	 */
	public BeanchmarkOptimization_tacitlyApproveCompare(String secns, String TestCaseId, String[] CDRDataFile,
			String catalogue1, String catalogue2, String[] value) {
		super();
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.CDRDataFile = CDRDataFile;
		this.catalogue1 = catalogue1;
		this.catalogue2 = catalogue2;
		this.value = value;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(FilePath, "tacitlyApproveCompare"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result);
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		NetworkAnalysisCenterTask.openLETBenchmarkOptimization(driver);
		BenchmarkOptimizationTask.tacitlyApproveCompare(driver, CDRDataFile, catalogue1, catalogue2, value,
				defaultWindowID);
		result = true;
	}

}
