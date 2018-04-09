package cloud_plugin.task.network_performance_analysis_center.network_planning.rank_up_plan;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.huawei.hutaf.webtest.htmlaw.BasicAction;

import cloud_plugin.page.network_performance_analysis_center.network_planning.rank_up_plan.RankUpPlanPage;
import cloud_plugin.page.network_performance_analysis_center.network_planning.traffic_forecast.TrafficForecastPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileCompare;
import common.util.FileHandle;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class RankUpPlan {

	private static String FilePath = ConstUrl.getProjectHomePath() + "\\Data\\baseline\\L_Rankupplan\\CommonFile\\模板";

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
	public static String creatLTEBenchmarkTask(WebDriver driver, String defaultWindowID, String taskName,
			boolean cboxpjm, boolean cboxbjm, boolean cboxsigtype, boolean cboxdttype, boolean cboxhttype,
			String[] UELogFDD, String[] CDRFDD, String[] sig, String[] para, String[] yuanperf, String csvperf,
			String[] UELogFDDUMTS, String[] CDRFDDUMTS, boolean scene, String ChooseYunyingshang, String UelogCdrFile,
			boolean Download, String[] sceneLeft, String[] sceneRight, String[] sceneHeBing, String Delete,
			String ChooseYunyingshang1, String huawuyinzivalue, String zengzhangfile, boolean TemplateDownload,
			String[] busytime) {
		NetworkAnalysisCenterTask.openLTERankUpPlan(driver);
		// ***点击创建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// ***任务名称输入
		taskName = RankUpPlanPage.setTaskName(driver, taskName);
		// ***任务类型及任务子项的设置
		RankUpPlan.ChooseTaskType(driver, cboxpjm, cboxbjm, cboxsigtype, cboxdttype, cboxhttype);

		// ***分析数据选择
		ChooseData(driver, UELogFDD, CDRFDD, sig, para, yuanperf, csvperf, UELogFDDUMTS, CDRFDDUMTS, defaultWindowID);
		// ***参数设置
		RankUpPlan.ParameterSet(driver, scene, ChooseYunyingshang, UelogCdrFile, Download, sceneLeft, sceneRight,
				sceneHeBing, Delete, ChooseYunyingshang1, huawuyinzivalue, zengzhangfile, TemplateDownload, busytime);
		RankUpPlanPage.BtnOkSet(driver);
		RankUpPlanPage.commitBtnClick(driver);
		return taskName;
	}

	public static void ChooseTaskType(WebDriver driver, boolean cboxpjm, boolean cboxbjm, boolean cboxsigtype,
			boolean cboxdttype, boolean cboxhttype) {
		// 任务类型——基于P3排名提升的Benchmark建模
		if (cboxpjm) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, RankUpPlanPage.CBoxpJm, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		// Benchmark建模
		if (cboxbjm) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, RankUpPlanPage.CBoxbjm, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		// SIG数据预处理
		if (cboxsigtype) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, RankUpPlanPage.CBoxsigType, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		// DT数据预处理
		if (cboxdttype) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, RankUpPlanPage.CBoxdtType, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
		// 话统数据预处理
		if (cboxhttype) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, RankUpPlanPage.CBoxhtType, true);
			SwitchDriver.switchDriverToSEQ(driver);
		}
	}

	public static void ChooseData(WebDriver driver, String[] UELogFDD, String[] CDRFDD, String[] sig, String[] para,
			String[] yuanperf, String csvperf, String[] UELogFDDUMTS, String[] CDRFDDUMTS, String defaultWindowID) {
		if (UELogFDD != null) {
			if (UELogFDD.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, UELogFDD, RankUpPlanPage.BtnUELogFDD, defaultWindowID);
			}
		}
		if (CDRFDD != null) {
			if (CDRFDD.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, CDRFDD, RankUpPlanPage.BtnCDRFDD, defaultWindowID);
			}
		}
		if (sig != null) {
			if (sig.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, sig, RankUpPlanPage.BtnSIG, defaultWindowID);
			}
		}
		if (para != null) {
			if (para.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, para, RankUpPlanPage.BtnPara, defaultWindowID);
			}
		}
		if (yuanperf != null) {
			if (yuanperf.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, yuanperf,
						"$('iframe[name=main]').contents().find('#perfData').click()", defaultWindowID);
			}
		}
		if (csvperf != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			CommonJQ.click(driver, RankUpPlanPage.BtnCsvPerf, true);
			CommonFile.ChooseOneFile(csvperf);
			CommonJQ.click(driver, RankUpPlanPage.ChooseCSVBtnOk, true);
			SwitchDriver.switchDriverToSEQ(driver);

		}
		if (UELogFDDUMTS != null) {
			if (UELogFDDUMTS.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, UELogFDDUMTS, RankUpPlanPage.BtnELogFDDUMTS, defaultWindowID);
			}
		}
		if (CDRFDDUMTS != null) {
			if (CDRFDDUMTS.length != 0) {
				GetDataByTypeTask.chooseFolder(driver, CDRFDDUMTS, RankUpPlanPage.BtnCDRFDDUMTS, defaultWindowID);
			}
		}
		BasicAction.sleep(3);

	}

	public static void ParameterSet(WebDriver driver, boolean scene, String ChooseYunyingshang, String UelogCdrFile,
			boolean Download, String[] sceneLeft, String[] sceneRight, String[] sceneHeBing, String Delete,
			String ChooseYunyingshang1, String huawuyinzivalue, String zengzhangfile, boolean TemplateDownload,
			String[] busytime) {

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, RankUpPlanPage.BtnParameterSet, true);
		for (int i = 0; i < 120; i++) {
			boolean flage = CommonJQ.isExisted(driver, RankUpPlanPage.Btnmask, true);
			if (flage) {
				BasicAction.sleep(1);
			} else {
				break;
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
		if (scene || ChooseYunyingshang != null || UelogCdrFile != null || Download || sceneLeft != null
				|| sceneRight != null || sceneHeBing != null || Delete != null)

		{
			if (!CommonJQ.isExisted(driver, RankUpPlanPage.BtnoccasionMerge, true)) {
				System.out.println("检测到存在");
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, RankUpPlanPage.LinkBenchmark, true);
				SwitchDriver.switchDriverToSEQ(driver);
			}

			if (scene) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				BasicAction.sleep(3);
				CommonJQ.click(driver, RankUpPlanPage.SigChoseNoca, true);
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (ChooseYunyingshang != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, RankUpPlanPage.Xalakuang, true);
				CommonJQ.click(driver, "#" + ChooseYunyingshang, true);
				for (int i = 0; i < 120; i++) {
					boolean flage = CommonJQ.isExisted(driver, RankUpPlanPage.Btnmask1, false);
					if (flage) {
						BasicAction.sleep(1);
					} else {
						break;
					}
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (UelogCdrFile != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, RankUpPlanPage.BtnfieldMappingFileData, true);
				CommonFile.ChooseOneFile(UelogCdrFile);
				CommonJQ.click(driver, RankUpPlanPage.BtnChooseFile, true);
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (Download) {
				System.out.println("*********************************************");
				downLoad_reportmoban(driver);
			}
			if (sceneLeft != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				if (sceneLeft[0].equalsIgnoreCase("全选")) {
					LOG.info_aw("场景全选");
					CommonJQ.click(driver, RankUpPlanPage.BtnselectedRightAll, true);
				} else {
					for (int i = 0; i < sceneLeft.length; i++) {
						if (!sceneLeft[i].equalsIgnoreCase("")) {
							CommonJQ.click(driver, "li[id=\"" + sceneLeft[i] + "\"]", true);
						}
					}
					CommonJQ.click(driver, RankUpPlanPage.BtnselectedRight, true);
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (sceneRight != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				if (sceneRight[0].equalsIgnoreCase("全选")) {
					LOG.info_aw("场景全选");
					CommonJQ.click(driver, RankUpPlanPage.BtnselectedLeftAll, true);
				} else {
					for (int i = 0; i < sceneRight.length; i++) {
						if (!sceneRight[i].equalsIgnoreCase("")) {
							CommonJQ.click(driver, "li[id=\"selected" + sceneRight[i] + "\"]", true);
						}
					}
					CommonJQ.click(driver, RankUpPlanPage.BtnselectedLeft, true);
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (sceneHeBing != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				if (!sceneHeBing[0].equalsIgnoreCase("")) {
					LOG.info_aw("输入合并场景自定义名称");
					CommonJQ.value(driver, RankUpPlanPage.TextmergeName, sceneHeBing[0]);
				}
				for (int i = 1; i < sceneHeBing.length; i++) {
					if (!sceneHeBing[i].equalsIgnoreCase("")) {
						CommonJQ.click(driver, "li[id=\"selected" + sceneHeBing[i] + "\"]", true);
					}

					CommonJQ.click(driver, RankUpPlanPage.BtnoccasionMerge, true);
					System.out.println("合并后");
				}
				SwitchDriver.switchDriverToSEQ(driver);
			}

			if (Delete != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, "li[id=\"sceneUnit" + Delete + "\"]", true);
				CommonJQ.click(driver, RankUpPlanPage.BtndelMergeO, true);
				SwitchDriver.switchDriverToSEQ(driver);
			}
		}
		if ((ChooseYunyingshang1 != null || huawuyinzivalue != null || zengzhangfile != null || TemplateDownload
				|| busytime != null)) {
			if (!CommonJQ.isExisted(driver, RankUpPlanPage.LabelBusy, true)) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, RankUpPlanPage.LinkBenchmark, true);
				SwitchDriver.switchDriverToSEQ(driver);
			}

			if (ChooseYunyingshang1 != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, RankUpPlanPage.LinkDatayuchuli, true, 1);
				CommonJQ.click(driver, RankUpPlanPage.XalakuangData, true);
				String yunyingshang = ChooseYunyingshang1;
				String js = "$('#operatorOption2').find('#" + yunyingshang + "').get(0).click();";
				CommonJQ.excuteJS(driver, js);
				// for(int i=0;i<120;i++)
				// {
				// boolean flage = CommonJQ.isExisted(driver,
				// RankUpPlanPage.Btnmask2,null,1);
				// if(flage)
				// {BasicAction.sleep(1);}
				// else
				// {
				// break;
				// }
				// }

				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (huawuyinzivalue != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.value(driver, RankUpPlanPage.SingChoosehuawuyinzivalue, huawuyinzivalue);
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (zengzhangfile != null) {
				SwitchDriver.switchDriverToFrame(driver, "//iframe");
				CommonJQ.click(driver, RankUpPlanPage.SingChoosezengzhangfile, true);
				CommonJQ.click(driver, RankUpPlanPage.Btnzengzhangfile, true);
				CommonFile.ChooseOneFile(zengzhangfile);
				CommonJQ.click(driver, RankUpPlanPage.BtnChooseFile, true);
				SwitchDriver.switchDriverToSEQ(driver);
			}
			if (TemplateDownload) {
				downLoad_reportZfactor(driver);
			}

			if (busytime != null) {
				if (busytime.length != 0) {
					SwitchDriver.switchDriverToFrame(driver, "//iframe");
					for (int i = 0; i < busytime.length; i++) {
						String[] m = { "1", "2", "3", "4", "5", "6" };
						CommonJQ.value(driver, "#date" + m[i], busytime[i]);
					}
					SwitchDriver.switchDriverToSEQ(driver);
				}

			}
		}

	}

	public static void ExpertHelp(WebDriver driver, String taskname, String HelpType, String HelpText, String Fujian) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if (HelpType != null) {
			for (int i = 0; i < 5; i++) {
				CommonJQ.click(driver, RankUpPlanPage.ListHelp, true);
				CommonJQ.click(driver, RankUpPlanPage.ListHelpType, true, i);
			}
		}
		if (HelpText != null) {
			CommonJQ.value(driver, RankUpPlanPage.TextExpertHelp, HelpText);
		}
		if (Fujian != null) {
			CommonJQ.click(driver, RankUpPlanPage.BtnFujian, true);
			CommonFile.ChooseOneFile(Fujian);
		}
		CommonJQ.click(driver, RankUpPlanPage.BtnExpertHelpCommit, true);
		for (int i = 0; i < 120; i++) {
			boolean flage = CommonJQ.isExisted(driver, RankUpPlanPage.TextResult, true);
			if (flage) {
				BasicAction.sleep(1);
			} else {
				break;
			}
		}
		if (CommonJQ.isExisted(driver, RankUpPlanPage.TextResult)) {
			System.out.println("专家求助提交成功");
		} else {
			System.out.println("专家求助提交失败");
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
	public static void downLoad_report(WebDriver driver, String defaultWindowID, String taskname, String ResultHome) {

		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		int length = CommonJQ.length(driver, "#showDownLoad", true);
		for (int i = 0; i < length; i++) {
			FileHandle.delSubFile(EnvConstant.Path_DownLoad);
			FileHandle.delSubFile(ResultHome + "\\" + i);
			CommonJQ.click(driver, "#showDownLoad", true, i);
			String reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "Benchmark");

			ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName, ResultHome + "\\" + i);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);

	}

	public static void downLoad_reportmoban(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		int length = CommonJQ.length(driver, "a[id=\"taskBean.modelDownload\"]", true);

		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "a[id=\"taskBean.modelDownload\"]", true);
		System.out.println("*********************************************1");
		String reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "template");
		Assert.assertTrue(CommonFile.fileReportMsg(EnvConstant.Path_DownLoad),
				FileCompare.fileCompare(FilePath, EnvConstant.Path_DownLoad, new String[] { "template" }));
		SwitchDriver.switchDriverToSEQ(driver);

	}

	public static void downLoad_reportZfactor(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		int length = CommonJQ.length(driver, "a[id=\"taskBean.modelDownload\"]", true);
		CommonJQ.click(driver, RankUpPlanPage.SingChoosezengzhangfile, true);
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "input[id=\"mouldDownload\"]", true);
		CommonJQ.click(driver, "a[id=\"TemplateDownload\"]", true);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, "Factor");
		Assert.assertTrue(CommonFile.fileReportMsg(EnvConstant.Path_DownLoad),
				FileCompare.fileCompare(FilePath, EnvConstant.Path_DownLoad, new String[] { "Factor" }));
		SwitchDriver.switchDriverToSEQ(driver);

	}

	public static void check_showtext(WebDriver driver, String selector, String descendantSelector, int index,
			String ExpertText)

	{
		String ErrMsgupfile = CommonJQ.text(driver, selector, "", index).trim();
		System.out.println("控件实际显示值为：" + ErrMsgupfile);
		if (!ErrMsgupfile.contains(ExpertText)) {
			Assert.fail("实际值为:" + ErrMsgupfile + ",期望值为:" + ExpertText);
		}
	}

	// 等待界面某元素加载完毕
	public static void wait_elementshowok(WebDriver driver, String selector, boolean isVisible)

	{
		boolean flage = true;
		for (int i = 0; i < 120; i++) {
			flage = CommonJQ.isExisted(driver, selector, true);
			if (flage) {
				BasicAction.sleep(1);
			} else {
				break;
			}
		}
		if (flage) {
			Assert.fail("页面元素未加载出来:" + selector);
		}

	}

}
