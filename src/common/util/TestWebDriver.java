package common.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.huawei.hutaf.webtest.htmlaw.BasicAction;
import com.huawei.webtest.restful.util.Log;


public class TestWebDriver {

	private static Logger logger = null;

	private static String MainWindowHandle = "";

	public static int BROWSER_TYPE_IE = 0;
	public static int BROWSER_TYPE_FIREFOX = 1;

	private static String ieDriverPath = "";

	private static RemoteWebDriver webDriver = null;
	private static JavascriptExecutor jsExecutor = null;

	private static long DefaultImplicitlyWait = 60; // seconds
	private static long DefaultScriptTimeout = 60; // seconds
	private static long DefaultPageLoadTimeout = 120; // seconds
	private static long DefaultAssertWaitTime = 5; // seconds

	/**
	 * 判断元素是否存在
	 * 
	 * @param message
	 * @param jsElement
	 */
	public static void assertJsElementExist(String message, String jsElement) {
		if (jsElement == null) {
			throw new Error("jsElement is null!");
		}
		jsElement = jsElement.trim();
		waitJsElementExist(jsElement);
		if (isJsElementExist(jsElement))
			return;
		List<String> splites = cutJsElement(jsElement);
		int size = splites.size();
		for (int i = 1; i <= size; i++) {
			String tmpJsElement = util_join(".", splites, i);
			if (isJsElementExist(tmpJsElement)) {
				continue;
			} else {
				throw new Error("[" + message + "] The web element + ["
						+ tmpJsElement + "] don't exist, when execute "
						+ jsElement);
			}
		}
	}

	/***
	 * 断言页面不包含该文本信息
	 * 
	 * @param message
	 * @param textToBeVerified
	 */
	public static void assertTextNotPresent(String message,
			String textToBeVerified) {
		By by = By.xpath("//*[contains(.,'" + textToBeVerified + "')]");
		for (int i = 0; i < DefaultAssertWaitTime; i++) {
			List<WebElement> wes = findElements(by);
			if (null == wes || wes.size() == 0) {
				return;
			} else {
				int j = 0;
				for (; j < wes.size(); j++) {
					WebElement we = wes.get(j);
					if (we.isDisplayed()) {
						break;
					}
				}
				if (j == wes.size()) {
					return;
				}
			}
			util_pause(1000);
		}
		throw new Error("[" + message + "] The page contains \""
				+ textToBeVerified + "\"");
	}

	/**
	 * 断言页面包含该文本信息
	 * 
	 * @param message
	 * @param textToBeVerified
	 */
	public static void assertTextPresent(String message, String textToBeVerified) {
		By by = By.xpath("//*[contains(.,'" + textToBeVerified + "')]");
		for (int i = 0; i < DefaultAssertWaitTime; i++) {
			List<WebElement> wes = findElements(by);
			if (null != wes && wes.size() != 0) {
				for (WebElement we : wes) {
					if (we.isDisplayed()) {
						util_log("[" + message + "]assert Text ["
								+ textToBeVerified + "]Present: Success");
						return;
					}
					util_pause(1000);
				}
			}
		}
		throw new Error("[" + message + "] The Page don't exist \""
				+ textToBeVerified + "\"");
	}

	/**
	 * 根据className查找元素并点击
	 * 
	 * @param className
	 */
	public static void clickElementByClassName(String className) {
		WebElement we = findElement(By.className(className));
		if (we != null) {
			clickElement(we);
		} else {
			throw new Error("cannot find element by className:" + className);
		}
	}

	/**
	 * 根据cssSelector查找元素并点击
	 * 
	 * @param cssSelector
	 */
	public static void clickElementByCssSelector(String cssSelector) {
		WebElement we = findElement(By.cssSelector(cssSelector));
		if (we != null) {
			clickElement(we);
		} else {
			throw new Error("cannot find element by cssSelector:" + cssSelector);
		}
	}

	/**
	 * 根据id查找元素并点击
	 * 
	 * @param id
	 */
	public static void clickElementByID(String id) {
		WebElement we = findElement(By.id(id));
		if (we != null) {
			clickElement(we);
		} else {
			throw new Error("cannot find element by id:" + id);
		}
	}

	/**
	 * 根据linkText查找元素并点击
	 * 
	 * @param xpath
	 */
	public static void clickElementByLinkText(String linkText) {
		WebElement we = findElement(By.linkText(linkText));
		if (we != null) {
			clickElement(we);
		} else {
			throw new Error("cannot find element by linkText:" + linkText);
		}
	}

	/**
	 * 根据partialLinkText查找元素并点击
	 * 
	 * @param xpath
	 */
	public static void clickElementByPartialLinkText(String linkText) {
		WebElement we = findElement(By.partialLinkText(linkText));
		if (we != null) {
			clickElement(we);
		} else {
			throw new Error("cannot find element by partialLinkText:"
					+ linkText);
		}
	}

	/**
	 * 根据元素名称查找元素并点击
	 * 
	 * @param tagName
	 */
	public static void clickElementByTagName(String tagName) {
		WebElement we = findElement(By.className(tagName));
		if (we != null) {
			clickElement(we);
		} else {
			throw new Error("cannot find element by tagName:" + tagName);
		}
	}

	/**
	 * 根据xpath查找元素并点击
	 * 
	 * @param xpath
	 */
	public static void clickElementByXpath(String xpath) {
		WebElement we = findElement(By.xpath(xpath));
		if (we != null) {
			clickElement(we);
		} else {
			throw new Error("cannot find element by xpath:" + xpath);
		}
	}

	/**
	 * 点击元素
	 * 
	 * @param jsElement
	 */
	public static void clickJsElemnt(String jsElement) {
		assertJsElementExist("", jsElement);
		executeScript(jsElement + ".click()");
	}

	/**
	 * 根据id点击按钮，弹出模态对话框
	 * 
	 * @param id
	 */
	public static void clickToOpenModalWindow(String id) {
		
		String jscmd1 = null;
		if (id.startsWith("$")) {
			jscmd1 = "setTimeout(function(){" + id + "},3000)";
		} else {

			jscmd1 = "setTimeout(function(){$('iframe[name=main]').contents().find('span#"
					+ id + "').eq(0).click()},3000)";
		}

		String currentWindowID = CommonObject.driver.getWindowHandle();
		Log.info(currentWindowID);
//		String tempWindowID = null;
		Set<String> handles_0 = CommonObject.driver.getWindowHandles();

		CommonObject.driver.switchTo().defaultContent();
		Pause.pause(1 * 1000);

		//CommonObject.driver.executeScript(jscmd1);
		BasicAction.executeScript(jscmd1);

		//LoadingPage.Loading(driver);
		Pause.pause(5 * 1000);// 单击选择文件按钮

		Set<String> handles_1 = null;
		handles_1 = CommonObject.driver.getWindowHandles();

		for (int i = 0; i < 2; i++) {
			if (handles_0.size() == handles_1.size()) {
				BasicAction.executeScript(jscmd1);
				Pause.pause(1500);
				handles_1 = CommonObject.driver.getWindowHandles();
				Log.info("try to open model window " + i + " .");
				continue;
			} else {
				Log.info("open model window success.");
				break;
			}

		}
		if (handles_0.size() == handles_1.size()) {
			Assert.fail("open model window fail");
		}
		// System.out.println("Handles_1:"+handles_1.toString());
		// 切换到在handles_1中,但不属于handles_0的那个窗口

		for (String s : handles_1) {
			if (handles_0.contains(s)) {
				continue;
			} else {
				TestWebDriver.switchToWindow_byHandleString(s);
				// LOG.info("switch to window[" + s + "] successfully!");
				break;
			}
		}
		// 等待转圈加载完成
		iswaitmaskok();

	}
	
	public static void iswaitmaskok() {
		String jscmd = "return $('div#my_mask_layer:visible').length==0";
         Pause.pause(2000);
		for (int i = 0; i < 20; i++) {
			if (!(boolean) TestWebDriver.executeScript(jscmd)) {
				Log.info("upload page not compile");
				Pause.pause(1000);
				continue;
			} else {
				Log.info("upload page  compile");
				
				break;
			}
		}

	}

	/**
	 * 根据id点击按钮，弹出模态对话框
	 * 
	 * @param id
	 */
	public static void clickToOpenModalWindow_scrollheight(String id,
			int scrollheight) {
		
		clickToOpenModalWindow(id);

	}

	/**
	 * 根据id点击按钮，弹出模态对话框
	 * 
	 * @param id
	 */
	public static void clickToOpenModalWindow(WebElement we, String id) {
		// WebElement we = findElement(By.id(id));
		if (we == null) {
			throw new Error("cannot find element by id:" + we);
		}
		if (webDriver instanceof InternetExplorerDriver) {
			Actions action = new Actions(webDriver);
			action.moveToElement(we).click().perform();
			switchToDefaultFrame();
		} else if (webDriver instanceof FirefoxDriver) {
			int abselutWidth = 0;
			int abselutHeight = 0;
			setBrowserFullScreen();
			Point location = we.getLocation();
			util_log("we location:" + location.toString());
			Dimension size = we.getSize();
			util_log("we size:" + size.toString());
			abselutWidth += location.x + size.width / 2;
			abselutHeight += location.y + size.height / 2;
			WebElement frame = getFrameByElementID(By.id(id));
			if (frame != null) {
				util_log("iframe location:" + frame.getLocation().toString());
				abselutWidth += frame.getLocation().x;
				abselutHeight += frame.getLocation().y;
			}
			setScroll(0);
			clickPointByRobot(abselutWidth, abselutHeight);
		}
		switchToDefaultFrame();
	}

	/**
	 * 执行js脚本
	 * 
	 * @param jscmd
	 * @return
	 */
	public static Object executeScript(String jscmd) {
		util_log("executeJScript:" + jscmd);
		Object o = jsExecutor.executeScript(jscmd);
		util_log("result:" + o);
		return o;
	}

	/**
	 * 根据给的条件，查找元素
	 * 
	 * @param by
	 * @return 查找到的元素，没有找到时返回null
	 */
	public static WebElement findElement(By by) {
		util_log("start to find element by:" + by.toString());
		switchToDefaultFrame();
		setImplicitlyWait(1);
		WebElement we = null;
		try {
			we = webDriver.findElement(by);
		} catch (NoSuchElementException e) {
		}
		if (we == null) {
			List<WebElement> frames = webDriver.findElementsByTagName("iframe");
			if (frames != null && frames.size() > 0) {
				for (WebElement frame : frames) {
					switchToDefaultFrame();
					util_log("find element by [" + by.toString()
							+ "] in iframe");
					webDriver.switchTo().frame(frame);
					try {
						we = webDriver.findElement(by);
					} catch (NoSuchElementException e) {
						continue;
					}
					break;
				}
			}
		}
		if (we == null) {
			switchToDefaultFrame();
			util_log("cannot find element by:" + by.toString());
		}
		setImplicitlyWait(DefaultImplicitlyWait);
		return we;
	}

	/**
	 * 获取元素的属性
	 * 
	 * @param jsElement
	 * @param attr
	 * @return
	 */
	public static Object getJsElementAttribute(String jsElement, String attr) {
		assertJsElementExist("", jsElement);
		if (jsElement.startsWith("$")) {
			return executeScript("return " + jsElement + ".attr('" + attr
					+ "')");
		} else {
			return executeScript("return " + jsElement + "." + attr);
		}
	}

	/**
	 * 获取元素的css属性
	 * 
	 * @param jsElement
	 * @param cssAttr
	 * @return
	 */
	public static Object getJsElementCssAttribute(String jsElement,
			String cssAttr) {
		assertJsElementExist("", jsElement);
		if (jsElement.startsWith("$")) {
			return executeScript("return " + jsElement + ".css('" + cssAttr
					+ "')");
		} else {
			return executeScript("return " + jsElement + ".style." + cssAttr);
		}
	}

	/**
	 * 跳转到新的地址url
	 * 
	 * @param url
	 */
	public static void gotoUrl(String url) {
		webDriver.navigate().to(url);
	}

	/**
	 * 根据传入的RemoteWebDriver进行初始化
	 * 
	 * @param driver
	 */
	public static void init(RemoteWebDriver driver) {
		if (!(driver instanceof InternetExplorerDriver)
				&& !(driver instanceof FirefoxDriver)) {
			throw new Error("unknown browser type!");
		}
		webDriver = driver;
		jsExecutor = webDriver;
		setMainWindowHandle();
		webDriver.manage().timeouts()
				.pageLoadTimeout(DefaultPageLoadTimeout, TimeUnit.SECONDS);
		webDriver.manage().timeouts()
				.implicitlyWait(DefaultImplicitlyWait, TimeUnit.SECONDS);
		webDriver.manage().timeouts()
				.setScriptTimeout(DefaultScriptTimeout, TimeUnit.SECONDS);
	}

	/**
	 * 浏览器窗口最大化
	 */
	public static void maximize() {
		webDriver.manage().window().maximize();
	}

	/**
	 * 根据id移动并点击元素
	 * 
	 * @param id
	 */
	public static void moveAndClickElementByID(String id) {
		WebElement we = findElement(By.id(id));
		if (we != null) {
			util_log("move and click web element:" + we.toString());
			Actions action = new Actions(webDriver);
			action.moveToElement(we);
			util_pause(500);
			action.click(we);
			switchToDefaultFrame();
		} else {
			throw new Error("cannot find element by id:" + id);
		}
	}

	/**
	 * 跳转到url
	 * 
	 * @param url
	 */
	public static void navigate(String url) {
		webDriver.navigate().to(url);
	}

	/**
	 * 打开浏览器
	 * 
	 * @param type
	 *            浏览器类型
	 */
	public static void openBrowser(int type) {
		if (type == BROWSER_TYPE_IE) {
			if (ieDriverPath == null || "".equals(ieDriverPath)) {
				throw new Error("not config IEDriverPath!");
			}
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			webDriver = new InternetExplorerDriver();
		} else if (type == BROWSER_TYPE_FIREFOX) {
			ProfilesIni profilesini = new ProfilesIni();
			FirefoxProfile profile = profilesini.getProfile("default");
			profile.setPreference("plugin.state.java", 2);
			profile.setPreference("plugins.click_to_play", true);
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("browser.download.useDownloadDir", false);
			profile.setPreference("browser.download.downloadDir", "\\.");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/xlsx");
			profile.setPreference("browser.download.panel.shown", false);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("security.enable_java", true);
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.manager.showWhenStarting",
					false);
			profile.setPreference("browser.download.dir", "D:\\");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/zip");
			profile.setPreference("intl.accept_languages", "zh-CN");
			webDriver = new FirefoxDriver(profile);
		} else {
			throw new Error("unknown browser type!");
		}
		jsExecutor = (JavascriptExecutor) webDriver;
		setMainWindowHandle();
		webDriver.manage().timeouts()
				.pageLoadTimeout(DefaultPageLoadTimeout, TimeUnit.SECONDS);
		webDriver.manage().timeouts()
				.implicitlyWait(DefaultImplicitlyWait, TimeUnit.SECONDS);
		webDriver.manage().timeouts()
				.setScriptTimeout(DefaultScriptTimeout, TimeUnit.SECONDS);
	}

	/**
	 * 退出浏览器
	 */
	public static void quit() {
		webDriver.close();
		webDriver.quit();
	}

	public static void ScreenShoter(String filePathName) {
		File screenShotFile = ((TakesScreenshot) webDriver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShotFile, new File(filePathName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置元素显示的值
	 * 
	 * @param jsElement
	 * @param content
	 */
	public static void sendKeysToJsElement(String jsElement, String content) {
		assertJsElementExist("", jsElement);
		if (jsElement.startsWith("$")) {
			executeScript(jsElement + ".val('" + content + "')");
		} else {
			executeScript(jsElement + ".value=" + content);
		}
	}

	/**
	 * 设置断言时等待时间
	 * 
	 * @param seconds
	 */
	public static void setAssertWaitTime(long seconds) {
		DefaultAssertWaitTime = seconds;
	}

	/**
	 * 设置IE浏览器的driver路径
	 * 
	 * @param IEDriverPath
	 */
	public static void setIEDriverPath(String IEDriverPath) {
		ieDriverPath = IEDriverPath;
	}

	/**
	 * 设置查找元素等待时间
	 * 
	 * @param seconds
	 */
	public static void setImplicitlyWait(long seconds) {
		DefaultImplicitlyWait = seconds;
		webDriver.manage().timeouts()
				.implicitlyWait(seconds, TimeUnit.MILLISECONDS);
	}

	/**
	 * 设置元素属性
	 * 
	 * @param jsElement
	 * @param attr
	 * @param value
	 * @return
	 */
	public static Object setJsElementAttribute(String jsElement, String attr,
			String value) {
		assertJsElementExist("", jsElement);
		if (jsElement.startsWith("$")) {
			return executeScript("return " + jsElement + ".attr('" + attr
					+ "', '" + value + "')");
		} else {
			return executeScript("return " + jsElement + "." + attr + "= '"
					+ value + "'");
		}
	}

	/**
	 * 设置元素的css属性
	 * 
	 * @param jsElement
	 * @param cssAttr
	 * @param value
	 * @return
	 */
	public static Object setJsElementCssAttribute(String jsElement,
			String cssAttr, String value) {
		assertJsElementExist("", jsElement);
		if (jsElement.startsWith("$")) {
			return executeScript(jsElement + ".css('" + cssAttr + "', '"
					+ value + "')");
		} else {
			return executeScript(jsElement + ".style." + cssAttr + "= '"
					+ value + "'");
		}
	}

	/**
	 * 设置日志输出的格式，如果不设置，将按照默认格式进行输出
	 * 
	 * @param formatter
	 */
	public static void setLogFormatter(Formatter formatter) {
		if (logger == null) {
			logger = Logger.getLogger("");
		}
		Handler[] handlers = logger.getHandlers();
		for (Handler handler : handlers) {
			if (handler instanceof ConsoleHandler) {
				handler.setFormatter(formatter);
			}
		}
	}

	/**
	 * 设置主窗口的handle，默认是打开浏览器时的窗口为主窗口
	 */
	public static void setMainWindowHandle() {
		MainWindowHandle = webDriver.getWindowHandle();
	}

	/**
	 * 设置页面加载超时时间
	 * 
	 * @param seconds
	 */
	public static void setPageLoadTimeout(long seconds) {
		DefaultPageLoadTimeout = seconds;
		webDriver.manage().timeouts()
				.pageLoadTimeout(seconds, TimeUnit.MILLISECONDS);
	}

	/**
	 * 设置脚本运行超时时间
	 * 
	 * @param seconds
	 */
	public static void setScirptTimeout(long seconds) {
		DefaultScriptTimeout = seconds;
		webDriver.manage().timeouts()
				.setScriptTimeout(seconds, TimeUnit.MILLISECONDS);
	}

	/**
	 * 切换到默认的frame
	 */
	public static void switchToDefaultFrame() {
		webDriver.switchTo().defaultContent();
	}

	/**
	 * 切换到iframe，在只有一个iframe的情况下使用
	 * 
	 * @throws Exception
	 *             存在多个iframe
	 */
	public static void switchToFrame() throws Exception {
		switchToDefaultFrame();
		List<WebElement> wes = webDriver.findElementsByTagName("iframe");
		System.out.println("wes:" + wes.toString());
		if (wes.size() == 1) {
			webDriver.switchTo().frame(wes.get(0));
		} else {
			throw new Exception("存在多个iframe");
		}
	}

	/**
	 * 根据cssSelector切换iframe
	 * 
	 * @param cssSelector
	 */
	public static void switchToFrameByCssSelector(String cssSelector) {
		switchToDefaultFrame();
		webDriver.switchTo().frame(
				webDriver.findElementByCssSelector(cssSelector));
	}

	/**
	 * 根据id切换frame
	 * 
	 * @param id
	 */
	public static void switchToFrameByID(String id) {
		switchToDefaultFrame();
		webDriver.switchTo().frame(webDriver.findElementById(id));
	}

	/**
	 * 根据name切换frame
	 * 
	 * @param name
	 */
	public static void switchToFrameByName(String name) {
		switchToDefaultFrame();
		webDriver.switchTo().frame(webDriver.findElementByName(name));
	}

	/**
	 * 切换到主窗口
	 */
	public static void switchToMainWindow() {
		if ("".equals(MainWindowHandle) || MainWindowHandle == null) {
			throw new Error("主窗口没有设置.");
		}
		webDriver.switchTo().window(MainWindowHandle);
	}

	/**
	 * 根据窗口标题进行切换窗口，如果没有找到会抛出异常
	 * 
	 * @param windowTitle
	 * @throws Exception
	 */
	public static void switchToWindow(String windowTitle) {
		if (windowTitle == null) {
			throw new Error("param windowTitle is null!");
		}
		while (1 >= getExistWindowNumber()) {
			util_pause(100); // 此处避免由于新弹窗没有被获取到
		}
		Set<String> handles = webDriver.getWindowHandles();
		for (String s : handles) {
			if (s.equals(MainWindowHandle)) {
				continue;
			} else {
				webDriver.switchTo().window(s);
				if (webDriver.getTitle().contains(windowTitle)) {
					util_log("switch to window[" + windowTitle
							+ "] successfully!");
					return;
				} else {
					continue;
				}
			}
		}
		throw new Error("cannot find window with title: " + windowTitle);
	}

	/**
	 * 直接切换到指定handle的窗口
	 * 
	 * @param windowTitle
	 * @throws Exception
	 */
	public static void switchToWindow_byHandleString(String handle) {

		webDriver.switchTo().window(handle);
		util_pause(3000);
	}

	/***
	 * 取消浏览器全屏
	 * 
	 * @throws MalformedURLException
	 */
	public static void unsetBrowserFullScreen() {
		if (isBrowserFullScreen()) {
			webDriver.getKeyboard().pressKey(Keys.F11);
			util_pause(3000);
		}
	}

	private static void clickElement(WebElement we) {
		util_log("click web element:" + we.toString());
		we.click();
		switchToDefaultFrame();
	}

	private static void clickPointByRobot(int x, int y) {
		Log.info("click point:" + "x:" + x + " y:" + y);
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private static List<String> cutJsElement(String jscmd) {
		ArrayList<String> ll = new ArrayList<String>();
		boolean isInSingleQuotation = false;
		boolean isInQuotation = false;
		long length = jscmd.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			char a = jscmd.charAt(i);
			if (a == '\'') {
				if (isInSingleQuotation || isInQuotation) {
					isInSingleQuotation = false;
				} else {
					isInSingleQuotation = true;
				}
				sb.append(a);
			} else if (a == '"') {
				if (isInSingleQuotation || isInQuotation) {
					isInQuotation = false;
				} else {
					isInQuotation = true;
				}
				sb.append(a);
			} else if (a == '.' && !isInSingleQuotation && !isInQuotation) {
				ll.add(sb.toString());
				sb = new StringBuffer();
			} else {
				sb.append(a);
			}
		}
		if (!"".equals(sb.toString())) {
			ll.add(sb.toString());
		}
		return ll;
	}

	/**
	 * 根据给的条件，查找元素
	 * 
	 * @param by
	 * @return 查找到的元素，没有找到时返回null
	 */
	public static List<WebElement> findElements(By by) {
		List<WebElement> all = new ArrayList<WebElement>();
		switchToDefaultFrame();
		setImplicitlyWait(1);
		List<WebElement> wes = null;
		try {
			wes = webDriver.findElements(by);
		} catch (NoSuchElementException e) {
		}
		if (wes != null) {
			all.addAll(wes);
		}
		List<WebElement> frames = webDriver.findElementsByTagName("iframe");
		if (frames != null && frames.size() > 0) {
			for (WebElement frame : frames) {
				switchToDefaultFrame();
				util_log("find element by [" + by.toString() + "] in iframe");
				webDriver.switchTo().frame(frame);
				try {
					wes = webDriver.findElements(by);
				} catch (NoSuchElementException e) {
					continue;
				}
				if (wes != null) {
					all.addAll(wes);
				}
			}
		}
		if (all.size() == 0) {
			switchToDefaultFrame();
			util_log("cannot find elements by:" + by.toString());
		}
		setImplicitlyWait(DefaultImplicitlyWait);
		return all;
	}

	private static int getExistWindowNumber() {
		Set<String> handles = CommonObject.driver.getWindowHandles();
		int num = handles.size();
		for (String s : handles) {
			CommonObject.driver.switchTo().window(s);
			try {
				CommonObject.driver.getWindowHandle();
			} catch (NoSuchWindowException e) {
				num--;
				continue;
			} catch (Error e) {
				num--;
				continue;
			}
		}
		return num;
	}

	private static WebElement getFrameByElementID(By by) {
		switchToDefaultFrame();
		setImplicitlyWait(1);
		try {
			webDriver.findElement(by);
		} catch (NoSuchElementException e) {
			List<WebElement> frames = webDriver.findElementsByTagName("iframe");
			util_log("iframe size:" + frames.size());
			if (frames != null && frames.size() > 0) {
				for (WebElement frame : frames) {
					switchToDefaultFrame();
					webDriver.switchTo().frame(frame);
					try {
						webDriver.findElement(by);
						util_log("find element by [" + by.toString()
								+ "] in iframe");
						switchToDefaultFrame();
						setImplicitlyWait(DefaultImplicitlyWait);
						return frame;
					} catch (NoSuchElementException e1) {
					}
				}
			}
		}
		return null;

	}

	/***
	 * 判断浏览器是否全屏
	 * 
	 * @return 如果全屏，返回true；如果非全屏，返回false
	 */
	private static boolean isBrowserFullScreen() {
		boolean flag = false;
		String ScreenW = String.valueOf(Toolkit.getDefaultToolkit()
				.getScreenSize().width);
		String ScreenH = String.valueOf(Toolkit.getDefaultToolkit()
				.getScreenSize().height);
		String WindowW = null;
		String WindowH = null;
		if (webDriver instanceof InternetExplorerDriver) {
			WindowW = executeScript(
					"return document.documentElement.clientWidth;").toString();
			WindowH = executeScript(
					"return document.documentElement.clientHeight;").toString();

		} else if (webDriver instanceof FirefoxDriver) {
			WindowW = executeScript("return window.outerWidth;").toString();
			WindowH = executeScript("return window.outerHeight;").toString();
		}
		if (WindowW.equals(ScreenW) && WindowH.equals(ScreenH)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 检查web页面元素是否存在 传入参数为js语句,如:$('div#geoLoading')
	 */
	private static boolean isJsElementExist(String element) {
		String jscmd = "return document.getElementById('XXX')";
		if (element.trim().startsWith("$")) {
			jscmd = jscmd.replace("document.getElementById('XXX')", element
					+ ".length>0");
		} else {
			jscmd = jscmd.replace("document.getElementById('XXX')", element);
		}
		return (Boolean) executeScript(jscmd);
	}

	/***
	 * 浏览器全屏
	 * 
	 * @throws MalformedURLException
	 */
	public static void setBrowserFullScreen() {
		if (!isBrowserFullScreen()) {
			System.out.println("browserfull1...");
			webDriver.getKeyboard().pressKey(Keys.F11);
			util_pause(3000);
			System.out.println("browserfull2...");
		}
		/*
		 * CommonObject.driver.manage().window().maximize(); util_pause(3000);
		 * System.out.println("browserfull...");
		 */
	}

	public static void setScroll(int height) {
		Log.info("scroll top :" + height);
		try {
			String setscroll = "document.documentElement.scrollTop=" + height;
			executeScript(setscroll);
		} catch (Exception e) {
			System.out.println("Fail to set the scroll.");
		}
	}

	private static String util_join(String s, List<String> ls, int end) {
		if (ls == null || ls.size() == 0)
			return "";
		StringBuffer sb = new StringBuffer();
		int min = ls.size() < end ? ls.size() : end;
		if (min > 0) {
			sb.append(ls.get(0));
		}
		for (int i = 1; i < min; i++) {
			sb.append(s).append(ls.get(i));
		}
		return sb.toString();
	}

	/**
	 * 简单的日志方法，用户可以将里面的内容替换为自己的日志记录方法
	 * 
	 * @param info
	 *            要打印的日志
	 */
	private static void util_log(String info) {
		if (logger == null) {
			logger = Logger.getLogger("");
			Handler[] handlers = logger.getHandlers();
			for (Handler handler : handlers) {
				if (handler instanceof ConsoleHandler) {
					handler.setFormatter(new SSALogFormatter());
				}
			}
		}
		logger.log(Level.INFO, info);
	}

	/**
	 * 等待ms毫秒
	 * 
	 * @param ms
	 */
	public static void util_pause(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}

	private static void waitJsElementExist(String jsElement) {
		for (int i = 0; i < DefaultAssertWaitTime; i++) {
			if (isJsElementExist(jsElement)) {
				return;
			} else {
				util_pause(1000);
			}
		}
	}

	/**
	 * 关闭该窗口
	 * 
	 * @param windowTitle
	 * @throws Exception
	 */
	public static void findWindow_closewindow(String windowTitle) {
		if (windowTitle == null) {
			throw new Error("param windowTitle is null!");
		}
		while (1 >= getExistWindowNumber()) {
			util_pause(100); // 此处避免由于新弹窗没有被获取到
		}
		Set<String> handles = webDriver.getWindowHandles();
		for (String s : handles) {
			if (s.equals(MainWindowHandle)) {
				continue;
			} else {
				webDriver.switchTo().window(s).close();
				Pause.pause(2000);
				webDriver.switchTo().window(MainWindowHandle);
				if (webDriver.getTitle().contains(windowTitle)) {
					util_log("switch to window close[" + windowTitle
							+ "] successfully!");
					return;
				} else {
					continue;
				}
			}
		}
		throw new Error("cannot find window with title: " + windowTitle);
	}

}

class SSALogFormatter extends Formatter {

	public static String getDateString() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		Date nowdate = c.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowdate);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.format(date);
	}

	@Override
	public String format(LogRecord record) {
		return getDateString() + "[" + record.getLevel() + "]"
				+ record.getMessage() + "\n";
	}
}
