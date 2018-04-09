package cloud_platform.testcase.upload_data.engineeringparametertemplate;

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
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.HomeTask;
import cloud_public.task.LoginTask;
import common.constant.system.SystemConstant;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.SeleniumUtil;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class T50_LTE_EngineeringParameter_TemplateDownLoad {

	private static boolean result = false;
	private static WebDriver driver = null;
	private static String defaultWindowID = null;
	private static String ParaFile = SystemConstant.UpLoadEngParamTable + "EngineeringParameterData_LTE.xlsx";

	private String secns;
	private String TestCaseId;
	private String TemplateName;
	private String FeatureName;
	private String BaseTemplatePath;

	public T50_LTE_EngineeringParameter_TemplateDownLoad(String secns, String testCaseId, String templateName,
			String featureName, String baseTemplatePath) {
		super();
		this.secns = secns;
		TestCaseId = testCaseId;
		TemplateName = templateName;
		FeatureName = featureName;
		BaseTemplatePath = baseTemplatePath;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		Collection<Object[]> coll = Arrays.asList(ParamUtil.getObjectArr(ParaFile, "LTE"));
		return coll;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		// 打开浏览器
		// 登陆
		// 加载进入数据上传界面
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
		HomeTask.JumpToUpLoad(driver);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.print("driver的值为:" + driver);
		if (driver != null) {
			driver.quit();
		}
	}

	@Before
	public void setUp() {
		result = false;
		try {
			// 关闭上一用例出现的冗余窗口
			GetDataByTypePage.closeWin(driver, defaultWindowID);
			HomeTask.JumpToUpLoad(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		StartBackFillTMSS.backFill(TestCaseId, result);
		SeleniumUtil.killJAVA();
	}

	@Test
	public void test() {
		// 开始业务操作
		LOG.info_testcase("场景描述：" + secns);
		DataCenterTask.engineerParameterTemplateDown(driver, TemplateName, FeatureName, BaseTemplatePath);
		result = true;
	}

}
