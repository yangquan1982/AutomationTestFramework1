package cloud_platform.page;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cloud_public.page.LoadingPage;
import common.util.LOG;

import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.ImageUtil;

public class UpDataPage {
	/**
	 * 选择制式
	 * @param driver
	 * @param rat
	 */
	public static void selectRat(WebDriver driver,String rat) {
		LOG.info_aw("====选择制式："+rat + "====");		
		String defaultRat = "return $('.ul_title .tab_set').find('.tab-li.tab-click').text().trim()";
		if(CommonJQ.excuteJStoString(driver, defaultRat).equalsIgnoreCase(rat)){
			return ;
		}
		String js = "$('.ul_title .tab_set').find('a').filter(function(){return $(this).text()=='"
				+ rat + "'}).click();";
		CommonJQ.excuteJS(driver, js);
		UpDataPage.waitForMask(driver);
	}
	
	// 等待页面信息加载 ，公共方法
	public static void waitForMask(WebDriver driver) {
		final int minute = 10;
		final int timeout = minute * 60 * 1000;
		int times = 0;
		boolean iswaitMask = CommonJQ.excuteJStoBoolean(driver,
				"return $('#my_mask_layer').is(':visible');");
		while (iswaitMask) {
			Pause.pause(100);
			times++;
			Assert.assertTrue("页面等待超时" + minute + "分钟", times < timeout / 100);
			iswaitMask = CommonJQ.excuteJStoBoolean(driver,
					"return $('#my_mask_layer').is(':visible');");
		}						
		
		boolean iswaitMask2 = CommonJQ.excuteJStoBoolean(driver,
				"return $('#mask_layer').is(':visible');");
		int times2 = 0;
		while (iswaitMask2) {
			Pause.pause(100);
			times2++;
			Assert.assertTrue("页面等待超时" + minute + "分钟", times2 < timeout / 100);
			iswaitMask2 = CommonJQ.excuteJStoBoolean(driver,
					"return $('#mask_layer').is(':visible');");
		}
		
		LoadingPage.Loading(driver);
	}
	
	
	
	//
	public static void selectDataType(WebDriver driver, String DataType,
			String subDataType) {
		LOG.info_aw("====选择数据类型："+DataType+"_" +subDataType+ "====");
		String script = "";
		script += "$('#left_nav').find('div').filter(function(){return $(this).text().trim()=='"+DataType+"'})";
		if (!(StringUtils.containsIgnoreCase(DataType, "工程参数") || StringUtils
				.containsIgnoreCase(DataType, "路测数据"))) {
			script += ".nextAll().find('div').filter(function(){return $(this).text().trim()=='"+subDataType+"'})";
		}
		String existDatatype = "return " + script + ".length;";
		if (CommonJQ.excuteJStoInt(driver, existDatatype) == 0) {
			Assert.fail("左侧树-" + DataType + "-下不存在-" + subDataType + "!!!");
		}
		script += ".click();";
		CommonJQ.excuteJS(driver, script);
		waitForMask(driver);
		Pause.pause(2000);
		
	}
	
	/**
	 * 选择数据类型 并展开
	 * @param driver
	 * @param DataType
	 */
	public static void selectDataType(WebDriver driver, String DataType) {		
		String script = "$('#left_nav').find('div').filter(function(){return $(this).text().trim()=='"+DataType+"'})";
		String script1 = script+".click();";
		String script2 = "return "+script+".attr('class')";
		CommonJQ.excuteJS(driver, script1);
		waitForMask(driver);
		String result = CommonJQ.excuteJStoString(driver, script2);
		if(!StringUtils.containsIgnoreCase(result, "open")){
			CommonJQ.excuteJS(driver, script1);
			waitForMask(driver);
		}		
	}

	
	private static final String BtnCreateFile = "#createFile";
	private static final String TextAddFile = "#addclusterName";
	private static final String BtnSubmit = "#addClusterSub";
	
	
	/**
	 * 判断文件夹是否存在
	 * @param driver
	 * @param folderName
	 * @return
	 */
	public static boolean isExsistFolder(WebDriver driver, String folderName){
		boolean flag = CommonJQ.isExisted(driver, "span[title=\"" + folderName + "\"]");
		if (!flag) {
			searchFolderByName(driver, folderName);
		}		
		return CommonJQ.isExisted(driver, "span[title=\"" + folderName + "\"]");
	}
	
	private static final String fileterTit = "#fileterTit";
	private static final String fileter = "#fileter";
	private static final String clusterName = "input[name=\"clusterName\"]";
	private static final String shcBtn = "#shcBtn span";
	/**
	 * 按照文件名称搜索
	 * @param driver
	 * @param folderName
	 */
	public static void searchFolderByName(WebDriver driver, String folderName){
		if(CommonJQ.isExisted(driver, fileter)){
			CommonJQ.click(driver, fileter, true);
		}else{
			CommonJQ.click(driver, fileterTit, true);
		}		
		UpDataPage.waitForMask(driver);
		CommonJQ.value(driver, clusterName, folderName);
		UpDataPage.waitForMask(driver);
		CommonJQ.click(driver, shcBtn, true);
		UpDataPage.waitForMask(driver);		
	}
	// 创建新的文件夹
	public static void createNewFolder(WebDriver driver, String folderName) {		
		CommonJQ.click(driver, BtnCreateFile, true);
		Pause.pause(1000);
		CommonJQ.value(driver, TextAddFile, folderName, true);
		CommonJQ.click(driver, BtnSubmit, true);
		UpDataPage.waitForMask(driver);
	}
	
	public static void clickFolderName(WebDriver driver, String folderName) {		
		CommonJQ.click(driver, "span[title=\"" + folderName + "\"]", true);
	}
	
	private static final String BtnUploadData = "#uploadDataBtnText";
	//点击上传任务按钮
	public static void clickAddUpLoadTaskBTN(WebDriver driver) {
		CommonJQ.click(driver, BtnUploadData, true, "点击添加上传任务");
	}
	
	
	private static final String CBoxCloseUploadWin = "#isCloseUploadWindow";
	
	//设置是否关闭上传结束关闭页面
	public static void clickClosePageInSuccessed(WebDriver driver) {
		CommonJQ.click(driver, CBoxCloseUploadWin, true);
	}
	
	public static boolean isCloseWindowsChecked(WebDriver driver){
		//WebElement we=driver.findElement(By.id("isCloseUploadWindow"));;
		return CommonJQ.isChecked(driver, CBoxCloseUploadWin);
	}
	
	private static String textdataTopeType = "dataTopeType";
	public static void selectDateType(WebDriver driver, String dataType){
		if(checkSetValueSame(driver, textdataTopeType, dataType)) return;
		setType(driver, textdataTopeType, dataType);
	}
	
	public static boolean checkDataTypeValueSame(WebDriver driver, String dataType){
		return checkSetValueSame(driver, textdataTopeType, dataType);
	}
	
	private static String SubDateTypeID = "dataSubType";
	public static void selectSubDateType(WebDriver driver, String dataType){
		if(checkSetValueSame(driver, SubDateTypeID, dataType)) return;
		setType(driver, SubDateTypeID, dataType);
	}
	
	public static boolean checkSubDataTypeValueSame(WebDriver driver, String dataType){
		return checkSetValueSame(driver, SubDateTypeID, dataType);
	}
	
	private static String textpciID = "pci";
	public static void selectFeatureOfPCI(WebDriver driver, String dataType){
		if(checkSetValueSame(driver, textpciID, dataType)) return;
		setType(driver, textpciID, dataType);
	}
	
	private static String textfeatureID = "feature";
	/**
	 * 选择特性
	 * @param driver
	 * @param dataType
	 */
	public static void selectFeature(WebDriver driver, String dataType){
		if(checkSetValueSame(driver, textfeatureID, dataType)) return;
		setType(driver, textfeatureID, dataType);
	}
	
	//公参选择下拉框
	private static String engineerParamFeaturelistindex = "#ext-gen1023";
	private static String engineerParamFeaturelists = "#boundlist-1010-listEl";
	/**
	 * 公参特性选择
	 * @param driver
	 * @param dataType
	 */
	public static void engineerParamFeatureSelect(WebDriver driver, String featurename){
		if(StringUtils.equalsIgnoreCase(featurename, "默认")){
			return;
		}
		CommonJQ.click(driver, engineerParamFeaturelistindex, true, "共参特性选择下拉框");
		CommonJQ.waitForElementVisble(driver, engineerParamFeaturelists);
		String js1 = "$('#boundlist-1010-listEl').find('div').filter(function(){return $(this).text()=='"+ featurename + "'})";
		String jslength = "return "+js1+".length;";
		int length = CommonJQ.excuteJStoInt(driver, jslength);
		if(length!=1) Assert.fail("当期那环境工参数据无["+featurename+"]可选择!");
		String jsclick = js1+".find('input').click();";
		CommonJQ.excuteJS(driver, jsclick);			
		CommonJQ.click(driver, engineerParamFeaturelistindex, true, "共参特性选择下拉框");
		Pause.pause(1000);		
	}
	
	public static boolean checkSetValueSame(WebDriver driver,String id,String type){		
		String script = "return $('#text"+id+"').val()";
		String val = CommonJQ.excuteJStoString(driver, script);
		if(StringUtils.containsIgnoreCase(val, type)){
			return true;
		}
		return false;
	}
	public static void setType(WebDriver driver,String id,String type){
		LOG.info_aw("====选择特性：" + type + "====");
		String script = "$('#text"+id+"').click();";
		CommonJQ.excuteJS(driver, script);
		//driver.findElement(By.id("text"+id)).click();
		Pause.pause(1500);
		List<WebElement > wes=driver.findElement(By.id("mshow"+id)).findElements(By.tagName("li"));
		for(WebElement we:wes){
			if(we.getText().equalsIgnoreCase(type))
			{
				we.click();Pause.pause(1000);
			}	
		}
	}

	public static void clickUploadGuidBTN(WebDriver driver) {
		// 点击创建任务按钮
		LOG.info_aw("--打开上传指南！！");
		Set<String> Uploadhandles = driver.getWindowHandles();
		String js = "$('#uploadAppletParent').find('a').filter(function(){return $(this).text()=='"+DataCenterConst.UpLoadGuide+"'}).click();";
		CommonJQ.setTimeout(driver, js, 3000);
		Set<String> UploadGuidehandles = CommonWD.getAllHandle(driver, 3);
		String UploadGuidehandle = null;
		for (String s : UploadGuidehandles) {
			if (Uploadhandles.contains(s)) {
				continue;
			} else {
				UploadGuidehandle = s;
				break;
			}
		}
		if (UploadGuidehandle == null) {
			Assert.fail("打开上传指南失败！！");
		}
		driver.switchTo().window(UploadGuidehandle);
		LoadingPage.Loading(driver);
		
	}

	/**
	 * 关闭leavepage图片
	 */
	public static void closeleavePage() {
		Pause.pause(500);
		if(ImageUtil.getImage("leavepage.jpg")){
			ImageUtil.clickImage("leavepage.jpg");
		}
		return;
		
	}
	
	
	/**
	 * 点击确定按钮图片
	 */
	public static void closeConfirm() {
		ImageUtil.clickImage("enter.jpg");
		Pause.pause(300);
		//工参上传异常界面关闭处理
		if(ImageUtil.getImage("ParamterCheck.jpg")){
			CommonFile.clickAcceleratorKey(KeyEvent.VK_ALT, KeyEvent.VK_F4);
			Pause.pause(300);
			ImageUtil.clickImage("enter.jpg");
		}
		Pause.pause(200);
	}

	/**
	 * 获取当前页显示的数据个数
	 * @param driver
	 * @return
	 */
	public static String getCurrentPageDataNum(WebDriver driver) {
		String script = "return $('div#page_data').find('em').text().trim()";
		return CommonJQ.excuteJStoString(driver, script);
	}

	/**
	 * 获取当前页面上传文件内容个数，按照名称获取
	 * @param driver
	 * @param dataname
	 * @return
	 */
	public static int getUpLoadDataNumByName(WebDriver driver, String dataname) {
		String script = "return $('.x-grid-cell-inner').find('span').filter(function(){return $(this).text().trim()=='"+dataname+"'}).length";
		return CommonJQ.excuteJStoInt(driver, script);
	}
	//模糊匹配
	public static int getUpLoadDataNumByName2(WebDriver driver, String dataname) {
		String script = "return $('.x-grid-cell-inner').find('span:contains("+dataname+")').length";
		return CommonJQ.excuteJStoInt(driver, script);
	}

	public static boolean isUpDataPageError(WebDriver driver) {
		String script = "return document.getElementsByClassName('sys-err-info').length";
		int num = CommonJQ.excuteJStoInt(driver, script);
		if(num>0){
			return true;
		}
		return false;
	}

	public static String getUpLoadPageErrorInfor(WebDriver driver) {
		String script ="return $('.sys-err-info').innerHTML;";
		Object obj = ((JavascriptExecutor) driver).executeScript(script);
		if(obj!=null){
			return obj.toString();
		}
		return null;
	}
	
	/**
	 * 点击工参名称工参预览按钮
	 * @param driver
	 * @param dataname
	 */
	public static void clickParamPreView(WebDriver driver,String dataname){
		String script ="$('#dataManagerGrid').find('span').filter(function(){return $(this).text().trim()=='"+dataname+"'}).parents('tr:first').find('a.echoA_Submit').get(0).click();";
		CommonJQ.setTimeout(driver, script, 2000);
	}
	
	
	public static void clickParamPreView(WebDriver driver,int index){
		String script ="$('.echoA_Submit:visible').get("+String.valueOf(index)+").click();";
		CommonJQ.setTimeout(driver, script, 2000);
	}
	
	public static int getParamPreViewNum(WebDriver driver){
		return CommonJQ.length(driver, ".echoA_Submit", true);
	}
	public static void checkParamViewData(WebDriver driver){
		String text =CommonJQ.text(driver, "tbody", "", true);
		Assert.assertFalse("工参预览界面为空！", StringUtils.isBlank(text));
	}
	/**
	 * 获取属性元素的索引
	 * @param driver
	 * @param attrname
	 * @return
	 */
	public static String getAttrIndex(WebDriver driver,String attrname){
		String script ="return $('#dataManagerGrid').find('span:contains("+attrname+")').parents('.x-column-header.x-column-header-align-left.x-box-item.x-column-header-default.x-unselectable').index()";
		return CommonJQ.excuteJStoString(driver, script);
	}
	/**
	 * 按照数据名称获取对应属性值
	 * @param driver
	 * @param dataname
	 * @param index
	 * @return
	 */
	public static String getAttrVaule(WebDriver driver,String dataname,String index){
		String script = "$('#dataManagerGrid').find('span').filter(function(){return $(this).text().trim()=='"+dataname+"'}).parents('tr:first').find('td').eq("+index+").text().trim()";
		return CommonJQ.excuteJStoString(driver, script);
	}
	
	
	private static String ConfirmBTNID = "#confirm>span";
	/**
	 * 单验多种数据类型点击确定
	 * @param driver
	 */
	public static void clickConfirmBTN(WebDriver driver){
		CommonJQ.click(driver, ConfirmBTNID, true, "单验多种数据类型点击确定");
	}

	//关闭选择本地资源 的资源管理器
	public static void closeSelectFilePage() {
		boolean getwarming = ImageUtil.getImage("javafilewarning.png");
		boolean openfileend = ImageUtil.getImage("javafileopen.jpg");
		if(getwarming||openfileend){
			ImageUtil.clickImage("javafileworningconfirmBTN.png");
			Pause.pause(300);
			ImageUtil.clickImage("javafilecancel.png");
			Pause.pause(300);
		}
	}

	public static boolean isErrorPage(WebDriver driver) {
		return CommonJQ.isExisted(driver, ".sys-error-main", true);
	}
	


}
