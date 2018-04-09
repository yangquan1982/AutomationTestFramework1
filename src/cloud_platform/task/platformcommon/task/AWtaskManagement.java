package cloud_platform.task.platformcommon.task;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import cloud_platform.page.MyWorkspacePage;
import cloud_plugin.page.PlugInPage;
import cloud_public.page.LoadingPage;
import common.constant.system.EnvConstant;
import common.util.FileHandle;
import common.util.LOG;

public class AWtaskManagement {
	

	/**
	 * 依据界面具体入口，来验证界面功能
	 * @param workContent 
	 *        值为personalWorkspace时，处理入口为个人空间
	 *        值不为personalWorkspace时，处理入口为插件
	 * @param driver
	 */
	public static void DealwithTask(WebDriver driver,String taskName ) 
	{
//		taskName = "待处理的任务(0)";
		taskName = null;
		String taskStatus= "分析成功";
		String exportsStatus ="全部";
		String [] buildData = {"2016-10-26 00:00:00","2016-11-02 23:59:59"};
		String searchKeyword ="";
		if("personalWorkspace".equals(taskName))
		{
			//验证任务重试功能
			DealwithTaskAgain_Workspace(driver,taskName ,taskStatus, exportsStatus,buildData,searchKeyword);
			
			//在我的空间中下载日志包文件
			DownloadLogPackageFile_Workspace(driver,taskName ,taskStatus, exportsStatus,buildData,searchKeyword);
			
//			//在我的工作空间中批量下载报告
			DownloadFileBatch_Workspace(driver ,taskName ,taskStatus, exportsStatus,buildData,searchKeyword);
			
			//在我的工作空间删除任务
			DeleteTask_Workspace(driver ,taskName ,taskStatus, exportsStatus,buildData,searchKeyword);
			
		}else
		{
			System.out.print("进入到插件界面");
			//删除任务
			DeleteTask_PlugInComponents(driver,taskStatus,exportsStatus,buildData,searchKeyword);
			
			//批量下载报告
			DownloadFileBatch_PlugInComponentse(driver,taskStatus,exportsStatus,buildData,searchKeyword);
			
			//任务重试
			DealwithTaskAgain_PlugInComponents(driver,taskStatus,exportsStatus,buildData,searchKeyword);
		}
		
	}
	
	


	/**
	 * 个人空间中批量下载报告
	 * @param d
	 * @param searchKeyword 
	 * @param buildData 
	 * @param exportsStatus 
	 * @param taskStatus 
	 * @param taskName 
	 */
	private static void DownloadFileBatch_Workspace(WebDriver driver, String taskName, 
			String taskStatus, String exportsStatus, String[] buildData, String searchKeyword) 
	{
		LOG.info_testcase("在我的工作空间批量下载报告");
		//选择工作空间的任务内容
		MyWorkspacePage.ChoseTask(driver,taskName);
		
		//过滤界面内容
		TaskWorkSpace.Search(driver,taskStatus,exportsStatus,buildData,searchKeyword);
		
		//将过滤后的任务多选
		TaskWorkSpace.clickTask(driver,searchKeyword,2);
		
		//点击批量下载
		TaskWorkSpace.clickTaskPackageLog(driver,EnvConstant.Path_DownLoad);
		
	}



	/**
	 * 个人空间中处理下载日志包文件
	 * @param driver
	 * @param searchKeyword 
	 * @param buildData 
	 * @param exportsStatus 
	 * @param taskStatus 
	 * @param taskName 
	 */
	public static void DownloadLogPackageFile_Workspace(WebDriver driver, String taskName, String taskStatus,
			String exportsStatus, String[] buildData, String searchKeyword) 
	{
		LOG.info_testcase("在我的工作空间中下载日志包文件");
		//选择工作空间的任务内容
		MyWorkspacePage.ChoseTask(driver,taskName);
		
		//过滤界面内容
		TaskWorkSpace.Search(driver,taskStatus,exportsStatus,buildData,searchKeyword);
		
		//选择第一个任务
		TaskWorkSpace.clickTask(driver,searchKeyword,1);
		
		//点击下载任务包按钮  记得处理清空数据
		TaskWorkSpace.clickTaskPackageLog(driver,EnvConstant.Path_DownLoad);
	}
	
	

	/**
	 * 个人空间中处理任务删除功能
	 * @param driver
	 */
	private static void DeleteTask_Workspace(WebDriver driver, String taskName, String taskStatus, String exportsStatus,
			String[] buildData, String searchKeyword) 
	{
		LOG.info_testcase("在我的工作空间删除任务");
		//选择工作空间的任务内容
		MyWorkspacePage.ChoseTask(driver,taskName);
		
		//过滤界面内容
		TaskWorkSpace.Search(driver,taskStatus,exportsStatus,buildData,searchKeyword);
		
		//将过滤后的任务选择第一个
		TaskWorkSpace.clickTask(driver,searchKeyword,1);
	    
		//获取任务的TaskId
		String taskID = MyWorkspacePage.chooseTaskId(driver,searchKeyword);
		
		//点击删除按钮
		TaskWorkSpace.deleteTask(driver,taskID);
	}

	
	
	/**
	 * 个人空间中处理任务重试功能
	 * @param driver
	 * @param taskStatus
	 * @param exportsStatus 专家求助状态
	 * @param buildData界面搜索时间
	 * @param searchKeyword 搜索关键字
	 */
	public static void DealwithTaskAgain_Workspace( WebDriver driver,String taskName,String taskStatus,String exportsStatus,
			String [] buildData,String searchKeyword) 
	{
		LOG.info_testcase("在我的工作空间中进行单站指标统计任务重试功能");
		LoadingPage.Loading(driver);
		
		//选择工作空间的任务内容
		MyWorkspacePage.ChoseTask(driver,taskName);
		
		//过滤界面内容
		TaskWorkSpace.Search(driver,taskStatus,exportsStatus,buildData,searchKeyword);
		
		//选择第一个任务
		TaskWorkSpace.clickTask(driver,searchKeyword,1);
		
		//点击任务重试按钮
		TaskWorkSpace.clickTaskAgian(driver);
		
	}

	


	/**
	 * 从插件进入任务列表界面，处理任务删除功能
	 * @param driver
	 * @param taskName 
	 * @param searchKeyword 
	 * @param buildData 
	 * @param exportsStatus 
	 * @param taskStatus 
	 */
	private static void DeleteTask_PlugInComponents(WebDriver driver, String taskStatus, String exportsStatus, String[] buildData, String searchKeyword) 
	{
		LOG.info_testcase("从插件进入任务列表界面,处理删除任务");
		
		//过滤界面内容
		TaskWorkSpacePlugIn.SearchPlugIn(driver,taskStatus,exportsStatus,buildData,searchKeyword);
		
		//选择单个任务
		TaskWorkSpacePlugIn.clickTaskPlugIn(driver,searchKeyword,1);
		
		//获取已选择任务的taskId
		String taskId = PlugInPage.getTaskId(driver);
		
		//点击删除按钮
		TaskWorkSpacePlugIn.deleteTaskPlugIn(driver,taskId);
	}

	
	/**
	 * 从插件进入任务列表界面，处理任务重试功能
	 * @param driver
	 * @param searchKeyword 
	 * @param buildData 
	 * @param exportsStatus 
	 * @param taskStatus 
	 */
	private static void DealwithTaskAgain_PlugInComponents(WebDriver driver, String taskStatus, String exportsStatus, 
			String[] buildData, String searchKeyword) 
	{
		LOG.info_testcase("从插件进入任务列表界面,处理任务重试功能");
		//过滤界面内容
		TaskWorkSpacePlugIn.SearchPlugIn(driver,taskStatus,exportsStatus,buildData,searchKeyword);
		
		//选择第一个任务
		TaskWorkSpacePlugIn.clickTaskPlugIn(driver,searchKeyword,1);
		
		//点击任务重试按钮
		PlugInPage.clickTaskAgianPlugIn(driver);
}


	/**
	 * 从插件进入任务列表界面,处理批量下载报告的功能
	 * @param driver
	 * @param taskName 
	 * @param searchKeyword 
	 * @param buildData 
	 * @param exportsStatus 
	 * @param taskStatus 
	 */
	public static void DownloadFileBatch_PlugInComponentse(WebDriver driver, String taskStatus, String exportsStatus, String[] buildData, 
			String searchKeyword) {
		//
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LOG.info_testcase("从插件进入任务列表界面,处理批量下载报告!");
		//过滤界面内容
		TaskWorkSpacePlugIn.SearchPlugIn(driver,taskStatus,exportsStatus,buildData,searchKeyword);
		
		//选择多个任务
		TaskWorkSpacePlugIn.clickTaskPlugIn(driver,searchKeyword,2);
		
		//点击批量下载

		boolean b = TaskWorkSpacePlugIn.clickTaskPackageLogPlugIn(driver,EnvConstant.Path_DownLoad);
		Assert.assertTrue(b, "批量下载失败!");
	}

			
}
