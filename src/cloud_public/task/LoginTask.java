package cloud_public.task;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import cloud_public.page.HomePage;
import cloud_public.page.LoadingPage;
import cloud_public.page.LoginPage;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.ZIP;

public class LoginTask {

	/**
	 * 普通外部用户登陆
	 * @param driver
	 */
	public static void login(WebDriver driver) {
		CommonWD.openHomePage(driver);
		String User = LoginTask.getUser(driver);		
		LoginPage.writeUsername(driver, User);
		LoginPage.writePassword(driver, ConstUrl.LOGINPWD);

		CommonJQ.click(driver, LoginPage.LOGINBUTTON, true);
		LoadingPage.Loading(driver);
		if(!checkLoginSuccess(driver)){
			Assert.fail("用户名："+User+"密码："+ConstUrl.LOGINPWD+",登陆失败！");
		}
		//切换中英文
		LoginTask.changeLanguage(driver);
		System.out.println(ZIP.NowTime()+"Login sucess");
	}
	/**
	 * 切换成旧版本
	 * @param driver
	 */
	public static void changeVersion(WebDriver driver) 
	{
		boolean changeVersionFlage = CommonJQ.isExisted(driver, LoginPage.ChangeVersion,true);
		if(changeVersionFlage){
			CommonJQ.click(driver, LoginPage.ChangeVersion, true);
			LoadingPage.Loading(driver);
		}
	}
	/**
	 * 专家登陆
	 * @param driver
	 */
	public static void loginExpert(WebDriver driver) {
		CommonWD.openHomePage(driver);
		String User = LoginTask.getExpertUser(driver);		
		LoginPage.writeUsername(driver, User);
		LoginPage.writePassword(driver, "Sk@12345678");

		CommonJQ.click(driver, LoginPage.LOGINBUTTON, true);
		LoadingPage.Loading(driver);
		if(!checkLoginSuccess(driver)){
			Assert.fail("用户名："+User+"密码："+"Sk@12345678"+",登陆失败！");
		}
		//切换中英文
		LoginTask.changeLanguage(driver);
		System.out.println(ZIP.NowTime()+"Login sucess");
	}
	
	
	/**
	 * 管理员登陆
	 * @param driver
	 */
	public static void loginSys(WebDriver driver) {
		CommonWD.openHomePage(driver);	
		
		LoginPage.writeUsername(driver, ConstUrl.LOGINSysUSER);
		LoginPage.writePassword(driver, ConstUrl.LOGINSysPWD);
		
		CommonJQ.click(driver, LoginPage.LOGINBUTTON, true);
		LoadingPage.Loading(driver);
		if(!checkLoginSuccess(driver)){
			Assert.fail("用户名："+ConstUrl.LOGINSysUSER+"密码："+ConstUrl.LOGINSysPWD+",登陆失败！");
		}
		//切换中英文
		LoginTask.changeLanguage(driver);
		System.out.println(ZIP.NowTime()+"Login sucess");
	}
	
	/**
	 * 项目经理登陆
	 * @param driver
	 */
	public static void loginPro(WebDriver driver) 
	{
		CommonWD.openHomePage(driver);	
		LoginPage.writeUsername(driver, ConstUrl.LOGINProUSER);
		LoginPage.writePassword(driver, ConstUrl.LOGINProPWD);
		
		CommonJQ.click(driver, LoginPage.LOGINBUTTON, true);
		LoadingPage.Loading(driver);
		if(!checkLoginSuccess(driver))
		{
			Assert.fail("用户名："+ConstUrl.LOGINProUSER+"密码："+ConstUrl.LOGINProPWD+",登陆失败！");
		}
		//切换中英文
		LoginTask.changeLanguage(driver);
		System.out.println(ZIP.NowTime()+"Login sucess");
	}
	
	
	
	/**
	 * 普通外部用户
	 * @param driver
	 * @return
	 */
	public static String getUser(WebDriver driver) {
		String User =ConstUrl.LOGINUSER;
		switch (ZIP.IP()) {
		// C20插件执行机
	    case "10.119.34.239" :User ="test30@163.com" ;break;
	    case "10.119.32.222" :User ="test31@163.com" ;break;
	    case "10.119.34.245" :User ="test32@163.com" ;break;
	    case "10.119.26.159" :User ="test33@163.com" ;break;
	    case "10.119.26.120" :User ="test34@163.com" ;break;
	    case "10.119.27.35"  :User ="test35@163.com" ;break;
	    case "10.119.37.135" :User ="test36@163.com" ;break;
	    case "10.119.33.223" :User ="test37@163.com" ;break;
	    case "10.119.37.192" :User ="test38@163.com" ;break;
	    case "10.119.35.140" :User ="test39@163.com" ;break;
	    case "10.119.36.160" :User ="test40@163.com" ;break;
	    case "10.119.25.7"   :User ="test41@163.com" ;break;
	    case "10.119.36.230" :User ="test42@163.com" ;break;
	    case "10.119.34.154" :User ="test43@163.com" ;break;
	    case "10.119.26.235" :User ="test44@163.com" ;break;
		// C30插件执行机
	    case "10.119.32.192" :User ="test51@163.com" ;break;
	    case "10.119.26.254" :User ="test52@163.com" ;break;
	    case "10.119.33.37"  :User ="test53@163.com" ;break;
	    case "10.119.36.170" :User ="test54@163.com" ;break;
	    case "10.119.27.132" :User ="test55@163.com" ;break;
	    case "10.180.251.230":User ="test56@163.com" ;break;
	    case "10.180.245.243":User ="test57@163.com" ;break;
	    case "10.180.244.231":User ="test58@163.com" ;break;
	    case "10.180.255.239":User ="test59@163.com" ;break;
	    case "10.180.249.38" :User ="test60@163.com" ;break;
	    case "10.180.247.220":User ="test61@163.com" ;break;
	    case "10.180.243.36" :User ="test62@163.com" ;break;
	    case "10.180.252.168":User ="test63@163.com" ;break;
	    case "10.180.255.247":User ="test64@163.com" ;break;
	    case "10.180.248.244":User ="test65@163.com" ;break;
	    //数据上传执行机
	    case "10.146.161.128":User ="test81@163.com" ;break;
	    case "10.180.75.26"  :User ="test82@163.com" ;break;
	    case "10.180.82.159" :User ="test83@163.com" ;break;
	    case "10.180.77.42"  :User ="test84@163.com" ;break;
	    case "10.180.81.112" :User ="test85@163.com" ;break;
	    case "10.180.78.112" :User ="test86@163.com" ;break;
	    case "10.180.87.215" :User ="test87@163.com" ;break;
	    case "10.180.79.139" :User ="test88@163.com" ;break;
	    case "10.180.154.217":User ="test89@163.com" ;break;
	    case "10.180.163.218":User ="test90@163.com" ;break;
	    case "10.180.159.193":User ="test91@163.com" ;break;
	    case "10.180.160.226":User ="test92@163.com" ;break;
	    //数据上传并发才测试账号
	    case "10.119.33.49" :User ="test51@126.com" ;break;
	    case "10.119.25.171" :User ="test52@126.com" ;break;
	    case "10.119.26.103"  :User ="test53@126.com" ;break;
	    case "10.119.34.194" :User ="test54@126.com" ;break;
	    case "10.119.25.28" :User ="test55@126.com" ;break;
	    case "10.119.37.92":User ="test56@126.com" ;break;
	    case "10.119.27.102":User ="test57@126.com" ;break;
	    case "10.119.32.247":User ="test58@126.com" ;break;
	    case "10.119.26.251":User ="test59@126.com" ;break;
	    case "10.119.32.209" :User ="test60@126.com" ;break;
	    case "10.119.26.225":User ="test61@126.com" ;break;
	    case "10.119.36.225" :User ="test62@126.com" ;break;
	    case "10.119.27.176":User ="test63@126.com" ;break;
	    case "10.119.25.63":User ="test64@126.com" ;break;
	    case "10.119.33.76":User ="test65@126.com" ;break;
	    //稳定性测试
	    case "10.180.165.79" :User ="test70@126.com" ;break;
	    //case "10.180.99.16" :User ="test71@126.com" ;break;
	    case "10.180.154.128" :User ="test72@126.com" ;break;
	    case "10.180.163.216" :User ="test73@126.com" ;break;
	    case "10.180.156.186" :User ="test74@126.com" ;break;
	    //算法测试
	    case "10.119.32.237"  :User ="test75@126.com" ;break;
	   
	    default:User =ConstUrl.LOGINUSER;	
		
		}
		return User;
	}
	/**
	 * 专家用户
	 * @param driver
	 * @return
	 */
	public static String getExpertUser(WebDriver driver) {
		String User =ConstUrl.LOGINUSER;
		switch (ZIP.IP()) {
		// 插件执行机
	    case "10.119.32.192" :User ="zwx319892" ;break;
	    case "10.119.26.254" :User ="zwx319890" ;break;
	    case "10.119.33.37"  :User ="wwx319893" ;break;
	    case "10.119.36.170" :User ="zwx319894" ;break;
	    case "10.119.27.132" :User ="swx319888" ;break;
	    case "10.180.251.230":User ="wwx319891" ;break;
	    case "10.180.245.243":User ="qwx319895" ;break;
	    case "10.180.244.231":User ="lwx319889" ;break;
	    case "10.180.255.239":User ="qwx319887" ;break;
	    case "10.180.249.38" :User ="wwx292650" ;break;
	    case "10.180.247.220":User ="zwx319867" ;break;
	    case "10.180.243.36" :User ="zwx292651" ;break;
	    case "10.180.252.168":User ="wwx292652" ;break;
	    case "10.180.255.247":User ="zwx292649" ;break;
	    case "10.180.248.244":User ="lwx290552" ;break;
	
	    default:User ="zwx290538";	
		}
		return User;
	}
	public static void login_account_pwd(WebDriver driver, String account, String pwd) {
		CommonWD.openHomePage(driver);		
	    LoginPage.writeUsername(driver, account);	
		
		LoginPage.writePassword(driver, pwd);
		CommonJQ.click(driver, LoginPage.LOGINBUTTON, true);
		LoadingPage.Loading(driver);
		if(!checkLoginSuccess(driver)){
			Assert.fail("用户名："+account+"密码："+pwd+",登陆失败！");
		}
		//切换中英文
		LoginTask.changeLanguage(driver);
		System.out.println(ZIP.NowTime()+"Login sucess");
	}
	
	public static void loginOutNotIndex(WebDriver driver) {
		
		CommonJQ.click(driver, LoginPage.USERINFO, true);
		System.out.println(ZIP.NowTime()+"Login out");
		((JavascriptExecutor) driver).executeScript("$('#userInfoShow').find('li[onclick=\"logout()\"]').click()");
	    Pause.pause(2000);
	}
	
	private static boolean checkLoginSuccess(WebDriver driver){
		
		if(CommonJQ.isVisible(driver, "#idVerify")){
			return false;
		}else{
			HomePage.closeNewUserDialog(driver);
		}
		return true;
		
	}
	/**
	 *  Cloud切换中英文
	 * @param driver
	 */
	public static void changeLanguage(WebDriver driver) {
		if("CN".equalsIgnoreCase(ConstUrl.language)){
			if(CommonJQ.isExisted_ByText(driver, "a", "English", true)){
				
			}else{
				CommonJQ.click_ByText(driver, "a", "中文", true, "中文");
				boolean Flage = CommonJQ.isExisted_ByText(driver, "a", "English", true);
				if(Flage==false){
					Assert.fail("Cloud 系统切换到中文失败");
				}
			}
			
		}else{
			if(CommonJQ.isExisted_ByText(driver, "a", "中文", true)){
				
			}else{
				CommonJQ.click_ByText(driver, "a", "English", true, "English");
				boolean Flage =CommonJQ.isExisted_ByText(driver, "a", "中文", true);
				if(Flage==false){
					Assert.fail("Cloud 系统切换到英文失败");
				}
			}
		}
	}
	
}
