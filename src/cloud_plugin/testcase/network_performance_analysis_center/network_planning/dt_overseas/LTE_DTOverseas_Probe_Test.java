package cloud_plugin.testcase.network_performance_analysis_center.network_planning.dt_overseas;

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

import cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDTDataOverseasTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_DTOverseas_Probe_Test {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static boolean result = false;

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\LTE_DT海外";
	private static String ParaFile = FilePath + "\\参数化表\\DT创建任务.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";
	private String secns;
	private String TestCaseId;
	private String taskName001;

	private String reportType;
	private String[] DTfile;
	private String[] LEParafile;
	private String[] Mapfile;
	private String[] ExEParafile;
	private String[] Ratefile;

	public LTE_DTOverseas_Probe_Test(String secns, String TestCaseId, String taskName001, String reportType,
			String[] DTfile, String[] LEParafile, String[] Mapfile, String[] ExEParafile, String[] Ratefile) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.reportType = reportType;
		this.DTfile = DTfile;
		this.LEParafile = LEParafile;
		this.Mapfile = Mapfile;
		this.ExEParafile = ExEParafile;
		this.Ratefile = Ratefile;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Probe异常"));
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@Before
	public void setUp() {
		result = false;
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void L_DT_ParaErr() {
		LOG.info_testcase("场景描述:" + secns);
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		AutoDTDataOverseasTask.creatLTEProbeTask(driver, defaultWindowID, taskName001, reportType, DTfile, LEParafile,
				Mapfile, ExEParafile, Ratefile);
		if (DTfile == null) {
			AutoDTDataOverseasTask.DT_ErrorMsg(driver, "DT");
		}
		if (LEParafile == null) {
			AutoDTDataOverseasTask.DT_ErrorMsg(driver, "LEPara");
		}

		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}

}
