package cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dingli;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dingli.AutomaticAnalysisDingLiPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import common.util.CommonJQ;
import common.util.SwitchDriver;

public class AutomaticAnalysisDingLiTask {

	public static String creatDingLiOptimizTask(WebDriver driver,
			String defaultWindowID,String taskName, boolean WhetherTopN, String TopN,
			boolean WhetherGrids, String GridsPre, String GridsLater,
			boolean WhetherPoint, String[] cfgfile, String[] EParafile,
			String[] DTfile, String[] Polygonfile) {
		// 打开数据自动分析任务
		NetworkAnalysisCenterTask.openAutoDingLiData(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutomaticAnalysisDingLiPage.RBoxDingLi, true);
		if (WhetherTopN) {
			CommonJQ.click(driver, AutomaticAnalysisDingLiPage.CBoxWhetherTopN, true);
			CommonJQ.value(driver, AutomaticAnalysisDingLiPage.TextTopN, TopN,true);
		}
		if (WhetherGrids) {
			CommonJQ.click(driver, AutomaticAnalysisDingLiPage.CBoxWhetherGrids, true);
			CommonJQ.value(driver, AutomaticAnalysisDingLiPage.TextGridsPre, GridsPre,true);
			CommonJQ.value(driver, AutomaticAnalysisDingLiPage.TexttGridsLater, GridsLater,true);
		}
		if (WhetherPoint) {
			CommonJQ.click(driver, AutomaticAnalysisDingLiPage.CBoxWhetherCollectionPoint, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutomaticAnalysisDingLiTask.setNetData(driver, defaultWindowID,
				cfgfile, EParafile, DTfile, Polygonfile);
		String Name = AutomaticAnalysisDingLiPage.setTaskName(driver, taskName);
		AutomaticAnalysisDingLiPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * <b>Description:</b>选择相关数据
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
	 * @param Polygonfile
	 *            Polygon
	 * @return void
	 */
	private static void setNetData(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] EParafile, String[] DTfile,
			String[] Polygonfile) {

		if (cfgfile != null) {
			if (cfgfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfile,
						AutomaticAnalysisDingLiPage.BtnCfg, defaultWindowID);
			}
		}

		if (EParafile != null) {
			if (EParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EParafile,
						AutomaticAnalysisDingLiPage.BtnPP, defaultWindowID);
			}
		}

		if (DTfile != null) {
			if (DTfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTfile,
						AutomaticAnalysisDingLiPage.BtnDT, defaultWindowID);
			}
		}
		if (DTfile != null) {
			if (Polygonfile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(driver, Polygonfile,
								AutomaticAnalysisDingLiPage.BtnPolygon,
								defaultWindowID);
			}
		}
	}
}
