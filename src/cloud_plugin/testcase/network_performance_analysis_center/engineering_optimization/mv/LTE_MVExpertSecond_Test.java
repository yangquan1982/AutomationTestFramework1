package cloud_plugin.testcase.network_performance_analysis_center.engineering_optimization.mv;

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

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.mv.AutoMVDataTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

/**
 * @author dxw 2016-12-14 MV专家求助 专家审核
 **/
@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_MVExpertSecond_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\MV专家求助\\专家附件";
	private static String FilePathReport = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\MV专家求助\\专家附件\\报告";
	private static String FilePath1 = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\MV专家求助";
	private static String ParaFile = FilePath1 + "\\参数化表\\MV.xlsx";
	private static String GT3KFile = FilePath1 + "\\GT3K.ini";

	private String secns;
	private String TestCaseId;
	private String taskName;
	private String detail;
	private String MVfilePath;
	private boolean ifDownLoadFile;

	public LTE_MVExpertSecond_Test(String secns, String testCaseId, String taskName, String detail,
			boolean ifDownLoadFile, String mVfilePath) {
		this.secns = secns;
		this.TestCaseId = testCaseId;
		this.taskName = taskName;
		this.detail = detail;
		this.ifDownLoadFile = ifDownLoadFile;
		this.MVfilePath = mVfilePath;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "MVExpertSecond"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.loginExpert(driver);
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
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
	}

	@Test
	public void test() {
		LOG.info_testcase("场景描述:" + secns);
		String MVfilePath1 = null;
		if (MVfilePath != null) {
			MVfilePath1 = FilePath + MVfilePath;
		}

		AutoMVDataTask.expertTaskSecond(driver, taskName, MVfilePath1, detail, ifDownLoadFile, FilePathReport,
				defaultWindowID);
		result = true;
	}
}
