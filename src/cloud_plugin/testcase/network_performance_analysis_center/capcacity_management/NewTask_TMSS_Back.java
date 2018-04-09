package cloud_plugin.testcase.network_performance_analysis_center.capcacity_management;

import java.util.Arrays;
import java.util.Collection;

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

import common.util.tmss.StartBackFillTMSS;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class NewTask_TMSS_Back {

	private static boolean result = false;

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static String taskId;
	private static String secs;

	public NewTask_TMSS_Back(String taskId, String secs) {
		this.taskId = taskId;
		this.secs = secs;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {
		System.out.println();
		Object[][] param = new Object[][] {
				{ "T001_HW_UMTS_AccessAnalysis_NeAll_Day_RABSetupFailure_SrvAll_FrqAll_EnvAll", "" },
				{ "T002_HW_UMTS_AccessAnalysis_NeAll_Hour_RABSetupFailure_SrvAll_FrqAll_Env2", "" } };
		return Arrays.asList(param);
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("@BeforeClass");
	}

	@Before
	public void setUp() {
		result = false;
		System.out.println("@Before");
	}

	@After
	public void tearDown() {
		System.out.println("@After");
		StartBackFillTMSS.backFill(taskId, result);
		System.out.println("tmss end xx");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("@AfterClass");
	}

	@Test
	public void T001_L_CancleTask() {
		System.out.println("@Test");
		System.out.println(taskId);
		// Assert.fail("");
		result = true;
	}

}
