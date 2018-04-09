package cloud_plugin.testcase.network_performance_analysis_center.network_planning.volte_coverage_evaluation;

import org.fest.swing.annotation.GUITest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.volte_coverage_evaluation.VolteCoverageEvaluationPage;
import cloud_plugin.task.network_performance_analysis_center.network_planning.volte_coverage_evaluation.VolteCoverageEvaluationTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Volte_Coverage_Evaluation_DataChoose_Test {

	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String BaselinePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\VoLTE覆盖评估";

	private static String ResultPath = ConstUrl.getProjectHomePath() + "\\Data\\result\\VoLTE覆盖评估";

	// Data path

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
	public void T001_NotTaskType() {

		String[] TaskType = {};
		VolteCoverageEvaluationTask.setServiceModule(driver, "Test", "FDD", "LTE->UMTS", "HW", TaskType, null);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		String message = CommonJQ.text(driver,
				"div[class=\"messager-body panel-body panel-body-noborder window-body\"]",
				"div[style=\"margin: 0 0 0 47px;\"]");
		SwitchDriver.switchDriverToSEQ(driver);

		Assert.assertTrue("期望值:请选择任务类型" + ",实际值:" + message, "请选择任务类型".equals(message));
	}

	@Test(GT3Kid = "")
	public void T002_NotNet() {

		VolteCoverageEvaluationTask.ChooseData_ErrorMsg(driver, defaultWindowID, null, null, null, null, null, null,
				null, null, null, null);

	}

	@Test(GT3Kid = "")
	public void T003_TaskCancel() {

		String[] TaskType = { "VoLTE业务覆盖率分析" };
		VolteCoverageEvaluationTask.setServiceModule(driver, "Test", "FDD", "LTE->UMTS", "HW", TaskType, null);
		VolteCoverageEvaluationPage.cancelBtnClick(driver);
		VolteCoverageEvaluationTask.GetAppName(driver);
	}

	@Test(GT3Kid = "")
	public void T004_DataCancel() {

		String[] TaskType = { "VoLTE业务覆盖率分析" };
		VolteCoverageEvaluationTask.setServiceModule(driver, "Test", "FDD", "LTE->UMTS", "HW", TaskType, null);
		String[] LTEsigFile = { "VoLTE覆盖评估" };
		if (LTEsigFile != null) {
			if (LTEsigFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, LTEsigFile,
						"$('iframe[name=main]').contents().find('#select_MrData').eq(0).click()", defaultWindowID);
			}
		}
		VolteCoverageEvaluationPage.nextBtnClick(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		boolean flage = CommonJQ.isExisted_siblings(driver, "input[name=\"lteTaskBean.mrDataNum\"]",
				"span[class=\"fileChoiceErrorMsg ng-binding\"]");
		if (flage == true) {
			Assert.fail("");
		}
		CommonJQ.siblingsClick(driver, "input[name=\"lteTaskBean.mrDataNum\"]", "span[ng-click=\"fileCancel(item)\"]");
		SwitchDriver.switchDriverToSEQ(driver);
		VolteCoverageEvaluationPage.nextBtnClick(driver);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		flage = CommonJQ.isExisted_siblings(driver, "input[name=\"lteTaskBean.mrDataNum\"]",
				"span[class=\"fileChoiceErrorMsg ng-binding\"]");
		if (flage == false) {
			Assert.fail("分析数据 :数据源取消功能失效.");
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	@Test(GT3Kid = "")
	public void T005_LTE_PrfTemplate() {
		String BaselineHome = BaselinePath + "\\LTE话统模板";
		String ResultHome = ResultPath + "\\LTE话统模板";

		VolteCoverageEvaluationTask.downLoad_Template(driver, "LTE->UMTS", "LTE", ResultHome);
		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome),
				FileCompare.SameNameCompareInPath(BaselineHome, ResultHome));
	}

	@Test(GT3Kid = "")
	public void T006_UMTS_PrfTemplate() {
		String BaselineHome = BaselinePath + "\\UMTS话统模板";
		String ResultHome = ResultPath + "\\UMTS话统模板";

		VolteCoverageEvaluationTask.downLoad_Template(driver, "LTE->UMTS", "UMTS", ResultHome);

		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome),
				FileCompare.SameNameCompareInPath(BaselineHome, ResultHome));
	}
}
