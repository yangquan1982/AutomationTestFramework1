package cloud_plugin.task.network_performance_analysis_center.network_planning.content_evaluate;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.content_evaluate.ContentEvaluatePage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class ContentEvaluateTask {

	public static String creatUMTSContentEvaluateTask(WebDriver driver,
			String defaultWindowID, String taskName_Star, String[] cfgFile,
			String[] EparaFile, String[] PfmFile, boolean TcpFlage,
			String TcpPowerOver, String TcpDCHUser, boolean RtwpFlage,
			String RtwpLoad, String RtwpUpUser, boolean CodeFlage, String Code,
			boolean HsuserFlage, String HSDPAUser, String HSDPAThrough,
			String FactorSub, String FactorCsUser, String FactorPsUp,
			String FactorPsDown, String FactorCsSig, String FactorPsSig,
			String FactorHSDPAUser, String FactorFile, boolean cellUserFactorFlage,
			boolean repPsUserDLFlage, String TopN,
			boolean TCPFlage, boolean TopMFlage, String TopM,
			String DataIntegrity) {
		// 打开
		NetworkAnalysisCenterTask.openUMTSContentEvaluate(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		LoadingPage.Loading(driver);
		// 选中任务名称
		String taskName = ContentEvaluatePage
				.setTaskName(driver, taskName_Star);
		ContentEvaluatePage.NextBtnClick(driver);
		// 选择分析数据
		if (cfgFile != null) {
			if (cfgFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								cfgFile,
								"$('iframe[name=main]').contents().find('#umtsconfData').siblings('#select_configFile').click()",
								defaultWindowID);
			}
		}
		if (EparaFile != null) {
			if (EparaFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								EparaFile,
								"$('iframe[name=main]').contents().find('#engineerParaData').siblings('#select_configFile').click()",
								defaultWindowID);
			}
		}
		if (PfmFile != null) {
			if (PfmFile.length != 0) {
				GetDataByTypeTask
						.chooseFolder(
								driver,
								PfmFile,
								"$('iframe[name=main]').contents().find('#umtspfmData').siblings('#select_configFile').click()",
								defaultWindowID);
			}
		}

		ContentEvaluatePage.NextBtnClick(driver);
		// 等效载波数指标
		ContentEvaluateTask.setEquivCarriers(driver, TcpFlage, TcpPowerOver,
				TcpDCHUser, RtwpFlage, RtwpLoad, RtwpUpUser, CodeFlage, Code,
				HsuserFlage, HSDPAUser, HSDPAThrough);
		ContentEvaluateTask.setFactor(driver, FactorSub, FactorCsUser,
				FactorPsUp, FactorPsDown, FactorCsSig, FactorPsSig,
				FactorHSDPAUser, FactorFile,  cellUserFactorFlage,
				 repPsUserDLFlage);
		ContentEvaluateTask.setBusy_Other(driver, TopN, TCPFlage, TopMFlage,
				TopM, DataIntegrity);
		// 提交任务
		ContentEvaluatePage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>等效载波数指标
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param TcpFlage
	 * @param RtwpFlage
	 * @param CodeFlage
	 * @param HsuserFlage
	 * @return void
	 */
	private static void setEquivCarriers(WebDriver driver, boolean TcpFlage,
			String TcpPowerOver, String TcpDCHUser, boolean RtwpFlage,
			String RtwpLoad, String RtwpUpUser, boolean CodeFlage, String Code,
			boolean HsuserFlage, String HSDPAUser, String HSDPAThrough) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, ContentEvaluatePage.CBoxParaTcp, true);
		CommonJQ.click(driver, ContentEvaluatePage.CBoxParaRtwp, true);
		CommonJQ.click(driver, ContentEvaluatePage.CBoxParaCode, true);
		if (TcpFlage) {
			CommonJQ.click(driver, ContentEvaluatePage.CBoxParaTcp, true);
			CommonJQ.value(driver, ContentEvaluatePage.TextTcpPowerOver,
					TcpPowerOver);
			CommonJQ.value(driver, ContentEvaluatePage.TextTcpDCHUser,
					TcpDCHUser);
		}

		if (RtwpFlage) {
			CommonJQ.click(driver, ContentEvaluatePage.CBoxParaRtwp, true);
			CommonJQ.value(driver, ContentEvaluatePage.TextRtwpLoad, RtwpLoad);
			CommonJQ.value(driver, ContentEvaluatePage.TextRtwpUpUser,
					RtwpUpUser);
		}
		if (CodeFlage) {
			CommonJQ.click(driver, ContentEvaluatePage.CBoxParaCode, true);
			CommonJQ.value(driver, ContentEvaluatePage.TextCode, Code);
		}

		if (HsuserFlage) {
			CommonJQ.click(driver, ContentEvaluatePage.CBoxParaHsuser, true);
			CommonJQ.value(driver, ContentEvaluatePage.TextHSDPAUser, HSDPAUser);
			CommonJQ.value(driver, ContentEvaluatePage.TextHSDPAThrough,
					HSDPAThrough);
		}

		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>预测因子
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param FactorSub
	 * @param FactorCsUser
	 * @param FactorPsUp
	 * @param FactorPsDown
	 * @param FactorCsSig
	 * @param FactorPsSig
	 * @param FactorPsUserDL
	 * @param FactorHSDPAUser
	 * @param FactorFile
	 * @return void
	 */
	private static void setFactor(WebDriver driver, String FactorSub,
			String FactorCsUser, String FactorPsUp, String FactorPsDown,
			String FactorCsSig, String FactorPsSig, String FactorHSDPAUser,
			String FactorFile, boolean cellUserFactorFlage,
			boolean repPsUserDLFlage) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, ContentEvaluatePage.TextFactorSub, FactorSub);
		CommonJQ.value(driver, ContentEvaluatePage.TextFactorCsUser,
				FactorCsUser);

		CommonJQ.value(driver, ContentEvaluatePage.TextFactorPsUp, FactorPsUp);
		CommonJQ.value(driver, ContentEvaluatePage.TextFactorPsDown,
				FactorPsDown);

		CommonJQ.value(driver, ContentEvaluatePage.TextFactorCsSig, FactorCsSig);
		CommonJQ.value(driver, ContentEvaluatePage.TextFactorPsSig, FactorPsSig);

		CommonJQ.value(driver, ContentEvaluatePage.TextFactorHSDPAUser,
				FactorHSDPAUser);
		if (FactorFile != null) {
			CommonJQ.click(driver, ContentEvaluatePage.BtnFactorFile, true);
			CommonFile.ChooseOneFile(FactorFile);
		}
		CommonJQ.click(driver, "#cellUserFactor", true);
		CommonJQ.click(driver, "#repPsUserDL", true);
		if (cellUserFactorFlage) {
			CommonJQ.click(driver, "#cellUserFactor", true);
		}
		if (repPsUserDLFlage) {
			CommonJQ.click(driver, "#repPsUserDL", true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>忙时 &&其他设置
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param TopN
	 * @param TCPFlage
	 * @param TopMFlage
	 * @param TopM
	 * @param DataIntegrity
	 * @return void
	 */
	private static void setBusy_Other(WebDriver driver, String TopN,
			boolean TCPFlage, boolean TopMFlage, String TopM,
			String DataIntegrity) {
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.siblingsClick(driver, ContentEvaluatePage.TextTopN, "span");
		CommonJQ.click(driver, ContentEvaluatePage.ListTopN, true,
				Integer.valueOf(TopN));
		if (TCPFlage) {
			CommonJQ.click(driver, ContentEvaluatePage.RBoxTCP, true, 0);
		} else {
			CommonJQ.click(driver, ContentEvaluatePage.RBoxTCP, true, 1);
		}
		if ("0".equals(TopN)) {
			CommonJQ.click(driver, ContentEvaluatePage.BtnCleanAll, true);
			CommonJQ.click(driver, ContentEvaluatePage.BtnSeleAll, true);
		}
		if (TopMFlage) {
			CommonJQ.click(driver, ContentEvaluatePage.CBoxWhetherTopM, true);
		} else {
			CommonJQ.click(driver, ContentEvaluatePage.TextTopM, true);
			 CommonJQ.click(driver, ContentEvaluatePage.ListTopM, true,
			 (Integer.valueOf(TopM) - 1));
		}
		CommonJQ.siblingsClick(driver, ContentEvaluatePage.TextDataIntegrity,
				"span");
		CommonJQ.click(driver, ContentEvaluatePage.ListDataIntegrity, true,
				Integer.valueOf(DataIntegrity));
		SwitchDriver.switchDriverToSEQ(driver);
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
	public static void downLoad_MoveReport(WebDriver driver,
			String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String reportName = downLoad_report( driver, defaultWindowID,  taskname );
		ZIP.depress(EnvConstant.Path_DownLoad + "\\"+reportName, ResultHome);	
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
	public static String downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname ) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#showDownLoad", true,"报告下载按钮");
		String reportName =CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", true);
		reportName  = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,reportName);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
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
		NetworkAnalysisCenterTask.openUMTSContentEvaluate(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		taskName = ContentEvaluatePage.setTaskName(driver, taskName);
		ContentEvaluatePage.cancelBtnClick(driver);
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
		NetworkAnalysisCenterTask.openUMTSContentEvaluate(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		taskName = ContentEvaluatePage.setTaskName(driver, taskName);
		ContentEvaluatePage.cancelTitleClick(driver);
		LoadingPage.Loading(driver, TaskReportPage.CreateTask);
	}
}
