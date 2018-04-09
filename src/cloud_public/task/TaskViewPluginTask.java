package cloud_public.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class TaskViewPluginTask {

	/**
	 * <b>Description:</b>显示任务详情
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param taskname
	 * @return
	 * @return String 返回当前窗口ID
	 */
	public static String viewTask(WebDriver driver, String taskname) {

		String nowWinID = "";
		String taskid ="";
		Set<String> handles_0 = driver.getWindowHandles();
		if(CommonJQ.isExisted(driver, TaskReportPage.CreateTask, true)){
			 taskid = TaskReportTask.searchTask(driver, taskname);
				if("".equalsIgnoreCase(taskid)){
					Assert.fail("关键字搜索未查找到任务，关键字："+taskname);
				}else{
					CommonJQ.click(driver, "#" + taskid, true);	
				}
		}else{
			 taskid = TaskReportTask.NewSearchTask(driver, taskname);
				if(("".equalsIgnoreCase(taskid))||("test".equalsIgnoreCase(taskid))){
					Assert.fail("关键字搜索未查找到任务，关键字："+taskname);
				}else{
					CommonJQ.click_ByText(driver, "a", taskid, true, "关键字搜索未查找到任务，关键字："+taskid);
				}
		}

		LoadingPage.Loading(driver);
		Set<String> handles_1 = driver.getWindowHandles();

		for (int i = 0; i < 10; i++) {
			if (handles_0.size() != handles_1.size()) {
				System.out.println("open model window success.");
				break;
			} else {
				Pause.pause(1000);
				handles_1 = driver.getWindowHandles();
				continue;
			}
		}
		if (handles_0.size() == handles_1.size()) {
			Assert.fail("open model window fail");
		}
		for (String s : handles_1) {
			if (handles_0.contains(s)) {
				continue;
			} else {
				driver.switchTo().window(s);
				nowWinID = s;
				System.out.println("switch to window[" + s + "] successfully!");
				break;
			}
		}
		return nowWinID;

	}

	/**
	 * <b>Description:</b>等待报告下载完成
	 * 
	 * @author sWX198085
	 * @param srcPath
	 * @param starts
	 * @return void
	 */
	public static String waittingDownLoadFile(String srcPath, String startsWith) {
		File result = null;
		long fileSize = 0;
		String fileName = "";
		boolean zipPartFlage = false;
		for (int i = 0; i < 60; i++) {
			System.out.println(ZIP.NowTime()
					+ "wait 1s DownLoadFile,startsWith:" + startsWith);
			zipPartFlage = false;
			Pause.pause(1000);
			File[] child = new File(srcPath).listFiles();
			int length = child.length;
			for (int j = 0; j < length && zipPartFlage == false; j++) {
				if ((child[j].getName().endsWith(".part"))
						&& (child[j].getName().startsWith(startsWith))) {
					zipPartFlage = true;
				}
			}
			if (zipPartFlage == false) {
				break;
			}

		}
		for (int i = 0; i < 3; i++) {
			for (File child : new File(srcPath).listFiles()) {
				if (child.isDirectory()) {
					continue;
				} else if (child.getName().startsWith("~$")) {
					continue;
				} else if (child.getName().endsWith(".part")) {
					continue;
				} else if (((child.getName().startsWith(startsWith)))
						&& ((child.getName().endsWith(".zip.part")) == false)) {
					try {
						fileName = child.getName();
						fileSize = child.length();
						System.out.println("fileSize:" + fileSize);
					} catch (Exception e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
					}
					if (fileSize > 22) {
						System.out.println(ZIP.NowTime()+ "fileName:"+fileName);
						return fileName;
					} else {
						Assert.fail("fileName:" + fileName
								+ ",文件小于等于22 bytes,下载内容存在异常");
					}
				} else {
					continue;
				}
			}
			System.out.println(ZIP.NowTime()
					+ "waitting DownLoad File,waitting 1s");
			Pause.pause(1000);
		}
		if (result == null) {
			Assert.assertTrue("startsWith:" + startsWith
					+ "not find file,srcPath:" + srcPath, false);
		}
		System.out.println(ZIP.NowTime()+ "fileName:"+fileName);
		return fileName;

	}

	/**
	 * 专家求助
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param HelpType
	 *            ：处理类型：远程交付任务、任务执行失败等
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附件路径
	 */
	public static void expertHelp(WebDriver driver, String taskName,
			String HelpType, String detailedDescription, String Filepath) {

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(
				driver,
				"li.expertHelpDetailLi  div[class=\"zz_select msel\"] input[type=\"text\"]",
				true, "专家求助:求助标题 下拉框");
		CommonJQ.click(driver, "ul#mshowflowTitle li#" + HelpType, true,
				"专家求助:求助标题 ");

		CommonJQ.value(driver,
				"textarea[class=\"expertHelpTextArea ng-pristine ng-valid\"]",
				detailedDescription, true, "专家求助:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#userfilePick", true, "专家求助:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		CommonJQ.click(driver,
				"div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]",
				true, "专家求助:提交按钮");
		LoadingPage.Loading(driver);
		String AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
		String ExMessage = "专家分析中...";

		Assert.assertTrue("专家求助界面，期望值：" + ExMessage + "，实际值：" + AcMessage,
				ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 专家处理
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param ProcType
	 *            ：处理类型：请用户追加数据、问题分析完成
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附件路径
	 */
	public static void expertProc(WebDriver driver, String taskName,
			String ProcType, String detailedDescription, String Filepath) {

		expertProc( driver,  taskName,
				false, null, ProcType,  detailedDescription,  Filepath);
	}
	/**
	 * 
	 * @param driver
	 * @param taskName：任务名
	 * @param DownFlage：是否下载附件
	 * @param fileName：需要下载的附件名称
	 * @param ProcType：处理类型：请用户追加数据、问题分析完成
	 * @param detailedDescription：详细描述
	 * @param Filepath：附件路径
	 */
	public static void expertProc(WebDriver driver, String taskName,
			boolean DownFlage,String fileName,String ProcType, String detailedDescription, String Filepath) {

		TaskViewPluginTask.viewTask(driver, taskName);
		
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
        if(DownFlage){
    		CommonJQ.click(driver, ".expertHelpDetailLabelSpan a", true,
    				"专家反馈:附件下载");
    		TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,
    				fileName);	
        }
		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#addDataReply", true, "专家反馈:请用户追加数据");
		} else {
			CommonJQ.click(driver, "#finishReply", true, "专家反馈:问题分析完成");
		}

		CommonJQ.value(driver,
				"textarea[class=\"expertHelpTextArea ng-pristine ng-valid\"]",
				detailedDescription, true, "专家反馈:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#expertReplyFilePick", true,
					"专家反馈:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		CommonJQ.click(driver,
				"div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]",
				true, "专家反馈:提交按钮");
		LoadingPage.Loading(driver);
		String AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
		String ExMessage = "";
		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			ExMessage = "用户追加数据中...";
		} else {
			ExMessage = "用户确认结果中...";
		}

		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage,
				ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}
	/**
	 * 专家求助：获取附件
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param Filepath
	 *            ： 附件名
	 */
	public static void expertGetFile(WebDriver driver, String taskName,
			String fileName) {
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, ".expertHelpDetailLabelSpan a", true,
				"专家反馈:附件下载");
		TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,
				fileName);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 用户确认
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param ProcType
	 *            ：继续求助，已解决
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附近文件路径
	 */
	public static void userConfirm(WebDriver driver, String taskName,
			String ProcType, String detailedDescription, String Filepath,
			int starNum) {
          
		String star = String.valueOf(starNum);
		userConfirm( driver,  taskName,
				 false, null, ProcType,  detailedDescription,  Filepath,
				 star);
	}

	/**
	 * 
	 * @param driver
	 * @param taskName：任务名
	 * @param DownFlage：是否下载附件
	 * @param fileName：需要下载的附件名称
	 * @param ProcType：继续求助，已解决
	 * @param detailedDescription：详细描述
	 * @param Filepath：附近文件路径
	 * @param starNum
	 */
	public static void userConfirm(WebDriver driver, String taskName,
			boolean DownFlage,String fileName,String ProcType, String detailedDescription, String Filepath,
			String starNum) {

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
        if(DownFlage){
    		CommonJQ.click(driver, ".expertHelpDetailLabelSpan a", true,
    				"专家反馈:附件下载");
    		TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,
    				fileName);	
        }
		if ("继续求助".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#seekForHelp", true, "用户确认:继续求助");
		} else {
			CommonJQ.click(driver, "#finishResolve", true, "用户确认:已解决");
		}

		CommonJQ.value(driver,
				"textarea[class=\"expertHelpTextArea ng-pristine ng-valid\"]",
				detailedDescription, true, "用户确认:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#usereComfirmFilePick", true,
					"用户确认:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		for (int i = 0; i <Integer.parseInt(starNum) ; i++) {
			CommonJQ.click(driver, ".star li", true, i, "用户确认:用户评分" + (i + 1)
					+ "星");
		}
		CommonJQ.click(driver,
				"div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]",
				true, "用户确认:提交按钮");
		LoadingPage.Loading(driver);
		String AcMessage = "";
		String ExMessage = "";
		if ("继续求助".equalsIgnoreCase(ProcType)) {
			AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
			ExMessage = "专家分析中...";
		} else {
			AcMessage = CommonJQ.text(driver, "div.expertHistoryTab span", "",
					0);
			ExMessage = "用户求助历史记录";
		}
		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage,
				ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 保存历史记录
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskid
	 * @param resultPath
	 */
	public static void saveHistoryRecord(WebDriver driver,
			String defaultWindowID, String taskName, String resultPath) {
		FileHandle.makeDirectory(resultPath);
		String taskid = TaskReportTask.asserTaskEndState(driver, taskName);
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskid);
		File srcFile = new File(resultPath);
		if (!srcFile.isDirectory()) {
			Assert.assertTrue(CommonFile.fileMsg(resultPath)
					+ " is not isDirectory", false);
		}
		resultPath = srcFile.getAbsolutePath();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		int historyLength = CommonJQ.length(driver,
				"div[ng-repeat=\"item in history.historyList\"]", false);

		try {
			BufferedWriter writerReport = null;
			writerReport = new BufferedWriter(new FileWriter(new File(
					resultPath + "/" + taskName + "_HistoryTime.txt")));
			for (int i = 0; i < historyLength; i++) {
				String text = CommonJQ
						.text(driver,
								"div[ng-repeat=\"item in history.historyList\"]",
								"", i).trim();
				writerReport.write(text);
				writerReport.write("\n");
			}
			writerReport.flush();
			writerReport.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);

	}
}
