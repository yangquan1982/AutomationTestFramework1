package cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dt_data;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutoDTDataPage {

	private static final String AppName = "li[id=\"genexspace.plugin.lteclusterPlugStr\"]";
	private static final String AppName2 = "li[id=\"genexspace.plugin.lteclusterInterPlugStr\"]";

	public static void DTClick(WebDriver driver) {
		CommonJQ.click(driver, AppName, true, "插件未找到，插件名：DT数据自动分析");
	}
	public static void ClusterClick(WebDriver driver) {
		CommonJQ.click(driver, AppName2, true, "插件未找到，插件名：簇优化分析");
	}	
	private static final String TaskName_ID = "#reportName";//Task Name
	
	public static final String DTAndOptimiz_ID = "#taskTypeCluster";//DT analysis and optimization	
	public static final String WhetherTopN_ID = "#checkBoxOfTopN";//Whether Top N
	public static final String TopN_ID = "#inputTopN";//Top N 
	public static final String WhetherGrids_ID = "#checkBoxOfRaster";//Whether Grids
	public static final String GridsPre_ID = "#inputRasterPre";//Grids
	public static final String GridsLater_ID = "#inputRasterLater";//Grids
	public static final String WhetherPoint_ID = "#checkBoxOfisExportSlot";//Whether Collection Point Info Is Required
	
	public static final String ThemeOptimiz_ID = "#taskTypeSpecial";//Theme Optimization
	public static final String SelectedOptimiz_ID = "#selectedSpecialNum";//Theme Optimization
	public static final String AllSelect_ID = "#allSelect";// Select Theme->Select All
	public static final String BlackPoint_ID = "#chkblackPoint";// 黑点专题  
	public static final String GuolopFG_ID = "#guolopFGSpecial";//过覆盖专题  
	public static final String Mod3_ID = "#modGRSpecial";//Mod3干扰专题  
	public static final String AntennaInterfer_ID = "#chkAntennaInterfer";// 天线接反专题 
	public static final String NbrLoss_ID = "#chkNbrLossSet";// 邻区错配漏配专题 
	public static final String LopFG_ID = "#overlopFGSpecial";//重叠覆盖专题 
	public static final String GPSAbnormal_ID = "#chkGPSAbnormal";// 小区经纬度异常专题  
	public static final String AntennaAbnormal_ID = "#chkAntennaAbnormal";// 小区经纬度异常专题  
	public static final String ZoomDisturb_ID = "#zoomDisturbSpecial";// 小区经纬度异常专题  
	
	public static final String CompareReport_ID = "#taskTypeCompReport";//Comparison Report
	
	public static final String CounterStatistics_ID = "#taskTypeTargetReport";//Counter Statistics
	public static final String ClusterOptimiz_ID = "#targetTypeCluster";//Cluster Optimization Statistics 
	public static final String StartTime_ID = "#date1";//Start Time
	public static final String EndTime_ID = "#date2";//End Time 
	
	public static final String Cfg_ID = "confDataSelect";//Configuration Data
	public static final String EPara_ID = "projectParaSelect";//Engineering Parameter
	public static final String DT_ID = "daDataSelect";//DT Data
	public static final String Polygon_ID = "polygonDataSelect";
	
	
	
	public static final String Cfg2_ID = "confDataSelect_comp";//Configuration Data
	public static final String EPara2_ID = "projectParaSelect_comp";//Engineering Parameter
	public static final String DT2_ID = "daDataSelect_comp";//DT Data
	
	private static final String SubmitBtn_ID = "#taskSubmitButton";
	private static final String CancelBtn_ID = "#taskCannelRetrunButton";
	
	public static final String TaskName_Msg_ID = "#rptNamPmt";
	public static final String TopN_Msg_ID = "#topNPmt";
	public static final String Raster_Msg_ID = "#rasterPmt";
	
	public static final String Cfg_Msg_ID = "#cnfPmt";
	public static final String EPara_Msg_ID = "#paraHitFDD";
	public static final String DT_Msg_ID = "#dtDatPmt";
	
	public static final String Special_Msg_ID = "#specialPmt";
	
	public static final String StartTime_Msg_ID = "#startTimeCheck";
	public static final String EndTime_Msg_ID = "#endTimeCheck";
	public static final String DownloadReport = "a[class=\"icon_down ml10\"]";
	
	public static final String BtnPara = "a[onclick=\"fetchPara();\"] span";

	public static final String BtnParaOK = "#confirmConfig";
	
	public static final String TitleMod3 = "div#modGRDiv span#tit1";
	public static final String TextMod3_RSRP = "input[name=\"specialParameter.mod3RsrpThreshold\"]";
	public static final String TextMod3_OffSet = "input[name=\"specialParameter.mod3OffSet\"]";
	public static final String TextMod3_SINR  = "input[name=\"specialParameter.mod3SinrThreshold\"]";
	public static final String TextMod3_PicNum  = "input[name=\"specialParameter.mod3PicNum\"]";
	
	public static final String TitleLapCover = "div#overlapCoverDiv span#tit2";
	public static final String TextLapCover_OffSet = "input[name=\"specialParameter.overlayRsrpOffSet\"]";
	public static final String TextLapCover_Value = "input[name=\"specialParameter.overlayRsrpValue\"]";
	public static final String TextLapCover_PowerfulArea = "input[name=\"specialParameter.overlayPowerfulArea\"]";
	
	public static final String TextLapCover_minOverlay0 = "input#minValueOverlay0";
	public static final String TextLapCover_maxOverlay0 = "input#maxValueOverlay0";
	public static final String TextLapCover_minOverlay1 = "input#minValueOverlay1";
	public static final String TextLapCover_maxOverlay1 = "input#maxValueOverlay1";
	public static final String TextLapCover_minOverlay2 = "input#minValueOverlay2";
	public static final String TextLapCover_maxOverlay2 = "input#maxValueOverlay2";
	
	
	public static final String TitleGuoCover = "div#guolopCoverDiv span#tit3";
	public static final String TextGuoCover_disThreshold = "input[name=\"specialParameter.disThreshold\"]";
	public static final String TextGuoCover_OffSet = "input[name=\"specialParameter.rsrpOffSet\"]";
	public static final String TextGuoCover_picNum = "input[name=\"specialParameter.picNum\"]";
	public static final String TextGuoCover_DifThreshold = "input[name=\"specialParameter.rsrpDifThreshold\"]";
	
	public static final String TitleZoom = "div#zoomDisturbSpecialDiv span#tit6";
	public static final String TextZoom_RsrpDown = "input[name=\"specialParameter.zoomRsrpDownValue\"]";
	public static final String TextZoom_RsrpUp = "input[name=\"specialParameter.zoomRsrpUpValue\"]";
	public static final String TextZoom_SinrDown = "input[name=\"specialParameter.zoomSinrDownValue\"]";
	public static final String TextZoom_SinrUp = "input[name=\"specialParameter.zoomSinrUpValue\"]";	
	public static final String TextZoom_DifRsrp = "input[name=\"specialParameter.zoomDifRsrpValue\"]";
	
	public static final String TextZoom_minZoom0 = "input#minValueZoom0";
	public static final String TextZoom_maxZoom0 = "input#maxValueZoom0";
	public static final String TextZoom_minZoom1 = "input#minValueZoom1";
	public static final String TextZoom_maxZoom1 = "input#maxValueZoom1";
	
	public static final String BtnParaSet = "div#paraSetForSpecial span#parameterSetting";
	public static final String BtnConfirm = "#confirmForSpecial";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		 taskName = taskName+"_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TaskName_ID, taskName);
		String text = CommonJQ.getValue(driver, TaskName_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TaskName_ID, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		return taskName;
	}
	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, SubmitBtn_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CancelBtn_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void BtnThemeParaClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnParaSet, true,"参数设置");
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void BtnThemeParaOKClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnConfirm, true,"参数设置->确定");
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void setMod3Para(WebDriver driver,String Mod3Para) {
		if(Mod3Para !=null){
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			
			boolean flage = CommonJQ.isExisted(driver, TextMod3_RSRP, true);
			if(flage==false){
				CommonJQ.click(driver, TitleMod3, true,"Mod3干扰专题");
			}
			String[] Vale = Mod3Para.split(",");
			String[] Name = {TextMod3_RSRP,TextMod3_OffSet,TextMod3_SINR,TextMod3_PicNum};
			String[] Message = {"Mod3干扰专题->RSRP","Mod3干扰专题->服务小区 RSRP- 邻小区 RSRP","Mod3干扰专题->SINR","Mod3干扰专题->效果图最大数量"};
			for(int i =0;i<Vale.length;i++){
				CommonJQ.value(driver, Name[i], Vale[i], true,Message[i]);
			}
			SwitchDriver.switchDriverToSEQ(driver);	
		}
	}
	public static void setOverCover(WebDriver driver,String OverCoverPara) {
		if(OverCoverPara !=null){
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			boolean flage = CommonJQ.isExisted(driver, TextLapCover_OffSet, true);
			if(flage==false){
				CommonJQ.click(driver, TitleLapCover, true,"重叠覆盖专题");
			}
			String[] Vale = OverCoverPara.split(",");
			String[] Name = {TextLapCover_OffSet,TextLapCover_Value,TextLapCover_PowerfulArea,
					TextLapCover_minOverlay0,TextLapCover_maxOverlay0,
					TextLapCover_minOverlay1,TextLapCover_maxOverlay1,
					TextLapCover_minOverlay2,TextLapCover_maxOverlay2};
			String[] Message = {"重叠覆盖专题->最强小区RSRP","重叠覆盖专题->最强小区RSRP的差值","重叠覆盖专题->最强小区数量",
					"重叠覆盖专题->采样点1对应的重叠覆盖小区个数min","重叠覆盖专题->采样点1对应的重叠覆盖小区个数max",
					"重叠覆盖专题->采样点2对应的重叠覆盖小区个数min","重叠覆盖专题->采样点2对应的重叠覆盖小区个数max",
					"重叠覆盖专题->采样点3对应的重叠覆盖小区个数min","重叠覆盖专题->采样点3对应的重叠覆盖小区个数max"};
			for(int i =0;i<Vale.length;i++){
				CommonJQ.value(driver, Name[i], Vale[i], true,Message[i]);
			}
			SwitchDriver.switchDriverToSEQ(driver);	
		}
	}
	public static void setGuoCover(WebDriver driver,String GuoCoverPara) {
		if(GuoCoverPara !=null){
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			boolean flage = CommonJQ.isExisted(driver, TextGuoCover_disThreshold, true);
			if(flage==false){
				CommonJQ.click(driver, TitleGuoCover, true,"过覆盖专题");
			}
			String[] Vale = GuoCoverPara.split(",");
			String[] Name = {TextGuoCover_disThreshold,TextGuoCover_OffSet,TextGuoCover_picNum,
					TextGuoCover_DifThreshold};
			String[] Message = {"过覆盖专题->覆盖距离","过覆盖专题->与服务小区RSRP差异门限","过覆盖专题->效果图最大数量",
					"过覆盖专题->服务小区RSRP门限"};
			for(int i =0;i<Vale.length;i++){
				CommonJQ.value(driver, Name[i], Vale[i], true,Message[i]);
			}
			SwitchDriver.switchDriverToSEQ(driver);	
		}
	}
	public static void setZoom(WebDriver driver,String ZoomPara) {
		if(ZoomPara !=null){
			SwitchDriver.switchDriverToFrame(driver, "//iframe");
			boolean flage = CommonJQ.isExisted(driver, TextZoom_RsrpDown, true);
			if(flage==false){
				CommonJQ.click(driver, TitleZoom, true,"小区自干扰专题");
			}
			String[] Vale = ZoomPara.split(",");
			String[] Name = {TextZoom_RsrpDown,TextZoom_RsrpUp,TextZoom_SinrDown,
					TextZoom_SinrUp,TextZoom_DifRsrp,TextZoom_minZoom0,TextZoom_maxZoom0,
					TextZoom_minZoom1,TextZoom_maxZoom1};
			String[] Message = {"小区自干扰专题->服务小区RSRP门限Down","小区自干扰专题->服务小区RSRP门限Up",
					"小区自干扰专题->服务小区SINR门限Down","小区自干扰专题->服务小区SINR门限Up",
					"小区自干扰专题->与最强邻区RSRP差异门限",
					"小区自干扰专题->采样点1对应的重叠覆盖小区个数min","小区自干扰专题->采样点1对应的重叠覆盖小区个数max",
					"小区自干扰专题->采样点2对应的重叠覆盖小区个数min","小区自干扰专题->采样点2对应的重叠覆盖小区个数max"};
			for(int i =0;i<Vale.length;i++){
				CommonJQ.value(driver, Name[i], Vale[i], true,Message[i]);
			}
			SwitchDriver.switchDriverToSEQ(driver);	
		}
	}
}
