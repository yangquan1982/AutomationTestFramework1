package cloud_plugin.testcase.network_performance_analysis_center.capacity_evaluate;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.content_evaluate.ContentEvaluateTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.LoginTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class UMTS_CapacityEvaluate_Report {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "ContentEvaluate";

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\U_容量评估";
	private static String ParaFile = FilePath + "\\参数化表\\SmokeCapacityEvaluate.xlsx";
	private String secns;
	private String TestCaseId;
	private String taskName001;

	private String[] cfgFile;
	private String[] EparaFile;
	private String[] PfmFile;

	private boolean TcpFlage;
	private String TcpPowerOver;
	private String TcpDCHUser;

	private boolean RtwpFlage;
	private String RtwpLoad;
	private String RtwpUpUser;

	private boolean CodeFlage;
	private String Code;

	private boolean HsuserFlage;
	private String HSDPAUser;
	private String HSDPAThrough;

	private String FactorSub;
	private String FactorCsUser;
	private String FactorPsUp;
	private String FactorPsDown;
	private String FactorCsSig;
	private String FactorPsSig;
	private String FactorHSDPAUser;

	private String FactorFile;
	private boolean cellUserFactorFlage;
	private boolean repPsUserDLFlage;

	private String TopN;
	private boolean TCPFlage;
	private boolean TopMFlage;

	private String TopM;
	private String DataIntegrity;

	public UMTS_CapacityEvaluate_Report(String secns, String TestCaseId, String taskName001, String[] cfgFile,
			String[] EparaFile, String[] PfmFile, boolean TcpFlage, String TcpPowerOver, String TcpDCHUser,
			boolean RtwpFlage, String RtwpLoad, String RtwpUpUser, boolean CodeFlage, String Code, boolean HsuserFlage,
			String HSDPAUser, String HSDPAThrough, String FactorSub, String FactorCsUser, String FactorPsUp,
			String FactorPsDown, String FactorCsSig, String FactorPsSig, String FactorHSDPAUser, String FactorFile,
			boolean cellUserFactorFlage, boolean repPsUserDLFlage, String TopN, boolean TCPFlage, boolean TopMFlage,
			String TopM, String DataIntegrity) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;

		this.cfgFile = cfgFile;
		this.EparaFile = EparaFile;
		this.PfmFile = PfmFile;

		this.TcpFlage = TcpFlage;
		this.TcpPowerOver = TcpPowerOver;
		this.TcpDCHUser = TcpDCHUser;

		this.RtwpFlage = RtwpFlage;
		this.RtwpLoad = RtwpLoad;
		this.RtwpUpUser = RtwpUpUser;

		this.CodeFlage = CodeFlage;
		this.Code = Code;

		this.HsuserFlage = HsuserFlage;
		this.HSDPAUser = HSDPAUser;
		this.HSDPAThrough = HSDPAThrough;

		this.FactorSub = FactorSub;
		this.FactorCsUser = FactorCsUser;
		this.FactorPsUp = FactorPsUp;
		this.FactorPsDown = FactorPsDown;
		this.FactorCsSig = FactorCsSig;
		this.FactorPsSig = FactorPsSig;
		this.FactorHSDPAUser = FactorHSDPAUser;
		this.FactorFile = FactorFile;
		this.cellUserFactorFlage = cellUserFactorFlage;
		this.repPsUserDLFlage = repPsUserDLFlage;

		this.TopN = TopN;
		this.TCPFlage = TCPFlage;
		this.TopMFlage = TopMFlage;

		this.TopM = TopM;
		this.DataIntegrity = DataIntegrity;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		/**
		 * 1.Basefile is empty,Checkfile is not empty 2.Basefile is not
		 * empty,Checkfile is empty 3.Basefile is empty,Checkfile is empty
		 * 4.Para is empty 5.CustParafile is empty
		 */

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "BasicTraffic"));
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
		StartBackFillTMSS.backFill(TestCaseId, result);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void U_BasicTraffic() {
		LOG.info_testcase("场景描述:" + secns);
		String FactorFile1 = null;
		if (FactorFile != null) {
			FactorFile1 = FilePath + FactorFile;
		}
		String taskName = ContentEvaluateTask.creatUMTSContentEvaluateTask(driver, defaultWindowID, taskName001,
				cfgFile, EparaFile, PfmFile, TcpFlage, TcpPowerOver, TcpDCHUser, RtwpFlage, RtwpLoad, RtwpUpUser,
				CodeFlage, Code, HsuserFlage, HSDPAUser, HSDPAThrough, FactorSub, FactorCsUser, FactorPsUp,
				FactorPsDown, FactorCsSig, FactorPsSig, FactorHSDPAUser, FactorFile1, cellUserFactorFlage,
				repPsUserDLFlage, TopN, TCPFlage, TopMFlage, TopM, DataIntegrity);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
