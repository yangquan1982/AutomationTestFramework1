package cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_atu_data;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutoATUDataPage {

	private static final String TaskName_ID = "#taskName";//Task Name
	private static final String ReportName_ID = "#reportName";//Report Name 
	
	public static final String ATUTask_ID = "#taskatu";
	public static final String Grids_ID = "#checkBoxOfRaster";
	public static final String GridsPre_ID = "#inputRasterPre";
	public static final String GridsLater_ID = "#inputRasterLater";
	
	public static final String ThemeTask_ID = "#taskreportcompare";
	
	public static final String CompareTask_ID = ".in_block #ATU_PA_CompReport_RDO";
	public static final String ATUAndATU_ID = "#ATU_ATU_CompReport_RDO";
	public static final String ATUAndPA_ID = "ul#comp_report_select_div #ATU_PA_CompReport_RDO";
	
	public static final String Cfg_ID = "confDataSelect";
	public static final String EPara_ID = "projectParaSelect";
	public static final String DT_ID = "daDataSelect";
	
	public static final String Cfg2_ID = "confDataSelect_Comp";
	public static final String EPara2_ID = "projectParaSelect_Comp";
	public static final String DT2_ID = "daDataSelect_Comp";
	
	
	public static final String BtnPara = "a[onclick=\"fetchPara();\"] span";
	public static final String TextOperRSRP = "#RSRP_Sign";
	public static final String ListOperRSRP = "#RSRP_Sign option";
	public static final String BtnParaOK = "#confirmConfig";
	
	public static final String BtnReportPara = "#modelSetting";
	public static final String BtnReportParaTit1 = "#tit_model1";
	public static final String BtnReportParaTit2 = "#tit_model2";
	public static final String BtnReportParaTit3 = "#tit_model3";
	public static final String BtnReportParaTit4 = "#tit_model4";
	public static final String BtnReportParaOK = "#confirmForChapters";
	
	public static final String TextOperSINR1 = "#SINR_Sign1";
	public static final String ListOperSINR1 = "#SINR_Sign1 option";
	public static final String TextOperSINR2 = "#SINR_Sign2";
	public static final String ListOperSINR2 = "#SINR_Sign2 option";
	
	
	private static final String SubmitBtn_ID = "#taskSubmitButton";
	private static final String CancelBtn_ID = "#taskCannelRetrunButton";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		 taskName = taskName+"_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TaskName_ID, taskName);
		String text = CommonJQ.getValue(driver, TaskName_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TaskName_ID, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		return taskName;
	}
	public static void setReportName(WebDriver driver,String reportName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, ReportName_ID, reportName);
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
}
