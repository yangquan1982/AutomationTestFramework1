package cloud_platform.testcase.upload_data.kskc;

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

import cloud_public.page.GetDataByTypePage;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import cloud_public.task.Task_KSKC;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class KSKC_DataCountVertify {

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String ParaFile = "D:\\GENEXCloud_V3_P1\\Data\\KSKC\\参数化测试\\可视可查参数化表.xlsx";

	private String secns;
	private String ProjectName;
	private String RAT;
	private String DataType;
	private String SubDataType;
	private String CfgNeNumber;
	private String DataNeNumber;

	public KSKC_DataCountVertify(String secns, String projectName, String rat, String dataType, String subDataType,
			String neDate, String DataNeNumber) {
		this.secns = secns;
		this.ProjectName = projectName;
		this.RAT = rat;
		this.DataType = dataType;
		this.SubDataType = subDataType;
		this.CfgNeNumber = neDate;
		this.DataNeNumber = DataNeNumber;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.print("This is collection");
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "数据量检查"));
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
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	@After
	public void tearDown() {

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Test(GT3Kid = "")
	public void T001_LTE_可是可查() {
		LOG.info_testcase("场景描述:" + secns);
		Task_KSKC.KSKC_DataCountVertify(driver, ProjectName, RAT, DataType, SubDataType, CfgNeNumber, DataNeNumber);
	}

}
