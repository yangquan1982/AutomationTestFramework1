package cloud_platform.task.platformcommon.projectmng;

import org.openqa.selenium.WebDriver;

import common.constant.constparameter.ConstUrl;
import common.util.LOG;

public class AwtaskManager_1 {
	public static String baseurl = ConstUrl.LOGINURL.substring(0, ConstUrl.LOGINURL.lastIndexOf("portal"));
	/**
	 * 进入项目管理
	 */
	public static void gotoprojectManager(WebDriver driver) {
		String url = baseurl + "prjmgr/projectManager.do";
		driver.navigate().to(url);
	}
	
	/**
	 * 进入个人工作空间
	 */
	public static void intoMyWorkerSpace(WebDriver driver) {
		LOG.info_aw("点击进入个人工作空间");
		String url = baseurl + "space/todo.do";
		driver.navigate().to(url);
    }
	
	/**
	 * 进入用户社区
	 */
	public static void gotousercommunity(WebDriver driver){
		LOG.info_aw("点击进入用户社区");
		String url = baseurl + "community/myHelp.do";
		LOG.info_aw("url:"+url);
		driver.navigate().to(url); 
	}
	

}
