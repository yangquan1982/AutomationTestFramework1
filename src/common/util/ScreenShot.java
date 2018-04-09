package common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenShot {

	private final static  String SHUTPATH= "/ScreenShot/";
	private final static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * <b>Description:</b>��ͼ
	*
	* @author lwx242612
	* @since 2016��6��15��
	* @param drivername
	* @param fileName
	* @return  void
	 */
	public static String snapshot(WebDriver driver, String fileName) {
		String currentPath = System.getProperty("user.dir");
		String savePath = currentPath + SHUTPATH +format.format(new Date(System.currentTimeMillis()))+"_"+ fileName+".png";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(savePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  savePath;
	}
}
