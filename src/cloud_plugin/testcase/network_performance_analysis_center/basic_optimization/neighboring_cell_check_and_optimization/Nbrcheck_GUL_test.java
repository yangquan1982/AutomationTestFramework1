package cloud_plugin.testcase.network_performance_analysis_center.basic_optimization.neighboring_cell_check_and_optimization;

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

import cloud_plugin.task.network_performance_analysis_center.basic_optimization.neighboring_cell_check_and_optimization.NbrcheckGultask;
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
public class Nbrcheck_GUL_test {
	@Rule
	public TestName name = new org.junit.rules.TestName();
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	// Data path
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\邻区核查与优化";
	private static String ParaFile = FilePath + "\\参数化表\\邻区核查与优化任务下发参数化表.xlsx";
	private static String GT3KFile = FilePath + "\\GT3K.ini";

	private String Sceneparameter;
	private String taskName;
	private String TestCaseId;
	private String LTOLAll;
	private String[] L2LCheck;
	private String L2LNBRPCIMod30Check;
	private String L2LNBRPCIMod30Distance;
	private String L2LPCIMod30;
	private String L2LPCIMod30Distance;
	private String L2LNBRPCIReusedDistance;
	private String L2LPCIReusedDistance;
	private String L2LmissNeByANRMode;
	private String L2LmissNeByANRModeFile;
	private String L2LIsUltraCheck;
	private String[] L2LIsUltraCheckParameterSet;
	private String L2LIsNeighborHighOrLow;
	private String[] L2LIsNeighborHighOrLowParameterSet;
	private String[] L2LDistanceThreshould;
	private String L2LIsMissingGIS;
	private String[] L2LIIsMissingGISParameterSet;

	private String LTOGAll;
	private String[] L2GCheck;
	private String L2GIsUltraCheck;
	private String[] L2GIsUltraCheckPmrSet;
	private String L2GIsNeighborHighOrLow;
	private String[] L2GIsNeighborHighOrLowPmrSet;
	private String L2GIsMissingGIS;
	private String[] L2GIsMissingGISPmrSet;
	private String L2GisGSM900First;

	private String LTOUAll;
	private String[] L2UCheck;
	private String L2UIsUltraCheck;
	private String[] L2UIsUltraCheckPmrSet;
	private String L2UIsNeighborHighOrLow;
	private String[] L2UIsNeighborHighOrLowPmrSet;
	private String L2UIsMissingGIS;
	private String[] L2UIsMissingGISPmrSet;

	private String[] Lcfgfile;
	private String[] LParafile;
	private String[] UParafile;
	private String[] GParafile;
	private String[] U2000Pfmfile;
	private String mobilityPolicyFile;
	private String[] Ucfgfile;
	private String[] Gcfgfile;

	public Nbrcheck_GUL_test(String Sceneparameter, String taskName, String TestCaseId, String LTOLAll,
			String[] L2LCheck, String L2LNBRPCIMod30Check, String L2LNBRPCIMod30Distance, String L2LPCIMod30,
			String L2LPCIMod30Distance, String L2LNBRPCIReusedDistance, String L2LPCIReusedDistance,
			String L2LmissNeByANRMode, String L2LmissNeByANRModeFile, String L2LIsUltraCheck,
			String[] L2LIsUltraCheckParameterSet, String L2LIsNeighborHighOrLow,
			String[] L2LIsNeighborHighOrLowParameterSet, String[] L2LDistanceThreshould, String L2LIsMissingGIS,
			String[] L2LIIsMissingGISParameterSet,

	String LTOGAll, String[] L2GCheck, String L2GIsUltraCheck, String[] L2GIsUltraCheckPmrSet,
			String L2GIsNeighborHighOrLow, String[] L2GIsNeighborHighOrLowPmrSet, String L2GIsMissingGIS,
			String[] L2GIsMissingGISPmrSet, String L2GisGSM900First,

	String LTOUAll, String[] L2UCheck, String L2UIsUltraCheck, String[] L2UIsUltraCheckPmrSet,
			String L2UIsNeighborHighOrLow, String[] L2UIsNeighborHighOrLowPmrSet, String L2UIsMissingGIS,
			String[] L2UIsMissingGISPmrSet,

	String[] Lcfgfile, String[] LParafile, String[] UParafile, String[] GParafile, String[] U2000Pfmfile,
			String mobilityPolicyFile, String[] Ucfgfile, String[] Gcfgfile) {

		this.Sceneparameter = Sceneparameter;
		this.taskName = taskName;
		this.TestCaseId = TestCaseId;
		this.LTOLAll = LTOLAll;
		this.L2LCheck = L2LCheck;
		this.L2LNBRPCIMod30Check = L2LNBRPCIMod30Check;
		this.L2LNBRPCIMod30Distance = L2LNBRPCIMod30Distance;
		this.L2LPCIMod30 = L2LPCIMod30;
		this.L2LPCIMod30Distance = L2LPCIMod30Distance;
		this.L2LNBRPCIReusedDistance = L2LNBRPCIReusedDistance;
		this.L2LPCIReusedDistance = L2LPCIReusedDistance;
		this.L2LmissNeByANRMode = L2LmissNeByANRMode;
		this.L2LmissNeByANRModeFile = L2LmissNeByANRModeFile;
		this.L2LIsUltraCheck = L2LIsUltraCheck;
		this.L2LIsUltraCheckParameterSet = L2LIsUltraCheckParameterSet;
		this.L2LIsNeighborHighOrLow = L2LIsNeighborHighOrLow;
		this.L2LIsNeighborHighOrLowParameterSet = L2LIsNeighborHighOrLowParameterSet;
		this.L2LDistanceThreshould = L2LDistanceThreshould;
		this.L2LIsMissingGIS = L2LIsMissingGIS;
		this.L2LIIsMissingGISParameterSet = L2LIIsMissingGISParameterSet;

		this.LTOGAll = LTOGAll;
		this.L2GCheck = L2GCheck;
		this.L2GIsUltraCheck = L2GIsUltraCheck;
		this.L2GIsUltraCheckPmrSet = L2GIsUltraCheckPmrSet;
		this.L2GIsNeighborHighOrLow = L2GIsNeighborHighOrLow;
		this.L2GIsNeighborHighOrLowPmrSet = L2GIsNeighborHighOrLowPmrSet;
		this.L2GIsMissingGIS = L2GIsMissingGIS;
		this.L2GIsMissingGISPmrSet = L2GIsMissingGISPmrSet;
		this.L2GisGSM900First = L2GisGSM900First;

		this.LTOUAll = LTOUAll;
		this.L2UCheck = L2UCheck;
		this.L2UIsUltraCheck = L2UIsUltraCheck;
		this.L2UIsUltraCheckPmrSet = L2UIsUltraCheckPmrSet;
		this.L2UIsNeighborHighOrLow = L2UIsNeighborHighOrLow;
		this.L2UIsNeighborHighOrLowPmrSet = L2UIsNeighborHighOrLowPmrSet;
		this.L2UIsMissingGIS = L2UIsMissingGIS;
		this.L2UIsMissingGISPmrSet = L2UIsMissingGISPmrSet;

		this.Lcfgfile = Lcfgfile;
		this.LParafile = LParafile;
		this.UParafile = UParafile;
		this.GParafile = GParafile;
		this.U2000Pfmfile = U2000Pfmfile;
		this.mobilityPolicyFile = mobilityPolicyFile;
		this.Ucfgfile = Ucfgfile;
		this.Gcfgfile = Gcfgfile;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("读取参数化excel表中的参数值");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "GULNbrCheck", 0));
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
	public void NbrCheck_Gul_Test() {

		if (L2LmissNeByANRModeFile != null) {
			L2LmissNeByANRModeFile = FilePath + L2LmissNeByANRModeFile;
		}
		if (mobilityPolicyFile != null) {
			mobilityPolicyFile = FilePath + mobilityPolicyFile;
		}
		LOG.info_testcase("场景描述:" + Sceneparameter);
		String taskNamel = NbrcheckGultask.creatNbrCheckTask(driver, defaultWindowID, taskName, LTOLAll, L2LCheck,
				L2LNBRPCIMod30Check, L2LNBRPCIMod30Distance, L2LPCIMod30, L2LPCIMod30Distance, L2LNBRPCIReusedDistance,
				L2LPCIReusedDistance, L2LmissNeByANRMode, L2LmissNeByANRModeFile, L2LIsUltraCheck,
				L2LIsUltraCheckParameterSet, L2LIsNeighborHighOrLow, L2LIsNeighborHighOrLowParameterSet,
				L2LDistanceThreshould, L2LIsMissingGIS, L2LIIsMissingGISParameterSet,

		LTOGAll, L2GCheck, L2GIsUltraCheck, L2GIsUltraCheckPmrSet, L2GIsNeighborHighOrLow, L2GIsNeighborHighOrLowPmrSet,
				L2GIsMissingGIS, L2GIsMissingGISPmrSet, L2GisGSM900First,

		LTOUAll, L2UCheck, L2UIsUltraCheck, L2UIsUltraCheckPmrSet, L2UIsNeighborHighOrLow, L2UIsNeighborHighOrLowPmrSet,
				L2UIsMissingGIS, L2UIsMissingGISPmrSet, Lcfgfile, LParafile, UParafile, GParafile, U2000Pfmfile,
				mobilityPolicyFile, Ucfgfile, Gcfgfile);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
		TaskReportTask.asserTaskInitialState(driver, taskNamel, "");
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
		// 设置TMSS回填结果
		result = true;
	}
}
