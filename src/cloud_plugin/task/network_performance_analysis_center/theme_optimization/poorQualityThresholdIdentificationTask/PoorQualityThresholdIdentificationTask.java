package cloud_plugin.task.network_performance_analysis_center.theme_optimization.poorQualityThresholdIdentificationTask;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.network_audit.NetWorkAuditPage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;
/***
 * 视频优化质差门限识别
 * **/
public class PoorQualityThresholdIdentificationTask {

	public static String createPoorQualityThresholdIdentification(WebDriver driver,String taskName, String[] modelData, String[] parameSetUp, String defaultWindowID) {
		// 新建任务
		TaskReportPage.CreateTaskClick(driver);
		LoadingPage.Loading(driver);
		
		//SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		String taskNameStr = NetWorkAuditPage.setTaskName(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@name=\"main\"]");// 嵌入
		CommonJQ.value(driver, "#taskName", taskNameStr, true,"任务名称");
		CommonJQ.click(driver, "span[ng-click=\"next()\"]",true, "下一步");
		/*if(modelData!=null) {
			CommonJQ.click(driver,"#select_videoModelDataTarget",true,"视频建模数据");
		}*/
		SwitchDriver.switchDriverToSEQ(driver);
		if(modelData!=null) {
			GetDataByTypeTask
			.chooseFolder(
					driver,
					modelData,
					"$('iframe[name=main]').contents().find('#select_videoModelDataTarget').click()",
					defaultWindowID);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@name=\"main\"]");// 嵌入
		CommonJQ.click(driver, "span[ng-click=\"next()\"]",true, "下一步");
		if(parameSetUp!=null) {
			CommonJQ.value(driver, "#targetPdcpThp", parameSetUp[0], true);
			CommonJQ.value(driver, "#supportThd", parameSetUp[1], true);
			CommonJQ.value(driver, "#confidenceThd", parameSetUp[2], true);
		}
		CommonJQ.click(driver, "span[ng-click=\"submit()\"]",true, "提交");
		SwitchDriver.switchDriverToSEQ(driver);
		return taskNameStr;
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
				"textarea[name=\"describe\"]",
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

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#addDataReply", true, "专家反馈:请用户追加数据");
		} else {
			CommonJQ.click(driver, "#finishReply", true, "专家反馈:问题分析完成");
		}

		CommonJQ.value(driver,
				".expertHelpDetailLabel>textarea",
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

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("继续求助".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#seekForHelp", true, "用户确认:继续求助");
		} else {
			CommonJQ.click(driver, "#finishResolve", true, "用户确认:已解决");
		}

		CommonJQ.value(driver,
				".expertHelpDetailLabel>textarea",
				detailedDescription, true, "专家反馈:详细描述");

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
		for (int i = 0; i < starNum; i++) {
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
			AcMessage = CommonJQ.text(driver, "span:contains('用户求助历史记录')", "",
					0);
			ExMessage = "用户求助历史记录";
		}
		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage,
				ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}
	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @param ResultHome
	 *            ：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver,
			String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			if (reportName[i].endsWith("zip")) {
				ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i],
						ResultHome);
			} else {
				FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\"
						+ reportName[i], ResultHome + "\\" + reportName[i]);
			}
		}

	}
	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @return String：报告完整名称
	 */
	public static String[] downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LoadingPage.Loading(driver, ".col6 a", "报告下载按钮");
		int reportNum = CommonJQ.length(driver,
				".taskReportTab .col2 span[class=\"ng-binding\"]", true);
		String[] reportName = new String[reportNum];
		for (int i = 0; i < reportNum; i++) {
			CommonJQ.click(driver, ".col6 a", true, i, "报告下载按钮");
			reportName[i] = CommonJQ.text(driver,
					".taskReportTab .col2 span[class=\"ng-binding\"]", "", i);
			TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,
					reportName[i]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
