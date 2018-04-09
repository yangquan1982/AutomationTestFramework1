package cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDTDataPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
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

public class AutoDingLiDataTask {

	/**
	 * <b>Description:</b>DT分析优化
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param WhetherTopN
	 * @param TopN
	 * @param WhetherGrids
	 * @param GridsPre
	 * @param GridsLater
	 * @param WhetherPoint
	 * @param cfgfile
	 * @param EParafile
	 * @param DTfile
	 * @return
	 * @return String 返回新建任务名称
	 */
	public static String creatDingLiOptimizTask(WebDriver driver,
			String defaultWindowID, String taskname, boolean WhetherTopN,
			String TopN, boolean WhetherGrids, String GridsPre,
			String GridsLater, boolean WhetherPoint, String[] cfgfile,
			String[] EParafile, String[] DTfile, String[] Polygon) {
		// 打开 鼎利用 数据自动分析任务
		NetworkAnalysisCenterTask.openAutoDingLiData(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");	
		if (WhetherTopN) {
			CommonJQ.click(driver, AutoDTDataPage.WhetherTopN_ID, true);
			CommonJQ.value(driver, AutoDTDataPage.TopN_ID, TopN, true);
		}
		if (WhetherGrids) {
			CommonJQ.click(driver, AutoDTDataPage.WhetherGrids_ID, true);
			CommonJQ.value(driver, AutoDTDataPage.GridsPre_ID, GridsPre, true);
			CommonJQ.value(driver, AutoDTDataPage.GridsLater_ID, GridsLater, true);
		}
		if (WhetherPoint) {
			CommonJQ.click(driver, AutoDTDataPage.WhetherPoint_ID, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutoDingLiDataTask.chooseFile(driver, defaultWindowID, cfgfile,
				EParafile, DTfile, Polygon);
		String Name = AutoDTDataPage.setTaskName(driver, taskname);

		AutoDTDataPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * <b>Description:</b>专题优化
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param cfgfile
	 * @param EParafile
	 * @param DTfile
	 * @return
	 * @return String 返回新建任务名称
	 */
	public static String creatThemeOptimizTask(WebDriver driver,
			String defaultWindowID, String taskname, String[] ThemeType,
			String[] cfgfile, String[] EParafile, String[] DTfile) {
		// 打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openAutoDingLiData(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataPage.ThemeOptimiz_ID, true);
		CommonJQ.click(driver, AutoDTDataPage.SelectedOptimiz_ID, true);
		CommonJQ.click(driver, AutoDTDataPage.AllSelect_ID, true);
		for (int i = 0; i < ThemeType.length; i++) {
			CommonJQ.click(driver, ThemeType[i], true);
		}
		CommonJQ.click(driver, AutoDTDataPage.SelectedOptimiz_ID, true);
		if (ThemeType.length != 0) {
			String Evalue = LanguageUtil
					.translate("A total of 9 topic(s)，You have selected")
					+ ThemeType.length
					+ " "
					+ LanguageUtil.translate("topic(s)");
			if (ThemeType[0].equals(AutoDTDataPage.AllSelect_ID)) {
				Evalue = LanguageUtil
						.translate("A total of 9 topic(s)，You have selected")
						+ 9 + " " + LanguageUtil.translate("topic(s)");
			}
			String Avalue = CommonJQ.getValue(driver,
					AutoDTDataPage.SelectedOptimiz_ID);
			Assert.assertTrue("Avalue:" + Avalue + ",Evalue:" + Evalue,
					StringUtils.equals(Evalue, Avalue));
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutoDingLiDataTask.chooseFile2(driver, defaultWindowID, cfgfile,
				EParafile, DTfile);

		String Name = AutoDTDataPage.setTaskName(driver, taskname);
		AutoDTDataPage.commitBtnClick(driver);

		return Name;
	}

	public static void downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, AutoDTDataPage.DownloadReport, true);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, taskname);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
	}

	/**
	 * <b>Description:</b>选择DT相关数据
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param cfgfile
	 *            配置
	 * @param EParafile
	 *            工参
	 * @param DTfile
	 *            路测
	 * @return void
	 */
	private static void chooseFile(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] EParafile, String[] DTfile,
			String[] Polygon) {
		if (cfgfile != null) {
			if (cfgfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfile,
						AutoDTDataPage.Cfg_ID, defaultWindowID);
			}
		}
		if (EParafile != null) {
			if (EParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EParafile,
						AutoDTDataPage.EPara_ID, defaultWindowID);
			}
		}
		if (DTfile != null) {
			if (DTfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTfile,
						AutoDTDataPage.DT_ID, defaultWindowID);
			}
		}
		if (Polygon != null) {
			if (Polygon.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, Polygon,
						AutoDTDataPage.Polygon_ID, defaultWindowID);
			}
		}

	}

	private static void chooseFile2(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] EParafile, String[] DTfile) {

		if (cfgfile != null) {
			if (cfgfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfile,
						AutoDTDataPage.Cfg_ID, defaultWindowID);
			}
		}
		if (EParafile != null) {
			if (EParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EParafile,
						AutoDTDataPage.EPara_ID, defaultWindowID);
			}
		}
		if (DTfile != null) {
			if (DTfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTfile,
						AutoDTDataPage.DT_ID, defaultWindowID);
			}
		}
	}
}
