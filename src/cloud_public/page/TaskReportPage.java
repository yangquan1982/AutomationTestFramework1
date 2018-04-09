package cloud_public.page;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.LanguageUtil;



public class TaskReportPage {

	// const string.
	public static void CreateTaskClick( WebDriver driver){
		CommonJQ.click_ByText(driver, "li", LanguageUtil.translate("Project Task"), true, "项目任务");
		CommonJQ.click_ByText(driver, "li", LanguageUtil.translate("New Task"), true, "新建任务");
	}
	public static final String CreateTask = "#createTask";
	public static final String DeleteTask = "#deleteTask";
	public static final String DownLoadReports = "#downLoadReports";
	public static final String TaskRetry = "#taskRetry";
	
	public static final String MShowTaskStatus = "#mshowtaskStatus";
	public static final String TextTaskStatus_ID = "#texttaskStatus";//
	public static final String TaskStatus_ID = "#taskStatus";
	public static final String TextTaskExpertStatus = "#texttaskExpertStatus";
	public static final String SearchId = "#searchId";
	public static final String SearchBtn = "div[class=\"in_block pt10\"]>span>label>span";
	
	public static final String ChecK = "div[class=\"x-grid-cell-inner \"}";
	public static final String ChecKAllBtn = "div.x-grid-row-checker";
	
	public static final String MessageOKBtn = "div.messager-button>a.l-btn";
	public static final String MessageCancelBtn = "div.messager-button>a.l-btn-cancel";//$('#gridview-1019-body tbody tr[data-boundview="gridview-1019"] > td').eq(3).text()
	
	public static final String TaskColumnText = "#gridview-1019-body tbody tr[data-boundview=\"gridview-1019\"] > td";
	public static final String NewTaskColumnText = "tbody tr td";
	public static final String TaskList = "gridview-1019";

}
