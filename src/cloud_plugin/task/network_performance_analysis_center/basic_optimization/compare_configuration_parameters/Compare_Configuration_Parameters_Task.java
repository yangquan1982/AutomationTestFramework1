package cloud_plugin.task.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

import java.io.File;

import org.fest.swing.timing.Pause;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_performance_analysis_center.basic_optimization.compare_configuration_parameters.CompareCfgParametersPage;
import cloud_plugin.task.network_performance_analysis_center.NetworkAnalysisCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.system.EnvConstant;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileCompare;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

/**
 * 配置参数核查对比
 **/
public class Compare_Configuration_Parameters_Task {
	/**
	 * 配置参数对比 LTE 同网元
	 * 
	 * @author pgenexautotest
	 * 
	 ***/
	public static String creatLTESameNetElement(WebDriver driver, String taskName, String[] oldConfigFileName,
			String[] newConfigFileName, String defaultWindowID) {
		// NetworkAnalysisCenterTask.openLTEAutoCfgParma_CompareData(driver);
		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		Pause.pause(2000);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.compare, true);//
		LoadingPage.Loading(driver, CompareCfgParametersPage.compareLTE, "对比->LTE");
		// 制式
		CommonJQ.click(driver, CompareCfgParametersPage.compareLTE, true);
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.compare, true);//
		// 对比类型
		CommonJQ.click(driver, CompareCfgParametersPage.lteSameCompare, true);
		SwitchDriver.switchDriverToSEQ(driver);
		// CompareCfgParametersPage.setTaskName(driver, taskName);
		if (null != oldConfigFileName) {
			GetDataByTypeTask.chooseFolder(driver, oldConfigFileName,
					"$('iframe[name=main]').contents().find('#oldFile~span').click()", defaultWindowID);
		}
		if (null != newConfigFileName) {
			GetDataByTypeTask.chooseFolder(driver, oldConfigFileName,
					"$('iframe[name=main]').contents().find('#newFile~span').click()", defaultWindowID);
		}
		return CompareCfgParametersPage.setTaskName(driver, taskName);
	}

	public static String creatLTEDifferNetElement(WebDriver driver, String taskName, String[] baseData,
			String[] compareData, String villages, String parameSetup, String treeNode, String exportModel,
			String inputModel, String defaultWindowID, String uploadFilePath) {
		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		Pause.pause(2000);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.compare, true);//
		LoadingPage.Loading(driver, CompareCfgParametersPage.compareLTE, "对比->LTE");
		// 制式
		CommonJQ.click(driver, CompareCfgParametersPage.compareLTE, true);
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.compare, true);//
		// 对比类型
		CommonJQ.click(driver, CompareCfgParametersPage.lteDifferentCompare, true);
		// 不同网元 必须要等待加载完成后才能继续
		LoadingPage.Loading(driver, "#lteDifferentCompare", "LET对比类型->不同网元配置对比");
		SwitchDriver.switchDriverToSEQ(driver);
		// CompareCfgParametersPage.setTaskName(driver, taskName);baseDataFile

		if (baseData != null) {
			GetDataByTypeTask.chooseFolder(driver, baseData,
					"$('iframe[name=main]').contents().find('#baseDataFile~span').click()", defaultWindowID);
		}
		if (compareData != null) {
			GetDataByTypeTask.chooseFolder(driver, compareData,
					"$('iframe[name=main]').contents().find('#compareDataPath~span').click()", defaultWindowID);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		LoadingPage.Loading(driver, "#baseDataFile~span", "基线数据tree");
		CommonJQ.click(driver, "a[title=\"" + villages + "\"]", true);

		if ("无线参数".equals(parameSetup)) {
			CommonJQ.click(driver, CompareCfgParametersPage.cellNameWire, true);
			CompareCfgParametersPage.wirelessParameter(driver, treeNode);
		} else if ("自定义参数".equals(parameSetup)) {
			CommonJQ.click(driver, CompareCfgParametersPage.cellNameSelf, true);
			if (exportModel != null) {// 导出全参数模板
				String BaselineHome = uploadFilePath;
				String ResultHome = uploadFilePath;
				CompareCfgParametersPage.downLoad_Template(driver, ResultHome, exportModel);
				FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
			}
			if (inputModel != null) {// 导入自定义模板
				CommonJQ.click(driver, CompareCfgParametersPage.wireImport, true);
				CommonJQ.click(driver, CompareCfgParametersPage.BtnImportFile, true, "导入模板->浏览");
				CommonFile.ChooseOneFile(inputModel);

				CommonJQ.click(driver, CompareCfgParametersPage.BtnInput, true, "导入模板->导入");
				CommonJQ.click(driver, CompareCfgParametersPage.BtnResultOk, true, "导入模板->导入->确定");
			}
		}
		// CommonJQ.click(driver, CompareCfgParametersPage.BtnSubmit, true,
		// "提交");
		SwitchDriver.switchDriverToSEQ(driver);
		return CompareCfgParametersPage.setTaskName(driver, taskName);
	}

	/**
	 * 配置参数对比 UMTS 同网元
	 * 
	 * @author pgenexautotest
	 * 
	 ***/
	public static String creatUMTSSameNetElement(WebDriver driver, String taskName, String[] oldConfigFileName,
			String[] newConfigFileName, String defaultWindowID) {
		NetworkAnalysisCenterTask.openLTEAutoCfgParma_CompareData(driver);
		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		Pause.pause(2000);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.compare, true);//
		// 制式
		CommonJQ.click(driver, CompareCfgParametersPage.compareUMTS, true);
		// 对比类型
		CommonJQ.click(driver, CompareCfgParametersPage.umtsSameCompare, true);
		LoadingPage.Loading(driver, CompareCfgParametersPage.umtsSameCompare, "UMTS同网元配置对比");
		SwitchDriver.switchDriverToSEQ(driver);
		if (null != oldConfigFileName) {
			GetDataByTypeTask.chooseFolder(driver, oldConfigFileName,
					"$('iframe[name=main]').contents().find('#oldFile~span').click()", defaultWindowID);
		}
		if (null != newConfigFileName) {
			GetDataByTypeTask.chooseFolder(driver, oldConfigFileName,
					"$('iframe[name=main]').contents().find('#newFile~span').click()", defaultWindowID);
		}
		return CompareCfgParametersPage.setTaskName(driver, taskName);
	}

	/**
	 * 配置参数对比 UMTS 小区劈裂前后配置对比
	 * 
	 * @author pgenexautotest
	 * 
	 ***/
	public static String creatUMTSDifferNetElement(WebDriver driver, String taskName, String[] flerryPreCfgFilePath,
			String[] flerryAftCfgFilePath, String relationMapping, String treeNode, String parameSetup,
			String exportModel, String inputModel1, String defaultWindowID, String uploadFilePath) {

		// NetworkAnalysisCenterTask.openLTEAutoCfgParma_CompareData(driver);
		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		Pause.pause(2000);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		/*
		 * LoadingPage.Loading(driver, CompareCfgParametersPage.compareUMTS,
		 * "UMTS制式");
		 */
		Pause.pause(1000);
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.compare, true);//
		// 制式
		CommonJQ.click(driver, CompareCfgParametersPage.compareUMTS, true);
		// 对比类型
		CommonJQ.click(driver, CompareCfgParametersPage.umtsDifferentCompare, true);
		LoadingPage.Loading(driver, CompareCfgParametersPage.cellNameWire, "界面选择参数");

		SwitchDriver.switchDriverToSEQ(driver);
		if (null != flerryPreCfgFilePath) {
			GetDataByTypeTask.chooseFolder(driver, flerryPreCfgFilePath,
					"$('iframe[name=main]').contents().find('#splitOldFile~span').click()", defaultWindowID);
		}
		if (null != flerryAftCfgFilePath) {
			GetDataByTypeTask.chooseFolder(driver, flerryAftCfgFilePath,
					"$('iframe[name=main]').contents().find('#splitNewFilePath~span').click()", defaultWindowID);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		if (null != relationMapping) {
			// GetDataByTypeTask.chooseFolder(driver, relationMapping,
			// "$('iframe[name=main]').contents().find('#splitMappingFile~span').click()",
			// defaultWindowID);
			CommonJQ.click(driver, CompareCfgParametersPage.splitMappingFile, true);
			CommonFile.ChooseOneFile(relationMapping);
			// CommonJQ.click(driver, CompareCfgParametersPage.BtnInput,
			// true,"导入模板->导入");
			CommonJQ.click(driver, CompareCfgParametersPage.isOK, true, "选择文件->打开->确定");
		}
		if ("界面选择参数".equals(parameSetup)) {
			CommonJQ.click(driver, CompareCfgParametersPage.cellNameWire, true);

		} else if ("导入参数列表文件".equals(parameSetup)) {
			CommonJQ.click(driver, CompareCfgParametersPage.cellNameSelf, true);
			CommonJQ.click(driver, CompareCfgParametersPage.wireImport, true);
			if (inputModel1 != null) {// 导入自定义模板
				CommonJQ.click(driver, "#importFile", true, "导入模板->浏览");
				// BtnInput
				CommonFile.ChooseOneFile(inputModel1);

				CommonJQ.click(driver, CompareCfgParametersPage.BtnInput, true, "导入模板->导入");
				CommonJQ.click(driver, CompareCfgParametersPage.BtnResultOk, true, "导入模板->导入->确定");
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
		if ("界面选择参数".equals(parameSetup)) {
			CompareCfgParametersPage.wirelessParameter(driver, treeNode);

		} else if ("导入参数列表文件".equals(parameSetup)) {
			if (exportModel != null) {// 导出全参数模板
				// String BaselineHome = uploadFilePath;
				String ResultHome = uploadFilePath;
				// CompareCfgParametersPage.downLoad_Template(driver,
				// ResultHome,exportModel);
				// FileCompare.SameNameCompareInPath(BaselineHome, ResultHome);
			}

		}

		return CompareCfgParametersPage.setTaskName(driver, taskName);
	}

	/**
	 * 配置参数对比GSM
	 * 
	 * @author pgenexautotest
	 * 
	 ***/
	public static String creatGSM(WebDriver driver, String taskName, String[] oldConfigFileName,
			String[] newConfigFileName, String defaultWindowID) {

		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		Pause.pause(1000);
		LoadingPage.Loading(driver, CompareCfgParametersPage.compare, "业务类型对比");
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.compare, true);//
		// 制式
		while (true) {
			if (CommonJQ.isExisted(driver, CompareCfgParametersPage.compareGSM, true)) {
				break;
			} else {
				CommonJQ.click(driver, CompareCfgParametersPage.compare, true);//
			}
		}
		CommonJQ.click(driver, CompareCfgParametersPage.compareGSM, true);
		SwitchDriver.switchDriverToSEQ(driver);
		if (null != oldConfigFileName) {
			GetDataByTypeTask.chooseFolder(driver, oldConfigFileName,
					"$('iframe[name=main]').contents().find('#oldFile~span').click()", defaultWindowID);
		}
		if (null != newConfigFileName) {
			GetDataByTypeTask.chooseFolder(driver, oldConfigFileName,
					"$('iframe[name=main]').contents().find('#newFile~span').click()", defaultWindowID);
		}
		return CompareCfgParametersPage.setTaskName(driver, taskName);
	}

	/**
	 * <b>Description:</b>>配置参数核查LTE
	 * 
	 * @author dwx431110
	 * @param driver
	 * @param taskName
	 * @param taskType
	 * @param checkType
	 * @param regulationSetup
	 * @param selectSecns
	 * @param parameBaseModel
	 * @param filterCondition_ifCheckAll
	 * @param filterConditionSelect
	 * @param filterConditionChecked
	 * @param outPutSetup
	 * @param outPutNumber
	 * @param cfgDataFile
	 * @param netSecnsFile
	 * @param uploadFilePath
	 * @param defaultWindowID
	 * @return
	 * @return String
	 */
	public static String createLTE_check(WebDriver driver, String taskName, String taskType, String checkType,
			String regulationSetup, String selectSecns, String parameBaseModel, boolean filterCondition_ifCheckAll,
			String filterConditionSelect, String[] filterConditionChecked, String[] outPutSetup, String outPutNumber,
			String[] cfgDataFile, String netSecnsFile, String uploadFilePath, boolean importModel,
			boolean historicalTemplate, String defaultWindowID) {

		// NetworkAnalysisCenterTask.openLTEAutoCfgParma_CompareData(driver);

		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// LoadingPage.Loading(driver, "#cdrNum", "分析数据->配置数据->选择文件");
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.check, true);//
		// 制式
		CommonJQ.click(driver, CompareCfgParametersPage.checkLTE, true);

		// 任务类型
		if ("日常例行优化".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.BtnTaskType, true);
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 0);
		} else if ("省公司管控参数核查".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.BtnTaskType, true);
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 1);
		} else if ("总体组健康核查".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.BtnTaskType, true);
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 2);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		// 配置数据文件
		if (null != cfgDataFile) {
			GetDataByTypeTask.chooseFolder(driver, cfgDataFile,
					"$('iframe[name=main]').contents().find('span[ng-click=\"fileChoice(item)\"]').click()",
					// "span[ng-click=\"fileChoice(item)\"]",//"$('iframe[name=main]').contents().find('.paramsDiv>span>span>input').click()",
					defaultWindowID);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 组网数据文件
		if (null != netSecnsFile) {
			CommonJQ.click(driver, "#networkScenePath", true, "选择文件");
			CommonFile.ChooseOneFile(netSecnsFile);
			Pause.pause(3000);
			if (CommonJQ.isExisted(driver, CompareCfgParametersPage.isOK, true)) {
				CommonJQ.click(driver, CompareCfgParametersPage.isOK, true, "选择文件->确定");
			}
		}
		// 参数设置
		CommonJQ.click(driver, ".paramsDiv>span>span>input", true);// .paramsDiv>span>span>input

		// 核查类型
		if ("LTE XML配置文件核查 ".equals(checkType)) {// LTE XML配置文件检查
			CommonJQ.click(driver, "#LTE", true);
		} else if ("LTE MML命令行核查 ".equals(checkType)) {// LTE MML配置文件检查
			CommonJQ.click(driver, "#UMTS", true);
		}
		// 规则设置
		CompareCfgParametersPage.LTE_regulationSetup(driver, regulationSetup, selectSecns, parameBaseModel,
				uploadFilePath, importModel, historicalTemplate, "");
		// 过滤条件
		CompareCfgParametersPage.LTE_filterCondition(driver, filterConditionSelect, filterCondition_ifCheckAll,
				filterConditionChecked);
		// 输出设置
		CompareCfgParametersPage.LTE_outPutSetup(driver, outPutSetup, outPutNumber);
		// 参数设置确认
		CommonJQ.click(driver, "#ok_Button", true);
		SwitchDriver.switchDriverToSEQ(driver);
		return CompareCfgParametersPage.setTaskName(driver, taskName);
	}

	/***
	 * <b>Description:</b>配置参数核查UTMS
	 * 
	 * @author dwx431110
	 * @param driver
	 * @param taskName
	 * @param taskType
	 * @param checkType
	 * @param regulationSetup
	 * @param selectSecns
	 * @param parameBaseModel
	 * @param filterCondition_ifCheckAll
	 * @param filterConditionSelect
	 * @param filterConditionChecked
	 * @param outPutSetup
	 * @param outPutNumber
	 * @param cfgDataFile
	 * @param netSecnsFile
	 * @param uploadFilePath
	 * @param defaultWindowID
	 * @return
	 * @return String
	 */
	public static String createUTMS_check(WebDriver driver, String taskName, String taskType, String regulationSetup,
			String selectSecns, String parameBaseModel, boolean filterCondition_ifCheckAll,
			String filterConditionSelect, String[] filterConditionChecked, String[] outPutSetup,
			String[] RNCcfgDataFile, String[] NodeBcfgDataFile, String netSecnsFile, String uploadFilePath,
			boolean importModel, boolean historicalTemplate, String UMTSScenes, String defaultWindowID) {

		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.check, true);//
		// 制式
		CommonJQ.click(driver, CompareCfgParametersPage.checkUMTS, true);
		CommonJQ.click(driver, CompareCfgParametersPage.checkUMTS, true);

		// 任务类型
		CommonJQ.click(driver, CompareCfgParametersPage.BtnTaskType, true);
		if ("日常例行优化".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 0);
		} else if ("省公司管控参数核查".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 1);
		} else if ("总体组健康核查".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 2);
		}
		Pause.pause(3000);
		SwitchDriver.switchDriverToSEQ(driver);
		// 分析数据
		if (null != RNCcfgDataFile) {
			GetDataByTypeTask.chooseFolder(driver, RNCcfgDataFile,
					"$('iframe[name=main]').contents().find('#rncConfData').click()", defaultWindowID);
		}
		if (null != NodeBcfgDataFile) {
			GetDataByTypeTask.chooseFolder(driver, NodeBcfgDataFile,
					"$('iframe[name=main]').contents().find('#nodeBConfData').click()", defaultWindowID);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		if (null != netSecnsFile) {
			CommonJQ.click(driver, "#networkScenePath", true, "选择文件");
			CommonFile.ChooseOneFile(netSecnsFile);
			Pause.pause(3000);
			CommonJQ.click(driver, CompareCfgParametersPage.isOK, true, "选择文件->确定");
		}
		// 参数设置
		CommonJQ.click(driver, "input[ng-click=\"showParamSetting()\"]", true);
		// 规则设置
		CompareCfgParametersPage.LTE_regulationSetup(driver, regulationSetup, selectSecns, parameBaseModel,
				uploadFilePath, importModel, historicalTemplate, UMTSScenes);
		// 过滤条件
		CompareCfgParametersPage.LTE_filterCondition(driver, filterConditionSelect, filterCondition_ifCheckAll,
				filterConditionChecked);
		// 输出设置
		CompareCfgParametersPage.LTE_outPutSetup(driver, outPutSetup, null);
		CommonJQ.click(driver, "#close_ok", true);

		SwitchDriver.switchDriverToSEQ(driver);

		return CompareCfgParametersPage.setTaskName(driver, taskName);
	}

	/**
	 * <b>Description:</b>配置参数核查GSM
	 * 
	 * @author dwx431110
	 * @param driver
	 * @param taskName
	 * @param taskType
	 * @param checkType
	 * @param regulationSetup
	 * @param selectSecns
	 * @param parameBaseModel
	 * @param filterCondition_ifCheckAll
	 * @param filterConditionSelect
	 * @param filterConditionChecked
	 * @param outPutSetup
	 * @param outPutNumber
	 * @param cfgDataFile
	 * @param netSecnsFile
	 * @param uploadFilePath
	 * @param defaultWindowID
	 * @return
	 * @return String
	 */
	public static String createGSM_check(WebDriver driver, String taskName, String taskType, String regulationSetup,
			String selectSecns, String parameBaseModel, boolean filterCondition_ifCheckAll,
			String filterConditionSelect, String[] filterConditionChecked, String[] outPutSetup, String[] cfgDataFile,
			String netSecnsFile, String uploadFilePath, boolean importModel, boolean historicalTemplate,
			String GSMScenes, String defaultWindowID) {
		// 新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// LoadingPage.Loading(driver, "#select_conf", "分析数据->配置数据->选择文件");
		// 业务类型
		CommonJQ.click(driver, CompareCfgParametersPage.check, true);//
		// 制式
		CommonJQ.click(driver, CompareCfgParametersPage.checkGSM, true);
		// 任务类型
		CommonJQ.click(driver, CompareCfgParametersPage.BtnTaskType, true);
		if ("日常例行优化".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 0);
		} else if ("省公司管控参数核查".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 1);
		} else if ("总体组健康核查".equals(taskType)) {
			CommonJQ.click(driver, CompareCfgParametersPage.TaskType, true, 2);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		// 分析数据
		if (null != cfgDataFile) {// 配置数据
			GetDataByTypeTask.chooseFolder(driver, cfgDataFile,
					"$('iframe[name=main]').contents().find('span[ng-click=\"fileChoice(item)\"]').click()",
					defaultWindowID);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		if (null != netSecnsFile) {
			CommonJQ.click(driver, "#networkScenePath", true, "选择文件");
			CommonFile.ChooseOneFile(netSecnsFile);
			Pause.pause(3000);
			CommonJQ.click(driver, CompareCfgParametersPage.isOK, true, "选择文件->确定");
		}
		// 参数设置
		CommonJQ.click(driver, "input[ng-click=\"showParamSetting()\"]", true);

		// 规则设置
		CompareCfgParametersPage.LTE_regulationSetup(driver, regulationSetup, selectSecns, parameBaseModel,
				uploadFilePath, importModel, historicalTemplate, GSMScenes);
		// 过滤条件
		CompareCfgParametersPage.LTE_filterCondition(driver, filterConditionSelect, filterCondition_ifCheckAll,
				filterConditionChecked);
		// 输出设置
		CompareCfgParametersPage.LTE_outPutSetup(driver, outPutSetup, null);
		CommonJQ.click(driver, "#ok_Button", true);
		SwitchDriver.switchDriverToSEQ(driver);
		return CompareCfgParametersPage.setTaskName(driver, taskName);
	}

	/**
	 * 专家求助
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param HelpType
	 *            ：处理类型：远程交付任务、任务执行失败等
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附件路径
	 */
	public static void expertHelp(WebDriver driver, String taskName, String HelpType, String detailedDescription,
			String Filepath) {

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		CommonJQ.click(driver, "li.expertHelpDetailLi  div[class=\"zz_select msel\"] input[type=\"text\"]", true,
				"专家求助:求助标题 下拉框");
		CommonJQ.click(driver, "ul#mshowflowTitle li#" + HelpType, true, "专家求助:求助标题 ");

		CommonJQ.value(driver, "textarea[name=\"describe\"]", detailedDescription, true, "专家求助:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#userfilePick", true, "专家求助:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		CommonJQ.click(driver, "div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]", true, "专家求助:提交按钮");
		if (CommonJQ.isExisted(driver, CompareCfgParametersPage.isOK, true)) {
			CommonJQ.click(driver, CompareCfgParametersPage.isOK, true);
		}
		LoadingPage.Loading(driver);
		String AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
		String ExMessage = "专家分析中...";

		Assert.assertTrue("专家求助界面，期望值：" + ExMessage + "，实际值：" + AcMessage, ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 专家处理
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param ProcType
	 *            ：处理类型：请用户追加数据、问题分析完成
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附件路径
	 */
	public static void expertProc(WebDriver driver, String taskName, String ProcType, String detailedDescription,
			String Filepath) {

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#addDataReply", true, "专家反馈:请用户追加数据");
		} else {
			CommonJQ.click(driver, "#finishReply", true, "专家反馈:问题分析完成");
		}

		CommonJQ.value(driver, ".expertHelpDetailLabel>textarea", detailedDescription, true, "专家反馈:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#expertReplyFilePick", true, "专家反馈:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		CommonJQ.click(driver, "div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]", true, "专家反馈:提交按钮");
		if (CommonJQ.isExisted(driver, CompareCfgParametersPage.isOK, true)) {
			CommonJQ.click(driver, CompareCfgParametersPage.isOK, true);
		}
		LoadingPage.Loading(driver);
		String AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
		String ExMessage = "";
		if ("请用户追加数据".equalsIgnoreCase(ProcType)) {
			ExMessage = "用户追加数据中...";
		} else {
			ExMessage = "用户确认结果中...";
		}

		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage, ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 用户确认
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名
	 * @param ProcType
	 *            ：继续求助，已解决
	 * @param detailedDescription
	 *            ：详细描述
	 * @param Filepath
	 *            ：附近文件路径
	 */
	public static void userConfirm(WebDriver driver, String taskName, String ProcType, String detailedDescription,
			String Filepath, int starNum) {

		TaskViewPluginTask.viewTask(driver, taskName);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");

		if ("继续求助".equalsIgnoreCase(ProcType)) {
			CommonJQ.click(driver, "#seekForHelp", true, "用户确认:继续求助");
		} else {
			CommonJQ.click(driver, "#finishResolve", true, "用户确认:已解决");
		}

		CommonJQ.value(driver, ".expertHelpDetailLabel>textarea", detailedDescription, true, "专家反馈:详细描述");

		if (Filepath != null) {
			File dir = new File(Filepath);
			if (dir.isFile() == false) {
				Assert.fail(CommonFile.fileMsg(Filepath) + " is not file");
			}
			Filepath = dir.getAbsolutePath();
			CommonJQ.click(driver, "#usereComfirmFilePick", true, "用户确认:上传附件选择按钮");
			CommonFile.ChooseOneFile(Filepath);
		}
		for (int i = 0; i < starNum; i++) {
			CommonJQ.click(driver, ".star li", true, i, "用户确认:用户评分" + (i + 1) + "星");
		}
		CommonJQ.click(driver, "div[class=\"expertSubmitDiv\"] span[class=\"ng-binding\"]", true, "用户确认:提交按钮");
		if (CommonJQ.isExisted(driver, CompareCfgParametersPage.isOK, true)) {
			CommonJQ.click(driver, CompareCfgParametersPage.isOK, true);
		}
		LoadingPage.Loading(driver);
		String AcMessage = "";
		String ExMessage = "";
		if ("继续求助".equalsIgnoreCase(ProcType)) {
			AcMessage = CommonJQ.text(driver, "div.expertMessage > span");
			ExMessage = "专家分析中...";
		} else {
			AcMessage = CommonJQ.text(driver, "div.expertHistoryTab span", "", 0);
			ExMessage = "用户求助历史记录";
		}
		Assert.assertTrue("专家反馈界面，期望值：" + ExMessage + "，实际值：" + AcMessage, ExMessage.equals(AcMessage));
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * <b>Description:</b>下载报告，解压并移动到指定目录
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname
	 *            ：任务名
	 * @param ResultHome
	 *            ：报告存放目录
	 * @return void
	 */
	public static void downLoad_MoveReport(WebDriver driver, String defaultWindowID, String taskname,
			String ResultHome) {
		FileHandle.delSubFile(ResultHome);
		String[] reportName = downLoad_report(driver, defaultWindowID, taskname);
		for (int i = 0; i < reportName.length; i++) {
			if (reportName[i].endsWith("zip")) {
				ZIP.depress(EnvConstant.Path_DownLoad + "\\" + reportName[i], ResultHome);
			} else {
				FileHandle.copyFile(EnvConstant.Path_DownLoad + "\\" + reportName[i],
						ResultHome + "\\" + reportName[i]);
			}
		}

	}

	/**
	 * <b>Description:</b>下载报告
	 * 
	 * @author sWX198085
	 * @param driver
	 * @param defaultWindowID
	 * @param taskname 任务名
	 * @return String：报告完整名称
	 */
	public static String[] downLoad_report(WebDriver driver, String defaultWindowID, String taskname) {
		String nowWinID = TaskViewPluginTask.viewTask(driver, taskname);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		LoadingPage.Loading(driver, ".col6 a", "报告下载按钮");
		int reportNum = CommonJQ.length(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", true);
		String[] reportName = new String[reportNum];
		for (int i = 0; i < reportNum; i++) {
			CommonJQ.click(driver, ".col6 a", true, i, "报告下载按钮");
			reportName[i] = CommonJQ.text(driver, ".taskReportTab .col2 span[class=\"ng-binding\"]", "", i);
			TaskViewPluginTask.waittingDownLoadFile(EnvConstant.Path_DownLoad, reportName[i]);
		}
		SwitchDriver.winIDClose(driver, nowWinID);
		SwitchDriver.switchToWinID(driver, defaultWindowID);
		SwitchDriver.switchDriverToSEQ(driver);
		return reportName;
	}
}
