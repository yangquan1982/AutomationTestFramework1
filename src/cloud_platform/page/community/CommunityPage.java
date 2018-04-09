package cloud_platform.page.community;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import cloud_public.page.AwCommon;
import cloud_public.page.LoadingPage;
import common.parameter.platform.Sharing_ZH_EN;
import common.util.CommonObject;
import common.util.LOG;
import common.util.TestWebDriver;
import common.util.ASSERT;
import common.util.CommonJQ;

public class CommunityPage {

	@FindBy(how=How.ID,using="title")
	private  WebElement title;
	
	@FindBy(how=How.ID,using="contents")
	private  WebElement contents;
	
	@FindBy(how=How.ID,using="tag")
	private  WebElement tag;
	
	@FindBy(how=How.ID,using="publishBtn")
	private  WebElement publishBtn;

	@FindBy(how=How.ID,using="reply_content")
	private  WebElement replyContent;
	
	//点赞按钮
	@FindBy(how=How.ID,using="praiseCounter")
	private  WebElement praise_Counter;
	
	//分享按钮
	@FindBy(how=How.ID,using="sharingCounter")
	private  WebElement sharing_Counter;
	
	//分享用户邮箱地址
	@FindBy(how=How.ID,using="additionMsg")
	private  WebElement sharing_additionMsg;
	
	
	/**
	 * 点击发起求助或讨论按钮
	 * @param driver
	 */
	public void clickStarthelpBtn(WebDriver driver)
	{
		LOG.info("click starthelpBtn");
		String jselement="$('div#btn_addCase').find('span:contains("+UserCommunity_ZH_EN.Ask_for_Help_or_Discuss+")')";
		CommonJQ.click(driver,jselement);
		
	}
	
	/**
	 * 设置案件类型
	 * @param driver 
	 * @param modenType
	 */
	public void setMode(WebDriver driver, String modenType)
	{
		LOG.info_aw("设置案例类型");
		driver.findElement(By.id("textmoduleId")).click();
		List<WebElement> wes = driver.findElement(By.id("mshowmoduleId")).findElements(By.tagName("li"));
		for(WebElement we :wes )
		{
			System.out.println(we.getText());
			if (we.getText().equalsIgnoreCase(modenType))AwCommon.moveAndClick(driver,we);
		}
	}
	
	/**
	 * 设置案件标题
	 * @param driver 
	 * @param Title
	 */
	public void setTitle(WebDriver driver, String Title)
	{
		
		LOG.info_aw("设置案例标题");
		title.click();
		title.clear();
		title.sendKeys(Title);
	}
	 
	 /**
	  * 设置案件内容
	 * @param driver 
	  * @param content
	  */
	 public void setContent(WebDriver driver, String content)
	 {
		LOG.info_aw("设置案例内容");
		CommonJQ.excuteJS(driver, "$('iframe[class=\"ke-edit-iframe\"]').contents().find('.ke-content').text(\""+content+"\")");
	}
	
	 /**
	  * 设置标签
	 * @param driver 
	  * @param Tag
	  */
	public void setTag(WebDriver driver, String Tag)
	{
		LOG.info_aw("设置标签");
		WebElement we = TestWebDriver.findElement(By.id("tag"));
		we.sendKeys(Tag);
	}
		
	/**
	 * 点击发布案例按钮
	 * @param driver
	 */
	public void clickPublishBtn(WebDriver driver)
	{
		LOG.info("点击发布案例按钮");
		//publishBtn.click();
		driver.findElement(By.id("publishBtn")).findElement(By.tagName("span")).click();
	}
	
	
	/**
	 * 点击取消发布按钮
	 * @param driver
	 */
	public void clickCancelBtn(WebDriver driver)
	{
		String jselement="$('a.btn_gray_1.ml10').find('span')";
		LOG.info("点击取消发布案例按钮");
		CommonJQ.click(driver,jselement);
	}
	
	/**
	 * 得到求助的浏览/回复次数
	 * @param taskName
	 * @return
	 */
	public static Integer[] getLookAndReplayNum(WebDriver driver,String taskName)
	{
		LOG.info("得到求助"+taskName+"的浏览/回复次数");
		String jselement="return $('div#r_container_conn_r').find('tr:contains("+taskName+")').find('td').eq(3).text()";
		System.out.println(jselement);
		ASSERT.checkWebElementsExist("", "$('div#r_container_conn_r').find('tr:contains("+taskName+")').find('td').eq(3)");
		
		String text=(String) CommonJQ.excuteJStoString(driver,jselement);
		LOG.info("获取到当前'浏览/回复'情况为:"+ text);
		String[] nums=(text.split("/"));
		Integer[] num=new Integer[nums.length];
		for(int i=0;i<nums.length;i++){
			num[i]=Integer.parseInt(nums[i].trim());
         System.out.println(num[i]);
		}
		return num;
	}
	
	/**
	 * 点击进入求助案件中
	 * @param driver
	 * @param name
	 */
	public void clickTaskByName(WebDriver driver,String name)
	{
		LOG.info("点击"+name+"进入求助案例中");
		for(int i=0;i<3;i++)
		{
			try
			{
				WebElement we = CommonObject.driver.findElementByPartialLinkText(name);//????????????????
				AwCommon.moveAndClick(we);
				LoadingPage.waitForPagLoad(driver);
				break;
			}catch (Exception e) 
			{
				LOG.info("The WebElement that task name contains " + name + " is not found.");
			}
		}
	}
	
	
	/**
	 * 提交回复
	 * @param driver 
	 */
	public void submitReply(WebDriver driver)
	{
		 String jsement="$('div#sumbitBtn').find('span:contains("+UserCommunity_ZH_EN.Submit+")')";
		 CommonJQ.click(driver,jsement);
	 }
	
	
	/**
	 * 点击取消按钮
	 */
	 public void cancelReply(WebDriver driver)
	 {
		 String jsement="$('div#sumbitBtn').find('span:contains("+Sharing_ZH_EN.Cancel_and_Back+")')";
		 CommonJQ.click(driver,jsement);
	 }
	 
	 
	 /**
	 * 在详情中点击删除按钮
	 */
	 public void ClickCancelBtnInDetail(WebDriver driver)
	 {
		driver.findElement(By.id("replayCounter")).click();
	 }
	 
	
	 /**
	  * 点击删除ok按钮
	  */
	 public void clickDeteOkbtnInDetail(WebDriver driver)
	 {
		driver.findElement(By.className("messager-button")).findElement(
				By.className("l-btn")).findElement(By.className("l-btn-left")).findElement(By.tagName("span")).click();
	 }
	 
	 
	 /**
	  * 点击取消删除按钮
	  */
	 public void clickDeteCancelbtnInDetail(WebDriver driver)
	 {
		driver.findElement(By.className("messager-button")).findElement(
				By.className("l-btn-cancel")).findElement(By.className("l-btn-left")).findElement(By.tagName("span")).click();
	 }
}
