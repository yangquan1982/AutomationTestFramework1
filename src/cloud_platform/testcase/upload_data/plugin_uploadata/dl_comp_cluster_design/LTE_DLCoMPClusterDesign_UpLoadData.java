package cloud_platform.testcase.upload_data.plugin_uploadata.dl_comp_cluster_design;

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
public class LTE_DLCoMPClusterDesign_UpLoadData {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String FilePath = ConstUrl.getProjectHomePath()
			+ "\\Data\\baseline\\L_特性规划设计";
	private static String ParaFile = FilePath + "\\参数化表\\特性规划设计数据上传.xlsx";

	private String projectName = IndexPage.LTE_Mobile;

	private String Scene;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String Feature;
	private String[] DataPath;
	private String Filename;

	public LTE_DLCoMPClusterDesign_UpLoadData(String scene, String rAT,
			String dataType, String subDataType, String feature,
			String[] DataPath, String Filename) {
		this.Scene = scene;
		this.RAT = rAT;
		this.DataType = dataType;
		this.SubDataType = subDataType;
		this.Feature = feature;
		this.DataPath = DataPath;
		this.Filename = Filename;

	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(AppParamUtil.getObjectArr(
				ParaFile, "数据"));
	}
	@BeforeClass
	public static void setUpBeforeClass() {
		SeleniumUtil.killJAVA();
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
	}

	@Before
	public void setUp() {
		SwitchDriver.upLoadCloseOtherWin(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		SeleniumUtil.killJAVA();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		SeleniumUtil.killJAVA();
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void LTE_UpLoad() {
		LOG.info_testcase("场景描述:" + Scene);

		BatchUpDataTask.dataSourceUpLoadUseCustomFolder(driver, RAT, DataType,
				SubDataType, Feature, DataPath, Filename, projectName);
	}
}
