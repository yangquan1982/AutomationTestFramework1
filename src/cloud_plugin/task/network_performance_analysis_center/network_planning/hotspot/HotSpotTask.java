package cloud_plugin.task.network_performance_analysis_center.network_planning.hotspot;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.hotspot.HotSpotPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.page.TaskViewPluginPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class HotSpotTask {

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName_Star
	 * @param PositType
	 * @param RasterValue
	 * @param AGPSFlage
	 * @param cfgFile
	 * @param EparaFile
	 * @param MrFile
	 * @param PfmFile
	 * @param NodeBPfmFile
	 * @param PCHRFile
	 * @param FeatureFile
	 * @param MapFile
	 * @return
	 * @return String
	 */

	public static String creatUMTSHotSpotSpeedTask(WebDriver driver,
			String defaultWindowID, String taskName_Star, String PositType,
			String RasterValue, boolean AGPSFlage, String[] cfgFile,
			String[] EparaFile, String[] MrFile, String[] PfmFile,
			String[] NodeBPfmFile, String[] FeatureFile, String[] MapFile,
			String RasterRateEvaType, String MRCount, String StartTime,
			String EndTime, String LowRate, String MainCellMR,
			boolean ClusterFlage, String FactorFile, boolean MRFlage) {
		// 打开
		NetworkAnalysisCenterTask.openUMTSHotSpot(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 选中任务名称
		String taskName = HotSpotPage.setTaskName(driver, taskName_Star);

		// 类型
		HotSpotPage.speedBtnClick(driver);
		HotSpotPage.nextBtnClick(driver);

		// 选择分析数据
		if (cfgFile != null) {
			if (cfgFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgFile,
						HotSpotPage.CfgBtn, defaultWindowID);
			}
		}

		if (EparaFile != null) {
			if (EparaFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EparaFile,
						HotSpotPage.EparaBtn, defaultWindowID);
			}
		}

		if (MrFile != null) {
			if (MrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, MrFile,
						HotSpotPage.MrBtn, defaultWindowID);
			}
		}

		if (PfmFile != null) {
			if (PfmFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, PfmFile,
						HotSpotPage.PfmBtn, defaultWindowID);
			}
		}

		if (NodeBPfmFile != null) {
			if (NodeBPfmFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, NodeBPfmFile,
						HotSpotPage.NodeBPfmBtn, defaultWindowID);
			}
		}

		if (FeatureFile != null) {
			GetDataByTypeTask.chooseTimeFolder(driver, FeatureFile,
					HotSpotPage.FeatureBtn, defaultWindowID);
		}
		if (MapFile != null) {
			if (MapFile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, MapFile,
					HotSpotPage.MapBtn, defaultWindowID);
			}
		}
		HotSpotPage.nextBtnClick(driver);
		// 参数设置
		// 定位方式设置
		HotSpotTask.taskPosit(driver, PositType, RasterValue, AGPSFlage);
		HotSpotTask.setRasterRateEva(driver, RasterRateEvaType, MRCount,
				StartTime, EndTime);
		HotSpotTask.setThreshold(driver, LowRate, MainCellMR);
		HotSpotTask.setCluster(driver, ClusterFlage, FactorFile);
		HotSpotTask.setOther(driver, MRFlage);
		// 提交任务
		HotSpotPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName_Star
	 * @param PositType
	 * @param RasterValue
	 * @param AGPSFlage
	 * @param cfgFile
	 * @param EparaFile
	 * @param MrFile
	 * @param PfmFile
	 * @param PCHRFile
	 * @return
	 * @return String
	 */
	public static String creatUMTSHotSpot6DTask(WebDriver driver,
			String defaultWindowID, String taskName_Star, String[] cfgFile,
			String[] EparaFile, String[] MrFile, String[] PCHRFile,
			String[] FeatureFile, String PositType, String RasterValue,
			boolean AGPSFlage, boolean AllFlage, boolean TerminalFlage,
			boolean BusinessFlage, boolean UserFlage, boolean ComplaintsFlage,
			boolean AnalysisFlage, String FactorFile) {
		// 打开
		NetworkAnalysisCenterTask.openUMTSHotSpot(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 选中任务名称
		String taskName = HotSpotPage.setTaskName(driver, taskName_Star);

		// 类型
		HotSpotPage.d6BtnClick(driver);
		HotSpotPage.nextBtnClick(driver);

		// 选择分析数据
		if (cfgFile != null) {
			if (cfgFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgFile,
						HotSpotPage.CfgBtn, defaultWindowID);
			}
		}
		if (EparaFile != null) {
			if (EparaFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EparaFile,
						HotSpotPage.EparaBtn, defaultWindowID);
			}
		}
		if (MrFile != null) {
			if (MrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, MrFile,
						HotSpotPage.MrBtn, defaultWindowID);
			}
		}
		if (PCHRFile != null) {
			if (PCHRFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, PCHRFile,
						HotSpotPage.PCHRBtn, defaultWindowID);
			}
		}

		if (FeatureFile != null) {
			GetDataByTypeTask.chooseTimeFolder(driver, FeatureFile,
					HotSpotPage.FeatureBtn, defaultWindowID);
		}
		HotSpotPage.nextBtnClick(driver);
		// 参数设置
		// 定位方式设置
		HotSpotTask.taskPosit(driver, PositType, RasterValue, AGPSFlage);
		HotSpotTask.set6DModel(driver, AllFlage, TerminalFlage, BusinessFlage,
				UserFlage, ComplaintsFlage, AnalysisFlage);
		HotSpotTask.set6DModelFile(driver, FactorFile);
		// 提交任务
		HotSpotPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param PositType
	 * @param RasterValue
	 * @param AGPSFlage
	 * @return void
	 */
	private static void taskPosit(WebDriver driver, String PositType,
			String RasterValue, boolean AGPSFlage) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("Fast".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, HotSpotPage.PosiObjLi_PATH, true);
			CommonJQ.click(driver, HotSpotPage.FastPosit, true);
		} else if ("Feature".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, HotSpotPage.PosiObjLi_PATH, true);
			CommonJQ.click(driver, HotSpotPage.FeaturePosit, true);
		} else if ("Complex".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, HotSpotPage.PosiObjLi_PATH, true);
			CommonJQ.click(driver, HotSpotPage.ComplexPosit, true);
		} else if ("AGPS".equalsIgnoreCase(PositType)) {
			CommonJQ.click(driver, HotSpotPage.PosiObjLi_PATH, true);
			CommonJQ.click(driver, HotSpotPage.AgpsPosit, true);
		}
		if ("20".equalsIgnoreCase(RasterValue)) {
			CommonJQ.click(driver, HotSpotPage.RasterObjLi_PATH, true);
			CommonJQ.click(driver, HotSpotPage.Raster20, true);
		} else if ("50".equalsIgnoreCase(RasterValue)) {
			CommonJQ.click(driver, HotSpotPage.RasterObjLi_PATH, true);
			CommonJQ.click(driver, HotSpotPage.Raster50, true);
		}
		if (AGPSFlage) {
			CommonJQ.click(driver, HotSpotPage.CheckAgps_ID, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskName
	 * @return void
	 */
	public static void Creat_CancleTask(WebDriver driver, String taskName) {
		// 打开
		NetworkAnalysisCenterTask.openUMTSCoverEvaluate(driver);
		;
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		taskName = HotSpotPage.setTaskName(driver, taskName);
		HotSpotPage.cancelBtnClick(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskName
	 * @return void
	 */
	public static void Creat_CancleTask2(WebDriver driver, String taskName) {
		// 打开
		NetworkAnalysisCenterTask.openUMTSCoverEvaluate(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		taskName = HotSpotPage.setTaskName(driver, taskName);
		HotSpotPage.cancelTitleClick(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
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
		TaskViewPluginPage.reportDownLoadHotSpotClick(driver);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "价值区域评估报告");
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
	}

	/**
	 * <b>Description:</b>栅格速率评估
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param RasterRateEvaType
	 * @param MRCount
	 * @param StartTime
	 * @param EndTime
	 * @return void
	 */
	private static void setRasterRateEva(WebDriver driver,
			String RasterRateEvaType, String MRCount, String StartTime,
			String EndTime) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.siblingsClick(driver, HotSpotPage.TextRasterRateEvaType,
				"input[type=\"text\"]");
		if ("All".equalsIgnoreCase(RasterRateEvaType)) {
			CommonJQ.click(driver, HotSpotPage.ListRateEvaType_All, true);
		} else {
			CommonJQ.click(driver, HotSpotPage.ListRateEvaType_one, true);
		}
		CommonJQ.value(driver, HotSpotPage.TextMRCount, MRCount);
		CommonJQ.value(driver, HotSpotPage.TextStartTime, StartTime);
		CommonJQ.value(driver, HotSpotPage.TextEndTime, EndTime);

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>门限
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param LowRate
	 * @param MainCellMR
	 * @return void
	 */
	private static void setThreshold(WebDriver driver, String LowRate,
			String MainCellMR) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		CommonJQ.value(driver, HotSpotPage.TextLowRate, LowRate);
		CommonJQ.value(driver, HotSpotPage.TextMainCellMR, MainCellMR);

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>Cluster
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param WhetherCluster
	 * @param FactorFile
	 * @return void
	 */
	private static void setCluster(WebDriver driver, boolean ClusterFlage,
			String FactorFile) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.siblingsClick(driver, HotSpotPage.TextCluster,
				"input[type=\"text\"]");
		if (ClusterFlage) {
			CommonJQ.click(driver, HotSpotPage.ListCluster_Yes, true);
		} else {
			CommonJQ.click(driver, HotSpotPage.ListCluster_No, true);
		}
		if(FactorFile != null){
			CommonJQ.click(driver, HotSpotPage.BtnFactor, true);
			CommonFile.ChooseOneFile(FactorFile);	
		}


		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param WhetherMR
	 * @return void
	 */
	private static void setOther(WebDriver driver, boolean MRFlage) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.siblingsClick(driver, HotSpotPage.TextWhetherMR,
				"input[type=\"text\"]");
		if (MRFlage) {
			CommonJQ.click(driver, HotSpotPage.ListWhetherMR_Yes, true);
		} else {
			CommonJQ.click(driver, HotSpotPage.ListWhetherMR_No, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param AllFlage
	 * @param TerminalFlage
	 * @param BusinessFlage
	 * @param UserFlage
	 * @param ComplaintsFlage
	 * @param AnalysisFlage
	 * @return void
	 */
	private static void set6DModel(WebDriver driver, boolean AllFlage,
			boolean TerminalFlage, boolean BusinessFlage, boolean UserFlage,
			boolean ComplaintsFlage, boolean AnalysisFlage) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, HotSpotPage.CBox6DAll, true);
		if (AllFlage) {
			CommonJQ.click(driver, HotSpotPage.CBox6DAll, true);
		} else {
			if (TerminalFlage) {
				CommonJQ.click(driver, HotSpotPage.CBox6DTerminal, true);
			}
			if (BusinessFlage) {
				CommonJQ.click(driver, HotSpotPage.CBox6DBusiness, true);
			}
			if (UserFlage) {
				CommonJQ.click(driver, HotSpotPage.CBox6DUser, true);
			}
			if (ComplaintsFlage) {
				CommonJQ.click(driver, HotSpotPage.CBox6DComplaints, true);
			}
			if (AnalysisFlage) {
				CommonJQ.click(driver, HotSpotPage.CBox6DAnalysis, true);
			}

		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param FactorFile
	 * @return void
	 */
	private static void set6DModelFile(WebDriver driver, String FactorFile) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (FactorFile != null) {
			CommonJQ.click(driver, HotSpotPage.BtnFactor, true);
			CommonFile.ChooseOneFile(FactorFile);
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}
}
