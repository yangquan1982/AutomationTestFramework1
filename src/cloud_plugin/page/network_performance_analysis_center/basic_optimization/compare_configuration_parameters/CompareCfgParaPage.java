package cloud_plugin.page.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class CompareCfgParaPage {

	private static final String TASKNAME_ID = "#taskName";
	public static final String SAMECONTRASTTYPE_CHECKBOX = "#samecontrastType";
	public static final String DIFFCONTRASTTYPE_CHECKBOX = "#diffcontrastType";

	public static final String CONID1_ID = "conid1";
	public static final String CONID2_ID = "conid2";
	public static final String CONID4_ID = "conid4";
	public static final String CONID3_ID = "conid3";
	private static final String WIRELESSALLTORIGHT_ID = "#wireLessAllToRight";
	private static final String WIRESEARCH_ID = "#wireSearch";
	public static final String ErrorMsg_CLASS = "span[class=\"fileChoiceErrorMsg ng-binding\"]";
	private static final String COMMIT_TASKNEW_ID = ".taskSubmitDiv #commit_Tasknew";
	private static final String Cancel_ID = ".taskSubmitDiv #cancel_Task";
	private static final String CELLNAMESELF_ID = "#cellNameSelf";
	private static final String COMMIT_TASKNEW_CSS = ".pop_btn #commit_Tasknew";
	private static final String COMMIT_TASKNEW1_CSS = ".pop_btn #commit_Tasknew_1";
	private static final String RESULTOK_CSS = " .pop_btn #resultOk";
	private static final String IMPORTFILE_ID = "#importFile";
	public static final String WIREIMPORT_ID = "#wireImport";

	private static final String EXPORTWIRELESS_ID = "#exportWireLess";
	
	public static final String WireMsg_ID ="#wireNodesInfo";
	public static final String CheckParaMsg_ID ="#uploadFileInfo";

	private static final String TitleCSS_CLASS = "span[class=\"titleCSS ng-binding\"] a[class=\"ng-binding\"]";


	public static String setTaskName(WebDriver driver,String taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName+"_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TASKNAME_ID);
		CommonJQ.value(driver, TASKNAME_ID, taskName);
		String text = CommonJQ.getValue(driver, TASKNAME_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TASKNAME_ID, taskName);
		}
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}


	public static void clickWireLessAllToRight(WebDriver driver) 
	{
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, WIRELESSALLTORIGHT_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void searchValue(WebDriver driver, String searchValue) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, WIRESEARCH_ID, searchValue);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, COMMIT_TASKNEW_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, Cancel_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void cancelTitleClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, TitleCSS_CLASS, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void selectCellNameSelfRadio(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CELLNAMESELF_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void exportWireLessModel(WebDriver driver,String filePath) {
		FileHandle.delSubFile(ConstUrl.DownLoadPath);
		FileHandle.delSubFile(filePath);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, EXPORTWIRELESS_ID, true);		
		SwitchDriver.switchDriverToSEQ(driver);
		TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, "MO_parameter_CSV_template");
		FileHandle.copyFile(ConstUrl.DownLoadPath+"\\MO_parameter_CSV_template.csv", filePath+"\\MO_parameter_CSV_template.csv");
	}

	public static void importSelfModelFile(WebDriver driver,String filepath) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		/* 按键导出 */
		CommonJQ.click(driver, IMPORTFILE_ID, true);
		CommonFile.ChooseOneFile(filepath);
		if(CommonJQ.isExisted(driver, COMMIT_TASKNEW_CSS)){
			CommonJQ.click(driver, COMMIT_TASKNEW_CSS, true);
		}else{
			CommonJQ.click(driver, COMMIT_TASKNEW1_CSS, true);
		}
		CommonJQ.click(driver, RESULTOK_CSS, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
}
