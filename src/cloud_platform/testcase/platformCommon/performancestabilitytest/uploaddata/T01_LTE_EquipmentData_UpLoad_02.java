package cloud_platform.testcase.platformCommon.performancestabilitytest.uploaddata;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.UpDataPage;
import cloud_public.page.IndexPage;
import cloud_public.task.HomeTask;
import cloud_public.task.IndexTask;
import cloud_public.task.LoginTask;
import common.util.CommonWD;
import common.util.LOG;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T01_LTE_EquipmentData_UpLoad_02 {

	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;

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
		// StartBackFillTMSS.backFill(TestCaseId,result);
		SeleniumUtil.killJAVA();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		SwitchDriver.closeOtherWin(driver, defaultWindowID);
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T00_LTE_SmokeTest() {
		LOG.info_testcase("场景描述:稳定性模型，1小时执行新建文件夹动作14次");
		// DataCenterTask.dataSourceUpLoad(driver, RAT, DataType,
		// SubDataType,Feature,DataPath);

		{
			// 新建文件夹
			String folderName = "稳定性测试新建文件夹_" + ZIP.GetTime("MMddHHmmss");
			IndexTask.changePrj(driver, IndexPage.StabilityTest);

			UpDataPage.selectRat(driver, "LTE");
			String[] subDataTypes = "配置数据".split(";");
			UpDataPage.selectDataType(driver, "设备侧数据", subDataTypes[0]);

			UpDataPage.createNewFolder(driver, folderName);
			UpDataPage.waitForMask(driver);
		}

	}

}
