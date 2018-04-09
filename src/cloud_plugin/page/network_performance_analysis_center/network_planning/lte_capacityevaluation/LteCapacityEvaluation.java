package cloud_plugin.page.network_performance_analysis_center.network_planning.lte_capacityevaluation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class LteCapacityEvaluation {

	public static final String TaskName_ID = "#taskName";
	public static final String NextStep_ID = "#next_Tasknew";// 下一步按钮

	public static final String BtnCfg = "select_configFile";
	public static final String Btnperf = "select_pfmFile";// 话统
	public static final String BtnNeGroupData = "input[id=\"neGroupData\"]";// 选择本地网元组数据
	public static final String ChooseNeGroupBtnOk = "span[class=\"l-btn-text\"]";
	// 参数设置
	// 制式
	public static final String SigBtnTDD = "input[id=\"TDD\"]";// 制式TDD
																// 基于负载的资源评估
	// 门限
	public static final String[] FDDFlow = { "input[id=\"hDownFlow\"]",
			"input[id=\"hAvgPRCCons\"]", "input[id=\"hMaxPRCCons\"]",
			"input[id=\"oDownFlow\"]" };
	// 预测因子
	public static final String[] TextFDDScriberFactor = {
			"input[id=\"flowScriberFactor\"]", "input[id=\"subScriberFactor\"]" };
	public static final String BtnvillageFile = "input[id=\"villageFile\"]";
	public static final String[] CBoxFDDFactor = {
			"input[id=\"cellUserFactor\"]", "input[id=\"dlRepPSUser\"]" };
	// 忙时 是否支持独立忙时指标
	public static final String ListDesparteBusy = "input[id=\"yesOrno\"]";
	public static final String ListNoBusy = "li[id=\"false\"]";
	// 忙时指标
	public static final String SigDLPRB = "input[value=\"1\"]";
	public static final String SigLiuliang = "input[value=\"2\"]";
	// 忙时筛选方式
	public static final String ListBusyChoose = "span[ng-click=\"showSelects1()\"]";// 可见的
	public static final String ListBusyValue = "li[ng-model=\"param.oavgPrccons\"]";// 不可见的value（0~24）
	// 自定义小时
	public static final String BtnSelectAllHours = "span[id=\"seleAllHours\"]";// 全选
	public static final String BtnSelectHours = "div[id=\"selectWeekHours\"] div[ng-click=\"colorClick(item);\"]";// value(0~24)
	// 忙时天数
	public static final String ListBusyDays = "span[ng-click=\"showSelects2()\"]";// 可见的
	public static final String ListBusyDays1 = "span[ng-click=\"showSelects3()\"]";// 可见的
	public static final String ListBusyDaysValue = "li[ng-model=\"param.oUtilizationRatio\"]";// 可见的value（0~6）
	public static final String ListBusyDaysValue1 = "li[ng-model=\"param.tddoUtilizationRatioChina\"]";
	public static final String ListBusyDaysValueother = "li[ng-model=\"param.tddoUtilizationRatioOther\"]";
	// 是否支持完整性校验                                                                                                                   
	public static final String ListTotalCheck = "input[id=\"totalCheckWhole\"]";// 可见的
	public static final String ListTotalCheckTrue = "li[id=\"true\"]";
	// 数据完整性时间区间
	public static final String BtnSelectAllTimes = "a[ng-click=\"selectAllTimes();\"] span[id=\"seleAllHours\"]";// 全选
	public static final String BtnSelectTimes = "div[id=\"selectWeekTimes\"] div[ng-click=\"timesClick(item);\"]";// value(0~24)
	// TDD 场景设置
	public static final String[] TextScene = { "input[id=\"smallBagScene\"]",
			"input[id=\"bigBagScene\"]" };
	public static final String ListselectArea = "li[id=\"areaObjLi\"] div label span";
	public static final String ListselectOther = "li[id=\"other\"]";
	// 中国区 小包、大包、中包other
	// 小包
	public static final String[] SigRrcconnuser = {
			"input[name=\"lteTask.smallRrcconnuser\"]",
			"input[name=\"lteTask.smallUpflow\"]",
			"input[name=\"lteTask.smallDownflow\"]",
			"input[name=\"lteTask.smallUlprbutili\"]",
			"input[name=\"lteTask.smallDlprbutili\"]",
			"input[name=\"lteTask.smallPdcchutili\"]" };
	// 小包规则And
	public static final String[] Listcondition = {
			"span[ng-click=\"showCondition1()\"]",
			"span[ng-click=\"showCondition2()\"]",
			"span[ng-click=\"showCondition3()\"]",
			"span[ng-click=\"showCondition4()\"]" };
	public static final String[] Listcondition1And = {
			"li[ng-click=\"changeCondition1($index)\"]",
			"li[ng-click=\"changeCondition2($index)\"]",
			"li[ng-click=\"changeCondition3($index)\"]",
			"li[ng-click=\"changeCondition4($index)\"]" };
	// 小包规则值
	public static final String[] TextsmallRrcUserRule = {
			"input[id=\"smallRrcUserRule\"]", "input[id=\"smallUpFlowRule\"]",
			"input[id=\"smallDownFlowRule\"]",
			"input[id=\"smallUlprbChannel\"]",
			"input[id=\"smallDlprbChannel\"]",
			"input[id=\"smallPdcchChannel\"]" };
	// 中包
	public static final String[] SigpakageRrcconnuser = {
			"input[name=\"lteTask.pakageRrcconnuser\"]",
			"input[name=\"lteTask.pakageUpflow\"]",
			"input[name=\"lteTask.pakageDownflow\"]",
			"input[name=\"lteTask.pakageUlprbutili\"]",
			"input[name=\"lteTask.pakageDlprbutili\"]",
			"input[name=\"lteTask.pakagePdcchutili\"]" };
	// 中包规则And
	public static final String[] ListMidcondition = {
			"span[ng-click=\"showCondition6()\"]",
			"span[ng-click=\"showCondition7()\"]",
			"span[ng-click=\"showCondition8()\"]" };
	public static final String[] ListMidconditionAnd = {
			"li[ng-click=\"changeCondition6($index)\"]",
			"li[ng-click=\"changeCondition7($index)\"]",
			"li[ng-click=\"changeCondition8($index)\"]" };
	// 中包规则值
	public static final String[] TextpakageRrcuserRule = {
			"input[id=\"pakageRrcuserRule\"]",
			"input[id=\"pakageUpFlowRule\"]",
			"input[id=\"pakageDownFlowRule\"]",
			"input[id=\"pakageUlprbChannel\"]",
			"input[id=\"pakageDlprbChannel\"]",
			"input[id=\"pakagePdcchChannel\"]" };
	// 大包
	public static final String[] SigbigRrcconnuser = {
			"input[name=\"lteTask.bigRrcconnuser\"]",
			"input[name=\"lteTask.bigUpflow\"]",
			"input[name=\"lteTask.bigDownflow\"]",
			"input[name=\"lteTask.bigUlprbutili\"]",
			"input[name=\"lteTask.bigDlprbutili\"]",
			"input[name=\"lteTask.bigPdcchutili\"]" };
	// 大包规则And
	public static final String[] ListBigCondition = {
			"span[ng-click=\"showBigCondition2()\"]",
			"span[ng-click=\"showBigCondition3()\"]",
			"span[ng-click=\"showBigCondition4()\"]" };
	public static final String[] ListBigConditionAnd = {
			"li[ng-click=\"changeBigCondition2($index)\"]",
			"li[ng-click=\"changeBigCondition3($index)\"]",
			"li[ng-click=\"changeBigCondition4($index)\"]" };
	// 大包规则值
	public static final String[] TextbigRrcUserRule = {
			"input[id=\"bigRrcUserRule\"]", "input[id=\"bigUpFlowRule\"]",
			"input[id=\"bigDownFlowRule\"]", "input[id=\"bigUlprbChannel\"]",
			"input[id=\"bigDlprbChannel\"]", "input[id=\"bigPdcchChannel\"]" };
	// 非中国区 大 中 小
	public static final String[] Textbig = {
			"input[id=\"bigDownUserRateBasic\"]",
			"input[id=\"bigDownFlowBasic\"]",
			"input[id=\"bigOnlineUserCountBasic\"]",
			"input[id=\"bigDownCellRateBasic\"]" };
	public static final String[] TextMid = {
			"input[id=\"middleDownUserRateBasic\"]",
			"input[id=\"middleDownFlowBasic\"]",
			"input[id=\"middleOnlineUserCountBasic\"]",
			"input[id=\"middleDownCellRateBasic\"]" };
	public static final String[] TextSmall = {
			"input[id=\"smallDownUserRateBasic\"]",
			"input[id=\"smallDownFlowBasic\"]",
			"input[id=\"smallOnlineUserCountBasic\"]",
			"input[id=\"smallDownCellRateBasic\"]" };
	public static final String CommitBtn = "#commit_Tasknew";
	public static final String CancelBtn = "#cancel_Task";

	public static String setTaskName(WebDriver driver, String taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName + "_" + ZIP.hhmmss();
		LoadingPage.Loading(driver, TaskName_ID);
		driver.findElement(By.id("taskName")).sendKeys(taskName);
		System.out.println(ZIP.NowTime() + "TaskName:" + taskName);
		CommonJQ.click(driver, NextStep_ID, true);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;	
	}

	public static void commitBtnClick(WebDriver driver) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, CommitBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
	
	

}
