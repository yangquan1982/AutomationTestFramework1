package cloud_plugin.task.network_performance_analysis_center.network_planning.lteupinterfere;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lteupinterfere.LteupinterferePage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.FileHandle;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class Lteupinterfere {

	public static String creatlteupinterfereTask(WebDriver driver,
			String defaultWindowID, String taskName, String SceneSet,
			String[] GanRaoCheckSet, String Nothreshold,
			String[] RrcAnalyCheck, String[] AreaCheck, String[] TypeCheck,
			String[] FddCheck, String[] cfgfile, String[] Pfmfile,
			String[] Csvfile, String[] Parafile, String[] Chrfile) {
		// 打开射频通道检查插件
		NetworkAnalysisCenterTask.openlteupinterfere(driver);
		// 切换到项目任务界面
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		LteupinterferePage.PrjTaskClick(driver);
		// 新建任务
		LteupinterferePage.NewCreatTask(driver);
		LoadingPage.Loading(driver);
		// 输入任务名称
		String Name = LteupinterferePage.setTaskName(driver, taskName);
		// 制式及场景选择
		ChooseScene(driver, SceneSet);
		// 参数设置
		ParameterSet(driver, GanRaoCheckSet, Nothreshold, RrcAnalyCheck,
				AreaCheck, TypeCheck, FddCheck);
		SwitchDriver.switchDriverToSEQ(driver);
		// 数据选择
		ChooseData(driver, defaultWindowID, cfgfile, Pfmfile, Csvfile,
				Parafile, Chrfile);
		LteupinterferePage.CommitBtnClick(driver);
		LoadingPage.Loading(driver, LteupinterferePage.BtnPrjTask, "射频初始界面");
		LteupinterferePage.PrjTaskClick(driver);		
		return Name;
	}

	/**
	 * 制式与场景设置
	 * 
	 * @param driver
	 * @param SceneSet
	 */
	public static void ChooseScene(WebDriver driver, String SceneSet) {
		if (SceneSet != null && SceneSet.equals("干扰识别")) {
			LteupinterferePage.GanRaoShiBie(driver);
		} else {
			if (SceneSet.equals("FDD")) {
				LteupinterferePage.SceneFdd(driver);
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 参数设置
	 * 
	 * @param driver
	 * @param GanRaoCheckSet
	 *            干扰程度检测设置
	 * @param Nothreshold
	 *            干扰程度不达标门限
	 * @param RrcAnalyCheck
	 *            RRC建立成功率检测
	 * @param AreaCheck
	 *            其他信息 与 区域检测设置
	 * @param TypeCheck
	 *            干扰类型识别
	 * @param FddCheck
	 *            FDD互调干扰检测
	 */
	public static void ParameterSet(WebDriver driver, String[] GanRaoCheckSet,
			String Nothreshold, String[] RrcAnalyCheck, String[] AreaCheck,
			String[] TypeCheck, String[] FddCheck) {
		if (GanRaoCheckSet != null || Nothreshold != null
				|| RrcAnalyCheck != null || AreaCheck != null
				|| TypeCheck != null || FddCheck != null) {
			LteupinterferePage.ParameterSet(driver);
			if (GanRaoCheckSet != null && GanRaoCheckSet.length != 0) {
				for (int i = 0; i < GanRaoCheckSet.length; i++) {
					if (GanRaoCheckSet[i] != null) {
						LOG.info_aw("修改干扰程度检测设置第" + i + "个参数为："
								+ GanRaoCheckSet[i]);
						LteupinterferePage.PmrSetValue(driver,
								LteupinterferePage.TextGanRaoJianCe[i],
								GanRaoCheckSet[i]);
					}
				}
			}
			if (Nothreshold != null) {
				if (Nothreshold.equals("中")) {
					LteupinterferePage.PmrSetRadioMid(driver);
				}
				if (Nothreshold.equals("高")) {
					LteupinterferePage.PmrSetRadioHigh(driver);
				}
			}
			if (RrcAnalyCheck != null && RrcAnalyCheck.length != 0) {
				for (int i = 0; i < RrcAnalyCheck.length; i++) {
					if (RrcAnalyCheck[i] != null) {
						if (RrcAnalyCheck[i].equalsIgnoreCase("true")) {
							LteupinterferePage.PmrSetRrcChoose(driver);
						} else {
							LOG.info_aw("修改RRC建立成功率检测第" + i + "个参数为："
									+ RrcAnalyCheck[i - 1]);
							LteupinterferePage
									.PmrSetValue(
											driver,
											LteupinterferePage.TextRrcAnalyCheck[i - 1],
											RrcAnalyCheck[i - 1]);
						}
					}
				}
			}
			if (AreaCheck != null && AreaCheck.length != 0) {
				for (int i = 0; i < AreaCheck.length; i++) {
					if (AreaCheck[i] != null) {
						if (AreaCheck[i].equalsIgnoreCase("false")) {
							LteupinterferePage.CancelOtherCheck(driver);
						} else {
							LOG.info_aw("修改其他信息第" + i + "个参数为：" + AreaCheck[i]);
							LteupinterferePage.PmrSetValue(driver,
									LteupinterferePage.TextTDDOtherCheck[i],
									AreaCheck[i]);
						}
					}
				}
			}
			if (TypeCheck != null && TypeCheck.length != 0) {
				for (int i = 0; i < TypeCheck.length; i++) {
					if (TypeCheck[i] != null) {
						LOG.info_aw("修改干扰类型识别第" + i + "个参数为：" + TypeCheck[i]);
						LteupinterferePage.PmrSetValue(driver,
								LteupinterferePage.TextType[i], TypeCheck[i]);
					}
				}
			}
			if (FddCheck != null && FddCheck.length != 0) {
				for (int i = 0; i < FddCheck.length; i++) {
					if (FddCheck[i] != null) {
						if (FddCheck[i].equalsIgnoreCase("false")) {
							LOG.info_aw("FDD互调干扰检测:取消勾选");
							LteupinterferePage.CancelFddCheckPmrSet(driver);
						} else {
							LOG.info_aw("修改FDD互调干扰检测第" + i + "个参数为："
									+ FddCheck[i]);
							LteupinterferePage.PmrSetValue(driver,
									LteupinterferePage.TextFddPimAnalyCheck[i],
									FddCheck[i]);
						}
					}
				}
			}
			LteupinterferePage.PmrSetOk(driver);
		}

	}

	private static void ChooseData(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] Pfmfile, String[] Csvfile,
			String[] Parafile, String[] Chrfile) {
		if (cfgfile != null && cfgfile.length != 0) {
			LOG.info_aw("选择配置数据：" + cfgfile);
			GetDataByTypeTask.chooseFolder(driver, cfgfile,
					LteupinterferePage.BtnCfg, defaultWindowID);
		}

		if (Pfmfile != null && Pfmfile.length != 0) {
			LOG.info_aw("选择话统数据：" + Pfmfile);
			GetDataByTypeTask.chooseFolder(driver, Pfmfile,
					LteupinterferePage.BtnPerf, defaultWindowID);
		}

		if (Csvfile != null && Csvfile.length != 0) {
			LOG.info_aw("选择工参数据：" + Csvfile);
			GetDataByTypeTask.chooseFolder(driver, Csvfile,
					LteupinterferePage.BtnCSV, defaultWindowID);
		}

		if (Parafile != null && Parafile.length != 0) {
			LOG.info_aw("选择工参数据：" + Parafile);
			GetDataByTypeTask.chooseFolder(driver, Parafile,
					LteupinterferePage.BtnPare, defaultWindowID);
		}

		if (Chrfile != null && Chrfile.length != 0) {
			LOG.info_aw("选择外部CHR：" + Chrfile);
			GetDataByTypeTask.chooseFolder(driver, Chrfile,
					LteupinterferePage.Btnchr, defaultWindowID);
		}
	}

	/**
	 * 解压下载到临时目录中的报告到指定路径
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 * @param ResultHome
	 */
	public static void lteupinterfere_downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			if (reportName[i].endsWith("zip") && reportName[i].contains("FDD")) {
				ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i],
						ResultHome);
			} else {
				if (reportName[i].endsWith("xlsx")) {
					FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\"
							+ reportName[i], ResultHome + "\\" + reportName[i]);
				}
			}
		}

	}

	/**
	 * 报告下载
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 * @return
	 */
	public static String[] downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		String[] reportName = new String[2];
		LoadingPage.Loading(driver, LteupinterferePage.BtnDownReport, "报告下载按钮");
		LteupinterferePage.ClickDownReport(driver,
				LteupinterferePage.BtnDownReport);
		reportName[0] = LteupinterferePage.GetText(driver,
				LteupinterferePage.BtnDownReport);
		TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,
				reportName[0]);
		if (LteupinterferePage.DownReportExist(driver,
				LteupinterferePage.BtnDownReportT)) {
			LteupinterferePage.ClickDownReport(driver,
					LteupinterferePage.BtnDownReportT);
			reportName[1] = LteupinterferePage.GetText(driver,
					LteupinterferePage.BtnDownReportT);
			TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,
					reportName[1]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}

}
