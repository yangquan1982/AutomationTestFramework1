package cloud_platform.task.platformcommon.systemManage;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import cloud_platform.page.SystemManagePage;
import cloud_public.page.IndexPage;
import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;

public class SystemManageTask {

	/**
	 * 验证项目管理模块功能
	 * 
	 * @param driver
	 */
	public static void projectManageVerfication(WebDriver driver) {
		// 点击项目管理设置
		SystemManagePage.clickProjectManagerSet(driver);

		// 判断项目管理界面元素 添加按钮 、批量导入按钮、删除按钮、搜索按钮是否都存在
		boolean b1 = SystemManagePage.addProjectManagerButtonIsExist(driver);
		Assert.assertTrue("项目经理设置界面‘添加项目经理’按钮异常！", b1);

		boolean b2 = SystemManagePage.importUsersButtonIsExist(driver);
		Assert.assertTrue("项目经理设置界面‘批量导入’按钮异常！", b2);

		boolean b3 = SystemManagePage.deleteProjectManagerButtonIsExist(driver);
		Assert.assertTrue("项目经理设置界面‘删除项目经理’按钮异常！", b3);

		boolean b4 = SystemManagePage.searchButtonIsExist(driver, "搜索");
		Assert.assertTrue("项目经理设置界面‘搜索’按钮异常！", b4);

	}

	/**
	 * 验证专家设置界面功能
	 * 
	 * @param driver
	 */
	public static void expertSetVerfication(WebDriver driver) {
		// 点击专家设置
		SystemManagePage.clickExpertSet(driver);

		// 判断专家设置界面元素
		boolean b1 = SystemManagePage.addExpertIsExist(driver);
		Assert.assertTrue("专家设置界面‘添加专家’按钮异常！", b1);

		boolean b2 = SystemManagePage.deleteExpertIsExist(driver);
		Assert.assertTrue("专家设置界面‘删除专家’按钮异常！", b2);

		boolean b3 = SystemManagePage.searchButtonIsExist(driver, "搜索");
		Assert.assertTrue("专家设置界面‘搜索’按钮异常！", b3);
	}

	/**
	 * 维护员设置界面功能
	 * 
	 * @param driver
	 */
	public static void maintainVerfication(WebDriver driver) {
		// 点击维护员设置
		SystemManagePage.clickMaintainSet(driver);

		// 验证维护员界面元素
		boolean b1 = SystemManagePage.addMaintain(driver, "添加维护员按钮");
		Assert.assertTrue("维护员设置界面‘添加维护员’按钮异常！", b1);

		boolean b2 = SystemManagePage.deleteMaintain(driver, "删除维护员按钮");
		Assert.assertTrue("维护员设置界面‘删除维护员’按钮异常！", b2);

		boolean b3 = SystemManagePage.searchButtonIsExist(driver, "搜索");
		Assert.assertTrue("维护员设置界面‘搜索’按钮异常！", b3);

	}

	/**
	 * 统计员设置界面功能
	 * 
	 * @param driver
	 */
	public static void statisticianVerfication(WebDriver driver) {
		// 点击维护员设置
		SystemManagePage.clickStatisticianSet(driver);

		// 验证维护员界面元素
		boolean b1 = SystemManagePage.addMaintain(driver, "添加统计员按钮");
		Assert.assertTrue("统计员设置界面‘添加统计员’按钮异常！", b1);

		boolean b2 = SystemManagePage.deleteMaintain(driver, "删除统计员按钮");
		Assert.assertTrue("统计员设置界面‘删除统计员’按钮异常！", b2);

		boolean b3 = SystemManagePage.searchButtonIsExist(driver, "搜索");
		Assert.assertTrue("统计员设置界面‘搜索’按钮异常！", b3);

	}

	/**
	 * 搜索
	 * 
	 * @param driver
	 * @param name
	 */
	public static void Search(WebDriver driver, String userName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		LoadingPage.Loading(driver);
		int Num_Before = Integer.parseInt(CommonJQ.text(driver,
				"span[class=\"total-item mr5 pl10\"] em", "", true).trim());

		CommonJQ.value(driver, "#user_name", userName, true, "搜索输入框");
		CommonJQ.click(driver, "#search", true, "搜索");
		int Num_After = Integer.parseInt(CommonJQ.text(driver,
				"span[class=\"total-item mr5 pl10\"] em", "", true).trim());
		for (int i = 0; (i < 3)
				&& (Num_Before != 1 && (Num_After == Num_Before)); i++) {
			Pause.pause(1000);
			CommonJQ.value(driver, "#user_name", userName, true, "搜索输入框");
			CommonJQ.click(driver, "#search", true, "搜索");
			Num_After = Integer.parseInt(CommonJQ.text(driver,
					"span[class=\"total-item mr5 pl10\"] em", "", true).trim());
			if (Num_After != Num_Before) {
				break;
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);

	}

	/**
	 * 判断项目是否存在，并返回boolean
	 * 
	 * @param driver
	 * @param name
	 * @return
	 */
	public static boolean isExistExpert(WebDriver driver, String userName) {
		Search(driver, userName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		boolean flage = CommonJQ.isExisted(driver, "a[title=" + "\"" + userName
				+ "\"]", true);
		SwitchDriver.switchDriverToSEQ(driver);
		return flage;
	}

	/**
	 * 添加专家
	 * @param driver
	 * @param userName
	 * @param area
	 * @param Agent
	 * @param operator
	 */
	public static void addExpert(WebDriver driver,boolean DelFlage, String userName,
			String region, String agent, String[] rat,String[] operator) {
		boolean Pageflage = CommonJQ.isExisted(driver, "#setExpert", true);
		if(Pageflage==false){
//			IndexPage.netSysMangeMenuClick(driver);//老界面
			IndexPage.netSysMange(driver);//新界面
		}

		// 点击专家设置
		CommonJQ.click(driver, "#setExpert", true, "专家设置");
		LoadingPage.Loading(driver);

		boolean flage = isExistExpert(driver, userName);
		if(DelFlage&&flage){
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			String jselemnet = "$('a[title=\""+userName+"\"]').parents(\"td\").prev().find('div.x-grid-row-checker')";
			CommonJQ.click(driver, jselemnet);
			CommonJQ.click(driver, "#delteSysmanager a", true, "删除专家");
			CommonJQ.click(driver, "div[class=\"messager-button\"] a[class=\"l-btn\"]", true);
			LoadingPage.Loading(driver);
			SwitchDriver.switchDriverToSEQ(driver);
			flage=false;
		}
		if (flage == false) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");

			CommonJQ.click(driver, "#addSManagerBtn", true, "添加专家");
			CommonJQ.value(driver, "#user_id", userName, true, "账号输入框");
			CommonJQ.click(driver, "#sel_role_all", true, "全选");

			CommonJQ.click(driver, "input[name=\"networks\"]", true, 0, "制式:LTE");
			for(int i =0;i<rat.length;i++){
				if("LTE".equalsIgnoreCase(rat[i])){
					CommonJQ.click(driver, "input[name=\"networks\"]", true, 0, "制式:LTE");
				}else if ("GSM".equalsIgnoreCase(rat[i])){
					CommonJQ.click(driver, "input[name=\"networks\"]", true, 1, "制式:GSM");
				}else if ("UMTS".equalsIgnoreCase(rat[i])){
					CommonJQ.click(driver, "input[name=\"networks\"]", true, 2, "制式:UMTS");
				}else{
					Assert.fail("制式设置错误，目前制式支持LTE、GSM、UMTS");
				}
			}
			CommonJQ.click(driver, "#addOperatorBtn", true, "添加");

			//
			Select select = new Select(driver.findElement(By.id("region")));
			select.selectByVisibleText(region);
			//
			Select select1 = new Select(driver.findElement(By.id("agent")));
			select1.selectByVisibleText(agent);
			//
			Select select2 = new Select(driver.findElement(By.id("operator")));
			for (int i = 0; i < operator.length; i++) {
				select2.selectByVisibleText(operator[i]);
			}
			CommonJQ.click(driver, "a#confirm span", true);
			CommonJQ.click(driver, "a[onclick=\"submitForm()\"]", true);
			SwitchDriver.switchDriverToSEQ(driver);
			flage = isExistExpert(driver, userName);
			Assert.assertTrue("添加专家失败，专家名："+userName, flage);
		}

		
	}

}
