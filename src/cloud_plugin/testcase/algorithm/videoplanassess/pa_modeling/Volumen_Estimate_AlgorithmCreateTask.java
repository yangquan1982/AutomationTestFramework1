package cloud_plugin.testcase.algorithm.videoplanassess.pa_modeling;

import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.HomePage;
import cloud_public.page.IndexPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.ParamUtil;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class Volumen_Estimate_AlgorithmCreateTask {
	
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = ConstUrl.getProjectHomePath()+"\\Data\\baseline\\02_算法\\参数化\\视频规划与评估\\容量评估前台参数化表.xlsx";
	private static String SheetName = "VMOS_算法";
	
	private String secns;
	private String TestCaseId;
	private String ProjectName;
	private String[] TaskType;
	private String[] CfgData;
	private String[] ParamData;
	private String[] SigData;
	private String[] NonEncryptedVideoSourceData;
	private String[] EncryptedVideoSourceData;
	private String[] OTTData;
	private String[] ElectronicMapData;
	private String[] LTECharacteristicLibraryData;
	private String[] PerformanceData;
	private String VideoTrafficForecastResultFile;
	private String[] XDR_MRPreprocessData;	
	private String[] CDRData;
	private String[] UELogsData;
	private String[] DataPreprocessingSetItems;
	private String[] DataPreprocessingSetValues;
	private String[] EvaluationSetItems;
	private String[] EvaluationSetItemsValues;
	private String  IsIndependentBusyhourKPIs;
	private String FilteringMode;
	private String BusyhourKPI;
	private String[] CustomHours;
	private String BusyDays;
	private String LegendSetItem;
	private String LegendSetSegmentNumber;
	private String[] LegendSetValue;

	public Volumen_Estimate_AlgorithmCreateTask(String secns, String testId,String projectName,
			String[] taskType, String[] cfgData, String[] paramData,
			String[] sigData, String[] nonEncryptedVideoSourceData,
			String[] encryptedVideoSourceData, String[] oTTData,
			String[] electronicMapData, String[] lTECharacteristicLibraryData,
			String[] performanceData, String videoTrafficForecastResultFile,String[] xDR_MRPreprocessData,
			String[] cDRData, String[] uELogsData,
			String[] dataPreprocessingSetItems,
			String[] dataPreprocessingSetValues, String[] evaluationSetItems,
			String[] evaluationSetItemsValues,
			String isIndependentBusyhourKPIs, String filteringMode,
			String busyhourKPI, String[] customHours, String busyDays,
			String legendSetItem, String legendSetSegmentNumber,
			String[] legendSetValue) {
		this.secns = secns;
		TestCaseId=testId;
		ProjectName = projectName;
		TaskType = taskType;
		CfgData = cfgData;
		ParamData = paramData;
		SigData = sigData;
		NonEncryptedVideoSourceData = nonEncryptedVideoSourceData;
		EncryptedVideoSourceData = encryptedVideoSourceData;
		OTTData = oTTData;
		ElectronicMapData = electronicMapData;
		LTECharacteristicLibraryData = lTECharacteristicLibraryData;
		PerformanceData = performanceData;
		VideoTrafficForecastResultFile = videoTrafficForecastResultFile;
		XDR_MRPreprocessData = xDR_MRPreprocessData;
		CDRData = cDRData;
		UELogsData = uELogsData;
		DataPreprocessingSetItems = dataPreprocessingSetItems;
		DataPreprocessingSetValues = dataPreprocessingSetValues;
		EvaluationSetItems = evaluationSetItems;
		EvaluationSetItemsValues = evaluationSetItemsValues;
		IsIndependentBusyhourKPIs = isIndependentBusyhourKPIs;
		FilteringMode = filteringMode;
		BusyhourKPI = busyhourKPI;
		CustomHours = customHours;
		BusyDays = busyDays;
		LegendSetItem = legendSetItem;
		LegendSetSegmentNumber = legendSetSegmentNumber;
		LegendSetValue = legendSetValue;
		
	}


	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.print("This is collection");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(
				ParaFile, SheetName));
		return coll;
	}


	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomePage.gotoldVersion(driver);
		IndexPage.OpenNetPerfomanceAnalysisCenter(driver);
	}

	@Before
	public void setUp() {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		SeqVMOSTask.goBackToNetworkAnalysisCenter(driver);
	}

	@After
	public void tearDown() {
//		StartBackFillTMSS.backFill(TestCaseId,result);
		System.out.println("tmss end xx");
		
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test
	public void T00_Algorithm_vMOSCreateTask() {	
		SeqVMOSTask.ParamCreateTask(driver, secns, ProjectName, TaskType,
				CfgData, ParamData, SigData, NonEncryptedVideoSourceData,
				EncryptedVideoSourceData, OTTData, ElectronicMapData,
				LTECharacteristicLibraryData, PerformanceData,
				VideoTrafficForecastResultFile, XDR_MRPreprocessData,CDRData, UELogsData,
				DataPreprocessingSetItems, DataPreprocessingSetValues,
				EvaluationSetItems, EvaluationSetItemsValues,
				IsIndependentBusyhourKPIs, FilteringMode, BusyhourKPI,
				CustomHours, BusyDays, LegendSetItem, LegendSetSegmentNumber,
				LegendSetValue);
	}

}
