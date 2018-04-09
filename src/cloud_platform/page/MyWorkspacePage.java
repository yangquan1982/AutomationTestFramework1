package cloud_platform.page;

import java.io.File;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.LOG;

import common.util.CommonJQ;
import common.util.SeleniumUtil;

public class MyWorkspacePage {

	
	/**
	 * 个人空间界面，选择界面的任务
	 * @param driver
	 * @param taskName
	 */
	public static void ChoseTask(WebDriver driver,String taskName)
	{
		if(taskName!=null)
		{
			String script = "$('div#left_nav').find('div').filter(function(){return $(this).text().trim()=='"+taskName+"'})";
			CommonJQ.click(driver, script);
		}
	}

	
	
	
	/**
	 * 设置任务状态值
	 * @param driver
	 * @param status
	 */
	public static void setTaskStatus(WebDriver driver,String status) 
	{
		LOG.info_testcase("设置任务状态值");
		CommonJQ.click(driver, "$('iframe').contents().find(\"ul#mshowtaskStatus li[id='"+getStatus(status)+"']\")");
	}


	/**
	 * 界面点击任务状态下拉框
	 * @param driver
	 * @param time 设定的要等待时间
	 * @param j  1代表任务状态 2代表专家求助状态
	 * @param string
	 */
	public static void clickDropdownList(WebDriver driver,int time,int j, String message) 
	{
		LOG.info_testcase("点击界面人物状态下拉框。");
		String script1 =  "$('iframe').contents().find('"+getTaskElements(j)+"').nextAll('span').click()";
		CommonJQ.excuteJS(driver, script1);
		for(int i = 0;i<time;i++)
		{
			String statusChange = CommonJQ.excuteJStoString(driver,"return $('iframe').contents().find('"+getTaskStatusElements(j)+"').css('display')");
			Pause.pause(1000);
			if(!statusChange.contains("block"))
			{
				continue;
			}else if(statusChange.contains("block"))
			{
				LOG.info_testcase("界面点击任务状态下拉框成功。");
				break;
			}else if(!statusChange.contains("block")&&(i+1)==time)
			{
				Assert.fail(message);
			}
		}
	}
	
	
	/**
	 * 获取界面元素的值
	 * @param j
	 * @return
	 */
	private static String  getTaskElements(int j) 
	{
		if(j==1){
			return "#texttaskStatus";
		}
		return "#textexpertHelpStatus";
	}
	
	
	/**
	 * 获取界面元素的值
	 * @param j
	 * @return
	 */
	private static String  getTaskStatusElements(int j) 
	{
		if(j==1)
		{
			return "#textexpertHelpStatus";
		}
		return "#mshowexpertHelpStatus";
	}
	
	/**
	 * 个人空间界面，获取对应的值
	 * @param status
	 * @return
	 */
	public static String getStatus(String status) 
	{
		String statusStr = "";
		
		if (status.equals("全部"))
		{
			statusStr = "ALL";
		}else if (status.equals("待系统分析"))
		{
			statusStr = "QUEUING";
		}else if (status.equals("系统分析中"))
		{
			statusStr = "DOING";
		}else if (status.equals("分析成功"))
		{
			statusStr = "SUCCESS";
		}else if (status.equals("分析失败"))
		{
			statusStr = "FAILURE";
		}else if (status.equals("尚未发起求助"))
		{
			statusStr = "1";
		}else if (status.equals("专家反馈"))
		{
			statusStr = "2";
		}else if (status.equals("数据追加"))
		{
			statusStr = "3";
		}else if (status.equals("用户确认"))
		{
			statusStr = "4";
		}else if (status.equals("流程结束"))
		{
			statusStr = "5";
		}
		return statusStr;
	}

	
	
	/**
	 * 设置专家任务状态
	 * @param driver
	 * @param exportsStatus
	 */
	public static void setExpertTaskStatus(WebDriver driver,
			String exportsStatus) 
	{
		//设置任务状态值
		LOG.info_testcase("设置任务状态值");
		CommonJQ.click(driver, "$('iframe').contents().find(\"ul#mshowexpertHelpStatus li[id='"+getStatus(exportsStatus)+"']\")");
	}



	/**
	 * 个人空间界面，设置时间
	 * @param driver 
	 * @param date
	 */
	public static void SetDate(WebDriver driver, String[] date) 
	{
		try 
		{
			CommonJQ.excuteJS(driver, "$('iframe#centoriframe').contents().find('input#date1').val(\""+ date[0] + "\")");
			CommonJQ.excuteJS(driver,"$('iframe#centoriframe').contents().find('input#date2').val(\""+ date[1] + "\")");
		} catch (Exception e) 
		{
			if(e.getMessage().contains("Unable to locate element"))
			{
				Assert.fail("在设置界面时间时，界面元素找不到！");
			}else{
				LOG.info_testcase(e.getMessage());
			}
		}
	}
	
	
	/**
	 * 个人空间界面，设置关键字
	 * @param driver 
	 * @param keyword
	 */
	public static void setKeywordWithoutFrame(WebDriver driver, String keyword) 
	{
		String scriptClick = "$('iframe').contents().find('#searchId')";
		CommonJQ.click(driver, scriptClick);
		
		try {
			String scriptClear = "$('iframe').contents().find('#searchId').val('')";
			String scriptSet = "$('iframe').contents().find('#searchId').val('"+keyword+"')";
			CommonJQ.excuteJS(driver, scriptClear);
			CommonJQ.excuteJS(driver, scriptSet);
		} catch (Exception e) {
			if(e.getMessage().contains("Unable to locate element")){
				Assert.fail("在设置关键字搜索时，界面元素不存在！");
			}
		}
		
		String scriptSendKey = "$('iframe').contents().find('#searchId').nextAll('span')";
		CommonJQ.click(driver, scriptSendKey);
	}


	/**
	 * 个人空间界面，点击搜索按钮
	 * @param driver 
	 */
	public static void clickSearchBotton(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('iframe#centoriframe').contents().find('label.inp_ser_1').find('span')");
		
	}


	/**
	 * 个人空间界面，获取界面任务数量
	 * @param driver
	 * @return
	 */
	public static String getReturnStr(WebDriver driver) 
	{
		String returnStr = null;
		try {
			String scriptNum = "return $('iframe[id=centoriframe]').contents().find('#gridview-1021-body').find('tr').length";
			returnStr = CommonJQ.excuteJStoString(driver, scriptNum);
		} catch (Exception e) {
			if(e.getMessage().contains("Unable to locate element"))
			{
				Assert.fail("获取界面任务数量时，定位元素失败！");
			}else{
				LOG.info_testcase(e.getMessage());
			}
		}
		return returnStr;
	}

	
	/**
	 * 个人空间界面，点击任务重试按钮
	 * @param driver
	 */
	public static void TaskTryAgain(WebDriver driver) 
	{
		String script = "$('iframe#centoriframe').contents().find(\"a span[id='tautologyTask']\")";
		CommonJQ.click(driver, script);
	}

	
	/**
	 * 个人空间界面，获取界面任务的数量
	 * @param driver
	 * @param searchKeyword
	 * @return
	 */
	public static String taskNum(WebDriver driver,String searchKeyword) 
	{
		String returnStr = null;
		try {
			String script = "return $('iframe[id=centoriframe]').contents().find('#gridview-1021-body').find('tr:contains("+searchKeyword+")').text()";
			returnStr = CommonJQ.excuteJStoString(driver, script);
		} catch (Exception e) {
			if(e.getMessage().contains("Unable to locate element"))
			{
				Assert.fail("获取界面任务数量时，定位元素失败！");
			}else{
				LOG.info_testcase(e.getMessage());
			}
		}
		return returnStr;
	}

	
	/**
	 * 个人空间界面，点击选择框
	 * @param driver 
	 * @param i
	 */
	public static void clickCheckbox(WebDriver driver, int i) 
	{
		String jselement = "$('iframe#centoriframe').contents().find('div#my_task_tab').find('div.x-grid-row-checker').eq("+ (i -1)+ ")";
		CommonJQ.click(driver, jselement);
		if (i>1)
		{
			String jselements = "$('iframe#centoriframe').contents().find('div#my_task_tab').find('div.x-grid-row-checker').eq("+ (i -2)+ ")";
			CommonJQ.click(driver, jselements);
		}
	}
	
	
	/**
	 * 个人空间界面，界面任务全选
	 * @param driver
	 * @param i
	 */
	public static void clickALLCheckbox(WebDriver driver, int i) 
	{
		//需要补充点击全选按钮
		try 
		{
			driver.switchTo().frame("centoriframe");
			SeleniumUtil.click_ByCssSelector(driver, "span[id=\"gridcolumn-1022-textEl\"]");
			LoadingPage.Loading(driver);
		} catch (Exception e) 
		{
			if(e.getMessage().contains("Unable to locate element"))
			{
				Assert.fail("在全选界面元素时，定位元素失败！");
			}
		}
	}
	
	
	/**
	 * 个人空间界面，获取临时存储目录的文件个数
	 * @param savePath
	 * @return
	 */
	public static int tempFileLength(String savePath) 
	{
		File[] fileList = new File(savePath).listFiles();
		return fileList.length;
	}


	/**
	 * 个人空间界面，点击批量下载按钮
	 * @param driver
	 */
	public static void TaskDownloadLogs(WebDriver driver) 
	{
		String script = "$('iframe#centoriframe').contents().find(\"a span[id='downLoadReportsSpan']\")";
		CommonJQ.click(driver, script);
	}


	/**
	 * 个人空间界面，获取任务的taskID
	 * @param driver
	 * @param searchKeyword
	 * @return
	 */
	public static String chooseTaskId(WebDriver driver,String searchKeyword) 
	{
		String taskID = null;
		try {
			String jscmd2 = "return $('iframe[id=centoriframe]').contents().find('#gridview-1021-body').find('tr:contains("+searchKeyword+")').find('td').eq(1).text()" ;
			taskID = CommonJQ.excuteJStoString(driver,jscmd2);
		} catch (Exception e) {
			if(e.getMessage().contains("Unable to locate element"))
			{
				Assert.fail("在获取任务的TaskId时，界面元素找不到！");
			}else{
				LOG.info_testcase(e.getMessage());
			}
		}
		return taskID;
	}


}
