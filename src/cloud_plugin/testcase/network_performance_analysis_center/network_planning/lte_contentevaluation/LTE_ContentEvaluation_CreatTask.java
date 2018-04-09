package cloud_plugin.testcase.network_performance_analysis_center.network_planning.lte_contentevaluation;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.content_evaluate.LteContentEvaluate;
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
public class LTE_ContentEvaluation_CreatTask {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\LTE_容量评估";
	private static String ParaFile = FilePath + "\\参数化表\\LTE容量评估任务下发参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private String[] Lcfg;
	private String[] LPara;
	private String NeGroupFile;
	private String ZhiShi;
	private String[] MeiXian;
	private String[] YuCeYinzi;
	private String YuCeYinziFile;
	private String[] AreaUpFactor;
	private String SupportBusy;
	private String BusyZhibiao;
	private String BusyChooseStye;
	private String[] SelfDefineHours;
	private String BusyDays;
	private String SupportAllJiaoYan;
	private String[] TimesQuJian;
	private String[] SceneSet;
	private String Aera;
	private String[] SmallSigSelect;
	private String[] SmallAnd;
	private String[] SmallText;
	private String[] MidSigSelect;
	private String[] MidAnd;
	private String[] MidText;
	private String[] BigSigSelect;
	private String[] Big;
	private String[] BigText;
	private String[] OtherBigText;
	private String[] OtherMidText;
	private String[] OtherSmallText;

	public LTE_ContentEvaluation_CreatTask(String Sceneparameter, String taskName, String TestCaseId, String[] Lcfg,
			String[] LPara, String NeGroupFile, String ZhiShi, String[] MeiXian, String[] YuCeYinzi,
			String YuCeYinziFile, String[] AreaUpFactor, String SupportBusy, String BusyZhibiao, String BusyChooseStye,
			String[] SelfDefineHours, String BusyDays, String SupportAllJiaoYan, String[] TimesQuJian,
			String[] SceneSet, String Aera, String[] SmallSigSelect, String[] SmallAnd, String[] SmallText,
			String[] MidSigSelect, String[] MidAnd, String[] MidText, String[] BigSigSelect, String[] Big,
			String[] BigText, String[] OtherBigText, String[] OtherMidText, String[] OtherSmallText) {

		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.Lcfg = Lcfg;
		this.LPara = LPara;
		this.NeGroupFile = NeGroupFile;
		this.ZhiShi = ZhiShi;
		this.MeiXian = MeiXian;
		this.YuCeYinzi = YuCeYinzi;
		this.YuCeYinziFile = YuCeYinziFile;
		this.AreaUpFactor = AreaUpFactor;
		this.SupportBusy = SupportBusy;
		this.BusyZhibiao = BusyZhibiao;
		this.BusyChooseStye = BusyChooseStye;
		this.SelfDefineHours = SelfDefineHours;
		this.BusyDays = BusyDays;
		this.SupportAllJiaoYan = SupportAllJiaoYan;
		this.TimesQuJian = TimesQuJian;
		this.SceneSet = SceneSet;
		this.Aera = Aera;
		this.SmallSigSelect = SmallSigSelect;
		this.SmallAnd = SmallAnd;
		this.SmallText = SmallText;
		this.MidSigSelect = MidSigSelect;
		this.MidAnd = MidAnd;
		this.MidText = MidText;
		this.BigSigSelect = BigSigSelect;
		this.Big = Big;
		this.BigText = BigText;
		this.OtherBigText = OtherBigText;
		this.OtherMidText = OtherMidText;
		this.OtherSmallText = OtherSmallText;
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
	public void LTE_ContentEvaluation_TaskCreat() {

		LOG.info_testcase("场景描述:" + Sceneparameter);
		if (NeGroupFile != null) {
			NeGroupFile = FilePath + NeGroupFile;
		}
		if (YuCeYinziFile != null) {
			YuCeYinziFile = FilePath + YuCeYinziFile;
		}
		String taskNamel = LteContentEvaluate.creatLteContentEvaluate(driver, defaultWindowID, taskName, Lcfg, LPara,
				NeGroupFile, ZhiShi, MeiXian, YuCeYinzi, YuCeYinziFile, AreaUpFactor, SupportBusy, BusyZhibiao,
				BusyChooseStye, SelfDefineHours, BusyDays, SupportAllJiaoYan, TimesQuJian, SceneSet, Aera,
				SmallSigSelect, SmallAnd, SmallText, MidSigSelect, MidAnd, MidText, BigSigSelect, Big, BigText,
				OtherBigText, OtherMidText, OtherSmallText);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskNamel, "容量评估");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
