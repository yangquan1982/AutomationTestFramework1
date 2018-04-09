package cloud_public.page;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.PlatformConst;
import cloud_platform.page.SystemManagePage;
import common.util.CommonObject;
import common.util.LOG;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.SwitchDriver;
import common.util.ZIP;

public class HomePage { 
	
	/**
	 * 
	* @Description: 返回旧版本
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-22
	 */
	public static void gotoldVersion(WebDriver driver){
		if (CommonJQ.existLinkText(driver, PlatformConst.GotoldVersion)) {
			CommonJQ.clickLinkTextByName(driver, PlatformConst.GotoldVersion);
			LoadingPage.Loading(driver);
		}
		return;				
	}
	
	/**
	 * 
	* @Description: 进入工作台
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-22
	 */
	public static void goMyWorkBench(WebDriver driver){
		if (isInMyworkBench(driver)) {
			return;
		}
		if (CommonJQ.existLinkText(driver, PlatformConst.Workbench)) {
			CommonJQ.clickLinkTextByName(driver, PlatformConst.Workbench);
			LoadingPage.Loading(driver);	
		}
		return;
	}
	// 判断是否在个人工作台页面
	private static boolean isInMyworkBench(WebDriver driver){
		return CommonJQ.isExisted(driver, ".personalWorkbench.ng-scope",true);
	}
	
	public static void closeNewUserDialog(WebDriver driver){
		if (CommonJQ.isExisted(driver, "#applyDialog", true)) {
			CommonJQ.click(driver, ".close", true, "关闭新用户登录对话框");
			LoadingPage.Loading(driver);
		}
		
	}
	public static void goApplication(WebDriver driver){
		if (CommonJQ.existLinkText(driver, PlatformConst.APP)) {
			LOG.info_aw("进入应用界面");
			CommonJQ.clickLinkTextByName(driver, PlatformConst.APP);
			LoadingPage.Loading(driver);	
		}
		return;	
	}
	
	/**
	 * 
	* @Description: 进入数据上传
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-22
	 */
	public static void enterUnloadData(WebDriver driver){
		CommonJQ.clickLinkTextByName(driver, PlatformConst.UpLoadData);
		LoadingPage.Loading(driver);	
	}
	
	/**
	 * 
	* @Description: 进入我的任务
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-22
	 */
	public static void enterMyTask(WebDriver driver){
		if (CommonJQ.existNavigationByName(driver, PlatformConst.My_Task)) {
			CommonJQ.clickNavigationByName(driver, PlatformConst.My_Task);
			LoadingPage.Loading(driver);	
		}
		return;	
		
	}
	
	// 进入知识共享中心
	public static void enterKnowledgeSC(WebDriver driver){
		if (CommonJQ.existLinkText(driver, PlatformConst.KnowledgeSharingCenter)) {
			CommonJQ.clickLinkTextByName(driver, PlatformConst.KnowledgeSharingCenter);
			LoadingPage.Loading(driver);	
		}
		return;	
		
	}
	private static String  HomeNavigationDiv = ".userFnBtnGroup";
	// 进入系统管理
	public static boolean enterSysMgmt(WebDriver driver){
		if (CommonJQ.existNavigationByName(driver,HomeNavigationDiv,PlatformConst.SystemManagement)) {
			CommonJQ.clickNavigationByName(driver,HomeNavigationDiv, PlatformConst.SystemManagement);
			LoadingPage.Loading(driver);
			return true;
		}
		return false;			
	}
	
	//进入我的应用
	public static boolean enterMyApplication(WebDriver driver){
		HomePage.goMyWorkBench(driver);	
		if (CommonJQ.existNavigationByName(driver,HomeNavigationDiv,PlatformConst.MyApp)) {
			CommonJQ.clickNavigationByName(driver,HomeNavigationDiv, PlatformConst.MyApp);
			LoadingPage.Loading(driver);
			return true;
		}
		return false;			
	}
	
	// 进入项目管理
	public static boolean enterProjMgmt(WebDriver driver){
		if (CommonJQ.existNavigationByName(driver, PlatformConst.ProjectManagement)) {
			CommonJQ.clickNavigationByName(driver, PlatformConst.ProjectManagement);
			LoadingPage.Loading(driver);
			return true;
		}
		return false;			
	}
	
	// 进入我的首页
	public static boolean enterPersonnalHomePage(WebDriver driver){
		HomePage.goMyWorkBench(driver);
		if (CommonJQ.existNavigationByName(driver,HomeNavigationDiv, PlatformConst.Personal_Homepage)) {
			CommonJQ.clickNavigationByName(driver,HomeNavigationDiv, PlatformConst.Personal_Homepage);
			LoadingPage.Loading(driver);
			return true;
		}
		return false;	
	}
	
	//进入报表统计
	public static boolean enterReportStatistic(WebDriver driver){
		if (CommonJQ.existNavigationByName(driver, PlatformConst.ReportStatistic)) {
			CommonJQ.clickNavigationByName(driver, PlatformConst.ReportStatistic);
			LoadingPage.Loading(driver);
			return true;
		}
		return false;			
	}
	
	//返回登录首页
	public static void goHome(WebDriver driver){
		CommonJQ.clickTextByName(driver, PlatformConst.HomeIndex);
		LoadingPage.Loading(driver);
	}
	//获取登录用户信息名称
	public static String getUserInfor(WebDriver driver){
		return CommonJQ.text(driver, "#userinfo", "span", true);
	}
	
	private static String enshrineAppCSS ="em.i-fav16.w.zoom_out_in:visible";//收藏app样式
	private static String desenshrineAppCSS ="em.i-fav16.w.zoom_in_out:visible";//取消收藏样式
	private static String AppCSS ="div.inner.ppd20:visible";//APP 块样式
	/**
	 * 
	* @Description:  收藏App
	* @return void
	* @author zwx320041
	* @date 2017-1-23
	 */
	public static void enshrineApp(WebDriver driver){
		String script = "$('"+enshrineAppCSS+"').get(0).click();";
		CommonJQ.excuteJS(driver, script);
	}
	
	//取消收藏app
	public static void desEnshrineApp(WebDriver driver){
		String script = "$('"+desenshrineAppCSS+"').get(0).click();";
		CommonJQ.excuteJS(driver, script);
	}
	
	//获取app 个数
	public static int getAppNum(WebDriver driver){
		return CommonJQ.length(driver, AppCSS);
	}
	// 获取所有APP名称
	public static String getAppName(WebDriver driver,int index){
		String script = "$('"+AppCSS+"').eq("+String.valueOf(index)+").find('h3 a').text();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	public static String getAppName2(WebDriver driver,int index){
		String script = "$('"+AppCSS+"').eq("+String.valueOf(index)+").find('h3').text();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	//获取所有未收藏的app名称
	public static String getDesEnshrineAppName(WebDriver driver,int index){
		String script = "$('"+enshrineAppCSS+"').parents('"+AppCSS+"').eq("+String.valueOf(index)+").find('h3 a').text();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	
	//获取所有为未收藏app数量
	public static int getDesEnshrineAppNum(WebDriver driver){
		return CommonJQ.length(driver, enshrineAppCSS);
	}
	//获取所有收藏的app 个数
	public static int getEnshrineAppNum(WebDriver driver){
		return CommonJQ.length(driver, desenshrineAppCSS);
	}
	
	private static String ToDoTaskTable = "#todoTask";//待处理任务内容 div  ID
	private static String MyTaskTable = "#myTask";//待处理任务内容 div  ID
	
	public static void clickTodoTaskByID(WebDriver driver,String taskid){
		LOG.info_aw("点击待处理的任务，ID："+taskid);
		CommonJQ.clickLinkTextByName(driver, ToDoTaskTable,taskid);
	}
	
	public static void clickMyTaskByID(WebDriver driver,String taskid){
		LOG.info_aw("点击待处理的任务，ID："+taskid);
		CommonJQ.clickLinkTextByName(driver, MyTaskTable,taskid);
	}
	
	
	
}
