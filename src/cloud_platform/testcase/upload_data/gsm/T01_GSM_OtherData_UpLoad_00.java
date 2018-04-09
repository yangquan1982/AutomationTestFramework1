package cloud_platform.testcase.upload_data.gsm;

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
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T01_GSM_OtherData_UpLoad_00 {

	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = SystemConstant.UpLoadParam + "SmokeTest_GSM.xlsx";
	private static String SheetName = "GSM_Other_00";

	private String Scene;
	private String TestCaseId;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String Feature;
	private String[] DataPath;

	public T01_GSM_OtherData_UpLoad_00(String scene, String testCaseId, String rAT, String dataType, String subDataType,
			String feature, String[] dataPath) {
		super();
		Scene = scene;
		TestCaseId = testCaseId;
		RAT = rAT;
		DataType = dataType;
		SubDataType = subDataType;
		Feature = feature;
		DataPath = dataPath;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.print("This is collection");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, SheetName));
		return coll;
	}

	@BeforeClass
	public static void setUpBeforeClass() {

		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomeTask.JumpToUpLoad(driver);
	}

	@Before
	public void setUp() {
		result = false;
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		// StartBackFillTMSS.backFill(TestCaseId,result);

	}

	@AfterClass
	public static void tearDownAfterClass() {
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T001_TDS_KSKCDefaultDisPlayVertify() {
		LOG.info_testcase("场景描述:" + Scene);
		DataCenterTask.dataSourceUpLoad(driver, RAT, DataType, SubDataType, Feature, DataPath);
		// 设置TMSS回填结果
		result = true;
	}

}
