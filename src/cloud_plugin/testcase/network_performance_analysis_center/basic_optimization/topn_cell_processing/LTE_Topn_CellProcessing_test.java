package cloud_plugin.testcase.network_performance_analysis_center.basic_optimization.topn_cell_processing;

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

import cloud_plugin.task.network_performance_analysis_center.basic_optimization.topn_cell_processing.Topncellprocessing;
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
public class LTE_Topn_CellProcessing_test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\LTE_Topn小区处理";
	private static String ParaFile = FilePath + "\\参数化表\\LTETopn小区处理任务下发参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private String ThemeOptimization;
	private String[] BusyDataSet;
	private String XiaoQuNumber;
	private String XiaoQuBiLi;
	private String XiaoQuBiLiValue;
	private String ClusterLevelThreshod;
	private String ContainMMLexception;
	private String abnormalMRanalysis;
	private String JieRuContainS1;
	private String QieHuanCommunityautofilter;
	private String[] IntraFrequency;
	private String[] InterFrequency;
	private String QieHuanabnormalMRanalysis;
	private String[] cfgfile;
	private String[] Pfmfile;
	private String[] Parafile;
	private String[] Warnfile;
	private String[] operatefile;
	private String[] OutChrfile;
	private String[] InChrfile;
	private String[] ThroughPutgate;
	private String[] Disconnect;
	private String[] Access;

	public LTE_Topn_CellProcessing_test(String Sceneparameter, String taskName, String TestCaseId,
			String ThemeOptimization, String[] BusyDataSet, String XiaoQuNumber, String XiaoQuBiLi,
			String XiaoQuBiLiValue, String ClusterLevelThreshod, String ContainMMLexception, String abnormalMRanalysis,
			String JieRuContainS1, String QieHuanCommunityautofilter, String[] IntraFrequency, String[] InterFrequency,
			String QieHuanabnormalMRanalysis, String[] cfgfile, String[] Pfmfile, String[] Parafile, String[] Warnfile,
			String[] operatefile, String[] OutChrfile, String[] InChrfile, String[] ThroughPutgate, String[] Disconnect,
			String[] Access) {

		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.ThemeOptimization = ThemeOptimization;
		this.BusyDataSet = BusyDataSet;
		this.XiaoQuNumber = XiaoQuNumber;
		this.XiaoQuBiLi = XiaoQuBiLi;
		this.XiaoQuBiLiValue = XiaoQuBiLiValue;
		this.ClusterLevelThreshod = ClusterLevelThreshod;
		this.ContainMMLexception = ContainMMLexception;
		this.abnormalMRanalysis = abnormalMRanalysis;
		this.JieRuContainS1 = JieRuContainS1;
		this.QieHuanCommunityautofilter = QieHuanCommunityautofilter;
		this.IntraFrequency = IntraFrequency;
		this.InterFrequency = InterFrequency;
		this.QieHuanabnormalMRanalysis = QieHuanabnormalMRanalysis;
		this.cfgfile = cfgfile;
		this.Pfmfile = Pfmfile;
		this.Parafile = Parafile;
		this.Warnfile = Warnfile;
		this.operatefile = operatefile;
		this.OutChrfile = OutChrfile;
		this.InChrfile = InChrfile;
		this.ThroughPutgate = ThroughPutgate;
		this.Disconnect = Disconnect;
		this.Access = Access;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("读取参数化excel表中的参数值");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Topn", 0));
		return coll;
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
		System.out.println("tmss end xx");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void L_TopnCreatTask() {

		LOG.info_testcase("场景描述:" + Sceneparameter);
		String taskNamel = Topncellprocessing.creatLteTopnTask(driver, defaultWindowID, taskName, ThemeOptimization,
				BusyDataSet, XiaoQuNumber, XiaoQuBiLi, XiaoQuBiLiValue, ClusterLevelThreshod, ContainMMLexception,
				abnormalMRanalysis, JieRuContainS1, QieHuanCommunityautofilter, IntraFrequency, InterFrequency,
				QieHuanabnormalMRanalysis, cfgfile, Pfmfile, Parafile, Warnfile, operatefile, OutChrfile, InChrfile,
				ThroughPutgate, Disconnect, Access);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskNamel, "");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
