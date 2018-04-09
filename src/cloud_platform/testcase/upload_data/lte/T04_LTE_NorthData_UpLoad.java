package cloud_platform.testcase.upload_data.lte;

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
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.system.SystemConstant;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T04_LTE_NorthData_UpLoad {
	
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = SystemConstant.UpLoadParam+"LTE_Normal_UpLoad_ParamTable.xlsx";
	private static String SheetName = "LTE_NorthData";
	
	private String Scene;
	private String TestCaseId;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String Feature;
	private String[] DataPath;private String[] checkInfor;
	
	
	public T04_LTE_NorthData_UpLoad(String scene, String testCaseId,
			String rAT, String dataType, String subDataType, String feature,
			String[] dataPath,String[] checkInfor) {
		super();
		Scene = scene;
		TestCaseId = testCaseId;
		RAT = rAT;
		DataType = dataType;
		SubDataType = subDataType;
		Feature = feature;
		DataPath = dataPath;this.checkInfor = checkInfor;
	}


	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("Parameter Excel Initial");
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
		result = false;
		SwitchDriver.upLoadCloseOtherWin(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(TestCaseId,result);
		SeleniumUtil.killJAVA();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		SwitchDriver.upLoadCloseOtherWin(driver, defaultWindowID);
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T01_LTE_UpLoad_Normal() {	
		LOG.info_testcase("场景描述:"+Scene);			
		DataCenterTask.dataSourceUpLoad(driver, RAT, DataType, SubDataType,Feature,DataPath,checkInfor);
		// 设置TMSS回填结果		
		result = true;
	}	

}
