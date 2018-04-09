package cloud_public.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.UploadDataPage;
import cloud_public.task.LoginTask;
import common.util.LOG;

import common.constant.constparameter.ConstUrl;
import common.constant.system.SystemConstant;
import common.util.CommonJQ;
import common.util.LanguageUtil;
import common.util.ParamUtil;

public class IndexPage {

	// const string.
 
	public static final String AlgorithmProject = "中国四川成都移动LTEwdw";//中国内蒙呼盟电信LTE自动化算法
	public static final String NetPerAnalyCemter = "td[id=\"netPerformanceAnalysisCenter\"] > a";
	public static final String NetPerAnalyCenter_ID = "netPerformanceAnalysisCenter";
	public static final String NetEvaAndAssCenter_ID = "netMonitorEnsureCenter";
	public static final String NetPrjMange_ID = "td[id=\"projectManage\"] > a";
	public static final String NetPlan_ID = "li[class=\"smallItem aniLeft bigger webjc\"]";
	
	private static final String NetPerAnalyCenterMenu_ID = "#netAnalysisCenter>a";
	private static final String NetEvaAndAssCenterMenu_ID = "#netSafeguardCenter>a";
	private static final String NetPrjMangeMenu_ID = "li[id=\"projectManager\"] > a";
	
	public static final String PrjSwi = "div[id=\"project_swi\"] > span[class=\"t2\"]";

	public static final String SearchText_ID = "#searchText";
	public static final String PrjList_ID = "#projectListul";
	public static final String BtnPrjTask = "li[ng-click=\"appDetailTagClick('task')\"]";

	
	public static final String ChinaTelecomLTEXizangNaqu = "中国西藏那曲电信LTE稳定性测试项目";
	public static final String StabilityTest = "中国安徽安庆电信LTE数据上传稳定性测试";
	//冒烟平台、数据上传专用项目
	public static final String ChinaTelecomLTEBeiJing = ConstUrl.getTargetValue("Smoke_Platform");
//	public static final String ChinaTelecomLTEBeiJing = "中国西藏林芝电信LTE1228_xz";
//	public static String ChinaTelecomLTEBeiJing = ParamUtil.ProjectName(SystemConstant.UpLoadProjectParam, "Traffic", 1, 1);
	//插件专用项目
	public static final String ChinaMobileLTEAssistantXiAn = ConstUrl.getTargetValue("ChinaMobileLTEAssistantXiAn");
	public static final String UMTS_Mobile = ConstUrl.getTargetValue("UMTS_Mobile");
	public static final String GSM_Mobile = ConstUrl.getTargetValue("GSM_Mobile");
	public static final String LTE_Mobile = ConstUrl.getTargetValue("LTE_Mobile");
	public static final String LTE_Telecom = ConstUrl.getTargetValue("LTE_Telecom");
	public static final String LTE_Unicom = ConstUrl.getTargetValue("LTE_Unicom");
	public static final String RAN_Mobile = ConstUrl.getTargetValue("RAN_Mobile");
	public static final String SingleSite_Mobile = ConstUrl.getTargetValue("LTE_Telecom");
	public static final String Drop_Call_Mobile = ConstUrl.getTargetValue("LTE_Drop_Call_Analysis");
	public static final String ChangeVersion = "#changeVersionImage";

	public static void netPerAnalyCenterMenuClick( WebDriver driver){
		if(CommonJQ.isExisted(driver, NetPerAnalyCenterMenu_ID)){
			CommonJQ.click(driver, NetPerAnalyCenterMenu_ID, true);
		}
	}
	
	public static void netEvaAndAssCenterMenuClick( WebDriver driver){
		if(CommonJQ.isExisted(driver, NetEvaAndAssCenterMenu_ID)){
			CommonJQ.click(driver, NetEvaAndAssCenterMenu_ID, true);
		}
	}
	
	public static void OpenNetPerfomanceAnalysisCenter( WebDriver driver){
		if(CommonJQ.isExisted(driver, "#"+NetPerAnalyCenter_ID)){
			CommonJQ.click(driver, "#"+NetPerAnalyCenter_ID,"a", true);
			LoadingPage.Loading(driver);
		}
	}
	/**
	 * 老界面项目管理跳转
	 * @param driver
	 */
	public static void netPrjMangeMenuClick( WebDriver driver){
		if (CommonJQ.isExisted(driver, NetPrjMange_ID)) {
			driver.findElement(By.id("projectManage")).findElement(By.tagName("a")).click();
		} else {
			CommonJQ.click(driver, NetPrjMangeMenu_ID, true);
		}
		LoadingPage.Loading(driver);
	}
	/**
	 * 新界面项目管理跳转
	 * @param driver
	 */
	public static void netPrjMange( WebDriver driver){
		
		boolean PrjMagFlage = CommonJQ.isExisted(driver, "div.userFnBtnGroup", true);
		if(PrjMagFlage){

		}else{
			String MyWork = LanguageUtil.translate("Personal Workbench");
			CommonJQ.click_ByText(driver, "a", MyWork, true, MyWork);
		}
		String PrjMag = LanguageUtil.translate("Project Management");
		CommonJQ.click_ByText(driver, "div", PrjMag, true, PrjMag);
		LoadingPage.Loading(driver);
	}
	/**
	 * 老界面系统管理跳转
	 * @param driver
	 */
	public static void netSysMangeMenuClick( WebDriver driver){
		if (CommonJQ.isExisted(driver, "#sysManage")) {
			try {
				driver.findElement(By.id("sysManage"))
						.findElement(By.tagName("a")).click();
			} catch (Exception e) {
			}
		} else {
			CommonJQ.click(driver, "#settingManager a", true, "系统管理");
		}
		LoadingPage.Loading(driver);
	}
	/**
	 * 新界面系统管理跳转
	 * @param driver
	 */
	public static void netSysMange( WebDriver driver){
		
		boolean SysMagFlage = CommonJQ.isExisted(driver, "div.userFnBtnGroup", true);
		if(SysMagFlage){

		}else{
			String MyWork = LanguageUtil.translate("Personal Workbench");
			CommonJQ.click_ByText(driver, "a", MyWork, true, MyWork);
		}
		String SysMag = LanguageUtil.translate("System Mgmt");
		CommonJQ.click_ByText(driver, "div", SysMag, true, SysMag);
		LoadingPage.Loading(driver);
	}
	/**
	 * 老界面数据上传跳转
	 * @param driver
	 */
	public static void netUpdataMenuClick( WebDriver driver){
		
		boolean UpBtnFlage = CommonJQ.isExisted(driver, "span.uploadDataBtnText", true);
		if(UpBtnFlage){

		}else{
			LoginTask.changeVersion(driver);
			if (CommonJQ.isExisted(driver, "#dataUpload", true)) {
				driver.findElement(By.id("dataUpload"))
						.findElement(By.tagName("a")).click();
			} else {
				if (CommonJQ.isExisted(driver, UploadDataPage.BtnUpLoad)) {
					CommonJQ.click(driver, UploadDataPage.BtnUpLoad, true,"数据上传");
				}
			}
		}
		LoadingPage.Loading(driver);
	}
	/**
	 * 新界面数据上传跳转
	 * @param driver
	 */
	public static void netUpdataMange( WebDriver driver){
		
		boolean UpBtnFlage = CommonJQ.isExisted(driver, "span.uploadDataBtnText", true);
		if(UpBtnFlage){

		}else{
			boolean UpMenuFlage = CommonJQ.isExisted(driver, UploadDataPage.BtnUpLoad, true);
			if(UpMenuFlage){

			}else{
				String MyWork = LanguageUtil.translate("Personal Workbench");
				CommonJQ.click_ByText(driver, "a", MyWork, true, MyWork);	
			}
			CommonJQ.click(driver, UploadDataPage.BtnUpLoad, true,"数据上传");
		}
		LoadingPage.Loading(driver);
	}
	/**
	 * 新界面应用界面跳转
	 * @param driver
	 */
	public static void netAppMange( WebDriver driver){
		
		String NetWorkInsight = LanguageUtil.translate("NetWork Insight");
		String Application = LanguageUtil.translate("Application");
		
		boolean NetWorkInsightFlage = CommonJQ.isExisted_ByText(driver, "li", NetWorkInsight, true);
		boolean ApplicationFlage = CommonJQ.isExisted_ByText(driver, "a", Application, true);
		if(NetWorkInsightFlage&&ApplicationFlage){
			
		}else{		
			ApplicationFlage = CommonJQ.isExisted_ByText(driver, "a", Application, true);
			if(ApplicationFlage){

			}else{
				String MyWork = LanguageUtil.translate("Personal Workbench");
				CommonJQ.click_ByText(driver, "a", MyWork, true, MyWork);	
			}
			CommonJQ.click_ByText(driver, "a", Application, true, Application);
		}
		LoadingPage.Loading(driver);
		NetWorkInsightFlage = CommonJQ.isExisted_ByText(driver, "li", NetWorkInsight, true);
		ApplicationFlage = CommonJQ.isExisted_ByText(driver, "a", Application, true);
		if((NetWorkInsightFlage&&ApplicationFlage)==false){
			Assert.fail("应用界面跳转异常，未找到应用主界面菜单："+NetWorkInsight);
		}
	}	
	
	public static void NetPlanClick( WebDriver driver){
		if(CommonJQ.isExisted(driver, NetPlan_ID)){
			CommonJQ.click(driver, NetPlan_ID, true);
		}
	}
	
	// 切换到项目任务页签
		public static void PrjTaskClick(WebDriver driver) {
			LOG.info_aw("进入射频插件界面点击任务任务页签");
			CommonJQ.click(driver, BtnPrjTask, true);
		}
}
