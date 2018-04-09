package cloud_plugin.task.network_evaluation_assurance_center.lte_kpi_report;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_evaluation_assurance_center.lte_kpi_report.LTEKPIReportPage;
import cloud_plugin.task.network_evaluation_assurance_center.NetworkSafeGuardCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class LTEKPIReportTask {

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param GTLQuaFlage
	 * @param GTLTrendFlage
	 * @param MobileLTEFlage
	 * @param MobileMBBFlage
	 * @param Datafile
	 * @return
	 * @return String
	 */
	public static String creatKPIReportTask(WebDriver driver, String defaultWindowID, String taskName,
			boolean GTLQuaFlage, boolean GTLTrendFlage, boolean MobileLTEFlage, boolean MobileMBBFlage,
			String[] Datafile) {
		// 打开 LTE KPI报告
		NetworkSafeGuardCenterTask.openLKPIReportChinaMobile(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		// 选中任务名称
		taskName = LTEKPIReportPage.setTaskName(driver, taskName);
		// 选择报告类型
		LTEKPIReportTask.reportType(driver, "移动月报", GTLQuaFlage, GTLTrendFlage, MobileLTEFlage, MobileMBBFlage);
		// 选择分析数据
		if (Datafile != null) {
			if ("MAX".equalsIgnoreCase(Datafile[0])) {
				String[] DatafileMax = {};
				GetDataByTypeTask.chooseTimeFolder(driver, DatafileMax, LTEKPIReportPage.Data_ID, defaultWindowID);
			} else {
				GetDataByTypeTask.chooseTimeFolder(driver, Datafile, LTEKPIReportPage.Data_ID, defaultWindowID);
			}
		}
		// 提交任务
		LTEKPIReportPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName_Star
	 * @param Datafile
	 * @return
	 * @return String
	 */

	public static String creatKPIReportTask(WebDriver driver, String defaultWindowID, String taskName_Star,
			String[] Datafile) {
		// 打开 LTE KPI报告
		NetworkSafeGuardCenterTask.openLKPIReportChinaMobile(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 选中任务名称
		String taskName = LTEKPIReportPage.setTaskName(driver, taskName_Star);
		// 选择报告类型
		LTEKPIReportTask.reportType(driver, "MobileMonth", true, true, true, true);
		// 选择分析数据
		GetDataByTypeTask.chooseTimeFolder(driver, Datafile, LTEKPIReportPage.Data_ID, defaultWindowID);

		// 提交任务
		LTEKPIReportPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName_Star
	 * @param Datafile
	 * @return
	 * @return String
	 */

	public static String creatKPIReportTelecomTask(WebDriver driver, String defaultWindowID, String Type,
			String taskName_Star, String[] Datafile) {
		// 打开 LTE KPI报告
		NetworkSafeGuardCenterTask.openLKPIReportChinaTelecom(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 选中任务名称
		String taskName = LTEKPIReportPage.setTaskName(driver, taskName_Star);
		// 选择报告类型
		LTEKPIReportTask.reportType(driver, Type, false, false, false, false);
		// 选择分析数据
		if (Datafile != null) {
			if ("MAX".equalsIgnoreCase(Datafile[0])) {
				String[] DatafileMax = {};
				GetDataByTypeTask.chooseTimeFolder(driver, DatafileMax, LTEKPIReportPage.Data_ID, defaultWindowID);
			} else {
				GetDataByTypeTask.chooseTimeFolder(driver, Datafile, LTEKPIReportPage.Data_ID, defaultWindowID);
			}
		}

		// 提交任务
		LTEKPIReportPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName_Star
	 * @param Datafile
	 * @return
	 * @return String
	 */

	public static String creatKPIReportUnicomTask(WebDriver driver, String defaultWindowID, String taskName_Star,
			String[] Datafile) {
		// 打开 LTE KPI报告
		NetworkSafeGuardCenterTask.openLKPIReportChinaUnicom(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 选中任务名称
		String taskName = LTEKPIReportPage.setTaskName(driver, taskName_Star);
		// 选择报告类型
		LTEKPIReportTask.reportType(driver, "联通周报", false, false, false, false);
		// 选择分析数据
		if (Datafile != null) {
			if ("MAX".equalsIgnoreCase(Datafile[0])) {
				String[] DatafileMax = {};
				GetDataByTypeTask.chooseTimeFolder(driver, DatafileMax, LTEKPIReportPage.Data_ID, defaultWindowID);
			} else {
				GetDataByTypeTask.chooseTimeFolder(driver, Datafile, LTEKPIReportPage.Data_ID, defaultWindowID);
			}
		}

		// 提交任务
		LTEKPIReportPage.commitBtnClick(driver);
		return taskName;
	}

	public static void LKpiReport_ErrorMsg(WebDriver driver, String defaultWindowID, String taskName,
			boolean ReportFlage, String[] Datafile) {

		String taskNameErrMsg = "\n                " + LanguageUtil.translate("The task name can not be empty")
				+ "\n            ";
		String ReportTypeErrMsg = LanguageUtil.translate("Report Type cannot be empty");
		String DataErrMsg = LanguageUtil.translate("Data Choice cannot be empty");
		// 打开 LTE KPI报告
		NetworkSafeGuardCenterTask.openLKPIReportChinaMobile(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 选中任务名称
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, LTEKPIReportPage.TaskName_ID, taskName);
		SwitchDriver.switchDriverToSEQ(driver);
		// 选择报告类型
		LTEKPIReportTask.reportType(driver, "移动月报", ReportFlage, ReportFlage, ReportFlage, ReportFlage);
		// 选择分析数据
		if (Datafile.length != 0) {
			GetDataByTypeTask.chooseTimeFolder(driver, Datafile, LTEKPIReportPage.Data_ID, defaultWindowID);
		}
		// 提交任务
		LTEKPIReportPage.commitBtnClick(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("".equals(taskName)) {
			String AcErrMsg = CommonJQ.text(driver, LTEKPIReportPage.TaskNameMsg_ID, "", 0);
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + taskNameErrMsg,
					taskNameErrMsg.equals(AcErrMsg));
		}
		if (ReportFlage == false) {
			String AcErrMsg = CommonJQ.text(driver, LTEKPIReportPage.ReportMsg_CLASS, "", 0);
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ReportTypeErrMsg,
					ReportTypeErrMsg.equals(AcErrMsg));
		}
		if (Datafile.length == 0) {
			String AcErrMsg = CommonJQ.text(driver, LTEKPIReportPage.Msg_CLASS, "", 0);
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + DataErrMsg, DataErrMsg.equals(AcErrMsg));
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param GTLQuaFlage
	 * @param GTLTrendFlage
	 * @param MobileLTEFlage
	 * @param MobileMBBFlage
	 * @return void
	 */
	private static void reportType(WebDriver driver, String Type, boolean GTLQuaFlage, boolean GTLTrendFlage,
			boolean MobileLTEFlage, boolean MobileMBBFlage) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("移动月报".equalsIgnoreCase(Type)) {
			int Month = CommonJQ.length(driver, "ul[id=\"mshowtask.modelType\"] li");
			for (int i = 0; i < 5 && Month < 1; i++) {
				Pause.pause(1000);
				Month = CommonJQ.length(driver, "ul[id=\"mshowtask.modelType\"] li");
			}
			CommonJQ.click(driver, LTEKPIReportPage.Type, true);
			CommonJQ.click(driver, LTEKPIReportPage.MobileMonth, true);
		} else if ("电信周报".equalsIgnoreCase(Type)) {
			int Month = CommonJQ.length(driver, "ul[id=\"mshowtask.modelType\"] li");
			for (int i = 0; i < 5 && Month < 2; i++) {
				Pause.pause(1000);
				Month = CommonJQ.length(driver, "ul[id=\"mshowtask.modelType\"] li");
			}
			CommonJQ.click(driver, LTEKPIReportPage.Type, true);
			CommonJQ.click(driver, LTEKPIReportPage.TelecomWeek, true);
		} else if ("电信月报".equalsIgnoreCase(Type)) {
			int Month = CommonJQ.length(driver, "ul[id=\"mshowtask.modelType\"] li");
			for (int i = 0; i < 5 && Month < 2; i++) {
				Pause.pause(1000);
				Month = CommonJQ.length(driver, "ul[id=\"mshowtask.modelType\"] li");
			}
			CommonJQ.click(driver, LTEKPIReportPage.Type, true);
			CommonJQ.click(driver, LTEKPIReportPage.TelecomMonth, true);
		} else if ("联通周报".equalsIgnoreCase(Type)) {
			int Month = CommonJQ.length(driver, "ul[id=\"mshowtask.modelType\"] li");
			for (int i = 0; i < 5 && Month < 1; i++) {
				Pause.pause(1000);
				Month = CommonJQ.length(driver, "ul[id=\"mshowtask.modelType\"] li");
			}
			CommonJQ.click(driver, LTEKPIReportPage.Type, true);
			CommonJQ.click(driver, LTEKPIReportPage.UnicomWeek, true);
		} else {
			Assert.fail("报告模板错误");
		}
		if ("移动月报".equalsIgnoreCase(Type)) {
			if (!GTLQuaFlage) {
				CommonJQ.click(driver, LTEKPIReportPage.GTLQua_ID, true);
			}
			if (!GTLTrendFlage) {
				CommonJQ.click(driver, LTEKPIReportPage.GTLTrend_ID, true);
			}
			if (!MobileLTEFlage) {
				CommonJQ.click(driver, LTEKPIReportPage.MobileLTE_ID, true);
			}
			if (!MobileMBBFlage) {
				CommonJQ.click(driver, LTEKPIReportPage.MobileMBB_ID, true);
			}
		}

		SwitchDriver.switchDriverToSEQ(driver);
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
		if (reportName.endsWith("zip")) {
			ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
		} else {
			FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome + "\\" + reportName);
		}
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
		CommonJQ.click(driver, "#showDownLoad", true, "报告下载按钮");
		String reportName = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", true);
		reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
