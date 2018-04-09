package cloud_plugin.page.network_performance_analysis_center.network_planning.lte_seq_vmos;

import java.util.ArrayList;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_public.page.HTML_Element;
import cloud_public.page.LoadingPage;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.LOG;
import common.util.SeleniumUtil;
import common.util.SwitchDriver;

public class LTE_Seq_VMOS_Page {
	
	
	public static final int MAXTime =240;//任务创建提交后的等待时间（秒）
	public static final String PluginId = "dl[id=\"genexspace.plugin.lteseqvmosPlugStrPro\"]";//Task Name
	private static final String createTaskBTN = "#createTask";
	private static final String deleteTaskBTN = "#deleteTaskSpan";
	private static final String downLoadReportsBTN = "#downLoadReports";
	private static final String taskRetryBTN = "#taskRetry";
	private static final String taskNameText = "taskName";
	private static final String taskTypeCheckboxClass = ".taskAddSelectTaskCheckboxChecked";
	private static final String taskTypeUnCheckboxClass = ".taskAddSelectTaskCheckboxUnchecked";
	private static final String dataAnalysisFileName = ".dataAnalysisFileName.ng-binding";
	private static final String dataAnalysisFileNameMustChooseClass = ".requireMark.ng-scope";
	private static final String dataAnalysisDiv = "#dataAnalysisDiv";
	private static final String fileFolderClass = ".btnGray.ng-scope.ng-binding";
	public static String pluginTitle="#pluginTitle";
	
	//打开插件
	public static void openApp(WebDriver driver){
		String script = "$('a:contains("+LTE_VMOS_Const.PluginNameOfVMOS+")').length>0;";
		if(CommonJQ.excuteJStoBoolean(driver, script)){
			CommonJQ.excuteJS(driver, "$('a:contains("+LTE_VMOS_Const.PluginNameOfVMOS+")').click();");	
			LoadingPage.Loading(driver);
		}else{
			Assert.fail("没有找到名称为："+LTE_VMOS_Const.PluginNameOfVMOS+"的App");
		}
		
	}
	//选择文件夹
	public static void selectFileByName(WebDriver driver,String fileName, String defaultWindowID) {
		LTE_Seq_VMOS_Page.clickSelectFile(driver, fileName);

		String newHnadle =CommonWD.getWindowHandle(driver, defaultWindowID);
		System.out.println("选择数据源获取新的句柄:"+newHnadle);
		if(newHnadle==null){
			Assert.fail("选择文件夹操作没有打开新的页面！！");
		}
		driver.switchTo().window(newHnadle);
		LoadingPage.Loading(driver);
		
	}
	
	public static void clickPFMSelectFile(WebDriver driver, String defaultWindowID) {
		SwitchDriver.switchDriverToSEQ(driver);		
		String script="$('iframe').contents().find('#yspfm').click();";
		CommonJQ.setTimeout(driver,script, 2000);
		String newHnadle =CommonWD.getWindowHandle(driver, defaultWindowID);
		System.out.println("选择数据源获取新的句柄:"+newHnadle);
		if(newHnadle==null){
			Assert.fail("选择文件夹操作没有打开新的页面！！");
		}
		driver.switchTo().window(newHnadle);
		LoadingPage.Loading(driver);
		
	}
	public static void clickSelectFile(WebDriver driver,String fileName){		
		SwitchDriver.switchDriverToSEQ(driver);		
		String script="$('iframe').contents().find('"+dataAnalysisDiv+"').find('"+dataAnalysisFileName+":contains("+fileName+")').filter(':visible').parent().parent().find('"+fileFolderClass+"').get(0).click();";
		String script1="$('iframe').contents().find('"+dataAnalysisDiv+"').find('"+dataAnalysisFileName+":contains("+fileName+")').filter(':visible').length;";
		if(CommonJQ.excuteJStoInt(driver, script1)==0){
			Assert.fail("当前任务类型没有数据源类型为"+fileName+"可选择，请检查参数表配置是否正确！");
		}
		CommonJQ.setTimeout(driver,script, 2000);
		//CommonJQ.excuteJS(driver, script);
		Pause.pause(1000);	
	}
	
	public static void clickCancelSelectFile(WebDriver driver,String fileName){		
		SwitchDriver.switchDriverToSEQ(driver);		
		String script="$('iframe').contents().find('"+dataAnalysisDiv+"').find('"+dataAnalysisFileName+":contains("+fileName+")').filter(':visible').parent().parent().find('"+fileFolderClass+"').click();";
		CommonJQ.setTimeout(driver,script, 2000);
		//CommonJQ.excuteJS(driver, script);
		Pause.pause(1000);	
	}
	
	public static void clickSelectFile2(WebDriver driver,String fileName){		
		SwitchDriver.switchDriverToSEQ(driver);		
		String script="$('iframe').contents().find('"+dataAnalysisDiv+"').find('"+dataAnalysisFileName+":contains("+fileName+")').filter(':visible').parent().parent().find('"+fileFolderClass+"').find('input').click();";
		CommonJQ.setTimeout(driver,script, 2000);
		//CommonJQ.excuteJS(driver, script);
		Pause.pause(1000);	
	}

	public static void clickCreateTaskBTN(WebDriver driver) {
		LOG.info("点击新建任务");
		CommonJQ.click(driver, createTaskBTN,true,"点击新建任务按钮");
		LoadingPage.Loading(driver);	
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
	}
	
	public static void clickDeleteTaskBTN(WebDriver driver) {
		CommonJQ.click(driver, deleteTaskBTN,true);
		LoadingPage.Loading(driver);		
	}
	
	public static void clickDownLoadReportsBTN(WebDriver driver) {
		CommonJQ.click(driver, downLoadReportsBTN,HTML_Element.SPAN,true);
		LoadingPage.Loading(driver);		
	}
	
	public static void clicktaskRetryBTN(WebDriver driver) {
		CommonJQ.click(driver, taskRetryBTN,HTML_Element.SPAN,true);
		LoadingPage.Loading(driver);		
	}
	
	
	public static void deleteOKBTN(WebDriver driver) {
		String script="$('.messager-button').find('span:contains(确定)').eq(0).click()";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);		
	}
	
	public static void deleteCancelBTN(WebDriver driver) {
		String script="$('.messager-button').find('span:contains(取消)').eq(0).click()";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);		
	}
	
	
	
	public static void createTaskNextBTN(WebDriver driver) {
		String script="$('.taskAddButtonDiv').find('span:contains(下一步)').click()";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);		
	}
	
	public static void createTaskPreviousBTN(WebDriver driver) {
		String script="$('.taskAddButtonDiv').find('span:contains(上一步)').click()";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);		
	}
	
	public static void createTaskCancelBTN(WebDriver driver) {
		String script="$('.taskAddButtonDiv').find('span:contains(取消)').click()";
		CommonJQ.excuteJS(driver, script);
		SwitchDriver.switchDriverToSEQ(driver);
		LTE_Seq_VMOS_Page.waitMask(driver, "创建任务取消按钮界面响应超时！！");
	}
	
	private static void waitMask(WebDriver driver,String assertInfor){
		
		for (int i = 0; i < 100; i++) {
			String script2 = "return $('iframe').contents().find('.mask_loading:visible').length;";
			int mask = CommonJQ.excuteJStoInt(driver, script2);
			if(mask>0){
				Pause.pause(1000);
			}else{
				break;
			}
		}
		int i =0;
		for (; i < MAXTime; i++) {
			if (!CommonJQ.isExisted(driver, createTaskBTN,true)) {
				Pause.pause(1000);
			}else{break;}
		}
		if(i==MAXTime){
			Assert.fail(assertInfor);
		}
		LoadingPage.Loading(driver);
		
	}
	
	public static void createTaskCommitBTN(WebDriver driver) {
		String script="$('.taskAddButtonDiv').find('span:contains(提交)').click()";
		CommonJQ.excuteJS(driver, script);		
		SwitchDriver.switchDriverToSEQ(driver);
		Pause.pause(1000);
		LTE_Seq_VMOS_Page.waitMask(driver, "创建任务点击提交超时！！");		
	}

	

	public static void setTaskName(WebDriver driver, String taskName) {
		SeleniumUtil.val_ById(driver, LTE_Seq_VMOS_Page.taskNameText, taskName);
		LoadingPage.Loading(driver);
	}
	//获取片区信息
	public static String getRegion(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.REGION+")').next().val();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	public static boolean isRegionDisabled(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.REGION+")').next().is(':disabled');";
		return CommonJQ.excuteJStoBoolean(driver, script);
	}
	
	// 获取代表处信息
	public static String getBranch(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.BRANCH+")').next().val();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	public static boolean isBranchDisabled(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.BRANCH+")').next().is(':disabled');";
		return CommonJQ.excuteJStoBoolean(driver, script);
	}
	// 获取运营商信息
	public static String getOperator(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.OPERATOR+")').next().val();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	
	// 获取项目名称
	public static String getNewTaskProjectName(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.ITEM+")').next().val();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	public static boolean isItemNameDisabled(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.ITEM+")').next().is(':disabled');";
		return CommonJQ.excuteJStoBoolean(driver, script);
	}
	
	// 获取制式
	public static String getRatName(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.RAT+")').next().val();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	public static boolean isRatDisabled(WebDriver driver) {
		String  script="return $('.taskContent').find('span:contains("+LTE_VMOS_Const.RAT+")').next().is(':disabled');";
		return CommonJQ.excuteJStoBoolean(driver, script);
	}
	//获取异常提示信息	
	
	private static String windowMessager=".panel.window.messager-window";
	private static String windowMessagerContent=".messageContentDiv";
	
	public static String getTipMessage(WebDriver driver) {
		
		String js = "return $('"+windowMessager+"').find('"+windowMessagerContent+"').text();";
		return CommonJQ.excuteJStoString(driver, js);
	}
	
	public static void clickTipMessageOK(WebDriver driver) {
		String js = "return $('"+windowMessager+"').find('.l-btn-text').text();";
		CommonJQ.excuteJS(driver, js);
		LoadingPage.Loading(driver);
	}
	// 提示信息是否显示
	public static boolean isTipMessageVisible(WebDriver driver) {
		return CommonJQ.isVisible(driver, windowMessager);
	}
	
	
	//选择任务类型
	public static void CancelTaskTypeSet(WebDriver driver) {
		while (CommonJQ.length(driver, taskTypeCheckboxClass, true)>0) {
			String js ="$('"+taskTypeCheckboxClass+"').filter(':visible').get(0).click();";
			CommonJQ.excuteJS(driver, js);
			LoadingPage.Loading(driver);
		}		
	}
	
	public static String TaskTypeTipsClass=".req.ng-binding";
	public static String getTaskTypeTipMessage(WebDriver driver) {
		return CommonJQ.text(driver, TaskTypeTipsClass, "",true);
	}
	public static int getTaskTypeNumber(WebDriver driver) {
		String script ="return $('"+taskTypeCheckboxClass+"').filter(':visible').length;";
		return CommonJQ.excuteJStoInt(driver, script);
	}
	
	
	public static void	selectTaskType(WebDriver driver, String taskType) {		
		String  script="$('.taskSelect').find('em:contains("+taskType+")').parent().get(0).click();";
		CommonJQ.excuteJS(driver, script);
		LoadingPage.Loading(driver);
		return ;
	}
	
	//获取已选择任务类型名称
	public static ArrayList<String> getSelectedTaskTypes(WebDriver driver){
		ArrayList<String> selectedTypes = new ArrayList<String>();
		long selects = CommonJQ.length(driver, taskTypeCheckboxClass, true);
		for (int i = 0; i < selects; i++) {
			String type = CommonJQ.text(driver, taskTypeCheckboxClass, "",i);
			if(type!=null){
				type=type.trim();
			}
			selectedTypes.add(type);
		}
		return selectedTypes;
	}
	
	//点击参数设置	
	public static void	clickParamSettingBTN(WebDriver driver, String taskName) {
		// CommonJQ.value(driver, LTE_Seq_VMOS_Page.taskNameText,taskName);
		// LoadingPage.Loading(driver);
		return ;
	}
	
	// 栅格主服务小区MR条数的过滤门限
	
	private static final String primaryServicefilterThreshold = "#primaryServicefilterThreshold";
	// 设置栅格MR条数的过滤门限
	
	private static final String filterThreshold = "#filterThreshold";
	//恢复默认值设置
	//设置完后点击确认
	//点击取消按钮
	// 容量忙时Input ID
	private static final String assessvolume = "#assessvolume";
	// 图例设置（视频流量）input ID
	private static final String videoflow = "#videoflow";
	// 图例设置（价值用户）input ID
	private static final String valueuser = "#valueuser";
	// 图例设置（价值终端）input ID
	private static final String valueterminal = "#valueterminal";
	// 图例设置（价值视频源）input ID
	private static final String valuevideo = "#valuevideo";
	// 图例设置（小区体验）input ID
	private static final String villagepractice = "#villagepractice";
	// 图例设置（定界结果）input ID
	private static final String delimitresult = "#delimitresult";
	//图例设置（无线原因分析结果）input ID
	private static final String wirelessreasonanalyseresult = "#wirelessreasonanalyseresult";
	// TOPN用户个数input ID
	private static final String usernumber = "#usernumber";
	// TOPN终端个数 input ID
	private static final String terminalnumber = "#terminalnumber";
	// 图例设置（vMOS 360P 480P 720P 1080P） input ID
	private static final String OMOS = "#OMOS";
	// 图例设置（RSRP,RSRQ,CQI, BLER,DLPRBUtilization_10s） input ID
	private static final String RSRP = "#RSRP";
	// TOPN视频源个数） input ID
	private static final String TopNvideonubmer = "#TopNvideonubmer";
	// TOPN业务体验差小区个数） input ID
	private static final String TopNvocationalworkvillagenubmer = "#TopNvocationalworkvillagenubmer";
	//容量评估忙时设置
	//完成容量评估忙时设置后，点击取消设置
	//图例设置（视频流量）
	//设置图例设置（价值用户）
	//图例设置（价值终端）为默认值时
	//图例设置（价值视频源）
	//图例设置（vMOS 360P 480P 720P 1080P）为默认值
	//图例设置（小区体验）为默认值
	//图例设置（RSRP,RSRQ,CQI, BLER,DLPRBUtilization_10s）为默认值
	//图例设置（定界结果）为默认值
	//图例设置（无线原因分析结果）为默认值
	//评估”项设置,TOPN用户个数为默认
	//“评估”项设置，TOPN终端个数为合法
	//“评估”项设置，TOPN视频源个数
	//“评估”项设置，TopN业务体验差小区个数
	
	// 数据分析数据选项个数dataAnalysisFileName
	public static long getDataAnalysisFileNameNumber(WebDriver driver) {		
		return CommonJQ.length(driver, dataAnalysisFileName);
	}
	//必选项个数
	public static long getDataAnalysisFileNameMustChooseNumber(WebDriver driver) {		
		return CommonJQ.length(driver, dataAnalysisFileNameMustChooseClass);
	}
	// 获取所有数据分析文件类型名称
	public static ArrayList<String> getAllDataAnalysisFileName(WebDriver driver) {
		ArrayList<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < getDataAnalysisFileNameNumber(driver); i++) {
			String temp = CommonJQ.text(driver, dataAnalysisFileName, null, i);
			fileNames.add(temp);		}
		return fileNames;
	}

	// 获取所有必选项类型名称
	public static ArrayList<String> getMustChooseDataAnalysisFileName(WebDriver driver) {
		ArrayList<String> mustfileNames = new ArrayList<String>();
		for (int i = 0; i < getDataAnalysisFileNameMustChooseNumber(driver); i++) {
			String script= "return $('.requireMark.ng-scope').eq("+String.valueOf(i)+").prev().text().trim();";
			String temp = CommonJQ.excuteJStoString(driver, script);
			mustfileNames.add(temp);
		}
		return mustfileNames;
	}
	//获取所有必选项提示信息
	public static ArrayList<String> getAllMustSelectedTipMessage(WebDriver driver) {
		ArrayList<String> alltipmessage = new ArrayList<String>();
		for (int i = 0; i < getDataAnalysisFileNameMustChooseNumber(driver); i++) {
			String script = "return $('.fileChoiceErrorMsg.ng-binding:visible').eq("+String.valueOf(i)+").text().trim();";
			String temp = CommonJQ.excuteJStoString(driver, script);
			alltipmessage.add(temp);
		}
		return alltipmessage;
	}
	// 评估 参数设置可选项检查
	// XRR&MR数据处理 分析数据必选项检查
	// XRR&MR数据处理 分析数据可选项检查
	//分析数据点击提交
	
	// 评估 分析数据必选项检查
	// 评估 分析数据可选项检查
	// XRR&MR数据处理&&评估 分析数据必选项检查
	// PA数据辅助模型训练 分析数据必选项检查
	
	//特性界面、个人工作空间、我的任务三个界面中均可找到所新建的任务
	
	//正常跳转到vmos界面
	//全选  多选  单选 删除
	// 批量下载
	// 任务重试
	// 状态栏检查
	private static final String r_container_conn = ".r_container_conn";
	private static final String btn_gray_1 = ".btn_gray_1";
	private static final String btn_no = ".btn_no";
	public static int getUnavailableBtn(WebDriver driver){
		return (int)CommonJQ.length(driver, r_container_conn, btn_no);
	}
	public static int getAvailableBtn(WebDriver driver){
		return (int)CommonJQ.length(driver, r_container_conn, btn_gray_1);
	}
	
	public static String getAvailableBtnText(WebDriver driver,int index){
		return CommonJQ.text(driver, r_container_conn, btn_gray_1,index);
	}
	
	public static String getUnavailableBtnText(WebDriver driver,int index){
		return CommonJQ.text(driver, r_container_conn, btn_no,index);
	}
	
	// 选择任务类型
	public static void selectTaskStatus(WebDriver driver, String TaskStatusType) {		
		String script = "$('#texttaskStatus').nextAll().filter(':visible').click();";
		CommonJQ.excuteJS(driver, script);
		String script2 = "$('#mshowtaskStatus').find('li:contains("+TaskStatusType+")').click();";
		CommonJQ.excuteJS(driver, script2);
		return;
	}

	// 获取可选状态选项
	public static ArrayList<String> getTaskStatusItems(WebDriver driver) {
		ArrayList<String> askStatusItems = new ArrayList<String>();
		int Items = CommonJQ.excuteJStoInt(driver, "return $('#mshowtaskStatus').find('li').length;");
		for (int i = 0; i < Items; i++) {
			String script = "return $('#mshowtaskStatus').find('li').eq("+String.valueOf(i)+").text().trim();";
			String temp = CommonJQ.excuteJStoString(driver, script);
			askStatusItems.add(temp);
		}
		return askStatusItems;
	}
	//专家求助状态栏
	// 选择专家求助类型
	public static void selectTaskExpertStatus(WebDriver driver, String TaskStatusType) {		
		String script = "$('#texttaskExpertStatus').nextAll().filter(':visible').click();";
		CommonJQ.excuteJS(driver, script);
		String script2 = "$('#mshowtaskExpertStatus').find('li:contains("+TaskStatusType+")').click();";
		CommonJQ.excuteJS(driver, script2);
		return;
	}

	// 获取可选状态选项
	public static ArrayList<String> getTaskExpertStatusItems(WebDriver driver) {
		ArrayList<String> askStatusItems = new ArrayList<String>();
		int Items = CommonJQ.excuteJStoInt(driver, "return $('#mshowtaskExpertStatus').find('li').length;");
		for (int i = 0; i < Items; i++) {
			String script = "return $('#mshowtaskExpertStatus').find('li').eq("+String.valueOf(i)+").text().trim();";
			String temp = CommonJQ.excuteJStoString(driver, script);
			askStatusItems.add(temp);
		}
		return askStatusItems;
	}
	
	//   任务状态栏    搜索菜单  ID   searchId
	private static final String searchId = "#searchId";
	public static void SearchTask(WebDriver driver, String searchtext) {		
		CommonJQ.value(driver, searchId, searchtext);
		CommonJQ.excuteJS(driver, "$('"+searchId+"').next().click()");
		Pause.pause(1000);
		LoadingPage.Loading(driver);
		return;
	}
	
	
	//任务列表项检查
	private static final String TaskNameSpan = "#searchId";
	public static boolean checkExistTaskByName(WebDriver driver, String tasknameString) {
		SearchTask(driver, tasknameString);
		String CSS = "span[title=\""+tasknameString+"\"]";
		return CommonJQ.isExisted(driver, CSS,true);
	}
	public static String getTaskID(WebDriver driver, String tasknameString) {	
		String CSS = "return $('span[title=\""+tasknameString+"\"]').parentsUntil('tbody','tr').find('a').text().trim();";
		return CommonJQ.excuteJStoString(driver, CSS);
	}
	//任务详情  报告下载
	
	public static void clickTaskID(WebDriver driver, String tasknameString) {	
		String taskid = LTE_Seq_VMOS_Page.getTaskID(driver, tasknameString);
		CommonJQ.click(driver, "#"+taskid,true);
	}
	public static String getPluginName(WebDriver driver) {		
		return CommonJQ.text(driver, LTE_Seq_VMOS_Page.pluginTitle, null);
	}
	
	private static final String NewTaskTitleCSS = ".titleCSS.ng-binding";
	public static String getNewTaskPluginTitleName(WebDriver driver) {		
		return CommonJQ.text(driver, NewTaskTitleCSS, "");
	}
	public static boolean netAnalysisCenterHref(WebDriver driver) {
		String isvisbleJS = "return $('#netAnalysisCenter').find('a').eq(0).is(':visible');";
		return CommonJQ.excuteJStoBoolean(driver, isvisbleJS);
	}
	
	public static void	clickNetAnalysisCenterHref(WebDriver driver) {
		String isvisbleJS = "$('#netAnalysisCenter').find('a').eq(0).click();";
		CommonJQ.excuteJS(driver, isvisbleJS);
		
	}

	private static final String VideoTrafficForecastResultFileInputID = "targetCellData";
	private static final String waitLoadingClass = "[class=\"mask_loading\"]";
	public static final String DataSelectProcessClass = "taskAddProgressDotText ng-binding taskAddProgressDotTextBold";
	public static void setVideoTrafficForecastResultFile(WebDriver driver,
			String videoTrafficForecastResultFile) {
		LTE_Seq_VMOS_Page.clickSelectFile2(driver, LTE_VMOS_Const.VideoTrafficForecastResultFile);
		CommonFile.ChooseOneFile(videoTrafficForecastResultFile);
		Pause.pause(1000);
		LoadingPage.waitMask(driver, waitLoadingClass);
		Pause.pause(1000);
		LTE_Seq_VMOS_Page.clickConfirmBTN(driver);		
	}

	private static void clickConfirmBTN(WebDriver driver) {
		String script = "$('iframe').contents().find('.messager-body.panel-body.panel-body-noborder.window-body').find('span:contains("+LTE_VMOS_Const.ConfirmText+")').eq(0).click();";
		CommonJQ.excuteJS(driver, script);
		
	}
	public static String getProcessDisplayClass(WebDriver driver, String processstep) {
		String script ="return $('.taskAddProgressBarDiv.ng-scope').find('span:contains("+processstep+")').eq(0).attr('class');";		
		return CommonJQ.excuteJStoString(driver, script);
	}

	private static final String templateBTN = "#select_laudioDataTemplateDownload";
	public static void clickTemplateDownBTN(WebDriver driver) {
		CommonJQ.click(driver, templateBTN,true,"点击模板下载");		
	}

	private static final String mshowselect2 = "#mshowselect2";
	public static void selectOTTypeAll(WebDriver driver,
			String filteringMode) {
		LOG.info_aw("选择OTT类型："+filteringMode);
		Assert.assertTrue("点击全部OTT", clickRadioByName(driver, LTE_VMOS_Const.OTTSetType1));
		LoadingPage.Loading(driver);		
	}
	//选择常用OTT类型
	public static void selectOTTypeCommon(WebDriver driver,
			String filteringMode) {
		LOG.info_aw("选择OTT类型："+filteringMode);
		Assert.assertTrue("点击常用OTT", clickRadioByName(driver, LTE_VMOS_Const.OTTSetType2));
		LoadingPage.Loading(driver);
		selectTypeByname(driver, "#mshowundefined", filteringMode, "OTT类型选择常用OTT");
	}
	
	public static void selectBusyTimeFilterType(WebDriver driver,String name){
		selectTypeByname(driver, "#mshowselect2", name, "选择忙时筛选方式");
	}

	private static void selectTypeByname(WebDriver driver,String selector,String name,String message){
		String script = "$('"+selector+"').prev().find('span').get(0).click()";
		CommonJQ.excuteJS(driver, script);
		CommonJQ.waitForElementVisble(driver, selector);
		Pause.pause(300);
		String script1 = "$('"+selector+"').find('li:visible').filter(function(){return $(this).text().trim()=='"+name.trim()+"'})";
		String script2 = script1+".length>0;";
		String script3 = script1+".click()";
		System.out.println(script2);
		if (CommonJQ.excuteJStoBoolean(driver, script2)) {
			CommonJQ.excuteJS(driver, script3);
			Pause.pause(300);
		}else{
			Assert.fail(message+"-下拉框没有对应选择项"+name);
		}
	}
	private static boolean clickRadioByName(WebDriver driver,String name){
		String script = "$('label:contains("+name+")').filter(':visible')";
		String script1 = script+".length;";
		String script2 = script+".find('input').get(0).click()";
		if(CommonJQ.excuteJStoInt(driver, script1)==0){
			return false;
		}else{
			CommonJQ.excuteJS(driver, script2);
			return true;
		}
	}
	//选择忙时指标
	public static void selectBusyTimeKPI(WebDriver driver, String busyhourKPI) {
		LOG.info_aw("选择忙时指标："+busyhourKPI);
		CommonJQ.click(driver, "label:contains("+busyhourKPI.trim()+")", "input", true, "选择"+busyhourKPI+"单选按钮");
		LoadingPage.Loading(driver);
	}

	private static final String astyle1 = ".astyle1";
	public static void selectCustomTimes(WebDriver driver, String[] customHours) {
		LOG.info_aw("自定义小时选择："+customHours.toString());
		CommonJQ.click(driver, astyle1, "span:contains("+"清除"+")", false,"点击自定义小时的清除按钮");
		LoadingPage.Loading(driver);
		if (customHours.length==1&&customHours[0].trim().equalsIgnoreCase("全选")) {
			CommonJQ.click(driver, astyle1, "span:contains("+"全选"+")", false);
			LoadingPage.Loading(driver);
			return;
		}
		for (int i = 0; i < customHours.length; i++) {			
			String script = "$('#selectWeekHours>div div').filter(function(){return $(this).text().trim()=='"+customHours[i].trim()+"'}).click()";
			CommonJQ.excuteJS(driver, script);
		}
		LoadingPage.Loading(driver);				
	}
	
	private static final String mshowselect3 = "#mshowselect3";
	public static void selectBusyTimeDays(WebDriver driver, String busyDays) {
		LOG.info_aw("选择忙时天数："+busyDays);
		selectTypeByname(driver, mshowselect3, busyDays, "选择忙时天数");
		LoadingPage.Loading(driver);
	}

	public static void enterTask(WebDriver driver, String taskid) {
		CommonJQ.clickLinkTextByName(driver, taskid);
		LoadingPage.Loading(driver);		
	}	
	
	private static final String downreportCSS = ".icon.icon_down.reportDownloadIcon";
	public static void clickDownLoadReport(WebDriver driver,int num){
		CommonJQ.click(driver, downreportCSS, true, num);
		LoadingPage.Loading(driver);
	}
	
	public static int getDownLoadReportNum(WebDriver driver){
		return CommonJQ.length(driver, downreportCSS, true);
	}
	
	public static String getReportName(WebDriver driver,int num){
		String script = "return $('"+downreportCSS+"').eq("+String.valueOf(num)+").parents('ul').find('.col2').text().trim();";
		return CommonJQ.excuteJStoString(driver, script);
	}
	
}
