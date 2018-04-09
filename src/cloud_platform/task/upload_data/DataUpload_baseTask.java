package cloud_platform.task.upload_data;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cloud_public.page.LoadingPage;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.ImageUtil;
import common.util.LOG;

public class DataUpload_baseTask {

	/**
	 * 切换项目
	 * @param driver
	 * @param projectName
	 */
	public static void changeProject(WebDriver driver, String projectName) {
		System.out.print("切换项目");
		String projectNameText = CommonJQ.excuteJStoString(driver,"return $('#text').text()");
		if(!projectNameText.contains(projectName)){
			WebElement programChange=driver.findElement(By.id("projectButton"));
			programChange.click();
			WebElement programContent = driver.findElement(By.id("searchText"));
			programContent.sendKeys(projectName);
			CommonJQ.excuteJS(driver, "$(\"#projectListul li[title='"+projectName.trim()+"']\").click();");
			LoadingPage.Loading(driver);			
		}
		
	}
	
	/**
	 * 选择数据类型
	 * @param driver
	 * @param DataType
	 * @param subDataType
	 */
	public static void selectDataType(WebDriver driver,String DataType, String subDataType)
	{
		String script = "return $(\"div[pageid='noEquipmentView']\").text()";
		String returnStr = CommonJQ.excuteJStoString(driver, script);
		if(returnStr.contains(DataType)){
			String scriptStr = "$(\"div[pageid='noEquipmentView']\").click()";
			CommonJQ.excuteJS(driver, scriptStr);
		}
		String subScript = "$(\"ul li[pageid='emap'] div\").click()";
		CommonJQ.excuteJS(driver, subScript);
	}

	
	/**
	 * 添加任务
	 * @param driver
	 * @param dataPath
	 */
	public static void addUploadTask(WebDriver driver, String dataPath) {
		
		boolean OpenFileIconflage = ImageUtil.clickImage("Btn_OpenFileIcon.jpg");
		if(OpenFileIconflage){
			CommonFile.ChooseOneFile2(dataPath);
		}
		//点击开始按钮，上传数据
		ImageUtil.clickImage("Btn_StartIcon.jpg");
		LoadingPage.Loading(driver);
		Pause.pause(6000);
	}
	
	
	public static void clickAcceleratorKey(int code1, int code2) {
		try {
			Pause.pause(500);
			Robot robot = new Robot();
			robot.keyPress(code1);
			robot.keyPress(code2);
			robot.keyRelease(code2);
			robot.keyRelease(code1);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取剪贴板中的内容
	 * @return
	 */
	public  static String getText(){
		String text=null;
		LOG.info_aw("从剪贴板中得到文本");
		Clipboard sysc=Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable clipT=sysc.getContents(null);
		
		try {
			text = (String) clipT.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	 * 关闭leavepage图片
	 */
	public static void closeleavePage() {
		ImageUtil.clickImage("leavepage.jpg");
	}
	
	
	public static void assertUploadOk(WebDriver driver,int seconds, String defaultWindowID ){

		for( int i = 0; i < seconds; i++)
		{ 
			Pause.pause(1000);
			boolean b =findImage("upload_ok.jpg","upload.jpg");
			if(b){
				return;
			}
		}
		Assert.fail("上传失败");
 }

	private static boolean findImage(String image,String image2) {
		boolean uploadOK = ImageUtil.findImage(image);
//		boolean b = ImageUtil.findImage(image2);
		if(uploadOK){
			return true;
		}
		return false;
	}

}
