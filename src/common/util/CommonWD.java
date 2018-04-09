package common.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.page.LoginPage;
import common.constant.constparameter.ConstUrl;
import common.constant.system.EnvConstant;



/**
 * <b>Description:</b> Common APIs Of WebDriver.
 * 
 * @author s00330949
 *
 */
public class CommonWD {

	public static String defaultWindowID = null; 
	public static RemoteWebDriver driver = null;
	public static String MainWindowHandle = null; 
	

	public static WebDriver getWebDriver() {
		System.out.println(ZIP.NowTime() + "Start the browser");
		SeleniumUtil.killJAVA();

		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyPress(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_WINDOWS);
			robot.keyRelease(KeyEvent.VK_D);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		final String browserType = ConstUrl.getBrowserType();
		if (StringUtils.equalsIgnoreCase(browserType, "ie")) {
			System.setProperty("webdriver.ie.driver", ConstUrl.IEDRIVER);
			DesiredCapabilities ieCapabilities=new DesiredCapabilities();
			ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true  );
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			RemoteWebDriver driver = new InternetExplorerDriver(ieCapabilities);
			CommonWD.init(driver);
			System.out.println(ZIP.NowTime() + "IE has started");
			return driver;
		}
		if (StringUtils.equalsIgnoreCase(browserType, "firefox")) {
			try {
				Runtime.getRuntime().exec("wmic process where name=\"firefox.exe\" call terminate");
				Runtime.getRuntime().exec("javaws -uninstall");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FirefoxProfile profile = new FirefoxProfile();
			CommonWD.setPreference(profile);
			RemoteWebDriver driver = new FirefoxDriver(profile);			
			CommonWD.init(driver);
			System.out.println(ZIP.NowTime()+"Firefox has started");
			return driver;
		}
		if (StringUtils.equalsIgnoreCase(browserType, "chrome")) {
			System.setProperty("webdriver.chrome.driver", ConstUrl.CHROMEDRIVER);
			ChromeOptions options = new ChromeOptions();
			RemoteWebDriver driver = new ChromeDriver(options);
			CommonWD.init(driver);
			System.out.println(ZIP.NowTime()+"Chrome has started");
			return driver;
		}
		Assert.fail("error browserType:" + StringUtils.trimToEmpty(browserType));
		return null;
	}

	private static void init(RemoteWebDriver driver){	
		String mainhandl = "";
		for (int i = 0; i < 100; i++) {
			mainhandl = driver.getWindowHandle();
			if(StringUtils.isBlank(mainhandl)){
				Pause.pause(1000);
			}else{
				break;
			}
		}
		MainWindowHandle = mainhandl;		
	}
	public static void switchToMainWindow(WebDriver driver) {
		if ("".equals(MainWindowHandle) || MainWindowHandle == null) {
			throw new Error("主窗口没有设置.");
		}
		driver.switchTo().window(MainWindowHandle);
	}
	private static void setPreference(FirefoxProfile profile) {

		profile.setAcceptUntrustedCertificates(true);
		profile.setPreference("security.default_personal_cert", "Select Automatically");
		profile.setPreference("plugin.state.java",2);
		profile.setPreference("plugins.click_to_play",true);
		profile.setPreference("intl.accept_languages","zh-CN"); 
		profile.setPreference("security.default_personal_cert","Always Activate"); 
		profile.setPreference("security.alternate_certificate_error_page","Always Activate"); 
		setProxy(profile, null);
		setDownloadPath(profile);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/zip,text/plain," + "application/vnd.ms-excel," + "text/csv,text/comma-separated-values,"
						+ "application/octet-stream,"
						+ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,"
						+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document" + "image/png");
	}

	private static void setDownloadPath(FirefoxProfile profile) {
		profile.setPreference("browser.download.dir", EnvConstant.Path_DownLoad);
		System.out.println(ZIP.NowTime()+"Path_DownLoad:"+EnvConstant.Path_DownLoad);
		profile.setPreference("browser.download.folderList", 2); // browser.download.folderList
		profile.setPreference("browser.download.useDownloadDir", true);
	}

	private static void setProxy(FirefoxProfile profile, String address) {
		profile.setPreference("network.proxy.type", 1);
//		profile.setPreference("network.proxy.http", address);// "proxycn2.huawei.com"
//		profile.setPreference("network.proxy.http_port", 8080);
//		profile.setPreference("network.proxy.ssl", address);
//		profile.setPreference("network.proxy.ssl_port", 8080);
//		profile.setPreference("network.proxy.ftp", address);
		profile.setPreference("dom.successive_dialog_time_limit", 0);
		profile.setPreference("dom.popup_maximum", 9999);
		profile.setPreference("network.proxy.ftp_port", 8080);
//		profile.setPreference("network.proxy.socks", address);
		profile.setPreference("network.proxy.socks_port", 8080);
		profile.setPreference("network.proxy.no_proxies_on", "10.0.0.0/8");
//		System.out.println("CURRENTPROXY" + address);
	}


	public static void clear(WebDriver driver, WebElement element) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
			element.clear();
		} catch (Exception e) {
			final JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			js.executeScript("arguments[0].value='';", element);
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public static void sendKeys(WebDriver driver, WebElement element, CharSequence... acharsequence) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
			element.sendKeys(acharsequence);
		} catch (Exception e) {
			final JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			js.executeScript("arguments[0].value='" + acharsequence + "';", element);
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	private static ArrayList<String> getAllWebBrowserPID() {
		ArrayList<String> pids = new ArrayList<String>();
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("tasklist");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner in = new Scanner(process.getInputStream());
		while (in.hasNextLine()) {
			String p = in.nextLine();
			if (p.contains("firefox.exe")) {
				StringBuffer buf = new StringBuffer();
				for (int i = 0; i < p.length(); i++) {
					char ch = p.charAt(i);
					if (ch != ' ') {
						buf.append(ch);
					} else if (buf.charAt(buf.length() - 1) != ' ') {
						buf.append(' ');
					}
				}
				pids.add(buf.toString().split(" ")[1]);
			}
		}
		in.close();
		return pids;
	}

	public static void killWebBrowser(WebDriver driver) throws Exception {
		if (!ConstUrl.killWebAfterRun()) {
			return;
		}
		while (true) {
			ArrayList<String> pids = getAllWebBrowserPID();
			if (pids.size() != 0) {
				for (String pid : pids) {
					try {
						if (driver != null) {
							driver.quit();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Runtime run = Runtime.getRuntime();
					try {
						Pause.pause();
						run.exec("taskkill /PID " + pid);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				break;
			}
		}
	}

	public static void click(WebDriver driver, WebElement element) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
			element.click();
		} catch (Exception e) {
			final JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", element);
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}



	public static void openFirefoxBrowser(WebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.navigate().to(ConstUrl.LOGINURL + "/portal");
		driver.manage().window().maximize();
	}

	/**
	 * 打开浏览器进入环境初始界面
	 * @param driver
	 */
	public static void openHomePage(WebDriver driver) {
		driver.navigate().to(ConstUrl.LOGINURL);
		System.out.println(ZIP.NowTime()+ConstUrl.LOGINURL);
		driver.manage().window().maximize();
		if(CommonJQ.isExisted(driver, LoginPage.LoginID)){
			CommonJQ.click(driver, LoginPage.LoginID, true);
			LoadingPage.Loading(driver);
		}
	}
	private static final String HOUR_LI_CLASS = ".hour-list > li ";
	private static final String HOUR_SELECT_CLASS = "ui-selectee.ui-selected";
	private static final String HOUR_SELECT_CLASS_COPY = "ng-binding.ng-scope.selected";

	/**
	 * <b>Description:</b>选择指定小时数
	 *
	 * @author lwx242612
	 * @since 2016年7月7日
	 * @param driver
	 * @param hours
	 * @return void
	 */
	public static void chooseHours(WebDriver driver, String[] hours) {
		Actions builder = new Actions(driver);
		WebElement hourList = getVisible(driver.findElements(By.cssSelector(".hour-list")));
		if (null == hourList) {
			
		}
		builder.keyDown(Keys.LEFT_CONTROL).perform();
		for (WebElement e : hourList.findElements(By.tagName("li"))) {
			if (ArrayUtils.contains(hours, e.getText())) {
				String hourClass = CommonJQ.getAttrValue(driver, HOUR_LI_CLASS, "class", Integer.valueOf(e.getText()));
				if (!ArrayUtils.isEquals(HOUR_SELECT_CLASS, hourClass)
						&& !ArrayUtils.isEquals(HOUR_SELECT_CLASS_COPY, hourClass)) {
					CommonWD.click(driver, e);
				}
			}
		}
		builder.keyUp(Keys.LEFT_CONTROL).perform();
	}

	/**
	 * <b>Description:</b> 选择可见元素
	 *
	 * @since 2016年7月11日
	 * @param findElements
	 * @return
	 * @return WebElement
	 */
	private static WebElement getVisible(List<WebElement> findElements) {
		for (WebElement element : findElements) {
			if (element.isDisplayed()) {
				return element;
			}
		}
		return null;
	}
	
	public static String getWindowHandle(WebDriver driver,String windowsid) {
		Set<String> Uploadhandles = driver.getWindowHandles();
		System.out.println("Handles_1:" + Uploadhandles.toString());
		String Uploadhandle = null;
		
		for (int i = 0; i < 100; i++) {
			if(Uploadhandles.size()>1&&!Uploadhandles.contains(""))	break;			
			Pause.pause(1000);
			Uploadhandles = driver.getWindowHandles();
			System.out.println("Handles_1循环:" + Uploadhandles.toString());
			
		}
		if(Uploadhandles.size()>0){
			for (String string : Uploadhandles) {
				if(StringUtils.isBlank(string)){
					Uploadhandles = driver.getWindowHandles();
				}
			}
			
		}
		for (String s : Uploadhandles) {
			if (s.contains(windowsid)) {
				continue;
			} else {
				Uploadhandle = s;
				break;
			}
		}
		return Uploadhandle;
	}
	
	public static void changePageDriver(WebDriver driver) {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (StringUtils.equals(handle, driver.getWindowHandle())) {
				continue;
			}
			driver.switchTo().window(handle);
		}
	}
	
	public static void driverMax(WebDriver driver){
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_F11);
			robot.keyRelease(KeyEvent.VK_F11);
			Pause.pause(1000);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Set<String> getAllHandle(WebDriver driver,int handnum){
		Set<String> allhandls = null;
		for(int i =0;i<100;i++){
			allhandls = driver.getWindowHandles();
			if(allhandls.size() ==handnum&&(!allhandls.contains(""))) break;
			else Pause.pause(1000);
		}
		return allhandls;
	}
	
	public static Set<String> getAllHandle(WebDriver driver){
		Set<String> allhandls = null;
		for(int i =0;i<100;i++){
			allhandls = driver.getWindowHandles();
			if(allhandls.size()>1&&(!allhandls.contains(""))) break;
			else Pause.pause(1000);
		}
		return allhandls;
	}
	
	
	

}
