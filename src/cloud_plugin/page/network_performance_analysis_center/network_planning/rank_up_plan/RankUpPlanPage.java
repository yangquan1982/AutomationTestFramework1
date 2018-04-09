package cloud_plugin.page.network_performance_analysis_center.network_planning.rank_up_plan;

import org.openqa.selenium.WebDriver;

import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class RankUpPlanPage {
	// 任务类型及功能子类
	private static final String TextTaskName = "#taskName";// Task Name
	public static final String CBoxpJm = "#pJm";// 基于P3排名提升的Benchmark建模
	public static final String CBoxbjm = "#bjm";// Benchmark建模
	public static final String CBoxsigType = "#sigType";// SIG数据预处理
	public static final String CBoxdtType = "#dtType";// DT数据预处理
	public static final String CBoxhtType = "#htType";// 话统数据预处理
	// 参数设置
	public static final String BtnParameterSet = "#parameter_setting";
	public static final String Btnmask = ".mask_loading";
	public static final String Btnmask1 = "div[class=\"mask_loading2 ng-scope\"]";
	public static final String Btnmask2 = "div[class=\"mask_loading ng-hide\"]";
	// ***参数设置 ——BenchMark建模设置
	// 建模场景
	public static final String SigChoseNoca = "input[value=\"0\"]";
	public static final String BtnChooseFile = "span[class=\"l-btn-text\"]";
	public static final String LinkBenchmark = "span[class=\"tit ng-binding\"]";
	public static final String LabelBusy = "span[class=\"setTime_Input4\"]";
	// 提示信息
	public static final String[] ErrorMessage = {
			"span:contains(\"LTE P3数据业务UE Log(FDD)不能为空，请选择文件\")",
			"span:contains(\"LTE SIG数据不能为空，请选择文件\")",
			"span:contains(\"LTE工参数据不能为空，请选择文件\")",
			"span:contains(\"LTE话统数据不能为空，请选择文件\")" };
	// 选择分析运营商
	public static final String Xalakuang = "div[class=\"zz_select msel\"] span";
	public static final String ChooseYunyingshangEPlus = "#E-Plus";
	public static final String ChooseYunyingshangTelekom = "#Telekom";
	public static final String ChooseYunyingshangTelefonica = "#Telefonica";
	public static final String ChooseYunyingshangVodafone = "#Vodafone";
	// UELOG\CDR数字字段映射关系
	public static final String BtnfieldMappingFileData = "input[id=\"targetNeGroupData\"]";
	public static final String BtnCDRCancel = "span[ng-click =\"fileCancel(modelParamFile)\"]";
	public static final String BtnModelDownload = "taskBean.modelDownload";
	// 全部场景
	public static final String BtnselectedRight = "span[ng-click=\"selectedRight()\"]";
	public static final String BtnselectedRightAll = "span[ng-click=\"selectedRightAll()\"]";
	public static final String BtnselectedLeft = "span[ng-click=\"selectedLeft()\"]";
	public static final String BtnselectedLeftAll = "span[ng-click=\"selectedLeftAll()\"]";
	public static final String BtnoccasionMerge = "span[ng-click=\"occasionMerge()\"]";
	public static final String BtndelMergeO = "span[ng-click=\"delMergeO()\"]";
	public static final String TextmergeName = "input[id=\"mergeName\"]";

	// ***参数设置 ——数据预处理
	public static final String LinkDatayuchuli = "span[class=\"tit ng-binding\"]";
	// 选择分析运营商
	public static final String XalakuangData = "div[class=\"zz_select msel\"] span";
	// 话务增长因子值
	public static final String SingChoosehuawuyinzivalue = "#riseFactor";
	// 增长因子文件
	public static final String SingChoosezengzhangfile = "#riseFactor2";
	// 增长因子文件值
	public static final String Btnzengzhangfile = "#targetNeGroupData2";
	public static final String BtnzengzhangfileCancel = ".localFileChooseBtn";
	public static final String BtnzengzhangfilemouldDownload = "#mouldDownload";
	// 话统忙时设置
	public static final String TextBusyone = "#date1";
	public static final String TextBusytwo = "#date2";
	public static final String TextBusythree = "#date3";
	public static final String TextBusyfour = "#date4";
	public static final String TextBusyfive = "#date5";
	public static final String TextBusysix = "#date6";
	// 恢复默认设置
	public static final String BtnRecover = "#recover_Task";
	// 参数设置——确定 按钮
	public static final String BtnOkSet = "#ok_Button";
	// 参数设置——取消 按钮
	public static final String BtnCancelSet = "#cancel_Button";
	// 数据选择
	public static final String BtnUELogFDD = "select_configFile";
	public static final String BtnCDRFDD = "select_Lteprojectparamter";
	public static final String BtnSIG = "select_PData";
	public static final String BtnPara = "select_Warn";
	public static final String BtnYuanshiPerf = "perfData";
	public static final String BtnCsvPerf = "#orgid";
	public static final String BtnELogFDDUMTS = "select_uelogVoice";
	public static final String BtnCDRFDDUMTS = "select_cdrVoice";// 重复id，待修改
	// 数据为空提交时，提示信息
	public static final String TextUELogFDDnull = "div[id=\"dataAnalysisDiv\"] ul[0] li span[class=\"fileChoiceErrorMsg ng-binding\"]";

	// 上传本地文件格式非法时，提示信息
	public static final String fileChoiceErrorMsg = "span[class=\"fileChoiceErrorMsg ng-binding\"]";
	public static final String ParaErrorMsg = "#checkCon";
	// 提交 按钮
	public static final String BtnCommit = "#commit_Tasknew";
	// 取消并返回 按钮
	public static final String BtnCancel = "span[id=\"cancel_Task\"]";
	// 提示信息 ： 请选择LTE P3数据业务CDR(FDD)文件
	public static final String TextTishi = ".ng-binding";
	// 提示信息：确定 按钮
	public static final String BtnOk = "#ok_Button_cdr";
	public static final String ChooseCSVBtnOk = "span[class=\"l-btn-text\"]";
	// 专家求助输入框
	public static final String TextExpertHelp = "textarea[class=\"expertHelpTextArea ng-pristine ng-valid\"]";
	// 上传附件按钮
	public static final String BtnFujian = "#userfilePick";
	public static final String ListHelp = "div[class=\"zz_select msel\"] label input";
	public static final String ListHelpType = "div[class=\"zz_select msel\"] ul li";
	// 专家求助提交按钮
	public static final String BtnExpertHelpCommit = "span[class=\"btn_blue\"] span";
	public static final String TextResult = "div[class=\"expertMessage\"]";

	public static String setTaskName(WebDriver driver, String taskName) {

		taskName = taskName + "_" + ZIP.hhmmss();
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.value(driver, TextTaskName, taskName);
		String text = CommonJQ.getValue(driver, TextTaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TextTaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		System.out.println(ZIP.NowTime() + "TaskName:" + taskName);
		return taskName;
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCommit, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void BtnOkSet(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnOkSet, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void BtnRecover(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnRecover, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void BtnCancel(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, BtnCancel, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
