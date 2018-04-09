package cloud_plugin.task.network_performance_analysis_center.basic_optimization.neighboring_cell_check_and_optimization;

import org.openqa.selenium.WebDriver;

import com.huawei.hutaf.webtest.htmlaw.BasicAction;

import cloud_plugin.page.network_performance_analysis_center.basic_optimization.neighboring_cell_check_and_optimization.NbrcheckGulpage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class NbrcheckGultask {

	public static String creatNbrCheckTask(WebDriver driver, String defaultWindowID, String taskName, String LTOLAll,
			String[] L2LCheck, String L2LNBRPCIMod30Check, String L2LNBRPCIMod30Distance, String L2LPCIMod30,
			String L2LPCIMod30Distance, String L2LNBRPCIReusedDistance, String L2LPCIReusedDistance,
			String L2LmissNeByANRMode, String L2LmissNeByANRModeFile, String L2LIsUltraCheck,
			String[] L2LIsUltraCheckParameterSet, String L2LIsNeighborHighOrLow,
			String[] L2LIsNeighborHighOrLowParameterSet, String[] L2LDistanceThreshould, String L2LIsMissingGIS,
			String[] L2LIIsMissingGISParameterSet,

	String LTOGAll, String[] L2GCheck, String L2GIsUltraCheck, String[] L2GIsUltraCheckPmrSet,
			String L2GIsNeighborHighOrLow, String[] L2GIsNeighborHighOrLowPmrSet, String L2GIsMissingGIS,
			String[] L2GIsMissingGISPmrSet, String L2GisGSM900First,

	String LTOUAll, String[] L2UCheck, String L2UIsUltraCheck, String[] L2UIsUltraCheckPmrSet,
			String L2UIsNeighborHighOrLow, String[] L2UIsNeighborHighOrLowPmrSet, String L2UIsMissingGIS,
			String[] L2UIsMissingGISPmrSet,

	String[] Lcfgfile, String[] LParafile, String[] UParafile, String[] GParafile, String[] U2000Pfmfile,
			String mobilityPolicyFile, String[] Ucfgfile, String[] Gcfgfile) {
		// 打开邻区核查与优化
		NetworkAnalysisCenterTask.openLTENbrCheckGul(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 输入任务名称
		String Name = NbrcheckGulpage.setTaskName(driver, taskName);
		// 参数设置
		ParameterSet(driver, LTOLAll, L2LCheck, L2LNBRPCIMod30Check, L2LNBRPCIMod30Distance, L2LPCIMod30,
				L2LPCIMod30Distance, L2LNBRPCIReusedDistance, L2LPCIReusedDistance, L2LmissNeByANRMode,
				L2LmissNeByANRModeFile, L2LIsUltraCheck, L2LIsUltraCheckParameterSet, L2LIsNeighborHighOrLow,
				L2LIsNeighborHighOrLowParameterSet, L2LDistanceThreshould, L2LIsMissingGIS,
				L2LIIsMissingGISParameterSet,

		LTOGAll, L2GCheck, L2GIsUltraCheck, L2GIsUltraCheckPmrSet, L2GIsNeighborHighOrLow, L2GIsNeighborHighOrLowPmrSet,
				L2GIsMissingGIS, L2GIsMissingGISPmrSet, L2GisGSM900First,

		LTOUAll, L2UCheck, L2UIsUltraCheck, L2UIsUltraCheckPmrSet, L2UIsNeighborHighOrLow, L2UIsNeighborHighOrLowPmrSet,
				L2UIsMissingGIS, L2UIsMissingGISPmrSet);

		// 数据选择
		ChooseData(driver, defaultWindowID, Lcfgfile, LParafile, UParafile, GParafile, U2000Pfmfile, mobilityPolicyFile,
				Ucfgfile, Gcfgfile);
		NbrcheckGulpage.commitBtnClick(driver);
		return Name;
	}

	private static void ParameterSet(WebDriver driver, String LTOLAll, String[] L2LCheck, String L2LNBRPCIMod30Check,
			String L2LNBRPCIMod30Distance, String L2LPCIMod30, String L2LPCIMod30Distance,
			String L2LNBRPCIReusedDistance, String L2LPCIReusedDistance, String L2LmissNeByANRMode,
			String L2LmissNeByANRModeFile, String L2LIsUltraCheck, String[] L2LIsUltraCheckParameterSet,
			String L2LIsNeighborHighOrLow, String[] L2LIsNeighborHighOrLowParameterSet, String[] L2LDistanceThreshould,
			String L2LIsMissingGIS, String[] L2LIIsMissingGISParameterSet,

	String LTOGAll, String[] L2GCheck, String L2GIsUltraCheck, String[] L2GIsUltraCheckPmrSet,
			String L2GIsNeighborHighOrLow, String[] L2GIsNeighborHighOrLowPmrSet, String L2GIsMissingGIS,
			String[] L2GIsMissingGISPmrSet, String L2GisGSM900First,

	String LTOUAll, String[] L2UCheck, String L2UIsUltraCheck, String[] L2UIsUltraCheckPmrSet,
			String L2UIsNeighborHighOrLow, String[] L2UIsNeighborHighOrLowPmrSet, String L2UIsMissingGIS,
			String[] L2UIsMissingGISPmrSet) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, NbrcheckGulpage.BtnParameterSet, true);
		// LTE到LTE核查
		LTOLCheck(driver, LTOLAll, L2LCheck, L2LNBRPCIMod30Check, L2LNBRPCIMod30Distance, L2LPCIMod30,
				L2LPCIMod30Distance, L2LNBRPCIReusedDistance, L2LPCIReusedDistance, L2LmissNeByANRMode,
				L2LmissNeByANRModeFile, L2LIsUltraCheck, L2LIsUltraCheckParameterSet, L2LIsNeighborHighOrLow,
				L2LIsNeighborHighOrLowParameterSet, L2LDistanceThreshould, L2LIsMissingGIS,
				L2LIIsMissingGISParameterSet);
		// LTE到GSM核查
		LTOGCheck(driver, LTOGAll, L2GCheck, L2GIsUltraCheck, L2GIsUltraCheckPmrSet, L2GIsNeighborHighOrLow,
				L2GIsNeighborHighOrLowPmrSet, L2GIsMissingGIS, L2GIsMissingGISPmrSet, L2GisGSM900First);
		// LTE到UMTS核查
		LTOUCheck(driver, LTOUAll, L2UCheck, L2UIsUltraCheck, L2UIsUltraCheckPmrSet, L2UIsNeighborHighOrLow,
				L2UIsNeighborHighOrLowPmrSet, L2UIsMissingGIS, L2UIsMissingGISPmrSet);
		CommonJQ.click(driver, NbrcheckGulpage.BtnokButton, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	private static void LTOLCheck(WebDriver driver, String LTOLAll, String[] L2LCheck, String L2LNBRPCIMod30Check,
			String L2LNBRPCIMod30Distance, String L2LPCIMod30, String L2LPCIMod30Distance,
			String L2LNBRPCIReusedDistance,

	String L2LPCIReusedDistance, String L2LmissNeByANRMode, String L2LmissNeByANRModeFile, String L2LIsUltraCheck,
			String[] L2LIsUltraCheckParameterSet, String L2LIsNeighborHighOrLow,
			String[] L2LIsNeighborHighOrLowParameterSet, String[] L2LDistanceThreshould, String L2LIsMissingGIS,
			String[] L2LIIsMissingGISParameterSet) {
		if (LTOLAll.equalsIgnoreCase("true")) {
			System.out.println("默认L到L勾选状态");
		} else {
			if (L2LCheck != null || L2LNBRPCIMod30Check != null || L2LNBRPCIMod30Distance != null || L2LPCIMod30 != null
					|| L2LPCIMod30Distance != null || L2LNBRPCIReusedDistance != null || L2LPCIReusedDistance != null
					|| L2LmissNeByANRMode != null || L2LmissNeByANRModeFile != null || L2LIsUltraCheck != null
					|| L2LIsUltraCheckParameterSet != null || L2LIsNeighborHighOrLow != null
					|| L2LIsNeighborHighOrLowParameterSet != null || L2LDistanceThreshould != null
					|| L2LIsMissingGIS != null || L2LIIsMissingGISParameterSet != null) {
				CommonJQ.click(driver, NbrcheckGulpage.RBtnLtoLAll, true);
				if (L2LCheck != null && L2LCheck.length != 0) {
					for (int i = 0; i < L2LCheck.length; i++) {
						if (L2LCheck[i].equals("单向邻区检查")) {
							LOG.info_aw("选择：LTOL单向邻区检查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LIsSignalCheck, true);

						} else {
							if (L2LCheck[i].equals("冗余外部小区检查")) {
								LOG.info_aw("选择：LTOL冗余外部小区检查");
								CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LIsRedundancyCheck, true);

							} else {
								if (L2LCheck[i].equals("外部小区参数一致性检查")) {
									LOG.info_aw("选择：LTOL外部小区参数一致性检查");
									CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LIsOuterSetting, true);

								} else {
									if (L2LCheck[i].equals("禁止切换的邻区核查")) {
										LOG.info_aw("选择：LTOL禁止切换的邻区核查");
										CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LforbidSwitchNeCheck, true);

									} else {
										if (L2LCheck[i].equals("无效邻区核查")) {
											LOG.info_aw("选择：LTOL无效邻区核查");
											CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LinvalidNCellCheck, true);

										} else {
											if (L2LCheck[i].equals("邻区参数核查")) {
												LOG.info_aw("选择：LTOL邻区参数核查");
												CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LNCellParamCheck, true);

											} else {
												if (L2LCheck[i].equals("不满足策略邻区关系核查")) {
													LOG.info_aw("选择：LTOL不满足策略邻区关系核查");
													CommonJQ.click(driver, NbrcheckGulpage.RBtnl2LnonPolicyCheck, true);

												} else {
													if (L2LCheck[i].equals("邻区PCI混淆核查")) {
														LOG.info_aw("选择：LTOL邻区PCI混淆核查");
														CommonJQ.click(driver, NbrcheckGulpage.RBtnNBRPCIConfusionCheck,
																true);

													}
												}
											}
										}
									}
								}
							}
						}

					}
				}
				if (L2LNBRPCIMod30Check != null || L2LNBRPCIMod30Distance != null) {
					if (L2LNBRPCIMod30Check != null) {
						LOG.info_aw("选择：LTOL邻区同频同PCI MOD 30 核查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnNBRPCIMod30Check, true);
					}
					if (L2LNBRPCIMod30Distance != null) {
						LOG.info_aw("设置复用距离");
						CommonJQ.value(driver, NbrcheckGulpage.TextNBRPCIMod30DistanceThr, L2LNBRPCIMod30Distance,
								true);
					}
				}
				if (L2LPCIMod30 != null || L2LPCIMod30Distance != null) {
					if (L2LPCIMod30 != null) {
						LOG.info_aw("选择：LTOL同频同PCI MOD 30 核查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnPCIMod30Check, true);
					}
					if (L2LPCIMod30Distance != null) {
						LOG.info_aw("设置复用距离");
						CommonJQ.value(driver, NbrcheckGulpage.TextPCIMod30DistanceThr, L2LPCIMod30Distance, true);
					}
				}
				if (L2LNBRPCIReusedDistance != null || L2LPCIReusedDistance != null) {
					if (L2LNBRPCIReusedDistance != null) {
						LOG.info_aw("选择：LTOL同频同PCI复用距离核查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnNBRPCIReusedDistanceCheck, true);
					}
					if (L2LPCIReusedDistance != null) {
						LOG.info_aw("设置复用距离");
						CommonJQ.value(driver, NbrcheckGulpage.TextPCIReusedDistanceThr, L2LPCIReusedDistance, true);
					}
				}
				if (L2LmissNeByANRMode != null || L2LmissNeByANRModeFile != null) {
					if (L2LmissNeByANRMode != null) {
						LOG.info_aw("选择：LTOL漏配邻区核查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnl2LmissNeByANRModeCheck, true);
					}
					if (L2LmissNeByANRModeFile != null) {
						LOG.info_aw("选择：邻区测量数据文件");
						CommonJQ.click(driver, NbrcheckGulpage.Btnl2LmissNeByANRModeFileName, true);
						CommonFile.ChooseOneFile(L2LmissNeByANRModeFile);
						CommonJQ.click(driver, NbrcheckGulpage.BtnterminalOk, true);
					}
				}
				if (L2LIsUltraCheck != null || L2LIsUltraCheckParameterSet != null) {
					if (L2LIsUltraCheck != null) {
						LOG.info_aw("选择：LTOL超远邻区检查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LIsUltraCheck, true);
					}
					if (L2LIsUltraCheckParameterSet != null) {
						for (int i = 0; i < L2LIsUltraCheckParameterSet.length; i++) {
							if (!L2LIsUltraCheckParameterSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改超远邻区检查第" + i + "个参数为:" + L2LIsUltraCheckParameterSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.RBtnL2LIsUltraCheckPamerSet[i],
										L2LIsUltraCheckParameterSet[i], true);
							} else {
								continue;
							}
						}
					}
				}
				if (L2LIsNeighborHighOrLow != null || L2LIsNeighborHighOrLowParameterSet != null) {
					if (L2LIsNeighborHighOrLow != null) {
						LOG.info_aw("选择：LTOL邻区过多过少检查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LIsNeighborHighOrLow, true);
					}
					if (L2LIsNeighborHighOrLowParameterSet != null) {
						for (int i = 0; i < L2LIsNeighborHighOrLowParameterSet.length; i++) {
							if (!L2LIsNeighborHighOrLowParameterSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改邻区过多过少检查第" + i + "个参数为:" + L2LIsNeighborHighOrLowParameterSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.TextL2LSameFreqHigh[i],
										L2LIsNeighborHighOrLowParameterSet[i], true);
							} else {
								continue;
							}
						}
					}
				}
				if (L2LDistanceThreshould != null) {
					CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LDistanceThreshouldChecked, true);
					BasicAction.sleep(1);
					int leng = L2LDistanceThreshould.length;
					if ((leng == 1) && (L2LDistanceThreshould[0] != "")) {
						LOG.info_aw("设置距离门限:小于");
						CommonJQ.click(driver, NbrcheckGulpage.ListL2LDistanceThresholdBiggerthan, true);
						BasicAction.sleep(1);
						CommonJQ.click(driver, NbrcheckGulpage.ListXiaoYu, true);
					}
					if (leng == 2) {
						if (L2LDistanceThreshould[0] != "") {
							LOG.info_aw("设置距离门限:小于");
							CommonJQ.click(driver, NbrcheckGulpage.ListL2LDistanceThresholdBiggerthan, true);
							BasicAction.sleep(1000000);
							CommonJQ.click(driver, NbrcheckGulpage.ListXiaoYu, true);
						}
						if (L2LDistanceThreshould[1] != "") {
							LOG.info_aw("设置距离门限");
							CommonJQ.value(driver, NbrcheckGulpage.TextL2LdistanceThreshold, L2LDistanceThreshould[1],
									true);
						}
					}

				}
				if (L2LIsMissingGIS != null || L2LIIsMissingGISParameterSet != null) {
					if (L2LIsMissingGIS != null) {
						LOG.info_aw("选择：LTOL漏配邻区检查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2LIsMissingGISCheck, true);
					}
					if (L2LIIsMissingGISParameterSet != null) {
						for (int i = 0; i < L2LIIsMissingGISParameterSet.length; i++) {
							if (!L2LIIsMissingGISParameterSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改漏配邻区检查第" + i + "个参数为:" + L2LIIsMissingGISParameterSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.TextL2LMissingGISSet[i],
										L2LIIsMissingGISParameterSet[i], true);
							} else {
								continue;
							}
						}
					}
				}
			}
		}

	}

	private static void LTOGCheck(WebDriver driver, String LTOGAll, String[] L2GCheck, String L2GIsUltraCheck,
			String[] L2GIsUltraCheckPmrSet, String L2GIsNeighborHighOrLow, String[] L2GIsNeighborHighOrLowPmrSet,
			String L2GIsMissingGIS, String[] L2GIsMissingGISPmrSet, String L2GisGSM900First) {
		if (LTOGAll != null) {
			CommonJQ.click(driver, NbrcheckGulpage.RBtnLtoGAll, true);
		} else {
			if (L2GCheck != null || L2GIsUltraCheck != null || L2GIsUltraCheckPmrSet != null
					|| L2GIsNeighborHighOrLow != null || L2GIsNeighborHighOrLowPmrSet != null || L2GIsMissingGIS != null
					|| L2GIsMissingGISPmrSet != null || L2GisGSM900First != null) {
				CommonJQ.click(driver, NbrcheckGulpage.LinkLTG, true);
				if (L2GCheck != null && L2GCheck.length != 0) {
					for (int i = 0; i < L2GCheck.length; i++) {
						if (L2GCheck[i].equals("单向邻区检查")) {
							LOG.info_aw("选择：LTOG单向邻区检查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2GIsSignalCheck, true);
						}
						if (L2GCheck[i].equals("冗余外部小区检查")) {
							LOG.info_aw("选择：LTOG冗余外部小区检查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2GIsRedundancyCheck, true);
						}
						if (L2GCheck[i].equals("外部小区参数一致性检查")) {
							LOG.info_aw("选择：LTOG外部小区参数一致性检查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2GIsOuterSetting, true);
						}
						if (L2GCheck[i].equals("禁止切换的邻区核查")) {
							LOG.info_aw("选择：LTOG禁止切换的邻区核查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2GforbidSwitchNeCheck, true);
						}
						if (L2GCheck[i].equals("邻区参数核查")) {
							LOG.info_aw("选择：LTOG邻区参数核查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2GNCellParamCheck, true);
						}
						if (L2GCheck[i].equals("不满足策略邻区关系核查")) {
							LOG.info_aw("选择：LTOG不满足策略邻区关系核查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnl2GnonPolicyCheck, true);
						}
						if (L2GCheck[i].equals("跨MSC POOL邻区和频点冲突核查")) {
							LOG.info_aw("选择：LTOG跨MSC POOL邻区和频点冲突核查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnisMscPoolCheck, true);
						}
					}
				}
				if (L2GIsUltraCheck != null || L2GIsUltraCheckPmrSet != null) {
					if (L2GIsUltraCheck != null) {
						LOG.info_aw("选择：LTOG超远邻区检查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2GIsUltraCheck, true);
					}
					if (L2GIsUltraCheckPmrSet != null) {
						for (int i = 0; i < L2GIsUltraCheckPmrSet.length; i++) {
							if (!L2GIsUltraCheckPmrSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改LTOG超远邻区检查第" + i + "个参数为:" + L2GIsUltraCheckPmrSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.RBtnL2GIsUltraCheckPmSet[i],
										L2GIsUltraCheckPmrSet[i], true);
							} else {
								continue;
							}
						}
					}

				}
				if (L2GIsNeighborHighOrLow != null || L2GIsNeighborHighOrLowPmrSet != null) {
					if (L2GIsNeighborHighOrLow != null) {
						LOG.info_aw("选择：LTOG邻区过多过少检查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2GIsNeighborHighOrLow, true);
					}
					if (L2GIsNeighborHighOrLowPmrSet != null) {
						for (int i = 0; i < L2GIsNeighborHighOrLowPmrSet.length; i++) {
							if (!L2GIsNeighborHighOrLowPmrSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改LTOG邻区过多过少检查第" + i + "个参数为:" + L2GIsNeighborHighOrLowPmrSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.RBtnL2GIsNeighborHighOrLowPmSet[i],
										L2GIsNeighborHighOrLowPmrSet[i], true);
							} else {
								continue;
							}
						}
					}

				}
				if (L2GIsMissingGIS != null || L2GIsMissingGISPmrSet != null || L2GisGSM900First != null) {
					if (L2GIsMissingGIS != null) {
						LOG.info_aw("选择：LTOG漏配邻区核查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2GIsMissingGISCheck, true);
					}
					if (L2GIsMissingGISPmrSet != null) {
						for (int i = 0; i < L2GIsMissingGISPmrSet.length; i++) {
							if (!L2GIsMissingGISPmrSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改LTOG漏配邻区核查第" + i + "个参数为:" + L2GIsMissingGISPmrSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.RBtnL2GIsMissingGISCheckPmSet[i],
										L2GIsMissingGISPmrSet[i], true);
							} else {
								continue;
							}
						}
					}
					if (L2GisGSM900First != null) {
						LOG.info_aw("选择：GSM900邻区优先");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnl2GisGSM900FirstCheck, true);
					}
				}
			}

		}
	}

	private static void LTOUCheck(WebDriver driver, String LTOUAll, String[] L2UCheck, String L2UIsUltraCheck,
			String[] L2UIsUltraCheckPmrSet, String L2UIsNeighborHighOrLow, String[] L2UIsNeighborHighOrLowPmrSet,
			String L2UIsMissingGIS, String[] L2UIsMissingGISPmrSet) {
		if (LTOUAll != null) {
			CommonJQ.click(driver, NbrcheckGulpage.RBtnLtoUAll, true);
		} else {
			if (L2UCheck != null || L2UIsUltraCheck != null || L2UIsUltraCheckPmrSet != null
					|| L2UIsNeighborHighOrLow != null || L2UIsNeighborHighOrLowPmrSet != null || L2UIsMissingGIS != null
					|| L2UIsMissingGISPmrSet != null) {
				CommonJQ.click(driver, NbrcheckGulpage.LinkLTU, true);
				if (L2UCheck != null && L2UCheck.length != 0) {
					for (int i = 0; i < L2UCheck.length; i++) {
						if (L2UCheck[i].equals("单向邻区检查")) {
							LOG.info_aw("选择：LTOU单向邻区检查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2UIsSignalCheck, true);
						}
						if (L2UCheck[i].equals("冗余外部小区检查")) {
							LOG.info_aw("选择：LTOU冗余外部小区检查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2UIsRedundancyCheck, true);
						}
						if (L2UCheck[i].equals("外部小区参数一致性检查")) {
							LOG.info_aw("选择：LTOU外部小区参数一致性检查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2UIsOuterSetting, true);
						}
						if (L2UCheck[i].equals("禁止切换的邻区核查")) {
							LOG.info_aw("选择：LTOU禁止切换的邻区核查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2UforbidSwitchNeCheck, true);
						}
						if (L2UCheck[i].equals("邻区参数核查")) {
							LOG.info_aw("选择：LTOU邻区参数核查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnL2UNCellParamCheck, true);
						}
						if (L2UCheck[i].equals("不满足策略邻区关系核查")) {
							LOG.info_aw("选择：LTOU不满足策略邻区关系核查");
							CommonJQ.click(driver, NbrcheckGulpage.RBtnl2UnonPolicyCheck, true);
						}
					}
				}
				if (L2UIsUltraCheck != null || L2UIsUltraCheckPmrSet != null) {
					if (L2UIsUltraCheck != null) {
						LOG.info_aw("选择：LTOU超远邻区检查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2UIsUltraCheck, true);
					}
					if (L2UIsUltraCheckPmrSet != null) {
						for (int i = 0; i < L2UIsUltraCheckPmrSet.length; i++) {
							if (!L2UIsUltraCheckPmrSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改LTOU超远邻区检查第" + i + "个参数为:" + L2UIsUltraCheckPmrSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.RBtnL2UIsUltraCheckPmSet[i],
										L2UIsUltraCheckPmrSet[i], true);
							} else {
								continue;
							}
						}
					}

				}
				if (L2UIsNeighborHighOrLow != null || L2UIsNeighborHighOrLowPmrSet != null) {
					if (L2UIsNeighborHighOrLow != null) {
						LOG.info_aw("选择：LTOU邻区过多过少检查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2UIsNeighborHighOrLow, true);
					}
					if (L2UIsNeighborHighOrLowPmrSet != null) {
						for (int i = 0; i < L2UIsNeighborHighOrLowPmrSet.length; i++) {
							if (!L2UIsNeighborHighOrLowPmrSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改LTOU邻区过多过少检查第" + i + "个参数为:" + L2UIsNeighborHighOrLowPmrSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.RBtnL2UIsNeighborHighOrLowPmSet[i],
										L2UIsNeighborHighOrLowPmrSet[i], true);
							} else {
								continue;
							}
						}
					}

				}
				if (L2UIsMissingGIS != null || L2UIsMissingGISPmrSet != null) {
					if (L2UIsMissingGIS != null) {
						LOG.info_aw("选择：LTOU漏配邻区核查");
						CommonJQ.click(driver, NbrcheckGulpage.RBtnL2UIsMissingGISCheck, true);
					}
					if (L2UIsMissingGISPmrSet != null) {
						for (int i = 0; i < L2UIsMissingGISPmrSet.length; i++) {
							if (!L2UIsMissingGISPmrSet[i].equalsIgnoreCase("")) {
								LOG.info_aw("修改LTOU漏配邻区核查第" + i + "个参数为:" + L2UIsMissingGISPmrSet[i]);
								CommonJQ.value(driver, NbrcheckGulpage.RBtnL2UIsMissingGISCheckPmSet[i],
										L2UIsMissingGISPmrSet[i], true);
							} else {
								continue;
							}
						}
					}
				}
			}
		}
	}

	private static void ChooseData(WebDriver driver, String defaultWindowID, String[] Lcfgfile, String[] LParafile,
			String[] UParafile, String[] GParafile, String[] U2000Pfmfile, String mobilityPolicyFile, String[] Ucfgfile,
			String[] Gcfgfile) {
		if (Lcfgfile != null && Lcfgfile.length != 0) {
			LOG.info_aw("选择LTE配置数据：" + Lcfgfile);
			GetDataByTypeTask.chooseFolder(driver, Lcfgfile, NbrcheckGulpage.BtnlteConfigParams, defaultWindowID);

		}

		if (LParafile != null && LParafile.length != 0) {
			LOG.info_aw("选择LTE工参数据：" + LParafile);
			GetDataByTypeTask.chooseFolder(driver, LParafile, NbrcheckGulpage.BtnlteEPParams, defaultWindowID);
		}

		if (UParafile != null && UParafile.length != 0) {
			LOG.info_aw("选择UMTS工参数据：" + UParafile);
			GetDataByTypeTask.chooseFolder(driver, UParafile, NbrcheckGulpage.BtnumtsParaParams, defaultWindowID);
		}

		if (GParafile != null && GParafile.length != 0) {
			LOG.info_aw("选择GSM工参数据：" + GParafile);
			GetDataByTypeTask.chooseFolder(driver, GParafile, NbrcheckGulpage.BtnGSMEPParams, defaultWindowID);
		}
		if (U2000Pfmfile != null && U2000Pfmfile.length != 0) {
			LOG.info_aw("选择U2000话统数据：" + U2000Pfmfile);
			GetDataByTypeTask.chooseFolder(driver, U2000Pfmfile, NbrcheckGulpage.BtnPFMParams, defaultWindowID);
		}
		if (mobilityPolicyFile != null) {
			LOG.info_aw("移动性策略文件");
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, NbrcheckGulpage.BtnmobilityPolicyFileName, true);
			SwitchDriver.switchDriverToSEQ(driver);
			CommonFile.ChooseOneFile(mobilityPolicyFile);
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, NbrcheckGulpage.BtnterminalOk, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (Ucfgfile != null && Ucfgfile.length != 0) {
			LOG.info_aw("选择UMTS配置数据：" + Ucfgfile);
			GetDataByTypeTask.chooseFolder(driver, Ucfgfile, NbrcheckGulpage.BtnumtsConfigParams, defaultWindowID);
		}

		if (Gcfgfile != null && Gcfgfile.length != 0) {
			LOG.info_aw("选择GSM配置数据：" + Ucfgfile);
			GetDataByTypeTask.chooseFolder(driver, Gcfgfile, NbrcheckGulpage.BtngsmConfigParams, defaultWindowID);
		}

	}

	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname：任务名
	 * @param ResultHome：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver, String defaultWindowID, String taskname,
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
		CommonJQ.click(driver, "#showDownLoad", true, "报告下载按钮");
		String reportName = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", true);
		reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
