package cloud_public.page;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import common.util.CommonJQ;
import common.util.CommonObject;

public class LoadingPage {

	/**
	 * <b>Description:</b>等待页面加载完毕
	 *
	 * @author lwx242612
	 * @since 2016年6月28日
	 * @param driver
	 * @param methodName
	 * @return 
	 * @return void
	 */
	public static void Loading(final WebDriver driver) {

		boolean flage = false;
		for(int i=0;i<120;i++){
			flage = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState")
						.equals("complete");
			if(flage){
				return ;
			}else{
				Pause.pause(500);
			}
		}
		if(flage==false){
			Assert.fail("页面加载超时:" + 60 + "秒");
		}
		if(CommonJQ.isEnabled(driver, "iframe")){
			CommonJQ.excuteJS(driver, "$('iframe').load(function(){});");			
		}
	}

	
	/**
	 * <b>Description:</b>等待页面加载完毕(通过指定的元素是否加载完毕判断)
	 *
	 * @author lwx242612
	 * @since 2016年6月28日
	 * @param driver
	 * @param methodName
	 * @return void
	 */
	public static void Loading(final WebDriver driver,String selector) {
		Loading( driver, selector, ""); 
	}
	public static void Loading(final WebDriver driver,String selector,String Message) {
		boolean flage = false;
		for(int i=0;i<30;i++){
			 flage = CommonJQ.isExisted(driver, selector,true);
			if(flage){
				return ;
			}else{
				Pause.pause(500);
			}
		}
		if(!flage){
			Assert.fail("页面加载超时:" + 15 + "秒"+"\""+Message+"\""+"未找到,PageLoad failure,not find selector:"+selector);
		}
	}	
	// 等待页面信息加载 
	public static void waitMask(WebDriver driver,String selector) {
		final int minute = 2;
		final int timeout = minute * 60 * 1000;
		int times = 0;
		boolean iswaitMask = CommonJQ.excuteJStoBoolean(driver,
				"return $('"+selector+"').is(':visible');");
		while (iswaitMask) {
			Pause.pause(100);
			times++;
			Assert.assertTrue("页面等待超时" + minute + "分钟", times < timeout / 100);
			iswaitMask = CommonJQ.excuteJStoBoolean(driver,
					"return $('"+selector+"').is(':visible');");
		}

	}
	
	
	/**
	 * 等待界面加载
	 * @param driver
	 */
	public static void waitForPagLoad(WebDriver driver)
	{
		final WebDriver web = driver;
		try {
			(new WebDriverWait(web,30)).until(new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver d){
					return CommonJQ.excuteJStoString(web, "return document.readyState").equals("complete");
				}
			});

		} catch (Exception e) {
			Assert.fail("页面加载超时"+CommonObject.driver.getCurrentUrl());
		}
	}

}
