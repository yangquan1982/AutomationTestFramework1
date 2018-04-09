package cloud_plugin.page.network_evaluation_assurance_center.lte_kpi_report;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class LTEKPIReportPage {

	public static final String TaskName_ID = "#taskName";//Task Name
	
	public static final String GTLQua_ID = "#GTLQua";//GTL Network Quality Monitoring Repor
	public static final String GTLTrend_ID = "#GTLTrend";//Chinese Area Mobile GTL Network Trends Information Report
	public static final String MobileLTE_ID = "#MobileLTE";//National LTE Boutique Network City Information Collection Report
	public static final String MobileMBB_ID = "#MobileMBB";//China Mobile MBB Collection and Delivery Of Wireless Network Planning Half Monthly Report
	
	public static final String Data_ID = "select_dataString";
	public static final String DataCancel_PATH = ".btn_gray .ng-binding";//
	
	public static final String Type = "div[class=\"zz_select msel\"] input[type=\"text\"]";
	public static final String MobileMonth  = "ul[id=\"mshowtask.modelType\"] li[id=\"5\"]";
	public static final String TelecomWeek  = "ul[id=\"mshowtask.modelType\"] li[id=\"0\"]";
	public static final String TelecomMonth = "ul[id=\"mshowtask.modelType\"] li[id=\"1\"]";
	public static final String UnicomWeek   = "ul[id=\"mshowtask.modelType\"] li[id=\"2\"]";
	
	public static final String TaskNameMsg_ID = "#taskInfoHit";
	public static final String ReportMsg_CLASS = ".reportNameErr span[class=\"req ng-binding\"]";
	public static final String Msg_CLASS = "span[class=\"fileChoiceErrorMsg ng-binding\"]";
	
	
	private static final String Commit_ID = "#commit_Task";
	private static final String Cancel_ID = "#cancel_Task";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		taskName = taskName+"_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		LoadingPage.Loading(driver,"#select_dataString","数据选择->选择文件");
		CommonJQ.value(driver, TaskName_ID, taskName);
		String text = CommonJQ.getValue(driver, TaskName_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TaskName_ID, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		return taskName;
	}
	
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, Commit_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, Cancel_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
