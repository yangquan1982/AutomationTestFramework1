package cloud_platform.task.platformcommon.systemManage;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.systemManage.SystemManagePage;
import common.util.LOG;

public class SystemManageTaskBase{

	
	/**
	 * 权限申请按钮是否存在 
	 * @param driver
	 */
	public static void checkLimitApplayButtonIsExist(WebDriver driver) 
	{
		if(SystemManagePage.limitApplayButtonReturn(driver)>0)
		{
			LOG.info("界面申请权限按钮存在！");
		}else{
			Assert.fail("界面申请权限按钮不存在！");
		}
	}
	
	
	
	/**
	 * 界面平台消息是否存在 
	 * @param driver
	 */
	public static void checkPlatformMessageIsExist(WebDriver driver) 
	{
		if(SystemManagePage.platformMessageReturn(driver)>0)
		{
			LOG.info("界面平台消息模块存在！");
		}else{
			Assert.fail("界面平台消息模块不存在！");
		}
	}
	
	/**
	 * 界面个人信息是否存在 
	 * @param driver
	 */
	public static void personalMessageIsExist(WebDriver driver) 
	{
		if(SystemManagePage.personalMessageReturn(driver)>0)
		{
			LOG.info("界面个人消息模块存在！");
		}else{
			Assert.fail("界面个人消息模块不存在！");
		}
	}



	/**
	 * 验证界面项目经理设置是否存在
	 * @param driver
	 */
	public static void projectManagerIsExist(WebDriver driver) 
	{
		if(SystemManagePage.projectManagerReturn(driver)>0)
		{
			LOG.info("界面项目经理设置存在！");
		}else{
			Assert.fail("界面项目经理设置不存在！");
		}
		
	}
	
	
	/**
	 * 验证界面专家设置是否存在
	 * @param driver
	 */
	public static void expertIsExist(WebDriver driver) 
	{
		if(SystemManagePage.expertSetReturn(driver)>0)
		{
			LOG.info("界面专家设置存在！");
		}else{
			Assert.fail("界面项专家设置不存在！");
		}
		
	}
	
	
	/**
	 * 验证界面维护员设置是否存在
	 * @param driver
	 */
	public static void setMaintainIsExist(WebDriver driver) 
	{
		if(SystemManagePage.maintenanceReturn(driver)>0)
		{
			LOG.info("界面维护员设置存在！");
		}else{
			Assert.fail("界面维护员设置不存在！");
		}
		
	}
	
	
	/**
	 * 验证界面统计员设置是否存在
	 * @param driver
	 */
	public static void statisticianIsExist(WebDriver driver) 
	{
		if(SystemManagePage.statisticReturn(driver)>0)
		{
			LOG.info("界面统计员设置存在！");
		}else{
			Assert.fail("界面统计员设置不存在！");
		}
		
	}
	
	
	
	/**
	 * 验证界面专家流程设置是否存在
	 * @param driver
	 */
	public static void expertProcessSetIsExist(WebDriver driver) 
	{
		if(SystemManagePage.expertProcessSetReturn(driver)>0)
		{
			LOG.info("界面专家流程设置存在！");
		}else{
			Assert.fail("界面专家流程设置存在！");
		}
		
	}
	

}
