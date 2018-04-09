package cloud_plugin.task.network_performance_analysis_center.engineering_optimization.mv;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.engineering_optimization.mv.MVPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.TaskReportTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutoMVDataTask {
	/***
	 * MV专家求助
	 * 
	 * @param driver
	 * @param defaultWindowdID
	 * ***/
	private static String BaselinePath = ConstUrl.getProjectHomePath()+
			"\\Data\\baseline\\MV专家求助";
	private static String ResultPath = ConstUrl.getProjectHomePath()
			+"\\Data\\result\\MV专家求助";
	public static String creatCounterStatisticsTask(WebDriver driver,
			String taskName_Star, boolean MVFlage,String HelpTitle, String HelpDetail, String MVfilePath) {

		NetworkAnalysisCenterTask.openLTEAutoMVData(driver);// 从登陆到专家求助界面
		// //打开新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		if (MVFlage) {
			CommonJQ.click(driver, MVPage.ifMVBtn, true);
			CommonJQ.click(driver, MVPage.ifMV, true, 0);
		} else if(!MVFlage) {
			CommonJQ.click(driver, MVPage.ifMVBtn, true);
			CommonJQ.click(driver, MVPage.ifMV, true, 1);
		} else {
			Assert.fail("是否MV是必选项");
		}
		CommonJQ.click(driver, MVPage.HelpTitleBtn, true);		
		CommonJQ.click(driver, MVPage.HelpTitle, "li#"+HelpTitle,true);
		CommonJQ.value(driver, MVPage.Detail,HelpDetail, true);
		
		if (MVfilePath!=null) {
			CommonJQ.click(driver, MVPage.FileBtn, true);
			CommonFile.ChooseOneFile(MVfilePath);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		
		String taskName =MVPage.setTaskName(driver, taskName_Star);//测试 任务名称				
		MVPage.commitBtnClick(driver);
		return taskName;
	}
	/**
	 * 
	 * @param driver
	 * @param taskName
	 * @param MVfilePath
	 * @param idea
	 * @param detail
	 */
	public static void expertTask(WebDriver driver,String taskName,String MVfilePath,boolean idea,String detail,boolean ifDownLoadFile,String downLoadSourceFileName) {
		NetworkAnalysisCenterTask.openLTEAutoMVData(driver);// 从登陆到专家求助界面
		String taskId = TaskReportTask.asserTaskState(driver, taskName,"Expert");
		CommonJQ.click(driver, "#"+taskId, true);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		//判断当前状态是否为专家反馈中如果是 就不能继续执行
		if(CommonJQ.isExisted(driver, MVPage.currentState, true)) {
			Assert.fail("任务状态预期是：专家反馈，实际值是:" + CommonJQ.getValue(driver, MVPage.currentState));
			SwitchDriver.switchDriverToSEQ(driver);
		} else {
			CommonJQ.value(driver, MVPage.Detail, detail);
			if(MVfilePath!=null) {
				CommonJQ.click(driver, MVPage.expertReplyFilePickBtn,true);
				CommonFile.ChooseOneFile(MVfilePath);
			}
			//下载文件
			if(ifDownLoadFile) {
				String BaselineHome = BaselinePath+"\\用户附件";
				String ResultHome = ResultPath+"\\用户附件";
				AutoMVDataTask.downLoad_Template(driver, ResultHome, downLoadSourceFileName);
				FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
			} 
			if(idea) {//数据分析完成
				CommonJQ.click(driver, MVPage.ideaFinish, true);
			} else {//请用户追加数据
				CommonJQ.click(driver, MVPage.ideaAdd,true);
			}	
			SwitchDriver.switchDriverToSEQ(driver);
			//提交
			MVPage.commitBtn_expert_Click(driver);
		}
	}
	/**
	 * driver 
	 * 任务名称
	 * 详细描述
	 * 是否下载案例模板
	 * 审核意见（已解决 true\继续求助false）
	 * ideaFlage; 评价等级
	 * MVfilePath；附件路径
	 * **/
	public static void domesticConsumerTask(WebDriver driver,String taskName,String detail,boolean ifDownloadMould, boolean idea,String grade,String MVfilePath, String sourceDownloadFileName) {
		NetworkAnalysisCenterTask.openLTEAutoMVData(driver);// 从登陆到专家求助界面
		//String taskId = TaskReportTask.searchTask(driver, taskName);
		String taskId = TaskReportTask.asserTaskState(driver, taskName,"Consumer_Domestic");
		CommonJQ.click(driver, "#"+taskId, true);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		if(CommonJQ.isExisted(driver, MVPage.currentState, true)) {
			Assert.fail("任务状态预期是：用户确认，实际值是:" + CommonJQ.getValue(driver, MVPage.currentState));
			SwitchDriver.switchDriverToSEQ(driver);
		} else {
			CommonJQ.value(driver, MVPage.Detail, detail);
			if(MVfilePath!=null) {
				CommonJQ.click(driver, MVPage.consumerFileBtn,true);
				CommonFile.ChooseOneFile(MVfilePath);
			}
			//案例模板下载
			if(ifDownloadMould) {
				//String BaselineHome = BaselinePath+"\\专家附件";
				String ResultHome = ResultPath+"\\专家附件";
				AutoMVDataTask.downLoad_Mould(driver, ResultHome, ifDownloadMould);
				//FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
			} 
			//附件下载
			if(sourceDownloadFileName!=null) {
				String BaselineHome = BaselinePath+"\\专家附件";
				String ResultHome = ResultPath+"\\专家附件";
				AutoMVDataTask.downLoad_Template(driver, ResultHome, sourceDownloadFileName);
				FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
			} 
			if(idea) {//已解决
				CommonJQ.click(driver, MVPage.ideaFinishRoid, true);
			} else {//继续求助
				CommonJQ.click(driver, MVPage.seekForHelp,true);
			}
			//用户平分
			if("很不满意".equalsIgnoreCase(grade)) {//
				CommonJQ.click(driver, MVPage.grade,true,0);
			} else if("不满意".equalsIgnoreCase(grade)) {
				CommonJQ.click(driver, MVPage.grade,true,1);
			} else if("一般".equalsIgnoreCase(grade)) {
				CommonJQ.click(driver, MVPage.grade,true,2);
			} else if("满意".equalsIgnoreCase(grade)) {
				CommonJQ.click(driver, MVPage.grade,true,3);
			} else if("非常满意".equalsIgnoreCase(grade)) {
				CommonJQ.click(driver, MVPage.grade,true,4);
			}
			SwitchDriver.switchDriverToSEQ(driver);
			MVPage.commitBtn_customer_Click(driver);
		}
	}
	/***
	 * <b>Description:</b> 第二次专家审核
	*
	* @author dwx431110
	* @param driver
	* @param taskName
	* @param MVfilePath1
	* @param detail
	* @param ifDownLoadFile
	* @return void
	 */
	public static void expertTaskSecond(WebDriver driver, String taskName, String MVfilePath1, 
			String detail, boolean ifDownLoadFile, String FilePathReport, String defaultWindowID) {
		NetworkAnalysisCenterTask.openLTEAutoMVData(driver);// 从登陆到专家求助界面
		String taskId = TaskReportTask.asserTaskState(driver, taskName,"ExpertSecond");
		CommonJQ.click(driver, "#"+taskId, true);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		String AcMessage1 = CommonJQ.text(driver, MVPage.frameBtn);
		Assert.assertTrue("专家审核界面，期望值：专家审核，实际值：" + AcMessage1,
				"专家审核".equals(AcMessage1));
		
		//报告下载
		FileHandle.delSubFile(MVfilePath1);
		String reportName = downLoad_report( driver, defaultWindowID,  taskName );
		if (reportName.endsWith("zip")) {
			ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName,
					FilePathReport);
		} else {
			FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\"
					+ reportName, FilePathReport + "\\" + reportName);
		}
				
		CommonJQ.click(driver, MVPage.frameBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@name=\"main\"]");// 嵌入iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@id=\"myAccessFrame\"]");// 嵌入iframe
		CommonJQ.value(driver, "#describe", detail, true);
		//提交
		CommonJQ.click(driver, "#userSubmit", true);
		//CommonJQ.click(driver, MVPage.isOK, true);//
		String AcMessage = CommonJQ.text(driver, "#r_expert_input>div>div> span");
		Assert.assertTrue("专家反馈界面，期望值：用户反馈中...，实际值：" + AcMessage,
				"用户反馈中...".equals(AcMessage));
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToSEQ(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname：任务名
	 * @return String：报告完整名称
	 */
	public static String downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname ) {
		//String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		//SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#showDownLoad", true,"报告下载按钮");
		String reportName =CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", true);
		reportName  = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,reportName);
		//SwitchDriver.winIDClose(driver, nowWinID);
		//SwitchDriver.switchToWinID(driver, defaultWindowID);
		//SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
	/**
	 * <b>Description:</b> 第二次用户确认
	*
	* @author dwx431110
	* @param driver
	* @param taskName
	* @param detail
	* @param ifDownloadMould
	* @param grade
	* @return void
	 */
	public static void domesticConsumerSecondTask(WebDriver driver, String taskName, String detail, boolean ifReportDownload,boolean ifDownloadFile, String grade, String FilePathReport, String defaultWindowID) {
		NetworkAnalysisCenterTask.openLTEAutoMVData(driver);// 从登陆到专家求助界面
		String taskId = TaskReportTask.asserTaskState(driver, taskName,"ExpertSecond");
		CommonJQ.click(driver, "#"+taskId, true);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		/*String AcMessage1 = CommonJQ.text(driver, MVPage.frameBtn); //用户反馈按钮
		Assert.assertTrue("用户反馈界面，期望值：用户反馈，实际值：" + AcMessage1,
				"用户反馈".equals(AcMessage1));*/
		//报告下载 ifReportDownload
		//报告下载
		FileHandle.delSubFile(FilePathReport);
		String reportName = downLoad_report( driver, defaultWindowID,  taskName );
		if (reportName.endsWith("zip")) {
			ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName,
					FilePathReport);
		} else {
			FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\"
					+ reportName, FilePathReport + "\\" + reportName);
		}
	
		//附件下载 
		/*if(ifDownloadFile) {
			CommonJQ.click(driver, ".icon icon_down reportDownloadIcon",true,"附件下载 ");
			TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, "附件下载 ");
		}*/
		CommonJQ.click(driver, MVPage.frameBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@name=\"main\"]");// 嵌入iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe[@id=\"myAccessFrame\"]");// 嵌入iframe
		CommonJQ.value(driver, "#describe", detail, true);
		//用户平分
		if("很不满意".equalsIgnoreCase(grade)) {//
			CommonJQ.click(driver, MVPage.grade2,true,0);
		} else if("不满意".equalsIgnoreCase(grade)) {
			CommonJQ.click(driver, MVPage.grade2,true,1);
		} else if("一般".equalsIgnoreCase(grade)) {
			CommonJQ.click(driver, MVPage.grade2,true,2);
		} else if("满意".equalsIgnoreCase(grade)) {
			CommonJQ.click(driver, MVPage.grade2,true,3);
		} else if("非常满意".equalsIgnoreCase(grade)) {
			CommonJQ.click(driver, MVPage.grade2,true,4);
		}
		//提交
		CommonJQ.click(driver, "#userSubmit", true);
		String AcMessage = CommonJQ.text(driver, "#tit");
		Assert.assertTrue("专家反馈界面，期望值：流程结束，实际值：" + AcMessage,
				"流程结束".equals(AcMessage));
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToSEQ(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	//附件下载
	public static void downLoad_Template(WebDriver driver,String filePath,String downLoadSourceFileName) {
		FileHandle.delSubFile(ConstUrl.DownLoadPath);//设置浏览器下载路径
		FileHandle.delSubFile(filePath);
		//SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, MVPage.ifDownLoadFile,true,downLoadSourceFileName);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, downLoadSourceFileName);
		FileHandle.copyFile(ConstUrl.DownLoadPath+"\\"+reportName, filePath+"\\ceshi.zip");
		//SwitchDriver.switchDriverToSEQ(driver);
	}
	//模板下载
	public static void downLoad_Mould(WebDriver driver,String filePath,boolean ifDownLoadMould) {
		FileHandle.delSubFile(ConstUrl.DownLoadPath);//设置浏览器下载路径
		//FileHandle.delSubFile(filePath);
		//SwitchDriver.switchDriverToFrame(driver, "//iframe");
		if(ifDownLoadMould) {
			CommonJQ.click(driver, MVPage.ifDownLoadMould,true,"案例模板");
			String reportName = TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, "案例模板");
			//FileHandle.copyFile(ConstUrl.DownLoadPath+"\\"+reportName, filePath+"\\ceshi.zip");
		}
		//SwitchDriver.switchDriverToSEQ(driver);
	}
	//报告下载
	public static void downLoad_Report(WebDriver driver,String filePath,boolean ifDownLoadMould) {
		if(ifDownLoadMould) {
			CommonJQ.click(driver, "#showDownLoad",true,"报告下载");
			String reportName = TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, "报告下载");
		}
	}
}
