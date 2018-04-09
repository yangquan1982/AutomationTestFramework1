package cloud_plugin.task.network_performance_analysis_center.engineering_optimization.UmtsConfParameterCompare;

import org.openqa.selenium.WebDriver;

import com.huawei.hutaf.webtest.htmlaw.BasicAction;

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.UmtsConfParameterCompare.UmtsConfParameterCompare;
import cloud_plugin.page.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlanPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class UmtsConfCompare {

	public static String creatUMTSConfCompareTask(WebDriver driver,
			String defaultWindowID, String taskName, String[] cfgfilebefore,
			String[] cfgfileafter, String splitMappingFile,
			String[] WuxianParaSeclet, String SelfDefineParaSeclet) {
		// 打开数据自动分析任务
		NetworkAnalysisCenterTask.OpenUMTSCfgCompare(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 设置任务名称
		for(int i=0;i<60;i++)
		{
			if(CommonJQ.isExisted(driver, UmtsConfParameterCompare.nnn, true))
			{
				break;
			}
			else
			{
				BasicAction.sleep(1);
			}			
		}
		SwitchDriver.switchDriverToSEQ(driver);
		String Name = UmtsConfParameterCompare.setTaskName(driver, taskName);
		// 选择数据
		UmtsConfCompare.ChooseData(driver, defaultWindowID, cfgfilebefore,
				cfgfileafter, splitMappingFile);
		// 参数选择
		UmtsConfCompare.ChooseParameter(driver, defaultWindowID,
				WuxianParaSeclet, SelfDefineParaSeclet);
		UmtsConfParameterCompare.commitBtnClick(driver);
		return Name;
	}

	private static void ChooseData(WebDriver driver, String defaultWindowID,
			String[] cfgfilebefore, String[] cfgfileafter,
			String splitMappingFile) {
		if (cfgfilebefore != null) {
			if (cfgfilebefore.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfilebefore,
						UmtsConfParameterCompare.BtnConfSelectBefore,
						defaultWindowID);
			}
		}
		if (cfgfileafter != null) {
			if (cfgfileafter.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfileafter,
						UmtsConfParameterCompare.BtnConfSelectAfter,
						defaultWindowID);
			}
		}
		if (splitMappingFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver,
					UmtsConfParameterCompare.BtnsplitMappingFile, true);
			CommonFile.ChooseOneFile(splitMappingFile);
			CommonJQ.click(driver, RankUpPlanPage.ChooseCSVBtnOk, true);// 此处需要适配
			SwitchDriver.switchDriverToSEQ(driver);

		}
	}

	// 界面功能未实现实现后进行修改
	private static void ChooseParameter(WebDriver driver,
			String defaultWindowID, String[] WuxianParaSeclet,
			String SelfDefineParaSeclet) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (WuxianParaSeclet != null) {
			for (int i = 0; i < WuxianParaSeclet.length; i++) {
				CommonJQ.value(driver, UmtsConfParameterCompare.TextwireSearch,
						WuxianParaSeclet[i]);
				CommonJQ.click(driver,
						UmtsConfParameterCompare.wrapbuttonleftPanel, true);
				CommonJQ.click(driver, "span:contains(" + WuxianParaSeclet[i]
						+ ")", true);
				CommonJQ.click(driver,
						UmtsConfParameterCompare.BtnwireLessToRight, true);
			}
		}
		if (SelfDefineParaSeclet != null) {
			CommonJQ.click(driver, UmtsConfParameterCompare.RadiocellNameSelf,
					true);
			CommonJQ.click(driver, UmtsConfParameterCompare.BtnwireImport, true);
			// 点击浏览按钮
			CommonJQ.click(driver, UmtsConfParameterCompare.BtnimportFile, true);
			CommonFile.ChooseOneFile(SelfDefineParaSeclet);
			// 点击导入按钮
			CommonJQ.click(driver, UmtsConfParameterCompare.BtncommitTasknew1,
					true);
			CommonJQ.click(driver, RankUpPlanPage.ChooseCSVBtnOk, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#reportDownLoad", true);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "UMTS扇区");
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName,
				ResultHome);
	}

}
