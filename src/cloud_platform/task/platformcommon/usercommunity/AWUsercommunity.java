package cloud_platform.task.platformcommon.usercommunity;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.community.Usercommunitypage;
import cloud_public.page.LoadingPage;

public class AWUsercommunity {

	/*
	 * 搜索标题
	 */
	public static void searchHelp(WebDriver driver, String serch_key) {
		LoadingPage.Loading(driver);
		driver.findElement(By.id("helpTopic_key")).click();
		driver.findElement(By.id("helpTopic_key")).clear();
		if (serch_key != null) {
			driver.findElement(By.id("helpTopic_key")).sendKeys(serch_key);
		}
		((JavascriptExecutor) driver).executeScript("$('label.inp_ser_1').find('span').click()");

	}

	/*
	 * 判断存在并删除
	 */
	public static void isExsitAndDel(WebDriver driver, String name) {
		if (isExistHelp(driver, name)) {
			deleteHelp(driver, name, true);

		}
	}

	/*
	 * 判断是否存在该标题的求助
	 */
	public static boolean isExistHelp(WebDriver driver, String title) {
		String jscmd = "return $('#gridview-1016-table').find('tr').eq(0).text()";
		return ((String) ((JavascriptExecutor) driver).executeScript(jscmd)).contains(title);
	}

	/*
	 * 删除求助
	 */
	public static void deleteHelp(WebDriver driver, String name, boolean commit) {
		Usercommunitypage.clickCheckbox(driver, name);
		Usercommunitypage.clickDeleteBtn(driver);
		if (commit) {
			Usercommunitypage.clickOKBtn(driver);
		} else {
			Usercommunitypage.clickCancelBtnInmain(driver);
		}

	}

	/**
	 * 创建求助任务
	 * 
	 * @param modeType
	 * @param title
	 * @param content
	 * @param tag
	 * @param attach
	 * @param commit
	 */
	public static void createHelp(WebDriver driver, String modeType, String title, String content, String tag,
			String attach, boolean commit) {

		Usercommunitypage.clickStarthelpBtn(driver);
		LoadingPage.Loading(driver);
		if (modeType != null)
			Usercommunitypage.setMode(driver, modeType);
		if (title != null)
			Usercommunitypage.setTitle(driver, title);
		if (content != null)
			Usercommunitypage.setContent(driver, content);
		if (tag != null)
			Usercommunitypage.setTag(driver, tag);
		if (attach != null) {
			driver.findElement(By.id("attachFile")).sendKeys(attach);
		}
		if (commit) {
			Pause.pause(3000);
			Usercommunitypage.clickPublishBtn(driver);

		} else {
			Usercommunitypage.clickCancelBtn(driver);
		}
	}

}
