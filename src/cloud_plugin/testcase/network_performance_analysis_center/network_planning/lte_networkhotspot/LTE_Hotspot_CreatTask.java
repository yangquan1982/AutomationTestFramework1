package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_networkhotspot;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.hotspot.LteHotsPot;
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
public class LTE_Hotspot_CreatTask {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\LTE_价值区域评估";
	private static String ParaFile = FilePath + "\\参数化表\\LTE价值区域评估任务下发参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private String[] cfgfile;
	private String[] Parafile;
	private String[] Mrfile;
	private String[] Pfmfile;
	private String[] FeatureFile;
	private String NeGroupData;
	private String ZhishiChoose;
	private String DingweiType;
	private String ShangeJingdu;
	private String IsOutDoor;
	private String ShanGeSvPingguType;
	private String RuoFugaiDoor;
	private String UserAddYinzi;
	private String UserAddYinziFile;
	private String RouFuGaiShibieStyle;
	private String ShanGeDabiaoDoor;
	private String DianPingDoor;
	private String CQIDoor;
	private String DanUserBaoZhang;
	private String DanUserBaoZhangFile;
	private String MRclean;
	private String[] HighSet;

	public LTE_Hotspot_CreatTask(String Sceneparameter, String taskName, String TestCaseId, String[] cfgfile,
			String[] Parafile, String[] Mrfile, String[] Pfmfile, String[] FeatureFile, String NeGroupData,
			String ZhishiChoose, String DingweiType, String ShangeJingdu, String IsOutDoor, String ShanGeSvPingguType,
			String RuoFugaiDoor, String UserAddYinzi, String UserAddYinziFile, String RouFuGaiShibieStyle,
			String ShanGeDabiaoDoor, String DianPingDoor, String CQIDoor, String DanUserBaoZhang,
			String DanUserBaoZhangFile, String MRclean, String[] HighSet) {

		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.cfgfile = cfgfile;
		this.Parafile = Parafile;
		this.Mrfile = Mrfile;
		this.Pfmfile = Pfmfile;
		this.FeatureFile = FeatureFile;
		this.NeGroupData = NeGroupData;
		this.ZhishiChoose = ZhishiChoose;
		this.DingweiType = DingweiType;
		this.ShangeJingdu = ShangeJingdu;
		this.IsOutDoor = IsOutDoor;
		this.ShanGeSvPingguType = ShanGeSvPingguType;
		this.RuoFugaiDoor = RuoFugaiDoor;
		this.UserAddYinzi = UserAddYinzi;
		this.UserAddYinziFile = UserAddYinziFile;
		this.RouFuGaiShibieStyle = RouFuGaiShibieStyle;
		this.ShanGeDabiaoDoor = ShanGeDabiaoDoor;
		this.DianPingDoor = DianPingDoor;
		this.CQIDoor = CQIDoor;
		this.DanUserBaoZhang = DanUserBaoZhang;
		this.DanUserBaoZhangFile = DanUserBaoZhangFile;
		this.MRclean = MRclean;
		this.HighSet = HighSet;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("读取参数化excel表中的参数值");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "LTE", 0));
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
	public void LTE_Hotspot_TaskCreat() {

		LOG.info_testcase("场景描述:" + Sceneparameter);
		if (NeGroupData != null) {
			NeGroupData = FilePath + NeGroupData;
		}
		if (UserAddYinziFile != null) {
			UserAddYinziFile = FilePath + UserAddYinziFile;
		}
		if (DanUserBaoZhangFile != null) {
			DanUserBaoZhangFile = FilePath + DanUserBaoZhangFile;
		}

		String taskNamel = LteHotsPot.creatLteHotspotTask(driver, defaultWindowID, taskName, cfgfile, Parafile, Mrfile,
				Pfmfile, FeatureFile, NeGroupData, ZhishiChoose, DingweiType, ShangeJingdu, IsOutDoor,
				ShanGeSvPingguType, RuoFugaiDoor, UserAddYinzi, UserAddYinziFile, RouFuGaiShibieStyle, ShanGeDabiaoDoor,
				DianPingDoor, CQIDoor, DanUserBaoZhang, DanUserBaoZhangFile, MRclean, HighSet);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskNamel, "价值区域评估");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
