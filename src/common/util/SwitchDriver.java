package common.util;

import java.awt.event.KeyEvent;
import java.util.Set;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cloud_platform.page.UpDataPage;
import cloud_public.page.LoadingPage;

public class SwitchDriver {

	public static void switchDriverToFrame(WebDriver driver) {
		switchDriverToFrame( driver, "//iframe","");
	}
	public static void switchDriverToFrame(WebDriver driver,String iframeName) {
		switchDriverToFrame( driver, iframeName,"");
	}
	public static void switchDriverToFrame(WebDriver driver,String iframeName,String Message) {
		WebElement frameElement= null;
		try {
			frameElement = driver.findElement(By.xpath(iframeName));
		} catch (Exception e) {
			Assert.fail(Message+"not find,Xpath:"+iframeName);
		}
		driver.switchTo().frame(frameElement);
		LoadingPage.Loading(driver);
	}	
	public static void switchDriverToSEQ(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public static void switchToWinID(WebDriver driver,String WinID) {
		driver.switchTo().window(WinID);
		System.out.println(ZIP.NowTime()+"Switch window success,WinID:"+WinID);
	}
	
	/**
	 * 
	* @Description: 切换到新的句柄
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	public static void switchToNewWin(WebDriver driver) {
		String newHandle = CommonWD.getWindowHandle(driver, CommonWD.MainWindowHandle);
		System.out.println("新的句柄ID:" + newHandle);
		if (newHandle == null) {
			Assert.fail("打开新的浏览器页面失败！");
		} else {
			driver.switchTo().window(newHandle);
			Pause.pause(1000);
		}
		System.out.println(ZIP.NowTime()+"Switch window success,WinID:"+newHandle);
	}
	
	/**
	 * 
	* @Description: 关闭其他句柄，切换到初始句柄
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	public static void switchToHomeWin(WebDriver driver) {
		Set<String> wins = CommonWD.getAllHandle(driver);
		for (String win : wins) {
			if (win.equals(CommonWD.MainWindowHandle)) {
				continue;
			} else {
				System.out.println(ZIP.NowTime() + "window close ID："+ win);
				SwitchDriver.winIDClose(driver, win);
			}
		}
		SwitchDriver.switchToWinID(driver, CommonWD.MainWindowHandle);
	}
	
	public static void winIDClose(WebDriver driver,String WinID) {
		try {
			driver.switchTo().window(WinID).close();
			System.out.println(ZIP.NowTime()+"Close window success,WinID:"+WinID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 关闭非主窗口
	 * @param driver
	 * @param defaultWindowID
	 */
	public static void closeOtherWin(WebDriver driver, String defaultWindowID) {
		Set<String> wins = null;
		for (int i = 0; i < 10; i++) {
			wins = driver.getWindowHandles();
			if (wins.size() > 1 && !wins.contains("")) break;
			Pause.pause(10);
		}

		Object[] wa = wins.toArray();

		for (Object win : wa) {
			if (win.toString().equals(defaultWindowID)) {
				continue;
			} else {
				System.out.println(ZIP.NowTime() + "window close:"
						+ win.toString());
				
				SwitchDriver.winIDClose(driver, win.toString());
				CommonJQ.clickKey(KeyEvent.VK_ENTER);
				UpDataPage.closeleavePage();
				SwitchDriver.switchToWinID(driver, defaultWindowID);
			}
		}
		SwitchDriver.switchToWinID(driver, defaultWindowID);
	}
	
	/**
	 * 关闭非主窗口
	 * @param driver
	 * @param defaultWindowID
	 */
	public static void upLoadCloseOtherWin(WebDriver driver, String defaultWindowID) {
		Set<String> wins = null;
		for (int i = 0; i < 10; i++) {
			wins = driver.getWindowHandles();
			if (wins.size() > 1 && !wins.contains("")) break;
			Pause.pause(10);
		}

		Object[] wa = wins.toArray();

		for (Object win : wa) {
			if (win.toString().equals(defaultWindowID)) {
				continue;
			} else {
				System.out.println(ZIP.NowTime() + "window close:"
						+ win.toString());
				UpDataPage.closeSelectFilePage();//关闭因为没有文件导致的异常界面
				UpDataPage.closeConfirm();//关闭异常上传异常提示确定按钮 和 工参异常关闭界面
				SwitchDriver.winIDClose(driver, win.toString());
				CommonJQ.clickKey(KeyEvent.VK_ENTER);
				UpDataPage.closeleavePage();
				SwitchDriver.switchToWinID(driver, defaultWindowID);
			}
		}
		SwitchDriver.switchToWinID(driver, defaultWindowID);
	}
}
