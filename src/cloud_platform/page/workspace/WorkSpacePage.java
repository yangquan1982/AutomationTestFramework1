package cloud_platform.page.workspace;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;

public class WorkSpacePage {
	
	private static String approveUserId = "#approveUserId";// 项目经理账号
	private static String applyReason1 = "#applyReason1";// 加入项目申请原因1
	private static String applyReason2 = "#applyReason2";  //项目经理申请原因
	
	/**
	 * 
	* @Description: 设置加入项目账号
	* @param driver
	* @param account 
	* @return void
	* @author zwx320041
	* @date 2017-1-11
	 */
	public static void setAddProjectManageAccount(WebDriver driver, String account){
		CommonJQ.ImportFile(driver, approveUserId.replaceAll("#", ""), account);
		LoadingPage.Loading(driver);
	}
	
	/**
	 * 
	* @Description: 选择项目
	* @param driver
	* @param projectname 
	* @return void
	* @author zwx320041
	* @date 2017-1-11
	 */
	public static void selectProject(WebDriver driver, String projectname){
		CommonJQ.click(driver, "#projectId~span", true);
		Pause.pause(500);
		CommonJQ.waitForElementVisble(driver, "#mshowprojectId");
		String css = "li:contains("+projectname+")";
		CommonJQ.click(driver, "#mshowprojectId", css, true,"选择项目："+projectname);
		Pause.pause(1000);
		LoadingPage.Loading(driver);
	}
	
	/**
	 * 
	* @Description: 设置申请原因
	* @param driver
	* @param applyreason 
	* @return void
	* @author zwx320041
	* @date 2017-1-11
	 */
	public static void setApplyReason(WebDriver driver, String applyreason){
		if (CommonJQ.isExisted(driver, applyReason1, true)) {
			CommonJQ.value(driver, applyReason1, applyreason, "设置申请原因");
		}else{
			CommonJQ.value(driver, applyReason2, applyreason, "设置申请原因");
		}
	}
	
	/*
	 * 获取告警信息
	 */
	public static String getWarningTips(WebDriver driver){
		String tips = null;
		if(CommonJQ.isExisted(driver, "#warningTips",true)){
			tips = CommonJQ.text(driver, "#warningTips");
		}
		return tips;
	}
	
	/**
	 * 
	* @Description: 等到申请对话框加载
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-11
	 */
	
	public static void waitapplyDialog(WebDriver driver){
		CommonJQ.waitForElementVisble(driver, "#applyDialog");
	}

	private static String myapplyID = "#content_height";
	private static int getLineIndex(WebDriver driver,String selector,String linename){
		String script = "return $('"+selector+"').find('ul:contains("+linename.trim()+"):visible').eq(0).index()";
		return CommonJQ.excuteJStoInt(driver, script);
	}
	
	private static int getRowIndex(WebDriver driver,String selector,String rowname){
		String script = "return $('"+selector+"').find('li:contains("+rowname.trim()+"):visible').eq(0).index()";
		return CommonJQ.excuteJStoInt(driver, script);
	}	
	
	//$('iframe').contents().find('#content_height').find('ul:visible').eq(1).find('li').eq(2).text()
	
	public static String getMyApplyRowtext(WebDriver driver,String linename, String rowname){
		int linenum = getLineIndex(driver, myapplyID,linename);
		int rownum = getRowIndex(driver, myapplyID, rowname);
		if (linenum==-1) {
			Assert.fail("当前界面不存在列："+linename);
		}
		if (linenum==-1) {
			Assert.fail("当前界面不存在行包含文本："+rowname);
		}		
		String js = "return $('#content_height').find('ul:visible').eq("+String.valueOf(linenum)+").find('li').eq("+String.valueOf(rownum)+").text()";
		return CommonJQ.excuteJStoString(driver, js);
	}
	
	public static String getMyApplyRowtext2(WebDriver driver,String linename, String rowname){
		int linenum = getLineIndex(driver, myapplyID,linename);
		int rownum = getRowIndex(driver, myapplyID, rowname);
		if (linenum==-1) {
			Assert.fail("当前界面不存在列："+linename);
		}
		if (linenum==-1) {
			Assert.fail("当前界面不存在行包含文本："+rowname);
		}		
		String js = "return $('#content_height').find('ul:visible').eq("+String.valueOf(linenum)+").find('li').eq("+String.valueOf(rownum)+").attr('title');";
		return CommonJQ.excuteJStoString(driver, js);
	}
	
	/**
	 * 
	* @Description: 按照申请类型和申请账号点击审批任务
	* @param driver
	* @param applytype
	* @param account 
	* @return void
	* @author zwx320041
	* @date 2017-1-12
	 */
	public static void clickApproveApplyTask(WebDriver driver,String applytype, String account){
		String script2 = "$('#todo_tab').find('ul:contains("+applytype.trim()+"):contains("+account.trim()+"):visible').find('a').get(0).click();";
		if(exsistApproveApplyTask(driver, applytype, account)==false){
			Assert.fail("待审批权限列表无 账号为："+account+",申请类型为"+applytype+"-的申请任务！");
		}else{
			CommonJQ.excuteJS(driver, script2);
			LoadingPage.Loading(driver);
		}
	}
	
	public static boolean exsistApproveApplyTask(WebDriver driver,String applytype, String account){
		String script = "return $('#todo_tab').find('ul:contains("+applytype.trim()+"):contains("+account.trim()+"):visible').find('a').length;";
		return CommonJQ.excuteJStoInt(driver, script)>0;
	}
	
	/**
	 * 
	* @Description: 设置审批意见
	* @param driver
	* @param content 
	* @return void
	* @author zwx320041
	* @date 2017-1-12
	 */
	public static void setApproveContent(WebDriver driver,String content){
		CommonJQ.value(driver, "#approveContent", content, true, "设置审批意见");
	}
	
	/**
	 * 
	* @Description: 选择审批结果
	* @param driver
	* @param approveresult 
	* @return void
	* @author zwx320041
	* @date 2017-1-12
	 */
	public static void selectApproveResult(WebDriver driver,String approveresult){
		if (approveresult.equalsIgnoreCase(WorkSpaceConst.ApproveResult1)) {
			CommonJQ.click(driver, "#approveStatus1", true,"选择同意申请");
		} else if(approveresult.equalsIgnoreCase(WorkSpaceConst.ApproveResult2)){
			CommonJQ.click(driver, "#approveStatus2", true,"选择拒绝申请");
		}		
	}
	
	/**
	 * 
	* @Description: 点击附件进行下载
	* @param driver 
	* @return void
	* @author zwx320041
	* @date 2017-1-12
	 */
	public static void clickDownLoadAttach(WebDriver driver){
		CommonJQ.click(driver, "#attachFile", true, "点击附件下载");
	}
	 
}
