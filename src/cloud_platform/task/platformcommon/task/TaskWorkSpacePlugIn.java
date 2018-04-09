package cloud_platform.task.platformcommon.task;

import java.io.File;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.MyWorkspacePage;
import cloud_plugin.page.PlugInPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.TaskViewPluginTask;
import common.util.CommonJQ;
import common.util.LOG;

public class TaskWorkSpacePlugIn {

	
	
	public static void SearchPlugIn(WebDriver driver, String taskStatus, String exportsStatus, 
			String[] buildData, String searchKeyword) 
	{
		if (taskStatus != null)
		{
			PlugInPage.filterStatusPlugIn(driver,taskStatus); //过滤任务状态
		}
		if (exportsStatus != null)
		{
			PlugInPage.filterExportsStatusPlugIn(driver,exportsStatus);//过滤专家求助状态
		}
		if (buildData != null)
		{
			PlugInPage.SetDatePlugIn(driver,buildData);//过滤任务创建时间
		}
		if (searchKeyword != null)
		{
			PlugInPage.setKeywordPlugIn(driver,searchKeyword);//关键字过滤
		}
		 //点击搜索按钮
		CommonJQ.click(driver, TaskReportPage.SearchBtn, true);
	}
	
	

	/**
	 * 选择任务
	 * @param driver
	 * @param searchKeyword 
	 */
	public static void clickTaskPlugIn(WebDriver driver, String searchKeyword,int i) 
	{
		//查看界面任务个数
		String taskNum = CommonJQ.text(driver, "#page_data span[class = \"total-item mr5 pl10\"]", "em", true);
		LOG.info_testcase("返回值为："+taskNum );
		if("0".equalsIgnoreCase(taskNum)){
			Assert.fail("依照关键字:\""+searchKeyword+"\"搜索时，界面列表中无此任务！");
		}else{
			CommonJQ.click(driver, "tbody#gridview-1019-body div.x-grid-row-checker", true, 0, "");
		}
	}
	
	
	/**
	 * 点击界面选择第一个box
	 * @param driver 
	 * @param i
	 */
	public static void chooseCheckBoxPlugIn(WebDriver driver, int i) 
	{
		if (i == 0)
		{
			MyWorkspacePage.clickALLCheckbox(driver,i);
		}
		else 
		{
			for (int j = 1; j <= i; j++) 
			{
				PlugInPage.clickCheckboxPlugIn(driver,j);
			}
		}
	}

	
	
	/**
	 * 下载任务包日志
	 * @param driver
	 * @param savePath
	 */
	public static boolean clickTaskPackageLogPlugIn(WebDriver driver, String savePath) 
	{
		//获取temp路径下的文件个数
		int tempNum = MyWorkspacePage.tempFileLength(savePath);
		System.out.print("下载之前文件的个数是"+tempNum);
		LOG.info_testcase("下载之前文件的个数是"+tempNum);
		
		//点击批量下载的按钮
		PlugInPage.taskDownloadsLogsPlugIn(driver);
		
		//等待任务下载完成
		TaskViewPluginTask.waittingDownLoadFile(savePath,"Report");
		boolean downloadResult = waitingDownloadFiles(savePath,".zip.part",tempNum); 
		
		//当下载任务数量不大于下载之前的文件个数，该 任务失败
		return TaskWorkSpace.returnResult(!downloadResult,"批量下载的功能有问题","界面批量下载功能正常");
	}
	
	
	/**
	 * 等待界面任务下载
	 * @param savePath
	 * @param tempNum 
	 * @param string
	 */
	public static boolean waitingDownloadFiles(String savePath, String endsStr, int tempNum) 
	{
		for(int i = 0;i<60;i++)
		{
			int num = 0;
			for (File child : new File(savePath).listFiles()) 
			{
				if(child.getName().endsWith(endsStr)){
					break;
				}else{
					num ++;
				}
			}
			if(num==new File(savePath).listFiles().length&&num>tempNum)
			{
				LOG.info_testcase("任务下载成功！");
				return true;
			}
			Pause.pause(1000);
		}
		System.out.print("下载后的文件个数为："+new File(savePath).listFiles().length);
		LOG.info_testcase("下载后的文件个数为："+new File(savePath).listFiles().length);
		return new File(savePath).listFiles().length>tempNum;
	}



	/**
	 *点击删除任务按钮 
	 * @param driver
	 * @param taskID 
	 */
	public static void deleteTaskPlugIn(WebDriver driver, String taskID) 
	{
		//点击删除按钮
		String script = "$('.r_container_conn').find(\"a span[id='deleteTaskSpan']\")";
		CommonJQ.click(driver, script);
		String delete = "$(\"span span[class='l-btn-text']\")";
		CommonJQ.click(driver, delete);
		
		//关键字过滤
		TaskWorkSpace.setKeyword(driver,taskID);
		
		//依据选择的任务的id，判断已删除的任务是否已被删除
		String scriptReturn = "return $('.r_container_conn').find('tr:contains("+taskID+")').eq(1).text()";
		String returnStr = CommonJQ.excuteJStoString(driver, scriptReturn);
		
		TaskWorkSpace.returnResult(!returnStr.equals(""),"选中的任务未被删除","界面删除功能正常!");
	}
	
	
	
}
