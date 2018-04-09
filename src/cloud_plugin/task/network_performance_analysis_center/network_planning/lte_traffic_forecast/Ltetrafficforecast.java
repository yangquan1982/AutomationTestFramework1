package cloud_plugin.task.network_performance_analysis_center.network_planning.lte_traffic_forecast;

import org.openqa.selenium.WebDriver;

import com.huawei.hutaf.webtest.htmlaw.BasicAction;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_trafficforecast.LteTrafficForecastPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.LOG;
import common.util.SwitchDriver;

public class Ltetrafficforecast {

	public static String creatLteTrafficForecastTask(WebDriver driver, String defaultWindowID, String taskName,
			String[] SelectTaskType, String ULhuiliu, String[] Gcfg, String[] Ucfg, String[] Lcfg, String[] GPara,
			String[] UPara, String[] LPara, String[] Gperf, String[] Uperf, String[] Lperf, String[] Gchr, String[] Gmr,
			String[] Umr, String TerminalFile, String[] Gprsperf, String[] Uprsperf, String[] Lprsperf,
			String LevelSelect, String[] SeperateFile, String NeGroupCell, String NeGroupFile, String HolidayFile,
			String Date, String YuCeLevel, String[] ZhibiaoChoose, String[] ZhibiaoChooseLeft,
			String[] MoRenZhibiaoChoose, String[] MoRenZhibiaoChooseLeft, String JiSuanYinZi, String[] YiZhiSet,
			String[] Boss, String[] HuiLiuPsSet, String[] UserDevelopSet, String[] SeparterLiuLiang, String[] BusyTime,
			String[] EventYuCe, String[] UserNumberYuCe) {
		// 打开LTE话务预测
		NetworkAnalysisCenterTask.openLTEhuawuyuce(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 输入任务名称
		String Name = LteTrafficForecastPage.setTaskName(driver, taskName);
		// 选择业务模块
		ChooseTaskType(driver, SelectTaskType);
		// 选择数据
		ChooseData(driver, defaultWindowID, ULhuiliu, Gcfg, Ucfg, Lcfg, GPara, UPara, LPara, Gperf, Uperf, Lperf, Gchr,
				Gmr, Umr, TerminalFile, Gprsperf, Uprsperf, Lprsperf, LevelSelect, SeperateFile, NeGroupCell,
				NeGroupFile);
		// 参数设置
		ParameterSet(driver, defaultWindowID, HolidayFile, Date, YuCeLevel, ZhibiaoChoose, ZhibiaoChooseLeft,
				MoRenZhibiaoChoose, MoRenZhibiaoChooseLeft, JiSuanYinZi, YiZhiSet, Boss, HuiLiuPsSet, UserDevelopSet,
				SeparterLiuLiang, BusyTime, EventYuCe, UserNumberYuCe);
		LteTrafficForecastPage.commitBtnClick(driver);
		return Name;
	}

	private static void ChooseTaskType(WebDriver driver, String[] SelectTaskType) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (SelectTaskType != null) {
			for (int i = 0; i < SelectTaskType.length; i++) {
				if (SelectTaskType[i].equals("基础话务预测")) {
					LOG.info_aw("业务模块不选择  基础话务预测");
					CommonJQ.click(driver, LteTrafficForecastPage.CBoxBasic, true);
				}
				if (SelectTaskType[i].equals("话务抑制还原")) {
					LOG.info_aw("业务模块选择  话务抑制还原");
					CommonJQ.click(driver, LteTrafficForecastPage.CBoxDepress, true);
				}
				if (SelectTaskType[i].equals("回流场景预测")) {
					LOG.info_aw("业务模块选择  回流场景预测");
					CommonJQ.click(driver, LteTrafficForecastPage.CBoxStarTerm, true);
				}
				if (SelectTaskType[i].equals("用户发展预测")) {
					LOG.info_aw("业务模块选择  用户发展预测");
					CommonJQ.click(driver, LteTrafficForecastPage.CBoxGradUser, true);
				}
				if (SelectTaskType[i].equals("分业务流量预测")) {
					LOG.info_aw("业务模块选择  分业务流量预测");
					CommonJQ.click(driver, LteTrafficForecastPage.CBoxSeparate, true);
				}
				if (SelectTaskType[i].equals("重大事件预测")) {
					LOG.info_aw("业务模块选择  重大事件预测");
					CommonJQ.click(driver, LteTrafficForecastPage.CBoxFuncArea, true);
				}
			}
		}
		CommonJQ.click(driver, LteTrafficForecastPage.BtnNext, true, "点击下一步按钮失败");
		SwitchDriver.switchDriverToSEQ(driver);
	}

	private static void ParameterSet(WebDriver driver, String defaultWindowID, String HolidayFile, String Date,
			String YuCeLevel, String[] ZhibiaoChoose, String[] ZhibiaoChooseLeft, String[] MoRenZhibiaoChoose,
			String[] MoRenZhibiaoChooseLeft, String JiSuanYinZi, String[] YiZhiSet, String[] Boss, String[] HuiLiuPsSet,
			String[] UserDevelopSet, String[] SeparterLiuLiang, String[] BusyTime, String[] EventYuCe,
			String[] UserNumberYuCe) {
		// 回流场景设置
		if (Boss != null || HuiLiuPsSet != null) {
			HuiLiuParameterSet(driver, defaultWindowID, Boss, HuiLiuPsSet);
		}
		// 公共参数
		if (HolidayFile != null || Date != null) {
			CommonParameterSet(driver, HolidayFile, Date);
		}
		// 基础指标设置
		BasicParameterSet(driver, YuCeLevel, ZhibiaoChoose, ZhibiaoChooseLeft, MoRenZhibiaoChoose,
				MoRenZhibiaoChooseLeft, JiSuanYinZi);
		// 话务抑制还原
		if (YiZhiSet != null) {
			YiZhiParameterSet(driver, YiZhiSet);
		}

		// 用户发展预测
		if (UserDevelopSet != null) {
			UserDevelopParameterSet(driver, UserDevelopSet);
		}
		// 分业务流量预测
		if (SeparterLiuLiang != null || BusyTime != null) {
			FenLiuliangParameterSet(driver, SeparterLiuLiang, BusyTime);
		}
		// 重大事件预测
		if (EventYuCe != null || UserNumberYuCe != null) {
			ZhongdaParameterSet(driver, EventYuCe, UserNumberYuCe);
		}

	}

	private static void CommonParameterSet(WebDriver driver, String HolidayFile, String Date) {
		if (HolidayFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LOG.info_aw("选择节假日表文件");
			CommonJQ.click(driver, LteTrafficForecastPage.BtntargetHolidayTableData, true);
			CommonFile.ChooseOneFile(HolidayFile);
			CommonJQ.click(driver, LteTrafficForecastPage.BtnterminalOk, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (Date != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LOG.info_aw("修改预测时间");
			CommonJQ.value(driver, LteTrafficForecastPage.BtnYuCeTime, Date);
			SwitchDriver.switchDriverToSEQ(driver);
		}
	}

	private static void BasicParameterSet(WebDriver driver, String YuCeLevel, String[] ZhibiaoChoose,
			String[] ZhibiaoChooseLeft, String[] MoRenZhibiaoChoose, String[] MoRenZhibiaoChooseLeft,
			String JiSuanYinZi) {

		if (YuCeLevel != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			if (YuCeLevel.equalsIgnoreCase("Cell")) {
				LOG.info_aw("修改预测级别为Cell");
				CommonJQ.click(driver, LteTrafficForecastPage.RBtnCell, true);
			}
			if (YuCeLevel.equalsIgnoreCase("Group")) {
				LOG.info_aw("修改预测级别为Group");
				CommonJQ.click(driver, LteTrafficForecastPage.RBtnGroup, true);
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (ZhibiaoChoose != null || ZhibiaoChooseLeft != null || MoRenZhibiaoChoose != null
				|| MoRenZhibiaoChooseLeft != null || JiSuanYinZi != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			if (ZhibiaoChoose != null || ZhibiaoChooseLeft != null || JiSuanYinZi != null) {
				CommonJQ.click(driver, LteTrafficForecastPage.BtnAllZhiBiao, true);
				if (ZhibiaoChoose != null) {
					LOG.info_aw("指标选择：从全部指标中选择");
					for (int i = 0; i < ZhibiaoChoose.length; i++) {
						CommonJQ.click(driver, LteTrafficForecastPage.RBoxChooseZhibiao, true,
								Integer.valueOf(ZhibiaoChoose[i]));
					}
					CommonJQ.click(driver, LteTrafficForecastPage.BtnRight, true);
				}
				if (ZhibiaoChooseLeft != null) {
					LOG.info_aw("指标选择：从右侧取消选择");
					for (int i = 0; i < ZhibiaoChooseLeft.length; i++) {
						CommonJQ.click(driver, LteTrafficForecastPage.RBoxChooseZhibiaoLeft, true,
								Integer.valueOf(ZhibiaoChooseLeft[i]));
					}
					CommonJQ.click(driver, LteTrafficForecastPage.BtnLeft, true);
				}
				if (JiSuanYinZi != null) {
					if (JiSuanYinZi.equals("1")) {
						LOG.info_aw("指标选择：取消计算预测增长因子");
						CommonJQ.click(driver, LteTrafficForecastPage.RBtnAddYinZi, true);
					}
					if (JiSuanYinZi.equals("3")) {
						LOG.info_aw("指标选择：取消计算预测增长因子");
						CommonJQ.click(driver, LteTrafficForecastPage.RBtnAddYinZi, true);
					}
				}
				CommonJQ.click(driver, LteTrafficForecastPage.BtnParameterSetOk, true);
			}

			if (MoRenZhibiaoChoose != null || MoRenZhibiaoChooseLeft != null || JiSuanYinZi != null) {
				CommonJQ.click(driver, LteTrafficForecastPage.BtnMoRenZhiBiao, true);
				if (MoRenZhibiaoChoose != null) {
					LOG.info_aw("指标选择：从全部指标中选择");
					for (int i = 0; i < MoRenZhibiaoChoose.length; i++) {
						CommonJQ.click(driver, LteTrafficForecastPage.RBoxChooseZhibiao, true,
								Integer.valueOf(MoRenZhibiaoChoose[i]));
					}
					CommonJQ.click(driver, LteTrafficForecastPage.BtnRight, true);
				}

				if (MoRenZhibiaoChooseLeft != null) {
					LOG.info_aw("指标选择：从右侧取消选择");
					for (int i = 0; i < MoRenZhibiaoChooseLeft.length; i++) {
						CommonJQ.click(driver, LteTrafficForecastPage.RBoxChooseZhibiaoLeft, true,
								Integer.valueOf(MoRenZhibiaoChooseLeft[i]));
					}
					CommonJQ.click(driver, LteTrafficForecastPage.BtnLeft, true);
				}
				if (JiSuanYinZi != null) {
					if (JiSuanYinZi.equals("2")) {
						LOG.info_aw("指标选择：取消计算预测增长因子");
						CommonJQ.click(driver, LteTrafficForecastPage.RBtnAddYinZi, true);
					}
					if (JiSuanYinZi.equals("3")) {
						LOG.info_aw("指标选择：取消计算预测增长因子");
						CommonJQ.click(driver, LteTrafficForecastPage.RBtnAddYinZi, true);
					}
				}
				CommonJQ.click(driver, LteTrafficForecastPage.BtnParameterSetOk, true);
			}
			SwitchDriver.switchDriverToSEQ(driver);
		} else {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			if (CommonJQ.isExisted(driver, LteTrafficForecastPage.BtnMoRenZhiBiao, true)) {
				LOG.info_aw("指标选择：默认指标");
				CommonJQ.click(driver, LteTrafficForecastPage.BtnMoRenZhiBiao, true);
				CommonJQ.click(driver, LteTrafficForecastPage.BtnParameterSetOk, true);

			}
			SwitchDriver.switchDriverToSEQ(driver);
		}

	}

	private static void YiZhiParameterSet(WebDriver driver, String[] YiZhiSet) {
		if (YiZhiSet != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteTrafficForecastPage.BtnShouDoSet, true, 1);
			for (int i = 0; i < YiZhiSet.length; i++) {
				if (!YiZhiSet[i].equalsIgnoreCase("")) {
					LOG.info_aw("修改话务抑制还原第" + i + "个参数为" + YiZhiSet[i]);
					CommonJQ.value(driver, LteTrafficForecastPage.TextYiZhi[i], YiZhiSet[i], true);
				} else {
					continue;
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}

	}

	private static void HuiLiuParameterSet(WebDriver driver, String defaultWindowID, String[] Boss,
			String[] HuiLiuPsSet) {
		if (Boss != null && Boss.length != 0) {
			LOG.info_aw("选择BOSS话单");
			GetDataByTypeTask.chooseFolder(driver, Boss, "select_targetBossBillData", defaultWindowID);
		}

		if (HuiLiuPsSet != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			for (int i = 0; i < HuiLiuPsSet.length; i++) {
				if (!HuiLiuPsSet[i].equalsIgnoreCase("")) {
					LOG.info_aw("修改回流场景预测第" + i + "个参数为" + HuiLiuPsSet[i]);
					CommonJQ.value(driver, LteTrafficForecastPage.HuiLiuSetParemeter[i], HuiLiuPsSet[i], true);
				} else {
					continue;
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}

	}

	private static void UserDevelopParameterSet(WebDriver driver, String[] UserDevelopSet) {
		if (UserDevelopSet != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			for (int i = 0; i < UserDevelopSet.length; i++) {
				if (!UserDevelopSet[i].equalsIgnoreCase("")) {
					LOG.info_aw("修改用户发展预测第" + i + "个参数为" + UserDevelopSet[i]);
					CommonJQ.value(driver, LteTrafficForecastPage.UserDevelopPlan[i], UserDevelopSet[i], true);
				} else {
					continue;
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
	}

	private static void FenLiuliangParameterSet(WebDriver driver, String[] SeparterLiuLiang, String[] BusyTime) {
		if (SeparterLiuLiang != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			for (int i = 0; i < SeparterLiuLiang.length; i++) {
				if (!SeparterLiuLiang[i].equalsIgnoreCase("")) {
					LOG.info_aw("选择第" + SeparterLiuLiang[i] + "个指标及业务选择");
					CommonJQ.click(driver, "span[id=\"kpiTree_" + SeparterLiuLiang[i] + "_check\"]", true);
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (BusyTime != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			for (int i = 0; i < BusyTime.length; i++) {
				if (!BusyTime[i].equalsIgnoreCase("")) {
					LOG.info_aw("选择" + BusyTime[i] + "忙时");
					int m = Integer.valueOf(BusyTime[i]);
					System.out.println("选择忙时" + m);
					CommonJQ.click(driver, LteTrafficForecastPage.BtnHourList, true, m);

				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}

	}

	private static void ZhongdaParameterSet(WebDriver driver, String[] EventYuCe, String[] UserNumberYuCe) {

		if (EventYuCe != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			for (int i = 0; i < EventYuCe.length; i++) {
				if (!EventYuCe[i].equalsIgnoreCase("")) {
					LOG.info_aw("选择第" + EventYuCe[i] + "个指标及业务选择");
					CommonJQ.click(driver, "span[id=\"sceneTree_" + EventYuCe[i] + "_check\"]", true);
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (UserNumberYuCe != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			for (int i = 0; i < UserNumberYuCe.length; i++) {
				if (!UserNumberYuCe[i].equalsIgnoreCase("")) {
					LOG.info_aw("修改用户数预测第" + i + "个参数");
					CommonJQ.value(driver, LteTrafficForecastPage.TextgreatEventCount[i], UserNumberYuCe[i],
							"修改用户数预测失败");
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}

	}

	private static void ChooseData(WebDriver driver, String defaultWindowID, String ULhuiliu, String[] Gcfg,
			String[] Ucfg, String[] Lcfg, String[] GPara, String[] UPara, String[] LPara, String[] Gperf,
			String[] Uperf, String[] Lperf, String[] Gchr, String[] Gmr, String[] Umr, String TerminalFile,
			String[] Gprsperf, String[] Uprsperf, String[] Lprsperf, String LevelSelect, String[] SeperateFile,
			String NeGroupCell, String NeGroupFile) {
		if (ULhuiliu != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteTrafficForecastPage.CBoxGLRefluxScene, true);
			CommonJQ.click(driver, LteTrafficForecastPage.CBoxulRefluxScene, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (Gcfg != null && Gcfg.length != 0) {
			LOG.info_aw("选择GSM配置数据");
			GetDataByTypeTask.chooseFolder(driver, Gcfg, LteTrafficForecastPage.BtnGCfg, defaultWindowID);

		}

		if (Ucfg != null && Ucfg.length != 0) {
			LOG.info_aw("选择UMTS配置数据");
			GetDataByTypeTask.chooseFolder(driver, Ucfg, LteTrafficForecastPage.BtnUCfg, defaultWindowID);
		}

		if (Lcfg != null && Lcfg.length != 0) {
			LOG.info_aw("选择LTE配置数据");
			GetDataByTypeTask.chooseFolder(driver, Lcfg, LteTrafficForecastPage.BtnLCfg, defaultWindowID);
		}

		if (GPara != null && GPara.length != 0) {
			LOG.info_aw("选择GSM工参数据");
			GetDataByTypeTask.chooseFolder(driver, GPara, LteTrafficForecastPage.BtnGpara, defaultWindowID);
		}
		if (UPara != null && UPara.length != 0) {
			LOG.info_aw("选择UMTS工参数据");
			GetDataByTypeTask.chooseFolder(driver, UPara, LteTrafficForecastPage.BtnUpara, defaultWindowID);
		}

		if (LPara != null && LPara.length != 0) {
			LOG.info_aw("选择LTE工参数据");
			GetDataByTypeTask.chooseFolder(driver, LPara, LteTrafficForecastPage.BtnLpara, defaultWindowID);
		}
		if (Gperf != null && Gperf.length != 0) {
			LOG.info_aw("选择GSM原始话统数据");
			GetDataByTypeTask.chooseFolder(driver, Gperf, LteTrafficForecastPage.BtnGperf, defaultWindowID);
		}
		if (Uperf != null && Uperf.length != 0) {
			LOG.info_aw("选择UMTS原始话统数据");
			GetDataByTypeTask.chooseFolder(driver, Uperf, LteTrafficForecastPage.BtnUperf, defaultWindowID);
		}
		if (Lperf != null && Lperf.length != 0) {
			LOG.info_aw("选择LTE原始话统数据");
			GetDataByTypeTask.chooseFolder(driver, Lperf, LteTrafficForecastPage.BtnLperf, defaultWindowID);
		}
		if (Gchr != null && Gchr.length != 0) {
			LOG.info_aw("选择GSM CHR数据");
			GetDataByTypeTask.chooseFolder(driver, Gchr, LteTrafficForecastPage.BtnGchr, defaultWindowID);
		}
		if (Gmr != null && Gmr.length != 0) {
			LOG.info_aw("选择GSM mr数据");
			GetDataByTypeTask.chooseFolder(driver, Gmr, LteTrafficForecastPage.BtnGmr, defaultWindowID);
		}
		if (Umr != null && Umr.length != 0) {
			LOG.info_aw("选择UMTE MR数据");
			GetDataByTypeTask.chooseFolder(driver, Umr, LteTrafficForecastPage.BtnUmr, defaultWindowID);
		}
		if (TerminalFile != null) {
			LOG.info_aw("选择终端数据");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteTrafficForecastPage.BtnterminalData, true);
			CommonFile.ChooseOneFile(TerminalFile);
			for (int i = 0; i < 60; i++) {
				if (CommonJQ.isExisted(driver, LteTrafficForecastPage.Loading, true)) {
					BasicAction.sleep(1);
				} else {
					break;
				}
			}
			CommonJQ.click(driver, LteTrafficForecastPage.BtnterminalOk, true);
			SwitchDriver.switchDriverToSEQ(driver);
		} else {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			if (CommonJQ.isExisted(driver, LteTrafficForecastPage.RBtnNeGroupData, true)) {
				CommonJQ.click(driver, LteTrafficForecastPage.RBtnNeGroupData, true);
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}

		if (Gprsperf != null && Gprsperf.length != 0) {
			LOG.info_aw("选择GSM PRS话统数据");
			GetDataByTypeTask.chooseFolder(driver, Gprsperf, LteTrafficForecastPage.BtnGprsPerf, defaultWindowID);
			LOG.info_aw("GSM PRS话统数据字段匹配");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteTrafficForecastPage.ListGtchTraffic, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseGtchTraffic, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListgCellName, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChoosegCellName, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListGbtsName, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseGbtsName, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListGbscName, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseGbscName, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListgTime, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChoosegTime, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListgLACCI, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChoosegLACCI, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListgselectList, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChoosegselectList, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (Uprsperf != null && Uprsperf.length != 0) {
			LOG.info_aw("选择UMTS PRS话统数据");
			GetDataByTypeTask.chooseFolder(driver, Uprsperf, LteTrafficForecastPage.RBoxUprsPerf, defaultWindowID);
			LOG.info_aw("UMTS PRS话统数据字段匹配");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteTrafficForecastPage.ListUrncName, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseUrncName, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListUnodeBName, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseUnodeBName, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListuCellName, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseuCellName, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListuTime, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseuTime, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListuSubscribers, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseuSubscribers, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListuLACCI, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseuLACCI, true);
			CommonJQ.click(driver, LteTrafficForecastPage.ListuselectList, false);
			CommonJQ.click(driver, LteTrafficForecastPage.ListChooseuselectList, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (Lprsperf != null && Lprsperf.length != 0) {
			LOG.info_aw("选择LTE PRS话统数据");
			GetDataByTypeTask.chooseFolder(driver, Lprsperf, LteTrafficForecastPage.BtnLprsPerf, defaultWindowID);
			LOG.info_aw("LTE PRS话统数据字段匹配");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			for (int i = 0; i < 60; i++) {
				if (CommonJQ.isExisted(driver, LteTrafficForecastPage.Loading, true)) {
					BasicAction.sleep(1);
				} else {
					break;
				}
			}

			if (LevelSelect != null && LevelSelect.equalsIgnoreCase("ENODEB")) {
				LOG.info_aw("级别选择：ENODEB");
				CommonJQ.click(driver, LteTrafficForecastPage.RBtnENODEB, true, 1);
				CommonJQ.click(driver, LteTrafficForecastPage.ListenodeBName, false);
				CommonJQ.click(driver, LteTrafficForecastPage.ListChooseenodeBName, true);
				CommonJQ.click(driver, LteTrafficForecastPage.ListlTime, false);
				CommonJQ.click(driver, LteTrafficForecastPage.ListChooselTime, true);
				CommonJQ.click(driver, LteTrafficForecastPage.ListlSubscribers, false);
				CommonJQ.click(driver, LteTrafficForecastPage.ListChooselSubscribers, true);
				CommonJQ.click(driver, LteTrafficForecastPage.ListlselectList, false);
				CommonJQ.click(driver, LteTrafficForecastPage.ListChoosellselectList, true);
			} else {
				LOG.info_aw("级别：LCELL");
				CommonJQ.click(driver, LteTrafficForecastPage.ListenodeBName, false);
				CommonJQ.click(driver, LteTrafficForecastPage.ListChooseenodeBName, true);

				CommonJQ.click(driver, LteTrafficForecastPage.ListlCellName, false);
				if (CommonJQ.isExisted(driver, LteTrafficForecastPage.ListChooseCellName1, true)) {
					CommonJQ.click(driver, LteTrafficForecastPage.ListChooseCellName1, true);
				} else {
					CommonJQ.click(driver, LteTrafficForecastPage.ListChooseCellName, true);
				}
				CommonJQ.click(driver, LteTrafficForecastPage.ListlTime, false);
				if (CommonJQ.isExisted(driver, LteTrafficForecastPage.ListChooselTime, true)) {
					CommonJQ.click(driver, LteTrafficForecastPage.ListChooselTime, true);
				} else {
					CommonJQ.click(driver, LteTrafficForecastPage.ListChooselTime1, true);
				}
				if (CommonJQ.isExisted(driver, LteTrafficForecastPage.ListlLACCI, false)) {
					CommonJQ.click(driver, LteTrafficForecastPage.ListlLACCI, false);
					if (CommonJQ.isExisted(driver, LteTrafficForecastPage.ListChooselLACCI, true)) {
						CommonJQ.click(driver, LteTrafficForecastPage.ListChooselLACCI, true);
					}
				}
				CommonJQ.click(driver, LteTrafficForecastPage.ListlSubscribers, false);
				CommonJQ.click(driver, LteTrafficForecastPage.ListChooselSubscribers, true);
				CommonJQ.click(driver, LteTrafficForecastPage.ListlselectList, false);
				CommonJQ.click(driver, LteTrafficForecastPage.ListChoosellselectList, true);
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (SeperateFile != null && SeperateFile.length != 0) {
			LOG.info_aw("选择分业务流量表");
			GetDataByTypeTask.chooseFolder(driver, SeperateFile, LteTrafficForecastPage.BtnfenyewuliuliangData,
					defaultWindowID);
		}
		if (NeGroupFile != null || NeGroupFile != null) {
			LOG.info_aw("选择网元组");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteTrafficForecastPage.RBtnCellGroup, true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (NeGroupCell != null) {
				LOG.info_aw("选择网元组：CELL");
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, LteTrafficForecastPage.ListgroupType, false);
				CommonJQ.click(driver, LteTrafficForecastPage.ListChooseType, true);
				SwitchDriver.switchDriverToSEQ(driver);
			}
			LOG.info_aw("选择网元组文件");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteTrafficForecastPage.BtnNeGroupData, true);
			CommonFile.ChooseOneFile(NeGroupFile);
			CommonJQ.click(driver, LteTrafficForecastPage.BtnterminalOk, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, LteTrafficForecastPage.BtnNext, true, "点击下一步按钮失败");
		SwitchDriver.switchDriverToSEQ(driver);
	}

}
