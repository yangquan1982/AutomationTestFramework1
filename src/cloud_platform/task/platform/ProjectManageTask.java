package cloud_platform.task.platform;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.projectManage.ProjectManageConst;
import cloud_platform.page.projectManage.ProjectManagePage;
import cloud_public.page.ButtonPage;
import cloud_public.task.HomeTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonJQ;
import common.util.LOG;


/**
 * @author pGenexAutotest
 *
 */
public class ProjectManageTask {
	
	// 创建项目并设置其他信息
	public static void paramCreateProject(WebDriver driver,String projectType,String area,String agent, String operator,  String projectLabel, String projectRat, String projectCode,String file, String GSC_userid,Boolean isDelete){
		String name = getProjectName(projectType, agent, operator, projectLabel, projectRat, projectCode);
		String path = ConstUrl.getProjectHomePath()	+ "\\Data\\全量环境\\"+file;//批量上传文件
		String proof = ConstUrl.getProjectHomePath()+ "\\Data\\全量环境\\Chrysanthemum.jpg";//凭证图片		
		Boolean Exisit = searchProject(driver, name);
		if (isDelete&&Exisit) {
			deleteProjectByName(driver, name);
		} else if (Exisit&&isDelete==false) {
			return;
		}		
		creatProject(driver, area, agent, operator, projectType, projectCode, projectRat, projectLabel, proof);
		batchImportProjectUser(driver, path);
		editCooperatorAuthority(driver, new String[]{"全选"}, true);
		editHWAuthority(driver, new String[]{"全选"}, true);
		if (!projectType.equalsIgnoreCase(ProjectManageConst.BusinessType_RF)) {
			editGSCInterfacePerson(driver, GSC_userid);
		}		
		checkExistProject(driver, name, true);		
	}
	
	// 只创建项目 不设置项目其他信息
	public static void paramCreateProject2(WebDriver driver,String projectType,String area,String agent, String operator,  String projectLabel, String projectRat, String projectCode,String file, String GSC_userid,Boolean isDelete){
		String name = getProjectName(projectType, agent, operator, projectLabel, projectRat, projectCode);
		String proof = ConstUrl.getProjectHomePath()+ "\\Data\\全量环境\\Chrysanthemum.jpg";//凭证图片		
		Boolean Exisit = searchProject(driver, name);
		if (isDelete&&Exisit) {
			deleteProjectByName(driver, name);
		} else if (Exisit&&isDelete==false) {
			return;
		}		
		creatProject(driver, area, agent, operator, projectType, projectCode, projectRat, projectLabel, proof);
				
		checkExistProject(driver, name, true);		
	}
	
	
	public static String getProjectName(String projectType,String agent, String operator,  String projectLabel, String projectRat, String projectCode){
		
			String name = agent+operator;
			if (projectType.equals(ProjectManageConst.BusinessType_RAN)) {
				name = name+projectLabel+"_"+projectCode;
			}else{
				name = name +projectRat+projectLabel;
			}
			return name;
	}
	/**
	 * 
	* @Description: 创建项目
	* @param driver
	* @param area
	* @param Agent
	* @param operator
	* @param businessArea
	* @param projectcode
	* @param rat
	* @param projectlabel
	* @param proof 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	public static void creatProject(WebDriver driver, String area, String Agent, String operator, String businessArea,String projectcode, String rat, String projectlabel,String proof){
		ProjectManagePage.clickTextByName(driver,ProjectManageConst.AddProject);
		ProjectManagePage.selectOperator(driver, area, Agent, operator);
		ProjectManagePage.setBusiness_Area(driver, businessArea);
		if (businessArea.equalsIgnoreCase(ProjectManageConst.BusinessType_RAN)) {
			ProjectManagePage.setProjectCode(driver, projectcode);
		} else {
			ProjectManagePage.setRAT(driver, rat);
		}		
		ProjectManagePage.setNewProjectName(driver, projectlabel);		
		ProjectManagePage.setProof(driver, proof);
		ButtonPage.clickFormSubmitBTN(driver);
//		Assert.assertTrue("创建项目失败！", isExistProject(driver, Agent+operator+rat+name));
	}
	
	/**
	 * 
	* @Description: 添加项目用户
	* @param driver
	* @param userid 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	public static void addProjectUser(WebDriver driver,String userid){
		LOG.info_aw("添加项目内部成员");
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.UserManage);
		boolean exsituser = ProjectManagePage.searchUser(driver, userid);
		if (exsituser) {
			deleteProjectUser(driver, userid);
		}
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.AddUser);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.InnerUser);
		ProjectManagePage.setUserId(driver, userid);
		ProjectManagePage.clickTextByName(driver, ButtonPage.Save);
		Assert.assertTrue("添加用户无异常", ProjectManagePage.isExistTextBTN(driver, ProjectManageConst.UserManage));
		if (ProjectManagePage.searchUser(driver, userid)==false) {
			Assert.fail("当前项目添加用户失败"+userid);
		}
	}
	
	public static void deleteProjectUser(WebDriver driver,String userid){
		LOG.info_aw("删除项目内部成员");
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.UserManage);
		if (ProjectManagePage.searchUser(driver, userid)) {
			CommonJQ.clickTableBoxByName(driver, userid);
			ProjectManagePage.clickTextByName(driver, ProjectManageConst.DeleteUser);
			ButtonPage.clickMessageConfirmBTN(driver);
			Assert.assertFalse("删除用户失败！", ProjectManagePage.searchUser(driver, userid));
		}else{
			Assert.fail("当前项目成员无此账号可删除！"+userid);
		}
	}
	
	//添加外部账号
	public static void addProjectoutUser(WebDriver driver,String userid){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.UserManage);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.AddUser);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.InnerUser);
		ProjectManagePage.setUserId(driver, userid);
		ProjectManagePage.clickTextByName(driver, ButtonPage.Save);
		Assert.assertTrue("添加用户无异常", ProjectManagePage.isExistTextBTN(driver, ProjectManageConst.UserManage));
	}
	
	//添加角色
	public static void addProjectRole(WebDriver driver,String rolename){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.RoleManage);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.AddRole);
		ProjectManagePage.setRoleName(driver, rolename);
		ProjectManagePage.clickTextByName(driver, ButtonPage.Save);
		Assert.assertTrue("添加用户无异常", ProjectManagePage.isExistTextBTN(driver, ProjectManageConst.RoleManage));
		Assert.assertTrue("添加角色失败！",CommonJQ.existLinkText(driver, rolename));
	}
	
	// 增加凭证
	public static void editProject(WebDriver driver,String path){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.BaseInfor);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.EditProject);
		ProjectManagePage.setProof(driver, path);
		ProjectManagePage.clickTextByName(driver, ButtonPage.Save);
//		String expectInfor = path.split("\\\\")[path.split("\\\\").length-1];
		String expectInfor = path.substring(path.lastIndexOf("\\")+1);
		String proofInfor = ProjectManagePage.getProofInfor(driver);
		Assert.assertTrue("添加凭证失败", StringUtils.containsIgnoreCase(proofInfor, expectInfor));		
	}
	
	// 增加凭证
	public static void changeManager(WebDriver driver,String proejecname ,String userid){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.BaseInfor);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.ChangeProjectManage);
		ProjectManagePage.changemanager(driver, userid, true);
		ProjectManagePage.goProjectList(driver);
		
//		Assert.assertTrue("添加凭证失败", StringUtils.containsIgnoreCase(proofInfor, expectInfor));		
	}
	
	/**
	 * 
	* @Description: 批量导入用户
	* @param driver
	* @param path 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	public static void  batchImportProjectUser(WebDriver driver,String path){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.UserManage);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.BatchImport);
		ProjectManagePage.batchImportUser(driver, path);
		ProjectManagePage.clickTextByName2(driver, ProjectManageConst.Import);
		ProjectManagePage.waitImportUser(driver);
		ProjectManagePage.clickTextByName(driver, ButtonPage.confirm);
		Assert.assertTrue("添加用户无异常", ProjectManagePage.isExistTextBTN(driver, ProjectManageConst.UserManage));
	}
	
	/**
	 * 
	* @Description: 编辑合作方人员权限
	* @param driver
	* @param analysisauthority
	* @param isHasUpload 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	public static void  editCooperatorAuthority(WebDriver driver, String[] analysisauthority, boolean isHasUpload){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.RoleManage);
		Pause.pause(1000);
		ProjectManagePage.clickLinkTextByName(driver, ProjectManageConst.CooperatorAuthority);
		ProjectManageTask.setAnalysisAuthority(driver, analysisauthority);
		ProjectManagePage.setUploadLimit(driver, isHasUpload);
		ProjectManagePage.clickTextByName(driver, ButtonPage.Save);
		Assert.assertTrue("设置合作方权限失败！", ProjectManagePage.isExistTextBTN(driver, ProjectManageConst.RoleManage));
	}
	
	/**
	 * 
	* @Description:编辑华为人员权限 
	* @param driver
	* @param analysisauthority
	* @param isHasUpload 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	public static void  editHWAuthority(WebDriver driver,String[] analysisauthority, boolean isHasUpload){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.RoleManage);
		Pause.pause(1000);
		ProjectManagePage.clickLinkTextByName(driver, ProjectManageConst.HWAuthority);
		ProjectManageTask.setAnalysisAuthority(driver, analysisauthority);
		ProjectManagePage.setUploadLimit(driver, isHasUpload);
		ProjectManagePage.clickTextByName(driver, ButtonPage.Save);
		Assert.assertTrue("设置华为人员权限失败！", ProjectManagePage.isExistTextBTN(driver, ProjectManageConst.RoleManage));
	}
	
	/**
	 * 
	* @Description: 编辑GSC接口人
	* @param driver
	* @param GSC_userid 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	public static void editGSCInterfacePerson(WebDriver driver, String GSC_userid){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.GSC_Interface);
		if (!ProjectManagePage.isDisableBTNByName(driver, ProjectManageConst.Remove)) {
			ProjectManagePage.clickTextByName(driver, ProjectManageConst.Remove);
			ButtonPage.clickMessageConfirmBTN(driver);			
		}
		ProjectManagePage.setUserId(driver, GSC_userid);
		ProjectManagePage.clickTextByName(driver, ButtonPage.Save);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.GSC_Interface);
		Assert.assertFalse("设置GSC接口人失败！", ProjectManagePage.isDisableBTNByName(driver, ProjectManageConst.Remove));
	}

	/**
	 * 
	* @Description: 验证是否存在项目
	* @param driver
	* @param name
	* @param isExist 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	public static void checkExistProject(WebDriver driver, String name,boolean isExist) 
	{
		LOG.info_aw("验证是否存在项目："+name);
		Search(driver, name);
		boolean flage =	CommonJQ.isExisted(driver, "a[title="+"\""+name+"\"]",true);
		for(int i=0;(i<10)&&(flage==false);i++){
			flage =	CommonJQ.isExisted(driver, "a[title="+"\""+name+"\"]",true);
			if(flage){
				break;
			}
			Pause.pause(1000);
		}		
		if (isExist) {
			Assert.assertTrue("没有项目："+name, flage);
			
		} else {
			Assert.assertFalse("有项目："+name, flage);
		}
	}
	
	private static Boolean searchProject(WebDriver driver, String name){
		LOG.info_aw("搜索是否存在项目："+name);
		Search(driver, name);
		boolean flage =	CommonJQ.isExisted(driver, "a[title="+"\""+name+"\"]",true);
		for(int i=0;(i<10)&&(flage==false);i++){
			flage =	CommonJQ.isExisted(driver, "a[title="+"\""+name+"\"]",true);
			if(flage){
				break;
			}
			Pause.pause(1000);
		}
		return flage;
	}
	/**
	 * 
	* @Description: 按照名称删除项目
	* @param driver
	* @param name 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	public static void deleteProjectByName(WebDriver driver, String name) 
	{
		checkExistProject(driver, name, true);
		LOG.info_aw("删除项目："+name);
		ProjectManagePage.clickTableBoxByName(driver, name);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.DeleteProject);
		ButtonPage.clickMessageConfirmBTN(driver);
		checkExistProject(driver, name, false);
	}
	
	/**
	 * 
	* @Description: 按照名称搜索项目
	* @param driver
	* @param name 
	* @return void
	* @author zwx320041
	* @date 2017-1-3
	 */
	private static void Search(WebDriver driver, String name) 
	{
		boolean flage =	CommonJQ.isExisted(driver, "#project_key", true);
		if(flage == false)
		{
			HomeTask.JumpToprojectManage(driver, true);
		}
		LOG.info_aw("搜索项目："+name);
		CommonJQ.value(driver, "span[id=\"searchInfo\"] #project_key", name, true);
		CommonJQ.click(driver, "span[id=\"searchInfo\"] #search", true);
		int Num = CommonJQ.length(driver, ".num a", true);
		for(int i =0;(i<3)&&(Num!=1);i++){
			Pause.pause(1000);
			CommonJQ.value(driver, "span[id=\"searchInfo\"] #project_key", name, true);
			CommonJQ.click(driver, "span[id=\"searchInfo\"] #search", true);
			Num = CommonJQ.length(driver, ".num a", true);
			if(Num==1){
				break;
			}
		}
	}
		
	/**
	 * 
	* @Description: 设置分析权限，第一个值为全选 则全选
	* @param driver
	* @param analysisauthority 
	* @return void
	* @author zwx320041
	* @date 2016-12-29
	 */
	private static void setAnalysisAuthority(WebDriver driver, String[] analysisauthority){
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.SelectAll);
		ProjectManagePage.clickTextByName(driver, ProjectManageConst.ReverseSelect);
		if(analysisauthority!=null){
			if (StringUtils.containsIgnoreCase(analysisauthority[0], ProjectManageConst.SelectAll)) {
				ProjectManagePage.clickTextByName(driver, ProjectManageConst.SelectAll);
				return;
			}
			for (int i = 0; i < analysisauthority.length; i++) {
				ProjectManagePage.setAnalysisLimit(driver, analysisauthority[i]);
			}
		}
		
	}
	
//	/**
//	 * 
//	* @Description: 批量导入用户
//	* @param driver
//	* @param path 
//	* @return void
//	* @author zwx320041
//	* @date 2016-12-29
//	 */
//	private static void BatchImportUser(WebDriver driver,String path){
//		ProjectManagePage.clickTextByName(driver, ProjectManageConst.BatchImport);
//		ProjectManagePage.batchImportUser(driver, path);
//		ProjectManagePage.clickTextByName(driver, ProjectManageConst.Import);		
//	}
}
