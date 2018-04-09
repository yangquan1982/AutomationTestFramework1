	package cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;

public class Seq_VMOS_ManagePage {

	private final static String CREATETASK_ID = "#createTask";
	private final static String TASKNAME_ID = "#taskName";

	private final static String NEXTPAGE_BTN_CLASS = "span[class='btn green ng-binding']";
	private final static String OK_BTN_CLASS = ".messager-button  .l-btn-left";


	private final static String TASKNAME_CSS = "span[class=\"taskAddInfoSpan ng-binding\"]:contains(\"任务名称\")~";
	private final static String PARAMSETTING_CLASS = ".taskAddProgressDotDiv";

	private final static String FAILURE_ID = "#FAILURE";



	/**
	 * <b>Description:</b>点击确认按钮
	 *
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static void clickOKButton(WebDriver driver) {
		CommonJQ.click(driver, OK_BTN_CLASS, true);
	}

	/**
	 * <b>Description:</b>新建任务
	 *
	 * @author lwx242612
	 * @param driver
	 * @return void
	 */
	public static String createNewTask(WebDriver driver) {
		CommonJQ.click(driver, CREATETASK_ID, true);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		String taskName = "AUTO_" + System.currentTimeMillis();
		CommonJQ.value(driver, TASKNAME_ID, taskName, true);
		clickNextPageBtn(driver, 0);
		return taskName;
	}

	/**
	 * <b>Description:</b>单击下一步按钮
	 *
	 * @author lwx242612
	 * @param driver
	 * @param pageNum
	 * @return void
	 */
	public static void clickNextPageBtn(WebDriver driver, int pageNum) {
		CommonJQ.click(driver, NEXTPAGE_BTN_CLASS, true, pageNum);
	}


	public static String getResultTaskName(WebDriver driver, int index) {
		return CommonJQ.getValue(driver, TASKNAME_CSS, index);
	}

	public static void clickResultParamSetting(WebDriver driver) {
		CommonJQ.click(driver, PARAMSETTING_CLASS, true, 2);
	}





	

	public static final String STATUE_CSS = ".theme label span";
	private static final String FAILURE_CHECKBOX_CSS = "tbody>tr>td  div.x-grid-row-checker";
	private static final String TASKID_ID = "tbody>tr>td  a";
	private static final String ALL_ID = "#ALL";

	public static final String SUCCESS_ID = "#SUCCESS";

	public static void clickSuccessSelect(WebDriver driver) {
		clickTaskSelectBtN(driver,0);
		CommonJQ.click(driver, SUCCESS_ID, false);
		if (CommonJQ.length(driver, FAILURE_CHECKBOX_CSS, true) > 0) {
			return;
		} else {
			Assert.fail("无分析成功的用例 ，不能发起任务重试");
		}
	}
	
	public static void clickTaskId(WebDriver driver,String taskId){
		if (StringUtils.isBlank(taskId)) {
			Assert.fail("没有符合条件的 任务！！");
		}
		CommonJQ.click(driver, "#" + taskId, true);		
	}
	public static void clickTaskByRow(WebDriver driver,int rowNum){
		CommonJQ.click(driver, FAILURE_CHECKBOX_CSS, true, rowNum);
		LoadingPage.Loading(driver);
	}
	public static void clickSelectAllTask(WebDriver driver){
		SeleniumUtil.click_ByCssSelector(driver, "span[id=\"gridcolumn-1020-textEl\"]");
		LoadingPage.Loading(driver);
	}
	
	
	public static String getTaskId(WebDriver driver,int index){
		String js = "return $('tbody>tr>td:nth-child(2)').eq("+String.valueOf(index)+").text().trim();";
		return CommonJQ.excuteJStoString(driver, js);
		
	}
	

	public static String clickFailureSelect(WebDriver driver) {
		clickTaskSelectBtN(driver,0);
		CommonJQ.click(driver, FAILURE_ID, false);
		if (getTaskNum(driver) > 0) {
			return getTaskId(driver, 0);
		} else {
			Assert.fail("无分析失败的用例 ，不能发起任务重试");
		}
		return "";
	}

	public static void clickTaskSelectBtN(WebDriver driver, int i) {
		CommonJQ.click(driver, STATUE_CSS, true, i);
		Pause.pause(500);
	}
	//按照任务名称选择任务
	public static void selectTaskByStatus(WebDriver driver, String  status) {
		clickTaskSelectBtN(driver, 0);
		String js = "$('#mshowtaskStatus').find('li:contains("+status+")').click();";
		CommonJQ.excuteJS(driver, js);
		Pause.pause(500);
		LoadingPage.Loading(driver);
	}
	
	public static void selectTaskByExpertStatus(WebDriver driver, String  expertstatus) {
		clickTaskSelectBtN(driver, 1);
		String js = "$('#mshowtaskExpertStatus').find('li:contains("+expertstatus+")').click();";
		CommonJQ.excuteJS(driver, js);
		Pause.pause(500);
		LoadingPage.Loading(driver);
	}
	
	// 获取所有任务状态信息
	
	public static ArrayList<String> getAllTaskStatus(WebDriver driver) {
		ArrayList<String> AllTaskStatus = new ArrayList<String>();
		int Items = getTaskNum(driver);
		for (int i = 0; i < Items; i++) {
			String temp = getTaskStatusByRow(driver, i);
			AllTaskStatus.add(temp);
		}
		return AllTaskStatus;
	}
	
	public static String getTaskNameByRow(WebDriver driver,int index){
		String script = "return $('tbody>tr>td:nth-child(4)').eq("+String.valueOf(index)+").text().trim();";
		return CommonJQ.excuteJStoString(driver, script);		
	}
	
	public static String getTaskStatusByRow(WebDriver driver,int index){
		String script = "return $('tbody>tr>td:nth-child(5)').eq("+String.valueOf(index)+").text().trim();";
		return CommonJQ.excuteJStoString(driver, script);		
	}
	
	public static String getTaskExpertStatusByRow(WebDriver driver,int index){
		String script = "return $('tbody>tr>td:nth-child(6)').eq("+String.valueOf(index)+").text().trim();";
		return CommonJQ.excuteJStoString(driver, script);		
	}
	
	//
	public static ArrayList<String> getAllTaskExpertStatus(WebDriver driver) {
		ArrayList<String> AllTaskExpertStatus = new ArrayList<String>();
		int Items = getTaskNum(driver);
		for (int i = 0; i < Items; i++) {
			String temp = getTaskExpertStatusByRow(driver, i);
			AllTaskExpertStatus.add(temp);
		}
		return AllTaskExpertStatus;
	}
	// 获取当前页任务数量
	public static int getTaskNum(WebDriver driver) {
		return CommonJQ.length(driver, FAILURE_CHECKBOX_CSS, true);		
	}

	private static final String SEARCHID_ID = "#searchId";
	private static final String TASKRETRY_ID = "#taskRetry";

	public static void clickTaskRebuild(WebDriver driver, String id) {
		CommonJQ.click(driver, ALL_ID, false);
		searchTask(driver, id);
		CommonJQ.click(driver, FAILURE_CHECKBOX_CSS, true, 0);
		CommonJQ.click(driver, TASKRETRY_ID, false);
	}
	
	public static void searchTask(WebDriver driver, String id) {
		CommonJQ.value(driver, SEARCHID_ID, id);
		CommonJQ.click(driver, SEARCHID_ID + "~", true);
		Pause.pause(500);
	}
	
	
	
	
	
	

	private static final String STATUS_VALUE_CSS = "tbody > tr > td";

	public static void compartFirstStatue(WebDriver driver) {
		LoadingPage.Loading(driver);
		Pause.pause(2000);
		String value = CommonJQ.text(driver, STATUS_VALUE_CSS, "", 5);
		if (!StringUtils.equals(value, LTE_VMOS_Const.WaitForAnalysis)) {
			Assert.fail("任务重试失败！");
		}
	}

	private static final String totalTaskNum =".total-item.mr5.pl10 em";
	public static int getAllTaskNum(WebDriver driver) {
		String number =CommonJQ.text(driver, totalTaskNum, "");
		return Integer.parseInt(number);
	}
	
	
	public static void clickDownLoadReport(WebDriver driver,int index){
		CommonJQ.click(driver, "#showDownLoad", true,0);
	}
	
	public static String getDownLoadReportName(WebDriver driver,int index){
		String js = "return $('.taskReportTab').find('ul.ng-scope').find('li.col2').eq(0).text().trim();";
		return CommonJQ.excuteJStoString(driver, js);		 
	}
	
	// 获取可选状态选项
	public static ArrayList<String> getHelpTitleItems(WebDriver driver) {
		ArrayList<String> helpItems = new ArrayList<String>();
		int Items = CommonJQ.excuteJStoInt(driver,
				"return $('#mshowflowTitle').find('li').length;");
		for (int i = 0; i < Items; i++) {
			String script = "return $('#mshowtaskStatus').find('li').eq("
					+ String.valueOf(i) + ").text().trim();";
			String temp = CommonJQ.excuteJStoString(driver, script);
			helpItems.add(temp);
		}
		return helpItems;
	}

}
