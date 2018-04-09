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

import cloud_platform.task.upload_data.DataCenterTask;
import cloud_public.page.IndexPage;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.system.SystemConstant;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class Volumen_Estimate_AlgorithmDataUpload {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = SystemConstant.AlgorithmUploadParamAlgirithm+"容量评估前台参数化表.xlsx";
	private static String SheetName = "数据上传_算法";
	
	
	private String Scene;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String Feature;
	private String[] DataPath;
	private String fileName;
	
	
	public Volumen_Estimate_AlgorithmDataUpload(String scene,String rAT, String dataType, 
			String subDataType, String feature,
			String[] dataPath,String fileName) 
	{
		this.Scene = scene;
		this.RAT = rAT;
		this.DataType = dataType;
		this.SubDataType = subDataType;
		this.Feature = feature;
		this.DataPath = dataPath;
		this.fileName = fileName;
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
		SeleniumUtil.killJAVA();
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomeTask.JumpToUpLoad(driver);
	}

	@Before
	public void setUp() {
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		SeleniumUtil.killJAVA();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T00_Algorithm_DataUpload() {	
		LOG.info_testcase("场景描述:"+Scene);			
		DataCenterTask.dataSourceUpLoadUseCustomFolder(driver, RAT, DataType, SubDataType,Feature,DataPath,fileName, IndexPage.AlgorithmProject);
	}	

}
