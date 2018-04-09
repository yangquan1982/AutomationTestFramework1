package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_trafficforecast;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_traffic_forecast.Ltetrafficforecast;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
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
public class Lte_trafficForecast_test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\LTE_话务预测";
	private static String ParaFile = FilePath + "\\业务与话务预测参数化表\\LTE业务与话务预测任务下发参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private String[] SelectTaskType;
	private String ULhuiliu;
	private String[] Gcfg;
	private String[] Ucfg;
	private String[] Lcfg;
	private String[] GPara;
	private String[] UPara;
	private String[] LPara;
	private String[] Gperf;
	private String[] Uperf;
	private String[] Lperf;
	private String[] Gchr;
	private String[] Gmr;
	private String[] Umr;
	private String TerminalFile;
	private String[] Gprsperf;
	private String[] Uprsperf;
	private String[] Lprsperf;
	private String LevelSelect;
	private String[] SeperateFile;
	private String NeGroupCell;
	private String NeGroupFile;
	private String HolidayFile;
	private String Date;
	private String YuCeLevel;
	private String[] ZhibiaoChoose;
	private String[] YiZhiSet;
	private String[] Boss;
	private String[] HuiLiuPsSet;
	private String[] UserDevelopSet;
	private String[] SeparterLiuLiang;
	private String[] BusyTime;
	private String[] EventYuCe;
	private String[] UserNumberYuCe;
	private String[] ZhibiaoChooseLeft;
	private String[] MoRenZhibiaoChoose;
	private String[] MoRenZhibiaoChooseLeft;
	private String JiSuanYinZi;

	public Lte_trafficForecast_test(String Sceneparameter, String taskName, String TestCaseId, String[] SelectTaskType,
			String ULhuiliu, String[] Gcfg, String[] Ucfg, String[] Lcfg, String[] GPara, String[] UPara,
			String[] LPara, String[] Gperf, String[] Uperf, String[] Lperf, String[] Gchr, String[] Gmr, String[] Umr,
			String TerminalFile, String[] Gprsperf, String[] Uprsperf, String[] Lprsperf, String LevelSelect,
			String[] SeperateFile, String NeGroupCell, String NeGroupFile, String HolidayFile, String Date,
			String YuCeLevel, String[] ZhibiaoChoose, String[] ZhibiaoChooseLeft, String[] MoRenZhibiaoChoose,
			String[] MoRenZhibiaoChooseLeft, String JiSuanYinZi, String[] YiZhiSet, String[] Boss, String[] HuiLiuPsSet,
			String[] UserDevelopSet, String[] SeparterLiuLiang, String[] BusyTime, String[] EventYuCe,
			String[] UserNumberYuCe) {

		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.SelectTaskType = SelectTaskType;
		this.ULhuiliu = ULhuiliu;
		this.Gcfg = Gcfg;
		this.Ucfg = Ucfg;
		this.Lcfg = Lcfg;
		this.GPara = GPara;
		this.UPara = UPara;
		this.LPara = LPara;
		this.Gperf = Gperf;
		this.Uperf = Uperf;
		this.Lperf = Lperf;
		this.Gchr = Gchr;
		this.Gmr = Gmr;
		this.Umr = Umr;
		this.TerminalFile = TerminalFile;
		this.Gprsperf = Gprsperf;
		this.Uprsperf = Uprsperf;
		this.Lprsperf = Lprsperf;
		this.LevelSelect = LevelSelect;
		this.SeperateFile = SeperateFile;
		this.NeGroupCell = NeGroupCell;
		this.NeGroupFile = NeGroupFile;
		this.HolidayFile = HolidayFile;
		this.Date = Date;
		this.YuCeLevel = YuCeLevel;
		this.ZhibiaoChoose = ZhibiaoChoose;
		this.YiZhiSet = YiZhiSet;
		this.Boss = Boss;
		this.HuiLiuPsSet = HuiLiuPsSet;
		this.UserDevelopSet = UserDevelopSet;
		this.SeparterLiuLiang = SeparterLiuLiang;
		this.BusyTime = BusyTime;
		this.EventYuCe = EventYuCe;
		this.UserNumberYuCe = UserNumberYuCe;

		this.ZhibiaoChooseLeft = ZhibiaoChooseLeft;
		this.MoRenZhibiaoChoose = MoRenZhibiaoChoose;
		this.MoRenZhibiaoChooseLeft = MoRenZhibiaoChooseLeft;
		this.JiSuanYinZi = JiSuanYinZi;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("读取参数化excel表中的参数值");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "LTE话务预测", 0));
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
	public void Lte_trafficForecastCreatTask() {

		if (TerminalFile != null) {
			TerminalFile = FilePath + TerminalFile;
		}
		if (NeGroupFile != null) {
			NeGroupFile = FilePath + NeGroupFile;
		}
		if (HolidayFile != null) {
			HolidayFile = FilePath + HolidayFile;
		}
		LOG.info_testcase("场景描述:" + Sceneparameter);
		String taskNamel = Ltetrafficforecast.creatLteTrafficForecastTask(driver, defaultWindowID, taskName,
				SelectTaskType, ULhuiliu, Gcfg, Ucfg, Lcfg, GPara, UPara, LPara, Gperf, Uperf, Lperf, Gchr, Gmr, Umr,
				TerminalFile, Gprsperf, Uprsperf, Lprsperf, LevelSelect, SeperateFile, NeGroupCell, NeGroupFile,
				HolidayFile, Date, YuCeLevel, ZhibiaoChoose, ZhibiaoChooseLeft, MoRenZhibiaoChoose,
				MoRenZhibiaoChooseLeft, JiSuanYinZi, YiZhiSet, Boss, HuiLiuPsSet, UserDevelopSet, SeparterLiuLiang,
				BusyTime, EventYuCe, UserNumberYuCe);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		System.out.println("新建任务名称为：" + taskNamel);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// TMSS回填
		result = true;
	}
}
