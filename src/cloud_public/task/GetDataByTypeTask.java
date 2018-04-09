package cloud_public.task;

import java.util.Set;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cloud_public.page.GetDataByTypePage;
import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.LanguageUtil;
import common.util.ZIP;

public class GetDataByTypeTask {

	/**
	 * <b>Description:</b>为了调用clickToNoId
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param id
	 * @return void
	 */
	public static void ToclickToNoId(WebDriver driver,String id) {


		clickToNoId(driver, id);

	}
	/**
	 * <b>Description:</b>根据id点击按钮，弹出模态对话框
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param id
	 * @return void
	 */
	private static void clickToNoId(WebDriver driver,String id) {


			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents()." + id + ".click()", 1000);

	}
	/**
	 * <b>Description:</b>为了调用clickToNoId
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param id
	 * @return void
	 */
	public static void ToclickToId(WebDriver driver,String id) {


		clickToId(driver, id);

	}
	/**
	 * <b>Description:</b>根据id点击按钮，弹出模态对话框
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param id
	 * @return void
	 */
	private static void clickToId(WebDriver driver,String id) {


			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents().find('li[id=" + id + "]').click()", 1000);

	}
	/**
	 * <b>Description:</b>选择报告模板
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param id
	 * @return void
	 */
	public static void SelectReportMode(WebDriver driver,String id) {


			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents().find('ul[id=mshowtaskBeanvolteReportTemplate] li').eq(" + id + ").click()", 1000);

	}
	/**
	 * <b>Description:</b>选择报告模板ssv
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param id
	 * @return void
	 */
	public static void SelectSSVReportMode(WebDriver driver,String id) {


			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents().find('ul[id=mshowtaskBeanSSVReportTemplate] li').eq(" + id + ").click()", 1000);

	}
	/**
	 * <b>Description:</b>建任务时，选择文件夹。支持选择多个文件夹
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param files
	 *            多个文件夹以,隔开
	 * @param chooseFileBtn
	 * @param defaultWindowID
	 * @return void
	 */
	public static void chooseFolder(WebDriver driver, String[] files, String chooseFileBtn, String defaultWindowID) {

		clickToOpenModalWindow(driver, chooseFileBtn); // 点击选择文件按钮
		chooseDefineData(driver, defaultWindowID, files);
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param Datafile
	 * @param chooseFileBtn
	 * @param defaultWindowID
	 * @return void
	 */
	public static void chooseTimeFolder(WebDriver driver, String[] Timefile, String chooseFileBtn,
			String defaultWindowID) {

		clickToOpenModalWindow(driver, chooseFileBtn); // 点击选择文件按钮
		String MaxDateTime = GetDataByTypeTask.getMaxDateTime(driver, chooseFileBtn);
		if (Timefile.length == 0) {
			String[] files = { MaxDateTime };
			chooseDefineData(driver, defaultWindowID, files);
		} else {
			chooseTimeFileData(driver, defaultWindowID, MaxDateTime, Timefile);
		}
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	/**
	 * <b>Description:</b>
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param chooseFileBtn
	 * @return
	 * @return String
	 */

	private static String getMaxDateTime(WebDriver driver, String chooseFileBtn) {
		String date = "";
		int length = driver.findElements(By.cssSelector("#device_data_tab tr")).size();
		System.out.println("文件夹的长度为：" + length);
		for (int i = 0; i < length; i++) {

			String jscmd = "return $('div#device_data_tab').find('tr').eq(" + i
					+ ").find('span:contains(est)').length==1";

			if ((boolean) ((JavascriptExecutor) driver).executeScript(jscmd)) {
				String jscmd1 = "return $('div#device_data_tab').find('tr').eq(" + i
						+ ").find('span:contains(est)').parents('tr').find('td').eq(1).text()";
				date = (String) ((JavascriptExecutor) driver).executeScript(jscmd1);
				return date;
			}
			String jscmd2 = "return $('div#device_data_tab').find('tr').eq(" + i
					+ ").find('span:contains(sWX198085)').length==1";
			if ((boolean) ((JavascriptExecutor) driver).executeScript(jscmd2)) {
				String jscmd1 = "return $('div#device_data_tab').find('tr').eq(" + i
						+ ").find('span:contains(sWX198085)').parents('tr').find('td').eq(1).text()";
				date = (String) ((JavascriptExecutor) driver).executeScript(jscmd1);
				return date;
			}
		}
		System.out.println(ZIP.NowTime() + "MaxDateTime is :" + date);
		return date;

	}
	/**
	 * <b>Description:</b>根据id点击按钮，弹出模态对话框Public
	 * 
	 * @author xwx357019
	 * @param driver
	 * @param id
	 * @return void
	 */
	public static void SSVclickToOpenModalWindow(WebDriver driver, String id) {

		if (id.startsWith("$")) {
			CommonJQ.setTimeout(driver, id, 1000);
		} else if (id.contains("选择文件")) {
			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents()." + id + ".click()", 1000);
		} else if (id.contains("#")) {
			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents().find('" + id + "').eq(0).click()", 1000);
		}else if (id.contains("span[id")) {
			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents().find('" + id + "').click()", 1000);
		}else {
			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents().find('span#" + id + "').eq(0).click()",
					1000);
		}


	}
	/**
	 * <b>Description:</b>根据id点击按钮，弹出模态对话框
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param id
	 * @return void
	 */
	private static void clickToOpenModalWindow(WebDriver driver, String id) {

		Set<String> handles_0 = driver.getWindowHandles();
		if (id.startsWith("$")) {
			CommonJQ.setTimeout(driver, id, 1000);
		} else if (id.contains("#")) {
			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents().find('" + id + "').eq(0).click()", 1000);
		} else if (id.contains("选择文件")) {
			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents()." + id + ".click()", 1000);
		} else {
			CommonJQ.setTimeout(driver, "$('iframe[name=main]').contents().find('span#" + id + "').eq(0).click()",
					1000);
		}

		Set<String> handles_1 = driver.getWindowHandles();

		for (int i = 0; i < 10; i++) {
			if ((handles_0.size() != handles_1.size()) && (handles_1.size() != 0)) {
				System.out.println(ZIP.NowTime() + "open model window success,handles_1.size():" + handles_1.size());
				for (String s : handles_1) {
					if (s.equals("")) {
						System.out.println(ZIP.NowTime() + "open model window fail,handles_1 is null");
						Pause.pause(1000);
						handles_1 = driver.getWindowHandles();
						continue;
					}
				}
				break;
			} else {
				Pause.pause(1000);
				handles_1 = driver.getWindowHandles();
				continue;
			}
		}
		if (handles_0.size() == handles_1.size()) {
			Assert.fail("open model window fail");
		}
		for (String s : handles_1) {
			if (handles_0.contains(s)) {
				continue;
			} else if (s.equals("")) {
				continue;
			} else {
				driver.switchTo().window(s);
				for (int i = 0; i < 10; i++) {
					if (driver.getWindowHandle().equals(s)) {
						break;
					} else {
						driver.switchTo().window(s);
						Pause.pause(1000);
					}
				}
				LoadingPage.Loading(driver);
				System.out.println(ZIP.NowTime() + "switch to window[" + s + "] successfully!");
				break;
			}
		}
	}

	private static void chooseDefineData(WebDriver driver, String defaultWindowID, String[] files) {
		if (files.length == 0) {
			Assert.fail("未指定需要选择的文件");
		} else {
			for (int i = 0; i < files.length; i++) {
				switch (files[i]) {
				case "ALL": {
					CommonJQ.setSelectOption(driver, GetDataByTypePage.PageSize, LanguageUtil.translate("All"));
					CommonJQ.click(driver, GetDataByTypePage.CheckRow, false);
					CommonJQ.click(driver, GetDataByTypePage.SubmitBtn, true);
					break;
				}
				case "10": {
					CommonJQ.setSelectOption(driver, GetDataByTypePage.PageSize, "10");
					CommonJQ.click(driver, GetDataByTypePage.CheckRow, false);
					CommonJQ.click(driver, GetDataByTypePage.SubmitBtn, true);
					break;
				}
				default: {
					if (files[i].contains(";")) {
						for (String file : files[i].split(";")) {
							String jselemnet = "$('span[title=\"" + file + "\"]').parents(\"td\").prev().find('div.x-grid-row-checker')";
							CommonJQ.click(driver, jselemnet);
						}
						CommonJQ.click(driver,GetDataByTypePage.SubmitBtn, true);
						return;
					}
					boolean flage = CommonJQ.isExisted(driver, "span[title=\"" + files[i] + "\"]");
					if (!flage) {
						GetDataByTypePage.searchInChoosePage(driver, files[i]);
					}
					System.out.println("files.length:" + files.length);
					if (i < files.length - 1) {
						GetDataByTypePage.clickfile(driver, files[i]);
					} else {
						if (CommonJQ.isExisted(driver, GetDataByTypePage.ListBox, GetDataByTypePage.InputVisible)) {
							GetDataByTypePage.clickRidoBtn(driver, files[i]);
						} else {
							GetDataByTypePage.clickcheckboxs(driver, files[i]);
						}
					}
				}
				}

			}
		}
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}

	private static void chooseTimeFileData(WebDriver driver, String defaultWindowID, String datefile, String[] files) {
		GetDataByTypePage.searchInChoosePage(driver, datefile);
		GetDataByTypePage.clickfile(driver, datefile);
		if (files.length == 0) {
			Assert.fail("未指定需要选择的文件");
		} else {
			boolean flage = CommonJQ.isExisted(driver, "#showRat");
			for (int i = 0; (i < 10) && (flage == false); i++) {
				flage = CommonJQ.isExisted(driver, "#showRat");
				if (flage) {
					break;
				} else {
					Pause.pause(1000);
				}
			}
			CommonJQ.setSelectOption(driver, GetDataByTypePage.PageSize, LanguageUtil.translate("All"));
			for (int i = 0; i < files.length; i++) {
				System.out.println("files.length:" + files.length);
				if (CommonJQ.isExisted(driver, GetDataByTypePage.ListBox, GetDataByTypePage.InputVisible)) {
					String jselement = "$('span[title=\"" + files[i]
							+ "\"]').parents(\"td\").prev().find('input.parasSelectRadio').eq(0)";
					CommonJQ.click(driver, jselement);
				} else {
					String jselemnet = "$('span[title=\"" + files[i]
							+ "\"]').parents(\"td\").prev().find('div.x-grid-row-checker')";
					CommonJQ.click(driver, jselemnet);
				}
			}
			CommonJQ.click(driver, GetDataByTypePage.SubmitBtn, true);
			Set<String> wins = driver.getWindowHandles();
			Object[] wa = wins.toArray();
			for (Object win : wa) {
				if (win.toString().equals(defaultWindowID)) {
					continue;
				} else {
					CommonJQ.click(driver, GetDataByTypePage.SubmitBtn, true);
				}
			}
		}
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
	}
}
