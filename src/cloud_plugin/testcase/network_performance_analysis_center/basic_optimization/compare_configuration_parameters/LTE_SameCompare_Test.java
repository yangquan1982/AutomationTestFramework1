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
import common.util.ZIP;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class LTE_SameCompare_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	// Data path
	private static String[] Oldfile = ConstUrl.getTargetValue("Compare_CfgFileFirst").split("/");
	private static String[] Newfile = ConstUrl.getTargetValue("Compare_CfgFileSecond").split("/");
	private static String[] Oldfile_empty = {};
	private static String[] Newfile_empty = {};

	@SuppressWarnings("static-access")
	public LTE_SameCompare_Test(String[] Oldfile, String[] Newfile) {
		this.Oldfile = Oldfile;
		this.Newfile = Newfile;
	}

	@Parameters()
	public static Collection<Object[]> data() {
		/**
		 * 1.Oldfile is empty,newfile is not empty 2.Oldfile is not
		 * empty,newfile is empty 3.Oldfile is empty,newfile is empty
		 */
		return Arrays.asList(new Object[][] { { Oldfile_empty, Newfile }, { Oldfile, Newfile_empty },
				{ Oldfile_empty, Newfile_empty } });
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

	@Test(GT3Kid = "网络性能分析中心.配置参数对比.LTE同网元配置对比")
	public void T001_LTESameNet_MsgErr() {
		System.out.println(ZIP.NowTime() + "Start the testcase:" + name.getMethodName());
		CompareCfgParaTask.SameCfgCompare_ErrorMsg(driver, defaultWindowID, Oldfile, Newfile);
		System.out.println(ZIP.NowTime() + "End the testcase:" + name.getMethodName());
	}
}
