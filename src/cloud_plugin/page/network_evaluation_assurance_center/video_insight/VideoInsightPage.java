package cloud_plugin.page.network_evaluation_assurance_center.video_insight;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_platform.task.upload_data.UploadDataTask;
import cloud_public.page.LoadingPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.LanguageUtil;
import common.util.SwitchDriver;
import common.util.ZIP;

public class VideoInsightPage {

	private static final String TextTaskName = "#taskName";
	private static final String CBoxCellSee = "input[name=\"taskBean.cellSeeClearly\"]";
	private static final String CBoxSEQGrid = "input[name=\"taskBean.seqGrid\"]";
	private static final String CBoxMRGrid = "input[name=\"taskBean.mrGrid\"]";

	private static final String BtnNext = "span[ng-click=\"next()\"]";
	private static final String BtnPrevious = "span[ng-click=\"previous()\"]";
	private static final String BtnSubmit = "span[ng-click=\"submit()\"]";
	private static final String BtnWholeNetWork = "a[id=\"redirectPowerId0\"]";

	private static final String BtnSEQAllNet = "#allNetTargetData";
	private static final String BtnOTTSheet = "#agreeShineListData";
	private static final String BtnISPSheet = "#agreeCdnListData";
	private static final String BtnSEQCell = "#cellResultData";
	private static final String BtnSEQGrid = "#grieResultData";

	public static final String BtnMessageOK = "div[class=\"messager-button\"] span[class=\"l-btn-text\"]";

	private static final String BtnTempSEQAllNet = "#select_allNetTargetTemplateDownload";
	private static final String BtnTempOTTSheet = "#select_agreeShineListDataTemplateDownload";
	private static final String BtnTempISPSheet = "#select_agreeCdnListDataTemplateDownload";
	private static final String BtnTempSEQCell = "#select_cellResultDataTemplateDownload";
	private static final String BtnTempSEQGrid = "#select_grieResultDataTemplateDownload";
	
	private static final String TextNoToLow = "#noVideoUserToLowFlowVideo";
	private static final String TextLVToH = "#lowVideoUserToHighVideoUser";
	private static final String TextLVUser = "#lowVideoFlowUserThreshold";
	private static final String TextTopTNum = "#topTerminalNum";

	public static String setTaskName(WebDriver driver, String taskName) {
		taskName = taskName + "_" + ZIP.hhmmss();		
		CommonJQ.value(driver, TextTaskName, taskName, "任务名称");
		return taskName;
	}

	public static void CBoxCellSeeClick(WebDriver driver) {
		CommonJQ.click(driver, CBoxCellSee, true, "小区洞察");
	}

	public static void CBoxSEQGridClick(WebDriver driver) {
		CommonJQ.click(driver, CBoxSEQGrid, true, "SEQ栅格");
	}

	public static void CBoxMRGridClick(WebDriver driver) {
		CommonJQ.click(driver, CBoxMRGrid, true, "MR栅格");
	}

	public static void BtnNextClick(WebDriver driver) {
		CommonJQ.click(driver, BtnNext, true, "下一步");
	}

	public static void BtnPreviousClick(WebDriver driver) {
		CommonJQ.click(driver, BtnPrevious, true, "上一步");
	}

	public static void BtnSubmitClick(WebDriver driver) {
		CommonJQ.click(driver, BtnSubmit, true, "提交");
	}

	public static String WholeNetWorkDetailsClick(WebDriver driver) {
		CommonJQ.click(driver, BtnWholeNetWork, true, "整网洞察->报告详情入口");
		return UploadDataTask.waitPage_swithWinID(driver,BtnWholeNetWork, false);
	}
	public static void checkTrafficInsight_Left(WebDriver driver,String TrafficInsight) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Traffic Insight"), true, "视频流量洞察");
	    if(TrafficInsight !=null){
	    	String[] Att=TrafficInsight.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void checkTrafficInsight_Right(WebDriver driver,String TrafficInsight) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Traffic Insight"), true, "视频流量洞察");
	    if(TrafficInsight !=null){
	    	String[] Att=TrafficInsight.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if("No data to display".equalsIgnoreCase(Att[i])){
					 if(CommonJQ.isExisted_ByText(driver, "p", Att[i], true)==false){
						 Assert.fail("Can't find elements:"+Att[i]);
					 }  
				 }else{
					 if(CommonJQ.isExisted_ByText(driver, "td", Att[i], true)==false){
						 Assert.fail("Can't find elements:"+Att[i]);
					 } 
				 }
			 }
	    }
	}
	public static void checkVideoSource(WebDriver driver,String VideoSource) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Video Source"), true, "价值视频源");
	    if(VideoSource !=null){
	    	String[] Att=VideoSource.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void checkValueUser(WebDriver driver,String ValueUser) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Value User"), true, "价值用户");
	    if(ValueUser !=null){
	    	String[] Att=ValueUser.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void checkValueTerminal(WebDriver driver,String ValueTerminal) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Value Terminal"), true, "价值终端");
	    if(ValueTerminal !=null){
	    	String[] Att=ValueTerminal.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void checkTrafficModel_Trend(WebDriver driver,String TrafficModel) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Traffic Model"), true, "话务模型");
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Traffic Trend"), true, "视频流量趋势");
		if(TrafficModel !=null){
	    	String[] Att=TrafficModel.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void checkTrafficModel_3G4GStat(WebDriver driver,String TrafficModel) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Traffic Model"), true, "话务模型");
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "UMTS/LTE Traffic Stat"), true, "3G/4G流量统计");
	    if(TrafficModel !=null){
	    	String[] Att=TrafficModel.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void checkTrafficModel_VideoStat(WebDriver driver,String TrafficModel) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Traffic Model"), true, "话务模型");
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Video Resolution Stat"), true, "视频分分辨率统计");
	    if(TrafficModel !=null){
	    	String[] Att=TrafficModel.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void checkTrafficModel_CDNStat(WebDriver driver,String TrafficModel) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "Traffic Model"), true, "话务模型");
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "CDN Traffic Stat"), true, "CDN流量统计");
	    if(TrafficModel !=null){
	    	String[] Att=TrafficModel.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void checkE2EDelay(WebDriver driver,String E2EDelay) {
		CommonJQ.click_ByText(driver, "div", LanguageUtil.translate("视频洞察", "E2E Delay"), true, "E2E时延");
	    if(E2EDelay !=null){
	    	String[] Att=E2EDelay.split(",");
			 for(int i = 0;i<Att.length;i++){
				 if(CommonJQ.isExisted_ByText(driver, "tspan", Att[i], true)==false){
					 Assert.fail("Can't find elements:"+Att[i]);
				 }
			 }
	    }
	}
	public static void SEQAllChooseFile(WebDriver driver, String filePath) {
		if(filePath != null){
			CommonJQ.click(driver, BtnSEQAllNet, true, "SEQ整网级指标->选择文件");
			CommonFile.ChooseOneFile(filePath);
			CommonJQ.click(driver, BtnMessageOK, true, "提示框成功->确定");
		}
	}

	public static void OTTSheetChooseFile(WebDriver driver, String filePath) {
		if(filePath != null){
			CommonJQ.click(driver, BtnOTTSheet, true, "OTT协议映射表->选择文件");
			CommonFile.ChooseOneFile(filePath);
			CommonJQ.click(driver, BtnMessageOK, true, "提示框成功->确定");	
		}
	}

	public static void ISPSheetChooseFile(WebDriver driver, String filePath) {
		if(filePath != null){
			CommonJQ.click(driver, BtnISPSheet, true, "ISP流向映射表->选择文件");
			CommonFile.ChooseOneFile(filePath);
			CommonJQ.click(driver, BtnMessageOK, true, "提示框成功->确定");	
		}
	}

	public static void SEQCellChooseFile(WebDriver driver, String filePath) {
		if(filePath != null){
			CommonJQ.click(driver, BtnSEQCell, true, "SEQ小区级指标->选择文件");
			CommonFile.ChooseOneFile(filePath);
			CommonJQ.click(driver, BtnMessageOK, true, "提示框成功->确定");	
		}
	}

	public static void SEQGridChooseFile(WebDriver driver, String filePath) {
		if(filePath != null){
			CommonJQ.click(driver, BtnSEQGrid, true, "SEQ栅格级指标->选择文件");
			CommonFile.ChooseOneFile(filePath);
			CommonJQ.click(driver, BtnMessageOK, true, "提示框成功->确定");	
		}
	}

	public static void ParaChooseFile(WebDriver driver, String defaultWindowID,
			String paraFile) {

		if (paraFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#select_lparasData", "工参数据->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			String[] filePath ={paraFile};
			GetDataByTypeTask.chooseFolder(driver, filePath,
					"select_lparasData", defaultWindowID);
		}
	}
	
	public static void CfgChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {

		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#select_lconfData", "配置数据->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"select_lconfData", defaultWindowID);
		}
	}
	public static void SigChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#select_lsigData", "SIG数据->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"select_lsigData", defaultWindowID);
		}
	}
	public static void NoEncryVideoChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#select_lxdrData", "非加密视频源->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"select_lxdrData", defaultWindowID);
		}
	}
	public static void EncryVideoChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#select_xdretData", "加密视频源->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"select_xdretData", defaultWindowID);
		}
	}
	
	public static void eMapChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#select_lemapData", "电子地图->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"select_lemapData", defaultWindowID);
		}
	}
	
	public static void OTTChooseFile(WebDriver driver, String defaultWindowID,
			String[] ChooseFile) {
		if (ChooseFile != null) {
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			LoadingPage.Loading(driver, "#select_lottData", "OTT->选择文件");
			SwitchDriver.switchDriverToSEQ(driver);
			GetDataByTypeTask.chooseFolder(driver, ChooseFile,
					"select_lottData", defaultWindowID);
		}
	}
	
	public static void SetNoToLow(WebDriver driver, String text) {
		if(text!=null){
			CommonJQ.value(driver, TextNoToLow, text, true, "非视频用户向低流量视频用户迁移比例（%）");
		}
	}

	public static void SetLVToH(WebDriver driver, String text) {
		if(text!=null){
			CommonJQ.value(driver, TextLVToH, text, true, "低视频用户向高流量视频用户迁移比例（%）");
		}
	}

	public static void SetLVUser(WebDriver driver, String text) {
		if(text!=null){
			CommonJQ.value(driver, TextLVUser, text, true, "低流量视频用户门限（MB）");
		}
	}

	public static void SetTopTNum(WebDriver driver, String text) {
		if(text!=null){
			CommonJQ.value(driver, TextTopTNum, text, true, "TOP终端个数");	
		}
	}
	
	public static void BtnTempSEQAllNetClick(WebDriver driver,String ResultHome) {
		CommonJQ.click(driver, BtnTempSEQAllNet, true, "SEQ整网级指标->模板下载");
		String TempName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "WholeNet_Resolution");
		FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + TempName,
				ResultHome + "\\" + TempName);
	}
	public static void BtnTempOTTSheetClick(WebDriver driver,String ResultHome) {
		CommonJQ.click(driver, BtnTempOTTSheet, true, "OTT协议映射表->模板下载");
		String TempName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "Ott_Mapping_Resolution");
		FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + TempName,
				ResultHome + "\\" + TempName);
	}
	public static void BtnTempISPSheetClick(WebDriver driver,String ResultHome) {
		CommonJQ.click(driver, BtnTempISPSheet, true, "ISP流向映射表->模板下载");
		String TempName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "Cdn_Mapping_Resolution");
		FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + TempName,
				ResultHome + "\\" + TempName);
	}
	public static void BtnTempSEQCellClick(WebDriver driver,String ResultHome) {
		CommonJQ.click(driver, BtnTempSEQCell, true, "SEQ小区级指标->模板下载");
		String TempName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "Cell_Resolution");
		FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + TempName,
				ResultHome + "\\" + TempName);
	}
	public static void BtnTempSEQGridClick(WebDriver driver,String ResultHome) {
		CommonJQ.click(driver, BtnTempSEQGrid, true, "SEQ栅格级指标->模板下载");
		String TempName = TaskViewPluginTask.waittingDownLoadFile(
				EnvConstant.Path_DownLoad, "Grid_Resolution");
		FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + TempName,
				ResultHome + "\\" + TempName);
	}
}
