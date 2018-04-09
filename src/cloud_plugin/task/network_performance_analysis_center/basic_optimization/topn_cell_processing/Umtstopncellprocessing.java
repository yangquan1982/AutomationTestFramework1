package cloud_plugin.task.network_performance_analysis_center.basic_optimization.topn_cell_processing;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.basic_optimization.topn_cell_processing.TopnCellProcessingPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class Umtstopncellprocessing {

	public static String creatUMTSTopnTask(WebDriver driver, String defaultWindowID, String taskName,
			String[] ThemeOptimization, String[] SetRABAccess, String SetRRCAccess, String[] SetThroughtOutRate,
			String[] SetQiehuan, String[] SetDisconnect, String[] cfgfile, String[] Parafile, String[] Pfmfile,
			String[] PChrfile, String[] Mrfile, String[] NodebConf, String[] NodebM2000Conf, String[] NodeBLicense,
			String[] MML) {
		// 打开TOPN
		NetworkAnalysisCenterTask.openUmtsTopnData(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 输入任务名称
		String Name = TopnCellProcessingPage.setTaskName(driver, taskName);
		// 选择优化对象 1
		ChooseTheme(driver, ThemeOptimization);
		// 参数设置
		ParameterSet(driver, SetRABAccess, SetRRCAccess, SetThroughtOutRate, SetQiehuan, SetDisconnect);
		SwitchDriver.switchDriverToSEQ(driver);
		// 数据选择
		ChooseData(driver, defaultWindowID, cfgfile, Parafile, Pfmfile, PChrfile, Mrfile, NodebConf, NodebM2000Conf,
				NodeBLicense, MML);
		TopnCellProcessingPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * 选择优化对象
	 * 
	 * @param driver
	 * @param ThemeOptimization
	 * 
	 */
	private static void ChooseTheme(WebDriver driver, String[] ThemeOptimization) {
		if (ThemeOptimization != null && ThemeOptimization.length != 0) {
			CommonJQ.click(driver, TopnCellProcessingPage.CheckRabAccess, true, "RAB接入");
			CommonJQ.click(driver, TopnCellProcessingPage.ChecktaskTopicRrc, true, "RRC接入");
			CommonJQ.click(driver, TopnCellProcessingPage.ChecktaskTopicThroughput, true, "吞吐率");
			CommonJQ.click(driver, TopnCellProcessingPage.ChecktaskTopicInterRatHo, true, "异系统切换");
			CommonJQ.click(driver, TopnCellProcessingPage.ChecktaskTopicCallDrop, true, "掉话");
			for (int i = 0; i < ThemeOptimization.length; i++) {
				if (ThemeOptimization[i].equalsIgnoreCase("RAB接入")) {
					LOG.info_aw("选择优化对象：" + ThemeOptimization[i]);
					CommonJQ.click(driver, TopnCellProcessingPage.CheckRabAccess, true, "RAB接入");
				}
				if (ThemeOptimization[i].equalsIgnoreCase("RRC接入")) {
					LOG.info_aw("选择优化对象：" + ThemeOptimization[i]);
					CommonJQ.click(driver, TopnCellProcessingPage.ChecktaskTopicRrc, true, "RRC接入");
				}
				if (ThemeOptimization[i].equalsIgnoreCase("吞吐率")) {
					LOG.info_aw("选择优化对象：" + ThemeOptimization[i]);
					CommonJQ.click(driver, TopnCellProcessingPage.ChecktaskTopicThroughput, true, "吞吐率");
				}
				if (ThemeOptimization[i].equalsIgnoreCase("异系统切换")) {
					LOG.info_aw("选择优化对象：" + ThemeOptimization[i]);
					CommonJQ.click(driver, TopnCellProcessingPage.ChecktaskTopicInterRatHo, true, "异系统切换");
				}
				if (ThemeOptimization[i].equalsIgnoreCase("掉话")) {
					LOG.info_aw("选择优化对象：" + ThemeOptimization[i]);
					CommonJQ.click(driver, TopnCellProcessingPage.ChecktaskTopicCallDrop, true, "掉话");
				}
			}
			LOG.info_aw("已成功选择优化对象");
		}

	}

	private static void ParameterSet(WebDriver driver, String[] SetRABAccess, String SetRRCAccess,
			String[] SetThroughtOutRate, String[] SetQiehuan, String[] SetDisconnect) {
		if (SetRABAccess != null || SetRRCAccess != null || SetThroughtOutRate != null || SetQiehuan != null
				|| SetDisconnect != null) {
			CommonJQ.click(driver, TopnCellProcessingPage.parametersetting_ID, true);
			if (SetRABAccess != null) {
				if (CommonJQ.isExisted(driver, TopnCellProcessingPage.LinkRABaccess)) {
					CommonJQ.click(driver, TopnCellProcessingPage.LinkRABaccess, true,
							TopnCellProcessingPage.SetParameter);
				}
				for (int i = 0; i < SetRABAccess.length; i++) {
					if (!SetRABAccess[i].equalsIgnoreCase("")) {
						LOG.info_aw("修改RAB接入第" + i + "个参数为" + SetRABAccess[i]);
						CommonJQ.value(driver, TopnCellProcessingPage.TextRABaccess[i], SetRABAccess[i], true);
					} else {
						continue;
					}
				}
			}
			if (SetRRCAccess != null) {
				if (CommonJQ.isExisted(driver, TopnCellProcessingPage.LinkRRCaccess)) {
					CommonJQ.click(driver, TopnCellProcessingPage.LinkRRCaccess, true,
							TopnCellProcessingPage.SetParameter);
				}

				LOG.info_aw("修改RRC接入参数为" + SetRRCAccess);
				CommonJQ.value(driver, TopnCellProcessingPage.TextAccessSuccessRate, SetRRCAccess, true);
			}
			if (SetThroughtOutRate != null) {
				if (CommonJQ.isExisted(driver, TopnCellProcessingPage.LinkTuntulv)) {
					CommonJQ.click(driver, TopnCellProcessingPage.LinkTuntulv, true,
							TopnCellProcessingPage.SetParameter);
				}
				for (int i = 0; i < SetThroughtOutRate.length; i++) {
					if (!SetThroughtOutRate[i].equalsIgnoreCase("")) {
						LOG.info_aw("修改RAB接入第" + i + "个参数为" + SetThroughtOutRate[i]);
						CommonJQ.value(driver, TopnCellProcessingPage.TextThroughOut[i], SetThroughtOutRate[i], true);
					} else {
						continue;
					}
				}
			}
			if (SetQiehuan != null) {
				if (CommonJQ.isExisted(driver, TopnCellProcessingPage.LinkQiehuan)) {
					CommonJQ.click(driver, TopnCellProcessingPage.LinkQiehuan, true,
							TopnCellProcessingPage.SetParameter);
				}
				for (int i = 0; i < SetQiehuan.length; i++) {
					if (!SetQiehuan[i].equalsIgnoreCase("")) {
						LOG.info_aw("修改RAB接入第" + i + "个参数为" + SetQiehuan[i]);
						CommonJQ.value(driver, TopnCellProcessingPage.TextQiehuan[i], SetQiehuan[i], true);
					} else {
						continue;
					}
				}
			}
			if (SetDisconnect != null) {
				if (CommonJQ.isExisted(driver, TopnCellProcessingPage.LinkDiaohua)) {
					CommonJQ.click(driver, TopnCellProcessingPage.LinkDiaohua, true,
							TopnCellProcessingPage.SetParameter);
				}
				for (int i = 0; i < SetDisconnect.length; i++) {
					if (!SetDisconnect[i].equalsIgnoreCase("")) {
						LOG.info_aw("修改RAB接入第" + i + "个参数为" + SetDisconnect[i]);
						CommonJQ.value(driver, TopnCellProcessingPage.TextDisconnection[i], SetDisconnect[i], true);
					} else {
						continue;
					}
				}
			}
			CommonJQ.click(driver, TopnCellProcessingPage.okButton, true);

		}

	}

	/**
	 * 数据选择
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param cfgfile
	 * @param Pfmfile
	 * @param Parafile
	 * @param Warnfile
	 * @param operatefile
	 * @param OutChrfile
	 * @param InChrfile
	 */
	private static void ChooseData(WebDriver driver, String defaultWindowID, String[] cfgfile, String[] Parafile,
			String[] Pfmfile, String[] PChrfile, String[] Mrfile, String[] NodebConf, String[] NodebM2000Conf,
			String[] NodeBLicense, String[] MML) {
		if (cfgfile != null && cfgfile.length != 0) {
			LOG.info_aw("选择配置数据");
			GetDataByTypeTask.chooseFolder(driver, cfgfile, TopnCellProcessingPage.selectconfData_ID, defaultWindowID);

		}

		if (Parafile != null && Parafile.length != 0) {
			LOG.info_aw("选择工参数据");
			GetDataByTypeTask.chooseFolder(driver, Parafile, TopnCellProcessingPage.selectengineerParaData,
					defaultWindowID);
		}

		if (Pfmfile != null && Pfmfile.length != 0) {
			LOG.info_aw("选择话统数据");
			GetDataByTypeTask.chooseFolder(driver, Pfmfile, TopnCellProcessingPage.selectperfData_ID, defaultWindowID);
		}

		if (PChrfile != null && PChrfile.length != 0) {
			LOG.info_aw("选择PCHR数据");
			GetDataByTypeTask.chooseFolder(driver, PChrfile, TopnCellProcessingPage.selectchrData_ID, defaultWindowID);
		}
		if (Mrfile != null && Mrfile.length != 0) {
			LOG.info_aw("选择MR数据");
			GetDataByTypeTask.chooseFolder(driver, Mrfile, TopnCellProcessingPage.selectmrData_ID, defaultWindowID);
		}

		if (NodebConf != null && NodebConf.length != 0) {
			LOG.info_aw("选择Nodeb配置数据");
			GetDataByTypeTask.chooseFolder(driver, NodebConf, TopnCellProcessingPage.selectnodebConfData_ID,
					defaultWindowID);
		}

		if (NodebM2000Conf != null && NodebM2000Conf.length != 0) {
			LOG.info_aw("选择NodeB M2000话统数据");
			GetDataByTypeTask.chooseFolder(driver, NodebM2000Conf, TopnCellProcessingPage.selectnodebPerfData_ID,
					defaultWindowID);
		}
		if (NodeBLicense != null && NodeBLicense.length != 0) {
			LOG.info_aw("选择NodeB License数据");
			GetDataByTypeTask.chooseFolder(driver, NodeBLicense, TopnCellProcessingPage.selectnodebLicenceData_ID,
					defaultWindowID);
		}
		if (MML != null && MML.length != 0) {
			LOG.info_aw("选择MML数据");
			GetDataByTypeTask.chooseFolder(driver, MML, TopnCellProcessingPage.selectmmlData_ID, defaultWindowID);
		}

	}

	public static void downLoad_report(WebDriver driver, String defaultWindowID, String taskname, String ResultHome) {

		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		int length = CommonJQ.length(driver, "#showDownLoad", true);
		System.out.println("界面控件个数：" + length);
		for (int i = 0; i < length; i++) {
			FileHandle.delSubFile(EnvConstant.Path_DownLoad);
			FileHandle.delSubFile(ResultHome + "\\" + i);
			CommonJQ.click(driver, "#showDownLoad" + i, true);

			String reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "Benchmark");

			ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, EnvConstant.Path_DownLoad);
			String reportName1 = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "UMTSTopNReport");
			ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName1, ResultHome + "\\" + i);

		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);

	}

}
