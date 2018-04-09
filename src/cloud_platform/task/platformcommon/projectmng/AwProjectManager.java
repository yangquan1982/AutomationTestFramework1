package cloud_platform.task.platformcommon.projectmng;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import cloud_platform.page.ProjectManagePage;
import cloud_public.page.IndexPage;
import cloud_public.page.LoadingPage;
import common.parameter.platform.ProjectManager_ZH_EN;
import common.util.CommonJQ;
import common.util.LOG;



public class AwProjectManager {

	/**
	 * 打开项目管理界面
	 * @param driver
	 */
	public static void openProjectManagePage(WebDriver driver) 
	{
		AwtaskManager_1.gotoprojectManager(driver);
		Pause.pause(1000);
		LoadingPage.Loading(driver);
	}

	
	/**
	 * 判断项目是否存在，并返回boolean
	 * @param driver
	 * @param name
	 * @return
	 */
	public static boolean isExistProject(WebDriver driver, String name) 
	{
		Search(driver, name);
		boolean flage =	CommonJQ.isExisted(driver, "a[title="+"\""+name+"\"]",true);
		for(int i=0;(i<10)&&(flage==false);i++){
			flage =	CommonJQ.isExisted(driver, "a[title="+"\""+name+"\"]",true);
			if(flage){
				break;
			}
			Pause.pause(1000);
		}
		return flage;
	}
	
	
	/**
	 * 搜索项目
	 * @param driver
	 * @param name
	 */
	public static void Search(WebDriver driver, String name) 
	{
		boolean flage =	CommonJQ.isExisted(driver, "#project_key", true);
		if(flage == false)
		{
//			IndexPage.netPrjMangeMenuClick(driver);//老界面
			IndexPage.netPrjMange(driver);//新界面
		}		
		CommonJQ.value(driver, "span[id=\"searchInfo\"] #project_key", name, true);
		CommonJQ.click(driver, "span[id=\"searchInfo\"] #search", true);
		int Num = CommonJQ.length(driver, ".num a", true);
		for(int i =0;(i<3)&&(Num!=1);i++){
			Pause.pause(1000);
			CommonJQ.value(driver, "span[id=\"searchInfo\"] #project_key", name, true);
			CommonJQ.click(driver, "span[id=\"searchInfo\"] #search", true);
			Num = CommonJQ.length(driver, ".num a", true);
			if(Num==1){
				break;
			}
		}
	}
	
	
	/**
	 * 删除项目
	 * @param driver
	 * @param name
	 */
	public static void deleteProjectByName(WebDriver driver, String name) 
	{
		LOG.info_testcase("将任务"+name+"选择！");
		AwProjectManager.checkProjectByName(driver, name);
		
		LOG.info_testcase("删除项目"+name+"!");
		CommonJQ.click(driver, "#delteSelectedProject a", true);
		CommonJQ.click(driver, "div[class=\"messager-button\"] a[class=\"l-btn\"]", true);
		LoadingPage.Loading(driver);
	}
	
	/**
	 * 根据项目名称选择
	 * @param driver
	 * @param name
	 */
	public static void checkProjectByName(WebDriver driver, String name) 
	{
		String jscmd = "$('tr:contains(" + name	+ "):visible').find('div.x-grid-row-checker').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
	}
	
	
	/**
	 * 创建项目，RF，完成后进入项目列表
	 * 自定义标签，片区，代表处，运营商，制式，凭证
	 * @param driver
	 * @param name
	 * @param area
	 * @param agent
	 * @param isp
	 * @param rat
	 * @param proof
	 * @param commit
	 */
	public static void creatProject(WebDriver driver,String name, String area, String agent,
			String isp, String rat, String proof, boolean commit) 
	{	
		creatProject_withOutIntoList(driver,name,area,agent,isp,rat,null,proof, commit);
		intoList(driver);
	}
	
	
	
	/**
	 * 
	 * @param driver
	 */
	public static void intoList(WebDriver driver) 
	{
		((JavascriptExecutor) driver).executeScript("$('div#crumbs').find('a').get(0).click()");
		Pause.pause(500);
		LoadingPage.Loading(driver);
	}
	
	
	/**
	 * 创建RF项目，
	 * @param driver
	 * @param name
	 * @param area
	 * @param agent
	 * @param isp
	 * @param rat
	 * @param BusinessArea
	 * @param proof
	 * @param commit
	 */
	public static void creatProject_withOutIntoList(WebDriver driver,String name, String area, String agent,
			String isp, String rat, String BusinessArea, String proof, boolean commit) {
		
		LoadingPage.Loading(driver);
		ProjectManagePage.clickCreateProjectButton(driver);
		if(name!=null)
		{
			LOG.info_testcase("设置自定义标签！");
			ProjectManagePage.setNewProjectName(driver, name); 
		}
			
		Pause.pause(500);
		if(area!=null)
		{
			LOG.info_testcase("选择运营商！");
			AwProjectManager.Selectoperator(driver, area, agent, isp); 
		}
			
		Pause.pause(500);
		if(BusinessArea!=null){
			ProjectManagePage.setBusiness_Area(driver, BusinessArea);
		}
		
		if(rat!=null){
			ProjectManagePage.setRAT(driver, rat);
		}
		Pause.pause(500);
		
		if(proof!=null){
			LOG.info_testcase("设置凭证！");
			driver.findElement(By.id("proof")).sendKeys(proof); 
		}
		
		if (commit) 
		{
			LOG.info_testcase("点击保存！");
			ProjectManagePage.clickCommitCreateProjectButton(driver); 
		} else 
		{
			LOG.info_testcase("点击取消！");
			ProjectManagePage.clickCancelCreateProjectButton(driver);
		}
		Pause.pause(3000);
		LoadingPage.Loading(driver);
	}
	
	/**
	 * 选择运营商
	 * @param driver
	 * @param area
	 * @param Agent
	 * @param operator
	 */
	public static void Selectoperator(WebDriver driver, String area, String Agent, String operator) {

		((JavascriptExecutor) driver).executeScript("$('input#operatorText').click()");

		selectop(driver, area, Agent, operator);
	}
	
	
	/**
	 * 选择片区，代表处，运营商
	 * @param driver
	 * @param area
	 * @param Agent
	 * @param operator
	 */
	public static void selectop(WebDriver driver, String area, String Agent, String operator) {
		//
		Select select = new Select(driver.findElement(By.id("region")));
		select.selectByVisibleText(area);
		Pause.pause(1000);
		//
		Select select1 = new Select(driver.findElement(By.id("agent")));
		select1.selectByVisibleText(Agent);
		Pause.pause(1000);
		//
		Select select2 = new Select(driver.findElement(By.id("operator")));
		select2.selectByVisibleText(operator);
		Pause.pause(1000);
		//
		CommonJQ.click(driver, "a#confirm", "span", true);
	}
	
	
	/**
	 * 验证项目添加成功
	 * @param driver
	 * @param prjname
	 */
	public static void verifyPrjAddSuccess(WebDriver driver, String prjname) {
		boolean flag = isExistProject(driver, prjname);
		Assert.assertTrue("项目管理页面第一行未找到指定项目，项目名："+prjname,flag);
	}
	
	
	/**
	 * 验证项目删除成功
	 * @param driver
	 * @param prjname
	 */
	public static void verifyPrjDelSuccess(WebDriver driver, String prjname) {
		boolean flag = isExistProject(driver, prjname);
		Assert.assertFalse("项目未删除,项目管理页面第一行找到指定项目，项目名："+prjname,flag);
	}
	
	
	
	public static void openProjectPage(WebDriver driver, String projectName) {
		AwProjectManager.verifyPrjAddSuccess(driver, projectName);	
		ProjectManagePage.clickProjectName(driver, projectName);
		LoadingPage.Loading(driver);
	}
	
	
	public static void openUserManagePage(WebDriver driver) 
	{
		ProjectManagePage.clickUserManageButton(driver);
	}
	
	
	public static void bathImportUser(WebDriver driver, String path, boolean iscommit) 
	{
		//
		CommonJQ.click(driver, "#rTabOper i[class=\"icon_import\"]", true);
		//r_pop_tit
		CommonJQ.waitForElementVisble(driver, ".r_pop_tit");
		//
		driver.findElement(By.id("proof")).sendKeys(path);
		Pause.pause(2000);
		//
		if(iscommit)
		{
			CommonJQ.click(driver, "#sub_updata", true);
			LoadingPage.Loading(driver);
			for(int i=0;i<10*60;i++)
			{

				int num = CommonJQ.length(driver, "div.r_pop_btn", true);
				if(num>=1){
					LOG.info("if");
					break;
				}else{
					LOG.info("else");
					Pause.pause(1000);
				}
			}
			boolean falge = CommonJQ.isExisted(driver, "div.r_pop_btn", true);
			if(falge==false){
				Assert.fail("批量导入用户失败,超过:10分钟");
			}
			CommonJQ.click(driver, "$('div.r_pop_btn:visible').find('a[class=\"btn_gray_1 close\"]').find('span')");
		}else
		{
			CommonJQ.click(driver, "$('div#dialogDiv').find('a.btn_gray_1.close').find('span:contains(\""+ProjectManager_ZH_EN.role_cancel+"\")')");
		}
		
	}
	
	
	/**
	 * 打开角色管理界面
	 * @param driver
	 */
	public static void openRoleManagePage(WebDriver driver) 
	{
		
		ProjectManagePage.clickRoleManageButton(driver);
	}
	
	
	/**
	 * 角色更改
	 * @param driver
	 * @param rolename
	 */
	public static void modifyRole_selectAll(WebDriver driver, String rolename) 
	{
		ProjectManagePage.enterRoleDetail(driver, rolename);
		CommonJQ.click(driver, "a#sel_role_all", true,"全选按钮");
		CommonJQ.click(driver, "input[name=dataUploadAuth]", true,0,"数据上传权限:允许");
		CommonJQ.click(driver, "a[onclick=\"submitForm();\"]",true,"保存按钮");
		LoadingPage.Loading(driver);
	}
	
	
	
	
	
	
	
	
}
