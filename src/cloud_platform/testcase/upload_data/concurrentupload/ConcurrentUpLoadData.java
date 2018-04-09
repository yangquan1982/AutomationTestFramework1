package cloud_platform.testcase.upload_data.concurrentupload;

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
import common.pobjec.ProjectUpDataInfor;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ReadNotes;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class ConcurrentUpLoadData {

	private static boolean result = false;
	private static boolean ProjectUpLoadComplete = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = SystemConstant.ConcurrentPath + "ConcurrentUpLoadParamTable.xlsx";
	private static String SheetName = "UpLoadData";
	private static String ProjectSheetName = "ProjectInformation";
	private static ProjectUpDataInfor PrjInfor = ReadNotes.getNotUpLoadProject(ParaFile, ProjectSheetName);

	private String projectName;
	private String[] prjnums;
	private String Scene;
	private String TestCaseId;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String Feature;
	private String[] DataPath;
	private String[] checkInfor;

	public ConcurrentUpLoadData(String[] prjnums, String scene, String testCaseId, String rAT, String dataType,
			String subDataType, String feature, String[] dataPath, String[] checkInfor) {
		super();
		this.prjnums = prjnums;
		Scene = scene;
		TestCaseId = testCaseId;
		RAT = rAT;
		DataType = dataType;
		SubDataType = subDataType;
		Feature = feature;
		DataPath = dataPath;
		this.checkInfor = checkInfor;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println("Parameter Excel Initial");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, SheetName, PrjInfor.getPrjnum()));
		return coll;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		// SeleniumUtil.killJAVA();
		// driver = CommonWD.getWebDriver();
		// defaultWindowID = driver.getWindowHandle();
		// LoginTask.login(driver);
		// HomeTask.JumpToUpLoad(driver);
	}

	@Before
	public void setUp() {
		SeleniumUtil.killJAVA();
		if (driver != null) {
			driver.quit();
		}
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomeTask.JumpToUpLoad(driver);
		result = false;
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
	}

	@After
	public void tearDown() {
		// StartBackFillTMSS.backFill(TestCaseId,result);
		// if(result==true){
		// ReadNotes.setProjectState(ParaFile, ProjectSheetName, PrjInfor);
		// }
		SeleniumUtil.killJAVA();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
		// 修改工程配置文件上传状态

		ReadNotes.setProjectState(ParaFile, ProjectSheetName, PrjInfor);

		SeleniumUtil.killJAVA();
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T01_ConCurrentUpLoadTest() {
		LOG.info_testcase("场景描述:" + Scene);
		if (RAT == null || DataType == null) {
			result = true;
			return;
		}
		DataCenterTask.dataSourceUpLoadConCurrentTest(driver, RAT, DataType, SubDataType, Feature, DataPath,
				PrjInfor.getPrjname());
		// 设置TMSS回填结果
		result = true;

	}

}
