package cloud_plugin.task.network_performance_analysis_center.capcacity_management;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.capcacity_management.CapcacityManagementPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskReportTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LanguageUtil;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;

public class CapcacityManagementTask {

	/**
	 * <b>Description:</b>新建容量管理任务
	 *
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void createNewTaskSetting(WebDriver driver, String taskName, String taskLanguage,
			String defaultWindowID, String[] cfgDate, String[] mmlDate, String[] prsDate, String prsDateModel,
			String[] u2000Date, String u2000DateModel, String enodbeFilePath) {

		NetworkAnalysisCenterTask.openCapcacityManagement(driver);
		CapcacityManagementPage.createNewTask(driver, taskName, taskLanguage);
		// 数据选择
		CapcacityManagementTask.setAnalysisDate(driver, defaultWindowID, cfgDate, mmlDate, prsDate, prsDateModel,
				u2000Date, u2000DateModel, enodbeFilePath);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CapcacityManagementPage.clickNextPageBtn(driver, 0);
		// 点击下一步 若是提示信息则断言退出
		long length = CommonJQ.length(driver, CapcacityManagementPage.FILECHOICEERRORMSG, true);
		if (length > 0) {
			String errorMes = CommonJQ.text(driver, CapcacityManagementPage.FILECHOICEERRORMSG, "");
			if (!StringUtils.equalsIgnoreCase(errorMes.trim(), "")) {
				Assert.fail(errorMes);
			}
		}
	}

	public static void createNewTaskSetting(WebDriver driver, String taskName, String defaultWindowID) {

		NetworkAnalysisCenterTask.openCapcacityManagement(driver);
		CapcacityManagementPage.createNewTask(driver, taskName, "en_US");
		// 数据选择
		CapcacityManagementTask.setAnalysisDate(driver, defaultWindowID, null, null, null, "", null, "", "");
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CapcacityManagementPage.clickNextPageBtn(driver, 0);
		// 点击下一步 若是提示信息则断言退出
		long length = CommonJQ.length(driver, CapcacityManagementPage.FILECHOICEERRORMSG, true);
		if (length > 0) {
			String errorMes = CommonJQ.text(driver, CapcacityManagementPage.FILECHOICEERRORMSG, "", true);
			if (!StringUtils.equalsIgnoreCase(errorMes.trim(),
					"配置数据不能为空MML报文不能为空PRS话统不能为空PRS话统模板不能为空U2000话统不能为空U2000话统模板不能为空")) {
				Assert.fail("全数据为空报错有问题");
			}
		}
	}

	/**
	 * <b>Description:</b>设置过滤参数
	 *
	 * @author lwx242612
	 * @param driver
	 * @param cellOrEnodeb
	 * @param ndicatorName
	 * @param ruleNum
	 * @param xnz
	 * @param timeGranularity
	 * @return void
	 */
	public static void setUpProblemRecognition(WebDriver driver, String cellOrEnodeb, String standardText, int ruleNum,
			String[] xnz, String[] timeGranularity) {
		CapcacityManagementPage.setUpProblemRecognition(driver, cellOrEnodeb, standardText, ruleNum, xnz,
				timeGranularity);
	}

	/**
	 * <b>Description:</b>设置分析数据
	 *
	 * @author lwx242612
	 * @param driver
	 * @param defaultWindowID
	 * @param cfgDate
	 *            配置数据
	 * @param mmlDate
	 *            MML报文
	 * @param prsDate
	 *            PRS话统
	 * @param prsDateModel
	 *            PRS话统模板
	 * @param u2000Date
	 *            U2000话统
	 * @param u2000DateModel
	 *            U2000话统模板
	 * @param enodbeFilePathENODBE列表
	 * @return void
	 */
	private static void setAnalysisDate(WebDriver driver, String defaultWindowID, String[] cfgDate, String[] mmlDate,
			String[] prsDate, String prsDateModel, String[] u2000Date, String u2000DateModel, String enodbeFilePath) {

		try {
			CommonJQ.waitForElement(driver, CapcacityManagementPage.SELECT_ENODEBCONFIGFILECONF_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwitchDriver.switchDriverToSEQ(driver);
		// 设置分析数据
		if (null != cfgDate && cfgDate.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, cfgDate, CapcacityManagementPage.SELECT_ENODEBCONFIGFILECONF_ID,
					defaultWindowID);
		}
		if (null != mmlDate && mmlDate.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, mmlDate, CapcacityManagementPage.SELECT_LTEPROJECTPARAMTERLTEMML_ID,
					defaultWindowID);
		}

		if (prsDate != null && prsDate.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, prsDate, CapcacityManagementPage.SELECT_LTEPROJECTPARAMTERPRSPFM_ID,
					defaultWindowID);
		}
		if (null != prsDateModel && !StringUtils.equals(prsDateModel, "")) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, CapcacityManagementPage.TEMPLATEFILEPRS_ID, true);
			CommonFile.ChooseOneFile(new File(prsDateModel).getAbsolutePath());
			CapcacityManagementPage.clickOKButton(driver);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (null != u2000Date && u2000Date.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, u2000Date,
					CapcacityManagementPage.SELECT_LTEPROJECTPARAMTERU2000TR_ID, defaultWindowID);
		}
		if (null != u2000DateModel && !StringUtils.equals(u2000DateModel, "")) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, CapcacityManagementPage.TEMPLATEFILEU2000_ID, true);
			CommonFile.ChooseOneFile(new File(u2000DateModel).getAbsolutePath());
			CapcacityManagementPage.clickOKButton(driver);
			SwitchDriver.switchDriverToSEQ(driver);
		}

		if (enodbeFilePath != null && !StringUtils.equals("", enodbeFilePath)) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, CapcacityManagementPage.TEMPLATEFILEOBJECT_ID, true);
			CommonFile.ChooseOneFile(new File(enodbeFilePath).getAbsolutePath());
			CapcacityManagementPage.clickOKButton(driver);
			SwitchDriver.switchDriverToSEQ(driver);
		}
	}

	public static void compareResultStatue(WebDriver driver, String taskName) {
		SwitchDriver.switchDriverToSEQ(driver);
		TaskReportTask.searchTask(driver, taskName);
		String gettaskStatus = CommonJQ.text(driver, TaskReportPage.TaskColumnText, "", 4);
		if (taskName.contains("AUTOFAIL")) {
			if (!StringUtils.equals(gettaskStatus, LanguageUtil.translate("Failed"))) {
				Assert.fail("期望状态:Failed" + "\t现状:" + gettaskStatus);
			}
		} else {
			if (!StringUtils.equals(gettaskStatus, LanguageUtil.translate("Succeeded"))) {
				Assert.fail("期望状态:Succeeded" + "\t现状:" + gettaskStatus);
			}
		}

	}

	public static void resultQuery(WebDriver driver, String taskName) {
		SwitchDriver.switchDriverToSEQ(driver);
		String taskId = TaskReportTask.searchTask(driver, taskName);
		// 进入任务详情
		CommonJQ.click(driver, "#" + taskId, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>任务详情对比
	 *
	 * @author lwx242612
	 * @param driver
	 * @param taskName
	 * @return void
	 */
	public static void resultCompare(WebDriver driver, String taskName, String cellStandardText, int cellRuleNum,
			String[] cellXnz, String[] cellTimeGranularity, String enodebStandardText, int enodebRuleNum,
			String[] enodebXnz, String[] enodebTimeGranularity) {

		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		String taskName1 = CapcacityManagementPage.getResultTaskName(driver, 0);
		String taskName2 = CapcacityManagementPage.getResultTaskName(driver, 1);
		if (!StringUtils.equals(taskName2, taskName1) && !StringUtils.equals(taskName1, taskName)) {
			Assert.fail("result taskName is not same");
		}
		// 单击参数设置
		CapcacityManagementPage.clickResultParamSetting(driver);
		// 获取小区级问题识别公式内容
		String cellText = CapcacityManagementPage.getCellDilatationTextl(driver);
		String enodebText = CapcacityManagementPage.getEnodebDilatationTextl(driver);

		if (cellStandardText != null && !StringUtils.equals(cellStandardText, cellText)) {
			Assert.fail("小区扩容标准公式 与 设置的不一样");
		}
		if (cellStandardText == null && !StringUtils.equals(cellText, "")) {
			Assert.fail("小区扩容标准公式 与 设置的不一样");
		}
		if (enodebStandardText != null && !StringUtils.equals(enodebText, enodebStandardText)) {
			Assert.fail("基站级扩容标准公式 与 设置的不一样");
		}
		if (enodebStandardText == null && !StringUtils.equals(enodebText, "")) {
			Assert.fail("基站级扩容标准公式 与 设置的不一样");
		}

		if (null != cellStandardText) {
			String[] cellxnz = CapcacityManagementPage.getXNZ(driver, "cell");
			if (!Arrays.equals(cellxnz, cellXnz)) {
				Assert.fail("小区过滤参数设置XNZ 与 设置不一致");
			}
			/*
			 * String[] cellTime =
			 * CapcacityManagementPage.getTimeGranularity(driver, "cell"); if
			 * (!Arrays.equals(cellTimeGranularity, cellTime)) { Assert.fail(
			 * "小区级过滤参数设置时间粒度 与 设置不一致"); }
			 */
		}

		if (null != enodebStandardText) {
			String[] enodebxnz = CapcacityManagementPage.getXNZ(driver, "enodeb");
			if (!Arrays.equals(enodebXnz, enodebxnz)) {
				Assert.fail("基站级过滤参数设置XNZ 与 设置不一致");
			}
			/*
			 * String[] enodebTime =
			 * CapcacityManagementPage.getTimeGranularity(driver, "enodeb"); if
			 * (Arrays.equals(enodebTimeGranularity, enodebTime)) { Assert.fail(
			 * "基站级过滤参数设置时间粒度 与 设置不一致"); }
			 */
		}

	}

	public static void clickFailureSelect(WebDriver driver) {
		String id = CapcacityManagementPage.clickFailureSelect(driver);
		CapcacityManagementPage.clickTaskRebuild(driver, id);
		CapcacityManagementPage.compartFirstStatue(driver);
	}

	public static void clickSuccessTaskIdAndDown(WebDriver driver) {
		CapcacityManagementPage.clickSuccessSelect(driver);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		CommonJQ.click(driver, "#showDownLoad", true);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, "Report.zip");

	}

	public static void clickSuccessAndDownBatches(WebDriver driver) {
		CommonJQ.click(driver, CapcacityManagementPage.STATUE_CSS, true, 0);
		CommonJQ.click(driver, CapcacityManagementPage.SUCCESS_ID, false);
		SeleniumUtil.click_ByCssSelector(driver, "span[id=\"gridcolumn-1020-textEl\"]");
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		CommonJQ.click(driver, "#downLoadReports", true);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, "Report.zip");

	}

	public static void clickALLESButton(WebDriver driver) {
		// 小区添加
		Pause.pause(2000);
		CommonJQ.click(driver, ".taskAddParamSetBtnAfterSel.ng-binding", false, 0);
		if (StringUtils.equals(CommonJQ.getValue(driver, "#cellDilatationText").trim(), "")) {
			Assert.fail("扩容标准ADD按钮失效");
		}

		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"Clear\")", true);

		if (!StringUtils.equals(CommonJQ.getValue(driver, "#cellDilatationText").trim(), "")) {
			Assert.fail("扩容标准CLEAR按钮失效");
		}

		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"AND\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"OR\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"(\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\")\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"=\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"≠\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\">\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"≥\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"<\")", true);
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"≤\")", true);

		String taskAddParamSetUl = "and  or ()=!=>>=<<=";

		if (!StringUtils.equals(CommonJQ.getValue(driver, "#cellDilatationText").trim(), taskAddParamSetUl)) {
			Assert.fail("扩容标准按钮失效 and  or ()=!=>>=<<=");
		}
		String taskAddParamSetUl2 = "and  or ()=!=>>=<";
		CommonJQ.click(driver, ".taskAddParamSetUl > li:contains(\"←\")", true);
		if (!StringUtils.equals(CommonJQ.getValue(driver, "#cellDilatationText").trim(), taskAddParamSetUl2)) {
			Assert.fail("扩容标准按钮失效 ← ");
		}

	}

}
