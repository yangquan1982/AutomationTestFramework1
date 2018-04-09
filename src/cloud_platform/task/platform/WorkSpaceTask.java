package cloud_platform.task.platform;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.workspace.WorkSpaceConst;
import cloud_platform.page.workspace.WorkSpacePage;
import cloud_public.page.ButtonPage;
import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;

public class WorkSpaceTask {
	
	
	
	/**
	 * 
	* @Description: 申请加入项目
	* @param driver
	* @param account
	* @param projectname
	* @param applyreason 
	* @return void
	* @author zwx320041
	* @date 2017-1-11
	 */
	public static void applyJoinProject(WebDriver driver, String account, String projectname, String applyreason,boolean isOK){
		LOG.info_aw("申请加入项目");
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.ProjectManage);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver);
		CommonJQ.clickTextByName(driver, WorkSpaceConst.PermissionApplication);
		WorkSpacePage.waitapplyDialog(driver);
		CommonJQ.clickTextByName(driver, WorkSpaceConst.ApplicationJoinInProject);
		WorkSpacePage.setAddProjectManageAccount(driver, account);
		WorkSpacePage.selectProject(driver, projectname);
		WorkSpacePage.setApplyReason(driver, applyreason);
		CommonJQ.clickTextByName(driver, ButtonPage.Submit);		
		LoadingPage.Loading(driver);
		if (isOK) {
			Assert.assertTrue("申请加入项目失败！", WorkSpacePage.getWarningTips(driver)==null);
		} else {
			String tips = WorkSpaceConst.WarningTips1;
			String realtips = WorkSpacePage.getWarningTips(driver);
			Assert.assertTrue("申请加入项目提示信息不正确,\n预期结果："+tips+"实际提示信息"+realtips, tips.equals(realtips));
			CommonJQ.clickTextByName(driver, ButtonPage.cancel);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void checkApplyInfor(WebDriver driver,String applytype,String Reason,String ProjectName,String State){
		LOG.info_aw("检查我的申请信息");
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.MyApplication);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver);
		//原因
		String real_reason = WorkSpacePage.getMyApplyRowtext2(driver, applytype, WorkSpaceConst.ApplyPageRow5);
		// 日期
		String real_date = WorkSpacePage.getMyApplyRowtext(driver, applytype, WorkSpaceConst.ApplyPageRow3);
		// 项目
		String real_project = WorkSpacePage.getMyApplyRowtext2(driver, applytype, WorkSpaceConst.ApplyPageRow4);
		// 状态
		String real_state = WorkSpacePage.getMyApplyRowtext(driver, applytype, WorkSpaceConst.ApplyPageRow2);
		
		Assert.assertEquals("申请原因不正确", Reason, real_reason);
		Assert.assertTrue("日期格式不正确！",real_date.contains(ZIP.GetTime("yyyy-MM-dd")));
		Assert.assertEquals("申请工程名称不正确", ProjectName, real_project);
		Assert.assertEquals("申请状态不正确", State, real_state);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	/**
	 * 
	* @Description: 审批权限申请
	* @param driver
	* @param applytype
	* @param account
	* @param approveresult
	* @param content 
	* @return void
	* @author zwx320041
	* @date 2017-1-12
	 */
	public static void approveApply(WebDriver driver, String applytype, String account, String approveresult,String content){
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.StayApprovePermission);
		LoadingPage.Loading(driver);
		CommonJQ.excuteJS(driver, "$('iframe').load(function(){});");
		SwitchDriver.switchDriverToFrame(driver);
		WorkSpacePage.clickApproveApplyTask(driver, applytype, account);
		LoadingPage.Loading(driver);
		WorkSpacePage.selectApproveResult(driver, approveresult);
		WorkSpacePage.setApproveContent(driver, content);
		CommonJQ.clickTextByName(driver, ButtonPage.Submit);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	/**
	 * 
	* @Description: 附件审批通过审批
	* @param driver
	* @param topic
	* @param account 
	* @return void
	* @author zwx320041
	* @date 2017-1-12
	 */
	public static void approveAttachPass(WebDriver driver, String topic, String account){
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.stayApproveAttachment);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver);
		WorkSpacePage.clickApproveApplyTask(driver, topic, account);
		LoadingPage.Loading(driver);
		CommonJQ.clickLinkTextByName(driver, WorkSpaceConst.ApproveResult3);		
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	/**
	 * 
	* @Description: 附件审批删除附件
	* @param driver
	* @param topic
	* @param account 
	* @return void
	* @author zwx320041
	* @date 2017-1-12
	 */
	public static void approveAttachDelete(WebDriver driver, String topic, String account){
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.stayApproveAttachment);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver);
		WorkSpacePage.clickApproveApplyTask(driver, topic, account);
		LoadingPage.Loading(driver);
		CommonJQ.clickLinkTextByName(driver, WorkSpaceConst.ApproveResult4);
		LoadingPage.Loading(driver);
		ButtonPage.clickMessageConfirmBTN(driver);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	/**
	 * 
	* @Description: 附件审批下载附件
	* @param driver
	* @param topic
	* @param account 
	* @return void
	* @author zwx320041
	* @date 2017-1-12
	 */
	public static void approveAttachDownLoad(WebDriver driver, String topic, String account){
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.stayApproveAttachment);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver);
		WorkSpacePage.clickApproveApplyTask(driver, topic, account);
		LoadingPage.Loading(driver);
		CommonJQ.clickLinkTextByName(driver, WorkSpaceConst.ApproveResult4);
		LoadingPage.Loading(driver);
		ButtonPage.clickMessageConfirmBTN(driver);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void approveAttachExistCheck(WebDriver driver, String topic,String account,boolean exsist){
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.stayApproveAttachment);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver);
		if (exsist) {
			Assert.assertTrue("不存在主题为"+topic+"的附件审批", WorkSpacePage.exsistApproveApplyTask(driver, topic, account));
		} else {
			Assert.assertFalse("不存在主题为"+topic+"的附件审批", WorkSpacePage.exsistApproveApplyTask(driver, topic, account));
		}
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToSEQ(driver);
		
	}

	public static void approveAttachPassAndDown(WebDriver driver, String title,
			String account, String attachPath) {
		// TODO Auto-generated method stub
		CommonJQ.clickNavigationByName(driver, WorkSpaceConst.stayApproveAttachment);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver);
		WorkSpacePage.clickApproveApplyTask(driver, title, account);
		LoadingPage.Loading(driver);
		UserCommunityTask.downAttachandCompare(driver, attachPath);
		CommonJQ.clickLinkTextByName(driver, WorkSpaceConst.ApproveResult3);		
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToSEQ(driver);
		
	}

	
	
	
	

}
