package cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutoDTDataOverseasPage {

	private static final String TaskName_ID = "#taskName";//Task Name
	
	public static final String Probe_CLASS = "li[class=\"taskRadioLi taskRadioLi2\"] [value=\"probe\"]";
	public static final String ProbeReportType_PATH = "div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String ReportType  = "ul[id=\"mshowundefined\"] li[id=\"undefined\"]";
	
	public static final String DT_ID = "genServerSpan";//DT Data
	public static final String LEPara_ID = "parasServerSpan";//LTE Engineering Parameter 
	public static final String GEPara_ID = "$('iframe[name=main]').contents().find('#gsmParasServer').siblings('#parasServerSpan').click()";//GSM Engineering Parameter
	public static final String UEPara_ID = "$('iframe[name=main]').contents().find('#umtsParasServer').siblings('#parasServerSpan').click()";//UMTS Engineering Parameter
	public static final String MAP_ID = "mapServerSpan";//MapLayer Files 
	public static final String ExEPara_ID = "parasextServerSpan";//Extended Engineering Parameters
	public static final String RateScreenshot_ID = "rateServerSpan";//Rate Screenshot 
	
	public static final String ThirdPatry_CLASS = "li[class=\"taskRadioLi taskRadioLi2\"] [value=\"thirdPatry\"]";
	public static final String Tems_ID = "trpServer";//DT Data
	public static final String Nemo_ID = "nmfServer";//DT Data
	public static final String EPara_ID = "parasServerSpan";//Engineering Parameter
	
	public static final String ClearText_CLASS = "li[class=\"taskRadioLi taskRadioLi2\"] [value=\"thirdPatryProbe\"]";
	public static final String ClearTextDT_ID = "fmtServerSpan";//DT Data
	public static final String ClearTextEPara_ID = "parasServerSpan";//Engineering Parameter
	
	public static final String Romes_CLASS = "li[class=\"taskRadioLi taskRadioLi2\"] [value=\"romes\"]";
	public static final String RomesDT_ID = "rscmdServerSpan";//DT Data
	public static final String RomesEPara_ID = "parasServerSpan";//Engineering Parameter
	private static final String Commit_ID = "#commit_Tasknew";
	
	//fileChoiceErrorMsg ng-binding
	public static final String Msg_CLASS = "span[class=\"fileChoiceErrorMsg ng-binding\"]";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName+"_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TaskName_ID);
		CommonJQ.value(driver, TaskName_ID, taskName);
		String text = CommonJQ.getValue(driver, TaskName_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TaskName_ID, taskName);
		}
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}
	
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, Commit_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
