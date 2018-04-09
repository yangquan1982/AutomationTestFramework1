package cloud_platform.page.usercommunity;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;

public class UserCommunityPage {
	
	/**
	 * 
	* @Description: 选择分类
	* @param driver
	* @param category 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static void selectCategory(WebDriver driver,String category){
		CommonJQ.click(driver, "#textmoduleId~span", true);
		Pause.pause(500);
		CommonJQ.waitForElementVisble(driver, "#mshowmoduleId");
		String css = "li:contains("+category+")";
		while (CommonJQ.isVisible(driver, "#mshowmoduleId")) {
			CommonJQ.click(driver, "#mshowmoduleId", css, true,"选择分类："+category);	
			Pause.pause(500);
		}		
		LoadingPage.Loading(driver);
	}
	
	/**
	 * 
	* @Description: 设置标题
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static void setTitle(WebDriver driver,String text){
		CommonJQ.value(driver,"#title", text,"设置标题");
	}
	/**
	 * 
	* @Description: 设置内容
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public  static void setContent(WebDriver driver,String text){
		String script = "$('iframe').contents().find('body').append('"+text+"')";
		CommonJQ.excuteJS(driver, script);
	}
	
	/**
	 * 
	* @Description: 设置标签
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static void setTag(WebDriver driver,String text){
		CommonJQ.value(driver,"#tag", text,"设置标签");
	}
	
	/**
	 * 添加附件
	* @Description: 
	* @param driver
	* @param path 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static void addAttachment(WebDriver driver,String path){
		CommonJQ.ImportFile(driver, "attachFile", path);
		LoadingPage.Loading(driver);
	}
	
	public static void reAddAttachment(WebDriver driver,String path){
		CommonJQ.waitForElementVisble(driver, "#addAttachFile");
		CommonJQ.ImportFile(driver, "addAttachFile", path);
		LoadingPage.Loading(driver);
	}

	/**
	 * 
	* @Description: 搜索帖子
	* @param driver
	* @param searchKey 
	* @return void
	* @author zwx320041
	* @date 2017-1-5
	 */
	public  static void searchTopic(WebDriver driver,String searchKey){
		//helpTopic_key
		CommonJQ.value(driver,"#helpTopic_key", searchKey,"用户社区设置搜索关键字："+searchKey);
		CommonJQ.click(driver, "#helpTopic_key~span", true,"点击搜索按钮");
		LoadingPage.Loading(driver);
	}
	
	/**
	 * 
	* @Description: 判断当前页面存在帖子
	* @param driver
	* @param topic
	* @return 
	* @return boolean
	* @author zwx320041
	* @date 2017-1-5
	 */
	public static boolean isExistTopic(WebDriver driver,String topic){
		String script = "return $('a:contains("+topic+"):visible').not(':has(a)').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;
	}
	
	public static boolean isToptopic(WebDriver driver,String topic){
		String script = "return $('a:contains("+topic+"):visible').not(':has(a)').find('img[src$=\"zd.gif\"]').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;
	}
	
	public static boolean notExsistBTN(WebDriver driver,String topic){
		String script = "return $(span:contains("+topic+"):visible').not(':has(span)').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;
	}

	
	public static boolean existAttach(WebDriver driver) {
		return CommonJQ.isExisted(driver, "#annex", true);
	}
	
	public static String attachDeleteText(WebDriver driver) {
		return CommonJQ.text(driver, "#annex", "#helpFile",true);
	}

	public static void downLoadAttach(WebDriver driver) {
		CommonJQ.click(driver, "#attachFile", true);
	}
}
