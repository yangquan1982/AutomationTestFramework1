package cloud_plugin.page.network_performance_analysis_center.basic_optimization.compare_configuration_parameters;

import org.fest.swing.timing.Pause;
import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;
import common.util.ZIP;

/**
 *配置参数核查对比
 * */
public class CompareCfgParametersPage {

	
	public static final String TaskName = "#taskName";
	
	public static final String CompareCfgPara_ClASS = ".configurationparametercheckandcomparison";
	
	public static final String check = "#check";
	
	public static final String compare = "#compare";
	
	public static final String checkLTE = "#checkLTE";
	
	public static final String checkUMTS = "#checkUMTS";
	
	public static final String checkGSM = "#checkGSM";
	
	public static final String compareLTE = "#compareLTE";
	
	public static final String compareUMTS = "#compareUMTS";
	
	public static final String compareGSM = "#compareGSM";
	
	public static final String lteSameCompare = "#lteSameCompare";
	
	public static final String lteDifferentCompare = "#lteDifferentCompare";
	
	public static final String umtsSameCompare = "#umtsSameCompare";
	
	public static final String umtsDifferentCompare = "#umtsDifferentCompare";
	
	public static final String submitBtn = "span[ng-click=\"insertAction()\"]";
	
	public static final String baseData = "#baseData";
	
	public static final String compareDataPath = "#compareDataPath";
	
	public static final String cellInfoTree_1 = "#cellInfoTree_1";
	
	public static final String cellInfoTree_2_span = "#cellInfoTree_2_span";
	
	public static final String exportWireLess = "#exportWireLess";
	
	public static final String splitMappingFile = "#splitMappingFile";
	
	public static final String cellNameSelf = "#cellNameSelf";//importFile
	
	public static final String cellNameWire = "#cellNameWire";
	
	public static final String BtnImportFile = "#importFile";
	
	public static final String BtnInput = "span[ng-click=\"importTemplate()\"]";//ng-click="importTemplate()"
	
	public static final String BtnResultOk = "#resultOk";
	
	public static final String  BtnSubmit = "span[ng-click=\"insertAction()\"]";
	
	public static final String isOK = ".l-btn-text";
	
	public static final String BtnTaskType = "input[id=\"checkTaskBean.taskType\"]~span";
	
	public static final String TaskType = "ul[id=\"mshowcheckTaskBean.taskType\"] li";
	
	public static final String CheckTypeXML = "#LTE";
	
	public static final String CheckTypeMML = "#UMTS";
	
	public static final String systemDir = "#systemDir";//规则设置系统默认
	
	public static final String selfDir = "#selfDir";//规则设置用户自定义
	
	public static final String wireImport = "#wireImport";
	
	public static final String BtnsceneName = "#task.sceneName";
	
	public static final String sceneName = "ul[id=\"mshowcheckTaskBean.sceneName\"] li";
	
	public static final String allchose = "#allchose";//全选
	
	public static final String BtnFilterName = "input[id=\"checkTaskBean.filterName\"]";
	
	public static final String filterName = "ul[id=\"mshowcheckTaskBean.filterName\"] li";
	
	public static final String flowTitle = "#flowTitle";
	
	public static final String TextArea = ".expertHelpTextArea.ng-pristine.ng-valid";
	
	public static final String BtnUserfilePick = "#userfilePick";
	
	public static final String Btn_blue = ".btn_blue";
	//输出设置
	public static final String unitMO = "input[name=\"checkTaskBean.moTag\"]";
	public static final String mmlInfo = "input[name=\"checkTaskBean.mmlTag\"]";
	public static final String outinfo = "input[name=\"checkTaskBean.detailTag\"]";
	public static final String isSplitReport = "input[name=\"checkTaskBean.isSplitReport\"]";
	public static final String eachReportNeNum = "#eachReportNeNum";
	//专家反馈
	public static final String mshowflowTitle = "#mshowflowTitle > li";
	public static final String userfilePick = "#userfilePick";
	public static String setTaskName(WebDriver driver, String  taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName = taskName + "_" + ZIP.hhmmss();
		LoadingPage.Loading(driver,TaskName);
		CommonJQ.value(driver, TaskName, taskName);
		String text = CommonJQ.getValue(driver, TaskName);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TaskName, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}
	public static void wirelessParameter(WebDriver driver, String treeNode) {
		//SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		String[] treeNodeArray = treeNode.split(";");
		for(int i = 0; i < treeNodeArray.length; i++) {
			String[] treeNodeStr = treeNodeArray[i].substring(treeNodeArray[i].indexOf("["), treeNodeArray[i].lastIndexOf("]")).split(",");
			String node1 = treeNodeStr[0];//一级节点
			String node2 = treeNodeStr[1];//二级节点
			String node3 = treeNodeStr[2];//叶子节点
			if("".equals(node3)) {
				if("".equals(node2)) {
					if(!"".equals(node1)) {
						CommonJQ.click(driver, "#wireLessAllToRight", true);
					}
				} else {//如果子节点为空 二级节点部位空
					CommonJQ.click(driver, "span:contains("+node2+")", true);
				}
			} else {//如果子节点不为空
				System.out.println(node2);
				String id_2 = CommonJQ.getAttrValue(driver, "span:contains(\""+node2+"\")", "id", 0);
				String id_3ul = id_2.replace("span", "ul");
				String addIcon = id_2.replace("span", "switch");
				CommonJQ.click(driver, "#"+addIcon, true);
				LoadingPage.Loading(driver, "#"+addIcon, "加载叶子节点");
				//CommonJQ.click(driver, "#"+id_3ul+" > span:contains("+node3+")",true);
				CommonJQ.click(driver, "#"+id_3ul, "span:contains("+node3+")", true);
				CommonJQ.click(driver, "#wireLessToRight", true);
			}
		}
		//SwitchDriver.switchDriverToSEQ(driver);
	}
	/**
	 * 配置参数核查
	 * 规则设置
	 * 
	 * **/
	public static void LTE_regulationSetup(WebDriver driver,String regulationSetup, String selectSecns, String parameBaseModel, String uploadFilePath,boolean importModel,boolean historicalTemplate, String GSMScenes) {
		if("系统默认".equals(regulationSetup)) {
			LoadingPage.Loading(driver, "#systemDefault", "系统默认");
			CommonJQ.click(driver, "#systemDefault", true);
			//场景
			CommonJQ.click(driver, "input[id=\"checkTaskBean.sceneName\"]~span", true);
			if("默认".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,0);
			} else if("GL_REFARMING_核心参数 ".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,1);
			} else if("L2G coverage based Redirection".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,2);
			} else if("L2U coverage based PSHO/Redirection".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,3);
			} else if("L2G Cell Reselection".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,4);
			} else if("L2U Cell Reselection".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,5);
			} else if("L2G Blind R8 Redirection CSFB".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,6);
			} else if("L2G Blind R9 Redirection CSFB".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,7);
			} else if("L2U Blind R9 Redirection CSFB".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,8);
			} else if("L2U PSHO CSFB".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,9);
			} else if("L2U&L2G mixed Blind Redirection CSFB".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,10);
			} else if("L2U Blind R8 Redirection CSFB".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,11);
			} else if("大话务 ".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,12);
			} else if("eMBMS".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,13);
			} else if("SFN-Normal RRU".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,14);
			} else if("SFN-pRRU".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,15);
			} else if("L2eHRPD".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,16);
			} else if("ANR".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,17);
			} else if("高铁".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,18);
			} else if("大话务-定时策略优化方案I".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,19);
			} else if("大话务-定时策略优化方案II".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,20);
			} else if("大话务-定时策略优化方案III".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,21);
			} else if("DRX".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,22);
			} else if("DRX(QCI1)".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,23);
			} else if("VOLTE".equals(selectSecns)) {
				CommonJQ.click(driver, sceneName, true,24);
			} 
		} else if("用户自定义".equals(regulationSetup)) {//
			LoadingPage.Loading(driver, "#userDefined", "用户自定义");
			CommonJQ.click(driver, "#userDefined", true);
			if(null!=parameBaseModel) {// 上传基线参数模板
				if(historicalTemplate) {
					CommonJQ.click(driver, "#history_model", true);
					CommonJQ.click(driver, "#textFilter",true);
					CommonJQ.click(driver, "li[title=\"LTEXMLParameterCheckRule.xlsx\"]", true);
				} else {
					CommonJQ.click(driver, "#filePick", true);
					CommonFile.ChooseOneFile(parameBaseModel);
					if(CommonJQ.isExisted(driver, ".l-btn-text", true)) {
						CommonJQ.click(driver, ".l-btn-text", true); 
					}
					if(importModel) {
						CommonJQ.click(driver, "span[ng-click=\"uploadTemplateFile()\"]", true);//导入模板
						//System.out.println(CommonJQ.isExisted(driver, ".l-btn-text", true));
						Pause.pause(3000);
						if(CommonJQ.isExisted(driver, ".l-btn-text", true)) {
							CommonJQ.click(driver, ".l-btn-text", true); 
						}
						if(GSMScenes!=null&&!"".equals(GSMScenes)) {
							CommonJQ.click(driver, "#ruleSettingDetail>div>ul>li>div>label>span", true,"场景");
							//CommonJQ.siblingsClick(driver, "input[id=\"mshowcheckTaskBean.sceneName\"]", "span");
							CommonJQ.click(driver, "li[title=\""+GSMScenes+" \"]", true);
						}
					}
					if(CommonJQ.isExisted(driver, ".l-btn-text", true)) {
						CommonJQ.click(driver, ".l-btn-text", true); 
					}
					Pause.pause(3000);
				}
			}
		}
	}
	/***
	 * 配置参数核查
	 * 过滤条件
	 * **/
	public static void LTE_filterCondition(WebDriver driver,String filterConditionSelect,boolean filterCondition_ifCheckAll, String[] filterConditionChecked) {
		if(!filterCondition_ifCheckAll) {
			CommonJQ.click(driver, allchose,true);
		}
		if("Subject".equals(filterConditionSelect)||"参数所属专题-ZH".equals(filterConditionSelect)) {
			CommonJQ.siblingsClick(driver, BtnFilterName, "span");
			CommonJQ.click(driver, filterName,true,0);
		} else {
			CommonJQ.siblingsClick(driver, BtnFilterName, "span");
			CommonJQ.click(driver, filterName,true,1);
		}	
		for(int i = 0; i < filterConditionChecked.length; i++) {
			CommonJQ.click(driver, "input[value="+filterConditionChecked[i]+"]", true);
		}
	}
	/***
	 *  配置参数核查
	 * 输出设置
	 * **/
	public static void LTE_outPutSetup(WebDriver driver,String[] outPutSetup, String outPutNumber) {
		for(int i = 0; i < outPutSetup.length; i++) {
			if("按MO合并".equals(outPutSetup[i])) {
				CommonJQ.click(driver, unitMO,true);
			} else if("输出MML命令".equals(outPutSetup[i])) {
				CommonJQ.click(driver, mmlInfo,true);
			} else if("输出详细信息".equals(outPutSetup[i])) {
				CommonJQ.click(driver, outinfo,true);
			} else if("设置单个报告网元数目".equals(outPutSetup[i])) {
				CommonJQ.click(driver, isSplitReport,true);
				CommonJQ.value(driver, eachReportNeNum, outPutNumber, true);
			}
		}
	}
	/**
	 * *文件下载****/
	public static void downLoad_Template(WebDriver driver,String filePath,String downLoadSourceFileName) {
		//FileHandle.delSubFile(ConstUrl.DownLoadPath);//设置浏览器下载路径
		//FileHandle.delSubFile(filePath);
		CommonJQ.click(driver, CompareCfgParametersPage.exportWireLess,true,downLoadSourceFileName);
		String reportName = TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, downLoadSourceFileName);
		FileHandle.copyFile(ConstUrl.DownLoadPath+"\\"+reportName, filePath+"\\MO_parameter_CSV_template.csv");
	} 
}
