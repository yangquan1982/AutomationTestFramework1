package cloud_platform.task.platform;

import org.apache.commons.lang.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.PlatformConst;
import cloud_platform.page.projectManage.ProjectManageConst;
import cloud_platform.page.projectManage.ProjectManagePage;
import cloud_platform.page.systemManage.SystemManageConst;
import cloud_platform.page.systemManage.SystemManagePage;
import cloud_public.task.HomeTask;
import common.parameter.CellInfo;
import common.parameter.platform.Operator;
import common.parameter.platform.ProjectMangeParameter;
import common.parameter.platform.SystemManageParameter;
import common.util.LOG;
import common.util.SwitchDriver;
import common.util.ZIP;


public class SystemManageTask {
	
	public static  void addMange(WebDriver driver,SystemManageParameter parameter) {
		SystemManagePage.clickUserManagerItem(driver, parameter.getSetitemname());
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		boolean exsistuser1= false;
		if (!parameter.getSetitemname().equalsIgnoreCase(SystemManageConst.Tip_ExpertProcess)) {
			exsistuser1 = searchUser(driver, parameter.getUserid());
		}
		if(exsistuser1){
			LOG.info_aw("删除账号:"+parameter.getUserid());
			SystemManagePage.selectUserByRowNumber(driver, 0);
			SystemManagePage.clickDeleteAccoutBTN(driver);
			SystemManagePage.clickConfirmBTN(driver);
			if(searchUser(driver, parameter.getUserid())){
				Assert.fail(parameter.getSetitemname()+"删除账户失败："+parameter.getUserid());
			}
		}
		if (!StringUtils.containsIgnoreCase(parameter.getSetitemname(), SystemManageConst.Set_ExpertProcess)){
			LOG.info_aw("点击新增账户");
			SystemManagePage.clickAddManagerBTN(driver);
		}
		
		if (!StringUtils.containsIgnoreCase(parameter.getSetitemname(), SystemManageConst.Set_ExpertProcess)) {
			LOG.info_aw("设置账户ID");
			SystemManagePage.addUserID(driver, parameter.getUserid());
		}
		
		if (StringUtils.containsIgnoreCase(parameter.getSetitemname(), SystemManageConst.Set_Expert)||StringUtils.containsIgnoreCase(parameter.getSetitemname(), SystemManageConst.Set_ExpertProcess)) {
			
			SystemManagePage.selectBusinessSubject(driver, parameter.getBusinessSubjects());
			//选择运营商
			
			SystemManageTask.setOperator(driver,parameter.getSetitemname(),parameter.getOperator());
		}
		if (StringUtils.containsIgnoreCase(parameter.getSetitemname(), SystemManageConst.Set_Expert)) {
			
			SystemManagePage.selectRats(driver, parameter.getRats());
		}
		
		
		if (StringUtils.containsIgnoreCase(parameter.getSetitemname(), SystemManageConst.Set_ExpertProcess)){
			SystemManagePage.selectProjectName(driver, parameter.getProjectname());
		}
		
		if (StringUtils.containsIgnoreCase(parameter.getSetitemname(), SystemManageConst.Set_Statistician)) {
			//选择负责运营商
			SystemManagePage.selectManageOperator(driver, parameter.getManageoperator());
			//选择管理区域
			SystemManagePage.selectManageArea(driver, parameter.getManagearea());
		}
		//提交表单
		SystemManagePage.clickSaveBTN(driver);
		SystemManagePage.waitLoading(driver,parameter.getSetitemname());
		
		
		if (StringUtils.containsIgnoreCase(parameter.getSetitemname(), SystemManageConst.Set_ExpertProcess)) {
			String tipmessage = SystemManagePage.getSetOKInfor(driver);
			if (StringUtils.containsIgnoreCase(tipmessage, SystemManageConst.Tip_ExpertProcess)||StringUtils.containsIgnoreCase(tipmessage, "保存")) {
				SystemManagePage.clickConfirmBTN(driver);
			} else {
				Assert.fail("添加专家流程失败！\n"+tipmessage);
			}
		} else {
			if(SystemManagePage.existSubmitBTN(driver)){
				Assert.fail(parameter.getSetitemname()+"-添加用户"+parameter.getUserid()+"失败！");
			}
			boolean exsistuser = searchUser(driver, parameter.getUserid());
			if(!exsistuser){
				Assert.fail(parameter.getSetitemname()+"-添加用户"+parameter.getUserid()+"失败！");
			}
		}
		
	}
	
	
	public static  void deleteAddMange(WebDriver driver,SystemManageParameter parameter,boolean isdelete) {
		SwitchDriver.switchDriverToSEQ(driver);
		boolean flag = deleteAccount2(driver, parameter,isdelete);
		SwitchDriver.switchDriverToSEQ(driver);
		if (flag) {
			addMange(driver, parameter);
		}
		
		
	}
	
	public static void editAccount(WebDriver driver,SystemManageParameter parameter){
		SystemManagePage.clickUserManagerItem(driver, parameter.getSetitemname());
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		boolean exsistuser = searchUser(driver, parameter.getUserid());
		if(exsistuser){
			SystemManagePage.clickUserID(driver, parameter.getUserid());
			SystemManagePage.clickSaveBTN(driver);			
		}else{
			Assert.fail("不存在账户："+parameter.getUserid());
		}		
	}
	
	public static void deleteAccount(WebDriver driver,SystemManageParameter parameter){
		SystemManagePage.clickUserManagerItem(driver, parameter.getSetitemname());
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		boolean exsistuser = searchUser(driver, parameter.getUserid());
		if(exsistuser){
			SystemManagePage.selectUserByRowNumber(driver, 0);
			SystemManagePage.clickDeleteAccoutBTN(driver);
			SystemManagePage.clickConfirmBTN(driver);
			if(searchUser(driver, parameter.getUserid())){
				Assert.fail(parameter.getSetitemname()+"删除账户失败："+parameter.getUserid());
			}
		}else{
			Assert.fail("不存在账户："+parameter.getUserid());
		}		
	}
	
	public static boolean deleteAccount2(WebDriver driver,SystemManageParameter parameter, boolean isdelete){
		SystemManagePage.clickUserManagerItem(driver, parameter.getSetitemname());
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		boolean exsistuser = searchUser(driver, parameter.getUserid());
		if(exsistuser&&isdelete){
			SystemManagePage.selectUserByRowNumber(driver, 0);
			SystemManagePage.clickDeleteAccoutBTN(driver);
			SystemManagePage.clickConfirmBTN(driver);
			if(searchUser(driver, parameter.getUserid())){
				Assert.fail(parameter.getSetitemname()+"删除账户失败："+parameter.getUserid());
			}
			return true;
		}else if(exsistuser==false){
			return true;
		}
		return false;
	}
	
	
	private static boolean searchUser(WebDriver driver,String userid){
		LOG.info_aw("搜索账号："+userid);
		SystemManagePage.searchUserByName(driver, userid);
		String searchresult = "";
		for (int i = 0; i < 100; i++) {
			searchresult = SystemManagePage.getfirstUserAccout(driver);
			if (searchresult.equalsIgnoreCase(userid)||StringUtils.isBlank(searchresult)) {
				break;
			}else{
				Pause.pause(100);
			}
		}
//		int usernumber = SystemManagePage.getUserNumber(driver);
//		return usernumber>=1;
		return searchresult.equalsIgnoreCase(userid);
	} 
	private static void setOperator(WebDriver driver, String setitemname,
			Operator operator) {
		LOG.info_aw("设置运营商信息");
		if(StringUtils.containsIgnoreCase(setitemname, SystemManageConst.Set_Expert)){
			SystemManagePage.clickAddOperatorBTN(driver);			
		}
		if(StringUtils.containsIgnoreCase(setitemname, SystemManageConst.Set_ExpertProcess)){
			SystemManagePage.clickAddOperatorBTN2(driver);			
		}
		SystemManagePage.waitOperatorselectElement(driver);
		SystemManagePage.selectOperators(driver, operator);
		
	}


	public static void AuthorityVertify(WebDriver driver,
			SystemManageParameter parameter, ProjectMangeParameter projectparameter, CellInfo authority, String accountType) {
		// 用户社区
		String title = "自动化测试用户社区帖子_"+ZIP.GetTime("MMddhhmmss");
		String content = "自动化测试用户社区帖子内容";
		String tag = "自动化测试用户社区帖子标签";
		parameter.setManageoperator(new String[] { "中国联通", "中国移动", "中国电信" });
		parameter.getManagearea().setSelectall(true);
		parameter.getOperator().setRegion(SystemManageConst.area);
		parameter.getOperator().setAgent(SystemManageConst.agent);
		parameter.getOperator().setOperator(
				new String[] { SystemManageConst.operator });
		//parameter.setProjectname(PlatformConst.projectName);
		projectparameter.setRegion(SystemManageConst.area);
		projectparameter.setAgent(SystemManageConst.agent);
		projectparameter.setOperator(PlatformConst.operator);
		
		projectparameter.setProjectType(ProjectManageConst.BusinessType_ICS);
		projectparameter.setProjectRat("LTE");
		switch (authority.getCellNum()) {
		case 1:
			parameter.setSetitemname(SystemManageConst.Set_SystemManger);			
			LOG.info_testcase("系统管理-添加系统管理员验证");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.addMange(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 2:
			parameter.setSetitemname(SystemManageConst.Set_SystemManger);			
			LOG.info_testcase("系统管理-删除系统管理员验证");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.deleteAccount(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 3:
			parameter.setSetitemname(SystemManageConst.Set_Expert);			
			LOG.info_testcase("系统管理-添加专家权限验证");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.addMange(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 4:
			parameter.setSetitemname(SystemManageConst.Set_Expert);			
			LOG.info_testcase("系统管理-编辑专家权限验证");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.editAccount(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 5:
			parameter.setSetitemname(SystemManageConst.Set_Expert);			
			LOG.info_testcase("系统管理-删除专家权限验证");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.deleteAccount(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 6:
			parameter.setSetitemname(SystemManageConst.Set_ProjectManger);			
			LOG.info_testcase("系统管理-添加项目经理");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.addMange(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 7:
			parameter.setSetitemname(SystemManageConst.Set_ProjectManger);			
			LOG.info_testcase("系统管理-删除项目经理");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.deleteAccount(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 8:
			parameter.setSetitemname(SystemManageConst.Set_Statistician);	
			LOG.info_testcase("系统管理-添加统计员");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.addMange(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 9:
			parameter.setSetitemname(SystemManageConst.Set_Statistician);			
			LOG.info_testcase("系统管理-编辑统计员");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.editAccount(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 10:
			parameter.setSetitemname(SystemManageConst.Set_Statistician);			
			LOG.info_testcase("系统管理-删除统计员");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.deleteAccount(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 11:
			parameter.setSetitemname(SystemManageConst.Set_ExpertProcess);			
			LOG.info_testcase("系统管理-专家流程设置");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpSystemManage(driver, true);
				if(authority.getIsRun().contains("N")){
					Assert.assertFalse("存在系统管理员权限", SystemManagePage.existAccountManage(driver, parameter.getSetitemname()));
				}else{
					SystemManageTask.addMange(driver, parameter);
				}
			} else {
				HomeTask.JumpSystemManage(driver, false);
			}			
			break;
		case 12:
			LOG.info_testcase("添加项目");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpToprojectManage(driver, true);
				if(authority.getIsRun().contains("N")){
					ProjectManagePage.isDisableBTNByName(driver, ProjectManageConst.AddProject);
				}else{
					ProjectManageTask.creatProject(driver,
							projectparameter.getRegion(),
							projectparameter.getAgent(),
							projectparameter.getOperator(),
							projectparameter.getProjectType(),
							projectparameter.getProjectCode(),
							projectparameter.getProjectRat(),
							projectparameter.getProjectLabel(), null);
					ProjectManageTask.checkExistProject(driver, projectparameter.getProjectname(), true);
				}
			} else {
				HomeTask.JumpToprojectManage(driver, false);
			}			
			break;
		case 13:
			LOG.info_testcase("项目添加项目成员 批量导入");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpToprojectManage(driver, true);
				if(authority.getIsRun().contains("N")){
					ProjectManagePage.isDisableBTNByName(driver, ProjectManageConst.AddProject);
				}else{					
					ProjectManageTask.checkExistProject(driver, projectparameter.getProjectname(), true);
					ProjectManagePage.clickLinkTextByName(driver, projectparameter.getProjectname());
					ProjectManageTask.batchImportProjectUser(driver, ProjectManageConst.UserFilePath);
				}
			} else {
				HomeTask.JumpToprojectManage(driver, false);
			}			
			break;
		case 14:
			LOG.info_testcase("项目编辑角色权限");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpToprojectManage(driver, true);
				if(authority.getIsRun().contains("N")){
					ProjectManagePage.isDisableBTNByName(driver, ProjectManageConst.AddProject);
				}else{					
					ProjectManageTask.checkExistProject(driver, projectparameter.getProjectname(), true);
					ProjectManagePage.clickLinkTextByName(driver, projectparameter.getProjectname());
					ProjectManageTask.editCooperatorAuthority(driver, new String[] {ProjectManageConst.SelectAll},  true);
					ProjectManageTask.editHWAuthority(driver, new String[] {ProjectManageConst.SelectAll},  true);
				}
			} else {
				HomeTask.JumpToprojectManage(driver, false);
			}			
			break;
		case 15:
			LOG.info_testcase("项目验证上传凭证");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpToprojectManage(driver, true);
				if(authority.getIsRun().contains("N")){
					ProjectManagePage.isDisableBTNByName(driver, ProjectManageConst.AddProject);
				}else{					
					ProjectManageTask.checkExistProject(driver, projectparameter.getProjectname(), true);
					ProjectManagePage.clickLinkTextByName(driver, projectparameter.getProjectname());
					ProjectManageTask.editProject(driver, ProjectManageConst.ProofPth);
				}
			} else {
				HomeTask.JumpToprojectManage(driver, false);
			}			
			break;
		case 16:
			LOG.info_testcase("项目接口人设置");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpToprojectManage(driver, true);
				if(authority.getIsRun().contains("N")){
					ProjectManagePage.isDisableBTNByName(driver, ProjectManageConst.AddProject);
				}else{					
					ProjectManageTask.checkExistProject(driver, projectparameter.getProjectname(), true);
					ProjectManagePage.clickLinkTextByName(driver, projectparameter.getProjectname());
					ProjectManageTask.editGSCInterfacePerson(driver, "wwx320172");
				}
			} else {
				HomeTask.JumpToprojectManage(driver, false);
			}			
			break;
		case 17:
			LOG.info_testcase("删除项目");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpToprojectManage(driver, true);
				if(authority.getIsRun().contains("N")){
					ProjectManagePage.isDisableBTNByName(driver, ProjectManageConst.AddProject);
				}else{					
					ProjectManageTask.deleteProjectByName(driver, projectparameter.getProjectname());
				}
			} else {
				HomeTask.JumpToprojectManage(driver, false);
			}			
			break;
		case 18:
			LOG.info_testcase("知识共享中心验证");
			if (authority.getIsRun()!=null) {
				HomeTask.JumpToKnowledgeShareCenter(driver, true);
			} else {
				HomeTask.JumpToKnowledgeShareCenter(driver, false);
			}			
			break;	
//		case 19:
//			LOG.info_testcase("用户社区发起求助");
//			HomeTask.JumpToUserCommunity(driver);
//			UserCommunityTask.askHelpOrDiscuss(driver, null, title, content, tag, null);
//			UserCommunityTask.checkTopic(driver, title, true);
//			break;	
//		case 20:
//			LOG.info_testcase("用户社区编辑帖子");
//			HomeTask.JumpToUserCommunity(driver);
//			UserCommunityTask.editTopic(driver, title, content+"_新增帖子内容");
//			UserCommunityTask.checkTopic(driver, title, true);
//			break;
//		case 21:
//			LOG.info_testcase("用户社区置顶帖子");
//			HomeTask.JumpToUserCommunity(driver);
//			if (c.getIsRun().contains("N")) {
//				Assert.assertFalse("置顶按钮存在", UserCommunityPage.notExsistBTN(driver, UserCommunityConst.Top));
//			} else {
//				UserCommunityTask.topTopic(driver, title);
//				UserCommunityTask.checkToppicTop(driver, title, true);	
//			}
//			
//			break;
//		case 22:
//			LOG.info_testcase("用户社区取消置顶帖子");
//			HomeTask.JumpToUserCommunity(driver);
//			if (c.getIsRun().contains("N")) {
//				Assert.assertFalse("取消置顶按钮存在", UserCommunityPage.notExsistBTN(driver, UserCommunityConst.Unstuck));
//				
//			} else {
//				
//				UserCommunityTask.unstuckTopic(driver, title);
//				UserCommunityTask.checkToppicTop(driver, title, false);
//			}			
//			break;
//		case 23:
//			LOG.info_testcase("用户社区删除帖子");
//			HomeTask.JumpToUserCommunity(driver);
//			UserCommunityTask.deleteTopic(driver, title);
//			UserCommunityTask.checkTopic(driver, title, false);
//			break;
		default:
			break;
		}
		
	}
	
//	public static void selectAuthoritySetItem(WebDriver driver,String setItem,String userid){
//		//选择设置项
//		SystemManagePage.clickUserManagerItem(driver, setItem);
//		//点击添加按钮
//		SwitchDriver.switchDriverToFrame(driver, "//iframe");
//		SystemManagePage.clickAddManagerBTN(driver);
//		
//		SystemManagePage.addUserID(driver, userid);
//		SystemManagePage.clickSaveBTN(driver);
//		SystemManagePage.searchUserByName(driver, userid);
//		int usernumber = SystemManagePage.getUserNumber(driver);
//		if(usernumber!=1){
//			Assert.fail(setItem+"-添加用户"+userid+"失败！");
//		}
//	}
	
	

}
