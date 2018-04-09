package cloud_plugin.task.network_performance_analysis_center.engineering_optimization.single_site_verification;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.single_site_verification.SingleSiteVerificationPage;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.TaskViewPluginTask;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.SwitchDriver;

public class SingleSiteVerificationTask {
	/**
	 * <b>Description:</b> //单站验证 和单站优化
	*
	* @author dwx431110
	* @param driver
	* @param taskName
	* @return
	* @return String
	 */
	public static String creat_SingleSiteCheck(WebDriver driver, 
			String taskName,
			String reportModel,
			String taskType,
			String mapType, 
			String[] cfgData, 
			String[] projectParame, 
			String[] roadSurveyData,
			String[] reconnaissanceMapCfg,//勘测图片配置
			 String[] speedImg,//速率截图配置
			 String[] DTFileCfg,//文件映射
			 String[] RSSICfg,// PSSI配置
			 String[] expansionParameter,//其他配置
			 String FilePath,
			String defaultWindowID) {
		// //打开新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		//报告模板
		if("EXCEL||云南-移动-LTE-单站报告模板".equals(reportModel)) {
			CommonJQ.click(driver, SingleSiteVerificationPage.reportDetails, true);
			CommonJQ.click(driver, SingleSiteVerificationPage.mshowvaReportModel, true,0);
		} else if("EXCEL||云南-移动-LTE-云南单站验收模板".equals(reportModel)) {
			CommonJQ.click(driver, SingleSiteVerificationPage.reportDetails, true);
			CommonJQ.click(driver, SingleSiteVerificationPage.mshowvaReportModel, true,1);
		} else if("EXCEL||移动-LTE-单站报告通用模板".equals(reportModel)) {
			CommonJQ.click(driver, SingleSiteVerificationPage.reportDetails, true);
			CommonJQ.click(driver, SingleSiteVerificationPage.mshowvaReportModel, true,2);
		} 
		//任务类型(单站验证)
		if("单站验证".equals(taskType)) {
			CommonJQ.click(driver, SingleSiteVerificationPage.BtnSingleStateChecked, true);
		} else if("单站验证".equals(taskType)) {
			CommonJQ.click(driver, SingleSiteVerificationPage.BtnSingleStateOptimize, true);
		}
		
		//地图类型
		if("平面地图".equals(mapType)) {//平面图
			CommonJQ.click(driver, SingleSiteVerificationPage.BtnPlaneMap, true);
		} else if("卫星地图".equals(mapType)) {//卫星图
			CommonJQ.click(driver, SingleSiteVerificationPage.BtnSatelliteMap, true);
		}
		
		SwitchDriver.switchDriverToSEQ(driver);
		//分析数据
		SingleSiteVerificationPage.analyzeData(driver, cfgData, projectParame, roadSurveyData, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		//参数设置
		CommonJQ.click(driver, SingleSiteVerificationPage.BtnParameterSetUp, true);
		SwitchDriver.switchDriverToSEQ(driver);
		SingleSiteVerificationPage.projectSetUp(driver, reconnaissanceMapCfg, speedImg, DTFileCfg, RSSICfg, expansionParameter,FilePath,defaultWindowID);
		return SingleSiteVerificationPage.setTaskName(driver, taskName);
	}
	/**
	 * <b>Description:</b> //指标统计
	*
	* @author dwx431110
	* @param driver
	* @param taskName
	* @return
	* @return String
	 */
	public static String create_SingleMajorization(WebDriver driver, 
			String taskName,
			String startTime, String endTime) {
		// //打开新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		//任务类型（指标统计）
		CommonJQ.click(driver, SingleSiteVerificationPage.BtnTargetCensus, true);
		//时间选择
		CommonJQ.value(driver, SingleSiteVerificationPage.startTime, startTime, true);
		CommonJQ.value(driver, SingleSiteVerificationPage.endTime, endTime, true);
		SwitchDriver.switchDriverToSEQ(driver);
		return SingleSiteVerificationPage.setTaskName(driver, taskName);
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
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@name=\"main\"]");// 嵌入
		//SwitchDriver.switchDriverToFrame(driver, "//iframe[@id=\"myframe\"]");// 嵌入
		CommonJQ.click(driver, "#textflowTitle~span", true,"专家求助:求助标题 下拉框");
		
		CommonJQ.click(driver, "ul#mshowflowTitle li#" + HelpType, true,
				"专家求助:求助标题 ");

		CommonJQ.value(driver,
				"#describe",
				detailedDescription, true, "专家求助:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#filePick", true, "专家求助:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		CommonJQ.click(driver,
				"#userSubmit>span",
				true, "专家求助:提交按钮");
		CommonJQ.click(driver, ".l-btn-text", true, "选择->确认");
		LoadingPage.Loading(driver);
		
		String AcMessage = CommonJQ.text(driver, "#r_expert_input>div>div>span");
		String ExMessage = "专家分析中...";

		Assert.assertTrue("专家求助界面，期望值：" + ExMessage + "，实际值：" + AcMessage,
				ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
		//SwitchDriver.switchDriverToSEQ(driver);
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
	public static void expertProc(WebDriver driver, String taskName, String ProcType, String detailedDescription, String filePath) {
		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@name=\"main\"]");// 嵌入
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@id=\"myframe\"]");// 嵌入
		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#btn_3>span>input", true, 1,"专家反馈:请用户追加数据");
		} else {
			CommonJQ.click(driver, "#btn_3>span>input", true, 0,"专家反馈:问题分析完成");
		}
		CommonJQ.value(driver,
				"#describe",
				detailedDescription, true, "专家反馈:详细描述");
		if (filePath != null) {
			File dir = new File(filePath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(filePath) + " is not file");
			}
			filePath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#filePick", true,
					"专家反馈:上传附件选择按钮");
			CommonFile.ChooseOneFile(filePath);
		}
		CommonJQ.click(driver,
				"#userSubmit",
				true, "专家反馈:提交按钮");
		CommonJQ.click(driver, ".l-btn-text", true, "选择->确认");
		LoadingPage.Loading(driver);
		String AcMessage = CommonJQ.text(driver, "#r_expert_input>div>div> span");
		String ExMessage = "";
		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			ExMessage = "用户追加数据中...";
		} else {
			ExMessage = "用户确认结果中...";
		}

		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage,
				ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
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
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@name=\"main\"]");// 嵌入
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@id=\"myframe\"]");// 嵌入
		if("继续求助".equals(ProcType)) {//#btn_3>span>input
			CommonJQ.click(driver, "#btn_3>span>input", true,1, "用户确认:继续求助");
		} else {
			CommonJQ.click(driver, "#btn_3>span>input", true,0, "用户确认:已解决");
		}
		CommonJQ.value(driver,
				"#describe",
				detailedDescription, true, "用户确认:详细描述");
		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#filePick", true,
					"用户确认:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		for (int i = 0; i < starNum; i++) {
			CommonJQ.click(driver, ".star li", true, i, "用户确认:用户评分" + (i + 1)
					+ "星");
		}
		CommonJQ.click(driver,
				"#userSubmit",
				true, "用户确认:提交按钮");
		
		LoadingPage.Loading(driver);
		String AcMessage = "";
		String ExMessage = "";
		if ("继续求助".equalsIgnoreCase(ProcType)) {
			AcMessage = CommonJQ.text(driver, "#r_expert_input>div>div>span");
			ExMessage = "专家分析中...";
		} else {
			AcMessage = CommonJQ.text(driver, "t2", "",
					0);
			ExMessage = "用户求助历史记录";
		}
		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage,
				ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
