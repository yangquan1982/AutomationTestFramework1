package cloud_plugin.testcase.network_performance_analysis_center.network_planning.rank_up_plan;

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

import cloud_plugin.task.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlan;
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
public class LTE_rankuplan_CreatTask_test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_Rankupplan";
	private static String ParaFile = FilePath + "\\参数化表\\排名提升规划.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private boolean cboxpjm;
	private boolean cboxbjm;
	private boolean cboxsigtype;
	private boolean cboxdttype;
	private boolean cboxhttype;

	private String[] UELogFDD;
	private String[] CDRFDD;
	private String[] sig;
	private String[] para;
	private String[] yuanperf;
	private String csvperf;
	private String[] UELogFDDUMTS;
	private String[] CDRFDDUMTS;

	private boolean scene;
	private String ChooseYunyingshang;
	private String UelogCdrFile;
	private boolean Download;
	private String[] sceneLeft;
	private String[] sceneRight;
	private String[] sceneHeBing;
	private String Delete;

	private String ChooseYunyingshang1;
	private String huawuyinzivalue;
	private String zengzhangfile;
	private boolean TemplateDownload;
	private String[] busytime;

	public LTE_rankuplan_CreatTask_test(String Sceneparameter, String taskName, String TestCaseId, boolean cboxpjm,
			boolean cboxbjm, boolean cboxsigtype, boolean cboxdttype, boolean cboxhttype, String[] UELogFDD,
			String[] CDRFDD, String[] sig, String[] para, String[] yuanperf, String csvperf, String[] UELogFDDUMTS,
			String[] CDRFDDUMTS, boolean scene, String ChooseYunyingshang, String UelogCdrFile, boolean Download,
			String[] sceneLeft, String[] sceneRight, String[] sceneHeBing, String Delete, String ChooseYunyingshang1,
			String huawuyinzivalue, String zengzhangfile, boolean TemplateDownload, String[] busytime) {
		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.cboxpjm = cboxpjm;
		this.cboxbjm = cboxbjm;
		this.cboxsigtype = cboxsigtype;
		this.cboxdttype = cboxdttype;
		this.cboxhttype = cboxhttype;

		this.UELogFDD = UELogFDD;
		this.CDRFDD = CDRFDD;
		this.sig = sig;
		this.para = para;
		this.yuanperf = yuanperf;
		this.csvperf = csvperf;
		this.UELogFDDUMTS = UELogFDDUMTS;
		this.CDRFDDUMTS = CDRFDDUMTS;

		this.scene = scene;
		this.ChooseYunyingshang = ChooseYunyingshang;
		this.UelogCdrFile = UelogCdrFile;
		this.Download = Download;
		this.sceneLeft = sceneLeft;
		this.sceneRight = sceneRight;
		this.sceneHeBing = sceneHeBing;
		this.Delete = Delete;

		this.ChooseYunyingshang1 = ChooseYunyingshang1;
		this.huawuyinzivalue = huawuyinzivalue;
		this.zengzhangfile = zengzhangfile;
		this.TemplateDownload = TemplateDownload;
		this.busytime = busytime;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		/**
		 * 1.Basefile is empty,Checkfile is not empty 2.Basefile is not
		 * empty,Checkfile is empty 3.Basefile is empty,Checkfile is empty
		 * 4.Para is empty 5.CustParafile is empty
		 */

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "创建任务", 0));
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@Before
	public void setUp() {
		LOG.info("用例开始");
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
	public void L_BenchMarkCreatTask() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		LOG.info_testcase("场景描述:" + Sceneparameter);
		if (csvperf != null) {
			csvperf = FilePath + csvperf;
		}
		if (UelogCdrFile != null) {
			UelogCdrFile = FilePath + UelogCdrFile;
		}
		if (zengzhangfile != null) {
			zengzhangfile = FilePath + zengzhangfile;
		}
		String taskNamel = RankUpPlan.creatLTEBenchmarkTask(driver, defaultWindowID, taskName, cboxpjm, cboxbjm,
				cboxsigtype, cboxdttype, cboxhttype, UELogFDD, CDRFDD, sig, para, yuanperf, csvperf, UELogFDDUMTS,
				CDRFDDUMTS, scene, ChooseYunyingshang, UelogCdrFile, Download, sceneLeft, sceneRight, sceneHeBing,
				Delete, ChooseYunyingshang1, huawuyinzivalue, zengzhangfile, TemplateDownload, busytime);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskNamel, "Benchmark");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}

}
