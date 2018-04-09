package common.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenLocation;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;

import common.constant.constparameter.ConstUrl;

public class ImageUtil {

	private static String getImagepath = "testcase.jpg";
	private static String inputString = "Zr123456";

	/**
	 * 随cloud系统中英文变化的图片
	 * @param imgPath
	 * @return
	 */
	public static boolean getImage(String imgPath) {
		if (ImageUtil.getImageCenter(imgPath) != null) {
			System.out.println(ZIP.NowTime()+"getImage succes:"+imgPath);
			return true;
		} else {
			System.out.println(ZIP.NowTime()+"getImage fail:"+imgPath);
			return false;
		}
	}
	/**
	 * 随操作系统中英文变化的图片
	 * @param imgPath
	 * @return
	 */
	public static boolean getSysImage(String imgPath) {
		if (ImageUtil.getSysImageCenter(imgPath) != null) {
			System.out.println(ZIP.NowTime()+"getSysImage succes:"+imgPath);
			return true;
		} else {
			System.out.println(ZIP.NowTime()+"getSysImage fail:"+imgPath);
			return false;
		}
	}
	/**
	 * 随cloud系统中英文变化的图片的点击操作
	 * @param imgPath
	 * @return
	 */
	public static boolean clickImage(String imgPath) {
		ScreenLocation sl = ImageUtil.getImageCenter(imgPath);
		System.out.println(ZIP.NowTime()+"图片的坐标:"+sl);
		if (sl != null) {
			ImageUtil.clickScreenLocation(sl.getX(), sl.getY());
			System.out.println(ZIP.NowTime()+"clickImage succes:"+imgPath);
			return true;
		} else {
			System.out.println(ZIP.NowTime()+"clickImage fail:"+imgPath);
			return false;
		}
	}
	/**
	 * 随操作系统中英文变化的图片的点击操作
	 * @param imgPath
	 * @return
	 */
	public static boolean clickSysImage(String imgPath) {
		ScreenLocation sl = ImageUtil.getSysImageCenter(imgPath);
		System.out.println(ZIP.NowTime()+"图片的坐标:"+sl);
		if (sl != null) {
			ImageUtil.clickScreenLocation(sl.getX(), sl.getY());
			System.out.println(ZIP.NowTime()+"clickSysImage succes:"+imgPath);
			return true;
		} else {
			System.out.println(ZIP.NowTime()+"clickSysImage fail:"+imgPath);
			return false;
		}
	}	
	/**
	 * 判断Cloud系统中英文，点击图片
	 * @param imgPath
	 * @return
	 */
	public static boolean clickDetailImage(String imgPath) {
		ScreenLocation sl = ImageUtil.getImageCenter(imgPath);
		System.out.println(ZIP.NowTime()+"图片的坐标:"+sl);
		if (sl != null) {
			ImageUtil.clickScreenLocation(sl.getX(), sl.getY());
			ImageUtil.clickScreenLocation(sl.getX() + 0, sl.getY() + 50);
			System.out.println(ZIP.NowTime()+"clickDetailImage succes:"+imgPath);
			return true;
		} else {
			System.out.println(ZIP.NowTime()+"clickDetailImage fail:"+imgPath);
			return false;
		}
	}
	
	/**
	 * 判断Cloud系统中英文，查找图片
	 * @param imgPath
	 * @return
	 */
	public static boolean findImage(String imgPath) {
		ScreenLocation sl = ImageUtil.getImageCenter(imgPath);
		System.out.println(ZIP.NowTime()+"图片的坐标:"+sl);
		if (sl != null) {
			System.out.println(ZIP.NowTime()+"find Image succes:"+imgPath);
			return true;
		} else {
			System.out.println(ZIP.NowTime()+"find Image fail:"+imgPath);
			return false;
		}
	}
	/**
	 * 判断Cloud系统中英文，等待图片加载
	 * @param imgPath
	 * @return
	 */
	public static void waitImage(String imgPath) {
		//等待时间  20秒
		int timeOut = 20;
		boolean isExistImage = false;
		for (int i = 0; i < timeOut; i++) {
			if(ImageUtil.getImage(imgPath)){
				isExistImage = true;
				return;
			}
			Pause.pause(1000);
		}
		if(!isExistImage){
			Assert.fail("获取图片失败！---"+translate()+imgPath);
		}
		
	}
	/**
	 * 判断Cloud系统中英文，偏移点击操作
	 * @param imgPath
	 * @return
	 */
	public static boolean clickImage(String imgPath,int M) {
		ScreenLocation sl = ImageUtil.getImageCenter(imgPath);
		if (sl != null) {
			ImageUtil.clickScreenLocation(sl.getX()+M, sl.getY());
			System.out.println(ZIP.NowTime()+"clickImage succes:"+imgPath);
			return true;
		} else {
			System.out.println(ZIP.NowTime()+"clickImage fail:"+imgPath);
			return false;
		}
	}
	/**
	 * 判断Cloud系统中英文，偏移点击操作
	 * @return
	 */
	public static boolean clickImage(String imgPath,int x,int y) {
		ScreenLocation sl = ImageUtil.getImageCenter(imgPath);
		if (sl != null) {
			ImageUtil.clickScreenLocation(sl.getX()+x, sl.getY()+y);
			System.out.println(ZIP.NowTime()+"clickImage succes:"+imgPath);
			return true;
		} else {
			System.out.println(ZIP.NowTime()+"clickImage fail:"+imgPath);
			return false;
		}
	}
	/**
	 * 判断Cloud系统中英文，输入内容
	 * @return
	 */
	public static boolean inputString(String imgPath, String info) {
		ScreenLocation sl = ImageUtil.getImageCenter(imgPath);
		if (sl != null) {
			ImageUtil.clickScreenLocation(sl.getX() + 20, sl.getY());
			ImageUtil.inputString(info);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断Cloud系统中英文，拼接图片识别路径
	 * @return
	 */
	private static String translate() {
		String ImageHome;
		if ("CN".equalsIgnoreCase(ConstUrl.language)) {
			ImageHome = ConstUrl.getProjectHomePath() + "\\setting\\image\\CN\\";
		} else {
			ImageHome = ConstUrl.getProjectHomePath() + "\\setting\\image\\EN\\";
		}
		return ImageHome;
	}

	/**
	 * 判断当前操作系统中英文，拼接图片识别路径
	 * @return
	 */
	private static String sysTranslate() {
		String ImageHome;
		String SysLanguage=null;
		try {
			SysLanguage = Locale.getDefault().getLanguage();
		} catch (Exception e) {

		} 
		if ("en".equalsIgnoreCase(SysLanguage)==false) {
			ImageHome = ConstUrl.getProjectHomePath() + "\\setting\\image\\CN\\";
		} else {
			ImageHome = ConstUrl.getProjectHomePath() + "\\setting\\image\\EN\\";
		}
		return ImageHome;
	}
	/**
	 *  判断Cloud系统中英文，获取图片
	 * @param imgPath
	 * @return
	 */
	private static ScreenLocation getImageCenter(String imgPath) {
		imgPath = translate() + imgPath;
		File img = new File(imgPath);
		if(!img.isFile()){
			Assert.fail("file not find:"+imgPath);
		}
		ScreenRegion s = new DesktopScreenRegion();
		Target imageTarget = null;
		try {
			imageTarget = new ImageTarget(new URL("file:///" + imgPath));
			ScreenRegion r =s.find(imageTarget) ;
			if(r!=null) {
				return r.getCenter();
			}
			else{
				imageTarget = null;
				s = null;
				return null;
			}
			
			
		} catch (MalformedURLException e) {
			return null;
		} catch (NullPointerException ex) {
			return null;
		}
	}
	
	/**
	 * 判断当前操作系统中英文，获取图片
	 * @param imgPath
	 * @return
	 */
	private static ScreenLocation getSysImageCenter(String imgPath) {
		imgPath = sysTranslate() + imgPath;
		File img = new File(imgPath);
		if(!img.isFile()){
			Assert.fail("file not find:"+imgPath);
		}
		ScreenRegion s = new DesktopScreenRegion();
		Target imageTarget = null;
		try {
			imageTarget = new ImageTarget(new URL("file:///" + imgPath));
			ScreenRegion r =s.find(imageTarget) ;
			if(r!=null) {
				return r.getCenter();
			}
			else{
				imageTarget = null;
				s = null;
				return null;
			}
			
			
		} catch (MalformedURLException e) {
			return null;
		} catch (NullPointerException ex) {
			return null;
		}
	}


	/**
	 *  判断Cloud系统中英文，获取图片数量个数
	 * @param imgPath
	 * @return
	 */
	public static int getImageNum(String imgPath) {
		imgPath = translate() + imgPath;
		File img = new File(imgPath);
		if(!img.isFile()){
			Assert.fail("file not find:"+imgPath);
		}
		ScreenRegion s = new DesktopScreenRegion();
		Target imageTarget = null;
		try {
			imageTarget = new ImageTarget(new URL("file:///" + imgPath));
			List<ScreenRegion> r = s.findAll(imageTarget);
			return r.size();			
		} catch (MalformedURLException e) {
			return 0;
		} catch (NullPointerException ex) {
			return 0;
		}
	}

	/**
	 * 根据图片位置，偏移（x,y）做点击操作
	 * @param x
	 * @param y
	 */
	private static void clickScreenLocation(int x, int y) {
		Robot robot;
		try {
			robot = new Robot();
			robot.mouseMove(x, y);
			robot.delay(300);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.delay(10);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param info
	 */
	private static void inputString(String info) {
		try {
			Robot robot = new Robot();
			if (info != null) {
				for (char c : info.toCharArray()) {
					if ((c >= 97 && c <= 122)) {
						robot.keyPress(c - 32);
						robot.keyRelease(c - 32);
					} else if ((c >= 65 && c <= 90)) {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(c);
						robot.keyRelease(c);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					} else if ((c >= 48 && c <= 57)) {
						robot.keyPress(c);
						robot.keyRelease(c);
					} else if (c == '/') {
						robot.keyPress(KeyEvent.VK_SLASH);
						robot.keyRelease(KeyEvent.VK_SLASH);
					} else if (c == ':') {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_SEMICOLON);
						robot.keyRelease(KeyEvent.VK_SEMICOLON);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					} else if (c == ' ') {
						robot.keyPress(KeyEvent.VK_SPACE);
						robot.keyRelease(KeyEvent.VK_SPACE);
					} else if (c == '_') {
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_SHIFT);
					} else if (c == '.') {
						robot.keyPress(KeyEvent.VK_PERIOD);
						robot.keyRelease(KeyEvent.VK_PERIOD);
					}
				}
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(inputString(getImagepath, inputString));
	}
}
