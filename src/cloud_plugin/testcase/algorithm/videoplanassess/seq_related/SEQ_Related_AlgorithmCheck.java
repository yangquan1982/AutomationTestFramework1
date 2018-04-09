package cloud_plugin.testcase.algorithm.videoplanassess.seq_related;

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

import com.huawei.webtest.restful.util.Log;

import cloud_plugin.task.algorithm.Algorithm;
import cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos.SeqVMOSTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.HomePage;
import cloud_public.page.IndexPage;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.ParamUtil;
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class SEQ_Related_AlgorithmCheck {
	
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private boolean result = false;
//	private static String GT3KFile = FilePath + "\\GT3K.ini";
	private static String path = System.getProperty("user.dir")+"\\Data\\baseline\\02_算法\\参数化\\视频规划与评估\\SEQ话单关联算法参数化表.xlsx";
	
	private String testID;
	private String taskName;
	private String mark;
	private String [] indexKey;
	private String [] indexValue;
	private String [] expectKey;
	private String [] expectValue;
	private String compareFilePath;
	
	
	public SEQ_Related_AlgorithmCheck(String testID,String taskName,String mark,String[] indexKey,
			String[] indexValue,String[] expectKey,String[] expectValue,String compareFilePath) 
	{
		this.testID = testID;
		this.taskName = taskName;
		this.mark = mark;
		this.indexKey = indexKey;
		this.indexValue = indexValue;
		this.expectKey = expectKey;
		this.expectValue = expectValue;
		this.compareFilePath = compareFilePath;
	} 

	@Parameters()
	public static Collection<Object[]> data() throws Exception 
	{
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(path, "算法正确性",16,2,0));
		return coll;
	}

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomePage.gotoldVersion(driver);
		IndexPage.OpenNetPerfomanceAnalysisCenter(driver);
	}


	@Before
	public void setUp() throws Exception 
	{
		SwitchDriver.switchDriverToSEQ(driver);
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		SeqVMOSTask.goBackToNetworkAnalysisCenter(driver);
	}

	@After
	public void tearDown() throws Exception 
	{
		Algorithm.compareCSV = null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		//退出登录
		driver.quit();
	}

	@Test 
	public void test_1() 
	{
//		Log.info("用例场景是："+testName);
		Log.info("检查的项为："+compareFilePath);
		Algorithm.startCheck(driver,defaultWindowID,taskName,mark,indexKey,indexValue,expectKey,expectValue,compareFilePath,"SEQ_Related");
		result = true;
	}

}
