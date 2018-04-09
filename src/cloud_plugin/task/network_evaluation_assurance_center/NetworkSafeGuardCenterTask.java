package cloud_plugin.task.network_evaluation_assurance_center;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_evaluation_assurance_center.NetworkSafeGuardCenterPage;
import cloud_public.page.IndexPage;
import cloud_public.task.IndexTask;
import cloud_public.task.LoginTask;
import cloud_plugin.page.NetWorkInsightPage;

public class NetworkSafeGuardCenterTask {

	/**
	 * <b>Description:</b>打开 LTE KPI报告 任务-移动
	 * 
	 * @author sWX198085
	 * @param driver
	 * @return void
	 */
	public static void openLKPIReportChinaMobile(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetEvaAndAssCenter_ID, IndexPage.LTE_Mobile,
				NetworkSafeGuardCenterPage.LteKpiReport_ClASS, "插件未找到，插件名：LTE KPI报告");
	}

	/**
	 * <b>Description:</b>打开 LTE KPI报告 任务-电信
	 * 
	 * @author sWX198085
	 * @param driver
	 * @return void
	 */
	public static void openLKPIReportChinaTelecom(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetEvaAndAssCenter_ID, IndexPage.LTE_Telecom,
				NetworkSafeGuardCenterPage.LteKpiReport_ClASS, "插件未找到，插件名：LTE KPI报告");
	}

	/**
	 * <b>Description:</b>打开 LTE KPI报告 任务-联通
	 * 
	 * @author sWX198085
	 * @param driver
	 * @return void
	 */
	public static void openLKPIReportChinaUnicom(WebDriver driver) {
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetEvaAndAssCenter_ID, IndexPage.LTE_Unicom,
				NetworkSafeGuardCenterPage.LteKpiReport_ClASS, "插件未找到，插件名：LTE KPI报告");
	}

	/**
	 * <b>Description:</b>打开KQI报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @return void
	 */
	public static void openKQIReport(WebDriver driver) {
		// 切换成旧版本
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetEvaAndAssCenter_ID, IndexPage.LTE_Telecom,
				NetworkSafeGuardCenterPage.LteKqiReport_ClASS, "插件未找到，插件名：KQI报告");
	}

	/**
	 * <b>Description:</b>打开 RAN远程交付
	 * 
	 * @author sWX198085
	 * @param driver
	 * @return void
	 */
	public static void openAutoRANData(WebDriver driver) {
		// 打开网络评估与保障中心;
		LoginTask.changeVersion(driver);
		IndexTask.openPlugin(driver, IndexPage.NetEvaAndAssCenter_ID, IndexPage.RAN_Mobile,
				NetworkSafeGuardCenterPage.RAN_CLASS, "插件未找到，插件名：RAN远程交付");

	}

	/**
	 * <b>Description:</b>打开视频洞察
	 * 
	 * @author sWX198085
	 * @param driver
	 * @return void
	 */
	public static void openVideoInsight(WebDriver driver) {
		IndexPage.netAppMange(driver);
		NetWorkInsightPage.VideoInsightClick(driver);
		IndexTask.changePrj(driver, IndexPage.LTE_Mobile);
	}
}
