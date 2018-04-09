package cloud_plugin.task.genexcloud_pa;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.huawei.hutaf.webtest.htmlaw.BasicAction;

import cloud_plugin.page.genexcloud_pa.AutoPADataPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutoPADataTask {

	/**
	 * <b>Description:</b>RF报告优化
	*
	* @author xwx357019
	* @param driver
	* @param defaultWindowID
	* @param taskName
	* @param cfgfile
	* @param EParafile
	* @param DTfile
	* @return
	* @return String 返回新建任务名称
	 */
	public static String creatRFThemeOptimizTask(WebDriver driver, String defaultWindowID, String DateModel, String[] ThemeType,
			String[] cfgfile,String[] EParafile,String[] DTfile, String secns) {
		//打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openAutoPAData(driver);
		String ReportName = "RF";
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);	
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoPADataPage.RFReport_ID, true,"任务类型:RF报告");
		SwitchDriver.switchDriverToSEQ(driver);
		BasicAction.sleep(2);
		GetDataByTypeTask.ToclickToNoId(driver,AutoPADataPage.SelectedRFOptimiz_ID);//展开选择专题参数
		BasicAction.sleep(2);
//		CommonJQ.click(driver, AutoPADataPage.AllSelect_ID, true,"专题选择:全选");
		for(int i =0;i<ThemeType.length;i++){
			if("Coverage".equalsIgnoreCase(ThemeType[i])){
//				CommonJQ.click(driver, AutoPADataPage.Coverage_ID, true,"专题选择:Coverage");
				GetDataByTypeTask.ToclickToNoId(driver,AutoPADataPage.Coverage_ID);//选择专题参数
				ReportName = "Coverage_RF_Report";
			}else{
				Assert.fail("专题选择错误，请查看是否存在，专题："+ThemeType[i]);
			}
		}
		
//		CommonJQ.click(driver, AutoPADataPage.SelectedOptimiz_ID, true);
//		GetDataByTypeTask.ToclickToNoId(driver, AutoPADataPage.SelectedReport);//选择报告模板 SelectedReport
//		BasicAction.sleep(2);
//		if(ReportMode.equals("VoLTE优化报告.docx")){
//			GetDataByTypeTask.SelectReportMode(driver, "0");
//			ReportName = secns + "VoLTE_docx";
//		}
//		else if(ReportMode.equals("VoLTE优化报告.xlsx")){
//			GetDataByTypeTask.SelectReportMode(driver, "1");
//			ReportName = secns + "VoLTE_xlsx";
//		}
//		BasicAction.sleep(2);
		GetDataByTypeTask.ToclickToNoId(driver,AutoPADataPage.SelectedDates);//展开选择数据类型
		BasicAction.sleep(2);
		GetDataByTypeTask.ToclickToId(driver, DateModel);//选择数据类型
		BasicAction.sleep(2);
		AutoPADataTask.chooseFile(driver, defaultWindowID, EParafile, DTfile);
		BasicAction.sleep(2);
		String Name = AutoPADataPage.setTaskName(driver, ReportName);	
		AutoPADataPage.commitBtnClick(driver);
		return Name;
	}
	/**
	 * <b>Description:</b>专题优化
	*
	* @author xwx357019
	* @param driver
	* @param defaultWindowID
	* @param taskName
	* @param cfgfile
	* @param EParafile
	* @param DTfile
	* @return
	* @return String 返回新建任务名称
	 */
	public static String creatThemeOptimizTask(WebDriver driver, String defaultWindowID, String ReportMode, String DateModel, String[] ThemeType,
			String[] cfgfile,String[] EParafile,String[] DTfile, String secns) {
		//打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openAutoPAData(driver);
		String ReportName = "VoLTE";
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);	
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoPADataPage.VoLTEOptimiz_ID, true,"任务类型:VoLTE&ViLTE优化");
		BasicAction.sleep(2);
		CommonJQ.click(driver, AutoPADataPage.SelectedVoLTEOptimiz_ID, true);//专题选择
		BasicAction.sleep(2);
//		CommonJQ.click(driver, AutoPADataPage.AllSelect_ID, true,"专题选择:全选");
		for(int i =0;i<ThemeType.length;i++){
			if("All".equalsIgnoreCase(ThemeType[i])){
				CommonJQ.click(driver, AutoPADataPage.AllSelect_ID, true,"专题选择:All");
			}else if("ViLTE Access Fail".equalsIgnoreCase(ThemeType[i])){
				CommonJQ.click(driver, AutoPADataPage.VoLTEAccessFailSelect_ID, true,"专题选择:ViLTE Access Fail");
			}else if("ViLTE Call Drop".equalsIgnoreCase(ThemeType[i])){
				CommonJQ.click(driver, AutoPADataPage.VoLTECallDropSelect_ID, true,"专题选择:ViLTE Call Drop");
			}else if("VoLTE MOS".equalsIgnoreCase(ThemeType[i])){
				CommonJQ.click(driver, AutoPADataPage.MOSCallDropSelect_ID, true,"专题选择:VoLTE MOS");
			}else if("Access Fail".equalsIgnoreCase(ThemeType[i])){
				CommonJQ.click(driver, AutoPADataPage.Access_Fail_ID, true,"专题选择:Access Fail");
			}else if("Call Drop".equalsIgnoreCase(ThemeType[i])){
				CommonJQ.click(driver, AutoPADataPage.Call_Drop_ID, true,"专题选择:Call Drop");
			}else{
				Assert.fail("专题选择错误，请查看是否存在，专题："+ThemeType[i]);
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
//		CommonJQ.click(driver, AutoPADataPage.SelectedOptimiz_ID, true);
		GetDataByTypeTask.ToclickToNoId(driver, AutoPADataPage.SelectedReport);//选择报告模板 SelectedReport
		BasicAction.sleep(2);
		if(ReportMode.equals("VoLTE优化报告.docx")){
			GetDataByTypeTask.SelectReportMode(driver, "0");
			ReportName = secns + "VoLTE_docx";
		}
		else if(ReportMode.equals("VoLTE优化报告.xlsx")){
			GetDataByTypeTask.SelectReportMode(driver, "1");
			ReportName = secns + "VoLTE_xlsx";
		}
		BasicAction.sleep(2);
		GetDataByTypeTask.ToclickToNoId(driver,AutoPADataPage.SelectedDates);//展开选择数据类型
		BasicAction.sleep(2);
		GetDataByTypeTask.ToclickToId(driver, DateModel);//选择数据类型
		BasicAction.sleep(2);
		AutoPADataTask.chooseFile(driver, defaultWindowID, EParafile, DTfile);
		BasicAction.sleep(2);
		String Name = AutoPADataPage.setTaskName(driver, ReportName);	
		AutoPADataPage.commitBtnClick(driver);
		return Name;
	}
	/**
	 * <b>Description:</b>SSV报告
	*
	* @author xwx357019
	* @param driver
	* @param defaultWindowID
	* @param taskName
	* @param cfgfile
	* @param EParafile
	* @param DTfile
	* @return
	* @return String 返回新建任务名称
	 */
	public static String creatSSVThemeOptimizTask(WebDriver driver, String defaultWindowID, String ReportMode, String DateModel, String CellID,
			String EParaMode,String[] EParafile,String[] DTfile) {
		//打开 DT数据自动分析任务
		NetworkAnalysisCenterTask.openAutoPAData(driver);
		String ReportName = "SSV";
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);	
		
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoPADataPage.SSVOptimiz_ID, true,"任务类型:指标统计");
		BasicAction.sleep(2);
		SwitchDriver.switchDriverToSEQ(driver);
//		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		GetDataByTypeTask.ToclickToNoId(driver, AutoPADataPage.SelectedReport);//选择报告模板 SelectedReport
		BasicAction.sleep(2);
		if(ReportMode.equals("联通单站验证报告FDD模板_定制.xlsx"))
		{
			GetDataByTypeTask.SelectSSVReportMode(driver, "0");
			ReportName = "FDD_Report";
		}
		else if(ReportMode.equals("单站分析模板.docx"))
		{
			GetDataByTypeTask.SelectSSVReportMode(driver, "1");
			ReportName = "SSV_Report";
		}
		BasicAction.sleep(2);

		

		GetDataByTypeTask.ToclickToNoId(driver,AutoPADataPage.SelectedSSVEngineerDates);//展开选择SSV公参类型
		BasicAction.sleep(2);
		GetDataByTypeTask.ToclickToId(driver, EParaMode);//选择SSV公参类型
		BasicAction.sleep(2);
		GetDataByTypeTask.ToclickToNoId(driver,AutoPADataPage.SelectedDates);//展开选择数据类型
		BasicAction.sleep(2);
		GetDataByTypeTask.ToclickToId(driver, DateModel);//选择数据类型
		BasicAction.sleep(2);
		AutoPADataTask.chooseFile(driver, defaultWindowID, EParafile, DTfile);
		
		GetDataByTypeTask.SSVclickToOpenModalWindow(driver, AutoPADataPage.SSVReportSelect_ID);
		BasicAction.sleep(2);
		AutoPADataPage.setCellID(driver, CellID);
		AutoPADataPage.SelectFirstCellID(driver);
		
		String Name = AutoPADataPage.setTaskName(driver, ReportName);	
		AutoPADataPage.commitBtnClick(driver);
		return Name;
	}	
	
	/**
	 * <b>Description:</b>指标统计
	*
	* @author sWX198085
	* @param driver
	* @param defaultWindowID
	* @param taskName
	* @param StartTime
	* @param EndTime
	* @return
	* @return String 返回新建任务名称
	 */
	public static String creatCounterStatisticsTask(WebDriver driver, String defaultWindowID,String taskName,
			String StartTime,String EndTime) {		
		NetworkAnalysisCenterTask.openAutoPAData(driver);
		System.out.println(ZIP.NowTime()+"Start creat counterStatistics task.");
		CommonJQ.click(driver, TaskReportPage.CreateTask, false);
		// 选中页面中Iframe
		LoadingPage.Loading(driver);	
		
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, AutoPADataPage.CounterStatistics_ID, true);
		CommonJQ.click(driver, AutoPADataPage.ClusterOptimiz_ID, true);
		if(StringUtils.isNotBlank(StartTime)){
			CommonJQ.value(driver, AutoPADataPage.StartTime_ID, StartTime, true);
		}
		if(StringUtils.isNotBlank(EndTime)){
			CommonJQ.value(driver, AutoPADataPage.EndTime_ID, EndTime, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		String Name = AutoPADataPage.setTaskName(driver, taskName);	
		AutoPADataPage.commitBtnClick(driver);
		System.out.println(ZIP.NowTime()+"Creat counterStatistics task over");
		return Name;
	}
	/**
	 * <b>Description:</b>
	*
	* @author sWX198085
	* @param driver
	* @param ErrType
	* @return void
	 */
	public static void DT_ErrorMsg(WebDriver driver,  String ErrType) {
		
		String ExTopNErrMsg = LanguageUtil.translate("Invalid value.");
		String ExRasterErrMsg = LanguageUtil.translate("Input can not be empty");
		String ExCfgErrMsg = LanguageUtil.translate("Configuration data cannot be empty");
		String ExEParaErrMsg = LanguageUtil.translate("Engineering parameters cannot be empty");
		String ExDTErrMsg = LanguageUtil.translate("DT data cannot be empty");
		String ExSpecialErrMsg = LanguageUtil.translate("Select topic(s)");
		String ExStartTimeErrMsg = LanguageUtil.translate("Start time cannot be empty");
		String ExEndTimeErrMsg = LanguageUtil.translate("End time cannot be empty");
		

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		
		if ("TopN".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoPADataPage.TopN_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ExTopNErrMsg,AcErrMsg.equals(AcErrMsg));
		} else if ("Raster".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoPADataPage.Raster_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ExRasterErrMsg,AcErrMsg.equals(AcErrMsg));
		} else if ("Cfg".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoPADataPage.Cfg_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ExCfgErrMsg,AcErrMsg.equals(ExCfgErrMsg));
		} else if ("EPara".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoPADataPage.EPara_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ExEParaErrMsg,AcErrMsg.equals(ExEParaErrMsg));
		}else if ("DT".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoPADataPage.DT_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ExDTErrMsg,AcErrMsg.equals(ExDTErrMsg));
		}else if ("Special".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoPADataPage.Special_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ExSpecialErrMsg,AcErrMsg.equals(ExSpecialErrMsg));
		}else if ("StartTime".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoPADataPage.StartTime_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ExStartTimeErrMsg,AcErrMsg.equals(ExStartTimeErrMsg));
		}else if ("EndTime".equalsIgnoreCase(ErrType)) {
			String AcErrMsg = CommonJQ.text(driver, AutoPADataPage.EndTime_Msg_ID, "");
			Assert.assertTrue("ActualValue:" + AcErrMsg + ",ExpectedValue:" + ExEndTimeErrMsg,AcErrMsg.equals(ExEndTimeErrMsg));
		}else{
			Assert.fail("ErrType is err,ErrType:"+ErrType);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>选择DT相关数据
	*
	* @author xwx357019
	* @param driver
	* @param defaultWindowID
	* @param cfgfile 配置
	* @param EParafile 工参
	* @param DTfile 路测
	* @return void
	 */
	private static void chooseFile(WebDriver driver, String defaultWindowID,String[] EParafile,String[] DTfile) {

//		if (cfgfile.length != 0) {
//			GetDataByTypeTask.chooseFolder(driver, cfgfile, AutoPADataPage.Cfg_ID, defaultWindowID);
//		}
		if (EParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, EParafile, AutoPADataPage.Assistant_EPara_ID, defaultWindowID);
		}
		if (DTfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, DTfile, AutoPADataPage.Assistant_DT_ID, defaultWindowID);
		}
	}
	private static void chooseFile2(WebDriver driver, String defaultWindowID,String[] cfgfile,String[] EParafile,String[] DTfile) {

		if (cfgfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, cfgfile, AutoPADataPage.Cfg2_ID, defaultWindowID);
		}
		if (EParafile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, EParafile, AutoPADataPage.EPara2_ID, defaultWindowID);
		}
		if (DTfile.length != 0) {
			GetDataByTypeTask.chooseFolder(driver, DTfile, AutoPADataPage.DT2_ID, defaultWindowID);
		}
	}
	
	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname：任务名
	 * @param ResultHome：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver,
			String defaultWindowID, String taskname, String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String reportName = downLoad_report( driver, defaultWindowID,  taskname );
		ZIP.depress(EnvConstant.Path_DownLoad + "\\"+reportName, ResultHome);	
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
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		CommonJQ.click(driver, "#showDownLoadDIV a", true);
		String reportName  = TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad,taskname);
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
