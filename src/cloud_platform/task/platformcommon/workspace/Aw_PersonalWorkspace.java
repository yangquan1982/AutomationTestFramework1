package cloud_platform.task.platformcommon.workspace;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import cloud_public.page.LoadingPage;

public class Aw_PersonalWorkspace {

	/***
	 * 根据参数设置每页显示多少条任务
	 */
	public static void setTaskNo(WebDriver driver, String taskno) {
		//
		driver.switchTo().frame("centoriframe");
		Select select = new Select(driver.findElement(By.id("pageSize")));
        select.selectByVisibleText(taskno);
        Pause.pause(500);
        LoadingPage.Loading(driver);
        driver.switchTo().defaultContent();
	}
	
	//个人工作空间页面, 任务分析状态//我的任务页面
	public static void filterTaskStatus(WebDriver driver, String status){
		//
		((JavascriptExecutor) driver).executeScript("$('iframe').contents().find('#texttaskStatus').click()");
		Pause.pause(2000);
        //
		((JavascriptExecutor) driver).executeScript(
					"$('iframe').contents().find('#mshowtaskStatus').find('li:contains("+status+")').click()");
		Pause.pause(500);LoadingPage.Loading(driver);Pause.pause(500);
	}
	
	
	
	
	
	
}
