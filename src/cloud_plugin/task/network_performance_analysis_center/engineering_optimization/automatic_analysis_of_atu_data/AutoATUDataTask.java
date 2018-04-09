package cloud_plugin.task.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_atu_data;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_atu_data.AutoATUDataPage;
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

public class AutoATUDataTask {
	/**
	 * <b>Description:</b>ATU分析优化
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
	 * @return
	 * @return String
	 */
	public static String creatATUOptimizTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean WhetherGrids,
			String GridsPre, String GridsLater, String[] cfgfile,
			String[] EParafile, String[] DTfile) {
		// 打开 ATU数据自动分析任务
		NetworkAnalysisCenterTask.openAutoATUData(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoATUDataPage.ATUTask_ID, true);

		if (WhetherGrids) {
			CommonJQ.click(driver, AutoATUDataPage.Grids_ID, true);
			CommonJQ.value(driver, AutoATUDataPage.GridsPre_ID, GridsPre, true);
			CommonJQ.value(driver, AutoATUDataPage.GridsLater_ID, GridsLater, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutoATUDataTask.chooseFile(driver, defaultWindowID, cfgfile, EParafile,
				DTfile);
		String Name = AutoATUDataPage.setTaskName(driver, taskName);
		AutoATUDataPage.setReportName(driver,
				LanguageUtil.translate("Optimization analysis report"));
		AutoATUDataPage.commitBtnClick(driver);
		return Name;
	}

	public static String creatATUOptimizTask(WebDriver driver,
			String defaultWindowID, String taskName, boolean WhetherGrids,
			String GridsPre, String GridsLater, String[] cfgfile,
			String[] EParafile, String[] DTfile, String[] chapter,
			String[] Indicator, String OperRSRP, String TextRSRP,
			String OperSINR, String TextSINR, String OperAPPUL,
			String TextAPPUL, String OperAPPDL, String TextAPPDL) {
		// 打开 ATU数据自动分析任务
		NetworkAnalysisCenterTask.openAutoATUData(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoATUDataPage.ATUTask_ID, true);

		if (WhetherGrids) {
			CommonJQ.click(driver, AutoATUDataPage.Grids_ID, true);
			CommonJQ.value(driver, AutoATUDataPage.GridsPre_ID, GridsPre,true);
			CommonJQ.value(driver, AutoATUDataPage.GridsLater_ID, GridsLater,true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutoATUDataTask.chooseFile(driver, defaultWindowID, cfgfile, EParafile,
				DTfile);
		String Name = AutoATUDataPage.setTaskName(driver, taskName);
		AutoATUDataPage.setReportName(driver,
				LanguageUtil.translate("Optimization analysis report"));
		AutoATUDataTask.setReportPara(driver, chapter, Indicator);
		AutoATUDataTask.setPara(driver, OperRSRP, TextRSRP, OperSINR, TextSINR,
				OperAPPUL, TextAPPUL, OperAPPDL, TextAPPDL);
		AutoATUDataPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param cfgfile
	 * @param EParafile
	 * @param DTfile
	 * @return
	 * @return String
	 */
	public static String creatThemeOptimizTask(WebDriver driver,
			String defaultWindowID, String taskName, String[] cfgfile,
			String[] EParafile, String[] DTfile) {
		// 打开 ATU数据自动分析任务
		NetworkAnalysisCenterTask.openAutoATUData(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoATUDataPage.ThemeTask_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
		AutoATUDataTask.chooseFile(driver, defaultWindowID, cfgfile, EParafile,
				DTfile);
		String Name = AutoATUDataPage.setTaskName(driver, taskName);
		AutoATUDataPage.setReportName(driver,
				LanguageUtil.translate("Optimization analysis report"));
		AutoATUDataPage.commitBtnClick(driver);
		return Name;
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 * @param compareType
	 * @param cfgfile
	 * @param EParafile
	 * @param DTfile
	 * @param cfgfile2
	 * @param EParafile2
	 * @param DTfile2
	 * @return
	 * @return String
	 */
	public static String creatCompareReportTask(WebDriver driver,
			String defaultWindowID, String taskName, String compareType,
			String[] cfgfile, String[] EParafile, String[] DTfile,
			String[] cfgfile2, String[] EParafile2, String[] DTfile2) {

		// 打开 ATU数据自动分析任务
		NetworkAnalysisCenterTask.openAutoATUData(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoATUDataPage.CompareTask_ID, true);
		if ("ATU与PA对比报告".equals(compareType)) {
			CommonJQ.click(driver, AutoATUDataPage.ATUAndPA_ID, true);
		} else if ("ATU与ATU对比报告".equals(compareType)) {
			CommonJQ.click(driver, AutoATUDataPage.ATUAndATU_ID, true);
		} else {
			Assert.fail("对比类型,输入错误，compareType：" + compareType);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutoATUDataTask.chooseFile(driver, defaultWindowID, cfgfile, EParafile,
				DTfile);
		AutoATUDataTask.chooseFile2(driver, defaultWindowID, cfgfile2,
				EParafile2, DTfile2);
		String Name = AutoATUDataPage.setTaskName(driver, taskName);
		AutoATUDataPage.setReportName(driver,
				LanguageUtil.translate("Optimization analysis report"));
		AutoATUDataPage.commitBtnClick(driver);
		return Name;
	}

	public static String creatCompareReportTask(WebDriver driver,
			String defaultWindowID, String taskName, String compareType,
			String[] cfgfile, String[] EParafile, String[] DTfile,
			String[] cfgfile2, String[] EParafile2, String[] DTfile2,
			String OperRSRP, String TextRSRP, String OperSINR, String TextSINR,
			String OperAPPUL, String TextAPPUL, String OperAPPDL,
			String TextAPPDL) {

		// 打开 ATU数据自动分析任务
		NetworkAnalysisCenterTask.openAutoATUData(driver);

		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoATUDataPage.CompareTask_ID, true);
		if ("ATU与PA对比报告".equals(compareType)) {
			CommonJQ.click(driver, AutoATUDataPage.ATUAndPA_ID, true);
		} else if ("ATU与ATU对比报告".equals(compareType)) {
			CommonJQ.click(driver, AutoATUDataPage.ATUAndATU_ID, true);
		} else {
			Assert.fail("对比类型,输入错误，compareType：" + compareType);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		AutoATUDataTask.chooseFile(driver, defaultWindowID, cfgfile, EParafile,
				DTfile);
		AutoATUDataTask.chooseFile2(driver, defaultWindowID, cfgfile2,
				EParafile2, DTfile2);
		String Name = AutoATUDataPage.setTaskName(driver, taskName);
		AutoATUDataPage.setReportName(driver,
				LanguageUtil.translate("Optimization analysis report"));
		AutoATUDataTask.setPara(driver, OperRSRP, TextRSRP, OperSINR, TextSINR,
				OperAPPUL, TextAPPUL, OperAPPDL, TextAPPDL);
		AutoATUDataPage.commitBtnClick(driver);
		return Name;
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

		if (cfgfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, cfgfile,
					AutoATUDataPage.Cfg_ID, defaultWindowID);
		}
		if (EParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, EParafile,
					AutoATUDataPage.EPara_ID, defaultWindowID);
		}
		if (DTfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, DTfile,
					AutoATUDataPage.DT_ID, defaultWindowID);
		}
	}

	private static void chooseFile2(WebDriver driver, String defaultWindowID,
			String[] cfgfile, String[] EParafile, String[] DTfile) {

		if (cfgfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, cfgfile,
					AutoATUDataPage.Cfg2_ID, defaultWindowID);
		}
		if (EParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, EParafile,
					AutoATUDataPage.EPara2_ID, defaultWindowID);
		}
		if (DTfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, DTfile,
					AutoATUDataPage.DT2_ID, defaultWindowID);
		}
	}

	/**
	 * 
	 * @param driver
	 * @param chapter
	 *            :章节定制
	 * @param Indicator
	 *            :指标输出定制
	 */
	private static void setReportPara(WebDriver driver, String[] chapter,
			String[] Indicator) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoATUDataPage.BtnReportPara, true, "模板设置按钮");
		CommonJQ.click(driver, AutoATUDataPage.BtnReportParaTit2, true, "章节定制");
		String Initjselemnet1 = "$('a[title=\"1 测试概况\"]').prev()";
		String Initjselemnet2 = "$('a[title=\"2 优化测试指标\"]').prev()";
		String Initjselemnet3 = "$('a[title=\"3 问题点分类汇总\"]').prev()";
		String Initjselemnet4 = "$('a[title=\"4 分析解决方案\"]').prev()";
		String Initjselemnet5 = "$('a[title=\"5 遗留问题与求助\"]').prev()";
		String Initjselemnet6 = "$('a[title=\"6 致 谢\"]').prev()";
		boolean Flage1 = CommonJQ.hasClass_Custom(driver, Initjselemnet1,
				"button chk checkbox_true_full");
		boolean Flage12 = CommonJQ.hasClass_Custom(driver, Initjselemnet1,
				"button chk checkbox_true_part");
		boolean Flage2 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_full");
		boolean Flage22 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_part");
		boolean Flage3 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_full");
		boolean Flage32 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_part");
		boolean Flage4 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_full");
		boolean Flage42 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_part");
		boolean Flage5 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_full");
		boolean Flage52 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_part");
		boolean Flage6 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_full");
		boolean Flage62 = CommonJQ.hasClass_Custom(driver, Initjselemnet2,
				"button chk checkbox_true_part");
		if (Flage1 || Flage12) {
			CommonJQ.click(driver, Initjselemnet1);
		}
		if (Flage2 || Flage22) {
			CommonJQ.click(driver, Initjselemnet2);
		}
		if (Flage3 || Flage32) {
			CommonJQ.click(driver, Initjselemnet3);
		}
		if (Flage4 || Flage42) {
			CommonJQ.click(driver, Initjselemnet4);
		}
		if (Flage5 || Flage52) {
			CommonJQ.click(driver, Initjselemnet5);
		}
		if (Flage6 || Flage62) {
			CommonJQ.click(driver, Initjselemnet6);
		}
		for (int i = 0; i < chapter.length; i++) {
			String jselemnet = "$('a[title=\"" + chapter[i]
					+ "\"]').prev().filter(':visible')";
			CommonJQ.click(driver, jselemnet);
		}
		CommonJQ.click(driver, AutoATUDataPage.BtnReportParaTit4, true,
				"指标输出定制");
		CommonJQ.click(driver, "input[onclick=\"move_allToLeft();\"]", true,
				"指标输出定制<<");
		if (Indicator != null) {
			if ("全指标".equalsIgnoreCase(Indicator[0])) {
				CommonJQ.click(driver, "input[onclick=\"move_allToRight();\"]",
						true, "指标输出定制>>");
			} else {
				for (int i = 0; i < Indicator.length; i++) {
					String script = "$('ul#out_left').find('li').filter(function(){return $(this).text().trim()=='"
							+ Indicator[i] + "'})";
					CommonJQ.click(driver, script, Indicator[i]);
				}
				CommonJQ.click(driver,
						"input[onclick=\"move_partToRight();\"]", true,
						"指标输出定制>");

			}
		}

		CommonJQ.click(driver, AutoATUDataPage.BtnReportParaOK, true,
				"模板设置->确定按钮");
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param OperRSRP
	 *            :弱覆盖判定运算符
	 * @param TextRSRP
	 *            :弱覆盖判定输入框"
	 * @param OperSINR
	 *            :SINR 质差判定运算符
	 * @param TextSINR
	 *            :SINR 质差判定输入框
	 * @param OperAPPUL
	 *            :APP 上行判定运算符
	 * @param TextAPPUL
	 *            :APP 上行判定输入框
	 * @param OperAPPDL
	 *            ：APP 下行判定运算符
	 * @param TextAPPDL
	 *            ：APP 下行判定输入框
	 */
	private static void setPara(WebDriver driver, String OperRSRP,
			String TextRSRP, String OperSINR, String TextSINR,
			String OperAPPUL, String TextAPPUL, String OperAPPDL,
			String TextAPPDL) {

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoATUDataPage.BtnPara, true, "参数设置按钮");
		// 弱覆盖判定
		CommonJQ.click(driver, AutoATUDataPage.TextOperRSRP, true, "RSRP运算符输入框");
		if ("<=".equalsIgnoreCase(OperRSRP)) {
			CommonJQ.click(driver, AutoATUDataPage.ListOperRSRP, true, 1,
					"RSRP运算符<=");
		} else {
			CommonJQ.click(driver, AutoATUDataPage.ListOperRSRP, true, 0,
					"RSRP运算符<");
		}
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
				CommonJQ.value(driver, "input[name=\"parameterRSRP.setCase\"]",
						RSRP[i], "弱覆盖判定:采样点比例Case输入框");
			}
		}
		// SINR 质差判定
		String[] OperSINRatt = OperSINR.split(",");
		for (int i = 0; i < OperSINRatt.length; i++) {

			if (i == 0) {
				CommonJQ.click(driver, AutoATUDataPage.TextOperSINR1, true,
						"SINR1运算符输入框");
				if ("<=".equalsIgnoreCase(OperSINRatt[i])) {
					CommonJQ.click(driver, AutoATUDataPage.ListOperSINR1, true,
							1, "SINR1运算符<=");
				} else {
					CommonJQ.click(driver, AutoATUDataPage.ListOperSINR1, true,
							0, "SINR1运算符<");
				}
			}
			if (i == 1) {
				CommonJQ.click(driver, AutoATUDataPage.TextOperSINR2, true,
						"SINR2运算符输入框");
				if (">".equalsIgnoreCase(OperSINRatt[i])) {
					CommonJQ.click(driver, AutoATUDataPage.ListOperSINR2, true,
							0, "SINR2运算符>");
				} else if ("<".equalsIgnoreCase(OperSINRatt[i])) {
					CommonJQ.click(driver, AutoATUDataPage.ListOperSINR2, true,
							2, "SINR2运算符<");
				} else if ("<=".equalsIgnoreCase(OperSINRatt[i])) {
					CommonJQ.click(driver, AutoATUDataPage.ListOperSINR2, true,
							3, "SINR2运算符<=");
				} else {
					CommonJQ.click(driver, AutoATUDataPage.ListOperSINR2, true,
							1, "SINR2运算符>=");
				}
			}

		}
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
				CommonJQ.value(driver, "input[name=\"parameterSINR.setCase\"]",
						SINR[i], "SINR 质差判定:采样点比例Case输入框");
			}
		}
		// APP 上行判定
		String[] OperAPPULatt = OperAPPUL.split(",");
		for (int i = 0; i < OperAPPULatt.length; i++) {

			if (i == 0) {
				CommonJQ.click(driver, "#PDCPUL_sign", true, "APPUL1运算符输入框");
				if ("<=".equalsIgnoreCase(OperAPPULatt[i])) {
					CommonJQ.click(driver, "#PDCPUL_sign option", true, 1,
							"APPUL1运算符<=");
				} else {
					CommonJQ.click(driver, "#PDCPUL_sign option", true, 0,
							"APPUL1运算符<");
				}
			}
			if (i == 1) {
				CommonJQ.click(driver, "#ulSignSINR", true, "APPUL2运算符输入框");
				if (">".equalsIgnoreCase(OperAPPULatt[i])) {
					CommonJQ.click(driver, "#ulSignSINR option", true, 0,
							"APPUL2运算符>");
				} else if ("<".equalsIgnoreCase(OperAPPULatt[i])) {
					CommonJQ.click(driver, "#ulSignSINR option", true, 2,
							"APPUL2运算符<");
				} else if ("<=".equalsIgnoreCase(OperAPPULatt[i])) {
					CommonJQ.click(driver, "#ulSignSINR option", true, 3,
							"APPUL2运算符<=");
				} else {
					CommonJQ.click(driver, "#ulSignSINR option", true, 1,
							"APPUL2运算符>=");
				}
			}
			if (i == 2) {
				CommonJQ.click(driver, "#ulSignRSRP", true, "APPUL3运算符输入框");
				if (">".equalsIgnoreCase(OperAPPULatt[i])) {
					CommonJQ.click(driver, "#ulSignRSRP option", true, 0,
							"APPUL3运算符>");
				} else if ("<".equalsIgnoreCase(OperAPPULatt[i])) {
					CommonJQ.click(driver, "#ulSignRSRP option", true, 2,
							"APPUL3运算符<");
				} else if ("<=".equalsIgnoreCase(OperAPPULatt[i])) {
					CommonJQ.click(driver, "#ulSignRSRP option", true, 3,
							"APPUL3运算符<=");
				} else {
					CommonJQ.click(driver, "#ulSignRSRP option", true, 1,
							"APPUL3运算符>=");
				}
			}

		}
		String[] APPUL = TextAPPUL.split(",");
		for (int i = 0; i < APPUL.length; i++) {
			if (i == 0) {
				CommonJQ.value(driver,
						"input[name=\"parameterPDCPUL.procCase\"]", APPUL[i],
						"APP 上行判定:APPUL1输入框");
			}
			if (i == 1) {
				CommonJQ.value(driver, "input[name=\"ulSINR\"]", APPUL[i],
						"APP 上行判定:APPUL2输入框");
			}
			if (i == 2) {
				CommonJQ.value(driver, "input[name=\"ulRSRP\"]", APPUL[i],
						"APP 上行判定:APPUL3输入框");
			}
			if (i == 3) {
				CommonJQ.value(driver,
						"input[name=\"parameterPDCPUL.procWidth\"]", APPUL[i],
						"APP 上行判定:采样点比例Width输入框");
			}
			if (i == 4) {
				CommonJQ.value(driver,
						"input[name=\"parameterPDCPUL.setCase\"]", APPUL[i],
						"APP 上行判定:采样点比例Case输入框");
			}
		}
		// APP 下行判定
		String[] OperAPPDLatt = OperAPPDL.split(",");
		for (int i = 0; i < OperAPPDLatt.length; i++) {

			if (i == 0) {
				CommonJQ.click(driver, "#PDCPDL_sign", true, "APPDL1运算符输入框");
				if ("<=".equalsIgnoreCase(OperAPPDLatt[i])) {
					CommonJQ.click(driver, "#PDCPDL_sign option", true, 1,
							"APPDL1运算符<=");
				} else {
					CommonJQ.click(driver, "#PDCPDL_sign option", true, 0,
							"APPDL1运算符<");
				}
			}
			if (i == 1) {
				CommonJQ.click(driver, "#dlSignSINR", true, "APPDL2运算符输入框");
				if (">".equalsIgnoreCase(OperAPPDLatt[i])) {
					CommonJQ.click(driver, "#dlSignSINR option", true, 0,
							"APPDL2运算符>");
				} else if ("<".equalsIgnoreCase(OperAPPDLatt[i])) {
					CommonJQ.click(driver, "#dlSignSINR option", true, 2,
							"APPDL2运算符<");
				} else if ("<=".equalsIgnoreCase(OperAPPDLatt[i])) {
					CommonJQ.click(driver, "#dlSignSINR option", true, 3,
							"APPDL2运算符<=");
				} else {
					CommonJQ.click(driver, "#dlSignSINR option", true, 1,
							"APPDL2运算符>=");
				}
			}
			if (i == 2) {
				CommonJQ.click(driver, "#dlSignRSRP", true, "APPDL3运算符输入框");
				if (">".equalsIgnoreCase(OperAPPDLatt[i])) {
					CommonJQ.click(driver, "#dlSignRSRP option", true, 0,
							"APPDL3运算符>");
				} else if ("<".equalsIgnoreCase(OperAPPDLatt[i])) {
					CommonJQ.click(driver, "#dlSignRSRP option", true, 2,
							"APPDL3运算符<");
				} else if ("<=".equalsIgnoreCase(OperAPPDLatt[i])) {
					CommonJQ.click(driver, "#dlSignRSRP option", true, 3,
							"APPDL3运算符<=");
				} else {
					CommonJQ.click(driver, "#dlSignRSRP option", true, 1,
							"APPDL3运算符>=");
				}
			}

		}
		String[] APPDL = TextAPPDL.split(",");
		for (int i = 0; i < APPDL.length; i++) {
			if (i == 0) {
				CommonJQ.value(driver,
						"input[name=\"parameterPDCPDL.procCase\"]", APPDL[i],
						"APP 下行判定:APPDL1输入框");
			}
			if (i == 1) {
				CommonJQ.value(driver, "input[name=\"dlSINR\"]", APPDL[i],
						"APP 下行判定:APPDL2输入框");
			}
			if (i == 2) {
				CommonJQ.value(driver, "input[name=\"dlRSRP\"]", APPDL[i],
						"APP 下行判定:APPDL3输入框");
			}
			if (i == 3) {
				CommonJQ.value(driver,
						"input[name=\"parameterPDCPDL.procWidth\"]", APPDL[i],
						"APP 下行判定:采样点比例Width输入框");
			}
			if (i == 4) {
				CommonJQ.value(driver,
						"input[name=\"parameterPDCPDL.setCase\"]", APPDL[i],
						"APP 下行判定:采样点比例Case输入框");
			}
		}
		CommonJQ.click(driver, AutoATUDataPage.BtnParaOK, true, "参数设置->确定按钮");
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
				EnvConstant.Path_DownLoad, "优化分析报告");
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
