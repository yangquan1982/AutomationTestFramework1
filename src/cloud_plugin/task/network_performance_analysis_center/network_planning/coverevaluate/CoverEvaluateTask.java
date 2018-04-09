package cloud_plugin.task.network_performance_analysis_center.network_planning.coverevaluate;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.coverevaluate.CoverEvaluatePage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.page.TaskViewPluginPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class CoverEvaluateTask {

	public static String creatUMTSCoverEvaluateTask(WebDriver driver, String defaultWindowID, String taskName_Star,
			String[] cfgFile, String[] EparaFile, String[] MrFile, String[] FeatureFile, String[] MapFile,
			String PositType, String RasterValue, boolean AGPSFlage, String RasterMR, String RasterCellMR, String ECIO,
			String RSCP, boolean FilterCellFlage, String MainCell, String IndoorCoverRate, String ComplexCoverRate,
			String IndoorCoverLevel, String ComplexCoverLevel, boolean MrClearFlage, boolean AcpFlage) {
		// 打开
		NetworkAnalysisCenterTask.openUMTSCoverEvaluate(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		// 选中任务名称
		String taskName = CoverEvaluatePage.setTaskName(driver, taskName_Star);

		CoverEvaluatePage.nextBtnClick(driver);

		// 选择分析数据
		if (cfgFile != null) {
			if (cfgFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgFile, CoverEvaluatePage.CfgBtn, defaultWindowID);
			}
		}
		if (EparaFile != null) {
			if (EparaFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EparaFile, CoverEvaluatePage.EparaBtn, defaultWindowID);
			}
		}
		if (MrFile != null) {
			if (MrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, MrFile, CoverEvaluatePage.MrBtn, defaultWindowID);
			}
		}
		if (FeatureFile != null) {
			GetDataByTypeTask.chooseTimeFolder(driver, FeatureFile, CoverEvaluatePage.FeatureBtn, defaultWindowID);
		}
		if (MapFile != null) {
			if (MapFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, MapFile, CoverEvaluatePage.MapBtn, defaultWindowID);
			}
		}
		CoverEvaluatePage.nextBtnClick(driver);
		// 定位方式设置
		CoverEvaluateTask.setPosit(driver, PositType, RasterValue, AGPSFlage);
		// 参数设置
		CoverEvaluateTask.setBaseMR(driver, RasterMR, RasterCellMR, ECIO, RSCP, FilterCellFlage, MainCell);
		CoverEvaluateTask.setCoverMR(driver, IndoorCoverRate, ComplexCoverRate, IndoorCoverLevel, ComplexCoverLevel);
		CoverEvaluateTask.setOther(driver, MrClearFlage, AcpFlage);
		// 提交任务
		CoverEvaluatePage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param PositType
	 * @param RasterValue
	 * @param AGPSFlage
	 * @return void
	 */
	private static void setPosit(WebDriver driver, String PositType, String RasterValue, boolean AGPSFlage) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("Fast".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, CoverEvaluatePage.PosiObjLi_PATH, true);
			CommonJQ.click(driver, CoverEvaluatePage.FastPosit, true);
		} else if ("Feature".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, CoverEvaluatePage.PosiObjLi_PATH, true);
			CommonJQ.click(driver, CoverEvaluatePage.FeaturePosit, true);
		} else if ("Complex".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, CoverEvaluatePage.PosiObjLi_PATH, true);
			CommonJQ.click(driver, CoverEvaluatePage.ComplexPosit, true);
		} else if ("AGPS".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, CoverEvaluatePage.PosiObjLi_PATH, true);
			CommonJQ.click(driver, CoverEvaluatePage.AgpsPosit, true);
		}
		if ("20".equalsIgnoreCase(RasterValue)) {
			CommonJQ.click(driver, CoverEvaluatePage.RasterObjLi_PATH, true);
			CommonJQ.click(driver, CoverEvaluatePage.Raster20, true);
		} else if ("50".equalsIgnoreCase(RasterValue)) {
			CommonJQ.click(driver, CoverEvaluatePage.RasterObjLi_PATH, true);
			CommonJQ.click(driver, CoverEvaluatePage.Raster50, true);
		}
		if (AGPSFlage) {
			CommonJQ.click(driver, CoverEvaluatePage.CheckAgps_ID, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param IndoorCoverRate
	 * @param ComplexCoverRate
	 * @param IndoorCoverLevel
	 * @param ComplexCoverLevel
	 * @param FilterCellFlage
	 * @param MainCell
	 * @return void
	 */
	private static void setBaseMR(WebDriver driver, String RasterMR, String RasterCellMR, String ECIO, String RSCP,
			boolean FilterCellFlage, String MainCell) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		CommonJQ.value(driver, CoverEvaluatePage.TextRasterMR, RasterMR);
		CommonJQ.value(driver, CoverEvaluatePage.TextRasterCellMR, RasterCellMR);

		CommonJQ.value(driver, CoverEvaluatePage.TextECIO, ECIO);
		CommonJQ.value(driver, CoverEvaluatePage.TextRSCP, RSCP);

		if (FilterCellFlage) {
			CommonJQ.click(driver, CoverEvaluatePage.CBoxMainCellFiter, true);
			CommonJQ.value(driver, CoverEvaluatePage.TextMainCell, MainCell);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	private static void setCoverMR(WebDriver driver, String IndoorCoverRate, String ComplexCoverRate,
			String IndoorCoverLevel, String ComplexCoverLevel) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		CommonJQ.value(driver, CoverEvaluatePage.TextIndoorCoverRate, IndoorCoverRate);
		CommonJQ.value(driver, CoverEvaluatePage.TextComplexCoverRate, ComplexCoverRate);

		CommonJQ.value(driver, CoverEvaluatePage.TextIndoorCoverLevel, IndoorCoverLevel);
		CommonJQ.value(driver, CoverEvaluatePage.TextComplexCoverLevel, ComplexCoverLevel);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param FilterCellFlage
	 * @param MrClearFlage
	 * @param AcpFlage
	 * @return void
	 */
	private static void setOther(WebDriver driver, boolean MrClearFlage, boolean AcpFlage) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.siblingsClick(driver, CoverEvaluatePage.TextClearMR, "input[type=\"text\"]");
		if (MrClearFlage) {
			CommonJQ.click(driver, CoverEvaluatePage.ListMrYES, true);
		} else {
			CommonJQ.click(driver, CoverEvaluatePage.ListMrNO, true);
		}
		CommonJQ.siblingsClick(driver, CoverEvaluatePage.TextAcp, "input[type=\"text\"]");
		if (AcpFlage) {
			CommonJQ.click(driver, CoverEvaluatePage.ListAcpYES, true);
		} else {
			CommonJQ.click(driver, CoverEvaluatePage.ListAcpNO, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
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
		// 打开
		NetworkAnalysisCenterTask.openUMTSCoverEvaluate(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		taskName = CoverEvaluatePage.setTaskName(driver, taskName);
		CoverEvaluatePage.cancelBtnClick(driver);
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
		// 打开
		NetworkAnalysisCenterTask.openUMTSCoverEvaluate(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		taskName = CoverEvaluatePage.setTaskName(driver, taskName);
		CoverEvaluatePage.cancelTitleClick(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
	}

	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 * @param ResultHome
	 * @return void
	 */
	public static void downLoad_report(WebDriver driver, String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		TaskViewPluginPage.reportDownLoadCoverEvaluteClick(driver);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "覆盖评估");
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
	}
}
