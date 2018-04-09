package cloud_platform.task.platform;

import org.apache.commons.lang.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.page.usercommunity.UserCommunityConst;
import cloud_platform.page.usercommunity.UserCommunityPage;
import cloud_platform.page.workspace.WorkSpaceConst;
import cloud_public.page.ButtonPage;
import cloud_public.page.LoadingPage;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileCompare;

public class UserCommunityTask {
	
	/**
	 * 
	* @Description:发起求助和讨论 
	* @param driver
	* @param category
	* @param text
	* @param path 
	* @return void
	* @author zwx320041
	 * @param content 
	 * @param tag 
	* @date 2017-1-10
	 */
	public static void askHelpOrDiscuss(WebDriver driver, String category, String title, String content, String tag,String path){
		CommonJQ.clickTextByName(driver, UserCommunityConst.AskforHelpOrDiscuss);
		LoadingPage.Loading(driver);
		if (category!=null&&!StringUtils.isBlank(category)) {
			UserCommunityPage.selectCategory(driver, category);
		}	
		UserCommunityPage.setTitle(driver, title);
		UserCommunityPage.setContent(driver, content);
		UserCommunityPage.setTag(driver, tag);
		if (path!=null&&!StringUtils.isBlank(path)) {
			UserCommunityPage.addAttachment(driver, path);
			CommonJQ.waitForElementVisble(driver, "#fileDate");
		}		
		CommonJQ.clickTextByName(driver, UserCommunityConst.Publish);
		LoadingPage.Loading(driver);		
	}
	
	/**
	 * 
	* @Description: 检查帖子是否存在
	* @param driver
	* @param text
	* @param isExistTopic 
	* @return void
	* @author zwx320041
	* @date 2017-1-10
	 */
	public static void checkTopic(WebDriver driver,String text,Boolean isExistTopic){
		UserCommunityPage.searchTopic(driver, text);
		if (isExistTopic){
			Assert.assertTrue("用户社区不存在帖子：" + text,UserCommunityPage.isExistTopic(driver, text));
		}else{
			Assert.assertFalse("用户社区存在帖子：" + text,UserCommunityPage.isExistTopic(driver, text));
		}
	}
	
	/**
	 * 
	* @Description: 编辑帖子
	* @param driver
	* @param text
	* @param content 
	* @return void
	* @author zwx320041
	* @date 2017-1-10
	 */
	public static void editTopic(WebDriver driver,String text,String content){
		checkTopic(driver, text,true);
		CommonJQ.clickTableBoxByName(driver, text);
		CommonJQ.clickTextByName(driver, UserCommunityConst.Edit);
		LoadingPage.Loading(driver);
		UserCommunityPage.setContent(driver, content);
		CommonJQ.clickTextByName(driver, UserCommunityConst.Submit);
		LoadingPage.Loading(driver);
		Assert.assertTrue("编辑帖子失败"+text,CommonJQ.existTextByName(driver, UserCommunityConst.AskforHelpOrDiscuss));
	}
	
	/**
	 * 
	* @Description: 删除帖子
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-10
	 */
	public static void deleteTopic(WebDriver driver,String text){
		checkTopic(driver, text,true);
		CommonJQ.clickTableBoxByName(driver, text);
		CommonJQ.clickTextByName(driver, UserCommunityConst.Delete);
		LoadingPage.Loading(driver);
		Pause.pause(1000);
		CommonJQ.clickTextByName(driver, UserCommunityConst.Confirm);
		LoadingPage.Loading(driver);
		UserCommunityPage.searchTopic(driver, text);
		Assert.assertFalse("删除帖子失败！" + text,UserCommunityPage.isExistTopic(driver, text));
	}
	
	/**
	 * 
	* @Description: 置顶帖子
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-10
	 */
	public static void topTopic(WebDriver driver,String text){
		checkTopic(driver, text,true);
		CommonJQ.clickTableBoxByName(driver, text);
		CommonJQ.clickTextByName(driver, UserCommunityConst.Top);
		Pause.pause(1000);
		CommonJQ.clickTextByName(driver, UserCommunityConst.Confirm);
		LoadingPage.Loading(driver);
	}
	/**
	 * 
	* @Description: 取消置顶
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-10
	 */
	public static void unstuckTopic(WebDriver driver,String text){
		checkTopic(driver, text,true);
		CommonJQ.clickTableBoxByName(driver, text);
		CommonJQ.clickTextByName(driver, UserCommunityConst.Unstuck);
		Pause.pause(1000);
		CommonJQ.clickTextByName(driver, UserCommunityConst.Confirm);
		LoadingPage.Loading(driver);
	}
	
	/**
	 * 
	* @Description: 检查帖子是否置顶
	* @param driver
	* @param text
	* @param isTop 
	* @return void
	* @author zwx320041
	* @date 2017-1-10
	 */
	public static void checkToppicTop(WebDriver driver,String text,boolean isTop){
		checkTopic(driver, text,true);
		if (isTop) {
			Assert.assertTrue("帖子："+text+"-没有置顶", UserCommunityPage.isToptopic(driver, text));
		}else {
			Assert.assertFalse("帖子："+text+"-置顶", UserCommunityPage.isToptopic(driver, text));
		}
	}
	
	/**
	 * 
	* @Description: 检查存在附件
	* @param driver
	* @param text
	* @param existattach 
	* @return void
	* @author zwx320041
	* @date 2017-1-17
	 */
	public static void checkTopicAttach(WebDriver driver,String text,boolean existattach){
		checkTopic(driver, text,true);
		CommonJQ.clickLinkTextByName(driver, text);
		LoadingPage.Loading(driver);
		if (existattach) {
			Assert.assertTrue("帖子："+text+"-没有附件", UserCommunityPage.existAttach(driver));
		}else {
			Assert.assertFalse("帖子："+text+"-有附件", UserCommunityPage.existAttach(driver));
		}		
	}
	
	/**
	 * 
	* @Description:检查附件被删除 
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-17
	 */
	public static void checkTopicAttachDeleted(WebDriver driver,String text){
		checkTopic(driver, text,true);
		CommonJQ.clickLinkTextByName(driver, text);
		LoadingPage.Loading(driver);
		Assert.assertTrue("帖子："+text+"-没有附件", UserCommunityPage.existAttach(driver));
		
		Assert.assertTrue("帖子："+text+"-没有附件", UserCommunityPage.attachDeleteText(driver).contains(UserCommunityConst.AttachDeleteInfor));
	}
	
	/**
	 * 
	* @Description: 删除附件
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-17
	 */
	public static void deleteAttach(WebDriver driver,String text){
		checkTopic(driver, text,true);
		CommonJQ.clickLinkTextByName(driver, text);
		CommonJQ.clickLinkTextByName(driver, WorkSpaceConst.ApproveResult4);
		LoadingPage.Loading(driver);
		ButtonPage.clickMessageConfirmBTN(driver);
		LoadingPage.Loading(driver);
	}

	/**
	 * 
	* @Description: 重新上传附件
	* @param driver
	* @param text
	* @param path 
	* @return void
	* @author zwx320041
	* @date 2017-1-17
	 */
	public static void appendAttach(WebDriver driver, String text, String path) {
		checkTopic(driver, text,true);
		CommonJQ.clickLinkTextByName(driver, text);
		CommonJQ.clickLinkTextByName(driver, UserCommunityConst.ReAddAttach);
		UserCommunityPage.reAddAttachment(driver, path);
		ButtonPage.clickMessageConfirmBTN(driver);
		CommonJQ.waitForElementVisble(driver, "#deleteFile");
	}

	/**
	 * 
	* @Description: 下载附件并对比文件
	* @param driver
	* @param title
	* @param attachPath 
	* @return void
	* @author zwx320041
	* @date 2017-1-17
	 */
	public static void downLoadAttach(WebDriver driver, String text,
			String attachPath) {
		checkTopic(driver, text,true);
		CommonJQ.clickLinkTextByName(driver, text);
		downAttachandCompare(driver,attachPath);
	}
	
	public static void downAttachandCompare(WebDriver driver, String attachPath){		
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		UserCommunityPage.downLoadAttach(driver);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, ".xlsx");
		String downloadFilePath = CommonFile
				.getDownLoadDirectorygetAbsolutePath();
		Assert.assertTrue("下载附件与原始文件对比失败，文件路径："+attachPath, FileCompare.fileCompare(attachPath, downloadFilePath));
	}

	/**
	 * 
	* @Description: 用户社区通过审批
	* @param driver
	* @param text 
	* @return void
	* @author zwx320041
	* @date 2017-1-17
	 */
	public static void ApproveApplyAttach(WebDriver driver, String text) {
		checkTopic(driver, text,true);
		CommonJQ.clickLinkTextByName(driver, text);
		LoadingPage.Loading(driver);
		CommonJQ.clickLinkTextByName(driver, WorkSpaceConst.ApproveResult3);		
		LoadingPage.Loading(driver);		
	}

	public static void cancelApproveApplyAttach(WebDriver driver, String text) {
		checkTopic(driver, text,true);
		CommonJQ.clickLinkTextByName(driver, text);
		LoadingPage.Loading(driver);
		CommonJQ.clickLinkTextByName(driver, UserCommunityConst.cancelApproveAttach);		
		LoadingPage.Loading(driver);		
	}

	

}
