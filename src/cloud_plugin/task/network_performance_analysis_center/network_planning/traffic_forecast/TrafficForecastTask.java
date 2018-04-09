package cloud_plugin.task.network_performance_analysis_center.network_planning.traffic_forecast;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.traffic_forecast.TrafficForecastPage;
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
import common.util.SeleniumUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class TrafficForecastTask {

	/**
	 * <b>Description:</b>基础话务预测
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param ServiceModule
	 * @param groupFlage
	 * @param groupType
	 * @param groupFile
	 * @param cfgFile
	 * @param pefFile
	 * @param chrFile
	 * @param pefU2000File
	 * @param pefPRSFile
	 * @param terminalFile
	 * @param HolidayFile
	 * @param ModifyPara
	 * @param PredictionDate
	 * @param IndicType
	 * @param IndicPara
	 * @return
	 * @return String
	 */
	public static String creatUMTSBasicTrafficForecastTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean groupFlage,
			String groupType, String groupFile, String[] cfgFile,
			String[] pefFile, String[] chrFile, String[] pefU2000File,
			String[] pefPRSFile, String terminalFile, String HolidayFile,
			String ModifyPara, String PredictionDate, String IndicType,
			boolean CalcFlage) {
		String[] ServiceModule = { TrafficForecastPage.CBoxBasic };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, groupFlage,
				groupType, groupFile, cfgFile, pefFile, chrFile, pefU2000File,
				pefPRSFile, terminalFile, null);
		TrafficForecastTask.setCommonPara(driver, HolidayFile, ModifyPara,
				PredictionDate);
		if(pefPRSFile==null){
			TrafficForecastTask.setBasicIndicators(driver, IndicType, CalcFlage);
		}else{
			TrafficForecastTask.setIndicators(driver, IndicType, CalcFlage);
		}
		
		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>话务抑制还原
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param cfgFile
	 * @param pefFile
	 * @param chrFile
	 * @param pefU2000File
	 * @param pefPRSFile
	 * @param terminalFile
	 * @param HolidayFile
	 * @param PredictionDate
	 * @param TcpUsage
	 * @param DcmUser
	 * @param HsdpaAve
	 * @return
	 * @return String
	 */
	public static String creatUMTSSuppreReductionTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean groupFlage,
			String groupType, String groupFile, String[] cfgFile,
			String[] pefFile, String[] chrFile, String[] pefU2000File,
			String[] pefPRSFile, String terminalFile, String HolidayFile,
			String ModifyPara, String PredictionDate, String SuppreType,String TcpUsage,
			String DcmUser, String HsdpaAve) {
		String[] ServiceModule = { TrafficForecastPage.CBoxDepress };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, groupFlage,
				groupType, groupFile, cfgFile, pefFile, chrFile, pefU2000File,
				pefPRSFile, terminalFile, null);

		TrafficForecastTask.setSuppreReduction(driver, SuppreType,TcpUsage, DcmUser,
				HsdpaAve);
		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>明星终端发展预测
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param groupFlage
	 * @param groupType
	 * @param groupFile
	 * @param cfgFile
	 * @param pefFile
	 * @param chrFile
	 * @param pefU2000File
	 * @param pefPRSFile
	 * @param terminalFile
	 * @param HolidayFile
	 * @param ModifyPara
	 * @param PredictionDate
	 * @param IndicType
	 * @param IndicPara
	 * @param TerminalCompany
	 * @param TermForecast
	 * @param PlanTermCount
	 * @param TerminalA
	 * @param TerminalB
	 * @return
	 * @return String
	 */
	public static String creatUMTSStarTermTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean groupFlage,
			String groupType, String groupFile, String[] cfgFile,
			String[] pefFile, String[] chrFile, String[] pefU2000File,
			String[] pefPRSFile, String terminalFile, String HolidayFile,
			String ModifyPara, String PredictionDate, String IndicType,
			String TerminalCompany, String TermForecast, String PlanTermCount,
			String TerminalA, String TerminalB, int Time, boolean CalcFlage) {
		String[] ServiceModule = { TrafficForecastPage.CBoxBasic,
				TrafficForecastPage.CBoxStarTerm };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, groupFlage,
				groupType, groupFile, cfgFile, pefFile, chrFile, pefU2000File,
				pefPRSFile, terminalFile, null);
		TrafficForecastTask.setCommonPara(driver, HolidayFile, ModifyPara,
				PredictionDate);
		if(pefPRSFile==null){
			TrafficForecastTask.setBasicIndicators(driver, IndicType, CalcFlage);
		}else{
			TrafficForecastTask.setIndicators(driver, IndicType, CalcFlage);
		}
		TrafficForecastTask.setTerminalDev(driver, TerminalCompany,
				TermForecast, PlanTermCount, TerminalA, TerminalB);
		TrafficForecastTask.setBusyTime(driver,
				TrafficForecastPage.BtnGradUserBusyHour, Time);
		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>用户发展预测
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param groupFlage
	 * @param groupType
	 * @param groupFile
	 * @param cfgFile
	 * @param pefFile
	 * @param chrFile
	 * @param pefU2000File
	 * @param pefPRSFile
	 * @param terminalFile
	 * @param HolidayFile
	 * @param ModifyPara
	 * @param PredictionDate
	 * @param IndicType
	 * @param IndicPara
	 * @param UsersType
	 * @param UserPlan
	 * @param HistoryNum
	 * @param HighUser
	 * @param MedialUser
	 * @param LowUser
	 * @return
	 * @return String
	 */
	public static String creatUMTSUserDevTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean groupFlage,
			String groupType, String groupFile, String[] cfgFile,
			String[] pefFile, String[] chrFile, String[] pefU2000File,
			String[] pefPRSFile, String terminalFile, String HolidayFile,
			String ModifyPara, String PredictionDate, String IndicType,
			String UsersType, String UserPlan, String HistoryNum,
			String HighUser, String MedialUser, String LowUser, int Times,
			boolean CalcFlage) {
		String[] ServiceModule = { TrafficForecastPage.CBoxBasic,
				TrafficForecastPage.CBoxGradUser };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, groupFlage,
				groupType, groupFile, cfgFile, pefFile, chrFile, pefU2000File,
				pefPRSFile, terminalFile, null);
		TrafficForecastTask.setCommonPara(driver, HolidayFile, ModifyPara,
				PredictionDate);
		if(pefPRSFile==null){
			TrafficForecastTask.setBasicIndicators(driver, IndicType, CalcFlage);
		}else{
			TrafficForecastTask.setIndicators(driver, IndicType, CalcFlage);
		}
		TrafficForecastTask.setUsersDev(driver, UsersType, UserPlan,
				HistoryNum, HighUser, MedialUser, LowUser);
		TrafficForecastTask.setBusyTime(driver,
				TrafficForecastPage.BtnGradUserBusyHour, Times);
		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param groupFlage
	 * @param groupType
	 * @param groupFile
	 * @param cfgFile
	 * @param pefFile
	 * @param chrFile
	 * @param pefU2000File
	 * @param pefPRSFile
	 * @param terminalFile
	 * @param HolidayFile
	 * @param ModifyPara
	 * @param PredictionDate
	 * @param MinuteTrafficFile
	 * @param TypeIndicator
	 * @param Times
	 * @return
	 * @return String
	 */
	public static String creatUMTSMinuteTrafficTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean groupFlage,
			String groupType, String groupFile, String[] cfgFile,
			String[] MinuteFile, String HolidayFile, String ModifyPara,
			String PredictionDate, String[] TypeIndicator, int[] Times) {
		String[] ServiceModule = { TrafficForecastPage.CBoxSeparate };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, groupFlage,
				groupType, groupFile, cfgFile, null, null, null, null, null,
				MinuteFile);
		TrafficForecastTask.setCommonPara(driver, HolidayFile, ModifyPara,
				PredictionDate);
		TrafficForecastTask.setMinuteTraffic(driver, TypeIndicator, Times);
		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param groupFlage
	 * @param groupType
	 * @param groupFile
	 * @param cfgFile
	 * @param pefFile
	 * @param chrFile
	 * @param pefU2000File
	 * @param pefPRSFile
	 * @param terminalFile
	 * @param HolidayFile
	 * @param ModifyPara
	 * @param PredictionDate
	 * @return
	 * @return String
	 */
	public static String creatUMTSRibbonDevTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean groupFlage,
			String groupType, String groupFile, String[] cfgFile,
			String[] pefFile, String[] chrFile, String[] pefU2000File,
			String[] pefPRSFile, String terminalFile, String HolidayFile,
			String ModifyPara, String PredictionDate, String Terminal1,
			String Terminal2, String Terminal3, int Time) {
		String[] ServiceModule = { TrafficForecastPage.CBoxFuncArea };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, groupFlage,
				groupType, groupFile, cfgFile, pefFile, chrFile, pefU2000File,
				pefPRSFile, terminalFile, null);
		TrafficForecastTask.setCommonPara(driver, HolidayFile, ModifyPara,
				PredictionDate);
		TrafficForecastTask.setRibbonDev(driver, Terminal1, Terminal2,
				Terminal3);
		TrafficForecastTask.setBusyTime(driver,
				TrafficForecastPage.BtnRibbonDevBusyHour, Time);
		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	public static String creatUMTSGreatEvenNoHisTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean groupFlage,
			String groupType, String groupFile, String[] cfgFile,
			String[] pefFile, String[] chrFile, String[] pefU2000File,
			String[] pefPRSFile, String terminalFile, String HolidayFile,
			String ModifyPara, String PredictionDate, String[] EvenIndicator,
			String GroundCount, String TypeRatio, String MarketRatio) {
		String[] ServiceModule = { TrafficForecastPage.CBoxGreatEven };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, groupFlage,
				groupType, groupFile, cfgFile, pefFile, chrFile, pefU2000File,
				pefPRSFile, terminalFile, null);
		TrafficForecastTask.setCommonPara(driver, HolidayFile, ModifyPara,
				PredictionDate);
		TrafficForecastTask.setGreatEvenNoHis(driver, EvenIndicator,
				GroundCount, TypeRatio, MarketRatio);
		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	public static String creatUMTSGreatEvenHisTask(WebDriver driver,
			String defaultWindowID, String taskName, String groupType,
			String groupFile, String[] cfgFile, String[] pefFile,
			String[] chrFile, String[] pefU2000File, String[] pefPRSFile,
			String terminalFile, String HolidayFile, String ModifyPara,
			String PredictionDate, String HisTimeStart, String HisTimeEnd,
			String GroundCount, String TypeRatio, String MarketRatio) {
		String[] ServiceModule = { TrafficForecastPage.CBoxGreatEven };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, true,
				groupType, groupFile, cfgFile, pefFile, chrFile, pefU2000File,
				pefPRSFile, terminalFile, null);
		TrafficForecastTask.setCommonPara(driver, HolidayFile, ModifyPara,
				PredictionDate);
		TrafficForecastTask.setGreatEvenHis(driver, HisTimeStart, HisTimeEnd,
				GroundCount, TypeRatio, MarketRatio);
		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	public static String creatUMTSGreatEvenLTETask(WebDriver driver,
			String defaultWindowID, String taskName, String groupType,
			String groupFile, String[] cfgFile, String[] pefFile,
			String[] chrFile, String[] pefU2000File, String[] pefPRSFile,
			String terminalFile, String HolidayFile, String ModifyPara,
			String PredictionDate, String HisTimeStart, String HisTimeEnd,
			String ModelStar, String ModelEnd, String SubStart, String SubEnd,
			String GroundCount, String TypeRatio, String MarketRatio) {
		String[] ServiceModule = { TrafficForecastPage.CBoxGreatEven };
		taskName = TrafficForecastTask.setServiceModule(driver, taskName,
				ServiceModule);
		TrafficForecastTask.setNetData(driver, defaultWindowID, true,
				groupType, groupFile, cfgFile, pefFile, chrFile, pefU2000File,
				pefPRSFile, terminalFile, null);
		TrafficForecastTask.setCommonPara(driver, HolidayFile, ModifyPara,
				PredictionDate);
		TrafficForecastTask.setGreatEvenLTE(driver, HisTimeStart, HisTimeEnd,
				ModelStar, ModelEnd, SubStart, SubEnd, GroundCount, TypeRatio,
				MarketRatio);

		TrafficForecastPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskName
	 * @param ServiceModule
	 * @return void
	 */
	private static String setServiceModule(WebDriver driver, String taskName,
			String[] ServiceModule) {
		// 打开
		NetworkAnalysisCenterTask.openUMTSTrafficForecast(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 选中任务名称
		taskName = TrafficForecastPage.setTaskName(driver, taskName);
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, TrafficForecastPage.CBoxBasic, true);
		for (int i = 0; i < ServiceModule.length; i++) {
			CommonJQ.click(driver, ServiceModule[i], true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		TrafficForecastPage.nextBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param groupFlage
	 * @param groupType
	 * @param groupFile
	 * @param cfgFile
	 * @param pefFile
	 * @param chrFile
	 * @param pefU2000File
	 * @param pefPRSFile
	 * @param terminalFile
	 * @return void
	 */
	private static void setNetData(WebDriver driver, String defaultWindowID,
			boolean groupFlage, String groupType, String groupFile,
			String[] cfgFile, String[] pefFile, String[] chrFile,
			String[] pefU2000File, String[] pefPRSFile, String terminalFile,
			String[] MinuteFile) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// CommonJQ.checkBoxClick(driver, TrafficForecastPage.CBoxGroup, "", 0,
		// groupFlage);
		if (groupFlage) {
			CommonJQ.click(driver, TrafficForecastPage.CBoxGroup, false);
			CommonJQ.siblingsClick(driver, TrafficForecastPage.TextGroup,
					"input[type=\"text\"]");
			if ("RNC".equalsIgnoreCase(groupType)) {
				CommonJQ.click(driver, TrafficForecastPage.ListRNC, false);
			} else if ("NODEB".equalsIgnoreCase(groupType)) {
				CommonJQ.click(driver, TrafficForecastPage.ListNODEB, false);
			} else if ("UCELL".equalsIgnoreCase(groupType)) {
				CommonJQ.click(driver, TrafficForecastPage.ListUCELL, false);
			}

			CommonJQ.click(driver, TrafficForecastPage.BtnGroup, false);
			CommonFile.ChooseOneFile(groupFile);
			CommonJQ.click(driver, TrafficForecastPage.BtnMessageOK, false);
		}
		// 选择分析数据
		if (terminalFile != null) {
			if ("default".equalsIgnoreCase(terminalFile)) {
				CommonJQ.click(driver, TrafficForecastPage.RBoxdefaultTerminal,
						true);
			} else {
				CommonJQ.click(driver, TrafficForecastPage.BtnTerminal, false);
				CommonFile.ChooseOneFile(terminalFile);
				CommonJQ.click(driver, TrafficForecastPage.BtnMessageOK, false);
			}

		}

		SwitchDriver.switchDriverToSEQ(driver);
		// 选择分析数据
		if (cfgFile != null) {
			if (cfgFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgFile,
						TrafficForecastPage.BtnCfg, defaultWindowID);
			}
		}

		// 选择分析数据
		if (pefFile != null) {
			if (pefFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, pefFile,
						TrafficForecastPage.BtnPef, defaultWindowID);
			}
		}

		// 选择分析数据
		if (chrFile != null) {
			if (chrFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, chrFile,
						TrafficForecastPage.BtnChr, defaultWindowID);
			}
		}

		// 选择分析数据
		if (pefU2000File != null) {
			if (pefU2000File.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, pefU2000File,
						TrafficForecastPage.BtnU2000Pef, defaultWindowID);
			}
		}
		// 选择分析数据
		if (MinuteFile != null) {
			if (MinuteFile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, MinuteFile,
						TrafficForecastPage.BtnMinuteTrafficData,
						defaultWindowID);
			}
		}
		// 选择分析数据
		if (pefPRSFile != null) {
			GetDataByTypeTask.chooseTimeFolder(driver, pefPRSFile,
					TrafficForecastPage.BtnPrsPef, defaultWindowID);

			SwitchDriver.switchDriverToFrame(driver, "//iframe");

			boolean flage =	CommonJQ.isExisted(driver, "input[id=\"selectedSpecialNum\"]", true);
			for(int i = 0;(i<2*60)&&(flage==false);i++){
				if(flage){
					break;
				}else{
					Pause.pause(1000);
					flage =	CommonJQ.isExisted(driver, "input[id=\"selectedSpecialNum\"]", true);
				}
			}
			CommonJQ.click(driver, "input[name = \"umtsTask.selectedNeType\"]",
					true, 0);// UCELL
			CommonJQ.siblingsClick(driver, "input[name=\"umtsTask.rncName\"]",
					"input[type=\"text\"]");
			CommonJQ.click(driver, "ul.mshow > li#RNCNAME", true);

			CommonJQ.siblingsClick(driver,
					"input[name=\"umtsTask.nodeBName\"]",
					"input[type=\"text\"]");
			CommonJQ.click(driver, "ul.mshow>li[id =\"NodeB Name\"]", true);

			CommonJQ.siblingsClick(driver, "input[name=\"umtsTask.cellName\"]",
					"input[type=\"text\"]");
			CommonJQ.click(driver, "ul.mshow>li[id =\"Cell Name\"]", true);

			CommonJQ.siblingsClick(driver, "input[name=\"umtsTask.time\"]",
					"input[type=\"text\"]");
			CommonJQ.click(driver, "ul.mshow>li#Time", true);

			CommonJQ.siblingsClick(driver,
					"input[name=\"umtsTask.subscribers\"]",
					"input[type=\"text\"]");
			CommonJQ.click(driver, "ul.mshow> li#Subscribers", true);

			CommonJQ.click(driver, "input[id=\"selectedSpecialNum\"]", true);
			CommonJQ.click(driver, "#allSelect", true);

			SwitchDriver.switchDriverToSEQ(driver);
		}

		TrafficForecastPage.nextBtnClick(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param HolidayFile
	 * @param ModifyPara
	 * @param PredictionDate
	 * @return void
	 */
	private static void setCommonPara(WebDriver driver, String HolidayFile,
			String ModifyPara, String PredictionDate) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (HolidayFile != null) {
			CommonJQ.click(driver, TrafficForecastPage.BtnHoliday, true);
			CommonFile.ChooseOneFile(HolidayFile);
			CommonJQ.click(driver, TrafficForecastPage.BtnMessageOK, true);
		}
		CommonJQ.value(driver, TrafficForecastPage.TextFutDate, PredictionDate);
		SwitchDriver.switchDriverToSEQ(driver);

	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param IndicType
	 * @param IndicPara
	 * @return void
	 */
	private static void setBasicIndicators(WebDriver driver, String IndicType,
			boolean CalcFlage) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("RNC".equalsIgnoreCase(IndicType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxRNC, false);
		} else if ("Cell".equalsIgnoreCase(IndicType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxCell, false);
		} else if ("NodeB".equalsIgnoreCase(IndicType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxNodeB, false);
		} else if ("Group".equalsIgnoreCase(IndicType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxGroup, false);
		}
		CommonJQ.click(driver, TrafficForecastPage.BtnIndexData, true);		
		CommonJQ.click(driver, TrafficForecastPage.CBoxCalc, true);
		if (CalcFlage) {
			CommonJQ.click(driver, TrafficForecastPage.CBoxCalc, true);
		}
		CommonJQ.click(driver, TrafficForecastPage.BtnndexDataOK, true);
		SwitchDriver.switchDriverToSEQ(driver);

	}

	private static void setIndicators(WebDriver driver, String IndicType,
			boolean CalcFlage) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("RNC".equalsIgnoreCase(IndicType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxRNC, false);
		} else if ("Cell".equalsIgnoreCase(IndicType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxCell, false);
		} else if ("NodeB".equalsIgnoreCase(IndicType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxNodeB, false);
		} else if ("Group".equalsIgnoreCase(IndicType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxGroup, false);
		}
		CommonJQ.click(driver, TrafficForecastPage.BtnIndexData, true);
		CommonJQ.click(driver, TrafficForecastPage.BtnLeft, true);

		for (int i = 0; i < CommonJQ
				.length(driver,
						"div[class=\"boxMove mr\"] input[ng-model=\"rightTreeData.checked\"]",
						true); i++) {
			CommonJQ.click(
					driver,
					"div[class=\"boxMove mr\"] input[ng-model=\"rightTreeData.checked\"]",
					true, i);
		}
		CommonJQ.click(driver, TrafficForecastPage.BtnLeft, true);
		for (int i = 0; (i < 30)&&(i < CommonJQ
				.length(driver,
						"div[class=\"ml boxMove\"] input[ng-model=\"kpiData.checked\"]",
						true)); i++) {
			CommonJQ.click(
					driver,
					"div[class=\"ml boxMove\"] input[ng-model=\"kpiData.checked\"]",
					true, i);
		}
		CommonJQ.click(driver, TrafficForecastPage.BtnRight, true);
		CommonJQ.click(driver, TrafficForecastPage.CBoxCalc, true);
		if (CalcFlage) {
			CommonJQ.click(driver, TrafficForecastPage.CBoxCalc, true);
		}
		CommonJQ.click(driver, TrafficForecastPage.BtnndexDataOK, true);
		SwitchDriver.switchDriverToSEQ(driver);

	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param TcpUsage
	 * @param DcmUser
	 * @param HsdpaAve
	 * @return void
	 */
	private static void setSuppreReduction(WebDriver driver,String SuppreType, String TcpUsage,
			String DcmUser, String HsdpaAve) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if("自动参数设置".equalsIgnoreCase(SuppreType)){
			CommonJQ.click(driver, "input[name=\"umtsTask.depressParamSetting\"]", true, 0, "自动参数设置");
		}else if("手动参数设置".equalsIgnoreCase(SuppreType)){
			CommonJQ.click(driver, "input[name=\"umtsTask.depressParamSetting\"]", true, 1, "手动参数设置");
			CommonJQ.value(driver, TrafficForecastPage.TextTcpUsage, TcpUsage,true,"TCP利用率门限（%）");
			CommonJQ.value(driver, TrafficForecastPage.TextDcmUser, DcmUser,true,"DCH在线用户数门限（%）");
			CommonJQ.value(driver, TrafficForecastPage.TextHsdpaAve, HsdpaAve,true,"HSDPA平均速率门限（%）");
		}else{
			Assert.fail("话务抑制还原 参数类型错误,应设置为:自动参数设置、手动参数设置");
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param TerminalCompany
	 * @param TermForecast
	 * @param PlanTermCount
	 * @param TerminalA
	 * @param TerminalB
	 * @return void
	 */
	private static void setTerminalDev(WebDriver driver,
			String TerminalCompany, String TermForecast, String PlanTermCount,
			String TerminalA, String TerminalB) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		CommonJQ.click(driver, TrafficForecastPage.TextStarTFore, false);
		CommonJQ.click(driver,
				"ul[id=\"mshowumtsTask.starTForecastVendor\"] li[id=\""
						+ TerminalCompany + "\"]", false);

		CommonJQ.value(driver, TrafficForecastPage.TextStarTTerm, TermForecast);
		SeleniumUtil.val_ByName(driver, "umtsTask.startPlanTermCount",
				PlanTermCount);

		CommonJQ.siblingsClick(driver, TrafficForecastPage.TextTermA,
				"input[type=\"text\"]");
		CommonJQ.click(driver,
				"ul[id=\"mshowumtsTask.startTermRef1\"] li[id=\"" + TerminalA
						+ "\"]", false);

		CommonJQ.siblingsClick(driver, TrafficForecastPage.TextTermB,
				"input[type=\"text\"]");
		CommonJQ.click(driver,
				"ul[id=\"mshowumtsTask.startTermRef2\"] li[id=\"" + TerminalB
						+ "\"]", false);

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param UsersType
	 * @param UserPlan
	 * @param HistoryNum
	 * @param HighUser
	 * @param MedialUser
	 * @param LowUser
	 * @return void
	 */
	private static void setUsersDev(WebDriver driver, String UsersType,
			String UserPlan, String HistoryNum, String HighUser,
			String MedialUser, String LowUser) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("GradUser".equalsIgnoreCase(UsersType)) {
			CommonJQ.click(driver, TrafficForecastPage.RBoxGradUser, false, 1);
			CommonJQ.value(driver, TrafficForecastPage.TextHighUser, HighUser);
			CommonJQ.value(driver, TrafficForecastPage.TextMedialUser,
					MedialUser);
			CommonJQ.value(driver, TrafficForecastPage.TextLowUser, LowUser);
		} else {
			CommonJQ.click(driver, TrafficForecastPage.RBoxNoGradUser, false, 0);
			CommonJQ.value(driver, TrafficForecastPage.TextUserPlan, UserPlan);
			CommonJQ.value(driver, TrafficForecastPage.TextHistoryNum,
					HistoryNum);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param String
	 *            MinuteTrafficFile
	 * @param String
	 *            [] TypeIndicator
	 * @param int[] Times
	 * @return void
	 */
	private static void setMinuteTraffic(WebDriver driver,
			String[] TypeIndicator, int[] Times) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		for (int i = 0; i < TypeIndicator.length; i++) {
			if ("ALL".equalsIgnoreCase(TypeIndicator[0])) {
				CommonJQ.click(driver, TrafficForecastPage.CBoxALL, false);
				break;
			}

		}
		SwitchDriver.switchDriverToSEQ(driver);
		for (int i = 0; i < Times.length; i++) {
			CommonJQ.click(
					driver,
					"$('iframe[name=main]').contents().find('ul[id=\"hourList1\"] li[ng-repeat=\"hour in hourList\"]').eq("
							+ i + ")");
		}

	}

	private static void setGreatEvenNoHis(WebDriver driver,
			String[] EvenIndicator, String GroundCount, String TypeRatio,
			String MarketRatio) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, TrafficForecastPage.RBoxNoHisData, false, 0);
		for (int i = 0; i < EvenIndicator.length; i++) {
			if ("ALL".equalsIgnoreCase(EvenIndicator[0])) {
				CommonJQ.click(driver, TrafficForecastPage.CBoxGreatEventALL,
						false);
				break;
			}
		}

		CommonJQ.value(driver, TrafficForecastPage.TextGroundCount, GroundCount);
		CommonJQ.value(driver, TrafficForecastPage.TextNetTypeRatio, TypeRatio);
		CommonJQ.value(driver, TrafficForecastPage.TextMarketRatio, MarketRatio);

		SwitchDriver.switchDriverToSEQ(driver);
	}

	private static void setGreatEvenHis(WebDriver driver, String HisTimeStart,
			String HisTimeEnd, String GroundCount, String TypeRatio,
			String MarketRatio) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, TrafficForecastPage.RBoxHasHisData, false, 1);
		CommonJQ.value(driver, TrafficForecastPage.TextHisTimeStart,
				HisTimeStart);
		CommonJQ.value(driver, TrafficForecastPage.TextHisTimeEnd, HisTimeEnd);

		CommonJQ.value(driver, TrafficForecastPage.TextGroundCount, GroundCount);
		CommonJQ.value(driver, TrafficForecastPage.TextNetTypeRatio, TypeRatio);
		CommonJQ.value(driver, TrafficForecastPage.TextMarketRatio, MarketRatio);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	private static void setGreatEvenLTE(WebDriver driver, String HisTimeStart,
			String HisTimeEnd, String ModelStar, String ModelEnd,
			String SubStart, String SubEnd, String GroundCount,
			String TypeRatio, String MarketRatio) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		CommonJQ.click(driver, TrafficForecastPage.RBoxLTEHisData, false, 2);

		CommonJQ.value(driver, TrafficForecastPage.TextLTETimeStart,
				HisTimeStart);
		CommonJQ.value(driver, TrafficForecastPage.TextLTETimeEnd, HisTimeEnd);

		CommonJQ.value(driver, TrafficForecastPage.TextModelStar, ModelStar);
		CommonJQ.value(driver, TrafficForecastPage.TextModelEnd, ModelEnd);

		CommonJQ.value(driver, TrafficForecastPage.TextSubStart, SubStart);
		CommonJQ.value(driver, TrafficForecastPage.TextSubEnd, SubEnd);

		CommonJQ.value(driver, TrafficForecastPage.TextGroundCount, GroundCount);
		CommonJQ.value(driver, TrafficForecastPage.TextNetTypeRatio, TypeRatio);
		CommonJQ.value(driver, TrafficForecastPage.TextMarketRatio, MarketRatio);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param Times
	 * @return void
	 */
	private static void setBusyTime(WebDriver driver, String BusyType, int Time) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if (Time >= 0 && Time <= 23) {
			CommonJQ.click(driver, BusyType, false, Time);
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	private static void setRibbonDev(WebDriver driver, String Terminal1,
			String Terminal2, String Terminal3) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (Terminal1 != null) {
			CommonJQ.siblingsClick(driver, TrafficForecastPage.TextTerminal1,
					"input[type=\"text\"]");
			CommonJQ.click(driver,
					"ul[id=\"mshowumtsTask.funcAreaDeveRefTerm1\"] li[id=\""
							+ Terminal1 + "\"]", false);
		}
		if (Terminal2 != null) {
			CommonJQ.siblingsClick(driver, TrafficForecastPage.TextTerminal2,
					"input[type=\"text\"]");
			CommonJQ.click(driver,
					"ul[id=\"mshowumtsTask.funcAreaDeveRefTerm2\"] li[id=\""
							+ Terminal2 + "\"]", false);
		}

		if (Terminal3 != null) {
			CommonJQ.siblingsClick(driver, TrafficForecastPage.TextTerminal3,
					"input[type=\"text\"]");
			CommonJQ.click(driver,
					"ul[id=\"mshowumtsTask.funcAreaDeveRefTerm3\"] li[id=\""
							+ Terminal3 + "\"]", false);
		}

		if (Terminal1 != null) {
			CommonJQ.siblingsClick(driver, TrafficForecastPage.TextTerminal1,
					"input[type=\"text\"]");
			CommonJQ.click(driver,
					"ul[id=\"mshowumtsTask.funcAreaDeveRefTerm1\"] li[id=\""
							+ Terminal1 + "\"]", false);
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
		NetworkAnalysisCenterTask.openUMTSTrafficForecast(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		taskName = TrafficForecastPage.setTaskName(driver, taskName);
		TrafficForecastPage.cancelBtnClick(driver);
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
		NetworkAnalysisCenterTask.openUMTSTrafficForecast(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		taskName = TrafficForecastPage.setTaskName(driver, taskName);
		TrafficForecastPage.cancelTitleClick(driver);
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
		TaskViewPluginPage.reportDownLoadTrafficForecastClick(driver);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "UMTS目标网预测报告");
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName,
				EnvConstant.Path_DownLoad);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + "UMTSReport.zip",
				ResultHome);

	}
}
