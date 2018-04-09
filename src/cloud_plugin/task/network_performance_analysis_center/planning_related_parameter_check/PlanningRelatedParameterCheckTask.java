package cloud_plugin.task.network_performance_analysis_center.planning_related_parameter_check;

import org.openqa.selenium.WebDriver;

import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class PlanningRelatedParameterCheckTask {

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
		CommonJQ.click(driver, "#downLoadTal", true, "报告下载按钮");
		String reportName = CommonJQ.text(driver, "#task_report_tab .col2 span[class=\"ng-binding\"]", "", true);
		reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
