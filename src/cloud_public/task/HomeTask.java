package cloud_public.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.PlatformConst;
import cloud_platform.page.workspace.WorkSpaceConst;
import cloud_public.page.HomePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.LoginPage;
import common.util.LOG;

import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.SwitchDriver;
import common.util.ZIP;

public class HomeTask {
	
	/**
	 * 
	* @Description: 进入数据上传
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-22
	 */
	public static void JumpToUpLoad(WebDriver driver){
		LOG.info_aw(ZIP.NowTime()+"进入数据上传界面====");		
		HomePage.goMyWorkBench(driver);
		HomePage.enterUnloadData(driver);		
		LoadingPage.Loading(driver);
	}
	
	/**
	 * 进入个人空间界面 projectManage
	 * @param driver
	 */
	public static void JumpToPersonalWorkspace(WebDriver driver){
		LOG.info_testcase("进入我的任务");
		HomePage.goMyWorkBench(driver);
		HomePage.enterMyTask(driver);
		LoadingPage.Loading(driver);
		Pause.pause(2000);//处理iframe 加载问题 等待
		LoadingPage.Loading(driver);
	}
	
	
	
	/**
	 * 进入用户社区
	 * @param driver 
	 */
	public static void JumpToUserCommunity(WebDriver driver){
		LOG.info_testcase("进入用户社区");
		HomePage.goMyWorkBench(driver);
		CommonJQ.clickLinkTextByName(driver, PlatformConst.UserCommunity);
		LoadingPage.Loading(driver);
	}
	

	
	/**
	 * 进入项目管理界面
	 * @param driver
	 */
	public static void JumpToprojectManage(WebDriver driver,boolean isjumpOK)
	{
		LOG.info_testcase("进入项目管理界面");
		HomePage.goMyWorkBench(driver);	
		if(isjumpOK){
			Assert.assertTrue("项目管理界面进入失败！", HomePage.enterProjMgmt(driver));
		}else{
			Assert.assertFalse("项目管理界面进入成功！", HomePage.enterProjMgmt(driver));
		}
	}
	
	/**
	 * 进入系统管理界面
	 * @param driver
	 */
	public static void JumpSystemManage(WebDriver driver) {
		LOG.info_testcase("进入系统管理界面");
		HomePage.goMyWorkBench(driver);	
		HomePage.enterSysMgmt(driver);			
	}
	
	 
	
	public static void JumpSystemManage(WebDriver driver,boolean isjumpOK) 
	{
		LOG.info_testcase("进入系统管理界面");
		HomePage.goMyWorkBench(driver);	
		LoadingPage.Loading(driver);
		if(isjumpOK){
			Assert.assertTrue("系统管理界面进入失败！", HomePage.enterSysMgmt(driver));
		}else{
			Assert.assertFalse("系统管理界面进入成功！", HomePage.enterSysMgmt(driver));
		}
	}
	
	/**
	 * 进入报表统计界面
	 */
	public static void JumpToReportStatistic(WebDriver driver)	{
		LOG.info_testcase("进入报表统计界面");
		HomePage.goMyWorkBench(driver);	
		HomePage.enterReportStatistic(driver);			
	}	
	
	public static void JumpToKnowledgeShareCenter(WebDriver driver,boolean isjumpOK) 
	{
		LOG.info_aw("进入知识共享中心");
		HomePage.goMyWorkBench(driver);	
		HomePage.enterKnowledgeSC(driver);	
		LoadingPage.Loading(driver);
		
	}
	
	/** 
	* @Description: 返回主页
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2016-12-24  
	*/ 
	
	public static void goBacktoHome(WebDriver driver){
		SwitchDriver.switchDriverToSEQ(driver);
		if(CommonJQ.isExisted(driver, "#footer", true)){
			return;
		}
		HomePage.goHome(driver);
//		boolean changeVersionFlage = CommonJQ.isExisted(driver, "#projectManage",true);
//		if(changeVersionFlage==false){
//			CommonJQ.click(driver, LoginPage.ChangeVersion, true);
//			LoadingPage.Loading(driver);
//		}
	}	
	
	/**
	 * 
	* @Description: 注销
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-13
	 */
	public static void logOut(WebDriver driver){
		CommonJQ.excuteJS(driver, "$('[onclick*=\"logout()\"]').click();");
		LoadingPage.Loading(driver);
		if (!CommonJQ.isExisted(driver, "a[href*=\"login.do\"]", true)) {
			Assert.fail("注销账户失败");
		}
	}

	/**
	 * 收藏APP功能验证
	 * 1 获取当前收藏APP信息
	 * 2收藏其余为收藏App信息
	 * 3检查收藏app 信息
	* @Description: 
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-24
	 */
	public static void enshrineApp(WebDriver driver) {
		// 收藏app 功能验证
		//获取已经收藏的App信息
		enterMyApplication(driver);
		ArrayList<String> myapps = getMyAppInfor2(driver);
		//收藏所有没有收藏的app信息
		enterApplication(driver);
		ArrayList<String> notmyapps = getNoEnshrineAppInfor(driver);
		enshrineAllApp(driver);
		enterMyApplication(driver);
		ArrayList<String> myappsnew = getMyAppInfor2(driver);		
		
		if (myappsnew.size()!=myapps.size()+notmyapps.size()) {
			String failapp = "";
			for (int i = 0; i < notmyapps.size(); i++) {
				int j=0;
				for (; j < myappsnew.size(); j++) {
					if (myappsnew.get(j).equals(notmyapps.get(i))) {
						break;
					}
				}
				if (j==myappsnew.size()) {
					failapp = failapp + notmyapps.get(i)+"\t";
				}
			}
			Assert.fail("以下App收藏失败：\n"+failapp);
		}
	}
	
	/**
	 *  
	* @Description: 取消所有App收藏功能验证
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	public static void desEnshrineApp(WebDriver driver) {
		// 收藏所有APP
		enterApplication(driver);
		enshrineAllApp(driver);
		//取消所有收藏的app
		enterApplication(driver);
		desEnshrineAllApp(driver);
		// 收藏的APP信息
		enterMyApplication(driver);
		ArrayList<String> myappsnew = getMyAppInfor2(driver);
		if(myappsnew.size()!=0){
			String infor = "";
			Iterator<String> iterator = myappsnew.iterator();
			while (iterator.hasNext()) {
				String app = (String) iterator.next();
				infor += app+"\t";
			}			
			Assert.fail("以下App取消收藏失败：\n"+infor);
		}
	}
	
	/**
	 * 进入我的应用
	* @Description: 
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	private static void enterMyApplication(WebDriver driver){
		HomePage.goMyWorkBench(driver);
		HomePage.enterMyApplication(driver);
	}
	/**
	 * 
	* @Description: 进入应用界面
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	private static void enterApplication(WebDriver driver){
		HomePage.goMyWorkBench(driver);
		HomePage.goApplication(driver);
	}
	
	
	
	// 获取我的应用下的所有APP名称
	private static ArrayList<String> getMyAppInfor2(WebDriver driver){
		
		int appnum = HomePage.getAppNum(driver);
		ArrayList<String> apps = new ArrayList<String>();
		for (int i = 0; i < appnum; i++) {
			String appname = HomePage.getAppName2(driver, i);
			apps.add(appname);
		}
		return apps;
	}
	// 获取应用下的所有APP名称
	private static ArrayList<String> getMyAppInfor(WebDriver driver){
		
		int appnum = HomePage.getAppNum(driver);
		ArrayList<String> apps = new ArrayList<String>();
		for (int i = 0; i < appnum; i++) {
			String appname = HomePage.getAppName(driver, i);
			apps.add(appname);
		}
		return apps;
	}
	
	/**
	 * 
	* @Description:获取应用下没有收藏的app信息，返回App名称 的List
	* @param driver
	* @return 
	* @return ArrayList<String>
	* @author zwx320041
	* @date 2017-2-10
	 */
	private static ArrayList<String> getNoEnshrineAppInfor(WebDriver driver){
		
		// 获取所有没有收藏的app 数量  app 信息
		int noEnshrineAppnum = HomePage.getDesEnshrineAppNum(driver);
		ArrayList<String> noEnshrineApps = new ArrayList<String>();
		for (int i = 0; i < noEnshrineAppnum; i++) {
			String noEnshrineApp = HomePage.getDesEnshrineAppName(driver, i);
			noEnshrineApps.add(noEnshrineApp);
		}
		return noEnshrineApps;
	}
	
	/**
	 * 
	* @Description: 收藏所有App
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	private static void enshrineAllApp(WebDriver driver){
		int notenshrineappnum = HomePage.getDesEnshrineAppNum(driver);
		for (int i = 0; i < notenshrineappnum; i++) {
			HomePage.enshrineApp(driver);
			Pause.pause(100);
		}
	}

	/**
	 * 
	* @Description: 取消收藏说有APP
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	private static void desEnshrineAllApp(WebDriver driver) {
		int notenshrineappnum = HomePage.getEnshrineAppNum(driver);
		for (int i = 0; i < notenshrineappnum; i++) {
			HomePage.desEnshrineApp(driver);
			Pause.pause(100);
		}
		
	}	
	
	/**
	 * 
	* @Description: 我的首页按照任务ID进入任务详情
	* @param driver
	* @param taskid 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	public static void enterMyTaskByID(WebDriver driver,String taskid){
		HomePage.enterPersonnalHomePage(driver);
		HomePage.clickMyTaskByID(driver, taskid);
		SwitchDriver.switchToNewWin(driver);		
	}
	
	/**
	 * 
	* @Description: 我的首页按照任务ID进入待处理的任务
	* @param driver
	* @param taskid 
	* @return void
	* @author zwx320041
	* @date 2017-2-10
	 */
	public static void enterTodoTaskByID(WebDriver driver,String taskid){
		HomePage.enterPersonnalHomePage(driver);
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.StayAnalysisTask);
		LoadingPage.Loading(driver);
		HomePage.clickTodoTaskByID(driver, taskid);
		SwitchDriver.switchToNewWin(driver);		
	}
	
	//待审批权限 待审批附件 沿用 WorkSpaceTask 方法

}
