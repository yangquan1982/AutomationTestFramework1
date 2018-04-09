package cloud_platform.page.community;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cloud_public.page.LoadingPage;
import common.util.CommonObject;
import common.util.LOG;

public class AWUsercommunity {

	/**
	 * 搜索标题
	 * */
	public static void searchHelp(WebDriver driver, String serch_key) 
	{
		LoadingPage.Loading(driver);
		driver.findElement(By.id("helpTopic_key")).click();
		driver.findElement(By.id("helpTopic_key")).clear();
		if (serch_key != null) {
			driver.findElement(By.id("helpTopic_key")).sendKeys(serch_key);
		}
		((JavascriptExecutor) driver)
				.executeScript("$('label.inp_ser_1').find('span').click()");

	}

	/**
	 * 判断存在并删除
	 * */
	public static void isExsitAndDel(WebDriver driver, String name) 
	{
		if (isExistHelp(driver, name)) {
			deleteHelp(driver, name, true);

		}
	}

	/**
	 * 判断是否存在该标题的求助
	 * */
	public static boolean isExistHelp(WebDriver driver, String title) 
	{
		String jscmd = "return $('#gridview-1016-table').find('tr').eq(0).text()";
		return ((String) ((JavascriptExecutor) driver).executeScript(jscmd))
				.contains(title);
	}

	/**
	 * 
	 * 删除求助
	 * */
	public static void deleteHelp(WebDriver driver, String name, boolean commit) 
	{
		clickCheckbox(driver, name);
		clickDeleteBtn(driver);
		if (commit) {
			clickOKBtn(driver);
		} else {
			clickCancelBtnInmain(driver);
		}
	}
	

	/**
	 * 点击多选框
	 * 
	 * @param name
	 */
	public static void clickCheckbox(WebDriver driver, String name) {
		String jscmd = "$('tr:contains(" + name
				+ ")').find('div.x-grid-row-checker').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
	}
	

	/**
	 * 点击删除按钮
	 * 
	 * @param name
	 */
	public static void clickDeleteBtn(WebDriver driver) {
		driver.findElement(By.id("deleteHelpSpan")).click();
	}
	

	/**
	 * 点击确定按钮
	 * 
	 * @param name
	 */
	public static void clickOKBtn(WebDriver driver) {
		// CommonObject.driver.findElement(By.className("l-btn")).findElement(By.className("l-btn-text")).click();
		String jscmd = "$('div.panel.window.messager-window').find('a.l-btn').find('span.l-btn-text').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
		
	}

	
	/**
	 * 点击取消按钮
	 * @param name
	 */
	public static void clickCancelBtnInmain(WebDriver driver) 
	{
		driver.findElement(By.className("l-btn-cancel"))
				.findElement(By.className("l-btn-text")).click();
	}


	/**
	 * 点击发起求助按钮
	 * */	
	public static void clickStarthelpBtn(WebDriver driver)
	{
		LOG.info("click starthelpBtn");
		//starthelp.click();
		String jscmd="$('div#btn_addCase').find('span:contains(\"发起求助或讨论\")').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
	}
	
	
	/**
	 * 设置案例类型
	 * */	
	public static void setMode(WebDriver driver, String modenType)
	{
		LOG.info_aw("设置案例类型");
		driver.findElement(By.id("textmoduleId")).click();
		
		String jscmd="$('#mshowmoduleId').find('li:contains("+modenType+")').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
	}

	
	/**
	 * 创建求助任务
	 * @param driver
	 * @param problem_Feedback
	 * @param string
	 * @param string2
	 * @param object
	 * @param string3
	 * @param b
	 */
	public static void createHelp(WebDriver driver,String modeType, String title,
			String content, String tag, String attach, boolean commit) 
	{
		CommunityPage page = PageFactory.initElements(driver,CommunityPage.class);
		page.clickStarthelpBtn(driver);
		LoadingPage.Loading(driver);
		
		if (modeType != null)
			page.setMode(driver,modeType);
		if (title != null)
			page.setTitle(driver,title);
		if (content != null)
			page.setContent(driver,content);
		if (tag != null)
			page.setTag(driver,tag);
		if (attach != null)
			driver.findElement(By.id("attachFile")).sendKeys(attach);
		if (commit) {
			page.clickPublishBtn(driver);
		} else {
			page.clickCancelBtn(driver);
		}
		
	}

	/**
	 * 等待界面加载
	 * @param driver
	 */
	public static void waitforPageload(WebDriver driver)
	{
		LoadingPage.waitForPagLoad(driver);
	}
	
	/**
	 * 得到浏览/回复次数
	 * @param driver 
	 * 
	 * @param taskname
	 * @return
	 */
	public static Integer[] getreplayNum(WebDriver driver, String taskname) {
//		CommunityPage page = CommonObject.communitypage;
		return CommunityPage.getLookAndReplayNum(driver,taskname);
	}
	
	
	/**
	 * 回复求助
	 * @param name
	 * @param content
	 * @param commit
	 */
	public static void replyHelp(WebDriver driver,String name, String content, boolean commit) 
	{
		CommunityPage page = PageFactory.initElements(driver,CommunityPage.class);
		page.clickTaskByName(driver,name);
		if (content != null)
			page.setContent(driver,content);
		if (commit) {
			page.submitReply(driver);
			page.cancelReply(driver);
			waitforPageload(driver);
		} else {
			page.cancelReply(driver);
			waitforPageload(driver);
		}
	}

	
	/**
	 * 删除回帖
	 * @param commit
	 */
	public static void deleteRep(WebDriver driver,boolean commit) 
	{
		CommunityPage page = CommonObject.communitypage;
		page.ClickCancelBtnInDetail(driver);
		if (commit){
			page.clickDeteOkbtnInDetail(driver);
		}else{
			page.clickDeteCancelbtnInDetail(driver);
		}
	}
	
}
