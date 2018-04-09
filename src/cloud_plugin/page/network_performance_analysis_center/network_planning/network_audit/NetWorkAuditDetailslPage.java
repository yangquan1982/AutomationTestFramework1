package cloud_plugin.page.network_performance_analysis_center.network_planning.network_audit;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;

public class NetWorkAuditDetailslPage {
	
	private static final String  TextTopN = "input[name=\"task.topn\"]";
	private static final String  TextNoPassCell = "input[name=\"task.notPassCellRatio\"]";
	private static final String  TextRSRPThreshold = "input[name=\"task.rsrpEffectiveThreshold\"]";
	private static final String  TextCfg = "input[name=\"task.confNum\"]";
	private static final String  TextPfm = "input[name=\"task.pfmNum\"]";
	private static final String  TextPara = "input[name=\"task.parasNum\"]";
	private static final String  TextMR = "input[name=\"task.mrNum\"]";
	private static final String  TextMML = "input[name=\"task.mmlNum\"]";
	private static final String  TextProperty = "input[name=\"task.propertyNum\"]";
	private static final String  TextSelfDefine = "input[name=\"task.selfDefineNum\"]";
	private static final String  TextChr = "input[name=\"task.chrNum\"]";
	private static final String  TextAlarm = "input[name=\"task.alarmNum\"]";
	private static final String  TextNodebConf = "input[name=\"task.nodebconfNum\"]";
	private static final String  TextNodebPfm = "input[name=\"task.alarmNum\"]";
	private static final String  TextNodebAlarm = "input[name=\"task.nodebalarmNum\"]";
	private static final String  TextNodebMML = "input[name=\"task.nodebmmlNum\"]";
	private static final String  TextNodebLicense = "input[name=\"task.nodeblicenseNum\"]";
	private static final String  TextIncrement = "input[name=\"task.incrementFile\"]";
	
	
	public static String TopNVal(WebDriver driver) {
		boolean flage = CommonJQ.isExisted(driver, TextTopN, true);
		if(!flage){
			CommonJQ.click(driver, "div.dataAnalysisTitle span", true,0, "任务详情");
		}
		return CommonJQ.getValue(driver, TextTopN,true,"任务参数->TOPN");
	}
	public static String NoPassCellVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextNoPassCell,true,"任务参数->不达标小区占比(%)");
	}
	public static String RSRPThresholdVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextRSRPThreshold,true,"任务参数->RSRP有效门限(dBm)");
	}
	public static String CfgVal(WebDriver driver) {
		boolean flage = CommonJQ.isExisted(driver, TextCfg, true);
		if(!flage){
			CommonJQ.click(driver, "div.dataAnalysisTitle span", true,0, "任务详情");
		}
		return CommonJQ.getValue(driver, TextCfg,true,"配置数据->输入框");
	}
	public static String PfmVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextPfm,true,"话统数据->输入框");
	}
	public static String ParaVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextPara,true,"工程参数->输入框");
	}
	public static String MRVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextMR,true,"外部CHR数据->输入框");
	}
	public static String ChrVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextChr,true,"CHR数据->输入框");
	}
	public static String MMLVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextMML,true,"MML数据->输入框");
	}
	public static String PropertyVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextProperty,true,"特征库数据->输入框");
	}
	public static String SelfDefineVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextSelfDefine,true,"自定义数据->输入框");
	}
	public static String AlarmVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextAlarm,true,"告警数据->输入框");
	}
	public static String NodebConfVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextNodebConf,true,"NodeB配置数据->输入框");
	}
	public static String NodebPfmVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextNodebPfm,1,"NodeB 话统数据->输入框");
	}
	public static String NodebAlarmVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextNodebAlarm,true,"NodeB 告警数据->输入框");
	}
	public static String NodebMMLVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextNodebMML,true,"NodeB MML数据->输入框");
	}
	public static String NodebLicenseVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextNodebLicense,true,"NodeB License数据->输入框");
	}
	public static String IncrementVal(WebDriver driver) {
		return CommonJQ.getValue(driver, TextIncrement,true,"增长因子数据->输入框");
	}

}
