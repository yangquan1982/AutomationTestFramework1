package cloud_plugin.page.network_evaluation_assurance_center.RAN_remote_delivery;

import org.openqa.selenium.WebDriver;

import cloud_public.page.LoadingPage;
import cloud_public.task.GetDataByTypeTask;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.SwitchDriver;
import common.util.ZIP;

public class RANRemoteDeliveryPage {
	
	public static final String TaskName_ID = "#taskName";//Task Name
	
	public static final String IOAFileBtn = "#select_taskIoaUploadFile";
	
	public static final String IOAConfirm = ".l-btn-text";//
	
	public static final String warningBtn = ".l-btn-text";
	
	public static final String IOAFileInput = "#taskIoaNum";
	
	public static final String WorkTypeBtn = "input[title=\"网络设计_RAN\"]";
	
	public static final String WorkType = "ul[id=\"mshowtask.designObj\"] li";
	
	public static final String AddSetMealPackage = "span[title=\"添加\"]";
	
	public static final String SetmealBtn = "#networkType_"; //远程交付套餐";
	
	public static final String Setmeal1 = "ul[id=\"mshownetworkType_";
	
	public static final String Setmeal2 = "\"] li";
	
	public static final String Setmea = "#mshownetworkType_";
	
	public static final String  Controller = "input[title=\"控制器\"]";//控制器
	
	public static final String Number = "#netcellNum_";//数量
	
	public static final String SecnsChooseBtn = "input[id=\"task.scene\"]";//场景选择
	
	public static final String SecnsChoose = "ul[id=\"mshowtask.scene\"] li";//场景选择 mshowtask.scene
	
	public static final String lld = "#lld";
	
	public static final String hld = "#hld";
	
	public static final String lldhld = "#lldhld";
	
	public static final String gsm = "#gsm";
	
	public static final String umts = "#umts";
	
	public static final String gu = "#gu";
	
	public static final String rncInPool = "#rncInPool";
	
	public static final String projectDocumentFileBtn = "#select_taskObjSceneUploadFile";
	
	public static final String projectDocComfi = ".l-btn-text";
	
	public static final String baseStationControlModeBtn = "input[id=\"task.baseStationControlMode\"]";
	
	public static final String baseStationControlMode ="ul[id=\"mshowtask.baseStationControlMode\"] li";
	
	public static final String baseStationWorkingModeBtn="#task.baseStationWorkingMode";
	
	public static final String baseStationWorkingMode="ul[id=\"mshowtask.baseStationWorkingMode\"] li";
	
	public static final String projectDocumentBtn = "#taskObjSceneUploadData";//"#select_taskObjSceneUploadFile";
	
	public static final String rnpDataFileBtn = "#rnpData";
	
	public static final String select_boqFileFileBtn = "#boqData";//"#select_boqFile";
	
	public static final String trafficModelDataFileBtn = "#trafficModelData";//"#trafficModelData";
	
	public static final String select_negotiationParamFileFileBtn = "#negotiationParamData";//"#select_negotiationParamFile";
	
	public static final String taskDescArea = ".taskDescArea";
	
	public static final String save_TasknewBtn = "#save_Tasknew";
	
	public static final String commit_TasknewBtn = "#commit_Tasknew";
	
	public static final String cancel_TaskBtn = "#cancel_Task";
	
	public static final String netDesignContentBtn = "input[id=\"task.designContent\"]";
	
	public static final String netDesignContent = "ul[id=\"mshowtask.designContent\"] li";//
	
	public static final String stockConfigurationFilePathBtn = "select_stockAndAllocationFile";
	
	public static final String bindingRelationBtn = "input[id=\"bindingRelationshipData\"]";
	
	public static final String baseStationDataBtn = "input[id=\"baseStationData\"]";
	
	public static final String antennaDataBtn = "input[id=\"antennaData\"]";//"#antennaData";
	
	public static final String projectRulesDataBtn = "input[id=\"projectRulesData\"]"; //"#projectRulesData";
	
	public static final String IOADownloadBtn = "span[ng-click=\"confirmDownloadIoa()\"]";
	
	public static final String taskAcceptSendToExpertBtn = "#taskAcceptSendToExpert";
	
	public static final String taskAcceptReject = "#taskAcceptReject";
	
	public static final String taskEditBtn = "div[class=\"taskEditTitle ng-binding\"] span";
	
	public static final String reportBtn = "input[id=\"taskAccept.expert\"]";
	
	public static final String report = "ul[id=\"mshowtaskAccept.expert\"] li";
	
	public static final String textarea = "form[id=\"taskAcceptForm\"] textarea";
	
	public static final String treatmentActionBtn = "span[ng-click=\"treatmentAction()\"]";
	
	public static final String fileUploadBtn = "select_taskAnalysisUploadData";//
	
	public static final String reportAccess = "#taskAnalysisAccept";
	
	public static final String reportForword = "#taskAnalysisReject";
	
	public static final String reportReject = "#taskAnalysisReject";
	
	public static final String reportTaskDescArea = "form[id=\"taskAnalysisForm\"] textarea";//form[id="taskAnalysisForm"] textarea
	
	public static final String reportSubmitBtn = "span[ng-click=\"analysisDataAction()\"]";
	
	public static final String taskResultClose= "#taskResultClose";
	
	public static final String taskResultBack = "#taskResultBack";
	
	public static final String taskResultTextarea =  "form[id=\"taskResultForm\"] textarea";
	
	public static final String resultSubmitBtn = "span[ng-click=\"resultDataAction()\"]";
	
	public static final String select_mmlFile = "select_mmlFile";
	//任务名称
	public static String setTaskName(WebDriver driver, String  taskName) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		taskName=taskName+ "_" + ZIP.hhmmss();
		LoadingPage.Loading(driver,TaskName_ID);
		CommonJQ.value(driver, TaskName_ID, taskName);
		String text = CommonJQ.getValue(driver, TaskName_ID);
		if (!(text.equals(taskName))) {
			CommonJQ.value(driver, TaskName_ID, taskName);
		}
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}
	//套餐包选择
	public static void setmealPackage(WebDriver driver,String[] paySetMeal,String[] number) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		for(int i = 0; i < paySetMeal.length; i++) {
			if(i > 0) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.AddSetMealPackage,true);
			}
			CommonJQ.siblingsClick(driver, RANRemoteDeliveryPage.SetmealBtn+i, "span");
			if("GU控制器HLD".equals(paySetMeal[i]) || "基站方案设计HLD".equals(paySetMeal[i])||
					"OSS网络设计（单机）".equals(paySetMeal[i])|| "基站Summary模板定制".equals(paySetMeal[i])
					|| "GU网络评估".equals(paySetMeal[i])|| "单模站点或多模分开部署".equals(paySetMeal[i])
					|| "基站脚本制作（新建场景）".equals(paySetMeal[i])
					|| "勘测模板定制，TSSR报告生成及评审".equals(paySetMeal[i])
					|| "硬件验收报告制作及评审（ISDP）".equals(paySetMeal[i])) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.Setmeal1+i+RANRemoteDeliveryPage.Setmeal2,true,0);
			} else if("GU控制器LLD".equals(paySetMeal[i]) || "基站方案设计LLD".equals(paySetMeal[i])|| "OSS网络设计（异地双机）".equals(paySetMeal[i])|| "GU特性推荐".equals(paySetMeal[i])
					|| "双模站点（共交付）".equals(paySetMeal[i])|| "软硬件验收报告制作及评审（ISDP）".equals(paySetMeal[i])) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.Setmeal1+i+RANRemoteDeliveryPage.Setmeal2,true,1);
			} else if("GU控制器HLD&LLD".equals(paySetMeal[i])||"LTE网络评估".equals(paySetMeal[i])||"三模站点（共交付）".equals(paySetMeal[i])||"简易软硬件验收报告制作（WDT）".equals(paySetMeal[i])) {//
				CommonJQ.click(driver, RANRemoteDeliveryPage.Setmeal1+i+RANRemoteDeliveryPage.Setmeal2,true,2);
			} else if("GUL基站网络设计HLD".equals(paySetMeal[i])||"LTE特性推荐".equals(paySetMeal[i])||"基站脚本制作（新建场景）".equals(paySetMeal[i])) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.Setmeal1+i+RANRemoteDeliveryPage.Setmeal2,true,3);
			} else if("基站脚本制作（非新建场景）".equals(paySetMeal[i])) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.Setmeal1+i+RANRemoteDeliveryPage.Setmeal2,true,4);
			}
			CommonJQ.value(driver, RANRemoteDeliveryPage.Number+i, number[i]);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
	//场景设计(业务类型为:网络设计_RAN)
	public static void secnsDesign(WebDriver driver,String secnsChoose, String netContent, String[] controllerChoose, String baseStationControllerModel, String baseStationWorkModel, String projectDocumentFilePath) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		//场景选择
		CommonJQ.siblingsClick(driver, RANRemoteDeliveryPage.SecnsChooseBtn, "span");
		if("新建".equals(secnsChoose)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.SecnsChoose, true, 0);
		} else if("改造".equals(secnsChoose)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.SecnsChoose, true, 1);
		} else if("扩容".equals(secnsChoose)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.SecnsChoose, true, 2);
		} else if("调整".equals(secnsChoose)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.SecnsChoose, true, 3);
		}
		//网设内容
		if("LLD".equals(netContent)) {
			if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.lld, true)) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.lld, true);
			}
		} else if("HLD".equals(netContent)) {
			if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.hld, true)) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.hld, true);
			}
		} else if("LLD & HLD".equals(netContent)) {
			if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.lldhld, true)) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.lldhld, true);
			}
		}
		//控制器选择
		for(int i = 0; i < controllerChoose.length; i++) {
			if("GSM".equals(controllerChoose[i])) {
				if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.gsm, true)) {
					if(!CommonJQ.isChecked(driver, RANRemoteDeliveryPage.gsm)) {
						CommonJQ.click(driver, RANRemoteDeliveryPage.gsm, true);
					}
				}
			}
			if("UMTS".equals(controllerChoose[i])) {
				if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.umts, true)) {
					if(!CommonJQ.isChecked(driver, RANRemoteDeliveryPage.umts)) {
						CommonJQ.click(driver, RANRemoteDeliveryPage.umts, true);
					}
				}
			}
			if("GU".equals(controllerChoose[i])) {
				if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.gu, true)) {
					if(!CommonJQ.isChecked(driver, RANRemoteDeliveryPage.gu)) {
						CommonJQ.click(driver, RANRemoteDeliveryPage.gu, true);
					}
				}
			}
			if("RNC in Pool".equals(controllerChoose[i])) {
				if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.rncInPool, true)) {
					if(!CommonJQ.isChecked(driver, RANRemoteDeliveryPage.rncInPool)) {
						CommonJQ.click(driver, RANRemoteDeliveryPage.rncInPool, true);
					}
				}
			}
		}
		//基站控制模式
		CommonJQ.siblingsClick(driver, RANRemoteDeliveryPage.baseStationControlModeBtn, "span");
		if("Single-Mode BTS".equals(baseStationControllerModel)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.baseStationControlMode, true,0);
		} else if("Co-MPT MBTS".equals(baseStationControllerModel)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.baseStationControlMode, true,1);
		}
		//基站工作模式
		CommonJQ.siblingsClick(driver, RANRemoteDeliveryPage.baseStationWorkingModeBtn, "span");
		if("GU".equals(baseStationControllerModel)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.baseStationWorkingMode, true,0);
		} else if("GL".equals(baseStationControllerModel)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.baseStationWorkingMode, true,1);
		} else if("UL".equals(baseStationControllerModel)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.baseStationWorkingMode, true,2);
		} else if("GUL".equals(baseStationControllerModel)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.baseStationWorkingMode, true,3);
		}
		//上传工程文档
		if(projectDocumentFilePath!=null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.projectDocumentBtn, true);
			CommonFile.ChooseOneFile(projectDocumentFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.projectDocComfi,true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
	//场景设计(业务类型为:基站与天馈设计)
	public static void secnsDesign_AntennaFeeder(WebDriver driver,String secnsChoose, String netContent, String projectDocumentFilePath) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		//场景选择
		CommonJQ.siblingsClick(driver, RANRemoteDeliveryPage.SecnsChooseBtn, "span");
		if("新建".equals(secnsChoose)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.SecnsChoose, true, 0);
		} else if("演进".equals(secnsChoose)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.SecnsChoose, true, 1);
		} else if("新建&演进".equals(secnsChoose)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.SecnsChoose, true, 2);
		} 
		//设计内容
		CommonJQ.siblingsClick(driver, RANRemoteDeliveryPage.netDesignContentBtn, "span");
		if("基站方案".equals(netContent)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.netDesignContent, true,0);
		} else if("天馈方案".equals(netContent)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.netDesignContent, true,1);
		} else if("基站&天馈方案".equals(netContent)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.netDesignContent, true,2);
		}
		//上传工程文档
		if(projectDocumentFilePath!=null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.projectDocumentBtn, true);
			CommonFile.ChooseOneFile(projectDocumentFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.projectDocComfi,true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
	//上传采集数据(业务类型为:网络设计_RAN)
	public static void collectData(WebDriver driver,String[] MMLFilePath, String RNPFilePath, String BOQFilePath, String telephoneModelFilePath, String consultParameFilePath, String defaultWindowID) {
		if(MMLFilePath!=null) {
			GetDataByTypeTask.chooseFolder(driver, MMLFilePath, select_mmlFile, defaultWindowID);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		if(RNPFilePath!=null) {
			if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.rnpDataFileBtn, true)) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.rnpDataFileBtn, true);
				CommonFile.ChooseOneFile(RNPFilePath);
				CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm,true);
			}
		} 
		if(BOQFilePath!=null) {
			if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.select_boqFileFileBtn, true)) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.select_boqFileFileBtn, true);
				CommonFile.ChooseOneFile(BOQFilePath);
				CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm,true);
			}
		}
		if(telephoneModelFilePath!=null) {
			if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.trafficModelDataFileBtn, true)) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.trafficModelDataFileBtn, true);
				CommonFile.ChooseOneFile(telephoneModelFilePath);
				CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm,true);
			}
		}
		if(consultParameFilePath!=null) {
			if(CommonJQ.isExisted(driver, RANRemoteDeliveryPage.select_negotiationParamFileFileBtn, true)) {
				CommonJQ.click(driver, RANRemoteDeliveryPage.select_negotiationParamFileFileBtn, true);
				CommonFile.ChooseOneFile(consultParameFilePath);
				CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm,true);
			}
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
	//上传采集数据(业务类型为:基站与天馈设计)
	public static void collectData_AntennaFeeder(WebDriver driver,String[] stockConfigurationFilePath, 
			String bindingRelationFilePath,
			String baseInformationFilePath, 
			String antennaFeederInformationFilePath, 
			String itemsRegulationFilePath,
			String defaultWindowID) {
		//存量配置
		GetDataByTypeTask.chooseFolder(driver, stockConfigurationFilePath, stockConfigurationFilePathBtn, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		//绑定关系配置
		if(bindingRelationFilePath!=null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.bindingRelationBtn,true);//
			CommonFile.ChooseOneFile(bindingRelationFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm,true);
		}
		//基站信息
		if(baseInformationFilePath!=null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.baseStationDataBtn,true);//
			CommonFile.ChooseOneFile(baseInformationFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm,true);
		}
		//天馈信息
		if(antennaFeederInformationFilePath!=null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.antennaDataBtn,true);//
			CommonFile.ChooseOneFile(antennaFeederInformationFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm,true);
		}
		//项目规则
		if(itemsRegulationFilePath!=null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.projectRulesDataBtn,true);//
			CommonFile.ChooseOneFile(itemsRegulationFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm,true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
	public static void commitBtnClick(WebDriver driver, String submitState) {
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		if("保存".equals(submitState)) {
			CommonJQ.click(driver, save_TasknewBtn, true);
		} else if("提交".equals(submitState)) {
			CommonJQ.click(driver, commit_TasknewBtn, true);
		} else if("取消并返回".equals(submitState)) {
			CommonJQ.click(driver, cancel_TaskBtn, true);
		}
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
