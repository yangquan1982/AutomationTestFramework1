package cloud_plugin.page.network_evaluation_assurance_center.video_insight;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;

public class VideoInsightDetailslPage {

	private static final String  taskAddProgress = "div.taskAddProgressDotDiv";
	private static final String  TextSeqAllNet = "input[name=\"taskBean.allNetTargetNum\"]";
	private static final String  TextSeqCell = "input[name=\"taskBean.cellResultNum\"]";
	private static final String  TextSeqGrid = "input[name=\"taskBean.grieResultNum\"]";
	private static final String  TextOTTSheet = "input[name=\"taskBean.agreeShineListNum\"]";
	private static final String  TextISPSheet = "input[name=\"taskBean.agreeCdnListNum\"]";
	private static final String  TextEpara = "input[name=\"taskBean.projectNum\"]";
	private static final String  TextCfg = "input[name=\"taskBean.configeNum\"]";
	private static final String  TextSig = "input[name=\"taskBean.sigNum\"]";
	private static final String  TextNoEncryVideo = "input[name=\"taskBean.xdrNum\"]";
	private static final String  TextEncryVideo = "input[name=\"taskBean.xdretNum\"]";
	private static final String  TextEMap = "input[name=\"taskBean.emapNum\"]";
	private static final String  TextOTT = "input[name=\"taskBean.ottNum\"]";
	
	private static final String TextNoToLow = "input[ng-model=\"taskBean.noVideoUserToLowFlowVideo\"]";
	private static final String TextLVToH = "input[ng-model=\"taskBean.lowVideoUserToHighVideoUser\"]";
	private static final String TextLVUser = "input[ng-model=\"taskBean.lowVideoFlowUserThreshold\"]";
	private static final String TextTopTNum = "input[ng-model=\"taskBean.topTerminalNum\"]";
			
	public static void DataClick(WebDriver driver) {
		boolean flage = CommonJQ.isExisted(driver, taskAddProgress, true);
		if(!flage){
			CommonJQ.click(driver, "div.reportTitle span", true,0, "任务详情");
		}
		CommonJQ.click(driver, taskAddProgress, true,1, "数据选择");
	}
	
	public static void ParaClick(WebDriver driver) {
		boolean flage = CommonJQ.isExisted(driver, taskAddProgress, true);
		if(!flage){
			CommonJQ.click(driver, "div.reportTitle span", true,0, "任务详情");
		}
		CommonJQ.click(driver, taskAddProgress, true,2, "参数设置");
	}

	public static String SeqAllNetVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextSeqAllNet,true,"SEQ整网级指标->输入框");
	}
	
	public static String SeqCellVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextSeqCell,true,"SEQ小区级指标->输入框");
	}
	
	public static String SeqGridVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextSeqGrid,true,"SEQ栅格级指标->输入框");
	}
	
	public static String OTTSheetVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextOTTSheet,true,"OTT协议映射表->输入框");
	}
	
	public static String ISPSheetVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextISPSheet,true,"ISP流向映射表->输入框");
	}
	
	public static String EparaVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextEpara,true,"工参数据->输入框");
	}
	
	public static String CfgVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextCfg,true,"配置数据->输入框");
	}
	public static String SigVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextSig,true,"SIG数据->输入框");
	}
	public static String NoEncryVideoVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextNoEncryVideo,true,"非加密视频源->输入框");
	}
	public static String EncryVideoVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextEncryVideo,true,"加密视频源->输入框");
	}
	public static String EMapVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextEMap,true,"电子地图->输入框");
	}
	public static String OTTVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextOTT,true,"OTT->输入框");
	}
	public static String NoToLowVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextNoToLow,true,"非视频用户向低流量视频用户迁移比例（%）->输入框");
	}
	public static String LVToHVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextLVToH,true,"低视频用户向高流量视频用户迁移比例（%）->输入框");
	}
	public static String LVUserVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextLVUser,true,"低流量视频用户门限（MB）->输入框");
	}
	public static String TopTNumVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextTopTNum,true,"TOP终端个数->输入框");
	}
}
