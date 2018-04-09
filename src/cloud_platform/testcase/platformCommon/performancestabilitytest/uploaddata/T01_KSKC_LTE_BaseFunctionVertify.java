package cloud_platform.testcase.platformCommon.performancestabilitytest.uploaddata;

import org.apache.commons.lang.StringUtils;
import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_public.page.GetDataByTypePage;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import cloud_public.task.Task_KSKC;
import common.util.CommonWD;
import common.util.LOG;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T01_KSKC_LTE_BaseFunctionVertify {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private String secns;
	private String ProjectName;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String PartNeName;
	private String NeName;
	private String NoDataNeName;
	private String SearchText;

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomeTask.JumpToUpLoad(driver);
	}

	@Before
	public void setUp() {
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	/**
	 * 可是可查用户自定义验证
	 */
	@Test(GT3Kid = "")
	public void T001_LTE_KSKCVertify() {
		LOG.info_testcase("场景描述:" + secns);
		if (StringUtils.isBlank(PartNeName)) {
			PartNeName = null;
		}
		if (StringUtils.isBlank(NeName)) {
			NeName = null;
		}
		if (StringUtils.isBlank(NoDataNeName)) {
			NoDataNeName = null;
		}

		ProjectName = "中国北京北京电信LTEPlatform_1";
		RAT = "LTE";
		DataType = "设备侧数据";
		SubDataType = "配置数据";
		SearchText = "西安火车站-HLH-XAAO092TL";

		Task_KSKC.KSKC_StabilityTest(driver, ProjectName, RAT, DataType, SubDataType, SearchText);

	}

	/**
	 * 可是可查用户自定义验证
	 */
	@Test(GT3Kid = "")
	public void T002_LTE_KSKCVertify() {
		LOG.info_testcase("场景描述:" + secns);
		if (StringUtils.isBlank(PartNeName)) {
			PartNeName = null;
		}
		if (StringUtils.isBlank(NeName)) {
			NeName = null;
		}
		if (StringUtils.isBlank(NoDataNeName)) {
			NoDataNeName = null;
		}

		ProjectName = "中国北京北京电信LTEPlatform_1";
		RAT = "LTE";
		DataType = "设备侧数据";
		SubDataType = "配置数据";
		SearchText = "西安火车站-HLH-XAAO092TL";

		Task_KSKC.KSKC_StabilityTest_filterByDate(driver, ProjectName, RAT, DataType, SubDataType, SearchText);

	}

}
