package cloud_plugin.page;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.MyWorkspacePage;
import cloud_platform.task.platformcommon.task.TaskWorkSpace;
import common.util.LOG;

import common.util.CommonJQ;

public class PlugInPage {

	/**
	 * 过滤插件中的任务
	 * 
	 * @param driver
	 * @param status
	 * @param work
	 */
	public static void filterStatusPlugIn(WebDriver driver, String status) {
		// 点击界面显示下拉框
		clickDropdownList(driver, 30, 1, "下拉框点击失败！");

		// 设置任务状态值
		CommonJQ.click(driver, "#btn_group", "ul#mshowtaskStatus li[id='" + MyWorkspacePage.getStatus(status) + "']",
				true, "界面任务状态");

	}

	/**
	 * 界面点击任务状态下拉框
	 * 
	 * @param driver
	 * @param time
	 *            设定的要等待时间
	 * @param j
	 *            1代表任务状态 2代表专家求助状态
	 * @param string
	 */
	private static void clickDropdownList(WebDriver driver, int time, int j, String message) {
		String script1 = "$('#btn_group').find('" + getTaskElements(j) + "').nextAll('span')";
		CommonJQ.click(driver, script1);
		for (int i = 0; i < time; i++) {
			String statusChange = CommonJQ.excuteJStoString(driver,
					"return $('iframe').contents().find('" + getTaskStatusElements(j) + "').css('display')");
			Pause.pause(1000);
			if (!statusChange.contains("block")) {
				continue;
			} else if (statusChange.contains("block")) {
				LOG.info_testcase("界面点击任务状态下拉框成功。");
				break;
			} else if (!statusChange.contains("block") && (i + 1) == time) {
				Assert.fail(message);
			}
		}
	}

	/**
	 * 获取界面元素的值
	 * 
	 * @param j
	 * @return
	 */
	private static String getTaskElements(int j) {
		if (j == 1) {
			return "#texttaskStatus";
		}
		return "#textexpertHelpStatus";
	}

	/**
	 * 获取界面元素的值
	 * 
	 * @param j
	 * @return
	 */
	private static String getTaskStatusElements(int j) {
		if (j == 1) {
			return "#mshowtaskStatus";
		}
		return "#mshowexpertHelpStatus";
	}

	/**
	 * 进入插件中 选择专家求助状态
	 * 
	 * @param driver
	 * @param exportsStatus
	 */
	public static void filterExportsStatusPlugIn(WebDriver driver, String exportsStatus) {
		// 点击界面显示下拉框
		clickDropdownList(driver, 30, 2, "下拉框点击失败！");

		// 设置任务状态值
		CommonJQ.click(driver, "#btn_group",
				"ul#mshowtaskExpertStatus li[id='" + MyWorkspacePage.getStatus(exportsStatus) + "']", true, "界面专家任务状态");
	}

	/**
	 * 设置时间
	 * 
	 * @param driver
	 * @param date
	 */
	public static void SetDatePlugIn(WebDriver driver, String[] date) {
		try {
			CommonJQ.excuteJS(driver, "$('div#btn_group').find('input#date1').val(\"" + date[0] + "\")");
			CommonJQ.excuteJS(driver, "$('div#btn_group').find('input#date2').val(\"" + date[1] + "\")");
		} catch (Exception e) {
			if (e.getMessage().contains("Unable to locate element")) {
				Assert.fail("找不到设置时间的界面元素！");
			} else {
				LOG.info_testcase(e.getMessage());
			}

		}
	}

	/**
	 * 设置关键字
	 * 
	 * @param driver
	 * @param keyword
	 */
	public static void setKeywordPlugIn(WebDriver driver, String keyword) {
		// 清除默认值
		try {
			CommonJQ.click(driver, "$('div#btn_group').find('#searchId')");
			String scriptClear = "$('div#btn_group').find('#searchId').val('')";
			String scriptSet = "$('div#btn_group').find('#searchId').val('" + keyword + "')";

			CommonJQ.excuteJS(driver, scriptClear);
			CommonJQ.excuteJS(driver, scriptSet);
		} catch (Exception e) {
			if (e.getMessage().contains("Unable to locate element")) {
				Assert.fail("检索的元素不存在！");
			} else {
				LOG.info_testcase(e.getMessage());
			}
		}

		// 设置关键字
		String scriptSendKey = "$('div#btn_group').find('#searchId').nextAll('span')";
		CommonJQ.click(driver, scriptSendKey);
	}

	/**
	 * 点击搜索按钮
	 * 
	 * @param driver
	 */
	public static void searchBottonPlugIn(WebDriver driver) {
		CommonJQ.click(driver, "$('iframe#centoriframe').contents().find('label.inp_ser_1').find('span')");
	}

	/**
	 * 获取界面任务的数量
	 * 
	 * @param driver
	 * @param searchKeyword
	 * @return
	 */
	public static String taskNum(WebDriver driver, String searchKeyword) {
		String script = "return $('tbody#gridview-1019-body').find('tr:contains(" + searchKeyword + ")').text()";
		String returnStr = CommonJQ.excuteJStoString(driver, script);
		return returnStr;
	}

	/**
	 * 界面任务选择
	 * 
	 * @param driver
	 * @param j
	 */
	public static void clickCheckboxPlugIn(WebDriver driver, int j) {
		CommonJQ.click(driver, "$('tbody#gridview-1019-body').find('div.x-grid-row-checker').eq(" + (j - 1) + ")");
		if (j > 1) {
			Pause.pause(1000);
			CommonJQ.click(driver, "$('tbody#gridview-1019-body').find('div.x-grid-row-checker').eq(" + (j - 2) + ")");
		}
	}

	/**
	 * 插件界面，点击任务重试按钮
	 * 
	 * @param driver
	 */
	public static boolean clickTaskAgianPlugIn(WebDriver driver) {
		boolean b = false;
		String scriptNum = "return $(\"#page_data\").find(\"span[class='total-item mr5 pl10'] em\").text()";
		String NumBeforeClick = CommonJQ.excuteJStoString(driver, scriptNum);

		String script = "$('.r_container_conn').find(\"#taskRetry span\")";
		CommonJQ.click(driver, script);
		Pause.pause(2000);

		String NumAfterClick = CommonJQ.excuteJStoString(driver, scriptNum);
		LOG.info_testcase("点击任务重试之前界面任务个数：" + NumBeforeClick + ";点击任务重试之后界面任务个数为：" + NumAfterClick);

		b = TaskWorkSpace.returnResult(NumBeforeClick.equals(NumAfterClick), "点击任务重试后，界面任务未开始执行任务重试功能，请检查该功能！",
				"界面批量任务重试功能正常！");
		return b;
	}

	/**
	 * 获取界面任务的id
	 * 
	 * @param driver
	 */
	public static String getTaskId(WebDriver driver) {
		String taskID = null;
		try {
			String jscmd2 = "return $('#gridview-1019-body').find('div.x-grid-cell-inner').eq(1).text()";
			taskID = CommonJQ.excuteJStoString(driver, jscmd2);
		} catch (Exception e) {
			if (e.getMessage().contains("Unable to locate element")) {
				Assert.fail("在获取任务的taskId时，界面元素找不到！");
			}
		}
		return taskID;
	}

	/**
	 * 电击批量下载按钮
	 * 
	 * @param driver
	 */
	public static void taskDownloadsLogsPlugIn(WebDriver driver) {
		String script = "$(\".r_container_conn\").find(\"#downLoadReports span\")";
		CommonJQ.click(driver, script);
	}

}
