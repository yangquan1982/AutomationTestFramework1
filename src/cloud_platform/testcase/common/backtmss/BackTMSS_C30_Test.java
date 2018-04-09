package cloud_platform.testcase.common.backtmss;

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

import common.constant.constparameter.ConstUrl;
import common.util.ParamUtil;
import common.util.tmss.StartBackFillTMSS;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class BackTMSS_C30_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static boolean result = false;

	private String TestCaseId;

	private static String ParaFile = ConstUrl.getProjectHomePath() + "\\Data\\TMSS回填\\TMSS回填参数化表.xlsx";
	private static String GT3KFile = ConstUrl.getProjectHomePath() + "\\Data\\TMSS回填\\GT3K.ini";

	public BackTMSS_C30_Test(String TestCaseId) {
		this.TestCaseId = TestCaseId;
	}

	@Parameters()
	public static Collection<Object[]> data() throws Exception {

		return Arrays.asList(ParamUtil.getObjectArr(ParaFile, "ContentEvaluate"));
	}

	@BeforeClass
	public static void setUpBeforeClass() {

	}

	@Before
	public void setUp() {
		result = false;

	}

	@After
	public void tearDown() {
		StartBackFillTMSS.backFill(TestCaseId, result, GT3KFile);
	}

	@AfterClass
	public static void tearDownAfterClass() {

	}

	@Test(GT3Kid = "")
	public void T001_ContentEvaluate() {
		// 设置TMSS回填结果
		result = true;
	}
}
