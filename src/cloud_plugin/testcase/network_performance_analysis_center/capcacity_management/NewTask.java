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

import cloud_plugin.task.network_performance_analysis_center.capcacity_management.CapcacityManagementTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.ParamUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class NewTask {

	private final static String paraFile = "Data/baseline/CapcacityManagement/参数化表/CapcacityManagementCfg.xlsx";

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String secns;
	private String taskLanguage;
	private String[] cfgDate;
	private String[] mmlDate;
	private String[] prsDate;
	private String prsDateModel;
	private String[] u2000Date;
	private String u2000DateModel;
	private String enodbeFilePath;

	private String cellStandardText;
	private int cellRuleNum;
	private String[] cellXnz;
	private String[] cellTimeGranularity;

	private String enodebStandardText;
	private int enodebRuleNum;
	private String[] enodebXnz;
	private String[] enodebTimeGranularity;

	public NewTask(String secns, String taskLanguage, String[] cfgDate, String[] mmlDate, String[] prsDate,
			String prsDateModel, String[] u2000Date, String u2000DateModel, String enodbeFilePath,
			String cellStandardText, int cellRuleNum, String[] cellXnz, String[] cellTimeGranularity,
			String enodebStandardText, int enodebRuleNum, String[] enodebXnz, String[] enodebTimeGranularity) {
		super();
		this.secns = secns;
		this.taskLanguage = taskLanguage;
		this.cfgDate = cfgDate;
		this.mmlDate = mmlDate;
		this.prsDate = prsDate;
		this.prsDateModel = prsDateModel;
		this.u2000Date = u2000Date;
		this.u2000DateModel = u2000DateModel;
		this.enodbeFilePath = enodbeFilePath;
		this.cellStandardText = cellStandardText;
		this.cellRuleNum = cellRuleNum;
		this.cellXnz = cellXnz;
		this.cellTimeGranularity = cellTimeGranularity;
		this.enodebStandardText = enodebStandardText;
		this.enodebRuleNum = enodebRuleNum;
		this.enodebXnz = enodebXnz;
		this.enodebTimeGranularity = enodebTimeGranularity;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println();
		Object[][] param = ParamUtil.getObjectArr(paraFile, "Message");
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
	public void T001_L_CancleTask() {
		System.out.println(secns);
		CapcacityManagementTask.createNewTaskSetting(driver, secns, taskLanguage, defaultWindowID, cfgDate, mmlDate,
				prsDate, prsDateModel, u2000Date, u2000DateModel, enodbeFilePath);
		CapcacityManagementTask.setUpProblemRecognition(driver, "cell", cellStandardText, cellRuleNum, cellXnz,
				cellTimeGranularity);
		CapcacityManagementTask.setUpProblemRecognition(driver, "enodeb", enodebStandardText, enodebRuleNum, enodebXnz,
				enodebTimeGranularity);

		// NetworkAnalysisCenterTask.openCapcacityManagement(driver);
		CapcacityManagementTask.resultQuery(driver, secns);
		CapcacityManagementTask.resultCompare(driver, secns, cellStandardText, cellRuleNum, cellXnz,
				cellTimeGranularity, enodebStandardText, enodebRuleNum, enodebXnz, enodebTimeGranularity);

	}

}
