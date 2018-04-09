package cloud_public.page;

import java.awt.event.KeyEvent;
import java.util.Set;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.upload_data.DataUpload_baseTask;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class GetDataByTypePage {

	// const string.
	public static final String TextName = "#textName";
	public static final String SearchBtn = "#searchBtu";	
	public static final String SubmitBtn = "#submitButton";
	public static final String PageSize = "#pageSize";//"div.x-grid-row-checker"
	public static final String CheckRow = "div.x-grid-row-checker";
	public static final String ListBox = "div.r_device_list_box";
	public static final String InputVisible = "input[type=radio]:visible";

	/**
	 * 点击文件复选框
	 * 
	 * @param file
	 */
	public static void clickcheckboxs(WebDriver driver,String file) {
		
		String jselemnet = "$('span[title=\""+file+"\"]').parents(\"td\").prev().find('div.x-grid-row-checker')";
		CommonJQ.click(driver, jselemnet);

		CommonJQ.click(driver,GetDataByTypePage.SubmitBtn, true,"数据选择页面->确认选择按钮");

		
	}
	public static void searchInChoosePage(WebDriver driver,String name){
		boolean NameFlage = CommonJQ.isExisted(driver, GetDataByTypePage.TextName, true);
		if(NameFlage==false){
			CommonJQ.click(driver, "#fileter", true, "数据选择页面->过滤");
		}
	    CommonJQ.value(driver, GetDataByTypePage.TextName, name,true,"数据选择页面->名称输入框");
	    CommonJQ.click(driver,GetDataByTypePage.SearchBtn, true,"数据选择页面->搜索按钮");
	    driver.switchTo().defaultContent();
}
	public static void clickRidoBtn(WebDriver driver,String file) {
		String jselement = "$('span[title=\""+file+"\"]').parents(\"td\").prev().find('input.parasSelectRadio').eq(0)";
		CommonJQ.click(driver, jselement);

		CommonJQ.click(driver, GetDataByTypePage.SubmitBtn, true,"数据选择页面->确认选择按钮");   
     
	}
	public static void closeOtherWindows(WebDriver driver,String defaultWindowID) {
		Set<String> wins = driver.getWindowHandles();
		Object[] wa = wins.toArray();
		

		for (Object win : wa) {
			if (win.toString().equals(defaultWindowID)) {
				continue;
			} else {
				System.out.println(ZIP.NowTime()+"window close:"+win.toString());
				SwitchDriver.winIDClose(driver, win.toString());
				CommonJQ.clickKey(KeyEvent.VK_ENTER);
				DataUpload_baseTask.closeleavePage();
				SwitchDriver.switchToWinID(driver, defaultWindowID); 
			}
		}
		SwitchDriver.switchToWinID(driver, defaultWindowID); 
		for(int i=0;i<5;i++){
			 int length =driver.getWindowHandles().size();
			 if(length != 1){
				 System.out.println(ZIP.NowTime()+"It is not equal to 1,WindowID size:"+length);
				 Pause.pause(1000); 
				 length =driver.getWindowHandles().size();
			 }else{
				 SwitchDriver.switchToWinID(driver, defaultWindowID);
					if(driver.getWindowHandle().equals(defaultWindowID)){
						Pause.pause(1000);
						break;
					}else{
						Pause.pause(1000);
					}

			 }

		}
		System.out.println(ZIP.NowTime()+"change window success");
		
	}
	
	public static void closeWin(WebDriver driver,String defaultWindowID){
		Set<String> wins = driver.getWindowHandles();
		Object[] wa = wins.toArray();
		
		for (Object win : wa) {
			if (win.toString().equals(defaultWindowID)) {
				continue;
			} else {
				System.out.println(ZIP.NowTime()+"window close:"+win.toString());
				SwitchDriver.winIDClose(driver, win.toString());
				CommonJQ.clickKey(KeyEvent.VK_ENTER);
				DataUpload_baseTask.closeleavePage();
				SwitchDriver.switchToWinID(driver, defaultWindowID); 
			}
		}
		SwitchDriver.switchToWinID(driver, defaultWindowID); 
	}
	
	
	/**
	 * 点击要选择的文件夹
	 * 
	 * @param i
	 */
	public static void clickfile(WebDriver driver, String file) {

        CommonJQ.click(driver, "span[title=\"" + file+"\"]", false); 
	}
	
	public static void clickBtnOK(WebDriver driver) {

        CommonJQ.click(driver, SubmitBtn, false); 
	}
	
	
}
