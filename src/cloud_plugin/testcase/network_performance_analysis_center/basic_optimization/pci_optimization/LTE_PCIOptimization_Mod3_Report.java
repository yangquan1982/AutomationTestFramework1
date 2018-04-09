package cloud_plugin.testcase.network_performance_analysis_center.basic_optimization.pci_optimization;

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

import cloud_plugin.task.network_performance_analysis_center.basic_optimization.pci_optimization.PCIOptimizationTask;
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
public class LTE_PCIOptimization_Mod3_Report {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ServiceType = "PCI Optimization";

	private static boolean result = false;

	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\PCI优化";
	private static String ParaFile = FilePath + "\\参数化表\\PCI优化.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";
	private String secns;
	private String TestCaseId;
	private String taskName001;

	private String AnyModel;

	private String[] CfgFile;
	private String[] PPFile;
	private String[] MatrixFile;
	private String[] DTOriginFile;
	private String[] DTCSVFile;
	private String[] SigFile;
	private String[] PrfOriginFile;
	private String[] PrfU2000File;
	private String[] PrfPRSFile;
	private String CellFile;

	private String[] Distance;
	private String[] Range;

	private String AnnealNum;
	private String InnerLoopNum;

	private boolean TopNFlage;
	private String TopN;

	private boolean NCLFlage;
	private boolean MOD30Flage;
	private boolean NotConsiderFlage;

	private String[] Quota;

	public LTE_PCIOptimization_Mod3_Report(String secns, String TestCaseId, String taskName001, String AnyModel,
			String[] CfgFile, String[] PPFile, String[] MatrixFile, String[] DTOriginFile, String[] DTCSVFile,
			String[] SigFile, String[] PrfOriginFile, String[] PrfU2000File, String[] PrfPRSFile, String CellFile,
			String[] Distance, String[] Range, String AnnealNum, String InnerLoopNum, boolean TopNFlage, String TopN,
			boolean NCLFlage, boolean MOD30Flage, boolean NotConsiderFlage, String[] Quota) {
		this.secns = secns;
		this.TestCaseId = TestCaseId;
		this.taskName001 = taskName001;
		this.AnyModel = AnyModel;

		this.CfgFile = CfgFile;
		this.PPFile = PPFile;
		this.MatrixFile = MatrixFile;
		this.DTOriginFile = DTOriginFile;
		this.DTCSVFile = DTCSVFile;
		this.SigFile = SigFile;
		this.PrfOriginFile = PrfOriginFile;
		this.PrfU2000File = PrfU2000File;
		this.PrfPRSFile = PrfPRSFile;
		this.CellFile = CellFile;

		this.Distance = Distance;
		this.Range = Range;
		this.AnnealNum = AnnealNum;
		this.InnerLoopNum = InnerLoopNum;

		this.TopNFlage = TopNFlage;
		this.TopN = TopN;

		this.NCLFlage = NCLFlage;
		this.MOD30Flage = MOD30Flage;
		this.NotConsiderFlage = NotConsiderFlage;

		this.Quota = Quota;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "基于PCI MOD3的优化"));
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
	public void L_creatRFTask() {
		LOG.info_testcase("场景描述:" + secns);
		String CellFile1 = null;
		if (CellFile != null) {
			CellFile1 = FilePath + CellFile;
		}
		String taskName = PCIOptimizationTask.creatPCIMOD3Task(driver, defaultWindowID, taskName001, AnyModel, CfgFile,
				PPFile, MatrixFile, DTOriginFile, DTCSVFile, SigFile, PrfOriginFile, PrfU2000File, PrfPRSFile,
				CellFile1, Distance, Range, AnnealNum, InnerLoopNum, TopNFlage, TopN, NCLFlage, MOD30Flage,
				NotConsiderFlage, Quota);

		LoadingPage.Loading(driver, TaskReportPage.CreateTask, "新建任务");
		TaskReportTask.asserTaskInitialState(driver, taskName, ServiceType);

		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
