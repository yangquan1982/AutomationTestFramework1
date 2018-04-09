package cloud_public.page;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;

public class ButtonPage {
	
	public static String confirm = "确定";
	public static String cancel = "取消";
	public static String Save = "保存";
	public static String Submit = "提交";
	
	public static void clickMessageConfirmBTN(WebDriver driver){
		CommonJQ.click(driver, "span.l-btn-text:contains("+confirm+")", true);
		LoadingPage.Loading(driver);
	}	
	
	public static void clickMessageCancelBTN(WebDriver driver){
		CommonJQ.click(driver, "span.l-btn-text:contains("+cancel+")", true);
		LoadingPage.Loading(driver);
	}
	
	
	public static void clickFormSaveBTN(WebDriver driver){
		CommonJQ.excuteJS(driver, "$('span:contains("+Save+")').not(':has(span)').click();");
		LoadingPage.Loading(driver);
	}
	
	public static void clickFormSubmitBTN(WebDriver driver){
		CommonJQ.excuteJS(driver, "$('span:contains("+Submit+")').not(':has(span)').click();");
		LoadingPage.Loading(driver);
	}
	
	public static void clickFormCancelBTN(WebDriver driver){
		CommonJQ.excuteJS(driver, "$('span:contains("+cancel+")').not(':has(span)').click();");
		LoadingPage.Loading(driver);
	}
	
	
	

}
