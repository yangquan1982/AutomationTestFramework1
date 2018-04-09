package common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtil {

	/**
	 * 根据className查找元素并点击
	 * 
	 * @param className
	 */
	public static void click_ByClassName(WebDriver driver,String className) {
		WebElement we = SeleniumUtil.AssertElement(driver, "className", className);			
		try {
			we.click();
		} catch (Exception e) {
			Assert.fail("clickElement is fail.");
		}
	}
	/**
	 * 根据className查找元素并赋值
	 * 
	 * @param className
	 */
	public static void val_ByClassName(WebDriver driver,String className,String text) {
		WebElement we = SeleniumUtil.AssertElement(driver, "className", className);			
		try {
			we.clear();
			we.sendKeys(text);
		} catch (Exception e) {
			Assert.fail("valElement is fail.");
		}
	}
	/**
	 * 根据id查找元素并点击
	 * 
	 * @param id
	 */
	public static void click_ById(WebDriver driver,String id) {
		WebElement we = SeleniumUtil.AssertElement(driver, "id", id);			
		try {
			we.click();
			System.out.println(ZIP.NowTime()+"clickElement is succ,id:"+id);
		} catch (Exception e) {
			Assert.fail("clickElement is fail.");
		}
	}
	/**
	 * 根据id查找元素并赋值
	 * 
	 * @param id
	 */
	public static void val_ById(WebDriver driver,String id,String text) {
		WebElement we = SeleniumUtil.AssertElement(driver, "id", id);			
		try {
			we.clear();
			we.sendKeys(text);
		} catch (Exception e) {
			Assert.fail("valElement is fail.");
		}
	}
	/**
	 * 根据cssSelector查找元素并点击
	 * 
	 * @param cssSelector
	 */
	public static void click_ByCssSelector(WebDriver driver,String cssSelector) {
		WebElement we = SeleniumUtil.AssertElement(driver, "cssSelector", cssSelector);			
		try {
			we.click();
		} catch (Exception e) {
			Assert.fail("clickElement is fail.");
		}
	}
	/**
	 * 根据name查找元素并点击
	 * 
	 * @param name
	 */
	public static void click_ByName(WebDriver driver,String name) {
		WebElement we = SeleniumUtil.AssertElement(driver, "name", name);			
		try {
			we.click();
		} catch (Exception e) {
			Assert.fail("clickElement is fail.");
		}
	}
	/**
	 * 根据name查找元素并赋值
	 * 
	 * @param name
	 */
	public static void val_ByName(WebDriver driver,String name,String text) {
		WebElement we = SeleniumUtil.AssertElement(driver, "name", name);			
		try {
			we.clear();
			we.sendKeys(text);
		} catch (Exception e) {
			Assert.fail("valElement is fail.");
		}
	}
	/**
	 * 根据linkText查找元素并点击
	 * 
	 * @param linkText
	 */
	public static void click_ByLinkText(WebDriver driver,String linkText) {
		WebElement we = SeleniumUtil.AssertElement(driver, "linkText", linkText);			
		try {
			we.click();
		} catch (Exception e) {
			Assert.fail("clickElement is fail.");
		}
	}
	/**
	 * 根据tagName查找元素并点击
	 * 
	 * @param tagName
	 */
	public static void click_ByTagName(WebDriver driver,String tagName) {
		WebElement we = SeleniumUtil.AssertElement(driver, "tagName", tagName);			
		try {
			we.click();
		} catch (Exception e) {
			Assert.fail("clickElement is fail.");
		}
	}
	/**
	 * 根据tagName查找元素并点击
	 * 
	 * @param tagName
	 */
	public static void click_ByPartialLinkText(WebDriver driver,String partialLinkText) {
		WebElement we = SeleniumUtil.AssertElement(driver, "partialLinkText", partialLinkText);			
		try {
			we.click();
		} catch (Exception e) {
			Assert.fail("clickElement is fail.");
		}
	}
	/**
	 * 根据xpath查找元素并点击
	 * 
	 * @param xpath
	 */
	public static void click_ByXpath(WebDriver driver,String xpath) {
		WebElement we = SeleniumUtil.AssertElement(driver, "xpath", xpath);			
		try {
			we.click();
		} catch (Exception e) {
			Assert.fail("clickElement is fail.");
		}
	}
	/**
	 * 根据xpath查找元素并赋值
	 * 
	 * @param xpath
	 */
	public static void val_ByXpath(WebDriver driver,String xpath,String text) {
		WebElement we = SeleniumUtil.AssertElement(driver, "xpath", xpath);			
		try {
			we.clear();
			we.sendKeys(text);
		} catch (Exception e) {
			Assert.fail("valElement is fail.");
		}
	}
	/**
	 * 
	 * @param driver
	 * @param Type
	 * @param Name
	 * @return
	 */
	public static boolean isExisted(WebDriver driver,String Type, String Name) {
		WebElement we =null;
		if("className".equals(Type)){
			 we = driver.findElement(By.className(Name));
		}else if ("id".equals(Type)){
			 we = driver.findElement(By.id(Name));
		}else if ("cssSelector".equals(Type)){
			 we = driver.findElement(By.cssSelector(Name));
		}else if ("name".equals(Type)){
			 we = driver.findElement(By.name(Name));
		}else if ("linkText".equals(Type)){
			 we = driver.findElement(By.linkText(Name));
		}else if ("tagName".equals(Type)){
			 we = driver.findElement(By.tagName(Name));
		}else if ("partialLinkText".equals(Type)){
			 we = driver.findElement(By.partialLinkText(Name));
		}else if ("xpath".equals(Type)){
			 we = driver.findElement(By.xpath(Name));
		}else{
			Assert.fail("findElement Type is err,Type:"+Type);
		}
		if(we == null){
			System.out.println(ZIP.NowTime()+"Can't find elements,"+Type+":"+Name);
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 
	 * @param driver
	 * @param Type
	 * @param Name
	 * @return
	 */
	private static WebElement AssertElement(WebDriver driver,String Type, String Name) {
		WebElement we = null;
		boolean flage = SeleniumUtil.isExisted(driver, Type, Name);
		for(int i = 0;i<2;i++){
			if(flage){
				break;
			}else{
				Pause.pause(1000);
				flage = SeleniumUtil.isExisted(driver, Type, Name);
			}
		}
		if(flage==false){
			Assert.fail("Can't find elements,"+Type+":"+Name);
		}else{
			if(Type.equals("className")){
				 we = driver.findElement(By.className(Name));
			}else if ("id".equals(Type)){
				 we = driver.findElement(By.id(Name));
			}else if ("cssSelector".equals(Type)){
				 we = driver.findElement(By.cssSelector(Name));
			}else if ("name".equals(Type)){
				 we = driver.findElement(By.name(Name));
			}else if ("linkText".equals(Type)){
				 we = driver.findElement(By.linkText(Name));
			}else if ("tagName".equals(Type)){
				 we = driver.findElement(By.tagName(Name));
			}else if ("partialLinkText".equals(Type)){
				 we = driver.findElement(By.partialLinkText(Name));
			}else if ("xpath".equals(Type)){
				 we = driver.findElement(By.xpath(Name));
			}else{
				Assert.fail("findElement Type is err,Type:"+Type);
			}
		}
		return we;
	}
	
	
	public static void killJAVA() {

		try {
			Runtime.getRuntime().exec("taskkill /f /IM iexplore.exe");//关闭IE进程
			Runtime.getRuntime().exec("taskkill /f /IM WerFault.exe");//关闭IE错误进程
			Process	proc = Runtime.getRuntime().exec("wmic process where caption=\"java.exe\" get commandline,Handle");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			try {
				while ((line = bufferedReader.readLine()) != null) {
					if (line.contains("GENEXCloudRmiService")) {
						//System.out.println("line:"+line);
						line=line.trim();
						String pid=line.substring(line.lastIndexOf(" "),line.length());
						System.out.println("pid:"+pid);
						String jscmd="taskkill /F -pid "+pid;
						System.out.println(jscmd);
						Runtime.getRuntime().exec(jscmd); 

				    }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		killjp2launcher();
		}
	
	private static void killjp2launcher(){
		try {
			Process proc = Runtime.getRuntime().exec("wmic process where caption=\"jp2launcher.exe\" get commandline,Handle");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			try {
				while ((line = bufferedReader.readLine()) != null) {
					if (line.contains("GENEXCloudRmiService")) {
						//System.out.println("line:"+line);
						line=line.trim();
						String pid=line.substring(line.lastIndexOf(" "),line.length());
						System.out.println("pid:"+pid);
						String jscmd="taskkill /F -pid "+pid;
						System.out.println(jscmd);
						Runtime.getRuntime().exec(jscmd); 
				    }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
}
