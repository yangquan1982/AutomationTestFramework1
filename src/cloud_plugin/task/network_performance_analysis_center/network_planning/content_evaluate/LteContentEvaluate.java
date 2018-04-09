package cloud_plugin.task.network_performance_analysis_center.network_planning.content_evaluate;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_capacityevaluation.LteCapacityEvaluation;
import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_trafficforecast.LteTrafficForecastPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.LOG;
import common.util.SwitchDriver;

public class LteContentEvaluate {

	public static String creatLteContentEvaluate(WebDriver driver,
			String defaultWindowID, String taskName, String[] Lcfg,
			String[] LPara, String NeGroupFile, String ZhiShi,
			String[] MeiXian, String[] YuCeYinzi, String YuCeYinziFile,
			String[] AreaUpFactor, String SupportBusy, String BusyZhibiao,
			String BusyChooseStye, String[] SelfDefineHours, String BusyDays,
			String SupportAllJiaoYan, String[] TimesQuJian, String[] SceneSet,
			String Aera, String[] SmallSigSelect, String[] SmallAnd,
			String[] SmallText, String[] MidSigSelect, String[] MidAnd,
			String[] MidText, String[] BigSigSelect, String[] Big,
			String[] BigText, String[] OtherBigText, String[] OtherMidText,
			String[] OtherSmallText) {
		// 打开LTE话务预测
		NetworkAnalysisCenterTask.openLTECapcacityEvaluate(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 输入任务名称
		String Name = LteCapacityEvaluation.setTaskName(driver, taskName);		
		// 选择数据
		ChooseData(driver, defaultWindowID, Lcfg, LPara, NeGroupFile);
		// 参数设置
		ParameterSet(driver, defaultWindowID, ZhiShi, MeiXian, YuCeYinzi,
				YuCeYinziFile, AreaUpFactor, SupportBusy, BusyZhibiao,
				BusyChooseStye, SelfDefineHours, BusyDays, SupportAllJiaoYan,
				TimesQuJian, SceneSet, Aera, SmallSigSelect, SmallAnd,
				SmallText, MidSigSelect, MidAnd, MidText, BigSigSelect, Big,
				BigText, OtherBigText, OtherMidText, OtherSmallText);
		LteCapacityEvaluation.commitBtnClick(driver);
		return Name;
	}

	private static void ParameterSet(WebDriver driver, String defaultWindowID,
			String ZhiShi, String[] MeiXian, String[] YuCeYinzi,
			String YuCeYinziFile, String[] AreaUpFactor, String SupportBusy,
			String BusyZhibiao, String BusyChooseStye,
			String[] SelfDefineHours, String BusyDays,
			String SupportAllJiaoYan, String[] TimesQuJian, String[] SceneSet,
			String Aera, String[] SmallSigSelect, String[] SmallAnd,
			String[] SmallText, String[] MidSigSelect, String[] MidAnd,
			String[] MidText, String[] BigSigSelect, String[] Big,
			String[] BigText, String[] OtherBigText, String[] OtherMidText,
			String[] OtherSmallText) {
		// TDD参数设置
		if (ZhiShi != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteCapacityEvaluation.SigBtnTDD, true);
			SwitchDriver.switchDriverToSEQ(driver);
			TDDParameterSet(driver, defaultWindowID, SceneSet, Aera,
					SmallSigSelect, SmallAnd, SmallText, MidSigSelect, MidAnd,
					MidText, BigSigSelect, Big, BigText, OtherBigText,
					OtherMidText, OtherSmallText);
		} else {
			// FDD参数设置
			FDDParameterSet(driver, MeiXian);
		}
		// 公共参数设置
		CommonParameterSet(driver, YuCeYinzi, YuCeYinziFile, AreaUpFactor,
				SupportBusy, BusyZhibiao, BusyChooseStye, SelfDefineHours,
				BusyDays, SupportAllJiaoYan, TimesQuJian);

	}

	private static void TDDParameterSet(WebDriver driver,
			String defaultWindowID, String[] SceneSet, String Aera,
			String[] SmallSigSelect, String[] SmallAnd, String[] SmallText,
			String[] MidSigSelect, String[] MidAnd, String[] MidText,
			String[] BigSigSelect, String[] Big, String[] BigText,
			String[] OtherBigText, String[] OtherMidText,
			String[] OtherSmallText) {
		if (SceneSet != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LOG.info_aw("修改TDD每RAB流量范围");
			for (int i = 0; i < SceneSet.length; i++) {
				if (!SceneSet[i].equalsIgnoreCase("")) {
					CommonJQ.value(driver, LteCapacityEvaluation.TextScene[i],
							SceneSet[i], true);
				} else {
					continue;
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (Aera != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LOG.info_aw("地区：非中国区");
			CommonJQ.click(driver, LteCapacityEvaluation.ListselectArea, true);
			CommonJQ.click(driver, LteCapacityEvaluation.ListselectOther, true);
			SwitchDriver.switchDriverToSEQ(driver);
			if (OtherBigText != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				for (int i = 0; i < OtherBigText.length; i++) {
					if (!OtherBigText[i].equalsIgnoreCase("")) {
						LOG.info_aw("修改TDD非中国区大包业务模型第" + i + "参数值 ");
						CommonJQ.value(driver,
								LteCapacityEvaluation.Textbig[i],
								OtherBigText[i], true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (OtherMidText != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				for (int i = 0; i < OtherMidText.length; i++) {
					if (!OtherMidText[i].equalsIgnoreCase("")) {
						LOG.info_aw("修改TDD非中国区中包业务模型第" + i + "参数值 ");
						CommonJQ.value(driver,
								LteCapacityEvaluation.TextMid[i],
								OtherMidText[i], true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (OtherSmallText != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				for (int i = 0; i < OtherSmallText.length; i++) {
					if (!OtherSmallText[i].equalsIgnoreCase("")) {
						LOG.info_aw("修改TDD非中国区小包业务模型第" + i + "参数值 ");
						CommonJQ.value(driver,
								LteCapacityEvaluation.TextSmall[i],
								OtherSmallText[i], true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
		} else {
			if (SmallSigSelect != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("取消TDD中国区小包业务模型单选按钮 ");
				for (int i = 0; i < SmallSigSelect.length; i++) {
					if (!SmallSigSelect[i].equalsIgnoreCase("")) {
						LOG.info_aw("取消勾选TDD中国区小包业务模型第" + i + "个单选按钮 ");
						CommonJQ.click(driver,
								LteCapacityEvaluation.SigRrcconnuser[i], true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (SmallAnd != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("修改TDD中国区小包业务模型规则:AND");
				for (int i = 0; i < SmallAnd.length; i++) {
					if (!SmallAnd[i].equalsIgnoreCase("")) {
						CommonJQ.click(driver,
								LteCapacityEvaluation.Listcondition[i], true);
						CommonJQ.click(driver,
								LteCapacityEvaluation.Listcondition1And[i],
								true, 1);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (SmallText != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("修改TDD中国区小包业务模型规则值");
				for (int i = 0; i < SmallText.length; i++) {
					if (!SmallText[i].equalsIgnoreCase("")) {
						CommonJQ.value(driver,
								LteCapacityEvaluation.TextsmallRrcUserRule[i],
								SmallText[i], true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (MidSigSelect != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("取消TDD中国区中包业务模型单选按钮 ");
				for (int i = 0; i < MidSigSelect.length; i++) {
					if (!MidSigSelect[i].equalsIgnoreCase("")) {
						LOG.info_aw("取消勾选TDD中国区中包业务模型第" + i + "个单选按钮 ");
						CommonJQ.click(driver,
								LteCapacityEvaluation.SigpakageRrcconnuser[i],
								true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (MidAnd != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("修改TDD中国区中包业务模型规则:AND");
				for (int i = 0; i < MidAnd.length; i++) {
					if (!MidAnd[i].equalsIgnoreCase("")) {
						CommonJQ.click(driver,
								LteCapacityEvaluation.ListMidcondition[i], true);
						CommonJQ.click(driver,
								LteCapacityEvaluation.ListMidconditionAnd[i],
								true, 1);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (MidText != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("修改TDD中国区中包业务模型规则值");
				for (int i = 0; i < MidText.length; i++) {
					if (!MidText[i].equalsIgnoreCase("")) {
						CommonJQ.value(driver,
								LteCapacityEvaluation.TextpakageRrcuserRule[i],
								MidText[i], true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}

			if (BigSigSelect != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("取消TDD中国区大包业务模型单选按钮 ");
				for (int i = 0; i < BigSigSelect.length; i++) {
					if (!BigSigSelect[i].equalsIgnoreCase("")) {
						LOG.info_aw("取消勾选TDD中国区大包业务模型第" + i + "个单选按钮 ");
						CommonJQ.click(driver,
								LteCapacityEvaluation.SigbigRrcconnuser[i],
								true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (Big != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("修改TDD中国区大包业务模型规则:AND");
				for (int i = 0; i < Big.length; i++) {
					if (!Big[i].equalsIgnoreCase("")) {
						CommonJQ.click(driver,
								LteCapacityEvaluation.ListBigCondition[i], true);
						CommonJQ.click(driver,
								LteCapacityEvaluation.ListBigConditionAnd[i],
								true, 1);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (BigText != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				LOG.info_aw("修改TDD中国区大包业务模型规则值");
				for (int i = 0; i < BigText.length; i++) {
					if (!BigText[i].equalsIgnoreCase("")) {
						CommonJQ.value(driver,
								LteCapacityEvaluation.TextbigRrcUserRule[i],
								BigText[i], true);
					} else {
						continue;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
		}

	}

	private static void FDDParameterSet(WebDriver driver, String[] MeiXian) {
		if (MeiXian != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			for (int i = 0; i < MeiXian.length; i++) {
				if (!MeiXian[i].equalsIgnoreCase("")) {
					LOG.info_aw("修改FDD门限第" + i + "个参数为" + MeiXian[i]);
					CommonJQ.value(driver, LteCapacityEvaluation.FDDFlow[i],
							MeiXian[i], true);
				} else {
					continue;
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
	}

	private static void CommonParameterSet(WebDriver driver,
			String[] YuCeYinzi, String YuCeYinziFile, String[] AreaUpFactor,
			String SupportBusy, String BusyZhibiao, String BusyChooseStye,
			String[] SelfDefineHours, String BusyDays,
			String SupportAllJiaoYan, String[] TimesQuJian) {
		if (YuCeYinzi != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LOG.info_aw("修改FDD预测因子");
			for (int i = 0; i < YuCeYinzi.length; i++) {
				if (!YuCeYinzi[i].equalsIgnoreCase("")) {
					LOG.info_aw("修改FDD预测因子第" + i + "个参数为" + YuCeYinzi[i]);
					CommonJQ.value(driver,
							LteCapacityEvaluation.TextFDDScriberFactor[i],
							YuCeYinzi[i], true);
				} else {
					continue;
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (YuCeYinziFile != null) {
			LOG.info_aw("选择预测因子文件");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteCapacityEvaluation.BtnvillageFile, true);
			CommonFile.ChooseOneFile(YuCeYinziFile);	
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (AreaUpFactor != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LOG.info_aw("取消勾选小区用户增长因子或者小区下行流量增长因子");
			for (int i = 0; i < AreaUpFactor.length; i++) {
				if (!AreaUpFactor[i].equalsIgnoreCase("")) {
					LOG.info_aw("取消勾选第" + i + "个增长因子" + AreaUpFactor[i]);
					CommonJQ.click(driver,
							LteCapacityEvaluation.CBoxFDDFactor[i], true);
				} else {
					continue;
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (SupportBusy != null) {
			LOG.info_aw("是否支持独立忙时指标：否");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteCapacityEvaluation.ListDesparteBusy, false);
			CommonJQ.click(driver, LteCapacityEvaluation.ListNoBusy, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (BusyZhibiao != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			if (BusyZhibiao.equalsIgnoreCase("DLPRB")) {
				LOG.info_aw("选择忙时指标:DLPRB");
				CommonJQ.click(driver, LteCapacityEvaluation.SigDLPRB, true);
			}
			if (BusyZhibiao.equals("流量")) {
				LOG.info_aw("选择忙时指标:流量");
				CommonJQ.click(driver, LteCapacityEvaluation.SigLiuliang, true);
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (BusyChooseStye != null) {
			LOG.info_aw("选择忙时筛选方式");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteCapacityEvaluation.ListBusyChoose, true);
			CommonJQ.click(driver, LteCapacityEvaluation.ListBusyValue, false,
					Integer.valueOf(BusyChooseStye));
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (SelfDefineHours != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LOG.info_aw("选择自定义小时");
			for (int i = 0; i < SelfDefineHours.length; i++) {
				if (SelfDefineHours[i].equalsIgnoreCase("全选")) {
					LOG.info_aw("全选自定义小时");
					CommonJQ.click(driver,
							LteCapacityEvaluation.BtnSelectAllHours, true,0);
				} else {
					if (!SelfDefineHours[i].equalsIgnoreCase("")) {
						LOG.info_aw("选择选第" + SelfDefineHours[i] + "小时");
						CommonJQ.click(driver,
								LteCapacityEvaluation.BtnSelectHours, true,
								Integer.valueOf(SelfDefineHours[i]));
					}
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (BusyDays != null) {
			LOG.info_aw("忙时天数选择");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			if(CommonJQ.isExisted(driver, LteCapacityEvaluation.ListBusyDays, true))
			{
			CommonJQ.click(driver, LteCapacityEvaluation.ListBusyDays, true);
			}
			else
			{
			CommonJQ.click(driver, LteCapacityEvaluation.ListBusyDays1, true);
			}
			if(CommonJQ.isExisted(driver, LteCapacityEvaluation.ListBusyDaysValue, true))
			{
			CommonJQ.click(driver, LteCapacityEvaluation.ListBusyDaysValue,
					true, Integer.valueOf(BusyDays));
			}
			else
			{
				if(CommonJQ.isExisted(driver, LteCapacityEvaluation.ListBusyDaysValue1, true))				
				{
					CommonJQ.click(driver, LteCapacityEvaluation.ListBusyDaysValue1,
						true, Integer.valueOf(BusyDays));
				}
				else
				{
					CommonJQ.click(driver, LteCapacityEvaluation.ListBusyDaysValueother,
							true, Integer.valueOf(BusyDays));
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (SupportAllJiaoYan != null) {
			LOG.info_aw("是否支持完整性校验：是");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteCapacityEvaluation.ListTotalCheck, false);
			CommonJQ.click(driver, LteCapacityEvaluation.ListTotalCheckTrue,
					true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (TimesQuJian != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LOG.info_aw("选择完整性时间区间");
			for (int i = 0; i < TimesQuJian.length; i++) {
				if (TimesQuJian[i].equalsIgnoreCase("全选")) {
					LOG.info_aw("全选自定义小时");
					CommonJQ.click(driver,
							LteCapacityEvaluation.BtnSelectAllTimes, true,1);
				} else {
					if (!TimesQuJian[i].equalsIgnoreCase("")) {
						LOG.info_aw("选择第" + TimesQuJian[i] + "个忙时区间");
						CommonJQ.click(driver,
								LteCapacityEvaluation.BtnSelectTimes, true,
								Integer.valueOf(TimesQuJian[i]));
					}
				}
			}
			SwitchDriver.switchDriverToSEQ(driver);					
		}

	}

	private static void ChooseData(WebDriver driver, String defaultWindowID,
			String[] Lcfg, String[] LPara, String NeGroupFile) {
		if (Lcfg != null && Lcfg.length != 0) {
			LOG.info_aw("选择LTE配置数据");
			GetDataByTypeTask.chooseFolder(driver, Lcfg,
					LteCapacityEvaluation.BtnCfg, defaultWindowID);
		}
		if (LPara != null && LPara.length != 0) {
			LOG.info_aw("选择LTE工参数据");
			GetDataByTypeTask.chooseFolder(driver, LPara,
					LteCapacityEvaluation.Btnperf, defaultWindowID);
		}
		if (NeGroupFile != null) {
			LOG.info_aw("选择网元组文件");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, LteCapacityEvaluation.BtnNeGroupData, true);
			CommonFile.ChooseOneFile(NeGroupFile);
			CommonJQ.click(driver, LteTrafficForecastPage.BtnterminalOk, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, LteCapacityEvaluation.NextStep_ID, true,
				"点击下一步按钮失败");
		SwitchDriver.switchDriverToSEQ(driver);
	}

}
