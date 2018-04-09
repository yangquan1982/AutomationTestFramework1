package cloud_plugin.task.network_evaluation_assurance_center.video_insight;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cloud_plugin.page.network_evaluation_assurance_center.video_insight.VideoInsightPage;
import cloud_plugin.task.network_evaluation_assurance_center.NetworkSafeGuardCenterTask;
import cloud_public.page.LoadingPage;
import cloud_public.page.TaskReportPage;
import common.constant.system.EnvConstant;
import common.util.CommonJQ;
import common.util.FileHandle;
import common.util.SwitchDriver;

public class VideoInsightTask {

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param taskName
	 *            ：任务名称
	 * @param TaskType
	 *            ：任务类型
	 * @param SEQAllNetFile
	 * @param OTTSheetFile
	 * @param ISPSheetFile
	 * @param SEQCellFile
	 * @param SEQGridFile
	 * @param CfgFile
	 * @param paraFile
	 * @param SigFile
	 * @param NoEncryVideoFile
	 * @param EncryVideoFile
	 * @param eMapFile
	 * @param OTTFile
	 * @param NoToLow
	 *            :非视频用户向低流量视频用户迁移比例
	 * @param LVToH
	 *            :低视频用户向高流量视频用户迁移比例
	 * @param LVUser
	 *            :低流量视频用户门限
	 * @param TopTNum
	 *            :TOP终端个数
	 * @return
	 */
	public static String CreatVideoInsightTask(WebDriver driver, String defaultWindowID, String taskName,
			String TaskType, String SEQAllNetFile, String OTTSheetFile, String ISPSheetFile, String SEQCellFile,
			String SEQGridFile, String[] CfgFile, String paraFile, String[] SigFile, String[] NoEncryVideoFile,
			String[] EncryVideoFile, String[] eMapFile, String[] OTTFile, String NoToLow, String LVToH, String LVUser,
			String TopTNum) {
		taskName = VideoInsightTask.setServiceModule(driver, taskName, TaskType);
		if (TaskType == null) {
			VideoInsightTask.setAysNetData(driver, defaultWindowID, SEQAllNetFile, OTTSheetFile, ISPSheetFile, null,
					null, null, null, null, null, null, null, null);
		} else {
			if (TaskType.contains("小区洞察")) {
				if (TaskType.contains("小区洞察") && TaskType.contains("SEQ栅格")) {
					VideoInsightTask.setAysNetData(driver, defaultWindowID, SEQAllNetFile, OTTSheetFile, ISPSheetFile,
							SEQCellFile, SEQGridFile, null, paraFile, null, null, null, null, null);
				} else if (TaskType.contains("小区洞察") && TaskType.contains("MR栅格")) {
					VideoInsightTask.setAysNetData(driver, defaultWindowID, SEQAllNetFile, OTTSheetFile, ISPSheetFile,
							SEQCellFile, null, CfgFile, paraFile, SigFile, NoEncryVideoFile, EncryVideoFile, eMapFile,
							OTTFile);
				} else {
					VideoInsightTask.setAysNetData(driver, defaultWindowID, SEQAllNetFile, OTTSheetFile, ISPSheetFile,
							SEQCellFile, null, null, paraFile, null, null, null, null, null);
				}

			} else {
				if (TaskType.contains("SEQ栅格")) {
					VideoInsightTask.setAysNetData(driver, defaultWindowID, SEQAllNetFile, OTTSheetFile, ISPSheetFile,
							null, SEQGridFile, null, null, null, null, null, null, null);
				} else if (TaskType.contains("MR栅格")) {
					VideoInsightTask.setAysNetData(driver, defaultWindowID, SEQAllNetFile, OTTSheetFile, ISPSheetFile,
							null, null, CfgFile, paraFile, SigFile, NoEncryVideoFile, EncryVideoFile, eMapFile,
							OTTFile);
				}
			}

		}
		VideoInsightTask.setPara(driver, NoToLow, LVToH, LVUser, TopTNum);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param taskName
	 *            ：任务名称
	 * @param TaskType
	 *            ：任务类型
	 * @return
	 */
	public static String setServiceModule(WebDriver driver, String taskName, String TaskType) {
		// 打开
		NetworkSafeGuardCenterTask.openVideoInsight(driver);
		CommonJQ.click(driver, TaskReportPage.CreateTask, true, "新建任务");
		LoadingPage.Loading(driver);
		// 选中页面中Iframe
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 选中任务名称
		taskName = VideoInsightPage.setTaskName(driver, taskName);
		if (TaskType != null) {
			String[] TaskTypeAtt = TaskType.split(",");
			for (int i = 0; i < TaskTypeAtt.length; i++) {
				if ("小区洞察".equalsIgnoreCase(TaskTypeAtt[i].trim())) {
					VideoInsightPage.CBoxCellSeeClick(driver);
				} else if ("SEQ栅格".equalsIgnoreCase(TaskTypeAtt[i].trim())) {
					VideoInsightPage.CBoxSEQGridClick(driver);
				} else if ("MR栅格".equalsIgnoreCase(TaskTypeAtt[i].trim())) {
					VideoInsightPage.CBoxMRGridClick(driver);
				} else {
					Assert.fail("任务类型错误,正确的应该是:小区洞察、SEQ栅格、MR栅格" + "，TaskType：" + TaskTypeAtt[i]);
				}
			}
		}

		VideoInsightPage.BtnNextClick(driver);
		SwitchDriver.switchDriverToSEQ(driver);
		return taskName;
	}

	/**
	 * 
	 * @param driver
	 * @param defaultWindowID
	 * @param SEQAllNetFile
	 *            :SEQ整网级指标
	 * @param OTTSheetFile
	 *            :OTT协议映射表
	 * @param ISPSheetFile
	 *            :ISP流向映射表
	 * @param SEQCellFile
	 *            :SEQ小区级指标
	 * @param SEQGridFile
	 *            :SEQ栅格级指标
	 * @param CfgFile
	 *            :配置数据
	 * @param paraFile
	 *            :工参数据
	 * @param SigFile
	 *            :SIG数据
	 * @param NoEncryVideoFile
	 *            :非加密视频源
	 * @param EncryVideoFile
	 *            :加密视频源
	 * @param eMapFile
	 *            :电子地图
	 * @param OTTFile
	 *            :OTT
	 */
	public static void setAysNetData(WebDriver driver, String defaultWindowID, String SEQAllNetFile,
			String OTTSheetFile, String ISPSheetFile, String SEQCellFile, String SEQGridFile, String[] CfgFile,
			String paraFile, String[] SigFile, String[] NoEncryVideoFile, String[] EncryVideoFile, String[] eMapFile,
			String[] OTTFile) {

		//
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// SEQ整网级指标
		VideoInsightPage.SEQAllChooseFile(driver, SEQAllNetFile);
		// OTT协议映射表
		VideoInsightPage.OTTSheetChooseFile(driver, OTTSheetFile);
		// ISP流向映射表
		VideoInsightPage.ISPSheetChooseFile(driver, ISPSheetFile);
		// SEQ小区级指标
		VideoInsightPage.SEQCellChooseFile(driver, SEQCellFile);
		// SEQ栅格级指标
		VideoInsightPage.SEQGridChooseFile(driver, SEQGridFile);
		SwitchDriver.switchDriverToSEQ(driver);

		// 配置数据
		VideoInsightPage.CfgChooseFile(driver, defaultWindowID, CfgFile);
		// 工参数据
		VideoInsightPage.ParaChooseFile(driver, defaultWindowID, paraFile);
		// SIG数据
		VideoInsightPage.SigChooseFile(driver, defaultWindowID, SigFile);
		// 非加密视频源
		VideoInsightPage.NoEncryVideoChooseFile(driver, defaultWindowID, NoEncryVideoFile);
		// 加密视频源
		VideoInsightPage.EncryVideoChooseFile(driver, defaultWindowID, EncryVideoFile);
		// 加密视频源
		VideoInsightPage.eMapChooseFile(driver, defaultWindowID, eMapFile);
		// OTT
		VideoInsightPage.OTTChooseFile(driver, defaultWindowID, OTTFile);

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		VideoInsightPage.BtnNextClick(driver);
		SwitchDriver.switchDriverToSEQ(driver);
	}

	/**
	 * 
	 * @param driver
	 * @param NoToLow
	 *            :非视频用户向低流量视频用户迁移比例
	 * @param LVToH
	 *            :低视频用户向高流量视频用户迁移比例
	 * @param LVUser
	 *            :低流量视频用户门限
	 * @param TopTNum
	 *            :TOP终端个数
	 */
	public static void setPara(WebDriver driver, String NoToLow, String LVToH, String LVUser, String TopTNum) {

		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// 非视频用户向低流量视频用户迁移比例（%）
		VideoInsightPage.SetNoToLow(driver, NoToLow);
		// 低视频用户向高流量视频用户迁移比例（%）
		VideoInsightPage.SetLVToH(driver, LVToH);
		// 低流量视频用户门限（MB）
		VideoInsightPage.SetLVUser(driver, LVUser);
		// TOP终端个数
		VideoInsightPage.SetTopTNum(driver, TopTNum);

		VideoInsightPage.BtnSubmitClick(driver);
		SwitchDriver.switchDriverToSEQ(driver);

	}

	/**
	 * 
	 * @param driver
	 * @param TaskType
	 *            :任务类型
	 * @param TempSEQAllNetFlage
	 *            :是否下载SEQ整网级指标模板
	 * @param TempOTTSheetFlage
	 *            :是否下载OTT协议映射表模板
	 * @param TempISPSheetFlage
	 *            :是否下载ISP流向映射表模板
	 * @param TempSEQCellFlage
	 *            :是否下载SEQ小区级指标模板
	 * @param TempSEQGridFlage
	 *            :是否下载SEQ栅格级指标模板
	 * @param ResultHome
	 *            :结果存放路径
	 */
	public static void TemplateDownload(WebDriver driver, String TaskType, boolean TempSEQAllNetFlage,
			boolean TempOTTSheetFlage, boolean TempISPSheetFlage, boolean TempSEQCellFlage, boolean TempSEQGridFlage,
			String ResultHome) {

		VideoInsightTask.setServiceModule(driver, "模板下载临时任务名", TaskType);

		FileHandle.delSubFile(ResultHome);
		FileHandle.delSubFile(EnvConstant.Path_DownLoad);
		FileHandle.makeDirectory(ResultHome);
		SwitchDriver.switchDriverToFrame(driver, "//iframe");
		// SEQ整网级指标
		if (TempSEQAllNetFlage) {
			VideoInsightPage.BtnTempSEQAllNetClick(driver, ResultHome);
		}
		// OTT协议映射表
		if (TempOTTSheetFlage) {
			VideoInsightPage.BtnTempOTTSheetClick(driver, ResultHome);

		}
		// ISP流向映射表
		if (TempISPSheetFlage) {
			VideoInsightPage.BtnTempISPSheetClick(driver, ResultHome);

		}
		// SEQ小区级指标
		if (TempSEQCellFlage) {
			VideoInsightPage.BtnTempSEQCellClick(driver, ResultHome);
		}
		// SEQ栅格级指标
		if (TempSEQGridFlage) {
			VideoInsightPage.BtnTempSEQGridClick(driver, ResultHome);
		}
		SwitchDriver.switchDriverToSEQ(driver);

	}
}
