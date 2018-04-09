package cloud_platform.page.community;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.LOG;

public class Usercommunitypage {

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
		Pause.pause(2000);
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
		Pause.pause(2000);
	}

	/**
	 * 点击取消按钮
	 * 
	 * @param name
	 */
	public static void clickCancelBtnInmain(WebDriver driver) {
		driver.findElement(By.className("l-btn-cancel"))
				.findElement(By.className("l-btn-text")).click();

	}

	/*
	 * 点击发起求助按钮
	 */
	public static void clickStarthelpBtn(WebDriver driver) {
		LOG.info("click starthelpBtn");
		// starthelp.click();
		String jscmd = "$('div#btn_addCase').find('span:contains(\"发起求助或讨论\")').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);

	}

	/*
	 * 设置案例类型
	 */
	public static void setMode(WebDriver driver, String modenType) {
		LOG.info_aw("设置案例类型");

		driver.findElement(By.id("textmoduleId")).click();

		String jscmd = "$('#mshowmoduleId').find('li:contains(" + modenType
				+ ")').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
        Pause.pause(1000);
	}

	/*
	 * 设置案例标题
	 */
	public static void setTitle(WebDriver driver, String title) {
		LOG.info_aw("设置案例标题");

		String jscmd = "$('#title').val(\""+title+"\")";
		((JavascriptExecutor) driver).executeScript(jscmd);
		Pause.pause(1000);
	}
	
	/*
	 * 设置案例内容
	 */
	public static void setContent(WebDriver driver, String content) {
		LOG.info_aw("设置案例内容");
        //
		String jscmd = "$('iframe[class=\"ke-edit-iframe\"]').contents().find('.ke-content').text(\""+content+"\")";
		((JavascriptExecutor) driver).executeScript(jscmd);
		Pause.pause(1000);
	}

	/*
	 * 设置标签
	 */
	public static void setTag(WebDriver driver, String tag) {
		LOG.info_aw("设置标签");
        //
		String jscmd = "$('#tag').val(\""+tag+"\")";
		((JavascriptExecutor) driver).executeScript(jscmd);
		Pause.pause(1000);
	}
	
	/*
	 * 点击发布按钮
	 */
	public static void clickPublishBtn(WebDriver driver) {
		LOG.info_aw("点击发布按钮");
        //
		String jscmd = "$('a#publishBtn').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
		Pause.pause(500);LoadingPage.Loading(driver);
	}
	
	/*
	 * 点击取消按钮
	 */
	public static void clickCancelBtn(WebDriver driver) {
		LOG.info_aw("点击取消按钮");
        //
		String jscmd = "$('a.btn_gray_1.ml10').find('span').click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
		Pause.pause(500);LoadingPage.Loading(driver);
	}
	
	/*
	 * 进入求助案例中
	 */
	public static void clickTaskByName(WebDriver driver, String title) {
		LOG.info_aw("点击取消按钮");
        //
		String jscmd = "$('td:contains(\""+title+"\")').eq(0).find('div a').get(0).click()";
		((JavascriptExecutor) driver).executeScript(jscmd);
		Pause.pause(500);LoadingPage.Loading(driver);Pause.pause(1000);
	}
	
	
	
	
	
	
	
	
	
	
}
