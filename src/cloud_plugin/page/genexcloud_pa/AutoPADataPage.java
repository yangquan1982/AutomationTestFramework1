package cloud_plugin.page.genexcloud_pa;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cloud_public.task.GetDataByTypeTask;
import common.util.CommonJQ;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutoPADataPage {
	/*******************
	 * Assistant Select xwx357019 Begin
	 *************************/
	public static final String VoLTEOptimiz_ID = "#volte";// volte Optimization
	public static final String RFReport_ID = "#rf";// volte Optimization
	public static final String SSVOptimiz_ID = "#count";// volte Optimization
	public static final String CellID_OK = "#filter_ok";// volte Optimization
	public static final String CellID_OK_END = "span[id=close_ok]";// volte
																	// Optimization
	public static final String VoLTE_DOC_Report = "#VoLTE优化报告.docx";// Report
																	// DOC
	public static final String VoLTE_XLSX_Report = "#VoLTE优化报告.xlsx";// Report
																		// XLSX
	public static final String SelectedVoLTEOptimiz_ID = "#selectedSpecialNum";// VoLTE
																				// Optimization
	public static final String SelectedRFOptimiz_ID = "find('input[id=\"selectedSpecialNum\"]').eq(1)";// VoLTE
																										// Optimization
	public static final String VoLTEAllSelect_ID = "#allSelect";// Select
																// Theme->Select
																// All
	public static final String VoLTEAccessFailSelect_ID = "#89001";// Select
																	// Theme->Access
																	// Fail
	public static final String VoLTECallDropSelect_ID = "#89002";// Select
																	// Theme->Call
																	// Drop
	public static final String MOSCallDropSelect_ID = "#88904";// Select
																// Theme->Call
																// Drop
	public static final String Access_Fail_ID = "#88901";// Select Theme->Call
															// Drop
	public static final String Call_Drop_ID = "#88902";// Select Theme->Call
														// Drop

	public static final String SSVReportSelect_ID = "Program_Parameter_Setting";//
	public static final String Assistant_EPara_ID = "find('span[class=ng-binding]:contains(选择文件)').eq(1)";// Engineering
																											// Parameter
	public static final String Assistant_DT_ID = "find('span[class=ng-binding]:contains(选择文件)').eq(5)";// Engineering
																										// Parameter
	private static final String Assistant_TaskName_ID = "#taskName";// Task Name
	private static final String Assistant_SubmitBtn_ID = "#commit_Tasknew";
	public static final String SelectedDates = "find('div[class=\"zz_select_short msel ng-scope\"]').find('span')";// VoLTE
																													// Optimization
	public static final String SelectedCellID = "find('div[class=\"paramLabel\"]').find('span')";// VoLTE
																									// Optimization
	public static final String CellID = "1199";// VoLTE Optimization
	public static final String SelectedReport = "find('div[class=\"zz_select msel2\"]').find('span')";// VoLTE
																										// ReportMode
	public static final String SelectedSSVEngineerDates = "find('div[class=\"zz_select_short netWorkType ng-scope\"]').find('span')";// SSV
																																		// Engineering

	/*******************
	 * Assistant Select xwx357019 End
	 *************************/

	private static final String TaskName_ID = "#reportName";// Task Name

	public static final String DTAndOptimiz_ID = "#taskTypeCluster";// DT
																	// analysis
																	// and
																	// optimization
	public static final String WhetherTopN_ID = "#checkBoxOfTopN";// Whether Top
																	// N
	public static final String TopN_ID = "#inputTopN";// Top N
	public static final String WhetherGrids_ID = "#checkBoxOfRaster";// Whether
																		// Grids
	public static final String GridsPre_ID = "#inputRasterPre";// Grids
	public static final String GridsLater_ID = "#inputRasterLater";// Grids
	public static final String WhetherPoint_ID = "#checkBoxOfisExportSlot";// Whether
																			// Collection
																			// Point
																			// Info
																			// Is
																			// Required

	public static final String Coverage_ID = "find('input[id=\"52\"]')";// Select
																		// Theme->Select
																		// All
	public static final String ThemeOptimiz_ID = "#taskTypeSpecial";// Theme
																	// Optimization
	public static final String SelectedOptimiz_ID = "#selectedSpecialNum";// Theme
																			// Optimization
	public static final String AllSelect_ID = "#allSelect";// Select
															// Theme->Select All
	public static final String BlackPoint_ID = "#chkblackPoint";// 黑点专题
	public static final String GuolopFG_ID = "#guolopFGSpecial";// 过覆盖专题
	public static final String Mod3_ID = "#modGRSpecial";// Mod3干扰专题
	public static final String AntennaInterfer_ID = "#chkAntennaInterfer";// 天线接反专题
	public static final String NbrLoss_ID = "#chkNbrLossSet";// 邻区错配漏配专题
	public static final String LopFG_ID = "#overlopFGSpecial";// 重叠覆盖专题
	public static final String GPSAbnormal_ID = "#chkGPSAbnormal";// 小区经纬度异常专题
	public static final String AntennaAbnormal_ID = "#chkAntennaAbnormal";// 小区经纬度异常专题
	public static final String ZoomDisturb_ID = "#zoomDisturbSpecial";// 小区经纬度异常专题

	public static final String CompareReport_ID = "#taskTypeCompReport";// Comparison
																		// Report

	public static final String CounterStatistics_ID = "#taskTypeTargetReport";// Counter
																				// Statistics
	public static final String ClusterOptimiz_ID = "#targetTypeCluster";// Cluster
																		// Optimization
																		// Statistics
	public static final String StartTime_ID = "#date1";// Start Time
	public static final String EndTime_ID = "#date2";// End Time

	public static final String Cfg_ID = "confDataSelect";// Configuration Data
	public static final String EPara_ID = "projectParaSelect";// Engineering
																// Parameter
	public static final String DT_ID = "daDataSelect";// DT Data
	public static final String Polygon_ID = "polygonDataSelect";

	public static final String Cfg2_ID = "confDataSelect_comp";// Configuration
																// Data
	public static final String EPara2_ID = "projectParaSelect_comp";// Engineering
																	// Parameter
	public static final String DT2_ID = "daDataSelect_comp";// DT Data

	private static final String SubmitBtn_ID = "#taskSubmitButton";
	private static final String CancelBtn_ID = "#taskCannelRetrunButton";

	public static final String TaskName_Msg_ID = "#rptNamPmt";
	public static final String TopN_Msg_ID = "#topNPmt";
	public static final String Raster_Msg_ID = "#rasterPmt";

	public static final String Cfg_Msg_ID = "#cnfPmt";
	public static final String EPara_Msg_ID = "#paraHitFDD";
	public static final String DT_Msg_ID = "#dtDatPmt";

	public static final String Special_Msg_ID = "#specialPmt";

	public static final String StartTime_Msg_ID = "#startTimeCheck";
	public static final String EndTime_Msg_ID = "#endTimeCheck";
	public static final String DownloadReport = "a[class=\"icon_down ml10\"]";

	public static String setTaskName(WebDriver driver, String taskName) {
		taskName = taskName + "_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, Assistant_TaskName_ID, taskName);
		String text = CommonJQ.getValue(driver, Assistant_TaskName_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, Assistant_TaskName_ID, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime() + "TaskName:" + taskName);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, Assistant_SubmitBtn_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CancelBtn_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void setCellID(WebDriver driver, String CellName) {
		final String script = "$('iframe[name=main]').contents().find('div[id=dialogSSV]').find('div').find('input').eq(0).val("
				+ CellName + ")";
		LOG.info_aw("script:" + script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		((JavascriptExecutor) driver).executeScript(script);
		Pause.pause(3000);
		final String script1 = "$('#dialogSSV' , $('iframe').contents()).scope()";
		((JavascriptExecutor) driver).executeScript(script1);
		GetDataByTypeTask.SSVclickToOpenModalWindow(driver, AutoPADataPage.CellID_OK);

	}

	public static void SelectFirstCellID(WebDriver driver) {
		final String script = "$('iframe[name=main]').contents().find('div[id=dialogSSV]').find('div').find('.ng-pristine.ng-valid').click()";
		LOG.info_aw("script:" + script);
		System.out.println(ZIP.NowTime() + "script:" + script);
		((JavascriptExecutor) driver).executeScript(script);
		GetDataByTypeTask.SSVclickToOpenModalWindow(driver, AutoPADataPage.CellID_OK_END);

	}
}
