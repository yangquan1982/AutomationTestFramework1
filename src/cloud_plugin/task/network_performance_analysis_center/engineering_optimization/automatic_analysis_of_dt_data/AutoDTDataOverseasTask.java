package cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data.AutoDTDataOverseasPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutoDTDataOverseasTask {
	/**
	 * <b>Description:</b>Probe 任务
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param DTfile
	 * @param LEParafile
	 * @param GEParafile
	 * @param UEParafile
	 * @param Mapfile
	 * @param ExEParafile
	 * @param Ratefile
	 * @return
	 * @return String
	 */
	public static String creatLTEProbeTask(WebDriver driver,
			String defaultWindowID, String taskName, String reportType,
			String[] DTfile, String[] LEParafile, String[] Mapfile,
			String[] ExEParafile, String[] Ratefile) {
		return AutoDTDataOverseasTask.creatProbeTask(driver, defaultWindowID,
				"LTE", taskName, reportType, DTfile, LEParafile, null, null,
				Mapfile, ExEParafile, Ratefile);

	}

	public static String creatGSMProbeTask(WebDriver driver,
			String defaultWindowID, String taskName, String reportType,
			String[] DTfile, String[] LEParafile, String[] GEParafile,
			String[] UEParafile, String[] Mapfile, String[] ExEParafile,
			String[] Ratefile) {
		return AutoDTDataOverseasTask.creatProbeTask(driver, defaultWindowID,
				"GSM", taskName, reportType, DTfile, LEParafile, GEParafile,
				UEParafile, Mapfile, ExEParafile, Ratefile);

	}

	public static String creatUMTSProbeTask(WebDriver driver,
			String defaultWindowID, String taskName, String reportType,
			String[] DTfile, String[] LEParafile, String[] GEParafile,
			String[] UEParafile, String[] Mapfile, String[] ExEParafile,
			String[] Ratefile) {
		return AutoDTDataOverseasTask.creatProbeTask(driver, defaultWindowID,
				"UMTS", taskName, reportType, DTfile, LEParafile, GEParafile,
				UEParafile, Mapfile, ExEParafile, Ratefile);

	}

	/**
	 * <b>Description:</b>Probe 任务
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param DTfile
	 * @param LEParafile
	 * @param GEParafile
	 * @param UEParafile
	 * @param Mapfile
	 * @param ExEParafile
	 * @param Ratefile
	 * @return
	 * @return String
	 */
	public static String creatProbeTask(WebDriver driver,
			String defaultWindowID, String Type, String taskName,
			String reportType, String[] DTfile, String[] LEParafile,
			String[] GEParafile, String[] UEParafile, String[] Mapfile,
			String[] ExEParafile, String[] Ratefile) {
		// 打开 DT数据自动分析任务
		if ("GSM".equalsIgnoreCase(Type)) {
			NetworkAnalysisCenterTask.openGSMAutoDTDataOverseas(driver);
		} else if ("UMTS".equalsIgnoreCase(Type)) {
			NetworkAnalysisCenterTask.openUMTSAutoDTDataOverseas(driver);
		} else {
			NetworkAnalysisCenterTask.openLTEAutoDTDataOverseas(driver);
		}
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		LoadingPage.Loading(driver, "#genServerSpan", "路测数据 ->选择文件");
		CommonJQ.click(driver, AutoDTDataOverseasPage.Probe_CLASS, true);
		CommonJQ.click(driver, AutoDTDataOverseasPage.ProbeReportType_PATH,
				true);
		String jsclick = "$('ul[id=\"mshowundefined\"]').find('li').filter(function(){return $(this).text()=='"
				+ reportType + "'})";
		CommonJQ.click(driver, jsclick, "当前环境无[" + reportType
				+ "]可选择!");
		SwitchDriver.switchDriverToSEQ(driver);
		if(DTfile!=null){
			if (DTfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, DTfile,
						AutoDTDataOverseasPage.DT_ID, defaultWindowID);
			}	
		}

		if(LEParafile!=null){
			if (LEParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, LEParafile,
						AutoDTDataOverseasPage.LEPara_ID, defaultWindowID);
			}
		}

		if(GEParafile!=null){
			if (GEParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, GEParafile,
						AutoDTDataOverseasPage.GEPara_ID, defaultWindowID);
			}	
		}

		if(UEParafile!=null){
			if (UEParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, UEParafile,
						AutoDTDataOverseasPage.UEPara_ID, defaultWindowID);
			}	
		}

		if(Mapfile!=null){
			if (Mapfile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, Mapfile,
						AutoDTDataOverseasPage.MAP_ID, defaultWindowID);
			}
		}

		if(ExEParafile!=null){
			if (ExEParafile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, ExEParafile,
						AutoDTDataOverseasPage.ExEPara_ID, defaultWindowID);
			}	
		}

		if(Ratefile!=null){
			if (Ratefile.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, Ratefile,
						AutoDTDataOverseasPage.RateScreenshot_ID, defaultWindowID);
			}	
		}
		taskName = AutoDTDataOverseasPage.setTaskName(driver, taskName);
		AutoDTDataOverseasPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>三方任务
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param DTType
	 * @param DTfile
	 * @param EParafile
	 * @param Mapfile
	 * @param ExEParafile
	 * @param Ratefile
	 * @return
	 * @return String
	 */
	public static String creatThirdPatryTask(WebDriver driver,
			String defaultWindowID, String DTType, String[] DTfile,
			String[] EParafile, String[] Mapfile, String[] ExEParafile,
			String[] Ratefile) {
		// 打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openLTEAutoDTDataOverseas(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		String taskName = AutoDTDataOverseasPage.setTaskName(driver,
				"ThirdPatry");
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataOverseasPage.ThirdPatry_CLASS, true);
		SwitchDriver.switchDriverToSEQ(driver);
		if (DTfile.length != 0) {
			if ("Tems".equalsIgnoreCase(DTType)) {
				GetDataByTypeTask.chooseFolder(driver, DTfile,
						AutoDTDataOverseasPage.Tems_ID, defaultWindowID);
			} else {
				GetDataByTypeTask.chooseFolder(driver, DTfile,
						AutoDTDataOverseasPage.Nemo_ID, defaultWindowID);
			}
		}
		if (EParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, EParafile,
					AutoDTDataOverseasPage.EPara_ID, defaultWindowID);
		}
		if (Mapfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Mapfile,
					AutoDTDataOverseasPage.MAP_ID, defaultWindowID);
		}
		if (ExEParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, ExEParafile,
					AutoDTDataOverseasPage.ExEPara_ID, defaultWindowID);
		}
		if (Ratefile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Ratefile,
					AutoDTDataOverseasPage.RateScreenshot_ID, defaultWindowID);
		}
		AutoDTDataOverseasPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>明文任务
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param DTfile
	 * @param EParafile
	 * @param Mapfile
	 * @param ExEParafile
	 * @param Ratefile
	 * @return
	 * @return String
	 */
	public static String creatClearTextTask(WebDriver driver,
			String defaultWindowID, String[] DTfile, String[] EParafile,
			String[] Mapfile, String[] ExEParafile, String[] Ratefile) {
		// 打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openLTEAutoDTDataOverseas(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		String taskName = AutoDTDataOverseasPage.setTaskName(driver,
				"ClearText");
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataOverseasPage.ClearText_CLASS, true);
		SwitchDriver.switchDriverToSEQ(driver);
		if (DTfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, DTfile,
					AutoDTDataOverseasPage.ClearTextDT_ID, defaultWindowID);
		}
		if (EParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, EParafile,
					AutoDTDataOverseasPage.ClearTextEPara_ID, defaultWindowID);
		}
		if (Mapfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Mapfile,
					AutoDTDataOverseasPage.MAP_ID, defaultWindowID);
		}
		if (ExEParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, ExEParafile,
					AutoDTDataOverseasPage.ExEPara_ID, defaultWindowID);
		}
		if (Ratefile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Ratefile,
					AutoDTDataOverseasPage.RateScreenshot_ID, defaultWindowID);
		}
		AutoDTDataOverseasPage.commitBtnClick(driver);
		return taskName;
	}

	/**
	 * <b>Description:</b>Romes任务
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param DTfile
	 * @param EParafile
	 * @param Mapfile
	 * @param ExEParafile
	 * @param Ratefile
	 * @return
	 * @return String
	 */
	public static String creatRomesTask(WebDriver driver,
			String defaultWindowID, String[] DTfile, String[] EParafile,
			String[] Mapfile, String[] ExEParafile, String[] Ratefile) {
		// 打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openLTEAutoDTDataOverseas(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		String taskName = AutoDTDataOverseasPage.setTaskName(driver, "Romes");
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoDTDataOverseasPage.Romes_CLASS, true);
		SwitchDriver.switchDriverToSEQ(driver);
		if (DTfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, DTfile,
					AutoDTDataOverseasPage.RomesDT_ID, defaultWindowID);
		}
		if (EParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, EParafile,
					AutoDTDataOverseasPage.RomesEPara_ID, defaultWindowID);
		}
		if (Mapfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Mapfile,
					AutoDTDataOverseasPage.MAP_ID, defaultWindowID);
		}
		if (ExEParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, ExEParafile,
					AutoDTDataOverseasPage.ExEPara_ID, defaultWindowID);
		}
		if (Ratefile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, Ratefile,
					AutoDTDataOverseasPage.RateScreenshot_ID, defaultWindowID);
		}
		AutoDTDataOverseasPage.commitBtnClick(driver);
		return taskName;
	}

	public static void DT_ErrorMsg(WebDriver driver, String ErrType) {

		String LExEParaErrMsg = LanguageUtil
				.translate("LTE engineering parameters cannot be empty");
		String GExEParaErrMsg = LanguageUtil
				.translate("GSM engineering parameters cannot be empty");
		String UExEParaErrMsg = LanguageUtil
				.translate("UMTS engineering parameters cannot be empty");
		String ExDTErrMsg = LanguageUtil.translate("DT data cannot be empty");

		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("LEPara".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataOverseasPage.Msg_CLASS, "", 0);
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ LExEParaErrMsg, AcErrMsg.equals(LExEParaErrMsg));
		} else if ("DT".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataOverseasPage.Msg_CLASS, "", 0);
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ ExDTErrMsg, AcErrMsg.equals(ExDTErrMsg));
		} else if ("GEPara".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataOverseasPage.Msg_CLASS, "", 0);
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ GExEParaErrMsg, AcErrMsg.equals(GExEParaErrMsg));
		} else if ("UEPara".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver,
					AutoDTDataOverseasPage.Msg_CLASS, "", 0);
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:"
					+ UExEParaErrMsg, AcErrMsg.equals(UExEParaErrMsg));
		} else {
			Assert.fail("ErrType is err,ErrType:" + ErrType);
		}
		SwitchDriver.switchDriverToSEQ(driver);
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
