package cloud_plugin.testcase.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

import java.util.Arrays;
import java.util.Collection;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.task.network_performance_analysis_center.basic_optimization.compare_configuration_parameters.CompareCfgParaTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonWD;
import common.util.LOG;
import common.util.ParamUtil;
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_DiffNetCfgCompare_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	// Data path
	private static String ParaFile = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\Compare\\参数化表\\DiffNetCfg.xlsx";
	private String secns;
	private String[] Basefile;
	private String[] Checkfile;
	private boolean isRadioParameter;
	private String Para;
	private String CustParafile;

	public LTE_DiffNetCfgCompare_Test(String secns, String[] Basefile, String[] Checkfile, boolean isRadioParameter,
			String Para, String CustParafile) {
		this.secns = secns;
		this.Basefile = Basefile;
		this.Checkfile = Checkfile;
		this.isRadioParameter = isRadioParameter;
		this.Para = Para;
		this.CustParafile = CustParafile;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		/**
		 * 1.Basefile is empty,Checkfile is not empty 2.Basefile is not
		 * empty,Checkfile is empty 3.Basefile is empty,Checkfile is empty
		 * 4.Para is empty 5.CustParafile is empty
		 */

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "Message"));
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = CommonWD.getWebDriver();
		defaultWindowID = driver.getWindowHandle();
		LoginTask.login(driver);
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
	public void T001_DiffNetCfg_MsgErr() {
		LOG.info_testcase("场景描述:" + secns);
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		CompareCfgParaTask.DiffNetCfgCompare_ErrorMsg(driver, defaultWindowID, Basefile, Checkfile, isRadioParameter,
				Para, CustParafile);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
