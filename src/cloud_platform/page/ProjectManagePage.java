package cloud_platform.page;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.platformcommon.projectmng.AwProjectManager;
import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.parameter.platform.ProjectManager_ZH_EN;

public class ProjectManagePage {

	/**
	 * 点击删除项目按钮
	 * @param driver
	 */
	public static void clickDeleteProjectButton(WebDriver driver)
	{
		CommonJQ.click(driver, "#delteSelectedProject", "span", true);	
	}
	
	
	/**
	 * 点击确认删除按钮
	 * @param driver
	 */
	public static void clickConfirmDeleteButton(WebDriver driver)
	{
		CommonJQ.click(driver, "div[class=\"messager-button\"] a[class=\"l-btn\"]", true);
	}
	
	
	/**
	 * 点击创建项目
	 * @param driver
	 */
	public static void clickCreateProjectButton(WebDriver driver)
	{
		LoadingPage.Loading(driver);
		CommonJQ.click(driver, "a[href=\"createNewProject.do\"]", true);
		LoadingPage.Loading(driver);
	}
	
	
	/**
	 * 设置新项目名称
	 * @param driver
	 * @param name
	 */
	public static void setNewProjectName(WebDriver driver, String name)
	{
		CommonJQ.value(driver, "#projectNameTag", name, true);		
	}
	
	
	/**
	 * 设置地区
	 * @param driver
	 * @param area
	 */
	public static void setBusiness_Area(WebDriver driver, String area)
	{
		CommonJQ.click(driver, "$('input#textprojectType').parent().find('span')");
		if(!CommonJQ.excuteJStoString(driver, "return $('ul#mshowundefined').css('display')").contains("block"))
		{
			Assert.fail("界面点击下拉框失败！");
		}
		CommonJQ.click(driver, "$(\"ul#mshowundefined\").find(\"li:contains('"+area+"')\")");
	}
	
	
	/**
	 * 设置制式
	 * @param driver
	 * @param RAT
	 */
	public static void setRAT(WebDriver driver, String RAT)
	{
		CommonJQ.click(driver, "$('ul#chooseRat').find('label:contains(\""+RAT+"\")').find('input')");		
	}
	
	
	/**
	 * 确认创建项目
	 * @param driver
	 */
	public static void clickCommitCreateProjectButton(WebDriver driver)
	{
		CommonJQ.click(driver,  "span:contains(\""+ProjectManager_ZH_EN.Submit+"\")", true);
	    LoadingPage.Loading(driver);
	}
	
	
	/**
	 * 取消创建项目
	 * @param driver
	 */
	public static void clickCancelCreateProjectButton(WebDriver driver)
	{
		CommonJQ.click(driver, "div#container", "span:contains(\""+ProjectManager_ZH_EN.role_cancel+"\")", true);
	    LoadingPage.Loading(driver);
	}
	
	
	/**
	 * 确认修改项目
	 * @param driver
	 */
	public static void clickProjectModifyButton(WebDriver driver)
	{
		CommonJQ.click(driver,  "span:contains(\""+ProjectManager_ZH_EN.role_save+"\")", true);
	    LoadingPage.Loading(driver);
	}
	
	
	/**
	 * 进入项目
	 * @param driver
	 * @param name
	 */
	public static void clickProjectName(WebDriver driver, String name)
	{
		AwProjectManager.Search(driver, name);
		CommonJQ.click(driver, "a[title="+"\""+name+"\"]", true);
	    LoadingPage.Loading(driver);
	}
	
	
	/**
	 * 点击用户管理
	 * @param driver
	 */
	public static void clickUserManageButton(WebDriver driver)
	{
		CommonJQ.click(driver, "li#project_2", "span", true);
	}
	
	
	/**
	 * 角色管理
	 * @param driver
	 */
	public static void clickRoleManageButton(WebDriver driver)
	{
		CommonJQ.click(driver, "li#project_3", "span", true);
	}
	
	
	/**
	 * 进入角色管理界面
	 * @param driver
	 * @param rolename
	 */
	public static void enterRoleDetail(WebDriver driver, String rolename)
	{
		CommonJQ.click(driver, "tbody#gridview-1025-body", "a:contains(\""+rolename+"\")", true, 0);
		LoadingPage.Loading(driver);

	}
	
	
	/**
	 * 进入编辑项目界面
	 * @param driver
	 */
	public static void clickEditProject(WebDriver driver)
	{
		String returnStr = CommonJQ.excuteJStoString(driver, "return $('.r_tab_oper').find('i.icon_edit').nextAll('span').eq(0).text()");
		if(returnStr.trim().equals("编辑项目"))
		{
			CommonJQ.click(driver, "$('.r_tab_oper').find('i.icon_edit').nextAll('span').eq(0)");
		}else{
			Assert.fail("界面未找到编辑项目！");
		}
		
	}
	
	
	/**
	 * 点击添加用户按钮  
	 */
	public static void addUserButton(WebDriver driver)
	{
		CommonJQ.click(driver, "#rTabOper i[class=\"icon_add\"]", true);
	}
	
	
	/**
	 * 添加内部用户时，设置用户名称
	 */
	public static void setInnerUserName(WebDriver driver,String innerUserName)
	{
		CommonJQ.value(driver, "#user_id_1", innerUserName, true);	
	}

	
	/**
	 * 添加内部用户时，设置用户ID
	 */
	public static void setInnerUserId(WebDriver driver,String innerUserID)
	{
		CommonJQ.value(driver, "#user_name_1", innerUserID, true);	
	}
	
	
	
	/**
	 * 添加外部用户时，设置用户邮箱 
	 */
	public static void setExteriorMail(WebDriver driver,String mail)
	{
		CommonJQ.value(driver, "#user_mail", mail, true);	
	}
	
	
	/**
	 * 添加外部用户时，设置用户名称
	 */
	public static void setExteriorUserName(WebDriver driver,String UserName)
	{
		CommonJQ.value(driver, "#user_name", UserName, true);	
	}
	
	
	/**
	 * 添加外部用户时，设置用户手机号
	 */
	public static void setExteriorPhoneNum(WebDriver driver,String phoneNum)
	{
		CommonJQ.value(driver, "#user_note", phoneNum, true);	
	}
	
	
	/**
	 * 点击添加用户界面选择框
	 * @param driver
	 */
	public static void CheckUser(WebDriver driver) 
	{
		CommonJQ.click(driver, "$(\"tbody#gridview-1017-body\").find(\"div.x-grid-row-checker\").eq(0)");
	}


	/**
	 * 点击删除用户按钮
	 * @param driver
	 */
	public static void clickRemoveUser(WebDriver driver) 
	{
		//点击删除按钮
		CommonJQ.click(driver, "$(\"span#deleteSelectUser\").find(\"span\")");
		
		//点击确认按钮
		CommonJQ.click(driver,"$(\"div.messager-button\").find(\"span.l-btn-left span\").eq(0)");
	}

	
	/**
	 * 新建角色
	 * @param driver
	 * @param string
	 */
	public static void clickAddNewRole(WebDriver driver) 
	{
		CommonJQ.click(driver, "$(\"span#addRoleId\").find(\"i.icon_add\").nextAll(\"span\")");
	}


	/**
	 * 设置角色名称
	 * @param driver
	 * @param roleName
	 */
	public static void setRole(WebDriver driver,String roleName) 
	{
		CommonJQ.value(driver, "#roleName", roleName, true);	
		
	}


	/**
	 * 点击全选
	 * @param driver
	 */
	public static void clickSelectAll(WebDriver driver) 
	{
		CommonJQ.click(driver, "$(\"div#roleSelBox\").find(\"a#sel_role_all\")");
	}


	/**
	 * 设置分析权限
	 * @param limitName
	 */
	public static void setChooseAnalysisLimit(WebDriver driver,String [] limitName)
	{
		for(int j = 0; j<limitName.length;j++)
		{
			for(int i = 1; i<13;i++)
			{
				String AnalysisLimit = CommonJQ.excuteJStoString(driver, "return $(\"div#roleList\").find(\"label span\").eq("+i+").text()");
				if(AnalysisLimit.contains(limitName[j]))
				{
					CommonJQ.click(driver, "$(\"div#roleList\").find(\"label span\").eq("+i+")");
					break;
				}
			}
		}
		
	}


	/**
	 * 角色添加界面 点击保存
	 * @param driver
	 */
	public static void saveButton(WebDriver driver) 
	{
		CommonJQ.click(driver, "$(\"a.btn_green_1\")");
	}


	
	/**
	 * 设置数据上传权限
	 * @param driver
	 * @param isHasUpload 
	 */
	public static void setUploadLimit(WebDriver driver, boolean isHasUpload) 
	{
		if(isHasUpload)
		{
			CommonJQ.click(driver, "$(\"dl input[name='dataUploadAuth']\").eq(0)"); //选择为允许
			getStatusUploadLimt(driver,0);
		}else
		{
			CommonJQ.click(driver, "$(\"dl input[name='dataUploadAuth']\").eq(1)"); //选择为不允许
		}
	}
	
	
	/**
	 * 查看当前角色管理界面数据上传权限设置状态
	 */
	public static void getStatusUploadLimt(WebDriver driver, int isHasUpload)
	{
		CommonJQ.excuteJStoString(driver, "$(\"dl input[name='dataUploadAuth']\").eq(1).is(\":checked\")");
	}


	/**
	 * 点击删除角色管理按钮
	 * @param driver
	 */
	public static void clickDeleteRoleButton(WebDriver driver)
	{
		CommonJQ.click(driver, "$('i.icon_del')");
	}


	/**
	 * 删除角色时点击确认按钮
	 * @param driver
	 */
	public static void clickEnterDeleteButton(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('')");
		
	}


	
	/**
	 * 设置为内部
	 * @param driver
	 */
	public static void setInnerCheck(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('input#inUser')");
		
	}
	
	
	
	/**
	 * 设置为内部
	 * @param driver
	 */
	public static void setExteriorCheck(WebDriver driver) 
	{
		CommonJQ.click(driver, "$('input#outUser')");
		
	}


	/**
	 * 获取角色在角色管理任务列表界面的位置
	 * @param driver
	 * @param roleName
	 * @return
	 */
	public static String getRoleSeat(WebDriver driver,String roleName) 
	{
		return CommonJQ.excuteJStoString(driver, "return $(\"a[title='"+roleName+"']\").parents('tr').index()");
	}
	
	
	
	
}
