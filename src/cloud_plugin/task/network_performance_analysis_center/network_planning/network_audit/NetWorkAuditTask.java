package cloud_plugin.task.network_performance_analysis_center.network_planning.network_audit;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.network_audit.NetWorkAuditDetailslPage;
import cloud_plugin.page.network_performance_analysis_center.network_planning.network_audit.NetWorkAuditPage;
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

public class NetWorkAuditTask {

	/**
	 * LTE网络评估任务
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param ExcelFlage	
	 * @param TopN
	 * @param FailedCellsRati
	 * @param RSRPThreshold
	 * @param SceneType
	 * @param checkType
	 * @param BusyType
	 * @param busyTime
	 * @param ThresholdFile
	 * @param cfgfile
	 * @param Pfmfile
	 * @param EParafile
	 * @param MRfile
	 * @param MMLfile
	 * @param Propertyfile
	 * @param SelfDefinefile
	 * @return
	 */
	public static String creatLTENetWorkAuditTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean ExcelFlage,
			String TopN, String FailedCellsRati, String RSRPThreshold,
			String SceneType, String[] checkType, String BusyType,
			String busyTime, String ThresholdFile, String[] cfgfile,
			String[] Pfmfile, String[] EParafile, String[] MRfile,
			String[] MMLfile, String[] Propertyfile, String[] SelfDefinefile) {

		NetWorkAuditTask.reportType(driver, "LTE", ExcelFlage, false);
		NetWorkAuditTask.taskPara(driver, "LTE", TopN, FailedCellsRati,
				RSRPThreshold);
		NetWorkAuditTask.SceneSelection(driver, "LTE", SceneType, checkType);
		NetWorkAuditTask.busySet(driver, BusyType, busyTime);
		NetWorkAuditTask.ThresholdSetting(driver, ThresholdFile);
		NetWorkAuditTask.chooseFileLTE(driver, defaultWindowID, cfgfile,
				Pfmfile, EParafile, MRfile, MMLfile, Propertyfile,
				SelfDefinefile);
		taskName = NetWorkAuditPage.setTaskName(driver, taskName);
		NetWorkAuditPage.commitBtnClick(driver);

		return taskName;
	}

	/**
	 * UMTS网络评估任务
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param ExcelFlage
	 * @param WordFlage
	 * @param TopN
	 * @param checkType
	 * @param BusyType
	 * @param busyTime
	 * @param ThresholdFile
	 * @param cfgfile
	 * @param Pfmfile
	 * @param EParafile
	 * @param CHRfile
	 * @param Alarmfile
	 * @param NodeBCfgfile
	 * @param NodeBPfmfile
	 * @param NodeBAlarmfile
	 * @param NodeBMMLfile
	 * @param NodeBLicensefile
	 * @return
	 */
	public static String creatUMTSNetWorkAuditTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean ExcelFlage,
			boolean WordFlage, String TopN, String[] checkType,
			String BusyType, String busyTime, String ThresholdFile,
			String[] cfgfile, String[] Pfmfile, String[] EParafile,
			String[] CHRfile, String[] Alarmfile, String[] NodeBCfgfile,
			String[] NodeBPfmfile, String[] NodeBAlarmfile,
			String[] NodeBMMLfile, String[] NodeBLicensefile,
			String GrowthFactorFile) {

		NetWorkAuditTask.reportType(driver, "UMTS", ExcelFlage, WordFlage);
		NetWorkAuditTask.taskPara(driver, "UMTS", TopN, null, null);
		NetWorkAuditTask.SceneSelection(driver, "UMTS", null, checkType);
		NetWorkAuditTask.busySet(driver, BusyType, busyTime);
		NetWorkAuditTask.ThresholdSetting(driver, ThresholdFile);
		NetWorkAuditTask.chooseFileUMTS(driver, defaultWindowID, cfgfile,
				Pfmfile, EParafile, CHRfile, Alarmfile, NodeBCfgfile,
				NodeBPfmfile, NodeBAlarmfile, NodeBMMLfile, NodeBLicensefile,
				GrowthFactorFile);
		taskName = NetWorkAuditPage.setTaskName(driver, taskName);
		NetWorkAuditPage.commitBtnClick(driver);

		return taskName;
	}

	/**
	 * LTE任务详情
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 * @param TopN
	 * @param NoPassCell
	 * @param RSRPThreshold
	 * @param Cfg
	 * @param Pfm
	 * @param Para
	 * @param MR
	 * @param MML
	 * @param Property
	 * @param SelfDefine
	 */
	public static void LTEcheckDetails(WebDriver driver,
			String defaultWindowID, String taskName, String TopN,
			String NoPassCell, String RSRPThreshold, String Cfg, String Pfm,
			String Para, String MR, String MML, String Property,
			String SelfDefine) {
		// 打开
		NetworkAnalysisCenterTask.openLTENetWorkAudit(driver);

		String nowWinID = TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		NetWorkAuditTask.checkAysNetData(driver, TopN, NoPassCell,
				RSRPThreshold, Cfg, Pfm, Para, MR, null, MML, Property,
				SelfDefine, null, null, null, null, null, null, null);

		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * UMTS任务详情
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 * @param TopN
	 * @param Cfg
	 * @param Pfm
	 * @param Para
	 * @param Chr
	 * @param Alarm
	 * @param NodebConf
	 * @param NodebPfm
	 * @param NodebAlarm
	 * @param NodebMML
	 * @param NodebLicense
	 * @param Increment
	 */
	public static void UMTScheckDetails(WebDriver driver,
			String defaultWindowID, String taskname, String TopN, String Cfg,
			String Pfm, String Para, String Chr, String Alarm,
			String NodebConf, String NodebPfm, String NodebAlarm,
			String NodebMML, String NodebLicense, String Increment) {
		// 打开
		NetworkAnalysisCenterTask.openUMTSNetWorkAudit(driver);

		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		NetWorkAuditTask.checkAysNetData(driver, TopN, null, null, Cfg, Pfm,
				Para, null, Chr, null, null, null, Alarm, NodebConf, NodebPfm,
				NodebAlarm, NodebMML, NodebLicense, Increment);

		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param NetType
	 * @param ExcelFlage
	 * @param WordFlage
	 * @return void
	 */
	private static void reportType(WebDriver driver, String NetType,
			boolean ExcelFlage, boolean WordFlage) {

		if ("LTE".equalsIgnoreCase(NetType)) {
			// 打开 网络评估任务
			NetworkAnalysisCenterTask.openLTENetWorkAudit(driver);
			TaskReportPage.CreateTaskClick(driver);
			// 选中页面
			LoadingPage.Loading(driver);
			// 选中页面中Iframe
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			if (!ExcelFlage) {
				CommonJQ.click(driver, NetWorkAuditPage.ExcelReport_ID, true,
						"EXCEL");
			}
			SwitchDriver.switchDriverToSEQ(driver);
		} else {
			// 打开 网络评估任务
			NetworkAnalysisCenterTask.openUMTSNetWorkAudit(driver);
			TaskReportPage.CreateTaskClick(driver);
			// 选中页面
			LoadingPage.Loading(driver);
			// 选中页面中Iframe
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			if (!ExcelFlage) {
				CommonJQ.click(driver, NetWorkAuditPage.ExcelReport_ID, true,
						"EXCEL");
			}
			if (WordFlage) {
				CommonJQ.click(driver, NetWorkAuditPage.WordReport_ID, true,
						"WORD");
			}
			SwitchDriver.switchDriverToSEQ(driver);
		}

	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param TopN
	 * @param FailedCellsRati
	 * @param RSRPThreshold
	 * @return void
	 */
	private static void taskPara(WebDriver driver, String NetType, String TopN,
			String FailedCellsRati, String RSRPThreshold) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("LTE".equalsIgnoreCase(NetType)) {
			CommonJQ.value(driver, NetWorkAuditPage.TopN_ID, TopN, true,
					"任务参数 ->TOPN");
			CommonJQ.value(driver, NetWorkAuditPage.FailedCellsRati_ID,
					FailedCellsRati, true, "任务参数 ->不达标小区占比(%)");
			CommonJQ.value(driver, NetWorkAuditPage.RSRPThreshold_ID,
					RSRPThreshold, true, "任务参数 ->RSRP有效门限(dBm)");
		} else {
			CommonJQ.value(driver, NetWorkAuditPage.TopN_ID, TopN, true,
					"任务参数 ->TOPN");
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 场景选择 检查项设置
	 * 
	 * @param driver
	 * @param SceneType
	 * @param checkType
	 */
	private static void SceneSelection(WebDriver driver, String NetType,
			String SceneType, String[] checkType) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if ("LTE".equalsIgnoreCase(NetType)) {
			if ("覆盖干扰评估".equalsIgnoreCase(SceneType)) {
				CommonJQ.click(driver, "#coverageSceneId", true, "覆盖干扰评估");
			} else {
				CommonJQ.click(driver, "#otherSceneId", true, "其他");
			}
			CommonJQ.click(driver,
					"$('a[title=\"LTE_NetworkAudit\"]').prev().filter(':visible')");
		} else {
			CommonJQ.click(driver,
					"$('a[title=\"UMTS_NetworkAudit\"]').prev().filter(':visible')");
		}
		for (int i = 0; i < checkType.length; i++) {
			String jselemnet = "$('a[title=\"" + checkType[i]
					+ "\"]').prev().filter(':visible')";
			CommonJQ.click(driver, jselemnet);
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 门限设置
	 * 
	 * @param driver
	 * @param ThresholdFile
	 */
	private static void ThresholdSetting(WebDriver driver, String ThresholdFile) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (ThresholdFile != null) {
			boolean isVisible = CommonJQ.isExisted(driver,
					"#thresholdImportData", true);
			if (isVisible == false) {
				CommonJQ.click(driver,
						".taskRadioLi span[ng-click=\"toggleThreshold()\"]",
						true, "门限设置");
			}
			CommonJQ.click(driver, "#thresholdImportData", true, "导入");
			CommonFile.ChooseOneFile(ThresholdFile);
			CommonJQ.click(driver, NetWorkAuditPage.BtnMessageOK, true, "上传成功");
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 忙时设置
	 * 
	 * @param driver
	 * @param BusyType
	 * @param busyTime
	 */
	private static void busySet(WebDriver driver, String BusyType,
			String busyTime) {

		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, NetWorkAuditPage.BusyHourSet_CLASS, false);
		if ("全天".equalsIgnoreCase(BusyType)) {
			CommonJQ.click(driver, NetWorkAuditPage.WholeDay_ID, true, "全天");
		} else {
			CommonJQ.click(driver, NetWorkAuditPage.DefineTime_ID, true, "指定时段");
			if (busyTime != null) {
				String[] busy = busyTime.split(",");
				for (int i = 0; i < busy.length; i++) {
					CommonJQ.click_ByText(driver, "#table_" + (i + 1) + " td",
							busy[i], false, busy[i]);
				}
			}
		}
		CommonJQ.click(driver, NetWorkAuditPage.TimeOK_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param cfgfile
	 * @param Pfmfile
	 * @param EParafile
	 * @param MRfile
	 * @param MMLfile
	 * @param Propertyfile
	 * @param SelfDefinefile
	 * @return void
	 */
	private static void chooseFileLTE(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] Pfmfile, String[] EParafile,
			String[] MRfile, String[] MMLfile, String[] Propertyfile,
			String[] SelfDefinefile) {

		if (cfgfile != null) {
			if (cfgfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfile,
						NetWorkAuditPage.Cfg_ID, defaultWindowID);
			}
		}

		if (Pfmfile != null) {
			if (Pfmfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, Pfmfile,
						NetWorkAuditPage.Pfm_ID, defaultWindowID);
			}
		}

		if (EParafile != null) {
			if (EParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EParafile,
						NetWorkAuditPage.Para_ID, defaultWindowID);
			}
		}

		if (MRfile != null) {
			if (MRfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, MRfile,
						NetWorkAuditPage.MR_ID, defaultWindowID);
			}
		}

		if (MMLfile != null) {
			if (MMLfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, MMLfile,
						NetWorkAuditPage.MML_ID, defaultWindowID);
			}
		}
		if (Propertyfile != null) {
			if (Propertyfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, Propertyfile,
						NetWorkAuditPage.Property_ID, defaultWindowID);
			}
		}
		if (SelfDefinefile != null) {
			if (SelfDefinefile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, SelfDefinefile,
						NetWorkAuditPage.SelfDefine_ID, defaultWindowID);
			}
		}

	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param cfgfile
	 * @param Pfmfile
	 * @param EParafile
	 * @param CHRfile
	 * @param Alarmfile
	 * @param NodeBCfgfile
	 * @param NodeBPfmfile
	 * @param NodeBAlarmfile
	 * @param NodeBMMLfile
	 * @param NodeBLicensefile
	 * @return void
	 */
	private static void chooseFileUMTS(WebDriver driver,
			String defaultWindowID, String[] cfgfile, String[] Pfmfile,
			String[] EParafile, String[] CHRfile, String[] Alarmfile,
			String[] NodeBCfgfile, String[] NodeBPfmfile,
			String[] NodeBAlarmfile, String[] NodeBMMLfile,
			String[] NodeBLicensefile, String GrowthFactorFile) {
		if (cfgfile != null) {
			if (cfgfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, cfgfile,
						NetWorkAuditPage.Cfg_ID, defaultWindowID);
			}
		}
		if (Pfmfile != null) {
			if (Pfmfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, Pfmfile,
						NetWorkAuditPage.Pfm_ID, defaultWindowID);
			}
		}
		if (EParafile != null) {
			if (EParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, EParafile,
						NetWorkAuditPage.Para_ID, defaultWindowID);
			}
		}
		if (CHRfile != null) {
			if (CHRfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, CHRfile,
						NetWorkAuditPage.CHR_ID, defaultWindowID);
			}
		}
		if (Alarmfile != null) {
			if (Alarmfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, Alarmfile,
						NetWorkAuditPage.Alarm_ID, defaultWindowID);
			}
		}
		if (NodeBCfgfile != null) {
			if (NodeBCfgfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, NodeBCfgfile,
						NetWorkAuditPage.NodebCfg_ID, defaultWindowID);
			}
		}
		if (NodeBPfmfile != null) {
			if (NodeBPfmfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, NodeBPfmfile,
						NetWorkAuditPage.NodebPfm_ID, defaultWindowID);
			}
		}
		if (NodeBAlarmfile != null) {
			if (NodeBAlarmfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, NodeBAlarmfile,
						NetWorkAuditPage.NodebAlarm_ID, defaultWindowID);
			}
		}
		if (NodeBMMLfile != null) {
			if (NodeBMMLfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, NodeBMMLfile,
						NetWorkAuditPage.NodebMML_ID, defaultWindowID);
			}
		}
		if (NodeBLicensefile != null) {
			if (NodeBLicensefile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, NodeBLicensefile,
						NetWorkAuditPage.NodebLicense_ID, defaultWindowID);
			}
		}
		if (GrowthFactorFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, "#incrementData", true, "增长因子数据->文件选择");
			CommonFile.ChooseOneFile(GrowthFactorFile);
			CommonJQ.click(driver, NetWorkAuditPage.BtnMessageOK, true, "上传成功");
			SwitchDriver.switchDriverToSEQ(driver);
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
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			if (reportName[i].endsWith("zip")) {
				ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i],
						EnvConstant.Path_DownLoad);
				String srcPath = FileHandle.maxTimesPath(
						EnvConstant.Path_DownLoad).getAbsolutePath();
				File dir = new File(srcPath);
				File filelist[] = dir.listFiles();
				for (File a : filelist) {

					String path = a.getAbsolutePath();
					if (path.endsWith(".svn")) {
						continue;
					}
					/* 当为目录时，递归调用该方法 */
					if (a.isDirectory()) {
						continue;
					}
					/* 当为文件时，递归调用该方法 */
					if (a.isFile()) {
						if (a.getName().startsWith("~$")) {
							continue;
						} else {
							if (path.endsWith(".zip")) {
								ZIP.depress(path, ResultHome);
							}

						}
					}
				}
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

	/**
	 * 任务详情
	 * 
	 * @param driver
	 * @param SEQAllNet
	 * @param OTTSheet
	 * @param ISPSheet
	 * @param SEQCell
	 * @param SEQGrid
	 * @param Cfg
	 * @param Epara
	 * @param Sig
	 * @param NoEncryVideo
	 * @param EncryVideo
	 * @param eMap
	 * @param OTT
	 */
	private static void checkAysNetData(WebDriver driver, String TopN,
			String NoPassCell, String RSRPThreshold, String Cfg, String Pfm,
			String Para, String MR, String Chr, String MML, String Property,
			String SelfDefine, String Alarm, String NodebConf, String NodebPfm,
			String NodebAlarm, String NodebMML, String NodebLicense,
			String Increment) {

		// 任务参数->TOPN
		if (TopN != null) {
			String ActVal = NetWorkAuditDetailslPage.TopNVal(driver);
			Assert.assertTrue("任务参数->TOPN，实际值：" + ActVal + "预期值：" + TopN,
					TopN.equals(ActVal));
		}
		// 任务参数->不达标小区占比(%)
		if (NoPassCell != null) {
			String ActVal = NetWorkAuditDetailslPage.NoPassCellVal(driver);
			Assert.assertTrue("任务参数->不达标小区占比(%)，实际值：" + ActVal + "预期值："
					+ NoPassCell, NoPassCell.equals(ActVal));
		}
		// 任务参数->RSRP有效门限(dBm)
		if (RSRPThreshold != null) {
			String ActVal = NetWorkAuditDetailslPage.RSRPThresholdVal(driver);
			Assert.assertTrue("任务参数->RSRP有效门限(dBm)，实际值：" + ActVal + "预期值："
					+ RSRPThreshold, RSRPThreshold.equals(ActVal));
		}

		// 配置数据
		if (Cfg != null) {
			String ActVal = NetWorkAuditDetailslPage.CfgVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + Cfg
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("配置数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// 话统数据
		if (Pfm != null) {
			String ActVal = NetWorkAuditDetailslPage.PfmVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + Pfm
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("话统数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// 工参数据
		if (Para != null) {
			String ActVal = NetWorkAuditDetailslPage.ParaVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + Para
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("工参数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// 外部CHR数据
		if (MR != null) {
			String ActVal = NetWorkAuditDetailslPage.MRVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + MR
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("外部CHR数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// CHR数据
		if (Chr != null) {
			String ActVal = NetWorkAuditDetailslPage.ChrVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + Chr
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("CHR数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// MML数据
		if (MML != null) {
			String ActVal = NetWorkAuditDetailslPage.MMLVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + MML
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("MML数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// 特征库数据
		if (Property != null) {
			String ActVal = NetWorkAuditDetailslPage.PropertyVal(driver);
			String Evalue = LanguageUtil.translate("You have selected")
					+ Property + LanguageUtil.translate("file(s)");
			Assert.assertTrue("特征库数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// 自定义数据
		if (SelfDefine != null) {
			String ActVal = NetWorkAuditDetailslPage.SelfDefineVal(driver);
			String Evalue = LanguageUtil.translate("You have selected")
					+ SelfDefine + LanguageUtil.translate("file(s)");
			Assert.assertTrue("自定义数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// 告警数据
		if (Alarm != null) {
			String ActVal = NetWorkAuditDetailslPage.AlarmVal(driver);
			String Evalue = LanguageUtil.translate("You have selected") + Alarm
					+ LanguageUtil.translate("file(s)");
			Assert.assertTrue("告警数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// NodeB配置数据
		if (NodebConf != null) {
			String ActVal = NetWorkAuditDetailslPage.NodebConfVal(driver);
			String Evalue = LanguageUtil.translate("You have selected")
					+ NodebConf + LanguageUtil.translate("file(s)");
			Assert.assertTrue("NodeB配置数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// NodeB 话统数据
		if (NodebPfm != null) {
			String ActVal = NetWorkAuditDetailslPage.NodebPfmVal(driver);
			String Evalue = LanguageUtil.translate("You have selected")
					+ NodebPfm + LanguageUtil.translate("file(s)");
			Assert.assertTrue("NodeB 话统数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// NodeB 告警数据
		if (NodebAlarm != null) {
			String ActVal = NetWorkAuditDetailslPage.NodebAlarmVal(driver);
			String Evalue = LanguageUtil.translate("You have selected")
					+ NodebAlarm + LanguageUtil.translate("file(s)");
			Assert.assertTrue("NodeB 告警数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// NodeB MML数据
		if (NodebMML != null) {
			String ActVal = NetWorkAuditDetailslPage.NodebMMLVal(driver);
			String Evalue = LanguageUtil.translate("You have selected")
					+ NodebMML + LanguageUtil.translate("file(s)");
			Assert.assertTrue("NodeB MML数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// NodeB License数据
		if (NodebLicense != null) {
			String ActVal = NetWorkAuditDetailslPage.NodebLicenseVal(driver);
			String Evalue = LanguageUtil.translate("You have selected")
					+ NodebLicense + LanguageUtil.translate("file(s)");
			Assert.assertTrue(
					"NodeB License数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
		// 增长因子数据
		if (Increment != null) {
			String ActVal = NetWorkAuditDetailslPage.IncrementVal(driver);
			String Evalue = LanguageUtil.translate("You have selected")
					+ Increment + LanguageUtil.translate("file(s)");
			Assert.assertTrue("增长因子数据，实际值：" + ActVal + "预期值：" + Evalue,
					Evalue.equals(ActVal));
		}
	}

	/**
	 * 模板下载
	 * @param driver
	 * @param TempIncrementFlage
	 * @param ResultHome
	 */
	public static void TemplateDownload(WebDriver driver,
			boolean TempIncrementFlage, String ResultHome) {

		NetWorkAuditTask.reportType(driver, "UMTS", true, true);

		FileHandle.delSubFile(ResultHome);
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		FileHandle.makeDirectory(ResultHome);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 增长因子数据->模板下载
		if (TempIncrementFlage) {
			NetWorkAuditPage.BtnTempIncrementClick(driver, ResultHome);
		}
		SwitchDriver.switchDriverToSEQ(driver);

	}

	/**
	 * 网络评估-任务列表界面校验
	 * @param driver
	 */
	public static void GetAppName(WebDriver driver) {
		TaskReportTask.GetAppName(driver,
				LanguageUtil.translate("网络评估", "NetWork Audit"));
	}
}
