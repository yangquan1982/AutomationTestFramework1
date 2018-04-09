package cloud_platform.testcase.upload_data.plugin_uploadata.capcacitymgt;

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
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class capcacitymgt_UpLoadData {
	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\CapcacityManagement\\参数化表\\"
			+ "容量管理数据上传.xlsx";
	private static String SheetName = "容量管理";
	private String projectName = IndexPage.LTE_Mobile;
	private static String GT3KFile = ConstUrl.getProjectHomePath()
			+ "\\Data\\baseline\\CapcacityManagement\\参数化表\\GT3K.ini";

	private String Scene;
	private String TestCaseId;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String Feature;
	private String[] DataPath;
	private String Filename;

	public capcacitymgt_UpLoadData(String scene, String testCaseId, String rAT, String dataType, String subDataType,
			String feature, String[] DataPath, String Filename) {

		Scene = scene;
		TestCaseId = testCaseId;
		RAT = rAT;
		DataType = dataType;
		SubDataType = subDataType;
		Feature = feature;
		this.DataPath = DataPath;
		this.Filename = Filename;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("Parameter Excel Initial");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, SheetName));
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
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
		SeleniumUtil.killJAVA();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void capcacitymgt_Normal() {
		LOG.info_testcase("场景描述:" + Scene);
		DataCenterTask.dataSourceUpLoadUseCustomFolder(driver, RAT, DataType, SubDataType, Feature, DataPath, Filename,
				projectName);
		// 设置TMSS回填结果
		result = true;
	}

}
