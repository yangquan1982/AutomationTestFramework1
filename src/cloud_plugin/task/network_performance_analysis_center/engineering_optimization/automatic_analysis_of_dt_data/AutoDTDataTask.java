package cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDTDataPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskReportTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutoDTDataTask {

	/**
	 * <b>Description:</b>DT分析优化
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param WhetherTopN
	 * @param TopN
	 * @param WhetherGrids
	 * @param GridsPre
	 * @param GridsLater
	 * @param WhetherPoint
	 * @param cfgfile
	 * @param EParafile
	 * @param DTfile
	 * @return
	 * @return String 返回新建任务名称
	 */
	public static String creatDTOptimizTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean WhetherTopN,
			String TopN, boolean WhetherGrids, String GridsPre,
			String GridsLater, boolean WhetherPoint, String TextRSRP,
			String TextSINR, String TextPDCPUL, String TextPDCPDL,
			String[] cfgfile, String[] EParafile, String[] DTfile) {
		// 打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openAutoDTData(driver);
		TaskReportPage.CreateTaskClick(driver);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataPage.DTAndOptimiz_ID, true, "DT分析优化");
		if (WhetherTopN) {
			CommonJQ.click(driver, AutoDTDataPage.WhetherTopN_ID, true,
					"是否TopN案例");
			CommonJQ.value(driver, AutoDTDataPage.TopN_ID, TopN, "TopN输入框");
		}
		if (WhetherGrids) {
			CommonJQ.click(driver, AutoDTDataPage.WhetherGrids_ID, true, "是否栅格");
			CommonJQ.value(driver, AutoDTDataPage.GridsPre_ID, GridsPre, true,
					"是否栅格Pre");
			CommonJQ.value(driver, AutoDTDataPage.GridsLater_ID, GridsLater,
					true, "是否栅格Later");
		}
		if (WhetherPoint) {
			CommonJQ.click(driver, AutoDTDataPage.WhetherPoint_ID, true,
					"是否输出采集点信息");
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutoDTDataTask.setPara(driver, TextRSRP, TextSINR, TextPDCPUL,
				TextPDCPDL);
		AutoDTDataTask.chooseFile(driver, defaultWindowID, cfgfile, EParafile,
				DTfile);
		String Name = AutoDTDataPage.setTaskName(driver, taskName);
		AutoDTDataPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * <b>Description:</b>专题优化
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param cfgfile
	 * @param EParafile
	 * @param DTfile
	 * @return
	 * @return String 返回新建任务名称
	 */
	public static String creatThemeOptimizTask(WebDriver driver,
			String defaultWindowID, String taskName, String Theme,
			String[] cfgfile, String[] EParafile, String[] DTfile,
			String Mod3Para, String OverCoverPara, String GuoCoverPara,
			String ZoomPara) {
		// 打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openAutoDTData(driver);
		TaskReportPage.CreateTaskClick(driver);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataPage.ThemeOptimiz_ID, true,
				"专题选择:专题优化");
		CommonJQ.click(driver, AutoDTDataPage.SelectedOptimiz_ID, true);
		CommonJQ.click(driver, AutoDTDataPage.AllSelect_ID, true, "专题选择:全选");
		String[] ThemeType = Theme.split(",");
		for (int i = 0; i < ThemeType.length; i++) {
			if ("全选".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.AllSelect_ID, true,
						"专题选择:全选");
			} else if ("黑点专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.BlackPoint_ID, true,
						"专题选择:黑点专题");
			} else if ("过覆盖专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.GuolopFG_ID, true,
						"专题选择:黑点专题");
			} else if ("Mod3干扰专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.Mod3_ID, true,
						"专题选择:Mod3干扰专题");
			} else if ("天线接反专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.AntennaInterfer_ID, true,
						"专题选择:天线接反专题");
			} else if ("邻区错配漏配专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.NbrLoss_ID, true,
						"专题选择:邻区错配漏配专题");
			} else if ("重叠覆盖专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.LopFG_ID, true,
						"专题选择:重叠覆盖专题");
			} else if ("小区经纬度异常专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.GPSAbnormal_ID, true,
						"专题选择:小区经纬度异常专题");
			} else if ("天线方位角异常专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.AntennaAbnormal_ID, true,
						"专题选择:天线方位角异常专题 ");
			} else if ("小区自干扰专题".equalsIgnoreCase(ThemeType[i])) {
				CommonJQ.click(driver, AutoDTDataPage.ZoomDisturb_ID, true,
						"专题选择:小区自干扰专题");
			} else {
				Assert.fail("专题选择错误，请查看是否存在，专题：" + ThemeType[i]);
			}
		}
		CommonJQ.click(driver, AutoDTDataPage.SelectedOptimiz_ID, true);
		if (ThemeType.length != 0) {
			String Evalue = LanguageUtil
					.translate("A total of 9 topic(s)，You have selected")
					+ ThemeType.length
					+ " "
					+ LanguageUtil.translate("topic(s)");
			if ("全选".equals(ThemeType[0])) {
				Evalue = LanguageUtil
						.translate("A total of 9 topic(s)，You have selected")
						+ 9 + " " + LanguageUtil.translate("topic(s)");
			}
			String Avalue = CommonJQ.getValue(driver,
					AutoDTDataPage.SelectedOptimiz_ID);
			Assert.assertTrue("Avalue:" + Avalue + ",Evalue:" + Evalue,
					StringUtils.equals(Evalue, Avalue));
		}
		CommonJQ.click(driver, AutoDTDataPage.ThemeOptimiz_ID, true,
				"专题选择:专题优化");
		SwitchDriver.switchDriverToSEQ(driver);

		AutoDTDataTask.setThemePara(driver, Mod3Para, OverCoverPara,
				GuoCoverPara, ZoomPara);
		
		AutoDTDataTask.chooseFile(driver, defaultWindowID, cfgfile, EParafile,
				DTfile);
		String Name = AutoDTDataPage.setTaskName(driver, taskName);
		AutoDTDataPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * <b>Description:</b>对比报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param WhetherGrids
	 * @param GridsPre
	 * @param GridsLater
	 * @param cfgfile
	 * @param EParafile
	 * @param DTfile
	 * @param cfgfile2
	 * @param EParafile2
	 * @param DTfile2
	 * @return
	 * @return String 返回新建任务名称
	 */
	public static String creatCompareReportTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean WhetherGrids,
			String GridsPre, String GridsLater, String TextRSRP,
			String TextSINR, String TextPDCPUL, String TextPDCPDL,
			String[] cfgfile, String[] EParafile, String[] DTfile,
			String[] cfgfile2, String[] EParafile2, String[] DTfile2) {
		// 打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openAutoDTData(driver);
		TaskReportPage.CreateTaskClick(driver);		
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataPage.CompareReport_ID, true);
		if (WhetherGrids) {
			CommonJQ.click(driver, AutoDTDataPage.WhetherGrids_ID, true);
			CommonJQ.value(driver, AutoDTDataPage.GridsPre_ID, GridsPre, true);
			CommonJQ.value(driver, AutoDTDataPage.GridsLater_ID, GridsLater, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutoDTDataTask.setPara(driver, TextRSRP, TextSINR, TextPDCPUL,
				TextPDCPDL);
		AutoDTDataTask.chooseFile(driver, defaultWindowID, cfgfile, EParafile,
				DTfile);
		AutoDTDataTask.chooseFile2(driver, defaultWindowID, cfgfile2,
				EParafile2, DTfile2);
		String Name = AutoDTDataPage.setTaskName(driver, taskName);
		AutoDTDataPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * <b>Description:</b>指标统计
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param StartTime
	 * @param EndTime
	 * @return
	 * @return String 返回新建任务名称
	 */
	public static String creatCounterStatisticsTask(WebDriver driver,
			String defaultWindowID, String taskName, String StartTime,
			String EndTime) {
		NetworkAnalysisCenterTask.openAutoDTData(driver);
		System.out.println(ZIP.NowTime()
				+ "Start creat counterStatistics task.");
		TaskReportPage.CreateTaskClick(driver);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataPage.CounterStatistics_ID, true);
		CommonJQ.click(driver, AutoDTDataPage.ClusterOptimiz_ID, true);
		if (StringUtils.isNotBlank(StartTime)) {
			CommonJQ.value(driver, AutoDTDataPage.StartTime_ID, StartTime, true);
		}
		if (StringUtils.isNotBlank(EndTime)) {
			CommonJQ.value(driver, AutoDTDataPage.EndTime_ID, EndTime, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		String Name = AutoDTDataPage.setTaskName(driver, taskName);
		AutoDTDataPage.commitBtnClick(driver);
		System.out.println(ZIP.NowTime() + "Creat counterStatistics task over");
		return Name;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param ErrType
	 * @return void
	 */
	public static void DT_ErrorMsg(WebDriver driver, String ErrType) {

		String ExTopNErrMsg = LanguageUtil.translate("Invalid value.");
		String ExRasterErrMsg = LanguageUtil
				.translate("Input can not be empty");
		String ExCfgErrMsg = LanguageUtil
				.translate("Configuration data cannot be empty");
		String ExEParaErrMsg = LanguageUtil
				.translate("Engineering parameters cannot be empty");
		String ExDTErrMsg = LanguageUtil.translate("DT data cannot be empty");
		String ExSpecialErrMsg = LanguageUtil.translate("Select topic(s)");
		String ExStartTimeErrMsg = LanguageUtil
				.translate("Start time cannot be empty");
		String ExEndTimeErrMsg = LanguageUtil
				.translate("End time cannot be empty");

		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("TopN".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoDTDataPage.TopN_Msg_ID,
					"");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExTopNErrMsg, AcErrMsg.equals(AcErrMsg));
		} else if ("Raster".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataPage.Raster_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExRasterErrMsg, AcErrMsg.equals(AcErrMsg));
		} else if ("Cfg".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoDTDataPage.Cfg_Msg_ID,
					"");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExCfgErrMsg, AcErrMsg.equals(ExCfgErrMsg));
		} else if ("EPara".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataPage.EPara_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExEParaErrMsg, AcErrMsg.equals(ExEParaErrMsg));
		} else if ("DT".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoDTDataPage.DT_Msg_ID,
					"");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExDTErrMsg, AcErrMsg.equals(ExDTErrMsg));
		} else if ("Special".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataPage.Special_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExSpecialErrMsg, AcErrMsg.equals(ExSpecialErrMsg));
		} else if ("StartTime".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataPage.StartTime_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExStartTimeErrMsg, AcErrMsg.equals(ExStartTimeErrMsg));
		} else if ("EndTime".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataPage.EndTime_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExEndTimeErrMsg, AcErrMsg.equals(ExEndTimeErrMsg));
		} else {
			Assert.fail("ErrType is err,ErrType:" + ErrType);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>选择DT相关数据
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param cfgfile
	 *            配置
	 * @param EParafile
	 *            工参
	 * @param DTfile
	 *            路测
	 * @return void
	 */
	private static void chooseFile(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] EParafile, String[] DTfile) {
		if (cfgfile != null) {
			if (cfgfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfile,
						AutoDTDataPage.Cfg_ID, defaultWindowID);
			}
		}
		if (EParafile != null) {
			if (EParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EParafile,
						AutoDTDataPage.EPara_ID, defaultWindowID);
			}
		}
		if (DTfile != null) {
			if (DTfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTfile,
						AutoDTDataPage.DT_ID, defaultWindowID);
			}
		}

	}

	private static void chooseFile2(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] EParafile, String[] DTfile) {
		if (cfgfile != null) {
			if (cfgfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfile,
						AutoDTDataPage.Cfg2_ID, defaultWindowID);
			}
		}
		if (EParafile != null) {
			if (EParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EParafile,
						AutoDTDataPage.EPara2_ID, defaultWindowID);
			}
		}
		if (DTfile != null) {
			if (DTfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTfile,
						AutoDTDataPage.DT2_ID, defaultWindowID);
			}
		}

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
		String reportName = downLoad_report(driver, defaultWindowID, taskname);
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome);
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
	public static String downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#showDownLoadDIV a", true);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, taskname);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}

	/**
	 * 
	 * @param driver
	 * @param TextRSRP
	 *            :弱覆盖判定输入框"
	 * @param TextSINR
	 *            :SINR 质差判定输入框
	 * @param TextPDCPUL
	 *            :PDCP 上行判定输入框
	 * @param TextPDCPDL
	 *            ：PDCP 下行判定输入框
	 */
	private static void setPara(WebDriver driver, String TextRSRP,
			String TextSINR, String TextPDCPUL, String TextPDCPDL) {

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataPage.BtnPara, true, "参数设置按钮");
		// 弱覆盖判定
		if (TextRSRP != null) {
			String[] RSRP = TextRSRP.split(",");
			for (int i = 0; i < RSRP.length; i++) {
				if (i == 0) {
					CommonJQ.value(driver,
							"input[name=\"parameterRSRP.procCase\"]", RSRP[i],
							"弱覆盖判定:RSRP输入框");
				}
				if (i == 1) {
					CommonJQ.value(driver,
							"input[name=\"parameterRSRP.procWidth\"]", RSRP[i],
							"弱覆盖判定:采样点比例Width输入框");
				}
				if (i == 2) {
					CommonJQ.value(driver,
							"input[name=\"parameterRSRP.setCase\"]", RSRP[i],
							"弱覆盖判定:采样点比例Case输入框");
				}
			}
		}

		// SINR 质差判定
		if (TextSINR != null) {
			String[] SINR = TextSINR.split(",");
			for (int i = 0; i < SINR.length; i++) {
				if (i == 0) {
					CommonJQ.value(driver,
							"input[name=\"parameterSINR.procCase\"]", SINR[i],
							"SINR 质差判定:SINR1输入框");
				}
				if (i == 1) {
					CommonJQ.value(driver, "input[name=\"procCaseSINRRSRP\"]",
							SINR[i], "SINR 质差判定:SINR2输入框");
				}
				if (i == 2) {
					CommonJQ.value(driver,
							"input[name=\"parameterSINR.procWidth\"]", SINR[i],
							"SINR 质差判定:采样点比例Width输入框");
				}
				if (i == 3) {
					CommonJQ.value(driver,
							"input[name=\"parameterSINR.setCase\"]", SINR[i],
							"SINR 质差判定:采样点比例Case输入框");
				}
			}
		}

		// PDCP 上行判定
		if (TextPDCPUL != null) {
			String[] PDCPUL = TextPDCPUL.split(",");
			for (int i = 0; i < PDCPUL.length; i++) {
				if (i == 0) {
					CommonJQ.value(driver,
							"input[name=\"parameterPDCPUL.procCase\"]",
							PDCPUL[i], "PDCP 上行判定:PDCPUL1输入框");
				}
				if (i == 1) {
					CommonJQ.value(driver, "input[name=\"ulSINR\"]", PDCPUL[i],
							"PDCP 上行判定定:PDCPUL2输入框");
				}
				if (i == 2) {
					CommonJQ.value(driver, "input[name=\"ulRSRP\"]", PDCPUL[i],
							"PDCP 上行判定:PDCPUL3输入框");
				}
				if (i == 3) {
					CommonJQ.value(driver,
							"input[name=\"parameterPDCPUL.procWidth\"]",
							PDCPUL[i], "PDCP 上行判定:采样点比例Width输入框");
				}
				if (i == 4) {
					CommonJQ.value(driver,
							"input[name=\"parameterPDCPUL.setCase\"]",
							PDCPUL[i], "PDCP 上行判定:采样点比例Case输入框");
				}
			}
		}

		// PDCP 下行判定
		if (TextPDCPDL != null) {
			String[] PDCPDL = TextPDCPDL.split(",");
			for (int i = 0; i < PDCPDL.length; i++) {
				if (i == 0) {
					CommonJQ.value(driver,
							"input[name=\"parameterPDCPDL.procCase\"]",
							PDCPDL[i], "PDCP 下行判定:PDCPDL1输入框");
				}
				if (i == 1) {
					CommonJQ.value(driver, "input[name=\"dlSINR\"]", PDCPDL[i],
							"PDCP 下行判定:PDCPDL2输入框");
				}
				if (i == 2) {
					CommonJQ.value(driver, "input[name=\"dlRSRP\"]", PDCPDL[i],
							"PDCP 下行判定:PDCPDL3输入框");
				}
				if (i == 3) {
					CommonJQ.value(driver,
							"input[name=\"parameterPDCPDL.procWidth\"]",
							PDCPDL[i], "PDCP 下行判定:采样点比例Width输入框");
				}
				if (i == 4) {
					CommonJQ.value(driver,
							"input[name=\"parameterPDCPDL.setCase\"]",
							PDCPDL[i], "PDCP 下行判定:采样点比例Case输入框");
				}
			}
		}
		CommonJQ.click(driver, AutoDTDataPage.BtnParaOK, true, "参数设置->确定按钮");
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 专题优化参数设置
	 * 
	 * @param driver
	 * @param Mod3Para
	 *            :Mod3干扰专题-参数
	 * @param OverCoverPara
	 *            :重叠覆盖专题-参数
	 * @param GuoCoverPara
	 *            :过覆盖专题-参数
	 * @param ZoomPara
	 *            :小区自干扰专题-参数
	 */
	private static void setThemePara(WebDriver driver, String Mod3Para,
			String OverCoverPara, String GuoCoverPara, String ZoomPara) {
		AutoDTDataPage.BtnThemeParaClick(driver);
		AutoDTDataPage.setMod3Para(driver, Mod3Para);
		AutoDTDataPage.setOverCover(driver, OverCoverPara);
		AutoDTDataPage.setGuoCover(driver, GuoCoverPara);
		AutoDTDataPage.setZoom(driver, ZoomPara);
		AutoDTDataPage.BtnThemeParaOKClick(driver);
	}
	
	public static void GetAppName(WebDriver driver ) {
		TaskReportTask.GetAppName(driver, LanguageUtil.translate("DT数据自动分析", "Automatic Analysis of DT Data"));
	}
}
