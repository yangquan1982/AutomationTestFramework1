package cloud_platform.task.platformcommon.task;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.MyWorkspacePage;
import common.util.CommonJQ;
import common.util.ImageUtil;
import common.util.LOG;

public class TaskWorkSpace {
	
	
	/**
	 * 在我的工作空间--我的任务列表中搜素
	 * @param driver 
	 * @param searchKeyword 
	 * @param buildData 
	 * @param exportsStatus 
	 * @param taskStatus 
	 * @param status
	 * @param exportstatus
	 * @param data
	 * @param keyword
	 */
	public static void Search(WebDriver driver, String taskStatus, String exportsStatus, 
			String[] buildData, String searchKeyword) 
	{
		if (taskStatus != null)
		{
			filterStatus(driver,taskStatus); //过滤任务状态
		}
		if (exportsStatus != null)
		{
			filterExportsStatus(driver,exportsStatus);//过滤专家求助状态
		}
		if (buildData != null)
		{
			MyWorkspacePage.SetDate(driver,buildData);//过滤任务创建时间
		}
		if (searchKeyword != null)
		{
			setKeyword(driver,searchKeyword);//关键字过滤
		}
		MyWorkspacePage.clickSearchBotton(driver); //点击搜索按钮
	}
	
	

	/**
	 * 进入个人空间页面
	 * @param driver 
	 * @param status
	 */
	public static void filterStatus(WebDriver driver, String status) 
	{
		//30为界面等待时间
		//1 设置为1表示此时任务内容为设置界面任务状态
		filterStatusWithoutFrame(driver,30,1,status);
	}
	
	
	/**
	 * 获取个人工作空间的报告状态
	 * @param driver 
	 * @param status
	 */
	public static void filterStatusWithoutFrame(WebDriver driver,int time,int j,String status) 
	{
		//点击界面显示下拉框
		MyWorkspacePage.clickDropdownList(driver,30,1,"下拉框点击失败。");
		
		//设置任务状态
		MyWorkspacePage.setTaskStatus(driver,status);
	}
	
	
	/**
	 * 设置关键字搜索
	 * @param driver 
	 * @param keyword
	 */
	public static void setKeyword(WebDriver driver, String keyword)
	{
		//设置关键字搜索
		MyWorkspacePage.setKeywordWithoutFrame(driver,keyword);
		
		//点击搜索按钮
		MyWorkspacePage.clickSearchBotton(driver); 
	}
	
	

	
	/**
	 * 选择任务
	 * @param driver
	 * @param searchKeyword 
	 */
	public static void clickTask(WebDriver driver, String searchKeyword,int i) 
	{
		//给checkBox设置为选择状态
		String returnTaskNum = MyWorkspacePage.taskNum(driver,searchKeyword);
		LOG.info_testcase("返回值为："+returnTaskNum );
		
		if(!"".equals(returnTaskNum))
		{
			chooseCheckBox(driver,i);
		}else
		{
			Assert.fail("依照关键字"+searchKeyword+"搜索时，界面列表中无此任务！");
		}
	}
	
	
	/**
	 * 点击界面选择第一个box
	 * @param driver 
	 * @param i
	 */
	public static void chooseCheckBox(WebDriver driver, int i) 
	{
		if (i == 0)
		{
			MyWorkspacePage.clickALLCheckbox(driver,i);
		}
		else 
		{
			for (int j = 1; j <= i; j++) 
			{
				MyWorkspacePage.clickCheckbox(driver,j);
			}
		}
	}
	
	
	
	/**
	 * 点击任务重试按钮
	 * @param driver
	 */
	public static void clickTaskAgian(WebDriver driver) 
	{
		boolean b = false;
		String NumBeforeClick = MyWorkspacePage.getReturnStr(driver); //获取点击任务重试之前界面的任务数量
		MyWorkspacePage.TaskTryAgain(driver);//电击任务重试按钮
		
		String NumAfterClick = MyWorkspacePage.getReturnStr(driver); //获取点击任务重试之后，界面的任务数量
		LOG.info_testcase("点击任务重试之前界面任务个数："+NumAfterClick+";点击任务重试之后界面任务个数为："+NumAfterClick);
		returnResult(NumBeforeClick.equals(NumAfterClick),"点击任务重试后，界面任务未开始执行任务重试功能，请检查该功能","界面任务重试功能正常");
	}

	
	/**
	 * 返回结果值
	 * @param equals
	 * @param errorStr
	 * @param rightStr
	 * @return
	 */
	public static boolean returnResult(boolean equals, String errorStr,
			String rightStr) {
		if(equals)
		{
			Assert.fail(errorStr);
			return false;
		}else{
			LOG.info_testcase(rightStr);
			return true;
		}
	}

	/**
	 * 下载任务包日志
	 * @param driver
	 * @param savePath
	 */
	public static boolean clickTaskPackageLog(WebDriver driver, String savePath) 
	{
		//获取temp路径下的文件数量
		int tempNum = MyWorkspacePage.tempFileLength(savePath);
		System.out.print("下载之前文件的个数是"+tempNum);
		LOG.info_testcase("下载之前文件的个数是"+tempNum);
		
		//点击批量下载的按钮
		MyWorkspacePage.TaskDownloadLogs(driver);
		
		//等待任务下载完成
		boolean downloadResult = TaskWorkSpacePlugIn.waitingDownloadFiles(savePath,".zip.part",tempNum); 

		return returnResult(!downloadResult,"批量下载的功能有问题","界面批量下载功能正常");
	}

	
	
	/**
	 *点击删除任务按钮 
	 * @param driver
	 * @param taskID 
	 */
	public static void deleteTask(WebDriver driver, String taskID) 
	{
		try 
		{
			//点击删除按钮
			String script = "$('iframe#centoriframe').contents().find(\"a span[id='deleteTaskSpan']\")";
			CommonJQ.click(driver, script);
			ImageUtil.clickImage("enter.jpg");
			
			//关键字过滤
			setKeyword(driver,taskID);
			
			//依据选择的任务的id，判断已删除的任务是否已被删除
			String scriptReturn = "return $('iframe[id=centoriframe]').contents().find('#gridview-1021-body').find('tr:contains("+taskID+")').text()";
			String returnStr = CommonJQ.excuteJStoString(driver, scriptReturn);
			returnResult(!returnStr.equals(""),"界面删除按钮有问题","界面任务删除功能正常");
		} catch (Exception e) 
		{
			if(e.getMessage().contains("")){
				Assert.fail("删除任务时，界面元素定位失败！");
			}else{
				LOG.info_testcase(e.getMessage());
			}
		}
	}
	
	
	
	/**
	 * 进入个人空间 选择专家求助状态
	 * @param driver
	 * @param exportsStatus
	 */
	public static void filterExportsStatus(WebDriver driver,
			String exportsStatus) 
	{
		//点击界面显示下拉框
		MyWorkspacePage.clickDropdownList(driver,30,2,"下拉框点击失败。");
		
		//设置专家求助装tia
		MyWorkspacePage.setExpertTaskStatus(driver,exportsStatus);
	}
	
	
	
}
