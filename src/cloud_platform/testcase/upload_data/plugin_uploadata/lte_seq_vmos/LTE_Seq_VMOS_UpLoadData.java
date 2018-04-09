package cloud_platform.testcase.upload_data.plugin_uploadata.lte_seq_vmos;

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
import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_VMOS_Const;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_Seq_VMOS_UpLoadData {
	
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = ConstUrl.getProjectHomePath()+"\\Data\\baseline\\LTE_Seq_vMOS\\参数化表\\精品视频参数化表.xlsx";
	private static String SheetName = "数据上传";
	
	private String Scene;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String Feature;
	private String[] DataPath;
	private String FolderName;
	

	public LTE_Seq_VMOS_UpLoadData(String scene, String rAT, String dataType,
			String subDataType, String feature, String[] dataPath,
			String folderName) {
		super();
		Scene = scene;
		RAT = rAT;
		DataType = dataType;
		SubDataType = subDataType;
		Feature = feature;
		DataPath = dataPath;
		FolderName = folderName;
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
	public void T01_LTE_UpLoad_Normal() {	
		LOG.info_testcase("场景描述:"+Scene);	
		String[] revisedDataPath = DataCenterTask.reviseUpLoadPath(DataPath);
		DataCenterTask.dataSourceUpLoadUseCustomFolder(driver, RAT, DataType, SubDataType,Feature,revisedDataPath,FolderName,LTE_VMOS_Const.VMOS_Project);
		// 设置TMSS回填结果		
	}	

}
