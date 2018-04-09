package cloud_platform.page;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;

public class UserCommunityPage {

	/**
	 * 界面分类
	 * @param driver
	 * @return
	 */
	public static boolean pageTypeReturn(WebDriver driver) 
	{
		String returnPageTypes = CommonJQ.text(driver, "#modelist");
		System.out.println("returnPageTypes:"+returnPageTypes);
		if(!returnPageTypes.contains("问题反馈"))
		{
			return false;
		}else if(!returnPageTypes.contains("需求反馈"))
		{
			return false;
		}else if(!returnPageTypes.contains("业务咨询"))
		{
			return false;
		}else if(!returnPageTypes.contains("其他"))
		{
			return false;
		}else
		{
			return true;
		}
	}

	
	/**
	 * 发起求助或讨论按钮
	 * @param driver
	 * @return
	 */
	public static boolean seekHelpButton(WebDriver driver) 
	{
		int seekHelpButton = CommonJQ.length(driver, ".btn_blue_1");
		if(seekHelpButton>0){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 删除按钮是否存在
	 * @param driver
	 * @return
	 */
	public static boolean deleteButton(WebDriver driver) 
	{
		int deleteButton = CommonJQ.length(driver, "#deleteHelp");
		if(deleteButton>0){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 编辑按钮是否存在
	 * @param driver
	 */
	public static boolean editButton(WebDriver driver) 
	{
		int editButton = CommonJQ.length(driver, "#editHelp");
		if(editButton>0){
			return true;
		}else{
			return false;
		}
		
	}


	/**
	 * 置顶按钮是否存在
	 * @param driver
	 */
	public static boolean topButton(WebDriver driver) 
	{
		int topButton = CommonJQ.length(driver, "#topHelp");
		if(topButton>0){
			return true;
		}else{
			return false;
		}
		
	}


	/**
	 * 取消置顶按钮是否存在
	 * @param driver
	 */
	public static boolean cancelTopButton(WebDriver driver) 
	{
		int cancelTopButton = CommonJQ.length(driver, "#cancelTopHelp");
		if(cancelTopButton>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	

}
