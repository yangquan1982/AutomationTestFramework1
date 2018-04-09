package cloud_plugin.task.network_performance_analysis_center.network_planning.lte_seq_vmos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_Seq_VMOS_Page;
import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.LTE_VMOS_Const;
import cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos.Seq_VMOS_ManagePage;
import cloud_public.page.GetDataByTypePage;
import cloud_public.page.IndexPage;
import cloud_public.page.LoadingPage;
import cloud_public.task.IndexTask;
import cloud_public.task.TaskReportTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.FileCompare;
import common.util.FileHandle;
import common.util.LOG;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class SeqVMOSTask {
	
	public  static void createTaskBaseInforCheck(WebDriver driver){
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);		
		SeqVMOSTask.checkCreateNewTaskSkip(driver);
		SeqVMOSTask.checkCreateNewTaskBaseInfo(driver);
		SeqVMOSTask.checkCreateNewTaskBaseInfoDisabled(driver);
		
		
	}
	
	public  static void createNewTask(WebDriver driver,String defaultWindowIDs,String DataType,String forderName){
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);		
		String TASKNAME = "自动化测试任务_"+ZIP.MMddhhmmssms();
		LTE_Seq_VMOS_Page.setTaskName(driver, TASKNAME);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);		
		//SeqVMOSTask.selectDataSource(driver,DataType,forderName,defaultWindowIDs);		
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);
		LTE_Seq_VMOS_Page.createTaskCommitBTN(driver);
		SeqVMOSTask.checkCreateTaskOK(driver,TASKNAME);		
	}
		
	private static void checkCreateTaskOK(WebDriver driver, String newtaskname) {
		boolean flag = LTE_Seq_VMOS_Page.checkExistTaskByName(driver, newtaskname);
		if(!flag){
			Assert.fail("创建精品视频任务失败！！");
		}
		
	}

	private static void selectDataSource(WebDriver driver, String dataType,
			String[] forderName) {
		String windowsId = driver.getWindowHandle();
		LTE_Seq_VMOS_Page.selectFileByName(driver, dataType,windowsId);
		SeqVMOSTask.chooseDefineData(driver, windowsId, forderName);		
	}
	
	
	private static void selectPFMDataSource(WebDriver driver, String dataType,
			String[] forderName) {
		String windowsId = driver.getWindowHandle();
		LTE_Seq_VMOS_Page.clickPFMSelectFile(driver, windowsId);
		SeqVMOSTask.chooseDefineData(driver, windowsId, forderName);		
	}

	
	private static void checkCreateNewTaskBaseInfo(WebDriver driver) {
		String regionName = LTE_Seq_VMOS_Page.getRegion(driver);
		if(!StringUtils.contains(LTE_VMOS_Const.RegionName, regionName)){
			Assert.fail("片区信息不一致");
		}
		String BranchName = LTE_Seq_VMOS_Page.getBranch(driver);
		if(!StringUtils.contains(LTE_VMOS_Const.BranchName, BranchName)){
			Assert.fail("代表处信息不一致");
		}
		String OperatorName = LTE_Seq_VMOS_Page.getOperator(driver);
		if(!StringUtils.contains(LTE_VMOS_Const.OperatorName, OperatorName)){
			Assert.fail("运营商信息不一致");
		}
		String RatName = LTE_Seq_VMOS_Page.getRatName(driver);
		if(!StringUtils.contains(LTE_VMOS_Const.Rat, RatName)){
			Assert.fail("制式信息不一致");
		}
		
		String newtaskItemName = LTE_Seq_VMOS_Page.getNewTaskProjectName(driver);
		if(!StringUtils.contains(LTE_VMOS_Const.VMOS_Project, newtaskItemName)){
			Assert.fail("项目名称信息不一致");
		}
		
	}
	
	public static void goBackToNetworkAnalysisCenter(WebDriver driver){
		
		SwitchDriver.switchDriverToSEQ(driver);
		if(LTE_Seq_VMOS_Page.netAnalysisCenterHref(driver)){
			LTE_Seq_VMOS_Page.clickNetAnalysisCenterHref(driver);
			LoadingPage.Loading(driver);
		}
		return;
	}
	
	/**
	 * 检查新建任务基本信息
	 * @param driver
	 */
	private static void checkCreateNewTaskBaseInfoDisabled(WebDriver driver) {
		if(!LTE_Seq_VMOS_Page.isRegionDisabled(driver)){
			Assert.fail("片区信息可用，与预期不符");
		}
		if(!LTE_Seq_VMOS_Page.isBranchDisabled(driver)){
			Assert.fail("代表处信息可用，与预期不符");
		}
		if(!LTE_Seq_VMOS_Page.isItemNameDisabled(driver)){
			Assert.fail("项目信息可用，与预期不符");
		}
		if(!LTE_Seq_VMOS_Page.isRatDisabled(driver)){
			Assert.fail("制式信息可用，与预期不符");
		}
		
		
		
	}

	/**
	 * 检查创建任务跳转
	 * @param driver
	 */
	public static void checkCreateNewTaskSkip(WebDriver driver) {
		String pluginname = LTE_Seq_VMOS_Page.getNewTaskPluginTitleName(driver);
		if (!StringUtils.containsIgnoreCase(pluginname,
				LTE_VMOS_Const.PluginNameOfVMOS)
				&& !StringUtils.containsIgnoreCase(pluginname,
						LTE_VMOS_Const.CreateNewTask)) {
			Assert.fail("打开新建任务失败");
		}
		
	}

	/**
	 * 打开插件
	 * 
	 * @author zwx320041
	 * @param driver netPerformanceAnalysisCenter
	 * @return void
	 */
	public static void openPlugin(WebDriver driver) {
		LTE_Seq_VMOS_Page.openApp(driver);
		if(CommonJQ.isVisible(driver, LTE_Seq_VMOS_Page.pluginTitle)){
			if(!StringUtils.containsIgnoreCase(LTE_Seq_VMOS_Page.getPluginName(driver), LTE_VMOS_Const.PluginNameOfVMOS)){
				String PluginName = LTE_Seq_VMOS_Page.getPluginName(driver).trim();
				Assert.fail("插件打开名称不正确！界面显示为："+PluginName);
			}
			
		}else{
			Assert.fail("插件打开失败");
		}
		
	}
	
	/**
	 * 更换项目
	 * @param driver
	 * @param PrjName
	 */
	public static void ChangeProject(WebDriver driver, String PrjName){
		IndexTask.changePrj(driver, PrjName);	
	}
	
	/**
	 * 选择数据源文件夹
	 * @param driver
	 * @param defaultWindowID
	 * @param files
	 */
	public static void chooseDefineData(WebDriver driver,
			String defaultWindowID, String[] files) {
		if (files.length == 0) {
			Assert.fail("未指定需要选择的文件");
		} else {
			for (int i = 0; i < files.length; i++) {
				switch (files[i]) {
				case "ALL": {
					CommonJQ.setSelectOption(driver,
							GetDataByTypePage.PageSize,
							LanguageUtil.translate("All"));
					CommonJQ.click(driver, GetDataByTypePage.CheckRow, false);
					CommonJQ.click(driver, GetDataByTypePage.SubmitBtn, true);
					break;
				}
				case "10": {
					CommonJQ.setSelectOption(driver,
							GetDataByTypePage.PageSize, "10");
					CommonJQ.click(driver, GetDataByTypePage.CheckRow, false);
					CommonJQ.click(driver, GetDataByTypePage.SubmitBtn, true);
					break;
				}
				default: {
					boolean flage = CommonJQ.isExisted(driver,"span[title=\""+ files[i]+"\"]");
					if(!flage){
						GetDataByTypePage.searchInChoosePage(driver, files[i]);
					}
					System.out.println("files.length:" + files.length);
					if (i < files.length - 1) {
						GetDataByTypePage.clickfile(driver, files[i]);
					} else {
						if (CommonJQ.isExisted(driver,
								GetDataByTypePage.ListBox,
								GetDataByTypePage.InputVisible)) {
							GetDataByTypePage.clickRidoBtn(driver, files[i]);
						} else {
							GetDataByTypePage.clickcheckboxs(driver, files[i]);
						}
					}
				}
				}

			}
		}
		GetDataByTypePage.closeOtherWindows(driver, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
	}

	/**
	 * 参数化创建任务
	 * 
	 * @param driver
	 * @param secns
	 * @param projectName
	 * @param taskType
	 * @param cfgData
	 * @param paramData
	 * @param sigData
	 * @param nonEncryptedVideoSourceData
	 * @param encryptedVideoSourceData
	 * @param oTTData
	 * @param electronicMapData
	 * @param lTECharacteristicLibraryData
	 * @param performanceData
	 * @param videoTrafficForecastResultFile
	 * @param cDRData
	 * @param uELogsData
	 * @param dataPreprocessingSetItems
	 * @param dataPreprocessingSetValues
	 * @param evaluationSetItems
	 * @param evaluationSetItemsValues
	 * @param isIndependentBusyhourKPIs
	 * @param filteringMode
	 * @param busyhourKPI
	 * @param customHours
	 * @param busyDays
	 * @param legendSetItem
	 * @param legendSetSegmentNumber
	 * @param legendSetValue
	 */
	public static void ParamCreateTask(WebDriver driver,String secns, String projectName,
			String[] taskType, String[] cfgData, String[] paramData,
			String[] sigData, String[] nonEncryptedVideoSourceData,
			String[] encryptedVideoSourceData, String[] oTTData,
			String[] electronicMapData, String[] lTECharacteristicLibraryData,
			String[] performanceData, String videoTrafficForecastResultFile,String[] xDR_MRPreprocessData,
			String[] cDRData, String[] uELogsData,
			String[] dataPreprocessingSetItems,
			String[] dataPreprocessingSetValues, String[] evaluationSetItems,
			String[] evaluationSetItemsValues,
			String isIndependentBusyhourKPIs, String filteringMode,
			String busyhourKPI, String[] customHours, String busyDays,
			String legendSetItem, String legendSetSegmentNumber,
			String[] legendSetValue) {
		
		LOG.info_testcase("场景描述:"+secns);		
		SeqVMOSTask.ChangeProject(driver,LTE_VMOS_Const.VMOS_Project); //LTE_VMOS_Const.VMOS_Project
		SeqVMOSTask.openPlugin(driver);
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);		
		String TASKNAME = projectName;
		SeqVMOSTask.checkCreateNewTaskSkip(driver);
		LTE_Seq_VMOS_Page.setTaskName(driver, TASKNAME);
		SeqVMOSTask.SelectTaskType(driver,taskType);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);	
		SeqVMOSTask.SkipOneCheck(driver);
		SeqVMOSTask.SelectDataSources(driver, cfgData, paramData, sigData,
				nonEncryptedVideoSourceData, encryptedVideoSourceData, oTTData,
				electronicMapData, lTECharacteristicLibraryData,
				performanceData, videoTrafficForecastResultFile, xDR_MRPreprocessData,cDRData,uELogsData);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);
		SeqVMOSTask.SkipTwoCheck(driver);
		SeqVMOSTask.SetDataPreprocessingParam(driver,dataPreprocessingSetItems,dataPreprocessingSetValues);
		SeqVMOSTask.SetEvaluationParam(driver,evaluationSetItems,evaluationSetItemsValues);
		
		SeqVMOSTask.SetOTType(driver,isIndependentBusyhourKPIs);
		//忙时筛选方式设置
		SeqVMOSTask.SetBusyTimeFilterType(driver, filteringMode);
		//设置忙时指标
		SeqVMOSTask.SetBusyTimeKPI(driver,busyhourKPI);
		//选择自定义时间
		SeqVMOSTask.SelectCustomTimes(driver,customHours);
		//设置忙时天数
		SeqVMOSTask.SetBusyTimeDays(driver,busyDays);
		
		LTE_Seq_VMOS_Page.createTaskCommitBTN(driver);
		SeqVMOSTask.checkCreateTaskOK(driver,TASKNAME);
		return;		
	}

	
	/**
	 * 参数化创建任务
	 * 
	 * @param driver
	 * @param secns
	 * @param projectName
	 * @param taskType
	 * @param cfgData
	 * @param paramData
	 * @param sigData
	 * @param nonEncryptedVideoSourceData
	 * @param encryptedVideoSourceData
	 * @param oTTData
	 * @param electronicMapData
	 * @param lTECharacteristicLibraryData
	 * @param performanceData
	 * @param videoTrafficForecastResultFile
	 * @param cDRData
	 * @param uELogsData
	 * @param dataPreprocessingSetItems
	 * @param dataPreprocessingSetValues
	 * @param evaluationSetItems
	 * @param evaluationSetItemsValues
	 * @param isIndependentBusyhourKPIs
	 * @param filteringMode
	 * @param busyhourKPI
	 * @param customHours
	 * @param busyDays
	 * @param legendSetItem
	 * @param legendSetSegmentNumber
	 * @param legendSetValue
	 */
	public static void ParamCreateTask(WebDriver driver,String secns, String projectName,
			String[] taskType, String[] cfgData, String[] paramData,
			String[] sigData, String[] nonEncryptedVideoSourceData,
			String[] encryptedVideoSourceData, String[] oTTData,
			String[] electronicMapData, String[] lTECharacteristicLibraryData,
			String[] performanceData, String videoTrafficForecastResultFile,String[] xDR_MRPreprocessData,
			String[] cDRData, String[] uELogsData,
			String[] dataPreprocessingSetItems,
			String[] dataPreprocessingSetValues, String[] evaluationSetItems,
			String[] evaluationSetItemsValues,
			String isIndependentBusyhourKPIs, String filteringMode,
			String busyhourKPI, String[] customHours, String busyDays,
			String legendSetItem, String legendSetSegmentNumber,
			String[] legendSetValue,String project) {
		
		LOG.info_testcase("场景描述:"+secns);		
		SeqVMOSTask.ChangeProject(driver,project); //LTE_VMOS_Const.VMOS_Project
		SeqVMOSTask.openPlugin(driver);
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);		
		String TASKNAME = projectName;
		SeqVMOSTask.checkCreateNewTaskSkip(driver);
		LTE_Seq_VMOS_Page.setTaskName(driver, TASKNAME);
		SeqVMOSTask.SelectTaskType(driver,taskType);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);	
		SeqVMOSTask.SkipOneCheck(driver);
		SeqVMOSTask.SelectDataSources(driver, cfgData, paramData, sigData,
				nonEncryptedVideoSourceData, encryptedVideoSourceData, oTTData,
				electronicMapData, lTECharacteristicLibraryData,
				performanceData, videoTrafficForecastResultFile, xDR_MRPreprocessData,cDRData,uELogsData);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);
		SeqVMOSTask.SkipTwoCheck(driver);
		SeqVMOSTask.SetDataPreprocessingParam(driver,dataPreprocessingSetItems,dataPreprocessingSetValues);
		SeqVMOSTask.SetEvaluationParam(driver,evaluationSetItems,evaluationSetItemsValues);
		
		SeqVMOSTask.SetOTType(driver,isIndependentBusyhourKPIs);
		//忙时筛选方式设置
		SeqVMOSTask.SetBusyTimeFilterType(driver, filteringMode);
		//设置忙时指标
		SeqVMOSTask.SetBusyTimeKPI(driver,busyhourKPI);
		//选择自定义时间
		SeqVMOSTask.SelectCustomTimes(driver,customHours);
		//设置忙时天数
		SeqVMOSTask.SetBusyTimeDays(driver,busyDays);
		
		LTE_Seq_VMOS_Page.createTaskCommitBTN(driver);
		SeqVMOSTask.checkCreateTaskOK(driver,TASKNAME);
		return;		
	}

	
	
	//报告下载全量对比
	public  static void DownLoadReport(WebDriver driver,String secns, String taskName, String[] StartWith){
		LOG.info_testcase("场景描述:"+secns);		
		SeqVMOSTask.ChangeProject(driver,LTE_VMOS_Const.VMOS_Project);
//		SeqVMOSTask.ChangeProject(driver,"中国四川成都移动LTEwdw");
		SeqVMOSTask.openPlugin(driver);
		String defaultWindowID = CommonWD.MainWindowHandle;
		String ResultHome1 = ConstUrl.getProjectHomePath()+ "\\Data\\result\\LTE_Seq_vMOS\\" + taskName;
		String BaselineHome1 = ConstUrl.getProjectHomePath()+ "\\Data\\baseline\\LTE_Seq_vMOS\\" + taskName;
		TaskReportTask.asserTaskEndState(driver, taskName);
		SeqVMOSTask.downLoad_MoveReport(driver, defaultWindowID,
				taskName, ResultHome1);		
		Assert.assertTrue(CommonFile.fileReportMsg(ResultHome1),FileCompare.SameNameCompareInPathMAX(BaselineHome1, ResultHome1));	
	}
	
	//算法验证报告下载
	public  static void DownLoadReport(WebDriver driver,String secns, String taskName,String taskId){
		LOG.info_aw("场景描述:"+secns);
		File file = new File(LTE_VMOS_Const.reportPath+taskName);
		if (file.isDirectory()) {
			LOG.info_aw("任务报告已经下载:"+taskName);
			return;
		}
		SeqVMOSTask.ChangeProject(driver,IndexPage.AlgorithmProject); //  LTE_VMOS_Const.VMOS_Project
		SeqVMOSTask.openPlugin(driver);
		String taskid = TaskReportTask.asserTaskEndState(driver, taskName);
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		String defaultWindowID = driver.getWindowHandle();
		
		LTE_Seq_VMOS_Page.enterTask(driver, taskid);
		String newHandle = CommonWD.getWindowHandle(driver, defaultWindowID);
		if (newHandle == null) {
			Assert.fail("进入任务失败！");
		} else {
			driver.switchTo().window(newHandle);
			Pause.pause(1000);
			LoadingPage.Loading(driver);
			SwitchDriver.switchDriverToFrame(driver);
			LoadingPage.Loading(driver);
		}
		
		int num = LTE_Seq_VMOS_Page.getDownLoadReportNum(driver);
		
		for (int i = 0; i < num; i++) {
			String reportName = LTE_Seq_VMOS_Page.getReportName(driver, i);
			LTE_Seq_VMOS_Page.clickDownLoadReport(driver, i);
			CommonFile.checkExistFiles(ConstUrl.DownLoadPath, reportName);
			try {
				org.apache.commons.io.FileUtils.copyFile(new File(ConstUrl.DownLoadPath+"\\"+reportName), new File(LTE_VMOS_Const.reportPath+taskName+"\\"+reportName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	private static void downLoad_MoveReport(WebDriver driver,
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

	private static String[] downLoad_report(WebDriver driver,
			String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LoadingPage.Loading(driver, ".col6 a", "报告下载按钮");
		int reportNum = LTE_Seq_VMOS_Page.getDownLoadReportNum(driver);
		String[] reportName = new String[reportNum];
		for (int i = 0; i < reportNum; i++) {
			String AreportName = LTE_Seq_VMOS_Page.getReportName(driver, i);
			LTE_Seq_VMOS_Page.clickDownLoadReport(driver, i);
			CommonJQ.click(driver, ".col6 a", true, i, "报告下载按钮");
			reportName[i] = AreportName;
			TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,
					reportName[i]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}

	/**
	 * 参数设置取消
	 * @param driver
	 * @param secns
	 * @param projectName
	 * @param taskType
	 * @param cfgData
	 * @param paramData
	 * @param sigData
	 * @param nonEncryptedVideoSourceData
	 * @param encryptedVideoSourceData
	 * @param oTTData
	 * @param electronicMapData
	 * @param lTECharacteristicLibraryData
	 * @param performanceData
	 * @param videoTrafficForecastResultFile
	 * @param xDR_MRPreprocessData
	 * @param cDRData
	 * @param uELogsData
	 * @param dataPreprocessingSetItems
	 * @param dataPreprocessingSetValues
	 * @param evaluationSetItems
	 * @param evaluationSetItemsValues
	 * @param isIndependentBusyhourKPIs
	 * @param filteringMode
	 * @param busyhourKPI
	 * @param customHours
	 * @param busyDays
	 * @param legendSetItem
	 * @param legendSetSegmentNumber
	 * @param legendSetValue
	 */	
	public static void ParamSettingCancelCreateTask(WebDriver driver,String secns, String projectName,
			String[] taskType, String[] cfgData, String[] paramData,
			String[] sigData, String[] nonEncryptedVideoSourceData,
			String[] encryptedVideoSourceData, String[] oTTData,
			String[] electronicMapData, String[] lTECharacteristicLibraryData,
			String[] performanceData, String videoTrafficForecastResultFile,String[] xDR_MRPreprocessData,
			String[] cDRData, String[] uELogsData,
			String[] dataPreprocessingSetItems,
			String[] dataPreprocessingSetValues, String[] evaluationSetItems,
			String[] evaluationSetItemsValues,
			String isIndependentBusyhourKPIs, String filteringMode,
			String busyhourKPI, String[] customHours, String busyDays,
			String legendSetItem, String legendSetSegmentNumber,
			String[] legendSetValue) {
		
		LOG.info_testcase("场景描述:"+secns);		
		SeqVMOSTask.ChangeProject(driver,LTE_VMOS_Const.VMOS_Project);
		SeqVMOSTask.openPlugin(driver);
		LTE_Seq_VMOS_Page.clickCreateTaskBTN(driver);		
		String TASKNAME = projectName;
		SeqVMOSTask.checkCreateNewTaskSkip(driver);
		LTE_Seq_VMOS_Page.setTaskName(driver, TASKNAME);
		SeqVMOSTask.SelectTaskType(driver,taskType);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);	
		SeqVMOSTask.SkipOneCheck(driver);
		SeqVMOSTask.SelectDataSources(driver, cfgData, paramData, sigData,
				nonEncryptedVideoSourceData, encryptedVideoSourceData, oTTData,
				electronicMapData, lTECharacteristicLibraryData,
				performanceData, videoTrafficForecastResultFile, xDR_MRPreprocessData,cDRData,uELogsData);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);
		SeqVMOSTask.SkipTwoCheck(driver);
		SeqVMOSTask.SetDataPreprocessingParam(driver,dataPreprocessingSetItems,dataPreprocessingSetValues);
		SeqVMOSTask.SetEvaluationParam(driver,evaluationSetItems,evaluationSetItemsValues);
		LTE_Seq_VMOS_Page.createTaskCancelBTN(driver);
		boolean createTaskOK= LTE_Seq_VMOS_Page.checkExistTaskByName(driver, TASKNAME);
		if(createTaskOK){
			Assert.fail("取消参数设置任务创建成功！！");
		}
	}
	
	public static void SkipOneCheck(WebDriver driver) {
		//taskAddProgressDotText ng-binding taskAddProgressDotTextBold
		String ClassSytle =LTE_Seq_VMOS_Page.getProcessDisplayClass(driver,LTE_VMOS_Const.ProcessOfDataSelect);
		if(!ClassSytle.equalsIgnoreCase(LTE_Seq_VMOS_Page.DataSelectProcessClass)){
			Assert.fail("跳转到数据选择界面失败！");
		}
		
	}

	private static void SkipTwoCheck(WebDriver driver) {
		// taskAddProgressDotText ng-binding taskAddProgressDotTextBold
		String ClassSytle = LTE_Seq_VMOS_Page.getProcessDisplayClass(driver,
				LTE_VMOS_Const.ProcessOfParamSet);
		if (!ClassSytle
				.equalsIgnoreCase(LTE_Seq_VMOS_Page.DataSelectProcessClass)) {
			Assert.fail("跳转到数据选择界面失败！");
		}
		
	}

	/**
	 * 设置评估参数
	 * @param driver
	 * @param dataPreprocessingSetItems
	 * @param dataPreprocessingSetValues
	 */
	private static void SetEvaluationParam(WebDriver driver,
			String[] evaluationSetItems,
			String[] evaluationSetItemsValues) {
		if(evaluationSetItems!=null&&!StringUtils.isBlank(evaluationSetItems[0])){
			if (evaluationSetItems.length != evaluationSetItemsValues.length) {
				Assert.fail("参数文件中，预处理设置项与设置值个数不匹配");
			}
			for (int i =0;i<evaluationSetItems.length;i++) {
				String css = getParamSetCSS(driver, evaluationSetItems[i]);
				String js = "$('"+css+".ng-binding:contains("+evaluationSetItems[i]+")').next().val('"+evaluationSetItemsValues[i]+"');";
				CommonJQ.excuteJS(driver, js);
				LoadingPage.Loading(driver);
			}
		}
		
		
	}
	//获取参数设置的名称样式 
	private static String getParamSetCSS(WebDriver driver,String dataPreprocessingSetItem) {
		String css =null;
		String text = dataPreprocessingSetItem.trim();
		String js1 ="return $('div.ng-binding:contains("+text+")').length";
		String js2 ="return $('span.ng-binding:contains("+text+")').length";
		int num1 = CommonJQ.excuteJStoInt(driver, js1);
		int num2 = CommonJQ.excuteJStoInt(driver, js2);
		if(num1>0){
			 css = "div";
		}
		else if(num2>0){			
			css = "span";
		}else{
			Assert.fail("界面没有找到参数设置名称："+dataPreprocessingSetItem);
		}
		return css;
	}

	/**
	 * 设置数据预处理参数
	 * 
	 * @param driver
	 * @param dataPreprocessingSetItems
	 * @param dataPreprocessingSetValues
	 */
	private static void SetDataPreprocessingParam(WebDriver driver,
			String[] dataPreprocessingSetItems,
			String[] dataPreprocessingSetValues) {
		if(dataPreprocessingSetItems!=null&&!StringUtils.isBlank(dataPreprocessingSetItems[0])){
			if (dataPreprocessingSetItems.length != dataPreprocessingSetValues.length) {
				Assert.fail("参数文件中，预处理设置项与设置值个数不匹配");
			}
			for (int i =0;i<dataPreprocessingSetItems.length;i++) {
				String css = getParamSetCSS(driver, dataPreprocessingSetItems[i]);
				String js = "$('"+css+".ng-binding:contains("+dataPreprocessingSetItems[i]+")').next().val('"+dataPreprocessingSetValues[i]+"');";
				int ss = CommonJQ.excuteJStoInt(driver, "return $('div.ng-binding:contains(栅格主服务小区MR条数的过滤门限)').length");
				CommonJQ.excuteJS(driver, js);
				LoadingPage.Loading(driver);
			}
		}
		
	}

	/**
	 * 选择数据源
	 * 
	 * @param driver
	 * @param cfgData
	 * @param paramData
	 * @param sigData
	 * @param nonEncryptedVideoSourceData
	 * @param encryptedVideoSourceData
	 * @param oTTData
	 * @param electronicMapData
	 * @param lTECharacteristicLibraryData
	 * @param performanceData
	 * @param videoTrafficForecastResultFile
	 * @param cDRData
	 * @param uELogsData
	 */
	private static void SelectDataSources(WebDriver driver, String[] cfgData,
			String[] paramData, String[] sigData,
			String[] nonEncryptedVideoSourceData,
			String[] encryptedVideoSourceData, String[] oTTData,
			String[] electronicMapData, String[] lTECharacteristicLibraryData,
			String[] performanceData, String videoTrafficForecastResultFile,String[] xDR_MRPreprocessData,
			String[] cDRData, String[] uELogsData) {
		if(cfgData!=null&&!StringUtils.isBlank(cfgData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.CfgData, cfgData);
		}
		if(paramData!=null&&!StringUtils.isBlank(paramData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.ParamData, paramData);
		}
		if(sigData!=null&&!StringUtils.isBlank(sigData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.SigData, sigData);
		}
		if(nonEncryptedVideoSourceData!=null&&!StringUtils.isBlank(nonEncryptedVideoSourceData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.NonEncryptedVideoSourceData, nonEncryptedVideoSourceData);
		}
		if(encryptedVideoSourceData!=null&&!StringUtils.isBlank(encryptedVideoSourceData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.EncryptedVideoSourceData, encryptedVideoSourceData);
		}
		if(oTTData!=null&&!StringUtils.isBlank(oTTData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.OTTData, oTTData);
		}
		if(electronicMapData!=null&&!StringUtils.isBlank(electronicMapData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.ElectronicMapData, electronicMapData);
		}
		if(lTECharacteristicLibraryData!=null&&!StringUtils.isBlank(lTECharacteristicLibraryData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.LTECharacteristicLibraryData, lTECharacteristicLibraryData);
		}
		if(performanceData!=null&&!StringUtils.isBlank(performanceData[0])){			
//			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.TrafficStatisticsData, performanceData);
			SeqVMOSTask.selectPFMDataSource(driver, LTE_VMOS_Const.TrafficStatisticsData, performanceData);
		}
		if(videoTrafficForecastResultFile!=null||!StringUtils.isBlank(videoTrafficForecastResultFile)){			
			LTE_Seq_VMOS_Page.setVideoTrafficForecastResultFile(driver, videoTrafficForecastResultFile);
		}
		if(xDR_MRPreprocessData!=null&&!StringUtils.isBlank(xDR_MRPreprocessData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.XDRPreprocessData, xDR_MRPreprocessData);
		}
		if(cDRData!=null&&!StringUtils.isBlank(cDRData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.CDRData, cDRData);
		}
		
		if(uELogsData!=null&&!StringUtils.isBlank(uELogsData[0])){			
			SeqVMOSTask.selectDataSource(driver, LTE_VMOS_Const.UELogsData, uELogsData);
		}
		
	}

	/**
	 * 选择任务类型
	 * 
	 * @param driver
	 * @param taskType
	 */
	public static void SelectTaskType(WebDriver driver, String[] taskType) {
		if(taskType!=null&&!StringUtils.isBlank(taskType[0])){
			LTE_Seq_VMOS_Page.CancelTaskTypeSet(driver);
			for (String type : taskType) {
				LTE_Seq_VMOS_Page.selectTaskType(driver, type);
			}
		}		
	}
	
	//进入分析    成功的任务
	public static void enterSuccessTask(WebDriver driver,String filtername){		//
		Seq_VMOS_ManagePage.selectTaskByStatus(driver,
				LTE_VMOS_Const.AnalysisSuccess);
		String taskid = null;
		getTaskIdFilterWithName(driver,filtername);
		if (filtername!=null) {
			taskid = getTaskIdFilterWithName(driver, filtername);
		}else{
			taskid = Seq_VMOS_ManagePage.getTaskId(driver, 0);
		}
		//点击任务ID，，进入任务详情		
		Seq_VMOS_ManagePage.clickTaskId(driver, taskid);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		checkEnterTaskSuccess(driver);
	}

	//
	public static String getTaskIdFilterWithName(WebDriver driver,
			String filtername) {
		int sum =Seq_VMOS_ManagePage.getTaskNum(driver);
		for (int i = 0; i < sum; i++) {
			String tempTaskname = Seq_VMOS_ManagePage.getTaskNameByRow(driver, i);
			if(tempTaskname.contains(filtername)){
				return Seq_VMOS_ManagePage.getTaskId(driver, i);				
			}
		}
		return null;
		
	}

	private static void checkEnterTaskSuccess(WebDriver driver) {
		String pluginname = LTE_Seq_VMOS_Page.getNewTaskPluginTitleName(driver);
		if (!StringUtils.containsIgnoreCase(pluginname,
				LTE_VMOS_Const.PluginNameOfVMOS)
				&& !StringUtils.containsIgnoreCase(pluginname,
						LTE_VMOS_Const.TaskDetail)) {
			Assert.fail("进入任务详情失败！");
		}
		
	}
	
	public  static void DownLoadReport(WebDriver driver){
		CommonFile.cleanDirectory(ConstUrl.DownLoadPath);
		Seq_VMOS_ManagePage.clickDownLoadReport(driver, 0);
		String reportName = Seq_VMOS_ManagePage.getDownLoadReportName(driver, 0);
		CommonFile.checkExistFiles(ConstUrl.DownLoadPath, reportName);		
	}

	public static void checkMustSelectData(WebDriver driver, String[] taskType,
			String[] mustselected, String[] mustselecttipmessage) {		
		
		SeqVMOSTask.SelectTaskType(driver,taskType);
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);	
		SeqVMOSTask.SkipOneCheck(driver);
		ArrayList<String> mustselecteditems=LTE_Seq_VMOS_Page.getMustChooseDataAnalysisFileName(driver);
		for (String string : mustselected) {
			if(!mustselecteditems.contains(string)){
				Assert.fail("任务类型："+Arrays.toString(taskType)+"必选项验证缺少必选项"+string);
			}
		}
		
		LTE_Seq_VMOS_Page.createTaskNextBTN(driver);
		System.out.println("");
		ArrayList<String> mustselecteditemstipmessage=LTE_Seq_VMOS_Page.getAllMustSelectedTipMessage(driver);
		for (String string : mustselecttipmessage) {
			if(!mustselecteditemstipmessage.contains(string)){
				Assert.fail("任务类型："+Arrays.toString(taskType)+"必选项提示信息不正确！"+string);
			}
			
		}
		return;
		
	}
	
	/**
	 * 设置OTT模式
	 * @param driver
	 * @param filteringMode
	 */
	private static void SetOTType(WebDriver driver,
			String filteringMode) {
		if(filteringMode!=null&&StringUtils.isBlank(filteringMode)==false){
			if (StringUtils.containsIgnoreCase(filteringMode, "ALL")) {
				LTE_Seq_VMOS_Page.selectOTTypeAll(driver,filteringMode);
			}else{
				LTE_Seq_VMOS_Page.selectOTTypeCommon(driver, filteringMode);
			}
		}
	}
	
	private static void SetBusyTimeFilterType(WebDriver driver,	String filteringMode) {
		if(filteringMode!=null&&StringUtils.isBlank(filteringMode)==false){
			LTE_Seq_VMOS_Page.selectBusyTimeFilterType(driver, filteringMode);
		}
	}

	/**
	 * 设置忙时指标
	 * @param driver
	 * @param busyhourKPI
	 */
	private static void SetBusyTimeKPI(WebDriver driver, String busyhourKPI) {
		if(busyhourKPI!=null&&StringUtils.isBlank(busyhourKPI)==false){
			LTE_Seq_VMOS_Page.selectBusyTimeKPI(driver,busyhourKPI);
		}
		
	}

	/**
	 * 设置自定义小时
	 * @param driver
	 * @param customHours
	 */
	private static void SelectCustomTimes(WebDriver driver, String[] customHours) {
		if(customHours!=null){
			LTE_Seq_VMOS_Page.selectCustomTimes(driver,customHours);
		}
	}

	/**
	 * 设置忙时天数
	 * @param driver
	 * @param busyDays
	 */
	private static void SetBusyTimeDays(WebDriver driver, String busyDays) {
		if(busyDays!=null&&StringUtils.isBlank(busyDays)==false){
			LTE_Seq_VMOS_Page.selectBusyTimeDays(driver,busyDays);
		}
		
	}

}
