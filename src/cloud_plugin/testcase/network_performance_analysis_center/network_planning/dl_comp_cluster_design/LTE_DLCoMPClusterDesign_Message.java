package cloud_plugin.testcase.network_performance_analysis_center.network_planning.dl_comp_cluster_design;

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

import cloud_plugin.page.network_performance_analysis_center.network_planning.dl_comp_cluster_design.DLCoMPClusterDesignPage;
import cloud_plugin.task.network_performance_analysis_center.network_planning.dl_comp_cluster_design.DLCoMPClusterDesignTask;
import cloud_public.page.GetDataByTypePage;
import cloud_public.task.LoginTask;
import common.constant.constparameter.ConstUrl;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.SwitchDriver;

@GUITest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LTE_DLCoMPClusterDesign_Message {
	@Rule
	public TestName name = new org.junit.rules.TestName();

	private static WebDriver driver = null;
	private static String defaultWindowID = null;

	private static String BaselinePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_特性规划设计";
	private static String ResultPath = ConstUrl.getProjectHomePath() + "\\Data\\result\\L_特性规划设计";

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
	public void T001_NotSetTaskName() {

		String[] TaskType = { "现网评估" };
		DLCoMPClusterDesignTask.setServiceModule(driver, null, TaskType);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		String ExMessage = LanguageUtil.translate("特性规划设计", "assignment name can not be null");

		CommonJQ.assertTextPresent(driver, ExMessage);
		SwitchDriver.switchDriverToSEQ(driver);

	}

	@Test(GT3Kid = "")
	public void T002_SetTaskName_err() {

		String[] TaskType = { "现网评估" };
		DLCoMPClusterDesignTask.setServiceModule(driver, "%", TaskType);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		boolean Flage = CommonJQ.isExisted(driver, "#taskName", true);
		SwitchDriver.switchDriverToSEQ(driver);
		if (Flage == false) {
			Assert.fail("界面：\"任务名称不能包含\"" + "未显示");
		}

	}

	@Test(GT3Kid = "")
	public void T002_NotChooseData() {

		String[] TaskType = { "现网评估", "CA规划及预测" };
		DLCoMPClusterDesignTask.setServiceModule(driver, "aa", TaskType);
		DLCoMPClusterDesignTask.setNetData(driver, defaultWindowID, null, null, null, null, null, null, null);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		String ExCfgMessage = LanguageUtil.translate("特性规划设计", "Configuration data cannot be empty");
		String ExParaMessage = LanguageUtil.translate("特性规划设计", "Engineering parameters cannot be empty");
		String ExSigMessage = LanguageUtil.translate("特性规划设计", "SIG data cannot be empty");
		String ExPfmMessage = LanguageUtil.translate("特性规划设计", "Traffic Statistics can not be empty");
		String ExChrMessage = LanguageUtil.translate("特性规划设计", "Internal CHRs cannot be empty.");
		CommonJQ.assertTextPresent(driver, ExCfgMessage);
		CommonJQ.assertTextPresent(driver, ExParaMessage);
		CommonJQ.assertTextPresent(driver, ExSigMessage);
		CommonJQ.assertTextPresent(driver, ExPfmMessage);
		CommonJQ.assertTextPresent(driver, ExChrMessage);

		SwitchDriver.switchDriverToSEQ(driver);

	}

	@Test(GT3Kid = "")
	public void T003_NotChooseData2() {

		String[] TaskType = { "现网评估" };
		DLCoMPClusterDesignTask.setServiceModule(driver, "aa", TaskType);
		DLCoMPClusterDesignTask.setNetData(driver, defaultWindowID, null, null, null, null, null, null, null);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		String ExCfgMessage = LanguageUtil.translate("特性规划设计", "Configuration data cannot be empty");
		String ExParaMessage = LanguageUtil.translate("特性规划设计", "Engineering parameters cannot be empty");
		String ExSigMessage = LanguageUtil.translate("特性规划设计", "SIG data cannot be empty");
		String ExPfmMessage = LanguageUtil.translate("特性规划设计", "Traffic Statistics can not be empty");

		CommonJQ.assertTextPresent(driver, ExCfgMessage);
		CommonJQ.assertTextPresent(driver, ExParaMessage);
		CommonJQ.assertTextPresent(driver, ExSigMessage);
		CommonJQ.assertTextPresent(driver, ExPfmMessage);

		SwitchDriver.switchDriverToSEQ(driver);

	}

	@Test(GT3Kid = "")
	public void T004_NotChooseScenario() {

		String[] TaskType = { "现网评估" };
		DLCoMPClusterDesignTask.setServiceModule(driver, "aa", TaskType);
		String[] cfgFile = { "杭州李立鹏2个站" };
		String[] paraFile = { "LTE杭州李立鹏工参数据.xlsx" };
		String[] sigFile = { "杭州李立鹏2个站" };
		String[] pefFile = { "杭州李立鹏2个站" };
		DLCoMPClusterDesignTask.setNetData(driver, defaultWindowID, cfgFile, paraFile, sigFile, pefFile, null, null,
				null);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxCoverageScenario, true, "筛选开关：覆盖场景");
		SwitchDriver.switchDriverToSEQ(driver);
		// 提交任务
		DLCoMPClusterDesignPage.commitBtnClick(driver);

		SwitchDriver.switchDriverToFrame(driver, "//iframe", "参数设置页面");
		String ExMessage = LanguageUtil.translate("特性规划设计", "Please select at least one scenario.");
		CommonJQ.assertTextPresent(driver, ExMessage);
		SwitchDriver.switchDriverToSEQ(driver);

	}

	@Test(GT3Kid = "")
	public void T005_paraDataTemplateDownload() {
		String BaselineTemp = BaselinePath + "\\paraDataTemplate";
		String ResultTemp = ResultPath + "\\paraDataTemplate";

		FileHandle.delSubFile(ResultTemp);
		FileHandle.makeDirectory(ResultTemp);

		String[] TaskType = { "现网评估" };
		DLCoMPClusterDesignTask.setServiceModule(driver, "aa", TaskType);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, "#select_paraDataTemplateDownload", true);
		SwitchDriver.switchDriverToSEQ(driver);

		FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\EngineeringParameter.xlsx",
				ResultTemp + "\\EngineeringParameter.xlsx");

		// 结果对比
		Assert.assertTrue(CommonFile.fileReportMsg(ResultTemp),
				FileCompare.SameNameCompareInPath(BaselineTemp, ResultTemp));

	}

	@Test(GT3Kid = "")
	public void T006_cellListDataTemplateDownload() {
		String BaselineTemp = BaselinePath + "\\cellListDataTemplate";
		String ResultTemp = ResultPath + "\\cellListDataTemplate";

		FileHandle.delSubFile(ResultTemp);
		FileHandle.makeDirectory(ResultTemp);

		String[] TaskType = { "现网评估", "CA规划及预测" };
		DLCoMPClusterDesignTask.setServiceModule(driver, "aa", TaskType);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, "#select_cellListDataTemplateDownload", true);
		SwitchDriver.switchDriverToSEQ(driver);

		FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\CellList.xlsx", ResultTemp + "\\CellList.xlsx");

		// 结果对比
		Assert.assertTrue(CommonFile.fileReportMsg(ResultTemp),
				FileCompare.SameNameCompareInPath(BaselineTemp, ResultTemp));

	}

	@Test(GT3Kid = "")
	public void T007_defaultValue() {

		String[] TaskType = { "现网评估", "CA规划及预测" };
		DLCoMPClusterDesignTask.setServiceModule(driver, "aa", TaskType);
		String[] cfgFile = { "济南特性规划设计" };
		String[] paraFile = { "济南特性规划设计工参表.xlsx" };
		String[] sigFile = { "济南特性规划设计" };
		String[] pefFile = { "济南特性规划设计" };
		String[] chrFile = { "济南特性规划设计" };
		DLCoMPClusterDesignTask.setNetData(driver, defaultWindowID, cfgFile, paraFile, sigFile, pefFile, chrFile, null,
				null);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		for (int i = 0; i < 8; i++) {
			String ActValue = CommonJQ.getValue(driver, "#freqBand" + (i + 1) + "Id", "频段设置：频段" + (i + 1));
			String ExValue = "";
			if (i == 0) {
				ExValue = "38";
			} else if (i == 1) {
				ExValue = "38";
			} else if (i == 2) {
				ExValue = "40";
			} else {
				ExValue = "";
			}
			Assert.assertTrue("频段设置：频段" + (i + 1) + "缺省值错误，预期：" + ExValue + "实际：" + ActValue,
					ExValue.equalsIgnoreCase(ActValue));
		}

		for (int i = 0; i < 8; i++) {
			String ActValue = CommonJQ.getValue(driver, "#freqBandWidth" + (i + 1) + "Id", "频段设置 ：频段带宽" + (i + 1));
			String ExValue = "";
			if (i == 0) {
				ExValue = "20";
			} else if (i == 1) {
				ExValue = "20";
			} else if (i == 2) {
				ExValue = "20";
			} else {
				ExValue = "";
			}
			Assert.assertTrue("频段设置 ：频段带宽" + (i + 1) + "缺省值错误，预期：" + ExValue + "实际：" + ActValue,
					ExValue.equalsIgnoreCase(ActValue));
		}
		String ActValue_CATerminal = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextCATerminal,
				"门限设置：CA终端渗透率(>)");
		String ExValue_CATerminal = "2";
		Assert.assertTrue("门限设置：CA终端渗透率(>)" + "缺省值错误，预期：" + ExValue_CATerminal + "实际：" + ActValue_CATerminal,
				ExValue_CATerminal.equalsIgnoreCase(ActValue_CATerminal));

		String ActValue_AverageRate = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextAverageRate,
				"门限设置：小区平均下载速率(<)");
		String ExValue_AverageRate = "40";
		Assert.assertTrue("门限设置：小区平均下载速率(<)" + "缺省值错误，预期：" + ExValue_AverageRate + "实际：" + ActValue_AverageRate,
				ExValue_AverageRate.equalsIgnoreCase(ActValue_AverageRate));

		String ActValue_ServiceVolume = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextServiceVolume,
				"门限设置：业务量");
		String ExValue_ServiceVolume = "200";
		Assert.assertTrue("门限设置：业务量" + "缺省值错误，预期：" + ExValue_ServiceVolume + "实际：" + ActValue_ServiceVolume,
				ExValue_ServiceVolume.equalsIgnoreCase(ActValue_ServiceVolume));

		String ActValue_DLPRBUsage = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextDLPRBUsage, "门限设置：下行PRB利用率");
		String ExValue_DLPRBUsage = "65";
		Assert.assertTrue("门限设置：下行PRB利用率" + "缺省值错误，预期：" + ExValue_DLPRBUsage + "实际：" + ActValue_DLPRBUsage,
				ExValue_DLPRBUsage.equalsIgnoreCase(ActValue_DLPRBUsage));

		String ActValue_CoverRatio = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextCoverRatio,
				"共覆盖参数设置:共覆盖比例(%)");
		String ExValue_CoverRatio = "20";
		Assert.assertTrue("共覆盖参数设置:下行PRB利用率" + "缺省值错误，预期：" + ExValue_CoverRatio + "实际：" + ActValue_CoverRatio,
				ExValue_CoverRatio.equalsIgnoreCase(ActValue_CoverRatio));

		String ActValue_RsrpThreshoid = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextRsrpThreshoid,
				"共覆盖参数设置:有效RSRP门限(dBm)");
		String ExValue_RsrpThreshoid = "-105";
		Assert.assertTrue(
				"共覆盖参数设置:有效RSRP门限(dBm)" + "缺省值错误，预期：" + ExValue_RsrpThreshoid + "实际：" + ActValue_RsrpThreshoid,
				ExValue_RsrpThreshoid.equalsIgnoreCase(ActValue_RsrpThreshoid));

		String ActValue_MrThreshoid = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextMrThreshoid,
				"共覆盖参数设置:有效MR门限");
		String ExValue_MrThreshoid = "-140";
		Assert.assertTrue("共覆盖参数设置:有效MR门限" + "缺省值错误，预期：" + ExValue_MrThreshoid + "实际：" + ExValue_MrThreshoid,
				ExValue_MrThreshoid.equalsIgnoreCase(ActValue_MrThreshoid));

		String ActValue_Azimuth = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextAzimuth, "CA组参数设置:共站方位角偏差");
		String ExValue_Azimuth = "45";
		Assert.assertTrue("CA组参数设置:共站方位角偏差" + "缺省值错误，预期：" + ExValue_Azimuth + "实际：" + ActValue_Azimuth,
				ExValue_Azimuth.equalsIgnoreCase(ActValue_Azimuth));

		String ActValue_Excursion = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextExcursion,
				"CA组参数设置:共站允许偏移距离");
		String ExValue_Excursion = "50";
		Assert.assertTrue("CA组参数设置:共站允许偏移距离" + "缺省值错误，预期：" + ExValue_Excursion + "实际：" + ActValue_Excursion,
				ExValue_Excursion.equalsIgnoreCase(ActValue_Excursion));

		String ActValue_NotDistance = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextNotDistance,
				"CA组参数设置:不共站距离门限");
		String ExValue_NotDistance = "500";
		Assert.assertTrue("CA组参数设置:不共站距离门限" + "缺省值错误，预期：" + ExValue_NotDistance + "实际：" + ActValue_NotDistance,
				ExValue_NotDistance.equalsIgnoreCase(ActValue_NotDistance));

		String ActValue_PenetranceRatio = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextPenetranceRatio,
				"CA Zone预测参数设置:CA终端渗透比例(%)");
		String ExValue_PenetranceRatio = "10";
		Assert.assertTrue(
				"CA Zone预测参数设置:CA终端渗透比例(%)" + "缺省值错误，预期：" + ExValue_PenetranceRatio + "实际：" + ActValue_PenetranceRatio,
				ExValue_PenetranceRatio.equalsIgnoreCase(ActValue_PenetranceRatio));

		String ActValue_ActivateRatio = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextActivateRatio,
				"CA Zone预测参数设置:CA终端激活比例(%)");
		String ExValue_ActivateRatio = "40";
		Assert.assertTrue(
				"CA Zone预测参数设置:CA终端激活比例(%)" + "缺省值错误，预期：" + ExValue_ActivateRatio + "实际：" + ActValue_ActivateRatio,
				ExValue_ActivateRatio.equalsIgnoreCase(ActValue_ActivateRatio));

		String ActValue_Throughput = CommonJQ.getValue(driver, DLCoMPClusterDesignPage.TextThroughput,
				"CA Zone预测参数设置:吞吐量CA激活门限");
		String ExValue_Throughput = "10";
		Assert.assertTrue("CA Zone预测参数设置:吞吐量CA激活门限" + "缺省值错误，预期：" + ExValue_Throughput + "实际：" + ActValue_Throughput,
				ExValue_Throughput.equalsIgnoreCase(ActValue_Throughput));

		SwitchDriver.switchDriverToSEQ(driver);

	}
}
