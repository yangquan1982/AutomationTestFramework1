package cloud_plugin.task.network_performance_analysis_center.three_dimensional_network_evaluation;

import java.util.Set;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.three_dimensional_network_evaluation.ThreeDNetWorkEvaPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class ThreeDNetWorkEvaTask {

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param RAT
	 * @param MRFile
	 * @param PCHRFile
	 * @param paraFile
	 * @param PolygonFile
	 * @param OnlineFlage
	 * @param MapFile
	 * @param FeatureFile
	 * @param AntennaFile
	 * @param OTTFile
	 * @param DTFile
	 * @return
	 */
	public static Set<String> Creat3DNetWorkEvaTask(WebDriver driver, String defaultWindowID, String taskName,
			String RAT, String[] MRFile, String[] PCHRFile, String paraFile, String[] PolygonFile, boolean OnlineFlage,
			String[] MapFile, String[] FeatureFile, String AntennaFile, String[] OTTFile, String DTFile) {

		taskName = ThreeDNetWorkEvaTask.setServiceModule(driver, taskName, RAT);
		if ("LTE-FDD".equalsIgnoreCase(RAT)) {
			ThreeDNetWorkEvaTask.setAysNetData(driver, defaultWindowID, MRFile, null, paraFile, PolygonFile,
					OnlineFlage, MapFile, FeatureFile, AntennaFile, OTTFile, DTFile);
		} else if ("UMTS".equalsIgnoreCase(RAT)) {
			ThreeDNetWorkEvaTask.setAysNetData(driver, defaultWindowID, MRFile, PCHRFile, paraFile, PolygonFile,
					OnlineFlage, MapFile, FeatureFile, AntennaFile, null, DTFile);
		} else {
			ThreeDNetWorkEvaTask.setAysNetData(driver, defaultWindowID, MRFile, null, paraFile, PolygonFile,
					OnlineFlage, MapFile, FeatureFile, AntennaFile, OTTFile, DTFile);
		}
		Set<String> handles = driver.getWindowHandles();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 数据完整性校验
		// ThreeDNetWorkEvaPage.BtnCheckDataClick(driver);
		// 下一步
		ThreeDNetWorkEvaPage.BtnCommitClick(driver);
		ThreeDNetWorkEvaPage.TipIfoOTTOK(driver);
		SwitchDriver.switchDriverToSEQ(driver);

		return handles;
	}

	/**
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名称
	 * @param RAT
	 *            ：制式
	 * @return
	 */
	public static String setServiceModule(WebDriver driver, String taskName, String RAT) {
		// 打开
		NetworkAnalysisCenterTask.open3DNetWorkEvaluation(driver);
		TaskReportPage.CreateTaskClick(driver);
		LoadingPage.Loading(driver);
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 选中任务名称
		ThreeDNetWorkEvaPage.TipIfoIE10OK(driver);
		taskName = ThreeDNetWorkEvaPage.setTaskName(driver, taskName);
		ThreeDNetWorkEvaPage.CheckRAT(driver, RAT);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param MRFile
	 *            :MR数据
	 * @param PCHRFile
	 *            :PCHR数据
	 * @param paraFile
	 *            :工参数据
	 * @param PolygonFile
	 *            :Polygon数据
	 * @param OnlineFlage
	 *            :是否在线地图
	 * @param MapFile
	 *            ：非在线地图数据
	 * @param FeatureFile
	 *            ：特征库数据
	 * @param AntennaFile
	 *            ：天线文件
	 * @param OTTFile
	 *            ：OTT参数
	 * @param DTFile
	 *            ：DT文件
	 */
	public static void setAysNetData(WebDriver driver, String defaultWindowID, String[] MRFile, String[] PCHRFile,
			String paraFile, String[] PolygonFile, boolean OnlineFlage, String[] MapFile, String[] FeatureFile,
			String AntennaFile, String[] OTTFile, String DTFile) {

		// MR数据
		ThreeDNetWorkEvaPage.MRChooseFile(driver, defaultWindowID, MRFile);
		// PCHR数据
		ThreeDNetWorkEvaPage.PCHRChooseFile(driver, defaultWindowID, PCHRFile);
		// 工参数据
		ThreeDNetWorkEvaPage.ParaChooseFile(driver, defaultWindowID, paraFile);
		// Polygon数据
		ThreeDNetWorkEvaPage.PolygonChooseFile(driver, defaultWindowID, PolygonFile);
		// 电子地图数据
		ThreeDNetWorkEvaPage.MapChooseFile(driver, defaultWindowID, OnlineFlage, MapFile);
		// 特征库数据
		ThreeDNetWorkEvaPage.FeatureChooseFile(driver, defaultWindowID, FeatureFile);
		// OTT参数数据
		ThreeDNetWorkEvaPage.OTTChooseFile(driver, defaultWindowID, OTTFile);
		//
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 天线文件
		ThreeDNetWorkEvaPage.AntennaChooseFile(driver, AntennaFile);
		// DT文件
		ThreeDNetWorkEvaPage.DTChooseFile(driver, DTFile);
		SwitchDriver.switchDriverToSEQ(driver);

	}

	/**
	 * <b>Description:</b>显示任务详情
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskname
	 * @return
	 * @return String 返回当前窗口ID
	 */
	public static String view3DGis(WebDriver driver, Set<String> handles_0) {
		String nowWinID = "";
		Set<String> handles_1 = driver.getWindowHandles();

		for (int i = 0; i < 10; i++) {
			if (handles_0.size() != handles_1.size()) {
				System.out.println(ZIP.NowTime() + "open new window success.");
				break;
			} else {
				System.out.println(ZIP.NowTime() + "wait new window.");
				Pause.pause(1000);
				handles_1 = driver.getWindowHandles();
				continue;
			}
		}
		if (handles_0.size() == handles_1.size()) {
			Assert.fail("3D 地图 页面未加载出来。");
		}
		for (String s : handles_1) {
			if (handles_0.contains(s)) {
				continue;
			} else {
				driver.switchTo().window(s);
				nowWinID = s;
				System.out.println(ZIP.NowTime() + "switch to window[" + s + "] successfully!");
				break;
			}
		}

		String CurrentUrl = driver.getCurrentUrl();
		System.out.println(ZIP.NowTime() + "driver.getCurrentUrl():" + driver.getCurrentUrl());
		for (int i = 0; (i < 10) && (CurrentUrl.contains("ThreeDimensionalNetworkEvaluation") == false); i++) {
			Pause.pause(1000);
			CurrentUrl = driver.getCurrentUrl();
			System.out.println(ZIP.NowTime() + "driver.getCurrentUrl():" + driver.getCurrentUrl());
			if (CurrentUrl.contains("ThreeDimensionalNetworkEvaluation")) {
				break;
			}
		}

		if (CurrentUrl.contains("ThreeDimensionalNetworkEvaluation") == false) {
			Assert.fail("3D 地图 页面未加载出来");
		}
		System.out.println(ZIP.NowTime() + "Over:" + driver.getCurrentUrl());
		return nowWinID;
	}

	/**
	 * 模板下载
	 * 
	 * @param driver
	 * @param RAT
	 * @param TempAntennasFlage
	 * @param TempDTFlage
	 * @param ResultHome
	 */
	public static void TemplateDownload(WebDriver driver, String RAT, boolean TempAntennasFlage, boolean TempDTFlage,
			String ResultHome) {

		ThreeDNetWorkEvaTask.setServiceModule(driver, "模板下载临时任务名", RAT);

		FileHandle.delSubFile(ResultHome);
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		FileHandle.makeDirectory(ResultHome);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 天线文件->模板下载
		if (TempAntennasFlage) {
			ThreeDNetWorkEvaPage.BtnTempAntennasClick(driver, ResultHome);
		}
		// DT文件->模板下载
		if (TempDTFlage) {
			ThreeDNetWorkEvaPage.BtnTempDtClick(driver, ResultHome);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @param ResultHome
	 *            ：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			if (reportName[i].endsWith("zip")) {
				ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i], ResultHome);
			} else {
				FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + reportName[i],
						ResultHome + "\\" + reportName[i]);
			}
		}

	}

	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @return String：报告完整名称
	 */
	public static String[] downLoad_report(WebDriver driver, String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LoadingPage.Loading(driver, ".col6 a", "报告下载按钮");
		int reportNum = CommonJQ.length(driver, ".r_task_report_tab .col2 span[class=\"ng-binding\"]", true);
		String[] reportName = new String[reportNum];
		for (int i = 0; i < reportNum; i++) {
			CommonJQ.click(driver, ".col6 a", true, i, "报告下载按钮");
			reportName[i] = CommonJQ.text(driver, ".r_task_report_tab .col2 span[class=\"ng-binding\"]", "", i);
			TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName[i]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
