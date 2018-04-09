package cloud_plugin.task.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.basic_optimization.compare_configuration_parameters.CompareCfgParaPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.ScreenShot;
import common.util.SwitchDriver;
import common.util.ZIP;

public class CompareCfgParaTask {

	/**
	 * <b>Description:</b>创建同网元对比任务
	 * 
	 * @author lwx242612
	 * @param driver
	 * @param defaultWindowID
	 * @param Oldfile
	 * @param Newfile
	 * @return
	 * @return String 返回新建任务名称
	 */
	public static String Creat_SameNetCfgCompare_Task(WebDriver driver, String defaultWindowID, String taskName,
			String[] Oldfile, String[] Newfile) {
		NetworkAnalysisCenterTask.OpenLTECfgCompare(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CompareCfgParaPage.SAMECONTRASTTYPE_CHECKBOX, true);
		SwitchDriver.switchDriverToSEQ(driver);
		if (Oldfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Oldfile, CompareCfgParaPage.CONID1_ID, defaultWindowID);
		}
		if (Newfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Newfile, CompareCfgParaPage.CONID2_ID, defaultWindowID);
		}
		taskName = CompareCfgParaPage.setTaskName(driver, taskName);
		CompareCfgParaPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>下载PA报告，解压并移动到指定目录,下载按钮不同
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname：任务名
	 * @param ResultHome：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport_ForPA(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String reportName = downLoad_report_ForPA(driver, defaultWindowID, taskname);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
	}

	/**
	 * <b>Description:</b>下载报告ForPA
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname：任务名
	 * @return String：报告完整名称
	 */
	public static String downLoad_report_ForPA(WebDriver driver, String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#showDownLoad", true, "报告下载按钮");
		String reportName = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", true);
		reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}

	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname：任务名
	 * @param ResultHome：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String reportName = downLoad_report(driver, defaultWindowID, taskname);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
	}

	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname：任务名
	 * @return String：报告完整名称
	 */
	public static String downLoad_report(WebDriver driver, String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#reportDownLoad", true, "报告下载按钮");
		String reportName = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", true);
		reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}

	/**
	 * <b>Description:</b>创建同网元配置对比任务
	 * 
	 * @author lwx242612
	 * @param driver
	 * @param defaultWindowID
	 * @param Oldfile
	 * @param Newfile
	 * @return void
	 */
	public static void SameCfgCompare_ErrorMsg(WebDriver driver, String defaultWindowID, String[] Oldfile,
			String[] Newfile) {
		String ExOldErrMsg = LanguageUtil.translate("The original configuration file cannot be empty.");
		String ExNewErrMsg = LanguageUtil.translate("The new configuration file cannot be empty.");

		CompareCfgParaTask.Creat_SameNetCfgCompare_Task(driver, defaultWindowID, "SameNet", Oldfile, Newfile);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ((Oldfile.length != 0)) {
			String AcNewErrMsg = CommonJQ.text(driver, CompareCfgParaPage.ErrorMsg_CLASS, "", 0);
			if (!ExNewErrMsg.equals(AcNewErrMsg)) {
				ScreenShot.snapshot(driver, "CfgCompare_Open");
				Assert.fail("ActualValue:" + AcNewErrMsg + ",ExpectedValue:" + ExNewErrMsg);
			}
		} else if ((Newfile.length != 0)) {
			String AcOldErrMsg = CommonJQ.text(driver, CompareCfgParaPage.ErrorMsg_CLASS, "", 0);
			if (!ExOldErrMsg.equals(AcOldErrMsg)) {
				ScreenShot.snapshot(driver, "CfgCompare_Open");
				Assert.fail("ActualValue:" + AcOldErrMsg + ",ExpectedValue:" + ExOldErrMsg);
			}
		} else {
			String AcOldErrMsg = CommonJQ.text(driver, CompareCfgParaPage.ErrorMsg_CLASS, "", 0);
			if (!ExOldErrMsg.equals(AcOldErrMsg)) {
				ScreenShot.snapshot(driver, "CfgCompare_Open");
				Assert.fail("ActualValue:" + AcOldErrMsg + ",ExpectedValue:" + ExOldErrMsg);
			}
			String AcNewErrMsg = CommonJQ.text(driver, CompareCfgParaPage.ErrorMsg_CLASS, "", 1);
			if (!ExNewErrMsg.equals(AcNewErrMsg)) {
				ScreenShot.snapshot(driver, "CfgCompare_Open");
				Assert.fail("ActualValue:" + AcNewErrMsg + ",ExpectedValue:" + ExNewErrMsg);
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param Basefile
	 * @param Checkfile
	 * @param isRadioParameter
	 * @param filePath
	 * @return void
	 */
	public static void DiffNetCfgCompare_ErrorMsg(WebDriver driver, String defaultWindowID, String[] Basefile,
			String[] Checkfile, boolean isRadioParameter, String Para, String CustParafile) {
		String ExBaseErrMsg = LanguageUtil.translate("Only choose a base configuration.");
		String ExCheckErrMsg = LanguageUtil.translate("The path of the data to be compared cannot be empty.");
		String ExParaErrMsg = LanguageUtil.translate("Radio parameters must be set.");
		String ExCustParaErrMsg = LanguageUtil.translate("Import a user-defined template.");

		CompareCfgParaTask.Create_DiffNetCfgCompare(driver, defaultWindowID, "DiffNet", Basefile, Checkfile,
				isRadioParameter, Para, CustParafile);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ((Basefile.length == 0) && (Checkfile.length != 0)) {
			String ErrMsg = CommonJQ.text(driver, CompareCfgParaPage.ErrorMsg_CLASS, "", 0);
			if (!ExBaseErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:" + ExBaseErrMsg);
			}

		}
		if ((Checkfile.length == 0) && (Basefile.length != 0)) {
			String ErrMsg = CommonJQ.text(driver, CompareCfgParaPage.ErrorMsg_CLASS, "", 0);
			if (!ExCheckErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:" + ExCheckErrMsg);
			}
		}
		if ((Checkfile.length == 0) && (Basefile.length == 0)) {
			String AcBaseErrMsg = CommonJQ.text(driver, CompareCfgParaPage.ErrorMsg_CLASS, "", 0);
			String AcCheckErrMsg = CommonJQ.text(driver, CompareCfgParaPage.ErrorMsg_CLASS, "", 1);
			if (!ExBaseErrMsg.equals(AcBaseErrMsg)) {
				Assert.fail("ActualValue:" + AcBaseErrMsg + ",ExpectedValue:" + ExBaseErrMsg);
			}
			if (!ExCheckErrMsg.equals(AcCheckErrMsg)) {
				Assert.fail("ActualValue:" + AcCheckErrMsg + ",ExpectedValue:" + ExCheckErrMsg);
			}
		}
		if ((isRadioParameter == false) && ("".equals(CustParafile))) {
			String ErrMsg = CommonJQ.text(driver, CompareCfgParaPage.CheckParaMsg_ID, "", 0);
			if (!ExCustParaErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:" + ExCustParaErrMsg);
			}
		}
		if ((isRadioParameter == true) && ("".equals(Para))) {
			String ErrMsg = CommonJQ.text(driver, CompareCfgParaPage.WireMsg_ID, "", 0);
			if (!ExParaErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:" + ExParaErrMsg);
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 *
	 * @author sWX198085
	 * @param driver
	 * @param filePath
	 * @return void
	 */
	public static void CfgCompare_template_Exp(WebDriver driver, String filePath) {
		NetworkAnalysisCenterTask.OpenLTECfgCompare(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CompareCfgParaPage.DIFFCONTRASTTYPE_CHECKBOX, true);
		SwitchDriver.switchDriverToSEQ(driver);
		// 核查参数设置为 自定义参数
		CompareCfgParaPage.selectCellNameSelfRadio(driver);
		// 导出全参数模板
		CompareCfgParaPage.exportWireLessModel(driver, filePath);
	}

	/**
	 * <b>Description:</b>LTE不同网元配置对比 流程
	 * 
	 * @author lwx242612
	 * @param driver
	 * @param defaultWindowID
	 * @param isRadioParameter
	 *            核查参数设置 是否是 无线参数
	 * @return void
	 */
	public static String Create_DiffNetCfgCompare(WebDriver driver, String defaultWindowID, String taskName,
			String[] Basefile, String[] Checkfile, boolean isRadioParameter, String Para, String CustParafile) {
		NetworkAnalysisCenterTask.OpenLTECfgCompare(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);

		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CompareCfgParaPage.DIFFCONTRASTTYPE_CHECKBOX, true);
		SwitchDriver.switchDriverToSEQ(driver);
		// 选择基线数据
		if (Basefile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Basefile, CompareCfgParaPage.CONID3_ID, defaultWindowID);
		}
		if (Checkfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Checkfile, CompareCfgParaPage.CONID4_ID, defaultWindowID);
		}

		// 选择待对比数据
		if (isRadioParameter) {
			// 核查参数设置为 无线参数

			if ("All".equalsIgnoreCase(Para)) {
				CompareCfgParaPage.clickWireLessAllToRight(driver);
			} else if (Para == null) {

			} else {
				CompareCfgParaPage.searchValue(driver, Para);
				CompareCfgParaPage.clickWireLessAllToRight(driver);
			}

		} else {
			// 核查参数设置为 自定义参数
			CompareCfgParaPage.selectCellNameSelfRadio(driver);

			// 导入
			if (CustParafile != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, CompareCfgParaPage.WIREIMPORT_ID, true);
				SwitchDriver.switchDriverToSEQ(driver);
				CompareCfgParaPage.importSelfModelFile(driver, CustParafile);
			}

		}
		taskName = CompareCfgParaPage.setTaskName(driver, taskName);
		// 提交
		CompareCfgParaPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 *
	 * @author sWX198085
	 * @param driver
	 * @param taskName
	 * @return void
	 */
	public static void Creat_CancleTask(WebDriver driver, String taskName) {
		NetworkAnalysisCenterTask.OpenLTECfgCompare(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		taskName = CompareCfgParaPage.setTaskName(driver, taskName);
		CompareCfgParaPage.cancelBtnClick(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
	}

	/**
	 * <b>Description:</b>
	 *
	 * @author sWX198085
	 * @param driver
	 * @param taskName
	 * @return void
	 */
	public static void Creat_CancleTask2(WebDriver driver, String taskName) {
		NetworkAnalysisCenterTask.OpenLTECfgCompare(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		taskName = CompareCfgParaPage.setTaskName(driver, taskName);
		CompareCfgParaPage.cancelTitleClick(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
	}
}
