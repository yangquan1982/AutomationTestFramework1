package cloud_public.page;
import java.util.Date;
import java.util.Set;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.util.CommonObject;
import common.util.TestWebDriver;



public class AwCommon {
	
	/***
	 * 获取当前时间字符串
	 * 
	 * @param dateType
	 *            时间格式，比如："ddHHmmss"、"yyyyMMddHHmmss"
	 * @return 返回当前时间
	 */
	public static String getDateString(String dateType) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
		return dateFormat.format(date);
	}
	
	
	public static void moveAndClick(WebElement we) {
		Actions action = new Actions(CommonObject.driver);
		action.moveToElement(we);
		Pause.pause(1000);
		we.click();
	}
	
	
	public static void moveAndClick(WebDriver driver, WebElement we) {
		Actions action = new Actions(driver);
		action.moveToElement(we);
		Pause.pause(1000);
		we.click();
	}
	
	public static boolean isWebElementExistByID(String id) {
		WebElement we = null ;
		try {
			we = TestWebDriver.findElement(By.id(id));
			if(we != null)
			{
				return we.isDisplayed();
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	public static void clickJsElement(String jsElement) {
		
		CommonObject.driver.executeScript(jsElement + ".click()");
	}
	
	public static boolean isWebElementExist(String element) {
		String jscmd = "return document.getElementById('XXX')";
		if (element.trim().startsWith("$")) {
			jscmd = jscmd.replace("document.getElementById('XXX')", element + ".length > 0");
		} else {
			jscmd = jscmd.replace("document.getElementById('XXX')", element);
		}
		System.out.println(jscmd);
		return (boolean) CommonObject.js.executeScript(jscmd);
	}
	public static void closeOtherWindows() {
		Pause.pause(5000);
		Set<String> wins = CommonObject.driver.getWindowHandles();
		Object[] wa = wins.toArray();
		System.out.println("窗口的个数…………"+wa.length);

		for (Object win : wa) {
			if (win.toString().equals(CommonObject.defaultWindowID)) {
				
				continue;
			} else {
				
				CommonObject.driver.switchTo().window(win.toString()).close();
				AwCommon.clickKey(KeyEvent.VK_ENTER);
				CommonObject.driver.switchTo().window(CommonObject.defaultWindowID);
			}
		}
		CommonObject.driver.switchTo().window(CommonObject.defaultWindowID);
		Pause.pause(5000);
		System.out.println("change window success");
	}
	public static void clickKey(int code) {
		try {
			Robot robot = new Robot();
			robot.keyPress(code);Pause.pause(500);
			robot.keyRelease(code);
			Pause.pause(500);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
