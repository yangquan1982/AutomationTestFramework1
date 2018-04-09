package cloud_plugin.task.network_performance_analysis_center.rf_channel_check;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class RFChannelCheckTask {

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
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i], ResultHome);
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
	public static String[] downLoad_report(WebDriver driver, String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LoadingPage.Loading(driver, "#downloadReportsId0", "报告下载按钮");
		int reportNum = CommonJQ.length(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", true);
		String[] reportName = new String[reportNum];
		for (int i = 0; i < reportNum; i++) {
			CommonJQ.click(driver, "#downloadReportsId" + i, true, "报告下载按钮");
			reportName[i] = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", i);
			System.out.println("reportName" + i + ":" + reportName[i]);
			reportName[i] = reportName[i].substring(0, reportName[i].indexOf("."));
			reportName[i] = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName[i]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
