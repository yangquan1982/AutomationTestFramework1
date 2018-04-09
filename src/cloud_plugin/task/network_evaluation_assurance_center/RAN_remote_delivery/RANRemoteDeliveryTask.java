package cloud_plugin.task.network_evaluation_assurance_center.RAN_remote_delivery;

import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_evaluation_assurance_center.RAN_remote_delivery.RANRemoteDeliveryPage;
import cloud_plugin.task.network_evaluation_assurance_center.NetworkSafeGuardCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import cloud_public.task.GetDataByTypeTask;
import cloud_public.task.TaskReportTask;
import cloud_public.task.TaskViewPluginTask;
import common.constant.constparameter.ConstUrl;
import common.util.CommonFile;
import common.util.CommonJQ;
import common.util.CommonWD;
import common.util.FileHandle;
import common.util.SwitchDriver;

/**
 * RAN远程交付
 * 
 * @author pgenexautotest
 */
public class RANRemoteDeliveryTask {
	/**
	 * 网络设计_RAN测试用例
	 **/
	public static String creatCountStatisticsTask(WebDriver driver, String taskName, String IOAFilePath,
			String workType, String[] paySetMeal, String[] number, String secnsChoose, String netContent,
			String[] controllerChoose, String baseStationControllerModel, String baseStationWorkModel,
			String projectDocumentFilePath, String[] MMLFilePath, String RNPFilePath, String BOQFilePath,
			String telephoneModelFilePath, String consultParameFilePath, String taskDescArea, String submitState,
			String defaultWindowID) {
		NetworkSafeGuardCenterTask.openAutoRANData(driver);
		// 打开新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// IOA 文件
		if (IOAFilePath != null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAFileBtn, true);//
			LoadingPage.Loading(driver, RANRemoteDeliveryPage.IOAFileBtn, "IOA选择文件->确认");
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm, true);
			CommonFile.ChooseOneFile(IOAFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm, true);
		}
		// 业务类型
		CommonJQ.click(driver, RANRemoteDeliveryPage.WorkTypeBtn, true);// WorkType
		if ("网络设计_RAN".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 0);// WorkType
		} else if ("基站与天馈设计".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 1);// WorkType
		} else if ("网络设计_OSS".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 2);// WorkType
		} else if ("Summary定制".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 3);// WorkType
		} else if ("网络评估".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 4);// WorkType
		} else if ("基站集成调测".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 5);// WorkType
		} else if ("基站脚本制作".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 6);// WorkType
		} else if ("勘测设计".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 7);// WorkType
		} else if ("验收".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 8);// WorkType
		}
		SwitchDriver.switchDriverToSEQ(driver);
		// 套餐包选择
		RANRemoteDeliveryPage.setmealPackage(driver, paySetMeal, number);
		// 场景设置
		RANRemoteDeliveryPage.secnsDesign(driver, secnsChoose, netContent, controllerChoose, baseStationControllerModel,
				baseStationWorkModel, projectDocumentFilePath);
		// 上传采集数据
		RANRemoteDeliveryPage.collectData(driver, MMLFilePath, RNPFilePath, BOQFilePath, telephoneModelFilePath,
				consultParameFilePath, defaultWindowID);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 任务描述
		CommonJQ.value(driver, RANRemoteDeliveryPage.taskDescArea, taskDescArea, true);
		SwitchDriver.switchDriverToSEQ(driver);
		String taskName_Star = RANRemoteDeliveryPage.setTaskName(driver, taskName);
		RANRemoteDeliveryPage.commitBtnClick(driver, submitState);

		return taskName_Star;
	}

	/**
	 * 基站与天馈设计 测试用例
	 **/
	public static String creatAntennaFeederTask(WebDriver driver, String taskName, String IOAFilePath, String workType,
			String[] paySetMeal, String[] number, String secnsChoose, String netContent, String projectDocumentFilePath,
			String[] stockConfigurationFilePath, String bindingRelationFilePath, String baseInformationFilePath,
			String antennaFeederInformationFilePath, String itemsRegulationFilePath, String taskDescArea,
			String submitState, String defaultWindowID) {
		NetworkSafeGuardCenterTask.openAutoRANData(driver);
		// 打开新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// IOA 文件
		if (IOAFilePath != null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAFileBtn, true);//
			LoadingPage.Loading(driver, RANRemoteDeliveryPage.IOAFileBtn, "IOA选择文件->确认");
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm, true);
			CommonFile.ChooseOneFile(IOAFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm, true);
		}
		// 业务类型
		CommonJQ.click(driver, RANRemoteDeliveryPage.WorkTypeBtn, true);// WorkType
		if ("网络设计_RAN".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 0);// WorkType
		} else if ("基站与天馈设计".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 1);// WorkType
		} else if ("网络设计_OSS".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 2);// WorkType
		} else if ("Summary定制".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 3);// WorkType
		} else if ("网络评估".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 4);// WorkType
		} else if ("基站集成调测".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 5);// WorkType
		} else if ("基站脚本制作".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 6);// WorkType
		} else if ("勘测设计".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 7);// WorkType
		} else if ("验收".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 8);// WorkType
		}
		SwitchDriver.switchDriverToSEQ(driver);
		// 套餐包选择
		RANRemoteDeliveryPage.setmealPackage(driver, paySetMeal, number);
		// 场景设计
		RANRemoteDeliveryPage.secnsDesign_AntennaFeeder(driver, secnsChoose, netContent, projectDocumentFilePath);
		// 上传采集数据
		RANRemoteDeliveryPage.collectData_AntennaFeeder(driver, stockConfigurationFilePath, bindingRelationFilePath,
				baseInformationFilePath, antennaFeederInformationFilePath, itemsRegulationFilePath, defaultWindowID);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 任务描述
		CommonJQ.value(driver, RANRemoteDeliveryPage.taskDescArea, taskDescArea, true);
		SwitchDriver.switchDriverToSEQ(driver);
		String taskName_Star = RANRemoteDeliveryPage.setTaskName(driver, taskName);
		RANRemoteDeliveryPage.commitBtnClick(driver, submitState);
		return taskName_Star;
	}

	public static String creatOtherTask(WebDriver driver, String taskName, String IOAFilePath, String workType,
			String[] paySetMeal, String[] number, String taskDescArea, String submitState) {
		NetworkSafeGuardCenterTask.openAutoRANData(driver);
		// 打开新建任务
		CommonJQ.click(driver, TaskReportPage.CreateTask, true);
		LoadingPage.Loading(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// IOA 文件
		if (IOAFilePath != null) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAFileBtn, true);//
			LoadingPage.Loading(driver, RANRemoteDeliveryPage.IOAFileBtn, "IOA选择文件->确认");
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm, true);
			CommonFile.ChooseOneFile(IOAFilePath);
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm, true);
		}
		// 业务类型
		CommonJQ.click(driver, RANRemoteDeliveryPage.WorkTypeBtn, true);// WorkType
		if ("网络设计_RAN".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 0);// WorkType
		} else if ("基站与天馈设计".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 1);// WorkType
		} else if ("网络设计_OSS".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 2);// WorkType
		} else if ("Summary定制".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 3);// WorkType
		} else if ("网络评估".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 4);// WorkType
		} else if ("基站集成调测".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 5);// WorkType
		} else if ("基站脚本制作".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 6);// WorkType
		} else if ("勘测设计".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 7);// WorkType
		} else if ("验收".equalsIgnoreCase(workType)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.WorkType, true, 8);// WorkType
		}
		SwitchDriver.switchDriverToSEQ(driver);
		// 套餐包选择
		RANRemoteDeliveryPage.setmealPackage(driver, paySetMeal, number);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 任务描述
		CommonJQ.value(driver, RANRemoteDeliveryPage.taskDescArea, taskDescArea, true);
		SwitchDriver.switchDriverToSEQ(driver);
		String taskName_Star = RANRemoteDeliveryPage.setTaskName(driver, taskName);
		RANRemoteDeliveryPage.commitBtnClick(driver, submitState);
		return taskName_Star;
	}

	// 接口人处理
	public static void GSCInterfaceHandle(WebDriver driver, String taskName, boolean IOADownload, String idea,
			String report, String describe) {
		NetworkSafeGuardCenterTask.openAutoRANData(driver);
		String taskId = TaskReportTask.RANAsserTaskState(driver, taskName, "Intefaces");
		CommonJQ.click(driver, "#" + taskId, true);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 下载IOA文件
		if (IOADownload) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.taskEditBtn, true);
			FileHandle.delSubFile(ConstUrl.DownLoadPath);// 设置浏览器下载路径
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOADownloadBtn, true, "IOA模板");
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm, true);
			TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, "IOA模板");
		}
		if ("转发给专家".equals(idea)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.taskAcceptSendToExpertBtn, true);
		} else if ("拒绝".equals(idea)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.taskAcceptReject, true);
		}
		CommonJQ.siblingsClick(driver, RANRemoteDeliveryPage.reportBtn, "span");
		CommonJQ.click(driver, "#" + report, true);//// gwx159415
		CommonJQ.value(driver, RANRemoteDeliveryPage.textarea, describe, true);
		CommonJQ.click(driver, RANRemoteDeliveryPage.treatmentActionBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	public static void reportHandle(WebDriver driver, String taskName, boolean IOADownload, String[] fileUploadPath,
			String idea, String describe, String defaultWindowID) {
		//
		NetworkSafeGuardCenterTask.openAutoRANData(driver);
		String taskId = TaskReportTask.RANAsserTaskState(driver, taskName, "Expert");
		CommonJQ.click(driver, "#" + taskId, true);
		CommonWD.changePageDriver(driver);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		// 下载IOA文件
		if (IOADownload) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.taskEditBtn, true);
			FileHandle.delSubFile(ConstUrl.DownLoadPath);// 设置浏览器下载路径
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOADownloadBtn, true, "IOA模板");
			CommonJQ.click(driver, RANRemoteDeliveryPage.IOAConfirm, true);
			TaskViewPluginTask.waittingDownLoadFile(ConstUrl.DownLoadPath, "IOA模板");
		}
		if ("接受".equals(idea)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.reportAccess, true);
		} else if ("转发给其他人".equals(idea)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.reportForword, true);
		} else if ("拒绝".equals(idea)) {
			CommonJQ.click(driver, RANRemoteDeliveryPage.reportReject, true);
		}
		CommonJQ.value(driver, RANRemoteDeliveryPage.reportTaskDescArea, describe, true);
		SwitchDriver.switchDriverToSEQ(driver);
		// 上传数据
		if (fileUploadPath != null) {
			GetDataByTypeTask.chooseFolder(driver, fileUploadPath, RANRemoteDeliveryPage.fileUploadBtn,
					defaultWindowID);
		}
		SwitchDriver.switchDriverToFrame(driver, "//iframe");// 嵌入iframe
		CommonJQ.click(driver, RANRemoteDeliveryPage.reportSubmitBtn, true);
		SwitchDriver.switchDriverToSEQ(driver);
	}
}
