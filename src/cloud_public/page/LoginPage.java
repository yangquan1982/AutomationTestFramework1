package cloud_public.page;

import org.openqa.selenium.WebDriver;

import common.util.LOG;

import common.util.CommonJQ;
import common.util.ZIP;


public class LoginPage {


	// const string.
	public static final String USER = "#user_id";
	public static final String PASSWORD = "#password";
	public static final String LOGINBUTTON = "#loginButton";

	public static final String USERINFO = "#userinfo";

	public static final String ChangeVersion = "a[href=\"index.do?version=v1\"]";
	public static final String LoginID = "#login>a";

	
	public static void writeUsername(WebDriver driver, String text) {
		LOG.info_aw("登陆用户名："+text);
		System.out.println(ZIP.NowTime()+"登陆用户名："+text);
		CommonJQ.value(driver, USER, text,true,"首页：用户名输入框");		
	}
	public static void writePassword(WebDriver driver, String text) {
		LOG.info_aw("登陆密码："+text);
		System.out.println(ZIP.NowTime()+"登陆密码："+text);
		CommonJQ.value(driver, PASSWORD, text,false,"首页：密码输入框");		
	}	
	
}
