package cloud_platform.page.systemManage;

import org.apache.commons.lang.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import cloud_public.page.LoadingPage;
import common.parameter.platform.ManageArea;
import common.parameter.platform.Operator;
import common.util.CommonJQ;
import common.util.LOG;


public class SystemManagePage {

	private static String addSManagerBtn = "#addSManagerBtn"; // 添加管理者按钮
	private static String batchAddUserButton = "#batchAddUserButton";// 批量添加按钮
	private static String delteSysmanager = "#delteSysmanager";// 删除管理者按钮
	private static String delteSysmanagerClass = ".in_block.pl5";// 删除管理者按钮
	private static String user_name = "#user_name";// 搜索用户名称
	private static String user_name2 = "#search_key";// 搜索用户名称
	private static String search = "#search";
	private static String totalnumber = ".total-item.mr5.pl10 em";// 页面数据总数
	private static String user_id = "#user_id";// 用户ID
	private static String form_username = "form #user_name";// 表单用户名称
	private static String SaveBTN = "a[onclick=\"submitForm()\"]";// 保存按钮
	private static String SaveBTN2 = "a[href$=\"submitForm()\"]>span";// 保存按钮
	private static String SaveBTN3 = "span[onclick$=\"submit()\"]";// 保存按钮
	private static String addOperatorBtn = "#addOperatorBtn";// 添加运营商按钮
	private static String left_nav = "#left_nav";// 系统管理左侧树
	private static String arealeftPanel = "#arealeftPanel"; // 选择管理地区 左侧区
	private static String operatorText = "#operatorText";//专家流程设置选择运营商
	private static String operator_dialog_cont = ".operator_dialog_cont";//运营商选择界面class
	private static String textprojectnameBTN = "#textprojectName~span"; //选择项目名称下拉箭头
	private static String mshowprojectName = "#mshowprojectName"; //选择项目名称下拉箭头
	private static String messageContentDiv = "div.messageContentDiv"; //专家流程设置成功提示信息div
	private static String confirmbtn = ".l-btn-left"; //专家流程设置成功确定按钮
	private static String DataList = "#data_list tbody";
	private static String Grid_Checker = ".x-grid-row-checker";

	
	/**
	 * 权限申请按钮是否存在
	 * @param driver
	 */
	public static int limitApplayButtonReturn(WebDriver driver) 
	{
		String  button = CommonJQ.excuteJStoString(driver, "return $('iframe').contents().find('a.btn_blue_1 span').length");
		return Integer.parseInt(button);
	}
	

	/**
	 * 平台消息也签是否存在  
	 * @param driver
	 */
	public static int  platformMessageReturn(WebDriver driver) 
	{
		int messageReturn = CommonJQ.length(driver, "#announcement");
		return messageReturn;
	}
	
	
	/**
	 * 我的权限是否存在  
	 * @param driver
	 */
	public static int personalMessageReturn(WebDriver driver) 
	{
		int messageReturn = CommonJQ.length(driver, "#myPopedom");
		return messageReturn;
	}

	/**
	 * 项目经理设置是否存在 
	 * @param driver
	 * @return
	 */
	public static int projectManagerReturn(WebDriver driver) 
	{
		int projectManagerReturn = CommonJQ.length(driver, "#setPManager");
		return projectManagerReturn;
	}
	
	/**
	 * 专家设置是否存在 
	 * @param driver
	 * @return
	 */
	public static int expertSetReturn(WebDriver driver) 
	{
		int expertSetReturn = CommonJQ.length(driver, "#setExpert");
		return expertSetReturn;
	}
	
	/**
	 * 维护员设置是否存在 
	 * @param driver
	 * @return
	 */
	public static int maintenanceReturn(WebDriver driver) 
	{
		int maintenanceReturn = CommonJQ.length(driver, "#setMaintain");
		return maintenanceReturn;
	}
	
	/**
	 * 统计员设置是否存在 
	 * @param driver
	 * @return
	 */
	public static int statisticReturn(WebDriver driver) 
	{
		int statisticReturn = CommonJQ.length(driver, "#statistician");
		return statisticReturn;
	}
	
	
	/**
	 * 专家流程设置是否存在   
	 * @param driver
	 * @return
	 */
	public static int expertProcessSetReturn(WebDriver driver) 
	{
		int expertProcessSetReturn = CommonJQ.length(driver, "#setReportAnalysis");
		return expertProcessSetReturn;
	}
	/**
	 * 添加管理者按钮是否存在
	 * 
	 * @param driver
	 * @return
	 */
	public static boolean isExistedAddManageBTN(WebDriver driver) {
		return CommonJQ.isExisted(driver, addSManagerBtn);
	}

	public static void clickAddManagerBTN(WebDriver driver) {
		CommonJQ.click(driver, addSManagerBtn, true, "点击添加管理者按钮");
		LoadingPage.Loading(driver);
	}
    public static  boolean existAccountManage(WebDriver driver,String accountype){
    	String css = "div:contains(" + accountype + ")";
    	return CommonJQ.isExisted(driver, left_nav, css, true);
    }
	public static void clickUserManagerItem(WebDriver driver, String setItem) {
		String css = "div:contains(" + setItem + ")";
		CommonJQ.clickNavigationByName(driver, left_nav, setItem);
//		CommonJQ.click(driver, left_nav, css, true, "点击" + setItem);
		LoadingPage.Loading(driver);
	}

	public static void searchUserByName(WebDriver driver, String username) {
		String css = user_name;
		if(CommonJQ.isExisted(driver, user_name2, true)){
			css = user_name2;
		}
		CommonJQ.value(driver, css, username.trim(), true, "搜索输入框");
		CommonJQ.click(driver, search, true, "搜索");
		LoadingPage.Loading(driver);
	}

	/**
	 * 
	 * 获取页面用户总数
	 * 
	 * @param driver
	 * @return
	 */
	public static int getUserNumber(WebDriver driver) {
		String number = CommonJQ.text(driver, totalnumber);
		if (!StringUtils.isBlank(number)) {
			return Integer.parseInt(number.trim());
		}
		return 0;
	}
	
	/**
	 * 
	* @Description: 返回界面第一个用户的用户账号
	* @param driver
	* @return 
	* @return String
	* @author zwx320041
	* @date 2017-2-16
	 */
	public static String getfirstUserAccout(WebDriver driver) {
		String acc = "";
		String script = "$('#data_list tbody').find('tr td:visible').eq(1).text();";
		acc = CommonJQ.excuteJStoString(driver, script);
		return acc;
	}

	/**
	 * 
	 * @Description: 添加用户ID
	 * @param driver
	 * @param userid
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-23
	 */
	public static void addUserID(WebDriver driver, String userid) {
		CommonJQ.value(driver, user_id, userid, "设置用户ID");
	}

	public static void addUserName(WebDriver driver, String username) {
		CommonJQ.value(driver, form_username, username, "设置用户名称");
	}

	/**
	 * 保存提交表单
	 * 
	 * @param driver
	 */
	public static void clickSaveBTN(WebDriver driver) {
		String css = SaveBTN;
		if(CommonJQ.isExisted(driver, SaveBTN2, true)){
			css = SaveBTN2;
		}
		else if(CommonJQ.isExisted(driver, SaveBTN3, true)){
			css = SaveBTN3;
		}
		CommonJQ.click(driver, css, true, "点击保存按钮");
		LoadingPage.Loading(driver);
	}

	public static boolean existSubmitBTN(WebDriver driver){
		String css = SaveBTN;
		if(CommonJQ.isExisted(driver, SaveBTN2, true)){
			css = SaveBTN2;
		}
		else if(CommonJQ.isExisted(driver, SaveBTN3, true)){
			css = SaveBTN3;
		}
		return CommonJQ.isExisted(driver, css, true);
	}
	/**
	 * @Description: 选择运营商
	 * @param driver
	 * @param region
	 *            区域
	 * @param agent
	 *            代表处
	 * @param operator
	 *            数组 支持多个运行商选择
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-23
	 */

	public static void selectOperators(WebDriver driver, Operator operator) {
		if (operator.getRegion()!=null) {
			Select select = new Select(driver.findElement(By.id("region")));
			select.selectByVisibleText(operator.getRegion());
		}
		if (operator.getAgent()!=null) {
			Select select1 = new Select(driver.findElement(By.id("agent")));
			if(select1.isMultiple()){
				select1.deselectAll();	
			}
			
			select1.selectByVisibleText(operator.getAgent());
		}
		
		if (operator.getOperator()!=null) {
			Select select2 = new Select(driver.findElement(By.id("operator")));
			if(select2.isMultiple()){
				select2.deselectAll();	
			}
			String[] opers = operator.getOperator();
			for (int i = 0; i < opers.length; i++) {
				select2.selectByVisibleText(opers[i]);
			}
		}
		

		CommonJQ.click(driver, "a#confirm span", true);
		LoadingPage.Loading(driver);
	}

	/**
	 * @Description: 选择业务专题
	 * @param driver
	 * @param subjects
	 *            *
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-23 业务专题为空或者全选 则全选
	 */

	public static void selectBusinessSubject(WebDriver driver, String[] subjects) {
		LOG.info_aw("设置业务专题");
		if (subjects == null || subjects[0].contains("全选")) {
			LOG.info_aw("全选所有业务专题");
			CommonJQ.click(driver, "#sel_role_all", true, "业务专题全选");

		} else {
			for (int i = 0; i < subjects.length; i++) {
				selectcheckBoxByName(driver, subjects[i]);
			}
		}
	}

	private static void selectcheckBoxByName(WebDriver driver, String name) {
		String script = "$('span').filter(function(){return $(this).text().trim()==\""
				+ name + "\"}).prev().click();";
		CommonJQ.excuteJS(driver, script);
	}

	public static void selectManageOperator(WebDriver driver,
			String[] manageoperators) {
		LOG.info_aw("设置负责运营商");
		if (manageoperators != null) {
			for (int i = 0; i < manageoperators.length; i++) {
				selectcheckBoxByName(driver, manageoperators[i]);
			}
		}
	}

	/**
	 * @Description: 选择制式复选框
	 * @param driver
	 * @param rats
	 *            *
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-23
	 */

	public static void selectRats(WebDriver driver, String[] rats) {
		LOG.info_aw("设置制式");
		if (rats == null) {
			LOG.info_aw("采用默认制式");
			return;
		} else {
			selectcheckBoxByName(driver, "LTE");// 取消LTE默认选项
			for (int i = 0; i < rats.length; i++) {
				selectcheckBoxByName(driver, rats[i]);
			}
		}
	}

	/**
	 * @Description: 点击添加运营商按钮
	 * @param driver
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-24
	 */

	public static void clickAddOperatorBTN(WebDriver driver) {
		CommonJQ.click(driver, addOperatorBtn, true);
	}

	/**
	 * @Description: 选择管理区域
	 * @param driver
	 * @param province
	 * @param city
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-24
	 */

	public static void selectManageArea(WebDriver driver,ManageArea managearea) {
		LOG.info_aw("选择管理区域");
		String script = "$('input').filter(function(){return $(this).val()=='>'}).click();";
		if (managearea.isSelectall()) {
			LOG.info_aw("全选管理区域");
			script = "$('input').filter(function(){return $(this).val()=='>>'}).click();";
			CommonJQ.excuteJS(driver, script);
			return;
		} 
		String selector = "a[title=\"" + managearea.getProvince() + "\"]>.button.ico_close";
		if (managearea.getCity()==null) {
			CommonJQ.click(driver, arealeftPanel, selector, true, "选择省份"+ managearea.getProvince());
		}else{
			CommonJQ.excuteJS(driver, "$('"+arealeftPanel+"').find('a[title=\"" + managearea.getProvince() + "\"').prev().click();");
			Pause.pause(300);
			selector = "a[title=\"" + managearea.getCity() + "\"]>.button.ico_docu";
			CommonJQ.click(driver, arealeftPanel, selector, true, "选择城市" + managearea.getCity());
		}
		CommonJQ.excuteJS(driver, script);
	}

	public static void clickAddOperatorBTN2(WebDriver driver) {
		CommonJQ.click(driver, operatorText , true);
	}
	
	public static void waitOperatorselectElement(WebDriver driver){
		CommonJQ.waitForElementVisble(driver, operator_dialog_cont);		
	}
	
	public static void selectProjectName(WebDriver driver,String projectname){
		LOG.info_aw("选择项目名称");
		CommonJQ.click(driver, textprojectnameBTN , true,"点击选择项目下拉箭头");
		CommonJQ.waitForElementVisble(driver, mshowprojectName);
		String descendantSelector = "li:contains("+projectname+")";
		Assert.assertTrue("选择项目无此项目",CommonJQ.length(driver, mshowprojectName, descendantSelector)>0);
		CommonJQ.click(driver, mshowprojectName, descendantSelector, true);
	}
	
	// 获取专家流程设置后的提示信息
	public static String getSetOKInfor(WebDriver driver){
		return CommonJQ.text(driver, messageContentDiv);
	}
	
	public static void selectUserByRowNumber(WebDriver driver,int index){
		
		CommonJQ.click(driver, DataList, Grid_Checker, true, index);		
	}
	
	public static void clickUserID(WebDriver driver,String userID){
		String css = "a:contains("+userID+")";
		CommonJQ.click(driver, DataList, css, true,0);
		LoadingPage.Loading(driver);
	}

	public static void clickDeleteAccoutBTN(WebDriver driver) {
		CommonJQ.click(driver, delteSysmanager,delteSysmanagerClass, true);
		LoadingPage.Loading(driver);		
	}
	
	public static void clickConfirmBTN(WebDriver driver){
		String css = "span:contains(确定)";
		CommonJQ.click(driver, css, true,"点击确定按钮");
		LoadingPage.Loading(driver);
	}


	public static void waitLoading(WebDriver driver, String setitemname) {
		if(StringUtils.containsIgnoreCase(setitemname, SystemManageConst.Set_ExpertProcess)){
			CommonJQ.waitForElementVisble(driver, confirmbtn);
		}else{
			CommonJQ.waitForElementVisble(driver, search);
		}
		
	}

}
