package cloud_plugin.page.network_performance_analysis_center.engineering_optimization.mv;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class MVPage {
	public static final String TextTaskName = "#taskDesc"; //
	
	public static final String  ifMV =  "ul[ng-show=\"ui.taskMvShow\"] li";
	
	public static final String  ifMVBtn  = "span[ng-click=\"showMv()\"]";
	
	public static final String HelpTitleBtn = ".zz_select.msel span";
	
	public static final String HelpTitle = "ul[id=\"mshowflowTitle\"]";
	
	public static final String  Detail = ".expertHelpTextArea.ng-pristine.ng-valid";
	
	public static final String FileBtn = "#userfilePick";
	
	public static final String SubmitBtn = "#commit_Tasknew";
	
	public static final String CancleBtn = "#cancel_Task";
	
	
	public static final String StateBtn = ".zz_select.msel span";
	
	public static final String State = "ul[id=\"mshowtaskStatus\"] li";
	
	public static final String HelpStateBtn = ".zz_select.msel span";
	
	public static final String HelpState = "ul[id=\"mshowtaskExpertStatus\"] li";
	
	
	public static final String gridChickedFirst = ".x-grid-row-checker";
	public static final String ideaAdd = "#addDataReply";
	public static final String ideaFinish = "#finishReply";
	public static final String expertSubmit="span[class=\"btn_blue\"] span";
	public static final String expertReplyFilePickBtn = "#expertReplyFilePick";
	public static final String ifDownLoadFile=".userAttachment.ng-binding";
	public static final String currentState="div[class=\"expertMessage\"] span";
	
	public static final String consumerFileBtn=".btn_gray";
	public static final String ideaFinishRoid = "#finishResolve";
	public static final String seekForHelp = "#seekForHelp";
	public static final String grade = "ul[class=\"star\"] li";
	public static final String grade2 = "div[class=\"star\"]>ul>li";
	public static final String ifDownLoadMould="#downLoadTempalte";
	
	public static final String frameBtn = "#frameBtn";
	public static final String isOK = ".l-btn-text";
	
	public static String setTaskName(WebDriver driver, String  taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName + "_" + ZIP.hhmmss();
		LoadingPage.Loading(driver,TextTaskName);
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, MVPage.SubmitBtn,true);
		SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
	}
	public static void cancalBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, MVPage.CancleBtn,true);
		SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
	}
	public static void commitBtn_expert_Click(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, MVPage.expertSubmit,true);
		SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
	}
	public static void commitBtn_customer_Click(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, MVPage.expertSubmit,true);
		SwitchDriver.switchDriverToSEQ(driver);//迁出 iframe
	}
}
