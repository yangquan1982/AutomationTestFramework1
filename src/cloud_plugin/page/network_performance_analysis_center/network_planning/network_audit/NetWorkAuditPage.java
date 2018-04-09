package cloud_plugin.page.network_performance_analysis_center.network_planning.network_audit;

import org.openqa.selenium.WebDriver;

import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class NetWorkAuditPage {

	private static final String AppName = "li[id=\"genexspace.plugin.networkauditPlugStr\"]";

	public static void NetWorkAuditClick(WebDriver driver) {
		CommonJQ.click(driver, AppName, true, "插件未找到，插件名：网络评估");
	}
	
	private static final String TaskName_ID = "#taskName";//Task Name
	public static final String ExcelReport_ID = "#excelReportId";
	public static final String WordReport_ID = "#wordReportId";
	public static final String TopN_ID = "#topNId";
	public static final String FailedCellsRati_ID = "#notPassCellRatioId";
	public static final String RSRPThreshold_ID = "#rsrpEffectiveThresholdId";
	
	public static final String BtnMessageOK = "div[class=\"messager-button\"] span[class=\"l-btn-text\"]";
	
	public static final String BusyHourSet_CLASS = "span[class=\"periodChooseTit ng-binding\"]";
	public static final String WholeDay_ID = "#wholeDayId";
	public static final String DefineTime_ID = "#defineTimeId";
	public static final String Date1StartTime_ID = "#table_1";
	public static final String Date1EndTime_ID = "#table_2";
	
	public static final String Date2StartTime_ID = "#table_3";
	public static final String Date2EndTime_ID = "#table_4";
	
	public static final String Date3StartTime_ID = "#table_5";
	public static final String Date3EndTime_ID = "#table_6";
	
	public static final String TimeOK_ID = "#TimeOk";
	public static final String TimeCancel_ID = "#TimeCancel";
	
	public static final String Cfg_ID = "select_confFilePath";
	public static final String Pfm_ID = "select_prsFilePath";
	public static final String Para_ID = "select_parasFilePath";
	public static final String MR_ID = "select_mrFilePath";
	public static final String MML_ID = "select_mmlFilePath";
	public static final String Property_ID = "select_propertyFilePath";
	public static final String SelfDefine_ID = "select_selfDefineFilePath";
	
	public static final String CHR_ID = "select_chrFilePath";
	public static final String Alarm_ID = "select_alarmFilePath";
	public static final String NodebCfg_ID = "select_nodebconfFilePath";
	public static final String NodebPfm_ID = "select_nodebpfmFilePath";
	public static final String NodebAlarm_ID = "select_nodebalarmFilePath";
	public static final String NodebMML_ID = "select_nodebmmlFilePath";
	public static final String NodebLicense_ID = "select_nodeblicenseFilePath";
	
	private static final String IncrementData_ID = "#incrementData";
	
	
	private static final String SubmitBtn_ID = "#submitTask";
	private static final String CancelBtn_ID = "#cancelTask";
	

	
	public static String setTaskName(WebDriver driver,String taskName) {
		 taskName = taskName+"_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TaskName_ID, taskName,true,"任务名称");
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		return taskName;
	}
	public static void incrementImport(WebDriver driver,String filepath) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, IncrementData_ID, true);
		CommonFile.ChooseOneFile(filepath);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, SubmitBtn_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CancelBtn_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void BtnTempIncrementClick(WebDriver driver,String ResultHome) {
		CommonJQ.click(driver, "#select_incrementTemplateDownload", true, "增长因子数据->模板下载");
		String TempName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "增长因子");
		ZIP.depress(EnvConstant.Path_DownLoad + "\\" + TempName,
				ResultHome);
	}
}
