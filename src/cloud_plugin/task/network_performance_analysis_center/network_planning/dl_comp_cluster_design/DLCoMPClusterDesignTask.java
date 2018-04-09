package cloud_plugin.task.network_performance_analysis_center.network_planning.dl_comp_cluster_design;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.dl_comp_cluster_design.DLCoMPClusterDesignPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskReportTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class DLCoMPClusterDesignTask {

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName_Star
	 * @param ServiceModule
	 * @param cfgFile
	 * @param paraFile
	 * @param sigFile
	 * @param pefFile
	 * @param chrFile
	 * @param FeatureFile
	 * @param cellFile
	 * @param CAType
	 * @param Band
	 * @param BandWidth
	 * @param CATerminalFlage
	 * @param CATerminal
	 * @param AverageDRateFlage
	 * @param AverageDRate
	 * @param ServiceVolumeFlage
	 * @param ServiceVolume
	 * @param DLPRBUsageFlage
	 * @param DLPRBUsage
	 * @param CoverageScenarioFlage
	 * @param CoverageScenario
	 * @param CoCover
	 * @param CoverRatio
	 * @param RsrpThreshoid
	 * @param MrThreshoid
	 * @param PlanCAType
	 * @param Azimuth
	 * @param Excursion
	 * @param NotDistance
	 * @param ForecastType
	 * @param PenetranceRatio
	 * @param ActivateRatio
	 * @param Throughput
	 * @return
	 */
	public static String creatLTEDLCoMPClusterDesignTask(WebDriver driver,
			String defaultWindowID, String taskName_Star,
			String[] ServiceModule, String[] cfgFile, String[] paraFile,
			String[] sigFile, String[] pefFile, String[] chrFile,
			String[] FeatureFile, String cellFile, String CAType,
			String[] Band, String[] BandWidth, boolean CATerminalFlage,
			String CATerminal, boolean AverageDRateFlage, String AverageDRate,
			boolean ServiceVolumeFlage, String ServiceVolume,
			boolean DLPRBUsageFlage, String DLPRBUsage,
			boolean CoverageScenarioFlage, String[] CoverageScenario,
			String CoCover, String CoverRatio, String RsrpThreshoid,
			String MrThreshoid, String PlanCAType, String Azimuth,
			String Excursion, String NotDistance, String ForecastType,
			String PenetranceRatio, String ActivateRatio, String Throughput) {

		//
		String taskName = DLCoMPClusterDesignTask.setServiceModule(driver,
				taskName_Star, ServiceModule);
		// 选择分析数据
		DLCoMPClusterDesignTask.setNetData(driver, defaultWindowID, cfgFile,
				paraFile, sigFile, pefFile, chrFile, FeatureFile, cellFile);
		//
		DLCoMPClusterDesignTask.setEvaluationPara(driver, CAType, Band,
				BandWidth);
		if(ServiceModule!=null){
			if(ServiceModule.length>1){
				DLCoMPClusterDesignTask.setPlanningFilteredBy(driver, CATerminalFlage,
						CATerminal, AverageDRateFlage, AverageDRate,
						ServiceVolumeFlage, ServiceVolume, DLPRBUsageFlage, DLPRBUsage,
						CoverageScenarioFlage, CoverageScenario);
				DLCoMPClusterDesignTask.setPlanningPara(driver, CoCover, CoverRatio,
						RsrpThreshoid, MrThreshoid, PlanCAType, Azimuth, Excursion,
						NotDistance);
				DLCoMPClusterDesignTask.setForecastPara(driver, ForecastType,
						PenetranceRatio, ActivateRatio, Throughput);	
			}
		}
		// 提交任务
		DLCoMPClusterDesignPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param chrFile
	 * @param FeatureFile
	 * @param cellFile
	 * @param CoCover
	 * @param CoverRatio
	 * @param RsrpThreshoid
	 * @param MrThreshoid
	 * @param PlanCAType
	 * @param Azimuth
	 * @param Excursion
	 * @param NotDistance
	 * @param ForecastType
	 * @param PenetranceRatio
	 * @param ActivateRatio
	 * @param Throughput
	 */
	public static void reRunLTEDLCoMPClusterDesignTask(WebDriver driver,
			String defaultWindowID, String taskName, String[] chrFile,
			String[] FeatureFile, String cellFile, String CoCover,
			String CoverRatio, String RsrpThreshoid, String MrThreshoid,
			String PlanCAType, String Azimuth, String Excursion,
			String NotDistance, String ForecastType, String PenetranceRatio,
			String ActivateRatio, String Throughput) {
		// 打开
		NetworkAnalysisCenterTask.openLTEDLCoMPClusterDesign(driver);
		TaskReportTask.asserTaskEndState(driver, taskName);
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskName);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		boolean Flage = CommonJQ.isExisted(driver,
				DLCoMPClusterDesignPage.CBoxCAPlan, true);
		if (Flage == false) {
			CommonJQ.click(driver, "span[ng-click=\"taskDetailClick()\"]",
					true, "任务详情");
		}
		CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxCAPlan, true,
				"任务类型:CA规划及预测");
		CommonJQ.click(driver, "span.taskAddProgressDot", true, 1, "数据选择");
		// 选择分析数据
		if (chrFile != null) {
			if (chrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, chrFile,
						DLCoMPClusterDesignPage.BtnChr, nowWinID);
			}
		}
		// 选择分析数据
		if (FeatureFile != null) {
			GetDataByTypeTask.chooseTimeFolder(driver, FeatureFile,
					DLCoMPClusterDesignPage.BtnFeature, nowWinID);
		}

		// 选择分析数据
		if (cellFile != null) {
			CommonJQ.click(driver, DLCoMPClusterDesignPage.BtnCell, true,
					"价值小区列表输入选择文件按钮");
			CommonFile.ChooseOneFile(cellFile);
			CommonJQ.click(driver, DLCoMPClusterDesignPage.BtnMessageOK, true);
		}

		CommonJQ.click(driver, "span.taskAddProgressDot", true, 2, "参数设置");

		DLCoMPClusterDesignTask.setPlanningPara(driver, CoCover, CoverRatio,
				RsrpThreshoid, MrThreshoid, PlanCAType, Azimuth, Excursion,
				NotDistance);
		DLCoMPClusterDesignTask.setForecastPara(driver, ForecastType,
				PenetranceRatio, ActivateRatio, Throughput);
		CommonJQ.click(driver,
				"span[ng-click=\"taskRun(ui.CAforecastValue)\"]", true,
				"任务重新运行");

		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);

		TaskReportTask.searchTask(driver, taskName);
		String gettaskStatus = CommonJQ.text(driver,
				TaskReportPage.TaskColumnText, "", 4);
		if (StringUtils.equals(gettaskStatus, "")) {
			Assert.fail("任务丢失未找到,taskName:" + taskName);
		}
		if (StringUtils.equals(gettaskStatus,
				LanguageUtil.translate("Succeeded"))) {
			Assert.fail("任务未重新执行,任务状态依然处于成功状体,taskName:" + taskName);
		}
	}

	/**
	 * 
	 * @param driver
	 * @param taskName
	 * @param ServiceModule
	 * @return
	 */
	public static String setServiceModule(WebDriver driver, String taskName,
			String[] ServiceModule) {
		// 打开
		NetworkAnalysisCenterTask.openLTEDLCoMPClusterDesign(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, true, "新建任务按钮");
		LoadingPage.Loading(driver);
		// 选中任务名称
		taskName = DLCoMPClusterDesignPage.setTaskName(driver, taskName);
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 界面选择初始化
		for (int i = 0; i < ServiceModule.length; i++) {
			if ("CA规划及预测".equalsIgnoreCase(ServiceModule[i])) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxCAPlan,
						true, "任务类型:CA规划及预测");
			} else if ("现网评估".equalsIgnoreCase(ServiceModule[i])) {

			} else {
				Assert.fail("任务类型:" + ServiceModule[i]
						+ ",参数值是错误的，请修改为合理参数，比如：CA规划及预测");
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
		DLCoMPClusterDesignPage.nextBtnClick(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param cfgFile
	 * @param paraFile
	 * @param sigFile
	 * @param pefFile
	 * @param chrFile
	 * @param FeatureFile
	 * @param cellFile
	 */
	public static void setNetData(WebDriver driver, String defaultWindowID,
			String[] cfgFile, String[] paraFile, String[] sigFile,
			String[] pefFile, String[] chrFile, String[] FeatureFile,
			String cellFile) {

		// 选择分析数据
		if (cfgFile != null) {
			if (cfgFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgFile,
						DLCoMPClusterDesignPage.BtnCfg, defaultWindowID);
			}
		}
		// 选择分析数据
		if (paraFile != null) {
			if (paraFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, paraFile,
						DLCoMPClusterDesignPage.BtnPara, defaultWindowID);
			}
		} // 选择分析数据
		if (sigFile != null) {
			if (sigFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, sigFile,
						DLCoMPClusterDesignPage.BtnSig, defaultWindowID);
			}
		}
		// 选择分析数据
		if (pefFile != null) {
			if (pefFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, pefFile,
						DLCoMPClusterDesignPage.BtnPfm, defaultWindowID);
			}
		}

		// 选择分析数据
		if (chrFile != null) {
			if (chrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, chrFile,
						DLCoMPClusterDesignPage.BtnChr, defaultWindowID);
			}
		}

		// 选择分析数据
		if (FeatureFile != null) {
			GetDataByTypeTask.chooseTimeFolder(driver, FeatureFile,
					DLCoMPClusterDesignPage.BtnFeature, defaultWindowID);
		}

		// 选择分析数据
		if (cellFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, DLCoMPClusterDesignPage.BtnCell, true,
					"价值小区列表输入选择文件按钮");
			CommonFile.ChooseOneFile(cellFile);
			CommonJQ.click(driver, DLCoMPClusterDesignPage.BtnMessageOK, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		DLCoMPClusterDesignPage.nextBtnClick(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param CAType
	 * @param Band
	 * @param BandWidth
	 * @param CATerminalFlage
	 * @param CATerminal
	 * @param AverageDRateFlage
	 * @param AverageDRate
	 * @param ServiceVolumeFlage
	 * @param ServiceVolume
	 * @param DLPRBUsageFlage
	 * @param DLPRBUsage
	 * @param CoverageScenarioFlage
	 * @param CoverageScenario
	 */
	private static void setEvaluationPara(WebDriver driver, String CAType,
			String[] Band, String[] BandWidth) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (CAType != null) {

			if ("T+T".equalsIgnoreCase(CAType)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxNetType,
						true, 0, CAType);
			} else if ("F+F".equalsIgnoreCase(CAType)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxNetType,
						true, 1, CAType);
			} else if ("F+T".equalsIgnoreCase(CAType)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxNetType,
						true, 2, CAType);
			} else if ("T+T+T".equalsIgnoreCase(CAType)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxNetType,
						true, 3, CAType);
			} else if ("F+F+F".equalsIgnoreCase(CAType)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxNetType,
						true, 4, CAType);
			} else if ("F+T+T".equalsIgnoreCase(CAType)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxNetType,
						true, 5, CAType);
			} else {
				Assert.fail("CA类型配置:" + CAType + ",参数值是错误的，请修改为合理参数，比如：T+T");
			}
			for (int i = 0; i < 8; i++) {
				CommonJQ.value(driver, "#freqBand" + (i + 1) + "Id", "", true,
						"频段设置：频段" + (i + 1));
				CommonJQ.value(driver, "#freqBandWidth" + (i + 1) + "Id", "",
						true, "频段设置 ：频段带宽" + (i + 1));
			}
			if (Band != null) {
				for (int i = 0; i < Band.length; i++) {
					CommonJQ.value(driver, "#freqBand" + (i + 1) + "Id",
							Band[i], true, "频段设置：频段" + (i + 1));
				}
			}
			if (BandWidth != null) {
				for (int i = 0; i < BandWidth.length; i++) {
					CommonJQ.value(driver, "#freqBandWidth" + (i + 1) + "Id",
							BandWidth[i], true, "频段设置 ：频段带宽" + (i + 1));
				}
			}
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param CATerminalFlage
	 * @param CATerminal
	 * @param AverageDRateFlage
	 * @param AverageDRate
	 * @param ServiceVolumeFlage
	 * @param ServiceVolume
	 * @param DLPRBUsageFlage
	 * @param DLPRBUsage
	 * @param CoverageScenarioFlage
	 * @param CoverageScenario
	 */
	private static void setPlanningFilteredBy(WebDriver driver,
			boolean CATerminalFlage, String CATerminal,
			boolean AverageDRateFlage, String AverageDRate,
			boolean ServiceVolumeFlage, String ServiceVolume,
			boolean DLPRBUsageFlage, String DLPRBUsage,
			boolean CoverageScenarioFlage, String[] CoverageScenario) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 界面选择初始化
		CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxAverageRate, true,
				"筛选开关：小区平均下载速率");
		CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxServiceVolume, true,
				"筛选开关：业务量");
		CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxDLPRBUsage, true,
				"筛选开关：下行PRB利用率");
		if (CATerminalFlage) {
			CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxCATerminal,
					true, "筛选开关：CA终端渗透率");
			if (CATerminal != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextCATerminal,
						CATerminal, true, "门限设置：CA终端渗透率(>)");
			}
		}
		if (AverageDRateFlage) {
			CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxAverageRate,
					true, "筛选开关：小区平均下载速率");
			if (AverageDRate != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextAverageRate,
						AverageDRate, true, "门限设置：小区平均下载速率(<)");
			}
		}
		if (ServiceVolumeFlage) {
			CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxServiceVolume,
					true, "筛选开关：业务量");
			if (ServiceVolume != null) {
				CommonJQ.value(driver,
						DLCoMPClusterDesignPage.TextServiceVolume,
						ServiceVolume, true, "门限设置：业务量(>)");
			}
		}
		if (DLPRBUsageFlage) {
			CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxDLPRBUsage,
					true, "筛选开关：下行PRB利用率");
			if (DLPRBUsage != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextDLPRBUsage,
						DLPRBUsage, true, "门限设置：下行PRB利用率(<)");
			}
		}
		if (CoverageScenarioFlage) {
			CommonJQ.click(driver,
					DLCoMPClusterDesignPage.CBoxCoverageScenario, true,
					"筛选开关：覆盖场景");
			if (CoverageScenario != null) {
				for (int i = 0; i < CoverageScenario.length; i++) {
					if ("全选".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxScenarioAll, true,
								"场景选择:" + CoverageScenario[i]);
						break;
					} else if ("大学校园".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxUniversity, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("火车站".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxRailwayStation,
								true, "场景选择:" + CoverageScenario[i]);
					} else if ("大型商场".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxSuperStore, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("汽车站".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxMotorStation, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("名胜古迹".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxScenicSites, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("宾馆".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxHotel, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("体育馆".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxGymnasium, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("写字楼".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxOffice, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("码头".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxWharf, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("候机室".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxBusShelter, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("饭店".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxRestaurant, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("政府".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxGovernment, true,
								"场景选择:" + CoverageScenario[i]);
					} else if ("CBD".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver, DLCoMPClusterDesignPage.CBoxCBD,
								true, "场景选择:" + CoverageScenario[i]);
					} else if ("居民小区".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxResidentialQuarter,
								true, "场景选择:" + CoverageScenario[i]);
					} else if ("公园".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxPark, true, "场景选择:"
										+ CoverageScenario[i]);
					} else if ("交通干道".equalsIgnoreCase(CoverageScenario[i])) {
						CommonJQ.click(driver,
								DLCoMPClusterDesignPage.CBoxMainChannel, true,
								"场景选择:" + CoverageScenario[i]);
					} else {
						Assert.fail("场景选择:" + CoverageScenario[i]
								+ ",参数值是错误的，请修改为合理参数，比如：大学校园");
					}

				}
			}
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param CoCover
	 * @param CoverRatio
	 * @param RsrpThreshoid
	 * @param MrThreshoid
	 * @param CAType
	 * @param Azimuth
	 * @param Excursion
	 * @param NotDistance
	 */
	private static void setPlanningPara(WebDriver driver, String CoCover,
			String CoverRatio, String RsrpThreshoid, String MrThreshoid,
			String PlanCAType, String Azimuth, String Excursion,
			String NotDistance) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (CoCover != null) {
			if ("MR计算共覆盖".equalsIgnoreCase(CoCover)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxCover, true,
						0, "共覆盖参数设置:" + CoCover);
			} else if ("仿真计算共覆盖".equalsIgnoreCase(CoCover)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxCover, true,
						1, "共覆盖参数设置:" + CoCover);
			} else {
				Assert.fail("共覆盖参数设置:" + CoCover
						+ ",参数值是错误的，请修改为合理参数，比如：MR-based calculation");
			}
			if (CoverRatio != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextCoverRatio,
						CoverRatio, true, "共覆盖参数设置:共覆盖比例(%)");
			}
			if (RsrpThreshoid != null) {
				CommonJQ.value(driver,
						DLCoMPClusterDesignPage.TextRsrpThreshoid,
						RsrpThreshoid, true, "共覆盖参数设置:有效RSRP门限(dBm)");
			}
			if (MrThreshoid != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextMrThreshoid,
						MrThreshoid, true, "共覆盖参数设置:有效MR门限");
			}
			if (PlanCAType != null) {
				if ("ACA".equalsIgnoreCase(PlanCAType)) {
					CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxACA,
							true, 0, "CA类型:" + PlanCAType);
				} else if ("CA GROUP".equalsIgnoreCase(PlanCAType)) {
					CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxACA,
							true, 1, "CA类型:" + PlanCAType);
				} else if ("FCA".equalsIgnoreCase(PlanCAType)) {
					CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxACA,
							true, 2, "CA类型:" + PlanCAType);
				} else {
					Assert.fail("CA类型:" + PlanCAType
							+ ",参数值是错误的，请修改为合理参数，比如：ACA");
				}
			}
			if (Azimuth != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextAzimuth,
						Azimuth, true, "CA组参数设置:共站方位角偏差");
			}
			if (Excursion != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextExcursion,
						Excursion, true, "CA组参数设置:共站允许偏移距离");
			}
			if (NotDistance != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextNotDistance,
						NotDistance, true, "CA组参数设置:不共站距离门限");
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param ForecastType
	 * @param PenetranceRatio
	 * @param ActivateRatio
	 * @param Throughput
	 */
	private static void setForecastPara(WebDriver driver, String ForecastType,
			String PenetranceRatio, String ActivateRatio, String Throughput) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (ForecastType != null) {
			if ("自动".equalsIgnoreCase(ForecastType)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxAuto, true,
						"CA Zone预测参数设置:" + ForecastType);
			} else if ("手动".equalsIgnoreCase(ForecastType)) {
				CommonJQ.click(driver, DLCoMPClusterDesignPage.RBoxHandMove,
						true, "CA Zone预测参数设置:" + ForecastType);
			} else {
				Assert.fail("CA Zone预测参数设置:" + ForecastType
						+ ",参数值是错误的，请修改为合理参数，比如：自动");
			}
			if ("手动".equalsIgnoreCase(ForecastType)) {
				if (PenetranceRatio != null) {
					CommonJQ.value(driver,
							DLCoMPClusterDesignPage.TextPenetranceRatio,
							PenetranceRatio, true, "CA Zone预测参数设置:CA终端渗透比例(%)");
				}
				if (ActivateRatio != null) {
					CommonJQ.value(driver,
							DLCoMPClusterDesignPage.TextActivateRatio,
							ActivateRatio, true, "CA Zone预测参数设置:CA终端激活比例(%)");
				}
			}
			if (Throughput != null) {
				CommonJQ.value(driver, DLCoMPClusterDesignPage.TextThroughput,
						Throughput, true, "CA Zone预测参数设置:吞吐量CA激活门限");
			}
		}

		SwitchDriver.switchDriverToSEQ(driver);
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
	public static void downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#showDownLoad", true);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "CA特性规划与设计报告");
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
	}

	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @param ResultHome
	 *            ：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver,
			String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			if (reportName[i].endsWith("zip")) {
				ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i],
						ResultHome);
			} else {
				FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\"
						+ reportName[i], ResultHome + "\\" + reportName[i]);
			}
		}

	}

	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @return String：报告完整名称
	 */
	public static String[] downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LoadingPage.Loading(driver, ".col6 a", "报告下载按钮");
		int reportNum = CommonJQ.length(driver,
				".taskReportTab .col2 span[class=\"ng-binding\"]", true);
		String[] reportName = new String[reportNum];
		for (int i = 0; i < reportNum; i++) {
			CommonJQ.click(driver, ".col6 a", true, i, "报告下载按钮");
			reportName[i] = CommonJQ.text(driver,
					".taskReportTab .col2 span[class=\"ng-binding\"]", "", i);
			TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,
					reportName[i]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
