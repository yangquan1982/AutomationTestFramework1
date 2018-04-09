package common.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.huawei.hutaf.webtest.htmlaw.BasicAction;

import cloud_platform.page.community.CommunityPage;


public class CommonObject 
{
	//public static ScreenRegion screenRegion = new DesktopScreenRegion();
	public static String defaultWindowID = null; 
	//public static RemoteWebDriver driver = null;
	public static RemoteWebDriver driver = null;
	public static JavascriptExecutor js = null;
	public static String downloadPath="D:\\NACWebAutoTest\\download_temp\\";
	public static CommunityPage communitypage = null;
	
	
	
	
	
	
	/**
	 * @author zWX192859
	 * 
	 *         add webdriver.ie.driver
	 */
	/*public static RemoteWebDriver getWebDriver() 
	{
		AwCommon.killWebBrowser();
		String browserType = Config.getBrowse.rType();
		
		if ("ie".equals(browserType)) 
		{
			System.setProperty("webdriver.ie.driver", Parameter.IEDriver);
			return new InternetExplorerDriver();
		}
		else 
		{
			ProfilesIni profilesini = new ProfilesIni();
			FirefoxProfile profile = profilesini.getProfile("default"); //firefox.exe -ProfileManager���������ù�����
			profile.setPreference("plugin.state.java",2);
			profile.setPreference("plugins.click_to_play",true);
//			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
//			profile.updateUserPrefs(new File("D:\\NACWebAutoTest\\userprofile"));
			profile.setPreference("browser.download.useDownloadDir", false);
			profile.setPreference("browser.download.downloadDir", "\\.");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/xlsx");
			profile.setPreference("browser.download.panel.shown", false);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			
			profile.setPreference("security.enable_java", true);
			
			profile.setPreference("browser.download.folderList",2);
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			profile.setPreference("browser.download.dir","D:\\");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/zip");
			
			profile.setPreference("intl.accept_languages","zh-CN"); 
			profile.setPreference("security.default_personal_cert","Always Activate"); 
			profile.setPreference("security.alternate_certificate_error_page","Always Activate"); 
			profile.setPreference("dom.successive_dialog_time_limit",0); 
			profile.setPreference("dom.popup_maximum",9999); 
//			DesiredCapabilities caps = DesiredCapabilities.firefox();
//			caps.setCapability(FirefoxDriver.PROFILE, profile);
			
			profile.setPreference("extensions.yslow.autorun", true);
			profile.setPreference("extensions.yslow.beaconInfo", "grade");
			profile.setPreference("extensions.yslow.beaconUrl", "http://127.0.0.1:8081/showslow/beacon/yslow/");
			profile.setPreference("extensions.yslow.optinBeacon", true);
			
			profile.setPreference("extensions.firebug.allPagesActivation", "on");
			profile.setPreference("extensions.firebug.net.enableSites", true);
			
			return new FirefoxDriver(profile);
		}
	}*/

	public static void initWebDriver()
	{
		driver = (RemoteWebDriver)BasicAction.getDriver();
		TestWebDriver.init(driver);
		driver.manage().timeouts().pageLoadTimeout(60,java.util.concurrent.TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		js = (JavascriptExecutor)driver;
		
		defaultWindowID = driver.getWindowHandle();
		communitypage  = PageFactory.initElements(driver,CommunityPage.class);
		
		
		
	}
	
	//判断是已加载jQuery  
    public static Boolean jQueryLoaded() 
    {  
        Boolean loaded;  
        try 
        {  
            loaded = (Boolean) js.executeScript("return " + "jQuery()!=null");  
        } 
        catch (WebDriverException e) 
        {  
            loaded = false;  
        }  
        
        return loaded;  
    }  
  
    //通过注入jQuery 
    public static void injectjQuery() 
    {  
        js.executeScript(" var headID = "  
                + "document.getElementsByTagName(\"head\")[0];"  
                + "var newScript = document.createElement('script');"  
                + "newScript.type = 'text/javascript';" + "newScript.src = "  
                + "'/nac/js/common/jquery-1.9.1.min.js';"  
                + "headID.appendChild(newScript);");  
    }  
    
    
    public static void main(String[] args) {
		
    	String filegbk = "D:\\WebTest\\Workspace2\\webtest\\GENEXCloud_V3_P1\\src\\util\\TestWebDriver.java";
    	String fileutf = "D:\\WebTest\\Workspace2\\webtest\\GENEXCloud_V3_P1\\src\\util\\TestWebDriverUtf8.java";
    	
    	try {
			FileUtils.writeLines(new File(fileutf), "UTF-8", FileUtils.readLines(new File(filegbk),"GBK"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    
	
}
