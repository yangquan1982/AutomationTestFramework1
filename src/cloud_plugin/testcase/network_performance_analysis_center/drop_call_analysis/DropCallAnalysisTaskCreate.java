package cloud_plugin.testcase.network_performance_analysis_center.drop_call_analysis;

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
import cloud_plugin.task.network_performance_analysis_center.drop_call_analysis.DropCallAnalysisTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.ParamUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class DropCallAnalysisTaskCreate {

	private final static String paraFile = "Data/baseline/DropCallAnalysis/参数化表/DropCallAnalysisCfg.xlsx";

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String testMessage;
	private String testCaseId;
	private String taskName;
	private String ratType;
	private String configurationData[];
	private String engineeringParameter[];
	private String pfmData[];
	private String chrDate[];

	public DropCallAnalysisTaskCreate(String testMessage, String testCaseId, String taskName, String ratType,
			String[] configurationData, String[] engineeringParameter, String[] pfmData, String[] chrDate) {
		super();
		this.testMessage = testMessage;
		this.testCaseId = testCaseId;
		this.taskName = taskName;
		this.ratType = ratType;
		this.configurationData = configurationData;
		this.engineeringParameter = engineeringParameter;
		this.pfmData = pfmData;
		this.chrDate = chrDate;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		Object[][] param = ParamUtil.getObjectArr(paraFile, "dropcalltask");
		return Arrays.asList(param);
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
	public void T001_Drop_Call_Analysis_Task_Create() {
		NetworkAnalysisCenterTask.openDropCallAnalysis(driver);
		DropCallAnalysisTask.createNewTask(driver, taskName, ratType, configurationData, engineeringParameter, pfmData,
				chrDate);

	}

}
