package cloud_plugin.task.network_performance_analysis_center.network_planning.volte_coverage_evaluation;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.volte_coverage_evaluation.VolteCoverageEvaluationPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskReportTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class VolteCoverageEvaluationTask {

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param RAT
	 * @param TaskType
	 * @param evaluateReport
	 * @param LTEsigFile
	 * @param LTEPPFile
	 * @param PPFile
	 * @param chrFile
	 * @param cfgFile
	 * @param mrFile
	 * @param LTEpropertyFile
	 * @param propertyFile
	 * @param LTEPrfFile
	 * @param GSMPrfFile
	 * @param mapFile
	 * @param polygonFile
	 * @param PositType
	 * @param RasterValue
	 * @param Raster_mr
	 * @param Raster_cell_mr
	 * @param Min_interfrence
	 * @param Min_level
	 * @param LTECoverage
	 * @param BusinessGrid
	 * @param LTEMRCollect
	 * @param MrReport
	 * @param IndoorRate
	 * @param ComplexRate
	 * @param IndoorLevel
	 * @param ComplexLevel
	 * @param RSRP
	 * @param AllFlage
	 * @param RateFlage
	 * @param RedirectionFlage
	 * @param CQIFlage
	 * @param UserCountFlage
	 * @param PerfFlowFlage
	 * @param SrvccFlage
	 * @return
	 */
	public static String creatGSMVolteCoverageEvaluationTask(WebDriver driver,
			String defaultWindowID, String taskName, String RAT,
			String[] TaskType, String evaluateReport, String[] LTEsigFile,
			String[] LTEPPFile, String[] PPFile, String[] chrFile,
			String[] cfgFile, String[] mrFile, String[] LTEpropertyFile,
			String[] propertyFile, String LTEPrfFile, String GSMPrfFile,
			String[] mapFile, String[] polygonFile, String PositType,
			String RasterValue, String Raster_mr, String Raster_cell_mr,
			String Min_interfrence, String Min_level, String LTECoverage,
			String BusinessGrid, String LTEMRCollect, String MrReport,
			String IndoorRate, String ComplexRate, String IndoorLevel,
			String ComplexLevel, String[] RSRP, boolean AllFlage,
			boolean RateFlage, boolean RedirectionFlage, boolean CQIFlage,
			boolean UserCountFlage, boolean PerfFlowFlage, boolean SrvccFlage) {
		//
		taskName = VolteCoverageEvaluationTask.setServiceModule(driver,
				taskName, RAT, "LTE->GSM", "HW", TaskType,evaluateReport);
		// 设置网元数据
		if (TaskType != null) {
			if (TaskType.length == 1
					&& "VoLTE驻留率分析".equalsIgnoreCase(TaskType[0])) {
				// 设置网元数据
				VolteCoverageEvaluationTask.setGSMNetData(driver,
						defaultWindowID, null, null, null, null, null, null,
						LTEpropertyFile, propertyFile, LTEPrfFile, GSMPrfFile,
						mapFile, polygonFile);
			} else {
				VolteCoverageEvaluationTask.setGSMNetData(driver,
						defaultWindowID, LTEsigFile, LTEPPFile, PPFile,
						chrFile, cfgFile, mrFile, LTEpropertyFile,
						propertyFile, LTEPrfFile, GSMPrfFile, mapFile,
						polygonFile);
				VolteCoverageEvaluationPage.nextBtnClick(driver);
				// 定位方式
				VolteCoverageEvaluationTask.setPosit(driver, PositType,
						RasterValue);
				// 基础MR
				VolteCoverageEvaluationTask
						.setBaseMR(driver, Raster_mr, Raster_cell_mr,
								Min_interfrence, Min_level, LTECoverage,
								BusinessGrid, LTEMRCollect, MrReport, null);
				// 弱覆盖MR
				VolteCoverageEvaluationTask.setCoverMR(driver, IndoorRate,
						ComplexRate, IndoorLevel, ComplexLevel);
				// 电平区间
				VolteCoverageEvaluationTask.setRSRP(driver, RSRP);
				// 扩展
				VolteCoverageEvaluationTask.setExDimension(driver, AllFlage,
						RateFlage, RedirectionFlage, CQIFlage, UserCountFlage,
						PerfFlowFlage, SrvccFlage);

			}
		}
		// 提交任务
		VolteCoverageEvaluationPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param RAT
	 * @param FallbackPolicy
	 * @param TaskType
	 * @param evaluateReport
	 * @param LTEsigFile
	 * @param LTEPPFile
	 * @param LTEpropertyFile
	 * @param LTEPrfFile
	 * @param PrfFile
	 * @param TrafficFile
	 * @param PredictionDataFile
	 * @param mapFile
	 * @param polygonFile
	 * @param PositType
	 * @param RasterValue
	 * @param Raster_mr
	 * @param Raster_cell_mr
	 * @param Min_interfrence
	 * @param Min_level
	 * @param LTECoverage
	 * @param BusinessGrid
	 * @param LTEMRCollect
	 * @param MrReport
	 * @param IndoorRate
	 * @param ComplexRate
	 * @param IndoorLevel
	 * @param ComplexLevel
	 * @param RSRP
	 * @param AllFlage
	 * @param RateFlage
	 * @param RedirectionFlage
	 * @param CQIFlage
	 * @param UserCountFlage
	 * @param PerfFlowFlage
	 * @param SrvccFlage
	 * @return
	 */
	public static String creatMVVolteCoverageEvaluationTask(WebDriver driver,
			String defaultWindowID, String taskName, String RAT,
			String FallbackPolicy, String[] TaskType, String evaluateReport,
			String[] LTEsigFile, String[] LTEPPFile, String[] LTEpropertyFile,
			String LTEPrfFile, String PrfFile, String TrafficFile,
			String PredictionDataFile, String[] mapFile, String[] polygonFile,
			String PositType, String RasterValue, String Raster_mr,
			String Raster_cell_mr, String Min_interfrence, String Min_level,
			String LTECoverage, String BusinessGrid, String LTEMRCollect,
			String MrReport, String IndoorRate, String ComplexRate,
			String IndoorLevel, String ComplexLevel, String[] RSRP,
			boolean AllFlage, boolean RateFlage, boolean RedirectionFlage,
			boolean CQIFlage, boolean UserCountFlage, boolean PerfFlowFlage,
			boolean SrvccFlage) {
		//
		taskName = VolteCoverageEvaluationTask.setServiceModule(driver,
				taskName, RAT, FallbackPolicy, "MV", TaskType,evaluateReport);
		// 设置网元数据
		if (TaskType != null) {
			if (TaskType.length == 1
					&& "VoLTE驻留率分析".equalsIgnoreCase(TaskType[0])) {
				// 设置网元数据
				VolteCoverageEvaluationTask.setMVNetData(driver,
						defaultWindowID, null, null,
						LTEpropertyFile, LTEPrfFile, PrfFile, null,
						null, null, polygonFile);
			} else {
				VolteCoverageEvaluationTask.setMVNetData(driver,
						defaultWindowID, LTEsigFile, LTEPPFile,
						LTEpropertyFile, LTEPrfFile, PrfFile, TrafficFile,
						PredictionDataFile, mapFile, polygonFile);
				VolteCoverageEvaluationPage.nextBtnClick(driver);
				// 定位方式
				VolteCoverageEvaluationTask.setPosit(driver, PositType,
						RasterValue);
				// 基础MR
				VolteCoverageEvaluationTask
						.setBaseMR(driver, Raster_mr, Raster_cell_mr,
								Min_interfrence, Min_level, LTECoverage,
								BusinessGrid, LTEMRCollect, MrReport, null);
				// 弱覆盖MR
				VolteCoverageEvaluationTask.setCoverMR(driver, IndoorRate,
						ComplexRate, IndoorLevel, ComplexLevel);
				// 电平区间
				VolteCoverageEvaluationTask.setRSRP(driver, RSRP);
				// 扩展
				VolteCoverageEvaluationTask.setExDimension(driver, AllFlage,
						RateFlage, RedirectionFlage, CQIFlage, UserCountFlage,
						PerfFlowFlage, SrvccFlage);

			}
		}
		// 提交任务
		VolteCoverageEvaluationPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param RAT
	 * @param LTEPPFile
	 * @param LTEpefFile
	 * @param LTEsigFile
	 * @param cfgFile
	 * @param PPFile
	 * @param pefFile
	 * @param chrFile
	 * @param mrFile
	 * @param propertyFile
	 * @param mapFile
	 * @param PositType
	 * @param RasterValue
	 * @param Raster_mr
	 * @param Raster_cell_mr
	 * @param Min_interfrence
	 * @param Min_level
	 * @param RSRPCoverage
	 * @param LTECoverage
	 * @param BusinessGrid
	 * @param LTEMRCollect
	 * @param MrReport
	 * @param UMTSMRDatePapaer
	 * @param IndoorRate
	 * @param ComplexRate
	 * @param IndoorLevel
	 * @param ComplexLevel
	 * @param AllFlage
	 * @param RateFlage
	 * @param RedirectionFlage
	 * @param CQIFlage
	 * @param UserCountFlage
	 * @param PerfFlowFlage
	 * @param SrvccFlage
	 * @param PrfReportFile
	 * @param ClearMrFlage
	 * @return
	 */
	public static String creatUMTSVolteCoverageEvaluationTask(WebDriver driver,
			String defaultWindowID, String taskName, String RAT,
			String[] TaskType, String evaluateRepor, String[] LTEPPFile,
			String LTEpefFile, String[] LTEsigFile, String[] cfgFile,
			String[] PPFile, String pefFile, String[] chrFile, String[] mrFile,
			String[] propertyFile, String[] mapFile, String[] polygonFile,
			String PositType, String RasterValue, String Raster_mr,
			String Raster_cell_mr, String Min_interfrence, String Min_level,
			String LTECoverage, String BusinessGrid, String LTEMRCollect,
			String MrReport, String UMTSMRDatePapaer, String IndoorRate,
			String ComplexRate, String IndoorLevel, String ComplexLevel,
			String[] RSRP, boolean AllFlage, boolean RateFlage,
			boolean RedirectionFlage, boolean CQIFlage, boolean UserCountFlage,
			boolean PerfFlowFlage, boolean SrvccFlage) {
		//
		taskName = VolteCoverageEvaluationTask.setServiceModule(driver,
				taskName, RAT, "LTE->UMTS", "HW", TaskType,evaluateRepor);
		if (TaskType != null) {
			if (TaskType.length == 1
					&& "VoLTE驻留率分析".equalsIgnoreCase(TaskType[0])) {
				// 设置网元数据
				VolteCoverageEvaluationTask
						.setNetData(driver, defaultWindowID, LTEpefFile,
								pefFile, propertyFile, mapFile, polygonFile);
			} else {
				// 设置网元数据
				VolteCoverageEvaluationTask.setNetData(driver, defaultWindowID,
						LTEPPFile, LTEpefFile, LTEsigFile, cfgFile, PPFile,
						pefFile, chrFile, mrFile, propertyFile, mapFile,
						polygonFile);
				// 定位方式
				VolteCoverageEvaluationTask.setPosit(driver, PositType,
						RasterValue);
				// 基础MR
				VolteCoverageEvaluationTask.setBaseMR(driver, Raster_mr,
						Raster_cell_mr, Min_interfrence, Min_level,
						LTECoverage, BusinessGrid, LTEMRCollect, MrReport,
						UMTSMRDatePapaer);
				// 弱覆盖MR
				VolteCoverageEvaluationTask.setCoverMR(driver, IndoorRate,
						ComplexRate, IndoorLevel, ComplexLevel);
				// 电平区间
				VolteCoverageEvaluationTask.setRSRP(driver, RSRP);
				// 扩展
				VolteCoverageEvaluationTask.setExDimension(driver, AllFlage,
						RateFlage, RedirectionFlage, CQIFlage, UserCountFlage,
						PerfFlowFlage, SrvccFlage);
				// 提交任务
				VolteCoverageEvaluationPage.commitBtnClick(driver);
			}
		}

		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param RAT
	 * @param LTEPPFile
	 * @param LTEpefFile
	 * @param LTEsigFile
	 * @param cfgFile
	 * @param PPFile
	 * @param pefFile
	 * @param chrFile
	 * @param mrFile
	 * @param propertyFile
	 * @param mapFile
	 * @param PositType
	 * @param RasterValue
	 * @param Raster_mr
	 * @param Raster_cell_mr
	 * @param Min_interfrence
	 * @param Min_level
	 * @param RSRPCoverage
	 * @param LTECoverage
	 * @param BusinessGrid
	 * @param LTEMRCollect
	 * @param MrReport
	 * @param UMTSMRDatePapaer
	 * @param IndoorRate
	 * @param ComplexRate
	 * @param IndoorLevel
	 * @param ComplexLevel
	 * @param AllFlage
	 * @param RateFlage
	 * @param RedirectionFlage
	 * @param CQIFlage
	 * @param UserCountFlage
	 * @param PerfFlowFlage
	 * @param SrvccFlage
	 * @param PrfReportFile
	 * @param ClearMrFlage
	 * @return
	 */
	public static String creatCDMAVolteCoverageEvaluationTask(WebDriver driver,
			String defaultWindowID, String taskName, String RAT,
			String[] TaskType, String[] LTEPPFile, String LTEpefFile,
			String[] LTEsigFile, String[] cfgFile, String[] PPFile,
			String pefFile, String[] chrFile, String[] mrFile,
			String[] propertyFile, String[] mapFile, String[] polygonFile,
			String PositType, String RasterValue, String Raster_mr,
			String Raster_cell_mr, String Min_interfrence, String Min_level,
			String RSRPCoverage, String LTECoverage, String BusinessGrid,
			String LTEMRCollect, String MrReport,
			String IndoorRate, String ComplexRate, String IndoorLevel,
			String ComplexLevel, boolean AllFlage, boolean RateFlage,
			boolean RedirectionFlage, boolean CQIFlage, boolean UserCountFlage,
			boolean PerfFlowFlage, boolean SrvccFlage) {
		//
		taskName = VolteCoverageEvaluationTask.setServiceModule(driver,
				taskName, RAT, "LTE->CDMA", "HW", TaskType,"");
		// 设置网元数据
		VolteCoverageEvaluationTask.setNetData(driver, defaultWindowID,
				LTEPPFile, LTEpefFile, LTEsigFile, cfgFile, PPFile, pefFile,
				chrFile, mrFile, propertyFile, mapFile, polygonFile);
		// 定位方式
		VolteCoverageEvaluationTask.setPosit(driver, PositType, RasterValue);
		// 基础MR
		VolteCoverageEvaluationTask.setBaseMR(driver, Raster_mr,
				Raster_cell_mr, Min_interfrence, Min_level, LTECoverage,
				BusinessGrid, LTEMRCollect, MrReport, null);
		// 弱覆盖MR
		VolteCoverageEvaluationTask.setCoverMR(driver, IndoorRate, ComplexRate,
				IndoorLevel, ComplexLevel);
		// 扩展
		VolteCoverageEvaluationTask.setExDimension(driver, AllFlage, RateFlage,
				RedirectionFlage, CQIFlage, UserCountFlage, PerfFlowFlage,
				SrvccFlage);
		// 提交任务
		VolteCoverageEvaluationPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param taskName
	 * @param RAT
	 * @param FallbackPolicy
	 * @param TaskType
	 * @return
	 */
	public static String setServiceModule(WebDriver driver, String taskName,
			String RAT, String FallbackPolicy, String Factory, String[] TaskType, String evaluateReport) {
		// 打开
		NetworkAnalysisCenterTask.openVolteCoverageEvaluation(driver);
		TaskReportPage.CreateTaskClick(driver);		
		LoadingPage.Loading(driver);
		// 选中任务名称
		taskName = VolteCoverageEvaluationPage.setTaskName(driver, taskName);
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("TDD".equalsIgnoreCase(RAT)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxTDD, true,"制式:TDD");
		} else {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxFDD, true,"制式:FDD");
		}
		if ("LTE->GSM".equalsIgnoreCase(FallbackPolicy)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxGSM, true,"回落模式 :LTE->GSM");
		} else if ("LTE->UMTS".equalsIgnoreCase(FallbackPolicy)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxUMTS, true,"回落模式 :LTE->UMTS");
		} else {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxCDMA, true,"回落模式 :LTE->CDMA");
		}
		if ("MV".equalsIgnoreCase(Factory)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxMV, true,"2G/3G 厂商 :HUAWEI");
		} else {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxHW, true,"2G/3G 厂商 :MV");
		}
		CommonJQ.click(driver, VolteCoverageEvaluationPage.CBoxFirst, true);
		for (int i = 0; i < TaskType.length; i++) {
			if ("VoLTE业务覆盖率分析".equalsIgnoreCase(TaskType[i])) {
				CommonJQ.click(driver, VolteCoverageEvaluationPage.CBoxFirst,
						true,"任务类型:VoLTE业务覆盖率分析");
			} else {
				CommonJQ.click(driver, VolteCoverageEvaluationPage.CBoxSecond,
						true,"任务类型:VoLTE驻留率预测");
			}
		}

		if (TaskType != null) {
			if (TaskType.length == 1) {
				if(evaluateReport!=null){
					CommonJQ.click(driver,
							"span[ng-click=\"showEvaluateReport()\"]", true);
					CommonJQ.click_containsByText(driver, "ul#evaluateReportUl li", evaluateReport, true, evaluateReport);
				}

			}
		}

		SwitchDriver.switchDriverToSEQ(driver);

		VolteCoverageEvaluationPage.nextBtnClick(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param LTEcfgFile
	 * @param LTEPPFile
	 * @param LTEpefFile
	 * @param LTEsigFile
	 * @param cfgFile
	 * @param PPFile
	 * @param pefFile
	 * @param chrFile
	 * @param mrFile
	 */

	public static void setNetData(WebDriver driver, String defaultWindowID,
			String[] LTEPPFile, String LTEpefFile, String[] LTEsigFile,
			String[] cfgFile, String[] PPFile, String pefFile,
			String[] chrFile, String[] mrFile, String[] propertyFile,
			String[] mapFile, String[] polygonFile) {
		// 选择分析数据
		if (LTEPPFile != null) {
			if (LTEPPFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								LTEPPFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.engineerParaNum\"]').siblings('span[class=\"btnGrayFileSelect ng-scope\"]').click()",
								defaultWindowID);
			}
		}
		if (LTEsigFile != null) {
			if (LTEsigFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								LTEsigFile,
								"$('iframe[name=main]').contents().find('#select_MrData').eq(0).click()",
								defaultWindowID);
			}
		}

		if (LTEpefFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#ltePerfData", true, "LTE 话统");
			CommonFile.ChooseOneFile(LTEpefFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true, "上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (PPFile != null) {
			if (PPFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								PPFile,
								"$('iframe[name=main]').contents().find('#select_gsmConfData').eq(0).click()",
								defaultWindowID);
			}
		}
		if (cfgFile != null) {
			if (cfgFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								cfgFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.gucConfNum\"]').siblings('span[class=\"btnGrayFileSelect ng-scope\"]').click()",
								defaultWindowID);
			}
		}

		if (pefFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#gucPerfData", true);
			CommonFile.ChooseOneFile(pefFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true);
			SwitchDriver.switchDriverToSEQ(driver);
		}

		if (chrFile != null) {
			if (chrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, chrFile,
						VolteCoverageEvaluationPage.BtnCHR, defaultWindowID);
			}
		}

		if (mrFile != null) {
			if (mrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, mrFile,
						VolteCoverageEvaluationPage.BtnMR, defaultWindowID);
			}
		}

		if (propertyFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							propertyFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.propertyData\"]').siblings('span[id=\"select_propertyData\"]').click()",
							defaultWindowID);

		}

		if (mapFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							mapFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.mapData\"]').siblings('span[id=\"select_mapData\"]').click()",
							defaultWindowID);

		}
		if (polygonFile != null) {
			if (polygonFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								polygonFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.pologyFilePath\"]').siblings('span[id=\"select_mapData\"]').click()",
								defaultWindowID);
			}
		}
		VolteCoverageEvaluationPage.nextBtnClick(driver);

	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param LTEsigFile
	 * @param LTEPPFile
	 * @param PPFile
	 * @param chrFile
	 * @param cfgFile
	 * @param mrFile
	 * @param LTEpropertyFile
	 * @param propertyFile
	 * @param LTEPrfFile
	 * @param GSMPrfFile
	 * @param mapFile
	 * @param polygonFile
	 */
	public static void setGSMNetData(WebDriver driver, String defaultWindowID,
			String[] LTEsigFile, String[] LTEPPFile, String[] PPFile,
			String[] chrFile, String[] cfgFile, String[] mrFile,
			String[] LTEpropertyFile, String[] propertyFile, String LTEPrfFile,
			String GSMPrfFile, String[] mapFile, String[] polygonFile) {
		// 选择分析数据
		if (LTEsigFile != null) {
			if (LTEsigFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								LTEsigFile,
								"$('iframe[name=main]').contents().find('#select_MrData').eq(0).click()",
								defaultWindowID);
			}
		}
		if (LTEPPFile != null) {
			if (LTEPPFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								LTEPPFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.engineerParaNum\"]').siblings('span[class=\"btnGrayFileSelect ng-scope\"]').click()",
								defaultWindowID);
			}
		}
		if (PPFile != null) {
			if (PPFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								PPFile,
								"$('iframe[name=main]').contents().find('#select_gsmConfData').eq(0).click()",
								defaultWindowID);
			}
		}
		if (chrFile != null) {
			if (chrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, chrFile,
						VolteCoverageEvaluationPage.BtnCHR, defaultWindowID);
			}
		}
		if (cfgFile != null) {
			if (cfgFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								cfgFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.gucConfNum\"]').siblings('span[class=\"btnGrayFileSelect ng-scope\"]').click()",
								defaultWindowID);
			}
		}
		if (mrFile != null) {
			if (mrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, mrFile,
						VolteCoverageEvaluationPage.BtnMR, defaultWindowID);
			}
		}
		if (LTEpropertyFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							LTEpropertyFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.propertyData\"]').siblings('span[id=\"select_propertyData\"]').click()",
							defaultWindowID);

		}
		if (propertyFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							propertyFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.propertyData\"]').siblings('span[id=\"select_propertyData\"]').click()",
							defaultWindowID);

		}
		if (LTEPrfFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#ltePerfData", true, "LTE 话统");
			CommonFile.ChooseOneFile(LTEPrfFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true, "上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (GSMPrfFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#gucPerfData", true);
			CommonFile.ChooseOneFile(GSMPrfFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true);
			SwitchDriver.switchDriverToSEQ(driver);
		}

		if (mapFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							mapFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.mapData\"]').siblings('span[id=\"select_mapData\"]').click()",
							defaultWindowID);

		}
		if (polygonFile != null) {
			if (polygonFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								polygonFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.pologyFilePath\"]').siblings('span[id=\"select_mapData\"]').click()",
								defaultWindowID);
			}
		}

	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param LTEsigFile
	 * @param LTEPPFile
	 * @param LTEpropertyFile
	 * @param LTEPrfFile
	 * @param PrfFile
	 * @param TrafficFile
	 * @param PredictionDataFile
	 * @param mapFile
	 * @param polygonFile
	 */
	public static void setMVNetData(WebDriver driver, String defaultWindowID,
			String[] LTEsigFile, String[] LTEPPFile, String[] LTEpropertyFile,
			String LTEPrfFile, String PrfFile, String TrafficFile,
			String PredictionDataFile, String[] mapFile, String[] polygonFile) {
		// 选择分析数据
		if (LTEsigFile != null) {
			if (LTEsigFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								LTEsigFile,
								"$('iframe[name=main]').contents().find('#select_MrData').eq(0).click()",
								defaultWindowID);
			}
		}
		if (LTEPPFile != null) {
			if (LTEPPFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								LTEPPFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.engineerParaNum\"]').siblings('span[class=\"btnGrayFileSelect ng-scope\"]').click()",
								defaultWindowID);
			}
		}

		if (LTEpropertyFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							LTEpropertyFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.propertyData\"]').siblings('span[id=\"select_propertyData\"]').click()",
							defaultWindowID);

		}

		if (LTEPrfFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#ltePerfData", true, "LTE 话统");
			CommonFile.ChooseOneFile(LTEPrfFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true, "LTE 话统->上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (PrfFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#gucPerfData", true, "话统(MV)");
			CommonFile.ChooseOneFile(PrfFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true, "话统(MV)->上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (TrafficFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#coverageSimulationData", true, "话务地图");
			CommonFile.ChooseOneFile(TrafficFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true, "话务地图->上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (PredictionDataFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#bestServerCellData", true, "栅格主服务小区信息");
			CommonFile.ChooseOneFile(PredictionDataFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true, "栅格主服务小区信息->上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (mapFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							mapFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.mapData\"]').siblings('span[id=\"select_mapData\"]').click()",
							defaultWindowID);

		}
		if (polygonFile != null) {
			if (polygonFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								polygonFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.pologyFilePath\"]').siblings('span[id=\"select_mapData\"]').click()",
								defaultWindowID);
			}
		}
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param LTEpefFile
	 * @param pefFile
	 * @param propertyFile
	 * @param mapFile
	 * @param polygonFile
	 */
	public static void setNetData(WebDriver driver, String defaultWindowID,
			String LTEpefFile, String pefFile, String[] propertyFile,
			String[] mapFile, String[] polygonFile) {

		if (LTEpefFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#ltePerfData", true, "LTE 话统");
			CommonFile.ChooseOneFile(LTEpefFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true, "LTE 话统->上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (pefFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#gucPerfData", true,"话统");
			CommonFile.ChooseOneFile(pefFile);
			CommonJQ.click(driver, VolteCoverageEvaluationPage.BtnMessageOK,
					true, "话统->上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
		}
		if (propertyFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							propertyFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.propertyData\"]').siblings('span[id=\"select_propertyData\"]').click()",
							defaultWindowID);
		}
		if (mapFile != null) {

			GetDataByTypeTask
					.chooseTimeFolder(
							driver,
							mapFile,
							"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.mapData\"]').siblings('span[id=\"select_mapData\"]').click()",
							defaultWindowID);

		}
		if (polygonFile != null) {
			if (polygonFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								polygonFile,
								"$('iframe[name=main]').contents().find('input[name=\"lteTaskBean.pologyFilePath\"]').siblings('span[id=\"select_mapData\"]').click()",
								defaultWindowID);
			}
		}
		VolteCoverageEvaluationPage.commitBtnClick(driver);

	}

	/**
	 * 
	 * @param driver
	 * @param PositType
	 * @param RasterValue
	 */
	private static void setPosit(WebDriver driver, String PositType,
			String RasterValue) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, VolteCoverageEvaluationPage.PosiObjLi_PATH,
				true, "定位方式->下拉框");
		if ("快速定位".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.FastPosit, true,"定位方式:快速定位");
		} else if ("特征库定位".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.FeaturePosit,
					true,"定位方式:特征库定位");
		} else if ("综合定位".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.ComplexPosit,
					true,"定位方式:综合定位");
		} else {
			Assert.fail("定位方式选择错误:" + PositType + ",请输入：特征库定位、快速定位或者综合定位。");
		}
		CommonJQ.click(driver,
				VolteCoverageEvaluationPage.RasterObjLi_PATH, true,"栅格精度->下拉框");
		if ("20".equalsIgnoreCase(RasterValue)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.Raster20, true,"栅格精度->20");
		} else if ("50".equalsIgnoreCase(RasterValue)) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.Raster50, true,"栅格精度->50");
		} else {
			Assert.fail("栅格精度选择错误:" + RasterValue + ",请输入：20或者50。");
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param Raster_mr
	 * @param Raster_cell_mr
	 * @param Min_interfrence
	 * @param Min_level
	 * @param RSRPCoverage
	 * @param LTECoverage
	 * @param BusinessGrid
	 * @param LTEMRCollect
	 * @param MrReport
	 */

	private static void setBaseMR(WebDriver driver, String Raster_mr,
			String Raster_cell_mr, String Min_interfrence, String Min_level,
			String LTECoverage, String BusinessGrid, String LTEMRCollect,
			String MrReport, String UMTSMRDatePapaer) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (Raster_mr != null) {
			CommonJQ.value(driver, VolteCoverageEvaluationPage.TextRaster_mr,
					Raster_mr, true,"栅格级MR测量次数门限");
		}
		if (Raster_cell_mr != null) {
			CommonJQ.value(driver,
					VolteCoverageEvaluationPage.TextRaster_cell_mr,
					Raster_cell_mr, true,"栅格小区级MR测量次数门限");
		}
		if (Min_interfrence != null) {
			CommonJQ.value(driver,
					VolteCoverageEvaluationPage.TextMin_interfrence,
					Min_interfrence, true,"最小干扰过滤门限(dB)");
		}

		CommonJQ.value(driver, VolteCoverageEvaluationPage.TextMin_level,
				Min_level, true,"最小电平过滤门限(dBm)");

		CommonJQ.value(driver, VolteCoverageEvaluationPage.TextLTECoverage,
				LTECoverage, true,"LTE盲覆盖栅格MR数量门限");
		boolean Flage = CommonJQ.isExisted(driver, VolteCoverageEvaluationPage.TextBusinessGrid, true);
		if(Flage){
			CommonJQ.value(driver, VolteCoverageEvaluationPage.TextBusinessGrid,
					BusinessGrid, true,"2G/3G业务栅格MR数量门限");	
		}else{
			CommonJQ.value(driver, VolteCoverageEvaluationPage.TextTrafficGrid,
					BusinessGrid, true,"2G/3G栅格话务量门限(Erl)");	
		}
		CommonJQ.value(driver, VolteCoverageEvaluationPage.TextLTEMRCollect,
				LTEMRCollect, true,"LTE MR 采集抽样率(%)");
		if (MrReport != null) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.TextMrReport,
					true,"LTE MR上报周期(s)");
			if ("5".equalsIgnoreCase(MrReport)) {
				CommonJQ.click(driver, VolteCoverageEvaluationPage.List5s, true,"LTE MR上报周期(s)->5");
			} else if ("10".equalsIgnoreCase(MrReport)) {
				CommonJQ.click(driver, VolteCoverageEvaluationPage.List10s,
						true,"LTE MR上报周期(s)->10");
			} else {
				CommonJQ.click(driver, VolteCoverageEvaluationPage.List60s,
						true,"LTE MR上报周期(s)->60");
			}
		}
		if (UMTSMRDatePapaer != null) {
			CommonJQ.value(driver,
					VolteCoverageEvaluationPage.TextUMTSMRDatePapaer,
					UMTSMRDatePapaer, true,"UMTS周期MR上报周期(s)");
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param IndoorRate
	 * @param ComplexRate
	 * @param IndoorLevel
	 * @param ComplexLevel
	 */
	private static void setCoverMR(WebDriver driver, String IndoorRate,
			String ComplexRate, String IndoorLevel, String ComplexLevel) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, VolteCoverageEvaluationPage.TextIndoorRate,
				IndoorRate, true,"室内弱覆盖占比门限(%)");
		CommonJQ.value(driver, VolteCoverageEvaluationPage.TextComplexRate,
				ComplexRate, true,"综合弱覆盖占比门限(%)");
		CommonJQ.value(driver, VolteCoverageEvaluationPage.TextIndoorLevel,
				IndoorLevel, true,"室内弱覆盖电平门限(dBm)");
		CommonJQ.value(driver, VolteCoverageEvaluationPage.TextComplexLevel,
				ComplexLevel, true,"综合弱覆盖电平门限(dBm)");
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param RSRP
	 */
	private static void setRSRP(WebDriver driver, String[] RSRP) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (RSRP != null) {
			for (int i = 0; i < 4; i++) {
				CommonJQ.click(driver, "#level_range_table #tr2 #td6", true, "");
				CommonJQ.click(driver, "#delete_level_num", true, "电平区间:删除");
				Pause.pause(1000);
			}
			for (int i = 0; i < RSRP.length; i++) {
				CommonJQ.click(driver, "#level_range_table #tr1 #td5", true, "");
				CommonJQ.value(driver, "#maximumn", RSRP[i], true, "电平区间:最大");
				CommonJQ.click(driver, "#modify_level_num", true, "电平区间:修改");
				Pause.pause(1000);
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param AllFlage
	 * @param RateFlage
	 * @param RedirectionFlage
	 * @param CQIFlage
	 * @param UserCountFlage
	 * @param PerfFlowFlage
	 * @param SrvccFlage
	 * @param PrfReportFile
	 */
	private static void setExDimension(WebDriver driver, boolean AllFlage,
			boolean RateFlage, boolean RedirectionFlage, boolean CQIFlage,
			boolean UserCountFlage, boolean PerfFlowFlage, boolean SrvccFlage) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxAllChose, true,"扩展维度->全选");
		if (AllFlage) {
			CommonJQ.click(driver, VolteCoverageEvaluationPage.RBoxAllChose,
					true,"扩展维度->全选");
		} else {
			if (RateFlage) {
				CommonJQ.click(driver,
						VolteCoverageEvaluationPage.RBoxRateGeography, true,"扩展维度->流量/速率地理化");
			}
			if (RedirectionFlage) {
				CommonJQ.click(driver,
						VolteCoverageEvaluationPage.RBoxRedirectionGeography,
						true,"扩展维度-> 重定向地理化");
			}
			if (CQIFlage) {
				CommonJQ.click(driver,
						VolteCoverageEvaluationPage.RBoxCQIGeography, true,"扩展维度->CQI地理化");
			}
			if (UserCountFlage) {
				CommonJQ.click(driver,
						VolteCoverageEvaluationPage.RBoxUserCountGeography,
						true,"扩展维度->用户数地理化");
			}
			if (PerfFlowFlage) {
				CommonJQ.click(driver,
						VolteCoverageEvaluationPage.RBoxPerfFlowGeography, true,"扩展维度->话统流量地理化");
			}
			if (SrvccFlage) {
				CommonJQ.click(driver,
						VolteCoverageEvaluationPage.RBoxSrvccGeography, true,"扩展维度->SRVCC地理化");
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}


	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param LTEPPFile
	 * @param LTEpefFile
	 * @param LTEsigFile
	 * @param cfgFile
	 * @param PPFile
	 * @param pefFile
	 * @param chrFile
	 * @param mrFile
	 * @param propertyFile
	 * @param mapFile
	 */
	public static void ChooseData_ErrorMsg(WebDriver driver,
			String defaultWindowID, String[] LTEPPFile, String LTEpefFile,
			String[] LTEsigFile, String[] cfgFile, String[] PPFile,
			String pefFile, String[] chrFile, String[] mrFile,
			String[] propertyFile, String[] mapFile) {

		String ExLTESigErrMsg = "LTE SIG 不能为空";
		String ExLTEPPErrMsg = "LTE工参 不能为空";
		String ExGUCfgErrMsg = "配置 不能为空";
		String ExGUPPErrMsg = "工参 不能为空";

		String ExGUCHRErrMsg = "CHR 不能为空";
		String ExGUMRErrMsg = "MR 不能为空";
		String[] TaskType = { "VoLTE业务覆盖率分析" };
		VolteCoverageEvaluationTask.setServiceModule(driver, "test", "FDD",
				"UMTS", "HW", TaskType,null);
		// 设置网元数据
		VolteCoverageEvaluationTask.setNetData(driver, defaultWindowID,
				LTEPPFile, LTEpefFile, LTEsigFile, cfgFile, PPFile, pefFile,
				chrFile, mrFile, propertyFile, mapFile, null);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if (LTEsigFile == null) {
			String ErrMsg = CommonJQ
					.text(driver,
							"tr.dataAnalysisFileListUl.ng-scope:nth-child(1) > .taskTableSecondColumn > .fileChoiceErrorMsg");
			if (!ExLTESigErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:"
						+ ExLTESigErrMsg);
			}
		}
		if (LTEPPFile == null) {
			String ErrMsg = CommonJQ
					.text(driver,
							"tr.dataAnalysisFileListUl.ng-scope:nth-child(2) > .taskTableSecondColumn > .fileChoiceErrorMsg");
			if (!ExLTEPPErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:"
						+ ExLTEPPErrMsg);
			}
		}
		if (PPFile == null) {
			String ErrMsg = CommonJQ
					.text(driver,
							"tr.dataAnalysisFileListUl.ng-scope:nth-child(4) > .taskTableSecondColumn > .fileChoiceErrorMsg");
			if (!ExGUPPErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:"
						+ ExGUPPErrMsg);
			}
		}
		if (cfgFile == null) {
			String ErrMsg = CommonJQ
					.text(driver,
							"tr.dataAnalysisFileListUl.ng-scope:nth-child(5) > .taskTableSecondColumn > .fileChoiceErrorMsg");
			if (!ExGUCfgErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:"
						+ ExGUCfgErrMsg);
			}
		}

		if (chrFile == null) {
			String ErrMsg = CommonJQ
					.text(driver,
							"tr.dataAnalysisFileListUl.ng-scope:nth-child(6) > .taskTableSecondColumn > .fileChoiceErrorMsg");
			if (!ExGUCHRErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:"
						+ ExGUCHRErrMsg);
			}
		}
		if (mrFile == null) {
			String ErrMsg = CommonJQ
					.text(driver,
							"tr.dataAnalysisFileListUl.ng-scope:nth-child(7) > .taskTableSecondColumn > .fileChoiceErrorMsg");
			if (!ExGUMRErrMsg.equals(ErrMsg)) {
				Assert.fail("ActualValue:" + ErrMsg + ",ExpectedValue:"
						+ ExGUMRErrMsg);
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
		CommonJQ.click(driver, "#showDownLoad1", true);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "VoLTE覆盖评估");
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
	}

	/**
	 * 
	 * @param driver
	 * @param FallbackPolicy
	 * @param Type
	 * @param filePath
	 */
	public static void downLoad_Template(WebDriver driver,
			String FallbackPolicy, String Type, String filePath) {
		FileHandle.delSubFile(ConstUrl.DownLoadPath);
		FileHandle.delSubFile(filePath);
		String[] TaskType = { "VoLTE业务覆盖率分析", "VoLTE驻留率分析" };
		VolteCoverageEvaluationTask.setServiceModule(driver, "Test", "FDD",
				FallbackPolicy, "HW", TaskType,null);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("LTE".equalsIgnoreCase(Type)) {
			CommonJQ.click(driver, "#select_propertyDataTemplateDownload",
					true, "LTE话统模板");
			String reportName = TaskViewPluginTask.waittingDownLoadFile(
					ConstUrl.DownLoadPath, "LTE话统模板");
			FileHandle.copyFile(ConstUrl.DownLoadPath + "\\" + reportName,
					filePath + "\\LTE话统模板.csv");
		} else if ("UMTS".equalsIgnoreCase(Type)) {
			CommonJQ.click(driver, "#select_gsmPfmDataTemplateDownload", true,
					"UMTS话统模板");
			String reportName = TaskViewPluginTask.waittingDownLoadFile(
					ConstUrl.DownLoadPath, "UMTS话统模板");
			FileHandle.copyFile(ConstUrl.DownLoadPath + "\\" + reportName,
					filePath + "\\UMTS话统模板.csv");
		} else {
			Assert.fail("模板类型设置错误，不存在，Type：" + Type);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void GetAppName(WebDriver driver ) {
		TaskReportTask.GetAppName(driver, LanguageUtil.translate("VoLTE覆盖评估", "VoLTE Coverage Evaluation"));
	}
}
