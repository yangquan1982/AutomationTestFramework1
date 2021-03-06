package cloud_platform.testcase.upload_data.plugin_uploadata.compare_configuration_parameters;

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

import cloud_platform.task.upload_data.BatchUpDataTask;
import cloud_public.page.IndexPage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.AppParamUtil;
import common.util.CommonWD;
import common.util.LOG;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_Check_UploadData {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\配置参数对比核查";
	private static String ParaFile = FilePath + "\\参数化表\\配置参数核查与对比数据上传.xlsx";

	private static String projectName = IndexPage.LTE_Mobile;

	private String Scene;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String Feature;
	private String[] DataPath;
	private String Filename;

	/**
	 * @param scene
	 * @param rAT
	 * @param dataType
	 * @param subDataType
	 * @param feature
	 * @param dataPath
	 * @param filename
	 */
	public LTE_Check_UploadData(String Scene, String RAT, String DataType, String SubDataType, String Feature,
			String[] DataPath, String Filename) {
		super();
		this.Scene = Scene;
		this.RAT = RAT;
		this.DataType = DataType;
		this.SubDataType = SubDataType;
		this.Feature = Feature;
		this.DataPath = DataPath;
		this.Filename = Filename;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(ParaFile, "LTE核查"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SeleniumUtil.killJAVA();
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SeleniumUtil.killJAVA();
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		SwitchDriver.upLoadCloseOtherWin(driver, defaultWindowID);
	}

	@After
	public void tearDown() throws Exception {
		SeleniumUtil.killJAVA();
	}

	@Test(GT3Kid = "")
	public void test() {
		LOG.info_testcase("场景描述:" + Scene);
		BatchUpDataTask.dataSourceUpLoadUseCustomFolder(driver, RAT, DataType, SubDataType, Feature, DataPath, Filename,
				projectName);
	}

}
