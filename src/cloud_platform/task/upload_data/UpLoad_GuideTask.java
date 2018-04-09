package cloud_platform.task.upload_data;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.task.Task_KSKC;
import common.util.CommonJQ;
import common.util.ImageUtil;
import common.util.TestWebDriver;

public class UpLoad_GuideTask {
	
	public static String downloadFileName = "GENEXCloud平台LTE工参模板.xlsx";
	
	//网络数据  子数据类型选择
	private static void selectSubDataType(WebDriver driver,String subDataType2) {
		System.out.println("选择网络数据："+subDataType2 + "--");
		String js = "$(\"span[title='"+subDataType2+"']\").click();";
		TestWebDriver.executeScript(js);
		Task_KSKC.waitForMask(driver);
		System.out.println();
	}

	/**
	 * 数据上传选择制式
	 * @param NetworkType
	 * @param dataType
	 */
	public static void selectRat(WebDriver driver,String rat) {
		System.out.println("选择制式："+rat + "--");
		String js = "$('.ul_title .tab_set').find('a').filter(function(){return $(this).text()=='"
				+ rat + "'}).click();";
		CommonJQ.excuteJS(driver, js);
		Task_KSKC.waitForMask(driver);
	}

	/**
	 * 选择数据类型和数据子类型
	 * @param driver
	 * @param DataType
	 * @param subDataType
	 */
	public static void selectDataType(WebDriver driver,String DataType, String subDataType) {
		
		System.out.println("选择数据类型："+DataType + "--数据字类型"+subDataType);
		String script = "";
		
			script += "$('#left_nav').find('div').filter(function(){return $(this).text().trim()=='"
					+ DataType + "'})";
		if (!(StringUtils.containsIgnoreCase(DataType, "工程参数")||StringUtils.containsIgnoreCase(DataType, "Engineering Parameter"))) {
			script += ".nextAll().find('div').filter(function(){return $(this).text()=='"
					+ subDataType + "'})";
		}
		
		script += ".click();";
		CommonJQ.excuteJS(driver, script);
		Task_KSKC.waitForMask(driver);
	}
	
	/**
	 * 选择数据类型
	 * @param driver
	 * @param DataType
	 */
	public static void selectDataType(WebDriver driver,String DataType) 
	{
		System.out.println("选择数据类型："+DataType );
		String script = "$('#left_nav').find('div').filter(function(){return $(this).text().trim()=='"
					+ DataType + "'})";
		script += ".click();";
		CommonJQ.excuteJS(driver, script);
		Task_KSKC.waitForMask(driver);
	}
	

	/**
	 * 找界面工参位置
	 * @param driver
	 * @param rat
	 * @param downloadFormworkType
	 */
	public static void findFormWork(WebDriver driver, String rat,String downloadFormworkType) 
	{
		//选择工参模板下载
		String script ="$(\"div[class='tit']\").find(\"div\").filter(function(){return $(this).text().trim()=='工参模板下载' })";
		script += ".click();";
		CommonJQ.excuteJS(driver, script);
		Pause.pause(2000);
		LoadingPage.Loading(driver);
		
	    //选择模板
		String scriptRat = "$(\"div[class='tit']\").find(\"a\").filter(function(){return $(this).text().trim()=='"+rat+"模板下载' })";
	    scriptRat +=".click();";
	    System.out.print("选择模板："+scriptRat);
	    CommonJQ.excuteJS(driver, scriptRat);
	    LoadingPage.Loading(driver);
	    Pause.pause(2000);
	    
	    //选择模板中的类型
	    String scriptType = "$(\"li:contains('"+downloadFormworkType+"') > input\").click();";
	    System.out.print("选择模板中的类型："+scriptType);
	    CommonJQ.excuteJS(driver, scriptType);
	    LoadingPage.Loading(driver);
	    ImageUtil.clickImage("enter.jpg");
	   
	    
	}

	
	/**
	 * 找到下载的文件路径
	 * @param driver
	 */
	public static String findFile(WebDriver driver) {
		boolean b = ImageUtil.clickImage("openFilePath.jpg");
		if(!b){
			 ImageUtil.clickImage("download.jpg");
			 ImageUtil.clickImage("openFilePath.jpg");
		}
		ImageUtil.clickImage("findDownloadExcelPath.jpg");
		ImageUtil.clickImage("dd.jpg");
		DataUpload_baseTask.clickAcceleratorKey(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Pause.pause(1000);
		DataUpload_baseTask.clickAcceleratorKey(KeyEvent.VK_CONTROL,KeyEvent.VK_C);
		Pause.pause(3*1000);
		String text=DataUpload_baseTask.getText();
		return text;
	}

	/**
	 * 下载文件
	 * @param downloadExcelPath
	 * @param second
	 * @return
	 */
	public static String findDownloadFileName(String downloadExcelPath, int second) {
		File[] fileList = new File(downloadExcelPath).listFiles();
		
		for(int k = 0; k< second; k++)
		{
			if(fileList.length==0){
				Pause.pause(1000);
				fileList = new File(downloadExcelPath).listFiles();
			}else
			{	
				String fileDownload = "";
				System.out.println("下载后文件个数："+fileList.length);
				for(int i = 0;i< fileList.length;i++){
					downloadFileName = fileList[i].getName();
					fileDownload = downloadExcelPath+"\\"+fileList[i].getName();
					System.out.println("文件名为："+fileDownload);
				}
				return fileDownload;
			}
		}
		return null;
	}
	
	/**
	 * 获取文件的个数
	 * @param downloadExcelPath
	 * @return
	 */
	public static int getFormworkNum(String downloadExcelPath) {
		File[] fileList = new File(downloadExcelPath).listFiles();
		return fileList.length+1;
	}

	
	/**
	 * 删除文件
	 * @param downloadExcelPath
	 */
	public static void deleteFile(String downloadExcelPath) {
		File[] fileList = new File(downloadExcelPath).listFiles();
		System.out.print("文件的个数是"+fileList.length);
		for(int i=0;i<fileList.length;i++){
			fileList[i].delete();
		}
		
	}
	
	
	
}
