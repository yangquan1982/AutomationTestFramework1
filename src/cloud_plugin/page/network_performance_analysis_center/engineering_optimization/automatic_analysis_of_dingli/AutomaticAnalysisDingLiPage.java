package cloud_plugin.page.network_performance_analysis_center.engineering_optimization.automatic_analysis_of_dingli;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class AutomaticAnalysisDingLiPage {

	private static final String TextTaskName = "#reportName";
	
	public static final String RBoxDingLi = "#taskTypeCluster";
	public static final String RBoxThemeOptimization = "#taskTypeSpecial";
	
	public static final String TextSelectedOptimiz = "#selectedSpecialNum";//Theme Optimization
	public static final String CBoxAllSelect = "#allSelect";// Select Theme->Select All
	public static final String CBoxBlackPoint = "#chkblackPoint";// 黑点专题  
	public static final String CBoxGuolopFG = "#guolopFGSpecial";//过覆盖专题  
	public static final String CBoxMod3 = "#modGRSpecial";//Mod3干扰专题  
	public static final String CBoxAntennaInterfer = "#chkAntennaInterfer";// 天线接反专题 
	public static final String CBoxNbrLoss = "#chkNbrLossSet";// 邻区错配漏配专题 
	public static final String CBoxLopFG = "#overlopFGSpecial";//重叠覆盖专题 
	public static final String CBoxGPSAbnormal = "#chkGPSAbnormal";// 小区经纬度异常专题  
	public static final String CBoxAntennaAbnormal = "#chkAntennaAbnormal";// 小区经纬度异常专题  
	public static final String CBoxZoomDisturb = "#zoomDisturbSpecial";// 小区经纬度异常专题  
	
	public static final String CBoxWhetherTopN = "#checkBoxOfTopN";
	public static final String TextTopN = "#inputTopN";
	
	public static final String CBoxWhetherGrids = "#checkBoxOfRaster";
	public static final String TextGridsPre = "#inputRasterPre";
	public static final String TexttGridsLater = "#inputRasterLater";
	
	public static final String CBoxWhetherCollectionPoint = "#checkBoxOfisExportSlot";
	
	public static final String BtnParaSet = "#parameterSetting";
	
	public static final String BtnCfg = "confDataSelect";
	public static final String BtnPP = "projectParaSelect";
	public static final String BtnDT = "daDataSelect";
	public static final String BtnPolygon = "polygonDataSelect";
	
	private static final String BtnCommit = "taskSubmitButton";
	private static final String BtnCancle = "taskCannelRetrunButton";
	
	public static String setTaskName(WebDriver driver,String taskName) {
		 taskName = taskName+"_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime()+"TaskName:" + taskName);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCommit, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	public static void cancelBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCancle, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
}
