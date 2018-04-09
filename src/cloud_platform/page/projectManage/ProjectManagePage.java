package cloud_platform.page.projectManage;

import org.apache.commons.lang.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import cloud_public.page.ButtonPage;
import cloud_public.page.LoadingPage;
import common.util.CommonObject;
import common.util.LOG;

import common.util.CommonJQ;

public class ProjectManagePage {

	/**
	 * 选择片区，代表处，运营商
	 * 
	 * @param driver
	 * @param area
	 * @param Agent
	 * @param operator
	 */
	public static void selectOperator(WebDriver driver, String area, String Agent, String operator) {
		LOG.info_aw("选择运营商信息");
		CommonJQ.click(driver, "input#operatorText", true);
		CommonJQ.waitForElementVisble(driver, ".operator_dialog_cont");
		Select select = new Select(driver.findElement(By.id("region")));
		select.selectByVisibleText(area);
		Pause.pause(1000);
		//
		Select select1 = new Select(driver.findElement(By.id("agent")));
		select1.selectByVisibleText(Agent);
		Pause.pause(1000);
		//
		Select select2 = new Select(driver.findElement(By.id("operator")));
		select2.selectByVisibleText(operator);
		Pause.pause(1000);
		//
		CommonJQ.click(driver, "a#confirm", "span", true);
	}

	/**
	 * 设置新项目名称
	 * 
	 * @param driver
	 * @param name
	 */
	public static void setNewProjectName(WebDriver driver, String name) {
		CommonJQ.value(driver, "#projectNameTag", name, true);
	}

	/**
	 * 设置业务领域
	 * 
	 * @param driver
	 * @param area
	 */
	public static void setBusiness_Area(WebDriver driver, String area) {
		LOG.info_aw("设置业务领域");
		CommonJQ.click(driver, "#textprojectType ~ span", true);
		Pause.pause(500);
		CommonJQ.waitForElementVisble(driver, "#mshowundefined");
		if (!CommonJQ.excuteJStoString(driver, "return $('ul#mshowundefined').css('display')").contains("block")) {
			Assert.fail("界面点击下拉框失败！");
		}
		CommonJQ.click(driver, "$(\"ul#mshowundefined\").find(\"li:contains('" + area + "')\")");
		Pause.pause(500);
		LoadingPage.Loading(driver);
	}

	/**
	 * 设置制式
	 * 
	 * @param driver
	 * @param RAT
	 */
	public static void setRAT(WebDriver driver, String RAT) {
		if (RAT == null || StringUtils.isBlank(RAT)) {
			return;
		}
		LOG.info_aw("设置制式");
		CommonJQ.click(driver, "$('ul#chooseRat').find('label:contains(\"" + RAT + "\")').find('input')");
	}

	/**
	 * 
	 * @Description: 设置凭证
	 * @param driver
	 * @param proof
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-28
	 */
	public static void setProof(WebDriver driver, String proof) {
		if (proof != null) {
			LOG.info_aw("设置凭证");
			driver.findElement(By.id("proof")).sendKeys(proof);
			LoadingPage.Loading(driver);
		}
	}

	public static String getProofInfor(WebDriver driver) {
		// proof_div
		return CommonJQ.text(driver, "#proof_div");
	}

	public static void batchImportUser(WebDriver driver, String path) {
		CommonJQ.waitForElementVisble(driver, ".r_pop_tit");
		driver.findElement(By.id("proof")).sendKeys(path);
		Pause.pause(1000);
	}

	/**
	 * 点击页面可见文本
	 * 
	 * @param driver
	 */
	public static void clickTextByName(WebDriver driver, String clickText) {
		LOG.info_aw("点击-" + clickText);
		String script = "$('span:contains(" + clickText + "):visible').not(':has(span)').get(0).click();";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);
	}

	/**
	 * 点击页面可见文本 父节点
	 * 
	 * @param driver
	 */
	public static void clickTextByName2(WebDriver driver, String clickText) {
		LOG.info_aw("点击-" + clickText);
		String script = "$('span:contains(" + clickText + "):visible').not(':has(span)').parent().click();";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);
	}

	public static void waitImportUser(WebDriver driver) {
		CommonJQ.waitForElementVisble(driver, "#msgDialog");

	}

	public static void clickLinkTextByName(WebDriver driver, String clickText) {
		LOG.info_aw("点击-" + clickText);
		String script = "$('a:contains(" + clickText + "):visible').not(':has(a)').get(0).click();";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);
	}

	public static boolean isExistTextBTN(WebDriver driver, String clickText) {
		String script = "return $('span:contains(" + clickText + "):visible').not(':has(span)').length;";
		return CommonJQ.excuteJStoInt(driver, script) > 0;
	}

	/**
	 * 
	 * @Description: 设置变更项目经理
	 * @param driver
	 * @param newuser
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-29
	 */
	public static void setChangeProjectManageUser(WebDriver driver, String newuser) {
		CommonJQ.value(driver, "#targetUserId", newuser);
	}

	public static String getfirstUserAccout(WebDriver driver) {
		String acc = "";
		String script = "$('#project_user_list').find('tr td:visible').eq(1).text();";
		acc = CommonJQ.excuteJStoString(driver, script);
		return acc;
	}

	public static boolean searchUser(WebDriver driver, String user) {
		CommonJQ.value(driver, "#todoSearchProjectUserId", user);
		CommonJQ.click(driver, "#search", true, "点击搜索按钮");
		LoadingPage.Loading(driver);
		Pause.pause(1000);
		boolean flag = false;
		for (int i = 0; i < 100; i++) {
			String searchresult = getfirstUserAccout(driver);
			if (searchresult.equalsIgnoreCase(user) || StringUtils.isBlank(searchresult)) {
				flag = true;
				if (StringUtils.isBlank(searchresult)) {
					flag = false;
				}
				break;
			} else {
				Pause.pause(200);
			}

		}
		return flag;
	}

	/***
	 * 添加内部用户或GSC接口人时，设置用户名称
	 */
	public static void setUserId(WebDriver driver, String userid) {
		CommonJQ.value(driver, "#user_id_1", userid, true);
	}

	/**
	 * 
	 * @Description: 设置用户邮箱
	 * @param driver
	 * @param email
	 * @return void
	 * @author zwx320041
	 * @date 2017-2-16
	 */
	public static void setUserEmail(WebDriver driver, String email) {
		CommonJQ.value(driver, "#user_mail", email, true);
	}

	/**
	 * 
	 * @Description: 设置用户名称
	 * @param driver
	 * @param username
	 * @return void
	 * @author zwx320041
	 * @date 2017-2-16
	 */
	public static void setUserName(WebDriver driver, String username) {
		if (CommonJQ.isExisted(driver, "#user_name", true)) {
			CommonJQ.value(driver, "#user_name", username, true);
		} else {
			CommonJQ.value(driver, "#user_name_1", username, true);
		}

	}

	/**
	 * 设置用户电话
	 * 
	 * @Description:
	 * @param driver
	 * @param userTel
	 * @return void
	 * @author zwx320041
	 * @date 2017-2-16
	 */
	public static void setUserTel(WebDriver driver, String userTel) {
		CommonJQ.value(driver, "#user_note", userTel, true);
	}

	/**
	 * 设置角色名称
	 * 
	 * @param driver
	 * @param roleName
	 */
	public static void setRoleName(WebDriver driver, String rolename) {
		CommonJQ.value(driver, "#roleName", rolename, true);
	}

	public static void changemanager(WebDriver driver, String userid, boolean commit) {
		CommonJQ.value(driver, "#targetUserId", userid, true, "设置新的项目经理ID");
		driver.findElement(By.id("targetUserName")).click();
		driver.findElement(By.id("targetUserId")).click();
		driver.findElement(By.id("targetUserName")).click();
		if (commit) {
			CommonJQ.waitForElementVisble(driver, "#submitBtnTwo");
			Assert.assertTrue("保存按钮不可用！", CommonJQ.isVisible(driver, "#submitBtnTwo"));
			ProjectManagePage.clickTextByName(driver, ButtonPage.Save);
		} else {
			ProjectManagePage.clickTextByName(driver, ButtonPage.cancel);
		}
	}

	// 获取项目管理员ID
	public static String getProjectManageID(WebDriver driver, String projectname) {
		String script = "$('a:contains(" + projectname
				+ ")').parentsUntil('tbody').filter('tr').find('td:eq(6)').text();";
		String manageid = CommonJQ.excuteJStoString(driver, script);
		return manageid;
	}

	// 返回项目列表
	public static void goProjectList(WebDriver driver) {
		LOG.info_aw("点击项目列表");
		String script = "$('.tit a').get(0).click();";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);

	}

	/**
	 * 添加外部用户时，设置用户邮箱
	 */
	public static void setExteriorMail(WebDriver driver, String mail) {
		CommonJQ.value(driver, "#user_mail", mail, true);
	}

	/**
	 * 添加外部用户时，设置用户名称
	 */
	public static void setExteriorUserName(WebDriver driver, String UserName) {
		CommonJQ.value(driver, "#user_name", UserName, true);
	}

	/**
	 * 添加外部用户时，设置用户手机号
	 */
	public static void setExteriorPhoneNum(WebDriver driver, String phoneNum) {
		CommonJQ.value(driver, "#user_note", phoneNum, true);
	}

	/**
	 * 设置分析权限
	 * 
	 * @param limitName
	 */
	public static void setAnalysisLimit(WebDriver driver, String limitName) {
		String script = "$('#roleSelBox').find('span').filter(function(){return $(this).text().trim()==\"" + limitName
				+ "\"}).prev().click()";
		CommonJQ.excuteJS(driver, script);
	}

	/**
	 * 设置数据上传权限
	 * 
	 * @param driver
	 * @param isHasUpload
	 */
	public static void setUploadLimit(WebDriver driver, boolean isHasUpload) {
		if (isHasUpload) {
			CommonJQ.click(driver, "$(\"dl input[name='dataUploadAuth']\").eq(0)"); // 选择为允许
		} else {
			CommonJQ.click(driver, "$(\"dl input[name='dataUploadAuth']\").eq(1)"); // 选择为不允许
		}
	}

	/**
	 * 查看当前角色管理界面数据上传权限设置状态
	 */
	public static void getStatusUploadLimt(WebDriver driver, int isHasUpload) {
		CommonJQ.excuteJStoString(driver, "$(\"dl input[name='dataUploadAuth']\").eq(1).is(\":checked\")");
	}

	/**
	 * 获取角色在角色管理任务列表界面的位置
	 * 
	 * @param driver
	 * @param roleName
	 * @return
	 */
	public static String getRoleSeat(WebDriver driver, String roleName) {
		return CommonJQ.excuteJStoString(driver, "return $(\"a[title='" + roleName + "']\").parents('tr').index()");
	}

	private static String ranProjectCode = "#ranProjectCode";

	public static void setProjectCode(WebDriver driver, String projectcode) {
		if (projectcode == null || StringUtils.isBlank(projectcode)) {
			return;
		}
		LOG.info_aw("设置项目编码");
		CommonJQ.value(driver, ranProjectCode, projectcode);
	}

	/**
	 * 
	 * @Description: 选择table数据点击复选框
	 * @param driver
	 * @param textName
	 * @return void
	 * @author zwx320041
	 * @date 2016-12-30
	 */
	public static void clickTableBoxByName(WebDriver driver, String textName) {
		String script = "$('a:contains(" + textName
				+ "):visible').not(':has(a)').parents('tr').find('.x-grid-row-checker').get(0).click();";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);
	}

	/**
	 * 
	 * @Description: 查看文本按钮是否可用
	 * @param driver
	 * @param buttoname
	 * @return
	 * @return boolean
	 * @author zwx320041
	 * @date 2016-12-30
	 */
	public static boolean isDisableBTNByName(WebDriver driver, String buttoname) {
		String script = "return $('span:contains(" + buttoname + "):visible').not(':has(span)').hasClass('cgray')";
		return CommonJQ.excuteJStoBoolean(driver, script);
	}

}
