package cloud_platform.page;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.LOG;

import common.util.CommonJQ;

public class SystemManagePage {
	
	
	/**
	 * 权限申请按钮是否存在
	 * @param driver
	 */
	public static int limitApplayButtonReturn(WebDriver driver) 
	{
		String  button = CommonJQ.excuteJStoString(driver, "return $('iframe').contents().find('a.btn_blue_1 span').length");
		return Integer.parseInt(button);
	}
	

	/**
	 * 平台消息也签是否存在  
	 * @param driver
	 */
	public static int  platformMessageReturn(WebDriver driver) 
	{
		int messageReturn = CommonJQ.length(driver, "#announcement");
		return messageReturn;
	}
	
	
	/**
	 * 我的权限是否存在  
	 * @param driver
	 */
	public static int personalMessageReturn(WebDriver driver) 
	{
		int messageReturn = CommonJQ.length(driver, "#myPopedom");
		return messageReturn;
	}

	/**
	 * 项目经理设置是否存在 
	 * @param driver
	 * @return
	 */
	public static int projectManagerReturn(WebDriver driver) 
	{
		int projectManagerReturn = CommonJQ.length(driver, "#setPManager");
		return projectManagerReturn;
	}
	
	/**
	 * 专家设置是否存在 
	 * @param driver
	 * @return
	 */
	public static int expertSetReturn(WebDriver driver) 
	{
		int expertSetReturn = CommonJQ.length(driver, "#setExpert");
		return expertSetReturn;
	}
	
	/**
	 * 维护员设置是否存在 
	 * @param driver
	 * @return
	 */
	public static int maintenanceReturn(WebDriver driver) 
	{
		int maintenanceReturn = CommonJQ.length(driver, "#setMaintain");
		return maintenanceReturn;
	}
	
	/**
	 * 统计员设置是否存在 
	 * @param driver
	 * @return
	 */
	public static int statisticReturn(WebDriver driver) 
	{
		int statisticReturn = CommonJQ.length(driver, "#statistician");
		return statisticReturn;
	}
	
	
	/**
	 * 专家流程设置是否存在   
	 * @param driver
	 * @return
	 */
	public static int expertProcessSetReturn(WebDriver driver) 
	{
		int expertProcessSetReturn = CommonJQ.length(driver, "#setReportAnalysis");
		return expertProcessSetReturn;
	}
	
	/**
	 * 添加项目经理按钮是否存在   
	 * @param driver
	 * @return
	 */
	public static int addProjectButtonReturn(WebDriver driver) 
	{
		int expertProcessSetReturn = CommonJQ.length(driver, "#addSManagerBtn");
		return expertProcessSetReturn;
	}


	/**
	 * 点击项目经理设置
	 * @param driver
	 */
	public static void clickProjectManagerSet(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('#setPManager')");
		LoadingPage.Loading(driver);
		
	}
	
	/**
	 * 点击专家设置
	 * @param driver
	 */
	public static void clickExpertSet(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('#setExpert')");
		LoadingPage.Loading(driver);
		
	}
	
	/**
	 * 点击维护员设置
	 * @param driver
	 */
	public static void clickMaintainSet(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('#setMaintain')");
		LoadingPage.Loading(driver);
		
	}
	
	
	/**
	 * 点击统计员设置
	 * @param driver
	 */
	public static void clickStatisticianSet(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('#statistician')");
		LoadingPage.Loading(driver);
		
	}
	
	/**
	 * 点击系统管理员设置
	 * @param driver
	 */
	public static void clickSystemManagerSet(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('#setSManager')");
		LoadingPage.Loading(driver);
	}


	/**
	 * 判断添加项目经理按钮是否存在
	 * @param driver
	 */
	public static boolean addProjectManagerButtonIsExist(WebDriver driver)  
	{
		int returnLength = CommonJQ.excuteJStoInt(driver, "return $(\"iframe\").contents().find(\"#addSManagerBtn\").length");
		if(returnLength>0)
		{
			LOG.info("添加项目经理按钮存在");
			return true;
		}else{
			LOG.info("添加项目经理按钮不存在");
			return false;
		}
	}


	/**
	 * 判断批量导入按钮 是否存在
	 * @param driver
	 */
	public static boolean importUsersButtonIsExist(WebDriver driver) 
	{
		int returnLength = CommonJQ.excuteJStoInt(driver, "return $(\"iframe\").contents().find(\"#batchAddUserButton\").length");;
		if(returnLength>0)
		{
			LOG.info("批量导入按钮存在");
			return true;
		}else{
			LOG.info("批量导入按钮不存在");
			return false;
		}
		
	}


	/**
	 * 删除项目经理按钮是否存在
	 * @param driver
	 */
	public static boolean deleteProjectManagerButtonIsExist(WebDriver driver) 
	{
		int returnLength = CommonJQ.excuteJStoInt(driver, "return $(\"iframe\").contents().find(\"#delteSysmanager\").length");;
		if(returnLength>0)
		{
			LOG.info("删除项目经理按钮存在");
			return true;
		}else{
			LOG.info("删除项目经理按钮不存在");
			return false;
		}
		
	}


	/**
	 *搜索按钮是否存在 
	 * @param driver
	 */
	public static boolean searchButtonIsExist(WebDriver driver,String message) 
	{
		int returnLength = CommonJQ.excuteJStoInt(driver, "return $(\"iframe\").contents().find(\"#searchInfo\").length");;
		if(returnLength>0)
		{
			LOG.info(message+"按钮存在");
			return true;
		}else{
			LOG.info(message+"按钮不存在");
			return false;
		}
		
	}
	
	
	/**
	 *添加专家按钮是否存在 
	 * @param driver
	 */
	public static boolean addExpertIsExist(WebDriver driver) 
	{
		int returnLength = CommonJQ.excuteJStoInt(driver, "return $(\"iframe\").contents().find(\"#addSManagerBtn\").length");;
		if(returnLength>0)
		{
			LOG.info("添加专家按钮存在");
			return true;
		}else{
			LOG.info("添加专家按钮不存在");
			return false;
		}
		
	}
	
	/**
	 *删除专家按钮是否存在 
	 * @param driver
	 */
	public static boolean deleteExpertIsExist(WebDriver driver) 
	{
		int returnLength = CommonJQ.excuteJStoInt(driver, "return $(\"iframe\").contents().find(\"#delteSysmanager\").length");;
		if(returnLength>0)
		{
			LOG.info("删除专家按钮存在");
			return true;
		}else{
			LOG.info("删除专家按钮不存在");
			return false;
		}
		
	}


	public static boolean addMaintain(WebDriver driver,	String message) 
	{
		int returnLength = CommonJQ.excuteJStoInt(driver, "return $(\"iframe\").contents().find(\"#addSManagerBtn\").length");;
		if(returnLength>0)
		{
			LOG.info(message+"存在");
			return true;
		}else{
			LOG.info(message+"不存在");
			return false;
		}
	}


	public static boolean deleteMaintain(WebDriver driver,String message) 
	{
		int returnLength = CommonJQ.excuteJStoInt(driver, "return $(\"iframe\").contents().find(\"#delteSysmanager\").length");;
		if(returnLength>0)
		{
			LOG.info(message+"存在");
			return true;
		}else{
			LOG.info(message+"存在");
			return false;
		}
	}


	
	

	
}
