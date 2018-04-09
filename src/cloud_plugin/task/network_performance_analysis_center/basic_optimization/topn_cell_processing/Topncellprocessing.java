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

public class Topncellprocessing {

	public static String creatLteTopnTask(WebDriver driver, String defaultWindowID, String taskName,
			String ThemeOptimization, String[] BusyDataSet, String XiaoQuNumber, String XiaoQuBiLi,
			String XiaoQuBiLiValue, String ClusterLevelThreshod, String ContainMMLexception, String abnormalMRanalysis,
			String JieRuContainS1, String QieHuanCommunityautofilter, String[] IntraFrequency, String[] InterFrequency,
			String QieHuanabnormalMRanalysis, String[] cfgfile, String[] Pfmfile, String[] Parafile, String[] Warnfile,
			String[] operatefile, String[] OutChrfile, String[] InChrfile, String[] ThroughPutgate, String[] Disconnect,
			String[] Access) {
		// 打开TOPN
		NetworkAnalysisCenterTask.openTopnData(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 输入任务名称
		String Name = TopnCellProcessingPage.setTaskName(driver, taskName);
		// 选择优化对象
		ChooseTheme(driver, ThemeOptimization);
		// 忙时设置
		BusySet(driver, BusyDataSet);
		// 参数设置
		ParameterSet(driver, ThemeOptimization, XiaoQuNumber, XiaoQuBiLi, XiaoQuBiLiValue, ClusterLevelThreshod,
				ContainMMLexception, abnormalMRanalysis, JieRuContainS1, QieHuanCommunityautofilter, IntraFrequency,
				InterFrequency, QieHuanabnormalMRanalysis, ThroughPutgate, Disconnect, Access);
		SwitchDriver.switchDriverToSEQ(driver);
		// 数据选择
		ChooseData(driver, defaultWindowID, cfgfile, Pfmfile, Parafile, Warnfile, operatefile, OutChrfile, InChrfile);

		TopnCellProcessingPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * 选择优化对象
	 * 
	 * @param driver
	 * @param ThemeOptimization
	 *            ：掉线；接入；切换；其余输入为默认吞吐率
	 */
	private static void ChooseTheme(WebDriver driver, String ThemeOptimization) {
		if (ThemeOptimization != null) {
			LOG.info_aw("选择优化对象：" + ThemeOptimization);
			if (ThemeOptimization.equalsIgnoreCase("每用户平均吞吐率")) {

				CommonJQ.click(driver, TopnCellProcessingPage.everyUserThroughput_ID, true,
						TopnCellProcessingPage.SceneRadioBtnError);

			}
			if (ThemeOptimization.equalsIgnoreCase("掉线")) {
				CommonJQ.click(driver, TopnCellProcessingPage.LostTo_ID, true,
						TopnCellProcessingPage.DiaoXianRadioBtnError);
			}
			if (ThemeOptimization.equalsIgnoreCase("接入")) {
				CommonJQ.click(driver, TopnCellProcessingPage.AccessRRC_ID, true,
						TopnCellProcessingPage.JieRuRadioBtnError);
			}
			if (ThemeOptimization.equalsIgnoreCase("切换")) {
				CommonJQ.click(driver, TopnCellProcessingPage.Switching_ID, true,
						TopnCellProcessingPage.QieHuanRadioBtnError);
			}
			LOG.info_aw("已成功选择优化对象：" + ThemeOptimization);
		}

	}

	/**
	 * 忙时设置
	 * 
	 * @param driver
	 * @param BusyDataSet
	 */
	private static void BusySet(WebDriver driver, String[] BusyDataSet) {
		if (BusyDataSet != null) {

			for (int i = 0; i < BusyDataSet.length; i++) {
				if (!BusyDataSet[i].equalsIgnoreCase("")) {
					int j = i + 1;
					LOG.info_aw("设置第" + j + "个忙时点为：" + BusyDataSet[i]);
					CommonJQ.value(driver, "#date" + j, BusyDataSet[i], true);
					LOG.info_aw("已成功设置第" + j + "个忙时点为：" + BusyDataSet[i]);
				} else {
					continue;
				}
			}
		}
	}

	private static void ParameterSet(WebDriver driver, String ThemeOptimization, String XiaoQuNumber, String XiaoQuBiLi,
			String XiaoQuBiLiValue, String ClusterLevelThreshod, String ContainMMLexception, String abnormalMRanalysis,
			String JieRuContainS1, String QieHuanCommunityautofilter, String[] IntraFrequency, String[] InterFrequency,
			String QieHuanabnormalMRanalysis, String[] ThroughPutgate, String[] Disconnect, String[] Access) {
		if (ThemeOptimization != null) {

			if (ThemeOptimization.equalsIgnoreCase("吞吐率") || ThemeOptimization.equalsIgnoreCase("每用户平均吞吐率")) {
				{
					ThroughPutParameterSet(driver, ThroughPutgate);
				}
			}

			if (ThemeOptimization.equalsIgnoreCase("掉线")) {
				DiaoXianParameterSet(driver, XiaoQuNumber, XiaoQuBiLi, XiaoQuBiLiValue, ClusterLevelThreshod,
						ContainMMLexception, abnormalMRanalysis, Disconnect);
			}
			if (ThemeOptimization.equalsIgnoreCase("接入")) {
				JieRuParameterSet(driver, XiaoQuNumber, XiaoQuBiLi, XiaoQuBiLiValue, ClusterLevelThreshod,
						JieRuContainS1, abnormalMRanalysis, Disconnect);
			}
			if (ThemeOptimization.equalsIgnoreCase("切换")) {
				QieHuanParameterSet(driver, XiaoQuNumber, XiaoQuBiLi, XiaoQuBiLiValue, QieHuanCommunityautofilter,
						ClusterLevelThreshod, IntraFrequency, InterFrequency, QieHuanabnormalMRanalysis, Access);
			}

		}
	}

	private static void ThroughPutParameterSet(WebDriver driver, String[] ThroughPutgate) {
		if (ThroughPutgate != null) {
			CommonJQ.click(driver, TopnCellProcessingPage.parametersetting_ID, true);
			for (int i = 0; i < ThroughPutgate.length; i++) {
				if (!ThroughPutgate[i].equalsIgnoreCase("")) {
					LOG.info_aw("修改吞吐率第" + i + "个参数为" + ThroughPutgate[i]);
					CommonJQ.value(driver, TopnCellProcessingPage.ThroughPutgate[i], ThroughPutgate[i], true);
				} else {
					continue;
				}
			}
			CommonJQ.click(driver, TopnCellProcessingPage.okButton, true);
		}

	}

	/**
	 * 优化对象选择掉线时 参数设置
	 * 
	 * @param driver
	 * @param XiaoQuNumber
	 *            小区数
	 * @param XiaoQuBiLi
	 * @param XiaoQuBiLiValue
	 * @param ClusterLevelThreshod
	 * @param ContainMMLexception
	 * @param abnormalMRanalysis
	 */
	private static void DiaoXianParameterSet(WebDriver driver, String XiaoQuNumber, String XiaoQuBiLi,
			String XiaoQuBiLiValue, String ClusterLevelThreshod, String ContainMMLexception, String abnormalMRanalysis,
			String[] Disconnect) {
		if (XiaoQuNumber != null || XiaoQuBiLi != null || XiaoQuBiLiValue != null || ClusterLevelThreshod != null
				|| ContainMMLexception != null || abnormalMRanalysis != null || Disconnect != null) {
			CommonJQ.click(driver, TopnCellProcessingPage.parametersetting_ID, true);
			if (XiaoQuNumber != null) {
				LOG.info_aw("设置-TOPN小区数-为：" + XiaoQuNumber);
				CommonJQ.value(driver, TopnCellProcessingPage.TOPNUM_ID, XiaoQuNumber, true);
				LOG.info_aw("已成功设置-TOPN小区数-为：" + XiaoQuNumber);
			}
			if (XiaoQuBiLi != null) {
				LOG.info_aw("选择-TOPN小区比例-");
				CommonJQ.click(driver, TopnCellProcessingPage.TOPPROPORTION, true,
						TopnCellProcessingPage.XiaoQuBiLiRadioBtnError);
				LOG.info_aw("已成功选择-TOPN小区比例-");
				if (XiaoQuBiLiValue != null) {
					LOG.info_aw("设置-TOPN小区比例-值为：" + XiaoQuBiLiValue);
					CommonJQ.value(driver, TopnCellProcessingPage.paramLabelBlank, XiaoQuBiLiValue, true);
					LOG.info_aw("已成功设置-TOPN小区比例-值为：" + XiaoQuBiLiValue);
				}
			}
			if (ClusterLevelThreshod != null) {
				LOG.info_aw("取消勾选-簇级指标达到门限，继续处理未达标小区 -");
				CommonJQ.click(driver, TopnCellProcessingPage.isContinueAnalyze, true,
						TopnCellProcessingPage.ClusterLevelThreshodError);
				LOG.info_aw("成功取消勾选-簇级指标达到门限，继续处理未达标小区 -");
			}
			if (ContainMMLexception != null) {
				LOG.info_aw("计算公式选择为：含MME异常原因");
				CommonJQ.click(driver, TopnCellProcessingPage.CONTAINING_MME, true,
						TopnCellProcessingPage.IncloudMMLRadioBtnError);
				LOG.info_aw("已成功选择计算公式为：含MME异常原因");
			}
			if (abnormalMRanalysis != null || Disconnect != null) {
				CommonJQ.click(driver, TopnCellProcessingPage.GaoJiCanShu, true);
				if (abnormalMRanalysis != null) {
					LOG.info_aw("高级参数设置为：异常MR分析（默认为全MR分析）");
					CommonJQ.click(driver, TopnCellProcessingPage.abnormalMR, true,
							TopnCellProcessingPage.IncloudMMLRadioBtnError);
					LOG.info_aw("已成功设置高级参数为：异常MR分析");
				}
				if (Disconnect != null) {
					for (int i = 0; i < Disconnect.length; i++) {
						if (!Disconnect[i].equalsIgnoreCase("")) {
							LOG.info_aw("修改掉线第" + i + "个参数为" + Disconnect[i]);
							CommonJQ.value(driver, TopnCellProcessingPage.Disconnectgate[i], Disconnect[i], true);
						} else {
							continue;
						}
					}
				}

			}
			CommonJQ.click(driver, TopnCellProcessingPage.okButton, true);
		}
	}

	/**
	 * 优化对象选择 接入 时 参数设置
	 * 
	 * @param driver
	 * @param JieRuXiaoQuNumber
	 * @param JieRuXiaoQuBiLi
	 * @param JieRuXiaoQuBiLiValue
	 * @param JieRuClusterLevelThreshod
	 * @param JieRuContainS1
	 * @param JieRuabnormalMRanalysis
	 */
	private static void JieRuParameterSet(WebDriver driver, String XiaoQuNumber, String XiaoQuBiLi,
			String XiaoQuBiLiValue, String ClusterLevelThreshod, String JieRuContainS1, String abnormalMRanalysis,
			String[] Disconnect) {
		if (XiaoQuNumber != null || XiaoQuBiLi != null || XiaoQuBiLiValue != null || ClusterLevelThreshod != null
				|| JieRuContainS1 != null || abnormalMRanalysis != null || Disconnect != null) {
			CommonJQ.click(driver, TopnCellProcessingPage.parametersetting_ID, true);
			if (XiaoQuNumber != null) {
				LOG.info_aw("设置-TOPN小区数-为：" + XiaoQuNumber);
				CommonJQ.value(driver, TopnCellProcessingPage.accessTOPNUM_ID, XiaoQuNumber, true);
				LOG.info_aw("已成功设置-TOPN小区数-为：" + XiaoQuNumber);
			}
			if (XiaoQuBiLi != null) {
				LOG.info_aw("选择-TOPN小区比例-");
				CommonJQ.click(driver, TopnCellProcessingPage.TOPPROPORTION, true,
						TopnCellProcessingPage.XiaoQuBiLiRadioBtnError);
				LOG.info_aw("已成功选择-TOPN小区比例-");
				if (XiaoQuBiLiValue != null) {
					LOG.info_aw("设置-TOPN小区比例-值为：" + XiaoQuBiLiValue);
					CommonJQ.value(driver, TopnCellProcessingPage.access_TOPPROPORTION, XiaoQuBiLiValue, true);
					LOG.info_aw("已成功设置-TOPN小区比例-值为：" + XiaoQuBiLiValue);
				}
			}
			if (ClusterLevelThreshod != null) {
				LOG.info_aw("取消勾选-簇级指标达到门限，继续处理未达标小区 -");
				CommonJQ.click(driver, TopnCellProcessingPage.isContinueAnalyze, true,
						TopnCellProcessingPage.ClusterLevelThreshodError);
				LOG.info_aw("成功取消勾选-簇级指标达到门限，继续处理未达标小区 -");
			}
			if (JieRuContainS1 != null) {
				LOG.info_aw("计算公式选择：-包含S1 -");
				CommonJQ.click(driver, TopnCellProcessingPage.ContainS1, true);
				LOG.info_aw("已成功选择计算公式为：-包含S1 -");
			}
			if (abnormalMRanalysis != null || Disconnect != null) {
				CommonJQ.click(driver, TopnCellProcessingPage.GaoJiCanShu, true);
				if (abnormalMRanalysis != null) {
					LOG.info_aw("高级参数设置为：异常MR分析（默认为全MR分析）");
					CommonJQ.click(driver, TopnCellProcessingPage.abnormalMR, true,
							TopnCellProcessingPage.IncloudMMLRadioBtnError);
					LOG.info_aw("已成功设置高级参数为：异常MR分析");
				}
				if (Disconnect != null) {
					for (int i = 0; i < Disconnect.length; i++) {
						if (!Disconnect[i].equalsIgnoreCase("")) {
							LOG.info_aw("修改接入第" + i + "个参数为" + Disconnect[i]);
							CommonJQ.value(driver, TopnCellProcessingPage.Disconnectgate[i], Disconnect[i], true);
						} else {
							continue;
						}
					}
				}

			}
			CommonJQ.click(driver, TopnCellProcessingPage.okButton, true);
		}
	}

	/**
	 * 切换 参数设置
	 * 
	 * @param driver
	 * @param QieHuanXiaoQuNumber
	 * @param QieHuanXiaoQuBiLi
	 * @param QieHuanXiaoQuBiLiValue
	 * @param QieHuanCommunityautofilter
	 * @param QieHuanClusterLevelThreshod
	 * @param IntraFrequency
	 * @param InterFrequency
	 * @param QieHuanabnormalMRanalysis
	 */
	private static void QieHuanParameterSet(WebDriver driver, String XiaoQuNumber, String XiaoQuBiLi,
			String XiaoQuBiLiValue, String QieHuanCommunityautofilter, String ClusterLevelThreshod,
			String[] IntraFrequency, String[] InterFrequency, String QieHuanabnormalMRanalysis, String[] Access) {
		if (XiaoQuNumber != null || XiaoQuBiLi != null || XiaoQuBiLiValue != null || ClusterLevelThreshod != null
				|| IntraFrequency != null || InterFrequency != null || QieHuanCommunityautofilter != null
				|| QieHuanabnormalMRanalysis != null || Access != null) {
			CommonJQ.click(driver, TopnCellProcessingPage.parametersetting_ID, true);
			if (XiaoQuNumber != null) {
				LOG.info_aw("设置-TOPN小区数-为：" + XiaoQuNumber);
				CommonJQ.value(driver, TopnCellProcessingPage.switchingTOPNUM, XiaoQuNumber, true);
				LOG.info_aw("已成功设置-TOPN小区数-为：" + XiaoQuNumber);
			}

			if (XiaoQuBiLi != null) {
				LOG.info_aw("选择-TOPN小区比例-");
				CommonJQ.click(driver, TopnCellProcessingPage.TOPPROPORTION, true,
						TopnCellProcessingPage.XiaoQuBiLiRadioBtnError);
				LOG.info_aw("已成功选择-TOPN小区比例-");
				if (XiaoQuBiLiValue != null) {
					LOG.info_aw("设置-TOPN小区比例-值为：" + XiaoQuBiLiValue);
					CommonJQ.value(driver, TopnCellProcessingPage.paramLabelBlank, XiaoQuBiLiValue, true);
					LOG.info_aw("已成功设置-TOPN小区比例-值为：" + XiaoQuBiLiValue);
				}
			}
			if (QieHuanCommunityautofilter != null) {
				LOG.info_aw("选择-Top小区自动筛选-");
				CommonJQ.value(driver, TopnCellProcessingPage.TOPCUSTOM, QieHuanCommunityautofilter, true);
				LOG.info_aw("已成功选择-Top小区自动筛选-");
			}

			if (ClusterLevelThreshod != null) {
				LOG.info_aw("取消勾选-簇级指标达到门限，继续处理未达标小区 -");
				CommonJQ.click(driver, TopnCellProcessingPage.isContinueAnalyzeqiehuan, true);
				LOG.info_aw("已成功取消勾选-簇级指标达到门限，继续处理未达标小区 -");
			}

			if (IntraFrequency != null) {
				if (!IntraFrequency[0].equalsIgnoreCase("null")) {
					LOG.info_aw("取消勾选-同频切换 -");
					CommonJQ.click(driver, TopnCellProcessingPage.TongPinQieHuan, true);
					LOG.info_aw("已成功取消勾选-同频切换 -");
				}
				if (!IntraFrequency[1].equalsIgnoreCase("null")) {
					LOG.info_aw("设置同频切换成功率为：" + IntraFrequency[1]);
					CommonJQ.value(driver, TopnCellProcessingPage.TongPinQieHuanSuccessRate, IntraFrequency[1], true);
					LOG.info_aw("已成功设置同频切换成功率为：" + IntraFrequency[1]);
				}
				if (!IntraFrequency[2].equalsIgnoreCase("null")) {
					LOG.info_aw("同频切换选择第" + IntraFrequency[2] + "个指标");
					CommonJQ.click(driver, TopnCellProcessingPage.ZhiBiaoChoose, true, 0);
					CommonJQ.click(driver, TopnCellProcessingPage.TPZhiBiao1, true,
							Integer.parseInt(IntraFrequency[2]));
					CommonJQ.click(driver, TopnCellProcessingPage.TongPinOk, true);
					LOG.info_aw("成功选择同频切换第" + IntraFrequency[2] + "个指标");
				}
			}
			if (InterFrequency != null) {

				if (!InterFrequency[0].equalsIgnoreCase("null")) {
					LOG.info_aw("取消勾选-异频切换 -");
					CommonJQ.click(driver, TopnCellProcessingPage.YiPinQieHuan, true);
					LOG.info_aw("已成功取消勾选-异频切换 -");
				}
				if (!InterFrequency[1].equalsIgnoreCase("null")) {
					LOG.info_aw("设置异频切换成功率为：" + IntraFrequency[1]);
					CommonJQ.value(driver, TopnCellProcessingPage.YiPinQieHuanSuccessRate, InterFrequency[1], true);
					LOG.info_aw("已成功设置异频切换成功率为：" + IntraFrequency[1]);
				}
				if (!InterFrequency[2].equalsIgnoreCase("null")) {
					LOG.info_aw("异频切换选择第" + IntraFrequency[2] + "个指标");
					CommonJQ.click(driver, TopnCellProcessingPage.ZhiBiaoChoose, true, 1);
					CommonJQ.click(driver, TopnCellProcessingPage.TPZhiBiao1, true,
							Integer.parseInt(InterFrequency[2]));
					CommonJQ.click(driver, TopnCellProcessingPage.TongPinOk, true);
					LOG.info_aw("成功选择异频切换第" + IntraFrequency[2] + "个指标");
				}
			}
			if (QieHuanabnormalMRanalysis != null || Access != null) {
				CommonJQ.click(driver, TopnCellProcessingPage.GaoJiCanShu, true);
				if (QieHuanabnormalMRanalysis != null) {
					LOG.info_aw("高级参数设置为：异常MR分析（默认为全MR分析）");
					CommonJQ.click(driver, TopnCellProcessingPage.abnormalMR2, true,
							TopnCellProcessingPage.IncloudMMLRadioBtnError);
					LOG.info_aw("已成功设置高级参数为：异常MR分析");
				}
				if (Access != null) {
					for (int i = 0; i < Access.length; i++) {
						if (!Access[i].equalsIgnoreCase("")) {
							LOG.info_aw("修改切换第" + i + "个参数为" + Access[i]);
							CommonJQ.value(driver, TopnCellProcessingPage.Accessgate[i], Access[i], true);
						} else {
							continue;
						}
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
	private static void ChooseData(WebDriver driver, String defaultWindowID, String[] cfgfile, String[] Pfmfile,
			String[] Parafile, String[] Warnfile, String[] operatefile, String[] OutChrfile, String[] InChrfile) {
		if (cfgfile != null && cfgfile.length != 0) {
			LOG.info_aw("选择配置数据：" + cfgfile);
			GetDataByTypeTask.chooseFolder(driver, cfgfile, TopnCellProcessingPage.selecteNodeBConfigFile_ID,
					defaultWindowID);

		}

		if (Pfmfile != null && Pfmfile.length != 0) {
			LOG.info_aw("选择话统数据：" + Pfmfile);
			GetDataByTypeTask.chooseFolder(driver, Pfmfile, TopnCellProcessingPage.selectPData, defaultWindowID);
		}

		if (Parafile != null && Parafile.length != 0) {
			LOG.info_aw("选择工参数据：" + Parafile);
			GetDataByTypeTask.chooseFolder(driver, Parafile, TopnCellProcessingPage.selectLteprojectparamter_ID,
					defaultWindowID);
		}

		if (Warnfile != null && Warnfile.length != 0) {
			LOG.info_aw("选择告警数据：" + Warnfile);
			GetDataByTypeTask.chooseFolder(driver, Warnfile, TopnCellProcessingPage.selectWarn_ID, defaultWindowID);
		}
		if (operatefile != null && operatefile.length != 0) {
			LOG.info_aw("选择操作日志：" + operatefile);
			GetDataByTypeTask.chooseFolder(driver, operatefile, TopnCellProcessingPage.selectoperate_ID,
					defaultWindowID);
		}

		if (OutChrfile != null && OutChrfile.length != 0) {
			LOG.info_aw("选择外部CHR：" + OutChrfile);
			GetDataByTypeTask.chooseFolder(driver, OutChrfile, TopnCellProcessingPage.selectMrData_ID, defaultWindowID);
		}

		if (InChrfile != null && InChrfile.length != 0) {
			LOG.info_aw("选择内部CHR：" + InChrfile);
			GetDataByTypeTask.chooseFolder(driver, InChrfile,
					"$('iframe[name=main]').contents().find('span[id=\"select_eNodeBConfigFile\"]').eq(1).click()",
					defaultWindowID);
		}

	}

	public static void ThroughputRate_downLoad_report(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String reportName = downLoad_report(driver, defaultWindowID, taskname);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, EnvConstant.Path_DownLoad);
		String reportName1 = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "吞吐率分析报告_");
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName1, ResultHome);
	}

	public static void Disconnect_downLoad_report(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {

		FileHandle.delSubFile(ResultHome);
		String reportName = downLoad_report(driver, defaultWindowID, taskname);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, EnvConstant.Path_DownLoad);
		String reportName1 = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "掉线分析报告_");
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName1, ResultHome);
	}

	public static void Access_downLoad_report(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String reportName = downLoad_report(driver, defaultWindowID, taskname);

		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, EnvConstant.Path_DownLoad);
		String reportName1 = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "接入分析报告_");
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName1, ResultHome);
	}

	public static void Switch_downLoad_report(WebDriver driver, String defaultWindowID, String taskname,
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
		CommonJQ.click(driver, "#downloadReportsId0", true, "报告下载按钮");
		String reportName = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", true);
		reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}

}
