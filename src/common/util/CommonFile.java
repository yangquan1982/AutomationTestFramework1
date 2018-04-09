package common.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;

import common.constant.constparameter.ConstUrl;

public class CommonFile {

	public static long getTimestampOfFirstFile(String dir, final String filter) {
		final File root = new File(dir);
		long times = 0;
		long timeout = Long.parseLong(ConstUrl.ExportTimeout) / 1000;
		FilenameFilter fileFilter = null;
		if (StringUtils.isNotEmpty(filter)) {
			fileFilter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String filename) {
					return StringUtils.endsWithIgnoreCase(filename, filter);
				}
			};
		}

		File[] files = null;
		do {
			files = root.listFiles(fileFilter);
			Pause.pause(1000);
			times++;

			if (times > timeout) {

			}
		} while (files.length == 0);

		long timestamp = files[0].lastModified();
		files[0].delete();
		return timestamp;
	}

	/**
	 * 删除文件夹中所有內容
	 * 
	 * @function 〈删除文件夹中所有內容〉
	 * @description 〈删除文件夹中所有內容，保留文件夾自为空目录〉
	 * @param [String path] [文件夹路径]
	 * @return boolean,[删除成功返回true，失败返回false]
	 * @exception/throws [异常类型] [异常说明]
	 * @author g00308196
	 * @date 2015-2-10
	 */
	public static boolean cleanDirectory(String path) {
		try {
			org.apache.commons.io.FileUtils.cleanDirectory(new File(path));
			
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * 删除文件夹及文件夹中所有内容
	 * 
	 * @function 〈删除文件夹及文件夹中所有内容 〉
	 * @description 〈删除文件夹及文件夹中所有内容 〉
	 * @param [String folderPath] [路径]
	 * @return void,[返回类型说明]
	 * @exception/throws [异常类型] [异常说明]
	 */
	public static void deleteDirectory(String dir) {
		try {
			org.apache.commons.io.FileUtils.deleteDirectory(new File(dir));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void checkExistFiles(String dir, final String filter) {
		final File root = new File(dir);
		long times = 0;
		long timeout = Long.parseLong(ConstUrl.ExportTimeout) / 1000;
		FilenameFilter fileFilter = null;
		if (StringUtils.isNotEmpty(filter)) {
			fileFilter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String filename) {
					return StringUtils.endsWithIgnoreCase(filename, filter);
				}
			};
		}

		File[] files = null;
		do {
			files = root.listFiles(fileFilter);
			Pause.pause(1000);
			times++;

			if (times > timeout) {
				Assert.fail("下载文件超时！");
			}
		} while (files.length == 0);

		files[0].lastModified();
		// files[0].canExecute();
	}

	/**
	 * 
	 * @Description <br>
	 *              获取下载目录的第一个文件名称
	 * @author zwx320041
	 * @param dir
	 * @param filter
	 */
	public static String getDownLoadDirectoryFileName() {
		final File root = new File(ConstUrl.DownLoadPath);

		File[] files = null;

		files = root.listFiles();
		if (files.length > 0) {
			return files[0].getName();
		}
		return null;
	}

	public static String getDownLoadDirectoryFileName(String path) {
		final File root = new File(path);
		root.getName();
		if (root.isFile()) {
			return root.getName();
		}
		return null;
	}

	public static String getDownLoadDirectorygetAbsolutePath() {
		final File root = new File(ConstUrl.DownLoadPath);

		File[] files = null;

		files = root.listFiles();
		if (files.length > 0) {
			return files[0].getAbsolutePath();
		}
		return null;
	}

	public static void ChooseOneFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			Assert.fail("文件不存在:" + filePath);
		}
		Pause.pause(2000);

		keyPressString(filePath);
		CommonFile.keyPress(KeyEvent.VK_ENTER);
		boolean clickflage = ImageUtil.clickSysImage("Open.jpg");
		for (int i = 0; i < 3; i++) {
			if (!clickflage) {
				break;
			} else {
				clickflage = ImageUtil.clickSysImage("Open.jpg");
			}
		}
		if(clickflage==true){
			Assert.fail("文件选择失败,请查看截图");
		}

	}

	public static void keyPressString(String str) {

		try {
			Robot r = new Robot();
			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
			keyPressWithCtrl(r, KeyEvent.VK_A);
			Transferable tText = new StringSelection(str);
			clip.setContents(tText, null);
			keyPressWithCtrl(r, KeyEvent.VK_V);
			Pause.pause(300);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public static void keyPressWithCtrl(Robot r, int key) {

		r.keyPress(KeyEvent.VK_CONTROL);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_CONTROL);

		r.delay(100);

	}

	public static void clickAcceleratorKey(int code1, int code2) {
		try {
			Robot robot = new Robot();
			robot.keyPress(code1);
			robot.keyPress(code2);
			robot.keyRelease(code2);
			robot.keyRelease(code1);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void keyPress(int code1) {
		
		try {
			Robot robot = new Robot();
			robot.keyPress(code1);
			robot.keyRelease(code1);
			Pause.pause(300);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static String getText() {
		String text = null;
		Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable clipT = sysc.getContents(null);

		try {
			text = (String) clipT.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return text;
	}

	public static void ChooseOneFile2(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			Assert.fail("文件不存在:" + filePath);
		}
		boolean getjavaflage = ImageUtil.getImage("javafilename.jpg");
		boolean getjavaflage2 = ImageUtil.getImage("javafilename2.png");
		for (int i = 0; i < 10; i++) {
			if (getjavaflage == false && getjavaflage2 == false) {
				Pause.pause(2000);
				getjavaflage = ImageUtil.getImage("javafilename.jpg");
				getjavaflage2 = ImageUtil.getImage("javafilename2.png");
			} else {
				break;
			}
		}
		if (getjavaflage == false && getjavaflage2 == false) {
			Assert.fail("获取windows资源管理器的文件名坐标失败！");
		} else {
			if (getjavaflage) {
				ImageUtil.clickImage("javafilename.jpg", 160);
			} else {
				ImageUtil.clickImage("javafilename2.png", 160);
			}

		}
		keyPressString(filePath);
		CommonFile.keyPress(KeyEvent.VK_ENTER);
		Pause.pause(1000);
		if (ImageUtil.getImage("javafileopen.jpg")||ImageUtil.getImage("javafileopen2.png")) {
			CommonFile.keyPress(KeyEvent.VK_ENTER);
		}		
		boolean getwarming = ImageUtil.getImage("javafilewarning.png");
		boolean openfileend = ImageUtil.getImage("javafileopen.jpg");
		if (getwarming||openfileend) {
			Assert.fail("选择文件或者文件夹不存在！！\n文件路径:" + filePath);
		}

	}

	public static String fileReportMsg(String filePath) {

		return "文件对比失败 :" + "\\" + "\\" + ZIP.IP() + "\\"
				+ filePath.substring(0, 1).toLowerCase() + "$"
				+ filePath.substring(2);

	}

	public static String fileMsg(String filePath) {

		return "\\" + "\\" + ZIP.IP() + "\\"
				+ filePath.substring(0, 1).toLowerCase() + "$"
				+ filePath.substring(2);

	}
}
